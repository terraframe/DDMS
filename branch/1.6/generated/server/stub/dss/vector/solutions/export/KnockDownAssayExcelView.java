package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.SkipIfProblem;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.assay.AdultAgeRange;
import dss.vector.solutions.entomology.assay.KnockDownAssay;
import dss.vector.solutions.entomology.assay.KnockDownInterval;
import dss.vector.solutions.entomology.assay.KnockDownIntervalQuery;
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

    kda.setCollection(MosquitoCollection.getByCollectionId(this.getCollectionId()));
    kda.setTestDate(this.getTestDate());
    kda.setTestMethod(Term.validateByDisplayLabel(this.getTestMethod(),
        KnockDownAssay.getTestMethodMd()));
    kda.setGeneration(Term.validateByDisplayLabel(this.getGeneration(),
        KnockDownAssay.getGenerationMd()));
    kda.setIsofemale(this.getIsofemale());
    kda.setSex(Term.validateByDisplayLabel(this.getSex(), KnockDownAssay.getSexMd()));
    kda.setSpecie(Term.validateByDisplayLabel(this.getSpecie(), KnockDownAssay.getSpecieMd()));
    kda.setIdentificationMethod(Term.validateByDisplayLabel(this.getIdentificationMethod(),
        KnockDownAssay.getIdentificationMethodMd()));
    
    AdultAgeRange excelAgeRange = this.getAgeRange();
    if (excelAgeRange != null)
    {
      AdultAgeRange newAgeRange = kda.getAgeRange();
      newAgeRange.setStartPoint(excelAgeRange.getStartPoint());
      newAgeRange.setEndPoint(excelAgeRange.getEndPoint());
    }

    kda.setFed(this.getFed());
    kda.setGravid(this.getGravid());
    kda.setExposureTime(this.getExposureTime());

    // set the Insecticide if at least one value is set (three values are
    // required, but
    // we want validation to notify the user that values are missing).
    if (this.isModified(INSECTICIDEACTIVEINGREDIENT) || this.isModified(INSECTICIDEUNITS)
        || this.isModified(INSECTICIDEAMOUNT))
    {
      kda.setInsecticide(Insecticide.get(this.getInsecticideActiveIngredient(),
          this.getInsecticideUnits(), this.getInsecticideAmount()));
    }

    kda.setQuantityTested(this.getQuantityTested());
    kda.setKd50(this.getKd50());
    kda.setKd95(this.getKd95());

    kda.apply();

    this.applyIntervalAmounts(kda);
  }

  /**
   * Apply any interval/amount grid data (assuming no prior problems, hence the
   * annotation to protect against error propagation)
   * 
   * @param assay
   */
  @SkipIfProblem
  private void applyIntervalAmounts(KnockDownAssay assay)
  {
    // see if there's any interval grid data.
    if (this.getIntervalTime() != null && this.getAmount() != null)
    {
      Integer time = this.getIntervalTime();
      Integer amount = this.getAmount();

      QueryFactory f = new QueryFactory();

      KnockDownIntervalQuery addiQ = new KnockDownIntervalQuery(f);

      // look for a match on the assay and time
      addiQ.WHERE(addiQ.getAssay().EQ(assay));
      addiQ.AND(addiQ.getIntervalTime().EQ(time));

      KnockDownInterval interval = null;

      OIterator<? extends KnockDownInterval> iter = addiQ.getIterator();

      try
      {
        // fetch the existing record or create a new one.
        if (iter.hasNext())
        {
          interval = iter.next();
        }
        else
        {
          interval = new KnockDownInterval();
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
    list.add(INSECTICIDEACTIVEINGREDIENT);
    list.add(INSECTICIDEAMOUNT);
    list.add(INSECTICIDEUNITS);
    list.add(QUANTITYTESTED);
    list.add(KD50);
    list.add(KD95);
    list.add(INTERVALTIME);
    list.add(AMOUNT);
    return list;
  }
}
