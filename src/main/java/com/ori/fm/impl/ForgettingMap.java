package com.ori.fm.impl;

/**
 * Basic java Map class.  The size of the Map is fixed with a default values of 1. 
 * elements are always added at the top of the Array which implements the Map functionality.  
 * When a find operation is performed on the Map, the corresponding element is brought to 
 * the top of the Array which implements the Map. Once the Array reaches its max size, 
 * which is passed through the Map constructor, it discards the entity which is at the 
 * bottom of the Array. It has three thread safe methods. The method V put(K key, V value) 
 * is the best as it blocks add() and find() methods grunting the called that entity values is 
 * not modified immediately after it was added to the map by other threads.
 * 
 * @author T1076073
 *
 * @param <K>
 * @param <V>
 */

public class ForgettingMap<K, V> {
	
	private KeyValuePair<K, V> keyValuePair[];
	private int capacity;
	// The static Array may not be full initially, the actual size need to be tracked. 
	private int actualSize;
	
	
    @SuppressWarnings("unchecked")
	public ForgettingMap(int capacity) {
    	this.capacity = (capacity < 1)? 1 : capacity;
    	this.keyValuePair = new KeyValuePair[capacity];
    }
	
	
	
	public synchronized void add(K key, V value) {
		boolean insertflag = true;
	    for (int i = 0; i < actualSize; i++) {
				if (keyValuePair[i].getKey().equals(key)) {
					keyValuePair[i].setValue(value);
					insertflag = false;
				}
		}
		
		//Insert through HotArray implementation 
	    if (insertflag) {
	    		push(new KeyValuePair<K, V>(key, value));
	    }
	}
	
	
	
	public synchronized V find(K key) {
		for (int i = 0; i < capacity; i++) {
			if (keyValuePair[i] != null) {
				if (keyValuePair[i].getKey().equals(key)) {
					V value = keyValuePair[i].getValue();
					// rearrange elements in side the array once accessed
					rearrange(i);
					return value;
	            }			
	        }
	    }
		return null;
	}
	
	
	
	private void rearrange(int position) {
    	if(position > 0 && position <= keyValuePair.length) {
    		KeyValuePair<K, V> keyValuePair_touched = keyValuePair[position];
     	   for (int i = position; i > 0; i-- ) {
     		  keyValuePair[i] = keyValuePair[i-1];   	   
     	   }
     	  keyValuePair[0] = keyValuePair_touched;
        }
    }
	
	
	
	private void push(KeyValuePair<K, V> keyValuePair_tobeadded) {
    	if(keyValuePair[0]==null){
    		keyValuePair[0] = keyValuePair_tobeadded;
    	}else {
    		for(int i=keyValuePair.length-2; i >-1; i--) {
    			if(keyValuePair[i] != null) {
    				keyValuePair[i+1] = keyValuePair[i];
    			}
    	    }
    		keyValuePair[0] = keyValuePair_tobeadded;
    	}
    	if(actualSize < capacity) {
    		actualSize = actualSize + 1;
    	}
    }
	
	/**Thread safe method**/
	public synchronized  V put(K key, V value) {
		add(key,value);
		return find(key);
	}
	
    /** Needed for unit test **/
	public KeyValuePair<K, V>[] getKeyValuePair() {
		return keyValuePair;
	}
	public void setKeyValuePair(KeyValuePair<K, V>[] keyValuePair) {
		this.keyValuePair = keyValuePair;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getActualSize() {
		return actualSize;
	}
	public void setActualSize(int actualSize) {
		this.actualSize = actualSize;
	}
}

