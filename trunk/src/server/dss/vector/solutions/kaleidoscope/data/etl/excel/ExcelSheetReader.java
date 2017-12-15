package dss.vector.solutions.kaleidoscope.data.etl.excel;

import java.io.InputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.runwaysdk.generation.loader.Reloadable;

public class ExcelSheetReader implements Reloadable
{
  /**
   * Handler used to parse the sheet
   */
  private SheetHandler  handler;

  /**
   * Data formatter used when parsing cell values
   */
  private DataFormatter formatter;

  public ExcelSheetReader(SheetHandler handler)
  {
    this(handler, new DataFormatter());
  }

  public ExcelSheetReader(SheetHandler handler, DataFormatter formatter)
  {
    this.handler = handler;
    this.formatter = formatter;
  }

  public void process(InputStream stream) throws Exception
  {
    try
    {
      OPCPackage pkg = OPCPackage.open(stream);

      ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
      XSSFReader xssfReader = new XSSFReader(pkg);
      StylesTable styles = xssfReader.getStylesTable();
      XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();

      while (iter.hasNext())
      {
        InputStream sheet = iter.next();

        try
        {
          String sheetName = iter.getSheetName();

          this.handler.startSheet(sheetName);

          InputSource sheetSource = new InputSource(sheet);
          ContentHandler handler = new XSSFSheetXMLHandler(styles, strings, this.handler, this.formatter, false);

          XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
          reader.setContentHandler(handler);
          reader.parse(sheetSource);

          this.handler.endSheet();
        }
        finally
        {
          sheet.close();
        }
      }
    }
    finally
    {
      stream.close();
    }
  }
}