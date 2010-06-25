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
import dss.vector.solutions.surveillance.CaseDiagnosisTypeView;
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;
import dss.vector.solutions.surveillance.CasePatientTypeView;

public class AggregatedCaseTypesListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String DIAGNOSIS = "Diagnosis - ";
  private static final String DISEASE = "Disease - ";
  private static final String PATIENT = "Patient - ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term diagnosis : Term.getRootChildren(CaseDiagnosisTypeView.getDiagnosisCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(DIAGNOSIS + diagnosis.getTermId(), diagnosis.getTermDisplayLabel().getValue()));
    }

    for (Term category : Term.getRootChildren(CaseDiseaseManifestationView.getDiseaseCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(DISEASE + category.getTermId(), category.getTermDisplayLabel().getValue()));
    }

    for (Term patient : Term.getRootChildren(CasePatientTypeView.getPatientCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(PATIENT + patient.getTermId(), patient.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    AggregatedCaseTypesExcelView collection = (AggregatedCaseTypesExcelView) instance;
    
    for (Term term : Term.getRootChildren(CaseDiagnosisTypeView.getDiagnosisCategoryMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(DIAGNOSIS + term.getTermId()))
        {
          collection.addDiagnosisType(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
    
    for (Term term : Term.getRootChildren(CaseDiseaseManifestationView.getDiseaseCategoryMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(DISEASE + term.getTermId()))
        {
          collection.addDiseaseCategory(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
    
    for (Term term : Term.getRootChildren(CasePatientTypeView.getPatientCategoryMd()))
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
