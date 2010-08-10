package dss.vector.solutions.ontology;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.SystemException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.session.Request;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.util.MDSSProperties;

public class OntologyExcelExporter
{
  private static final int NAME_COLUMN = 4;

  private HSSFWorkbook workbook;
  private HSSFSheet sheet;
  private int indent = NAME_COLUMN;
  private int rowCount = 0;

  // Keep track of the terms we've already exported
  private Set<String> exported;

  @Request
  public static void main(String[] args) throws IOException
  {
    long start = System.currentTimeMillis();

    String fileName = "OntologyExport.xls";
    File file = new File(fileName);
    if (args.length==0)
    {
      System.out.println("No file name specified.  Using default location: " + file.getAbsoluteFile());
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }

    OntologyExcelExporter exporter = new OntologyExcelExporter();
    exporter.export();
    FileIO.write(file, exporter.write());

    long end = System.currentTimeMillis();
    System.out.println("Exported in " + (end-start)/1000.0 + " seconds");
  }

  public OntologyExcelExporter()
  {
    workbook = new HSSFWorkbook();
    sheet = workbook.createSheet();
    exported = new TreeSet<String>();
  }

  public void export()
  {
    HSSFRow header = sheet.createRow(rowCount++);
    header.createCell(0).setCellValue(new HSSFRichTextString("ID"));
    header.createCell(1).setCellValue(new HSSFRichTextString("Active Malaria"));
    header.createCell(2).setCellValue(new HSSFRichTextString("Active Dengue"));
    header.createCell(3).setCellValue(new HSSFRichTextString("ParentId"));
    header.createCell(NAME_COLUMN).setCellValue(new HSSFRichTextString("Name"));

    Term root = Term.getByTermId(MDSSProperties.getString("ROOT"));
    for (Term child : root.getAllChildTerm())
    {
      recurse(child);
    }

    for (short s=0; s<NAME_COLUMN; s++)
    {
      sheet.autoSizeColumn(s);
    }
    for (int i=NAME_COLUMN; i<40; i++)
    {
      sheet.setColumnWidth(i, 1280);
    }
  }

  private void recurse(Term parent)
  {
    Boolean activeMalaria = true;
    Boolean activeDengue = true;
    OIterator<? extends InactiveProperty> props = parent.getAllInactiveProperties();
    try
    {
      for (InactiveProperty ip : props)
      {
        Disease disease = ip.getDisease();
        if (disease.equals(Disease.MALARIA))
        {
          activeMalaria = !ip.getInactive();
        }
        if (disease.equals(Disease.DENGUE))
        {
          activeDengue = !ip.getInactive();
        }
      }
    }
    finally
    {
      props.close();
    }

    // Write the row
    HSSFRow row = sheet.createRow(rowCount++);
    row.createCell(0).setCellValue(new HSSFRichTextString(parent.getTermId()));
    row.createCell(1).setCellValue(activeMalaria);
    row.createCell(2).setCellValue(activeDengue);
    row.createCell(indent++).setCellValue(new HSSFRichTextString(parent.getName()));

    // If we've already exported this term, no need to repeat all of its children.  It's just here for its multiple parents.
    if(exported.add(parent.getId()))
    {
      for (Term child : parent.getAllChildTerm())
      {
        recurse(child);
      }
    }

    indent--;
  }

  /**
   * Writes the workbook to a byte array, which can then be written to the
   * filesystem or streamed across the net.
   *
   * @return
   */
  public byte[] write()
  {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    BufferedOutputStream buffer = new BufferedOutputStream(bytes);
    try
    {
      workbook.write(buffer);
      buffer.flush();
      buffer.close();
      return bytes.toByteArray();
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }
}
