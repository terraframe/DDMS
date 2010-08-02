package dss.vector.solutions.query;

import java.util.ArrayList;
import java.util.List;

import dss.vector.solutions.MdssLog;

public class EqualSizeCategoryFactory extends EqualSizeCategoryFactoryBase implements com.runwaysdk.generation.loader.Reloadable {
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

		double ulp = this.getUlp(precision);
		double min = this.floor(dataMin, ulp);
		double max = this.ceil(dataMax, ulp);
		double step = (max - min) / (double) count;

		// If min and max are the same value create a single range with that
		// value
		// as the lower/upper bound.
		// This is simulated by forcing the count to equal 1.
		if (min == max) {
			count = 1;
		}

		switch (count) {
		case 1:
			// This doesn't make any sense--it would generate a single range from 
			// negative infinity to infinity...which is the same as the default
			// range...so we return no range at all.
			break;
		case 2:
			// Add the initial (negative infinity to midpoint) range
			categories.add(this.createRange(layer, null, "" + this.round(min + step, ulp), categoryGen, 0, count));
			// Add the final (midpoint to infinity) range
			categories.add(this.createRange(layer, "" + this.round(min + step, ulp), null,  categoryGen, 1, count));
			break;
		default:
			if (count > 0) {
				double current = min + step;
				// Add the initial (negative infinity to first value) range
				categories.add(this.createRange(layer, null, "" + this.round(current, ulp),  categoryGen, 0, count));

				for (int i = 1; i < count - 1; i++) {
					// If this not a degenerate range, add it to the list of ranges 
					if (this.round(current, ulp) != this.round(current + step, ulp)) {
						categories.add(this.createRange(layer, "" + this.round(current, ulp), "" + this.round(current + step, ulp),  categoryGen, i, count));
					}
					current = current + step;
				}

				// Add the final (last value to infinity) range
				categories.add(this.createRange(layer, "" + this.round(current, ulp), null, categoryGen, count-1, count));
			}
		}
	}
}
