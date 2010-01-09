package dss.vector.solutions.threshold;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.general.ThresholdCalculationType;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.HealthFacilityQuery;
import dss.vector.solutions.intervention.monitor.IndividualInstanceQuery;

public class FacilityThresholdCalculator extends ThresholdCalculator implements com.terraframe.mojo.generation.loader.Reloadable {
	
	public FacilityThresholdCalculator(ThresholdCalculationType calculationType) {
		super(calculationType);
	}
	
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
}
