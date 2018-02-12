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
package dss.vector.solutions.kaleidoscope.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedComponentQuery;

import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.util.LocalizationFacade;

public class ReportProviderUtil implements Reloadable
{
  private static void parseCondition(List<DashboardCondition> conditions, ReportConditionHandlerIF _handler)
  {
    for (DashboardCondition condition : conditions)
    {
      _handler.handleCondition(condition);
    }
  }

  public static void addConditions(QueryConfiguration _config, String _type, GeneratedComponentQuery _query)
  {
    String criteria = _config.getCriteria();

    if (criteria != null && criteria.length() > 0)
    {
      List<DashboardCondition> conditions = DashboardCondition.deserialize(criteria);

      ReportProviderUtil.parseCondition(conditions, new ReportConditionHandler(_type, _config.getValueQuery(), _query));
    }
  }

  public static String getConditionInformation(List<DashboardCondition> conditions)
  {
    ConditionInformationHandler handler = new ConditionInformationHandler();

    ReportProviderUtil.parseCondition(conditions, handler);

    return handler.getInformation();
  }

  public static Date parseDate(String source)
  {
    Locale locale = LocalizationFacade.getLocale();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", locale);

    try
    {
      Date date = format.parse(source);

      return date;
    }
    catch (ParseException cause)
    {
      // DateParseException e = new DateParseException(cause);
      // e.setInput(source);
      // e.setPattern(format.toLocalizedPattern());
      // throw e;

      throw new ProgrammingErrorException(cause);
    }
  }
}
