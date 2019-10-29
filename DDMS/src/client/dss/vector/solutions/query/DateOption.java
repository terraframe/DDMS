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
package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public class DateOption implements Reloadable, SerializableOption
{
  private String classType;

  private String attributeName;

  public DateOption(String classType, String attributeName)
  {
    this.classType = classType;
    this.attributeName = attributeName;
  }

  public String serialize()
  {
    StringBuffer object = new StringBuffer();
    object.append("{klass:Mojo.Meta.findClass('" + classType + "')");
    object.append(", accessor:'" + attributeName + "'}");

    return object.toString();
  }

}
