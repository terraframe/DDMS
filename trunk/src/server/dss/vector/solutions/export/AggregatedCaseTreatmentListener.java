package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedCaseView;

public class AggregatedCaseTreatmentListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String TREATMENT = "Treatments - ";
  private static final String METHOD = "Treatment Methods - ";
  private static final String STOCK = "Out of Stock - ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term treatment : Term.getRootChildren(AggregatedCaseView.getCaseTreatmentsMd()))
    {
      extraColumns.add(new ExcelColumn(TREATMENT + treatment.getTermId(), treatment.getTermDisplayLabel().getValue()));
    }

    for (Term method : Term.getRootChildren(AggregatedCaseView.getCaseTreatmentMethodMd()))
    {
      extraColumns.add(new ExcelColumn(METHOD + method.getTermId(), method.getTermDisplayLabel().getValue()));
    }

    for (Term stock : Term.getRootChildren(AggregatedCaseView.getCaseStocksMd()))
    {
      extraColumns.add(new ExcelColumn(STOCK + stock.getTermId(), stock.getTermDisplayLabel().getValue()));
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
    AggregatedCaseTreatmentsExcelView aggregatedCase = (AggregatedCaseTreatmentsExcelView) instance;
    
    for (Term term : Term.getRootChildren(AggregatedCaseView.getCaseTreatmentsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TREATMENT + term.getTermId()))
        {
          aggregatedCase.addTreatment(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
    
    for (Term term : Term.getRootChildren(AggregatedCaseView.getCaseTreatmentMethodMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(METHOD + term.getTermId()))
        {
          aggregatedCase.addMethod(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
    
    for (Term term : Term.getRootChildren(AggregatedCaseView.getCaseStocksMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(STOCK + term.getTermId()))
        {
          aggregatedCase.addStock(term, ExcelUtil.getBoolean(row.getCell(column.getIndex())));
        }
      }
    }
  }
}
