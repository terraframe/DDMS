package dss.vector.solutions.kaleidoscope.dashboard.layer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDateTimeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeNumberDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdAttributeTimeDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeReferenceDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.gis.metadata.MdAttributeMultiPolygon;
import com.runwaysdk.system.gis.metadata.MdAttributePoint;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeCharacter;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdAttributeDecimal;
import com.runwaysdk.system.metadata.MdAttributeDouble;
import com.runwaysdk.system.metadata.MdAttributeFloat;
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdAttributeLong;
import com.runwaysdk.system.metadata.MdAttributeReference;
import com.runwaysdk.system.metadata.MdAttributeText;
import com.runwaysdk.system.metadata.MdAttributeVirtual;

import dss.vector.solutions.kaleidoscope.MappableAttribute;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategy;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyView;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationType;
import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType;
import dss.vector.solutions.kaleidoscope.dashboard.Dashboard;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardLegend;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardMap;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyle;
import dss.vector.solutions.kaleidoscope.dashboard.Drilldown;
import dss.vector.solutions.kaleidoscope.dashboard.MdAttributeView;
import dss.vector.solutions.kaleidoscope.dashboard.MissingLocationAttributeException;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.kaleidoscope.wrapper.AttributeType;
import dss.vector.solutions.kaleidoscope.wrapper.MapVisitor;
import dss.vector.solutions.kaleidoscope.wrapper.ThematicLayer;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRelationship;
import dss.vector.solutions.util.CollectionUtil;

public class DashboardThematicLayer extends DashboardThematicLayerBase implements Reloadable, ThematicLayer
{
  private static final long serialVersionUID = -810007054;

  public static long        LIMIT            = 10;

  public static String      layerType        = "THEMATICLAYER";

  public DashboardThematicLayer()
  {
    super();
  }

  // @Override
  // public String getName()
  // {
  // return this.getNameLabel().getValue();
  // }

  @Transaction
  public void applyAll(DashboardStyle style, String mapId, AggregationStrategy strategy, DashboardCondition[] conditions)
  {
    // If there is an existing aggregation strategy then delete it and use the new one
    AggregationStrategy existing = this.getAggregationStrategy();

    strategy.apply();

    this.setAggregationStrategy(strategy);

    super.applyAll(style, mapId, conditions);

    if (existing != null)
    {
      existing.delete();
    }
  }

  @Override
  public String applyWithStyleAndStrategy(DashboardStyle style, String mapId, AggregationStrategy strategy, String state)
  {
    List<DashboardCondition> conditions = DashboardCondition.getConditionsFromState(state);

    try
    {
      this.applyAll(style, mapId, strategy, conditions.toArray(new DashboardCondition[conditions.size()]));
    }
    catch (com.runwaysdk.dataaccess.database.DuplicateDataDatabaseException e)
    {
      throw new DuplicateLayerException(e);
    }

    return this.publish();
  }

  /**
   * Gets the min and max values of a data set to be used for styling based data distributions
   */
  public HashMap<String, Double> getLayerMinMax(String _attribute)
  {

    HashMap<String, Double> minMaxMap = new HashMap<String, Double>();
    String thematicAttrType = this.getMdAttribute().getType();

    QueryFactory f = new QueryFactory();
    ValueQuery wrapper = new ValueQuery(f);
    wrapper.FROM(getViewName(), "");

    List<Selectable> selectables = new LinkedList<Selectable>();

    //
    // Only number types can be used
    //
    Set<String> numerics = new TreeSet<String>();
    numerics.add(MdAttributeLong.CLASS);
    numerics.add(MdAttributeInteger.CLASS);
    numerics.add(MdAttributeDouble.CLASS);
    numerics.add(MdAttributeDecimal.CLASS);
    numerics.add(MdAttributeFloat.CLASS);
    numerics.add(MdAttributeBoolean.CLASS);

    if (numerics.contains(thematicAttrType))
    {
      selectables.add(wrapper.aSQLAggregateDouble("min_data", "MIN(" + _attribute + ")"));
      selectables.add(wrapper.aSQLAggregateDouble("max_data", "MAX(" + _attribute + ")"));
    }
    else
    {
      throw new ProgrammingErrorException("Could not calculate the min/max value of a non-numeric attribute");
    }

    selectables.add(wrapper.aSQLAggregateLong("totalResults", "COUNT(*)"));

    wrapper.SELECT(selectables.toArray(new Selectable[selectables.size()]));

    OIterator<? extends ValueObject> iter = wrapper.getIterator();
    try
    {
      ValueObject row = iter.next();

      String min = row.getValue("min_data");
      String max = row.getValue("max_data");

      CollectionUtil.populateMap(minMaxMap, "min", min, new Double(0));
      CollectionUtil.populateMap(minMaxMap, "max", max, new Double(0));

      return minMaxMap;
    }
    finally
    {
      iter.close();
    }
  }

