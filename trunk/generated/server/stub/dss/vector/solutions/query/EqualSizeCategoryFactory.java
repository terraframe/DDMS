package dss.vector.solutions.query;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AbortIfProblem;

public class EqualSizeCategoryFactory extends EqualSizeCategoryFactoryBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 137489069;

	public EqualSizeCategoryFactory() {
		super();
	}

	@Override
	protected String[] getRequiredAttributes() {
		return new String[] { CategoryGen.CATEGORYCOUNT, CategoryGen.PRECISIONFIGURES };
	}

	@Override
	public List<AbstractCategory> createInternal(Layer layer, CategoryGen categoryGen) {
		ArrayList<AbstractCategory> categories = new ArrayList<AbstractCategory>();

		QueryInfo info = layer.calculateQueryInfo();
		if (info.isThematicNumeric()) {
			double min = Double.parseDouble(info.getMinimum());
			double max = Double.parseDouble(info.getMaximum());
			this.createCategories(categories, layer, min, max, categoryGen);
		} else {
			// TODO -- Throw exception or problem
		}

		return categories;
	}

	private void createCategories(List<AbstractCategory> categories, Layer layer, double dataMin, double dataMax, CategoryGen categoryGen) {
		int count = categoryGen.getCategoryCount();
		int precision = categoryGen.getPrecisionFigures();

		Color pointStrokeStart = Color.decode(categoryGen.getPointStrokeStart());
		Color pointStrokeEnd = Color.decode(categoryGen.getPointStrokeEnd());

		Color polygonStrokeStart = Color.decode(categoryGen.getPolygonStrokeStart());
		Color polygonStrokeEnd = Color.decode(categoryGen.getPolygonStrokeEnd());

		Color polygonFillStart = Color.decode(categoryGen.getPolygonFillStart());
		Color polygonFillEnd = Color.decode(categoryGen.getPolygonFillEnd());

		Color fontFillStart = Color.decode(categoryGen.getFontFillStart());
		Color fontFillEnd = Color.decode(categoryGen.getFontFillEnd());

		Color labelHaloFillStart = Color.decode(categoryGen.getLabelHaloFillStart());
		Color labelHaloFillEnd = Color.decode(categoryGen.getLabelHaloFillEnd());
		
		
		double ulp = Math.pow(10.0d, precision);
		double min = this.floor(dataMin, ulp);
		double max = dataMax;
		if (dataMax != this.floor(dataMax, ulp)) {
			max = this.ceil(dataMax, ulp);
		}
		double step = (max - min) / (double) count;
		System.out.println("min=" + min + " max=" + max + " step=" + step);

		// If min and max are the same value create a single range with that
		// value
		// as the lower/upper bound.
		// This is simulated by forcing the count to equal 1.
		if (min == max) {
			count = 1;
		}

		switch (count) {
		case 1:
			this.addRange(categories, layer, min, max, pointStrokeStart, polygonStrokeStart, polygonFillStart, fontFillStart, labelHaloFillStart);
			break;
		case 2:
			this.addRange(categories, layer, min, this.floor(min + step, ulp), pointStrokeStart, polygonStrokeStart, polygonFillStart, fontFillStart, labelHaloFillStart);
			this.addRange(categories, layer, this.ceil(min + step, ulp), max, pointStrokeEnd, polygonStrokeEnd, polygonFillEnd, fontFillEnd, labelHaloFillEnd);
			break;
		default:
			if (count > 0) {
				double current = min + step;
				this.addRange(categories, layer, min, this.floor(current, ulp), pointStrokeStart, polygonStrokeStart, polygonFillStart, fontFillStart, labelHaloFillStart);

				for (int i = 1; i < count - 1; i++) {
					Color pointStroke = this.interpolateColor(i, count, pointStrokeStart, pointStrokeEnd);
					Color polygonStroke = this.interpolateColor(i, count, polygonStrokeStart, polygonStrokeEnd);
					Color polygonFill = this.interpolateColor(i, count, polygonFillStart, polygonFillEnd);
					Color fontFill = this.interpolateColor(i, count, fontFillStart, fontFillEnd);
					Color labelHaloFill = this.interpolateColor(i, count, labelHaloFillStart, labelHaloFillEnd);

					this.addRange(categories, layer, this.ceil(current, ulp), this.floor(current + step, ulp), pointStroke, polygonStroke, polygonFill, fontFill, labelHaloFill);
					current = current + step;
				}

				this.addRange(categories, layer, this.ceil(current, ulp), max, pointStrokeEnd, polygonStrokeEnd, polygonFillEnd, fontFillEnd, labelHaloFillEnd);
			}
		}
	}

	private double floor(double n, double ulp) {
		return Math.floor(n * ulp) / ulp;
	}

	private double ceil(double n, double ulp) {
		return (Math.floor(n * ulp) + 1.0d) / ulp;
	}
	
	private void addRange(List<AbstractCategory> categories, Layer layer, double lowerBound, double upperBound, Color pointStroke, Color polygonStroke, Color polygonFill, Color fontFill, Color labelHaloFill) {
		if (lowerBound == upperBound && categories.size() > 0) {
			RangeCategory lastCategory = (RangeCategory) categories.get(categories.size() - 1); 
			double lastUpperBound = Double.parseDouble(lastCategory.getUpperBoundStr());
			if (lowerBound <= lastUpperBound) {
				return;
			}
		}
		categories.add(this.createRange(layer, "" + lowerBound, "" + upperBound, pointStroke, polygonStroke, polygonFill, fontFill, labelHaloFill));
	}
	
	private AbstractCategory createRange(Layer layer, String lowerBound, String upperBound, Color pointStroke, Color polygonStroke, Color polygonFill, Color fontFill, Color labelHaloFill) {
		System.out.println(lowerBound + "->" + upperBound);
		
		RangeCategory cat = new RangeCategory();

		cat.setLowerBoundStr(lowerBound);
		cat.setUpperBoundStr(upperBound);

		Styles styles = new Styles();
		styles.setPointStroke(this.encodeColor(pointStroke));
		styles.setPolygonStroke(this.encodeColor(polygonStroke));
		styles.setPolygonFill(this.encodeColor(polygonFill));
		styles.setFill(this.encodeColor(fontFill));
		styles.setLabelHaloFill(this.encodeColor(labelHaloFill));
		styles.apply();

		cat.setStyles(styles);

		return cat;
	}
}
