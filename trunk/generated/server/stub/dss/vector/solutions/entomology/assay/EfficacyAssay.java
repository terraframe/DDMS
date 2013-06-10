package dss.vector.solutions.entomology.assay;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.constants.CommonProperties;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;

import dss.vector.solutions.entomology.ControlMortalityException;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.Surface;
import dss.vector.solutions.irs.InsecticideBrandUse;
import dss.vector.solutions.irs.InvalidInsecticideBrandUseProblem;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.querybuilder.EfficacyAssayQB;

public class EfficacyAssay extends EfficacyAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 1236363373386L;

  public EfficacyAssay()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getUniqueAssayId();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else if(this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
    }
    else if (this.getGeoEntity() != null && this.getInsecticideBrand() != null)
    {
      return "(" + this.getGeoEntity().getLabel() + ", " + this.getInsecticideBrand().toString() + ")";
    }

    return super.toString();
  }

  @Override
  public void validateAgeRange()
  {
    super.validateAgeRange();

    new AdultAgeRangeValidator(this).validate();
  }

  @Override
  public void validateGravid()
  {
    super.validateGravid();

    new GravidValidator(this).validate();
  }

  @Override
  public void validateFed()
  {
    super.validateFed();

    new FedValidator(this).validate();
  }

  @Override
  public void validateQuantityDead()
  {
    super.validateQuantityDead();

    new QuantityDeadValidator(this).validate();
  }

  @Override
  public void validateGeoEntity()
  {
    if (this.getGeoEntity() != null && ! ( this.getGeoEntity() instanceof Surface ))
    {
      throw new InvalidReferenceException("[" + this.getGeoEntity().getId() + "] is not a valid Surface geo id", (MdAttributeReferenceDAOIF) EfficacyAssay.getGeoEntityMd());
    }
  }

  @Override
  public void validateInsecticideBrand()
  {
    super.validateInsecticideBrand();

    if (this.getInsecticideBrand() != null)
    {
      if (!this.getInsecticideBrand().getInsecticideUse().contains(InsecticideBrandUse.ITM) && !this.getInsecticideBrand().getInsecticideUse().contains(InsecticideBrandUse.IRS))
      {
        InvalidInsecticideBrandUseProblem p = new InvalidInsecticideBrandUseProblem();
        p.setNotification(this, INSECTICIDEBRAND);
        p.throwIt();
      }
    }
  }

  @Override
  public void apply()
  {
    UniqueAssayUtil.setUniqueAssayId(this);
    
    validateControlTestMortality();
    validateGeoEntity();
    validateQuantityDead();
    validateAgeRange();
    validateFed();
    validateGravid();
    validateInsecticideBrand();

    boolean modifiedControlMortality = this.isModified(EfficacyAssay.CONTROLTESTMORTALITY);
    Float controlTestMortality = this.getControlTestMortality();

    int live = 0;

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      live = this.getQuantityTested() - this.getQuantityDead();
    }

    this.setQuantityLive(live);

    this.calculateMortalityRate();

    super.apply();

    /*
     * Update all of the existing efficacy assays which have the same geo entity and test date
     */
    if (modifiedControlMortality)
    {
      QueryFactory factory = new QueryFactory();
      EfficacyAssayQuery query = new EfficacyAssayQuery(factory);

      query.WHERE(query.getGeoEntity().EQ(this.getGeoEntity()));
      query.AND(query.getTestDate().EQ(this.getTestDate()));
      query.AND(query.getSiteMaster().EQ(CommonProperties.getDomain()));
      query.AND(query.getId().NE(this.getId()));

      OIterator<? extends EfficacyAssay> iterator = query.getIterator();

      try
      {
        while (iterator.hasNext())
        {
          EfficacyAssay assay = iterator.next();
          assay.updateControlTestMortality(controlTestMortality);
        }
      }
      finally
      {
        iterator.close();
      }
    }
  }

  public void calculateMortalityRate()
  {
    Float controlTestMortality = this.getControlTestMortality();

    float mortality = 0.0F;

    if (this.getQuantityDead() != null && this.getQuantityTested() != null && this.getQuantityDead() <= this.getQuantityTested())
    {
      mortality = (float) ( this.getQuantityDead() ) * 100 / this.getQuantityTested();
    }

    if (controlTestMortality != null && controlTestMortality > 5)
    {
      // Use abbots formula to correct the mortality rate
      // Corrected % = 100 * (T - C) / (100 - C)
      // (WHO/CDC/NTD/WHOPES/GCDPP/2006.3)
      // T = % mortality of the treated population
      // C = % mortality of the control population

      mortality = 100.0F * ( mortality - controlTestMortality ) / ( 100.0F - controlTestMortality );
    }

    this.setMortality(mortality);
  }

  private void updateControlTestMortality(Float controlTestMortality)
  {
    this.appLock();
    this.setControlTestMortality(controlTestMortality);
    this.calculateMortalityRate();
    super.apply();
  }

  @Override
  public Float getOverallMortalityRate()
  {
    int dead = 0;
    int total = 0;
    EfficacyAssay[] assays = searchByGeoEntityAndDate(this.getGeoEntity(), this.getTestDate());

    for (EfficacyAssay assay : assays)
    {
      dead += assay.getQuantityDead();
      total += assay.getQuantityTested();
    }

    if (total == 0)
    {
      return 0.0F;
    }

    float mortality = 0.0F;

    if (dead <= total)
    {
      mortality = (float) ( dead ) * 100 / total;
    }

    if (this.getControlTestMortality() != null && this.getControlTestMortality() > 5)
    {
      // Use abbots formula to correct the mortality rate
      // Corrected % = 100 * (T - C) / (100 - C)
      // (WHO/CDC/NTD/WHOPES/GCDPP/2006.3)
      // T = % mortality of the treated population
      // C = % mortality of the control population

      mortality = 100.0F * ( mortality - this.getControlTestMortality() ) / ( 100.0F - this.getControlTestMortality() );
    }

    return mortality;
  }

  public EfficacyAssayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public EfficacyAssayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  public EfficacyAssayView getView()
  {
    EfficacyAssayView view = new EfficacyAssayView();

    view.populateView(this);

    return view;
  }

  public static EfficacyAssayView getView(String id)
  {
    return EfficacyAssay.get(id).getView();
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    return new EfficacyAssayQB(xml, config, layer).construct();
  }

  /**
   * @param geoId
   * @param testDate
   * @param controlTestMortality
   * @return
   */
  @Request
  public static Boolean checkControlTestMortality(String geoId, Date testDate, Float controlTestMortality)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
    EfficacyAssay[] assays = EfficacyAssay.searchByGeoEntityAndDate(geoEntity, testDate);

    for (EfficacyAssay assay : assays)
    {
      if ( ( controlTestMortality == null && assay.getControlTestMortality() != null ) || ( controlTestMortality != null && assay.getControlTestMortality() == null ))
      {
        return false;
      }

      if (!controlTestMortality.equals(assay.getControlTestMortality()))
      {
        return false;
      }
    }

    return true;
  }

  public static EfficacyAssay[] searchByGeoEntityAndDate(GeoEntity geoEntity, Date testDate)
  {
    QueryFactory factory = new QueryFactory();
    EfficacyAssayQuery query = new EfficacyAssayQuery(factory);

    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getTestDate().EQ(testDate));

    OIterator<? extends EfficacyAssay> iterator = query.getIterator();

    try
    {
      List<EfficacyAssay> list = new LinkedList<EfficacyAssay>();

      while (iterator.hasNext())
      {
        list.add(iterator.next());
      }

      return list.toArray(new EfficacyAssay[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public void validateControlTestMortality()
  {
    if (this.getControlTestMortality() != null && this.getControlTestMortality() > 20)
    {
      String msg = "The mortality rate of the control collection exceeds 20% invalidating this test";

      ControlMortalityException e = new ControlMortalityException(msg);
      e.apply();

      throw e;
    }
  }

}
