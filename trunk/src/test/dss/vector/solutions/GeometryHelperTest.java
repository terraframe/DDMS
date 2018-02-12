/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;


import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;

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
		this.test("LINESTRING (0 0, 0 100)", "MULTIPOLYGON (((0 0, -1 50, 0 100, 1 50, 0 0)))");
	}
	
	public void testSingleVertical() {
		this.test("LINESTRING (0 0, 100 0)", "MULTIPOLYGON (((0 0, 50 1, 100 0, 50 -1, 0 0)))");
	}
	
	public void testDoubleHorizontal() {
		this.test("LINESTRING (0 0, 0 50, 0 100)", "MULTIPOLYGON (((0 0, -1 50, 0 100, 1 50, 0 0)))");
	}
	
	public void testDoubleVertical() {
		this.test("LINESTRING (0 0, 50 0, 100 0)", "MULTIPOLYGON (((0 0, 50 1, 100 0, 50 -1, 0 0)))");
	}
	
	public void testM() {
		this.test("LINESTRING (0 0, 0 100, 50 50, 100 100, 100 0)", "MULTIPOLYGON (((0 0, -0.3826834323650898 100.9238795325113, 50 51, 100.38268343236508 100.9238795325113, 100 0, 99.61731656763492 99.0761204674887, 50 49, 0.3826834323650898 99.0761204674887, 0 0)))");
	}
	
	public void testW() {
		this.test("LINESTRING (0 100, 0 0, 50 50, 100 0, 100 100)", "MULTIPOLYGON (((0 100, -0.3826834323650898 -0.9238795325112868, 50 49, 100.38268343236508 -0.9238795325112868, 100 100, 99.61731656763492 0.9238795325112868, 50 51, 0.3826834323650898 0.9238795325112868, 0 100)))");
	}
	
	public void testE() {
		this.test("LINESTRING (100 100, 0 100, 50 50, 0 0, 100 0)", "MULTIPOLYGON (((100 100, -0.9238795325112868 100.38268343236508, 49 50, -0.9238795325112868 -0.3826834323650898, 100 0, 0.9238795325112868 0.3826834323650898, 51 50, 0.9238795325112868 99.61731656763492, 100 100)))");
	}
	
	public void test3() {
		this.test("LINESTRING (0 100, 100 100, 50 50, 100 0, 0 0)", "MULTIPOLYGON (((0 100, 100.9238795325113 100.38268343236508, 51 50, 100.9238795325113 -0.3826834323650898, 0 0, 99.0761204674887 0.3826834323650898, 49 50, 99.0761204674887 99.61731656763492, 0 100)))");
	}
	
	public void testM9() {
		this.test("MULTILINESTRING ((26.40524 -14.974667, 26.41307 -14.97446, 26.68933 -14.98577), (26.68933 -14.98577, 26.81573 -15.03853, 26.88436 -15.04778))", "x");
	}
	
	private void test(String wktIn, String wktOut) {
		try {
			Geometry g = helper.parseGeometry(wktIn);
			String wktGenerated = helper.getGeoMultiPolygon(g).toText(); 
			if (!wktOut.equals(wktGenerated)) {
				System.out.println("Expected: " + wktOut);
				System.out.println("Received: " + wktGenerated);
				fail();
			}
		} catch (ParseException e) {
			fail();
		}

	}
}
