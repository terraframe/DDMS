package dss.vector.solutions.threshold;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.Statistics;
import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.general.ThresholdCalculationMethod;
import dss.vector.solutions.general.ThresholdCalculationType;
import dss.vector.solutions.general.ThresholdData;
import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;

public abstract class ThresholdCalculator implements com.terraframe.mojo.generation.loader.Reloadable {
	protected final ThresholdCalculationType calculationType;
	protected String testingLimiter = null;
	
	protected class ThresholdCalculationPeriod {
		public MalariaSeason season;
		public EpiDate startingEpiWeek;
		public EpiDate endingEpiWeek;
	}

	public ThresholdCalculator(ThresholdCalculationType calculationType) {
		super();
		this.calculationType = calculationType;
	}
	
	public void setTestingLimiter(String testingLimiter) {
		this.testingLimiter = testingLimiter;
	}

	public MalariaSeason calculateThresholds(boolean currentPeriod) {
		ThresholdCalculationPeriod period = this.getCalculationPeriod(currentPeriod);
		if (period.season != null) {
			this.calculateThresholds(period);
		}
		return period.season;
	}
	
	protected abstract void calculateThresholds(ThresholdCalculationPeriod calculationPperiod);
	
	@Transaction
	protected void calculateThresholds(ThresholdCalculationPeriod calculationPeriod, GeoEntity geoEntity) {
		ThresholdCalculationMethod t1Method = calculationType.getT1Method().get(0);
		ThresholdCalculationMethod t2Method = calculationType.getT2Method().get(0);

		// System.out.println("Calculating thresholds: " +
		// startingEpiWeek.getActualPeriod() + "/" +
		// startingEpiWeek.getActualYear() + " - " +
		// endingEpiWeek.getActualPeriod() + "/" +
		// endingEpiWeek.getActualYear());
		if (!calculationPeriod.startingEpiWeek.getEndDate().after(calculationPeriod.endingEpiWeek.getEndDate())) {
			ThresholdData thresholdData = this.getThresholdData(geoEntity, calculationPeriod.season);
			for (EpiDate currentEpiWeek = calculationPeriod.startingEpiWeek; !currentEpiWeek.getStartDate().after(calculationPeriod.endingEpiWeek.getStartDate()); currentEpiWeek = currentEpiWeek.getNext()) {
				int period = currentEpiWeek.getActualPeriod();
				int year = currentEpiWeek.getActualYear();
				long t1 = this.calculate( t1Method, geoEntity, period, year);
				long t2 = t1;
				if (t1Method != t2Method) {
					t2 = this.calculate( t2Method, geoEntity, period, year);
				}
				this.createWeeklyThreshold( thresholdData, currentEpiWeek, t1, t2, 0, 0);
			}
		}
	}
	
	@Transaction
	protected long calculate(ThresholdCalculationMethod method, GeoEntity geoEntity, int week, int year) {
		long calculation = 0;

		if (method == ThresholdCalculationMethod.UPPER_THIRD_QUARTILE) {
			calculation = Math.round(this.calculateQuartile(geoEntity, week, year, 3));
		} else if (method == ThresholdCalculationMethod.MEAN_PLUS_15_SD) {
			calculation = Math.round(this.calculateMeanSD(geoEntity, week, year, 1.5d));
		} else if (method == ThresholdCalculationMethod.MEAN_PLUS_20_SD) {
			calculation = Math.round(this.calculateMeanSD(geoEntity, week, year, 2.0d));
		} else if (method == ThresholdCalculationMethod.BINOMIAL_95) {
			calculation = Math.round(this.calculateBinomial(geoEntity, week, year, 0.95d));
		} else if (method == ThresholdCalculationMethod.BINOMIAL_99) {
			calculation = Math.round(this.calculateBinomial(geoEntity, week, year, 0.99d));
		}

		return calculation;
	}
	
