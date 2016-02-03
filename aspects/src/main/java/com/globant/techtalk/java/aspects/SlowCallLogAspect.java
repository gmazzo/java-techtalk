package com.globant.techtalk.java.aspects;

import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SlowCallLogAspect {
	private static final Logger LOGGER = Logger
		.getLogger(SlowCallLog.class.getName());

	/**
	 * Intercepts any method for a class annotated with {@link SlowCallLog}
	 */
	@Around("@within(slowCallLog)")
	public Object interceptAnnotatedClass(ProceedingJoinPoint joinPoint,
		SlowCallLog slowCallLog) throws Throwable {
		return interceptCall(joinPoint, slowCallLog);
	}

	/**
	 * Intercepts any method annotated with {@link SlowCallLog}
	 */
	@Around("@annotation(slowCallLog)")
	public Object interceptAnnotatedMethod(ProceedingJoinPoint joinPoint,
		SlowCallLog slowCallLog) throws Throwable {
		return interceptCall(joinPoint, slowCallLog);
	}

	private Object interceptCall(ProceedingJoinPoint joinPoint,
		SlowCallLog slowCallLog) throws Throwable {
		long start = System.currentTimeMillis(); // first pick the start time

		try {
			return joinPoint.proceed(); // this will execute the target method (using reflection)

		} finally {
			long took = System.currentTimeMillis() - start; // calculates the time it took

			if (took > slowCallLog.value()) {
				LOGGER.warning("Call of " + joinPoint.toShortString() + " took "
					+ took + "ms");
			}
		}
	}
}
