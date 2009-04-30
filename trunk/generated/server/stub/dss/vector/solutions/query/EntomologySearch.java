package dss.vector.solutions.query;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.geo.GeoHierarchy;

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
    String name = view.getQueryName();
    String xml = view.getQueryXml();
    String thematicLayerClass = view.getThematicLayer();

    GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(thematicLayerClass);

    EntomologySearch search = new EntomologySearch();
    search.setQueryName(name);
    search.setQueryXml(xml);
    search.setThematicLayer(geoH);
    search.apply();

    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    mdssUser.addPersistedQueries(search).apply();

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