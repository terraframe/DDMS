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

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.io.excel.ContextBuilderIF;
import com.runwaysdk.dataaccess.metadata.MdWebFormDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdWebForm;

import dss.vector.solutions.ExcelImportManager;

public class ContextBuilderFacade implements ContextBuilderIF, Reloadable
{
  private static final String           DEFAULT = "DEFAULT";

  private Map<String, ContextBuilderIF> map;
  
  private ExcelImportManager manager;

  public ContextBuilderFacade(ExcelImportManager manager)
  {
    this.map = new HashMap<String, ContextBuilderIF>();
    this.map.put(DEFAULT, new DefaultContextBuilder());
    this.manager = manager;
  }

  public ContextBuilderFacade(ContextBuilderIF defaultBuilder, ExcelImportManager manager)
  {
    this.map = new HashMap<String, ContextBuilderIF>();
    this.map.put(DEFAULT, defaultBuilder);
    this.manager = manager;
  }
  
  public void add(String contextType, ContextBuilderIF builder)
  {
    this.map.put(contextType, builder);
  }

  @Override
  public ImportContext createContext(Sheet sheet, String sheetName, Workbook errorWorkbook, String type)
  {
    ContextBuilderIF builder = this.getBuilder(type);

    return builder.createContext(sheet, sheetName, errorWorkbook, type);
  }
  
  @Override
  public void configure(ImportContext context, Row typeRow, Row nameRow, Row labelRow)
  {
    ContextBuilderIF builder = this.getBuilder(context.getMdClassType());

    builder.configure(context, typeRow, nameRow, labelRow);
  }

  public ContextBuilderIF getBuilder(String type)
  {
    if (this.map.containsKey(type))
    {
      return this.map.get(type);
    }

    if (MdFormUtil.isFormBusinessPackage(type))
    {
      if (!this.map.containsKey(type))
      {
        MdWebForm mdForm = MdFormUtil.getMdFormFromBusinessType(type);

        if (mdForm != null)
        {
          MdWebFormDAOIF mdFormDao = MdWebFormDAO.get(mdForm.getId());

          String classType = mdForm.getFormMdClass().definesType();

          this.add(classType, new FormContextBuilder(mdFormDao, new FormImportFilter(), manager));

          MdFormUtil.addGridContexts(mdFormDao, this);
        }
        else
        {
          UnsupportedImportTypeException e = new UnsupportedImportTypeException();
          e.setClassType(type);
          e.apply();

          throw e;
        }
      }

      return this.map.get(type);
    }
    else if (MdFormUtil.isFormRelationshipPackage(type))
    {
      if (!this.map.containsKey(type))
      {
        UnsupportedImportTypeException e = new UnsupportedImportTypeException();
        e.setClassType(type);
        e.apply();

        throw e;
      }

      return this.map.get(type);
    }

    return this.map.get(DEFAULT);
  }
}
