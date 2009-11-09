package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.intervention.monitor.HouseholdView;
import dss.vector.solutions.ontology.Term;

public class DynamicNetListener implements ExcelExportListener, ImportListener, Reloadable
{
  private Term[] allNets;

  public DynamicNetListener()
  {
    allNets = Term.getRootChildren(HouseholdView.getDisplayNetsMd());
  }

  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term net : allNets)
    {
      extraColumns.add(new ExcelColumn(net.getName(), net.getName()));
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
    for (Term net : allNets)
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(net.getName()))
        {
          HSSFCell cell = row.getCell(column.getIndex());

          // Ensure that the net cells have been defined.
          if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
          {
            int count = new Double(cell.getNumericCellValue()).intValue();
            survey.addNets(net, count);
            continue;
          }
        }
      }
    }
  }

}
