package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.ontology.Term;

public class TimeResponseAssayExcelView extends TimeResponseAssayExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1853916247;
  
  public TimeResponseAssayExcelView()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    TimeResponseAssayView assay = new TimeResponseAssayView();
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setAssay(Term.validateByDisplayLabel(this.getAssay(), TimeResponseAssayView.getAssayMd()));
    assay.setActiveIngredient(Term.validateByDisplayLabel(this.getActiveIngredient(), TimeResponseAssayView.getActiveIngredientMd()));
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(), TimeResponseAssayView.getSpeciesMd()));
    assay.setLifeStage(Term.validateByDisplayLabel(this.getLifeStage(), TimeResponseAssayView.getLifeStageMd()));
    assay.setSynergist(this.getSynergist());
    assay.setTestStrainResult(this.getTestStrainResult());
    assay.setReferenceStrainResult(this.getReferenceStrainResult());
    assay.apply();
  }
  
  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONID);
    list.add(ASSAY);
    list.add(ACTIVEINGREDIENT);
    list.add(SPECIES);
    list.add(LIFESTAGE);
    list.add(SYNERGIST);
    list.add(TESTSTRAINRESULT);
    list.add(REFERENCESTRAINRESULT);
    return list;
  }
  
}
