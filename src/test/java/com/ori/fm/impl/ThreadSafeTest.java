package com.ori.fm.impl;

import com.ori.fm.impl.ForgettingMap;

/**
 * 
 * In this test three threads put entries in to the ForgettingMap object simultaneously 
 * and tries to modify the values with its own value if the key already exists. The set of  
 * keys used by all three threads are same, so there is a very high chances that it will 
 * modify the value which was not set by that thread. The failure scenario can be demonstrate 
 * by calling the longWorkNotThreadSafe(Thread_name) method which are commented. A thread safe 
 * method V put(K key, V value) on the Map is provided which holds the ForgettingMap object 
 * monitor till it returns the value corresponding to the key, which was added to the Map 
 * back to the caller.
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
