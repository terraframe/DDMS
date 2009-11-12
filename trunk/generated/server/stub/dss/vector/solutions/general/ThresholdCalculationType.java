package dss.vector.solutions.general;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Statistics;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.intervention.monitor.IndividualCaseQuery;
import dss.vector.solutions.surveillance.PeriodType;

public class ThresholdCalculationType extends ThresholdCalculationTypeBase implements com.terraframe.mojo.generation.loader.Reloadable {
	private static final long serialVersionUID = 1258003583251L;

	public ThresholdCalculationType() {
		super();
	}

	public ThresholdCalculationTypeView getView() {
		ThresholdCalculationTypeView view = new ThresholdCalculationTypeView();
		view.populateView(this);

		return view;
	}

	@Transaction
	public static ThresholdCalculationType getCurrent() {
		ThresholdCalculationTypeQuery query = new ThresholdCalculationTypeQuery(new QueryFactory());
		query.ORDER_BY_DESC(query.getCreateDate());

		OIterator<? extends ThresholdCalculationType> iterator = query.getIterator();

		try {
			while (iterator.hasNext()) {
				return iterator.next();
			}

			// No ThresholdCalculationType has been created before. Therefore
			// return one containing the default values.
			return null;
		} finally {
			iterator.close();
		}
	}

	@Override
	public void apply() {
		if (this.isDifferent()) {
			super.apply();
		}
	}

	private boolean isDifferent() {
		ThresholdCalculationTypeView recent = ThresholdCalculationTypeView.getCalculationThreshold();

		if (!this.getT1Method().containsAll(recent.getT1Method())) {
			return true;
		}

		if (!this.getT2Method().containsAll(recent.getT2Method())) {
			return true;
		}

		if (!this.getWeeksBefore().equals(recent.getWeeksBefore())) {
			return true;
		}

		if (!this.getWeeksAfter().equals(recent.getWeeksAfter())) {
			return true;
		}

		if (!this.getPriorYears().equals(recent.getPriorYears())) {
			return true;
		}

		if (!this.getWeight0().equals(recent.getWeight0())) {
			return true;
		}

		if (!this.getWeight1().equals(recent.getWeight1())) {
			return true;
		}

		if (!this.getWeight2().equals(recent.getWeight2())) {
			return true;
		}

		if (!this.getWeight3().equals(recent.getWeight3())) {
			return true;
		}

		if (!this.getWeight4().equals(recent.getWeight4())) {
			return true;
		}

		if (!this.getWeight5().equals(recent.getWeight5())) {
			return true;
		}

		if (!this.getWeight6().equals(recent.getWeight6())) {
			return true;
		}

		if (!this.getWeight7().equals(recent.getWeight7())) {
			return true;
		}

		if (!this.getWeight8().equals(recent.getWeight8())) {
			return true;
		}

		if (!this.getWeight9().equals(recent.getWeight9())) {
			return true;
		}

		return false;
	}

