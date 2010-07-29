package dss.vector.solutions.threshold;


import java.util.Date;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.F;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SUM;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.intervention.monitor.DiagnosisType;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;

public class PoliticalThresholdCalculator extends ThresholdCalculator implements com.runwaysdk.generation.loader.Reloadable {
	public static final int POSITIVE_COUNT_INDEX = 0;
	public static final int NEGATIVE_COUNT_INDEX = 1;
	public static final int CLINICAL_COUNT_INDEX = 2;

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
		double[] counts = this.getIndividualCounts(factory, entityQuery, initialDate, finalDate);
		
		double ratio = 1.0d;
		
		if (counts[POSITIVE_COUNT_INDEX] + counts[NEGATIVE_COUNT_INDEX] > 0) {
			ratio = ((double) (counts[POSITIVE_COUNT_INDEX])) / ((double) (counts[POSITIVE_COUNT_INDEX] + counts[NEGATIVE_COUNT_INDEX]));
		}
		
		return (double) counts[POSITIVE_COUNT_INDEX] + ((double) counts[CLINICAL_COUNT_INDEX] * ratio);
	}
	
	@Transaction
	public double[] getIndividualCounts(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		double[] counts = new double[3];
		
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
		innerQuery.AND(iQuery.getIndividualCase().getSymptomOnset().GE(initialDate));
		innerQuery.AND(iQuery.getIndividualCase().getSymptomOnset().LE(finalDate));
		// innerQuery.AND(iQuery.getActivelyDetected().EQ(false));

		ValueQuery vQuery = new ValueQuery(factory);
		vQuery.SELECT(F.SUM(vQuery.aSQLLong("positive", "(case when " + positiveColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), "positiveCases"));
		vQuery.SELECT(F.SUM(vQuery.aSQLLong("negative", "(case when " + positiveColumn.getColumnAlias() + " = 0 and " + negativeColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), "negativeCases"));
		vQuery.SELECT(F.SUM(vQuery.aSQLLong("clinical", "(case when " + positiveColumn.getColumnAlias() + " = 0 and " + negativeColumn.getColumnAlias() + " = 0 and " + clinicalColumn.getColumnAlias() + " > 0 then 1 else 0 end)"), "clinicalCases"));
		vQuery.FROM("(" + innerQuery.getSQL() + ")", "innerQuery");
		//MdssLog.debug(vQuery.getSQL());
		
		for (ValueObject valueObject : vQuery.getIterator()) {
			counts[POSITIVE_COUNT_INDEX] += this.getValue(valueObject, "positiveCases");
			counts[NEGATIVE_COUNT_INDEX] += this.getValue(valueObject, "negativeCases");
			counts[CLINICAL_COUNT_INDEX] += this.getValue(valueObject, "clinicalCases");
			//MdssLog.debug(sumPositiveCases + "\t" + sumNegativeCases + "\t" + sumClinicalCases);
		}
		
		return counts;
	}
	
	@Transaction
	protected double getAggregatedCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		ValueQuery valueQuery = new ValueQuery(factory);
		AggregatedCaseQuery caseQuery = new AggregatedCaseQuery(factory);

		// MdssLog.debug("From: " + initialWeek.getStartDate() + "\n  To: " + finalWeek.getEndDate());
		valueQuery.SELECT(F.SUM(caseQuery.getPositiveCases(), "positiveCases"));
		valueQuery.SELECT(F.SUM(caseQuery.getNegativeCases(), "negativeCases"));
		valueQuery.SELECT(F.SUM(caseQuery.getCases(), "clinicalCases"));
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
			sumPositiveCases += this.getValue(valueObject, "positiveCases");
			sumNegativeCases += this.getValue(valueObject, "negativeCases");
			sumClinicalCases += this.getValue(valueObject, "clinicalCases");
		}
		
		double ratio = 1.0d;
		
		if (sumPositiveCases + sumNegativeCases > 0) {
			ratio = ((double) (sumPositiveCases)) / ((double) (sumPositiveCases + sumNegativeCases));
		}
		
		return (double) sumPositiveCases + ((double) sumClinicalCases * ratio);
	}

	protected void setThresholdValues(WeeklyThreshold weeklyThreshold, double t1, double t2) {
		if (t1 <= 0) {
			weeklyThreshold.setNotification(null);
		} else {
			weeklyThreshold.setNotification(t1);
		}
		
		if (t2 <= 0) {
			weeklyThreshold.setIdentification(null);
		} else { 
			weeklyThreshold.setIdentification(t2);
		}
	}
}
