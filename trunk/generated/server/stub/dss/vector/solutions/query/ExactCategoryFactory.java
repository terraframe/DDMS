package dss.vector.solutions.query;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactCategoryFactory extends ExactCategoryFactoryBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 207120471;

	public ExactCategoryFactory() {
		super();
	}

	@Override
	public boolean isRequiredParameter(String param) {
		if (STARTING_COLOR.equals(param)) return true;
		if (ENDING_COLOR.equals(param)) return true;
		return false;
	}

	@Override
	public boolean isSupportedParameter(String param) {
		if (STARTING_COLOR.equals(param)) return true;
		if (ENDING_COLOR.equals(param)) return true;
		return false;
	}
	
	@Override
	public List<AbstractCategory> create(Layer layer, Map<String, String> parameters) {
		ArrayList<AbstractCategory> categories = new ArrayList<AbstractCategory>();

		Color startingColor = Color.decode(parameters.get(STARTING_COLOR));
		Color endingColor = Color.decode(parameters.get(ENDING_COLOR));

		List<String> layerValues = this.getLayerValues(layer);

		for (int i = 0; i < layerValues.size(); i++) {
			String value = layerValues.get(i);
			Color c = this.interpolateColor(i, layerValues.size(), startingColor, endingColor);
			categories.add(this.createExact(layer, value, c));
		}

		return null;
	}

	private List<String> getLayerValues(Layer layer) {
		return null;
	}

	private AbstractCategory createExact(Layer layer, String value, Color c) {
		// TODO Auto-generated method stub
		NonRangeCategory cat = new NonRangeCategory();

		return cat;
	}
}
