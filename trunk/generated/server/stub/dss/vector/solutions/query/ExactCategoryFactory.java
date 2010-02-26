package dss.vector.solutions.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.query.DISTINCT;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.SelectableSQLDouble;
import com.terraframe.mojo.query.ValueQuery;

public class ExactCategoryFactory extends ExactCategoryFactoryBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 207120471;
	private static final long TOO_MANY_VALUES = 50l;

	public ExactCategoryFactory() {
		super();
	}

	@Override
	protected String[] getRequiredAttributes() {
		return new String[] {};
	}

	@Override
	protected List<AbstractCategory> createInternal(Layer layer, CategoryGen categoryGen) {
		ArrayList<AbstractCategory> categories = new ArrayList<AbstractCategory>();

		//QueryInfo info = layer.calculateQueryInfo();
		//if(info.hasThematicVariable() && info.isThematicNumeric())

		ValueQuery values = this.getLayerValueQuery(layer);
		int i = 0;
		int count = (int) values.getCount();

		if (count > TOO_MANY_VALUES) {

		} else {
			OIterator<ValueObject> iter = values.getIterator();
			try {
				while (iter.hasNext()) {
					String value = iter.next().getValue("layer_value");
					categories.add(this.createExact(layer, value, categoryGen, i++, count));
				}
			} finally {
				iter.close();
			}
		}

		return categories;
	}

	private ValueQuery getLayerValueQuery(Layer layer) {
		// SQL distinct to avoid dups and smallest to largest
		Map<Layer, ValueQuery> layerVQs = MapUtil.createDBViews(new Layer[] { layer }, true);
		ValueQuery layerVQ = layerVQs.get(layer);
		ValueQuery wrapper = new ValueQuery(layerVQ.getQueryFactory());

		wrapper.FROM(layer.getViewName(), "layer_values_view");

		SelectableSQLDouble layerValues = wrapper.aSQLDouble("layer_value", "SELECT " + layer.getThematicColumnAlias());

		wrapper.SELECT(new DISTINCT(layerValues));

		wrapper.WHERE(layerValues.NE((Double) null));

		wrapper.ORDER_BY_ASC(layerValues);

		return wrapper;
	}
}
