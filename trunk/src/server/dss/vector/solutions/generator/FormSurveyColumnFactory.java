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

import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebReferenceDAOIF;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.StringFieldColumn;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.form.business.FormHousehold;

public class FormSurveyColumnFactory extends FormColumnFactory implements Reloadable
{
  @Override
  public ExcelColumn getColumn(MdWebAttributeDAOIF mdField)
  {
    if (mdField instanceof MdWebReferenceDAOIF)
    {
      MdAttributeConcreteDAOIF mdAttribute = (MdAttributeConcreteDAOIF) mdField.getDefiningMdAttribute();

      if (mdAttribute.definesAttribute().equals(FormHousehold.SURVEY))
      {
        return new StringFieldColumn(mdField);
      }
    }

    return super.getColumn(mdField);  
  }
}
