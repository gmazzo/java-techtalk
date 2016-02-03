package com.globant.techtalk.java.services;

import com.globant.techtalk.java.aspects.SlowCallLog;

@SlowCallLog
public class ServiceImpl implements Service {

	@Override
	public void doSomething(long willTook) {
		try {
			Thread.sleep(150);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Did something and took " + willTook);
	}

	@Override
	@SlowCallLog(1000)
	public void doSomethingElse(long willTook) {
		try {
			Thread.sleep(willTook);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Did something else and took " + willTook);
	}

}
