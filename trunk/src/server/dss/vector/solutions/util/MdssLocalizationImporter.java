package dss.vector.solutions.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.terraframe.mojo.SystemException;
import com.terraframe.mojo.constants.MdLocalizableInfo;
import com.terraframe.mojo.constants.SupportedLocaleInfo;
import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.dataaccess.io.FileReadException;
import com.terraframe.mojo.dataaccess.io.FileWriteException;
import com.terraframe.mojo.dataaccess.metadata.MdLocalizableDAO;
import com.terraframe.mojo.dataaccess.metadata.SupportedLocaleDAO;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdAttribute;
import com.terraframe.mojo.system.metadata.MdLocalizable;
import com.terraframe.mojo.system.metadata.MdType;
import com.terraframe.mojo.system.metadata.MetaData;
import com.terraframe.mojo.system.metadata.MetaDataDisplayLabel;
import com.terraframe.mojo.util.FileIO;

public class MdssLocalizationImporter
{
  private HSSFSheet customSheet;
  private HSSFSheet clientSheet;
  private HSSFSheet serverSheet;
  private HSSFSheet commonSheet;
  private HSSFSheet labelSheet;
  private HSSFSheet propertySheet;
  private HSSFSheet controlPanelSheet;
  private List<Locale> locales;
  
  @StartSession
  public static void main(String[] args) throws FileNotFoundException
  {
    long start = System.currentTimeMillis();
    
    MdssLocalizationImporter mli = new MdssLocalizationImporter();
    mli.read(new FileInputStream("testLoc.xls"));

    long stop = System.currentTimeMillis();
    System.out.println("Execution time: " + ( stop - start ) / 1000.0 + " seconds");
  }
  
  public MdssLocalizationImporter()
  {
    locales = new LinkedList<Locale>();
  }
  
  @StartSession
  public void read(InputStream stream)
  {
    openStream(stream);
    
    parseLocales();
    
    updateProperties("MDSS", propertySheet);
    updateProperties("serverExceptions", serverSheet);
    updateProperties("commonExceptions", commonSheet);
    updateProperties("clientExceptions", clientSheet);
    updateProperties("MdssControlPanel", controlPanelSheet);
    updateExceptions();
    updateLabels();
  }

  @SuppressWarnings("unchecked")
  private void parseLocales()
  {
    HSSFRow row = propertySheet.getRow(0);
    Iterator<HSSFCell> cellIterator = row.cellIterator();
    cellIterator.next();
    while (cellIterator.hasNext())
    {
      HSSFCell cell = cellIterator.next();
      locales.add(new Locale(cell.getRichStringCellValue().getString()));
    }
  }

  @SuppressWarnings("unchecked")
  private void updateProperties(String bundle, HSSFSheet sheet)
  {
    File dir = null;
    try
    {
      dir = FileIO.getDirectory(bundle + ".properties");
    }
    catch (URISyntaxException e)
    {
      e.printStackTrace();
    }
    
    int c = 0;
    for (Locale l : locales)
    {
      c++;
      String data = new String();
      Iterator<HSSFRow> rowIterator = sheet.rowIterator();
      rowIterator.next();
      while(rowIterator.hasNext())
      {
        HSSFRow row = rowIterator.next();
        String key = getStringValue(row.getCell(0));
        if (key==null)
        {
          continue;
        }
        
        HSSFCell cell = row.getCell(c);
        if (cell==null)
        {
          continue;
        }
        
        data += key + '=' + getStringValue(cell) + '\n';
      }
      
      String suffix = l.toString();
      if (suffix.equals(Locale.ENGLISH.toString()))
      {
        suffix = new String();
      }
      else
      {
        suffix = '_' + suffix;
      }
      
      File file = new File(dir, bundle + suffix + ".properties");
      try
      {
        FileIO.write(file, data);
      }
      catch (IOException e)
      {
        throw new FileWriteException(file, e);
      }
    }
  }

