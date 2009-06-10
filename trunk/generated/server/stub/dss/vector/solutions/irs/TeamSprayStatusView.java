package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class TeamSprayStatusView extends TeamSprayStatusViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860692429L;

  public TeamSprayStatusView()
  {
    super();
  }

  public void populate(SprayStatus status)
  {
    super.populate(status);

    TeamSpray s = (TeamSpray) status.getSpray();
    
    this.configureSprayTeam(s.getSprayTeam());

    this.setTeamSprayWeek(s.getTeamSprayWeek());
    this.setTarget(s.getTarget());
    this.setTeamLeader(s.getTeamLeader());
  }
  
  public void configureSprayTeam(SprayTeam team)
  {
    this.setTeamLabel(team);
    this.setSprayTeam(team);
  }

  private void setTeamLabel(SprayTeam team)
  {
    OIterator<? extends SprayLeader> it = team.getAllTeamLeader();

    try
    {
      if (it.hasNext())
      {
        SprayLeader leader = it.next();
        String leaderName = leader.getPerson().getFirstName() + " " + leader.getPerson().getLastName();
        this.setTeamLabel(team.getTeamId() + " - " + leaderName);
      }
      else
      {
        this.setTeamLabel(team.getTeamId());
      }

    }
    finally
    {
      it.close();
    }
  }

  protected void populateSpray(TeamSpray spray)
  {
    super.populateSpray(spray);

    SprayOperator leader = this.getTeamLeader();
    
    spray.setSprayTeam(this.getSprayTeam());
    spray.setTeamSprayWeek(this.getTeamSprayWeek());
    spray.setTarget(this.getTarget());
    spray.setTeamLeader(leader);
  }

  @Override
  @Transaction
  public void apply()
  {
    // Create spray
    TeamSpray abstractSpray = (TeamSpray)this.getSpray();

    if (abstractSpray == null)
    {
      abstractSpray = TeamSpray.findOrCreate(this.getSprayData(), this.getSprayTeam());
    }
    
    if (!abstractSpray.isNew())
    {
      abstractSpray.lock();
    }

    this.populateSpray(abstractSpray);

    abstractSpray.apply();


    ActorSprayStatus status = new ActorSprayStatus();

    if (this.hasConcrete())
    {
      status = ActorSprayStatus.lock(this.getStatusId());
    }

    this.populateConcrete(status, abstractSpray);

    status.apply();

    this.populate(status);
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      SprayStatus.get(this.getStatusId()).delete();
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

  public static TeamSprayStatusView search(SprayData data, SprayTeam op)
  {
    TeamSpray spray = TeamSpray.findOrCreate(data, op);

    if (spray != null)
    {
      SprayStatusQuery query = new SprayStatusQuery(new QueryFactory());
      query.WHERE(query.getSpray().EQ(spray));
      query.ORDER_BY_ASC(query.getCreateDate());

      OIterator<? extends SprayStatus> it = query.getIterator();

      try
      {
        while (it.hasNext())
        {
          SprayStatus status = it.next();

          if (! ( status instanceof HouseholdSprayStatus ))
          {
            return (TeamSprayStatusView) status.getView();
          }
        }
      }
      finally
      {
        it.close();
      }
    }

    return null;
  }
}
