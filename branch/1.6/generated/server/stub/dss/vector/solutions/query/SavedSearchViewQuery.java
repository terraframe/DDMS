package dss.vector.solutions.query;

import com.runwaysdk.query.Condition;

import dss.vector.solutions.general.Disease;


/**
 *
 * @author Autogenerated by TerraFrame
 */
public class SavedSearchViewQuery extends dss.vector.solutions.query.SavedSearchViewQueryBase  implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1240879208564L;

  private SavedSearchQuery searchQuery;
  private boolean includeXML;
  private boolean includeConfig;
  private String queryType;

  public SavedSearchViewQuery(com.runwaysdk.query.QueryFactory queryFactory)
  {
    super(queryFactory);
    this.buildQuery(new DefaultSavedSearchViewBuilder(queryFactory));
  }

  public SavedSearchViewQuery(com.runwaysdk.query.QueryFactory queryFactory, String queryType, boolean includeXML, boolean includeConfig)
  {
    super(queryFactory);
    this.searchQuery = new SavedSearchQuery(queryFactory);
    this.includeXML = includeXML;
    this.includeConfig = includeConfig;
    this.queryType = queryType;
    this.buildQuery(new DefaultSavedSearchViewBuilder(queryFactory));
  }

  public SavedSearchViewQuery(com.runwaysdk.query.QueryFactory queryFactory, com.runwaysdk.query.ViewQueryBuilder viewQueryBuilder)
  {
    super(queryFactory, viewQueryBuilder);
  }

  class DefaultSavedSearchViewBuilder extends com.runwaysdk.query.ViewQueryBuilder implements com.runwaysdk.generation.loader.Reloadable
  {
    public DefaultSavedSearchViewBuilder(com.runwaysdk.query.QueryFactory queryFactory)
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
    }

    /**
     * Implement only if additional join criteria is required.
     */
    protected void buildWhereClause()
    {
      SavedSearchViewQuery viewQuery = this.getViewQuery();
      
      /*
       * Fix for #2682 - Changing a form name could lose its saved queries.
       * 
       * The convention for query type should be [domain_class_being_queried]:[java_class_to_process_the query].
       * The reason for the convention is that the domain class is not always the class that processes the query.
       * All form generator query builders will be [domain_class]:[MDSSInfo.TYPE_QB], but to provide backwards
       * compatibility where the query type was erroneously [domain_class]:[form_name], the matching criteria for
       * the query type will differ (to avoid writing a migration script). All new queries will follow the proper
       * convention.
       */
      
      Condition cond;
      if(QueryConstants.isFormGeneratorQuery(queryType))
      {
        String domainClass = QueryConstants.getFormGeneratorClass(queryType);
        cond = searchQuery.getQueryType().LIKE(domainClass+QueryConstants.NAMESPACE_DELIM+"*");
      }
      else
      {
        cond = searchQuery.getQueryType().EQ(queryType);
      }
      
      viewQuery.WHERE(cond);
      viewQuery.AND(searchQuery.getType().EQ(SavedSearch.CLASS));
      viewQuery.AND(searchQuery.getDisease().EQ(Disease.getCurrent()));
      viewQuery.ORDER_BY_ASC(searchQuery.getQueryName());
    }

  }
}