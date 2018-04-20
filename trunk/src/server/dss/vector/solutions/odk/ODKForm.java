package dss.vector.solutions.odk;

import com.runwaysdk.dataaccess.MdClassDAOIF;

import dss.vector.solutions.geo.GeoFilterCriteria;

public class ODKForm
{
  protected MdClassDAOIF base;
  
  protected ODKForm[] repeats;
  
  protected GeoFilterCriteria gfc;
  
  public ODKForm(MdClassDAOIF base, GeoFilterCriteria gfc, ODKForm ... repeats)
  {
    this.base = base;
    this.repeats = repeats;
    this.gfc = gfc;
  }
  
  public ODKForm(MdClassDAOIF base, ODKForm ... repeats)
  {
    this.repeats = repeats;
  }
  
  public void setGeoFilterCriteria(GeoFilterCriteria geoFilters)
  {
    this.gfc = geoFilters;
  }
  
  public GeoFilterCriteria getGeoFilterCriteria()
  {
    return this.gfc;
  }

  public MdClassDAOIF getBase() {
    return base;
  }

  public void setBase(MdClassDAOIF base) {
    this.base = base;
  }

  public ODKForm[] getRepeats() {
    return repeats;
  }

  public void setRepeats(ODKForm[] repeats) {
    this.repeats = repeats;
  }
  
  
}
