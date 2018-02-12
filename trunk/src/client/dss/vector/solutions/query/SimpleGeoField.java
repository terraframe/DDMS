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

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class SimpleGeoField implements GeoFieldIF
{
  private Set<String> extraUniversals;

  private boolean     sprayHierarchy;

  private boolean     populationHierarchy;

  private boolean     politicalHierarchy;

  private boolean     urbanHierarchy;

  private String      filter;

  public SimpleGeoField()
  {
    this.extraUniversals = new TreeSet<String>();
    this.sprayHierarchy = false;
    this.populationHierarchy = false;
    this.politicalHierarchy = false;
    this.urbanHierarchy = false;
    this.filter = null;
  }

  @Override
  public String[] getExtraUniversals()
  {
    return extraUniversals.toArray(new String[extraUniversals.size()]);
  }

  @Override
  public String getFilterType()
  {
    return this.filter;
  }

  @Override
  public Boolean getIsPoliticalHierarchy()
  {
    return politicalHierarchy;
  }

  @Override
  public Boolean getIsPopulationHierarchy()
  {
    return populationHierarchy;
  }

  @Override
  public Boolean getIsSprayHierarchy()
  {
    return sprayHierarchy;
  }

  @Override
  public Boolean getIsUrbanHierarchy()
  {
    return urbanHierarchy;
  }

  public void addExtraUniversal(String extraUniversal)
  {
    this.extraUniversals.add(extraUniversal);
  }

  public void addExtraUniversals(Collection<String> extraUniversals)
  {
    this.extraUniversals.addAll(extraUniversals);
  }

  public void setFilterType(String filter)
  {
    this.filter = filter;
  }

  public void setExtraUniversals(Set<String> extraUniversals)
  {
    this.extraUniversals = extraUniversals;
  }

  public void setSprayHierarchy(boolean sprayHierarchy)
  {
    this.sprayHierarchy = sprayHierarchy;
  }

  public void setPopulationHierarchy(boolean populationHierarchy)
  {
    this.populationHierarchy = populationHierarchy;
  }

  public void setPoliticalHierarchy(boolean politicalHierarchy)
  {
    this.politicalHierarchy = politicalHierarchy;
  }

  public void setUrbanHierarchy(boolean urbanHierarchy)
  {
    this.urbanHierarchy = urbanHierarchy;
  }
}
