package dss.vector.solutions.generator;

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormSurvey;

public abstract class HouseholdReferenceContextBuilder extends FormContextBuilder implements Reloadable
{
  private HouseholdColumn householdColumn;

  private SurveyColumn    surveyColumn;

  public HouseholdReferenceContextBuilder(MdFormDAOIF mdForm)
  {
    super(mdForm, new FormSurveyImportFilter());

    this.householdColumn = null;
    this.surveyColumn = null;
  }

  @Override
  public void configure(ImportContext currentContext, Row typeRow, Row nameRow, Row labelRow)
  {
    super.configure(currentContext, typeRow, nameRow, labelRow);

    if (this.householdColumn != null && this.surveyColumn != null)
    {
      this.householdColumn.setSurveyIndex(this.surveyColumn.getIndex());
    }
  }

  protected void buildAttributeColumn(ImportContext context, MdAttributeDAOIF mdAttribute)
  {
    if (mdAttribute instanceof MdAttributeReferenceDAOIF)
    {
      MdAttributeReferenceDAOIF mdAttributeReference = (MdAttributeReferenceDAOIF) mdAttribute;
      MdBusinessDAOIF mdBusiness = mdAttributeReference.getReferenceMdBusinessDAO();

      if (mdBusiness.definesType().equals(FormHousehold.CLASS))
      {
        this.householdColumn = new HouseholdColumn(mdAttributeReference);

        context.addExpectedColumn(this.householdColumn);
      }
      else if (mdBusiness.definesType().equals(FormSurvey.CLASS))
      {
        this.surveyColumn = new SurveyColumn(mdAttributeReference);

        context.addExpectedColumn(this.surveyColumn);
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

  protected SurveyColumn getSurveyColumn()
  {
    return surveyColumn;
  }

  protected HouseholdColumn getHouseholdColumn()
  {
    return householdColumn;
  }

}
