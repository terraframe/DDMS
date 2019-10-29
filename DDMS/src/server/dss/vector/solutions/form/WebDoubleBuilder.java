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
import com.runwaysdk.system.metadata.MdAttributeDouble;
import com.runwaysdk.system.metadata.MdWebDouble;

public class WebDoubleBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDoubleBuilder()
  {
    super();
  }

  public WebDoubleBuilder(MdWebDouble mdWebDouble)
  {
    super(mdWebDouble);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDoubleBuilder();
  }

  @Override
  public MdWebDouble getMdField()
  {
    return (MdWebDouble) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttributeConcrete)
  {
    Locale locale = Session.getCurrentLocale();
    Format<Double> f = AbstractFormatFactory.getFormatFactory().getFormat(Double.class);
    
    MdWebDouble mdWebDouble = this.getMdField();
    MdAttributeDouble mdAttributeDouble = (MdAttributeDouble) mdAttributeConcrete;

    mdAttributeDouble.setDatabaseLength(mdWebDouble.getDecPrecision());
    mdAttributeDouble.setDatabaseDecimal(mdWebDouble.getDecScale());

    String start = mdWebDouble.getStartRange();
    Double startVal = f.parse(start, locale);
    mdAttributeDouble.setStartRange(startVal);

    String end = mdWebDouble.getEndRange();
    Double endVal = f.parse(end, locale);
    mdAttributeDouble.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebDouble.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebDouble.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttributeDouble);
  }

}
