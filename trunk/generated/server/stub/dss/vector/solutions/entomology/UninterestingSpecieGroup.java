package dss.vector.solutions.entomology;


public class UninterestingSpecieGroup extends UninterestingSpecieGroupBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288151066L;

  public UninterestingSpecieGroup()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getCollection().getKey() + "-" + this.getSampleId();
  }

  public UninterestingSpecieGroupView getView()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    view.setGroupId(this.getId());
    view.setSampleId(this.getSampleId());
    view.setQuantity(this.getQuantity());
    view.setCollection(this.getCollection());
    view.setSpecie(this.getSpecie());
    view.setIdentificationMethod(this.getIdentificationMethod());

    return view;
  }

}
