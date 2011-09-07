package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.general.Disease;

public class BiochemicalAssay extends BiochemicalAssayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1877238023;
  
  public BiochemicalAssay()
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
    else if(this.getMosquitoId() != null && !this.getMosquitoId().equals(""))
    {
      return this.getMosquitoId();
    }
    else if(this.getSpecies() != null)
    {
      return this.getSpecies().getTermDisplayLabel().getValue();
    }
    
    return super.toString();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }

  @Override
  public void apply()
  {
    validateMosquitoId();
    validateNumberElevated();

    if (this.isNew() && this.getDisease() == null) {
    	this.setDisease(Disease.getCurrent());
    }
    
    super.apply();
  }

  @Override
  public void validateMosquitoId()
  {
    if (this.getNumberTested() != null)
    {
      if (this.getNumberTested() > 1 && ( this.hasMosquitoId() ))
      {
        this.setMosquitoId(null);
      }
      if (this.getNumberTested() == 1 && ( !this.hasMosquitoId() ))
      {
        this.setMosquitoId(LocalProperty.getNextId());
      }
    }
  }
  
  @Override
  public void validateNumberElevated()
  {
    if(this.getNumberElevated() != null && this.getNumberTested() != null)
    {
      if(this.getNumberElevated() > this.getNumberTested())
      {        
        String msg = "Number of tested mosquitos must be GTE to the number of positive mosquitos";
        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, NUMBERELEVATED);
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberTested());
        p.setInvalidValue(this.getNumberElevated());
        p.apply();
        
        p.throwIt();        
      }
    }
  }
  
  private boolean hasMosquitoId()
  {
    return this.getMosquitoId() != null && !this.getMosquitoId().equals("");
  }

  @Override
  @Transaction
  public BiochemicalAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public BiochemicalAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public BiochemicalAssayView getView()
  {
    BiochemicalAssayView view = new BiochemicalAssayView();

    view.populateView(this);

    return view;
  }

  
}
