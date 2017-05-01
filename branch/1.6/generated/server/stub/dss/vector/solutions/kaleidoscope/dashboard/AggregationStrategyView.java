package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon;
import com.runwaysdk.system.gis.metadata.MdAttributePoint;
import com.runwaysdk.system.metadata.MdAttribute;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.kaleidoscope.MappableClass;
import dss.vector.solutions.kaleidoscope.MappableClassGeoNode;
import dss.vector.solutions.kaleidoscope.MappableClassGeoNodeQuery;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.geo.GeoNodeGeometry;

public class AggregationStrategyView extends AggregationStrategyViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241142559;

  public AggregationStrategyView()
  {
    super();
  }

  public static AggregationStrategyView[] getAggregationStrategies(String nodeId)
  {
    GeoNode node = GeoNode.get(nodeId);

    return AggregationStrategyView.getAggregationStrategies(node, null);
  }

  public static AggregationStrategyView[] getAggregationStrategies(GeoNode node, Boolean aggregatable)
  {
    List<AggregationStrategyView> list = new LinkedList<AggregationStrategyView>();

    if (aggregatable)
    {
      MappableClassGeoNodeQuery query = new MappableClassGeoNodeQuery(new QueryFactory());
      query.WHERE(query.getChild().EQ(node));

      OIterator<? extends MappableClassGeoNode> iterator = query.getIterator();

      try
      {
        MappableClassGeoNode relationship = iterator.next();
        MappableClass wrapper = relationship.getParent();

        GeoHierarchy root = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);

        List<? extends GeoHierarchy> lowests = wrapper.getAllUniversal().getAll();

        for (GeoHierarchy lowest : lowests)
        {
          LinkedList<GeoHierarchy> universals = new LinkedList<GeoHierarchy>(lowest.getAllParents());
          
          universals.addFirst(lowest);
          
          for (GeoHierarchy universal : universals)
          {
            if (!universal.getId().equals(root.getId()))
            {
              AggregationStrategyView view = new AggregationStrategyView();
              view.setAggregationType(UniversalAggregationStrategy.CLASS);
              view.setValue(universal.getId());
              view.setDisplayLabel(universal.getDisplayLabel());
              view.setAvailableGeometryTypes(new JSONArray().toString());

              list.add(view);
            }
          }
        }
      }
      finally
      {
        iterator.close();
      }
    }

    if (node instanceof GeoNodeGeometry)
    {
      String label = getDisplayLabel(node);

      AggregationStrategyView view = new AggregationStrategyView();
      view.setAggregationType(GeometryAggregationStrategy.CLASS);
      view.setValue(GeometryAggregationStrategy.VALUE);
      view.setDisplayLabel(label);

      JSONArray geomTypesJSONArr = new JSONArray();

      MdAttribute geomAttr = node.getGeometryAttribute();
      if (geomAttr != null)
      {
        geomTypesJSONArr.put(geomAttr.getClass().getName());
      }

      MdAttributePoint pointAttr = node.getPointAttribute();
      if (pointAttr != null)
      {
        geomTypesJSONArr.put(pointAttr.getClass().getName());
      }

      MdAttributeMultiPolygon polyAttr = node.getMultiPolygonAttribute();
      if (polyAttr != null)
      {
        geomTypesJSONArr.put(polyAttr.getClass().getName());
      }

      view.setAvailableGeometryTypes(geomTypesJSONArr.toString());

      list.add(view);
    }
    
    Collections.reverse(list);

    return list.toArray(new AggregationStrategyView[list.size()]);
  }

  public static String getDisplayLabel(GeoNode node)
  {
    MdAttribute attribute = node.getGeometryAttribute();

    if (attribute == null)
    {
      attribute = node.getPointAttribute();
    }

    if (attribute == null)
    {
      attribute = node.getMultiPolygonAttribute();
    }

    if (attribute == null)
    {
      attribute = node.getGeoEntityAttribute();
    }

    return attribute.getDisplayLabel().getValue();
  }

  public JSONObject toJSON() throws JSONException
  {
    JSONObject object = new JSONObject();
    String aggStratId = this.getId();
    String aggStratLabel = this.getDisplayLabel();
    String aggType = this.getAggregationType();
    String aggValue = this.getValue();

    JSONArray aggGeomTypes = new JSONArray(this.getAvailableGeometryTypes());

    object.put("id", aggStratId);
    object.put("label", aggStratLabel);
    object.put("type", aggType);
    object.put("value", aggValue);
    object.put("geomTypes", aggGeomTypes);

    return object;
  }
}
