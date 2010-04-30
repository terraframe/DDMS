package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.DiagnosticAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.ontology.Term;

public class DiagnosticAssayExcelView extends DiagnosticAssayExcelViewBase implements Reloadable
{
  private static final long serialVersionUID = -307738699;
  
  public DiagnosticAssayExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    DiagnosticAssayView assay = new DiagnosticAssayView();
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setActiveIngredient(Term.validateByDisplayLabel(this.getActiveIngredient(), DiagnosticAssayView.getActiveIngredientMd()));
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(), DiagnosticAssayView.getSpeciesMd()));
    assay.setLifeStage(Term.validateByDisplayLabel(this.getLifeStage(), DiagnosticAssayView.getLifeStageMd()));
    assay.setSynergist(this.getSynergist());
    assay.setOutcome(Term.validateByDisplayLabel(this.getOutcome(), DiagnosticAssayView.getOutcomeMd()));
    assay.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONID);
    list.add(ACTIVEINGREDIENT);
    list.add(SPECIES);
    list.add(LIFESTAGE);
    list.add(SYNERGIST);
    list.add(OUTCOME);
    return list;
  }
  
}
