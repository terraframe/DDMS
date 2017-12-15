package dss.vector.solutions.kaleidoscope.data.etl.excel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.util.CellReference;

import com.runwaysdk.business.SmartException;
import com.runwaysdk.business.Transient;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.kaleidoscope.data.etl.ColumnType;
import dss.vector.solutions.kaleidoscope.data.etl.ConverterIF;
import dss.vector.solutions.kaleidoscope.data.etl.SourceContextIF;
import dss.vector.solutions.kaleidoscope.data.etl.SourceFieldIF;

public class SourceContentHandler implements SheetHandler, Reloadable
{
  /**
   * Handler which handles the view object once they have been created.
   */
  private ConverterIF          converter;

  /**
   * View import context
   */
  private SourceContextIF      context;

  /**
   * Current sheet name
   */
  private String               sheetName;

  /**
   * Column index-Column Name Map for the current sheet
   */
  private Map<Integer, String> map;

  /**
   * Current row number
   */
  private int                  rowNum;

  /**
   * Current view
   */
  private Transient            view;

  /**
   * Decimal format used to remove trailing 0s from the long values
   */
  private DecimalFormat        nFormat;

  /**
   * Format used for parsing and formatting dateTime fields
   */
  private DateFormat           dateTimeFormat;

  /**
   * Format used for parsing and formatting dateTime fields
   */
  private DateFormat           dateFormat;

  public SourceContentHandler(ConverterIF converter, SourceContextIF context)
  {
    this.converter = converter;
    this.context = context;

    this.map = new HashMap<Integer, String>();
    this.nFormat = new DecimalFormat("###.#");

    this.dateTimeFormat = new SimpleDateFormat(ExcelDataFormatter.DATE_TIME_FORMAT);
    this.dateFormat = new SimpleDateFormat(ExcelDataFormatter.DATE_FORMAT);
  }

  @Override
  public void startSheet(String sheetName)
  {
    this.sheetName = sheetName;
  }

  @Override
  public void endSheet()
  {
  }

  @Override
  public void startRow(int rowNum)
  {
    this.rowNum = rowNum;

    if (rowNum != 0)
    {
      this.view = this.context.newView(this.sheetName);
    }
  }

  @Override
  public void endRow()
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
      // Wrap all exceptions with information about the cell and row
      ExcelObjectException exception = new ExcelObjectException();
      exception.setRow(new Long(this.rowNum));

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
    try
    {
      if (cellType.equals(ColumnType.FORMULA))
      {
        throw new ExcelFormulaException();
      }

      if (this.rowNum == 0)
      {
        if (!cellType.equals(ColumnType.TEXT))
        {
          throw new InvalidHeaderRowException();
        }

        this.setColumnName(cellReference, formattedValue);
      }
      else if (this.view != null)
      {
        String columnName = this.getColumnName(cellReference);
        SourceFieldIF field = this.context.getFieldByName(this.sheetName, columnName);
        String attributeName = field.getAttributeName();

        if (field.getType().equals(ColumnType.LONG))
        {
          formattedValue = this.nFormat.format(Double.parseDouble(contentValue));
        }
        else if (field.getType().equals(ColumnType.DATE))
        {
          Date date = this.dateTimeFormat.parse(contentValue);

          formattedValue = this.dateFormat.format(date);
        }

        this.view.setValue(attributeName, formattedValue);
      }
    }
    catch (Exception e)
    {
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
  }

  @Override
  public void headerFooter(String text, boolean isHeader, String tagName)
  {
  }
}
