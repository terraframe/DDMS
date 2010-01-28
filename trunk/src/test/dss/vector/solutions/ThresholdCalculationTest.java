package dss.vector.solutions;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.session.StartSession;
import com.terraframe.mojo.system.metadata.MdEntity;

import dss.vector.solutions.general.EpiDate;
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
import dss.vector.solutions.intervention.monitor.IndividualCase;
import dss.vector.solutions.intervention.monitor.IndividualCaseQuery;
import dss.vector.solutions.intervention.monitor.IndividualInstance;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.surveillance.AggregatedAgeGroup;
import dss.vector.solutions.surveillance.AggregatedCase;
import dss.vector.solutions.threshold.FacilityThresholdCalculator;
import dss.vector.solutions.threshold.PoliticalThresholdCalculator;
import dss.vector.solutions.threshold.ThresholdCalculator;

public class ThresholdCalculationTest extends TestCase {
	private static GeoEntity EGYPT = GeoEntity.searchByGeoId("22220002");
	private static GeoEntity DJIBOUTI = GeoEntity.searchByGeoId("22220033");
	private static GeoEntity KABWE_MINE_HOSPITAL = GeoEntity.searchByGeoId("2020010");
	private static final boolean CALCULATE_ALL_THRESHOLDS = false;
	
	MalariaSeason malariaSeason = null;
	Patient patient = null;
	
	@StartSession
	public void setUp() throws Exception {
		this.createData();
	}

	public void tearDown() throws Exception {
	}

	public void xtestDataCreation() {
		assertNotNull(patient);
		assertEquals(496, this.getCaseCount(EGYPT, createDate(2009, Calendar.JANUARY, 1), createDate(2009, Calendar.DECEMBER, 31)));
		assertEquals(496, this.getCaseCount(EGYPT, createDate(2009, Calendar.AUGUST, 1), createDate(2009, Calendar.AUGUST, 31)));
		assertEquals(9, this.getCaseCount(EGYPT, createDate(2009, Calendar.AUGUST, 2), createDate(2009, Calendar.AUGUST, 4)));
	}

	public void xtestMalariaSeasons() {
		EpiDate[] epiWeeks = malariaSeason.getEpiWeeks();
		for (int i = 0; i < epiWeeks.length; i++) {
			System.out.println("" + epiWeeks[i].getPeriod() + ":" + epiWeeks[i].getEpiYear() + " (" + epiWeeks[i].getStartDate() + " -> " + epiWeeks[i].getEndDate() + ") = " + this.getCaseCount(EGYPT, epiWeeks[i].getStartDate(), epiWeeks[i].getEndDate()));
		}
	}

	@Transaction
	private long getCaseCount(GeoEntity geoEntity, Date startDate, Date endDate) {
		QueryFactory factory = new QueryFactory();

		GeoEntityQuery entityQuery = geoEntity.getPoliticalDecendants(factory);

		IndividualCaseQuery query = new IndividualCaseQuery(factory);

		Condition condition = query.getProbableSource().EQ(entityQuery);
		condition = AND.get(condition, query.getDiagnosisDate().GE(startDate));
		condition = AND.get(condition, query.getDiagnosisDate().LE(endDate));

		query.WHERE(condition);

		return query.getCount();
	}

