package dss.vector.solutions.generator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormPersonQuery;

public class FormPersonImportContext extends FormImportContext implements Reloadable
{

  public FormPersonImportContext(HSSFSheet sheet, String sheetName, HSSFSheet error, MdClassDAOIF mdClass)
  {
    super(sheet, sheetName, error, mdClass);
  }

  @Override
  protected Mutable getMutableForRow(HSSFRow row)
  {
    String oid = getCellValue(row, FormPerson.OID);
    String surveyId = getCellValue(row, FormPerson.SURVEY);

    if (oid != null && oid.length() > 0 && surveyId != null && surveyId.length() > 0)
    {
      FormPersonQuery query = new FormPersonQuery(new QueryFactory());
      query.WHERE(AND.get(query.getOid().EQ(oid), query.getSurvey().getOid().EQ(surveyId)));

      OIterator<? extends FormPerson> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          FormPerson person = it.next();
          person.lock();

          return person;
        }
      }
      finally
      {
        it.close();
      }
    }

    return super.getMutableForRow(row);
  }
}
