package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.ontology.Term;

public class KnockDownAssayExcelView extends KnockDownAssayExcelViewBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245998013289L;

  public KnockDownAssayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    KnockDownAssay kda = UniqueAssayUtil.getOrCreateAssay(KnockDownAssay.class, this.getUniqueAssayId());
    if (!kda.isNew())
    {
      kda.appLock();
    }

    kda.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, COLLECTIONID))
    {
      kda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, TESTDATE))
    {
      kda.setTestDate(this.getTestDate());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, TESTMETHOD))
    {
      kda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
          KnockDownAssay.getTestMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, GENERATION))
    {
      kda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
          KnockDownAssay.getGenerationMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, ISOFEMALE))
    {
      kda.setIsofemale(this.getIsofemale());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, SEX))
    {
      kda.setSex(Term.validateByDisplayLabel(this.getSex(), KnockDownAssay.getSexMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, SPECIE))
    {
      kda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), KnockDownAssay.getSpecieMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, IDENTIFICATIONMETHOD))
    {
      kda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(),
          KnockDownAssay.getIdentificationMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, AGERANGE))
    {
      AdultAgeRange excelAgeRange = this.getAgeRange();
      AdultAgeRange newAgeRange = kda.getAgeRange();
      newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
      newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, FED))
    {
      kda.setFed(this.getFed());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, GRAVID))
    {
      kda.setGravid(this.getGravid());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, EXPOSURETIME))
    {
      kda.setExposureTime(this.getExposureTime());
    }

    // FIXME define the policy for updating Insecticide
    kda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(),
          this.getInsecticideUnits(), this.getInsecticideAmount()));

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, QUANTITYTESTED))
    {
      kda.setQuantityTested(this.getQuantityTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, KD50))
    {
      kda.setKd50(this.getKd50());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, kda, KD95))
    {
      kda.setKd95(this.getKd95());
    }

    kda.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(TESTDATE);
    list.add(TESTMETHOD);
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(SEX);
    list.add(SPECIE);
    list.add(IDENTIFICATIONMETHOD);
    list.add(AGERANGE);
    list.add(FED);
    list.add(GRAVID);
    list.add(EXPOSURETIME);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(QUANTITYTESTED);
    list.add(KD50);
    list.add(KD95);
    return list;
  }
}
