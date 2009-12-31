package dss.vector.solutions.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.terraframe.mojo.business.Mutable;
import com.terraframe.mojo.dataaccess.io.ExcelExportListener;
import com.terraframe.mojo.dataaccess.io.excel.ExcelColumn;
import com.terraframe.mojo.dataaccess.io.excel.ExcelUtil;
import com.terraframe.mojo.dataaccess.io.excel.ImportListener;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.system.metadata.MdAttribute;

import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.CaseDiagnostic;
import dss.vector.solutions.surveillance.CaseReferral;
import dss.vector.solutions.surveillance.CaseTreatment;
import dss.vector.solutions.surveillance.CaseTreatmentMethod;
import dss.vector.solutions.surveillance.CaseTreatmentStock;
import dss.vector.solutions.surveillance.ChildCaseView;

public class AggregatedCaseListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String TREATMENT = "treatment ";
  private static final String STOCK = "stock ";
  private static final String METHOD = "method ";
  private static final String DIAGNOSTIC = "diagnostic ";
  private static final String POSITIVE = "positive ";
  private static final String REFERRAL = "referral ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    
    for (Term grid : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      String outOfStock = MdAttribute.get(CaseTreatmentStock.getOutOfStockMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(STOCK + grid.getTermId(), grid.getName().toString() + " " + outOfStock));
    }
    
    for (Term grid : Term.getRootChildren(ChildCaseView.getCaseTreatmentsMd()))
    {
      String amount = MdAttribute.get(CaseTreatment.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(TREATMENT + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
    
    for (Term grid : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      String amount = MdAttribute.get(CaseTreatmentMethod.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(METHOD + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
    
    for (Term grid : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      String attributeName = DIAGNOSTIC + grid.getTermId();
      String total = MdAttribute.get(CaseDiagnostic.getAmountMd().getId()).getDisplayLabel().toString();
      String positive = MdAttribute.get(CaseDiagnostic.getAmountPositiveMd().getId()).getDisplayLabel().toString();
      String gridLabel = grid.getName().toString();
      
      extraColumns.add(new ExcelColumn(attributeName, gridLabel + " " + total));
      extraColumns.add(new ExcelColumn(attributeName + POSITIVE, gridLabel + " " + positive));
    }
    
    for (Term grid : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      String amount = MdAttribute.get(CaseReferral.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(REFERRAL + grid.getTermId(), grid.getName().toString() + " " + amount));
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
    AggregatedCaseExcelView aggregatedCase = (AggregatedCaseExcelView) instance;
    
    for (Term term : Term.getRootChildren(ChildCaseView.getCaseStocksMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(STOCK + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Boolean inStock = ExcelUtil.getBoolean(cell);
          aggregatedCase.addStock(term, inStock);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(ChildCaseView.getCaseTreatmentsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TREATMENT + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          aggregatedCase.addTreatment(term, count);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(ChildCaseView.getCaseTreatmentMethodMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(METHOD + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          aggregatedCase.addMethod(term, count);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(ChildCaseView.getCaseDiagnosticMd()))
    {
      Integer amount = null;
      Integer amountPositive = null;
      
      // Iterate over all extra columns, looking for matches to this DiagnosticGrid
      for (ExcelColumn column : extraColumns)
      {
        String diagnosticName = DIAGNOSTIC + term.getTermId();
        if (column.getAttributeName().equals(diagnosticName))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          amount = new Double(cell.getNumericCellValue()).intValue();
        }
        if (column.getAttributeName().equals(diagnosticName + POSITIVE))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          amountPositive = new Double(cell.getNumericCellValue()).intValue();
        }
      }
      
      // Don't add any associations if either attribute is unspecified
      if (amount!=null && amountPositive!=null)
      {
        aggregatedCase.addDiagnostic(term, amount, amountPositive);
      }
    }
    
    for (Term term : Term.getRootChildren(ChildCaseView.getCaseReferralsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(REFERRAL + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          int count = new Double(cell.getNumericCellValue()).intValue();
          aggregatedCase.addReferral(term, count);
        }
      }
    }
  }
}
