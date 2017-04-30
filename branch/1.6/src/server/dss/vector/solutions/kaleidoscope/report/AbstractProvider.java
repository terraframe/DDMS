package dss.vector.solutions.kaleidoscope.report;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeLocal;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.Coalesce;
import com.runwaysdk.query.F;
import com.runwaysdk.query.GeneratedBusinessQuery;
import com.runwaysdk.query.GeneratedComponentQuery;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.SelectableReference;
import com.runwaysdk.query.SelectableSQLCharacter;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeReference;

import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.LocatedInQuery;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.GeoEntityQuery.GeoEntityQueryReferenceIF;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategy;
import dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategyView;
import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType;
import dss.vector.solutions.kaleidoscope.dashboard.GeometryAggregationStrategy;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.kaleidoscope.dashboard.query.ThematicQueryBuilder;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.util.LocalizationFacade;
import dss.vector.solutions.util.QueryUtil;

public abstract class AbstractProvider implements Reloadable, ReportProviderIF
{
  public static final String CHILDREN     = "1";

  public static final String GRANDKIDS    = "2";

  public static final String SIBLINGS     = "3";

  public static final String COUSINS      = "4";

  public static final String PARENTS      = "5";

  public static final String GRANDPARENTS = "6";

  public static final String NONE         = "7";

  public static final String TYPE         = "8";

  @Override
  public boolean hasSupport(String queryId)
  {
    List<PairView> views = this.getSupportedQueryDescriptors();

    for (PairView view : views)
    {
      String value = view.getValue();

      if (value.equals(queryId))
      {
        return true;
      }
    }

    return false;
  }

  protected void addSelectables(GeneratedBusinessQuery query, List<ReportAttributeMetadata> attributes, ValueQuery vQuery)
  {
    MdEntityDAOIF mdClass = query.getMdClassIF();

    for (ReportAttributeMetadata attribute : attributes)
    {
      String attributeName = attribute.getAttributeName();

      SelectablePrimitive selectable = this.getSelectable(vQuery, query, attribute);
      selectable.setColumnAlias(attributeName);
      selectable.setUserDefinedAlias(attributeName);
      selectable.setUserDefinedDisplayLabel(mdClass.definesAttribute(attributeName).getDisplayLabel(Session.getCurrentLocale()));

      vQuery.SELECT(selectable);

      if (attribute.isOrderBy())
      {
        vQuery.ORDER_BY_DESC(selectable);
      }
    }
  }

  protected SelectablePrimitive getSelectable(ValueQuery vQuery, GeneratedComponentQuery query, ReportAttributeMetadata attribute)
  {
    Attribute selectable = query.get(attribute.getAttributeName());

    if (selectable instanceof SelectableReference)
    {
      MdAttributeReferenceDAOIF mdAttribute = (MdAttributeReferenceDAOIF) selectable.getMdAttributeIF();

      if (mdAttribute.getReferenceMdBusinessDAO().definesType().equals(Term.CLASS))
      {
        AttributeReference reference = (AttributeReference) selectable;
        TermQuery cQuery = new TermQuery(vQuery);

        vQuery.WHERE(reference.LEFT_JOIN_EQ(cQuery));

        return cQuery.getTermDisplayLabel().localize();
      }
    }
    else if (attribute.getAggregation() != null && attribute.getAggregation().equals(AllAggregationType.SUM))
    {
      return F.SUM(selectable);
    }

    return (SelectablePrimitive) selectable;
  }

  protected boolean hasDepth(GeoEntity geoEntity, int minDepth)
  {
    GeoHierarchy root = GeoHierarchy.getGeoHierarchyFromType(Earth.CLASS);
    GeoHierarchy universal = GeoHierarchy.getGeoHierarchyFromType(geoEntity.getType());

    int depth = this.getDepth(root, universal, 0);

    return ( depth > minDepth );
  }

  protected int getDepth(GeoHierarchy root, GeoHierarchy universal, int depth)
  {

    if (root.getId().equals(universal.getId()))
    {
      return depth;
    }

    int parentDepth = Integer.MAX_VALUE;

    List<? extends GeoHierarchy> parents = universal.getAllAllowedInGeoEntity().getAll();

    for (GeoHierarchy parent : parents)
    {
      parentDepth = Math.min(parentDepth, this.getDepth(root, parent, depth + 1));
    }

    return parentDepth;
  }

