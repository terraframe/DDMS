package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.Property;
import dss.vector.solutions.RangeValueProblem;

public class PooledInfectionAssay extends PooledInfectionAssayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -470295545;

  public PooledInfectionAssay()
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
    else if(this.getPoolId() != null && !this.getPoolId().equals(""))
    {
      return this.getPoolId();
    }
    else if(this.getSpecies() != null)
    {
      return this.getSpecies().getDisplay();
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
    validatePoolId();
    validateNumberPositive();

    super.apply();
  }

  @Override
  public void validatePoolId()
  {
    if (this.getPoolsTested() != null)
    {
      if (this.getPoolsTested() > 1 && ( this.hasPoolId() ))
      {
        this.setPoolId(null);
      }
      if (this.getPoolsTested() == 1 && ( !this.hasPoolId() ))
      {
        this.setPoolId(Property.getNextId());
      }
    }
  }

  private boolean hasPoolId()
  {
    return this.getPoolId() != null && !this.getPoolId().equals("");
  }

  @Override
  public void validateNumberPositive()
  {
    if (this.getNumberPositive() != null && this.getPoolsTested() != null)
    {
      if (this.getNumberPositive() > this.getPoolsTested())
      {
        String msg = "Number of tested pools must be GTE to the number of positive pools";
        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, NUMBERPOSITIVE);
        p.setLowerLimit(0);
        p.setUpperLimit(this.getPoolsTested());
        p.setInvalidValue(this.getNumberPositive());
        p.apply();

        p.throwIt();
      }
    }
  }

  @Override
  @Transaction
  public PooledInfectionAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public PooledInfectionAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public PooledInfectionAssayView getView()
  {
    PooledInfectionAssayView view = new PooledInfectionAssayView();

    view.populateView(this);

    return view;
  }

}
