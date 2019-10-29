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

import com.runwaysdk.ClientException;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.controller.DTOFacade;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

public class YUIColumn implements Reloadable
{
  private Boolean   writable;

  private String    attributeName;

  private String    key;

  private YUIEditor editor;

  /**
   * If this column is hidden
   */
  private boolean   hidden;

  /**
   * If this column is editable
   */
  private boolean   editable;

  /**
   * If the column should sum its values onto the last row
   */
  private boolean   sum;

  /**
   * Validator to use in the column
   */
  private String    validator;

  private String    title;

  private String    label;

  private String    getter;

  private String    defaultValue;

  private String    postfix;

  private String    options;

  private Integer   width;

  private boolean   isCustom;

  public YUIColumn(ColumnSetup setup, MutableDTO view, String attribute, String postfix)
  {
    try
    {
      this.postfix = postfix;
      this.hidden = setup.isHidden();
      this.editable = setup.isEditable();
      this.sum = setup.isSum();
      this.validator = setup.getValidator();
      this.title = setup.getTitle();
      this.getter = setup.getGetter();
      this.defaultValue = null;
      this.options = null;
      this.width = setup.getWidth();
      
      if (setup.isCustom())
      {
        this.writable = false;
        this.attributeName = setup.getKey();
        this.key = setup.getKey();
        this.editor = null;
        this.label = setup.getLabel();
        this.title = setup.getTitle();
        this.isCustom = true;
      }
      else
      {
        DTOFacade facade = new DTOFacade(attribute, view);
        AttributeMdDTO attributeMd = facade.getAttributeMdDTO();

        this.writable = view.isWritable(attributeMd.getName());
        this.attributeName = attributeMd.getName();
        this.key = CommonGenerationUtil.upperFirstCharacter(attributeMd.getName());
        this.editor = YUIEditor.getEditor(attributeMd, setup, view, key + postfix);
        this.label = setup.getLabel() != null ? setup.getLabel() : attributeMd.getDisplayLabel();
        if (attributeMd.isRequired() && setup.isIndicateRequired())
        {
          this.label = "* " + this.label;
        }

        this.initDefaultValue(facade);
        this.isCustom = false;
      }

      this.initOptions(view);
    }
    catch (Exception e)
    {
      throw new ClientException(e);
    }
  }

  private final void initOptions(MutableDTO view)
  {
    if (this.editor instanceof YUILabeledEditor)
    {
      ClientRequestIF request = view.getRequest();
      YUILabeledEditor _editor = (YUILabeledEditor) this.editor;

      this.options = _editor.getDropdownOptions(request);
    }
  }

  private final void initDefaultValue(DTOFacade facade) throws Exception
  {
    Object value = facade.getValue();

    if (value != null)
    {
      String _defaultValue = this.editor.getDefaultValue(value);

      if (_defaultValue != null && _defaultValue.length() > 0)
      {
        this.defaultValue = "'" + key + this.postfix + "':" + _defaultValue;
      }
    }
  }

  public boolean isCustom()
  {
    return isCustom;
  }

  public Boolean getWritable()
  {
    return writable;
  }

  public String getLabel()
  {
    return this.label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getKey()
  {
    return key;
  }

  public String getColumnKey()
  {
    return key + postfix;
  }

  public YUIEditor getEditor()
  {
    return this.editor;
  }

  public boolean isSum()
  {
    return this.sum;
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean isHidden()
  {
    return this.hidden;
  }

  public boolean isEditable()
  {
    return this.editable;
  }

  public String getValidator()
  {
    return this.validator;
  }

  public String getDropDownOptions()
  {
    return this.options;
  }

  public Integer getWidth()
  {
    return width;
  }

  public String getValue(MutableDTO view)
  {
    try
    {
      DTOFacade facade = new DTOFacade(key, view);

      Object object = view.getValue(attributeName);

      if (this.isHidden() && object != null && ! ( editor instanceof YUIEnumerationEditor ))
      {
        return object.toString();
      }
      else
      {
        if (this.getter != null && this.getter.length() > 0)
        {
          Class<? extends MutableDTO> klass = view.getClass();
          object = klass.getMethod(getter).invoke(view);
        }
        else
        {
          object = facade.getValue();
        }

        if (object != null)
        {
          return this.editor.getValue(object);
        }
      }

      return null;
    }
    catch (Exception e)
    {
      throw new ClientException(e);
    }

  }

  public String getDefaultValue()
  {
    return defaultValue;
  }

  public static String getDefaultValue(String objectValue, String objectLabel)
  {
    return "{'value':'" + objectValue + "','label':'" + objectLabel + "'}";
  }
}
