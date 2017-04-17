package dss.vector.solutions.surveillance;

import com.runwaysdk.generation.loader.Reloadable;

public class CaseDiseaseManifestationAmount extends CaseDiseaseManifestationAmountBase implements Reloadable, ChildOption
{
  private static final long serialVersionUID = -26748056;
  
  public CaseDiseaseManifestationAmount(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public CaseDiseaseManifestationAmount(dss.vector.solutions.surveillance.CaseDiseaseManifestation parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public CaseDiseaseManifestationAmountView getView()
  {
    CaseDiseaseManifestationAmountView view = new CaseDiseaseManifestationAmountView();

    view.populateView(this);

    return view;
  }

}
