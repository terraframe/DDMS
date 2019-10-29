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
package dss.vector.solutions.ontology;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.dataaccess.io.ExcelExporter;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.ExcelImportManager;

public class TermSynonymArrayExcelView extends TermSynonymArrayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 853636322;

  public TermSynonymArrayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    String termInsId = this.getTermInstanceId();

    Term term = Term.getByTermId(termInsId);

    String[] names = this.getSynonymNames().split(",");
    for (int i = 0; i < names.length; ++i)
    {
      String name = names[i];

      TermSynonym.createSynonym(name, term.getTermId());
    }
  }

  public static List<String> customAttributeOrder()
  {
    List<String> list = new ArrayList<String>();

    list.add(TERMINSTANCEID);
    list.add(SYNONYMNAMES);
    return list;
  }

  public static void setupExportListener(ExcelExporter exporter, String... params)
  {
    // exporter.addListener(createExcelTermListener(null));
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    // context.addListener(createExcelTermListener(importer));
  }

  // private static ExcelAdapter createExcelTermListener(ExcelImportManager
  // importer)
  // {
  // HierarchyBuilder builder = new HierarchyBuilder();
  // for (TermHierarchy hierarchy : TermHierarchy.getAll())
  // {
  // builder.add(hierarchy);
  // }
  // return new DynamicTermColumnListener(CLASS, TERM, builder, importer);
  // return new ExcelAdapter();
  // }
}
