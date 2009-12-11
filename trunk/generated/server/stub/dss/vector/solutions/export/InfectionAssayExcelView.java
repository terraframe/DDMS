package dss.vector.solutions.export;

import dss.vector.solutions.entomology.InfectionAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.ontology.Term;

public class InfectionAssayExcelView extends InfectionAssayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 908608466;
  
  public InfectionAssayExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    InfectionAssayView assay = new InfectionAssayView();
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setMosquitoId(this.getMosquitoId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(), InfectionAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(), InfectionAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), InfectionAssayView.getSexMd()));
    assay.setParasite(Term.validateByDisplayLabel(this.getParasite(), InfectionAssayView.getParasiteMd()));
    assay.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(), InfectionAssayView.getTestMethodMd()));
    assay.setInfected(this.getInfected());
    assay.setNumberTested(this.getNumberTested());
    assay.setNumberPositive(this.getNumberPositive());
    assay.apply();
  }
}
