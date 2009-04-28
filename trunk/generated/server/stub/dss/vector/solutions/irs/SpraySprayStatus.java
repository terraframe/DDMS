package dss.vector.solutions.irs;

public class SpraySprayStatus extends SpraySprayStatusBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240930065786L;
  
  public SpraySprayStatus(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public SpraySprayStatus(dss.vector.solutions.irs.AbstractSpray parent, dss.vector.solutions.irs.SprayStatus child)
  {
    this(parent.getId(), child.getId());
  }
  
}
