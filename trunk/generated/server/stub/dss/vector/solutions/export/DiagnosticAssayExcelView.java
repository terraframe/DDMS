package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.DiagnosticAssay;
import dss.vector.solutions.entomology.DiagnosticAssayQuery;
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
    
    MosquitoCollection collection = MosquitoCollection.getByCollectionId(this.getCollectionId());
    Term ingredient = Term.validateByDisplayLabel(this.getActiveIngredient(), DiagnosticAssayView.getActiveIngredientMd());
    Term specie = Term.validateByDisplayLabel(this.getSpecies(), DiagnosticAssayView.getSpeciesMd());
    Term stage = Term.validateByDisplayLabel(this.getLifeStage(), DiagnosticAssayView.getLifeStageMd());
    
    // Search for an existing record
    DiagnosticAssayQuery query = new DiagnosticAssayQuery(new QueryFactory());
    query.WHERE(query.getCollection().EQ(collection));
    query.WHERE(query.getActiveIngredient().EQ(ingredient));
    query.WHERE(query.getSpecies().EQ(specie));
    query.WHERE(query.getLifeStage().EQ(stage));
    OIterator<? extends DiagnosticAssay> iterator = query.getIterator();
    if (iterator.hasNext())
    {
      DiagnosticAssay next = iterator.next();
      next.lock();
      assay.populateView(next);
    }
    else
    {
      assay.setCollection(collection);
      assay.setActiveIngredient(ingredient);
      assay.setSpecies(specie);
      assay.setLifeStage(stage);
    }
    iterator.close();
    
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
