package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

public class AggregatedCaseListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String TREATMENT = "treatment ";
  private static final String STOCK = "stock ";
  private static final String METHOD = "method ";
  private static final String DIAGNOSTIC = "diagnostic ";
  private static final String POSITIVE = "positive ";
  private static final String REFERRAL = "referral ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    
  }

  public void preHeader(ExcelColumn columnInfo)
  {
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row)
  {
    AggregatedCaseExcelView aggregatedCase = (AggregatedCaseExcelView) instance;
    
  }
}
