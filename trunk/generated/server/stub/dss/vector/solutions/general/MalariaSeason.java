package dss.vector.solutions.general;

import java.util.Date;
import java.util.GregorianCalendar;

import com.ibm.icu.util.Calendar;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionAllreadyExistsException;
import dss.vector.solutions.entomology.MosquitoCollectionQuery;

public class MalariaSeason extends MalariaSeasonBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242259530708L;

  public MalariaSeason()
  {
    super();
  }

  @Override
  public void apply()
  {
    super.validateEndDate();
    super.validateEndDate();

    validateStartEndDates();
    validateLength();
    validateOverlap();

    super.apply();
  }

  public void validateStartEndDates()
  {
    if (this.getStartDate().getTime() > this.getEndDate().getTime())
    {
      String msg = "The start date must be less then the end date";
      MalariaSeasonDateProblem e = new MalariaSeasonDateProblem(msg);
      e.apply();
      e.throwIt();
    }
  }

  public void validateOverlap()
  {
    if (this.getOverlap() != null)
    {
      String msg = "This malaria season overlaps with an existing season";
      MalariaSeasonOverlapProblem e = new MalariaSeasonOverlapProblem(msg);
      e.setOverlap(this.getOverlap().getSeasonName());
      e.apply();
      e.throwIt();
    }
  }

  public void validateLength()
  {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(this.getStartDate());
    calendar.add(Calendar.YEAR,1);
    if(this.getEndDate().getTime() > calendar.getTime().getTime())
    {
      String msg = "The season may not be longer then one year";
      MalariaSeasonTooLongProblem e = new MalariaSeasonTooLongProblem(msg);
      e.apply();
      e.throwIt();
    }

  }


  private MalariaSeason getOverlap()
  {
    MalariaSeason startOverlap = MalariaSeason.getSeasonByDate(this.getStartDate());

    if (startOverlap != null && startOverlap.getId() != this.getId())
    {
      return startOverlap;
    }

    MalariaSeason endOverlap = MalariaSeason.getSeasonByDate(this.getEndDate());

    if (endOverlap != null && endOverlap.getId() != this.getId())
    {
      return endOverlap;
    }

    return null;
  }

  public static MalariaSeason getSeasonByDate(Date date)
  {

    MalariaSeason malariaSeason = null;

    QueryFactory factory = new QueryFactory();
    MalariaSeasonQuery query = new MalariaSeasonQuery(factory);

    query.AND(query.getStartDate().LE(date));
    query.AND(query.getEndDate().GE(date));

    OIterator<? extends MalariaSeason> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {

        malariaSeason = iterator.next();
      }

    }
    finally
    {
      iterator.close();
    }

    return malariaSeason;
  }
}
