package dss.vector.solutions.threshold;


import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.general.ThresholdCalculationCaseTypes;
import dss.vector.solutions.general.ThresholdCalculationType;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.intervention.monitor.IndividualCaseQuery;
import dss.vector.solutions.surveillance.PeriodType;

public class PoliticalThresholdCalculator extends ThresholdCalculator implements com.terraframe.mojo.generation.loader.Reloadable {
	
	public PoliticalThresholdCalculator(ThresholdCalculationType calculationType) {
		super(calculationType);
	}

	@Transaction
	protected void calculateThresholds(ThresholdCalculationPeriod calculationPeriod) {
		GeoEntity earth = Earth.getEarthInstance();
		GeoEntityQuery query = earth.getPopulationDecendants(new QueryFactory());
		OIterator<? extends GeoEntity> it = query.getIterator();

		try {
			while (it.hasNext()) {
				GeoEntity geoEntity = it.next();
				if (this.testingLimiter == null || this.testingLimiter.equals(geoEntity.getGeoId())) {
					System.out.println(geoEntity.getEntityName());
					this.calculateThresholds(calculationPeriod, geoEntity);
				}
			}
		} finally {
			it.close();
		}
	}

	@Transaction
	protected double[] calculateWeightedSeasonalMeans(GeoEntity geoEntity, int week, int year) {
		double[] weightedSeasonalMeans = new double[calculationType.getPriorYears()];
		double sumOfWeights = 0d;
		for (int i = 0; i < calculationType.getPriorYears(); i++) {
			sumOfWeights += calculationType.getWeights()[i];
		}

		QueryFactory factory = new QueryFactory();
		GeoEntityQuery entityQuery = geoEntity.getPoliticalDecendants(factory);

		for (int i = 0; i < calculationType.getPriorYears(); i++) {
			int thisYear = year - i - 1;

			EpiDate selectedWeek = EpiDate.getInstanceByPeriod(PeriodType.WEEK, week, thisYear);

			EpiDate initialWeek = selectedWeek;
			for (int j = 0; j < calculationType.getWeeksBefore(); j++) {
				initialWeek = initialWeek.getPrevious();
			}

			EpiDate finalWeek = selectedWeek;
			for (int j = 0; j < calculationType.getWeeksAfter(); j++) {
				finalWeek = finalWeek.getNext();
			}

			long totalCases = 0;
			if (calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.INDIVIDUAL) || calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.BOTH)) {
				totalCases += this.getIndividualCount(factory, entityQuery, initialWeek, finalWeek);
			}
			if (calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.AGGREGATED) || calculationType.getCaseTypes().contains(ThresholdCalculationCaseTypes.BOTH)) {
				totalCases += this.getAggregatedCount(factory, entityQuery, initialWeek, finalWeek);
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
	private long getIndividualCount(QueryFactory factory, GeoEntityQuery entityQuery, EpiDate initialWeek, EpiDate finalWeek) {
		IndividualCaseQuery query = new IndividualCaseQuery(factory);

		Condition condition = query.getProbableSource().EQ(entityQuery);
		condition = AND.get(condition, query.getDiagnosisDate().GE(initialWeek.getStartDate()));
		condition = AND.get(condition, query.getDiagnosisDate().LE(finalWeek.getEndDate()));
		query.WHERE(condition);

		return query.getCount();
	}
}
