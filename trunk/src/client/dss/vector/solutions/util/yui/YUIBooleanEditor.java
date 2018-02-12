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

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeBooleanMdDTO;

import dss.vector.solutions.util.Halp;

public class YUIBooleanEditor extends YUIEditor implements Reloadable
{
  private AttributeBooleanMdDTO attributeMd;
  
  private Boolean includeBlank;

  public YUIBooleanEditor(AttributeBooleanMdDTO attributeMd, ColumnSetup setup) throws Exception
  {
    super();

    this.attributeMd = attributeMd;
    this.includeBlank = (setup.getIncludeBlank() != null ? setup.getIncludeBlank() : false);
  }

  public String getType()
  {
    return DROPDOWN_EDITOR;
  }

  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    String positiveLabel = attributeMd.getPositiveDisplayLabel().replaceAll("'", "\\\\'");
    String negativeLabel = attributeMd.getNegativeDisplayLabel().replaceAll("'", "\\\\'");

    List<String> radioOptions = new LinkedList<String>();
        
    if(includeBlank)
    {
      radioOptions.add("{label:'', value:''}");
    }
    
    radioOptions.add("{label:'" + positiveLabel + "', value:'true'}");
    radioOptions.add("{label:'" + negativeLabel + "', value:'false'}");

    options.add("dropdownOptions:[" + Halp.join(radioOptions) + "]");

    return options;
  }

  @Override
  public String getDefaultValue(Object object)
  {
    Boolean value = (Boolean) object;

    String objectValue = value.toString();
    String objectLabel = this.attributeMd.getNegativeDisplayLabel();

    if (value)
    {
      objectLabel = attributeMd.getPositiveDisplayLabel();
    }

    return "{'value':'" + objectValue + "','label':'" + objectLabel + "'}";
  }

  @Override
  public String getValue(Object object)
  {
    Boolean value = (Boolean) object;
    String label = ( value ? attributeMd.getPositiveDisplayLabel() : attributeMd.getNegativeDisplayLabel() );

    return Halp.getLabeledValue(label, value.toString());
  }
}
