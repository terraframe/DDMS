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
package dss.vector.solutions.threshold;

import java.util.Date;

import com.runwaysdk.dataaccess.ValueObject;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.F;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.HealthFacilityQuery;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;
import dss.vector.solutions.surveillance.AggregatedCaseQuery;

public class FacilityThresholdCalculator extends ThresholdCalculator implements com.runwaysdk.generation.loader.Reloadable {

	@Transaction
	protected GeoEntityQuery getEntityQuery(QueryFactory factory) {
		return new HealthFacilityQuery(factory);
	}
	
	@Transaction
	protected GeoEntityQuery getRelatedEntitiesQuery(GeoEntity geoEntity, QueryFactory factory) {
		HealthFacilityQuery entityQuery = new HealthFacilityQuery(factory);
		entityQuery.WHERE(entityQuery.getGeoId().EQ(geoEntity.getGeoId()));
		return entityQuery;
	}

	@Transaction
	protected double getIndividualCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		IndividualInstanceQuery query = new IndividualInstanceQuery(factory);
		
		query.WHERE(query.getIndividualCase().getDisease().EQ(Disease.getCurrent()));
		query.AND(query.getHealthFacility().EQ(entityQuery));
		query.AND(query.getFacilityVisit().GE(initialDate));
		query.AND(query.getFacilityVisit().LE(finalDate));
		query.AND(query.getActivelyDetected().EQ(false));

		return (double) query.getCount();
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
		
		return (double) (sumPositiveCases + sumClinicalCases + sumNegativeCases);
	}
	
	protected void setThresholdValues(WeeklyThreshold weeklyThreshold, double t1, double t2) {
		if (t1 <= 0) {
			weeklyThreshold.setFacilityNotification(null);
		} else {
			weeklyThreshold.setFacilityNotification(t1);
		}
		
		if (t2 <= 0) {
			weeklyThreshold.setFacilityIdentification(null);
		} else {
			weeklyThreshold.setFacilityIdentification(t2);
		}
	}
}
