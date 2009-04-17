package dss.vector.solutions.entomology;

import dss.vector.solutions.entomology.SexMasterBase;

public class SexMaster extends SexMasterBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234724383182L;

  public SexMaster()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getType() + "." + this.getEnumName();
  }

}
