package dss.vector.solutions.general;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Property;
import dss.vector.solutions.PropertyInfo;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoHierarchyProperty;
import dss.vector.solutions.threshold.FacilityThresholdCalculator;
import dss.vector.solutions.threshold.PoliticalThresholdCalculator;
import dss.vector.solutions.threshold.ThresholdCalculator;

public class ThresholdCalculationTypeView extends ThresholdCalculationTypeViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long   serialVersionUID = 1257806999566L;

  private static final String WEIGHT           = "weight";

  public ThresholdCalculationTypeView()
  {
    super();
  }

  public void populateView(ThresholdCalculationType concrete)
  {
    this.setConcreteId(concrete.getId());

    this.populateConfiguration();

    this.setWeeksBefore(concrete.getWeeksBefore());
    this.setWeeksAfter(concrete.getWeeksAfter());
    this.setPriorYears(concrete.getPriorYears());

    this.setWeight0(concrete.getWeight0());
    this.setWeight1(concrete.getWeight1());
    this.setWeight2(concrete.getWeight2());
    this.setWeight3(concrete.getWeight3());
    this.setWeight4(concrete.getWeight4());
    this.setWeight5(concrete.getWeight5());
    this.setWeight6(concrete.getWeight6());
    this.setWeight7(concrete.getWeight7());
    this.setWeight8(concrete.getWeight8());
    this.setWeight9(concrete.getWeight9());

    this.clearCaseTypes();
    for (ThresholdCalculationCaseTypes m : concrete.getCaseTypes())
    {
      this.addCaseTypes(m);
    }

    this.clearT1Method();
    for (ThresholdCalculationMethod m : concrete.getT1Method())
    {
      this.addT1Method(m);
    }

    this.clearT2Method();
    for (ThresholdCalculationMethod m : concrete.getT2Method())
    {
      this.addT2Method(m);
    }
  }

  private void populateConfiguration()
  {
    Property isEpiWeek = Property.getByPackageAndName(PropertyInfo.GENERAL_PACKAGE, PropertyInfo.IS_EPI_WEEK);

    try
    {
      GeoHierarchyProperty property = (GeoHierarchyProperty) GeoHierarchyProperty.get(GeoHierarchyProperty.CLASS, PropertyInfo.EPIDEMIC_UNIVERSAL);
      
      this.setEpidemicUniversal(property.getGeoHierarchy());
    }
    catch (DataNotFoundException e)
    {
      // Do nothing
    }

    if (isEpiWeek != null)
    {
      try
      {
        OutbreakCalculation countingMethod = OutbreakCalculation.valueOf(isEpiWeek.getPropertyValue());

        this.setCountingMethod(OutbreakCalculation.EPI_WEEK.equals(countingMethod));
      }
      catch (Exception e)
      {
        this.setCountingMethod(true);
      }
    }
    else
    {
      this.setCountingMethod(true);
    }
  }

  private void populateConcrete(ThresholdCalculationType concrete)
  {
    concrete.setWeeksBefore(this.getWeeksBefore());
    concrete.setWeeksAfter(this.getWeeksAfter());
    concrete.setPriorYears(this.getPriorYears());

    if (this.getPriorYears() != null)
    {
      int years = this.getPriorYears();

      for (int i = 0; i < years; i++)
      {
        concrete.setValue(ThresholdCalculationType.WEIGHT + i, this.getWeight(i));
      }
    }

    concrete.clearCaseTypes();
    for (ThresholdCalculationCaseTypes m : this.getCaseTypes())
    {
      concrete.addCaseTypes(m);
    }

    concrete.clearT1Method();
    for (ThresholdCalculationMethod m : this.getT1Method())
    {
      concrete.addT1Method(m);
    }

    concrete.clearT2Method();
    for (ThresholdCalculationMethod m : this.getT2Method())
    {
      concrete.addT2Method(m);
    }
  }

  public String getWeight(int i)
  {
    return this.getValue(WEIGHT + i);
  }

  private void buildAttributeMap(ThresholdCalculationType concrete)
  {
    new AttributeNotificationMap(concrete, ThresholdCalculationType.CASETYPES, this, ThresholdCalculationTypeView.CASETYPES);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.T1METHOD, this, ThresholdCalculationTypeView.T1METHOD);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.T2METHOD, this, ThresholdCalculationTypeView.T2METHOD);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEEKSBEFORE, this, ThresholdCalculationTypeView.WEEKSBEFORE);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEEKSAFTER, this, ThresholdCalculationTypeView.WEEKSAFTER);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.PRIORYEARS, this, ThresholdCalculationTypeView.PRIORYEARS);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT0, this, ThresholdCalculationTypeView.WEIGHT0);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT1, this, ThresholdCalculationTypeView.WEIGHT1);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT2, this, ThresholdCalculationTypeView.WEIGHT2);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT3, this, ThresholdCalculationTypeView.WEIGHT3);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT4, this, ThresholdCalculationTypeView.WEIGHT4);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT5, this, ThresholdCalculationTypeView.WEIGHT5);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT6, this, ThresholdCalculationTypeView.WEIGHT6);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT7, this, ThresholdCalculationTypeView.WEIGHT7);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT8, this, ThresholdCalculationTypeView.WEIGHT8);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.WEIGHT9, this, ThresholdCalculationTypeView.WEIGHT9);
  }

  @Override
  @Transaction
  public void apply()
  {
    GeoHierarchy universal = this.getEpidemicUniversal();

    GeoHierarchyProperty property;

    try
    {
      property = (GeoHierarchyProperty) GeoHierarchyProperty.get(GeoHierarchyProperty.CLASS, PropertyInfo.EPIDEMIC_UNIVERSAL);
      property.lock();
    }
    catch (DataNotFoundException e)
    {
      property = new GeoHierarchyProperty();
      property.setPropertyName(PropertyInfo.EPIDEMIC_UNIVERSAL);
    }

    property.setGeoHierarchy(universal);
    property.apply();

    ThresholdCalculationType calculationType = this.saveCalculationType();
    this.populateView(calculationType);
  }

  public ThresholdCalculationType saveCalculationType()
  {
    ThresholdCalculationType calculationType = new ThresholdCalculationType();

    this.buildAttributeMap(calculationType);

    this.populateConcrete(calculationType);

    calculationType.apply();

    return calculationType;
  }

  public static Integer getPercentComplete()
  {
    return ThresholdCalculator.getPercentComplete();
  }

  @Transaction
  public static void calculateThresholds(ThresholdCalculationTypeView thresholdCalculation, Boolean currentPeriod)
  {
    ThresholdCalculationType calcType = thresholdCalculation.saveCalculationType();
    ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, currentPeriod);
    ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, currentPeriod);
  }

  @Transaction
  public static void calculatePoliticalThresholds(ThresholdCalculationTypeView thresholdCalculation, Boolean currentPeriod)
  {
    ThresholdCalculationType calcType = thresholdCalculation.saveCalculationType();
    ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, currentPeriod);
  }

  @Transaction
  public static void calculateFacilityThresholds(ThresholdCalculationTypeView thresholdCalculation, Boolean currentPeriod)
  {
    ThresholdCalculationType calcType = thresholdCalculation.saveCalculationType();
    ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, currentPeriod);
  }

  public static ThresholdCalculationTypeView getCalculationThreshold()
  {
    ThresholdCalculationTypeQuery query = new ThresholdCalculationTypeQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getCreateDate());

    OIterator<? extends ThresholdCalculationType> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        return iterator.next().getView();
      }

      // No ThresholdCalculationType has been created before. Therefore
      // return one containing the default values.
      ThresholdCalculationTypeView view = new ThresholdCalculationTypeView();
      view.populateConfiguration();

      return view;
    }
    finally
    {
      iterator.close();
    }
  }

  public double[] getWeights()
  {
    double[] weights = new double[this.getPriorYears()];
    switch (this.getPriorYears())
    {
      case 10:
        weights[9] = this.getWeight9();
      case 9:
        weights[8] = this.getWeight8();
      case 8:
        weights[7] = this.getWeight7();
      case 7:
        weights[6] = this.getWeight6();
      case 6:
        weights[5] = this.getWeight5();
      case 5:
        weights[4] = this.getWeight4();
      case 4:
        weights[3] = this.getWeight3();
      case 3:
        weights[2] = this.getWeight2();
      case 2:
        weights[1] = this.getWeight1();
      case 1:
        weights[0] = this.getWeight0();
    }
    return weights;
  }
}
