package dss.vector.solutions.irs;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class TeamSprayView extends TeamSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    TeamSpray spray = new TeamSpray();

    if (this.hasConcrete())
    {
      spray = TeamSpray.get(this.getSprayId());
      
      validateSprayMethod(spray.getSprayData().getSprayMethod());

      validateSprayTeam(spray.getSprayTeam());

      this.setSprayData(spray.getSprayData());            
    }

    this.lockSprayData();

    this.populateMapping(spray);

    this.populateConcrete(spray);

    this.getSprayData().apply();

    spray.apply();
    spray.populateView(this);
  }

  public void validateSprayTeam(SprayTeam existing)
  {
    if (existing != null && this.getSprayTeam() != null)
    {
      if (!existing.getId().equals(this.getSprayTeam().getId()) && this.hasStatus())
      {
        String msg = "The spray team cannot be altered if operator status rows already exist";
        ModifiedSprayTeamException e = new ModifiedSprayTeamException(msg);
        e.apply();

        throw e;
      }
    }
  }

  protected void populateMapping(TeamSpray spray)
  {
    super.populateMapping(spray);

    new AttributeNotificationMap(spray, TeamSpray.SPRAYTEAM, this, TeamSprayView.SPRAYTEAM);
  }

  protected void populateConcrete(TeamSpray spray)
  {
    super.populateConcrete(spray);

    spray.setSprayTeam(this.getSprayTeam());
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      TeamSpray.get(this.getSprayId()).delete();
    }
  }

  public boolean hasStatus()
  {
    return this.getStatus().length > 0;
  }

  public OperatorSprayStatusView[] getStatus()
  {

    if (!this.hasConcrete())
    {
      return new OperatorSprayStatusView[0];
    }

    TeamSpray spray = TeamSpray.get(this.getSprayId());
    SprayData data = spray.getSprayData();
    SprayTeam team = spray.getSprayTeam();
    SprayOperator[] members = team.getTeamMembers();

//    spray.populateView(this);

    return OperatorSprayStatusView.search(data, members);
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
