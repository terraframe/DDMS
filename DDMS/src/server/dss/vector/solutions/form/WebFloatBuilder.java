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

import java.util.Locale;

import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeFloat;
import com.runwaysdk.system.metadata.MdWebFloat;

public class WebFloatBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebFloatBuilder()
  {
    super();
  }

  public WebFloatBuilder(MdWebFloat mdWebFloat)
  {
    super(mdWebFloat);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebFloatBuilder();
  }

  @Override
  public MdWebFloat getMdField()
  {
    return (MdWebFloat) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    Locale locale = Session.getCurrentLocale();
    Format<Float> f = AbstractFormatFactory.getFormatFactory().getFormat(Float.class);
    
    MdAttributeFloat mdAttributeFloat = (MdAttributeFloat) mdAttribute;

    MdWebFloat mdWebFloat = this.getMdField();

    mdAttributeFloat.setDatabaseLength(mdWebFloat.getDecPrecision());
    mdAttributeFloat.setDatabaseDecimal(mdWebFloat.getDecScale());

    String start = mdWebFloat.getStartRange();
    Float startVal = f.parse(start, locale);
    mdAttributeFloat.setStartRange(startVal);

    String end = mdWebFloat.getEndRange();
    Float endVal = f.parse(end, locale);
    mdAttributeFloat.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebFloat.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebFloat.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttributeFloat);
  }

}
