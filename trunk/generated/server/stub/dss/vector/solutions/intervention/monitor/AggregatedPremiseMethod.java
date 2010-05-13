package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.surveillance.ChildOption;

public class AggregatedPremiseMethod extends AggregatedPremiseMethodBase implements com.runwaysdk.generation.loader.Reloadable, ChildOption
{
  private static final long serialVersionUID = -654163986;
  
  public AggregatedPremiseMethod(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public AggregatedPremiseMethod(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public AggregatedPremiseMethodView getView()
  {
    AggregatedPremiseMethodView view = new AggregatedPremiseMethodView();
    
    view.populateView(this);
    
    return view;
  }
}
