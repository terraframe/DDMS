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

import dss.vector.solutions.intervention.monitor.AggregatedIPTView;
import dss.vector.solutions.intervention.monitor.IPTANCVisit;
import dss.vector.solutions.intervention.monitor.IPTDose;
import dss.vector.solutions.intervention.monitor.IPTPatients;
import dss.vector.solutions.intervention.monitor.IPTTreatment;
import dss.vector.solutions.ontology.Term;

public class AggregatedIPTListener implements ExcelExportListener, ImportListener, Reloadable
{
  private static final String PATIENTS = "patient ";
  private static final String ANCVISITS = "ANC visit ";
  private static final String DOSES = "dose ";
  private static final String TREATMENT = "treatment ";
  
  public void addColumns(List<ExcelColumn> extraColumns)
  {
    for (Term grid : Term.getRootChildren(AggregatedIPTView.getDisplayPatientsMd()))
    {
      String amount = MdAttribute.get(IPTPatients.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(PATIENTS + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
    
    for (Term grid : Term.getRootChildren(AggregatedIPTView.getDisplayVisitsMd()))
    {
      String amount = MdAttribute.get(IPTANCVisit.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(ANCVISITS + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
    
    for (Term grid : Term.getRootChildren(AggregatedIPTView.getDisplayDoseMd()))
    {
      String amount = MdAttribute.get(IPTDose.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(DOSES + grid.getTermId(), grid.getName().toString() + " " + amount));
    }
    
    for (Term grid : Term.getRootChildren(AggregatedIPTView.getDisplayTreatmentsMd()))
    {
      String amount = MdAttribute.get(IPTTreatment.getAmountMd().getId()).getDisplayLabel().toString();
      extraColumns.add(new ExcelColumn(TREATMENT + grid.getTermId(), grid.getName().toString() + " " + amount));
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
    AggregatedIPTExcelView aggregatedIPT = (AggregatedIPTExcelView) instance;
    
    for (Term term : Term.getRootChildren(AggregatedIPTView.getDisplayPatientsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(PATIENTS + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Integer amount = new Double(cell.getNumericCellValue()).intValue();
          aggregatedIPT.addPatient(term, amount);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(AggregatedIPTView.getDisplayVisitsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(ANCVISITS + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Integer amount = new Double(cell.getNumericCellValue()).intValue();
          aggregatedIPT.addVisit(term, amount);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(AggregatedIPTView.getDisplayDoseMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(DOSES + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Integer amount = new Double(cell.getNumericCellValue()).intValue();
          aggregatedIPT.addDose(term, amount);
        }
      }
    }
    
    for (Term term : Term.getRootChildren(AggregatedIPTView.getDisplayTreatmentsMd()))
    {
      for (ExcelColumn column : extraColumns)
      {
        if (column.getAttributeName().equals(TREATMENT + term.getTermId()))
        {
          HSSFCell cell = row.getCell(column.getIndex());
          Integer amount = new Double(cell.getNumericCellValue()).intValue();
          aggregatedIPT.addTreatment(term, amount);
        }
      }
    }
  }
}
