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
package dss.vector.solutions.util.yui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.Constants;
import com.runwaysdk.generation.loader.Reloadable;

public class YUIDateEditor extends YUIEditor implements Reloadable
{
  @Override
  public List<String> getOptions()
  {
    List<String> options = new LinkedList<String>();

    options.add("calendar:MDSS.Calendar.init()");

    return options;
  }

  @Override
  public String getType()
  {
    return DATE_EDITOR;
  }

  @Override
  public String getValue(Object object)
  {
    SimpleDateFormat df = new SimpleDateFormat(Constants.DATETIME_FORMAT);

    return df.format((Date) object);
  }

  @Override
  public String getDefaultValue(Object value)
  {
    return "'" + this.getValue(value) + "'"; 
  }
}