  @Override
  public String getJSON()
  {
    return this.toJSON().toString();
  }

  private static MdAttributeConcrete getMdAttributeConcrete(MdAttribute mdAttr)
  {
    if (mdAttr instanceof MdAttributeVirtual)
    {
      MdAttributeVirtual mdAttributeVirtual = (MdAttributeVirtual) mdAttr;

      return mdAttributeVirtual.getMdAttributeConcrete();
    }

    return ( (MdAttributeConcrete) mdAttr );
  }

  private static String getCategoryType(MdAttributeDAOIF mdAttribute)
  {
    MdAttributeConcreteDAOIF mdAttributeConcrete = mdAttribute.getMdAttributeConcrete();

    if (mdAttributeConcrete instanceof MdAttributeDateDAOIF)
    {
      return "date";
    }
    else if (mdAttributeConcrete instanceof MdAttributeNumberDAOIF)
    {
      return "number";
    }

    return "text";
  }

  private static JSONObject getMdAttributeType(MdAttribute mdAttribute)
  {
    JSONObject attrObj = new JSONObject();
    MdAttributeConcrete mdAttributeConcrete = getMdAttributeConcrete(mdAttribute);

    // Determine if the attribute is an ontology attribute
    if (mdAttributeConcrete instanceof MdAttributeReference)
    {
      MdAttributeReferenceDAOIF mdAttributeTerm = MdAttributeReferenceDAO.get(mdAttributeConcrete.getId());

      if (mdAttributeTerm.getReferenceMdBusinessDAO().definesType().equals(Term.CLASS))
      {
        try
        {
          // boolean dynamic = ! ( Dashboard.getOptionCount(mdAttribute.getId()) < LIMIT );

          attrObj.put("isOntologyAttribute", true);
          attrObj.put("isTextAttribute", false);
          attrObj.put("relationshipType", TermRelationship.CLASS);
          attrObj.put("termType", Term.CLASS);
          attrObj.put("dynamic", true);

          // if (!dynamic)
          // {
          // attrObj.put("nodes", Dashboard.getClassifierTreeJSON(mdAttribute.getId()));
          // }
        }
        catch (JSONException e)
        {
          throw new ProgrammingErrorException(e);
        }
      }
    }
    else if (mdAttributeConcrete instanceof MdAttributeCharacter || mdAttributeConcrete instanceof MdAttributeText)
    {
      try
      {
        attrObj.put("isTextAttribute", true);
        attrObj.put("isOntologyAttribute", false);
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }
    else
    {
      try
      {
        attrObj.put("isOntologyAttribute", false);
        attrObj.put("isTextAttribute", false);
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return attrObj;
  }

  public static String getOptionsJSON(String thematicAttributeId, String dashboardId)
  {
    try
    {
      Dashboard dashboard = Dashboard.get(dashboardId);
      MdAttribute tAttr = MdAttribute.get(thematicAttributeId);
      MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(thematicAttributeId);

      MappableAttribute mAttribute = MappableAttribute.getMappableAttribute(mdAttribute);
      Boolean aggregatable = ( mAttribute != null ? mAttribute.getAggregatable() : true );

      String[] fonts = DashboardThematicStyle.getSortedFonts();
      String geoNodesJSON = dashboard.getGeoNodesJSON(tAttr, aggregatable);

      JSONArray aggStrategiesJSON = new JSONArray();
      GeoNode[] geoNodes = dashboard.getGeoNodes(tAttr);

      for (GeoNode geoNode : geoNodes)
      {
        String nodeId = geoNode.getId();
        String nodeType = geoNode.getType();
        String nodeLabel = geoNode.getDisplayLabelAttribute().getDisplayLabel().getValue();

        List<AggregationStrategyView> strategies = Arrays.asList(AggregationStrategyView.getAggregationStrategies(geoNode, aggregatable));

        if (strategies.size() > 0)
        {
          Collections.reverse(strategies);

          JSONArray aggregationStrategies = new JSONArray();

          for (AggregationStrategyView strategy : strategies)
          {
            aggregationStrategies.put(strategy.toJSON());
          }

          JSONObject nodeObj = new JSONObject();
          nodeObj.put("nodeId", nodeId);
          nodeObj.put("nodeType", nodeType);
          nodeObj.put("nodeLabel", nodeLabel);
          nodeObj.put("aggregationStrategies", aggregationStrategies);

          aggStrategiesJSON.put(nodeObj);
        }
      }

      JSONArray secondaryAttributes = getSecodaryAttributesJSON(dashboard.getMapId(), thematicAttributeId);
      JSONObject attributeType = getMdAttributeType(tAttr);
      String attrDataType = getCategoryType(mdAttribute);

      // Set possible layer types based on attribute type
      Map<String, String> layerTypes = new LinkedHashMap<String, String>();
      MdAttributeConcrete mdAttributeConcrete = getMdAttributeConcrete(tAttr);

      if (mdAttributeConcrete instanceof MdAttributeDate)
      {
        layerTypes.put(AllLayerType.BASICPOINT.getEnumName(), AllLayerType.BASICPOINT.getDisplayLabel());
        layerTypes.put(AllLayerType.BASICPOLYGON.getEnumName(), AllLayerType.BASICPOLYGON.getDisplayLabel());
      }
      else if (mdAttributeConcrete instanceof MdAttributeReference || mdAttributeConcrete instanceof MdAttributeText || mdAttributeConcrete instanceof MdAttributeCharacter)
      {
        layerTypes.put(AllLayerType.BASICPOINT.getEnumName(), AllLayerType.BASICPOINT.getDisplayLabel());
        layerTypes.put(AllLayerType.CATEGORYPOINT.getEnumName(), AllLayerType.CATEGORYPOINT.getDisplayLabel());
        layerTypes.put(AllLayerType.BASICPOLYGON.getEnumName(), AllLayerType.BASICPOLYGON.getDisplayLabel());
        layerTypes.put(AllLayerType.CATEGORYPOLYGON.getEnumName(), AllLayerType.CATEGORYPOLYGON.getDisplayLabel());
      }
      else
      {
        layerTypes.put(AllLayerType.BASICPOINT.getEnumName(), AllLayerType.BASICPOINT.getDisplayLabel());
        layerTypes.put(AllLayerType.GRADIENTPOINT.getEnumName(), AllLayerType.GRADIENTPOINT.getDisplayLabel());
        layerTypes.put(AllLayerType.CATEGORYPOINT.getEnumName(), AllLayerType.CATEGORYPOINT.getDisplayLabel());
        layerTypes.put(AllLayerType.BUBBLE.getEnumName(), AllLayerType.BUBBLE.getDisplayLabel());
        layerTypes.put(AllLayerType.BASICPOLYGON.getEnumName(), AllLayerType.BASICPOLYGON.getDisplayLabel());
        layerTypes.put(AllLayerType.GRADIENTPOLYGON.getEnumName(), AllLayerType.GRADIENTPOLYGON.getDisplayLabel());
        layerTypes.put(AllLayerType.CATEGORYPOLYGON.getEnumName(), AllLayerType.CATEGORYPOLYGON.getDisplayLabel());
      }

      JSONObject json = new JSONObject();
      json.put("aggregations", getAggregationMethodsAsJSON(thematicAttributeId, aggregatable));
      json.put("aggegationStrategies", aggStrategiesJSON);
      json.put("fonts", new JSONArray(Arrays.asList(fonts)));
      json.put("geoNodes", new JSONArray(geoNodesJSON));

      json.put("attributeType", attributeType);
      json.put("attributeDataType", attrDataType);

      json.put("secondaryAttributes", secondaryAttributes);

      json.put("aggregationMap", DashboardStyle.getAggregationJSON());

      json.put("layerTypeNames", new JSONArray(layerTypes.keySet()));
      json.put("layerTypeLabels", new JSONArray(layerTypes.values()));

      JSONArray pointTypes = new JSONArray();
      pointTypes.put("CIRCLE");
      pointTypes.put("STAR");
      pointTypes.put("SQUARE");
      pointTypes.put("TRIANGLE");
      pointTypes.put("CROSS");
      pointTypes.put("X");
      json.put("pointTypes", pointTypes);

      if (aggStrategiesJSON.length() == 0)
      {
        throw new MissingLocationAttributeException();
      }

      return json.toString();
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private static JSONArray getAggregationMethodsAsJSON(String thematicAttributeId, boolean aggregatable)
  {
    try
    {
      JSONArray methods = new JSONArray();

      if (aggregatable)
      {
        OIterator<? extends AggregationType> aggregations = DashboardStyle.getSortedAggregations(thematicAttributeId).getIterator();

        try
        {
          for (AggregationType aggMethod : aggregations)
          {
            String formattedAggMethod = aggMethod.toString().replaceAll(".*\\.", "");

            JSONObject aggMethodObj = new JSONObject();
            aggMethodObj.put("method", formattedAggMethod);
            aggMethodObj.put("label", aggMethod.getDisplayLabel());
            aggMethodObj.put("id", aggMethod.getId());

            methods.put(aggMethodObj);
          }
        }
        finally
        {
          aggregations.close();
        }
      }
      else
      {
        JSONObject method = new JSONObject();
        method.put("method", AllAggregationType.NONE.name());
        method.put("label", AllAggregationType.NONE.getDisplayLabel());
        method.put("id", AllAggregationType.NONE.getId());

        methods.put(method);
      }

      return methods;
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  private static JSONArray getSecodaryAttributesJSON(String mapId, String mdAttributeId)
  {
    JSONArray secAttrs = new JSONArray();
    MdAttributeView[] secondaryAttributes = DashboardMap.getSecondaryAttributes(mapId, mdAttributeId);

    try
    {
      JSONObject object = new JSONObject();
      object.put("label", "None");
      object.put("id", "NONE");

      secAttrs.put(object);
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }

    for (MdAttributeView secAttr : secondaryAttributes)
    {
      JSONObject secAttrObj = new JSONObject();
      try
      {
        MdAttributeConcreteDAOIF mdAttributeConcrete = MdAttributeDAO.get(secAttr.getMdAttributeId()).getMdAttributeConcrete();

        secAttrObj.put("id", secAttr.getId());
        secAttrObj.put("mdAttributeId", secAttr.getMdAttributeId());
        secAttrObj.put("type", secAttr.getAttributeType());
        secAttrObj.put("label", secAttr.getDisplayLabel());
        secAttrObj.put("categoryType", DashboardThematicLayer.getCategoryType(mdAttributeConcrete));

        if (mdAttributeConcrete instanceof MdAttributeReferenceDAOIF)
        {
          MdAttributeReferenceDAOIF mdAttributeTerm = (MdAttributeReferenceDAOIF) mdAttributeConcrete;

          if (mdAttributeTerm.getReferenceMdBusinessDAO().definesType().equals(Term.CLASS))
          {
            // boolean dynamic = ! ( Dashboard.getOptionCount(secAttr.getMdAttributeId()) < LIMIT );

            secAttrObj.put("dynamic", true);

            // if (!dynamic)
            // {
            // secAttrObj.put("nodes", Dashboard.getClassifierTreeJSON(secAttr.getMdAttributeId()));
            // }
          }
        }

        secAttrs.put(secAttrObj);
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return secAttrs;
  }

  public JSONObject toJSON()
  {
    try
    {
      DashboardLegend legend = this.getDashboardLegend();

      JSONObject json = new JSONObject();
      json.put("viewName", getViewName());
      json.put("sldName", getSLDName());
      json.put("layerName", getName());
      json.put("layerId", getId());
      json.put("inLegend", this.getDisplayInLegend());
      json.put("legendXPosition", legend.getLegendXPosition());
      json.put("legendYPosition", legend.getLegendYPosition());
      json.put("groupedInLegend", legend.getGroupedInLegend());
      json.put("featureStrategy", getFeatureStrategy());
      json.put("mdAttributeId", this.getMdAttributeId());
      json.put("attributeType", this.getAttributeType());
      json.put("aggregationMethod", this.getAggregationMethod());
      json.put("aggregationAttribute", this.getAttribute());
      json.put("layerType", layerType);
      json.put("attributeLabel", this.getAttributeDisplayLabel());
      json.put("geoNodeId", this.getGeoNodeId());

      AggregationStrategy aggStrategy = this.getAggregationStrategy();
      JSONObject aggStratJSON = ( aggStrategy != null ) ? aggStrategy.getJSON() : new JSONObject();
      json.put("aggregationStrategy", aggStratJSON);

      JSONArray jsonStyles = new JSONArray();
      List<? extends DashboardStyle> styles = this.getStyles();
      for (int i = 0; i < styles.size(); ++i)
      {
        DashboardStyle style = styles.get(i);
        jsonStyles.put(style.toJSON());
      }
      json.put("styles", jsonStyles);

      return json;
    }
    catch (JSONException ex)
    {
      log.error("Could not properly form DashboardLayer [" + this.toString() + "] into valid JSON to send back to the client.");
      throw new ProgrammingErrorException(ex);
    }
  }

  public String getAttributeDisplayLabel()
  {
    MdAttributeDAOIF mdAttribute = this.getMdAttributeDAO();

    String label = mdAttribute.getDisplayLabel(Session.getCurrentLocale());

    if (label == null || label.length() == 0)
    {
      return mdAttribute.getMdAttributeConcrete().getDisplayLabel(Session.getCurrentLocale());
    }

    return label;
  }

  /**
   * @prerequisite conditions is populated with any DashboardConditions necessary for restricting the view dataset.
   * 
   * @return A ValueQuery for use in creating/dropping the database view which will be used with GeoServer.
   */
  @Override
  public ValueQuery getViewQuery(LinkedList<Drilldown> drilldowns)
  {
    AggregationStrategy strategy = this.getAggregationStrategy();

    return strategy.getViewQuery(this, drilldowns);
  }

  public DashboardStyle getStyle()
  {
    OIterator<? extends DashboardStyle> iter = this.getAllHasStyle();

    try
    {
      while (iter.hasNext())
      {
        return iter.next();

      }
    }
    finally
    {
      iter.close();
    }

    throw new ProgrammingErrorException("Dashboard layer exists without a style");
  }

  @Override
  public void accepts(MapVisitor visitor)
  {
    visitor.visit(this);
  }

  public AllAggregationType getAggregationMethod()
  {
    List<AllAggregationType> allAgg = this.getAggregationType();

    if (allAgg.size() > 0)
    {
      return allAgg.get(0);
    }

    return null;
  }

  public MdAttributeDAOIF getMdAttributeDAO()
  {
    String mdAttributeId = this.getMdAttributeId();

    if (mdAttributeId != null && mdAttributeId.length() > 0)
    {
      return MdAttributeDAO.get(mdAttributeId);
    }

    return null;
  }

  @Override
  public AttributeType getAttributeType()
  {
    MdAttributeConcreteDAOIF mdAttribute = this.getMdAttributeDAO().getMdAttributeConcrete();

    if (mdAttribute instanceof MdAttributeDateDAOIF)
    {
      return AttributeType.DATE;
    }
    else if (mdAttribute instanceof MdAttributeDateTimeDAOIF)
    {
      return AttributeType.DATETIME;
    }
    else if (mdAttribute instanceof MdAttributeTimeDAOIF)
    {
      return AttributeType.TIME;
    }
    else if (mdAttribute instanceof MdAttributeNumberDAOIF)
    {
      return AttributeType.NUMBER;
    }

    return AttributeType.BASIC;
  }

  @Override
  public String getAttribute()
  {
    return MdAttributeDAO.get(this.getMdAttributeId()).definesAttribute();
  }

  protected void populate(DashboardLayer source)
  {
    super.populate(source);

    if (source instanceof DashboardThematicLayer)
    {
      DashboardThematicLayer tSource = (DashboardThematicLayer) source;

      List<AllAggregationType> types = tSource.getAggregationType();

      for (AllAggregationType type : types)
      {
        this.addAggregationType(type);
      }

      this.setMdAttribute(tSource.getMdAttribute());
      this.setGeoNode(tSource.getGeoNode());
      this.setAggregationStrategy(tSource.getAggregationStrategy().clone());
    }
  }

  @Override
  protected DashboardLayer newInstance()
  {
    return new DashboardThematicLayer();
  }

  public String getCategoryLabel(String categoryId)
  {
    AggregationStrategy strategy = this.getAggregationStrategy();
    GeoNode geoNode = this.getGeoNode();

    return strategy.getCategoryLabel(geoNode, categoryId);
  }

  public static String getGeoNodeGeometryTypesJSON(String geoNodeId)
  {
    JSONArray json = new JSONArray();
    GeoNode geoNode = GeoNode.get(geoNodeId);

    MdAttribute mdAttributeGeometry = geoNode.getGeometryAttribute();
    if (mdAttributeGeometry != null)
    {
      json.put(mdAttributeGeometry.getType());
    }

    MdAttributePoint mdAttributePoint = geoNode.getPointAttribute();
    if (mdAttributePoint != null)
    {
      json.put(mdAttributePoint.getType());
    }

    MdAttributeMultiPolygon mdAttributeMultiPolygon = geoNode.getMultiPolygonAttribute();
    if (mdAttributeMultiPolygon != null)
    {
      json.put(mdAttributeMultiPolygon.getType());
    }

    return json.toString();
  }
}
