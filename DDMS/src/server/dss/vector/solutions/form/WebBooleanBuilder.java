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
/**
 * 
 */
package dss.vector.solutions.form;

import com.runwaysdk.constants.MdAttributeBooleanInfo;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeBoolean;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdWebBoolean;

public class WebBooleanBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebBooleanBuilder()
  {
    super();
  }

  public WebBooleanBuilder(MdWebBoolean mdWebBoolean)
  {
    super(mdWebBoolean);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebBooleanBuilder();
  }

  @Override
  public MdWebBoolean getMdField()
  {
    return (MdWebBoolean) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdWebBoolean mdField = this.getMdField();
    Boolean defaultValue = mdField.getDefaultValue();
    
    MdAttributeBoolean mdAttributeBoolean = (MdAttributeBoolean) mdAttribute;
    
    if(defaultValue != null)
    {
      mdAttribute.setValue(MdAttributeBooleanInfo.DEFAULT_VALUE, defaultValue.toString());      
    }
    else
    {
      mdAttribute.setValue(MdAttributeBooleanInfo.DEFAULT_VALUE, "");      
    }

    // FIXME allow user defined labels
    if (mdAttributeBoolean.isNew())
    {
      mdAttributeBoolean.getPositiveDisplayLabel().setDefaultValue("true");
      mdAttributeBoolean.getNegativeDisplayLabel().setDefaultValue("false");
    }
    

    super.updateMdAttribute(mdAttributeBoolean);
  }
}
