package dss.vector.solutions.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.generation.loader.Reloadable;

public class ContextBuilderFacade implements ContextBuilderIF, Reloadable
{
  private Map<String, ContextBuilderIF> map;

  public ContextBuilderFacade()
  {
    this.map = new HashMap<String, ContextBuilderIF>();
  }

  public void add(String contextType, ContextBuilderIF builder)
  {
    this.map.put(contextType, builder);
  }

  @Override
  public ImportContext createContext(HSSFSheet sheet, String sheetName, HSSFWorkbook errorWorkbook, String type)
  {
    ContextBuilderIF builder = this.map.get(type);

    if (builder != null)
    {
      return builder.createContext(sheet, sheetName, errorWorkbook, type);
    }
    else
    {
      UnsupportedImportTypeException e = new UnsupportedImportTypeException();
      e.setClassType(type);
      e.apply();

      throw e;
    }
  }

  @Override
  public void configure(ImportContext context, HSSFRow typeRow, HSSFRow nameRow, HSSFRow labelRow)
  {
    ContextBuilderIF builder = this.map.get(context.getMdClassType());

    if (builder != null)
    {
      builder.configure(context, typeRow, nameRow, labelRow);
    }
    else
    {
      UnsupportedImportTypeException e = new UnsupportedImportTypeException();
      e.setClassType(context.getMdClassType());
      e.apply();

      throw e;
    }

  }
}
