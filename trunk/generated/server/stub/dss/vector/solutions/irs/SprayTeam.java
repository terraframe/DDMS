package dss.vector.solutions.irs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.attributes.InvalidReferenceException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.generation.loader.Reloadable;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.Person;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.SprayZoneQuery;

public class SprayTeam extends SprayTeamBase implements Reloadable
{
  private static final long serialVersionUID = 1240342487755L;

  class OperatorCompator implements Comparator<SprayOperator>, Reloadable
  {
    public int compare(SprayOperator o1, SprayOperator o2)
    {
      return o1.getId().compareTo(o2.getId());
    }
  }

  public SprayTeam()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getTeamId();
  }

  @Override
  @Transaction
  public void create(String geoId, String leaderId, String[] operatorIds)
  {
    this.setSprayZone(geoId);
    this.apply();

    if (leaderId != null)
      this.addTeamLeader(SprayLeader.get(leaderId)).apply();

    addOperators(operatorIds);
  }

  public SprayOperator[] getTeamMembers()
  {
    Set<SprayOperator> set = new TreeSet<SprayOperator>(new OperatorCompator());

    List<? extends SprayOperator> members = this.getAllSprayTeamMembers().getAll();
    OIterator<? extends SprayLeader> leaders = this.getAllTeamLeader();

    try
    {
      while (leaders.hasNext())
      {
        SprayLeader leader = leaders.next();
        Person person = leader.getPerson();
        SprayOperator operator = person.getSprayOperatorDelegate();

        if (operator != null && members.contains(operator))
        {
          set.add(operator);
        }
      }

      set.addAll(members);

      return set.toArray(new SprayOperator[set.size()]);
    }
    finally
    {
      leaders.close();
    }
  }

  @Override
  public SprayOperatorView[] getTeamMemberViews()
  {
    SprayOperator[] operators = this.getTeamMembers();
    SprayOperatorView[] views = new SprayOperatorView[operators.length];
    
    for(int i = 0; i < operators.length; i++)
    {
      views[i] = operators[i].populateView();
    }
    
    return SprayOperatorView.getAllForTeam(this);
  }

  @Override
  @Transaction
  public void edit(String geoId, String leaderId, String[] operatorIds, String[] removedIds)
  {
    this.setSprayZone(geoId);
    this.apply();

    for (LeadTeam relationship : this.getAllTeamLeaderRel())
    {
      relationship.delete();
    }

    if (leaderId != null)
    {
      this.addTeamLeader(SprayLeader.get(leaderId)).apply();
    }

    addOperators(operatorIds);

    for (String id : removedIds)
    {
      SprayOperator assignedOperator = SprayOperator.get(id);
      for (InTeam oldTeam : assignedOperator.getAllSprayTeamRel())
      {
        oldTeam.delete();
      }
    }
  }

  private void addOperators(String[] operatorIds)
  {
    for (String id : operatorIds)
    {
      SprayOperator assignedOperator = SprayOperator.get(id);
      for (InTeam oldTeam : assignedOperator.getAllSprayTeamRel())
      {
        oldTeam.delete();
      }
      this.addSprayTeamMembers(assignedOperator).apply();
    }
  }

  private void setSprayZone(String geoId)
  {
    QueryFactory queryFactory = new QueryFactory();
    SprayZoneQuery sprayZoneQuery = new SprayZoneQuery(queryFactory);
    sprayZoneQuery.WHERE(sprayZoneQuery.getGeoId().EQ(geoId));
    OIterator<? extends SprayZone> zoneIterator = sprayZoneQuery.getIterator();
    if (zoneIterator.hasNext())
    {
      this.setSprayZone(zoneIterator.next());
      zoneIterator.close();
    }
    else
    {
      // No results = the geoId is invalid.
      throw new InvalidReferenceException("[" + geoId + "] is not a valid Spray Zone GeoId",
          (MdAttributeReferenceDAOIF) SprayTeam.getSprayZoneMd());
    }
  }

  public static SprayTeam[] search(GeoEntity geoEntity)
  {
    List<SprayTeam> list = new LinkedList<SprayTeam>();
    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());

    query.WHERE(query.getSprayZone().EQ(geoEntity));
    query.ORDER_BY_ASC(query.getTeamId());
    OIterator<? extends SprayTeam> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }

      return list.toArray(new SprayTeam[list.size()]);
    }
    finally
    {
      it.close();
    }
  }

  public String toString()
  {
    return ResourceTarget.getTargeterName(this);
  }

  @Transaction
  public static SprayTeam newTeam(String geoId, String leaderId, String[] availableIds,
      String[] assignedIds)
  {
    SprayTeam sprayTeam = new SprayTeam();

    QueryFactory queryFactory = new QueryFactory();
    SprayZoneQuery sprayZoneQuery = new SprayZoneQuery(queryFactory);
    sprayZoneQuery.WHERE(sprayZoneQuery.getGeoId().EQ(geoId));
    OIterator<? extends SprayZone> zoneIterator = sprayZoneQuery.getIterator();
    if (zoneIterator.hasNext())
    {
      sprayTeam.setSprayZone(zoneIterator.next());
      zoneIterator.close();
    }
    else
    {
      // No results = the geoId is invalid.
      throw new InvalidReferenceException("[" + geoId + "] is not a valid Spray Zone GeoId",
          (MdAttributeReferenceDAOIF) SprayTeam.getSprayZoneMd());
    }
    sprayTeam.apply();

    sprayTeam.addTeamLeader(SprayLeader.get(leaderId)).apply();

    for (String id : availableIds)
    {
      sprayTeam.addSprayTeamMembers(SprayOperator.get(id)).apply();
    }

    for (String id : assignedIds)
    {
      SprayOperator assignedOperator = SprayOperator.get(id);
      for (InTeam oldTeam : assignedOperator.getAllSprayTeamRel())
      {
        oldTeam.delete();
      }
      sprayTeam.addSprayTeamMembers(assignedOperator).apply();
    }

    return sprayTeam;
  }

  public static SprayTeam[] findByLocation(String geoId)
  {
    List<SprayTeam> list = new LinkedList<SprayTeam>();
    GeoEntity location = GeoEntity.searchByGeoId(geoId);

    List<GeoEntity> parents = location.getPrunedParents(Arrays.asList(new String[] { SprayZone.CLASS }));
    List<GeoEntity> children = location.getPrunedChildren(Arrays
        .asList(new String[] { SprayZone.CLASS }));
    List<GeoEntity> geoEntities = new LinkedList<GeoEntity>();

    geoEntities.addAll(parents);
    geoEntities.addAll(children);

    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());
    query.WHERE(query.getSprayZone().EQ(location));

    for (GeoEntity geoEntity : geoEntities)
    {
      query.OR(query.getSprayZone().EQ(geoEntity));
    }

    query.ORDER_BY_ASC(query.getTeamId());

    OIterator<? extends SprayTeam> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next());
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new SprayTeam[list.size()]);
  }

  public static SprayTeam getByTeamId(String teamId)
  {
    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());
    query.WHERE(query.getTeamId().EQ(teamId));
    OIterator<? extends SprayTeam> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }

  }
}
