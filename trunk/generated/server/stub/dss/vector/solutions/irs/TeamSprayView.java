package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.MDSSUser;
import dss.vector.solutions.geo.generated.GeoEntity;

public class TeamSprayView extends TeamSprayViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676032L;

  public TeamSprayView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    List<SprayMethod> method = this.getSprayMethod();
    TeamSpray spray = new TeamSpray();

    SprayData data = SprayData.get(this.getBrand(), this.getGeoEntity(), this.getSprayDate(), method
        .toArray(new SprayMethod[method.size()]));

    this.populateSprayData(data);

    data.apply();

    if (this.hasConcrete())
    {
      spray = TeamSpray.get(this.getSprayId());
    }

    this.populateConcrete(spray, data);

    spray.apply();
    spray.populateView(this);
  }

  protected void populateConcrete(TeamSpray spray, SprayData data)
  {
    super.populateConcrete(spray, data);

    spray.setSprayTeam(this.getSprayTeam());
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      TeamSpray.get(this.getSprayId()).delete();
    }
  }

  public OperatorSprayStatusView[] getStatus()
  {
    if (!this.hasConcrete())
    {
      return new OperatorSprayStatusView[0];
    }

    List<SprayStatusView> list = new LinkedList<SprayStatusView>();

    SprayData data = TeamSpray.get(this.getSprayId()).getSprayData();
    OIterator<? extends MDSSUser> members = this.getSprayTeam().getAllSprayTeamMembers();

    for (MDSSUser user : members)
    {
      SprayOperator operator = user.getPerson().getSprayOperatorDelegate();
      OperatorSprayStatusView view = OperatorSprayStatusView.search(data, operator);

      if (view == null)
      {
        view = new OperatorSprayStatusView();
        view.setSprayData(data);
        view.setSprayOperator(operator);
      }

      list.add(view);
    }

    return list.toArray(new OperatorSprayStatusView[list.size()]);
  }


  public static TeamSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand, String teamId)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.AND(query.getSprayData().getGeoEntity().EQ(geoEntity));
    query.AND(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayData().getSprayMethod().containsAny(sprayMethod));
    query.AND(query.getSprayTeam().getId().EQ(teamId));

    OIterator<? extends TeamSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      return null;
    }
    finally
    {
      it.close();
    }
  }
}
