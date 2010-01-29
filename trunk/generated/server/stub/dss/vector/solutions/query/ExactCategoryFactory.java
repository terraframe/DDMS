package dss.vector.solutions.query;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.query.DISTINCT;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.SelectableSQLDouble;
import com.terraframe.mojo.query.ValueQuery;

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
	  
	  // SQL distinct to avoid dups and smallest to largest
	  Map<Layer, ValueQuery> layerVQs = MapUtil.createDBViews(new Layer[]{layer}, true);
	  ValueQuery layerVQ = layerVQs.get(layer);
	  ValueQuery wrapper = new ValueQuery(layerVQ.getQueryFactory());
	  
	  QueryInfo info = layer.calculateQueryInfo();
	  List<String> values = new LinkedList<String>();
	  if(info.hasThematicVariable() && info.isThematicNumeric())
	  {
	    wrapper.FROM(layer.getViewName(), "layer_values_view");
	    
      SelectableSQLDouble layerValues = wrapper.aSQLDouble("layer_value", "SELECT "+
          QueryConstants.THEMATIC_DATA_COLUMN);
      
      wrapper.SELECT(new DISTINCT(layerValues));
      
      wrapper.WHERE(layerValues.NE((Double) null));
      
      wrapper.ORDER_BY_ASC(layerValues);
      
      OIterator<ValueObject> iter = wrapper.getIterator();
      try
      {
        while(iter.hasNext())
        {
          String value = iter.next().getValue("layer_value");
          values.add(value);
        }
      }
      finally
      {
        iter.close();
      }
	  }
	  else
	  {
	    // THROW EX?
	  }
	  
		return values;
	}

	private AbstractCategory createExact(Layer layer, String value, Color c) {
		// TODO Auto-generated method stub
		NonRangeCategory cat = new NonRangeCategory();

		return cat;
	}
}
