package dss.vector.solutions.querybuilder;

import java.util.Map;

import org.json.JSONObject;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.GeneratedTableClassQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.intervention.monitor.Larvacide;
import dss.vector.solutions.intervention.monitor.LarvacideInstance;
import dss.vector.solutions.intervention.monitor.LarvacideInstanceQuery;
import dss.vector.solutions.intervention.monitor.LarvacideQuery;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class LarvacideQB extends AbstractQB implements Reloadable
{

  public LarvacideQB(String xml, String config, Layer layer, Integer pageNumber, Integer pageSize, Disease disease)
  {
    super(xml, config, layer, pageNumber, pageNumber, disease);
  }
  
  @Override
  protected String getAuditClassAlias()
  {
    return Larvacide.CLASS;
  }

  @Override
  protected ValueQuery construct(QueryFactory queryFactory, ValueQuery valueQuery,
      Map<String, GeneratedTableClassQuery> queryMap, String xml, JSONObject queryConfig)
  {
    LarvacideQuery larvacideQuery = (LarvacideQuery) queryMap.get(Larvacide.CLASS);
    LarvacideInstanceQuery larvacideInstanceQuery = (LarvacideInstanceQuery) queryMap.get(LarvacideInstance.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    
    if (larvacideInstanceQuery != null)
    {

      valueQuery.WHERE(larvacideQuery.instances(larvacideInstanceQuery));
      // FIXME left join if possible?
//      LarvacideAssociationQuery larvacideAssQuery = new LarvacideAssociationQuery(valueQuery);
//      valueQuery.WHERE(larvacideQuery.LEFT_JOIN_EQ(larvacideAssQuery.parentId()));
//      valueQuery.WHERE(larvacideAssQuery.childId().LEFT_JOIN_EQ(larvacideInstanceQuery.getId())); 

      QueryUtil.joinTermAllpaths(valueQuery, LarvacideInstance.CLASS, larvacideInstanceQuery, this.getTermRestrictions(), this.getLayer());
    }
    
    if (personQuery != null)
    {
      valueQuery.WHERE(larvacideQuery.getTeamLeader().LEFT_JOIN_EQ(personQuery.getTeamMemberDelegate()));
    }
    
    this.addGeoDisplayLabelQuery(larvacideQuery);

    this.setNumericRestrictions(valueQuery, queryConfig);

    return QueryUtil.setQueryDates(xml, valueQuery, larvacideQuery, larvacideQuery.getStartDate(), larvacideQuery.getDisease());
  }

}
