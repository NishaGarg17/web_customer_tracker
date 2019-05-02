package com.luv2code.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	// set up logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// set up point cut declarations
	@Pointcut("execution(* com.luv2code.controller.*.*(..))")
	private void forControllerPackage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.service.*.*(..))")
	private void forServicePackage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.dao.*.*(..))")
	private void forDAOPackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {
		
	}
	// add @Before Advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		// display the method we are calling
		
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("====>>> in @Before: calling method: " + method);
		Object args[] = theJoinPoint.getArgs();
		for(Object arg : args) {
			logger.info("Argument: " + arg);
		}
	}
	// add @AfterReturning Advice
	@AfterReturning(pointcut="forAppFlow()",returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		// display the method we are returning from
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("====>>> in @AfterReturning: from method: " + method);
		// display data returned
		logger.info("====>>> result: " + theResult);
	}
}
