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
package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.F;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Request;
import com.runwaysdk.session.RequestType;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.ThresholdCalculationCaseTypes;
import dss.vector.solutions.general.ThresholdCalculationMethod;
import dss.vector.solutions.general.ThresholdCalculationType;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.HealthFacility;
import dss.vector.solutions.intervention.monitor.DiagnosisType;
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.threshold.FacilityThresholdCalculator;
import dss.vector.solutions.threshold.PoliticalThresholdCalculator;
import dss.vector.solutions.threshold.ThresholdCalculator;

public class ThresholdCalculationTest extends RunwayTestCase
{
  private static GeoEntity     EGYPT                    = GeoEntity.searchByGeoId("22220002");

  private static GeoEntity     DJIBOUTI                 = GeoEntity.searchByGeoId("22220033");

  private static GeoEntity     KABWE_MINE_HOSPITAL      = GeoEntity.searchByGeoId("2020010");

  private static GeoEntity     MBOSHYA                  = GeoEntity.searchByGeoId("88000271");

  private static GeoEntity     CHIBOMBO                 = GeoEntity.searchByGeoId("100101");

  private static GeoEntity     MUNGULE                  = GeoEntity.searchByGeoId("23230329");

  private static final boolean CALCULATE_ALL_THRESHOLDS = false;

  private static final boolean CREATE_DATA_ONCE         = true;

  private ThresholdCalculationType createCalculationType(ThresholdCalculationCaseTypes caseTypes, ThresholdCalculationMethod t1, ThresholdCalculationMethod t2)
  {
    double[] weights = { 2.0d, 1.0d, 0.5d };
    return this.createCalculationType(caseTypes, t1, t2, 1, 1, 3, weights);
  }

  private ThresholdCalculationType createCalculationType212(ThresholdCalculationCaseTypes caseTypes, ThresholdCalculationMethod t1, ThresholdCalculationMethod t2)
  {
    double[] weights = { 1.0d, 0.5d };
    return this.createCalculationType(caseTypes, t1, t2, 2, 1, 2, weights);
  }

  private ThresholdCalculationType createCalculationType224(ThresholdCalculationCaseTypes caseTypes, ThresholdCalculationMethod t1, ThresholdCalculationMethod t2)
  {
    double[] weights = { 1.0d, 1.0d, 1.0d, 1.0d };
    return this.createCalculationType(caseTypes, t1, t2, 2, 2, 4, weights);
  }

  @Transaction
  private ThresholdCalculationType createCalculationType(ThresholdCalculationCaseTypes caseTypes, ThresholdCalculationMethod t1, ThresholdCalculationMethod t2, int weeksBefore, int weeksAfter, int priorYears, double[] weights)
  {
    ThresholdCalculationType calcType = new ThresholdCalculationType();

    calcType.addCaseTypes(caseTypes);
    calcType.addT1Method(t1);
    calcType.addT2Method(t2);
    calcType.setWeeksBefore(weeksBefore);
    calcType.setWeeksAfter(weeksAfter);
    calcType.setPriorYears(priorYears);
    calcType.setWeights(weights);

    calcType.apply();

    return calcType;
  }

  private void createIndividualCases(int caseCount, int instanceCount, Date date, Patient patient, GeoEntity geoEntity)
  {
    this.createIndividualCases(Disease.getCurrent(), caseCount, instanceCount, date, patient, geoEntity);
  }

  private void createIndividualCases(Disease disease, int caseCount, int instanceCount, Date date, Patient patient, GeoEntity geoEntity)
  {
    this.createIndividualCases(disease, caseCount, 1, 0, 0, 0, instanceCount, 0, date, patient, geoEntity);

  }

  private void createIndividualCases(Disease disease, int caseCount, int activeClinicalCount, int activePositiveCount, int activeNegativeCount, int passiveClinicalCount, int passivePositiveCount, int passiveNegativeCount, Date date, Patient patient, GeoEntity geoEntity)
  {
    for (int c = 0; c < caseCount; c++)
    {
      IndividualCase caseData = this.createIndividualCase(disease, date, patient, geoEntity);
      for (int aic = 0; aic < activeClinicalCount; aic++)
      {
        this.createIndividualInstance(caseData, date, geoEntity, true, DiagnosisType.CLINICAL_DIAGNOSIS);
      }
      for (int aip = 0; aip < activePositiveCount; aip++)
      {
        this.createIndividualInstance(caseData, date, geoEntity, true, DiagnosisType.POSITIVE_DIAGNOSIS);
      }
      for (int ain = 0; ain < activeNegativeCount; ain++)
      {
        this.createIndividualInstance(caseData, date, geoEntity, true, DiagnosisType.NEGATIVE_DIAGNOSIS);
      }
      for (int pic = 0; pic < passiveClinicalCount; pic++)
      {
        this.createIndividualInstance(caseData, date, geoEntity, false, DiagnosisType.CLINICAL_DIAGNOSIS);
      }
      for (int pip = 0; pip < passivePositiveCount; pip++)
      {
        this.createIndividualInstance(caseData, date, geoEntity, false, DiagnosisType.POSITIVE_DIAGNOSIS);
      }
      for (int pin = 0; pin < passiveNegativeCount; pin++)
      {
        this.createIndividualInstance(caseData, date, geoEntity, false, DiagnosisType.NEGATIVE_DIAGNOSIS);
      }
    }
  }

  private IndividualCase createIndividualCase(Disease disease, Date date, Patient patient, GeoEntity geoEntity)
  {
    IndividualCase caseData = new IndividualCase();
    caseData.setDisease(disease);
    caseData.setDiagnosisDate(date);
    caseData.setPatient(patient);
    caseData.setProbableSource(geoEntity);
    caseData.setResidence(geoEntity);
    caseData.apply();
    return caseData;
  }

