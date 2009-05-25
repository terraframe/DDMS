package dss.vector.solutions.surveillance;

public class CaseDiagnostic extends CaseDiagnosticBase implements CaseGrid, com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693166129L;

  public CaseDiagnostic(String parentId, String childId)
  {
    super(parentId, childId);
  }

  public CaseDiagnostic(dss.vector.solutions.surveillance.AggregatedCase parent, dss.vector.solutions.surveillance.DiagnosticGrid child)
  {
    this(parent.getId(), child.getId());
  }


  public CaseDiagnostic clone(AggregatedCase parent)
  {
    CaseDiagnostic clone = new CaseDiagnostic(parent, this.getChild());
    clone.setAmount(this.getAmount());
    clone.setAmountPositive(this.getAmountPositive());

    return clone;
  }
}
