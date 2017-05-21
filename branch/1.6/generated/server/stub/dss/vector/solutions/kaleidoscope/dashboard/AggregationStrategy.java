package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;

public abstract class AggregationStrategy extends AggregationStrategyBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -27686881;

  public AggregationStrategy()
  {
    super();
  }

  public abstract ValueQuery getViewQuery(DashboardThematicLayer layer, Map<String, Drilldown> drilldowns);

  public abstract JSONObject getJSON();

  public abstract String getCategoryLabel(GeoNode geoNode, String categoryId);

  public abstract AggregationStrategy clone();
}
