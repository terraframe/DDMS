package dss.vector.solutions.kaleidoscope.dashboard;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geoserver.GeoserverFacade;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.kaleidoscope.dashboard.layer.EmptyLayerInformation;
import dss.vector.solutions.kaleidoscope.dashboard.query.GeoEntityThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.dashboard.query.ThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.wrapper.FeatureType;

public class UniversalAggregationStrategy extends UniversalAggregationStrategyBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final Log   log              = LogFactory.getLog(UniversalAggregationStrategy.class);

  private static final long serialVersionUID = 1170847564;

  public UniversalAggregationStrategy()
  {
    super();
  }

  public String getGeometryColumn(DashboardThematicLayer layer)
  {
    if (layer.getFeatureType().equals(FeatureType.POINT))
    {
      return GeoEntity.GEOPOINT;
    }
    else
    {
      return GeoEntity.GEOMULTIPOLYGON;
    }
  }

  @Override
  public ValueQuery getViewQuery(DashboardThematicLayer layer, Map<String, Drilldown> drilldowns)
  {
    QueryFactory factory = new QueryFactory();

    // Query containing the aggregated values and geo entity ids
    ValueQuery valueQuery = this.getThematicValueQuery(factory, layer, drilldowns);

    // Query to join with the geo entity geometries
    ValueQuery geometryQuery = new ValueQuery(factory);

    // Outer query joining the value queries and the geometry query
    ValueQuery outerQuery = new ValueQuery(factory);

    if (log.isDebugEnabled())
    {
      // print the SQL if the generated
      log.debug("SLD for Layer [%s], this:\n" + valueQuery.getSQL());
    }

    if (valueQuery.getCount() == 0)
    {
      EmptyLayerInformation info = new EmptyLayerInformation();
      info.setLayerName(layer.getName());
      info.apply();

      info.throwIt();
    }

    // Set the GeoID and the Geometry attribute for the second query
    GeoEntityQuery entityQuery = new GeoEntityQuery(geometryQuery);

    Selectable geoId2 = entityQuery.getGeoId(GeoEntity.GEOID);
    geoId2.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId2.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);

    // geometry
    String columnName = this.getGeometryColumn(layer);

    Selectable geom = entityQuery.get(columnName);
    geom.setColumnAlias(GeoserverFacade.GEOM_COLUMN);
    geom.setUserDefinedAlias(GeoserverFacade.GEOM_COLUMN);

    geometryQuery.SELECT(geoId2, geom);

    // Add all of the selectables from the values query to the outer query
    for (Selectable selectable : valueQuery.getSelectableRefs())
    {
      Attribute attribute = valueQuery.get(selectable.getResultAttributeName());
      attribute.setColumnAlias(selectable.getColumnAlias());

      outerQuery.SELECT(attribute);
    }

    // Add the geometry selectable from the geometry query to the outer query
    Attribute geomAttribute = geometryQuery.get(GeoserverFacade.GEOM_COLUMN);
    geomAttribute.setColumnAlias(GeoserverFacade.GEOM_COLUMN);

    outerQuery.SELECT(geomAttribute);

    // Join the geometry query to the values query through the geo id
    outerQuery.WHERE(geometryQuery.aCharacter(ThematicQueryBuilder.LOCATION_ALIAS).EQ(valueQuery.aCharacter(ThematicQueryBuilder.LOCATION_ALIAS)));

    return outerQuery;
  }

  private ValueQuery getThematicValueQuery(QueryFactory factory, DashboardThematicLayer layer, Map<String, Drilldown> drilldowns)
  {
    GeoHierarchy universal = this.getUniversalForQuery(layer, drilldowns);

    GeoEntityThematicQueryBuilder builder = new GeoEntityThematicQueryBuilder(factory, layer, universal);
    ValueQuery valueQuery = builder.getThematicValueQuery();

    return valueQuery;
  }

  private GeoHierarchy getUniversalForQuery(DashboardThematicLayer layer, Map<String, Drilldown> drilldowns)
  {
    if (drilldowns.containsKey(layer.getId()))
    {
      Drilldown drilldown = drilldowns.get(layer.getId());

      return GeoHierarchy.get(drilldown.getUniversalId());
    }

    return this.getUniversal();
  }

  @Override
  public JSONObject getJSON()
  {
    try
    {
      JSONObject object = new JSONObject();
      object.put("type", this.getClass().getSimpleName());
      object.put("value", this.getUniversalId());
      object.put("id", this.getId());

      return object;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  @Override
  public String getCategoryLabel(GeoNode geoNode, String categoryId)
  {
    GeoEntity entity = GeoEntity.getByKey(categoryId);

    return entity.getEntityLabel().getValue();
  }

  @Override
  public AggregationStrategy clone()
  {
    UniversalAggregationStrategy clone = new UniversalAggregationStrategy();
    clone.setUniversal(this.getUniversal());
    clone.apply();

    return clone;
  }
}
