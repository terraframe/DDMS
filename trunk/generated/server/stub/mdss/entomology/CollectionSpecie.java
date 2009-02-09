package mdss.entomology;

public class CollectionSpecie extends CollectionSpecieBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234203358498L;
  
  public CollectionSpecie(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CollectionSpecie(mdss.entomology.AbstractMosquitoCollection parent, mdss.entomology.MorphologicalSpecieGroup child)
  {
    this(parent.getId(), child.getId());
  }
  
}
