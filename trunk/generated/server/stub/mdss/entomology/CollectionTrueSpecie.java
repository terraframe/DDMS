package mdss.entomology;

public class CollectionTrueSpecie extends CollectionTrueSpecieBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288149069L;
  
  public CollectionTrueSpecie(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CollectionTrueSpecie(mdss.entomology.AbstractMosquitoCollection parent, mdss.entomology.TrueSpecieEntity child)
  {
    this(parent.getId(), child.getId());
  }
  
}
