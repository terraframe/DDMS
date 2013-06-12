package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.MolecularAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.ontology.Term;

public class MolecularAssayExcelView extends MolecularAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1979900442;

  public MolecularAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    MolecularAssayView assay = new MolecularAssayView();

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
          .setSpecies(Term.validateByDisplayLabel(this.getSpecies(), MolecularAssayView.getSpeciesMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, IDENTMETHOD))
    {
      assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
          MolecularAssayView.getIdentMethodMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SEX))
    {
      assay.setSex(Term.validateByDisplayLabel(this.getSex(), MolecularAssayView.getSexMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, GENERATION))
    {
      assay.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
          MolecularAssayView.getGenerationMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, ISOFEMALE))
    {
      assay.setIsofemale(this.getIsofemale());

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, ASSAYMETHOD))
    {
      assay.setAssayMethod(Term.validateByDisplayLabel(this.getAssayMethod(),
          MolecularAssayView.getAssayMethodMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, TARGET))
    {
      assay.setTarget(Term.validateByDisplayLabel(this.getTarget(), MolecularAssayView.getTargetMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERRR))
    {
      assay.setNumberRR(this.getNumberRR());

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERRS))
    {
      assay.setNumberRS(this.getNumberRS());

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERSS))
    {
      assay.setNumberSS(this.getNumberSS());

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
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(ASSAYMETHOD);
    list.add(TARGET);
    list.add(NUMBERRR);
    list.add(NUMBERRS);
    list.add(NUMBERSS);
    return list;
  }
}
