package dss.vector.solutions.surveillance;

public class CaseDiagnosisType extends CaseDiagnosisTypeBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1876800194;
  
  public CaseDiagnosisType()
  {
    super();
  }

  public static CaseDiagnosisTypeView getView(String id)
  {
    CaseDiagnosisTypeView view = new CaseDiagnosisTypeView();
    
    view.populateView(CaseDiagnosisType.get(id));
    
    return view;
  }
  
}
