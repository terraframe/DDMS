/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.generator;

import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.MdFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.form.business.FormSurvey;

public abstract class HouseholdReferenceContextBuilder extends FormContextBuilder implements Reloadable
{
  private HouseholdColumn householdColumn;

  private SurveyColumn    surveyColumn;

  public HouseholdReferenceContextBuilder(MdFormDAOIF mdForm, ExcelImportManager manager)
  {
    super(mdForm, new FormSurveyImportFilter(), manager);

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
