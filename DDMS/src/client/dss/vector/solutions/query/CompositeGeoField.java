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

import java.util.Arrays;
import java.util.TreeSet;

import com.runwaysdk.generation.loader.Reloadable;

public class CompositeGeoField extends SimpleGeoField implements Reloadable, GeoFieldIF
{
  private TreeSet<String> filterSet;

  public CompositeGeoField()
  {
    super();

    this.filterSet = new TreeSet<String>();
  }

  public void addField(GeoFieldIF field)
  {
    String filterType = field.getFilterType();

    if (filterType != null && filterType.length() > 0)
    {
      this.setFilterType(filterType);
    }

    if (field.getIsSprayHierarchy())
    {
      this.setSprayHierarchy(true);
    }

    if (field.getIsPopulationHierarchy())
    {
      this.setPopulationHierarchy(true);
    }

    if (field.getIsPoliticalHierarchy())
    {
      this.setPoliticalHierarchy(true);
    }

    if (field.getIsUrbanHierarchy())
    {
      this.setUrbanHierarchy(true);
    }

    String[] extra = field.getExtraUniversals();

    this.addExtraUniversals(Arrays.asList(extra));
  }

  @Override
  public void setFilterType(String filter)
  {
    this.filterSet.add(filter);
  }

  @Override
  public String getFilterType()
  {
    StringBuffer buffer = new StringBuffer();

    for (String filter : this.filterSet)
    {
      buffer.append("," + "'" + filter + "'");
    }

    String filterType = buffer.toString().replaceFirst(",", "");

    return "[" + filterType + "]";
  }
}
