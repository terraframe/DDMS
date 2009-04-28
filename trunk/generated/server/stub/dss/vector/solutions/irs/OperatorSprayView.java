package dss.vector.solutions.irs;

import java.util.Date;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorSprayView extends OperatorSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853366055L;

  public OperatorSprayView()
  {
    super();
  }


  @Override
  @Transaction
  public void apply()
  {
    List<SprayMethod> method = this.getSprayMethod();
    OperatorSpray spray = new OperatorSpray();

    SprayData data = SprayData.get(this.getBrand(), this.getGeoEntity(), this.getSprayDate(), method.toArray(new SprayMethod[method.size()]));

    this.populateSprayData(data);

    data.apply();

    if(this.hasConcrete())
    {
      spray = OperatorSpray.get(this.getSprayId());
    }

    this.populateConcrete(spray, data);

    spray.apply();
    spray.populateView(this);
  }

  protected void populateConcrete(OperatorSpray spray, SprayData data)
  {
    super.populateConcrete(spray, data);

    spray.setOperatorSprayWeek(this.getOperatorSprayWeek());
    spray.setReceived(this.getReceived());
    spray.setRefills(this.getRefills());
    spray.setReturned(this.getReturned());
    spray.setSprayOperator(this.getSprayOperator());
    spray.setUsed(this.getUsed());
  }

  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      OperatorSpray.get(this.getSprayId()).delete();
    }
  }


  public static OperatorSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand, String operatorId)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);

    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.AND(query.getSprayData().getGeoEntity().EQ(geoEntity));
    query.AND(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayData().getSprayMethod().containsAny(sprayMethod));
    query.AND(query.getSprayOperator().getOperatorId().EQ(operatorId));

    OIterator<? extends OperatorSpray> it = query.getIterator();

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
