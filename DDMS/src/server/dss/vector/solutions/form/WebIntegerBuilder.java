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
import com.runwaysdk.system.metadata.MdAttributeInteger;
import com.runwaysdk.system.metadata.MdWebInteger;

public class WebIntegerBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebIntegerBuilder()
  {
    super();
  }

  public WebIntegerBuilder(MdWebInteger mdWebInteger)
  {
    super(mdWebInteger);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebIntegerBuilder();
  }

  @Override
  public MdWebInteger getMdField()
  {
    return (MdWebInteger) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    Locale locale = Session.getCurrentLocale();
    Format<Integer> f = AbstractFormatFactory.getFormatFactory().getFormat(Integer.class);
    
    MdAttributeInteger mdAttributeInteger = (MdAttributeInteger) mdAttribute;
    MdWebInteger mdWebInteger = this.getMdField();
    
    String start = mdWebInteger.getStartRange();
    Integer startVal = f.parse(start, locale);
    mdAttributeInteger.setStartRange(startVal);

    
    String end = mdWebInteger.getEndRange();
    Integer endVal = f.parse(end, locale);
    mdAttributeInteger.setEndRange(endVal);
    
    // reset the string values to store as English locale
    mdWebInteger.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebInteger.setEndRange(f.format(endVal, Locale.ENGLISH));

    super.updateMdAttribute(mdAttributeInteger);
  }

}
