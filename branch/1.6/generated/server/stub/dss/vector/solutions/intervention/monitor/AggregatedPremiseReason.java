package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.surveillance.ChildOption;

public class AggregatedPremiseReason extends AggregatedPremiseReasonBase implements com.runwaysdk.generation.loader.Reloadable, ChildOption
{
  private static final long serialVersionUID = -460001839;
  
  public AggregatedPremiseReason(String parentId, String childId)
  {
    super(parentId, childId);
  }
  
  public AggregatedPremiseReason(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit parent, dss.vector.solutions.ontology.Term child)
  {
    this(parent.getId(), child.getId());
  }
  
  public AggregatedPremiseReasonView getView()
  {
    AggregatedPremiseReasonView view = new AggregatedPremiseReasonView();
    
    view.populateView(this);
    
    return view;
  }
}

