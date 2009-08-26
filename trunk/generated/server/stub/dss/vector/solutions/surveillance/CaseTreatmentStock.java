package dss.vector.solutions.surveillance;

public class CaseTreatmentStock extends CaseTreatmentStockBase implements ChildOption, com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693145662L;
  
  public CaseTreatmentStock(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseTreatmentStock(dss.vector.solutions.surveillance.AggregatedCase parent, dss.vector.solutions.surveillance.TreatmentGrid child)
  {
    this(parent.getId(), child.getId());
  }

  public CaseTreatmentStock clone(AggregatedCase parent)
  {
    CaseTreatmentStock clone = new CaseTreatmentStock(parent, this.getChild());
    clone.setOutOfStock(this.getOutOfStock());

    return clone;
  }
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
