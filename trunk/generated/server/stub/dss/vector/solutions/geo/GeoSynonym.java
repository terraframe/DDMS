package dss.vector.solutions.geo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.query.ColumnInfo;

public class GeoSynonym extends GeoSynonymBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248321233376L;

  public GeoSynonym()
  {
    super();
  }

  public static void checkExcelGeoHierarchy(java.io.InputStream inputStream)
  {
    // Open the stream
    Iterator<HSSFRow> rowIterator;
    try
    {
      rowIterator = openStream(inputStream);

      // Parse the header rows, which builds up our list of ColumnInfos
      readHeaders(rowIterator);
    }
    catch (IOException e)
    {
      // If we've encountered an exception already, there's no point in proceeding
      throw new SystemException(e);
    }
  }

  /**
   * Reads the first two rows, which represent the attribute names and attribute
   * display labels respectively. Creates the list of {@link ColumnInfo}s that
   * is referenced when importing row data.
   *
   * @param rowIterator
   */
  @SuppressWarnings("unchecked")
  private static void readHeaders(Iterator<HSSFRow> rowIterator)
  {
    HSSFRow typeRow = rowIterator.next();
    HSSFRow nameRow = rowIterator.next();
    HSSFRow labelRow = rowIterator.next();

//    appendRow(typeRow);
//    appendRow(nameRow);
//    appendRow(labelRow);

  }

  /**
   * Opens the stream, parses the type from the sheet name, and returns an
   * initialized row iterator
   *
   * @param stream
   * @return
   * @throws IOException
   */
  @SuppressWarnings("unchecked")
  private static Iterator<HSSFRow> openStream(InputStream stream) throws IOException
  {
    POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
    HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
    HSSFSheet sheet = workbook.getSheetAt(0);
    Iterator<HSSFRow> rowIterator = sheet.rowIterator();

    return rowIterator;
  }


}