  private String getStringValue(HSSFCell cell)
  {
    if (cell==null)
    {
      return null;
    }
    else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
    {
      return null;
    }
    else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
    {
      return (new BigDecimal(cell.getNumericCellValue())).toString();
    }
    else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN)
    {
      return new Boolean(cell.getBooleanCellValue()).toString();
    }
    else
    {
      return cell.getRichStringCellValue().getString().trim();
    }
  }
  
  @SuppressWarnings("unchecked")
  @Transaction
  private void updateLabels()
  {
    Iterator<HSSFRow> rowIterator = labelSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext())
    {
      HSSFRow row = rowIterator.next();
      readLabelRow(row);
    }
  }

  private void readLabelRow(HSSFRow row)
  {
    String key = getStringValue(row.getCell(0));
    if (key==null)
    {
      return;
    }
    
    MetaData metadata = MetaData.getByKey(key);
    metadata.lock();
    
    MetaDataDisplayLabel label = null;
    if (metadata instanceof MdType)
    {
      label = ((MdType) metadata).getDisplayLabel();
    }
    if (metadata instanceof MdAttribute)
    {
      label = ((MdAttribute) metadata).getDisplayLabel();
    }
    
    int c=1;
    for (Locale l : locales)
    {
      String value = getStringValue(row.getCell(c++));
      if (value!=null)
      {
        if (l.equals(Locale.ENGLISH))
        {
          label.setDefaultLocale(value);
        }
        else
        {
          label.setValue(l, value);
        }
      }
    }
    metadata.apply();
  }
  
  @Transaction
  private void ensureInstalledLocales()
  {
    for (Locale l : locales)
    {
      String localeString = l.toString();
      
      if (localeString.equals(Locale.ENGLISH.toString()))
      {
        continue;
      }
      
      try
      {
        SupportedLocaleDAO.getEnumeration(SupportedLocaleInfo.CLASS, localeString);
      }
      catch (ProgrammingErrorException e)
      {
        SupportedLocaleDAO locale = SupportedLocaleDAO.newInstance();
        locale.setValue(SupportedLocaleInfo.NAME, localeString);
        locale.setValue(SupportedLocaleInfo.DISPLAY_LABEL, l.getDisplayName(Locale.ENGLISH));
        locale.setValue(SupportedLocaleInfo.LOCALE_LABEL, l.getDisplayName(Locale.ENGLISH));
        locale.apply();
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void updateExceptions()
  {
    Iterator<HSSFRow> rowIterator = customSheet.rowIterator();
    rowIterator.next();
    while (rowIterator.hasNext())
    {
      HSSFRow row = rowIterator.next();
      
      String key = getStringValue(row.getCell(0));
      if (key==null)
      {
        continue;
      }
      
      MdLocalizableDAO dao = (MdLocalizableDAO)MdLocalizableDAO.get(MdLocalizable.CLASS, key);
      
      String xmlString;
      File xmlFile = dao.getXmlFile();
      if (xmlFile.exists())
      {
        try
        {
          xmlString = FileIO.readString(xmlFile);
        }
        catch (IOException e)
        {
          throw new FileReadException(xmlFile, e);
        }
      }
      else
      {
        xmlString = dao.getValue(MdLocalizableInfo.MESSAGES);
      }
      
      int start = xmlString.indexOf("<locale");
      int end = xmlString.lastIndexOf("/locale>");
      String prefix = xmlString.substring(0, start).trim();
      String suffix = "\n" + xmlString.substring(end + 8).trim();
      
      int c = 1;
      String middle = new String();
      for (Locale l : locales)
      {
        String value = getStringValue(row.getCell(c++));
        if (value!=null)
        {
          middle += "\n  <locale language=\"" + l.toString() + "\">" + value + "</locale>";
        }
      }
      
      try
      {
        FileIO.write(xmlFile, prefix + middle + suffix);
      }
      catch (IOException e)
      {
        throw new FileWriteException(xmlFile, e);
      }
    }
  }

  /**
   * Opens the stream and parses the sheet based on name
   *
   * @param stream
   */
  private void openStream(InputStream stream)
  {
    try
    {
      POIFSFileSystem fileSystem = new POIFSFileSystem(stream);
      HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
      customSheet = workbook.getSheet(MdssLocalizationExporter.MD_EXCEPTIONS);
      clientSheet = workbook.getSheet(MdssLocalizationExporter.CLIENT_EXCEPTIONS);
      serverSheet = workbook.getSheet(MdssLocalizationExporter.SERVER_EXCEPTIONS);
      commonSheet = workbook.getSheet(MdssLocalizationExporter.COMMON_EXCEPTIONS);
      labelSheet = workbook.getSheet(MdssLocalizationExporter.DISPLAY_LABELS);
      propertySheet = workbook.getSheet(MdssLocalizationExporter.MDSS_PROPERTIES);
      controlPanelSheet = workbook.getSheet(MdssLocalizationExporter.CONTROL_PANEL_PROPERTIES);
    }
    catch (IOException e)
    {
      throw new SystemException(e);
    }
  }

}
