package com.example.accessrecord.aspect;

import com.example.accessrecord.objects.AccessRecordHandler;
import com.example.accessrecord.objects.DesensitizeDataType;
import com.example.accessrecord.objects.DesensitizeHandler;
import com.example.accessrecord.persistence.entity.AccessRecord;
import com.example.devutils.utils.access.WebUtils;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AMe on 2020-07-11 14:57.
 */
@Aspect
public class AccessRecordAroundAspect extends BaseAspect {

    private static final Logger logger = LoggerFactory.getLogger(AccessRecordAroundAspect.class);

    @Autowired
    private DesensitizeHandler desensitizeHandler;
    @Autowired
    private AccessRecordHandler accessRecordHandler;

    @Around("handlerClass() && !ignoreClass() && handlerMethod() && !ignoreMethod()")
    public Object aroundAccess(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = WebUtils.getServletRequest();
        if (request == null) {
            logger.error("HttpServletRequest failed to get");
            return pjp.proceed();
        } else {
            long start = System.currentTimeMillis();
            Object respContent = pjp.proceed();
            long end = System.currentTimeMillis();
            long duration = end - start;

            AccessRecord accessRecord = new AccessRecord();
            accessRecord.setRespContent(desensitizeHandler.desensitize(DesensitizeDataType.RESP_CONTENT, respContent));
            accessRecord.setRespCode(Optional.ofNullable(WebUtils.getServletResponse()).map(HttpServletResponse::getStatus).orElse(-1));
            accessRecord.setDuration((int) duration);
            try {
                accessRecordHandler.perfect(pjp, accessRecord);
                accessRecordHandler.handle(accessRecord);
            } catch (Exception ex) {
                logger.error("", ex);
            }
            return respContent;
        }
    }

}
