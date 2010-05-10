package dss.vector.solutions.entomology;

public class PremiseTaxon extends PremiseTaxonBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -378392539;
  
  public PremiseTaxon()
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
    else if (this.getPremise() != null && this.getTaxon() != null)
    {
      return this.getPremise().toString() + " - " + this.getTaxon().getTermDisplayLabel().getValue();
    }

    return super.toString();
  }

  @Override
  protected String buildKey()
  {
    if (this.getPremise() != null && this.getTaxon() != null)
    {
      return this.getPremise().getKey() + " - " + this.getTaxon().getTermId();
    }

    return super.buildKey();
  }
  
  @Override
  public void delete()
  {
    super.delete();
    
    CollectionPremise _premise = this.getPremise();
    
    if(!_premise.hasTaxons())
    {
      _premise.delete();
    }
  }
  
  public ImmatureCollectionView getView()
  {
    ImmatureCollectionView view = new ImmatureCollectionView();
    
    CollectionPremise _premise = this.getPremise();
    ImmatureCollection _collection = _premise.getCollection();
    
    view.populateView(_collection, _premise, this);
    
    return view;
  }
}
