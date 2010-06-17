package dss.vector.solutions.threshold;


import java.util.Date;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.F;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.intervention.monitor.IndividualCaseQuery;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;

public class PoliticalThresholdCalculator extends ThresholdCalculator implements com.runwaysdk.generation.loader.Reloadable {

	@Transaction
	protected GeoEntityQuery getEntityQuery(QueryFactory factory) {
		GeoEntity earth = Earth.getEarthInstance();
		return earth.getPopulationDecendants(new QueryFactory());
	}

	@Transaction
	protected GeoEntityQuery getRelatedEntitiesQuery(GeoEntity geoEntity, QueryFactory factory) {
		return geoEntity.getAllDecendants(factory);
	}

	@Transaction
	protected double getIndividualCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		IndividualCaseQuery query = new IndividualCaseQuery(factory);

		query.WHERE(query.getDisease().EQ(Disease.getCurrent()));
		query.AND(query.getProbableSource().EQ(entityQuery));
		query.AND(query.getDiagnosisDate().GE(initialDate));
		query.AND(query.getDiagnosisDate().LE(finalDate));

		return (double) query.getCount();
	}
	
	@Transaction
	protected double getAggregatedCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		ValueQuery valueQuery = new ValueQuery(factory);
		AggregatedCaseQuery caseQuery = new AggregatedCaseQuery(factory);

		// System.out.println("From: " + initialWeek.getStartDate());
		// System.out.println("  To: " + finalWeek.getEndDate());
		valueQuery.SELECT(F.SUM(caseQuery.getCases(), "clinicalCases"));
		valueQuery.SELECT(F.SUM(caseQuery.getPositiveCases(), "positiveCases"));
		valueQuery.SELECT(F.SUM(caseQuery.getNegativeCases(), "negativeCases"));
		valueQuery.WHERE(caseQuery.getDisease().EQ(Disease.getCurrent()));
		valueQuery.AND(caseQuery.getGeoEntity().EQ(entityQuery));
		valueQuery.AND(caseQuery.getStartDate().GE(initialDate));
		valueQuery.AND(caseQuery.getEndDate().LE(finalDate));
		// Make sure we only grab epi week periods
		valueQuery.AND(caseQuery.getEndDate().EQ(valueQuery.aSQLDate("startDate", caseQuery.getStartDate().getDbQualifiedName() + "+ interval '6 days'")));
		valueQuery.FROM(caseQuery.getStartDate().getDefiningTableName(), caseQuery.getStartDate().getDefiningTableAlias());

		long sumClinicalCases = 0l;
		long sumPositiveCases = 0l;
		long sumNegativeCases = 0l;
		for (ValueObject valueObject : valueQuery.getIterator()) {
			sumClinicalCases += this.getValue(valueObject, "clinicalCases");
			sumPositiveCases += this.getValue(valueObject, "positiveCases");
			sumNegativeCases += this.getValue(valueObject, "negativeCases");
		}
		
		double ratio = 1.0d;
		
		if (sumPositiveCases + sumNegativeCases > 0) {
			ratio = ((double) (sumPositiveCases)) / ((double) (sumPositiveCases + sumNegativeCases));
		}
		
		return (double) sumPositiveCases + ((double) sumClinicalCases * ratio);
	}

	protected void setThresholdValues(WeeklyThreshold weeklyThreshold, long t1, long t2) {
		if (t1 <= 0) {
			weeklyThreshold.setNotification(null);
		} else {
			weeklyThreshold.setNotification((int) t1);
		}
		
		if (t2 <= 0) {
			weeklyThreshold.setIdentification(null);
		} else { 
			weeklyThreshold.setIdentification((int) t2);
		}
	}
}
