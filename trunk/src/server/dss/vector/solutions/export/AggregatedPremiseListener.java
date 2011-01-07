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

import dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class AggregatedPremiseListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String METHOD = "Intervention Method Count - ";
  private static final String REASON = "Non Treatement Reason Count - ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term method : TermRootCache.getRoots(AggregatedPremiseVisitView.getInterventionMethodMd()))
    {
      extraColumns.add(new ExcelColumn(METHOD + method.getTermId(), method.getTermDisplayLabel().getValue()));
    }

    for (Term method : TermRootCache.getRoots(AggregatedPremiseVisitView.getNonTreatmentReasonMd()))
    {
      extraColumns.add(new ExcelColumn(REASON + method.getTermId(), method.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    AggregatedPremiseExcelView collection = (AggregatedPremiseExcelView) instance;
    
    for (Term term : TermRootCache.getRoots(AggregatedPremiseVisitView.getInterventionMethodMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(METHOD + term.getTermId()))
        {
          collection.addInterventionMethod(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
    
    for (Term term : TermRootCache.getRoots(AggregatedPremiseVisitView.getNonTreatmentReasonMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(REASON + term.getTermId()))
        {
          collection.addNonTreatmentReason(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
  }
  
  public void preHeader(ExcelColumn columnInfo)
  {
  }
  
  public void preWrite(HSSFWorkbook workbook)
  {
  }
}
