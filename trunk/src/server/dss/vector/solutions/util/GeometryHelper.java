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
	private static double OUTLINE_DELTA = 0.00000001d;
	
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
		Coordinate[] linePoints = lineString.getCoordinates();
		
		if (linePoints.length == 0 || lineString.isClosed()) {
			return this.createPolygon(linePoints, lineString.getFactory());
		}

		if (linePoints.length == 1) {
			Coordinate[] newPoints = this.createTriangle(linePoints[0]);
			return this.createPolygon(newPoints, lineString.getFactory());
		}
		
		if (linePoints.length == 2) {
			Coordinate[] newPoints = new Coordinate[3];
			newPoints[0] = linePoints[0];
			//Interpolate a third, middle position so that we can create a diamond!
			newPoints[1] = new Coordinate((linePoints[0].x + linePoints[1].x) / 2.0d, (linePoints[0].y + linePoints[1].y) / 2.0d);
			newPoints[2] = linePoints[1];
			linePoints = newPoints;
		}
		ArrayList<Coordinate> outline = new ArrayList<Coordinate>();
		
		Coordinate offsetPoint = addFirstPoint(outline, linePoints[0], linePoints[1]);
		for (int i=1; i < linePoints.length-1; i++) {
			offsetPoint = addBisectedAnglePoint(outline, linePoints[i-1], linePoints[i], linePoints[i+1], offsetPoint);
		}
		offsetPoint = addMidPoint(outline, linePoints[linePoints.length-1], linePoints[linePoints.length-2], offsetPoint);
		for (int i=linePoints.length - 2; i > 0; i--) {
			offsetPoint = addBisectedAnglePoint(outline, linePoints[i+1], linePoints[i], linePoints[i-1], offsetPoint);
		}
		addLastPoint(outline, linePoints[0]);
		
		Coordinate[] full = outline.toArray(new Coordinate[outline.size()]);

		return this.createPolygon(full, lineString.getFactory());

	}

	private Polygon createPolygon(Coordinate[] coordinates, GeometryFactory factory) {
		LinearRing ring = factory.createLinearRing(coordinates);
		Polygon polygon = factory.createPolygon(ring, null);
		return polygon;
	}
	
	private Coordinate addFirstPoint(ArrayList<Coordinate> outline, Coordinate thisPoint, Coordinate nextPoint) {
		outline.add(thisPoint);
		
		Coordinate[] perpendiculars = this.getPerpendicularPoints(thisPoint, nextPoint);
		this.orderPerpendiculars(perpendiculars);
		return perpendiculars[0];
	}
	
	private Coordinate addBisectedAnglePoint(ArrayList<Coordinate> outline, Coordinate lastPoint, Coordinate thisPoint, Coordinate nextPoint, Coordinate offsetPoint) {
		Coordinate[] perpendiculars = this.getPerpendicularPoints(outline, lastPoint, thisPoint, nextPoint);
		this.orderPerpendiculars(perpendiculars, lastPoint, thisPoint, offsetPoint);
		outline.add(perpendiculars[0]);
		return perpendiculars[0];
	}
	
	private Coordinate addMidPoint(ArrayList<Coordinate> outline, Coordinate thisPoint, Coordinate nextPoint, Coordinate offsetPoint) {
		outline.add(thisPoint);
		
		Coordinate[] perpendiculars = this.getPerpendicularPoints(thisPoint, nextPoint);
		this.orderPerpendiculars(perpendiculars, nextPoint, thisPoint, offsetPoint);
		return perpendiculars[1];
	}

	private Coordinate addLastPoint(ArrayList<Coordinate> outline, Coordinate p1) {
		outline.add(p1);
		return p1;
	}

	private Coordinate[] getPerpendicularPoints(Coordinate thisPoint, Coordinate otherPoint) {
		Coordinate[] perpendiculars = new Coordinate[2];
		
		double dx = otherPoint.x - thisPoint.x;
		double dy = otherPoint.y - thisPoint.y;
		double l = this.length(dx, dy);
		
		perpendiculars[0] = new Coordinate(thisPoint.x + (OUTLINE_DELTA * -dy / l), thisPoint.y + (OUTLINE_DELTA * dx / l));
		perpendiculars[1] = new Coordinate(thisPoint.x + (OUTLINE_DELTA * dy / l), thisPoint.y + (OUTLINE_DELTA * -dx / l));
		
		return perpendiculars;
	}
	
	private Coordinate[] getPerpendicularPoints(ArrayList<Coordinate> outline, Coordinate lastPoint, Coordinate thisPoint, Coordinate nextPoint) {
		Coordinate[] perpendiculars = new Coordinate[2];

		Coordinate a = new Coordinate(lastPoint.x-thisPoint.x, lastPoint.y-thisPoint.y);
		Coordinate b = new Coordinate(nextPoint.x-thisPoint.x, nextPoint.y-thisPoint.y);
		double la = this.length(a.x, a.y);
		double lb = this.length(b.x, b.y);
		
		Coordinate c = new Coordinate((la * b.x) + (lb * a.x), (la * b.y) + (lb * a.y)); 
		double lc = this.length(c.x, c.y);
		
		if (lc > 0.0) {
			perpendiculars[0] = new Coordinate(thisPoint.x + (OUTLINE_DELTA * c.x / lc), thisPoint.y + (OUTLINE_DELTA * c.y / lc));
			perpendiculars[1] = new Coordinate(thisPoint.x - (OUTLINE_DELTA * c.x / lc), thisPoint.y - (OUTLINE_DELTA * c.y / lc));
		} else {
			perpendiculars = this.getPerpendicularPoints(thisPoint, nextPoint);
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

	private void orderPerpendiculars(Coordinate[] perpendiculars, Coordinate thisPoint, Coordinate nextPoint, Coordinate offsetPoint) {
		if ( (sgn(thisPoint, nextPoint, offsetPoint) != sgn(thisPoint, nextPoint, perpendiculars[0])) && (sgn(thisPoint, offsetPoint, perpendiculars[0]) != sgn(nextPoint, offsetPoint, perpendiculars[0])) ) {
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
