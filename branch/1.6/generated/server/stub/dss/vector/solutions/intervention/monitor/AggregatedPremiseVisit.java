package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.general.Disease;

public class AggregatedPremiseVisit extends AggregatedPremiseVisitBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -103241221;
  
  public AggregatedPremiseVisit()
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
   
  public AggregatedPremiseVisitView getView()
  {
    AggregatedPremiseVisitView view = new AggregatedPremiseVisitView();
    
    view.populateView(this);
    
    return view;
  }

}
