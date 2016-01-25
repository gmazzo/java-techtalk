package com.globant.techtalk.java;

import com.globant.techtalk.java.ClassDescriber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClassDescriberTest {
	private final Class<?> targetClass;
	private ClassDescriber describer;

	@Parameters(name = "{0}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{
				ClassDescriberTest.class
			}, {
				ClassDescriber.class
			}, {
				Object.class
			}, {
				String.class
			}, {
				Set.class
			}, {
				ArrayList.class
			}
		});
	}

	public ClassDescriberTest(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	@Before
	public void setup() {
		describer = new ClassDescriber(targetClass);
	}

	@Test
	public void testDescribe() {
		String description = describer.describe();
		System.out.println(description);
		System.out.println("----------------------");
	}

}
