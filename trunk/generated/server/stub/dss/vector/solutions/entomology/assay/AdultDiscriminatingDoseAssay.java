package dss.vector.solutions.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.entomology.ResistanceProperty;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.ResistanceQB;

public class AdultDiscriminatingDoseAssay extends AdultDiscriminatingDoseAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 1234543769104L;

  public AdultDiscriminatingDoseAssay()
  {
    super();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if (this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
    }
    else if (this.getCollection() != null && this.getInsecticide() != null)
    {
      return "(" + this.getCollection().getCollectionId() + ", " + this.getInsecticide().toString() + ")";
    }

    return super.toString();
  }

  @Transaction
  public void applyAll(AdultDiscriminatingDoseIntervalView[] intervals)
  {
    this.apply();

    for (AdultDiscriminatingDoseIntervalView interval : intervals)
    {
      interval.setAssay(this);
      interval.apply();
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    AdultDiscriminatingDoseIntervalQuery query = new AdultDiscriminatingDoseIntervalQuery(new QueryFactory());
    query.WHERE(query.getAssay().EQ(this));
    query.ORDER_BY(query.getIntervalTime(), SortOrder.ASC);

    OIterator<? extends AdultDiscriminatingDoseInterval> iterator = query.getIterator();

    try
    {
      List<? extends AdultDiscriminatingDoseInterval> intervals = iterator.getAll();

      for (AdultDiscriminatingDoseInterval interval : intervals)
      {
        interval.delete();
      }
    }
    finally
    {
      iterator.close();
    }

    super.delete();
  }

  @SuppressWarnings("unchecked")
  @Override
  public AdultDiscriminatingDoseIntervalView[] getIntervals()
  {
    AdultDiscriminatingDoseIntervalViewQuery query = new AdultDiscriminatingDoseIntervalViewQuery(new QueryFactory());
    query.WHERE(query.getAssay().EQ(this));
    query.ORDER_BY(query.getIntervalTime(), SortOrder.ASC);

    OIterator<? extends AdultDiscriminatingDoseIntervalView> iterator = query.getIterator();

    try
    {
      List<AdultDiscriminatingDoseIntervalView> list = (List<AdultDiscriminatingDoseIntervalView>) iterator.getAll();

      if (list.size() == 0 && this.isNew())
      {
        /*
         * Return the default intervals
         */
        list = new LinkedList<AdultDiscriminatingDoseIntervalView>();
        list.add(AdultDiscriminatingDoseIntervalView.build(this, 10));
        list.add(AdultDiscriminatingDoseIntervalView.build(this, 20));
        list.add(AdultDiscriminatingDoseIntervalView.build(this, 30));
        list.add(AdultDiscriminatingDoseIntervalView.build(this, 40));
        list.add(AdultDiscriminatingDoseIntervalView.build(this, 50));
        list.add(AdultDiscriminatingDoseIntervalView.build(this, 60));
      }

      return list.toArray(new AdultDiscriminatingDoseIntervalView[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }

  @Override
  public void validateControlTestMortality()
  {
    Float mortality = this.getControlTestMortality();

    if (mortality != null && mortality > 20)
    {
      String msg = "The mortality rate of the control collection exceeds 20% invalidating this test";

      ControlMortalityException e = new ControlMortalityException(msg);
      e.apply();

      throw e;
    }
  }

  @Override
  @Transaction
  public void apply()
  {
    boolean modifiedReplicate = isModifiedReplicate();

    this.applyNoPropigate();

    /*
     * The control test has been modified so update all of the replicates
     */
    if (modifiedReplicate)
    {
      if (this.getRootAssay() != null && this.getRootAssay().length() > 0)
      {
        AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new QueryFactory());
        query.WHERE(query.getRootAssay().EQ(this.getRootAssay()));

        OIterator<? extends AdultDiscriminatingDoseAssay> iterator = query.getIterator();

        try
        {
          while (iterator.hasNext())
          {
            AdultDiscriminatingDoseAssay replicate = iterator.next();

            if (!replicate.getId().equals(this.getId()))
            {
              replicate.lock();
              replicate.setCollection(this.getCollection());
              replicate.setTestDate(this.getTestDate());
              replicate.setTestMethod(this.getTestMethod());
              replicate.setGeneration(this.getGeneration());
              replicate.setIsofemale(this.getIsofemale());
              replicate.setSex(this.getSex());
              replicate.setSpecie(this.getSpecie());
              replicate.setIdentificationMethod(this.getIdentificationMethod());
              replicate.getAgeRange().setStartPoint(this.getAgeRange().getStartPoint());
              replicate.getAgeRange().setEndPoint(this.getAgeRange().getEndPoint());
              replicate.setExposureTime(this.getExposureTime());
              replicate.setHoldingTime(this.getHoldingTime());
              replicate.setInsecticide(this.getInsecticide());
              replicate.setQuantityTested(this.getQuantityTested());
              replicate.setControlTestNumberExposed(this.getControlTestNumberExposed());
              replicate.setControlTestNumberDead(this.getControlTestNumberDead());
              replicate.applyNoPropigate();
            }
          }
        }
        finally
        {
          iterator.close();
        }
      }
    }
  }

  public boolean isModifiedReplicate()
  {
    String[] attributes = new String[] { AdultDiscriminatingDoseAssay.COLLECTION, AdultDiscriminatingDoseAssay.TESTDATE, AdultDiscriminatingDoseAssay.TESTMETHOD, AdultDiscriminatingDoseAssay.GENERATION, AdultDiscriminatingDoseAssay.ISOFEMALE, AdultDiscriminatingDoseAssay.SEX, AdultDiscriminatingDoseAssay.SPECIE, AdultDiscriminatingDoseAssay.IDENTIFICATIONMETHOD, AdultDiscriminatingDoseAssay.AGERANGE, AdultDiscriminatingDoseAssay.EXPOSURETIME, AdultDiscriminatingDoseAssay.HOLDINGTIME,
        AdultDiscriminatingDoseAssay.INSECTICIDE, AdultDiscriminatingDoseAssay.QUANTITYTESTED, AdultDiscriminatingDoseAssay.CONTROLTESTNUMBEREXPOSED, AdultDiscriminatingDoseAssay.CONTROLTESTNUMBERDEAD };

    for (String attribute : attributes)
    {
      if (this.isModified(attribute))
      {
        return true;
      }
    }

    return false;
  }

  private void applyNoPropigate()
  {
    if (this.getRootAssay() == null || this.getRootAssay().length() == 0)
    {
      this.setRootAssay(this.getId());
    }

    if (this.getControlTestNumberDead() != null && this.getControlTestNumberExposed() != null && this.getControlTestNumberExposed() > 0)
    {
      this.setControlTestMortality((float) this.getControlTestNumberDead() / this.getControlTestNumberExposed() * 100);
    }
    else
    {
      this.setControlTestMortality(0F);
    }

    UniqueAssayUtil.setUniqueAssayId(this);

    validateControlTestMortality();
    validateQuantityDead();

    float mortality = 0.0F;
    int live = 0;

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      mortality = (float) ( this.getQuantityDead() ) * 100 / this.getQuantityTested();
      live = this.getQuantityTested() - this.getQuantityDead();
    }

    this.setQuantityLive(live);
    this.setMortality(mortality);
    this.setObservedMortality(mortality);

    if (this.getControlTestMortality() != null && this.getControlTestMortality() > 5)
    {
      // Use abbots formula to correct the mortality rate
      // Corrected % = 100 * (T - C) / (100 - C)
      // (WHO/CDC/NTD/WHOPES/GCDPP/2006.3)
      // T = % mortality of the treated population
      // C = % mortality of the control population

      float corrected = 100.0F * ( mortality - this.getControlTestMortality() ) / ( 100.0F - this.getControlTestMortality() );
      this.setMortality(corrected);
    }

    /*
     * Set the corrected number dead value
     */
    this.setCorrectedQuantityDead( ( this.getMortality() * this.getQuantityTested() / 100 ));

    super.apply();

    if (this.isResistant() && this.getInsecticide() != null && this.getCollection() != null)
    {
      Term activeIngredient = this.getInsecticide().getActiveIngredient();
      String label = activeIngredient.getTermDisplayLabel().getValue(Session.getCurrentLocale());
      String collectionId = this.getCollection().getCollectionId();

      ResistantCollection info = new ResistantCollection();
      info.setActiveIngredient(label);
      info.setCollectionId(collectionId);
      info.throwIt();
    }
  }

  protected boolean isResistant()
  {
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE);

    return ( this.getMortality() < resistant );
  }

  protected boolean isPotentiallyResistant()
  {
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE);
    Integer resistant = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_RESISTANCE);

    return ( resistant < this.getMortality() && this.getMortality() <= susceptible );
  }

  protected boolean isSusceptible()
  {
    Integer susceptible = ResistanceProperty.getPropertyValue(PropertyInfo.ADULT_DDA_SUSCEPTIBILE);

    return ( this.getMortality() > susceptible );
  }

  @Override
  public AdultDiscriminatingDoseAssay cloneAssay()
  {
    AdultDiscriminatingDoseAssay clone = new AdultDiscriminatingDoseAssay();
    clone.setCollection(this.getCollection());
    clone.setTestDate(this.getTestDate());
    clone.setTestMethod(this.getTestMethod());
    clone.setGeneration(this.getGeneration());
    clone.setIsofemale(this.getIsofemale());
    clone.setSex(this.getSex());
    clone.setSpecie(this.getSpecie());
    clone.setIdentificationMethod(this.getIdentificationMethod());
    clone.getAgeRange().setStartPoint(this.getAgeRange().getStartPoint());
    clone.getAgeRange().setEndPoint(this.getAgeRange().getEndPoint());
    clone.setExposureTime(this.getExposureTime());
    clone.setHoldingTime(this.getHoldingTime());
    clone.setInsecticide(this.getInsecticide());
    clone.setQuantityTested(this.getQuantityTested());
    clone.setControlTestNumberExposed(this.getControlTestNumberExposed());
    clone.setControlTestNumberDead(this.getControlTestNumberDead());

    if (this.getRootAssay() != null)
    {
      clone.setRootAssay(this.getRootAssay());
    }
    else
    {
      clone.setRootAssay(this.getId());
    }

    return clone;
  }

  @Override
  public Boolean hasReplicates()
  {
    if (this.getRootAssay() != null && this.getRootAssay().length() > 0)
    {
      if (this.isNew() || !this.isAppliedToDB())
      {
        return true;
      }
      else
      {
        AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new QueryFactory());
        query.WHERE(query.getRootAssay().EQ(this.getRootAssay()));

        long count = query.getCount();

        return ( count > 1 );
      }
    }

    return false;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize)
  {
    return new ResistanceQB(xml, config, layer, pageSize, pageSize).construct();
  }

}
