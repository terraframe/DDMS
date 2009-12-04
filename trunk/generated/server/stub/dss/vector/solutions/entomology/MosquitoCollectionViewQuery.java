package dss.vector.solutions.entomology;

import java.util.List;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.CONCAT;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ViewQueryBuilder;

@com.terraframe.mojo.business.ClassSignature(hash = 1462504487)
/**
 *
 * @author Autogenerated by TerraFrame
 */
public class MosquitoCollectionViewQuery extends dss.vector.solutions.entomology.MosquitoCollectionViewQueryBase implements Reloadable
{
  private static final long serialVersionUID = 1462504487;

  public MosquitoCollectionViewQuery(QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultMosquitoCollectionViewBuilder(queryFactory));
  }

  public MosquitoCollectionViewQuery(QueryFactory queryFactory, ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  static class DefaultMosquitoCollectionViewBuilder extends ViewQueryBuilder implements Reloadable
  {
    private MosquitoCollectionQuery query;

//    private ValueQuery              labelQuery;

    public DefaultMosquitoCollectionViewBuilder(QueryFactory queryFactory)
    {
      super(queryFactory);

      query = new MosquitoCollectionQuery(queryFactory);
//      labelQuery = new ValueQuery(queryFactory);
    }

    protected MosquitoCollectionViewQuery getViewQuery()
    {
      return (MosquitoCollectionViewQuery) super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      MosquitoCollectionViewQuery vQuery = this.getViewQuery();

//      SelectableSQLCharacter label = labelQuery.aSQLCharacter("displayLabel", "displayLabel");
      
      CONCAT entityLabel = F.CONCAT(query.getGeoEntity().getEntityName(), " (");
      entityLabel = F.CONCAT(entityLabel, query.getGeoEntity().getGeoId());
      entityLabel = F.CONCAT(entityLabel, ")");
      
      vQuery.map(MosquitoCollectionView.CONCRETEID, query.getId());
      vQuery.map(MosquitoCollectionView.GEOENTITY, query.getGeoEntity());
      vQuery.map(MosquitoCollectionView.GEOENTITYLABEL, entityLabel);
      vQuery.map(MosquitoCollectionView.COLLECTIONDATE, query.getCollectionDate());
      vQuery.map(MosquitoCollectionView.COLLECTIONID, query.getCollectionId());
      vQuery.map(MosquitoCollectionView.COLLECTIONMETHOD, query.getCollectionMethod());
      vQuery.map(MosquitoCollectionView.COLLECTIONMETHODLABEL, query.getCollectionMethod().getName());
      vQuery.map(MosquitoCollectionView.LIFESTAGE, query.getLifeStage());
      vQuery.map(MosquitoCollectionView.ABUNDANCE, query.getAbundance());
      
//      vQuery.getComponentQuery().FROM("geo_displayLabel", "geo_displayLabel");

    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
//      MdBusiness mdBusiness = MdBusiness.getMdBusiness(MosquitoCollection.CLASS);
//
//      labelQuery.WHERE(new InnerJoinEq("id", mdBusiness.getTableName(), query.getTableAlias(), "id", "geo_displayLabel", "geo_displayLabel"));
    }

  }

  private static class SearchQueryBuilder extends DefaultMosquitoCollectionViewBuilder implements Reloadable
  {
    private SearchMosquitoCollectionView view;

    private Condition              condition;

    protected SearchQueryBuilder(QueryFactory queryFactory, SearchMosquitoCollectionView view)
    {
      super(queryFactory);

      this.view = view;
      this.condition = null;
    }

    @Override
    protected void buildWhereClause()
    {
      MosquitoCollectionViewQuery vQuery = this.getViewQuery();

      if (view.getAbundance() != null)
      {
        this.addCondition(vQuery.getAbundance().EQ(view.getAbundance()));
      }
      
      if(view.getStartDate() != null)
      {
        this.addCondition(vQuery.getCollectionDate().GE(view.getStartDate()));        
      }
      else if (view.getCollectionDate() != null)
      {
        this.addCondition(vQuery.getCollectionDate().GE(view.getCollectionDate()));
      }
      
      if (view.getEndDate() != null)
      {
        this.addCondition(vQuery.getCollectionDate().LE(view.getEndDate()));
      }
      
      if (view.getCollectionId() != null && !view.getCollectionId().equals(""))
      {
        this.addCondition(vQuery.getCollectionId().EQ(view.getCollectionId()));
      }

      if (view.getCollectionMethod() != null)
      {
        this.addCondition(vQuery.getCollectionMethod().EQ(view.getCollectionMethod()));
      }

      if (view.getGeoEntity() != null)
      {
        this.addCondition(vQuery.getGeoEntity().EQ(view.getGeoEntity()));
      }

      List<LifeStage> list = view.getLifeStage();

      if (list.size() > 0)
      {
        LifeStage[] array = list.toArray(new LifeStage[list.size()]);
        this.addCondition(vQuery.getLifeStage().containsAny(array));
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

  private static class ValueQueryBuilder extends DefaultMosquitoCollectionViewBuilder implements Reloadable
  {
    private String value;

    protected ValueQueryBuilder(QueryFactory queryFactory, String value)
    {
      super(queryFactory);

      this.value = value;
    }

    @Override
    protected void buildWhereClause()
    {
      MosquitoCollectionViewQuery vQuery = this.getViewQuery();

      String search = "%" + this.value + "%";
      search = search.replace(" ", "% ");

      Condition condition = vQuery.getCollectionId().LIKEi(search);
      condition = OR.get(condition, vQuery.getGeoEntity().getEntityName().LIKEi(search));
      condition = OR.get(condition, vQuery.getGeoEntity().getGeoId().LIKEi(search));
      
      vQuery.WHERE(condition);
    }
  }

  public static MosquitoCollectionViewQuery searchCollections()
  {
    MosquitoCollectionViewQuery query = new MosquitoCollectionViewQuery(new QueryFactory());
    query.restrictRows(15, 1);
    
    return query;
  }

  public static MosquitoCollectionViewQuery searchCollections(SearchMosquitoCollectionView collection)
  {
    QueryFactory factory = new QueryFactory();
    SearchQueryBuilder builder = new SearchQueryBuilder(factory, collection);

    MosquitoCollectionViewQuery query = new MosquitoCollectionViewQuery(factory, builder);
    query.restrictRows(15, 1);

    return query;
  }

  public static MosquitoCollectionViewQuery searchCollections(String value)
  {
    QueryFactory factory = new QueryFactory();
    ValueQueryBuilder builder = new ValueQueryBuilder(factory, value);

    MosquitoCollectionViewQuery query = new MosquitoCollectionViewQuery(factory, builder);
    query.restrictRows(15, 1);
    query.ORDER_BY_ASC(query.getCollectionId());

    return query;
  }
}
