package dss.vector.solutions.intervention.monitor;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.query.GeneratedEntityQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.Person;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.query.Layer;
import dss.vector.solutions.util.QueryUtil;

public class Larvacide extends LarvacideBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1257372022458L;

  public Larvacide()
  {
    super();
  }


	@Override
	public void apply() {
		if (this.isNew() && this.getDisease() == null) {
			this.setDisease(Disease.getCurrent());
		}

		super.apply();
	}
	
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    
    return this.getClassDisplayLabel();
  }

  @Override
  public LarvacideInstanceView[] getInstanceViews()
  {
    List<? extends LarvacideInstance> instances = this.getAllInstances().getAll();
    LarvacideInstanceView[] views = new LarvacideInstanceView[instances.size()];
    int i = 0;

    for (LarvacideInstance instance : instances)
    {
      views[i++] = instance.getView();
    }

    return views;
  }

  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
    JSONObject queryConfig;
    try
    {
      queryConfig = new JSONObject(config);
    }
    catch (JSONException e1)
    {
      throw new ProgrammingErrorException(e1);
    }

    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);

    // IMPORTANT: Required call for all query screens.
    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);

    LarvacideQuery larvacideQuery = (LarvacideQuery) queryMap.get(Larvacide.CLASS);
    LarvacideInstanceQuery larvacideInstanceQuery = (LarvacideInstanceQuery) queryMap.get(LarvacideInstance.CLASS);
    dss.vector.solutions.PersonQuery personQuery = (dss.vector.solutions.PersonQuery) queryMap.get(dss.vector.solutions.Person.CLASS);

    LarvacideAssociationQuery larvacideAssQuery = new LarvacideAssociationQuery(queryFactory);

    valueQuery.WHERE(larvacideAssQuery.parentId().EQ(larvacideQuery.getId()));

    if (larvacideInstanceQuery != null)
    {
      valueQuery.WHERE(larvacideAssQuery.childId().EQ(larvacideInstanceQuery.getId()));
      if (personQuery != null)
      {
        Pattern pattern = Pattern.compile("(" + Person.FIRSTNAME + "|" + Person.LASTNAME + ")[a-zA-z_]+Criteria");
        Matcher matcher = pattern.matcher(config);
        
        // if there is no restriction on person, we left join, otherwise we inner join
        if (!matcher.find())
        {
          valueQuery.WHERE(larvacideQuery.getTeamLeader(Larvacide.TEAMLEADER).LEFT_JOIN_EQ((SelectableSingle) personQuery.getTeamMemberDelegate(Person.TEAMMEMBERDELEGATE)));
        }
        else
        {
          valueQuery.WHERE(larvacideQuery.getTeamLeader(Larvacide.TEAMLEADER).EQ(personQuery.getTeamMemberDelegate(Person.TEAMMEMBERDELEGATE)));
        }
      }

    }

    // QueryUtil.joinTermAllpaths(valueQuery,dss.vector.solutions.Person.CLASS,personQuery);

    QueryUtil.joinGeoDisplayLabels(valueQuery, Larvacide.CLASS, larvacideQuery);

    QueryUtil.joinTermAllpaths(valueQuery, LarvacideInstance.CLASS, larvacideInstanceQuery);

    QueryUtil.setTermRestrictions(valueQuery, queryMap);

    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);

    return QueryUtil.setQueryDates(xml, valueQuery, larvacideQuery, larvacideQuery.getStartDate());
  }
}
