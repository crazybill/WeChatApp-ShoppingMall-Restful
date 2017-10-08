package com.leewaiho.togogo.common.aspect;

import com.leewaiho.togogo.common.Const.ServiceCode;
import com.leewaiho.togogo.common.exception.CheckException;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.pojo.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
        // 记录下请求内容
        logger.info("触发方法 : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info("传递参数 : {} ", Arrays.toString(joinPoint.getArgs()));
    }
    
    @Around("Result()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Result<?> result;
        try {
            result = (Result<?>) joinPoint.proceed();
            logger.info("方法: {} 使用的时间: {} ms", joinPoint.getSignature(), (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable throwable) {
            result = handlerException(joinPoint, throwable);
        }
        return result;
    }
    
    public Result<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        
        Result<?> result = new Result();
        
        // 已知异常
        if (e instanceof CheckException) {
            if (e instanceof ServiceException) {
                ServiceException se = ((ServiceException) e);
                if (se.getCode() != null)
                    result.setCode(se.getCode().getCode());
                result.setMessage(se.getMessage());
            } else {
                result.setCode(ServiceCode.UNKNOWED.getCode());
                result.setMessage(e.getLocalizedMessage());
            }
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
        logger.info("响应内容 : {}", result);
    }
}
