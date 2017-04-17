package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;

public interface GeoFieldIF extends Reloadable
{
  public Boolean getIsSprayHierarchy();

  public Boolean getIsPopulationHierarchy();

  public Boolean getIsPoliticalHierarchy();

  public Boolean getIsUrbanHierarchy();

  public String[] getExtraUniversals();
  
  public String getFilterType();
}
