package dss.vector.solutions.entomology;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class CollectionPremise extends CollectionPremiseBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 880239189;
  
  public CollectionPremise()
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
    super.delete();
    
    ImmatureCollection _collection = this.getCollection();
    
    if(!_collection.hasPremises())
    {
      _collection.delete();
    }
  }
  
  @Transaction
  public void deleteAll()
  {
    // DELETE ALL Taxon
    List<PremiseTaxon> taxons = this.getTaxons();
    
    for(PremiseTaxon taxon : taxons)
    {
      taxon.delete();
    }
    
    this.delete();    
  }
  
  public boolean hasTaxons()
  {
    return (this.getTaxons().size() > 0);
  }

  private List<PremiseTaxon> getTaxons()
  {
    PremiseTaxonQuery query = new PremiseTaxonQuery(new QueryFactory());    
    query.WHERE(query.getPremise().EQ(this));
    
    OIterator<? extends PremiseTaxon> it = query.getIterator();
    
    try
    {
      List<? extends PremiseTaxon> taxons = it.getAll();
      
      return new LinkedList<PremiseTaxon>(taxons);
    }
    finally
    {
      it.close();
    }
  }
  
  public ImmatureCollectionView getView()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();
    
    ImmatureCollection _collection = this.getCollection();
    
    view.populateView(_collection, this, null);
    
    return view;
  }
}