	@Transaction
	private void createData() {
		output("Creating Data...");
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

		malariaSeason = new MalariaSeason();
		malariaSeason.setSeasonName("Threshold Calculation Test Season");
		malariaSeason.setStartDate(createDate(2010, Calendar.MARCH, 1));
		malariaSeason.setEndDate(createDate(2010, Calendar.APRIL, 30));
		malariaSeason.apply();

		Person person = new Person();
		person.setFirstName("Chris");
		person.setLastName("Reigrut");
		person.setSex(Term.getByTermId("MDSS:0000354"));
		person.setDateOfBirth(createDate(1968, Calendar.OCTOBER, 25));
		person.apply();

		patient = new Patient();
		patient.setPerson(person);
		patient.apply();

		for (int day = 1; day <= 31; day++) {
			this.createIndividualCases(day, 1, createDate(2009, Calendar.AUGUST, day), patient, EGYPT);
		}

		this.createIndividualCases(9, 1, createDate(2005, Calendar.MARCH, 16), patient, DJIBOUTI);
		this.createIndividualCases(9, 1, createDate(2005, Calendar.MARCH, 23), patient, DJIBOUTI);
		this.createIndividualCases(9, 1, createDate(2005, Calendar.MARCH, 30), patient, DJIBOUTI);
		this.createIndividualCases(9, 1, createDate(2006, Calendar.MARCH, 15), patient, DJIBOUTI);
		this.createIndividualCases(9, 1, createDate(2006, Calendar.MARCH, 22), patient, DJIBOUTI);
		this.createIndividualCases(9, 1, createDate(2006, Calendar.MARCH, 29), patient, DJIBOUTI);
		this.createIndividualCases(2, 1, createDate(2007, Calendar.MARCH, 14), patient, DJIBOUTI);
		this.createIndividualCases(4, 1, createDate(2007, Calendar.MARCH, 21), patient, DJIBOUTI);
		this.createIndividualCases(4, 1, createDate(2007, Calendar.MARCH, 28), patient, DJIBOUTI);
		this.createIndividualCases(1, 1, createDate(2008, Calendar.MARCH, 12), patient, DJIBOUTI);
		this.createIndividualCases(1, 1, createDate(2008, Calendar.MARCH, 19), patient, DJIBOUTI);
		this.createIndividualCases(3, 1, createDate(2008, Calendar.MARCH, 26), patient, DJIBOUTI);
		this.createIndividualCases(3, 1, createDate(2009, Calendar.MARCH, 18), patient, DJIBOUTI);
		this.createIndividualCases(2, 1, createDate(2009, Calendar.MARCH, 25), patient, DJIBOUTI);
		this.createIndividualCases(1, 1, createDate(2009, Calendar.APRIL, 1), patient, DJIBOUTI);
		
		this.createAggregatedCases(9, createDate(2005, Calendar.MARCH, 13), createDate(2005, Calendar.MARCH, 19), DJIBOUTI);
		this.createAggregatedCases(9, createDate(2005, Calendar.MARCH, 20), createDate(2005, Calendar.MARCH, 26), DJIBOUTI);
		this.createAggregatedCases(9, createDate(2005, Calendar.MARCH, 27), createDate(2005, Calendar.APRIL, 2), DJIBOUTI);
		this.createAggregatedCases(9, createDate(2006, Calendar.MARCH, 12), createDate(2006, Calendar.MARCH, 18), DJIBOUTI);
		this.createAggregatedCases(9, createDate(2006, Calendar.MARCH, 19), createDate(2006, Calendar.MARCH, 25), DJIBOUTI);
		this.createAggregatedCases(9, createDate(2006, Calendar.MARCH, 26), createDate(2006, Calendar.APRIL, 1), DJIBOUTI);
		this.createAggregatedCases(2, createDate(2007, Calendar.MARCH, 11), createDate(2007, Calendar.MARCH, 17), DJIBOUTI);
		this.createAggregatedCases(4, createDate(2007, Calendar.MARCH, 18), createDate(2007, Calendar.MARCH, 24), DJIBOUTI);
		this.createAggregatedCases(4, createDate(2007, Calendar.MARCH, 25), createDate(2007, Calendar.MARCH, 31), DJIBOUTI);
		this.createAggregatedCases(1, createDate(2008, Calendar.MARCH, 9), createDate(2008, Calendar.MARCH, 15), DJIBOUTI);
		this.createAggregatedCases(1, createDate(2008, Calendar.MARCH, 16), createDate(2008, Calendar.MARCH, 22), DJIBOUTI);
		this.createAggregatedCases(3, createDate(2008, Calendar.MARCH, 23), createDate(2008, Calendar.MARCH, 29), DJIBOUTI);
		this.createAggregatedCases(3, createDate(2009, Calendar.MARCH, 15), createDate(2009, Calendar.MARCH, 21), DJIBOUTI);
		this.createAggregatedCases(2, createDate(2009, Calendar.MARCH, 22), createDate(2009, Calendar.MARCH, 28), DJIBOUTI);
		this.createAggregatedCases(1, createDate(2009, Calendar.MARCH, 29), createDate(2009, Calendar.APRIL, 4), DJIBOUTI);
		
		// This is a period less than a week -- should not be counted 
		this.createAggregatedCases(100, createDate(2008, Calendar.MARCH, 10), createDate(2008, Calendar.MARCH, 14), DJIBOUTI);
		
		this.createIndividualCases(1, 9, createDate(2005, Calendar.MARCH, 16), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 9, createDate(2005, Calendar.MARCH, 23), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 9, createDate(2005, Calendar.MARCH, 30), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 9, createDate(2006, Calendar.MARCH, 15), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 9, createDate(2006, Calendar.MARCH, 22), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 9, createDate(2006, Calendar.MARCH, 29), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 2, createDate(2007, Calendar.MARCH, 14), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 4, createDate(2007, Calendar.MARCH, 21), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 4, createDate(2007, Calendar.MARCH, 28), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 1, createDate(2008, Calendar.MARCH, 12), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 1, createDate(2008, Calendar.MARCH, 19), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 3, createDate(2008, Calendar.MARCH, 26), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 3, createDate(2009, Calendar.MARCH, 18), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 2, createDate(2009, Calendar.MARCH, 25), patient, KABWE_MINE_HOSPITAL);
		this.createIndividualCases(1, 1, createDate(2009, Calendar.APRIL, 1), patient, KABWE_MINE_HOSPITAL);
		
		this.createAggregatedCases(9, createDate(2005, Calendar.MARCH, 13), createDate(2005, Calendar.MARCH, 19), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(9, createDate(2005, Calendar.MARCH, 20), createDate(2005, Calendar.MARCH, 26), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(9, createDate(2005, Calendar.MARCH, 27), createDate(2005, Calendar.APRIL, 2), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(9, createDate(2006, Calendar.MARCH, 12), createDate(2006, Calendar.MARCH, 18), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(9, createDate(2006, Calendar.MARCH, 19), createDate(2006, Calendar.MARCH, 25), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(9, createDate(2006, Calendar.MARCH, 26), createDate(2006, Calendar.APRIL, 1), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(2, createDate(2007, Calendar.MARCH, 11), createDate(2007, Calendar.MARCH, 17), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(4, createDate(2007, Calendar.MARCH, 18), createDate(2007, Calendar.MARCH, 24), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(4, createDate(2007, Calendar.MARCH, 25), createDate(2007, Calendar.MARCH, 31), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(1, createDate(2008, Calendar.MARCH, 9), createDate(2008, Calendar.MARCH, 15), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(1, createDate(2008, Calendar.MARCH, 16), createDate(2008, Calendar.MARCH, 22), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(3, createDate(2008, Calendar.MARCH, 23), createDate(2008, Calendar.MARCH, 29), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(3, createDate(2009, Calendar.MARCH, 15), createDate(2009, Calendar.MARCH, 21), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(2, createDate(2009, Calendar.MARCH, 22), createDate(2009, Calendar.MARCH, 28), KABWE_MINE_HOSPITAL);
		this.createAggregatedCases(1, createDate(2009, Calendar.MARCH, 29), createDate(2009, Calendar.APRIL, 4), KABWE_MINE_HOSPITAL);
		
		// This is a period less than a week -- should not be counted 
		this.createAggregatedCases(100, createDate(2008, Calendar.MARCH, 10), createDate(2008, Calendar.MARCH, 14), KABWE_MINE_HOSPITAL);
		
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
/* These are old tests for methods that are no longer public
 
	@Transaction
	public void xtestWeightedMean() {
		// Weeks are 0 based, so we have to look at week 12 - 1
		assertEquals(2.10d, thresholdCalculator.calculateWeightedMean(thresholdCalculator.calculateWeightedSeasonalMeans(DJIBOUTI, 11, 2010)), 0.01d);
	}

	@Transaction 
	public void xtestMean15SD() {
		// Weeks are 0 based, so we have to look at week 12 - 1
		assertEquals(3.825d, thresholdCalculator.calculateMeanSD(DJIBOUTI, 11, 2010, 1.5d), 0.005d);
		assertEquals(4l, thresholdCalculator.calculate(ThresholdCalculationMethod.MEAN_PLUS_15_SD, DJIBOUTI, 11, 2010));
	}
	
	@Transaction 
	public void xtestMean20SD() {
		// Weeks are 0 based, so we have to look at week 12 - 1
		assertEquals(4.40d, thresholdCalculator.calculateMeanSD(DJIBOUTI, 11, 2010, 2.0d), 0.01d);
		assertEquals(4l, thresholdCalculator.calculate(ThresholdCalculationMethod.MEAN_PLUS_20_SD, DJIBOUTI, 11, 2010));
	}	
	
	@Transaction 
	public void xtestUpperThirdQuartile() {
		// Weeks are 0 based, so we have to look at week 12 - 1
		assertEquals(2.43d, thresholdCalculator.calculateQuartile(DJIBOUTI, 11, 2010, 3), 0.01d);
		assertEquals(2l, thresholdCalculator.calculate(ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, DJIBOUTI, 11, 2010));
	}
	
	@Transaction 
	public void xtestBinomial95() {
		// Weeks are 0 based, so we have to look at week 12 - 1
		assertEquals(6.37d, thresholdCalculator.calculateBinomial(DJIBOUTI, 11, 2010, 0.95d), 0.01d);
		assertEquals(6l, thresholdCalculator.calculate(ThresholdCalculationMethod.BINOMIAL_95, DJIBOUTI, 11, 2010));
	}	
	
	@Transaction 
	public void xtestBinomial99() {
		// Weeks are 0 based, so we have to look at week 12 - 1
		assertEquals(8.46d, thresholdCalculator.calculateBinomial(DJIBOUTI, 11, 2010, 0.99d), 0.01d);
		assertEquals(8l, thresholdCalculator.calculate(ThresholdCalculationMethod.BINOMIAL_99, DJIBOUTI, 11, 2010));
	}
*/	
	
	@Transaction
	public void xtestCalculatePoliticalIndividualMeanThresholds() {
		output("Calculating Political Individual Mean Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
		assertThresholds(true, 11, 2010, td, 4, 4);
		output("Done!");
	}	

	@Transaction
	public void xtestCalculatePoliticalIndividualQuartileThresholds() {
		output("Calculating Political Individual Quartile Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
		assertThresholds(true, 11, 2010, td, 2, 2);
		output("Done!");
	}

	@Transaction
	public void xtestCalculatePoliticalIndividualBinomialThresholds() {
		output("Calculating Political Individual Binomial Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
		assertThresholds(true, 11, 2010, td, 6, 8);
		output("Done!");
	}	
	
	@Transaction
	public void xtestCalculatePoliticalAggregatedMeanThresholds() {
		output("Calculating Political Aggregated Mean Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
		assertThresholds(true, 11, 2010, td, 4, 4);
		output("Done!");
	}	

	@Transaction
	public void xtestCalculatePoliticalAggregatedQuartileThresholds() {
		output("Calculating Political Aggregated Quartile Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
		assertThresholds(true, 11, 2010, td, 2, 2);
		output("Done!");
	}

	@Transaction
	public void xtestCalculatePoliticalAggregatedBinomialThresholds() {
		output("Calculating Political Aggregated Binomial Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = DJIBOUTI.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(PoliticalThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(DJIBOUTI, season);
		assertThresholds(true, 11, 2010, td, 6, 8);
		output("Done!");
	}		
	
	@Transaction
	public void testCalculateFacilityIndividualMeanThresholds() {
		output("Calculating Facility Individual Mean Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
		assertThresholds(false, 11, 2010, td, 4, 4);
		output("Done!");
	}	

	@Transaction
	public void testCalculateFacilityIndividualQuartileThresholds() {
		output("Calculating Facility Individual Quartile Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
		assertThresholds(false, 11, 2010, td, 2, 2);
		output("Done!");
	}

	@Transaction
	public void testCalculateFacilityIndividualBinomialThresholds() {
		output("Calculating Facility Individual Binomial Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.INDIVIDUAL, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
		assertThresholds(false, 11, 2010, td, 6, 8);
		output("Done!");
	}	
	
	@Transaction
	public void testCalculateFacilityAggregatedMeanThresholds() {
		output("Calculating Facility Aggregated Mean Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.MEAN_PLUS_15_SD, ThresholdCalculationMethod.MEAN_PLUS_20_SD);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
		assertThresholds(false, 11, 2010, td, 4, 4);
		output("Done!");
	}	

	@Transaction
	public void testCalculateFacilityAggregatedQuartileThresholds() {
		output("Calculating Facility Aggregated Quartile Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE, ThresholdCalculationMethod.UPPER_THIRD_QUARTILE);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
		assertThresholds(false, 11, 2010, td, 2, 2);
		output("Done!");
	}

	@Transaction
	public void testCalculateFacilityAggregatedBinomialThresholds() {
		output("Calculating Facility Aggregated Binomial Thresholds...");
		ThresholdCalculationType calcType = this.createCalculationType(ThresholdCalculationCaseTypes.AGGREGATED, ThresholdCalculationMethod.BINOMIAL_95, ThresholdCalculationMethod.BINOMIAL_99);
		if (!CALCULATE_ALL_THRESHOLDS) {
			ThresholdCalculator.testingLimiter = KABWE_MINE_HOSPITAL.getGeoId();
		}
		MalariaSeason season = ThresholdCalculator.calculateThresholds(FacilityThresholdCalculator.class, calcType, false);
		
		ThresholdData td = ThresholdData.getThresholdData(KABWE_MINE_HOSPITAL, season);
		assertThresholds(false, 11, 2010, td, 6, 8);
		output("Done!");
	}	
	
	@Transaction
	public void testLeavingDataForManualTesting() {
		// This is an empty test to leave the data inside the system for validation
		// And to allow manual testing
	}

	private ThresholdCalculationType createCalculationType(ThresholdCalculationCaseTypes caseTypes, ThresholdCalculationMethod t1, ThresholdCalculationMethod t2) {
		double[] weights = {2.0d, 1.0d, 0.5d};
		return this.createCalculationType(caseTypes, t1, t2, 1, 1, 3, weights);
	}
	
	private ThresholdCalculationType createCalculationType(ThresholdCalculationCaseTypes caseTypes, ThresholdCalculationMethod t1, ThresholdCalculationMethod t2, int weeksBefore, int weeksAfter, int priorYears, double[] weights) {
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
	private void assertThresholds(boolean checkPolitical, int week, int year, ThresholdData td, int t1, int t2) {
		assertNotNull(td);
		WeeklyThreshold weeklyThreshold = td.getEpiWeeksRel(EpiWeek.getEpiWeek(week, year));
		assertNotNull(weeklyThreshold);
		if (checkPolitical) {
			assertNotNull(weeklyThreshold.getIdentification());
			assertEquals(t1, (int) weeklyThreshold.getNotification());
			assertNotNull(weeklyThreshold.getNotification());
			assertEquals(t2, (int) weeklyThreshold.getIdentification());
		} else {
			assertNotNull(weeklyThreshold.getFacilityIdentification());
			assertEquals(t1, (int) weeklyThreshold.getFacilityNotification());
			assertNotNull(weeklyThreshold.getFacilityNotification());
			assertEquals(t2, (int) weeklyThreshold.getFacilityIdentification());			
		}
	}
	
	private void createIndividualCases(int caseCount, int instanceCount, Date date, Patient patient, GeoEntity geoEntity) {
		for (int i = 0; i < caseCount; i++) {
			IndividualCase caseData = this.createIndividualCase(date, patient, geoEntity);
			this.createIndividualInstance(caseData, date, geoEntity, true);
			for (int j = 0; j < instanceCount; j++) {
				this.createIndividualInstance(caseData, date, geoEntity, false);
			}
		}
	}

	private IndividualCase createIndividualCase(Date date, Patient patient, GeoEntity geoEntity) {
		IndividualCase caseData = new IndividualCase();
		caseData.setDiagnosisDate(date);
		caseData.setPatient(patient);
		caseData.setProbableSource(geoEntity);
		caseData.setResidence(geoEntity);
		caseData.apply();
		return caseData;
	}
	
	private IndividualInstance createIndividualInstance(IndividualCase caseData, Date date, GeoEntity geoEntity, boolean active) {
		IndividualInstance instanceData = new IndividualInstance();
		instanceData.setIndividualCase(caseData);
		if (geoEntity instanceof HealthFacility) {
			instanceData.setHealthFacility((HealthFacility) geoEntity);
		}
		instanceData.setActivelyDetected(active);
		instanceData.setFacilityVisit(date);
		instanceData.apply();
		return instanceData;
	}
	
	private void createAggregatedCases(int count, Date startDate, Date endDate, GeoEntity geoEntity) {
		AggregatedCase data = new AggregatedCase();
		data.setStartDate(startDate);
		data.setEndDate(endDate);
		data.setGeoEntity(geoEntity);
	    data.setAgeGroup(AggregatedAgeGroup.getAll()[0]);
		data.setCases(count);
		data.apply();
	}

	@Transaction
	private void deleteAllTableRecords(String className) {
		MdEntity biz = MdEntity.getMdEntity(className);
		biz.deleteAllTableRecords();
	}

	private static Date createRelativeDate(int year, int month, int day) {
		return createRelativeCalendar(year, month, day).getTime();
	}

	private static Date createDate(int year, int month, int day) {
		return createCalendar(year, month, day).getTime();
	}

	private static Calendar createCalendar(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month, day);
		return c;
	}

	private static Calendar createRelativeCalendar(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DAY_OF_MONTH, day);
		return c;
	}
	
	private void output(String message) {
		System.out.println(new Date() + ": " + message);
	}
}
