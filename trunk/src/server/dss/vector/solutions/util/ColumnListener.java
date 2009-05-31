package dss.vector.solutions.util;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.ExcelExporter.ColumnInfo;
import com.terraframe.mojo.generation.loader.Reloadable;

public class ColumnListener implements ExcelExportListener, Reloadable
{
  private Map<String, String> map;

  public ColumnListener(Map<String, String> map)
  {
    this.map = map;
  }
  
  public void preHeader(ColumnInfo columnInfo)
  {
    String key = columnInfo.getMdAttribute().getId();

    if(map.containsKey(key))
    {
      String displayLabel = map.get(key);

      columnInfo.setDisplayLabel(displayLabel);
    }
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }    
}