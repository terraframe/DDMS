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
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.form.business.FormPerson;
import dss.vector.solutions.form.business.FormPersonQuery;

public class FormPersonImportContext extends FormImportContext implements Reloadable
{

  public FormPersonImportContext(Sheet sheet, String sheetName, Sheet error, MdClassDAOIF mdClass, ExcelImportManager manager)
  {
    super(sheet, sheetName, error, mdClass, manager);
  }

  @Override
  protected Mutable getMutableForRow(Row row)
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
          person.appLock();

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
