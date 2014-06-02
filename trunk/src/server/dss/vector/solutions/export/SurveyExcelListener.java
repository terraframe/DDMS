package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelAdapter;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.SurveyedPersonView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class SurveyExcelListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String LOCATIONS  = "Treatment Sought At ";

  private static final String TREATMENTS = "Treatment Taken ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term location : TermRootCache.getRoots(SurveyedPersonView.getDisplayLocationsMd()))
    {
      extraColumns.add(new ExcelColumn(LOCATIONS + location.getTermId(), location.getTermDisplayLabel().getValue()));
    }

    for (Term treatment : TermRootCache.getRoots(SurveyedPersonView.getDisplayTreatmentsMd()))
    {
      extraColumns.add(new ExcelColumn(TREATMENTS + treatment.getTermId(), treatment.getTermDisplayLabel().getValue()));
    }
  }

  public void preHeader(ExcelColumn columnInfo)
  {
  }

  public void preWrite(HSSFWorkbook workbook)
  {
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row)
  {
    SurveyExcelView survey = (SurveyExcelView) instance;

    for (Term term : TermRootCache.getRoots(SurveyedPersonView.getDisplayLocationsMd()))
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

    for (Term term : TermRootCache.getRoots(SurveyedPersonView.getDisplayTreatmentsMd()))
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
