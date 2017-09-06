package dss.vector.solutions.kaleidoscope.dashboard.query;

import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AggregateFunction;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.AttributeChar;
import com.runwaysdk.query.AttributeCharacter;
import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.F;
import com.runwaysdk.query.GeneratedComponentQuery;
import com.runwaysdk.query.OrderBy;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableBoolean;
import com.runwaysdk.query.SelectableIndicator;
import com.runwaysdk.query.SelectableMoment;
import com.runwaysdk.query.SelectableNumber;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.geoserver.GeoserverFacade;
import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardThematicStyle;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardAttributeCondition;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.kaleidoscope.dashboard.condition.LocationCondition;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.util.QueryUtil;

public abstract class ThematicQueryBuilder implements Reloadable
{
  public static final String       LOCATION_ALIAS = "geo_id_00";

  public static final String       LABEL_ALIAS    = "display_label_00";

  private QueryFactory             factory;

  private MdAttributeDAOIF         thematicMdAttribute;

  private DashboardStyle           style;

  private AllAggregationType       aggregation;

  private List<DashboardCondition> conditions;

  public ThematicQueryBuilder(QueryFactory factory, DashboardThematicLayer layer)
  {
    this.factory = factory;
    this.thematicMdAttribute = layer.getMdAttributeDAO();
    this.style = layer.getStyle();
    this.aggregation = layer.getAggregationMethod();
    this.conditions = layer.getConditions();
  }

  public ThematicQueryBuilder(QueryFactory factory, MdAttributeDAOIF thematicMdAttribute, DashboardStyle style, AllAggregationType aggregation, List<DashboardCondition> conditions)
  {
    this.factory = factory;
    this.thematicMdAttribute = thematicMdAttribute;
    this.style = style;
    this.aggregation = aggregation;
    this.conditions = conditions;
  }

  protected abstract SelectableSingle getLabelSelectable(GeneratedComponentQuery query);

  protected abstract Selectable getIdentifierSelectable(GeneratedComponentQuery query);

  protected abstract void initialize(ValueQuery vQuery);

  protected abstract void addLocationCriteria(ValueQuery vQuery, GeneratedComponentQuery componentQuery);

  protected abstract void addLocationCondition(ValueQuery vQuery, GeneratedComponentQuery componentQuery, LocationCondition condition);

