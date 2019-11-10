package com.ori.fm.impl;

import com.ori.fm.impl.ForgettingMap;
import junit.framework.TestCase;
public class ForgettingMapTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		ForgettingMap<?, ?> forgettingMap = new ForgettingMap<Object, Object>(0);
		assertEquals(0,forgettingMap.getKeyValuePair().length);
		assertEquals(1,forgettingMap.getCapacity());
		assertEquals(0,forgettingMap.getActualSize());
	}

	public final void testForgettingMap() {
		ForgettingMap<?, ?> forgettingMap = new ForgettingMap<Object, Object>(100);
		assertEquals(100,forgettingMap.getKeyValuePair().length);
		assertEquals(100,forgettingMap.getCapacity());
		assertEquals(0,forgettingMap.getActualSize());
	}

	public final void testAdd() {
		// Array size and numbers of entries are equal
		ForgettingMap<String, String> forgettingMap = new ForgettingMap<String, String>(10);
		for (int i=0; i< 10; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		assertEquals(10,forgettingMap.getKeyValuePair().length);
		assertEquals(10,forgettingMap.getCapacity());
		assertEquals(10,forgettingMap.getActualSize());	
		
		// Small Array size than the numbers of entries added
		forgettingMap = new ForgettingMap<String, String>(5);
		for (int i=0; i< 10; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		assertEquals(5,forgettingMap.getKeyValuePair().length);
		assertEquals(5,forgettingMap.getCapacity());
		assertEquals(5,forgettingMap.getActualSize());	
		
	    // Big Array size than the numbers of entries added
		forgettingMap = new ForgettingMap<String, String>(100);
		for (int i=0; i< 10; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		assertEquals(100,forgettingMap.getKeyValuePair().length);
		assertEquals(100,forgettingMap.getCapacity());
		assertEquals(10,forgettingMap.getActualSize());	
		
		// Test no duplicates
		forgettingMap = new ForgettingMap<String, String>(5);
		for (int i=0; i< 5; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		
		// Add same key values again
		for (int i=0; i< 5; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		assertEquals(5,forgettingMap.getKeyValuePair().length);
		assertEquals(5,forgettingMap.getCapacity());
		assertEquals(5,forgettingMap.getActualSize());
		
		
		// Test Forgetting behaviour 
		forgettingMap = new ForgettingMap<String, String>(5);
		for (int i=0; i< 10; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		assertEquals(5,forgettingMap.getKeyValuePair().length);
		assertEquals(5,forgettingMap.getCapacity());
		assertEquals(5,forgettingMap.getActualSize());	
	    
		// These values are forgotten 
	    for (int i = 0; i <5 ; i++) {
		  assertNull(forgettingMap.find("Key"+i));	
		}
	    
	    // These values has taken its (forgotten element's) place
	    for (int i = 5; i <10 ; i++) {
			  assertEquals(forgettingMap.find("Key"+i),"Value"+i);	
		}
	}

	public final void testFind() {
		ForgettingMap<String, String> forgettingMap = new ForgettingMap<String, String>(200);
		for (int i=0; i< 200; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		assertEquals(200,forgettingMap.getKeyValuePair().length);
		assertEquals(200,forgettingMap.getCapacity());
		assertEquals(200,forgettingMap.getActualSize());	
		
		for (int i=0; i< 200; i++) {
			assertEquals(forgettingMap.find("Key"+i),"Value"+i);
		}	
		assertNull(forgettingMap.find("blahKey"));
	}
	
	// Simulation of  removal of least used Associations through find action 
    public void testRemoveLeastUsed() {
    	// Provision for 10 Elements
    	ForgettingMap<String, String> forgettingMap = new ForgettingMap<String, String>(10);
    	// Add only 10
		for (int i=0; i< 10; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		// Find first 5
		for (int i = 0; i <5 ; i++) {
			  assertEquals(forgettingMap.find("Key"+i),"Value"+i);	
		}
		// Add another 10
		for (int i=20; i< 25; i++) {
			String key = "Key"+i;
			String value = "Value"+i;
			forgettingMap.add(key, value);	
		}
		// Last 5 from the first batch should not exists
		for (int i = 5; i <10 ; i++) {
			  assertNull(forgettingMap.find("Key"+i));	
		}
    }
    

}
