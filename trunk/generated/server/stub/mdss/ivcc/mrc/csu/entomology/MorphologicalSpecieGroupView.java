package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.entomology.MorphologicalSpecieGroupViewBase;
import mdss.ivcc.mrc.csu.mo.IdentificationMethod;
import mdss.ivcc.mrc.csu.mo.Specie;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

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
      group.setCollection(AbstractMosquitoCollection.get(this.getCollectionId()));
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
      group.setCollection(AbstractMosquitoCollection.get(this.getCollectionId()));
      group.setQuantity(this.getQuantity());
      group.setQuantity(this.getQuantity());
      group.setIdentificationMethod(method);
      group.setSpecie(specie);
      group.apply();      
    }
  }

  @Transaction
  public static mdss.ivcc.mrc.csu.entomology.MorphologicalSpecieGroupView[] saveAll(mdss.ivcc.mrc.csu.entomology.MorphologicalSpecieGroupView[] array)
  {
    for(MorphologicalSpecieGroupView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}
