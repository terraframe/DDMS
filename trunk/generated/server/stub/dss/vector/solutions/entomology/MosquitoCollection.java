package dss.vector.solutions.entomology;

import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Property;
import dss.vector.solutions.entomology.assay.CollectionAssay;
import dss.vector.solutions.entomology.assay.CollectionAssayQuery;

public class MosquitoCollection extends MosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1894808272;
  
  public MosquitoCollection()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getId();
  }
  
  @Override
  @Transaction
  public void delete()
  {
    // Delete all of the sub collections
    MosquitoCollectionView view = this.getView();
    
    SubCollectionView[] collections = view.getSubCollections();
    
    for(SubCollectionView collection : collections)
    {
      collection.deleteConcrete();
    }
    
    // Delete all of the Assays
    List<? extends CollectionAssay> list = this.getAssays();
    
    for(CollectionAssay assay : list)
    {
      assay.delete();
    }
    
    // Delete all infection and pooled infection assays
    InfectionAssayView[] infectionAssays = view.getInfectionAssays();
    
    for(InfectionAssayView assay : infectionAssays)
    {
      assay.deleteConcrete();
    }
    
    PooledInfectionAssayView[] pooledInfectionAssays = view.getPooledInfectionAssays();
    
    for(PooledInfectionAssayView assay : pooledInfectionAssays)
    {
      assay.deleteConcrete();
    }    
    
    // DELETE ALL MECHANISM ASSAYS
    BiochemicalAssayView[] biochemicalAssays = view.getBiochemicalAssays();
    
    for(BiochemicalAssayView assay : biochemicalAssays)
    {
      assay.deleteConcrete();
    }
        
    MolecularAssayView[] molecularAssays = view.getMolecularAssays();
    
    for(MolecularAssayView assay : molecularAssays)
    {
      assay.deleteConcrete();
    }
    
    super.delete();
  }
  
  private List<? extends CollectionAssay> getAssays()
  {
    CollectionAssayQuery query = new CollectionAssayQuery(new QueryFactory());    
    query.WHERE(query.getCollection().EQ(this));
    OIterator<? extends CollectionAssay> it = query.getIterator();
    
    try
    {
      return it.getAll();
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
    this.populateLifeStageName();
    
    super.apply();
  }

  private void populateLifeStageName()
  {
    for(LifeStage stage  : this.getLifeStage())
    {
      this.setLifeStageName(stage.getEnumName());
    }
  }

  private void populateCollectionId()
  {
    if(this.getCollectionId() == null || this.getCollectionId().equals(""))
    {
      this.setCollectionId(Property.getNextId());
    }
  }

  @Override
  @Transaction
  public MosquitoCollectionView lockView()
  {
    this.lock();

    return this.getView();
  }

  @Override
  @Transaction
  public MosquitoCollectionView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public MosquitoCollectionView getView()
  {
    MosquitoCollectionView view = new MosquitoCollectionView();

    view.populateView(this);

    return view;
  }
  

}
