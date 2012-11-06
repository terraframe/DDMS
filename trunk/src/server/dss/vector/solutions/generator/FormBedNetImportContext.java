package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormBedNet;

public class FormBedNetImportContext extends FormImportContext implements Reloadable
{

  public FormBedNetImportContext(HSSFSheet sheet, String sheetName, HSSFSheet error, MdClassDAOIF mdClass)
  {
    super(sheet, sheetName, error, mdClass);
  }

  @Override
  protected Mutable getMutableForRow(HSSFRow row)
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
