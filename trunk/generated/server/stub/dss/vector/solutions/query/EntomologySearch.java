package dss.vector.solutions.query;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;

public class EntomologySearch extends EntomologySearchBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240879247627L;

  public EntomologySearch()
  {
    super();
  }

  /**
   * Saves a new EntomologySearch and adds it to the current user.
   *
   * @param view
   * @return
   */
  @Transaction
  public static EntomologySearch saveSearch(SavedSearchView view)
  {
    EntomologySearch search = new EntomologySearch();
    search.populate(view);

    return search;
  }

  public static SavedSearchViewQuery getEntomologyQueries()
  {
    QueryFactory f = new QueryFactory();
    EntomologySearchQuery searchQ = new EntomologySearchQuery(f);
    SavedSearchViewQuery q = new SavedSearchViewQuery(f, searchQ, false);
    return q;
  }

}