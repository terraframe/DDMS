package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.BasicCondition;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;

public class TeamSprayStatusView extends TeamSprayStatusViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    // The spray must be created before a status can be attached to it.
    this.applySpray();

    ActorSprayStatus status = new ActorSprayStatus();

    if (this.hasConcrete())
    {
      status = ActorSprayStatus.lock(this.getStatusId());
    }

    this.populateConcrete(status);

    status.apply();

    this.populate(status);
  }

  private void applySpray()
  {
    AbstractSpray spray = this.getSpray();

    if (spray == null)
    {
      spray = new TeamSpray();
    }
    else
    {
      this.getSpray().lock();
    }

    this.populateSpray((TeamSpray) spray);

    spray.apply();

    this.setSpray(spray);
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

  public static TeamSprayStatusView[] search(SprayData data, SprayTeam... teams)
  {
    List<TeamSprayStatusView> list = new LinkedList<TeamSprayStatusView>();

    QueryFactory factory = new QueryFactory();

    TeamSprayQuery teamQuery = new TeamSprayQuery(factory);
    teamQuery.WHERE(teamQuery.getSprayData().EQ(data));
    teamQuery.AND(TeamSprayStatusView.buildOrCondition(teamQuery, teams));

    SprayStatusQuery query = new SprayStatusQuery(factory);
    query.WHERE(query.getSpray().EQ(teamQuery));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends SprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        SprayStatus status = it.next();

        if (! ( status instanceof HouseholdSprayStatus ))
        {
          list.add((TeamSprayStatusView) status.getView());
        }
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
