/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.permission;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.runwaysdk.SystemException;
import com.runwaysdk.business.rbac.ActorDAO;
import com.runwaysdk.business.rbac.ActorDAOIF;
import com.runwaysdk.business.rbac.Operation;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.constants.MetadataInfo;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeDimensionDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MdRelationshipDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.cache.globalcache.ehcache.CacheShutdown;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdMethodDAO;
import com.runwaysdk.dataaccess.metadata.MdRelationshipDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.PermissionBuilder;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.Roles;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.export.ExcelVersionException;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.generator.FormSystemURLBuilder;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyView;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.permissions.RoleProperty;
import dss.vector.solutions.permissions.RolePropertyQuery;

public class PermissionImporter implements Reloadable
{
  private static final String               URL_SHEET_NAME        = "URL Permissions";

  private static final String               VISIBILITY_SHEET_NAME = "GUIVisibility";

  private static final String               ROLE_SHEET_NAME       = "MDSS Role Assignment";

  private Boolean                           urlOnly;

  private Disease[]                         diseases;

  private HashMap<String, MdDimensionDAOIF> mdDimensions;

  private HashMap<String, SystemURL>        systemURLs;

  public PermissionImporter()
  {
    this(false, Disease.getAllDiseases());
  }

  public PermissionImporter(boolean urlOnly, Disease... diseases)
  {
    this.urlOnly = urlOnly;
    this.diseases = diseases;
    this.systemURLs = new HashMap<String, SystemURL>();
    this.mdDimensions = new HashMap<String, MdDimensionDAOIF>();

    for (Disease disease : this.diseases)
    {
      MdDimensionDAOIF mdDimension = MdDimensionDAO.get(disease.getDimension().getId());

      mdDimensions.put(disease.getKey(), mdDimension);
    }
  }

  @Transaction
  public void read(InputStream stream)
  {
    Workbook workbook = openStream(stream);

    this.readURLSheet(workbook);

    if (!this.urlOnly)
    {
      this.readVisibilitySheet(workbook);
      this.readRoleAssignment(workbook);
      this.addLocatedInPermissions();
    }

    this.serializeURLRoles();
  }

