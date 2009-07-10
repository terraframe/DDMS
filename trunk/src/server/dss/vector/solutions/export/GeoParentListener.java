package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.Reloadable;

public class GeoParentListener implements ImportListener, Reloadable
{
  private String parentGeoEntityId;
  
  public GeoParentListener(String parentId)
  {
    this.parentGeoEntityId = parentId;
  }
  
  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row)
  {
    GeoEntityExcelView view = (GeoEntityExcelView)instance;
    view.setParentGeoEntityId(this.parentGeoEntityId);
  }
}
