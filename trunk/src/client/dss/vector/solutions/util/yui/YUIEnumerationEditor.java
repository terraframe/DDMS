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
package dss.vector.solutions.util.yui;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;

import dss.vector.solutions.util.Halp;

public class YUIEnumerationEditor extends YUIEditor implements Reloadable
{
  private AttributeEnumerationMdDTO attribute;
  
  private Boolean includeBlank;

  public YUIEnumerationEditor(AttributeEnumerationMdDTO attribute, ColumnSetup setup)
  {
    this.attribute = attribute;
    this.includeBlank = (setup.getIncludeBlank() != null ? setup.getIncludeBlank() : !attribute.isRequired());
  }
  
  @Override
  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    List<String> dropdownOptions = new LinkedList<String>();

    if(includeBlank)
    {
      dropdownOptions.add("{label:'', value:''}");
    }
    
    for (Map.Entry<String, String> e : attribute.getEnumItems().entrySet())
    {
      dropdownOptions.add("{label:'" + e.getValue() + "', value:'" + e.getKey() + "'}");
    }

    options.add("dropdownOptions:[" + Halp.join(dropdownOptions) + "]");

    return options;
  }

  @Override
  public String getType()
  {
    return DROPDOWN_EDITOR;
  }
  
  @Override
  public String getValue(Object object)
  {
    String value = object.toString();
    
    return value.replaceAll("\\[", "").replaceAll("\\]", "");
  }
  
  @Override
  public String getDefaultValue(Object value)
  {
    return "";
  }

}
