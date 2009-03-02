package mdss.ivcc.mrc.csu.entomology;

public class UninterestingSpecieGroupView extends UninterestingSpecieGroupViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235769206695L;
  
  public UninterestingSpecieGroupView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    UninterestingSpecieGroup group = null;
    String id = this.getGroupId();

    if (id == null || id.equals(""))
    {
      group = new UninterestingSpecieGroup();
    }
    else
    {
      group = UninterestingSpecieGroup.lock(id);
    }

    group.setSpecie(this.getSpecie());
    group.setCollection(this.getCollection());
    group.setIdentificationMethod(this.getIdentificationMethod());
    group.setSampleId(this.getSampleId());
    group.setQuanity(this.getQuanity());    
    group.apply();

    this.setGroupId(group.getId());
  }
  
  @Override
  public void delete()
  {
    UninterestingSpecieGroup.get(this.getGroupId()).delete();
  }
}
