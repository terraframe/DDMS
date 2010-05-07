package dss.vector.solutions.entomology;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.AllPathsQuery;

/**
 * 
 * @author Autogenerated by RunwaySDK
 */
public class PupalCollectionViewQuery extends dss.vector.solutions.entomology.PupalCollectionViewQueryBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1901879336;

  public PupalCollectionViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultPupalCollectionViewBuilder(queryFactory));
  }

  public PupalCollectionViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  static class DefaultPupalCollectionViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    private PupalCollectionQuery collectionQuery;

    private PupalPremiseQuery    premiseQuery;

    public DefaultPupalCollectionViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
    {
      super(queryFactory);

      this.collectionQuery = new PupalCollectionQuery(queryFactory);
      this.premiseQuery = new PupalPremiseQuery(queryFactory);
    }

    protected PupalCollectionViewQuery getViewQuery()
    {
      return (PupalCollectionViewQuery) super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      PupalCollectionViewQuery vQuery = this.getViewQuery();

      vQuery.map(PupalCollectionView.CONCRETEID, collectionQuery.getId());
      vQuery.map(PupalCollectionView.GEOENTITY, collectionQuery.getGeoEntity());
      vQuery.map(PupalCollectionView.STARTDATE, collectionQuery.getStartDate());
      vQuery.map(PupalCollectionView.ENDDATE, collectionQuery.getEndDate());
      vQuery.map(PupalCollectionView.COLLECTIONID, collectionQuery.getCollectionId());
      vQuery.map(PupalCollectionView.NOTES, collectionQuery.getNotes());
      vQuery.map(PupalCollectionView.PREMISEID, premiseQuery.getId());
      vQuery.map(PupalCollectionView.PREMISETYPE, premiseQuery.getPremiseType());
      vQuery.map(PupalCollectionView.NUMBEREXAMINED, premiseQuery.getNumberExamined());
      vQuery.map(PupalCollectionView.PREMISESIZE, premiseQuery.getPremiseSize());
      vQuery.map(PupalCollectionView.NUMBERINHABITANTS, premiseQuery.getNumberInhabitants());
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      PupalCollectionViewQuery vQuery = this.getViewQuery();
      vQuery.WHERE(collectionQuery.getId().EQ(premiseQuery.getCollection().getId()));
    }
  }

  private static class SearchQueryBuilder extends DefaultPupalCollectionViewBuilder implements Reloadable
  {
    private PupalCollectionView view;

    private Condition           condition;

    private AllPathsQuery       pathsQuery;

    protected SearchQueryBuilder(QueryFactory queryFactory, PupalCollectionView view)
    {
      super(queryFactory);

      this.view = view;
      this.condition = null;
      this.pathsQuery = new AllPathsQuery(queryFactory);
    }

    @Override
    protected void buildWhereClause()
    {
      PupalCollectionViewQuery vQuery = this.getViewQuery();

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

      if (view.getCollectionId() != null && !view.getCollectionId().equals(""))
      {
        this.addCondition(vQuery.getCollectionId().EQ(view.getCollectionId()));
      }

      if (view.getPremiseType() != null)
      {
        this.addCondition(vQuery.getPremiseType().EQ(view.getPremiseType()));
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

  public static PupalCollectionViewQuery searchCollections()
  {
    PupalCollectionViewQuery query = new PupalCollectionViewQuery(new QueryFactory());
    query.restrictRows(15, 1);

    return query;
  }

  public static PupalCollectionViewQuery searchCollections(PupalCollectionView collection)
  {
    QueryFactory factory = new QueryFactory();
    SearchQueryBuilder builder = new SearchQueryBuilder(factory, collection);

    PupalCollectionViewQuery query = new PupalCollectionViewQuery(factory, builder);
    query.restrictRows(15, 1);

    return query;
  }

}
