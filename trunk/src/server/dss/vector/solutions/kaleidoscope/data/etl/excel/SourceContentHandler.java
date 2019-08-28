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
package dss.vector.solutions.kaleidoscope.data.etl.excel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.POIXMLProperties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.runwaysdk.RunwayException;
import com.runwaysdk.business.LocalizableIF;
import com.runwaysdk.business.SmartException;
import com.runwaysdk.business.Transient;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.kaleidoscope.data.etl.ColumnType;
import dss.vector.solutions.kaleidoscope.data.etl.ConverterIF;
import dss.vector.solutions.kaleidoscope.data.etl.ProgressMonitorIF;
import dss.vector.solutions.kaleidoscope.data.etl.SourceContextIF;
import dss.vector.solutions.kaleidoscope.data.etl.SourceFieldIF;
import dss.vector.solutions.util.LocalizationFacade;

public class SourceContentHandler implements SheetHandler, Reloadable
{
  /**
   * Handler which handles the view object once they have been created.
   */
  private ConverterIF             converter;

  /**
   * View import context
   */
  private SourceContextIF         context;

  /**
   * Current sheet name
   */
  private String                  sheetName;

  /**
   * Column index-Column Name Map for the current sheet
   */
  private Map<Integer, String>    map;

  /**
   * Current row number
   */
  private int                     rowNum;

  /**
   * Current error row number
   */
  private int                     errorNum;

  /**
   * Current view
   */
  private Transient               view;

  /**
   * Decimal format used to remove trailing 0s from the long values
   */
  private DecimalFormat           nFormat;

  /**
   * Format used for parsing and formatting dateTime fields
   */
  private DateFormat              dateTimeFormat;

  /**
   * Format used for parsing and formatting dateTime fields
   */
  private DateFormat              dateFormat;

  /**
   * Handles progress reporting
   */
  private ProgressMonitorIF       monitor;

  /**
   * Workbook containing error rows
   */
  private SXSSFWorkbook           workbook;

  /**
   * Date style format
   */
  private CellStyle               style;

  private HashMap<String, Object> values;

  private boolean                 isFirstSheet;
  
  private int                     errorColumnNumber;
  
  private String                  errorForRow;

  public SourceContentHandler(ConverterIF converter, SourceContextIF context, ProgressMonitorIF monitor)
  {
    this.converter = converter;
    this.context = context;
    this.isFirstSheet = true;

    this.map = new HashMap<Integer, String>();
    this.nFormat = new DecimalFormat("###.#");

    this.dateTimeFormat = new SimpleDateFormat(ExcelDataFormatter.DATE_TIME_FORMAT);
    this.dateFormat = new SimpleDateFormat(ExcelDataFormatter.DATE_FORMAT);
    this.monitor = monitor;
  }

  @Override
  public void startSheet(String sheetName)
  {
    this.sheetName = sheetName;
    this.errorNum = 1;

    this.getWorkbook().createSheet(sheetName);
  }

  @Override
  public void endSheet()
  {
    Sheet sheet = this.getWorkbook().getSheet(this.sheetName);

    int headerNum = 0;

    if (sheet.getLastRowNum() > headerNum)
    {
      this.converter.setErrors(this.getWorkbook());
    }

    this.isFirstSheet = false;
  }

  @Override
  public void startRow(int rowNum)
  {
    if (this.isFirstSheet)
    {
      this.rowNum = rowNum;
      this.values = new HashMap<String, Object>();

      boolean isHeader = ( rowNum == 0 );

      if (!isHeader)
      {
        this.view = this.context.newView(this.sheetName);

        this.monitor.setCurrentRow(rowNum);
      }
    }
  }

  @Override
  public void endRow()
  {
    if (this.isFirstSheet)
    {
      try
      {
        if (this.view != null)
        {
          this.converter.create(this.view);

          this.view = null;
        }
      }
      catch (Exception e)
      {
        this.addErrorForRow(e);
      }
      
      boolean isHeader = ( rowNum == 0 );

      if (isHeader)
      {
        Sheet sheet = this.getWorkbook().getSheet(sheetName);

        this.writeRow(sheet, rowNum);
      }
      else if (errorForRow != null && errorForRow.length() > 0)
      {
        this.writeRowWithException(this.errorForRow);
      }
    }
  }
  
  private void addErrorForRow(Exception e)
  {
    if (errorForRow == null || errorForRow.length() == 0)
    {
      errorForRow = getLocalizedException(e);
    }
    else
    {
      errorForRow = " " + getLocalizedException(e);
    }
  }

  private void writeRowWithException(String errorMessage)
  {
    Sheet sheet = this.getWorkbook().getSheet(sheetName);

    int headerRowNum = 0;

    Row row = this.writeRow(sheet, headerRowNum + this.errorNum++);

    row.createCell(errorColumnNumber).setCellValue(errorMessage);
  }

