package dss.vector.solutions.export.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.SkipIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseInterval;
import dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalQuery;
import dss.vector.solutions.entomology.assay.UniqueAssayUtil;
import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.ontology.Term;

public class AdultDiscriminatingDoseAssayExcelView extends AdultDiscriminatingDoseAssayExcelViewBase
    implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1244508626257L;

  public AdultDiscriminatingDoseAssayExcelView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    AdultDiscriminatingDoseAssay adda = UniqueAssayUtil.getOrCreateAssay(
        AdultDiscriminatingDoseAssay.class, this.getUniqueAssayId());

    if (!adda.isNew())
    {
      adda.appLock();
    }

    adda.setUniqueAssayId(this.getUniqueAssayId());

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, COLLECTIONID))
    {
      adda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, TESTDATE))
    {
      adda.setTestDate(this.getTestDate());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, TESTMETHOD))
    {
      adda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
          AdultDiscriminatingDoseAssay.getTestMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, GENERATION))
    {
      adda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
          AdultDiscriminatingDoseAssay.getGenerationMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, ISOFEMALE))
    {
      adda.setIsofemale(this.getIsofemale());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, SEX))
    {
      adda.setSex(Term.validateByDisplayLabel(this.getSex(), AdultDiscriminatingDoseAssay.getSexMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, SPECIE))
    {
      adda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(),
          AdultDiscriminatingDoseAssay.getSpecieMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, IDENTIFICATIONMETHOD))
    {
      adda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(),
          AdultDiscriminatingDoseAssay.getIdentificationMethodMd()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, AGERANGE))
    {
      AdultAgeRange excelAgeRange = this.getAgeRange();
      AdultAgeRange newAgeRange = adda.getAgeRange();
      newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
      newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, FED))
    {
      adda.setFed(this.getFed());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, GRAVID))
    {
      adda.setGravid(this.getGravid());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, EXPOSURETIME))
    {
      adda.setExposureTime(this.getExposureTime());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, HOLDINGTIME))
    {
      adda.setHoldingTime(this.getHoldingTime());
    }

    // set the Insecticide if at least one value is set (three values are
    // required, but
    // we want validation to notify the user that values are missing).
    if (this.isModified(INSECTICIDEACTIVEINGREDIENT) || this.isModified(INSECTICIDEUNITS)
        || this.isModified(INSECTICIDEAMOUNT))
    {
      adda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(),
          this.getInsecticideUnits(), this.getInsecticideAmount()));
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, QUANTITYTESTED))
    {
      adda.setQuantityTested(this.getQuantityTested());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, QUANTITYDEAD))
    {
      adda.setQuantityDead(this.getQuantityDead());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, CONTROLTESTMORTALITY))
    {
      adda.setControlTestMortality(this.getControlTestMortality());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, KD50))
    {
      adda.setKd50(this.getKd50());
    }

    if (UniqueAssayUtil.allowAttributeUpdate(this, adda, KD95))
    {
      adda.setKd95(this.getKd95());
    }

    adda.apply();

    // with the ADDA applied we can reference it for interval/amount values
    applyIntervalAmounts(adda);
  }

  /**
   * Apply any interval/amount grid data (assuming no prior problems, hence the
   * annotation to protect against error propagation)
   * 
   * @param assay
   */
  @SkipIfProblem
  private void applyIntervalAmounts(AdultDiscriminatingDoseAssay assay)
  {
    // see if there's any interval grid data.
    if (this.getIntervalTime() != null && this.getAmount() != null)
    {
      Integer time = this.getIntervalTime();
      Integer amount = this.getAmount();

      QueryFactory f = new QueryFactory();

      AdultDiscriminatingDoseIntervalQuery addiQ = new AdultDiscriminatingDoseIntervalQuery(f);

      // look for a match on the assay and time
      addiQ.WHERE(addiQ.getAssay().EQ(assay));
      addiQ.AND(addiQ.getIntervalTime().EQ(time));

      AdultDiscriminatingDoseInterval interval = null;

      OIterator<? extends AdultDiscriminatingDoseInterval> iter = addiQ.getIterator();

      try
      {
        // fetch the existing record or create a new one.
        if (iter.hasNext())
        {
          interval = iter.next();
        }
        else
        {
          interval = new AdultDiscriminatingDoseInterval();
          interval.setAssay(assay);
          interval.setIntervalTime(time);
        }
      }
      finally
      {
        iter.close();
      }

      if (!interval.isNew())
      {
        interval.appLock();
      }

      interval.setAmount(amount);
      interval.apply();
    }
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(COLLECTIONID);
    list.add(UNIQUEASSAYID);
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
    list.add(HOLDINGTIME);
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(QUANTITYTESTED);
    list.add(QUANTITYDEAD);
    list.add(CONTROLTESTMORTALITY);
    list.add(KD50);
    list.add(KD95);
    list.add(INTERVALTIME);
    list.add(AMOUNT);
    return list;
  }

}
