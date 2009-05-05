package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EpiWeek extends EpiConverter
{
  public EpiWeek(int period, Integer year, Date offset)
  {
    super(period, year);

    GregorianCalendar c2 = new GregorianCalendar();
    c2.setTime(offset);

    Calendar c1 = Calendar.getInstance();
    c1.clear();
    c1.set(year, 1, 1, 0, 0, 0);
    c1.set(Calendar.DATE, 1);
    c1.add(Calendar.WEEK_OF_YEAR, period);

    int dayOfYear = c2.get(Calendar.DAY_OF_YEAR);
    int offsetAmount = c1.before(c2) ? dayOfYear:-dayOfYear;
    c1.add(Calendar.DAY_OF_YEAR, offsetAmount);
    
    startDate = c1.getTime();    

    c1.add(Calendar.DAY_OF_YEAR, 6);
    endDate = c1.getTime();
  }

  public EpiWeek(Date startDate, Date endDate, Date offset)
  {
    super(startDate, endDate);

    GregorianCalendar c2 = new GregorianCalendar();
    c2.setTime(offset);

    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    
    int dayOfYear = c2.get(Calendar.DAY_OF_YEAR);
    int offsetAmount = c1.before(c2) ? -dayOfYear:dayOfYear;
    c1.add(Calendar.DAY_OF_YEAR, offsetAmount);


    this.period = c1.get(Calendar.WEEK_OF_YEAR);
    this.year = c1.get(Calendar.YEAR);
  }
}
