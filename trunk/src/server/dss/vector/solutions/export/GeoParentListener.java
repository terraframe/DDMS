package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ImportAdapter;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

public class GeoParentListener extends ImportAdapter implements ImportListener, Reloadable
{
  private String parentGeoEntityId;

  public GeoParentListener(String parentId)
  {
    this.parentGeoEntityId = parentId;
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row)
  {
    GeoEntityExcelView view = (GeoEntityExcelView) instance;
    view.setParentGeoEntityId(this.parentGeoEntityId);
  }
}
