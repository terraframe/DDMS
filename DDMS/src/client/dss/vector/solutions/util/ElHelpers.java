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
package dss.vector.solutions.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class ElHelpers implements com.runwaysdk.generation.loader.Reloadable
{

  public static String getDateFormatString(HttpServletRequest request)
  {
    Locale locale = request.getLocale();
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    SimpleDateFormat formatter = (SimpleDateFormat) df;
    return formatter.toPattern();
  }

  public static String getFormatedDate(HttpServletRequest request, Date date)
  {
    SimpleDateFormat formatter = new SimpleDateFormat(getDateFormatString(request));
    return formatter.format(date);
  }

  public static String dashNull(String str)
  {
    if (str == null)
    {
      return "-";
    }
    else
    {
      return str;
    }
  }
}
