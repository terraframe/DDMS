package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.PooledInfectionAssayView;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.ontology.Term;

public class PooledInfectionAssayExcelView extends PooledInfectionAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2134010;

  public PooledInfectionAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    PooledInfectionAssayView assay = new PooledInfectionAssayView();

    assay.setUniqueAssayId(this.getUniqueAssayId());

    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setPoolId(this.getPoolId());
    assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(),
        PooledInfectionAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
        PooledInfectionAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), PooledInfectionAssayView.getSexMd()));
    assay.setParasite(Term.validateByDisplayLabel(this.getParasite(),
        PooledInfectionAssayView.getParasiteMd()));
    assay.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
        PooledInfectionAssayView.getTestMethodMd()));
    assay.setInfected(this.getInfected());
    assay.setMosquitosTested(this.getMosquitosTested());
    assay.setPoolsTested(this.getPoolsTested());
    assay.setNumberPositive(this.getNumberPositive());

    assay.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(POOLID);
    list.add(SPECIES);
    list.add(IDENTMETHOD);
    list.add(SEX);
    list.add(PARASITE);
    list.add(TESTMETHOD);
    list.add(INFECTED);
    list.add(MOSQUITOSTESTED);
    list.add(POOLSTESTED);
    list.add(NUMBERPOSITIVE);
    return list;
  }
}
