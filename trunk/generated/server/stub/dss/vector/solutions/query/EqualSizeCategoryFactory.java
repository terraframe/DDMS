package dss.vector.solutions.query;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSizeCategoryFactory extends EqualSizeCategoryFactoryBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 137489069;

	public EqualSizeCategoryFactory() {
		super();
	}

	@Override
	public List<AbstractCategory> create(Layer layer, Map<String, String> parameters) {
		ArrayList<AbstractCategory> categories = new ArrayList<AbstractCategory>();

		Color startingColor = Color.decode(parameters.get(STARTING_COLOR));
		Color endingColor = Color.decode(parameters.get(ENDING_COLOR));
		int count = Integer.parseInt(parameters.get(COUNT));
		int precision = Integer.parseInt(parameters.get(PRECISION));

		QueryInfo info = layer.calculateQueryInfo();
		if (info.isThematicNumeric()) {
			double min = Double.parseDouble(info.getMinimum());
			double max = Double.parseDouble(info.getMaximum());
			this.createCategories(categories, layer, min, max, count, precision, startingColor, endingColor);
		} else {
			// TODO -- Throw exception or problem
		}

		return categories;
	}

	public void createCategories(List<AbstractCategory> categories, Layer layer, double min, double max, int count, int precision, Color startingColor, Color endingColor) {
		double ulp = Math.pow(10.0d, precision);
		double step = (max - min) / (double) count;
		
		switch (count) {
			case 1: 
				categories.add(this.createRange(layer, "" + min, "" + max, startingColor));
				break;
			case 2:
				categories.add(this.createRange(layer, "" + min, "" + this.floor(min + step, ulp), startingColor));
				categories.add(this.createRange(layer, "" + this.ceil(min + step, ulp), "" + max, endingColor));
				break;
			default:
				if (count > 0) {
					double current = min + step;
					categories.add(this.createRange(layer, "" + min, "" + this.floor(current, ulp), startingColor));
	
					for (int i = 1; i < count - 1; i++) {
						Color c = this.interpolateColor(i, count, startingColor, endingColor);
						categories.add(this.createRange(layer, "" + this.ceil(current, ulp), "" + this.floor(current + step, ulp), c));
						current = current + step;
					}
					
					categories.add(this.createRange(layer, "" + this.ceil(current, ulp), "" + max, endingColor));
				}
		}
	}
	
	private double floor(double n, double ulp) {
		return Math.floor(n * ulp) / ulp;
	}

	private double ceil(double n, double ulp) {
		return (Math.floor(n * ulp) + 1.0d) / ulp;
	}

	private AbstractCategory createRange(Layer layer, String lowerValue, String upperValue, Color c) {
		System.out.println(lowerValue + " -> " + upperValue);
		// TODO Auto-generated method stub
		RangeCategory cat = new RangeCategory();

		return cat;
	}
}
