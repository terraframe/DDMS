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

import dss.vector.solutions.entomology.PupalContainerView;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermRootCache;

public class PupalCollectionListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String AMOUNT = "Pupae Amount - ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term specie : TermRootCache.getRoots(PupalContainerView.getPupaeAmountMd()))
    {
      extraColumns.add(new ExcelColumn(AMOUNT + specie.getTermId(), specie.getTermDisplayLabel().getValue()));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    PupalCollectionExcelView collection = (PupalCollectionExcelView) instance;
    
    for (Term term : TermRootCache.getRoots(PupalContainerView.getPupaeAmountMd()))
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
  
  public void preHeader(ExcelColumn columnInfo)
  {
  }
  
  public void preWrite(HSSFWorkbook workbook)
  {
  }
}
