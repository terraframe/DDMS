package dss.vector.solutions.surveillance;

import com.runwaysdk.generation.loader.Reloadable;

public class CaseDiagnosisTypeAmount extends CaseDiagnosisTypeAmountBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 1197756146;
  
  public CaseDiagnosisTypeAmount(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseDiagnosisTypeAmount(dss.vector.solutions.surveillance.CaseDiagnosisType parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public CaseDiagnosisTypeAmountView getView()
  {
    CaseDiagnosisTypeAmountView view = new CaseDiagnosisTypeAmountView();

    view.populateView(this);

    return view;
  }


}