  protected void addLocationQuery(QueryConfiguration config, SelectableReference geoEntityAttribute, GeneratedComponentQuery... queries)
  {
    if (config.hasLayerId())
    {
      String layerId = config.getLayerId();

      DashboardThematicLayer layer = DashboardThematicLayer.get(layerId);
      AggregationStrategy strategy = layer.getAggregationStrategy();

      if (strategy instanceof GeometryAggregationStrategy)
      {
        if (this.isValid(layer, queries))
        {
          this.addGeometryQuery(config, queries[0], layer);
        }
        else
        {
          this.addGeoEntityQuery(config, geoEntityAttribute);
        }
      }
      else
      {
        this.addGeoEntityQuery(config, geoEntityAttribute);
      }
    }
    else
    {
      this.addGeoEntityQuery(config, geoEntityAttribute);
    }
  }

  private boolean isValid(DashboardThematicLayer layer, GeneratedComponentQuery... queries)
  {
    MdAttributeDAOIF layerAttribute = MdAttributeDAO.get(layer.getMdAttributeId());
    String attributeName = layerAttribute.definesAttribute();

    for (GeneratedComponentQuery query : queries)
    {
      MdClassDAOIF mdClassIF = query.getMdClassIF();
      MdAttributeDAOIF mdAttribute = mdClassIF.definesAttribute(attributeName);

      if (mdAttribute != null)
      {
        return true;
      }
    }

    return false;
  }

  private void addGeometryQuery(QueryConfiguration config, GeneratedComponentQuery query, DashboardThematicLayer layer)
  {
    String categoryId = config.getCategory();

    ValueQuery vQuery = config.getValueQuery();
    String aggregation = config.getAggregation();

    GeoNode geoNode = layer.getGeoNode();
    MdAttribute identifierAttribute = geoNode.getIdentifierAttribute();
    MdAttribute displayLabelAttribute = geoNode.getDisplayLabelAttribute();
    MdAttributeReference entityAttribute = geoNode.getGeoEntityAttribute();

    Selectable geoLocation = query.get(displayLabelAttribute.getAttributeName());

    if (geoLocation instanceof AttributeLocal)
    {
      geoLocation = ( (AttributeLocal) geoLocation ).localize();
    }

    geoLocation.setColumnAlias(entityAttribute.getAttributeName());
    geoLocation.setUserDefinedAlias(entityAttribute.getAttributeName());
    geoLocation.setUserDefinedDisplayLabel(entityAttribute.getDisplayLabel().getValue());

    Selectable geoId = query.get(identifierAttribute.getAttributeName());
    geoId.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId.setUserDefinedDisplayLabel(GeoEntity.getGeoIdMd().getDisplayLabel(Session.getCurrentLocale()));

    SelectableSQLCharacter categoryLabel = vQuery.aSQLCharacter("categoryLabel", "'" + layer.getCategoryLabel(categoryId) + "'");
    categoryLabel.setColumnAlias("categoryLabel");
    categoryLabel.setUserDefinedAlias("categoryLabel");
    categoryLabel.setUserDefinedDisplayLabel(LocalizationFacade.getFromBundles("categoryLabel"));

    vQuery.SELECT(geoLocation, geoId, categoryLabel);

    if (aggregation != null && aggregation.equals(TYPE))
    {
      String label = AggregationStrategyView.getDisplayLabel(geoNode);

      SelectableSQLCharacter universal = vQuery.aSQLCharacter("universalLabel", "'" + label + "'");
      universal.setColumnAlias("universalLabel");
      universal.setUserDefinedAlias("universalLabel");
      universal.setUserDefinedDisplayLabel(LocalizationFacade.getFromBundles("universalLabel"));

      vQuery.SELECT(universal);
    }
    else if (aggregation != null && aggregation.equals(COUSINS))
    {
      // Cousins
      GeoEntityQueryReferenceIF parent = (GeoEntityQueryReferenceIF) query.get(entityAttribute.getAttributeName());

      LocatedInQuery parentQuery = new LocatedInQuery(vQuery);

      vQuery.WHERE(parentQuery.getChild().EQ(parent));

      this.addGeoLabel(vQuery, "parent", parent);
      this.addGeoLabel(vQuery, "grandparent", parentQuery.getParent());
    }
    else if (aggregation != null && aggregation.equals(SIBLINGS))
    {
      // Siblings
      GeoEntityQueryReferenceIF parent = (GeoEntityQueryReferenceIF) query.get(entityAttribute.getAttributeName());
      this.addGeoLabel(vQuery, "parent", parent);
    }
  }