  private IndividualInstance createIndividualInstance(IndividualCase caseData, Date date, GeoEntity geoEntity, boolean active, DiagnosisType diagnosisType)
  {
    IndividualInstance instanceData = new IndividualInstance();
    instanceData.setIndividualCase(caseData);
    instanceData.addDiagnosisType(diagnosisType);
    if (geoEntity instanceof HealthFacility)
    {
      instanceData.setHealthFacility((HealthFacility) geoEntity);
    }
    instanceData.setActivelyDetected(active);
    instanceData.setFacilityVisit(date);
    instanceData.apply();
    return instanceData;
  }

  private void createAggregatedCases(int count, Date startDate, Date endDate, GeoEntity geoEntity)
  {
    this.createAggregatedCases(Disease.getCurrent(), count, startDate, endDate, geoEntity);
  }

  private void createAggregatedCases(Disease disease, int count, Date startDate, Date endDate, GeoEntity geoEntity)
  {
    this.createAggregatedCases(disease, 0, count, 0, startDate, endDate, geoEntity);
  }

  private void createAggregatedCases(Disease disease, int clinicalCount, int confirmedPositiveCount, int confirmedNegativeCount, Date startDate, Date endDate, GeoEntity geoEntity)
  {
    AggregatedCase data = new AggregatedCase();
    data.setDisease(disease);
    data.setStartDate(startDate);
    data.setEndDate(endDate);
    data.setGeoEntity(geoEntity);
    data.setAgeGroup(AggregatedAgeGroup.getAll()[0]);
    data.setCases(clinicalCount);
    data.setPositiveCases(confirmedPositiveCount);
    data.setNegativeCases(confirmedNegativeCount);
    data.apply();
  }

  @Transaction
  private void deleteAllTableRecords(String className)
  {
    MdEntity biz = MdEntity.getMdEntity(className);
    biz.deleteAllTableRecords();
  }

  private static Date createRelativeDate(int year, int month, int day)
  {
    return createRelativeCalendar(year, month, day).getTime();
  }

  private static Date createDate(int year, int month, int day)
  {
    return createCalendar(year, month, day).getTime();
  }

  private static Calendar createCalendar(int year, int month, int day)
  {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.set(year, month, day);
    return c;
  }

