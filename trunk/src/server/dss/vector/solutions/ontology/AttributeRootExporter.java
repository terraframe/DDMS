package dss.vector.solutions.ontology;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.dataaccess.MdAttributeDAOIF;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdAttribute;
import com.terraframe.mojo.system.metadata.MdAttributeConcrete;
import com.terraframe.mojo.system.metadata.MdAttributeVirtual;
import com.terraframe.mojo.system.metadata.MdClass;
import com.terraframe.mojo.util.FileIO;

/**
 * 
 *
 * @author Eric Grunzke
 */
public class AttributeRootExporter
{
  private HSSFWorkbook workbook;
  private HSSFSheet sheet;
  
  @StartSession
  public static void main(String[] args) throws IOException
  {
    String fileName = "AttributeRoots.xls";
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
    
    AttributeRootExporter exporter = new AttributeRootExporter();
    exporter.exportTemplate();
    FileIO.write(file, exporter.write());
  }
  
  public AttributeRootExporter()
  {
    workbook = new HSSFWorkbook();
    
    sheet = workbook.createSheet();
    HSSFRow header = sheet.createRow(0);

    header.createCell(0).setCellValue(new HSSFRichTextString("Key"));
    header.createCell(1).setCellValue(new HSSFRichTextString("Class"));
    header.createCell(2).setCellValue(new HSSFRichTextString("Attribute"));
    header.createCell(3).setCellValue(new HSSFRichTextString("Default"));    
  }
  
  public void exportTemplate()
  {
    BrowserFieldQuery query = new BrowserFieldQuery(new QueryFactory());
    OIterator<? extends BrowserField> iterator = query.getIterator();
    
    int rowCount = 1;
    short maxCellCount = 0;
    for (BrowserField field : iterator)
    {
      MdAttribute mdAttribute = field.getMdAttribute();
      MdClass mdClass = mdAttribute.getAllDefiningClass().next();
      
      String attributeLabel = mdAttribute.getDisplayLabel().getDefaultValue();
      if (attributeLabel.length()==0 && mdAttribute instanceof MdAttributeVirtual)
      {
        MdAttributeVirtual virtual = (MdAttributeVirtual)mdAttribute;
        MdAttributeConcrete concrete = virtual.getAllConcreteAttribute().next();
        attributeLabel = concrete.getDisplayLabel().getDefaultValue();
      }
      
      HSSFRow row = sheet.createRow(rowCount++);
      
      int cellCount = 0;
      row.createCell(cellCount++).setCellValue(new HSSFRichTextString(mdAttribute.getKey()));
      row.createCell(cellCount++).setCellValue(new HSSFRichTextString(mdClass.getDisplayLabel().getDefaultValue()));
      row.createCell(cellCount++).setCellValue(new HSSFRichTextString(attributeLabel));
      
      MdAttributeDAOIF mdAttributeDAO = (MdAttributeDAOIF) BusinessFacade.getEntityDAO(mdAttribute);
      String defaultTermId = mdAttributeDAO.getMdAttributeConcrete().getDefaultValue();
      String defaultTermValue = new String();
      if (defaultTermId.length()>0)
      {
        defaultTermValue = Term.get(defaultTermId).getTermId();
      }
      row.createCell(cellCount++).setCellValue(new HSSFRichTextString(defaultTermValue));
      
      for (BrowserRoot root : field.getAllroot())
      {
        row.createCell(cellCount++).setCellValue(new HSSFRichTextString(root.getTerm().getTermId()));
      }
      if (cellCount>maxCellCount)
      {
        maxCellCount = (short)cellCount;
      }
    }
    
    for (short s=0; s<maxCellCount; s++)
    {
      sheet.autoSizeColumn(s);
    }
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