  @Transaction
  public void delete()
  {
    for (Disease disease : diseases)
    {
      RolePropertyQuery query = new RolePropertyQuery(new QueryFactory());
      query.WHERE(query.getDisease().EQ(disease));

      OIterator<? extends RoleProperty> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          it.next().delete();
        }
      }
      finally
      {
        it.close();
      }
    }
  }

  private void addLocatedInPermissions()
  {
    MdRelationshipDAOIF locatedIn = MdRelationshipDAO.getMdRelationshipDAO(LocatedIn.CLASS);
    MdBusinessDAOIF allPaths = MdBusinessDAO.getMdBusinessDAO(AllPaths.CLASS);

    MDSSRoleViewQuery query = new MDSSRoleViewQuery(new QueryFactory());
    OIterator<? extends MDSSRoleView> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        MDSSRoleView view = it.next();
        RoleDAO role = view.getRole().getBusinessDAO();

        boolean hasUniversalPermissions = this.hasUniversalPermissions(role);

        if (hasUniversalPermissions)
        {
          role.grantPermission(Operation.WRITE, locatedIn.getId());
          role.grantPermission(Operation.CREATE, locatedIn.getId());
          role.grantPermission(Operation.DELETE, locatedIn.getId());

          role.grantPermission(Operation.WRITE, allPaths.getId());
          role.grantPermission(Operation.CREATE, allPaths.getId());
          role.grantPermission(Operation.DELETE, allPaths.getId());
        }
        else
        {
          role.revokePermission(Operation.WRITE, locatedIn.getId());
          role.revokePermission(Operation.CREATE, locatedIn.getId());
          role.revokePermission(Operation.DELETE, locatedIn.getId());

          role.revokePermission(Operation.WRITE, allPaths.getId());
          role.revokePermission(Operation.CREATE, allPaths.getId());
          role.revokePermission(Operation.DELETE, allPaths.getId());
        }
      }
    }
    finally
    {
      it.close();
    }
  }

  private boolean hasUniversalPermissions(RoleDAO role)
  {
    boolean hasUniversalPermissions = false;

    GeoHierarchyView[] universals = GeoHierarchy.getAllViews();

    for (GeoHierarchyView universal : universals)
    {
      String generatedType = universal.getGeneratedType();

      if (!generatedType.equals(Earth.CLASS) && !generatedType.equals(GeoEntity.CLASS))
      {
        MetadataDAOIF metadata = MdBusinessDAO.getMdBusinessDAO(generatedType);
        Set<Operation> permissions = role.getAssignedPermissions(metadata);

        if (permissions.contains(Operation.CREATE))
        {
          hasUniversalPermissions = true;
        }
      }
    }
    return hasUniversalPermissions;
  }

  private void serializeURLRoles()
  {
    Set<String> keys = this.systemURLs.keySet();

    for (String key : keys)
    {
      SystemURL url = this.systemURLs.get(key);

      for (Disease disease : diseases)
      {
        String[] actionKeys = new String[] { "READ", "WRITE" };

        for (String actionKey : actionKeys)
        {
          PermissionFactory factory = new PermissionFactory(actionKey);

          PermissionAction action = factory.getAction(url, disease);
          Roles role = action.getRole();

          ActorDAOIF actor = (ActorDAOIF) ActorDAO.get(role.getId());

          if (url.getUrl().equals(FormSystemURLBuilder.FORM_SURVEY_CRUD_URL) || url.getUrl().equals(FormSystemURLBuilder.FORM_SURVEY_QUERY_URL))
          {
            new PermissionBuilder(actor).cleanup();
          }
          else
          {
            new PermissionBuilder(actor).serialize();
          }
        }
      }
    }

    RoleDAO role = RoleDAO.findRole(RoleDAOIF.PUBLIC_ROLE).getBusinessDAO();
    new PermissionBuilder(role).serialize();
  }

  @SuppressWarnings("unchecked")
  private Iterator<Row> getSheetRows(Workbook workbook, String sheetName)
  {
    return workbook.getSheet(sheetName).iterator();
  }

  private void readRoleAssignment(Workbook workbook)
  {
    Iterator<Row> iterator = this.getSheetRows(workbook, ROLE_SHEET_NAME);

    if (iterator.hasNext())
    {
      Row header = iterator.next();

      List<RoleDAO> roles = this.getMDSSRoles(header);

      while (iterator.hasNext())
      {
        Row row = iterator.next();

        this.readAssignment(row, roles);
      }
    }
  }

  private void readAssignment(Row row, List<RoleDAO> roles)
  {
    SystemURL url = this.getURL(ExcelUtil.getString(row.getCell(0)));

    if (url != null)
    {
      for (Disease disease : diseases)
      {
        RoleDAO writeRoleDAO = url.getRole(disease, PermissionOption.WRITE);
        RoleDAO readRoleDAO = url.getRole(disease, PermissionOption.READ);

        if (writeRoleDAO != null && readRoleDAO != null)
        {
          int i = 1;

          for (RoleDAO role : roles)
          {
            Set<RoleDAOIF> superRoles = role.getSuperRoles();
            String action = ExcelUtil.getString(row.getCell(i++));

            if (action.equalsIgnoreCase("W"))
            {
              // ensure that the role does not already inherit the writeRoleDAO
              if (!superRoles.contains(writeRoleDAO))
              {
                role.addAscendant(writeRoleDAO);
              }

              if (!superRoles.contains(readRoleDAO))
              {
                role.addAscendant(readRoleDAO);
              }
            }
            else if (action.equalsIgnoreCase("R"))
            {
              if (!superRoles.contains(readRoleDAO))
              {
                role.addAscendant(readRoleDAO);
              }
            }
          }
        }
      }
    }
  }

  private List<RoleDAO> getMDSSRoles(Row header)
  {
    List<RoleDAO> list = new LinkedList<RoleDAO>();

    int i = 1;

    while (header.getCell(i) != null)
    {
      String roleName = ExcelUtil.getString(header.getCell(i++));

      MDSSRoleView view = MDSSRole.getViewByRoleName(roleName);

      if (!view.hasConcrete())
      {
        view.apply();
      }

      list.add(view.getRole().getBusinessDAO());
    }

    return list;
  }

  private void readURLSheet(Workbook workbook)
  {
    Iterator<Row> iterator = this.getSheetRows(workbook, URL_SHEET_NAME);

    // Skip the header row
    iterator.next();

    while (iterator.hasNext())
    {
      readURLRow(iterator.next());
    }
  }

  private void readVisibilitySheet(Workbook workbook)
  {
    RoleDAO guiVisibility = RoleDAO.findRole(MDSSRoleInfo.GUI_VISIBILITY).getBusinessDAO();
    Iterator<Row> iterator = this.getSheetRows(workbook, VISIBILITY_SHEET_NAME);

    // Skip the header row
    if (iterator.hasNext())
    {
      iterator.next();

      while (iterator.hasNext())
      {
        readVisibilityRow(iterator.next(), guiVisibility);
      }
    }
  }

  private void readVisibilityRow(Row row, RoleDAO role)
  {
    String key = ExcelUtil.getString(row.getCell(0));
    String diseaseName = ExcelUtil.getString(row.getCell(1));

    if (key != null && key.length() > 0)
    {
      MdAttributeDAOIF mdAttributeDAO = MdAttributeDAO.getByKey(key);

      if (mdAttributeDAO != null)
      {
        this.setVisibility(role, diseaseName, mdAttributeDAO);
      }
      else
      {
        throw new RuntimeException("Could not find a MdAttribute with the key [" + key + "]");
      }
    }

  }

  private void setVisibility(RoleDAO role, String diseaseName, MdAttributeDAOIF mdAttributeDAO)
  {
    List<MdAttributeDAOIF> list = new LinkedList<MdAttributeDAOIF>();

    list.add(mdAttributeDAO);
    list.add(mdAttributeDAO.getMdAttributeConcrete());

    for (MdAttributeDAOIF mdAttribute : list)
    {
      if (diseaseName != null && diseaseName.length() > 0)
      {
        MdDimensionDAOIF mdDimension = this.mdDimensions.get(diseaseName);
        MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);

        role.grantPermission(Operation.DENY_READ, mdAttributeDimension.getId());
      }
      else
      {
        Set<String> keys = mdDimensions.keySet();

        for (String dimensionKey : keys)
        {
          MdDimensionDAOIF mdDimension = mdDimensions.get(dimensionKey);

          MdAttributeDimensionDAOIF mdAttributeDimension = mdAttribute.getMdAttributeDimension(mdDimension);

          role.grantPermission(Operation.DENY_READ, mdAttributeDimension.getId());
        }
      }
    }
  }

  private void readURLRow(Row row)
  {
    String urlKey = ExcelUtil.getString(row.getCell(0));
    String actionKey = ExcelUtil.getString(row.getCell(1));
    PermissionFactory factory = new PermissionFactory(actionKey);

    if (urlKey.equalsIgnoreCase("COMMON"))
    {
      Set<String> keys = this.systemURLs.keySet();

      for (String key : keys)
      {
        SystemURL url = this.systemURLs.get(key);

        for (Disease disease : diseases)
        {
          PermissionAction action = factory.getAction(url, disease);

          this.importPermissions(row, action);
        }
      }
    }
    else if (urlKey.equalsIgnoreCase(RoleDAOIF.PUBLIC_ROLE))
    {
      RoleDAO role = RoleDAO.findRole(RoleDAOIF.PUBLIC_ROLE).getBusinessDAO();

      for (Disease disease : diseases)
      {
        PermissionAction action = factory.getAction(role, disease);

        this.importPermissions(row, action);
      }
    }
    else
    {
      SystemURL url = this.getURL(urlKey);

      if (url != null)
      {

        for (Disease disease : diseases)
        {
          PermissionAction action = factory.getAction(url, disease);

          this.importPermissions(row, action);
        }
      }
    }
  }

  private SystemURL getURL(String urlKey)
  {
    if (!this.systemURLs.containsKey(urlKey))
    {
      SystemURL url = SystemURL.getByURL(urlKey);

      if (url != null)
      {
        this.systemURLs.put(urlKey, url);
      }
    }

    return this.systemURLs.get(urlKey);
  }

  private void importPermissions(Row row, PermissionAction action)
  {
    int i = 3;

    while (row.getCell(i) != null)
    {
      String key = ExcelUtil.getString(row.getCell(i++));
      if (key == null)
      {
        continue;
      }
      if (key.length() == 0)
      {
        continue;
      }

      MetadataDAOIF metadata = getMetadata(key);

      if (metadata != null)
      {
        action.assign(metadata);
      }
    }
  }

  private MetadataDAOIF getMetadata(String key)
  {
    if (key == null || key.length() == 0)
    {
      return null;
    }

    try
    {
      return MdClassDAO.getMdClassDAO(key);
    }
    catch (Exception e)
    {
      try
      {
        return MdMethodDAO.getMdMethod(key);
      }
      catch (Exception e1)
      {

      }
    }

    throw new DataNotFoundException("Key [" + key + "] not found.", MdTypeDAO.getMdTypeDAO(MetadataInfo.CLASS));
  }

  /**
   * Opens the stream and returns an initialized row iterator
   * 
   * @param stream
   * @return
   * @throws IOException
   */
  private Workbook openStream(InputStream stream)
  {
    try
    {
      Workbook workbook = WorkbookFactory.create(stream);

      return workbook;
    }
    catch (OfficeXmlFileException e)
    {
      throw new ExcelVersionException(e);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
    catch (InvalidFormatException e)
    {
      throw new ExcelVersionException(e);
    }
  }

  public static void main(String[] args) throws Exception
  {
    try
    {
      PermissionImporter.start(args);
    }
    finally
    {
      CacheShutdown.shutdown();
    }
  }

  @Request
  private static void start(String[] args) throws FileNotFoundException
  {
    String fileName = "Permissions.xls";
    File file = new File(fileName);

    if (args.length == 0)
    {
      if (file.exists())
      {
        System.out.println("No file name specified. Using default location: " + file.getAbsoluteFile());
      }
      else
      {
        System.out.println("No file name specified. Add file name as a comand line argument.");
        return;
      }
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }

    PermissionImporter importer = new PermissionImporter();
    importer.read(new BufferedInputStream(new FileInputStream(file)));
  }
}
