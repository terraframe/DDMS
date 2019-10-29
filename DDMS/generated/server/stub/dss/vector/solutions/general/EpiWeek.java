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
package dss.vector.solutions.general;

import java.util.Date;

import com.runwaysdk.ConfigurationException;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.surveillance.PeriodType;

public class EpiWeek extends EpiWeekBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256576864448L;

  public static final int NUMBER_OF_WEEKS = 53;
  
  public EpiWeek()
  {
    super();
  }

  @Override
  public void apply()
  {
    validatePeriod();

    super.apply();
  }

  @Override
  public void validatePeriod()
  {
    if(this.getPeriod() != null && this.getPeriod() > 53)
    {
      String msg = "Epi Period can never be greater than 53";

      throw new ConfigurationException(msg);
    }
  }

  public static EpiWeek getEpiWeek(Integer period, Integer year)
  {
    EpiDate week = EpiDate.getInstanceByPeriod(PeriodType.WEEK, period, year);

    EpiWeekQuery query = new EpiWeekQuery(new QueryFactory());
    query.WHERE(AND.get(query.getPeriod().EQ(week.getActualPeriod()), query.getYearOfWeek().EQ(week.getActualYear())));
    OIterator<? extends EpiWeek> it = query.getIterator();

    try
    {
      if(it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public static EpiWeek getEpiWeek(Date date)
  {
    return EpiCache.getWeek(date);
  }
  
  public static EpiWeek getEpiWeek(EpiDate date)
  {
    EpiWeek week = EpiWeek.getEpiWeek(date.getPeriod(), date.getYear());

    if(week == null)
    {
      week = new EpiWeek();
      week.setPeriod(date.getActualPeriod());
      week.setYearOfWeek(date.getActualYear());
      week.apply();
    }
    return week;
  }
}
