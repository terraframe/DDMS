/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.runwaysdk.constants.Constants;
import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSQLDate;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.PeriodMonthProblem;
import dss.vector.solutions.PeriodQuarterProblem;
import dss.vector.solutions.PeriodWeekProblem;
import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.surveillance.PeriodType;
import dss.vector.solutions.util.QueryUtil;

public class EpiDate extends EpiDateBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243373494497L;

  public EpiDate()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    return this.getPeriod() + " - " + this.getYear();
  }

  public static EpiDate getInstanceByPeriod(PeriodType periodType, Integer period, Integer year)
  {
    EpiDate newInstance = new EpiDate();
    newInstance.construct(periodType, period, year);
    return newInstance;
  }

  public static EpiDate getInstanceByDate(Date startDate, Date endDate)
  {
    EpiDate newInstance = new EpiDate();
    newInstance.construct(startDate, endDate);
    return newInstance;
  }

  // First Period is Zero
  public void construct(PeriodType periodType, int period, Integer year)
  {
    super.clearPeriodType();
    super.setPeriod(period);
    super.setEpiYear(year);
    super.addPeriodType(periodType);

    if (periodType.equals(PeriodType.WEEK))
    {
      Integer numberOfWeeks = EpiDate.getNumberOfEpiWeeks(year);
      
      while(period >= numberOfWeeks)
      {
        period = period - numberOfWeeks;
        year = year + 1;
        
        numberOfWeeks = EpiDate.getNumberOfEpiWeeks(year);
      }
            
      super.setPeriod(period);
      super.setEpiYear(year);
      
      Calendar calendar = getEpiCalendar(year);
      calendar.add(Calendar.WEEK_OF_YEAR, period);
      
      Date startDate = calendar.getTime();
      
      calendar.add(Calendar.WEEK_OF_YEAR, 1);
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      
      Date endDate = calendar.getTime();

      super.setStartDate(startDate);
      super.setEndDate(endDate);
    }
    else if (periodType.equals(PeriodType.MONTH))
    {
      // January == 0, December == 11
      Calendar calendar = makeRegularCalendar(year);

      calendar.set(Calendar.MONTH, period - 1);
      super.setStartDate(calendar.getTime());
      calendar.add(Calendar.MONTH, 1);
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      super.setEndDate(calendar.getTime());
    }
    else if (periodType.equals(PeriodType.QUARTER))
    {
      // January == 0, December == 11
      Calendar calendar = makeRegularCalendar(year);

      calendar.set(Calendar.MONTH, 3 * ( period - 1 ));
      super.setStartDate(calendar.getTime());
      calendar.add(Calendar.MONTH, 3);
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      super.setEndDate(calendar.getTime());
    }
  }

  public void construct(Date startDate, Date endDate)
  {
    GregorianCalendar calendar = makeRegularCalendar(startDate);

    super.clearPeriodType();
    super.setStartDate(startDate);
    super.setEndDate(endDate);
    super.setEpiYear(calendar.get(Calendar.YEAR));

    if (plusOneWeek(startDate).equals(endDate))
    {
      calendar = getEpiCalendar(getEpiYear());
      calendar.setTime(startDate);

      super.addPeriodType(PeriodType.WEEK);
      super.setPeriod(calendar.get(Calendar.WEEK_OF_YEAR));
    }
    else if (plusOneMonth(startDate).equals(endDate))
    {
      // January == 0, December == 11, Valid Quarter months are 1 - 12
      int month = calendar.get(Calendar.MONTH) + 1;

      // Calendar.month is zero indexed, while period is 1 indexed
      super.setPeriod(month);
      super.addPeriodType(PeriodType.MONTH);
    }
    else
    {
      // January == 0, December == 11, Valid Quarter Periods are 1 - 4
      int month = calendar.get(Calendar.MONTH);

      // Calendar.month is zero indexed, while period is 1 indexed
      super.setPeriod( ( month / 3 ) + 1);
      super.addPeriodType(PeriodType.QUARTER);
    }
  }

  private static GregorianCalendar getEpiCalendar(int year)
  {
    int startDay = Property.getInt(PropertyInfo.EPI_WEEK_PACKAGE, PropertyInfo.EPI_START_DAY);
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.setFirstDayOfWeek(startDay+1);
    // The last day of the first epi week is the first SAT in Jan provided it is
    // 4 or more days in.
    cal.setMinimalDaysInFirstWeek(4);
    cal.set(Calendar.YEAR, year);
    cal.set(Calendar.WEEK_OF_YEAR, 1);
    //cal.add(Calendar.DAY_OF_WEEK, 1);
    return cal;
  }

  private GregorianCalendar makeRegularCalendar(int year)
  {
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.set(year, 0, 1);

    return cal;
  }

  private GregorianCalendar makeRegularCalendar(Date date)
  {
    GregorianCalendar cal = new GregorianCalendar();
    cal.clear();
    cal.setTime(date);
    return cal;
  }

  public GregorianCalendar getEpiCal()
  {
    return getEpiCalendar(getEpiYear());
  }

  private Date plusOneMonth(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.MONTH, 1);
    c1.add(Calendar.DAY_OF_MONTH, -1);
    return c1.getTime();
  }

  private Date plusOneWeek(Date startDate)
  {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startDate);
    c1.add(Calendar.DAY_OF_YEAR, 6);
    return c1.getTime();
  }

  public Integer getNumberOfEpiWeeks()
  {
    return EpiDate.getNumberOfEpiWeeks(this.getEpiYear());
  }

  public Integer getActualPeriod()
  {
    if (this.getPeriodType().contains(PeriodType.WEEK))
    {
      Integer period = this.getPeriod();
      Integer numberOfEpiWeeks = this.getNumberOfEpiWeeks();

      while (period > numberOfEpiWeeks)
      {
        period = period % numberOfEpiWeeks;
      }

      return period;
    }
    
    return this.getPeriod();
  }

  public Integer getActualYear()
  {
    if (this.getPeriodType().contains(PeriodType.WEEK))
    {
      int year = this.getYear();
      int period = this.getPeriod();
      int numberOfEpiWeeks = this.getNumberOfEpiWeeks();

      while (period > numberOfEpiWeeks)
      {
        period = period % numberOfEpiWeeks;
        year++;
      }

      return year++;
    }
    
    return this.getYear();
  }
  
  public EpiDate getPrevious() {
	  EpiDate previousDate = null;
	  if (this.getPeriodType().contains(PeriodType.WEEK)) {
		  if (this.getPeriod() == 0) {
			  previousDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, EpiDate.getNumberOfEpiWeeks(this.getYear()-1)-1, this.getYear()-1);
		  } else {
			  previousDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, this.getPeriod() - 1, this.getYear());
		  }
  		}
  	return previousDate;
  }  
  
  public EpiDate getNext() {
	  EpiDate nextDate = null;
	  if (this.getPeriodType().contains(PeriodType.WEEK)) {
		  if (this.getPeriod() == EpiDate.getNumberOfEpiWeeks(this.getYear()) - 1) {
			  nextDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, 1, this.getYear()+1);
		  } else {
			  nextDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, this.getPeriod() + 1, this.getYear());
		  }
  		}
  	return nextDate;
  }

  public static Integer getNumberOfEpiWeeks(int year)
  {
    Calendar thisYear = getEpiCalendar(year);
    Calendar nextYear = getEpiCalendar(year + 1);
    // add 52 weeks + 1 day and see if that puts us into the next epi year
    thisYear.add(Calendar.WEEK_OF_YEAR, 52);
    thisYear.add(Calendar.DAY_OF_WEEK, 1);
    if (nextYear.after(thisYear))
    {
      return 53;
    }
    else
    {
      return 52;
    }
  }

  public static Date snapToEpiWeek(Date startDate, Boolean snapToFirstDay)
  {
    int period = Calendar.DAY_OF_WEEK;

    Calendar cal = new GregorianCalendar();
    cal.clear();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    
    int piviot = cal.get(period) - cal.getFirstDayOfWeek();
    
    if (piviot < 0)
    {
      piviot = 7 + piviot;
    }
    
    int days_before_piviot = piviot ;
    
    int days_after_piviot = 6 - piviot  ;

    // beginning of week wins in case of tie

    if (snapToFirstDay == null)
    {
      snapToFirstDay = ( days_before_piviot < days_after_piviot );
    }

    if (snapToFirstDay)
    {
      cal.add(Calendar.DAY_OF_YEAR, -days_before_piviot);
    }
    else
    {
      cal.add(Calendar.DAY_OF_YEAR, days_after_piviot);
    }
    
    Date d = cal.getTime();
    
    return d;
  }

  public static Date snapToMonth(Date startDate, Boolean snapToFirstDay)
  {
    int period = Calendar.DAY_OF_MONTH;

    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    int piviot = cal.get(period);
    int max = cal.getActualMaximum(period);
    int min = cal.getActualMinimum(period);

    int days_before_piviot = piviot - min;
    int days_after_piviot = max - piviot;

    // beginning of month wins in case of tie
    if (snapToFirstDay == null)
    {
      snapToFirstDay = ( days_before_piviot < days_after_piviot );
    }

    if (snapToFirstDay)
    {
      cal.add(Calendar.DAY_OF_YEAR, -days_before_piviot);
    }
    else
    {
      cal.add(Calendar.DAY_OF_YEAR, days_after_piviot);
    }
    return cal.getTime();
  }

  public static Date snapToQuarter(Date startDate, Boolean snapToFirstDay)
  {
    int period = Calendar.DAY_OF_YEAR;

    
    
    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    int quarter = ( ( cal.get(Calendar.MONTH)  ) / 3 );
    Calendar startOfQuarter = new GregorianCalendar(cal.get(Calendar.YEAR), quarter * 3, 1);
    Calendar endOfQuarter = (Calendar) startOfQuarter.clone();
    endOfQuarter.add(Calendar.MONTH, 3);
    endOfQuarter.add(Calendar.DAY_OF_YEAR, -1);
    
    //Date quarterStartDate = startOfQuarter.getTime();
    //Date quarterEndDate = endOfQuarter.getTime();
    
    //MdssLog.debug();
    //MdssLog.debug(cal.get(Calendar.MONTH));
    //MdssLog.debug(startDate);
    //MdssLog.debug(quarter);
    //MdssLog.debug(quarterStartDate);
    //MdssLog.debug(quarterEndDate);

    int piviot = cal.get(period);
    int min = startOfQuarter.get(period);
    int max = endOfQuarter.get(period);

    int days_before_piviot = piviot - min;
    int days_after_piviot = max - piviot;

    // begining of month wins in case of tie
    if (snapToFirstDay == null)
    {
      snapToFirstDay = ( days_before_piviot < days_after_piviot );
    }

    if (snapToFirstDay)
    {
      cal.add(Calendar.DAY_OF_YEAR, -days_before_piviot);
    }
    else
    {
      cal.add(Calendar.DAY_OF_YEAR, days_after_piviot);
    }
    return cal.getTime();
  }

  public static Date snapToYear(Date startDate, Boolean snapToFirstDay)
  {
    int period = Calendar.DAY_OF_YEAR;

    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    cal = getEpiCalendar(cal.get(Calendar.YEAR));
    cal.setTime(startDate);

    int piviot = cal.get(period);
    int max = cal.getActualMaximum(period);
    int min = cal.getActualMinimum(period);

    int days_before_piviot = piviot - min;
    int days_after_piviot = max - piviot;

    // beginning of year wins in case of tie
    if (snapToFirstDay == null)
    {
      snapToFirstDay = ( days_before_piviot < days_after_piviot );
    }

    if (snapToFirstDay)
    {
      cal.set(period, min);
    }
    else
    {
      cal.set(period, max);
    }
    return cal.getTime();
  }
  

  public static int getEpiYearFromDate(Date date)
  {

    Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    int year =cal.get(Calendar.YEAR);
    
    
    Date thisYear = getEpiCalendar(year).getTime();
    Date nextYear = getEpiCalendar(year + 1).getTime();
    
    //after the start of next epi year
    if (date.compareTo(nextYear) >= 0 )
    {
      year = year + 1;
    }
    //before the start of this epi year
    else if(date.compareTo(thisYear) < 0 )
    {
      year = year - 1;
    }

    
    return year;
     
  }
  
  
  
  public static Date snapToEpiYear(Date startDate, Boolean snapToFirstDay)
  {

    Date start =  getEpiCalendar(getEpiYearFromDate(startDate)).getTime();
    Date end =  getEpiCalendar(getEpiYearFromDate(startDate)+1).getTime();

    long piviot = startDate.getTime();
    long max = end.getTime();
    long min = start.getTime();

    long milis_before_piviot = piviot - min;
    long milis_after_piviot = max - piviot;

    // beginning of year wins in case of tie
    if (snapToFirstDay == null)
    {
      snapToFirstDay = ( milis_before_piviot < milis_after_piviot );
    }

    if (snapToFirstDay)
    {
      return start;
    }
    else
    {
      return new Date(max - 1000*60*60*24);
    }

  }

  public static Date snapToSeason(Date snapable, Boolean snapToFirstDay)
  {
    if (snapToFirstDay == null)
    {
      snapToFirstDay = true;
    }

    SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    MalariaSeasonQuery seasonQuery = new MalariaSeasonQuery(queryFactory);

    String startOrEnd = null;

    if (snapToFirstDay)
    {
      startOrEnd = QueryUtil.getColumnName(MalariaSeason.getStartDateMd());
      valueQuery.SELECT(seasonQuery.getStartDate(startOrEnd));
      SelectableSQLDate dist = valueQuery.aSQLDate("dist", "(age(" + startOrEnd + ", '" + formatter.format(snapable) + "'))", "dist");
      valueQuery.ORDER_BY_DESC(dist);
      valueQuery.WHERE(seasonQuery.getStartDate(startOrEnd).LE(formatter.format(snapable)));
    }
    else
    {
      startOrEnd = QueryUtil.getColumnName(MalariaSeason.getEndDateMd());
      valueQuery.SELECT(seasonQuery.getEndDate(startOrEnd));
      SelectableSQLDate dist = valueQuery.aSQLDate("dist", "(age(" + startOrEnd + ", '" + formatter.format(snapable) + "'))", "dist");
      valueQuery.ORDER_BY_ASC(dist);
      valueQuery.WHERE(seasonQuery.getEndDate(startOrEnd).GE(formatter.format(snapable)));
    }


    OIterator<? extends ValueObject> iterator = valueQuery.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        ValueObject valueObject = iterator.next();
        try
        {
          return formatter.parse(valueObject.getValue(startOrEnd));
        }
        catch (ParseException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      else
      {
        String msg = "This date does not fall in to a season";
        // throw new InvalidIdException(msg, geoId);
      }
    }
    finally
    {
      iterator.close();
    }
    return snapable;
  }

  public PeriodType getEpiPeriodType()
  {
    return (PeriodType) this.getPeriodType().get(0);
  }

  public Integer getYear()
  {
    return super.getEpiYear();
  }

  public static void validate(PeriodType periodType, Integer period, Integer year)
  {
    if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.QUARTER))
    {
      PeriodQuarterProblem p = new PeriodQuarterProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.MONTH))
    {
      PeriodMonthProblem p = new PeriodMonthProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
    else if (period > periodType.getMaximumPeriod() && periodType.equals(PeriodType.WEEK))
    {
      PeriodWeekProblem p = new PeriodWeekProblem();
      p.setPeriod(period);
      p.setMaxPeriod(periodType.getMaximumPeriod());
      p.throwIt();
    }
  }

  public static EpiDate getEpiWeek(Date date)
  {
    Calendar instance = Calendar.getInstance();
    instance.setTime(date);
    
    Calendar calendar = getEpiCalendar(instance.get(Calendar.YEAR));
    calendar.setTime(date);

    // IMPORTANT: The periods in the system are 0 based while the week_of_year
    // is 1 based. Therefore to get the actual period of the system you must
    // subtract 1 from the week of the year.
    int period = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
    int year = calendar.get(Calendar.YEAR);

    EpiDate epiDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, period, year);
    
    if(epiDate.getEndDate().before(date) && !epiDate.getEndDate().equals(date))
    {
      period = EpiDate.getNumberOfEpiWeeks(year);
      
      epiDate = EpiDate.getInstanceByPeriod(PeriodType.WEEK, period, year);
    }
    
    
    return epiDate;
  }

}
