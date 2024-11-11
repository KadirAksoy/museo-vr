//package com.kadiraksoy.museo_vr.aspect;
//
//
//import com.kadiraksoy.museo_vr.model.enums.LogLevel;
//import com.kadiraksoy.museo_vr.model.log.Log;
//import com.kadiraksoy.museo_vr.service.LogService;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAspect {
//
//    private final LogService logService;
//
//    public LoggingAspect(LogService logService) {
//        this.logService = logService;
//    }
//
//    @Before("execution(* com.kadiraksoy.museo_vr.service.*.*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getName();
//        Object[] args = joinPoint.getArgs();
//        String before = "This is logged before any request coming to the service.";
//
//        Log log = new Log(message(methodName, className, args,before), LogLevel.INFO);
//        logService.log(log);
//    }
//    @AfterReturning(pointcut = "execution(* com.kadiraksoy.museo_vr.service.*.*(..))")
//    public  void logAfter(JoinPoint joinPoint){
//
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getName();
//        Object[] args = joinPoint.getArgs();
//        String after = "If the request coming to this service is successful, it will be logged.";
//
//        Log log = new Log(message(methodName, className, args,after), LogLevel.INFO);
//        logService.log(log);
//    }
//
//
//    private String message(String methodName, String className, Object[] args, String status) {
//        StringBuilder message = new StringBuilder();
//        message.append("Status: ").append(status).append("\n");
//        message.append("Method: ").append(methodName).append("\n");
//        message.append("Class: ").append(className).append("\n");
//        if (args.length > 0) {
//            message.append("Arguments:");
//            for (Object arg : args) {
//                message.append("\n - ").append(arg);
//            }
//        } else {
//            message.append("No arguments.");
//        }
//        return message.toString();
//    }
//}
