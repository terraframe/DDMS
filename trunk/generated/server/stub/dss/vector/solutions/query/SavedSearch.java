package dss.vector.solutions.query;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.terraframe.mojo.business.rbac.UserDAOIF;
import com.terraframe.mojo.constants.LocalProperties;
import com.terraframe.mojo.dataaccess.database.IDGenerator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;
import com.terraframe.mojo.util.FileIO;
import com.terraframe.mojo.vault.VaultFileDAO;
import com.terraframe.mojo.vault.VaultFileDAOIF;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.surveillance.AggregatedCase;

public abstract class SavedSearch extends SavedSearchBase implements
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
    PersistsSearchQuery persistsQuery = new PersistsSearchQuery(f);

    // restrict by user
    persistsQuery.WHERE(persistsQuery.parentId().EQ(user.getId()));
    persistsQuery.WHERE(persistsQuery.hasChild(searchQuery));

    // restrict by type and search name
    searchQuery.WHERE(searchQuery.getQueryName().EQ(searchName));
    searchQuery.WHERE(searchQuery.getType().EQ(this.getType()));
    //searchQuery.WHERE(searchQuery.persistedBy(persistsQuery));

    if (searchQuery.getCount() > 0)
    {
      String error = "A name must be unique per query screen.";
      UniqueSearchNameException ex = new UniqueSearchNameException(error);
      throw ex;
    }
  }

  /**
   * Updates this SavedSearch with the given view.
   *
   * @param view
   */
  protected void update(SavedSearchView view)
  {
    String xml = view.getQueryXml();

    this.setQueryXml(xml);
    this.apply();
  }

  /**
   * Creates and applies this SavedSearch object with the given information in
   * the SavedSearchView.
   *
   * @param view
   * @param savedQuery
   */
  protected void create(SavedSearchView view)
  {
    UserDAOIF userDAO = Session.getCurrentSession().getUser();
    MDSSUser mdssUser = MDSSUser.get(userDAO.getId());

    String name = view.getQueryName();

    checkUniqueness(name, mdssUser);

    String xml = view.getQueryXml();

    // Create the thematic layer if it does not exist
    String thematicLayerType = view.getThematicLayer();
    ThematicLayer thematicLayer = ThematicLayer.newInstance(thematicLayerType);
    this.setThematicLayer(thematicLayer);

    this.setQueryName(name);
    this.setQueryXml(xml);
    this.apply();

    mdssUser.addPersistedQueries(this).apply();
  }

  protected SavedSearchView getAsView(boolean includeXML)
  {
    SavedSearchView view = new SavedSearchView();

    view.setQueryName(this.getQueryName());
    view.setSavedQueryId(this.getId());

    ThematicLayer layer = this.getThematicLayer();
    if(layer != null)
    {
      view.setThematicLayerId(layer.getId());
    }

    if(includeXML)
    {
      view.setQueryXml(this.getQueryXml());
    }

    return view;
  }
  
  public InputStream getTemplateStream()
  {
    String template = this.getTemplateFile();
    
    if(template == null || template.equals(""))
    {
      String msg = "A report template has not been defined for this query";
      throw new RuntimeException(msg);
    }
    
    VaultFileDAOIF file = VaultFileDAO.get(template);
    
    return file.getFile();
  }
  
  @Override
  public String generateCSV(String queryXML, String geoEntityType, String savedSearchId)
  {
    try
    {
      String path = LocalProperties.getJspDir() + "/tmp/" + IDGenerator.nextID() + "/";
      
      // Make the file structure
      new File(path).mkdirs();
      
      File file = new File(path + "temp.csv"); 
      file.deleteOnExit();
      
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
      
      InputStream in = AggregatedCase.exportQueryToCSV(queryXML, geoEntityType, savedSearchId);
      
      FileIO.write(out, in);
      
      return path;
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void deleteCSV(String file)
  {
    try
    {
      FileIO.deleteDirectory(new File(file));
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

}
