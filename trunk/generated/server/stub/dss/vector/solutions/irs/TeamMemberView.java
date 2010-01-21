package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.AttributeChar;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;
import com.terraframe.mojo.query.SelectablePrimitive;
import com.terraframe.mojo.query.ValueQuery;

import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.query.QueryBuilder;

public class TeamMemberView extends TeamMemberViewBase implements Reloadable
{
  private static final long serialVersionUID = 1241483300982L;

  public TeamMemberView()
  {
    super();
  }

  @Override
  public String toString()
  {
    return getFirstName() + " " + getLastName() + " " + getMemberId();
  }

  public static TeamMemberView[] getAllOperators()
  {
    TeamMemberQuery query = new TeamMemberQuery(new QueryFactory());
    query.WHERE(query.getIsSprayOperator().EQ(true));

    return getViewsFromQuery(query);
  }

  public static TeamMemberView[] getAllOperatorsForLocation(java.lang.String geoId)
  {
    QueryFactory queryFactory = new QueryFactory();

    SprayTeamQuery sprayTeamQuery = new SprayTeamQuery(queryFactory);
    sprayTeamQuery.WHERE(sprayTeamQuery.getSprayZone().getGeoId().EQ(geoId));

    InTeamQuery inTeamQuery = new InTeamQuery(queryFactory);
    inTeamQuery.WHERE(inTeamQuery.hasParent(sprayTeamQuery));

    TeamMemberQuery sprayOperatorQuery = new TeamMemberQuery(queryFactory);
    sprayOperatorQuery.WHERE(AND.get(sprayOperatorQuery.sprayTeam(inTeamQuery), sprayOperatorQuery.getIsSprayOperator().EQ(true)));

    return getViewsFromQuery(sprayOperatorQuery);
  }

  public static TeamMemberView[] getAllOperatorsForTeam(dss.vector.solutions.irs.SprayTeam sprayTeam)
  {
    QueryFactory queryFactory = new QueryFactory();

    SprayTeamQuery sprayTeamQuery = new SprayTeamQuery(queryFactory);
    sprayTeamQuery.WHERE(sprayTeamQuery.getId().EQ(sprayTeam.getId()));

    InTeamQuery inTeamQuery = new InTeamQuery(queryFactory);
    inTeamQuery.WHERE(inTeamQuery.hasParent(sprayTeamQuery));

    TeamMemberQuery sprayOperatorQuery = new TeamMemberQuery(queryFactory);
    sprayOperatorQuery.WHERE(AND.get(sprayOperatorQuery.sprayTeam(inTeamQuery), sprayOperatorQuery.getIsSprayOperator().EQ(true)));

    return getViewsFromQuery(sprayOperatorQuery);
  }

  @SuppressWarnings("unchecked")
  public static TeamMemberView[] getMemberViews(SprayTeam sprayTeam)
  {
    OIterator<? extends TeamMember> leaders = sprayTeam.getAllTeamLeader();
    OIterator<? extends TeamMember> members = sprayTeam.getAllSprayTeamMembers();

    try
    {
      Set<TeamMemberView> views = new TreeSet<TeamMemberView>(new TeamMemberViewComparator());

      List<TeamMember> list = (List<TeamMember>) leaders.getAll();
      list.addAll(members.getAll());

      for (TeamMember member : list)
      {
        TeamMemberView view = member.getView();

        if (!views.contains(view))
        {
          views.add(view);
        }
      }

      return views.toArray(new TeamMemberView[views.size()]);
    }
    finally
    {
      leaders.close();
      members.close();
    }
  }

  private static TeamMemberView[] getViewsFromQuery(TeamMemberQuery query)
  {
    List<TeamMemberView> list = new LinkedList<TeamMemberView>();

    OIterator<? extends TeamMember> iterator = query.getIterator();

    try
    {
      while (iterator.hasNext())
      {
        list.add(iterator.next().getView());
      }
    }
    finally
    {
      iterator.close();
    }

    return list.toArray(new TeamMemberView[list.size()]);
  }

  public static ValueQuery getOtherOperators(String value, String teamId)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = factory.valueQuery();

    PersonQuery personQuery = new PersonQuery(valueQuery);
    TeamMemberQuery leaderQuery = new TeamMemberQuery(valueQuery);
    SprayTeamQuery teamQuery = new SprayTeamQuery(valueQuery);

    AttributeChar orderBy = personQuery.getFirstName(TeamMemberView.FIRSTNAME);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { leaderQuery.getId(TeamMemberView.ID), leaderQuery.getMemberId(TeamMemberView.MEMBERID), orderBy, personQuery.getLastName(TeamMemberView.LASTNAME) };

    // IMPORTANT: The conditions teamQuery.getId().NE(teamId) and
    // leaderQuery.sprayTeam(teamQuery) only work with the assumption
    // that a team with the id = teamId exists and that team members
    // can only be assigned to a single team at a time.  If either of 
    // these conditions are not true then a cross product will result
    // where invalid data is returned.
    Condition[] conditions = new Condition[] { teamQuery.getId().NE(teamId), leaderQuery.getIsSprayOperator().EQ(true), leaderQuery.sprayTeam(teamQuery), personQuery.getTeamMemberDelegate().EQ(leaderQuery) };

    if (value != null && !value.equals(""))
    {
      String[] array = value.split(" ");
      QueryBuilder.textLookup(valueQuery, factory, array, selectables, conditions);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, orderBy, selectables, conditions);
    }

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }

  public static ValueQuery getUnassignedOperators(String value)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = factory.valueQuery();

    PersonQuery personQuery = new PersonQuery(valueQuery);
    TeamMemberQuery leaderQuery = new TeamMemberQuery(valueQuery);
    InTeamQuery inTeamQuery = new InTeamQuery(valueQuery);

    AttributeChar orderBy = personQuery.getFirstName(TeamMemberView.FIRSTNAME);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { leaderQuery.getId(TeamMemberView.ID), leaderQuery.getMemberId(TeamMemberView.MEMBERID), orderBy, personQuery.getLastName(TeamMemberView.LASTNAME) };

    Condition[] conditions = new Condition[] { leaderQuery.getIsSprayOperator().EQ(true), leaderQuery.getId().SUBSELECT_NOT_IN(inTeamQuery.childId()), personQuery.getTeamMemberDelegate().EQ(leaderQuery) };

    if (value != null && !value.equals(""))
    {
      String[] array = value.split(" ");
      QueryBuilder.textLookup(valueQuery, factory, array, selectables, conditions);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, orderBy, selectables, conditions);
    }

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }

  public static ValueQuery searchOperators(String value)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = factory.valueQuery();

    PersonQuery personQuery = new PersonQuery(valueQuery);
    TeamMemberQuery leaderQuery = new TeamMemberQuery(valueQuery);

    AttributeChar orderBy = personQuery.getFirstName(TeamMemberView.FIRSTNAME);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { leaderQuery.getId(TeamMemberView.ID), leaderQuery.getMemberId(TeamMemberView.MEMBERID), orderBy, personQuery.getLastName(TeamMemberView.LASTNAME) };

    Condition[] conditions = new Condition[] { leaderQuery.getIsSprayOperator().EQ(true), personQuery.getTeamMemberDelegate().EQ(leaderQuery) };

    if (value != null && !value.equals(""))
    {
      String[] array = value.split(" ");
      QueryBuilder.textLookup(valueQuery, factory, array, selectables, conditions);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, orderBy, selectables, conditions);
    }

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }
}
