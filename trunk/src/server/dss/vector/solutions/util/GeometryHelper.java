package dss.vector.solutions.util;

import java.util.ArrayList;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeometryHelper {
	private static double TRIANGLE_DELTA = 0.01d;
	private static double OUTLINE_DELTA = 0.001d;
	
	public Geometry getGeometry(String wktString) {
		Geometry g = null;

		WKTReader r = new WKTReader();
		try {
			g = r.read(wktString);
		} catch (ParseException e) {
			// FAILED TO PARSE
		}

		return g;
	}

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
	 * Return a MultiPolygon object that corresponds to the given Geometry
	 * object.
	 * 
	 * For a point, we'll return a triangle centered around the point. For a
	 * LineString or MultiLineString, we'll return a MultiPolygon created by
	 * tracing the line(s) from start to finish, and then back again. For a
	 * Polygon, we'll return a MultiPolygon that contains only that polygon For
	 * a MultiPolygon, we'll just return it
	 * 
	 * @param g
	 * @return
	 */
	public MultiPolygon getGeoMultiPolygon(Geometry g) {
		MultiPolygon geoMultiPolygon = null;

		if (g instanceof Point) {
			Coordinate[] points = this.createTriangle(g.getCoordinate());
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

	private Coordinate[] createTriangle(Coordinate center) {
		Coordinate[] points = new Coordinate[4];
		points[0] = new Coordinate(center);
		points[0].y += TRIANGLE_DELTA;
		points[1] = new Coordinate(center);
		points[1].y -= TRIANGLE_DELTA;
		points[1].x -= TRIANGLE_DELTA;
		points[2] = new Coordinate(center);
		points[2].y -= TRIANGLE_DELTA;
		points[2].x += TRIANGLE_DELTA;
		points[3] = new Coordinate(points[0]);
		return points;
	}
	
	/**
	 * @param lineString
	 * @return
	 */
	private Polygon createPolygon(LineString lineString) {
		Coordinate[] forward = lineString.getCoordinates();
		
		if (forward.length == 0 || lineString.isClosed()) {
			return this.createPolygon(forward, lineString.getFactory());
		}

		if (forward.length == 1) {
			Coordinate[] points = this.createTriangle(forward[0]);
			return this.createPolygon(points, lineString.getFactory());
		}
		
		ArrayList<Coordinate> outline = new ArrayList<Coordinate>();
		
		addFirstPoint(outline, forward[0], forward[1]);
		for (int i=1; i < forward.length-1; i++) {
			addBisectedAnglePoint(outline, forward[i-1], forward[i], forward[i+1], true);
		}
		addMidPoints(outline, forward[forward.length-1], forward[forward.length-2]);
		for (int i=forward.length - 2; i > 0; i--) {
			addBisectedAnglePoint(outline, forward[i+1], forward[i], forward[i-1], true);
		}
		addLastPoints(outline, forward[1], forward[0]);
		
		Coordinate[] full = outline.toArray(new Coordinate[outline.size()]);

		return this.createPolygon(full, lineString.getFactory());

	}

	private Polygon createPolygon(Coordinate[] coordinates, GeometryFactory factory) {
		LinearRing ring = factory.createLinearRing(coordinates);
		Polygon polygon = factory.createPolygon(ring, null);
		return polygon;
	}
	
	private void addFirstPoint(ArrayList<Coordinate> outline, Coordinate p1, Coordinate p2) {
		Coordinate[] perpendiculars = this.getPerpendicularPoints(outline, p1, p2);
		this.orderPerpendiculars(perpendiculars);
		outline.add(perpendiculars[0]);
	}
	
	private void addBisectedAnglePoint(ArrayList<Coordinate> outline, Coordinate p1, Coordinate p2, Coordinate p3, boolean isForward) {
		Coordinate[] perpendiculars = this.getPerpendicularPoints(outline, p1, p2, p3);
		this.orderPerpendiculars(perpendiculars, p1, p2, outline.get(outline.size() - 1));
		outline.add(perpendiculars[0]);
	}
	
	private void addMidPoints(ArrayList<Coordinate> outline, Coordinate p1, Coordinate p2) {
		Coordinate[] perpendiculars = this.getPerpendicularPoints(outline, p1, p2);
		this.orderPerpendiculars(perpendiculars, p1, p2, outline.get(outline.size() - 1));
		outline.add(perpendiculars[0]);
		outline.add(perpendiculars[1]);
	}

	private void addLastPoints(ArrayList<Coordinate> outline, Coordinate p1, Coordinate p2) {
		Coordinate[] perpendiculars = this.getPerpendicularPoints(outline, p2, p1);
		this.orderPerpendiculars(perpendiculars);
		outline.add(perpendiculars[1]);
		outline.add(perpendiculars[0]);
	}

	private Coordinate[] getPerpendicularPoints(ArrayList<Coordinate> outline, Coordinate p1, Coordinate p2) {
		Coordinate[] perpendiculars = new Coordinate[2];
		
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		double l = this.length(dx, dy);
		
		perpendiculars[0] = new Coordinate(p1.x + (OUTLINE_DELTA * -dy / l), p1.y + (OUTLINE_DELTA * dx / l));
		perpendiculars[1] = new Coordinate(p1.x + (OUTLINE_DELTA * dy / l), p1.y + (OUTLINE_DELTA * -dx / l));
		
		return perpendiculars;
	}
	
	private Coordinate[] getPerpendicularPoints(ArrayList<Coordinate> outline, Coordinate p1, Coordinate p2, Coordinate p3) {
		Coordinate[] perpendiculars = new Coordinate[2];

		Coordinate a = new Coordinate(p1.x-p2.x, p1.y-p2.y);
		Coordinate b = new Coordinate(p3.x-p2.x, p3.y-p2.y);
		double la = this.length(a.x, a.y);
		double lb = this.length(b.x, b.y);
		
		Coordinate c = new Coordinate((la * b.x) + (lb * a.x), (la * b.y) + (lb * a.y)); 
		double lc = this.length(c.x, c.y);
		
		if (lc > 0.0) {
			perpendiculars[0] = new Coordinate(p2.x + (OUTLINE_DELTA * c.x / lc), p2.y + (OUTLINE_DELTA * c.y / lc));
			perpendiculars[1] = new Coordinate(p2.x - (OUTLINE_DELTA * c.x / lc), p2.y - (OUTLINE_DELTA * c.y / lc));
		} else {
			perpendiculars = this.getPerpendicularPoints(outline, p2, p3);
		}

		return perpendiculars;
	}

	private void orderPerpendiculars(Coordinate[] perpendiculars) {
		boolean isFirstLeftAbove = (perpendiculars[0].x < perpendiculars[1].x || (perpendiculars[0].x == perpendiculars[1].x && perpendiculars[0].y > perpendiculars[1].y));
		
		if (!isFirstLeftAbove) {
			Coordinate temp = perpendiculars[0];
			perpendiculars[0] = perpendiculars[1];
			perpendiculars[1] = temp;
		}
	}

	private void orderPerpendiculars(Coordinate[] perpendiculars, Coordinate l1, Coordinate l2, Coordinate o) {
		if ( (sgn(l1, l2, o) != sgn(l1, l2, perpendiculars[0])) && (sgn(l1, o, perpendiculars[0]) != sgn(l2, o, perpendiculars[0])) ) {
			Coordinate temp = perpendiculars[0];
			perpendiculars[0] = perpendiculars[1];
			perpendiculars[1] = temp;
		}
	}
	
	private boolean sgn(Coordinate u, Coordinate v, Coordinate w) {
		double det = ((u.x-w.x) * (v.y-w.y)) - ((u.y-w.y) * (v.x-w.x));
		return det > 0;
	}

	private double length(double x, double y) {
		return Math.sqrt((x * x) + (y * y));
	}
}
