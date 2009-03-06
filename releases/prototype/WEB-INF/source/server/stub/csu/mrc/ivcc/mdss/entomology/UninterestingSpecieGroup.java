package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupBase;
import csu.mrc.ivcc.mdss.mo.IdentificationMethod;
import csu.mrc.ivcc.mdss.mo.Specie;

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
    Specie specie = this.getSpecie();
    IdentificationMethod identificationMethod = this.getIdentificationMethod();
    
    view.setGroupId(this.getId());
    view.setSampleId(this.getSampleId());
    view.setQuantity(this.getQuantity());
    view.setCollection(this.getCollection());

    if(specie != null)
    {
      view.setSpecie(specie);
    }
    
    if(identificationMethod != null)
    {
      view.setIdentificationMethod(identificationMethod);
    }
    
    view.applyNoPersist();
    
    return view;
  }
  
}