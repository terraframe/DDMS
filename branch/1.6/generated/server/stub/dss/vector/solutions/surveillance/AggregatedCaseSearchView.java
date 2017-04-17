package dss.vector.solutions.surveillance;

import java.util.Date;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;

public class AggregatedCaseSearchView extends AggregatedCaseSearchViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -45175735;

  public AggregatedCaseSearchView()
  {
    super();
  }

  @Override
  public AggregatedCaseView searchByView()
  {
    if (this.getSearchType())
    {
      return AggregatedCaseSearchView.searchByDates(this.getGeoEntity(), this.getStartDate(), this.getEndDate(), this.getAgeGroup());
    }
    else
    {
      PeriodType _periodType = this.getPeriodType().get(0);

      return AggregatedCaseSearchView.searchByGeoEntityAndEpiDate(this.getGeoEntity(), _periodType, this.getPeriod(), this.getPeriodYear(), this.getAgeGroup());
    }
  }

  @Transaction
  public static AggregatedCaseView searchByGeoEntityAndEpiDate(GeoEntity geoEntity, PeriodType periodType, Integer period, Integer year, AggregatedAgeGroup ageGroup)
  {
    // IMPORTANT: WEEK is 0 based while MONTH and QUARTER are 1 based. Thus we
    // need to offset the 'period' for WEEK
    Integer _period = ( periodType.equals(PeriodType.WEEK) ? period - 1 : period );

    // IMPORTANT: Validation is 1 based even if the period type is WEEK
    EpiDate.validate(periodType, period, year);

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, _period, year);

    Date startDate = date.getStartDate();
    Date endDate = date.getEndDate();

    return AggregatedCaseSearchView.searchByDates(geoEntity, startDate, endDate, ageGroup);
  }

  public static AggregatedCaseView searchByDates(GeoEntity geoEntity, Date startDate, Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCase concrete = AggregatedCaseSearchView.searchByGeoEntityAndDate(geoEntity, startDate, endDate, ageGroup);

    if (concrete != null)
    {
      AggregatedCaseView view = concrete.getView();
      view.setAgeGroup(ageGroup);
      view.setConcreteId(concrete.getId());

      return view;
    }

    AggregatedCaseView view = new AggregatedCaseView();
    view.setGeoEntity(geoEntity);
    view.setStartDate(startDate);
    view.setEndDate(endDate);
    view.setAgeGroup(ageGroup);

    return view;
  }

  public static AggregatedCase searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate, AggregatedAgeGroup ageGroup)
  {
    AggregatedCaseQuery query = new AggregatedCaseQuery(new QueryFactory());
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
    query.AND(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));
    query.AND(query.getAgeGroup().EQ(ageGroup));

    OIterator<? extends AggregatedCase> it = query.getIterator();

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

}
