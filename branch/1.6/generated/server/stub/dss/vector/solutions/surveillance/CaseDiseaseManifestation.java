package dss.vector.solutions.surveillance;

public class CaseDiseaseManifestation extends CaseDiseaseManifestationBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1615702743;

  public CaseDiseaseManifestation()
  {
    super();
  }

  public static CaseDiseaseManifestationView getView(String id)
  {
    CaseDiseaseManifestationView view = new CaseDiseaseManifestationView();

    view.populateView(CaseDiseaseManifestation.get(id));

    return view;
  }
}
