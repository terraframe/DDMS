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

import dss.vector.solutions.intervention.monitor.ControlInterventionView;
import dss.vector.solutions.ontology.Term;

public class InsecticideInterventionListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String BRAND = " - Insecticide Formulation";
  private static final String QUANTITY = " - Quantity";
  private static final String UNIT = " - Unit";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term method : Term.getRootChildren(ControlInterventionView.getInsecticideInterventionMd()))
    {
      String methodLabel = method.getTermDisplayLabel().getValue();
      String methodTerm = method.getTermId();
      extraColumns.add(new ExcelColumn(methodTerm + BRAND, methodLabel + BRAND));
      extraColumns.add(new ExcelColumn(methodTerm + QUANTITY, methodLabel + QUANTITY));
      extraColumns.add(new ExcelColumn(methodTerm + UNIT, methodLabel + UNIT));
    }
  }

  public void handleExtraColumns(Mutable instance, List<ExcelColumn> extraColumns, HSSFRow row) throws Exception
  {
    InsecticideInterventionExcelView collection = (InsecticideInterventionExcelView) instance;
    
    for (Term method : Term.getRootChildren(ControlInterventionView.getInsecticideInterventionMd()))
    {
      collection.addInterventionMethod(method);
      String methodTerm = method.getTermId();
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(methodTerm + BRAND))
        {
          String b = ExcelUtil.getString(row.getCell(column.getIndex()));
          collection.addInsecticide(b);
        }
        if (column.getAttributeName().equals(methodTerm + QUANTITY))
        {
          collection.addQuantity(ExcelUtil.getInteger(row.getCell(column.getIndex())));
        }
        if (column.getAttributeName().equals(methodTerm + UNIT))
        {
          collection.addUnit(ExcelUtil.getString(row.getCell(column.getIndex())));
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
