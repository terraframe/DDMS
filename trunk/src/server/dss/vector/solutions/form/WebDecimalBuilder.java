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

import java.math.BigDecimal;
import java.util.Locale;

import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttributeConcrete;
import com.runwaysdk.system.metadata.MdAttributeDecimal;
import com.runwaysdk.system.metadata.MdWebDecimal;

public class WebDecimalBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDecimalBuilder()
  {
    super();
  }

  public WebDecimalBuilder(MdWebDecimal mdWebDecimal)
  {
    super(mdWebDecimal);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDecimalBuilder();
  }

  @Override
  public MdWebDecimal getMdField()
  {
    return (MdWebDecimal) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    Locale locale = Session.getCurrentLocale();
    Format<BigDecimal> f = AbstractFormatFactory.getFormatFactory().getFormat(BigDecimal.class);
    
    MdAttributeDecimal mdAttributeDecimal = (MdAttributeDecimal) mdAttribute;

    MdWebDecimal mdWebDecimal = this.getMdField();

    mdAttributeDecimal.setDatabaseLength(mdWebDecimal.getDecPrecision());
    mdAttributeDecimal.setDatabaseDecimal(mdWebDecimal.getDecScale());

    String start = mdWebDecimal.getStartRange();
    BigDecimal startVal = f.parse(start, locale);
    mdAttributeDecimal.setStartRange(startVal);

    String end = mdWebDecimal.getEndRange();
    BigDecimal endVal = f.parse(end, locale);
    mdAttributeDecimal.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebDecimal.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebDecimal.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttribute);
  }
}
