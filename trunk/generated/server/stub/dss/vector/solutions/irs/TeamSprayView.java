package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

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

    if (this.hasConcrete())
    {
      spray = TeamSpray.get(this.getSprayId());
    }

    SprayData data = SprayData.get(this.getBrand(), this.getGeoEntity(), this.getSprayDate(), method.toArray(new SprayMethod[method.size()]));
    
    this.populateMapping(spray, data);

    this.applySprayData(data);

    this.populateConcrete(spray, data);

    spray.apply();
    spray.populateView(this);
  }
  
  protected void populateMapping(TeamSpray spray, SprayData data)
  {
    super.populateMapping(spray, data);
    
    new AttributeNotificationMap(spray, TeamSpray.SPRAYTEAM, this, TeamSprayView.SPRAYTEAM);
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

    TeamSpray spray = TeamSpray.get(this.getSprayId());
    SprayData data = spray.getSprayData();
    SprayTeam team = spray.getSprayTeam();
    SprayOperator[] members = team.getTeamMembers();

    for (SprayOperator operator : members)
    {
      OperatorSprayStatusView view = OperatorSprayStatusView.search(data, operator);

      if (view == null)
      {
        view = new OperatorSprayStatusView();
        view.setSprayData(data);
        view.populate(operator);
      }

      list.add(view);
    }
    
    spray.populateView(this);

    return list.toArray(new OperatorSprayStatusView[list.size()]);
  }


  public static TeamSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand, String teamId)
  {
    TeamSprayQuery query = new TeamSprayQuery(new QueryFactory());

    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.AND(query.getSprayData().getGeoEntity().getGeoId().EQ(geoId));
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
      
      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
      
      TeamSprayView view = new TeamSprayView();
      view.setGeoEntity(geoEntity);
      view.setSprayDate(sprayDate);
      view.addSprayMethod(sprayMethod);
      view.setBrand(brand);
      view.setSprayTeam(SprayTeam.get(teamId));

      return view;
    }
    finally
    {
      it.close();
    }
  }
}
