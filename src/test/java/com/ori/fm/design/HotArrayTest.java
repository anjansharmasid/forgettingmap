package com.ori.fm.design;

import com.ori.fm.design.HotArray;

import junit.framework.TestCase;

/** This tests are done to ensure that the behaviour of the Array which 
 * is going to be used to implemented the Forgetting  Map is correct.
 * 
 * @author T1076073
 *
 */

public class HotArrayTest extends TestCase {

	private HotArray hotArray;
	
	protected void setUp() throws Exception {
		super.setUp();
		hotArray = new HotArray(0);
		assertEquals(0,hotArray.getAr().length);
		assertEquals(1,hotArray.getCapacity());
		assertEquals(0,hotArray.getActualSize());
		
	}

	public final void testHotArray() {
		hotArray = new HotArray(4);
		assertEquals(4,hotArray.getAr().length);
		assertEquals(4,hotArray.getCapacity());
		assertEquals(0,hotArray.getActualSize());
	}


	public final void testPush() {
		hotArray = new HotArray(4);
		hotArray.push(1);
		hotArray.push(2);
		hotArray.push(3);
		assertEquals(hotArray.getActualSize(),3);
		int actual0 = hotArray.getAr()[0];
		assertEquals(actual0,3);
		int actual1 = hotArray.getAr()[1];
		assertEquals(actual1,2);
		int actual3 = hotArray.getAr()[2];
		assertEquals(actual3,1);
	}

	public final void testRearrange() {
		hotArray = new HotArray(6);
		hotArray.push(1);
		hotArray.push(2);
		hotArray.push(3);
		assertEquals(hotArray.getActualSize(),3);
		int actual0 = hotArray.getAr()[0];
		assertEquals(actual0,3);
		int actual1 = hotArray.getAr()[1];
		assertEquals(actual1,2);
		int actual3 = hotArray.getAr()[2];
		assertEquals(actual3,1);
		hotArray.push(4);
		int actual4 = hotArray.getAr()[0];
		assertEquals(actual4,4);
	}
}
