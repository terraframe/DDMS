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
import com.runwaysdk.system.metadata.MdAttributeLong;
import com.runwaysdk.system.metadata.MdWebLong;

public class WebLongBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebLongBuilder()
  {
    super();
  }

  public WebLongBuilder(MdWebLong mdWebLong)
  {
    super(mdWebLong);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebLongBuilder();
  }

  @Override
  public MdWebLong getMdField()
  {
    return (MdWebLong) super.getMdField();
  }
  
  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    Locale locale = Session.getCurrentLocale();
    Format<Long> f = AbstractFormatFactory.getFormatFactory().getFormat(Long.class);
    
    MdAttributeLong mdAttributeLong = (MdAttributeLong) mdAttribute;
    MdWebLong mdWebLong = this.getMdField();

    String start = mdWebLong.getStartRange();
    Long startVal = f.parse(start, locale);
    
    mdAttributeLong.setStartRange(startVal);

    String end = mdWebLong.getEndRange();
    Long endVal = f.parse(end, locale);
    mdAttributeLong.setEndRange(endVal);

    // reset the string values to store as English locale
    mdWebLong.setStartRange(f.format(startVal, Locale.ENGLISH));
    mdWebLong.setEndRange(f.format(endVal, Locale.ENGLISH));
    
    super.updateMdAttribute(mdAttributeLong);
  }
}
