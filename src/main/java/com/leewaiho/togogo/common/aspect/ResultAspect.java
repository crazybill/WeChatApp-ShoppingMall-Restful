package com.leewaiho.togogo.common.aspect;

import com.leewaiho.togogo.common.exception.CheckException;
import com.leewaiho.togogo.common.pojo.Result;
import com.leewaiho.togogo.common.util.HttpContextUtil;
import com.leewaiho.togogo.common.util.IpAddressUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
@Component
@Aspect
public class ResultAspect {
    
    private Logger logger = LoggerFactory.getLogger(ResultAspect.class);
    
    
    @Pointcut("execution(public com.leewaiho.togogo.common.pojo.Result *(..))")
    public void Result() {
    }
    
    @Before("Result()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        HttpServletRequest request = HttpContextUtil.getRequest();
        // 记录下请求内容
        logger.info("================ {} ================", request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + IpAddressUtil.getIpAddress(request));
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("================= Http Request End =================");
    }
    
    @Around("Result()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Result<?> result;
        try {
            result = (Result<?>) joinPoint.proceed();
            logger.info("Method: " + joinPoint.getSignature() + " use Time: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable throwable) {
            result = handlerException(joinPoint, throwable);
        }
        return result;
    }
    
    public Result<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        
        Result<?> result = new Result();
        
        // 已知异常
        if (e instanceof CheckException) {
            result.setMessage(e.getLocalizedMessage());
            result.setSuccess(false);
        } else {
            logger.error(pjp.getSignature() + " error ", e);
            result.setMessage(e.toString());
            result.setSuccess(false);
            // 未知异常是应该重点关注的，这里可以做其他操作，如通知邮件，单独写到某个文件等等。
        }
        
        return result;
    }
    
    @AfterReturning(returning = "result", pointcut = "Result()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容
        logger.info("Response : " + result);
    }
}
