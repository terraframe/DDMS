package dss.vector.solutions;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;


public class StatisticsTest extends TestCase {

	private static final double QUARTILE_TOLERANCE = 0.0000001;

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	public void testQuartile() {
		Statistics s = new Statistics();

		List<Double> list1 = Arrays.asList(0.0, 2.0, 3.0, 5.0, 6.0, 8.0, 9.0);
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
		
		List<Double> list2 = Arrays.asList(1.0, 3.0, 3.0, 4.0, 4.0, 5.0, 6.0);
		assertEquals(1.0, s.quartile(list2, 0), QUARTILE_TOLERANCE);
		assertEquals(3.0, s.quartile(list2, 1), QUARTILE_TOLERANCE);
		assertEquals(4.0, s.quartile(list2, 2), QUARTILE_TOLERANCE);
		assertEquals(4.5, s.quartile(list2, 3), QUARTILE_TOLERANCE);
		assertEquals(6.0, s.quartile(list2, 4), QUARTILE_TOLERANCE);
		
		List<Double> list3 = Arrays.asList(1.0, 3.0, 4.0, 4.0, 5.0, 5.0, 6.0);
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
