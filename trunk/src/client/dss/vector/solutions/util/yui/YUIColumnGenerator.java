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

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.ClientException;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.Halp;

public class YUIColumnGenerator implements Reloadable
{
  private YUIColumn column;

  public YUIColumnGenerator(YUIColumn column)
  {
    this.column = column;
  }

  public String getColumnDefinition()
  {
    try
    {
      ArrayList<String> buff = new ArrayList<String>();

      String label = column.getLabel();

      buff.add("key:'" + column.getColumnKey() + "'");
      buff.add("label:'" + label.replaceAll("'", "\\\\'") + "'");

      if (column.isSum())
      {
        buff.add("sum:true");
      }

      if (column.getTitle() != null)
      {
        buff.add("title:'" + column.getTitle() + "'");
      }

      if (column.isHidden())
      {
        buff.add("hidden:true");
      }

      if (column.getWidth() != null)
      {
        buff.add("width:" + column.getWidth());
      }

      buff.add(this.generateFormatter());
      buff.add(this.generateSaveFlag());

      if (column.isEditable())
      {
        buff.add(this.generateEditor());
      }

      return Halp.join(buff);
    }
    catch (Exception e)
    {
      throw new ClientException(e);
    }
  }

  private String generateValidator()
  {
    if (column.getWritable() && column.isEditable())
    {
      if (column.getValidator() != null)
      {
        return "validator:" + column.getValidator();
      }
    }

    return null;
  }

  private String generateSaveFlag()
  {
    if (column.getEditor() instanceof YUILabeledEditor)
    {
      return "save_as_id:true";
    }

    return null;
  }

  private String generateFormatter()
  {
    YUIEditor editor = column.getEditor();
    
    if (editor instanceof YUIDateEditor)
    {
      return "formatter:YAHOO.widget.DataTable.formatDate";
    }
    // provide a formatter for the read-only view of numbers. Editable 
    // columns already localize their numbers but we must do this manually
    // with a formatter for viewing (non-editable) grids.
    else if(!column.isEditable() && editor instanceof YUINumberEditor)
    {
      if(((YUINumberEditor)editor).isDecimal())
      {
        return "formatter: MDSS.NumberCellFormatter.formatDecimal";
      }
      else
      {
        return "formatter: MDSS.NumberCellFormatter.formatInteger";
      }
    }
    
    return null;
  }

  private String generateEditor()
  {
    // Default to a text box editor
    YUIEditor editor = column.getEditor();

    List<String> options = editor.getOptions();
    options.add(this.generateValidator());
    options.add("disableBtns:true");

    return "editor:" + editor.getType() + "({" + Halp.join(options) + "})";
  }
}
