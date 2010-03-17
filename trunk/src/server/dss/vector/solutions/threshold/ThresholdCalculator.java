package dss.vector.solutions.threshold;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.dataaccess.ValueObject;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.F;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.Statistics;
import dss.vector.solutions.general.CalculationInProgressException;
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
import dss.vector.solutions.surveillance.AggregatedCaseQuery;
import dss.vector.solutions.surveillance.PeriodType;

/*
 * Algorithm: For EACH GeoEntity (that has population and
 * isPolitical/isHeathFacility) For EACH EpiWeek in the calculation period For
 * ALL politicalDescendants/me For EACH year from now-1 to now-1-priorYears For
 * ALL weeks between (now – beforeWeeks) and (now + afterWeeks) Calculate mean
 * Calculate threshold Create weekly threshold record
 */
public abstract class ThresholdCalculator implements com.terraframe.mojo.generation.loader.Reloadable {
	private static ThresholdCalculator instance = null;

	public static String testingLimiter = null;

	protected ThresholdCalculationType calculationType;

	protected long geoEntityCount = 1;

	protected long completedCount = 0;

	protected class ThresholdCalculationPeriod implements com.terraframe.mojo.generation.loader.Reloadable {
		public MalariaSeason season;

		public EpiDate startingEpiWeek;

		public EpiDate endingEpiWeek;

		public ThresholdCalculationPeriod() {
			super();
		}
	}

	public static MalariaSeason calculateThresholds(Class<? extends ThresholdCalculator> clazz, ThresholdCalculationType calculationType, boolean currentPeriod) {
		MalariaSeason season = null;
		try {
			ThresholdCalculator instance = newInstance(clazz, calculationType);
			if (instance != null) {
				// // Lock the classloader during the long running operation
				// try {
				// LockHolder.lock(LoaderDecorator.instance());
				// season = instance.calculateThresholds(currentPeriod);
				// } finally {
				// LockHolder.unlock();
				// }
				try {
					season = instance.calculateThresholds(currentPeriod);
				} finally {
					clearInstance();
				}
			}
		} catch (InstantiationException e) {
			throw new ApplicationException(e);
		} catch (IllegalAccessException e) {
			throw new ApplicationException(e);
		}
		return season;
	}

	public static int getPercentComplete() {
		ThresholdCalculator instance = getInstance();
		if (instance == null) {
			return -1;
		}
		return Math.round(100 * instance.completedCount / instance.geoEntityCount);
	}

	private synchronized static ThresholdCalculator getInstance() {
		return instance;
	}

	private synchronized static ThresholdCalculator newInstance(Class<? extends ThresholdCalculator> clazz, ThresholdCalculationType calculationType) throws InstantiationException, IllegalAccessException {
		if (instance == null) {
			instance = (ThresholdCalculator) clazz.newInstance();
			instance.calculationType = calculationType;
		} else {
			String msg = "Thresholds are already being calculated";

			CalculationInProgressException e = new CalculationInProgressException(msg);
			e.setPercentComplete(ThresholdCalculator.getPercentComplete());
			e.apply();

			throw e;
		}

		return instance;
	}

	private synchronized static void clearInstance() {
		instance = null;
	}

	protected abstract GeoEntityQuery getEntityQuery(QueryFactory factory);

	protected abstract GeoEntityQuery getRelatedEntitiesQuery(GeoEntity geoEntity, QueryFactory factory);

