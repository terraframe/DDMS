package dss.vector.solutions.permission;

import com.runwaysdk.generation.loader.Reloadable;

public class PermissionChange implements Reloadable
{
  private boolean deny;

  private String  metadataId;

  public PermissionChange(boolean deny, String metadataId)
  {
    this.deny = deny;
    this.metadataId = metadataId;
  }

  public boolean isDeny()
  {
    return deny;
  }

  public String getMetadataId()
  {
    return metadataId;
  }

}
