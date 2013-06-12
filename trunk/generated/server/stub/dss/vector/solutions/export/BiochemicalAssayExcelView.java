package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.entomology.BiochemicalAssayView;
import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.ontology.Term;

public class BiochemicalAssayExcelView extends BiochemicalAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -1997921529;

  public BiochemicalAssayExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    BiochemicalAssayView assay = new BiochemicalAssayView();

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
      assay.setSpecies(Term.validateByDisplayLabel(this.getSpecies(),
          BiochemicalAssayView.getSpeciesMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, IDENTMETHOD))
    {
      assay.setIdentMethod(Term.validateByDisplayLabel(this.getIdentMethod(),
          BiochemicalAssayView.getIdentMethodMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, SEX))
    {
      assay.setSex(Term.validateByDisplayLabel(this.getSex(), BiochemicalAssayView.getSexMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, GENERATION))
    {
      assay.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
          BiochemicalAssayView.getGenerationMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, ISOFEMALE))
    {
      assay.setIsofemale(this.getIsofemale());

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, ASSAY))
    {
      assay.setAssay(Term.validateByDisplayLabel(this.getAssay(), BiochemicalAssayView.getAssayMd()));

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERTESTED))
    {
      assay.setNumberTested(this.getNumberTested());

    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, assay, NUMBERELEVATED))
    {
      assay.setNumberElevated(this.getNumberElevated());
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
    list.add(ASSAY);
    list.add(NUMBERTESTED);
    list.add(NUMBERELEVATED);
    return list;
  }
}
