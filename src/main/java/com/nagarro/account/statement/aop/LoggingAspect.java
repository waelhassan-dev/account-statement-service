package com.nagarro.account.statement.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggingAspect {


	@Pointcut("execution(* com.nagarro.account.statement.controllers.*.*(..))")
	private void forControllerPackages() {}
	
	@Pointcut("execution(* com.nagarro.account.statement.service.*.*(..))")
	private void forServicePackages() {}

	@Pointcut("execution(* com.nagarro.account.statement.repository.*.*(..))")
	private void forRepositoryPackages() {}
	
	@Pointcut("forControllerPackages() || forServicePackages() || forRepositoryPackages() ")
	private void forTransactionFlow() {}
	
	
	
	@Before("forTransactionFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		log.info("in .. calling " + theMethod);
		
		Object[] args = theJoinPoint.getArgs();
		for(Object tempArg : args) {
			log.info("passed args: " + tempArg);
		}
	}
	
	
	@AfterReturning(pointcut = "forTransactionFlow()", returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		String theMethod = theJoinPoint.getSignature().toShortString();
		log.info("out .. from: " + theMethod);
		
		log.info("outcome result: " + theResult);
		
	}
	
}
