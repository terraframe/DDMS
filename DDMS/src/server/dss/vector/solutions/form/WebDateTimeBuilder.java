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

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDateTime;
import com.runwaysdk.system.metadata.MdWebDateTime;

public class WebDateTimeBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDateTimeBuilder()
  {
    super();
  }

  public WebDateTimeBuilder(MdWebDateTime mdWebDateTime)
  {
    super(mdWebDateTime);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDateTimeBuilder();
  }

  @Override
  public MdWebDateTime getMdField()
  {
    return (MdWebDateTime) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttr)
  {
    MdAttributeDateTime md = (MdAttributeDateTime) mdAttr;

    super.updateMdAttribute(md);
  }
}
