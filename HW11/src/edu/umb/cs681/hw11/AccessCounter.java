package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private Map<java.nio.file.Path, Integer> mHashMap = new HashMap<java.nio.file.Path, Integer>();
	private ReentrantLock lock = new ReentrantLock();
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
		lock.lock();
		try {
			if (mHashMap.get(path) != null)
				mHashMap.put(path, mHashMap.get(path) + 1);
			else 
				mHashMap.put(path, 1);
		}
		finally {
			lock.unlock();
		}
	}
	
	public int getCount(Path path) {
		lock.lock();
		try {
			if (mHashMap.get(path) != null)
				return mHashMap.get(path);
			else
				return 0;
		}
		finally {
			lock.unlock();
		}
	}	
}