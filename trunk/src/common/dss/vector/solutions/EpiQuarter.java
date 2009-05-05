package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EpiQuarter extends EpiConverter
{
  public EpiQuarter(int period, Integer year, Date offset)
  {
    super(period, year);

    GregorianCalendar c2 = new GregorianCalendar();
    c2.setTime(offset);

    Calendar c1 = Calendar.getInstance();
    c1.clear();
    c1.set(year, 1, 1, 0, 0, 0);
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

    int dayOfYear = c2.get(Calendar.DAY_OF_YEAR);
    int offsetAmount = c1.before(c2) ? dayOfYear:-dayOfYear;
    c1.add(Calendar.DAY_OF_YEAR, offsetAmount);
    
    startDate = c1.getTime();
   
    c1.add(Calendar.MONTH, 3);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    endDate = c1.getTime();
  }

  public EpiQuarter(Date startDate, Date endDate, Date offset)
  {
    super(startDate, endDate);

    GregorianCalendar c2 = new GregorianCalendar();
    c2.setTime(offset);

    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);

    int dayOfYear = c2.get(Calendar.DAY_OF_YEAR);
    int offsetAmount = c1.before(c2) ? -dayOfYear:dayOfYear;
    c1.add(Calendar.DAY_OF_YEAR, offsetAmount);

    int month = c1.get(Calendar.MONTH);    
    this.year = c1.get(Calendar.YEAR);

    if (month < 4)
    {
      this.period = 1;
    }
    else if (month < 7)
    {
      this.period = 2;
    }
    else if (month < 10)
    {
      this.period = 3;
    }
    else
    {
      this.period = 4;
    }
  }

}
