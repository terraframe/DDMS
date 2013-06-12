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

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, COLLECTIONID))
    {
      assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, POOLID))
    {
      assay.setPoolId(this.getPoolId());

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SPECIES))
    {
      assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(),
          PooledInfectionAssayView.getSpeciesMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, IDENTMETHOD))
    {

      assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
          PooledInfectionAssayView.getIdentMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SEX))
    {

      assay.setSex(Term.validateByDisplayLabel(this.getSex(), PooledInfectionAssayView.getSexMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, PARASITE))
    {

      assay.setParasite(Term.validateByDisplayLabel(this.getParasite(),
          PooledInfectionAssayView.getParasiteMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, TESTMETHOD))
    {

      assay.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
          PooledInfectionAssayView.getTestMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, INFECTED))
    {

      assay.setInfected(this.getInfected());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, MOSQUITOSTESTED))
    {

      assay.setMosquitosTested(this.getMosquitosTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, POOLSTESTED))
    {

      assay.setPoolsTested(this.getPoolsTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERPOSITIVE))
    {

      assay.setNumberPositive(this.getNumberPositive());
    }

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
