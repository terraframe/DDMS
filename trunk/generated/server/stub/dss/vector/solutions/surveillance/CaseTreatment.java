package dss.vector.solutions.surveillance;

public class CaseTreatment extends CaseTreatmentBase implements ChildOption, com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693150665L;
  
  public CaseTreatment(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseTreatment(dss.vector.solutions.surveillance.AggregatedCase parent, dss.vector.solutions.surveillance.TreatmentGrid child)
  {
    this(parent.getId(), child.getId());
  }

  public CaseTreatment clone(AggregatedCase parent)
  {
    CaseTreatment clone = new CaseTreatment(parent, this.getChild());
    clone.setAmount(this.getAmount());

    return clone;
  }
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
