package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.DiagnosticAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssayView;
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


    assay.setUniqueAssayId(this.getUniqueAssayId());

    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
    assay.setCollection(collection);

    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(),
        TimeResponseAssayView.getActiveIngredientMd());
    assay.setActiveIngredient(ingredient);

    Term specie = Term.validateByDisplayLabel(this.getSpecies(), TimeResponseAssayView.getSpeciesMd());
    assay.setSpecies(specie);
    Term stage = Term.validateByDisplayLabel(this.getLifeStage(),
        TimeResponseAssayView.getLifeStageMd());
    assay.setLifeStage(stage);
    assay.setSynergist(this.getSynergist());
    assay
        .setOutcome(Term.validateByDisplayLabel(this.getOutcome(), DiagnosticAssayView.getOutcomeMd()));

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(ACTIVEINGREDIENT);
    list.add(SPECIES);
    list.add(LIFESTAGE);
    list.add(SYNERGIST);
    list.add(OUTCOME);
    return list;
  }

}
