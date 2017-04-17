package dss.vector.solutions.entomology;

import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class PupalPremise extends PupalPremiseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 705461057;
  
  public PupalPremise()
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
    else if (this.getCollection() != null && this.getPremiseType() != null)
    {
      return this.getCollection().getKey() + " - " + this.getPremiseType().getTermDisplayLabel().getValue();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    if (this.getCollection() != null && this.getPremiseType() != null)
    {
      return this.getCollection().getKey() + " - " + this.getPremiseType().getTermId();
    }

    return super.buildKey();
  }

  @Override
  @Transaction
  public void delete()
  { 
    // First delete all of the containers
    PupalContainerQuery query = new PupalContainerQuery(new QueryFactory());

    query.WHERE(query.getPremise().EQ(this));

    OIterator<? extends PupalContainer> it = query.getIterator();

    try
    {
      List<? extends PupalContainer> containers = it.getAll();

      for(PupalContainer container : containers)
      {
        container.delete();
      }
    }
    finally
    {
      it.close();
    }
    
    super.delete();
    
    PupalCollection _collection = this.getCollection();
    
    if(!_collection.hasPremises())
    {
      _collection.delete();
    }
  }
  
  public PupalCollectionView getView()
  {
    PupalCollectionView view = new PupalCollectionView();
    
    PupalCollection _collection = this.getCollection();
    
    view.populateView(_collection, this);
    
    return view;
  }

}
