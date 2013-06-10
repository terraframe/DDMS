package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.TimeResponseAssay;
import dss.vector.solutions.entomology.TimeResponseAssayQuery;
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
    
    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
    Term assayTerm = Term.validateByDisplayLabel(this.getAssay(), TimeResponseAssayView.getAssayMd());
    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(), TimeResponseAssayView.getActiveIngredientMd());
    Term specie = Term.validateByDisplayLabel(this.getSpecies(), TimeResponseAssayView.getSpeciesMd());
    Term stage = Term.validateByDisplayLabel(this.getLifeStage(), TimeResponseAssayView.getLifeStageMd());
    
    // Search for an existing record
    TimeResponseAssayQuery query = new TimeResponseAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(collection));
    query.WHERE(query.getAssay().EQ(assayTerm));
    query.WHERE(query.getActiveIngredient().EQ(ingredient));
    query.WHERE(query.getSpecies().EQ(specie));
    query.WHERE(query.getLifeStage().EQ(stage));
    OIterator<? extends TimeResponseAssay> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      TimeResponseAssay next = iterator.next();
      next.lock();
      assay.populateView(next);
    }
    else
    {
      assay.setCollection(collection);
      assay.setAssay(assayTerm);
      assay.setActiveIngredient(ingredient);
      assay.setSpecies(specie);
      assay.setLifeStage(stage);
    }
    iterator.close();
    
    assay.setUniqueAssayId(this.getUniqueAssayId());
    assay.setSynergist(this.getSynergist());
    assay.setTestStrainResult(this.getTestStrainResult());
    assay.setReferenceStrainResult(this.getReferenceStrainResult());
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
