package mdss.ivcc.mrc.csu.entomology;

import mdss.ivcc.mrc.csu.mo.IdentificationMethod;
import mdss.ivcc.mrc.csu.mo.Specie;

public class UninterestingSpecieGroup extends UninterestingSpecieGroupBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288151066L;
  
  public UninterestingSpecieGroup()
  {
    super();
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
