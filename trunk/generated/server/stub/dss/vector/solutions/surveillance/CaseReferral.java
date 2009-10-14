package dss.vector.solutions.surveillance;

import dss.vector.solutions.ontology.Term;

public class CaseReferral extends CaseReferralBase implements ChildOption, com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693142838L;

  public CaseReferral(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public CaseReferral(AggregatedCase parent, Term child)
  {
    this(parent.getId(), child.getId());
  }

  public CaseReferral clone(AggregatedCase parent)
  {
    CaseReferral clone = new CaseReferral(parent, this.getChild());
    clone.setAmount(this.getAmount());

    return clone;
  }

  @Override
  protected String buildKey()
  {
    return this.getParent().getKey() + "." + this.getChild().getKey();
  }
}
