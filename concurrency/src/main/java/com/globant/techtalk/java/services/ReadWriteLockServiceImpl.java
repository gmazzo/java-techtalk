package com.globant.techtalk.java.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This {@link Service}, is thread-safe, and will allow concurrent
 * {@link Service#report(String)} operations, an just block when collecting
 * 
 */
public class ReadWriteLockServiceImpl extends AbstractService {
	private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

	public ReadWriteLockServiceImpl() {
		super(new CopyOnWriteArrayList<>()); // report is a "write" op, we require a safe collection to store the data
	}

	@Override
	public boolean report(String value) {
		lock.readLock().lock(); // shared lock, will allow concurrent report ops
		try {
			return super.report(value);

		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public List<String> collect() {
		lock.writeLock().lock(); // exclusive lock, will hold any other op
		try {
			return super.collect();

		} finally {
			lock.writeLock().unlock();
		}
	}

}
