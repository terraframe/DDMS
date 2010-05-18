package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.general.Disease;

public class PersonIntervention extends PersonInterventionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1444070414;
  
  public PersonIntervention()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    if(this.isNew() && !this.isAppliedToDB())
    {
      this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }
 
  public PersonInterventionView getView()
  {
    PersonInterventionView view = new PersonInterventionView();
    
    view.populateView(this);
    
    return view;
  }
}