	protected abstract long getIndividualCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate);

	protected abstract void setThresholdValues(WeeklyThreshold weeklyThreshold, long t1, long t2);

	private MalariaSeason calculateThresholds(boolean currentPeriod) {
		ThresholdCalculationPeriod period = this.getCalculationPeriod(currentPeriod);
		if (period.season != null) {
			this.calculateThresholds(period);
		}
		return period.season;
	}

	@Transaction
	protected void calculateThresholds(ThresholdCalculationPeriod calculationPeriod) {
		GeoEntityQuery query = this.getEntityQuery(new QueryFactory());
		OIterator<? extends GeoEntity> it = query.getIterator();
		if (testingLimiter == null) {
			this.geoEntityCount = query.getCount();
		}

		try {
			// For EACH GeoEntity that has population and isPolitical
			while (it.hasNext()) {
				GeoEntity geoEntity = it.next();
				if (testingLimiter == null || testingLimiter.equals(geoEntity.getGeoId())) {
					// System.out.println(geoEntity.getEntityName());
					this.calculateThresholds(calculationPeriod, geoEntity);
					this.completedCount++;
				}
			}
		} finally {
			it.close();
		}
	}

	@Transaction
	protected void calculateThresholds(ThresholdCalculationPeriod calculationPeriod, GeoEntity geoEntity) {
		ThresholdCalculationMethod t1Method = calculationType.getT1Method().get(0);
		ThresholdCalculationMethod t2Method = calculationType.getT2Method().get(0);

		System.out.println("Calculating thresholds: " + calculationPeriod.startingEpiWeek.getActualPeriod() + "/" + calculationPeriod.startingEpiWeek.getActualYear() + " - " + calculationPeriod.endingEpiWeek.getActualPeriod() + "/" + calculationPeriod.endingEpiWeek.getActualYear());
		if (!calculationPeriod.startingEpiWeek.getEndDate().after(calculationPeriod.endingEpiWeek.getEndDate())) {
			// For EACH EpiWeek in the calculation period
			for (EpiDate currentEpiWeek = calculationPeriod.startingEpiWeek; !currentEpiWeek.getStartDate().after(calculationPeriod.endingEpiWeek.getStartDate()); currentEpiWeek = currentEpiWeek.getNext()) {
				int period = currentEpiWeek.getActualPeriod();
				int year = currentEpiWeek.getActualYear();

				// Find the start and end dates for each year
				Date[] yearlyInitialDates = new Date[calculationType.getPriorYears()];
				Date[] yearlyFinalDates = new Date[calculationType.getPriorYears()];
				for (int i = 0; i < calculationType.getPriorYears(); i++) {
					int thisYear = year - i - 1;
					EpiDate selectedWeek = EpiDate.getInstanceByPeriod(PeriodType.WEEK, period, thisYear);

					EpiDate initialWeek = selectedWeek;
					for (int j = 0; j < calculationType.getWeeksBefore(); j++) {
						initialWeek = initialWeek.getPrevious();
					}
					yearlyInitialDates[i] = initialWeek.getStartDate();

					EpiDate finalWeek = selectedWeek;
					for (int j = 0; j < calculationType.getWeeksAfter(); j++) {
						finalWeek = finalWeek.getNext();
					}
					yearlyFinalDates[i] = finalWeek.getEndDate();
				}

				double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, yearlyInitialDates, yearlyFinalDates);
				long t1 = this.calculate(t1Method, geoEntity, period, year, weightedSeasonalMeans);
				long t2 = t1;
				if (t1Method != t2Method) {
					t2 = this.calculate(t2Method, geoEntity, period, year, weightedSeasonalMeans);
				}

				this.createWeeklyThreshold(geoEntity, calculationPeriod.season, currentEpiWeek, t1, t2);
			}
		}
	}

	@Transaction
	protected long calculate(ThresholdCalculationMethod method, GeoEntity geoEntity, int week, int year, double[] weightedSeasonalMeans) {
		long calculation = 0;

		if (method == ThresholdCalculationMethod.UPPER_THIRD_QUARTILE) {
			calculation = Math.round(this.calculateQuartile(geoEntity, week, year, weightedSeasonalMeans, 3));
		} else if (method == ThresholdCalculationMethod.MEAN_PLUS_15_SD) {
			calculation = Math.round(this.calculateMeanSD(geoEntity, week, year, weightedSeasonalMeans, 1.5d));
		} else if (method == ThresholdCalculationMethod.MEAN_PLUS_20_SD) {
			calculation = Math.round(this.calculateMeanSD(geoEntity, week, year, weightedSeasonalMeans, 2.0d));
		} else if (method == ThresholdCalculationMethod.BINOMIAL_95) {
			calculation = Math.round(this.calculateBinomial(geoEntity, week, year, weightedSeasonalMeans, 0.95d));
		} else if (method == ThresholdCalculationMethod.BINOMIAL_99) {
			calculation = Math.round(this.calculateBinomial(geoEntity, week, year, weightedSeasonalMeans, 0.99d));
		}

		return calculation;
	}

	@Transaction
	protected double calculateBinomial(GeoEntity geoEntity, int week, int year, double[] weightedSeasonalMeans, double percentage) {
		long population = PopulationData.calculateAnnualPopulation(geoEntity.getGeoId(), year);
		double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		Statistics s = new Statistics();
		return s.binomial(mean, population, 1.0d - percentage);
	}

	@Transaction
	protected double calculateQuartile(GeoEntity geoEntity, int week, int year, double[] weightedSeasonalMeans, int quartile) {
		Statistics s = new Statistics();
		return s.quartile(weightedSeasonalMeans, 3);
	}

	@Transaction
	protected double calculateMeanSD(GeoEntity geoEntity, int week, int year, double[] weightedSeasonalMeans, double multiplier) {
		double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		StandardDeviation std = new StandardDeviation();
		return mean + (multiplier * std.evaluate(weightedSeasonalMeans));
	}

	@Transaction
	protected double[] calculateWeightedSeasonalMeans(GeoEntity geoEntity, Date[] yearlyInitialDates, Date[] yearlyFinalDates) {
		double[] weightedSeasonalMeans = new double[calculationType.getPriorYears()];
		double sumOfWeights = 0d;
		for (int i = 0; i < calculationType.getPriorYears(); i++) {
			sumOfWeights += calculationType.getWeights()[i];
		}

		QueryFactory factory = new QueryFactory();
		GeoEntityQuery entityQuery = this.getRelatedEntitiesQuery(geoEntity, factory);

		// For EACH year from now-1 to now-1-priorYears
		for (int i = 0; i < calculationType.getPriorYears(); i++) {
			long totalCases = 0;
			if (calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.INDIVIDUAL) || calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.BOTH)) {
				totalCases += this.getIndividualCount(factory, entityQuery, yearlyInitialDates[i], yearlyFinalDates[i]);
			}
			if (calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.AGGREGATED) || calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.BOTH)) {
				totalCases += this.getAggregatedCount(factory, entityQuery, yearlyInitialDates[i], yearlyFinalDates[i]);
			}
			double seasonalMean = (double) totalCases / (double) (calculationType.getWeeksBefore() + calculationType.getWeeksAfter() + 1);
			weightedSeasonalMeans[i] = seasonalMean * calculationType.getWeights()[i] * (double) calculationType.getPriorYears() / sumOfWeights;

			// System.out.println("COUNT for " + thisYear + " (" +
			// initialWeek.getStartDate() + "-" + finalWeek.getEndDate() +
			// ") = " + query.getCount());
			// System.out.println("WSM for " + thisYear + " = " +
			// weightedSeasonalMeans[i]);
		}

		return weightedSeasonalMeans;
	}

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
			Date now = new Date();

			EpiDate thisEpiWeek = EpiDate.getEpiWeek(new Date(now.getYear(), now.getMonth(), now.getDate()));

			if (currentPeriod) {
				// If we're currently in a season, then we can't calculate any/
				// dates
				// for weeks before now
				// So set the start to next epi week, and the end to the end of
				// the
				// season
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
	protected void createWeeklyThreshold(GeoEntity geoEntity, MalariaSeason season, EpiDate epiDate, long t1, long t2) {
		EpiWeek epiWeek = EpiWeek.getEpiWeek(epiDate);
		ThresholdData thresholdData = this.getThresholdData(geoEntity, season);

		WeeklyThreshold weeklyThreshold = thresholdData.getEpiWeeksRel(epiWeek);
		if (weeklyThreshold == null) {
			if (t1 > 0 || t2 > 0) {
				// only bother creating a record if both values are non-zero
				weeklyThreshold = new WeeklyThreshold(thresholdData, epiWeek);
			}
		} else {
			weeklyThreshold.appLock();
		}

		if (weeklyThreshold != null) {
			this.setThresholdValues(weeklyThreshold, t1, t2);
			weeklyThreshold.setCalculationType(calculationType);
			weeklyThreshold.apply();
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
	protected long getAggregatedCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		ValueQuery valueQuery = new ValueQuery(factory);
		AggregatedCaseQuery caseQuery = new AggregatedCaseQuery(factory);

		// System.out.println("From: " + initialWeek.getStartDate());
		// System.out.println("  To: " + finalWeek.getEndDate());
		valueQuery.SELECT(F.SUM(caseQuery.getCases(), "cases"));
		valueQuery.WHERE(caseQuery.getGeoEntity().EQ(entityQuery));
		valueQuery.WHERE(caseQuery.getStartDate().GE(initialDate));
		valueQuery.WHERE(caseQuery.getEndDate().LE(finalDate));
		// Make sure we only grab epi week periods
		valueQuery.AND(caseQuery.getEndDate().EQ(valueQuery.aSQLDate("startDate", caseQuery.getStartDate().getQualifiedName() + "+ interval '6 days'")));
		valueQuery.FROM(caseQuery.getStartDate().getDefiningTableName(), caseQuery.getStartDate().getDefiningTableAlias());

		long sum = 0;
		for (ValueObject valueObject : valueQuery.getIterator()) {
			String valueString = valueObject.getValue("cases");
			if (valueString.length() > 0) {
				long value = Long.parseLong(valueString);
				// System.out.println(value);
				sum += value;
			}
		}
		return sum;
	}
}
