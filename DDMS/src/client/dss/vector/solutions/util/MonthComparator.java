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

import java.util.Comparator;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.MonthOfYearDTO;
import dss.vector.solutions.MonthOfYearMasterDTO;

public class MonthComparator implements Comparator<MonthOfYearMasterDTO>, Reloadable
{

  public int compare(MonthOfYearMasterDTO month1, MonthOfYearMasterDTO month2)
  {
    return this.getIndex(month1).compareTo(this.getIndex(month2));
  }
  
  private Integer getIndex(MonthOfYearMasterDTO month)
  {
    String enumName = month.getEnumName();

    if(enumName.equalsIgnoreCase(MonthOfYearDTO.JANUARY.name()))
    {
      return 1;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.FEBRUARY.name()))
    {
      return 2;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.MARCH.name()))
    {
      return 3;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.APRIL.name()))
    {
      return 4;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.MAY.name()))
    {
      return 5;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.JUNE.name()))
    {
      return 6;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.JULY.name()))
    {
      return 7;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.AUGUST.name()))
    {
      return 8;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.SEPTEMBER.name()))
    {
      return 9;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.OCTOBER.name()))
    {
      return 10;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYearDTO.NOVEMBER.name()))
    {
      return 11;
    }
    
    return 12;
  }

}
