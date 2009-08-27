package dss.vector.solutions.intervention.monitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.surveillance.PeriodType;

public class AggregatedIPT extends AggregatedIPTBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244737089690L;

  public AggregatedIPT()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getGeoEntity() != null && this.getStartDate() != null && this.getEndDate() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);

      return this.getGeoEntity().getGeoId() + "." + format.format(this.getStartDate()) + "-" + format.format(this.getEndDate());

    }
    return this.getId();
  }

  public static AggregatedIPT searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate)
  {
    AggregatedIPTQuery query = new AggregatedIPTQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));

    OIterator<? extends AggregatedIPT> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  @Override
  public void apply()
  {
    validateStartDate();
    validateEndDate();

    super.apply();
  }

  @Override
  public void validateStartDate()
  {
    if (this.getStartDate() != null)
    {
      super.validateStartDate();

      Date current = new Date();

      if (current.before(this.getStartDate()))
      {
        String msg = "It is impossible to have a start date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getStartDate());
        p.setCurrentDate(current);
        p.setNotification(this, STARTDATE);
        p.apply();
        p.throwIt();
      }
    }
  }
  
  @Override
  public void validateEndDate()
  {
    if (this.getEndDate() != null)
    {
      super.validateEndDate();

      Date current = new Date();

      if (current.before(this.getEndDate()))
      {
        String msg = "It is impossible to have a end date after the current date";

        CurrentDateProblem p = new CurrentDateProblem(msg);
        p.setGivenDate(this.getEndDate());
        p.setCurrentDate(current);
        p.setNotification(this, ENDDATE);
        p.apply();
        p.throwIt();
      }
    }
  }

  public static AggregatedIPTView searchByGeoEntityAndEpiDate(GeoEntity geoEntity, PeriodType periodType, Integer period, Integer year)
  {
    EpiDate.validate(periodType, period, year);

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, period, year);

    AggregatedIPT concrete = searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date.getEndDate());

    if (concrete != null)
    {
      return concrete.getView();
    }

    AggregatedIPTView view = new AggregatedIPTView();
    view.setGeoId(geoEntity.getGeoId());
    view.setPeriod(period);
    view.addPeriodType(periodType);
    view.setPeriodYear(year);

    return view;
  }

  public static AggregatedIPTView getView(String id)
  {
    return AggregatedIPT.get(id).getView();
  }

  public AggregatedIPTView getView()
  {
    AggregatedIPTView view = new AggregatedIPTView();
    view.populateView(this);

    return view;
  }

  @Override
  @Transaction
  public void lock()
  {
    super.lock();

    // Lock the grid relationship also, this must be in the same transaction
    for (IPTPatients patients : this.getAllPatientsRel())
    {
      patients.lock();
    }

    for (IPTANCVisit visit : this.getAllANCVisitsRel())
    {
      visit.lock();
    }

    for (IPTDose dose : this.getAllDosesRel())
    {
      dose.lock();
    }

    for (IPTTreatment treatment : this.getAllTreatmentsRel())
    {
      treatment.lock();
    }
  }

  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();

    // Lock the grid relationship also, this must be in the same transaction
    for (IPTPatients patients : this.getAllPatientsRel())
    {
      patients.unlock();
    }

    for (IPTANCVisit visit : this.getAllANCVisitsRel())
    {
      visit.unlock();
    }

    for (IPTDose dose : this.getAllDosesRel())
    {
      dose.unlock();
    }

    for (IPTTreatment treatment : this.getAllTreatmentsRel())
    {
      treatment.unlock();
    }
  }

  @Override
  public AggregatedIPTView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public AggregatedIPTView lockView()
  {
    this.lock();

    return this.getView();
  }

}
