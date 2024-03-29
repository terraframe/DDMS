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
package dss.vector.solutions.export;

import java.text.DateFormat;
import java.util.Date;

public class AmbiguousRecipientProblem extends AmbiguousRecipientProblemBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256860224517L;

  public AmbiguousRecipientProblem()
  {
    super();
  }

  public AmbiguousRecipientProblem(java.lang.String developerMessage)
  {
    super(developerMessage);
  }

  protected String replace(String template, String replaceMe, Object newValue)
  {
    if (newValue == null)
    {
      return template;
    }

    if (newValue instanceof Date)
    {
      DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale);

      String formattedValue = format.format((Date) newValue);
      return template.replace(replaceMe, formattedValue);
    }

    return template.replace(replaceMe, newValue.toString());
  }
}
