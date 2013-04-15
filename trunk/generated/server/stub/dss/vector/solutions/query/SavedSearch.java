package dss.vector.solutions.query;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.rbac.UserDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.GeneratedViewQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.query.ViewQueryBuilder;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;
import com.runwaysdk.vault.VaultFileDAO;
import com.runwaysdk.vault.VaultFileDAOIF;

import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.UserSettings;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.report.UndefinedTemplateException;

public class SavedSearch extends SavedSearchBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1241158161320L;

  /**
   * The prefix for the database view names that represent saved searches
   * (queries).
   */
  public static final String VIEW_PREFIX      = "q_";

  private static Log         log              = LogFactory.getLog(SavedSearch.class);

  public SavedSearch()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    // Ask Naifeh if this is a valid key
    // return this.getQueryType() + "-" + this.getQueryName();
    return this.getId();
  }

  @Override
  public void delete()
  {
    super.delete();

    this.deleteDatabaseViewIfExists();
  }

  /**
   * Apply method that also checks if this SavedSearch object is mappable or
   * not.
   */
  @SuppressWarnings("unchecked")
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
      while (attrs.hasNext())
      {
        if (selectedUniversals.getJSONArray(attrs.next()).length() > 0)
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

    this.createOrReplaceDatabaseView();
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

    // a search name must be unique across all types for mapping purposes.
    // We exclude the DefaultSavedSearch subclass.
    searchQuery.WHERE(searchQuery.getQueryName().EQ(searchName));
    searchQuery.AND(searchQuery.getType().EQ(SavedSearch.CLASS));
    searchQuery.AND(searchQuery.getDisease().EQ(Disease.getCurrent()));

    if (searchQuery.getCount() > 0)
    {
      String error = "A name must be unique per query screen.";
      UniqueSearchNameException ex = new UniqueSearchNameException(error);
      throw ex;
    }
  }

  /**
   * Generates the database view name for this SavedSearch, which follows a
   * simple naming convention:
   * 
   * VIEW_PREFIX + query name [sanitized] + _ + disease
   * 
   * For example, a complete view name for a query called "my query" would be:
   * 
   * Q_my_query_malaria
   * 
   * There is no need to persist this as an attribute because it can be
   * predictably generated as the query name is immutable.
   * 
   * @return
   */
  @AbortIfProblem
  private String generateViewName()
  {
    if (this instanceof DefaultSavedSearch)
    {
      throw new ProgrammingErrorException("Cannot generate a database view for a DefaultSavedSearch.");
    }

    String temp = this.getQueryName();
    
    // Use the disease key because it will not change unlike the display label, which
    // the user can modify, thus invalidating a view name. We are treading readability for
    // consistency here, but it doesn't matter because the user will most likely copy and paste
    // the view name. It's not meant to be one-hundred percent user friendly.
    String disease = this.getDisease().getKey();

    // now convert all characters to a valid string using the same mechanism
    // that converts universal and form labels into a database table/view
    // identifier.
    temp = GeoHierarchy.getSystemName(temp, "_"+disease, false);

    // views can have 63 characters in the name, so given that the prefix,
    // delimiters,
    // and disease suffix will take about 15-18 characters, truncate the query
    // name to
    // 45 characters (which is plenty descriptive).
    if (temp.length() > 45)
    {
      temp = temp.substring(0, 45);
    }

    String viewName = VIEW_PREFIX + temp + "_" + disease;

    // Postgres creates tables/views in lowercase, so enforce that convention
    // here as well so we don't get into trouble with mixed casing.
    viewName = viewName.toLowerCase();
    return viewName;
  }
  
  /**
   * Removes all database views for queries.
   */
  public static void cleanupDatabaseViews()
  {
    QueryFactory f = new QueryFactory();
    SavedSearchQuery q = new SavedSearchQuery(f);
    
    OIterator<? extends SavedSearch> iter = q.getIterator();
    try
    {
      while(iter.hasNext())
      {
        SavedSearch search = iter.next();
        try
        {
          search.deleteDatabaseViewIfExists(); 
        }
        catch(Throwable t)
        {
          // continue if there's an error, which can happen under normal circumstances
          log.error("Could not delete the database view for SavedSearch ["+search+"].", t);
        }
      }
    }
    finally
    {
      iter.close();
    }
  }
  
  /**
   * Creates database views for queries.
   */
  public static void createDatabaseViews()
  {
    QueryFactory f = new QueryFactory();
    SavedSearchQuery q = new SavedSearchQuery(f);
    
    OIterator<? extends SavedSearch> iter = q.getIterator();
    try
    {
      while(iter.hasNext())
      {
        SavedSearch search = iter.next();
        try
        {
          search.createOrReplaceDatabaseView(); 
        }
        catch(Throwable t)
        {
          // continue if there's an error, which can happen under normal circumstances
          log.error("Could not create the database view for SavedSearch ["+search+"].", t);
        }
      }
    }
    finally
    {
      iter.close();
    }
  }

  /**
   * Returns the database view name for this query if one exists.
   * 
   * @param searchId
   * @return
   */
  @Override
  public String getDatabaseViewName()
  {
    if (this.getQueryType().equals(GeoHierarchy.getQueryType()) || this instanceof DefaultSavedSearch)
    {
      throw new NoDBViewForDefaultQueryException();
    }

    String viewName = this.generateViewName();
    if (databaseViewExists(viewName))
    {
      return viewName;
    }
    else
    {
      String msg = "The query [" + this.getQueryName() + "] does not have a database view.";
      throw new DatabaseViewMissingException(msg);
    }
  }

  /**
   * Creates the database view for this query or updates (replaces) it if one
   * already exists. If the query is invalid because it has no columns then the
   * database view is deleted, if one exists.
   */
  @AbortIfProblem
  private void createOrReplaceDatabaseView()
  {
    if (this.getQueryType().equals(GeoHierarchy.getQueryType()) || this instanceof DefaultSavedSearch)
    {
      return;
    }

    // remove the existing database view
    this.deleteDatabaseViewIfExists();

    String queryType = this.getQueryType();
    String xml = this.getQueryXml();
    String config = this.getConfig();

    String queryClass = QueryConstants.getQueryClass(queryType);

    try
    {
      ValueQuery valueQuery = QueryBuilder.getValueQuery(queryClass, xml, config, null);

      // create the database view
      String viewName = this.generateViewName();
      String sql = "(" + valueQuery.getSQL() + ")";
      Database.createView(viewName, sql);
    }
    catch (NoColumnsAddedException e)
    {
      // cannot create a database view because there are no columns.
      // This is not an error in-and-of-itself, but make sure to log
      // it just to be sure.
      log.debug("Could not create a database view for the query [" + this.getQueryName() + "].", e);
    }
  }

  private void deleteDatabaseViewIfExists()
  {
    if (this.getQueryType().equals(GeoHierarchy.getQueryType()) || this instanceof DefaultSavedSearch)
    {
      return;
    }

    List<String> batch = new LinkedList<String>();
    String viewName = this.generateViewName();
    batch.add("DROP VIEW IF EXISTS " + viewName + " CASCADE");
    Database.executeBatch(batch);
  }

  /**
   * Fetches all SavedSearches that can be mapped.
   * 
   * @return
   */
  public static SavedSearchView[] getMappableSearches()
  {
    List<SavedSearchView> views = new LinkedList<SavedSearchView>();

    QueryFactory f = new QueryFactory();
    UniversalSearchBuilder builder2 = new UniversalSearchBuilder(f);
    SavedSearchViewQuery q2 = new SavedSearchViewQuery(f, builder2);
    views.addAll(q2.getIterator().getAll());

    MappableSearchBuilder builder = new MappableSearchBuilder(f);
    SavedSearchViewQuery q = new SavedSearchViewQuery(f, builder);
    views.addAll(q.getIterator().getAll());

    return views.toArray(new SavedSearchView[views.size()]);
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
    if (view == null || view.getSavedQueryId() == null || view.getSavedQueryId().trim().length() == 0)
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }

    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());
    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedSearch defaultSearch = settings.getDefaultSearch();
    if (defaultSearch != null && defaultSearch.getId().equals(view.getSavedQueryId()))
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

    String name = view.getQueryName();

    String xml = view.getQueryXml();

    this.setQueryName(name);
    this.setQueryXml(xml);
    this.setQueryType(view.getQueryType());
    this.setConfig(view.getConfig());
    this.setDisease(Disease.getCurrent());

    checkUniqueness(name, mdssUser);

    this.apply();

    if (asDefault)
    {
      // Always replace the old default search.
      UserSettings settings = UserSettings.createIfNotExists(mdssUser);
      DefaultSavedSearch search = settings.getDefaultSearch();

      if (search != null)
      {
        search.delete();
      }

      settings.appLock();
      settings.setDefaultSearch((DefaultSavedSearch) this);
      settings.apply();
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

    if (includeXML)
    {
      view.setQueryXml(this.getQueryXml());
    }

    if (includeConfig)
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
        while (termKeys.hasNext())
        {
          String termKey = (String) termKeys.next();

          JSONObject termIdsObj = terms.getJSONObject(termKey);

          Iterator<?> idKeys = termIdsObj.keys();
          while (idKeys.hasNext())
          {
            String id = (String) idKeys.next();
            ids.add(id);

            if (!termIds.containsKey(id))
            {
              termIds.put(id, new LinkedList<JSONObject>());
            }

            // JSONObject termIdDisplay = termIdsObj.getJSONObject(id);
            termIds.get(id).add(termIdsObj);
          }
        }

        if (ids.size() > 0)
        {
          QueryFactory f = new QueryFactory();
          TermQuery t = new TermQuery(f);
          ValueQuery v = new ValueQuery(f);

          v.SELECT(t.getId("tId"), t.getName("termName"), t.getTermId("termId"));
          v.WHERE(t.getId("tId").IN(ids.toArray(new String[ids.size()])));
          OIterator<ValueObject> iter = v.getIterator();

          try
          {
            while (iter.hasNext())
            {
              ValueObject o = iter.next();
              String id = o.getValue("tId");
              String display = o.getValue("termName") + " (" + o.getValue("termId") + ")";

              for (JSONObject termIdDisplay : termIds.get(id))
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
      catch (JSONException e)
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

    if (template == null || template.equals(""))
    {
      String msg = "A report template has not been defined for this query";

      UndefinedTemplateException e = new UndefinedTemplateException(msg);
      e.apply();

      throw e;
    }

    VaultFileDAOIF file = VaultFileDAO.get(template);

    return file.getFileStream();
  }

  public static SavedSearchView loadSearch(String searchId)
  {
    if (searchId == null || searchId.trim().length() == 0)
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }

    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());
    UserSettings settings = UserSettings.createIfNotExists(mdssUser);
    DefaultSavedSearch defaultSearch = settings.getDefaultSearch();
    if (defaultSearch != null && defaultSearch.getId().equals(searchId))
    {
      NoSearchSpecifiedException ex = new NoSearchSpecifiedException();
      throw ex;
    }

    // To avoid a migration script, create the database view
    // if one does not exist. This lazy-load approach will
    // also reduce overhead in trying to synchronize the state
    // of the query with its database view representation.
    SavedSearch search = SavedSearch.get(searchId);
    String viewName = search.generateViewName();

    if (!databaseViewExists(viewName))
    {
      search.createOrReplaceDatabaseView();
    }

    return search.getAsView(true, true);
  }

  /**
   * Checks if the given view exists in the database. For some reason
   * Database.tableExists(table) was not working consistently, so this is a
   * different check that does a direct query.
   * 
   * @param viewName
   * @return
   */
  private static boolean databaseViewExists(String viewName)
  {
    return Database.tableExists(viewName);
    
//    ValueQuery v = new ValueQuery(new QueryFactory());
//    v.SELECT(v.aSQLBoolean("constantBool", "true"));
//    v.FROM(viewName, viewName);
//    
//    v.restrictRows(1, 1); // restrict the rows to simplify the query
//
//    try
//    {
//      v.getCount();
//      log.debug("The database view [" + viewName + "] exists.");
//
//      return true;
//    }
//    catch (DatabaseException ex)
//    {
//      log.warn("The database view [" + viewName + "] does not exist.", ex);
//      return false;
//    }
  }

  @Transaction
  public static SavedSearchView loadDefaultSearch(SavedSearchView view)
  {
    SavedSearch search = new DefaultSavedSearch();

    search.create(view, true);

    return search.getAsView(false, false);
  }

  private static class UniversalSearchBuilder extends ViewQueryBuilder implements Reloadable
  {
    private SavedSearchQuery searchQuery;

    private MdBusinessQuery  mdBusiness;

    private UniversalSearchBuilder(QueryFactory f)
    {
      super(f);

      this.searchQuery = new SavedSearchQuery(f);
      this.mdBusiness = new MdBusinessQuery(f);
    }

    @Override
    protected void buildSelectClause()
    {
      GeneratedViewQuery viewQuery = this.getViewQuery();

      viewQuery.map(SavedSearchView.QUERYNAME, mdBusiness.getDisplayLabel().localize());
      viewQuery.map(SavedSearchView.SAVEDQUERYID, searchQuery.getId());
      viewQuery.map(SavedSearchView.QUERYTYPE, searchQuery.getQueryType());
    }

    @Override
    protected void buildWhereClause()
    {
      GeneratedViewQuery viewQuery = this.getViewQuery();

      Condition condition = OR.get(this.searchQuery.getDisease().EQ(Disease.getCurrent()), this.searchQuery.getDisease().EQ((Disease) null));

      viewQuery.WHERE(this.searchQuery.getType().EQ(SavedSearch.CLASS));
      viewQuery.AND(this.searchQuery.getMappable().EQ(true));
      viewQuery.AND(condition);
      viewQuery.AND(this.searchQuery.getQueryType().EQ(GeoHierarchy.getQueryType()));

      viewQuery.AND(this.mdBusiness.getPackageName().EQ(MDSSInfo.GENERATED_GEO_PACKAGE));
      viewQuery.AND(this.mdBusiness.getTypeName().EQ(this.searchQuery.getQueryName()));

      viewQuery.ORDER_BY_ASC(searchQuery.getQueryName());
    }
  }

  private static class MappableSearchBuilder extends com.runwaysdk.query.ViewQueryBuilder implements Reloadable
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

      Condition condition = OR.get(this.searchQuery.getDisease().EQ(Disease.getCurrent()), this.searchQuery.getDisease().EQ((Disease) null));

      viewQuery.WHERE(this.searchQuery.getType().EQ(SavedSearch.CLASS));
      viewQuery.AND(this.searchQuery.getMappable().EQ(true));
      viewQuery.AND(condition);
      viewQuery.AND(this.searchQuery.getQueryType().NE(GeoHierarchy.getQueryType()));
      viewQuery.ORDER_BY_ASC(searchQuery.getQueryName());
    }

  }

  /**
   * Returns any available thematic variables (Selectables) available on this
   * query this SavedSearch encapsulates.
   */
  @Override
  public ThematicVariable[] getThematicVariables()
  {
    if (this.getQueryType().equals(GeoHierarchy.getQueryType()))
    {
      // For the universal queries we can grab the entity name and
      // geo id directly as thematic variables.
      ThematicVariable[] thematicVars = new ThematicVariable[2];

      MdAttribute entityName = MdAttribute.getByKey(GeoEntity.CLASS + "." + GeoEntity.ENTITYLABEL);
      MdAttribute geoId = MdAttribute.getByKey(GeoEntity.CLASS + "." + GeoEntity.GEOID);

      ThematicVariable entityNameVar = new ThematicVariable();
      entityNameVar.setAttributeName(GeoEntity.ENTITYLABEL);
      entityNameVar.setDisplayLabel(entityName.getDisplayLabel().getValue());
      entityNameVar.setUserAlias(GeoEntity.ENTITYLABEL);

      ThematicVariable geoIdVar = new ThematicVariable();
      geoIdVar.setAttributeName(GeoEntity.GEOID);
      geoIdVar.setDisplayLabel(geoId.getDisplayLabel().getValue());
      geoIdVar.setUserAlias(GeoEntity.GEOID);

      thematicVars[0] = entityNameVar;
      thematicVars[1] = geoIdVar;

      return thematicVars;
    }
    else
    {
      String xml = this.getQueryXml();
      String config = this.getConfig();
      String queryType = this.getQueryType();

      // QueryBuilder.getValueQuery() takes in the query class for use with
      // reflection.
      // TODO pass in queryType and have getValueQuery deref the class
      String queryClass = QueryConstants.getQueryClass(queryType);
      ValueQuery valueQuery = QueryBuilder.getValueQuery(queryClass, xml, config, null);

      List<Selectable> selectables = valueQuery.getSelectableRefs();
      ThematicVariable[] thematicVars = new ThematicVariable[selectables.size()];

      int loopCount = 0;
      for (Selectable sel : selectables)
      {
        ThematicVariable var = new ThematicVariable();
        var.setAttributeName(sel.getDbQualifiedName());
        var.setDisplayLabel(sel.getUserDefinedDisplayLabel());
        var.setUserAlias(sel.getUserDefinedAlias());

        thematicVars[loopCount] = var;
        loopCount++;
      }

      return thematicVars;
    }
  }

  /**
   * Returns all available GeoHierarchies provided by this SavedSearch
   */
  @Override
  public AttributeGeoHierarchy[] getAttributeGeoHierarchies()
  {
    List<AttributeGeoHierarchy> attrGeos = new LinkedList<AttributeGeoHierarchy>();
    String configStr = this.getConfig();

    if (this.getQueryType().equals(GeoHierarchy.getQueryType()))
    {
      String universal;
      try
      {
        JSONObject config = new JSONObject(configStr);
        JSONObject selected = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
        universal = (String) selected.keys().next(); // There will always be one
        // key

      }
      catch (JSONException e)
      {
        // We should never hit this since MDSS controls the JSON configuration.
        throw new ProgrammingErrorException(e);
      }

      GeoHierarchy geoH = GeoHierarchy.getGeoHierarchyFromType(universal);

      // To keep things generic, we steal the metadata of the GeoEntity class
      // instead of pointing to a specific GeoEntity reference attribute on a
      // domain class. The display is from the GeoEntity class itself, which
      // is adequate for universal queries. The MdAttribute reference is spoofed
      // and simply points to the MdAttribute that defines GeoEntity.entityName.
      MdBusiness mdBusiness = MdBusiness.getMdBusiness(GeoEntity.CLASS);
      MdAttribute mdAttr = MdAttribute.getByKey(GeoEntity.CLASS + "." + GeoEntity.ENTITYLABEL);

      AttributeGeoHierarchy attrGeo = new AttributeGeoHierarchy();
      attrGeo.setAttributeDisplayLabel(mdBusiness.getDisplayLabel().getValue());
      attrGeo.setMdAttributeId(mdAttr.getId());
      attrGeo.setGeoHierarchyDisplayLabel(geoH.getDisplayLabel());
      attrGeo.setGeoHierarchyId(geoH.getId());

      attrGeos.add(attrGeo);
    }
    else
    {
      try
      {
        JSONObject config = new JSONObject(configStr);
        JSONObject selected = config.getJSONObject(QueryConstants.SELECTED_UNIVERSALS);
        JSONArray names = selected.names();
        for (int i = 0; i < names.length(); i++)
        {
          String qualifiedAttribute = names.getString(i);
          MdAttribute mdAttribute = MdAttribute.getByKey(qualifiedAttribute);
          JSONArray universals = selected.getJSONArray(qualifiedAttribute);

          for (int j = 0; j < universals.length(); j++)
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

      }
      catch (JSONException e)
      {
        // We should never hit this since MDSS controls the JSON configuration.
        throw new ProgrammingErrorException(e);
      }
    }

    return attrGeos.toArray(new AttributeGeoHierarchy[attrGeos.size()]);
  }

  @Override
  public String toString()
  {
    return this.getQueryName();
  }

  public static SavedSearch getSavedSearch(String queryName, String queryType)
  {
    SavedSearchQuery query = new SavedSearchQuery(new QueryFactory());
    query.WHERE(AND.get(query.getQueryType().EQ(queryType), query.getQueryName().EQ(queryName)));

    OIterator<? extends SavedSearch> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }
}
