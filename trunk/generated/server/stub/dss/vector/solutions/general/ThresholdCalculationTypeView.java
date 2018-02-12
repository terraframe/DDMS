/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.general;

import java.util.List;

import com.runwaysdk.ProblemIF;
import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.RequestManagement;

import dss.vector.solutions.InstallProperties;
import dss.vector.solutions.threshold.FacilityThresholdCalculator;
import dss.vector.solutions.threshold.PoliticalThresholdCalculator;
import dss.vector.solutions.threshold.ThresholdCalculator;

public class ThresholdCalculationTypeView extends ThresholdCalculationTypeViewBase implements com.runwaysdk.generation.loader.Reloadable
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

    this.setNotificationMinimum(concrete.getNotificationMinimum());
    this.setIdentificationMinimum(concrete.getIdentificationMinimum());
  }

  private void populateView(ThresholdAlertCalculationType concrete)
  {
    this.clearCountingMethod();
    for (OutbreakCalculation m : concrete.getCountingMethod())
    {
      this.addCountingMethod(m);
    }

    this.setEpidemicUniversal(concrete.getEpidemicUniversal());

    this.setClinicalPositivePercentage(concrete.getClinicalPositivePercentage());
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

    concrete.setNotificationMinimum(this.getNotificationMinimum());
    concrete.setIdentificationMinimum(this.getIdentificationMinimum());

  }

  private void populateConcrete(ThresholdAlertCalculationType concrete)
  {
    concrete.clearCountingMethod();
    for (OutbreakCalculation m : this.getCountingMethod())
    {
      concrete.addCountingMethod(m);
    }

    concrete.setEpidemicUniversal(this.getEpidemicUniversal());
    concrete.setClinicalPositivePercentage(this.getClinicalPositivePercentage());
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
    new AttributeNotificationMap(concrete, ThresholdCalculationType.NOTIFICATIONMINIMUM, this, ThresholdCalculationTypeView.NOTIFICATIONMINIMUM);
    new AttributeNotificationMap(concrete, ThresholdCalculationType.IDENTIFICATIONMINIMUM, this, ThresholdCalculationTypeView.IDENTIFICATIONMINIMUM);
  }

  private void buildAttributeMap(ThresholdAlertCalculationType concrete)
  {
    new AttributeNotificationMap(concrete, ThresholdAlertCalculationType.COUNTINGMETHOD, this, ThresholdCalculationTypeView.COUNTINGMETHOD);
    new AttributeNotificationMap(concrete, ThresholdAlertCalculationType.EPIDEMICUNIVERSAL, this, ThresholdCalculationTypeView.EPIDEMICUNIVERSAL);
    new AttributeNotificationMap(concrete, ThresholdAlertCalculationType.CLINICALPOSITIVEPERCENTAGE, this, ThresholdCalculationTypeView.CLINICALPOSITIVEPERCENTAGE);
  }

  @Override
  @Transaction
  public void apply()
  {
    ThresholdCalculationType calculationType = this.saveCalculationType();
    this.populateView(calculationType);

    ThresholdAlertCalculationType alertCalculationType = this.saveAlertCalculationType();
    this.populateView(alertCalculationType);
  }

  public ThresholdCalculationType saveCalculationType()
  {
    ThresholdCalculationType calculationType = new ThresholdCalculationType();

    this.buildAttributeMap(calculationType);

    this.populateConcrete(calculationType);

    calculationType.apply();

    return calculationType;
  }

  public ThresholdAlertCalculationType saveAlertCalculationType()
  {

    ThresholdAlertCalculationType alertCalculationType = ThresholdAlertCalculationType.getCurrent();

    alertCalculationType.lock();

    this.buildAttributeMap(alertCalculationType);

    this.populateConcrete(alertCalculationType);

    alertCalculationType.apply();

    return alertCalculationType;
  }

  public static Integer getPercentComplete()
  {
    return ThresholdCalculator.getPercentComplete();
  }

  @Transaction
  public static void calculateThresholds(ThresholdCalculationTypeView thresholdCalculation, Boolean currentPeriod)
  {
    InstallProperties.validateMasterOperation();

    thresholdCalculation.saveAlertCalculationType();
    ThresholdCalculationType calcType = thresholdCalculation.saveCalculationType();
    ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, currentPeriod);
    ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, currentPeriod);
  }

  @Transaction
  public static void calculatePoliticalThresholds(ThresholdCalculationTypeView thresholdCalculation, Boolean currentPeriod)
  {
    InstallProperties.validateMasterOperation();

    thresholdCalculation.saveAlertCalculationType();
    ThresholdCalculationType calcType = thresholdCalculation.saveCalculationType();
    
    List<ProblemIF> problems = RequestManagement.aspectOf().getProblemList();
    
    if(problems.size() == 0)
    {
      ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, currentPeriod);
    }
  }

  @Transaction
  public static void calculateFacilityThresholds(ThresholdCalculationTypeView thresholdCalculation, Boolean currentPeriod)
  {
    InstallProperties.validateMasterOperation();

    thresholdCalculation.saveAlertCalculationType();
    ThresholdCalculationType calcType = thresholdCalculation.saveCalculationType();
    
    List<ProblemIF> problems = RequestManagement.aspectOf().getProblemList();
    
    if(problems.size() == 0)
    {
      ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, currentPeriod);
    }
  }

  public static ThresholdCalculationTypeView getCalculationThreshold()
  {
    ThresholdCalculationTypeView view = null;
    QueryFactory factory = new QueryFactory();

    ThresholdCalculationTypeQuery query = new ThresholdCalculationTypeQuery(factory);
    query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
    query.ORDER_BY_DESC(query.getCreateDate());

    OIterator<? extends ThresholdCalculationType> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        view = iterator.next().getView();
      }
      else
      {
        // No ThresholdCalculationType has been created before. Therefore
        // return one containing the default values.
        view = new ThresholdCalculationTypeView();
      }
    }
    finally
    {
      iterator.close();
    }

    ThresholdAlertCalculationTypeQuery aquery = new ThresholdAlertCalculationTypeQuery(factory);
    aquery.WHERE(aquery.getDisease().EQ(Disease.getCurrent()));

    OIterator<? extends ThresholdAlertCalculationType> aiterator = aquery.getIterator();

    try
    {
      if (aiterator.hasNext())
      {
        view.populateView(aiterator.next());
      }

    }
    finally
    {
      aiterator.close();
    }

    return view;
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
