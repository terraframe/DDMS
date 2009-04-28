package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;

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

    this.setSprayTeam(s.getSprayTeam());
    this.setTeamSprayWeek(s.getTeamSprayWeek());
  }

  protected void populateSpray(TeamSpray spray)
  {
    spray.setSprayData(this.getSprayData());
    spray.setSprayTeam(this.getSprayTeam());
    spray.setTeamSprayWeek(this.getTeamSprayWeek());
  }

  @Override
  @Transaction
  public void apply()
  {
    //Create spray
    AbstractSpray abstractSpray = this.getSpray();

    if(abstractSpray == null)
    {
      abstractSpray = TeamSpray.find(this.getSprayData(), this.getSprayTeam());

      this.populateSpray((TeamSpray) abstractSpray);

      abstractSpray.apply();
    }

    SprayStatus status = new SprayStatus();

    if(this.hasConcrete())
    {
      status = SprayStatus.get(this.getStatusId());
    }

    this.populateConcrete(status, abstractSpray);

    status.apply();

    this.populate(status);
  }

  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      SprayStatus.get(this.getStatusId()).delete();
    }
  }

  @Transaction
  public static TeamSprayStatusView[] applyAll(TeamSprayStatusView[] views)
  {
    for(TeamSprayStatusView view : views)
    {
      view.apply();
    }

    return views;
  }

}
