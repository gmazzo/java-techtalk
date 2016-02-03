package com.globant.techtalk.java.aspects;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annoate a class or method to log an event if it tooks more than the given
 * time threshold to run *
 */
@Documented
@Target({
	ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface SlowCallLog {

	/**
	 * The time threshold, in milliseconds. Defaults to 200ms.
	 */
	long value() default 200;

}
