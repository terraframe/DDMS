package dss.vector.solutions.entomology;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.Property;

public class SubCollection extends SubCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1584021814;
  
  public SubCollection()
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
    this.populateCollectionId();
    this.populateTotal();
    
    super.apply();
  }
  
  private void populateTotal()
  {
    int total = 0;
    
    if(this.getFemale() != null)
    {
      total +=  this.getFemale();
    }
    
    if(this.getMale() != null)
    {
      total +=  this.getMale();
    }
    
    if(this.getLarvae() != null)
    {
      total +=  this.getLarvae();
    }
    
    if(this.getPupae() != null)
    {
      total +=  this.getPupae();
    }
    
    if(this.getUnknowns() != null)
    {
      total +=  this.getUnknowns();
    }
    
    if(this.getEggs() != null)
    {
      total +=  this.getEggs();
    }
    
    this.setTotal(total);
  }


  private void populateCollectionId()
  {
    if(this.getSubCollectionId() == null || this.getSubCollectionId().equals(""))
    {
      this.setSubCollectionId(Property.getNextId());
    }
  }

  @Override
  @Transaction
  public SubCollectionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public SubCollectionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public SubCollectionView getView()
  {
    SubCollectionView view = new SubCollectionView();

    view.populateView(this);

    return view;
  }
}
