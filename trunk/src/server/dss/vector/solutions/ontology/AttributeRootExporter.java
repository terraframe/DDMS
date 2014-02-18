package dss.vector.solutions.ontology;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.SystemException;
import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeVirtual;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.util.FileIO;

import dss.vector.solutions.general.Disease;

/**
 *
 *
 * @author Eric Grunzke
 */
public class AttributeRootExporter
{
  private HSSFWorkbook workbook;

  private HSSFSheet    sheet;

  private Disease[]    allDiseases;

  private int rowCount;

  private short maxCellCount;

  @Request
  public static void main(String[] args) throws IOException
  {
    String fileName = "AttributeRoots.xls";
    File file = new File(fileName);
    if (args.length == 0)
    {
      System.out.println("No file name specified.  Using default location: " + file.getAbsoluteFile());
    }
    else
    {
      fileName = args[0];
      file = new File(fileName);
    }

    AttributeRootExporter exporter = new AttributeRootExporter();
    exporter.exportTemplate();
    FileIO.write(file, exporter.write());
  }

  public AttributeRootExporter()
  {
    workbook = new HSSFWorkbook();
    sheet = workbook.createSheet();
    allDiseases = Disease.getAllDiseases();
    rowCount = 0;
    maxCellCount = 0;
  }

  public void exportTemplate()
  {
    BrowserFieldQuery query = new BrowserFieldQuery(new QueryFactory());
    query.ORDER_BY(query.getKeyName(), SortOrder.ASC);

    OIterator<? extends BrowserField> iterator = query.getIterator();

    HSSFRow header = sheet.createRow(rowCount++);
    header.createCell(0).setCellValue(new HSSFRichTextString("Key"));
    header.createCell(1).setCellValue(new HSSFRichTextString("Class"));
    header.createCell(2).setCellValue(new HSSFRichTextString("Attribute"));
    header.createCell(3).setCellValue(new HSSFRichTextString("Default"));
    header.createCell(4).setCellValue(new HSSFRichTextString("Disease"));

    for (BrowserField field : iterator)
    {
      int totalRoots = 0;
      List<ExportData> commonRoots = ExportData.getCommonRoots(field);
      writeRootRow(field, commonRoots, null);
      totalRoots += commonRoots.size();

      for (Disease disease : allDiseases)
      {
        List<ExportData> rootsByDisease = ExportData.getRootsByDisease(field, disease, commonRoots);
        writeRootRow(field, rootsByDisease, disease);
        totalRoots += rootsByDisease.size();
      }

      // This means that there is a field, but no roots have been assigned to it yet.
      // We still want to export a line for it, though, so that roots can be added.
      if (totalRoots==0)
      {
        writeFieldInfo(field, null);
      }
    }

    for (short s = 4; s < maxCellCount; s++)
    {
      if (s>4 && s%2==1)
      {
        createAndSet(header, s, "Root ID #" + (s-3)/2);
      }
      if (s>4 && s%2==0)
      {
        createAndSet(header, s, "Selectable #" + (s-3)/2);
      }
    }

    for (short s = 0; s < maxCellCount; s++)
    {
      sheet.autoSizeColumn(s);
    }
  }

  private void writeRootRow(BrowserField field, List<ExportData> roots, Disease disease)
  {
    if (roots.size()==0)
    {
      return;
    }

    HSSFRow row = writeFieldInfo(field, disease);

    int cellCount = 5;
    for (ExportData root : roots)
    {
      createAndSet(row, cellCount++, root.getTermId());
      row.createCell(cellCount++).setCellValue(root.getSelectable());
    }

    if (cellCount > maxCellCount)
    {
      maxCellCount = (short) cellCount;
    }
  }

  private HSSFRow writeFieldInfo(BrowserField field, Disease disease)
  {
    MdAttribute mdAttribute = field.getMdAttribute();
    MdClass mdClass = mdAttribute.getAllDefiningClass().next();
    String attributeLabel = mdAttribute.getDisplayLabel().getDefaultValue();

    if (attributeLabel.length() == 0 && mdAttribute instanceof MdAttributeVirtual)
    {
      MdAttributeVirtual virtual = (MdAttributeVirtual) mdAttribute;
      MdAttributeConcrete concrete = virtual.getAllConcreteAttribute().next();
      attributeLabel = concrete.getDisplayLabel().getDefaultValue();
    }

    // Create a row for this root-default-disease triple
    HSSFRow row = sheet.createRow(rowCount++);

    int c = 0;
    createAndSet(row, c++, mdAttribute.getKey());
    createAndSet(row, c++, mdClass.getDisplayLabel().getDefaultValue());
    createAndSet(row, c++, attributeLabel);

    MdAttributeDAOIF mdAttributeDAO = (MdAttributeDAOIF) BusinessFacade.getEntityDAO(mdAttribute);
    String defaultTermId = mdAttributeDAO.getMdAttributeConcrete().getDefaultValue();
    String defaultTermValue = new String();

    if (defaultTermId.length() > 0)
    {
      defaultTermValue = Term.get(defaultTermId).getTermId();
    }
    createAndSet(row, c++, defaultTermValue);

    if (disease!=null)
    {
      createAndSet(row, c, disease.getKey());
    }
    return row;
  }

  private void createAndSet(HSSFRow row, int cellNum, String value)
  {
    row.createCell(cellNum).setCellValue(new HSSFRichTextString(value));
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
