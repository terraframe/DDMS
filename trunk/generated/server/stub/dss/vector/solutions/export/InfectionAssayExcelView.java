package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.InfectionAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
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
    assay.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    assay.setMosquitoId(this.getMosquitoId());
    assay
        .setSpecies(Term.validateByDisplayLabel(this.getSpecies(), InfectionAssayView.getSpeciesMd()));
    assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
        InfectionAssayView.getIdentMethodMd()));
    assay.setSex(Term.validateByDisplayLabel(this.getSex(), InfectionAssayView.getSexMd()));
    assay.setParasite(Term.validateByDisplayLabel(this.getParasite(),
        InfectionAssayView.getParasiteMd()));
    assay.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
        InfectionAssayView.getTestMethodMd()));
    assay.setInfected(this.getInfected());
    assay.setNumberTested(this.getNumberTested());
    assay.setNumberPositive(this.getNumberPositive());

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
