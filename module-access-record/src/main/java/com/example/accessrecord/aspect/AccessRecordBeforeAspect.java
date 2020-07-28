package com.example.accessrecord.aspect;

import com.example.accessrecord.objects.AccessRecordHandler;
import com.example.accessrecord.persistence.entity.AccessRecord;
import com.example.devutils.utils.access.WebUtils;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AMe on 2020-07-11 14:57.
 */
@Aspect
public class AccessRecordBeforeAspect extends BaseAspect {

    private static final Logger logger = LoggerFactory.getLogger(AccessRecordBeforeAspect.class);

    @Autowired
    private AccessRecordHandler accessRecordHandler;

    @Before("handlerClass() && !ignoreClass() && handlerMethod() && !ignoreMethod()")
    public void beforeAccess(JoinPoint joinPoint) {
        HttpServletRequest request = WebUtils.getServletRequest();
        if (request == null) {
            logger.error("HttpServletRequest failed to get");
        } else {
            AccessRecord accessRecord = new AccessRecord();
            accessRecord.setRespContent(null);
            accessRecord.setRespCode(-1);
            accessRecord.setDuration(-1);
            try {
                accessRecordHandler.perfect(joinPoint, accessRecord);
                accessRecordHandler.handle(accessRecord);
            } catch (Exception ex) {
                logger.error("", ex);
            }
        }
    }

}