  private void addGeoEntityQuery(QueryConfiguration config, SelectableReference geoEntityAttribute)
  {
    ValueQuery vQuery = config.getValueQuery();
    String aggregation = config.getAggregation();
    GeoEntity geoEntity = this.getGeoEntity(config);

    GeoEntityQuery entityQuery = new GeoEntityQuery(vQuery);

    MdAttributeConcreteDAOIF mdAttribute = geoEntityAttribute.getMdAttributeIF();

    Coalesce geoLocation = entityQuery.getEntityLabel().localize();
    geoLocation.setColumnAlias(mdAttribute.definesAttribute());
    geoLocation.setUserDefinedAlias(mdAttribute.definesAttribute());
    geoLocation.setUserDefinedDisplayLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

    SelectableChar geoId = entityQuery.getGeoId();
    geoId.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId.setUserDefinedDisplayLabel(GeoEntity.getGeoIdMd().getDisplayLabel(Session.getCurrentLocale()));

    vQuery.SELECT(geoLocation, geoId);

    this.addGeoLabel(vQuery, "category", geoEntity);

    if (aggregation != null && aggregation.equals(COUSINS))
    {
      // cousins
      LocatedInQuery parentQuery = new LocatedInQuery(vQuery);
      LocatedInQuery grandParentQuery = new LocatedInQuery(vQuery);
      LocatedInQuery uncleQuery = new LocatedInQuery(vQuery);
      LocatedInQuery cuzQuery = new LocatedInQuery(vQuery);
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(parentQuery.getChild().EQ(geoEntity));
      vQuery.AND(grandParentQuery.getChild().EQ(parentQuery.getParent()));
      vQuery.AND(uncleQuery.getParent().EQ(grandParentQuery.getParent()));
      vQuery.AND(cuzQuery.getParent().EQ(uncleQuery.getChild()));
      vQuery.AND(aptQuery.getParentGeoEntity().EQ(cuzQuery.getChild()));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));

