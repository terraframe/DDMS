package dss.vector.solutions.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class GeometryHelper {
	private static double DELTA = 0.01d;
	
	/**
	 * Return a Point object that corresponds to the given Geometry object.
	 * 
	 * For a Point, we'll return the Point, otherwise we'll return the centroid
	 * of the given Geometry
	 * 
	 * @param g
	 * @return
	 */
	public Point getGeoPoint(Geometry g) {
		Point geoPoint = null;

		if (g instanceof Point) {
			geoPoint = (Point) g;
		} else {
			geoPoint = g.getCentroid();
		}

		return geoPoint;
	}

	/**
	 * Return a MultiPolygon object that corresponds to the given Geometry object.
	 * 
	 * For a point, we'll return a triangle centered around the point.
	 * For a LineString or MultiLineString, we'll return a MultiPolygon created by tracing
	 * the line(s) from start to finish, and then back again.
	 * For a Polygon, we'll return a MultiPolygon that contains only that polygon
	 * For a MultiPolygon, we'll just return it
	 * 
	 * @param g
	 * @return
	 */
	public MultiPolygon getGeoMultiPolygon(Geometry g) {
		MultiPolygon geoMultiPolygon = null;

		if (g instanceof Point) {
			Coordinate[] points = new Coordinate[4];
			points[0] = new Coordinate(g.getCoordinate());
			points[0].y += DELTA;
			points[1] = new Coordinate(g.getCoordinate());
			points[1].y -= DELTA;
			points[1].x -= DELTA;
			points[2] = new Coordinate(g.getCoordinate());
			points[2].y -= DELTA;
			points[2].x += DELTA;
			points[3] = new Coordinate(points[0]);
			geoMultiPolygon = new MultiPolygon(new Polygon[] { this.createPolygon(points, g.getFactory()) }, g.getFactory());
		} else if (g instanceof MultiPolygon) {
			geoMultiPolygon = (MultiPolygon) g;
		} else if (g instanceof Polygon) {
			geoMultiPolygon = new MultiPolygon(new Polygon[] { (Polygon) g }, g.getFactory());
		} else if (g instanceof LineString) {
			geoMultiPolygon = new MultiPolygon(new Polygon[] { this.createPolygon((LineString) g) }, g.getFactory());
		} else if (g instanceof MultiLineString) {
			MultiLineString mls = (MultiLineString) g;
			Polygon[] polygons = new Polygon[mls.getNumGeometries()];
			for (int n = 0; n < mls.getNumGeometries(); n++) {
				polygons[n] = this.createPolygon((LineString) mls.getGeometryN(n));
			}
			geoMultiPolygon = new MultiPolygon(polygons, g.getFactory());
		}
		return geoMultiPolygon;
	}

	/**
	 * @param lineString
	 * @return
	 */
	private Polygon createPolygon(LineString lineString) {
		Coordinate[] forward = lineString.getCoordinates();
		Coordinate[] backward = lineString.reverse().getCoordinates();
		Coordinate[] full = null;

		// If the LineString is closed (i.e. a loop: the first and last points
		// are the same) then
		// we can use it as is, otherwise we have to "self-close" by adding all
		// the points
		// in reverse order
		if (forward.length < 1 || forward[0].equals(backward[0])) {
			full = forward;
		} else {
			full = new Coordinate[forward.length + backward.length];
			System.arraycopy(forward, 0, full, 0, forward.length);
			System.arraycopy(backward, 0, full, forward.length, backward.length);
		}

		return this.createPolygon(full, lineString.getFactory());

	}

	private Polygon createPolygon(Coordinate[] coordinates, GeometryFactory factory) {
		LinearRing ring = factory.createLinearRing(coordinates);
		Polygon polygon = factory.createPolygon(ring, null);
		return polygon;
	}

}
