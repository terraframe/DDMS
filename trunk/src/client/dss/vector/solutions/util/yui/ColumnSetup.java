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

import com.runwaysdk.generation.loader.Reloadable;

/**
 * @author jsmethie
 * 
 */
public class ColumnSetup implements Reloadable
{
  /**
   * Denotes if this column is custom or not, meaning a custom column 
   * doesn't map to a DTO but instead contains all the information it needs.
   */
  private boolean isCustom;
  
  /**
   * If this column is hidden
   */
  private boolean hidden;

  /**
   * If this column is editable
   */
  private boolean editable;

  /**
   * If the column should sum its values onto the last row
   */
  private boolean sum;

  /**
   * Validator to use in the column
   */
  private String  validator;

  /**
   * Type to use if this setup represents a reference column
   */
  private String  type;

  /**
   * The name of the static method used to populate the reference column
   */
  private String  method;

  private String  title;

  private String  label;

  private String  getter;

  private String  setter;

  private Integer width;

  private Boolean includeBlank;

  private boolean indicateRequired;
  
  private String display;
  
  private String key;

  public ColumnSetup()
  {
    this(false, true);
  }

  public ColumnSetup(boolean hidden, boolean editable)
  {
    this(hidden, editable, null, null, null);
  }

  public ColumnSetup(boolean hidden, boolean editable, String validator, String type, String method)
  {
    this.hidden = hidden;
    this.editable = editable;
    this.validator = validator;
    this.type = type;
    this.method = method;
    this.sum = false;
    this.includeBlank = false;
    this.title = null;
    this.label = null;
    this.indicateRequired = true;
    
    this.display = null;
    this.key = null;
    this.isCustom = false;
  }
  
  public ColumnSetup(String key)
  {
    this(true, false);
    
    this.isCustom = true;
    this.label = key+"_l";
    this.title = key+"_t";
    this.key = key;
  }
  
  public void setCustom(boolean isCustom)
  {
    this.isCustom = isCustom;
  }
  
  public boolean isCustom()
  {
    return isCustom;
  }
  
  public String getKey()
  {
    return key;
  }
   
  public String getDisplay()
  {
    return display;
  }
  
  public void setDisplay(String display)
  {
    this.display = display;
  }

  public boolean isHidden()
  {
    return hidden;
  }

  public void setHidden(boolean hidden)
  {
    this.hidden = hidden;
  }

  public boolean isEditable()
  {
    return editable;
  }

  public void setEditable(boolean editable)
  {
    this.editable = editable;
  }

  public String getValidator()
  {
    return validator;
  }

  public void setValidator(String validator)
  {
    this.validator = validator;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getMethod()
  {
    return method;
  }

  public void setMethod(String method)
  {
    this.method = method;
  }

  public boolean isSum()
  {
    return sum;
  }

  public void setSum(boolean sum)
  {
    this.sum = sum;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getLabel()
  {
    return label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getGetter()
  {
    return getter;
  }

  public void setGetter(String getter)
  {
    this.getter = getter;
  }

  public String getSetter()
  {
    return setter;
  }

  public void setSetter(String setter)
  {
    this.setter = setter;
  }

  public boolean hasGetter()
  {
    return ( this.getter != null && this.getter.length() > 0 );
  }

  public Integer getWidth()
  {
    return width;
  }

  public void setWidth(Integer width)
  {
    this.width = width;
  }

  public Boolean getIncludeBlank()
  {
    return includeBlank;
  }

  public void setIncludeBlank(Boolean includeBlank)
  {
    this.includeBlank = includeBlank;
  }
  
  public boolean isIndicateRequired()
  {
    return indicateRequired;
  }

  public void setIndicateRequired(boolean indicateRequired)
  {
    this.indicateRequired = indicateRequired;
  }
}