	public double[] getWeights() {
		double[] weights = new double[this.getPriorYears()];
		switch (this.getPriorYears()) {
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

	public void setWeights(double[] weights) {
		switch (this.getPriorYears()) {
		case 10:
			this.setWeight9(weights[9]);
		case 9:
			this.setWeight8(weights[8]);
		case 8:
			this.setWeight7(weights[7]);
		case 7:
			this.setWeight6(weights[6]);
		case 6:
			this.setWeight5(weights[5]);
		case 5:
			this.setWeight4(weights[4]);
		case 4:
			this.setWeight3(weights[3]);
		case 3:
			this.setWeight2(weights[2]);
		case 2:
			this.setWeight1(weights[1]);
		case 1:
			this.setWeight0(weights[0]);
		}
	}

	public MalariaSeason getSeason(boolean currentPeriod) {
		if (currentPeriod) {
			return MalariaSeason.getSeasonByDate(new Date());
		} else {
			return MalariaSeason.getNextSeasonByDate(new Date());
		}
	}

	@Transaction
	public MalariaSeason calculateThresholds(boolean currentPeriod) {
		MalariaSeason season = this.getSeason(currentPeriod);

		if (season != null) {

			EpiDate thisEpiWeek = EpiDate.getEpiWeek(new Date());

			EpiDate startingEpiWeek = null;
			EpiDate endingEpiWeek = null;
			if (currentPeriod) {
				// If we're currently in a season, then we can't calculate any
				// dates
				// for weeks before now
				// So set the start to next epi week, and the end to the end of
				// the
				// season
				startingEpiWeek = thisEpiWeek.getNext();
				endingEpiWeek = EpiDate.getEpiWeek(season.getEndDate());
			} else {
				// If we're not in a season, we'll calculate NEXT season
				// We can only calculate up to a year out
				startingEpiWeek = EpiDate.getEpiWeek(season.getStartDate());
				Calendar nextYear = Calendar.getInstance();
				nextYear.add(Calendar.YEAR, 1);
				if (season.getEndDate().after(nextYear.getTime())) {
					endingEpiWeek = EpiDate.getEpiWeek(nextYear.getTime());
				} else {
					endingEpiWeek = EpiDate.getEpiWeek(season.getEndDate());
				}
			}

			GeoEntity earth = Earth.getEarthInstance();
			GeoEntityQuery query = earth.getPopulationDecendants(new QueryFactory());
			OIterator<? extends GeoEntity> it = query.getIterator();

			try {
				while (it.hasNext()) {
					GeoEntity geoEntity = it.next();
					// if ("22220033".equals(geoEntity.getGeoId())) {
					// System.out.println(geoEntity.getEntityName());
					// }
					this.calculateThresholds(geoEntity, season, startingEpiWeek, endingEpiWeek);
				}
			} finally {
				it.close();
			}
		}

		return season;
	}

	@Transaction
	private void calculateThresholds(GeoEntity geoEntity, MalariaSeason season, EpiDate startingEpiWeek, EpiDate endingEpiWeek) {
		ThresholdCalculationMethod t1Method = this.getT1Method().get(0);
		ThresholdCalculationMethod t2Method = this.getT2Method().get(0);

		// System.out.println("Calculating thresholds: " +
		// startingEpiWeek.getActualPeriod() + "/" +
		// startingEpiWeek.getActualYear() + " - " +
		// endingEpiWeek.getActualPeriod() + "/" +
		// endingEpiWeek.getActualYear());
		if (!startingEpiWeek.getEndDate().after(endingEpiWeek.getEndDate())) {
			ThresholdData thresholdData = this.getThresholdData(geoEntity, season);
			for (EpiDate currentEpiWeek = startingEpiWeek; !currentEpiWeek.getStartDate().after(endingEpiWeek.getStartDate()); currentEpiWeek = currentEpiWeek.getNext()) {
				int period = currentEpiWeek.getActualPeriod();
				int year = currentEpiWeek.getActualYear();
				long t1 = this.calculate(t1Method, geoEntity, period, year);
				long t2 = t1;
				if (t1Method != t2Method) {
					t2 = this.calculate(t2Method, geoEntity, period, year);
				}
				this.createWeeklyThreshold(thresholdData, currentEpiWeek, t1, t2);
			}
		}
	}

	@Transaction
	private void createWeeklyThreshold(ThresholdData thresholdData, EpiDate epiDate, long t1, long t2) {
		if (t1 > 0 && t2 > 0) {
			// System.out.println(thresholdData.getGeoEntity().getEntityName() +
			// ", " + thresholdData.getSeason().getSeasonName() + " (" +
			// epiDate.getActualPeriod() + "/" + epiDate.getActualYear() +
			// ") = " + t1 + ", " + t2);
			EpiWeek epiWeek = EpiWeek.getEpiWeek(epiDate);
			WeeklyThreshold weeklyThreshold = thresholdData.getEpiWeeksRel(epiWeek);
			if (weeklyThreshold == null) {
				weeklyThreshold = new WeeklyThreshold(thresholdData, epiWeek);
				if (t1 > 0) {
					weeklyThreshold.setNotification((int) t1);
				}
				if (t2 > 0) {
					weeklyThreshold.setIdentification((int) t2);
				}
				weeklyThreshold.setCalculationType(this);
				weeklyThreshold.apply();
			}
		}

	}

	@Transaction
	private ThresholdData getThresholdData(GeoEntity geoEntity, MalariaSeason season) {
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
	private long calculate(ThresholdCalculationMethod method, GeoEntity geoEntity, int week, int year) {
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
	private double calculateBinomial(GeoEntity geoEntity, int week, int year, double percentage) {
		long population = PopulationData.calculateAnnualPopulation(geoEntity.getGeoId(), year);

		double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, week, year);

		double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		Statistics s = new Statistics();
		return s.binomial(mean, population, 1.0d - percentage);
	}

	@Transaction
	private double calculateQuartile(GeoEntity geoEntity, int week, int year, int quartile) {
		double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, week, year);

		// double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		Statistics s = new Statistics();
		return s.quartile(weightedSeasonalMeans, 3);
	}

	@Transaction
	private double calculateMeanSD(GeoEntity geoEntity, int week, int year, double multiplier) {
		double[] weightedSeasonalMeans = this.calculateWeightedSeasonalMeans(geoEntity, week, year);

		double mean = this.calculateWeightedMean(weightedSeasonalMeans);

		StandardDeviation std = new StandardDeviation();
		return mean + (multiplier * std.evaluate(weightedSeasonalMeans));
	}

	@Transaction
	private double calculateWeightedMean(double[] weightedSeasonalMeans) {
		double runningSum = 0d;
		for (int i = 0; i < weightedSeasonalMeans.length; i++) {
			runningSum += weightedSeasonalMeans[i];
		}
		return runningSum / (double) weightedSeasonalMeans.length;
	}

	@Transaction
	private double[] calculateWeightedSeasonalMeans(GeoEntity geoEntity, int week, int year) {
		double[] weightedSeasonalMeans = new double[this.getPriorYears()];
		double sumOfWeights = 0d;
		for (int i = 0; i < this.getPriorYears(); i++) {
			sumOfWeights += this.getWeights()[i];
		}

		QueryFactory factory = new QueryFactory();
		GeoEntityQuery entityQuery = geoEntity.getPoliticalDecendants(factory);

		for (int i = 0; i < this.getPriorYears(); i++) {
			int thisYear = year - i - 1;

			EpiDate selectedWeek = EpiDate.getInstanceByPeriod(PeriodType.WEEK, week, thisYear);

			EpiDate initialWeek = selectedWeek;
			for (int j = 0; j < this.getWeeksBefore(); j++) {
				initialWeek = initialWeek.getPrevious();
			}

			EpiDate finalWeek = selectedWeek;
			for (int j = 0; j < this.getWeeksAfter(); j++) {
				finalWeek = finalWeek.getNext();
			}

			IndividualCaseQuery query = new IndividualCaseQuery(factory);

			Condition condition = query.getProbableSource().EQ(entityQuery);
			condition = AND.get(condition, query.getDiagnosisDate().GE(initialWeek.getStartDate()));
			condition = AND.get(condition, query.getDiagnosisDate().LE(finalWeek.getEndDate()));
			query.WHERE(condition);

			double seasonalMean = (double) query.getCount() / (double) (this.getWeeksBefore() + this.getWeeksAfter() + 1);
			weightedSeasonalMeans[i] = seasonalMean * getWeights()[i] * (double) this.getPriorYears() / sumOfWeights;

			// System.out.println("COUNT for " + thisYear + " (" +
			// initialWeek.getStartDate() + "-" + finalWeek.getEndDate() +
			// ") = " + query.getCount());
			// System.out.println("WSM for " + thisYear + " = " +
			// weightedSeasonalMeans[i]);
		}

		return weightedSeasonalMeans;
	}

}
