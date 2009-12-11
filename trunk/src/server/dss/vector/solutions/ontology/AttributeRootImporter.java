package dss.vector.solutions.ontology;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.constants.MdAttributeConcreteInfo;
import com.terraframe.mojo.dataaccess.BusinessDAO;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.dataaccess.MdAttributeRefDAOIF;
import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdAttribute;

/**
 * Reads in an excel file that maps Mo Attriutes to term roots. Note that if you
 * run this from a development environment but want results to appear in a
 * deployed container (Run in Eclipse, see in Tomcat), you'll need a profile
 * with database.properties set up for deploy but environment=develop or you'll
 * get ClassCastExceptions. Unfortunately, this cannot be helped.
 * 
 * @author Eric Grunzke
 */
public class AttributeRootImporter
{
  @StartSession
  public static void main(String[] args) throws Exception
  {
    String fileName = "AttributeRoots.xls";
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

    AttributeRootImporter importer = new AttributeRootImporter();
    importer.read(new BufferedInputStream(new FileInputStream(file)));
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
    this.importDefault(row);
    this.importRoots(row);
  }

  private void importDefault(HSSFRow row)
  {
    String key = row.getCell(0).getRichStringCellValue().getString();

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.getByKey(key);
    if (mdAttribute == null)
    {
      throw new DataNotFoundException("Attribute key [" + key + "] not found.", MdTypeDAO.getMdTypeDAO(MdAttribute.CLASS));
    }

    // Iterate over all remaining columns. Each should have a Mo Term ID
    HSSFCell cell = row.getCell(3);

    if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
    {
      String termId = cell.getRichStringCellValue().getString();
      if (termId.length()==0)
      {
        return;
      }
      Term term = Term.getByTermId(termId);

//      FieldDefaultView view = new FieldDefaultView();
//      view.setMdAttribute(MdAttribute.get(mdAttribute.getId()));
//      view.setDefaultValue(term);
//      view.apply();
      
      if (term != null)
      {
        BusinessDAO mdAttributeDAO = mdAttribute.getMdAttributeConcrete().getBusinessDAO();

        if (mdAttributeDAO instanceof MdAttributeRefDAOIF)
        {
          mdAttributeDAO.setValue(MdAttributeConcreteInfo.DEFAULT_VALUE, term.getId());
          mdAttributeDAO.apply();
        }
      }
    }
  }

  private void importRoots(HSSFRow row)
  {
    String key = row.getCell(0).getRichStringCellValue().getString();

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.getByKey(key);

    if (mdAttribute == null)
    {
      throw new DataNotFoundException("Attribute key [" + key + "] not found.", MdTypeDAO.getMdTypeDAO(MdAttribute.CLASS));
    }

    BrowserField browserField = BrowserField.getFieldForAttribute(mdAttribute.definedByClass().definesType(), mdAttribute.definesAttribute());

    int i = 4;

    // Iterate over all remaining columns. Each should have a Mo Term ID
    while (row.getCell(i) != null)
    {
      HSSFCell cell = row.getCell(i);
      String termId = cell.getRichStringCellValue().getString();

      Term term = Term.getByTermId(termId);

      try
      {
        BrowserRoot browserRoot = new BrowserRoot();
        browserRoot.setTerm(term);
        browserField.addBrowserRoot(browserRoot);
      }
      catch (DuplicateRootException e)
      {
        // We'll ignore this. It's cheaper to catch the exception than to fetch
        // and iterate over the entire list of existing roots
      }

      i++;
    }

    browserField.apply();
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
}
