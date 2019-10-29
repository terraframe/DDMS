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

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.ontology.Term;

public class TermColumn extends AttributeColumn implements Reloadable
{
  private MdAttributeReferenceDAOIF mdAttribute;

  public TermColumn(MdAttributeReferenceDAOIF mdAttribute)
  {
    super(mdAttribute);

    this.mdAttribute = mdAttribute;
  }

  @Override
  public String javaType()
  {
    return Term.class.getName();
  }

  @Override
  public Object getValue(Cell cell) throws Exception
  {
    String displayLabel = ExcelUtil.getString(cell);

    return Term.validateByDisplayLabel(displayLabel, mdAttribute);
  }
}
