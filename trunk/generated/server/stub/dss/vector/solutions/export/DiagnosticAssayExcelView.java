package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.entomology.DiagnosticAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
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

    // NOTE: We are now searching by assay id
    // // Search for an existing record
    // MosquitoCollection collection =
    // MosquitoCollection.getByCollectionId(this.getCollectionId());
    // Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(),
    // DiagnosticAssayView.getActiveIngredientMd());
    // Term specie = Term.validateByDisplayLabel(this.getSpecies(),
    // DiagnosticAssayView.getSpeciesMd());
    // Term stage = Term.validateByDisplayLabel(this.getLifeStage(),
    // DiagnosticAssayView.getLifeStageMd());
    // DiagnosticAssayQuery query = new DiagnosticAssayQuery(new
    // QueryFactory());
    // query.WHERE(query.getCollection().EQ(collection));
    // query.WHERE(query.getActiveIngredient().EQ(ingredient));
    // query.WHERE(query.getSpecies().EQ(specie));
    // query.WHERE(query.getLifeStage().EQ(stage));
    // OIterator<? extends DiagnosticAssay> iterator = query.getIterator();
    // try
    // {
    // if (iterator.hasNext())
    // {
    // DiagnosticAssay next = iterator.next();
    // next.lock();
    // assay.populateView(next);
    // }
    // else
    // {
    // assay.setCollection(collection);
    // assay.setActiveIngredient(ingredient);
    // assay.setSpecies(specie);
    // assay.setLifeStage(stage);
    // }
    // }
    // finally
    // {
    // iterator.close();
    // }

    assay.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, COLLECTIONID))
    {
      MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
      assay.setCollection(collection);
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, ACTIVEINGREDIENT))
    {
      Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(),
          TimeResponseAssayView.getActiveIngredientMd());
      assay.setActiveIngredient(ingredient);
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SPECIES))
    {
      Term specie = Term.validateByDisplayLabel(this.getSpecies(), TimeResponseAssayView.getSpeciesMd());
      assay.setSpecies(specie);
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, LIFESTAGE))
    {
      Term stage = Term.validateByDisplayLabel(this.getLifeStage(),
          TimeResponseAssayView.getLifeStageMd());
      assay.setLifeStage(stage);
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SYNERGIST))
    {
      assay.setSynergist(this.getSynergist());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, OUTCOME))
    {
      assay
          .setOutcome(Term.validateByDisplayLabel(this.getOutcome(), DiagnosticAssayView.getOutcomeMd()));
    }

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
