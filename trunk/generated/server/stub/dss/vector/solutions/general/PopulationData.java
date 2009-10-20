package dss.vector.solutions.general;

import java.util.Calendar;

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
  public void apply()
  {
    this.validateGeoEntity();
    this.validateYearOfData();

    super.apply();
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
