/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.generator;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdClassDAOIF;
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
import com.runwaysdk.system.metadata.WebGroupField;

import dss.vector.solutions.form.business.FormBedNet;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormSurvey;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.MenuItem;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.geo.ExtraFieldUniversal;
import dss.vector.solutions.geo.GeoField;
import dss.vector.solutions.permission.PermissionAction;
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
  /**
   * IMPORTANT: THIS VALUE MUST MATCH THE KEY DEFINED IN MenuItems.xls for the form survey query url
   */
  public static final String  FORM_SURVEY_QUERY_URL = "dss.vector.solutions.query.QueryController.queryFormSurvey.mojo";

  /**
   * IMPORTANT: THIS VALUE MUST MATCH THE KEY DEFINED IN MenuItems.xls for the form survey view all
   * url
   */
  public static final String  FORM_SURVEY_CRUD_URL  = "dss.vector.solutions.form.business.FormSurveyController.viewAll.mojo";

  /**
   * Prefix to add to before all generated CRUD URLs
   */
  private static final String CRUD_URL_PREFIX       = "Enter ";

  /**
   * Prefix to add to before all generated Query URLs
   */
  private static final String QUERY_URL_PREFIX      = "Query ";

  private MdWebFormDAOIF      mdForm;

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
    SystemURL crudURL = this.getCrudURL();
    SystemURL queryURL = this.getQueryURL();

    this.deleteRoles(crudURL);
    this.deleteRoles(queryURL);
    this.deleteMenu(crudURL);
    this.deleteMenu(queryURL);

    crudURL.delete();
    queryURL.delete();
  }

  @Transaction
  public void add(MdClassDAOIF mdClass)
  {
    this.addPermissions(mdClass, Disease.getAllDiseases());
  }

  public void addPermissions(MdClassDAOIF mdClass, Disease... diseases)
  {
    SystemURL crudURL = this.getCrudURL();

    for (Disease disease : diseases)
    {
      // Create the Write role for the CRUD screen
      WriteAction crudWriteAction = new WriteAction(crudURL, disease);
      crudWriteAction.assign(mdClass);

      // Create the Read Role for the CRUD screen
      ReadAction crudReadAction = new ReadAction(crudURL, disease);
      crudReadAction.assign(mdClass);
    }
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
    // Create the system url for the CRUD screen
    if (this.getCrudURL() == null)
    {
      SystemURL crudURL = new SystemURL();
      crudURL.setUrl(this.getCrudURLKey());
      crudURL.getDisplayLabel().setValue("defaultLocale", CRUD_URL_PREFIX + " " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
      crudURL.setUrlName(CRUD_URL_PREFIX + " " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
      crudURL.apply();
    }

    // Create the system url for the query screen
    if (this.getQueryURL() == null)
    {
      SystemURL queryURL = new SystemURL();
      queryURL.setUrl(this.getQueryURLKey());
      queryURL.getDisplayLabel().setValue("defaultLocale", QUERY_URL_PREFIX + " " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
      queryURL.setUrlName(QUERY_URL_PREFIX + " " + mdForm.getDisplayLabel(Session.getCurrentLocale()));
      queryURL.apply();
    }

    this.addPermissions(Disease.getAllDiseases());
  }

  public void addPermissions(Disease... diseases)
  {
    SystemURL crudURL = this.getCrudURL();
    SystemURL queryURL = this.getQueryURL();

    this.addPermissions(crudURL, queryURL, diseases);
  }

  private void addPermissions(SystemURL crudURL, SystemURL queryURL, Disease... diseases)
  {
    MdBusinessDAOIF mdBusiness = (MdBusinessDAOIF) mdForm.getFormMdClass();

    List<MdRelationshipDAOIF> mdRelationships = new LinkedList<MdRelationshipDAOIF>();
    mdRelationships.addAll(mdBusiness.getAllChildMdRelationships());
    mdRelationships.addAll(mdBusiness.getAllParentMdRelationships());

    for (Disease disease : diseases)
    {
      // Create the Write role for the CRUD screen
      WriteAction crudWriteAction = new WriteAction(crudURL, disease);
      crudWriteAction.assign(mdBusiness);
      addDefaultMdWebPermissions(crudWriteAction);

      for (MdRelationshipDAOIF mdRelationship : mdRelationships)
      {
        crudWriteAction.assign(mdRelationship);
      }

      // Create the Read Role for the CRUD screen
      ReadAction crudReadAction = new ReadAction(crudURL, disease);
      crudReadAction.assign(mdBusiness);
      addDefaultMdWebPermissions(crudReadAction);

      for (MdRelationshipDAOIF mdRelationship : mdRelationships)
      {
        crudReadAction.assign(mdRelationship);
      }

      // Create the Write role for the query screen
      WriteAction queryWriteAction = new WriteAction(queryURL, disease);
      addDefaultMdWebPermissions(queryWriteAction);
      queryWriteAction.assign(MdClassDAO.getMdClassDAO(SavedSearch.CLASS));
      queryWriteAction.assign(MdClassDAO.getMdClassDAO(PersistsSearch.CLASS));
      queryWriteAction.assign(MdClassDAO.getMdClassDAO(VaultFile.CLASS));

      // Create the Read Role for the query screen
      ReadAction queryReadAction = new ReadAction(queryURL, disease);
      addDefaultMdWebPermissions(queryReadAction);
      queryReadAction.assign(MdClassDAO.getMdClassDAO(VaultFile.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(DefaultSavedSearch.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(SavedSearch.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(PersistsSearch.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(SavedSearchView.CLASS));
      queryReadAction.assign(MdClassDAO.getMdClassDAO(ReadableAttributeView.CLASS));
      
      queryReadAction.assign(MdMethodDAO.getMdMethod(QueryBuilder.CLASS + ".getQueryResults"));
      queryReadAction.assign(MdMethodDAO.getMdMethod(QueryBuilder.CLASS + ".getTextAttributeSugestions"));
    }
  }
  
  private void addDefaultMdWebPermissions(PermissionAction action)
  {
    action.assign(MdClassDAO.getMdClassDAO(MdForm.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebBreak.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebCharacter.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebComment.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebDate.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebDouble.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebFloat.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebGeo.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebHeader.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebInteger.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebMultipleTerm.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebPrimitive.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebBoolean.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebLong.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebSingleTerm.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdWebSingleTermGrid.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(MdField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(WebGridField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(WebGroupField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(FormField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(GeoField.CLASS));
    action.assign(MdClassDAO.getMdClassDAO(ExtraFieldUniversal.CLASS));
  }

  public SystemURL getCrudURL()
  {
    SystemURL url = SystemURL.getByURL(this.getCrudURLKey());
    
    if (url == null)
    {
      url = SystemURL.getByURL(this.getCrudURLFetchByKey());
    }
    
    return url;
  }

  public SystemURL getQueryURL()
  {
    return SystemURL.getByURL(this.getQueryURLKey());
  }

  private String getQueryURLKey()
  {
    String formType = mdForm.definesType();

    // All types on the form survey share the same hard-coded url
    if (formType.equals(FormSurvey.FORM_TYPE) || formType.equals(FormHousehold.FORM_TYPE) || formType.equals(FormBedNet.FORM_TYPE) || formType.equals(FormPerson.FORM_TYPE))
    {
      return FORM_SURVEY_QUERY_URL;
    }

    return "dss.vector.solutions.query.QueryController.queryType.mojo?type=" + mdForm.definesType();
  }

  private String getCrudURLKey()
  {
    String formType = mdForm.definesType();

    // All types on the form survey share the same hard-coded url
    if (formType.equals(FormSurvey.FORM_TYPE) || formType.equals(FormHousehold.FORM_TYPE) || formType.equals(FormBedNet.FORM_TYPE) || formType.equals(FormPerson.FORM_TYPE))
    {
      return FORM_SURVEY_CRUD_URL;
    }

    return "dss.vector.solutions.form.FormObjectController.formGenerator.mojo?mdFormId=" + mdForm.getId();
  }
  
  private String getCrudURLFetchByKey()
  {
    String formType = mdForm.definesType();

    return "dss.vector.solutions.form.FormObjectController.formGeneratorKey.mojo?formKey=" + formType;
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
