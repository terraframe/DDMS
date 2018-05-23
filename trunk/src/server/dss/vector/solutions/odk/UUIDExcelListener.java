package dss.vector.solutions.odk;

import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.generation.loader.Reloadable;

public class UUIDExcelListener implements Reloadable, ExcelExportListener
{

  @Override
  public void preHeader(ExcelColumn column)
  {
  }

  @Override
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    extraColumns.add(new UUIDExcelColum());
  }

}
