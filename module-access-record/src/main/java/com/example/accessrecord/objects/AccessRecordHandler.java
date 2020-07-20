package com.example.accessrecord.objects;

import com.example.accessrecord.manager.AccessRecordManager;
import com.example.accessrecord.persistence.entity.AccessRecord;
import com.example.devutils.utils.JsonUtils;
import com.example.devutils.utils.access.RequestUtils;
import com.example.devutils.utils.access.WebUtils;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.jooq.lambda.Unchecked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AMe on 2020-07-11 16:53.
 */
@Component
public class AccessRecordHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccessRecordHandler.class);
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private DesensitizeHandler desensitizeHandler;
    @Autowired
    private VisitorInfoSupplier visitorInfoSupplier;
    @Autowired
    private AccessRecordProps accessRecordProps;
    @Autowired
    private AccessRecordManager accessRecordManager;

    public AccessRecord perfect(JoinPoint joinPoint, AccessRecord accessRecord) {
        HttpServletRequest request = WebUtils.getServletRequest();
        if (request == null) {
            logger.error("HttpServletRequest failed to get");
            return accessRecord;
        }
        Signature signature = joinPoint.getSignature();
        accessRecord.setHandlerClass(signature.getDeclaringTypeName());
        accessRecord.setHandlerMethod(signature.getName());
        accessRecord.setReqParams(desensitizeHandler.desensitize(joinPoint.getArgs()));
        accessRecord.setReqUrl(RequestUtils.getUrl(request));
        Optional.ofNullable(visitorInfoSupplier.get(request))
            .map(visitorInfo -> visitorInfo instanceof String ? (String) visitorInfo : Unchecked.supplier(() -> JsonUtils.toJsonStr(visitorInfo)).get())
            .ifPresent(accessRecord::setVisitor);
        accessRecord.setClientIp(RequestUtils.getIp(request));
        accessRecord.setUserAgent(RequestUtils.getUserAgent(request));
        accessRecord.setScope(accessRecordProps.getScope());
        return accessRecord;
    }

    public void handle(AccessRecord accessRecord) {
        executorService.submit(() -> {
            try {
                accessRecordManager.add(accessRecord);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        });
    }

}
