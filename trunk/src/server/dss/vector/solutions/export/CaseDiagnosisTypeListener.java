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
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;

public class CaseDiagnosisTypeListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String DIAGNOSIS = "Diagnosis - ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term diagnosis : TermRootCache.getRoots(CaseDiagnosisTypeView.getDiagnosisCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(DIAGNOSIS + diagnosis.getTermId(), diagnosis.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    CaseDiagnosisTypeExcelView collection = (CaseDiagnosisTypeExcelView) instance;

    for (Term term : TermRootCache.getRoots(CaseDiagnosisTypeView.getDiagnosisCategoryMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(DIAGNOSIS + term.getTermId()))
        {
          collection.addDiagnosisType(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
  }
}