      this.addGeoLabel(vQuery, "parent", parentQuery.getParent());
      this.addGeoLabel(vQuery, "grandparent", grandParentQuery.getParent());
    }
    else if (aggregation != null && aggregation.equals(SIBLINGS))
    {
      // Siblings
      LocatedInQuery parentQuery = new LocatedInQuery(vQuery);
      LocatedInQuery siblingQuery = new LocatedInQuery(vQuery);
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(parentQuery.getChild().EQ(geoEntity));
      vQuery.AND(siblingQuery.getParent().EQ(parentQuery.getParent()));
      vQuery.AND(aptQuery.getParentGeoEntity().EQ(siblingQuery.getChild()));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));

      this.addGeoLabel(vQuery, "parent", parentQuery.getParent());
    }
    else if (aggregation != null && aggregation.equals(CHILDREN))
    {
      // Kids
      LocatedInQuery kidsQuery = new LocatedInQuery(vQuery);
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(kidsQuery.getParent().EQ(geoEntity));
      vQuery.AND(aptQuery.getParentGeoEntity().EQ(kidsQuery.getChild()));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));
    }
    else if (aggregation != null && aggregation.equals(GRANDKIDS))
    {
      // Kids
      LocatedInQuery kidsQuery = new LocatedInQuery(vQuery);
      LocatedInQuery grandKidQuery = new LocatedInQuery(vQuery);
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(kidsQuery.getParent().EQ(geoEntity));
      vQuery.WHERE(grandKidQuery.getParent().EQ(kidsQuery.getChild()));
      vQuery.AND(aptQuery.getParentGeoEntity().EQ(grandKidQuery.getChild()));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));
    }
    else if (aggregation != null && aggregation.equals(PARENTS) && this.hasDepth(geoEntity, 1))
    {
      LocatedInQuery lQuery = new LocatedInQuery(vQuery);
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(lQuery.getChild().EQ(geoEntity));
      vQuery.AND(aptQuery.getParentGeoEntity().EQ(lQuery.getParent()));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));
    }
    else if (aggregation != null && aggregation.equals(GRANDPARENTS) && this.hasDepth(geoEntity, 2))
    {
      LocatedInQuery lQuery1 = new LocatedInQuery(vQuery);
      LocatedInQuery lQuery2 = new LocatedInQuery(vQuery);
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(lQuery1.getChild().EQ(geoEntity));
      vQuery.AND(lQuery2.getChild().EQ(lQuery1.getParent()));
      vQuery.AND(aptQuery.getParentGeoEntity().EQ(lQuery2.getParent()));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));
    }
    else if (aggregation != null && aggregation.equals(TYPE))
    {
      MdClassDAOIF mdClass = geoEntity.getMdClass();

      GeoEntityQuery gQuery = (GeoEntityQuery) QueryUtil.getQuery(mdClass, vQuery);

      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(aptQuery.getParentGeoEntity().EQ(gQuery));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));
    }
    else
    {
      // Direct
      AllPathsQuery aptQuery = new AllPathsQuery(vQuery);

      vQuery.WHERE(aptQuery.getParentGeoEntity().EQ(geoEntity));
      vQuery.AND(entityQuery.EQ(aptQuery.getParentGeoEntity()));
      vQuery.AND(geoEntityAttribute.EQ(aptQuery.getChildGeoEntity()));
    }
  }

  private void addGeoLabel(ValueQuery vQuery, String suffix, SelectableReference parent)
  {
    GeoEntityQuery parentEntityQuery = new GeoEntityQuery(vQuery);

    Coalesce parentLocation = parentEntityQuery.getEntityLabel().localize();
    parentLocation.setColumnAlias(suffix + "Label");
    parentLocation.setUserDefinedAlias(suffix + "Label");
    parentLocation.setUserDefinedDisplayLabel(LocalizationFacade.getFromBundles(suffix + "Label"));

    SelectableChar parentGeoId = parentEntityQuery.getGeoId();
    parentGeoId.setColumnAlias(suffix + "GeoId");
    parentGeoId.setUserDefinedAlias(suffix + "GeoId");
    parentGeoId.setUserDefinedDisplayLabel(LocalizationFacade.getFromBundles(suffix + "GeoId"));

    vQuery.SELECT(parentLocation, parentGeoId);
    vQuery.AND(parentEntityQuery.EQ(parent));
  }

  private void addGeoLabel(ValueQuery vQuery, String suffix, GeoEntity entity)
  {
    GeoEntityQuery query = new GeoEntityQuery(vQuery);

    Coalesce parentLocation = query.getEntityLabel().localize();
    parentLocation.setColumnAlias(suffix + "Label");
    parentLocation.setUserDefinedAlias(suffix + "Label");
    parentLocation.setUserDefinedDisplayLabel(LocalizationFacade.getFromBundles(suffix + "Label"));

    vQuery.SELECT(parentLocation);
    vQuery.AND(query.getId().EQ(entity.getId()));
  }

  @Override
  public PairView[] getSupportedAggregation(String queryId)
  {
    List<PairView> list = new LinkedList<PairView>();

    list.add(PairView.create(NONE, "NONE"));
    list.add(PairView.create(CHILDREN, "CHILDREN"));
    list.add(PairView.create(GRANDKIDS, "GRANDKIDS"));
    list.add(PairView.create(PARENTS, "PARENTS"));
    list.add(PairView.create(GRANDPARENTS, "GRANDPARENTS"));
    list.add(PairView.create(SIBLINGS, "SIBLINGS"));
    list.add(PairView.create(COUSINS, "COUSINS"));
    list.add(PairView.create(TYPE, "TYPE"));

    return list.toArray(new PairView[list.size()]);
  }

  private GeoEntity getGeoEntity(QueryConfiguration config)
  {
    if (config.hasCategory())
    {
      String category = config.getCategory();

      try
      {
        return GeoEntity.getByKey(category);
      }
      catch (Exception e)
      {
        // Incoming data is bad, just use the default geo id
      }
    }

    if (config.hasDefaultGeoId())
    {
      return GeoEntity.get(config.getDefaultGeoId());
    }

    throw new ProgrammingErrorException("No default geo entity has been provided");
  }
}
