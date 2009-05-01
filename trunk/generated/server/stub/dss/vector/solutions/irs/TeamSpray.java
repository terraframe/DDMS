package dss.vector.solutions.irs;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class TeamSpray extends TeamSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860702014L;

  public TeamSpray()
  {
    super();
  }

  public TeamSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public TeamSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public TeamSprayView getView()
  {
    TeamSprayView view = new TeamSprayView();

    this.populateView(view);

    return view;
  }

  public void populateView(TeamSprayView view)
  {
    SprayData data = this.getSprayData();
    view.setBrand(data.getBrand());
    view.setGeoEntity(data.getGeoEntity());
    view.setSprayDate(data.getSprayDate());

    view.clearSprayMethod();
    view.clearSurfaceType();

    for (SprayMethod method : data.getSprayMethod())
    {
      view.addSprayMethod(method);
    }

    for (SurfaceType type : data.getSurfaceType())
    {
      view.addSurfaceType(type);
    }

    view.setTeamSprayWeek(this.getTeamSprayWeek());
    view.setSprayTeam(this.getSprayTeam());
    view.setTarget(this.getTarget());
    view.setTeamSprayWeek(this.getTeamSprayWeek());

    view.setSprayId(this.getId());
    view.setDataId(data.getId());
  }

  @Override
  public SprayStatusView getSprayStatusView()
  {
    return new TeamSprayStatusView();
  }

  public static TeamSprayView getView(String id)
  {
    return TeamSpray.get(id).getView();
  }

  public static TeamSpray find(SprayData data, SprayTeam team)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayData().EQ(data));
    query.AND(query.getSprayTeam().EQ(team));

    OIterator<? extends TeamSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }

  public static TeamSpray findOrCreate(SprayData data, SprayTeam team)
  {
    TeamSpray spray = TeamSpray.find(data, team);

    if(spray == null)
    {
      spray = new TeamSpray();
    }

    return spray;
  }

}
