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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;

import dss.vector.solutions.form.business.FormHousehold;
import dss.vector.solutions.general.UnknownValueException;

public class HouseholdColumn extends AttributeColumn implements Reloadable
{
  /**
   * The index of the survey column
   */
  private int surveyIndex;

  public HouseholdColumn(MdAttributeReferenceDAOIF mdAttribute)
  {
    super(mdAttribute);

    this.surveyIndex = 0;
  }

  public void setSurveyIndex(int surveyIndex)
  {
    this.surveyIndex = surveyIndex;
  }

  public int getSurveyIndex()
  {
    return surveyIndex;
  }

  @Override
  public String javaType()
  {
    return FormHousehold.class.getName();
  }

  @Override
  public Object getValue(Cell cell) throws Exception
  {
    Row row = cell.getSheet().getRow(cell.getRowIndex());

    String surveyId = ExcelUtil.getString(row.getCell(this.surveyIndex));
    String householdId = ExcelUtil.getString(cell);

    FormHousehold household = FormHousehold.getByHouseholdId(surveyId, householdId);

    if (household != null)
    {
      return household;
    }
    else
    {
      String msg = "Unknown household with householdId [" + householdId + "] in the survey [" + surveyId + "]";

      MdBusinessDAOIF mdBusiness = MdBusinessDAO.getMdBusinessDAO(FormHousehold.CLASS);
      String typeLabel = mdBusiness.getDisplayLabel(Session.getCurrentLocale());
      String attributeLabel = mdBusiness.definesAttribute(FormHousehold.OID).getDisplayLabel(Session.getCurrentLocale());

      UnknownValueException ex = new UnknownValueException(msg);
      ex.setAttributeLabel(attributeLabel);
      ex.setTypeLabel(typeLabel);
      ex.setValue(householdId);
      ex.apply();

      throw ex;
    }
  }
}
