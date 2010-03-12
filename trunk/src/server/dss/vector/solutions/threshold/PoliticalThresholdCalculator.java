package dss.vector.solutions.threshold;


import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.general.WeeklyThreshold;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.intervention.monitor.IndividualCaseQuery;

public class PoliticalThresholdCalculator extends ThresholdCalculator implements com.terraframe.mojo.generation.loader.Reloadable {

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
	protected long getIndividualCount(QueryFactory factory, GeoEntityQuery entityQuery, Date initialDate, Date finalDate) {
		IndividualCaseQuery query = new IndividualCaseQuery(factory);

		Condition condition = query.getProbableSource().EQ(entityQuery);
		condition = AND.get(condition, query.getDiagnosisDate().GE(initialDate));
		condition = AND.get(condition, query.getDiagnosisDate().LE(finalDate));
		query.WHERE(condition);

		return query.getCount();
	}

	protected void setThresholdValues(WeeklyThreshold weeklyThreshold, long t1, long t2) {
		weeklyThreshold.setNotification((int) t1);
		weeklyThreshold.setIdentification((int) t2);
	}
}
