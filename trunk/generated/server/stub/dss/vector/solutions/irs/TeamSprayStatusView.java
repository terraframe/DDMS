package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.BasicCondition;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.generated.GeoEntity;

public class TeamSprayStatusView extends TeamSprayStatusViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860692429L;

  public TeamSprayStatusView()
  {
    super();
  }

  public void populateView(TeamSprayStatus concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setSpray(concrete.getSpray());
    this.setHouseholds(concrete.getHouseholds());
    this.setStructures(concrete.getStructures());
    this.setSprayedHouseholds(concrete.getSprayedHouseholds());
    this.setSprayedStructures(concrete.getSprayedStructures());
    this.setPrevSprayedHouseholds(concrete.getPrevSprayedHouseholds());
    this.setPrevSprayedStructures(concrete.getPrevSprayedStructures());
    this.setSprayTeam(concrete.getSprayTeam());
    this.setTeamLeader(concrete.getTeamLeader());
    this.setTarget(concrete.getTarget());
    this.setRooms(concrete.getRooms());
    this.setVerandas(concrete.getVerandas());
    this.setCattleSheds(concrete.getCattleSheds());
    this.setSprayedRooms(concrete.getSprayedRooms());
    this.setVerandasSprayed(concrete.getVerandasSprayed());
    this.setCattleShedsSprayed(concrete.getCattleShedsSprayed());
    this.setPeople(concrete.getPeople());
    this.setNumberOfPeople(concrete.getNumberOfPeople());
    this.setBedNets(concrete.getBedNets());
    this.setRoomsWithBedNets(concrete.getRoomsWithBedNets());
    this.setLocked(concrete.getLocked());
    this.setVerandasLocked(concrete.getVerandasLocked());
    this.setCattleShedsLocked(concrete.getCattleShedsLocked());
    this.setOther(concrete.getOther());
    this.setVerandasOther(concrete.getVerandasOther());
    this.setCattleShedsOther(concrete.getCattleShedsOther());    
    this.setRefused(concrete.getRefused());
    this.setVerandasRefused(concrete.getVerandasRefused());
    this.setCattleShedsRefused(concrete.getCattleShedsRefused());
    this.setWrongSurface(concrete.getWrongSurface());
    this.setNozzlesUsed(concrete.getNozzlesUsed());
    this.setPumpsUsed(concrete.getPumpsUsed());
        
    if (concrete.getSprayTeam() != null)
    {
      this.setTeamLabel(concrete.getSprayTeam().getTeamId());
    }
    else
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, SPRAYTEAM);
      p.apply();

      p.throwIt();
    }
    this.setReceived(concrete.getReceived());
    this.setRefills(concrete.getRefills());
    this.setReturned(concrete.getReturned());
    this.setUsed(concrete.getUsed());
  }

  protected void populateConcrete(TeamSprayStatus concrete)
  {
    concrete.setSpray(this.getSpray());
    concrete.setHouseholds(this.getHouseholds());
    concrete.setStructures(this.getStructures());
    concrete.setSprayedHouseholds(this.getSprayedHouseholds());
    concrete.setSprayedStructures(this.getSprayedStructures());
    concrete.setPrevSprayedHouseholds(this.getPrevSprayedHouseholds());
    concrete.setPrevSprayedStructures(this.getPrevSprayedStructures());
    concrete.setSprayTeam(this.getSprayTeam());
    concrete.setTeamLeader(this.getTeamLeader());
    concrete.setTarget(this.getTarget());
    concrete.setReceived(this.getReceived());
    concrete.setRefills(this.getRefills());
    concrete.setReturned(this.getReturned());
    concrete.setUsed(this.getUsed());
    concrete.setRooms(this.getRooms());
    concrete.setVerandas(this.getVerandas());
    concrete.setCattleSheds(this.getCattleSheds());
    concrete.setSprayedRooms(this.getSprayedRooms());
    concrete.setVerandasSprayed(this.getVerandasSprayed());
    concrete.setCattleShedsSprayed(this.getCattleShedsSprayed());
    concrete.setNumberOfPeople(this.getNumberOfPeople());
    concrete.setPeople(this.getPeople());
    concrete.setBedNets(this.getBedNets());
    concrete.setRoomsWithBedNets(this.getRoomsWithBedNets());
    concrete.setLocked(this.getLocked());
    concrete.setVerandasLocked(this.getVerandasLocked());
    concrete.setCattleShedsLocked(this.getCattleShedsLocked());
    concrete.setOther(this.getOther());
    concrete.setVerandasOther(this.getVerandasOther());
    concrete.setCattleShedsOther(this.getCattleShedsOther());    
    concrete.setRefused(this.getRefused());
    concrete.setVerandasRefused(this.getVerandasRefused());
    concrete.setCattleShedsRefused(this.getCattleShedsRefused());
    concrete.setWrongSurface(this.getWrongSurface());    
    concrete.setNozzlesUsed(this.getNozzlesUsed());
    concrete.setPumpsUsed(this.getPumpsUsed());    
  }

  private void populateMapping(TeamSprayStatus concrete)
  {
    new AttributeNotificationMap(concrete, TeamSprayStatus.SPRAY, this, TeamSprayStatusView.SPRAY);
    new AttributeNotificationMap(concrete, TeamSprayStatus.HOUSEHOLDS, this, TeamSprayStatusView.HOUSEHOLDS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.STRUCTURES, this, TeamSprayStatusView.STRUCTURES);
    new AttributeNotificationMap(concrete, TeamSprayStatus.SPRAYEDHOUSEHOLDS, this, TeamSprayStatusView.SPRAYEDHOUSEHOLDS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.SPRAYEDSTRUCTURES, this, TeamSprayStatusView.SPRAYEDSTRUCTURES);
    new AttributeNotificationMap(concrete, TeamSprayStatus.PREVSPRAYEDHOUSEHOLDS, this, TeamSprayStatusView.PREVSPRAYEDHOUSEHOLDS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.PREVSPRAYEDSTRUCTURES, this, TeamSprayStatusView.PREVSPRAYEDSTRUCTURES);
    new AttributeNotificationMap(concrete, TeamSprayStatus.SPRAYTEAM, this, TeamSprayStatusView.SPRAYTEAM);
    new AttributeNotificationMap(concrete, TeamSprayStatus.TEAMLEADER, this, TeamSprayStatusView.TEAMLEADER);
    new AttributeNotificationMap(concrete, TeamSprayStatus.TARGET, this, TeamSprayStatusView.TARGET);
    new AttributeNotificationMap(concrete, TeamSprayStatus.RECEIVED, this, TeamSprayStatusView.RECEIVED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.REFILLS, this, TeamSprayStatusView.REFILLS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.RETURNED, this, TeamSprayStatusView.RETURNED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.USED, this, TeamSprayStatusView.USED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.ROOMS, this, TeamSprayStatusView.ROOMS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.VERANDAS, this, TeamSprayStatusView.VERANDAS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.CATTLESHEDS, this, TeamSprayStatusView.CATTLESHEDS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.SPRAYEDROOMS, this, TeamSprayStatusView.SPRAYEDROOMS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.VERANDASSPRAYED, this, TeamSprayStatusView.VERANDASSPRAYED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.CATTLESHEDSSPRAYED, this, TeamSprayStatusView.CATTLESHEDSSPRAYED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.NUMBEROFPEOPLE, this, TeamSprayStatusView.NUMBEROFPEOPLE);
    new AttributeNotificationMap(concrete, TeamSprayStatus.PEOPLE, this, TeamSprayStatusView.PEOPLE);
    new AttributeNotificationMap(concrete, TeamSprayStatus.BEDNETS, this, TeamSprayStatusView.BEDNETS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.ROOMSWITHBEDNETS, this, TeamSprayStatusView.ROOMSWITHBEDNETS);
    new AttributeNotificationMap(concrete, TeamSprayStatus.LOCKED, this, TeamSprayStatusView.LOCKED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.VERANDASLOCKED, this, TeamSprayStatusView.VERANDASLOCKED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.CATTLESHEDSLOCKED, this, TeamSprayStatusView.CATTLESHEDSLOCKED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.OTHER, this, TeamSprayStatusView.OTHER);
    new AttributeNotificationMap(concrete, TeamSprayStatus.VERANDASOTHER, this, TeamSprayStatusView.VERANDASOTHER);
    new AttributeNotificationMap(concrete, TeamSprayStatus.CATTLESHEDSOTHER, this, TeamSprayStatusView.CATTLESHEDSOTHER);
    new AttributeNotificationMap(concrete, TeamSprayStatus.REFUSED, this, TeamSprayStatusView.REFUSED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.VERANDASREFUSED, this, TeamSprayStatusView.VERANDASREFUSED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.CATTLESHEDSREFUSED, this, TeamSprayStatusView.CATTLESHEDSREFUSED);
    new AttributeNotificationMap(concrete, TeamSprayStatus.WRONGSURFACE, this, TeamSprayStatusView.WRONGSURFACE);    
    new AttributeNotificationMap(concrete, TeamSprayStatus.NOZZLESUSED, this, TeamSprayStatusView.NOZZLESUSED);    
    new AttributeNotificationMap(concrete, TeamSprayStatus.PUMPSUSED, this, TeamSprayStatusView.PUMPSUSED);        
  }

  @Override
  @Transaction
  public void apply()
  {
    TeamSprayStatus concrete = new TeamSprayStatus();

    if (this.hasConcrete())
    {
      concrete = TeamSprayStatus.lock(this.getConcreteId());
    }

    this.populateMapping(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  protected boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      TeamSprayStatus.get(this.getConcreteId()).delete();
    }
  }

  @Transaction
  public static TeamSprayStatusView[] applyAll(TeamSprayStatusView[] views)
  {
    for (TeamSprayStatusView view : views)
    {
      view.apply();
    }

    return views;
  }

  public static TeamSprayStatusView[] search(InsecticideBrand brand, GeoEntity geoEntity, Date date, SprayMethod sprayMethod, SprayTeam... teams)
  {
    List<TeamSprayStatusView> list = new LinkedList<TeamSprayStatusView>();

    QueryFactory factory = new QueryFactory();

    TeamSprayQuery teamQuery = new TeamSprayQuery(factory);
    teamQuery.WHERE(teamQuery.getBrand().EQ(brand));
    teamQuery.AND(teamQuery.getGeoEntity().EQ(geoEntity));
    teamQuery.AND(teamQuery.getSprayDate().EQ(date));
    teamQuery.AND(teamQuery.getSprayMethod().containsAny(sprayMethod));
    teamQuery.AND(TeamSprayStatusView.buildOrCondition(teamQuery, teams));

    TeamSprayStatusQuery query = new TeamSprayStatusQuery(factory);
    query.WHERE(query.getSpray().EQ(teamQuery));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends TeamSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        TeamSprayStatus status = it.next();

        list.add((TeamSprayStatusView) status.getView());
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new TeamSprayStatusView[list.size()]);
  }

  private static Condition buildOrCondition(TeamSprayQuery query, SprayTeam... teams)
  {
    Condition or = null;

    for (SprayTeam team : teams)
    {
      BasicCondition condition = query.getSprayTeam().EQ(team);

      if (or == null)
      {
        or = condition;
      }
      else
      {
        or = OR.get(or, condition);
      }
    }
    return or;
  }
}