  private Row writeRow(Sheet sheet, int rowNum)
  {
    CreationHelper helper = this.getWorkbook().getCreationHelper();

    Row row = sheet.createRow(rowNum);

    Set<Entry<String, Object>> entries = this.values.entrySet();

    for (Entry<String, Object> entry : entries)
    {
      String celRef = entry.getKey();
      Object value = entry.getValue();

      int column = new CellReference(celRef).getCol();

      Cell cell = row.createCell(column);

      if (value instanceof Double)
      {
        cell.setCellValue((Double) value);
      }
      else if (value instanceof Date)
      {
        cell.setCellValue((Date) value);
        cell.setCellStyle(this.style);
      }
      else
      {
        cell.setCellValue(helper.createRichTextString((String) value));
      }
    }

    int headerRowNum = 0;

    if (rowNum == headerRowNum)
    {
      String label = LocalizationFacade.getFromBundles("dataUploader.causeOfFailure");

      this.errorColumnNumber = row.getLastCellNum();
      
      row.createCell(this.errorColumnNumber).setCellValue(helper.createRichTextString(label));
    }

    return row;
  }

  private String setColumnName(String cellReference, String columnName)
  {
    CellReference reference = new CellReference(cellReference);
    Integer column = new Integer(reference.getCol());

    return this.map.put(column, columnName);
  }

  private String getColumnName(String cellReference)
  {
    CellReference reference = new CellReference(cellReference);
    Integer column = new Integer(reference.getCol());

    return this.map.get(column);
  }

  @Override
  public void cell(String cellReference, String contentValue, String formattedValue, ColumnType cellType)
  {
    if (this.isFirstSheet)
    {

      try
      {
        if (cellType.equals(ColumnType.FORMULA))
        {
          throw new ExcelFormulaException();
        }

        if (this.rowNum == 0)
        {
          if (! ( cellType.equals(ColumnType.TEXT) || cellType.equals(ColumnType.INLINE_STRING) ))
          {
            throw new InvalidHeaderRowException();
          }

          this.setColumnName(cellReference, formattedValue);

          // Store the original value in a temp map in case there is an error
          String label = LocalizationFacade.getFromBundles("dataUploader.causeOfFailure");

          if (!contentValue.equals(label))
          {
            this.values.put(cellReference, contentValue);
          }
        }
        else if (this.view != null)
        {
          String columnName = this.getColumnName(cellReference);
          SourceFieldIF field = this.context.getFieldByName(this.sheetName, columnName);

          if (field != null && !field.getType().equals(ColumnType.IGNORE))
          {
            String attributeName = field.getAttributeName();
            Object original = field.isNumber() ? Double.parseDouble(contentValue) : contentValue;

            if (field.getType().equals(ColumnType.LONG))
            {
              formattedValue = this.nFormat.format(Double.parseDouble(contentValue));
            }
            else if (field.getType().equals(ColumnType.DATE))
            {
              Date date = this.dateTimeFormat.parse(contentValue);

              formattedValue = this.dateFormat.format(date);

              original = date;
            }

            this.view.setValue(attributeName, formattedValue);

            // Store the original value in a temp map in case there is an error
            this.values.put(cellReference, original);
          }
        }
      }
      catch (Exception e)
      {
        if (this.rowNum == 0)
        {
          if (e instanceof InvalidHeaderRowException)
          {
            throw (InvalidHeaderRowException) e;
          }
          
          // Wrap all exceptions with information about the cell and row
          ExcelValueException exception = new ExcelValueException();
          exception.setCell(cellReference);
  
          if (e instanceof SmartException)
          {
            SmartException sme = (SmartException) e;
            exception.setMsg(sme.localize(Session.getCurrentLocale()));
          }
          else
          {
            exception.setMsg(e.getLocalizedMessage());
          }
  
          throw exception;
        }
        else
        {
          this.addErrorForRow(e);
        }
      }
    }
  }

  @Override
  public void headerFooter(String text, boolean isHeader, String tagName)
  {
  }

  public synchronized Workbook getWorkbook()
  {
    if (this.workbook == null)
    {
      this.workbook = new SXSSFWorkbook(10);
      this.style = this.workbook.createCellStyle();

      CreationHelper helper = this.workbook.getCreationHelper();
      this.style.setDataFormat(helper.createDataFormat().getFormat("MM/DD/YY"));

      XSSFWorkbook wb = this.workbook.getXSSFWorkbook();
      POIXMLProperties props = wb.getProperties();

      POIXMLProperties.CustomProperties custProp = props.getCustomProperties();
      custProp.addProperty("dataset", this.context.getId(this.sheetName));
    }

    return this.workbook;
  }

  public int getTotalRows()
  {
    return rowNum;
  }

  public int getNumberOfErrors()
  {
    return ( this.errorNum - 1 );
  }

  @Override
  public void setDatasetProperty(String dataset)
  {
    // Do nothing
  }

  public static String getLocalizedException(Throwable t)
  {
    if (t instanceof RunwayException)
    {
      ( (RunwayException) t ).setLocale(Session.getCurrentLocale());
    }

    if (t instanceof LocalizableIF)
    {
      return ( (LocalizableIF) t ).localize(Session.getCurrentLocale());
    }

    return t.getLocalizedMessage();
  }

}
