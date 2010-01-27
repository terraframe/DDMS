package dss.vector.solutions.query;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ExactCategoryFactory extends ExactCategoryFactoryBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 207120471;

	public ExactCategoryFactory() {
		super();
	}

  @Override
  protected String[] getRequiredAttributes()
  {
    return new String[]{}; 
  }
	
	@Override
  protected List<AbstractCategory> createInternal(Layer layer, CategoryGen categoryGen) {
    ArrayList<AbstractCategory> categories = new ArrayList<AbstractCategory>();
    
    Color startingColor = Color.decode(categoryGen.getPolygonStrokeStart());
    Color endingColor = Color.decode(categoryGen.getPolygonStrokeEnd());
	  
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
