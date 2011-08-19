package dss.vector.solutions.query;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class SimpleGeoField implements GeoFieldIF
{
  private Set<String> extraUniversals;

  private boolean     sprayHierarchy;

  private boolean     populationHierarchy;

  private boolean     politicalHierarchy;

  private boolean     urbanHierarchy;

  private String      filter;

  public SimpleGeoField()
  {
    this.extraUniversals = new TreeSet<String>();
    this.sprayHierarchy = false;
    this.populationHierarchy = false;
    this.politicalHierarchy = false;
    this.urbanHierarchy = false;
    this.filter = null;
  }

  @Override
  public String[] getExtraUniversals()
  {
    return extraUniversals.toArray(new String[extraUniversals.size()]);
  }

  @Override
  public String getFilterType()
  {
    return this.filter;
  }

  @Override
  public Boolean getIsPoliticalHierarchy()
  {
    return politicalHierarchy;
  }

  @Override
  public Boolean getIsPopulationHierarchy()
  {
    return populationHierarchy;
  }

  @Override
  public Boolean getIsSprayHierarchy()
  {
    return sprayHierarchy;
  }

  @Override
  public Boolean getIsUrbanHierarchy()
  {
    return urbanHierarchy;
  }

  public void addExtraUniversal(String extraUniversal)
  {
    this.extraUniversals.add(extraUniversal);
  }

  public void addExtraUniversals(Collection<String> extraUniversals)
  {
    this.extraUniversals.addAll(extraUniversals);
  }

  public void setFilterType(String filter)
  {
    this.filter = filter;
  }

  public void setExtraUniversals(Set<String> extraUniversals)
  {
    this.extraUniversals = extraUniversals;
  }

  public void setSprayHierarchy(boolean sprayHierarchy)
  {
    this.sprayHierarchy = sprayHierarchy;
  }

  public void setPopulationHierarchy(boolean populationHierarchy)
  {
    this.populationHierarchy = populationHierarchy;
  }

  public void setPoliticalHierarchy(boolean politicalHierarchy)
  {
    this.politicalHierarchy = politicalHierarchy;
  }

  public void setUrbanHierarchy(boolean urbanHierarchy)
  {
    this.urbanHierarchy = urbanHierarchy;
  }
}
