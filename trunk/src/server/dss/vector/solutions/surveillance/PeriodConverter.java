package dss.vector.solutions.surveillance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PeriodConverter
{
  private Date startDate;

  private Date endDate;

  public PeriodConverter(PeriodType periodType, int period, String year)
  {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");
    try
    {
      Calendar c1 = Calendar.getInstance();
      c1.setTime(format.parse(year));
      // c1.add(Calendar.DATE,20);

      if (periodType.equals(PeriodType.QUARTER))
      {
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
      else if(periodType.equals(PeriodType.MONTH))
      {
        c1.set(Calendar.DATE, 1);
        c1.set(Calendar.MONTH, period);

        startDate = c1.getTime();

        c1.add(Calendar.MONTH, 1);
        c1.add(Calendar.DAY_OF_MONTH, -1);
        endDate = c1.getTime();
      }
      else if(periodType.equals(PeriodType.WEEK))
      {
        c1.set(Calendar.DATE, 1);
        c1.add(Calendar.WEEK_OF_YEAR, period);

        startDate = c1.getTime();

        c1.add(Calendar.DAY_OF_MONTH, 6);
        endDate = c1.getTime();        
      }
    }
    catch (ParseException e)
    {
      //TODO change this runtime exception
      throw new RuntimeException(e);
    }
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

}
