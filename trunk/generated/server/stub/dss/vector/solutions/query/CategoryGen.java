package dss.vector.solutions.query;

import java.awt.Color;

public class CategoryGen extends CategoryGenBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = -1125536921;

	public CategoryGen() {
		super();
	}

	protected Color interpolateColor(int n, int total, Color startingColor, Color endingColor) {
		return new Color(this.interpolate(n, total, startingColor.getRed(), endingColor.getRed()), this.interpolate(n, total, startingColor.getGreen(), endingColor.getGreen()), this.interpolate(n, total, startingColor.getBlue(), endingColor.getBlue()));
	}

	private int interpolate(int n, int total, int start, int end) {
		return start + Math.round( ((float) (end - start)) * ((float) n / (float) (total - 1)) );
	}

	public static Color decodeColor(String s) {
		return Color.decode(s);
	}

	public static String encodeColor(Color c) {
		return String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
	}

	public Color interpolatePointStrokeColor(int n, int total) {
		return this.interpolateColor(n, total, decodeColor(this.getPointStrokeStart()), decodeColor(this.getPointStrokeEnd()));
	}

	public Color interpolatePolygonStrokeColor(int n, int total) {
		return this.interpolateColor(n, total, decodeColor(this.getPolygonStrokeStart()), decodeColor(this.getPolygonStrokeEnd()));
	}

	public Color interpolatePolygonFillColor(int n, int total) {
		return this.interpolateColor(n, total, decodeColor(this.getPolygonFillStart()), decodeColor(this.getPolygonFillEnd()));
	}

	public Color interpolateFontFillColor(int n, int total) {
		return this.interpolateColor(n, total, decodeColor(this.getFontFillStart()), decodeColor(this.getFontFillEnd()));
	}

	public Color interpolateLabelHaloFillColor(int n, int total) {
		return this.interpolateColor(n, total, decodeColor(this.getLabelHaloFillStart()), decodeColor(this.getLabelHaloFillEnd()));
	}

	public int interpolatePointSize(int n, int total) {
		return this.interpolate(n, total, this.getPointSizeStart(), this.getPointSizeEnd());
	}
	
	public Styles interpolateStyle(int n, int total) {
		Styles styles = new Styles();
		styles.setPointStroke(encodeColor(this.interpolatePointStrokeColor(n, total)));
		styles.setPolygonStroke(encodeColor(this.interpolatePolygonStrokeColor(n, total)));
		styles.setPolygonFill(encodeColor(this.interpolatePolygonFillColor(n, total)));
		styles.setFill(encodeColor(this.interpolateFontFillColor(n, total)));
		styles.setLabelHaloFill(encodeColor(this.interpolateLabelHaloFillColor(n, total)));
		styles.setPointSize(this.interpolatePointSize(n, total));
		styles.apply();
		return styles;
	}
}
