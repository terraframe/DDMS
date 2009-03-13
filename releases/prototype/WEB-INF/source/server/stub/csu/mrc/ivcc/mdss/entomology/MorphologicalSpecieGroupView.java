package csu.mrc.ivcc.mdss.entomology;


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
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();      
      group.setCollection(this.getCollection());
      group.setQuantity(this.getQuantity());
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
  public static csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView[] saveAll(csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupView[] array)
  {
    for(MorphologicalSpecieGroupView view : array)
    {
      view.apply();
    }
    
    return array;
  }  
}