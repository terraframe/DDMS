package mdss.entomology.assay;

public class ADDAIntervals extends ADDAIntervalsBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234543767629L;
  
  public ADDAIntervals(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public ADDAIntervals(mdss.entomology.assay.AdultDiscriminatingDoseAssay parent, mdss.entomology.assay.ADDATestInterval child)
  {
    this(parent.getId(), child.getId());
  }
  
}
