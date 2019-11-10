package com.ori.fm.design;

/**
 * 
 * @author T1076073
 * 
 * Design of a Forgetting Map using  java Arrays. This is a POC class which 
 * demonstrates the design logic. This class implements a Java Arrays data 
 * structure to keep track of what entries (KeyValuePair) are being added  
 * to the Map , what  has been searched on the Map. Position of the unused 
 * entries (KeyValuePair) are pushed down this Array. Every add() and find() 
 * operation on the Map triggers the array element swap.
 *
 */

public class HotArray {
	
	private int capacity;
	private int actualSize;
	private Integer ar[];
	
	
	public HotArray(int capacity) {
		this.capacity = (capacity < 1) ? 1 : capacity ;
		ar = new Integer[capacity];
    }
	
	
	
	/**
	 * This method is call when there is a add() or find() operation on the Map
	 * @param key : Position of the KeyValuePair object on the Array implementation of the Map
	 */
    public synchronized void push(int key) {
    	if(ar[0]==null){
    		ar[0] = key;
    		actualSize = actualSize +1;
    	}else {
    		for(int i=ar.length-2; i >-1; i--) {
    			if(ar[i] != null) {
    				ar[i+1] = ar[i];
    			}
    	    }
    		ar[0] = key;
    		if(actualSize < capacity) {
    			actualSize = actualSize + 1;
    		}
    	}
    }
	
	
	
    /**
     * 
     * @param position
     * 
     * Rearrange array elements, recently access elements should have a higher index.
     */
    @SuppressWarnings("unused")
	private synchronized void rearrange(int position) {
    	if(position > 0 && position <= actualSize) {
     	   int tmp = ar[position];
     	   for (int i = position; i > 0; i-- ) {
                 ar[i] = ar[i-1];   	   
     	   }
     	   ar[0] = tmp;
        }
    }
    
   
	
    /**
     * The tailing entries of the Array is not recently user compared to other so can be removed. 
     */
    public synchronized int getRemoveablePosition() {
    	for (int i = actualSize-1; i > -1 ; i++) {
    		if (ar[i] !=null){
    		System.out.print(ar[i]);
    		return ar[i];
    		}
    	}
    	return 0;
    }
	
    public synchronized int putAndGetPosition(int key) {
		push(key);
		return getRemoveablePosition();
	}
	
    /** Needed for test purpose only **/
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
	public Integer[] getAr() {
		return ar;
	}
	public void setAr(Integer[] ar) {
		this.ar = ar;
	}
  
}    
