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
import com.runwaysdk.transport.metadata.AttributeMdDTO;

import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.Halp;

public class YUITermEditor extends YUIEditor implements Reloadable
{
  private AttributeMdDTO attribute;
  
  private String type;
  
  public YUITermEditor(AttributeMdDTO attribute, String type)
  {
    this.attribute = attribute;
    this.type = type;
  }

  @Override
  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    options.add("klass:'" + type + "'");
    options.add("attribute:'" + attribute.getName() + "'");
    
    return options;
  }

  @Override
  public String getType()
  {
    return TERM_EDITOR;
  }
  
  @Override
  public String getValue(Object object)
  {
    TermDTO term = (TermDTO) object;

    return Halp.getTermIdWithDisplayLabel(term);
  }

  @Override
  public String getDefaultValue(Object value)
  {
    TermDTO dto = (TermDTO) value;
    
    return YUIColumn.getDefaultValue(dto.getId(), dto.getTermDisplayLabel().getValue());
  }
}
