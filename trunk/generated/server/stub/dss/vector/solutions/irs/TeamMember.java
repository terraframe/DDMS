package dss.vector.solutions.irs;

import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;

import dss.vector.solutions.LocalProperty;
import dss.vector.solutions.Person;
import dss.vector.solutions.PersonQuery;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.query.QueryBuilder;

public class TeamMember extends TeamMemberBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -107961923;

  public TeamMember()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    return this.getMemberId();
  }

  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    else
    {
      return this.getClassDisplayLabel();
    }
  }
  
  @Override
  public void apply()
  {
    if(this.getMemberId() == null || this.getMemberId().equals(""))
    {
      this.setMemberId(LocalProperty.getNextId());
    }
    
    super.apply();
  }

  public String getLabel()
  {
    Person person = this.getPerson();

    return person.getFirstName() + " " + person.getLastName() + " - " + this.getMemberId();
  }

  @Override
  public TeamMemberView getView()
  {
    Person person = this.getPerson();

    TeamMemberView view = new TeamMemberView();
    view.setActorId(this.getId());
    view.setMemberId(this.getMemberId());
    view.setFirstName(person.getFirstName());
    view.setLastName(person.getLastName());
    view.setIsSprayLeader(this.getIsSprayLeader());
    view.setIsSprayOperator(this.getIsSprayOperator());
    
    OIterator<? extends SprayTeam> sprayTeam = this.getAllSprayTeam();
    if (sprayTeam.hasNext())
    {
      view.setIsAssigned(true);
      view.setTeamId(sprayTeam.next().getTeamId());
    }
    else
    {
      view.setIsAssigned(false);
    }

    return view;
  }

  public static TeamMember getSprayLeaderById(String id)
  {
    TeamMemberQuery leaderQuery = new TeamMemberQuery(new QueryFactory());

    leaderQuery.WHERE(AND.get(leaderQuery.getMemberId().EQ(id), leaderQuery.getIsSprayLeader().EQ(true)));

    OIterator<? extends TeamMember> leaderIterator = leaderQuery.getIterator();

    try
    {
      if (leaderIterator.hasNext())
      {
        return leaderIterator.next();
      }
      InvalidLeaderIDException e = new InvalidLeaderIDException();
      e.setLeaderId(id);
      throw e;
    }
    finally
    {
      leaderIterator.close();
    }
  }

  public static TeamMember getOperatorById(String operatorId)
  {
    TeamMemberQuery query = new TeamMemberQuery(new QueryFactory());
    query.WHERE(AND.get(query.getMemberId().EQ(operatorId), query.getIsSprayOperator().EQ(true)));

    OIterator<? extends TeamMember> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      InvalidOperatorIDException e = new InvalidOperatorIDException();
      e.setOperatorId(operatorId);
      throw e;
    }
    finally
    {
      iterator.close();
    }
  }

  public static TeamMember getSprayOperator(String id)
  {
    TeamMember member = TeamMember.get(id);

    if (member.getIsSprayOperator())
    {
      return member;
    }

    return null;
  }

  public static TeamMember getSprayLeader(String id)
  {
    TeamMember member = TeamMember.get(id);

    if (member.getIsSprayLeader())
    {
      return member;
    }

    return null;
  }

  public static TeamMember getMemberById(String operatorId)
  {
    TeamMemberQuery query = new TeamMemberQuery(new QueryFactory());
    query.WHERE(query.getMemberId().EQ(operatorId));

    OIterator<? extends TeamMember> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      InvalidMemberIDException e = new InvalidMemberIDException();
      e.setMemberId(operatorId);
      throw e;
    }
    finally
    {
      iterator.close();
    }
  }

  public static int getAvailableOperators(GeoEntity location)
  {
    QueryFactory factory = new QueryFactory();

    GeoEntityQuery parentSprayZones = location.getPrunedParents(factory, SprayZone.CLASS);
    GeoEntityQuery childSprayZones = location.getPrunedChildren(factory, SprayZone.CLASS);

    SprayTeamQuery teamQuery = new SprayTeamQuery(factory);
    teamQuery.WHERE(teamQuery.getSprayZone().EQ(location));
    teamQuery.OR(teamQuery.getSprayZone().EQ(parentSprayZones));
    teamQuery.OR(teamQuery.getSprayZone().EQ(childSprayZones));

    TeamMemberQuery operatorQuery = new TeamMemberQuery(factory);
    operatorQuery.WHERE(AND.get(operatorQuery.sprayTeam(teamQuery), operatorQuery.getIsSprayOperator().EQ(true)));

    return (int) operatorQuery.getCount();
  }

  public static ValueQuery searchForOperators(String search)
  {
    QueryFactory f = new QueryFactory();

    PersonQuery personQuery = new PersonQuery(f);
    TeamMemberQuery operatorQuery = new TeamMemberQuery(f);
    ValueQuery valueQuery = new ValueQuery(f);

    Selectable[] selectables = new Selectable[] { operatorQuery.getId(TeamMember.ID), operatorQuery.getMemberId(TeamMember.MEMBERID), personQuery.getFirstName(Person.FIRSTNAME), personQuery.getLastName(Person.LASTNAME), };

    valueQuery.SELECT(selectables);

    String statement = "%" + search + "%";

    // Search conditions
    Condition or = OR.get(operatorQuery.getMemberId().LIKEi(statement), personQuery.getFirstName().LIKEi(statement), personQuery.getLastName().LIKEi(statement));

    // The person must be a spray operator AND not in team
    Condition and = AND.get(personQuery.getTeamMemberDelegate().EQ(operatorQuery), or);

    valueQuery.WHERE(and);

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

  public static ValueQuery searchForLeader(String search)
  {
    QueryFactory factory = new QueryFactory();
    ValueQuery valueQuery = factory.valueQuery();

    PersonQuery personQuery = new PersonQuery(valueQuery);
    TeamMemberQuery leaderQuery = new TeamMemberQuery(valueQuery);

    SelectableChar orderBy = personQuery.getFirstName(TeamMemberView.FIRSTNAME);    
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { leaderQuery.getId(TeamMemberView.ID), leaderQuery.getMemberId(TeamMemberView.MEMBERID), orderBy, personQuery.getLastName(TeamMemberView.LASTNAME) };

    Condition[] conditions = new Condition[] { leaderQuery.getIsSprayLeader().EQ(true), personQuery.getTeamMemberDelegate().EQ(leaderQuery) };

    if (search != null && !search.equals(""))
    {
      String[] array = search.split(" ");
      QueryBuilder.textLookup(valueQuery, factory, array, selectables, selectables, conditions);
    }
    else
    {
      QueryBuilder.orderedLookup(valueQuery, factory, orderBy, selectables, conditions);
    }

    valueQuery.restrictRows(15, 1);

    return valueQuery;
  }
}
