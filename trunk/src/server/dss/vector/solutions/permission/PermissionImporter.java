package dss.vector.solutions.permission;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.runwaysdk.SystemException;
import com.runwaysdk.business.rbac.RoleDAO;
import com.runwaysdk.business.rbac.RoleDAOIF;
import com.runwaysdk.constants.MetadataInfo;
import com.runwaysdk.dataaccess.MdDimensionDAOIF;
import com.runwaysdk.dataaccess.MetadataDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdClassDAO;
import com.runwaysdk.dataaccess.metadata.MdDimensionDAO;
import com.runwaysdk.dataaccess.metadata.MdMethodDAO;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.StartSession;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.general.SystemURL;
import dss.vector.solutions.general.SystemURLQuery;

public class PermissionImporter implements Reloadable
{
  private List<MdDimensionDAOIF>     mdDimensions;

  private HashMap<String, SystemURL> systemURLs;

  public PermissionImporter()
  {
    this.mdDimensions = MdDimensionDAO.getAllMdDimensions();
    this.systemURLs = new HashMap<String, SystemURL>();
  }

  @Transaction
  public void read(InputStream stream)
  {
    Iterator<HSSFRow> iterator = openStream(stream);

    // Skip the header row
    iterator.next();

    while (iterator.hasNext())
    {
      readRow(iterator.next());
    }
  }

  private void readRow(HSSFRow row)
  {
    String urlKey = ExcelUtil.getString(row.getCell(0));

    if (urlKey.equalsIgnoreCase("COMMON"))
    {
      Set<String> keys = this.systemURLs.keySet();

      for (String key : keys)
      {
        SystemURL url = this.systemURLs.get(key);
        PermissionAction action = this.getAction(row, url);

        this.importPermissions(row, action);
      }
    }
    else if (urlKey.equalsIgnoreCase(RoleDAOIF.PUBLIC_ROLE))
    {
      RoleDAO role = RoleDAO.findRole(RoleDAOIF.PUBLIC_ROLE).getBusinessDAO();

      PermissionAction action = this.getAction(row, null, role);

      this.importPermissions(row, action);
    }
    else
    {
      SystemURL url = this.getURL(urlKey);
      RoleDAO role = this.importRole(row);

      PermissionAction action = this.getAction(row, url, role);

      action.updateURL();

      this.importPermissions(row, action);
    }
  }

  private PermissionAction getAction(HSSFRow row, SystemURL url, RoleDAO role)
  {
    String action = ExcelUtil.getString(row.getCell(1));

    return PermissionAction.getAction(action, url, role, mdDimensions);
  }

  private PermissionAction getAction(HSSFRow row, SystemURL url)
  {
    String action = ExcelUtil.getString(row.getCell(1));

    return PermissionAction.getAction(action, url, mdDimensions);
  }

  private SystemURL getURL(String urlKey)
  {
    if (!this.systemURLs.containsKey(urlKey))
    {
      SystemURLQuery query = new SystemURLQuery(new QueryFactory());
      query.WHERE(query.getUrl().EQ(urlKey));

      OIterator<? extends SystemURL> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          SystemURL url = it.next();

          this.systemURLs.put(urlKey, url);
        }
      }
      finally
      {
        it.close();
      }
    }

    return this.systemURLs.get(urlKey);
  }

  private RoleDAO importRole(HSSFRow row)
  {
    String _roleName = ExcelUtil.getString(row.getCell(2));
    String roleName = MDSSRoleInfo.MDSS_PREFIX + "." + _roleName;

    try
    {
      return RoleDAO.findRole(roleName).getBusinessDAO();
    }
    catch (DataNotFoundException e)
    {
      RoleDAO role = RoleDAO.createRole(roleName, roleName);
      role.apply();

      return role;
    }
  }

  private void importPermissions(HSSFRow row, PermissionAction action)
  {
    int i = 3;

    while (row.getCell(i) != null)
    {
      String key = ExcelUtil.getString(row.getCell(i++));

      MetadataDAOIF metadata = getMetadata(key);

      action.assign(metadata);
    }
  }

  private MetadataDAOIF getMetadata(String key)
  {
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
  @SuppressWarnings("unchecked")
  private Iterator<HSSFRow> openStream(InputStream stream)
  {
    try
    {
      POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      HSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<HSSFRow> rowIterator = sheet.rowIterator();

      return rowIterator;
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

  @StartSession
  public static void main(String[] args) throws Exception
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
