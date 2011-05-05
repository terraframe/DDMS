package dss.vector.solutions.gis.shapefile;

import dss.vector.solutions.gis.GISAdminLocalizer;
import dss.vector.solutions.gis.GISException;

public class CRSException extends GISException
{
  /**
   * 
   */
  private static final long serialVersionUID = 3008960432217674519L;

  @Override
  public String getMessage()
  {
    return GISAdminLocalizer
    .getMessage("INVALID_COORDINATE_SPATIAL_REFERENCE");
  }
}
