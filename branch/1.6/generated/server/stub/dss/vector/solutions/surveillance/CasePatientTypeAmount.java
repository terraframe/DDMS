package dss.vector.solutions.surveillance;

import com.runwaysdk.generation.loader.Reloadable;

public class CasePatientTypeAmount extends CasePatientTypeAmountBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = 187559946;
  
  public CasePatientTypeAmount(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CasePatientTypeAmount(dss.vector.solutions.surveillance.CasePatientType parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
 
  
  public CasePatientTypeAmountView getView()
  {
    CasePatientTypeAmountView view = new CasePatientTypeAmountView();

    view.populateView(this);

    return view;
  }
}
