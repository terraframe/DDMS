package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
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

    SprayData data = SprayData.get(this.getBrand(), this.getGeoEntity(), this.getSprayDate(), method.toArray(new SprayMethod[method.size()]));
    
    OperatorSpray spray = new OperatorSpray();

    if(this.hasConcrete())
    {
      spray = OperatorSpray.get(this.getSprayId());
    }
    
    this.populateMapping(spray, data);

    this.applySprayData(data);

    this.populateConcrete(spray, data);

    spray.apply();
    spray.populateView(this);
  }

  protected void populateMapping(OperatorSpray spray, SprayData data)
  {
    super.populateMapping(spray, data);
    
    new AttributeNotificationMap(spray, OperatorSpray.OPERATORSPRAYWEEK, this, OperatorSprayView.OPERATORSPRAYWEEK);
    new AttributeNotificationMap(spray, OperatorSpray.SPRAYOPERATOR, this, OperatorSprayView.SPRAYOPERATOR);
  }

  protected void populateConcrete(OperatorSpray spray, SprayData data)
  {
    super.populateConcrete(spray, data);

    spray.setOperatorSprayWeek(this.getOperatorSprayWeek());
    spray.setSprayOperator(this.getSprayOperator());
  }

  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      OperatorSpray.get(this.getSprayId()).delete();
    }
  }

  public HouseholdSprayStatusView[] getStatus()
  {
    OperatorSpray spray = OperatorSpray.get(this.getSprayId());
    List<HouseholdSprayStatusView> list = new LinkedList<HouseholdSprayStatusView>();
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().EQ(spray));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends HouseholdSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add((HouseholdSprayStatusView) it.next().getView());
      }

      spray.populateView(this);

      return list.toArray(new HouseholdSprayStatusView[list.size()]);
    }
    finally
    {
      it.close();
    }    
  }

  public static OperatorSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand, String operatorId)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());

    query.WHERE(query.getSprayData().getBrand().EQ(brand));
    query.AND(query.getSprayData().getGeoEntity().getGeoId().EQ(geoId));
    query.AND(query.getSprayData().getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayData().getSprayMethod().containsAny(sprayMethod));
    query.AND(query.getSprayOperator().getId().EQ(operatorId));

    OIterator<? extends OperatorSpray> it = query.getIterator();

    try
    {
      if(it.hasNext())
      {
        return it.next().getView();
      }
      
      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
      OperatorSprayView view = new OperatorSprayView();
      view.setGeoEntity(geoEntity);
      view.setSprayDate(sprayDate);
      view.addSprayMethod(sprayMethod);
      view.setBrand(brand);
      view.setSprayOperator(SprayOperator.get(operatorId));

      return view;
    }
    finally
    {
      it.close();
    }
  }
}
