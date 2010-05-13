package dss.vector.solutions.intervention.monitor;

public class PersonIntervention extends PersonInterventionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1444070414;
  
  public PersonIntervention()
  {
    super();
  }
 
  public PersonInterventionView getView()
  {
    PersonInterventionView view = new PersonInterventionView();
    
    view.populateView(this);
    
    return view;
  }
}