  private static Calendar createRelativeCalendar(int year, int month, int day)
  {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.YEAR, year);
    c.add(Calendar.MONTH, month);
    c.add(Calendar.DAY_OF_MONTH, day);
    return c;
  }

  private void assertThresholds(boolean checkPolitical, int week, int year, ThresholdData td, double t1, double t2)
  {
    final double DELTA = 0.5d;

    assertNotNull(td);
    WeeklyThreshold weeklyThreshold = td.getEpiWeeksRel(EpiWeek.getEpiWeek(week, year));
    assertNotNull(weeklyThreshold);
    if (checkPolitical)
    {
      assertNotNull(weeklyThreshold.getIdentification());
      assertEquals(t1, weeklyThreshold.getNotification(), DELTA);
      assertNotNull(weeklyThreshold.getNotification());
      assertEquals(t2, weeklyThreshold.getIdentification(), DELTA);
    }
    else
    {
      assertNotNull(weeklyThreshold.getFacilityIdentification());
      assertEquals(t1, weeklyThreshold.getFacilityNotification(), DELTA);
      assertNotNull(weeklyThreshold.getFacilityNotification());
      assertEquals(t2, weeklyThreshold.getFacilityIdentification(), DELTA);
    }
  }

  private void output(String message)
  {
    System.out.println(new Date() + ": " + message);
  }

  @Transaction
  private void clearData()
  {
    this.deleteAllTableRecords(Person.CLASS);
    this.deleteAllTableRecords(Patient.CLASS);
    this.deleteAllTableRecords(IndividualCase.CLASS);
    this.deleteAllTableRecords(IndividualInstance.CLASS);
    this.deleteAllTableRecords(AggregatedCase.CLASS);
    this.deleteAllTableRecords(MalariaSeason.CLASS);
    this.deleteAllTableRecords(PopulationData.CLASS);
    this.deleteAllTableRecords(ThresholdData.CLASS);
    this.deleteAllTableRecords(EpiWeek.CLASS);
    this.deleteAllTableRecords(ThresholdCalculationType.CLASS);
    this.deleteAllTableRecords(WeeklyThreshold.CLASS);
  }

  /******************************************************************************/
  /* TEST DATA CREATION METHODS START HERE */
  /******************************************************************************/

  @Transaction
  private void createData()
  {
    output("Creating Data...");
    this.clearData();

    Disease currentDisease = Disease.getCurrent();
    System.out.println("Current Disease: " + currentDisease);
    Disease otherDisease = null;
    if (currentDisease.equals(Disease.getMalaria()))
    {
      otherDisease = Disease.getDengue();
    }
    else
    {
      otherDisease = Disease.getMalaria();
    }

    String seasonName = "Threshold Calculation Test Season";
    MalariaSeason malariaSeason = new MalariaSeason();
    malariaSeason.setSeasonName(seasonName);
    malariaSeason.getSeasonLabel().setValue(seasonName);
    malariaSeason.setStartDate(createDate(2010, Calendar.NOVEMBER, 1));
    malariaSeason.setEndDate(createDate(2010, Calendar.DECEMBER, 31));
    malariaSeason.apply();

    Person person = new Person();
    person.setFirstName("Chris");
    person.setLastName("Reigrut");
    person.setSex(Term.getByTermId("MDSS:0000354"));
    person.setDateOfBirth(createDate(1968, Calendar.OCTOBER, 25));
    person.apply();

    Patient patient = new Patient();
    patient.setPerson(person);
    patient.apply();

    for (int day = 1; day <= 31; day++)
    {
      this.createIndividualCases(currentDisease, day, 1, createDate(2009, Calendar.AUGUST, day), patient, EGYPT);
    }

    this.createIndividualCases(currentDisease, 9, 1, createDate(2005, Calendar.NOVEMBER, 16), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 9, 1, createDate(2005, Calendar.NOVEMBER, 23), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 9, 1, createDate(2005, Calendar.NOVEMBER, 30), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 9, 1, createDate(2006, Calendar.NOVEMBER, 15), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 9, 1, createDate(2006, Calendar.NOVEMBER, 22), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 9, 1, createDate(2006, Calendar.NOVEMBER, 29), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 2, 1, createDate(2007, Calendar.NOVEMBER, 14), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 4, 1, createDate(2007, Calendar.NOVEMBER, 21), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 4, 1, createDate(2007, Calendar.NOVEMBER, 28), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 1, createDate(2008, Calendar.NOVEMBER, 12), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 1, createDate(2008, Calendar.NOVEMBER, 19), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 3, 1, createDate(2008, Calendar.NOVEMBER, 26), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 3, 1, createDate(2009, Calendar.NOVEMBER, 18), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 2, 1, createDate(2009, Calendar.NOVEMBER, 25), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 1, createDate(2009, Calendar.DECEMBER, 2), patient, DJIBOUTI);

    this.createAggregatedCases(currentDisease, 9, createDate(2005, Calendar.NOVEMBER, 13), createDate(2005, Calendar.NOVEMBER, 19), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 9, createDate(2005, Calendar.NOVEMBER, 20), createDate(2005, Calendar.NOVEMBER, 26), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 9, createDate(2005, Calendar.NOVEMBER, 27), createDate(2005, Calendar.DECEMBER, 3), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 9, createDate(2006, Calendar.NOVEMBER, 12), createDate(2006, Calendar.NOVEMBER, 18), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 9, createDate(2006, Calendar.NOVEMBER, 19), createDate(2006, Calendar.NOVEMBER, 25), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 9, createDate(2006, Calendar.NOVEMBER, 26), createDate(2006, Calendar.DECEMBER, 2), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 2, createDate(2007, Calendar.NOVEMBER, 11), createDate(2007, Calendar.NOVEMBER, 17), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 4, createDate(2007, Calendar.NOVEMBER, 18), createDate(2007, Calendar.NOVEMBER, 24), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 4, createDate(2007, Calendar.NOVEMBER, 25), createDate(2007, Calendar.NOVEMBER, 31), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 1, createDate(2008, Calendar.NOVEMBER, 9), createDate(2008, Calendar.NOVEMBER, 15), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 1, createDate(2008, Calendar.NOVEMBER, 16), createDate(2008, Calendar.NOVEMBER, 22), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 3, createDate(2008, Calendar.NOVEMBER, 23), createDate(2008, Calendar.NOVEMBER, 29), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 3, createDate(2009, Calendar.NOVEMBER, 15), createDate(2009, Calendar.NOVEMBER, 21), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 2, createDate(2009, Calendar.NOVEMBER, 22), createDate(2009, Calendar.NOVEMBER, 28), DJIBOUTI);
    this.createAggregatedCases(currentDisease, 1, createDate(2009, Calendar.NOVEMBER, 29), createDate(2009, Calendar.DECEMBER, 5), DJIBOUTI);

    // This is a period less than a week -- should not be counted
    this.createAggregatedCases(currentDisease, 100, createDate(2008, Calendar.NOVEMBER, 10), createDate(2008, Calendar.NOVEMBER, 14), DJIBOUTI);

    // These are for the wrong disease -- should not be counted
    this.createIndividualCases(otherDisease, 3, 1, createDate(2009, Calendar.NOVEMBER, 18), patient, DJIBOUTI);
    this.createIndividualCases(otherDisease, 2, 1, createDate(2009, Calendar.NOVEMBER, 25), patient, DJIBOUTI);
    this.createIndividualCases(otherDisease, 1, 1, createDate(2009, Calendar.DECEMBER, 2), patient, DJIBOUTI);
    this.createAggregatedCases(otherDisease, 3, createDate(2009, Calendar.NOVEMBER, 15), createDate(2009, Calendar.NOVEMBER, 21), DJIBOUTI);
    this.createAggregatedCases(otherDisease, 2, createDate(2009, Calendar.NOVEMBER, 22), createDate(2009, Calendar.NOVEMBER, 28), DJIBOUTI);
    this.createAggregatedCases(otherDisease, 1, createDate(2009, Calendar.NOVEMBER, 29), createDate(2009, Calendar.DECEMBER, 5), DJIBOUTI);

    this.createIndividualCases(currentDisease, 1, 9, createDate(2005, Calendar.NOVEMBER, 16), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 9, createDate(2005, Calendar.NOVEMBER, 23), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 9, createDate(2005, Calendar.NOVEMBER, 30), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 9, createDate(2006, Calendar.NOVEMBER, 15), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 9, createDate(2006, Calendar.NOVEMBER, 22), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 9, createDate(2006, Calendar.NOVEMBER, 29), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 2, createDate(2007, Calendar.NOVEMBER, 14), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 4, createDate(2007, Calendar.NOVEMBER, 21), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 4, createDate(2007, Calendar.NOVEMBER, 28), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 1, createDate(2008, Calendar.NOVEMBER, 12), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 1, createDate(2008, Calendar.NOVEMBER, 19), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 3, createDate(2008, Calendar.NOVEMBER, 26), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 3, createDate(2009, Calendar.NOVEMBER, 18), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 2, createDate(2009, Calendar.NOVEMBER, 25), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(currentDisease, 1, 1, createDate(2009, Calendar.DECEMBER, 2), patient, KABWE_MINE_HOSPITAL);

    this.createAggregatedCases(currentDisease, 9, createDate(2005, Calendar.NOVEMBER, 13), createDate(2005, Calendar.NOVEMBER, 19), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 9, createDate(2005, Calendar.NOVEMBER, 20), createDate(2005, Calendar.NOVEMBER, 26), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 9, createDate(2005, Calendar.NOVEMBER, 27), createDate(2005, Calendar.DECEMBER, 3), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 9, createDate(2006, Calendar.NOVEMBER, 12), createDate(2006, Calendar.NOVEMBER, 18), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 9, createDate(2006, Calendar.NOVEMBER, 19), createDate(2006, Calendar.NOVEMBER, 25), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 9, createDate(2006, Calendar.NOVEMBER, 26), createDate(2006, Calendar.DECEMBER, 2), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 2, createDate(2007, Calendar.NOVEMBER, 11), createDate(2007, Calendar.NOVEMBER, 17), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 4, createDate(2007, Calendar.NOVEMBER, 18), createDate(2007, Calendar.NOVEMBER, 24), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 4, createDate(2007, Calendar.NOVEMBER, 25), createDate(2007, Calendar.NOVEMBER, 31), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 1, createDate(2008, Calendar.NOVEMBER, 9), createDate(2008, Calendar.NOVEMBER, 15), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 1, createDate(2008, Calendar.NOVEMBER, 16), createDate(2008, Calendar.NOVEMBER, 22), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 3, createDate(2008, Calendar.NOVEMBER, 23), createDate(2008, Calendar.NOVEMBER, 29), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 3, createDate(2009, Calendar.NOVEMBER, 15), createDate(2009, Calendar.NOVEMBER, 21), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 2, createDate(2009, Calendar.NOVEMBER, 22), createDate(2009, Calendar.NOVEMBER, 28), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(currentDisease, 1, createDate(2009, Calendar.NOVEMBER, 29), createDate(2009, Calendar.DECEMBER, 5), KABWE_MINE_HOSPITAL);

    // This is a period less than a week -- should not be counted
    this.createAggregatedCases(currentDisease, 100, createDate(2008, Calendar.NOVEMBER, 10), createDate(2008, Calendar.NOVEMBER, 14), KABWE_MINE_HOSPITAL);

    // These are for the wrong disease -- should not be counted
    this.createIndividualCases(otherDisease, 1, 3, createDate(2009, Calendar.NOVEMBER, 18), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(otherDisease, 1, 2, createDate(2009, Calendar.NOVEMBER, 25), patient, KABWE_MINE_HOSPITAL);
    this.createIndividualCases(otherDisease, 1, 1, createDate(2009, Calendar.DECEMBER, 2), patient, KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(otherDisease, 3, createDate(2009, Calendar.NOVEMBER, 15), createDate(2009, Calendar.NOVEMBER, 21), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(otherDisease, 2, createDate(2009, Calendar.NOVEMBER, 22), createDate(2009, Calendar.NOVEMBER, 28), KABWE_MINE_HOSPITAL);
    this.createAggregatedCases(otherDisease, 1, createDate(2009, Calendar.NOVEMBER, 29), createDate(2009, Calendar.DECEMBER, 5), KABWE_MINE_HOSPITAL);

    PopulationData politicalPopulationData = new PopulationData();
    politicalPopulationData.setGeoEntity(DJIBOUTI);
    politicalPopulationData.setYearOfData(2009);
    politicalPopulationData.setGrowthRate(0D);
    politicalPopulationData.setPopulation(182981L);
    politicalPopulationData.apply();

    PopulationData facilityPopulationData = new PopulationData();
    facilityPopulationData.setGeoEntity(KABWE_MINE_HOSPITAL);
    facilityPopulationData.setYearOfData(2009);
    facilityPopulationData.setGrowthRate(0D);
    facilityPopulationData.setPopulation(182981L);
    facilityPopulationData.apply();

    output("Done!");
  }

  @Transaction
  private void createData2()
  {
    output("Creating Data...");
    this.clearData();

    MalariaSeason malariaSeason = new MalariaSeason();
    malariaSeason.setSeasonName("Threshold Calculation Test Season");
    malariaSeason.setStartDate(createDate(2010, Calendar.JANUARY, 1));
    malariaSeason.setEndDate(createDate(2010, Calendar.FEBRUARY, 28));
    malariaSeason.apply();

    Person person = new Person();
    person.setFirstName("Chris");
    person.setLastName("Reigrut");
    person.setSex(Term.getByTermId("MDSS:0000354"));
    person.setDateOfBirth(createDate(1968, Calendar.OCTOBER, 25));
    person.apply();

    Patient patient = new Patient();
    patient.setPerson(person);
    patient.apply();

    this.createIndividualCases(2, 1, createDate(2005, Calendar.JANUARY, 26), patient, DJIBOUTI);
    this.createIndividualCases(4, 1, createDate(2005, Calendar.FEBRUARY, 2), patient, DJIBOUTI);
    this.createIndividualCases(7, 1, createDate(2005, Calendar.FEBRUARY, 9), patient, DJIBOUTI);
    this.createIndividualCases(2, 1, createDate(2005, Calendar.FEBRUARY, 16), patient, DJIBOUTI);
    this.createIndividualCases(3, 1, createDate(2005, Calendar.FEBRUARY, 23), patient, DJIBOUTI);
    this.createIndividualCases(8, 1, createDate(2006, Calendar.FEBRUARY, 1), patient, DJIBOUTI);
    this.createIndividualCases(8, 1, createDate(2006, Calendar.FEBRUARY, 8), patient, DJIBOUTI);
    this.createIndividualCases(6, 1, createDate(2006, Calendar.FEBRUARY, 15), patient, DJIBOUTI);
    this.createIndividualCases(15, 1, createDate(2007, Calendar.JANUARY, 31), patient, DJIBOUTI);
    this.createIndividualCases(9, 1, createDate(2007, Calendar.FEBRUARY, 7), patient, DJIBOUTI);
    this.createIndividualCases(8, 1, createDate(2008, Calendar.JANUARY, 30), patient, DJIBOUTI);
    this.createIndividualCases(7, 1, createDate(2008, Calendar.FEBRUARY, 6), patient, DJIBOUTI);
    this.createIndividualCases(6, 1, createDate(2008, Calendar.FEBRUARY, 13), patient, DJIBOUTI);
    this.createIndividualCases(5, 1, createDate(2009, Calendar.JANUARY, 28), patient, DJIBOUTI);
    this.createIndividualCases(4, 1, createDate(2009, Calendar.FEBRUARY, 4), patient, DJIBOUTI);
    this.createIndividualCases(3, 1, createDate(2009, Calendar.FEBRUARY, 11), patient, DJIBOUTI);
    this.createIndividualCases(1, 1, createDate(2010, Calendar.JANUARY, 27), patient, DJIBOUTI);

    PopulationData politicalPopulationData = new PopulationData();
    politicalPopulationData.setGeoEntity(DJIBOUTI);
    politicalPopulationData.setYearOfData(2009);
    politicalPopulationData.setGrowthRate(0D);
    politicalPopulationData.setPopulation(182981L);
    politicalPopulationData.apply();

    output("Done!");
  }

  @Transaction
  private void createData3()
  {
    output("Creating Data...");
    this.clearData();

    MalariaSeason malariaSeason = new MalariaSeason();
    malariaSeason.setSeasonName("Season 2009");
    malariaSeason.setStartDate(createDate(2009, Calendar.OCTOBER, 1));
    malariaSeason.setEndDate(createDate(2010, Calendar.MAY, 31));
    malariaSeason.apply();

    Person person = new Person();
    person.setFirstName("Chris");
    person.setLastName("Reigrut");
    person.setSex(Term.getByTermId("MDSS:0000354"));
    person.setDateOfBirth(createDate(1968, Calendar.OCTOBER, 25));
    person.apply();

    Patient patient = new Patient();
    patient.setPerson(person);
    patient.apply();

    this.createAggregatedCases(12, createDate(2006, Calendar.FEBRUARY, 26), createDate(2006, Calendar.MARCH, 4), MBOSHYA);
    this.createAggregatedCases(450, createDate(2006, Calendar.FEBRUARY, 26), createDate(2006, Calendar.MARCH, 4), CHIBOMBO);
    this.createAggregatedCases(12, createDate(2006, Calendar.FEBRUARY, 26), createDate(2006, Calendar.MARCH, 4), MUNGULE);
    this.createAggregatedCases(13, createDate(2006, Calendar.MARCH, 5), createDate(2006, Calendar.MARCH, 11), MBOSHYA);
    this.createAggregatedCases(500, createDate(2006, Calendar.MARCH, 5), createDate(2006, Calendar.MARCH, 11), CHIBOMBO);
    this.createAggregatedCases(13, createDate(2006, Calendar.MARCH, 5), createDate(2006, Calendar.MARCH, 11), MUNGULE);
    this.createAggregatedCases(20, createDate(2006, Calendar.MARCH, 12), createDate(2006, Calendar.MARCH, 18), MBOSHYA);
    this.createAggregatedCases(720, createDate(2006, Calendar.MARCH, 12), createDate(2006, Calendar.MARCH, 18), CHIBOMBO);
    this.createAggregatedCases(20, createDate(2006, Calendar.MARCH, 12), createDate(2006, Calendar.MARCH, 18), MUNGULE);
    this.createAggregatedCases(19, createDate(2006, Calendar.MARCH, 19), createDate(2006, Calendar.MARCH, 25), MBOSHYA);
    this.createAggregatedCases(690, createDate(2006, Calendar.MARCH, 19), createDate(2006, Calendar.MARCH, 25), CHIBOMBO);
    this.createAggregatedCases(19, createDate(2006, Calendar.MARCH, 19), createDate(2006, Calendar.MARCH, 25), MUNGULE);
    this.createAggregatedCases(5, createDate(2006, Calendar.MARCH, 26), createDate(2006, Calendar.APRIL, 1), MBOSHYA);
    this.createAggregatedCases(200, createDate(2006, Calendar.MARCH, 26), createDate(2006, Calendar.APRIL, 1), CHIBOMBO);
    this.createAggregatedCases(5, createDate(2006, Calendar.MARCH, 26), createDate(2006, Calendar.APRIL, 1), MUNGULE);

    this.createAggregatedCases(13, createDate(2007, Calendar.FEBRUARY, 25), createDate(2007, Calendar.MARCH, 3), MBOSHYA);
    this.createAggregatedCases(500, createDate(2007, Calendar.FEBRUARY, 25), createDate(2007, Calendar.MARCH, 3), CHIBOMBO);
    this.createAggregatedCases(13, createDate(2007, Calendar.FEBRUARY, 25), createDate(2007, Calendar.MARCH, 3), MUNGULE);
    this.createAggregatedCases(14, createDate(2007, Calendar.MARCH, 4), createDate(2007, Calendar.MARCH, 10), MBOSHYA);
    this.createAggregatedCases(550, createDate(2007, Calendar.MARCH, 4), createDate(2007, Calendar.MARCH, 10), CHIBOMBO);
    this.createAggregatedCases(14, createDate(2007, Calendar.MARCH, 4), createDate(2007, Calendar.MARCH, 10), MUNGULE);
    this.createAggregatedCases(21, createDate(2007, Calendar.MARCH, 11), createDate(2007, Calendar.MARCH, 17), MBOSHYA);
    this.createAggregatedCases(760, createDate(2007, Calendar.MARCH, 11), createDate(2007, Calendar.MARCH, 17), CHIBOMBO);
    this.createAggregatedCases(21, createDate(2007, Calendar.MARCH, 11), createDate(2007, Calendar.MARCH, 17), MUNGULE);
    this.createAggregatedCases(20, createDate(2007, Calendar.MARCH, 18), createDate(2007, Calendar.MARCH, 24), MBOSHYA);
    this.createAggregatedCases(710, createDate(2007, Calendar.MARCH, 18), createDate(2007, Calendar.MARCH, 24), CHIBOMBO);
    this.createAggregatedCases(20, createDate(2007, Calendar.MARCH, 18), createDate(2007, Calendar.MARCH, 24), MUNGULE);
    this.createAggregatedCases(6, createDate(2007, Calendar.MARCH, 25), createDate(2007, Calendar.MARCH, 31), MBOSHYA);
    this.createAggregatedCases(230, createDate(2007, Calendar.MARCH, 25), createDate(2007, Calendar.MARCH, 31), CHIBOMBO);
    this.createAggregatedCases(6, createDate(2007, Calendar.MARCH, 25), createDate(2007, Calendar.MARCH, 31), MUNGULE);

    this.createAggregatedCases(11, createDate(2008, Calendar.FEBRUARY, 24), createDate(2008, Calendar.MARCH, 1), MBOSHYA);
    this.createAggregatedCases(390, createDate(2008, Calendar.FEBRUARY, 24), createDate(2008, Calendar.MARCH, 1), CHIBOMBO);
    this.createAggregatedCases(11, createDate(2008, Calendar.FEBRUARY, 24), createDate(2008, Calendar.MARCH, 1), MUNGULE);
    this.createAggregatedCases(12, createDate(2008, Calendar.MARCH, 2), createDate(2008, Calendar.MARCH, 8), MBOSHYA);
    this.createAggregatedCases(460, createDate(2008, Calendar.MARCH, 2), createDate(2008, Calendar.MARCH, 8), CHIBOMBO);
    this.createAggregatedCases(12, createDate(2008, Calendar.MARCH, 2), createDate(2008, Calendar.MARCH, 8), MUNGULE);
    this.createAggregatedCases(19, createDate(2008, Calendar.MARCH, 9), createDate(2008, Calendar.MARCH, 15), MBOSHYA);
    this.createAggregatedCases(670, createDate(2008, Calendar.MARCH, 9), createDate(2008, Calendar.MARCH, 15), CHIBOMBO);
    this.createAggregatedCases(19, createDate(2008, Calendar.MARCH, 9), createDate(2008, Calendar.MARCH, 15), MUNGULE);
    this.createAggregatedCases(18, createDate(2008, Calendar.MARCH, 16), createDate(2008, Calendar.MARCH, 22), MBOSHYA);
    this.createAggregatedCases(625, createDate(2008, Calendar.MARCH, 16), createDate(2008, Calendar.MARCH, 22), CHIBOMBO);
    this.createAggregatedCases(18, createDate(2008, Calendar.MARCH, 16), createDate(2008, Calendar.MARCH, 22), MUNGULE);
    this.createAggregatedCases(4, createDate(2008, Calendar.MARCH, 23), createDate(2008, Calendar.MARCH, 29), MBOSHYA);
    this.createAggregatedCases(150, createDate(2008, Calendar.MARCH, 23), createDate(2008, Calendar.MARCH, 29), CHIBOMBO);
    this.createAggregatedCases(4, createDate(2008, Calendar.MARCH, 23), createDate(2008, Calendar.MARCH, 29), MUNGULE);

    this.createAggregatedCases(24, createDate(2009, Calendar.MARCH, 1), createDate(2009, Calendar.MARCH, 7), MBOSHYA);
    this.createAggregatedCases(1300, createDate(2009, Calendar.MARCH, 1), createDate(2009, Calendar.MARCH, 7), CHIBOMBO);
    this.createAggregatedCases(24, createDate(2009, Calendar.MARCH, 1), createDate(2009, Calendar.MARCH, 7), MUNGULE);
    this.createAggregatedCases(21, createDate(2009, Calendar.MARCH, 8), createDate(2009, Calendar.MARCH, 14), MBOSHYA);
    this.createAggregatedCases(1600, createDate(2009, Calendar.MARCH, 8), createDate(2009, Calendar.MARCH, 14), CHIBOMBO);
    this.createAggregatedCases(21, createDate(2009, Calendar.MARCH, 8), createDate(2009, Calendar.MARCH, 14), MUNGULE);
    this.createAggregatedCases(45, createDate(2009, Calendar.MARCH, 15), createDate(2009, Calendar.MARCH, 21), MBOSHYA);
    this.createAggregatedCases(200, createDate(2009, Calendar.MARCH, 15), createDate(2009, Calendar.MARCH, 21), CHIBOMBO);
    this.createAggregatedCases(45, createDate(2009, Calendar.MARCH, 15), createDate(2009, Calendar.MARCH, 21), MUNGULE);
    this.createAggregatedCases(60, createDate(2009, Calendar.MARCH, 22), createDate(2009, Calendar.MARCH, 28), MBOSHYA);
    this.createAggregatedCases(2300, createDate(2009, Calendar.MARCH, 22), createDate(2009, Calendar.MARCH, 28), CHIBOMBO);
    this.createAggregatedCases(60, createDate(2009, Calendar.MARCH, 22), createDate(2009, Calendar.MARCH, 28), MUNGULE);
    this.createAggregatedCases(18, createDate(2009, Calendar.MARCH, 29), createDate(2009, Calendar.APRIL, 4), MBOSHYA);
    this.createAggregatedCases(550, createDate(2009, Calendar.MARCH, 29), createDate(2009, Calendar.APRIL, 4), CHIBOMBO);
    this.createAggregatedCases(18, createDate(2009, Calendar.MARCH, 29), createDate(2009, Calendar.APRIL, 4), MUNGULE);

    PopulationData politicalPopulationData1 = new PopulationData();
    politicalPopulationData1.setGeoEntity(MBOSHYA);
    politicalPopulationData1.setYearOfData(2009);
    politicalPopulationData1.setGrowthRate(0D);
    politicalPopulationData1.setPopulation(182981L);
    politicalPopulationData1.apply();

    PopulationData politicalPopulationData2 = new PopulationData();
    politicalPopulationData2.setGeoEntity(MUNGULE);
    politicalPopulationData2.setYearOfData(2009);
    politicalPopulationData2.setGrowthRate(0D);
    politicalPopulationData2.setPopulation(150000L);
    politicalPopulationData2.apply();

    output("Done!");
  }

  @Transaction
  private void createData4()
  {
    output("Creating Data...");
    this.clearData();

    Disease currentDisease = Disease.getCurrent();
    System.out.println("Current Disease: " + currentDisease);
    Disease otherDisease = null;
    if (currentDisease.equals(Disease.getMalaria()))
    {
      otherDisease = Disease.getDengue();
    }
    else
    {
      otherDisease = Disease.getMalaria();
    }

    MalariaSeason malariaSeason = new MalariaSeason();
    malariaSeason.setSeasonName("Threshold Calculation Test Season");
    malariaSeason.setStartDate(createDate(2010, Calendar.NOVEMBER, 1));
    malariaSeason.setEndDate(createDate(2010, Calendar.DECEMBER, 31));
    malariaSeason.apply();

    Person person = new Person();
    person.setFirstName("Chris");
    person.setLastName("Reigrut");
    person.setSex(Term.getByTermId("MDSS:0000354"));
    person.setDateOfBirth(createDate(1968, Calendar.OCTOBER, 25));
    person.apply();

    Patient patient = new Patient();
    patient.setPerson(person);
    patient.apply();

    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 0, 0, createDate(2005, Calendar.NOVEMBER, 16), patient, DJIBOUTI); // can't
    // really
    // exist
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 0, 1, createDate(2005, Calendar.NOVEMBER, 23), patient, DJIBOUTI); // NEGATIVE
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 1, 0, createDate(2005, Calendar.NOVEMBER, 30), patient, DJIBOUTI); // POSITIVE
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 1, 1, createDate(2006, Calendar.NOVEMBER, 15), patient, DJIBOUTI); // POSITIVE
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 1, 0, 0, createDate(2006, Calendar.NOVEMBER, 22), patient, DJIBOUTI); // CLINICAL
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 1, 0, 1, createDate(2006, Calendar.NOVEMBER, 29), patient, DJIBOUTI); // NEGATIVE
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 1, 1, 0, createDate(2007, Calendar.NOVEMBER, 14), patient, DJIBOUTI); // POSITIVE
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 1, 1, 1, createDate(2007, Calendar.NOVEMBER, 21), patient, DJIBOUTI); // POSITIVE
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 0, 2, createDate(2007, Calendar.NOVEMBER, 28), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 2, 0, createDate(2008, Calendar.NOVEMBER, 12), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 0, 2, 2, createDate(2008, Calendar.NOVEMBER, 19), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 2, 0, 0, createDate(2008, Calendar.NOVEMBER, 26), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 2, 0, 2, createDate(2009, Calendar.NOVEMBER, 18), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 2, 2, 0, createDate(2009, Calendar.NOVEMBER, 25), patient, DJIBOUTI);
    this.createIndividualCases(currentDisease, 1, 0, 0, 0, 2, 2, 2, createDate(2009, Calendar.DECEMBER, 2), patient, DJIBOUTI);

    output("Done!");
  }

  /******************************************************************************/
  /* OLD TESTS START HERE */
  /******************************************************************************/
  @Transaction
  public void xtestCalculateBenPoliticalIndividualMeanThresholds()
  {
    output("Calculating Ben's Political Individual Mean Thresholds...");
    double[] weights = { 1.0d, 1.0d, 1.0d, 1.0d, 1.0d };

    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD, 1, 1, 5, weights);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, true);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 5, 2010, td, 9, 11);
    assertThresholds(true, 6, 2010, td, 6, 6);
    assertThresholds(true, 7, 2010, td, 3, 3);
    assertThresholds(true, 8, 2010, td, 1, 1);
    output("Done!");
  }

  @Transaction
  public void ztestCalculatePoliticalAggregatedMeanThresholds()
  {
    output("Calculating Political Aggregated Mean Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType224(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = CHIBOMBO.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, true);

    ThresholdData td = ThresholdData.getThresholdData(CHIBOMBO, season);
    try
    {
      assertThresholds(true, 14, 2010, td, 118, 138);
    }
    catch (Throwable e)
    {
      System.out.println(e);
    }
    output("Done!");
  }

  @Transaction
  public void ztestCalculatePoliticalAggregatedQuartileThresholds()
  {
    output("Calculating Political Aggregated Quartile Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = MBOSHYA.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(MBOSHYA, season);
    try
    {
      assertThresholds(true, 46, 2010, td, 2, 2);
    }
    catch (Throwable e)
    {
      System.out.println(e);
    }
    output("Done!");
  }

  @Transaction
  public void ztestCalculatePoliticalAggregatedBinomialThresholds()
  {
    output("Calculating Political Aggregated Binomial Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType212(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = MBOSHYA.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, true);

    ThresholdData td = ThresholdData.getThresholdData(MBOSHYA, season);
    try
    {
      assertThresholds(true, 11, 2010, td, 31, 36);
    }
    catch (Throwable e)
    {
      System.out.println(e);
    }
    output("Done!");
  }

  @Transaction
  public void xtestLeavingDataForManualTesting()
  {
    // This is an empty test to leave the data inside the system for
    // validation
    // And to allow manual testing
  }

  /******************************************************************************/
  /* TESTS START HERE */
  /******************************************************************************/

  @Request(RequestType.SESSION)
  public void testCreateNewInstanceData(String sessionId)
  {
    this.createData4();
    try
    {
      Date initialDate = createDate(2005, Calendar.NOVEMBER, 14);
      Date finalDate = createDate(2009, Calendar.DECEMBER, 2);

      QueryFactory factory = new QueryFactory();

      GeoEntityQuery entityQuery = new GeoEntityQuery(factory);
      entityQuery.WHERE(entityQuery.getGeoId().EQ(DJIBOUTI.getGeoId()));

      IndividualInstanceQuery iQuery = new IndividualInstanceQuery(factory);

      ValueQuery innerQuery = new ValueQuery(factory);
      SUM positiveColumn = F.SUM(innerQuery.aSQLLong("positive", "(case when " + iQuery.getDiagnosisType().getDbColumnName() + "_c = '" + DiagnosisType.POSITIVE_DIAGNOSIS.getId() + "' then 1 else 0 end)"), "positive");
      SUM negativeColumn = F.SUM(innerQuery.aSQLLong("negative", "(case when " + iQuery.getDiagnosisType().getDbColumnName() + "_c = '" + DiagnosisType.NEGATIVE_DIAGNOSIS.getId() + "' then 1 else 0 end)"), "negative");
      SUM clinicalColumn = F.SUM(innerQuery.aSQLLong("clinical", "(case when " + iQuery.getDiagnosisType().getDbColumnName() + "_c = '" + DiagnosisType.CLINICAL_DIAGNOSIS.getId() + "' then 1 else 0 end)"), "clinical");
      innerQuery.SELECT(iQuery.getIndividualCase());
      innerQuery.SELECT(positiveColumn);
      innerQuery.SELECT(negativeColumn);
      innerQuery.SELECT(clinicalColumn);
      innerQuery.WHERE(iQuery.getIndividualCase().getDisease().EQ(Disease.getCurrent()));
      innerQuery.AND(iQuery.getIndividualCase().getProbableSource().EQ(entityQuery));
      innerQuery.AND(iQuery.getIndividualCase().getDiagnosisDate().GE(initialDate));
      innerQuery.AND(iQuery.getIndividualCase().getDiagnosisDate().LE(finalDate));

      // innerQuery.AND(iQuery.getActivelyDetected().EQ(false));

      ValueQuery vQuery = new ValueQuery(factory);
      vQuery.SELECT(F.SUM(vQuery.aSQLLong("positive", "(case when " + positiveColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), "positiveCases"));
      vQuery.SELECT(F.SUM(vQuery.aSQLLong("negative", "(case when " + positiveColumn.getColumnAlias() + " = 0 and " + negativeColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), "negativeCases"));
      vQuery.SELECT(F.SUM(vQuery.aSQLLong("clinical", "(case when " + positiveColumn.getColumnAlias() + " = 0 and " + negativeColumn.getColumnAlias() + " = 0 and " + clinicalColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), "clinicalCases"));
      vQuery.FROM("(" + innerQuery.getSQL() + ")", "innerQuery");
      // System.out.println(vQuery.getSQL());

      long sumClinicalCases = 0l;
      long sumPositiveCases = 0l;
      long sumNegativeCases = 0l;

      for (ValueObject valueObject : vQuery.getIterator())
      {
        sumClinicalCases += this.getValue(valueObject, "clinicalCases");
        sumPositiveCases += this.getValue(valueObject, "positiveCases");
        sumNegativeCases += this.getValue(valueObject, "negativeCases");
        // System.out.println(sumPositiveCases + "\t" + sumNegativeCases + "\t"
        // + sumClinicalCases);
      }

      assertEquals(8, sumPositiveCases);
      assertEquals(4, sumNegativeCases);
      assertEquals(2, sumClinicalCases);

    }
    catch (Throwable t)
    {
      t.printStackTrace(System.out);
    }
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCreateData(String sessionId)
  {

    System.out.println(Disease.getCurrent());
    if (CREATE_DATA_ONCE)
    {
      this.createData();
    }
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalIndividualMeanThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Individual Mean Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 4, 4);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalIndividualQuartileThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Individual Quartile Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 2, 2);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalIndividualBinomialThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Individual Binomial Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 6, 8);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalAggregatedMeanThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Aggregated Mean Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 4, 4);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalAggregatedQuartileThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Aggregated Quartile Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 2, 2);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalAggregatedBinomialThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Aggregated Binomial Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 6, 8);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityIndividualMeanThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Individual Mean Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 4, 4);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityIndividualQuartileThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Individual Quartile Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 2, 2);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityIndividualBinomialThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Individual Binomial Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 6, 8);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityAggregatedMeanThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Aggregated Mean Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 4, 4);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityAggregatedQuartileThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Aggregated Quartile Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 2, 2);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityAggregatedBinomialThresholds(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Aggregated Binomial Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 6, 8);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculatePoliticalIndividualMeanThresholdsWithMinimums(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Political Individual Mean Thresholds With Minimums...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    calcType.lock();
    calcType.setNotificationMinimum(100.01d);
    calcType.setIdentificationMinimum(200.02d);
    calcType.apply();
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
    assertThresholds(true, 46, 2010, td, 100, 200);
    output("Done!");
  }

  @Request(RequestType.SESSION)
  public void testCalculateFacilityIndividualMeanThresholdsWithMinumums(String sessionId)
  {
    if (!CREATE_DATA_ONCE)
    {
      this.createData();
    }
    output("Calculating Facility Individual Mean Thresholds...");
    ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
    calcType.lock();
    calcType.setNotificationMinimum(100.01d);
    calcType.setIdentificationMinimum(200.02d);
    calcType.apply();
    if (!CALCULATE_ALL_THRESHOLDS)
    {
      ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
    }
    MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);

    ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
    assertThresholds(false, 46, 2010, td, 100, 200);
    output("Done!");
  }

  protected long getValue(ValueObject valueObject, String key)
  {
    long value = 0;
    String valueString = valueObject.getValue(key);
    if (valueString.length() > 0)
    {
      value = Long.parseLong(valueString);
    }
    return value;
  }
}
