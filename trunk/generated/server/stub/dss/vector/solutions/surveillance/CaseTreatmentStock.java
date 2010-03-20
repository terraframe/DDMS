package dss.vector.solutions.surveillance;

import dss.vector.solutions.ontology.Term;

public class CaseTreatmentStock extends CaseTreatmentStockBase implements ChildOption, com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693145662L;
  
  public CaseTreatmentStock(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseTreatmentStock(AggregatedCase parent, Term child)
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
