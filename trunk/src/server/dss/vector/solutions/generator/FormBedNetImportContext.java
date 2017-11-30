package dss.vector.solutions.generator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.form.business.FormBedNet;

public class FormBedNetImportContext extends FormImportContext implements Reloadable
{

  public FormBedNetImportContext(Sheet sheet, String sheetName, Sheet error, MdClassDAOIF mdClass, ExcelImportManager manager)
  {
    super(sheet, sheetName, error, mdClass, manager);
  }

  @Override
  protected Mutable getMutableForRow(Row row)
  {
    String oid = getCellValue(row, FormBedNet.OID);
    String surveyId = getCellValue(row, FormBedNet.SURVEY);

    if (oid != null && oid.length() > 0 && surveyId != null && surveyId.length() > 0)
    {
      FormBedNet bedNet = FormBedNet.getByBedNetId(surveyId, oid);

      if (bedNet != null)
      {
        bedNet.appLock();
        return bedNet;  
      }
    }

    return super.getMutableForRow(row);
  }

}
