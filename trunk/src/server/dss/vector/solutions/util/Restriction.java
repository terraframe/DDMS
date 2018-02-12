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

import java.util.LinkedList;
import java.util.List;

public class Restriction
{
  private String       attributeName;

  private List<String> restrictions;

  private Boolean      aggregate;

  public Restriction(String attributeName)
  {
    this.attributeName = attributeName;
    this.restrictions = new LinkedList<String>();
    this.aggregate = false;
  }

  public String getAttributeName()
  {
    return attributeName;
  }

  public List<String> getRestrictions()
  {
    return restrictions;
  }

  public Boolean getAggregate()
  {
    return aggregate;
  }

  public void setAggregate(Boolean aggregate)
  {
    this.aggregate = aggregate;
  }
}
