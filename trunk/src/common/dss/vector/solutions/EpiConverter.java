package dss.vector.solutions;

import java.util.Date;

public class EpiConverter
{
  protected Integer period;

  protected Integer year;

  protected Date    startDate;

  protected Date    endDate;

  public EpiConverter(int period, Integer year)
  {
    this.period = period;
    this.year = year;
  }

  public EpiConverter(Date startDate, Date endDate)
  {
    this.startDate = startDate;
    this.endDate = endDate;
  }


  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public Integer getPeriod()
  {
    return period;
  }

  public Integer getYear()
  {
    return year;
  }
}
