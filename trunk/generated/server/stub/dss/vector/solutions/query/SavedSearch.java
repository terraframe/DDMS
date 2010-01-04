package dss.vector.solutions.query;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.GeneratedViewQuery;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.Selectable;
import com.terraframe.mojo.query.ValueQuery;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.system.metadata.MdAttribute;
import com.terraframe.mojo.vault.VaultFileDAO;
import com.terraframe.mojo.vault.VaultFileDAOIF;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.report.UndefinedTemplateException;

public class SavedSearch extends SavedSearchBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241158161320L;

  public SavedSearch()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    // Ask Naifeh if this is a valid key
//    return this.getQueryType() + "-" + this.getQueryName();
    return this.getId();
  }
  
  /**
   * Apply method that also checks if this SavedSearch object is mappable
   * or not.
   */
  public void apply()
  {
    try
    {
      JSONObject config = new JSONObject(this.getConfig());
      JSONObject selectedUniversals = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      
      // Any search is mappable if it has at least one universal 
      // selected for any GeoEntity attribute reference.
      boolean mappable = false;
      Iterator<String> attrs = selectedUniversals.keys();
      while(attrs.hasNext())
      {
        if(selectedUniversals.getJSONArray(attrs.next()).length() > 0)
        {
          mappable = true;
          break;
        }
      }

      this.setMappable(mappable);
      
    }
    catch (JSONException e)
    {
      String error = "An error occured while marking a query as mappable.";
      throw new ProgrammingErrorException(error, e);
    }
    
    super.apply();
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

    // restrict by type and search name
    searchQuery.WHERE(searchQuery.getQueryName().EQ(searchName));
//    searchQuery.WHERE(searchQuery.getQueryType().EQ(this.getQueryType()));
    searchQuery.WHERE(searchQuery.getQueryType().NEi(DefaultSavedSearch.DEFAULT));

    if (searchQuery.getCount() > 0)
    {
      String error = "A name must be unique per query screen.";
      UniqueSearchNameException ex = new UniqueSearchNameException(error);
      throw ex;
    }
  }
  
  /**
   * Fetches all SavedSearches that can be mapped.
   * 
   * @return
   */
  public static SavedSearchViewQuery getMappableSearches()
  {
    QueryFactory f = new QueryFactory();
    MappableSearchBuilder builder = new MappableSearchBuilder(f);
    SavedSearchViewQuery q = new SavedSearchViewQuery(f, builder);
    
    return q;
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

    if(includeXML)
    {
      view.setQueryXml(this.getQueryXml());
    }

    if(includeConfig)
    {
      JSONObject config;

      try
      {
        // Dereference all MO Terms in the configuration
        config = new JSONObject(this.getConfig());
        JSONObject terms = config.getJSONObject("terms");

        Map<String, List<JSONObject>> termIds = new HashMap<String, List<JSONObject>>();
        List<String> ids = new LinkedList<String>();

        Iterator<?> termKeys = terms.keys();
        while(termKeys.hasNext())
        {
          String termKey = (String) termKeys.next();

          JSONObject termIdsObj = terms.getJSONObject(termKey);

          Iterator<?> idKeys = termIdsObj.keys();
          while(idKeys.hasNext())
          {
            String id = (String) idKeys.next();
            ids.add(id);

            if(!termIds.containsKey(id))
            {
              termIds.put(id, new LinkedList<JSONObject>());
            }

//            JSONObject termIdDisplay = termIdsObj.getJSONObject(id);
            termIds.get(id).add(termIdsObj);
          }
        }

        if(ids.size() > 0)
        {
          QueryFactory f = new QueryFactory();
          TermQuery t = new TermQuery(f);
          ValueQuery v = new ValueQuery(f);

          v.SELECT(t.getId("tId"), t.getName("termName"), t.getTermId("termId"));
          v.WHERE(t.getId("tId").IN(ids.toArray(new String[ids.size()])));
          OIterator<ValueObject> iter = v.getIterator();

          try
          {
            while(iter.hasNext())
            {
              ValueObject o = iter.next();
              String id = o.getValue("tId");
              String display = o.getValue("termName") + " ("+o.getValue("termId")+")";

              for(JSONObject termIdDisplay : termIds.get(id))
              {
                termIdDisplay.put(id, display);
              }
            }
          }
          finally
          {
            iter.close();
          }
        }
      }
      catch(JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }

      view.setConfig(config.toString());
    }

    return view;
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
    SavedSearch search = new DefaultSavedSearch();

    search.create(view, true);

    return search.getAsView(false, false);
  }

  private static class MappableSearchBuilder extends com.terraframe.mojo.query.ViewQueryBuilder implements Reloadable
  {
    private SavedSearchQuery searchQuery;
    
    private MappableSearchBuilder(QueryFactory f)
    {
      super(f);
      
      this.searchQuery = new SavedSearchQuery(f);
    }
    
    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery viewQuery = this.getViewQuery();
      
      viewQuery.map(SavedSearchView.QUERYNAME, searchQuery.getQueryName());
      viewQuery.map(SavedSearchView.SAVEDQUERYID, searchQuery.getId());
      viewQuery.map(SavedSearchView.QUERYTYPE, searchQuery.getQueryType()); 
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery viewQuery = this.getViewQuery();
      
      viewQuery.WHERE(this.searchQuery.getQueryType().NE(DefaultSavedSearch.DEFAULT));
      viewQuery.AND(this.searchQuery.getMappable().EQ(true));
      viewQuery.ORDER_BY_ASC(searchQuery.getQueryName());
    }
    
  }
  
  /**
   * Returns any available thematic variables (Selectables) available
   * on this query this SavedSearch encapsulates.
   */
  @Override
  public ThematicVariable[] getThematicVariables()
  {
    String xml = this.getQueryXml();
    String config = this.getConfig();
    String queryType = this.getQueryType();
    
    // QueryBuilder.getValueQuery() takes in the query class for use with reflection.
    // TODO pass in queryType and have getValueQuery deref the class
    String queryClass = QueryConstants.getQueryClass(queryType);
    ValueQuery valueQuery = QueryBuilder.getValueQuery(queryClass, xml, config, null);
    
    Selectable[] selectables = valueQuery.getSelectables();
    ThematicVariable[] thematicVars = new ThematicVariable[selectables.length];
    
    for(int i=0; i<selectables.length; i++)
    {
      Selectable sel = selectables[i];
      
      ThematicVariable var = new ThematicVariable();
      var.setAttributeName(sel.getQualifiedName());
      var.setDisplayLabel(sel.getUserDefinedDisplayLabel());
      var.setUserAlias(sel.getUserDefinedAlias());
      
      thematicVars[i] = var;
    }
    
    return thematicVars;
  }
  
  /**
   * Returns all available GeoHierarchies provided by this SavedSearch
   */
  @Override
  public AttributeGeoHierarchy[] getAttributeGeoHierarchies()
  {
    String configStr = this.getConfig();
    
    try
    {
      JSONObject config = new JSONObject(configStr);
      JSONObject selected = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
      JSONArray names = selected.names();
      List<AttributeGeoHierarchy> attrGeos = new LinkedList<AttributeGeoHierarchy>();
      for(int i=0; i<names.length(); i++)
      {
        String qualifiedAttribute = names.getString(i);
        MdAttribute mdAttribute = MdAttribute.getByKey(qualifiedAttribute);
        JSONArray universals = selected.getJSONArray(qualifiedAttribute);
        
        for(int j=0; j<universals.length(); j++)
        {
          GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(universals.getString(j));
          
          AttributeGeoHierarchy attrGeo = new AttributeGeoHierarchy();
          attrGeo.setAttributeDisplayLabel(mdAttribute.getDisplayLabel().getValue());
          attrGeo.setMdAttributeId(mdAttribute.getId());
          attrGeo.setGeoHierarchyDisplayLabel(geoH.getDisplayLabel());
          attrGeo.setGeoHierarchyId(geoH.getId());
          
          attrGeos.add(attrGeo);
        }
      }
      
      return attrGeos.toArray(new AttributeGeoHierarchy[attrGeos.size()]);
    }
    catch (JSONException e)
    {
      // We should never hit this since MDSS controls the JSON configuration.
      throw new ProgrammingErrorException(e);
    }
  }

  
  @Override
  public String toString()
  {
    return this.getQueryName();
  }
}
