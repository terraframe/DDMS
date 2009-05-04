package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class ZoneSprayView extends ZoneSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860657649L;

  public ZoneSprayView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    List<SprayMethod> method = this.getSprayMethod();
    ZoneSpray spray = new ZoneSpray();

    SprayData data = SprayData.get(this.getBrand(), this.getGeoEntity(), this.getSprayDate(), method.toArray(new SprayMethod[method.size()]));

    this.applySprayData(data);

    if(this.hasConcrete())
    {
      spray = ZoneSpray.get(this.getSprayId());
    }

    this.populateConcrete(spray, data);

    spray.apply();
    spray.populateView(this);
  }

  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      ZoneSpray.get(this.getSprayId()).delete();
    }
  }

  public TeamSprayStatusView[] getStatus()
  {
    if (!this.hasConcrete())
    {
      return new TeamSprayStatusView[0];
    }

    List<TeamSprayStatusView> list = new LinkedList<TeamSprayStatusView>();

    ZoneSpray spray = ZoneSpray.get(this.getSprayId());
    SprayData data = spray.getSprayData();
    SprayTeam[] teams = SprayTeam.search(data.getGeoEntity());

    for (SprayTeam team : teams)
    {
      TeamSprayStatusView view = TeamSprayStatusView.search(data, team);

      if (view == null)
      {
        view = new TeamSprayStatusView();
        view.setSprayData(data);
        view.setSprayTeam(team);
      }

      list.add(view);
    }
    
    spray.populateView(this);

    return list.toArray(new TeamSprayStatusView[list.size()]);
  }


  public static ZoneSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand)
  {
    ZoneSprayQuery query = new ZoneSprayQuery(new QueryFactory());

    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.AND(query.getSprayData().getGeoEntity().getGeoId().EQ(geoId));
    query.AND(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayData().getSprayMethod().containsAny(sprayMethod));

    OIterator<? extends ZoneSpray> it = query.getIterator();

    try
    {
      if(it.hasNext())
      {
        return it.next().getView();
      }

      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
      
      ZoneSprayView view = new ZoneSprayView();
      view.setGeoEntity(geoEntity);
      view.setSprayDate(sprayDate);
      view.addSprayMethod(sprayMethod);
      view.setBrand(brand);

      return view;
    }
    finally
    {
      it.close();
    }
  }

}
