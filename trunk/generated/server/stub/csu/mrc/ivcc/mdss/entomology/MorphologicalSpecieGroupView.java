package csu.mrc.ivcc.mdss.entomology;


import com.terraframe.mojo.dataaccess.transaction.Transaction;

import csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupViewBase;
import csu.mrc.ivcc.mdss.mo.IdentificationMethod;
import csu.mrc.ivcc.mdss.mo.Specie;

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

    if(this.getGroupId() == null || this.getGroupId().equals(""))
    {
      IdentificationMethod method = IdentificationMethod.get(this.getIdentificationMethod());
      Specie specie = Specie.get(this.getSpecie());
      
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();      
      group.setCollection(ConcreteMosquitoCollection.get(this.getCollectionId()));
      group.setQuantity(this.getQuantity());
      group.setIdentificationMethod(method);
      group.setSpecie(specie);
      group.apply();
      
      this.setGroupId(group.getId());
    }
    else
    {
      IdentificationMethod method = IdentificationMethod.get(this.getIdentificationMethod());
      Specie specie = Specie.get(this.getSpecie());

      MorphologicalSpecieGroup group = MorphologicalSpecieGroup.lock(this.getGroupId());      
      group.setCollection(ConcreteMosquitoCollection.get(this.getCollectionId()));
      group.setQuantity(this.getQuantity());
      group.setQuantity(this.getQuantity());
      group.setIdentificationMethod(method);
      group.setSpecie(specie);
      group.apply();      
    }
  }
  
  @Override
  public void delete()
  {
    MorphologicalSpecieGroup.get(this.getGroupId()).delete();
  }

  @Transaction
  public static csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView[] saveAll(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView[] array)
  {
    for(MorphologicalSpecieGroupView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}
