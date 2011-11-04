package dss.vector.solutions.generator;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdFormDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormSurvey;

public class FormHouseholdContextBuilder extends FormContextBuilder implements Reloadable
{
  public FormHouseholdContextBuilder()
  {
    super((MdFormDAOIF) MdFormDAO.getMdTypeDAO(FormHousehold.FORM_TYPE), new FormSurveyImportFilter());
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(FormSurvey.CLASS))
      {
        context.addExpectedColumn(new SurveyColumn(mdAttributeReference));
      }
      else
      {
        super.buildAttributeColumn(context, mdAttribute);
      }
    }
    else
    {
      super.buildAttributeColumn(context, mdAttribute);
    }
  }

}
