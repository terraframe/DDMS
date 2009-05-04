package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
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
      abstractSpray = TeamSpray.findOrCreate(this.getSprayData(), this.getSprayTeam());
      
      if(!abstractSpray.isNew())
      {
        abstractSpray.lock();
      }

      this.populateSpray((TeamSpray) abstractSpray);

      abstractSpray.apply();
    }

    SprayStatus status = new SprayStatus();

    if(this.hasConcrete())
    {
      status = SprayStatus.lock(this.getStatusId());
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
