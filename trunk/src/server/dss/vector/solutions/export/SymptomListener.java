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

import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class SymptomListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String SYMPTOM = "symptom ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term term : TermRootCache.getRoots(IndividualInstance.getSymptomMd()))
    {
      extraColumns.add(new ExcelColumn(SYMPTOM + term.getTermId(), term.getTermDisplayLabel().getValue()));
    }
  }

  public void preHeader(ExcelColumn columnInfo)
  {
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    IndividualCaseExcelView individualCase = (IndividualCaseExcelView) instance;
    
    for (Term term : TermRootCache.getRoots(IndividualInstance.getSymptomMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(SYMPTOM + term.getTermId()))
        {
          if (ExcelUtil.getBoolean(row.getCell(column.getIndex())))
          {
            individualCase.addSymptom(term);
          }
        }
      }
    }
  }

}
