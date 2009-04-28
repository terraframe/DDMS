package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;

public class EpiWeek {
    private Integer period;

    private Integer year;

    private Date startDate;

    private Date endDate;

    public EpiWeek(int period, Integer year) {
        this.period = period;
        this.year = year;

        initalizeWeek(period, year);
    }

    public EpiWeek(Date startDate, Date endDate)
    {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);

        this.period = c1.get(Calendar.WEEK_OF_YEAR);
        this.year = c1.get(Calendar.YEAR);
        this.startDate = startDate;
        this.endDate = endDate;
    }


    private void initalizeWeek(Integer period, Integer year)
    {
      Calendar c1 = Calendar.getInstance();
      c1.clear();
      c1.set(year, 1, 1, 0, 0, 0);
      c1.set(Calendar.DATE, 1);
      c1.add(Calendar.WEEK_OF_YEAR, period);

      startDate = c1.getTime();

      c1.add(Calendar.DAY_OF_YEAR, 6);
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

    public Integer getPeriod()
    {
      return period;
    }

    public Integer getYear()
    {
      return year;
    }

}
