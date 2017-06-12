package dss.vector.solutions.geoserver;

import com.runwaysdk.generation.loader.Reloadable;

public class ReseedRequest extends AbstractRequest implements Reloadable
{
  public ReseedRequest(String layer, String workspace)
  {
    super(layer, workspace);
  }

  @Override
  protected String getType()
  {
    return "reseed";
  }
}
