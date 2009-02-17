package mdss.entomology;

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
      IdentificationMethod method = IdentificationMethod.getIdentificationMethod(this.getIdentificationMethod());
      
      MorphologicalSpecieGroup group = new MorphologicalSpecieGroup();      
      group.setCollection(AbstractMosquitoCollection.get(this.getCollectionId()));
      group.setQuantity(this.getQuantity());
      group.setIdentificationMethod(method);
      group.setSpecie(Specie.getSpecie(this.getSpecie()));
      group.apply();
    }
    else
    {
      IdentificationMethod method = IdentificationMethod.getIdentificationMethod(this.getIdentificationMethod());

      MorphologicalSpecieGroup group = MorphologicalSpecieGroup.get(this.getGroupId());      
      group.setCollection(AbstractMosquitoCollection.get(this.getCollectionId()));
      group.setQuantity(this.getQuantity());
      group.setQuantity(this.getQuantity());
      group.setIdentificationMethod(method);
      group.setSpecie(Specie.getSpecie(this.getSpecie()));
      group.apply();      
    }
  }

  @Transaction
  public static void saveAll(mdss.entomology.MorphologicalSpecieGroupView[] array)
  {
    for(MorphologicalSpecieGroupView view : array)
    {
      view.apply();
    }
  }  
}
