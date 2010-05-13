package dss.vector.solutions.intervention.monitor;

public class AggregatedPremiseVisit extends AggregatedPremiseVisitBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -103241221;
  
  public AggregatedPremiseVisit()
  {
    super();
  }
 
  public AggregatedPremiseVisitView getView()
  {
    AggregatedPremiseVisitView view = new AggregatedPremiseVisitView();
    
    view.populateView(this);
    
    return view;
  }

}
