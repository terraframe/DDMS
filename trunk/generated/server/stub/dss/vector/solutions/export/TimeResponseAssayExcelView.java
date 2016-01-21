package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssayView;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.ontology.Term;

public class TimeResponseAssayExcelView extends TimeResponseAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
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

    // NOTE: We are now searching by assay id
//    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
//    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(),
//        DiagnosticAssayView.getActiveIngredientMd());
//    Term specie = Term.validateByDisplayLabel(this.getSpecies(), DiagnosticAssayView.getSpeciesMd());
//    Term stage = Term.validateByDisplayLabel(this.getLifeStage(), DiagnosticAssayView.getLifeStageMd());
//    
    // // Search for an existing record
    // TimeResponseAssayQuery query = new TimeResponseAssayQuery(new
    // QueryFactory());
    // query.WHERE(query.getCollection().EQ(collection));
    // query.WHERE(query.getAssay().EQ(assayTerm));
    // query.WHERE(query.getActiveIngredient().EQ(ingredient));
    // query.WHERE(query.getSpecies().EQ(specie));
    // query.WHERE(query.getLifeStage().EQ(stage));
    // OIterator<? extends TimeResponseAssay> iterator = query.getIterator();
    // try
    // {
    // if (iterator.hasNext())
    // {
    // TimeResponseAssay next = iterator.next();
    // next.lock();
    // assay.populateView(next);
    // }
    // else
    // {
    // assay.setCollection(collection);
    // assay.setAssay(assayTerm);
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

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, ASSAY))
    {
      Term assayTerm = Term.validateByDisplayLabel(this.getAssay(), TimeResponseAssayView.getAssayMd());
      assay.setAssay(assayTerm);
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

    {
      if (UniqueAssayUtil.allowAttributeUpdate(this, assay, TESTSTRAINRESULT))
      assay.setTestStrainResult(this.getTestStrainResult());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, REFERENCESTRAINRESULT))
    {
      assay.setReferenceStrainResult(this.getReferenceStrainResult());
    }

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
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
