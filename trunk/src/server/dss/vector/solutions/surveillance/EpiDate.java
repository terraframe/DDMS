package dss.vector.solutions.surveillance;

import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.EpiConverter;
import dss.vector.solutions.EpiMonth;
import dss.vector.solutions.EpiQuarter;
import dss.vector.solutions.EpiWeek;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;

public class EpiDate implements Reloadable
{
  private PeriodType type;

  private EpiConverter converter;

  public EpiDate(PeriodType periodType, int period, Integer year)
  {
    this.type = periodType;
    
    Date offset = Property.getDate(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START);

    if (periodType.equals(PeriodType.QUARTER))
    {
      this.converter = new EpiQuarter(period, year, offset);
    }
    else if (periodType.equals(PeriodType.MONTH))
    {
      this.converter = new EpiMonth(period, year, offset);
    }
    else if (periodType.equals(PeriodType.WEEK))
    {
      this.converter = new EpiWeek(period, year, offset);
    }
  }

  public EpiDate(Date startDate, Date endDate)
  {
    Date offset = Property.getDate(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START);

    if (this.getWeek(startDate).equals(endDate))
    {
      this.type = PeriodType.WEEK;
      this.converter = new EpiWeek(startDate, endDate, offset);
    }
    else if (this.getMonth(startDate).equals(endDate))
    {
      this.type = PeriodType.MONTH;
      this.converter = new EpiMonth(startDate, endDate, offset);
    }
    else
    {
      this.type = PeriodType.QUARTER;
      this.converter = new EpiQuarter(startDate, endDate, offset);
    }
  }

  private Date getMonth(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);

    return c1.getTime();
  }

  private Date getWeek(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.DAY_OF_YEAR, 6);

    return c1.getTime();
  }

  public Date getStartDate()
  {
    return converter.getStartDate();
  }

  public Date getEndDate()
  {
    return converter.getEndDate();
  }

  public PeriodType getType()
  {
    return type;
  }

  public Integer getPeriod()
  {
    return converter.getPeriod();
  }

  public Integer getYear()
  {
    return converter.getYear();
  }
}
