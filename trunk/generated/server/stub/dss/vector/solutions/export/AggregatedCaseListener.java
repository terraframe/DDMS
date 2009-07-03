package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.system.metadata.MdAttribute;

import dss.vector.solutions.surveillance.CaseDiagnostic;
import dss.vector.solutions.surveillance.DiagnosticGrid;
import dss.vector.solutions.surveillance.ReferralGrid;
import dss.vector.solutions.surveillance.TreatmentGrid;
import dss.vector.solutions.surveillance.TreatmentMethodGrid;

public class AggregatedCaseListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String TREATMENT = "treatment";
  private static final String STOCK = "stock";
  private static final String METHOD = "method";
  private static final String DIAGNOSTIC = "diagnostic";
  private static final String POSITIVE = "positive";
  private static final String REFERRAL = "referral";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (TreatmentGrid grid : TreatmentGrid.getAll())
    {
      extraColumns.add(new ExcelColumn(STOCK + grid.getOptionName(), grid.getDisplayLabel().toString()));
    }
    
    for (TreatmentGrid grid : TreatmentGrid.getAll())
    {
      extraColumns.add(new ExcelColumn(TREATMENT + grid.getOptionName(), grid.getDisplayLabel().toString()));
    }
    
    for (TreatmentMethodGrid grid : TreatmentMethodGrid.getAll())
    {
      extraColumns.add(new ExcelColumn(METHOD + grid.getOptionName(), grid.getDisplayLabel().toString()));
    }
    
    for (DiagnosticGrid grid : DiagnosticGrid.getAll())
    {
      String attributeName = DIAGNOSTIC + grid.getOptionName();
      MdAttribute positive = (MdAttribute)BusinessFacade.get(CaseDiagnostic.getAmountPositiveMd());
      String positiveLabel = positive.getDisplayLabel().toString();
      String gridLabel = grid.getDisplayLabel().toString();
      
      extraColumns.add(new ExcelColumn(attributeName, gridLabel));
      extraColumns.add(new ExcelColumn(attributeName + POSITIVE, gridLabel + " " + positiveLabel));
    }
    
    for (ReferralGrid grid : ReferralGrid.getAll())
    {
      extraColumns.add(new ExcelColumn(REFERRAL + grid.getOptionName(), grid.getDisplayLabel().toString()));
    }
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
    
    for (TreatmentGrid grid : TreatmentGrid.getAll())
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(STOCK + grid.getOptionName()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Boolean inStock = cell.getBooleanCellValue();
          aggregatedCase.addStock(grid, inStock);
        }
        if (column.getAttributeName().equals(TREATMENT + grid.getOptionName()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          aggregatedCase.addTreatment(grid, count);
        }
      }
    }
    
    for (TreatmentMethodGrid grid : TreatmentMethodGrid.getAll())
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(METHOD + grid.getOptionName()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          aggregatedCase.addMethod(grid, count);
        }
      }
    }
    
    for (DiagnosticGrid grid : DiagnosticGrid.getAll())
    {
      Integer amount = null;
      Integer amountPositive = null;
      
      // Iterate over all extra columns, looking for matches to this DiagnosticGrid
      for (ExcelColumn column : extraColumns)
      {
        String diagnosticName = DIAGNOSTIC + grid.getOptionName();
        if (column.getAttributeName().equals(diagnosticName))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          amount = new Double(cell.getNumericCellValue()).intValue();
        }
        if (column.getAttributeName().equals(diagnosticName + POSITIVE))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          amountPositive = new Double(cell.getNumericCellValue()).intValue();
        }
      }
      
      // Don't add any associations if either attribute is unspecified
      if (amount!=null && amountPositive!=null)
      {
        aggregatedCase.addDiagnostic(grid, amount, amountPositive);
      }
    }
    
    for (ReferralGrid grid : ReferralGrid.getAll())
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(REFERRAL + grid.getOptionName()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          aggregatedCase.addReferral(grid, count);
        }
      }
    }
  }
}
