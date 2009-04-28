package dss.vector.solutions.irs;

import java.util.Date;
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

    this.populateSprayData(data);

    data.apply();

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


  public static ZoneSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand)
  {
    ZoneSprayQuery query = new ZoneSprayQuery(new QueryFactory());
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.AND(query.getSprayData().getGeoEntity().EQ(geoEntity));
    query.AND(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayData().getSprayMethod().containsAny(sprayMethod));

    OIterator<? extends ZoneSpray> it = query.getIterator();

    try
    {
      if(it.hasNext())
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
