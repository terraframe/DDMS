package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.ontology.Term;

public class LarvaeDiscriminatingDoseAssayExcelView extends LarvaeDiscriminatingDoseAssayExcelViewBase
    implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245996428742L;

  public LarvaeDiscriminatingDoseAssayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    LarvaeDiscriminatingDoseAssay ldda = UniqueAssayUtil.getOrCreateAssay(
        LarvaeDiscriminatingDoseAssay.class, this.getUniqueAssayId());
    if (!ldda.isNew())
    {
      ldda.appLock();
    }

    ldda.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, COLLECTIONID))
    {
      ldda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, TESTDATE))
    {
      ldda.setTestDate(this.getTestDate());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, SPECIE))
    {
      ldda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(),
          LarvaeDiscriminatingDoseAssay.getSpecieMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, IDENTIFICATIONMETHOD))
    {
      ldda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(),
          LarvaeDiscriminatingDoseAssay.getIdentificationMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, TESTMETHOD))
    {
      ldda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
          LarvaeDiscriminatingDoseAssay.getTestMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, GENERATION))
    {
      ldda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
          LarvaeDiscriminatingDoseAssay.getGenerationMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, ISOFEMALE))
    {
      ldda.setIsofemale(this.getIsofemale());
    }

    // Age ranges
    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, STARTPOINT))
    {
      ldda.setStartPoint(Term.validateByDisplayLabel(this.getStartPoint(),
          LarvaeDiscriminatingDoseAssay.getStartPointMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, ENDPOINT))
    {
      ldda.setEndPoint(Term.validateByDisplayLabel(this.getEndPoint(),
          LarvaeDiscriminatingDoseAssay.getEndPointMd()));
    }

    // set the Insecticide if at least one value is set (three values are
    // required, but
    // we want validation to notify the user that values are missing).
    if (this.isModified(INSECTICIDEACTIVEINGREDIENT) || this.isModified(INSECTICIDEUNITS)
        || this.isModified(INSECTICIDEAMOUNT))
    {
      ldda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(),
          this.getInsecticideUnits(), this.getInsecticideAmount()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, EXPOSURETIME))
    {
      ldda.setExposureTime(this.getExposureTime());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, HOLDINGTIME))
    {
      ldda.setHoldingTime(this.getHoldingTime());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, QUANTITYTESTED))
    {
      ldda.setQuantityTested(this.getQuantityTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, QUANTITYDEAD))
    {
      ldda.setQuantityDead(this.getQuantityDead());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, CONTROLTESTMORTALITY))
    {
      ldda.setControlTestMortality(this.getControlTestMortality());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, LT50))
    {
      ldda.setLt50(this.getLt50());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, ldda, LT95))
    {
      ldda.setLt95(this.getLt95());
    }

    ldda.apply();
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(UNIQUEASSAYID);
    list.add(COLLECTIONID);
    list.add(TESTDATE);
    list.add(SPECIE);
    list.add(IDENTIFICATIONMETHOD);
    list.add(TESTMETHOD);
    list.add(GENERATION);
    list.add(ISOFEMALE);
    list.add(STARTPOINT);
    list.add(ENDPOINT);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(EXPOSURETIME);
    list.add(HOLDINGTIME);
    list.add(QUANTITYTESTED);
    list.add(QUANTITYDEAD);
    list.add(CONTROLTESTMORTALITY);
    list.add(LT50);
    list.add(LT95);
    return list;
  }
}
