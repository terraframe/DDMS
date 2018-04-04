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

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.runwaysdk.business.Business;
import com.runwaysdk.business.BusinessQuery;
import com.runwaysdk.business.Mutable;
import com.runwaysdk.dataaccess.MdClassDAOIF;
import com.runwaysdk.dataaccess.io.excel.AttributeColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelColumn;
import com.runwaysdk.dataaccess.io.excel.ExcelUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.ExcelImportManager;

public class FormImportContext extends ManagedImportContext implements Reloadable
{

  public FormImportContext(Sheet sheet, String sheetName, Sheet error, MdClassDAOIF mdClass, ExcelImportManager manager)
  {
    super(sheet, sheetName, error, mdClass, manager);
  }

  @Override
  protected Mutable getMutableForRow(Row row)
  {
    String oid = this.getCellValue(row, MdFormUtil.OID);

    if (oid != null && oid.length() > 0)
    {
      MdClassDAOIF mdClass = this.getMdClass();

      QueryFactory factory = new QueryFactory();
      BusinessQuery query = factory.businessQuery(mdClass.definesType());
      query.WHERE(query.aCharacter(MdFormUtil.OID).EQ(oid));

      OIterator<Business> it = query.getIterator();

      try
      {
        if (it.hasNext())
        {
          Business mutable = it.next();
          mutable.appLock();

          return mutable;
        }
      }
      finally
      {
        it.close();
      }
    }

    return super.getMutableForRow(row);
  }

  protected ExcelColumn getColumn(String columnName)
  {
    List<AttributeColumn> columns = this.getExpectedColumns();

    for (AttributeColumn column : columns)
    {
      if (column.getAttributeName().equals(columnName))
      {
        return column;
      }
    }

    return null;
  }

  protected String getCellValue(Row row, String columnName)
  {
    ExcelColumn column = this.getColumn(columnName);
    
    return ExcelUtil.getString(row.getCell(column.getIndex()));
  }

}
