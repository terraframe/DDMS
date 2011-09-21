package dss.vector.solutions.generator;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.dataaccess.metadata.MdMethodDAO;
import com.runwaysdk.dataaccess.metadata.MdWebFormDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.Roles;
import com.runwaysdk.system.VaultFile;
import com.runwaysdk.system.metadata.FormField;
import com.runwaysdk.system.metadata.MdField;
import com.runwaysdk.system.metadata.MdForm;
import com.runwaysdk.system.metadata.MdWebBoolean;
import com.runwaysdk.system.metadata.MdWebBreak;
import com.runwaysdk.system.metadata.MdWebCharacter;
import com.runwaysdk.system.metadata.MdWebComment;
import com.runwaysdk.system.metadata.MdWebDate;
import com.runwaysdk.system.metadata.MdWebDouble;
import com.runwaysdk.system.metadata.MdWebField;
import com.runwaysdk.system.metadata.MdWebFloat;
import com.runwaysdk.system.metadata.MdWebForm;
import com.runwaysdk.system.metadata.MdWebGeo;
import com.runwaysdk.system.metadata.MdWebHeader;
import com.runwaysdk.system.metadata.MdWebInteger;
import com.runwaysdk.system.metadata.MdWebLong;
import com.runwaysdk.system.metadata.MdWebMultipleTerm;
import com.runwaysdk.system.metadata.MdWebPrimitive;
import com.runwaysdk.system.metadata.MdWebSingleTerm;
import com.runwaysdk.system.metadata.MdWebSingleTermGrid;
import com.runwaysdk.system.metadata.WebGridField;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.permission.ReadAction;
import dss.vector.solutions.permission.WriteAction;
import dss.vector.solutions.permissions.RoleProperty;
import dss.vector.solutions.query.DefaultSavedSearch;
import dss.vector.solutions.query.PersistsSearch;
import dss.vector.solutions.query.QueryBuilder;
import dss.vector.solutions.query.SavedSearch;
import dss.vector.solutions.query.SavedSearchView;
import dss.vector.solutions.util.ReadableAttributeView;

public class FormSystemURLBuilder implements Reloadable
{
  private MdWebFormDAOIF mdForm;

  public FormSystemURLBuilder(MdWebForm mdForm)
  {
    this(MdWebFormDAO.get(mdForm.getId()));
  }

  public FormSystemURLBuilder(MdWebFormDAOIF mdForm)
  {
    this.mdForm = mdForm;
  }

  @Transaction
  public void delete()
  {
    SystemURL crudURL = SystemURL.getByKey("Enter " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
    SystemURL queryURL = SystemURL.getByKey("Query " + mdForm.getDisplayLabel(Session.getCurrentLocale()));

    this.deleteRoles(crudURL);
    this.deleteRoles(queryURL);
    this.deleteMenu(crudURL);
    this.deleteMenu(queryURL);

    crudURL.delete();
    queryURL.delete();
  }

  private void deleteMenu(SystemURL crudURL)
  {
    List<? extends MenuItem> items = MenuItem.findMenuItems(crudURL);

    for (MenuItem item : items)
    {
      item.delete();
    }
  }

  private void deleteRoles(SystemURL url)
  {
    OIterator<? extends RoleProperty> iterator = url.getAllRoles();

    try
    {
      while (iterator.hasNext())
      {
        RoleProperty property = iterator.next();
        Roles role = property.getRole();

        property.delete();
        role.delete();
      }
    }
    finally
    {
      iterator.close();
    }
  }

  @Transaction
  public void generate()
  {
    MdBusinessDAOIF mdBusiness = (MdBusinessDAOIF) mdForm.getFormMdClass();

    List<MdRelationshipDAOIF> mdRelationships = new LinkedList<MdRelationshipDAOIF>();
    mdRelationships.addAll(mdBusiness.getAllChildMdRelationships());
    mdRelationships.addAll(mdBusiness.getAllParentMdRelationships());

    // Create the system url for the CRUD screen
    SystemURL crudURL = new SystemURL();
    crudURL.setUrl("dss.vector.solutions.generator.ExcelController.importType.mojo?type=" + mdForm.definesType());
    crudURL.getDisplayLabel().setValue("defaultLocale", "Enter " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
    crudURL.apply();

    // Create the system url for the query screen
    SystemURL queryURL = new SystemURL();
    queryURL.setUrl("dss.vector.solutions.query.QueryController.queryType.mojo?type=" + mdForm.definesType());
    queryURL.getDisplayLabel().setValue("defaultLocale", "Query " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
    queryURL.apply();

    Disease[] diseases = Disease.getAllDiseases();

    for (Disease disease : diseases)
    {
      // Create the Write role for the CRUD screen
      WriteAction crudWriteAction = new WriteAction(crudURL, disease);
      crudWriteAction.assign(mdBusiness);

      for (MdRelationshipDAOIF mdRelationship : mdRelationships)
      {
        crudWriteAction.assign(mdRelationship);
      }

      // Create the Read Role for the CRUD screen
      ReadAction crudReadAction = new ReadAction(crudURL, disease);
      crudReadAction.assign(mdBusiness);

      for (MdRelationshipDAOIF mdRelationship : mdRelationships)
      {
        crudReadAction.assign(mdRelationship);
      }

      // Create the Write role for the query screen
      WriteAction queryWriteAction = new WriteAction(queryURL, disease);
      queryWriteAction.assign(MdClassDAO.getMdClassDAO(SavedSearch.CLASS));
      queryWriteAction.assign(MdClassDAO.getMdClassDAO(PersistsSearch.CLASS));
      queryWriteAction.assign(MdClassDAO.getMdClassDAO(VaultFile.CLASS));

      // Create the Read Role for the query screen
      ReadAction queryReadAction = new ReadAction(queryURL, disease);
      queryReadAction.assign(MdClassDAO.getMdClassDAO(VaultFile.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(DefaultSavedSearch.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(SavedSearch.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(PersistsSearch.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(SavedSearchView.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(ReadableAttributeView.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdForm.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdField.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebField.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebBreak.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebCharacter.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebComment.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebDate.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebDouble.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebFloat.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebGeo.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebHeader.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebInteger.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebMultipleTerm.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebPrimitive.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebBoolean.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebLong.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebSingleTerm.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(MdWebSingleTermGrid.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(WebGridField.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(GeoField.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(FormField.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(GeoField.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(ExtraFieldUniversal.CLASS));
      queryReadAction.assign(MdMethodDAO.getMdMethod(QueryBuilder.CLASS + ".getQueryResults"));
      queryReadAction.assign(MdMethodDAO.getMdMethod(QueryBuilder.CLASS + ".getTextAttributeSugestions"));
    }

  }

  @Request
  public static void main(String[] args)
  {
    MdWebFormDAOIF mdForm = (MdWebFormDAOIF) MdFormDAO.getMdTypeDAO("com.test.TestTypeForm");

    if (args[0].equals("delete"))
    {
      new FormSystemURLBuilder(mdForm).delete();
    }
    else
    {
      new FormSystemURLBuilder(mdForm).generate();
    }
  }
}
