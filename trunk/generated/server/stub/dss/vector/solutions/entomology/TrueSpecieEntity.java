package dss.vector.solutions.entomology;

import dss.vector.solutions.entomology.TrueSpecieEntityBase;

public abstract class TrueSpecieEntity extends TrueSpecieEntityBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288141596L;
  
  public TrueSpecieEntity()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getCollection() != null && this.getSampleId() != null)
    {
      return this.getCollection().getKey() + "." + this.getSampleId();
    }
    
    return this.getId();
  }
}
