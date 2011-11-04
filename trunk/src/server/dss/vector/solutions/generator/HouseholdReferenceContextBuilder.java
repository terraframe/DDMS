package dss.vector.solutions.generator;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormHousehold;

public class HouseholdReferenceContextBuilder extends FormContextBuilder implements Reloadable
{
  public HouseholdReferenceContextBuilder(MdFormDAOIF mdForm)
  {
    super(mdForm, new FormSurveyImportFilter());
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(FormHousehold.CLASS))
      {
        context.addExpectedColumn(new HouseholdColumn(mdAttributeReference));
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