	@Transaction
	protected double calculateBinomial(GeoEntity geoEntity, int week, int year, double percentage) {
		long population = PopulationData.calculateAnnualPopulation(geoEntity.getGeoId(), year);
		double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, week, year);
		double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		Statistics s = new Statistics();
		return s.binomial(mean, population, 1.0d - percentage);
	}

	@Transaction
	protected double calculateQuartile(GeoEntity geoEntity, int week, int year, int quartile) {
		double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, week, year);

		Statistics s = new Statistics();
		return s.quartile(weightedSeasonalMeans, 3);
	}

	@Transaction
	protected double calculateMeanSD(GeoEntity geoEntity, int week, int year, double multiplier) {
		double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, week, year);
		double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		StandardDeviation std = new StandardDeviation();
		return mean + (multiplier * std.evaluate(weightedSeasonalMeans));
	}

	protected abstract double[] calculateWeightedSeasonalMeans(GeoEntity geoEntity, int week, int year);

	@Transaction
	protected double calculateWeightedMean(double[] weightedSeasonalMeans) {
		double runningSum = 0d;
		for (int i = 0; i < weightedSeasonalMeans.length; i++) {
			runningSum += weightedSeasonalMeans[i];
		}
		return runningSum / (double) weightedSeasonalMeans.length;
	}
	
	protected MalariaSeason getSeason(boolean currentPeriod) {
		if (currentPeriod) {
			return MalariaSeason.getSeasonByDate(new Date());
		} else {
			return MalariaSeason.getNextSeasonByDate(new Date());
		}
	}

	@Transaction
	protected ThresholdCalculationPeriod getCalculationPeriod(boolean currentPeriod) {
		ThresholdCalculationPeriod period = new ThresholdCalculationPeriod();
		period.season = this.getSeason(currentPeriod);

		if (period.season != null) {
			EpiDate thisEpiWeek = EpiDate.getEpiWeek(new Date());

			if (currentPeriod) {
				// If we're currently in a season, then we can't calculate any/ dates for weeks before now
				// So set the start to next epi week, and the end to the end of the season
				period.startingEpiWeek = thisEpiWeek.getNext();
				period.endingEpiWeek = EpiDate.getEpiWeek(period.season.getEndDate());
			} else {
				// If we're not in a season, we'll calculate NEXT season
				// We can only calculate up to a year out
				period.startingEpiWeek = EpiDate.getEpiWeek(period.season.getStartDate());
				Calendar nextYear = Calendar.getInstance();
				nextYear.add(Calendar.YEAR, 1);
				if (period.season.getEndDate().after(nextYear.getTime())) {
					period.endingEpiWeek = EpiDate.getEpiWeek(nextYear.getTime());
				} else {
					period.endingEpiWeek = EpiDate.getEpiWeek(period.season.getEndDate());
				}
			}
		}
		
		return period;
	}
	
	@Transaction
	protected void createWeeklyThreshold(ThresholdData thresholdData, EpiDate epiDate, long pt1, long pt2, long ft1, long ft2) {
		if (pt1 > 0 || pt2 > 0 || ft1 > 0 || ft2 > 0) {
			// System.out.println(thresholdData.getGeoEntity().getEntityName() +
			// ", " + thresholdData.getSeason().getSeasonName() + " (" +
			// epiDate.getActualPeriod() + "/" + epiDate.getActualYear() +
			// ") = " + t1 + ", " + t2);
			EpiWeek epiWeek = EpiWeek.getEpiWeek(epiDate);
			WeeklyThreshold weeklyThreshold = thresholdData.getEpiWeeksRel(epiWeek);
			if (weeklyThreshold == null) {
				weeklyThreshold = new WeeklyThreshold(thresholdData, epiWeek);
				if (pt1 > 0) {
					weeklyThreshold.setNotification((int) pt1);
				}
				if (pt2 > 0) {
					weeklyThreshold.setIdentification((int) pt2);
				}
				if (ft1 > 0) {
					weeklyThreshold.setFacilityNotification((int) ft1);
				}
				if (ft2 > 0) {
					weeklyThreshold.setFacilityIdentification((int) ft2);
				}
				weeklyThreshold.setCalculationType(calculationType);
				weeklyThreshold.apply();
			}
		}

	}

	@Transaction
	protected ThresholdData getThresholdData(GeoEntity geoEntity, MalariaSeason season) {
		ThresholdData td = ThresholdData.getThresholdData(geoEntity, season);
		if (td == null) {
			// System.out.println("Creating Threshold Data");
			td = new ThresholdData();
			td.setGeoEntity(geoEntity);
			td.setSeason(season);
			td.apply();
		}
		// System.out.println(td);
		return td;

	}
	
	@Transaction
	protected long getAggregatedCount(QueryFactory factory, GeoEntityQuery entityQuery, EpiDate initialWeek, EpiDate finalWeek) {
		ValueQuery valueQuery = new ValueQuery(factory);
		AggregatedCaseQuery caseQuery = new AggregatedCaseQuery(factory);

		//System.out.println("From: " + initialWeek.getStartDate());
		//System.out.println("  To: " + finalWeek.getEndDate());
		valueQuery.SELECT(F.SUM(caseQuery.getCases(), "cases"));
		valueQuery.WHERE(caseQuery.getGeoEntity().EQ(entityQuery));
		valueQuery.WHERE(caseQuery.getStartDate().GE(initialWeek.getStartDate()));
		valueQuery.WHERE(caseQuery.getEndDate().LE(finalWeek.getEndDate()));
		// Make sure we only grab epi week periods
		valueQuery.AND(caseQuery.getEndDate().EQ(valueQuery.aSQLDate("startDate", caseQuery.getStartDate().getQualifiedName() + "+ interval '6 days'")));
		valueQuery.FROM(caseQuery.getStartDate().getDefiningTableName(), caseQuery.getStartDate().getDefiningTableAlias());

		long sum = 0;
		for (ValueObject valueObject : valueQuery.getIterator()) {
			String valueString = valueObject.getValue("cases");
			if (valueString.length() > 0) {
				long value = Long.parseLong(valueString);
				System.out.println(value);
				sum += value;
			}
		}
		return sum;
	}
}

