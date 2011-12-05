package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormHousehold;

public class FormHouseholdImportContext extends FormImportContext implements Reloadable
{
  public FormHouseholdImportContext(HSSFSheet sheet, String sheetName, HSSFSheet error, MdClassDAOIF mdClass)
  {
    super(sheet, sheetName, error, mdClass);
  }

  @Override
  protected Mutable getMutableForRow(HSSFRow row)
  {
    String oid = getCellValue(row, FormHousehold.OID);
    String surveyId = getCellValue(row, FormHousehold.SURVEY);

    if (oid != null && oid.length() > 0 && surveyId != null && surveyId.length() > 0)
    {
      FormHousehold household = FormHousehold.getByHouseholdId(surveyId, oid);

      if (household != null)
      {
        return household;
      }
    }

    return super.getMutableForRow(row);
  }
}
