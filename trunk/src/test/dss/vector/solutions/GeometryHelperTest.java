package dss.vector.solutions;


import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;

import dss.vector.solutions.util.GeometryHelper;

import junit.framework.TestCase;

public class GeometryHelperTest extends TestCase {
	GeometryHelper helper;

	protected void setUp() throws Exception {
		super.setUp();
		helper = new GeometryHelper();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSquare() {
		this.test("LINESTRING (0 0, 0 100, 100 100, 100 0, 0 0)", "MULTIPOLYGON (((0 0, 0 100, 100 100, 100 0, 0 0)))");
	}
	
	public void testSingleHorizontal() {
		this.test("LINESTRING (0 0, 0 100)", "MULTIPOLYGON (((-1 0, -1 100, 1 100, 1 0, -1 0)))");
	}
	
	public void testSingleVertical() {
		this.test("LINESTRING (0 0, 100 0)", "MULTIPOLYGON (((0 1, 100 1, 100 -1, 0 -1, 0 1)))");
	}
	
	public void testDoubleHorizontal() {
		this.test("LINESTRING (0 0, 0 50, 0 100)", "MULTIPOLYGON (((-1 0, -1 50, -1 100, 1 100, 1 50, 1 0, -1 0)))");
	}
	
	public void testDoubleVertical() {
		this.test("LINESTRING (0 0, 50 0, 100 0)", "MULTIPOLYGON (((0 1, 50 1, 100 1, 100 -1, 50 -1, 0 -1, 0 1)))");
	}
	
	public void testM() {
		this.test("LINESTRING (0 0, 0 100, 50 50, 100 100, 100 0)", "MULTIPOLYGON (((-1 0, -0.3826834323650898 100.9238795325113, 50 51, 100.38268343236508 100.9238795325113, 101 0, 99 0, 99.61731656763492 99.0761204674887, 50 49, 0.3826834323650898 99.0761204674887, 1 0, -1 0)))");
	}
	
	public void testW() {
		this.test("LINESTRING (0 100, 0 0, 50 50, 100 0, 100 100)", "MULTIPOLYGON (((-1 100, -0.3826834323650898 -0.9238795325112868, 50 49, 100.38268343236508 -0.9238795325112868, 101 100, 99 100, 99.61731656763492 0.9238795325112868, 50 51, 0.3826834323650898 0.9238795325112868, 1 100, -1 100)))");
	}
	
	public void testE() {
		this.test("LINESTRING (100 100, 0 100, 50 50, 0 0, 100 0)", "MULTIPOLYGON (((100 101, -0.9238795325112868 100.38268343236508, 49 50, -0.9238795325112868 -0.3826834323650898, 100 -1, 100 1, 0.9238795325112868 0.3826834323650898, 51 50, 0.9238795325112868 99.61731656763492, 100 99, 100 101)))");
	}
	
	public void test3() {
		this.test("LINESTRING (0 100, 100 100, 50 50, 100 0, 0 0)", "MULTIPOLYGON (((0 101, 100.9238795325113 100.38268343236508, 51 50, 100.9238795325113 -0.3826834323650898, 0 -1, 0 1, 99.0761204674887 0.3826834323650898, 49 50, 99.0761204674887 99.61731656763492, 0 99, 0 101)))");
	}
	
	
	private void test(String wktIn, String wktOut) {
		Geometry g  = helper.getGeometry(wktIn);
		assertEquals(wktOut, helper.getGeoMultiPolygon(g).toText());
	}
}