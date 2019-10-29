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
package dss.vector.solutions.irs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.attributes.InvalidReferenceException;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.SprayZoneQuery;

public class SprayTeam extends SprayTeamBase implements Reloadable
{
  private static final long serialVersionUID = 1240342487755L;

  class OperatorCompator implements Comparator<TeamMember>, Reloadable
  {
    public int compare(TeamMember o1, TeamMember o2)
    {
      return o1.getId().compareTo(o2.getId());
    }
  }

  public SprayTeam()
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
    else
    {
      return ResourceTarget.getTargeterName(this);
    }
  }

  @Override
  protected String buildKey()
  {
    // This prefix was added so that spray team names do not conflict with team member names (ticket 3146)
    String sprayTeamPrefix = "ST�";
    
    return sprayTeamPrefix + this.getTeamId();
  }

  @Override
  @Transaction
  public void create(String geoId, String leaderId, String[] operatorIds)
  {
    this.setSprayZone(geoId);
    this.apply();

    if (leaderId != null)
    {
      this.addTeamLeader(TeamMember.getSprayLeader(leaderId)).apply();
    }

    addOperators(operatorIds);
  }

  public TeamMember[] getTeamMembers()
  {
    Set<TeamMember> set = new TreeSet<TeamMember>(new OperatorCompator());

    OIterator<? extends TeamMember> leaders = this.getAllTeamLeader();
    OIterator<? extends TeamMember> members = this.getAllSprayTeamMembers();

    try
    {
      set.addAll(leaders.getAll());
      set.addAll(members.getAll());

      return set.toArray(new TeamMember[set.size()]);
    }
    finally
    {      
      leaders.close();
      members.close();
    }
  }

  @Override
  public TeamMemberView[] getTeamMemberViews()
  {
    return TeamMemberView.getMemberViews(this);
  }
  
  @Override
  public TeamMemberView[] getOperatorViews()
  {
    return TeamMemberView.getAllOperatorsForTeam(this);
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
      this.addTeamLeader(TeamMember.getSprayLeader(leaderId)).apply();
    }

    addOperators(operatorIds);

    for (String id : removedIds)
    {
      TeamMember assignedOperator = TeamMember.get(id);
      
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
      TeamMember assignedOperator = TeamMember.get(id);
      
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
      throw new InvalidReferenceException("[" + geoId + "] is not a valid Spray Zone GeoId", (MdAttributeReferenceDAOIF) SprayTeam.getSprayZoneMd());
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

  @Transaction
  public static SprayTeam newTeam(String geoId, String leaderId, String[] availableIds, String[] assignedIds)
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
      throw new InvalidReferenceException("[" + geoId + "] is not a valid Spray Zone GeoId", (MdAttributeReferenceDAOIF) SprayTeam.getSprayZoneMd());
    }
    sprayTeam.apply();

    sprayTeam.addTeamLeader(TeamMember.getSprayLeader(leaderId)).apply();

    for (String id : availableIds)
    {
      sprayTeam.addSprayTeamMembers(TeamMember.getSprayOperator(id)).apply();
    }

    for (String id : assignedIds)
    {
      TeamMember assignedOperator = TeamMember.getSprayOperator(id);
      
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
    List<GeoEntity> children = location.getPrunedChildren(Arrays.asList(new String[] { SprayZone.CLASS }));
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

  public String getLabel()
  {
    OIterator<? extends TeamMember> it = this.getAllTeamLeader();

    try
    {
      if (it.hasNext())
      {
        TeamMember leader = it.next();
        String leaderName = leader.getPerson().getFirstName() + " " + leader.getPerson().getLastName();

        return this.getTeamId() + " - " + leaderName;
      }

      return this.getTeamId();
    }
    finally
    {
      it.close();
    }
  }
}
