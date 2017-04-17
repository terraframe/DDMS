package dss.vector.solutions;

import junit.framework.TestCase;


public class StatisticsTest extends TestCase {

	private static final double QUARTILE_TOLERANCE = 0.0000001;

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	public void testQuartile() {
		Statistics s = new Statistics();

		double[] list1 = {0.0d, 2.0d, 3.0d, 5.0d, 6.0d, 8.0d, 9.0d};
		assertEquals(0.0, s.quartile(list1, 0), QUARTILE_TOLERANCE);
		assertEquals(2.5, s.quartile(list1, 1), QUARTILE_TOLERANCE);
		assertEquals(5.0, s.quartile(list1, 2), QUARTILE_TOLERANCE);
		assertEquals(7.0, s.quartile(list1, 3), QUARTILE_TOLERANCE);
		assertEquals(9.0, s.quartile(list1, 4), QUARTILE_TOLERANCE);
		
		try {
			s.quartile(list1, -1);
			fail();
		} catch (UnsupportedOperationException uoe) {
			// Correct operation
		} catch (Exception e) {
			fail();
		}
		
		try {
			s.quartile(list1, 5);
			fail();
		} catch (UnsupportedOperationException uoe) {
			// Correct operation
		} catch (Exception e) {
			fail();
		}
		
		double[] list2 = {1.0d, 3.0d, 3.0d, 4.0d, 4.0d, 5.0d, 6.0d};
		assertEquals(1.0, s.quartile(list2, 0), QUARTILE_TOLERANCE);
		assertEquals(3.0, s.quartile(list2, 1), QUARTILE_TOLERANCE);
		assertEquals(4.0, s.quartile(list2, 2), QUARTILE_TOLERANCE);
		assertEquals(4.5, s.quartile(list2, 3), QUARTILE_TOLERANCE);
		assertEquals(6.0, s.quartile(list2, 4), QUARTILE_TOLERANCE);
		
		double[] list3 = {1.0d, 3.0d, 4.0d, 4.0d, 5.0d, 5.0d, 6.0d};
		assertEquals(1.0, s.quartile(list3, 0), QUARTILE_TOLERANCE);
		assertEquals(3.5, s.quartile(list3, 1), QUARTILE_TOLERANCE);
		assertEquals(4.0, s.quartile(list3, 2), QUARTILE_TOLERANCE);
		assertEquals(5.0, s.quartile(list3, 3), QUARTILE_TOLERANCE);
		assertEquals(6.0, s.quartile(list3, 4), QUARTILE_TOLERANCE);
	}
	
	public void testBinomial() {
		Statistics s = new Statistics();
		
		assertEquals(6.37, s.binomial(2.0952381, 182981, 0.05), 0.01);
		assertEquals(8.46, s.binomial(2.0952381, 182981, 0.01), 0.01);
		
	}
}
