package dss.vector.solutions.surveillance;

public class CaseTreatmentMethod extends CaseTreatmentMethodBase implements CaseGrid, com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693144058L;
  
  public CaseTreatmentMethod(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseTreatmentMethod(dss.vector.solutions.surveillance.AggregatedCase parent, dss.vector.solutions.surveillance.TreatmentMethodGrid child)
  {
    this(parent.getId(), child.getId());
  }

  public CaseTreatmentMethod clone(AggregatedCase parent)
  {
    CaseTreatmentMethod clone = new CaseTreatmentMethod(parent, this.getChild());
    clone.setAmount(this.getAmount());

    return clone;
  }
  
}
