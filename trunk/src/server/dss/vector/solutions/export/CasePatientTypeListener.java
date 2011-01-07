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
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.CasePatientTypeView;

public class CasePatientTypeListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String PATIENT = "Patient - ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term patient : TermRootCache.getRoots(CasePatientTypeView.getPatientCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(PATIENT + patient.getTermId(), patient.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    CasePatientTypeExcelView collection = (CasePatientTypeExcelView) instance;
    
    for (Term term : TermRootCache.getRoots(CasePatientTypeView.getPatientCategoryMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(PATIENT + term.getTermId()))
        {
          collection.addPatientType(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
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
