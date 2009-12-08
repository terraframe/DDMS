package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.Property;
import dss.vector.solutions.RangeValueProblem;

public class BiochemicalAssay extends BiochemicalAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1877238023;
  
  public BiochemicalAssay()
  {
    super();
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
        this.setMosquitoId(Property.getNextId());
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
