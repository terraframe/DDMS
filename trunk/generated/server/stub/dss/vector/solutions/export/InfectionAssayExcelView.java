package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.InfectionAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.ontology.Term;

public class InfectionAssayExcelView extends InfectionAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
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

    assay.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, COLLECTIONID))
    {
      assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, MOSQUITOID))
    {
      assay.setMosquitoId(this.getMosquitoId());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SPECIES))
    {
      assay
          .setSpecies(Term.validateByDisplayLabel(this.getSpecies(), InfectionAssayView.getSpeciesMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, IDENTMETHOD))
    {
      assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
          InfectionAssayView.getIdentMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SEX))
    {
      assay.setSex(Term.validateByDisplayLabel(this.getSex(), InfectionAssayView.getSexMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, PARASITE))
    {
      assay.setParasite(Term.validateByDisplayLabel(this.getParasite(),
          InfectionAssayView.getParasiteMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, TESTMETHOD))
    {
      assay.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
          InfectionAssayView.getTestMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, INFECTED))
    {
      assay.setInfected(this.getInfected());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERTESTED))
    {
      assay.setNumberTested(this.getNumberTested());
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
    list.add(MOSQUITOID);
    list.add(SPECIES);
    list.add(IDENTMETHOD);
    list.add(SEX);
    list.add(PARASITE);
    list.add(TESTMETHOD);
    list.add(INFECTED);
    list.add(NUMBERTESTED);
    list.add(NUMBERPOSITIVE);
    return list;
  }
}
