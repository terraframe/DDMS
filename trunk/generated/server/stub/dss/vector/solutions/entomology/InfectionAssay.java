package dss.vector.solutions.entomology;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.RangeValueProblem;
import dss.vector.solutions.entomology.assay.UniqueAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.EntomologyQB;

public class InfectionAssay extends InfectionAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 1835211494;

  public InfectionAssay()
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
    else if(this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
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
  public void apply()
  {
    UniqueAssayUtil.setUniqueAssayId(this);
    
    validateMosquitoId();
    validateNumberPositive();
    
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
  public void validateNumberPositive()
  {
    if(this.getNumberPositive() != null && this.getNumberTested() != null)
    {
      if(this.getNumberPositive() > this.getNumberTested())
      {        
        String msg = "Number of tested mosquitos must be GTE to the number of positive mosquitos";
        RangeValueProblem p = new RangeValueProblem(msg);
        p.setNotification(this, NUMBERPOSITIVE);
        p.setLowerLimit(0);
        p.setUpperLimit(this.getNumberTested());
        p.setInvalidValue(this.getNumberPositive());
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
  public InfectionAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public InfectionAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public InfectionAssayView getView()
  {
    InfectionAssayView view = new InfectionAssayView();

    view.populateView(this);

    return view;
  }
  
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    return new EntomologyQB(xml, config, layer).construct();
  }

}
