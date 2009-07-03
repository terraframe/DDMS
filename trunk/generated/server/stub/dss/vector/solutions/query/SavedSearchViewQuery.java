package dss.vector.solutions.query;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.session.Session;

/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SavedSearchViewQuery extends dss.vector.solutions.query.SavedSearchViewQueryBase  implements com.terraframe.mojo.generation.loader.Reloadable
{
private static final long serialVersionUID = 1240879208564L;

  private SavedSearchQuery searchQuery;
  private PersistsSearchQuery persistQuery;
  private boolean includeXML;
  private boolean includeConfig;
  private String queryType;

  public SavedSearchViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultSavedSearchViewBuilder(queryFactory));
  }

  public SavedSearchViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, String queryType, boolean includeXML, boolean includeConfig)
  {
    super(queryFactory);
    this.searchQuery = new SavedSearchQuery(queryFactory);
    this.persistQuery = new PersistsSearchQuery(queryFactory);
    this.includeXML = includeXML;
    this.includeConfig = includeConfig;
    this.queryType = queryType;
    this.buildQuery(new DefaultSavedSearchViewBuilder(queryFactory));
  }

  public SavedSearchViewQuery(com.terraframe.mojo.query.QueryFactory queryFactory, com.terraframe.mojo.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultSavedSearchViewBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements com.terraframe.mojo.generation.loader.Reloadable
  {
    public DefaultSavedSearchViewBuilder(com.terraframe.mojo.query.QueryFactory queryFactory)
    {
      super(queryFactory);
    }

    protected SavedSearchViewQuery getViewQuery()
    {
      return (SavedSearchViewQuery)super.getViewQuery();
    }

    /**
     * build the select clause
     */
    protected void buildSelectClause()
    {
      SavedSearchViewQuery viewQuery = this.getViewQuery();

      viewQuery.map(SavedSearchView.QUERYNAME, searchQuery.getQueryName());
      viewQuery.map(SavedSearchView.SAVEDQUERYID, searchQuery.getId());
      viewQuery.map(SavedSearchView.QUERYTYPE, searchQuery.getQueryType());

      if(includeXML)
      {
        viewQuery.map(SavedSearchView.QUERYXML, searchQuery.getQueryXml());
      }
      
      if(includeConfig)
      {
        viewQuery.map(SavedSearchView.CONFIG, searchQuery.getConfig());
      }

      // FIXME, is this ever needed when simply loading views to display available queries?
//      CONCAT c = F.CONCAT(searchQuery.getThematicLayer().getGeoHierarchy().getGeoEntityClass().getPackageName(),
//          F.CONCAT(".", searchQuery.getThematicLayer().getGeoHierarchy().getGeoEntityClass().getTypeName()));
//      viewQuery.map(SavedSearchView.THEMATICLAYER, c);
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      SavedSearchViewQuery viewQuery = this.getViewQuery();

      // limit search instances to the current user.
      UserDAOIF user = Session.getCurrentSession().getUser();
      viewQuery.WHERE(persistQuery.parentId().EQ(user.getId()));
      viewQuery.WHERE(searchQuery.persistedBy(persistQuery));
      
      viewQuery.WHERE(searchQuery.getQueryType().EQ(queryType));

      viewQuery.ORDER_BY_ASC(searchQuery.getQueryName());
    }

  }
}