  public ValueQuery getThematicValueQuery()
  {

    // IMPORTANT - Everything is going to be a 'thematic layer' in IDE,
    // but we need to define a non-thematic's behavior or even finalize
    // on the semantics of a layer without a thematic attribute...which might
    // not even exist!
    if (style != null && style instanceof DashboardThematicStyle)
    {
      ValueQuery thematicQuery = this.build();

      DashboardThematicStyle tStyle = (DashboardThematicStyle) style;

      MdAttributeDAOIF secondaryMdAttribute = tStyle.getSecondaryAttributeDAO();

      if (secondaryMdAttribute != null)
      {
        AttributeCharacter thematicGeoId = thematicQuery.aCharacter(ThematicQueryBuilder.LOCATION_ALIAS);
        thematicGeoId.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
        thematicGeoId.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);

        AttributeChar thematicLabel = (AttributeChar) thematicQuery.get(ThematicQueryBuilder.LABEL_ALIAS);
        thematicLabel.setColumnAlias(ThematicQueryBuilder.LABEL_ALIAS);
        thematicLabel.setUserDefinedAlias(ThematicQueryBuilder.LABEL_ALIAS);

        Attribute thematicAttribute = thematicQuery.get(thematicMdAttribute.definesAttribute());
        thematicAttribute.setColumnAlias(thematicMdAttribute.definesAttribute());

        ValueQuery innerQuery = new ValueQuery(factory);
        innerQuery.SELECT(thematicGeoId, thematicLabel, thematicAttribute);

        if (!secondaryMdAttribute.getId().equals(thematicMdAttribute.getId()))
        {
          AllAggregationType secondaryAggregation = tStyle.getSecondaryAttributeAggregationMethod();

          ValueQuery secondaryQuery = this.build(secondaryMdAttribute, secondaryAggregation);

          AttributeCharacter secondaryGeoId = secondaryQuery.aCharacter(ThematicQueryBuilder.LOCATION_ALIAS);
          secondaryGeoId.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
          secondaryGeoId.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);

          Attribute secondaryAttribute = secondaryQuery.get(secondaryMdAttribute.definesAttribute());
          secondaryAttribute.setColumnAlias(secondaryMdAttribute.definesAttribute());
          secondaryAttribute.setUserDefinedDisplayLabel(secondaryMdAttribute.getDisplayLabel(Session.getCurrentLocale()));

          innerQuery.SELECT(secondaryAttribute);
          innerQuery.WHERE(thematicGeoId.LEFT_JOIN_EQ(secondaryGeoId));
        }

        return innerQuery;
      }
      else
      {
        return thematicQuery;
      }
    }
    else
    {
      return this.build();
    }
  }

  private ValueQuery build()
  {
    return this.build(this.thematicMdAttribute, aggregation);
  }

  private ValueQuery build(MdAttributeDAOIF mdAttribute, AllAggregationType aggregation)
  {
    ValueQuery vQuery = new ValueQuery(factory);

    this.initialize(vQuery);

    MdClassDAOIF mdClass = mdAttribute.definedByClass();

    GeneratedComponentQuery query = QueryUtil.getQuery(mdClass, factory);

    // thematic attribute
    String attributeName = mdAttribute.definesAttribute();
    String displayLabel = mdAttribute.getDisplayLabel(Session.getCurrentLocale());

    Selectable thematicAttr = query.getS(attributeName);
    // use the basic Selectable if no aggregate is selected
    Selectable thematicSel = thematicAttr;

    Selectable label = this.getLabelSelectable(query);
    Selectable id = this.getIdentifierSelectable(query);

    if (thematicSel instanceof SelectableNumber || thematicSel instanceof SelectableMoment || thematicSel instanceof SelectableBoolean  || thematicSel instanceof SelectableIndicator)
    {
      boolean isAggregate = false;

      if (aggregation != null)
      {
        // String func = null;
        if (aggregation == AllAggregationType.SUM)
        {
          // func = "SUM";
          thematicSel = F.SUM(thematicAttr);
        }
        else if (aggregation == AllAggregationType.MIN)
        {
          // func = "MIN";
          thematicSel = F.MIN(thematicAttr);
        }
        else if (aggregation == AllAggregationType.MAX)
        {
          // func = "MAX";
          thematicSel = F.MAX(thematicAttr);
        }
        else if (aggregation == AllAggregationType.AVG)
        {
          // func = "AVG";
          thematicSel = F.AVG(thematicAttr);
        }
        isAggregate = true;
      }

      Integer length = GeoserverFacade.getDecimalLength();
      Integer precision = GeoserverFacade.getDecimalPrecision();

      String sql;

      if (thematicSel instanceof SelectableMoment)
      {
        sql = thematicSel.getSQL();
      }
      else
      {
        sql = thematicSel.getSQL() + "::decimal(" + length + "," + precision + ")";
      }

      if (isAggregate)
      {
        thematicSel = vQuery.aSQLAggregateDouble(thematicSel.getResultAttributeName(), sql, attributeName, displayLabel);
      }
      else
      {
        thematicSel = vQuery.aSQLDouble(thematicSel.getResultAttributeName(), sql, attributeName, displayLabel);
      }

      thematicSel.setColumnAlias(attributeName);

      this.setCriteriaOnInnerQuery(vQuery, query);

      vQuery.SELECT(thematicSel, label, id);
    }
    else
    {
      if (aggregation != null)
      {
        OrderBy.SortOrder sortOrder;

        if (aggregation == AllAggregationType.MAJORITY)
        {
          // func = "MAJORITY";
          sortOrder = OrderBy.SortOrder.DESC;
        }
        else
        // (agg == AllAggregationType.MINORITY)
        {
          // func = "MINORITY";
          sortOrder = OrderBy.SortOrder.ASC;
        }

        ValueQuery winFuncQuery = new ValueQuery(factory);

        if (mdAttribute.getMdAttributeConcrete() instanceof MdAttributeReferenceDAOIF)
        {
          MdAttributeReferenceDAOIF MdAttributeReferenceDAOIF = (MdAttributeReferenceDAOIF) mdAttribute.getMdAttributeConcrete();
          if (MdAttributeReferenceDAOIF.getReferenceMdBusinessDAO().definesType().equals(Term.CLASS))
          {
            AttributeReference thematicTerm = (AttributeReference) thematicAttr;

            TermQuery classifierQ = new TermQuery(winFuncQuery);
            winFuncQuery.WHERE(classifierQ.EQ(thematicTerm));

            thematicAttr = classifierQ.getTermDisplayLabel().localize();
            thematicAttr.setUserDefinedDisplayLabel(MdAttributeReferenceDAOIF.getDisplayLabel(Session.getCurrentLocale()));
          }
        }

        thematicSel = F.COUNT(thematicAttr, "COUNT");
        AggregateFunction stringAgg = F.STRING_AGG(thematicAttr, ", ", "AGG").OVER(F.PARTITION_BY(F.COUNT(thematicAttr), id));
        stringAgg.setUserDefinedDisplayLabel(thematicAttr.getUserDefinedDisplayLabel());

        AggregateFunction rank = query.RANK("RANK").OVER(F.PARTITION_BY(id), new OrderBy(F.COUNT(thematicAttr), sortOrder));

        winFuncQuery.SELECT_DISTINCT(thematicSel);
        winFuncQuery.SELECT_DISTINCT(stringAgg);
        winFuncQuery.SELECT_DISTINCT(rank);
        winFuncQuery.SELECT_DISTINCT(label);
        winFuncQuery.SELECT_DISTINCT(id);
        winFuncQuery.GROUP_BY((SelectableSingle) thematicAttr, (SelectableSingle) id);
        winFuncQuery.ORDER_BY(thematicSel, sortOrder);

        this.setCriteriaOnInnerQuery(winFuncQuery, query);

        Selectable outerThematicSel = winFuncQuery.get("AGG");
        outerThematicSel.setUserDefinedAlias(attributeName);
        outerThematicSel.setColumnAlias(attributeName);

        Selectable outerLabel = winFuncQuery.get(ThematicQueryBuilder.LABEL_ALIAS);
        outerLabel.setUserDefinedAlias(ThematicQueryBuilder.LABEL_ALIAS);
        outerLabel.setColumnAlias(ThematicQueryBuilder.LABEL_ALIAS);

        Selectable outerGeoId = winFuncQuery.get(ThematicQueryBuilder.LOCATION_ALIAS);
        outerGeoId.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
        outerGeoId.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);

        vQuery.SELECT(outerThematicSel);
        vQuery.SELECT(outerLabel);
        vQuery.SELECT(outerGeoId);
        vQuery.WHERE(winFuncQuery.aSQLAggregateInteger("RANK", rank.getColumnAlias()).EQ(1));
      }

      // Assumes isAggregate is true
    }

    return vQuery;
  }

  private void setCriteriaOnInnerQuery(ValueQuery vQuery, GeneratedComponentQuery componentQuery)
  {
    MdClassDAOIF mdClass = componentQuery.getMdClassIF();

    this.addLocationCriteria(vQuery, componentQuery);

    // Attribute condition filtering (i.e. sales unit is greater than 50)

    if (conditions != null)
    {
      for (DashboardCondition condition : conditions)
      {
        if (condition instanceof DashboardAttributeCondition)
        {
          String mdAttributeId = ( (DashboardAttributeCondition) condition ).getMdAttributeId();

          MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(mdAttributeId);
          MdClassDAOIF definedByClass = mdAttribute.definedByClass();

          if (definedByClass.getId().equals(mdClass.getId()))
          {
            Selectable attr = componentQuery.get(mdAttribute.definesAttribute());

            condition.restrictQuery(vQuery, attr);
          }
        }
        else if (condition instanceof LocationCondition)
        {
          this.addLocationCondition(vQuery, componentQuery, (LocationCondition) condition);
        }
      }
    }
  }
}
