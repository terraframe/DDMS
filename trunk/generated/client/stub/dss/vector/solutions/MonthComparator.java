package dss.vector.solutions;

import java.util.Comparator;

import com.terraframe.mojo.generation.loader.Reloadable;

public class MonthComparator implements Comparator<MonthOfYearMasterDTO>, Reloadable
{

  @Override
  public int compare(MonthOfYearMasterDTO month1, MonthOfYearMasterDTO month2)
  {
    return this.getIndex(month1).compareTo(this.getIndex(month2));
  }
  
  private Integer getIndex(MonthOfYearMasterDTO month)
  {
    String enumName = month.getEnumName();

    if(enumName.equalsIgnoreCase(MonthOfYear.JANUARY.name()))
    {
      return 1;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.FEBRUARY.name()))
    {
      return 2;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.MARCH.name()))
    {
      return 3;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.APRIL.name()))
    {
      return 4;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.MAY.name()))
    {
      return 5;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.JUNE.name()))
    {
      return 6;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.JULY.name()))
    {
      return 7;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.AUGUST.name()))
    {
      return 8;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.SEPTEMBER.name()))
    {
      return 9;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.OCTOBER.name()))
    {
      return 10;
    }
    
    if(enumName.equalsIgnoreCase(MonthOfYear.NOVEMBER.name()))
    {
      return 11;
    }
    
    return 12;
  }

}
