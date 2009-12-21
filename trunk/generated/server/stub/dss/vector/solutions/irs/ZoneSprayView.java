package dss.vector.solutions.irs;

import java.util.Date;

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
    ZoneSpray spray = new ZoneSpray();

    if (this.hasConcrete())
    {
      spray = ZoneSpray.get(this.getSprayId());
            
      validateSprayMethod(spray.getSprayData().getSprayMethod());
      validateGeoEntity(spray.getSprayData().getGeoEntity()); 
      
      this.setSprayData(spray.getSprayData());                  
    }
    
    this.lockSprayData();

    this.populateMapping(spray);

    this.populateConcrete(spray);
    
    this.getSprayData().apply();

    spray.apply();
    spray.populateView(this);
  }

  private void validateGeoEntity(GeoEntity geoEntity)
  {
    GeoEntity newGeoEntity = this.getGeoEntity();
    
    if (geoEntity != null && newGeoEntity != null)
    {
      if (!geoEntity.getId().equals(newGeoEntity.getId()) && this.hasStatus())
      {
        String msg = "The geo entity cannot be altered if team status rows already exist";
        ModifiedSprayZoneException e = new ModifiedSprayZoneException(msg);
        e.apply();

        throw e;
      }
    }
  }

  protected void populateConcrete(ZoneSpray spray)
  {
    super.populateConcrete(spray);

    spray.setSupervisor(this.getSupervisor());
    spray.setTarget(this.getTarget());
    spray.setSprayWeek(this.getSprayWeek());
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
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

    ZoneSpray spray = ZoneSpray.get(this.getSprayId());
    SprayData data = spray.getSprayData();
    SprayTeam[] teams = SprayTeam.findByLocation(data.getGeoEntity().getGeoId());

//    spray.populateView(this);

    return TeamSprayStatusView.search(data, teams);
  }

  public boolean hasStatus()
  {
    return this.getStatus().length > 0;
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
      if (it.hasNext())
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
