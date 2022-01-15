package com.gizone.client.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yyt
 */
@Slf4j
@Aspect
@Component
public class LogParamAspect {

    @Autowired
    HttpServletRequest request;


    @Pointcut("(execution(* com.gizone.client.controller..*(..)))")
    public void postPutAspect(){}

    @Before(value="postPutAspect()")
    public void doBefore(JoinPoint joinPoint) {
        int bodyIndex = isUseBody(joinPoint);
        log.info(request.getRequestURI());
        if(bodyIndex > -1) {
            log.info("request body:{}", JSON.toJSONString(joinPoint.getArgs()[bodyIndex]));
        }else {
            // log.debug("request form:{}", JSON.toJSONString(request.getParameterMap()));
        }
    }

    /**
     * 返回 > -1 值则为找到
     * @param pjp
     * @return
     */
    private int isUseBody(final JoinPoint pjp) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();
        Annotation[][] annotations = method.getParameterAnnotations();

        for (int i = 0; i < annotations.length; i++) {
            Annotation[] annotationArray = annotations[i];
            for (Annotation annotation : annotationArray) {
                if(annotation.annotationType().isAssignableFrom(RequestBody.class)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
