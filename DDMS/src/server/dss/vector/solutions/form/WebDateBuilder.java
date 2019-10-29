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
import com.runwaysdk.system.metadata.MdAttributeDate;
import com.runwaysdk.system.metadata.MdWebDate;

public class WebDateBuilder extends WebPrimitiveBuilder implements Reloadable
{
  public WebDateBuilder()
  {
    super();
  }

  public WebDateBuilder(MdWebDate mdWebDate)
  {
    super(mdWebDate);
  }

  @Override
  public WebFieldBuilder copy()
  {
    return new WebDateBuilder();
  }

  @Override
  public MdWebDate getMdField()
  {
    return (MdWebDate) super.getMdField();
  }

  @Override
  protected void updateMdAttribute(MdAttributeConcrete mdAttribute)
  {
    MdAttributeDate mdAttributeDate = (MdAttributeDate) mdAttribute;

    MdWebDate mdWebDate = this.getMdField();

    mdAttributeDate.setStartDate(mdWebDate.getStartDate());
    mdAttributeDate.setEndDate(mdWebDate.getEndDate());
    mdAttributeDate.setAfterTodayExclusive(mdWebDate.getAfterTodayExclusive());
    mdAttributeDate.setAfterTodayInclusive(mdWebDate.getAfterTodayInclusive());
    mdAttributeDate.setBeforeTodayExclusive(mdWebDate.getBeforeTodayExclusive());
    mdAttributeDate.setBeforeTodayInclusive(mdWebDate.getBeforeTodayInclusive());

    super.updateMdAttribute(mdAttributeDate);
  }
}
