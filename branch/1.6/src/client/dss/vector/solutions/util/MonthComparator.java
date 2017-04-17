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
