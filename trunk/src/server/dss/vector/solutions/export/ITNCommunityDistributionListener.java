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
import com.terraframe.mojo.system.metadata.MdAttribute;

import dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView;
import dss.vector.solutions.intervention.monitor.ITNCommunityNet;
import dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup;
import dss.vector.solutions.ontology.Term;

public class ITNCommunityDistributionListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String TARGETGROUPS = "Target group ";
  private static final String ITNTYPE = "ITN type ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term grid : Term.getRootChildren(ITNCommunityDistributionView.getDisplayTargetGroupsMd()))
    {
      String amount = MdAttribute.get(ITNCommunityTargetGroup.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(TARGETGROUPS + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
    
    for (Term grid : Term.getRootChildren(ITNCommunityDistributionView.getDisplayNetsMd()))
    {
      String amount = MdAttribute.get(ITNCommunityNet.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(ITNTYPE + grid.getTermId(), grid.getName().toString() + " " + amount));
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
    ITNCommunityExcelView community = (ITNCommunityExcelView) instance;
    
    for (Term term : Term.getRootChildren(ITNCommunityDistributionView.getDisplayTargetGroupsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TARGETGROUPS + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Integer amount = new Double(cell.getNumericCellValue()).intValue();
          community.addTargetGroup(term, amount);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(ITNCommunityDistributionView.getDisplayNetsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(ITNTYPE + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Integer amount = new Double(cell.getNumericCellValue()).intValue();
          community.addITNType(term, amount);
        }
      }
    }
  }
}
