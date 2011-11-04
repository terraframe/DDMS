package dss.vector.solutions.generator;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;

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
  public void configure(ImportContext currentContext, HSSFRow typeRow, HSSFRow nameRow, HSSFRow labelRow)
  {
    ContextBuilderIF builder = this.map.get(currentContext.getMdClassType());

    builder.configure(currentContext, typeRow, nameRow, labelRow);
  }
}
