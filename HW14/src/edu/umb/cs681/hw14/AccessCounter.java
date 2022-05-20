package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	
	private ConcurrentHashMap<Path, AtomicInteger> mHashMap = new ConcurrentHashMap<Path, AtomicInteger>();
	private static ReentrantLock staticlock = new ReentrantLock();
	private static AccessCounter instance = null;
	private AccessCounter() {};	
	
	public static AccessCounter getInstance() {
		staticlock.lock();
		try {
			if (instance == null)
				instance = new AccessCounter();
			return instance;
		}
		finally {
			staticlock.unlock();
		}
	}
	
	public void increment(Path path) {
		mHashMap.compute(path, (Path p, AtomicInteger i) -> {
	        if(i == null) {
	            return new AtomicInteger(1);
	        } else {
	            return new AtomicInteger(i.incrementAndGet());
	        }
	    });
	}
	
	public int getCount(Path path) {
		return mHashMap.compute(path, (Path p, AtomicInteger i) -> {
            if(i == null) {
                return new AtomicInteger(0);
            } else {
                return i;
            }
        }).get();
	}	
}