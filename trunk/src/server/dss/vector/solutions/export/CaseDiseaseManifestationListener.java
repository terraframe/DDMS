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
import dss.vector.solutions.surveillance.CaseDiseaseManifestationView;

public class CaseDiseaseManifestationListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String DISEASE = "Disease - ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term category : TermRootCache.getRoots(CaseDiseaseManifestationView.getDiseaseCategoryMd()))
    {
      extraColumns.add(new ExcelColumn(DISEASE + category.getTermId(), category.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    CaseDiseaseManifestationExcelView collection = (CaseDiseaseManifestationExcelView) instance;
    
    for (Term term : TermRootCache.getRoots(CaseDiseaseManifestationView.getDiseaseCategoryMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(DISEASE + term.getTermId()))
        {
          collection.addDiseaseCategory(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
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
