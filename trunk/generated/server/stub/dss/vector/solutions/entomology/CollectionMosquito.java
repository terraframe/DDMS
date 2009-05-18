package dss.vector.solutions.entomology;

public class CollectionMosquito extends CollectionMosquitoBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242677949536L;
  
  public CollectionMosquito(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CollectionMosquito(dss.vector.solutions.entomology.AbstractMosquitoCollection parent, dss.vector.solutions.entomology.Mosquito child)
  {
    this(parent.getId(), child.getId());
  }
  
}
