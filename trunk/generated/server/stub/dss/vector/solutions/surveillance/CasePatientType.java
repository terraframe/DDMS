package dss.vector.solutions.surveillance;

public class CasePatientType extends CasePatientTypeBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1388219971;
  
  public CasePatientType()
  {
    super();
  }
 
  public static CasePatientTypeView getView(String id)
  {
    CasePatientTypeView view = new CasePatientTypeView();

    view.populateView(CasePatientType.get(id));

    return view;
  }

}
