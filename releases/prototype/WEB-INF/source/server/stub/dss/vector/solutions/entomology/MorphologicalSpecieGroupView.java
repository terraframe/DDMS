package dss.vector.solutions.entomology;


import com.terraframe.mojo.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MorphologicalSpecieGroupViewBase;

public class MorphologicalSpecieGroupView extends MorphologicalSpecieGroupViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234793969635L;
  
  public MorphologicalSpecieGroupView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    if(this.getCollection() == null)
    {
      this.setCollection(MosquitoCollectionPoint.findOrCreate(this.getGeoEntity(), this.getDateCollected()));
    }
    
    if(this.getGroupId() == null || this.getGroupId().equals(""))
    {
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();      
      group.setCollection(this.getCollection());
      group.setQuantity(this.getQuantity());
      group.setQuantityFemale(this.getQuantityFemale());
      group.setQuantityMale(this.getQuantityMale());
      group.setIdentificationMethod(this.getIdentificationMethod());
      group.setSpecie(this.getSpecie());
      group.apply();
      
      this.setGroupId(group.getId());
    }
    else
    {
      MorphologicalSpecieGroup group = MorphologicalSpecieGroup.lock(this.getGroupId());      
      group.setCollection(this.getCollection());
      group.setQuantity(this.getQuantity());
      group.setQuantityFemale(this.getQuantityFemale());
      group.setQuantityMale(this.getQuantityMale());
      group.setIdentificationMethod(this.getIdentificationMethod());
      group.setSpecie(this.getSpecie());
      group.apply();      
    }
  }
  
  @Override
  public void delete()
  {
    MorphologicalSpecieGroup.get(this.getGroupId()).delete();
  }

  @Transaction
  public static dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] saveAll(dss.vector.solutions.entomology.MorphologicalSpecieGroupView[] array)
  {
    for(MorphologicalSpecieGroupView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}
