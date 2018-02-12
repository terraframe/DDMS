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

import java.util.List;

import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeBooleanMdDTO;
import com.runwaysdk.transport.metadata.AttributeDateMdDTO;
import com.runwaysdk.transport.metadata.AttributeDecMdDTO;
import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;
import com.runwaysdk.transport.metadata.AttributeMdDTO;
import com.runwaysdk.transport.metadata.AttributeNumberMdDTO;
import com.runwaysdk.transport.metadata.AttributeReferenceMdDTO;

import dss.vector.solutions.LabeledDTO;
import dss.vector.solutions.ontology.TermDTO;

public abstract class YUIEditor implements Reloadable
{
  public static final String DROPDOWN_EDITOR = "new YAHOO.widget.DropdownCellEditor";

  public static final String TEXTBOX_EDITOR  = "new YAHOO.widget.TextboxCellEditor";

  public static final String DATE_EDITOR     = "new YAHOO.widget.DateCellEditor";

  public static final String TERM_EDITOR     = "new YAHOO.widget.OntologyTermEditor";

  public static final String NUMBER_EDITOR   = "new YAHOO.widget.NumberCellEditor";

  public abstract String getType();

  public abstract List<String> getOptions();

  public abstract String getDefaultValue(Object value);

  public String getValue(Object object)
  {
    return object.toString();
  }

  public static YUIEditor getEditor(AttributeMdDTO attribute, ColumnSetup setup, MutableDTO view, String key) throws Exception
  {
    if (attribute instanceof AttributeBooleanMdDTO)
    {
      return new YUIBooleanEditor((AttributeBooleanMdDTO) attribute, setup);
    }
    else if (attribute instanceof AttributeDateMdDTO)
    {
      return new YUIDateEditor();
    }
    else if (attribute instanceof AttributeNumberMdDTO)
    {
      boolean isDecimal = attribute instanceof AttributeDecMdDTO;
      return new YUINumberEditor(isDecimal);
    }
    else if (attribute instanceof AttributeEnumerationMdDTO)
    {
      return new YUIEnumerationEditor((AttributeEnumerationMdDTO) attribute, setup);
    }
    else if (attribute instanceof AttributeReferenceMdDTO)
    {
      Class<?> javaType = attribute.getJavaType();

      if (setup.getType() != null)
      {
        javaType = LoaderDecorator.load(setup.getType());
      }

      if (LabeledDTO.class.isAssignableFrom(javaType))
      {
        return new YUILabeledEditor(attribute, setup, key);
      }
      else if (TermDTO.class.isAssignableFrom(javaType))
      {
        return new YUITermEditor(attribute, view.getType());
      }

      return new YUIReferenceEditor();
    }

    return new YUITextEditor();
  }
}
