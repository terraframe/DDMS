package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.general.MalariaSeasonDateProblem;

public class ImmatureCollection extends ImmatureCollectionBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 746053127;
  
  public ImmatureCollection()
  {
    super();
  }
 
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getCollectionId() != null)
    {
      return this.getCollectionId();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    return this.getCollectionId();
  }
  
  @Transaction
  public void deleteAll()
  {
    // DELETE ALL CollectionPremise
    List<CollectionPremise> premises = this.getPremises();
    
    for(CollectionPremise premise : premises)
    {
      premise.deleteAll();
    }
    
    this.delete();
  }
  
  public boolean hasPremises()
  {
    return (this.getPremises().size() > 0);
  }

  private List<CollectionPremise> getPremises()
  {
    CollectionPremiseQuery query = new CollectionPremiseQuery(new QueryFactory());    
    query.WHERE(query.getCollection().EQ(this));
    
    OIterator<? extends CollectionPremise> it = query.getIterator();
    
    try
    {
      List<? extends CollectionPremise> premises = it.getAll();
      
      return new LinkedList<CollectionPremise>(premises);
    }
    finally
    {
      it.close();
    }
  }
  
  @Override
  public void apply()
  {
    this.populateCollectionId();
    this.validateStartDate();

    super.apply();
  }
  
  @Override
  public void validateStartDate()
  {
    if(this.getStartDate() != null && this.getEndDate() != null)
    {
      if(this.getStartDate().after(this.getEndDate()))
      {
        MalariaSeasonDateProblem p = new MalariaSeasonDateProblem();
        p.apply();
        
        p.throwIt();
      }
    }
  }

  private void populateCollectionId()
  {
    if (this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(LocalProperty.getNextId());
    }
  }  
  
  public ImmatureCollectionView getView()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();
    
    view.populateView(this, null, null);
    
    return view;
  }
}
