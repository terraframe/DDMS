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

import dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class IndividualPremiseListener extends ExcelAdapter implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String METHOD = "Intervention Method Used - ";

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term method : TermRootCache.getRoots(IndividualPremiseVisitView.getInterventionMethodMd()))
    {
      extraColumns.add(new ExcelColumn(METHOD + method.getTermId(), method.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, Row row) throws Exception
  {
    IndividualPremiseExcelView collection = (IndividualPremiseExcelView) instance;

    for (Term term : TermRootCache.getRoots(IndividualPremiseVisitView.getInterventionMethodMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(METHOD + term.getTermId()))
        {
          collection.addInterventionMethod(term, ExcelUtil.getBoolean(row.getCell(column.getIndex())));
        }
      }
    }
  }
}
