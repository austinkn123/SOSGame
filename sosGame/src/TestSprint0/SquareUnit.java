package TestSprint0;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SquareUnit {

	@Test
	public void test1Square() {
		TestFunction obj = new TestFunction();
		assertEquals(16, obj.square(4));
	}
	
	@Test
	public void test2IfStatement() {
		TestFunction obj2 = new TestFunction();
		String output_function = obj2.determineGrade('A');
		assertEquals("Excellent", output_function);
	}
	
	@Test
	public void test3ArrayComparison() {
		int[] Arr = {20,30,40,50,60,70,80, 120};
		TestFunction obj3 = new TestFunction();
		int[] output_function = obj3.insertionSort();
//		assertEquals([10, 20, 30, 40, 50, 60, 70, 80], output_function);
		assertTrue(Arrays.equals(Arr, output_function));
	}

}