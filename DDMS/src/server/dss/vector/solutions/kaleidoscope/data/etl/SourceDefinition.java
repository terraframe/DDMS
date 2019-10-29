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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdView;

public class SourceDefinition implements SourceDefinitionIF, Reloadable
{
  private String                         sheetName;

  private String                         label;

  private String                         type;

  private Map<String, SourceFieldIF>     fieldMap;

  private HashMap<String, SourceFieldIF> labelMap;

  private boolean                        isNew;

  private boolean                        isApplied;

  private String                         id;

  public SourceDefinition()
  {
    this.fieldMap = new HashMap<String, SourceFieldIF>();
    this.labelMap = new HashMap<String, SourceFieldIF>();
    this.isNew = true;
    this.isApplied = false;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @Override
  public String getId()
  {
    return id;
  }

  @Override
  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  @Override
  public String getName()
  {
    return this.sheetName;
  }

  public void setSheetName(String sheetName)
  {
    this.sheetName = sheetName;
  }

  public void setNew(boolean isNew)
  {
    this.isNew = isNew;
  }

  @Override
  public boolean isNew()
  {
    return this.isNew;
  }
  
  @Override
  public boolean isApplied()
  {
    return this.isApplied;
  }
  
  public void setApplied(boolean isApplied)
  {
    this.isApplied = isApplied;
  }

  @Override
  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public void addField(SourceFieldIF field)
  {
    this.fieldMap.put(field.getColumnName(), field);
    this.labelMap.put(field.getLabel(), field);
  }

  @Override
  public SourceFieldIF getFieldByName(String columnName)
  {
    return this.fieldMap.get(columnName);
  }

  @Override
  public SourceFieldIF getFieldByLabel(String label)
  {
    return this.labelMap.get(label);
  }

  @Override
  public List<SourceFieldIF> getFields()
  {
    return new LinkedList<SourceFieldIF>(this.fieldMap.values());
  }

  @Override
  public void persist()
  {
    if (this.isNew())
    {
      ExcelSourceBinding source = new ExcelSourceBinding();
      source.setSheetName(this.sheetName);
      source.setMdView(MdView.getMdView(this.type));
      source.apply();

      Collection<SourceFieldIF> fields = this.fieldMap.values();

      for (SourceFieldIF field : fields)
      {
        field.persist(source);
      }

      this.setApplied(true);      
      this.setId(source.getId());
    }
  }
}
