package dss.vector.solutions.export;

import dss.vector.solutions.entomology.MolecularAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.ontology.Term;

public class MolecularAssayExcelView extends MolecularAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1979900442;
  
  public MolecularAssayExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    MolecularAssayView assay = new MolecularAssayView();
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setMosquitoId(this.getMosquitoId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(), MolecularAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(), MolecularAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), MolecularAssayView.getSexMd()));
    assay.setGeneration(Term.validateByDisplayLabel(this.getGeneration(), MolecularAssayView.getGenerationMd()));
    assay.setIsofemale(this.getIsofemale());
    assay.setAssayMethod(Term.validateByDisplayLabel(this.getAssayMethod(), MolecularAssayView.getAssayMethodMd()));
    assay.setTarget(Term.validateByDisplayLabel(this.getTarget(), MolecularAssayView.getTargetMd()));
    assay.setNumberRR(this.getNumberRR());
    assay.setNumberRS(this.getNumberRS());
    assay.setNumberSS(this.getNumberSS());
    assay.apply();
  }
}
