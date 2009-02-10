package mdss.entomology;

public class MorphologicalSpecieGroup extends MorphologicalSpecieGroupBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288141011L;
  
  public MorphologicalSpecieGroup()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    boolean firstApply = !this.isAppliedToDB() & this.isNew();

    super.apply();
    
    if(firstApply)
    {
      AbstractMosquitoCollection c = this.getCollection();
      CollectionSpecie collectionSpecie = new CollectionSpecie(c,this);
      collectionSpecie.apply();
    }
  }  
}
