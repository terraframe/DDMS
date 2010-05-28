package dss.vector.solutions.export;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.io.ExcelExportListener;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.io.excel.ImportListener;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.ontology.Term;

public class PupalCollectionListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String AMOUNT = "Pupae Amount - ";
  
  @Override
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term specie : Term.getRootChildren(PupalContainerView.getPupaeAmountMd()))
    {
      extraColumns.add(new ExcelColumn(AMOUNT + specie.getTermId(), specie.getTermDisplayLabel().getValue()));
    }
  }

  @Override
  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    PupalCollectionExcelView collection = (PupalCollectionExcelView) instance;
    
    for (Term term : Term.getRootChildren(PupalContainerView.getPupaeAmountMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(AMOUNT + term.getTermId()))
        {
          collection.addPupaeAmount(term, ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
      }
    }
  }
  
  @Override
  public void preHeader(ExcelColumn columnInfo)
  {
  }
  
  @Override
  public void preWrite(HSSFWorkbook workbook)
  {
  }
}
