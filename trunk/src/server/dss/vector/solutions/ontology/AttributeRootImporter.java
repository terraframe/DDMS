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
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
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

  @SuppressWarnings("unchecked")
  private void readRow(HSSFRow row)
  {
    Iterator<HSSFCell> iterator = row.cellIterator();

    String key = iterator.next().getRichStringCellValue().getString();
    iterator.next(); // Skip the Class Label
    iterator.next(); // Skip the Attribute Label

    MdAttributeDAOIF mdAttribute = MdAttributeDAO.getByKey(key);
    if (mdAttribute == null)
    {
      throw new DataNotFoundException("Attribute key [" + key + "] not found.", MdTypeDAO.getMdTypeDAO(MdAttribute.CLASS));
    }

    BrowserField browserField = BrowserField.getFieldForAttribute(mdAttribute.definedByClass().definesType(), mdAttribute.definesAttribute());

    // Iterate over all remaining columns.  Each should have a Mo Term ID
    while (iterator.hasNext())
    {
      String termId = iterator.next().getRichStringCellValue().getString();
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
