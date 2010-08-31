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

import dss.vector.solutions.RequiredAttributeProblem;
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
      String methodTerm = method.getTermId();
      String brandName = methodTerm + BRAND;
      String quantityName = methodTerm + QUANTITY;
      String unitName = methodTerm + UNIT;
      
      String methodLabel = method.getTermDisplayLabel().getValue();
      String brandLabel= methodLabel + BRAND;
      String quantityLabel= methodLabel + QUANTITY;
      String unitLabel= methodLabel + UNIT;
      
      String brand = null;
      Integer quantity = null;
      String unit = null;
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(brandName))
        {
          brand = ExcelUtil.getString(row.getCell(column.getIndex()));
        }
        if (column.getAttributeName().equals(quantityName))
        {
          quantity = ExcelUtil.getInteger(row.getCell(column.getIndex()));
        }
        if (column.getAttributeName().equals(unitName))
        {
          unit = ExcelUtil.getString(row.getCell(column.getIndex()));
        }
      }
      
//      When commented out, grid rows without all necessary data are simply ignored.
//      Uncomment to force all grids to be present.
//      if (brand==null)
//      {
//        RequiredAttributeProblem rap = new RequiredAttributeProblem();
//        rap.setAttributeName(brandName);
//        rap.setAttributeDisplayLabel(brandLabel);
//        rap.throwIt();
//      }
//      
//      if (quantity==null)
//      {
//        RequiredAttributeProblem rap = new RequiredAttributeProblem();
//        rap.setAttributeName(quantityName);
//        rap.setAttributeDisplayLabel(quantityLabel);
//        rap.throwIt();
//      }
//      
//      if (unit==null)
//      {
//        RequiredAttributeProblem rap = new RequiredAttributeProblem();
//        rap.setAttributeName(unitName);
//        rap.setAttributeDisplayLabel(unitLabel);
//        rap.throwIt();
//      }
      
      // Only add a grid row if all necessary information is present.
      if (brand!=null && unit !=null && quantity !=null)
      {
        collection.addInterventionMethod(method);
        collection.addInsecticide(brand);
        collection.addQuantity(quantity);
        collection.addUnit(unit);
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
