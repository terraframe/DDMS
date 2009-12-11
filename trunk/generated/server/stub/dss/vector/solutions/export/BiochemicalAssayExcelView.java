package dss.vector.solutions.export;

import dss.vector.solutions.entomology.BiochemicalAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.ontology.Term;

public class BiochemicalAssayExcelView extends BiochemicalAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1997921529;
  
  public BiochemicalAssayExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    BiochemicalAssayView assay = new BiochemicalAssayView();
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setMosquitoId(this.getMosquitoId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(), BiochemicalAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(), BiochemicalAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), BiochemicalAssayView.getSexMd()));
    assay.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), BiochemicalAssayView.getGenerationMd()));
    assay.setIsofemale(this.getIsofemale());
    assay.setAssay(Term.validateByDisplayLabel(this.getAssay(), BiochemicalAssayView.getAssayMd()));
    assay.setNumberTested(this.getNumberTested());
    assay.setNumberElevated(this.getNumberElevated());
    assay.apply();
  }
}
