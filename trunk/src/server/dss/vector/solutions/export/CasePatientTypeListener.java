package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;
import dss.vector.solutions.surveillance.CasePatientTypeView;

public class CasePatientTypeListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String PATIENT = "Patient - ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term patient : TermRootCache.getRoots(CasePatientTypeView.getPatientCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(PATIENT + patient.getTermId(), patient.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
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
}
