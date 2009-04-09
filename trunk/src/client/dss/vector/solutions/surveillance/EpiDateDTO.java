package dss.vector.solutions.surveillance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.generation.loader.Reloadable;

public class EpiDateDTO implements Reloadable
{
  private PeriodTypeDTO periodType;

  private Integer period;

  private String year;

  private Date startDate;

  private Date endDate;

  public EpiDateDTO(PeriodTypeDTO periodType, Integer period, String year)
  {
    this.periodType = periodType;
    this.period = period;
    this.year = year;

    try
    {
      if (periodType.equals(PeriodTypeDTO.QUARTER))
      {
        initializeQuarter(period, year);
      }
      else if(periodType.equals(PeriodTypeDTO.MONTH))
      {
        initializeMonth(period, year);
      }
      else if(periodType.equals(PeriodTypeDTO.WEEK))
      {
        initalizeWeek(period, year);
      }
    }
    catch (ParseException e)
    {
      //TODO change this runtime exception
      throw new RuntimeException(e);
    }
  }

  private void initalizeWeek(Integer period, String year) throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    Calendar c1 = Calendar.getInstance();
    c1.setTime(format.parse(year));

    c1.set(Calendar.DATE, 1);
    c1.add(Calendar.WEEK_OF_YEAR, period);

    startDate = c1.getTime();

    c1.add(Calendar.DAY_OF_MONTH, 6);
    endDate = c1.getTime();
  }

  private void initializeMonth(Integer period, String year) throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    Calendar c1 = Calendar.getInstance();
    c1.setTime(format.parse(year));
    c1.set(Calendar.DATE, 1);
    c1.set(Calendar.MONTH, period);

    startDate = c1.getTime();

    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    endDate = c1.getTime();
  }

  private void initializeQuarter(Integer period, String year) throws ParseException
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");

    Calendar c1 = Calendar.getInstance();
    c1.setTime(format.parse(year));
    c1.set(Calendar.DATE, 1);

    switch (period)
    {
      case 1:
        c1.set(Calendar.MONTH, 1);
        break;
      case 2:
        c1.set(Calendar.MONTH, 4);
        break;
      case 3:
        c1.set(Calendar.MONTH, 7);
        break;
      default:
        c1.set(Calendar.MONTH, 10);
        break;
    }

    startDate = c1.getTime();

    c1.add(Calendar.MONTH, 3);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    endDate = c1.getTime();
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public PeriodTypeDTO getPeriodType()
  {
    return periodType;
  }

  public Integer getPeriod()
  {
    return period;
  }

  public String getYear()
  {
    return year;
  }

  public String getDisplayLabel(ClientRequestIF clientRequest)
  {
    PeriodTypeMasterDTO item = periodType.item(clientRequest);

    return item.getDisplayLabel() + " " + period + " - " + year;
  }
}
