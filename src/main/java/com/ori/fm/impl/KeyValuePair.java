package com.ori.fm.impl;

/**
 * 
 * @author T1076073 Key Value Pair to be used inside ForgettingMap
 */
public class KeyValuePair<K, V> {

	private final K key;
	private V value;
	
	
	
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	
	
	public K getKey() {
		return key;
	}
	
	
	
	public V getValue() {
		return value;
	}
	
	
	
	public void setValue(V value) {
		this.value = value;
	}
	
	
}
