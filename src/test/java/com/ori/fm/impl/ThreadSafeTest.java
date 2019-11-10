package com.ori.fm.impl;

import com.ori.fm.impl.ForgettingMap;

/**
 * 
 * In this test three threads puts entries in to a single ForgettingMap object simultaneously 
 * and tries to modify the values with its own value.  They key used by all three threads are 
 * same, so there is a very high chances that it will modify the value which was not set by 
 * that thread. The failure scenario can be proved by calling the longWorkNotThreadSafe(Thread_name) 
 * method which are commented. A thread safe method V put(K key, V value) on the Map is provided 
 * which blocks the ForgettingMap object till it return the value added to the Map to the caller.
 * 
 * * @author T1076073
 */

public class ThreadSafeTest {
	
	ForgettingMap<String, String> forgettingMapTH = new ForgettingMap<String, String>(1000);
	
	public static void main(String args[]) { 
		ThreadSafeTest threadSafeTest = new ThreadSafeTest();
		Thread t1 = new Thread(() ->{
			threadSafeTest.longWork("Thread_1");
			//threadSafeTest.longWorkNotThreadSafe("Thread_1");
		});
		Thread t2 = new Thread(() ->{
			threadSafeTest.longWork("Thread_2");
			//threadSafeTest.longWorkNotThreadSafe("Thread_2");
		});
		Thread t3 = new Thread(() ->{
			threadSafeTest.longWork("Thread_3");
			//threadSafeTest.longWorkNotThreadSafe("Thread_3");
		});
		t1.start();
		t2.start();
		t3.start();
	}
    
    
    public void longWork(String thread_name) {
    	String keyprefix="ChangeMe";
    	int count = 0;
    	while(count < 100000) {
    		for(int i =0; i <10; i++) {
    			String key = keyprefix+""+i;
    			String value = thread_name+""+i;
    			// Thread safe method
    			String actualValues = forgettingMapTH.put(key, value);
    			if(! value.equals(actualValues)) {
    				System.out.println("Object is being tempered by other thread. Expected :"+ value +" Found:"+actualValues+ " by thread "  + thread_name);
    				System.out.println("Failed : Thread safty test PASS");
    				System.exit(1);
    			}
    	}
    	count = count + 1;
     }
    	System.out.println("Pass: Thread safty test PASS  " + thread_name);
	}
    
    public void longWorkNotThreadSafe(String thread_name) {
    	String keyprefix="ChangeMe";
    	int count = 0;
    	while(count < 100000) {
    		for(int i =0; i <10; i++) {
    			String key = keyprefix+""+i;
    			String value = thread_name+""+i;
    			// Not Thread safe method
    			forgettingMapTH.put(key, value);
    			String actualValues = forgettingMapTH.find(key);
    			if(! value.equals(actualValues)) {
    				System.out.println("Object is being tempered by other thread. Expected :"+ value +" Found:"+actualValues+ " by thread "  + thread_name);
    				System.out.println("Failed : Thread safty test Failed");
    				System.exit(1);
    			}
    	}
    	count = count + 1;
     }
    	System.out.println("Pass: Thread safty test PASS  " + thread_name);
	}
}
