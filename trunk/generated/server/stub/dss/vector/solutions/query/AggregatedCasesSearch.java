package dss.vector.solutions.query;

import com.terraframe.mojo.query.QueryFactory;

public class AggregatedCasesSearch extends AggregatedCasesSearchBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241483332896L;

  public AggregatedCasesSearch()
  {
    super();
  }

  /**
   * Saves a search for Aggregated Cases.
   *
   * @param savedQueryView
   * @return
   */
  public static AggregatedCasesSearch saveSearch(dss.vector.solutions.query.SavedSearchView view)
  {
    AggregatedCasesSearch search = new AggregatedCasesSearch();
    search.populate(view);

    return search;
  }



  /**
   * Returns all saved queries for Aggregated cases for this user.
   * @return
   */
  public static SavedSearchViewQuery getAggregatedCasesQueries()
  {
    QueryFactory f = new QueryFactory();
    AggregatedCasesSearchQuery searchQ = new AggregatedCasesSearchQuery(f);
    SavedSearchViewQuery q = new SavedSearchViewQuery(f, searchQ, false);
    return q;
  }

}
