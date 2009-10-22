package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.Session;

import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntity;

public class PopulationData extends PopulationDataBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256052008761L;

  public PopulationData()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getGeoEntity().getKey() + " - " + this.getYearOfData();
  }

  public PopulationDataView getView()
  {
    PopulationDataView view = new PopulationDataView();
    view.populateView(this);

    return view;
  }

  @Override
  public PopulationDataView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public PopulationDataView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public void delete()
  {
    super.delete();

    if (!this.getEstimated() && this.getPopulation() != null)
    {
      this.updateFuturePopulations();
    }
  }

  private void updateFuturePopulations()
  {
    List<PopulationData> future = this.getFuturePopulationData();

    // Update all of the future populations until we find data with a population
    // value. We don't want to update the data after another entry, X, with
    // population because the estimated population values after year X are
    // based upon X's population value not this population value.
    for (PopulationData data : future)
    {
      if (data.getEstimated())
      {
        if (data.getGrowthRate() != null)
        {
          data.setPopulation(null);
          data.setEstimated(false);
          data.apply();
        }
        else
        {
          data.delete();
        }
      }
      else
      {
        return;
      }
    }
  }

  private List<PopulationData> getFuturePopulationData()
  {
    List<PopulationData> list = new LinkedList<PopulationData>();

    PopulationDataQuery query = new PopulationDataQuery(new QueryFactory());
    query.WHERE(AND.get(query.getGeoEntity().EQ(this.getGeoEntity()), query.getYearOfData().GT(this.getYearOfData())));
    query.ORDER_BY_ASC(query.getYearOfData());

    list.addAll(query.getIterator().getAll());

    return list;
  }

  @Override
  public void apply()
  {
    this.validateGeoEntity();
    this.validateYearOfData();
    this.validatePopulation();

    super.apply();

    if (!this.getEstimated() && this.getPopulation() != null)
    {
      this.updateFuturePopulations();
    }
  }

  @Override
  public void validatePopulation()
  {
    if (this.getPopulation() != null && this.getPopulation() < 0)
    {
      String msg = "Population cannot be less than 0";

      PopulationProblem p = new PopulationProblem(msg);
      p.setNotification(this, POPULATION);
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateGeoEntity()
  {
    GeoEntity entity = this.getGeoEntity();

    if (entity != null)
    {
      GeoHierarchy geoHierarchy = GeoHierarchy.getGeoHierarchyFromType(entity.getType());

      if (!geoHierarchy.getPopulationAllowed())
      {
        String universal = entity.getMdClass().getDisplayLabel(Session.getCurrentLocale());
        String geoEntityName = entity.getEntityName();

        String label = geoEntityName + " (" + universal + ")";

        String msg = "The Geo Entity [" + label + "] does not allow population values";

        GeoEntityPopulationProblem p = new GeoEntityPopulationProblem(msg);
        p.setEntityLabel(label);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateYearOfData()
  {
    if (this.getYearOfData() != null && !PopulationData.isValidYear(this.getYearOfData()))
    {
      Calendar calendar = Calendar.getInstance();

      int max = calendar.getActualMaximum(Calendar.YEAR);
      int min = calendar.getActualMinimum(Calendar.YEAR);

      String msg = "The year [" + this.getYearOfData() + " ] must be between [" + min + "] and [" + max + "]";

      RangeValueProblem p = new RangeValueProblem(msg);
      p.setNotification(this, YEAROFDATA);
      p.setLowerLimit(min);
      p.setUpperLimit(max);
      p.setInvalidValue(this.getYearOfData());
      p.apply();
      p.throwIt();
    }
  }

  public static boolean isValidYear(Integer year)
  {
    Calendar calendar = Calendar.getInstance();

    int max = calendar.getActualMaximum(Calendar.YEAR);
    int min = calendar.getActualMinimum(Calendar.YEAR);

    return ! ( year < min || year > max );
  }
}
