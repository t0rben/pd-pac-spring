package com.prodyna.pac.dialogue.aspect;

import com.prodyna.pac.dialogue.error.NotAuthenticatedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Aspect
@Component
public class SecureBasicAuthAspect {

    @Pointcut(value = "@annotation(com.prodyna.pac.dialogue.annotation.SecureBasicAuth)")
    protected void annotationSecureBasicAuth() {

    }

    @Around("annotationSecureBasicAuth()")
    public Object annotationSecureBasicAuth(ProceedingJoinPoint joinPoint) throws Throwable {

        Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            final String authorization = request.getHeader("Authorization");

            if (authorization == null) {
                HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
                assert response != null;
                response.setStatus(401);
                response.addHeader("WWW-Authenticate", "Basic realm=\"PAC 2019\"");
                return null;
            } else {
                final String[] s = authorization.split(" ");

                if (s.length != 2 || !s[0].equalsIgnoreCase("Basic")) {
                    throw new NotAuthenticatedException();
                }

                String authorizationValue = new String(Base64.getDecoder().decode(s[1]));

                final String[] authorizationValueSplit = authorizationValue.split(":");

                if (authorizationValueSplit.length != 2 || !authorizationValueSplit[0].equals("Torben") || !authorizationValueSplit[1].equals("Bock")) {
                    throw new NotAuthenticatedException();
                } else {
                    return joinPoint.proceed();
                }

            }

        } else {
            log.debug("Not called in the context of an HTTP request");
            return joinPoint.proceed();
        }

    }

}
