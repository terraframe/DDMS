package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;

import com.terraframe.mojo.dataaccess.MdAttributeReferenceDAOIF;
import com.terraframe.mojo.dataaccess.attributes.InvalidReferenceException;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.SprayZone;
import dss.vector.solutions.geo.generated.SprayZoneQuery;

public class SprayTeam extends SprayTeamBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240342487755L;

  public SprayTeam()
  {
    super();
  }

  @Override
  @Transaction
  public void create(String geoId, String leaderId, String[] operatorIds)
  {
    this.setSprayZone(geoId);
    this.apply();

    if (leaderId!=null)
      this.addTeamLeader(SprayLeader.get(leaderId)).apply();

    addOperators(operatorIds);
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

    if (leaderId!=null)
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
      throw new InvalidReferenceException("[" + geoId + "] is not a valid Spray Zone GeoId", (MdAttributeReferenceDAOIF)SprayTeam.getSprayZoneMd());
    }
  }

  public static SprayTeam[] search(GeoEntity geoEntity)
  {
    List<SprayTeam> list = new LinkedList<SprayTeam>();
    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());

    query.WHERE(query.getSprayZone().EQ(geoEntity.getGeoId()));
    query.ORDER_BY_ASC(query.getTeamId());
    OIterator<? extends SprayTeam> it = query.getIterator();

    try
    {
      while(it.hasNext())
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
      throw new InvalidReferenceException("[" + geoId + "] is not a valid Spray Zone GeoId", (MdAttributeReferenceDAOIF)SprayTeam.getSprayZoneMd());
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
}
