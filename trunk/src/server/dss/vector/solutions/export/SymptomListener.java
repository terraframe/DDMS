package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ExcelUtil;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;

public class SymptomListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String SYMPTOM = "symptom ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term term : Term.getRootChildren(IndividualInstance.getSymptomMd()))
    {
      extraColumns.add(new ExcelColumn(SYMPTOM + term.getTermId(), term.getName().toString()));
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
    
    for (Term term : Term.getRootChildren(IndividualInstance.getSymptomMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(SYMPTOM + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Boolean inStock = ExcelUtil.getBoolean(cell);
          individualCase.addSymptom(term, inStock);
        }
      }
    }
  }

}
