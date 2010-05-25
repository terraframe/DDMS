package dss.vector.solutions.intervention.monitor;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;


public class IndividualIPTCase extends IndividualIPTCaseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1254012841931L;
  
  public IndividualIPTCase()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else if (this.getPatient() != null)
    {      
      Person person = this.getPatient().getPerson();
      String label = person.getFirstName() + " " + person.getLastName();
      
      return this.getClassDisplayLabel() + ": (" + label + ")";
    }
    
    return this.buildKey();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  @Override
  public void apply()
  {
	if (this.isNew() && this.getDisease() == null) {
	    this.setDisease(Disease.getCurrent());
	}
	    
    super.apply();
    
    // As per ticket #890 we must update the patients residential information
    if(this.getResidentialLocation() != null)
    {
      Person person = this.getPatient().getPerson();
      
      person.lock();
      person.setResidentialGeoEntity(this.getResidentialLocation());
      person.apply();
    }
  }
  
  public static IndividualIPTCaseView getView(String id)
  {
    return IndividualIPTCase.get(id).getView();
  }

  public IndividualIPTCaseView getView()
  {
    IndividualIPTCaseView view = new IndividualIPTCaseView();
    view.populateView(this);

    return view;
  }

  @Override
  public IndividualIPTCaseView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public IndividualIPTCaseView lockView()
  {
    this.lock();

    return this.getView();
  }
}
