package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.general.Disease;

public class InsecticideIntervention extends InsecticideInterventionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1350034915;
  
  public InsecticideIntervention()
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
}
