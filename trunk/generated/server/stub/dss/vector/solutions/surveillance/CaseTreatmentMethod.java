package dss.vector.solutions.surveillance;

import dss.vector.solutions.ontology.Term;

public class CaseTreatmentMethod extends CaseTreatmentMethodBase implements ChildOption, com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693144058L;
  
  public CaseTreatmentMethod(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseTreatmentMethod(AggregatedCase parent, Term child)
  {
    this(parent.getId(), child.getId());
  }

  public CaseTreatmentMethod clone(AggregatedCase parent)
  {
    CaseTreatmentMethod clone = new CaseTreatmentMethod(parent, this.getChild());
    clone.setAmount(this.getAmount());

    return clone;
  }
  
  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
