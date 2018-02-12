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
package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class DateExtrapolationView extends AuxiliaryProvider implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.DateExtrapolationView";

  public DateExtrapolationView(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  protected View getView()
  {
    return View.DATE_EXTRAPOLATION_VIEW;
  }

  @Override
  public String getSQL()
  {
    String yearCol = QueryUtil.getColumnName(EpiWeek.getYearOfWeekMd());
    String periodCol = this.irsQB.getPeriodCol();
    int startDay = this.irsQB.getStartDay();

    String sql = "";
    sql += "SELECT \n";
    sql += " "+yearCol+" AS "+yearCol+", \n";
    sql += " " + periodCol + " AS " + periodCol + ", \n";
    sql += " (get_epistart(" + yearCol + ", " + startDay + ") + (to_char((" + periodCol
        + ")*7, '999')||' days')::interval)::date AS " + Alias.PLANNED_DATE + " \n";
    sql += "FROM epi_week ";

    return sql;
  }

}
