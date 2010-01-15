package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ExcelUtil;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.ontology.Term;

public class SurveyExcelListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String LOCATIONS = "Treatment Sought At ";
  private static final String TREATMENTS = "Treatment Taken ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term location : Term.getRootChildren(SurveyedPersonView.getDisplayLocationsMd()))
    {
      extraColumns.add(new ExcelColumn(LOCATIONS + location.getTermId(), location.getDisplay()));
    }
    
    for (Term treatment : Term.getRootChildren(SurveyedPersonView.getDisplayTreatmentsMd()))
    {
      extraColumns.add(new ExcelColumn(TREATMENTS + treatment.getTermId(), treatment.getDisplay()));
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
    SurveyExcelView survey = (SurveyExcelView) instance;
    
    for (Term term : Term.getRootChildren(SurveyedPersonView.getDisplayLocationsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(LOCATIONS + term.getTermId()))
        {
          if (ExcelUtil.getBoolean(row.getCell(column.getIndex())))
          {
            survey.addLocation(term);
          }
        }
      }
    }
    
    for (Term term : Term.getRootChildren(SurveyedPersonView.getDisplayTreatmentsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TREATMENTS + term.getTermId()))
        {
          if (ExcelUtil.getBoolean(row.getCell(column.getIndex())))
          {
            survey.addTreatment(term);
          }
        }
      }
    }
  }
}
