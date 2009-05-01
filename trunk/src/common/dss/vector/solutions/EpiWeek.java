package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;

public class EpiWeek extends EpiConverter
{
  public EpiWeek(int period, Integer year)
  {
    super(period, year);

    Calendar c1 = Calendar.getInstance();
    c1.clear();
    c1.set(year, 1, 1, 0, 0, 0);
    c1.set(Calendar.DATE, 1);
    c1.add(Calendar.WEEK_OF_YEAR, period);

    startDate = c1.getTime();

    c1.add(Calendar.DAY_OF_YEAR, 6);
    endDate = c1.getTime();
  }

  public EpiWeek(Date startDate, Date endDate)
  {
    super(startDate, endDate);

    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);

    this.period = c1.get(Calendar.WEEK_OF_YEAR);
    this.year = c1.get(Calendar.YEAR);
  }
}
