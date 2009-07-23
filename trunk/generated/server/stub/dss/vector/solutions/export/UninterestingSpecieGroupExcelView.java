package dss.vector.solutions.export;

import dss.vector.solutions.entomology.ConcreteMosquitoCollection;
import dss.vector.solutions.entomology.UninterestingSpecieGroupView;
import dss.vector.solutions.mo.IdentificationMethod;
import dss.vector.solutions.mo.Specie;

public class UninterestingSpecieGroupExcelView extends UninterestingSpecieGroupExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1248377153876L;
  
  public UninterestingSpecieGroupExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    UninterestingSpecieGroupView view = new UninterestingSpecieGroupView();
    
    view.setCollection(ConcreteMosquitoCollection.getByCollectionId(this.getCollectionId()));
    view.setSampleId(this.getSampleId());
    view.setSpecie(Specie.validateByDisplayLabel(this.getSpecie()));
    view.setIdentificationMethod(IdentificationMethod.validateByDisplayLabel(this.getIdentificationMethod()));
    view.setQuantity(this.getQuantity());
    view.apply();
  }
  
}
