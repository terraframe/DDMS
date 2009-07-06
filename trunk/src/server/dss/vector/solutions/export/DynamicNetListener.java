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

import dss.vector.solutions.intervention.monitor.Net;

public class DynamicNetListener implements ExcelExportListener, ImportListener, Reloadable
{
  private Net[] allNets;

  public DynamicNetListener()
  {
    allNets = Net.getAllLeafs();
  }
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Net net : allNets)
    {
      extraColumns.add(new ExcelColumn(net.getNetName(), net.getDisplayLabel().toString()));
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
    for (Net net : allNets)
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(net.getNetName()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          survey.addNets(net, count);
          continue;
        }
      }
    }
  }

}
