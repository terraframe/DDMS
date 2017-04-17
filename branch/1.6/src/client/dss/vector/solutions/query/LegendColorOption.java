package dss.vector.solutions.query;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

/**
 * Used as a wrapper for Style color options that a Layer legend references.
 */
public class LegendColorOption implements Reloadable
{
  private String mdAttributeId;
  private String display;
  
  public LegendColorOption(AttributeMdDTO mdDTO)
  {
    this.mdAttributeId = mdDTO.getId();
    this.display = mdDTO.getDisplayLabel();
  }
  
  public String getMdAttributeId()
  {
    return this.mdAttributeId;
  }
  
  public String getDisplay()
  {
    return this.display;
  }
}
