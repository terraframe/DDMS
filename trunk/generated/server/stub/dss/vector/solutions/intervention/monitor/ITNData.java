package dss.vector.solutions.intervention.monitor;

import java.util.Date;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.general.EpiDate;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.surveillance.PeriodType;

public class ITNData extends ITNDataBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1245774436880L;
  
  public ITNData()
  {
    super();
  }
  
  public static ITNData searchByGeoEntityAndDate(GeoEntity geoEntity, Date startDate, Date endDate)
  {
    ITNDataQuery query = new ITNDataQuery(new QueryFactory());
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.AND(query.getStartDate().EQ(startDate));
    query.AND(query.getEndDate().EQ(endDate));

    OIterator<? extends ITNData> it = query.getIterator();

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

  
  public static ITNDataView searchByGeoEntityAndEpiDate(GeoEntity geoEntity, PeriodType periodType, Integer period, Integer year)
  {
    EpiDate.validate(periodType, period, year); 

    EpiDate date = EpiDate.getInstanceByPeriod(periodType, period, year);
    
    ITNData concrete = searchByGeoEntityAndDate(geoEntity, date.getStartDate(), date.getEndDate());

    if (concrete != null)
    {     
      return concrete.getView();
    }

    ITNDataView view = new ITNDataView();
    view.setGeoId(geoEntity.getGeoId());
    view.setPeriod(period);
    view.addPeriodType(periodType);
    view.setPeriodYear(year);

    return view;
  }
  
  public static ITNDataView getView(String id)
  {
    return ITNData.get(id).getView();
  }


  public ITNDataView getView()
  {
    ITNDataView view = new ITNDataView();
    view.populateView(this);
    
    return view;
  }
  
  @Override
  @Transaction
  public void lock()
  {
    super.lock();
    
    // Lock the grid relationship also, this must be in the same transaction
    for (ITNService service : this.getAllServicesRel())
    {
      service.lock();
    }

    for (ITNTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.lock();
    }

    for (ITNNet net : this.getAllNetsRel())
    {
      net.lock();
    }
  }
  
  @Override
  @Transaction
  public void unlock()
  {
    super.unlock();
    
    // Unlock the grid relationship also, this must be in the same transaction
    for (ITNService service : this.getAllServicesRel())
    {
      service.unlock();
    }

    for (ITNTargetGroup group : this.getAllTargetGroupsRel())
    {
      group.unlock();
    }

    for (ITNNet net : this.getAllNetsRel())
    {
      net.unlock();
    }
  }
  
  @Override
  public ITNDataView unlockView()
  {
    this.unlock();
    
    return this.getView();
  }
  
  @Override
  public ITNDataView lockView()
  {
    this.lock();
    
    return this.getView();
  }
  
  @Override
  public void apply()
  {    
    //Validate the amount of currency recieved
    this.validateCurrencyReceived();
    
    super.apply();
  }
  
  @Override
  public void validateCurrencyReceived()
  {
    if(this.getCurrencyReceived() != null)
    {
      if(this.getNumberSold() == null || this.getNumberSold() == 0)
      {
        String msg = "Currency received cannot be set when the total number of ITNs sold is zero.";
        CurrencyAmountProblem p = new CurrencyAmountProblem(msg);
        p.setNotification(this, ITNData.CURRENCYRECEIVED);
        p.apply();
        
        p.throwIt();
      }
    }
      
  }

}
