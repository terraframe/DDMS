package dss.vector.solutions.general;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;

public class ThresholdAlertCalculationType extends ThresholdAlertCalculationTypeBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 168898279;

  public ThresholdAlertCalculationType()
  {
    super();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
  }

  @Override
  protected String buildKey()
  {
    return this.getDisease().getKeyName();
  }

  public static ThresholdAlertCalculationType getCurrent()
  {
    return getCurrent(Disease.getCurrent());
  }
  
  public static ThresholdAlertCalculationType getCurrent(Disease disease)
  {
    return getByKey(disease.getKeyName());
  }

  public static List<GeoHierarchy> getEpidemicUniversals()
  {
    List<GeoHierarchy> list = new LinkedList<GeoHierarchy>();
    ThresholdAlertCalculationTypeQuery query = new ThresholdAlertCalculationTypeQuery(new QueryFactory());
    OIterator<? extends ThresholdAlertCalculationType> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        ThresholdAlertCalculationType configuration = it.next();
        
        list.add(configuration.getEpidemicUniversal());
      }
      
      return list;
    }
    finally
    {
      it.close();
    }
  }
}
