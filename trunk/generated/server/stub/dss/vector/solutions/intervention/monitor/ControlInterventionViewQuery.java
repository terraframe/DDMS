package dss.vector.solutions.intervention.monitor;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.AllPathsQuery;

/**
 * 
 * @author Autogenerated by RunwaySDK
 */
public class ControlInterventionViewQuery extends dss.vector.solutions.intervention.monitor.ControlInterventionViewQueryBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2024864936;

  public ControlInterventionViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultControlInterventionViewBuilder(queryFactory));
  }

  public ControlInterventionViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  static class DefaultControlInterventionViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private ControlInterventionQuery query;

    public DefaultControlInterventionViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);

      this.query = new ControlInterventionQuery(queryFactory);
    }

    protected ControlInterventionViewQuery getViewQuery()
    {
      return (ControlInterventionViewQuery) super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      ControlInterventionViewQuery vQuery = this.getViewQuery();

      vQuery.map(ControlInterventionView.CONCRETEID, query.getId());
      vQuery.map(ControlInterventionView.GEOENTITY, query.getGeoEntity());
      vQuery.map(ControlInterventionView.STARTDATE, query.getStartDate());
      vQuery.map(ControlInterventionView.ENDDATE, query.getEndDate());
      vQuery.map(ControlInterventionView.COMMENTS, query.getComments());
      vQuery.map(ControlInterventionView.INDIVIDULPREMISEUNIVERSAL, query.getIndividulPremiseUniversal());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {

    }

  }

  private static class SearchQueryBuilder extends DefaultControlInterventionViewBuilder implements Reloadable
  {
    private ControlInterventionView view;

    private Condition               condition;

    private AllPathsQuery           pathsQuery;

    protected SearchQueryBuilder(QueryFactory queryFactory, ControlInterventionView view)
    {
      super(queryFactory);

      this.view = view;
      this.condition = null;
      this.pathsQuery = new AllPathsQuery(queryFactory);
    }

    @Override
    protected void buildWhereClause()
    {
      super.buildWhereClause();

      ControlInterventionViewQuery vQuery = this.getViewQuery();

      if (view.getGeoEntity() != null)
      {
        this.pathsQuery.WHERE(this.pathsQuery.getParentGeoEntity().EQ(view.getGeoEntity()));

        this.addCondition(vQuery.getGeoEntity().EQ(this.pathsQuery.getChildGeoEntity()));
      }

      if (view.getStartDate() != null)
      {
        this.addCondition(vQuery.getStartDate().GE(view.getStartDate()));
      }

      if (view.getEndDate() != null)
      {
        this.addCondition(vQuery.getEndDate().LE(view.getEndDate()));
      }

      if (condition != null)
      {
        vQuery.WHERE(condition);
      }
    }

    private void addCondition(Condition newCondition)
    {
      if (condition == null)
      {
        condition = newCondition;
      }
      else
      {
        condition = AND.get(condition, newCondition);
      }
    }
  }

  public static ControlInterventionViewQuery searchCollections()
  {
    ControlInterventionViewQuery query = new ControlInterventionViewQuery(new QueryFactory());
    query.restrictRows(15, 1);

    return query;
  }

  public static ControlInterventionViewQuery searchCollections(ControlInterventionView collection)
  {
    QueryFactory factory = new QueryFactory();
    SearchQueryBuilder builder = new SearchQueryBuilder(factory, collection);

    ControlInterventionViewQuery query = new ControlInterventionViewQuery(factory, builder);
    query.restrictRows(15, 1);

    return query;
  }
}
