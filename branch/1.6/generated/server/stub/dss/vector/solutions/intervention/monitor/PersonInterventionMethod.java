package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.surveillance.ChildOption;

public class PersonInterventionMethod extends PersonInterventionMethodBase implements com.runwaysdk.generation.loader.Reloadable, ChildOption
{
  private static final long serialVersionUID = 1383224177;
  
  public PersonInterventionMethod(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public PersonInterventionMethod(dss.vector.solutions.intervention.monitor.PersonIntervention parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public PersonInterventionMethodView getView()
  {
    PersonInterventionMethodView view = new PersonInterventionMethodView();
    
    view.populateView(this);
    
    return view;
  }

}
