package dss.vector.solutions.geo;

import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.geo.generated.GeoEntity;

/**
 * Honestly this class is a little weird. It should be called GeoHierarchy, but DDMS already has a class called GeoHierarchy, which isn't really
 *   a geo hierarchy, its just a weird wrapper around Universal.
 * 
 * @author rrowlands
 */
public class GeoFilterCriteria
{
  /**
   * Flag denoting if the search restrict by political hierarchies
   */
  private boolean political;

  /**
   * Flag denoting if the search should restrict by spray hierarchies
   */
  private boolean spray;

  /**
   * Flag denoting if the search restrict by populated hierarchies
   */
  private boolean populated;

  /**
   * Flag denoting if the search should restrict by urban hierarchies
   */
  private boolean urban;
  
  private String[] extraUniversals;
  
  public GeoFilterCriteria()
  {
    this(false, false, false, false);
  }

  public GeoFilterCriteria(boolean political, boolean spray, boolean populated, boolean urban, String...extraUniversals)
  {
    this.political = political;
    this.spray = spray;
    this.populated = populated;
    this.urban = urban;
    this.extraUniversals = extraUniversals;
  }
  
  public boolean isPartOfHierarchy(GeoEntity geo)
  {
    MdBusiness universal = MdBusiness.getMdBusiness(geo.getClass().getName());
    
    GeoHierarchy uniMetadata = GeoHierarchy.getGeoHierarchyFromType(universal);
    
    if (!political && !spray && !urban)
    {
      // Special criteria: Accept all entities

      return true;
    }
    else if (political && uniMetadata.getPolitical())
    {
      return true;
    }
    else if (spray && uniMetadata.getSprayTargetAllowed())
    {
      return true;
    }
    else if (urban && uniMetadata.getUrban())
    {
      return true;
    }
    
    for (String extra : extraUniversals)
    {
      if (universal.definesType().equals(extra))
      {
        return true;
      }
    }

    return false;
  }
  
  public String[] getExtraUniversals()
  {
    return this.extraUniversals;
  }
  
  public boolean isPolitical()
  {
    return political;
  }

  public void setPolitical(boolean political)
  {
    this.political = political;
  }

  public boolean isSpray()
  {
    return spray;
  }

  public void setSpray(boolean spray)
  {
    this.spray = spray;
  }

  public boolean isPopulated()
  {
    return populated;
  }

  public void setPopulated(boolean populated)
  {
    this.populated = populated;
  }
  
  public boolean isUrban()
  {
    return urban;
  }

  public void setUrban(boolean urban)
  {
    this.urban = urban;
  }
}
