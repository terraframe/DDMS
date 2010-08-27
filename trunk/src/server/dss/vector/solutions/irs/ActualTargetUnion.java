package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;



public abstract class ActualTargetUnion extends AbstractTargetUnion implements Reloadable
{
  
  @Override
  public final String setSprayDate(Alias alias)
  {
//    return set("get_epiWeek_from_date("+this.sprayDateCol+","+this.startDay+")", alias);
    return set(this.sprayDateCol, alias);
  }

  @Override
  public final String setSpraySeason(Alias alias)
  {
    return set("sprayseason", idCol, alias);
  };
  
  @Override
  public String setGeoEntity(Alias alias)
  {
    return set(this.geoEntityCol, alias);
  }
  
  @Override
  public String setSprayMethod(Alias alias)
  {
    return set(this.sprayMethodCol, alias);
  }
  
  @Override
  public String setSurfaceType(Alias alias)
  {
    return set(this.surfaceTypeCol, alias);
  }
  
  @Override
  public String setBrand(Alias alias)
  {
    return set(this.brandCol, alias);
  }
}
