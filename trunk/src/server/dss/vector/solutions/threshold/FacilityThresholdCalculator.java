package dss.vector.solutions.threshold;

import java.util.Date;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.HealthFacilityQuery;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;

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
	protected long getIndividualCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		IndividualInstanceQuery query = new IndividualInstanceQuery(factory);

		Condition condition = query.getHealthFacility().EQ(entityQuery);
		condition = AND.get(condition, query.getFacilityVisit().GE(initialDate));
		condition = AND.get(condition, query.getFacilityVisit().LE(finalDate));
		condition = AND.get(condition, query.getActivelyDetected().EQ(false));
		query.WHERE(condition);

		return query.getCount();
	}
	
	protected void setThresholdValues(WeeklyThreshold weeklyThreshold, long t1, long t2) {
		if (t1 <= 0) {
			weeklyThreshold.setFacilityNotification(null);
		} else {
			weeklyThreshold.setFacilityNotification((int) t1);
		}
		
		if (t2 <= 0) {
			weeklyThreshold.setFacilityIdentification(null);
		} else {
			weeklyThreshold.setFacilityIdentification((int) t2);
		}
	}
}
