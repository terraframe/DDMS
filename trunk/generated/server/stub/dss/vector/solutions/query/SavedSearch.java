package dss.vector.solutions.query;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.dataaccess.database.Database;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.vault.VaultFileDAO;
import com.terraframe.mojo.vault.VaultFileDAOIF;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.report.UndefinedTemplateException;

public class SavedSearch extends SavedSearchBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158161320L;

  public SavedSearch()
  {
    super();
  }

  /**
   * Checks that a search name is unique for a user on a given SavedSearch
   * subclass.
   *
   * @param searchName
   * @param user
   */
  private void checkUniqueness(String searchName, MDSSUser user)
  {
    // make sure search name is unique for this user for the given query.
    QueryFactory f = new QueryFactory();
    SavedSearchQuery searchQuery = new SavedSearchQuery(f);
//    PersistsSearchQuery persistsQuery = new PersistsSearchQuery(f);

    // restrict by user
//    persistsQuery.WHERE(persistsQuery.parentId().EQ(user.getId()));
//    persistsQuery.WHERE(persistsQuery.hasChild(searchQuery));

    // restrict by type and search name
    searchQuery.WHERE(searchQuery.getQueryName().EQ(searchName));
    searchQuery.WHERE(searchQuery.getQueryType().EQ(this.getQueryType()));
    searchQuery.WHERE(searchQuery.getQueryType().NEi(DefaultSavedSearch.DEFAULT));
    
    if (searchQuery.getCount() > 0)
    {
      String error = "A name must be unique per query screen.";
      UniqueSearchNameException ex = new UniqueSearchNameException(error);
      throw ex;
    }
  }
  
  /**
   * Creates a new SavedSearch object.
   * 
   * @param view
   * @return
   */
  @Transaction
  public static SavedSearchView saveSearch(SavedSearchView view)
  {
    SavedSearch search = new SavedSearch();
    search.create(view, false);
    
    return search.getAsView(false, false);
  }
  
  @Transaction
  public static SavedSearchView updateSearch(SavedSearchView view)
  {
    if(view == null || view.getSavedQueryId() == null || view.getSavedQueryId().trim().length() == 0)
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }
    
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());
    SavedSearch defaultSearch = mdssUser.getDefaultSearch();
    if(defaultSearch != null && defaultSearch.getId().equals(view.getSavedQueryId()))
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }
    
    SavedSearch search = SavedSearch.get(view.getSavedQueryId());
    search.update(view);
    
    return search.getAsView(false, false);
  }
  
  public static SavedSearchViewQuery getSearchesForType(String type)
  {
    QueryFactory f = new QueryFactory();
    SavedSearchViewQuery q = new SavedSearchViewQuery(f, type, false, false);
    return q;
  }

  /**
   * Updates this SavedSearch with the given view.
   *
   * @param view
   */
  protected void update(SavedSearchView view)
  {
    String xml = view.getQueryXml();
    String config = view.getConfig();
    
    this.appLock();
    
    this.setQueryXml(xml);
    this.setConfig(config);
    
    this.apply();
  }

  /**
   * Creates and applies this SavedSearch object with the given information in
   * the SavedSearchView.
   *
   * @param view
   * @param savedQuery
   */
  @Transaction
  protected void create(SavedSearchView view, boolean asDefault)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    if(asDefault)
    {
      // Always replace the old default search.
      SavedSearch search = mdssUser.getDefaultSearch();
      
      if(search != null)
      {
        search.delete();
      }
    }
    
    String name = view.getQueryName();


    String xml = view.getQueryXml();


    this.setQueryName(name);
    this.setQueryXml(xml);
    this.setQueryType(view.getQueryType());
    this.setConfig(view.getConfig());
    
    checkUniqueness(name, mdssUser);

    // Create the thematic layer if it does not exist
    String thematicLayerType = view.getThematicLayer();
    ThematicLayer thematicLayer = ThematicLayer.newInstance(thematicLayerType);
    this.setThematicLayer(thematicLayer);

    this.apply();

    if(asDefault)
    {
      mdssUser.appLock();
      mdssUser.setDefaultSearch(this);
      mdssUser.apply();
    }
    else
    {
      mdssUser.addPersistedQueries(this).apply();
    }
  }
  
  public SavedSearchView getAsView(Boolean includeXML, Boolean includeConfig)
  {
    SavedSearchView view = new SavedSearchView();

    view.setQueryName(this.getQueryName());
    view.setSavedQueryId(this.getId());

    ThematicLayer layer = this.getThematicLayer();
    if(layer != null)
    {
      String layerId = layer.getId();
      view.setThematicLayerId(layerId);
      
      String thematicLayerId = view.getThematicLayerId();
      System.out.println(thematicLayerId);
    }

    if(includeXML)
    {
      view.setQueryXml(this.getQueryXml());
    }
    
    if(includeConfig)
    {
      view.setConfig(this.getConfig());
    }

    return view;
  }
  
  public LayerView[] getAllLayers()
  {
    List<LayerView> views = new LinkedList<LayerView>();
    
    OIterator<? extends UniversalLayer> iter = this.getAllDefinesLayers();
    
    try
    {
      while(iter.hasNext())
      {
        views.add(iter.next().getAsView());
      }
    }
    finally
    {
      iter.close();
    }
    
    
    ThematicLayer thematicLayer = this.getThematicLayer();
    if(thematicLayer != null)
    {
      views.add(thematicLayer.getAsView());
    }
    
    return views.toArray(new LayerView[views.size()]);
  }
  
  public InputStream getTemplateStream()
  {
    String template = this.getTemplateFile();
    
    if(template == null || template.equals(""))
    {
      String msg = "A report template has not been defined for this query";
      
      UndefinedTemplateException e = new UndefinedTemplateException(msg);
      e.apply();
      
      throw e;
    }
    
    VaultFileDAOIF file = VaultFileDAO.get(template);
    
    return file.getFile();
  }
  
  public static SavedSearchView loadSearch(String searchId)
  {
    if(searchId == null || searchId.trim().length() == 0)
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }
    
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());
    SavedSearch search = mdssUser.getDefaultSearch();
    if(search != null && search.getId().equals(searchId))
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }
    
    return getAsView(searchId, true, true);
  }
  
  @Transaction
  public static SavedSearchView loadDefaultSearch(SavedSearchView view)
  {
    // This is an entry point common to all query screens, so we take this opportunity to clear out old views
    cleanOldViews();
    
    SavedSearch search = new DefaultSavedSearch();
    
    search.create(view, true);
    
    return search.getAsView(false, false);
  }

  /**
   * Cleans up all views older than an hour.
   */
  private static void cleanOldViews()
  {
    long anHourAgo = System.currentTimeMillis() - (60 * 60 * 1000);
    List<String> viewsToDelete = new LinkedList<String>();
    for (String viewName : Database.getViewsByPrefix(ThematicLayer.GEO_VIEW_PREFIX))
    {
      String next = viewName;
      try
      {
        long parseLong = Long.parseLong(next.substring(ThematicLayer.GEO_VIEW_PREFIX.length()));
        if (parseLong < anHourAgo)
        {
          // This view is old.  Add it to the deletion list 
          viewsToDelete.add(viewName);
        }
      }
      catch (NumberFormatException e)
      {
        // This can happen if there's a view that matches the prefix but doesn't have the timestamp.  Just ignore it.
      }
    }
    // Perform the actual drop commands
    Database.dropViews(viewsToDelete);
  }
}
