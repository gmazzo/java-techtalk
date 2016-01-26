package com.globant.techtalk.java.services;

public class Service1Impl implements Service {

	public Service1Impl() {
		System.out.println(getClass().getSimpleName() + ": initialized");
	}

	@Override
	public boolean doSomething() {
		System.out.println(getClass().getSimpleName() + ": something");
		return true;
	}

	@Override
	public void doSomethingElse() {
		System.out.println(getClass().getSimpleName() + ": something else");
	}

}
