package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
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
    ZoneSpray concrete = new ZoneSpray();
    
    if (this.hasConcrete())
    {
      concrete = ZoneSpray.get(this.getConcreteId());

      validateSprayMethod(concrete.getSprayMethod());
      validateGeoEntity(concrete.getGeoEntity()); 
    }
        
    this.populateMapping(concrete);

    this.populateConcrete(concrete);
        
    concrete.apply();

    this.populateView(concrete);
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
  
  protected void validateSprayMethod(List<SprayMethod> existing)
  {
    List<SprayMethod> newMethod = this.getSprayMethod();

    // Determine if the spray method has changed. If a spray method has changed
    // and this spray already has a spray status then throw an exception,
    // because a the status may have values that are invalid with the new spray
    // method.
    if (!newMethod.containsAll(existing) && this.hasStatus())
    {
      String msg = "The spray method cannot be altered if status rows already exist";
      ModifiedSprayMethodException e = new ModifiedSprayMethodException(msg);
      e.apply();

      throw e;
    }
  }

  protected void populateMapping(ZoneSpray spray)
  {
    new AttributeNotificationMap(spray, ZoneSpray.BRAND, this, ZoneSprayView.BRAND);
    new AttributeNotificationMap(spray, ZoneSpray.GEOENTITY, this, ZoneSprayView.GEOENTITY);
    new AttributeNotificationMap(spray, ZoneSpray.SPRAYDATE, this, ZoneSprayView.SPRAYDATE);
    new AttributeNotificationMap(spray, ZoneSpray.SPRAYMETHOD, this, ZoneSprayView.SPRAYMETHOD);
    new AttributeNotificationMap(spray, ZoneSpray.SURFACETYPE, this, ZoneSprayView.SURFACETYPE);
    new AttributeNotificationMap(spray, ZoneSpray.TARGET, this, ZoneSprayView.TARGET);
    new AttributeNotificationMap(spray, ZoneSpray.SPRAYWEEK, this, ZoneSprayView.SPRAYWEEK);
    new AttributeNotificationMap(spray, ZoneSpray.SUPERVISOR, this, ZoneSprayView.SUPERVISOR);
  }

  protected void populateConcrete(ZoneSpray concrete)
  {
    concrete.setSprayDate(this.getSprayDate());
    concrete.setBrand(this.getBrand());
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setSurfaceType(this.getSurfaceType());
    concrete.setTarget(this.getTarget());
    concrete.setSupervisor(this.getSupervisor());
    concrete.setSprayWeek(this.getSprayWeek());    
    concrete.clearSprayMethod();

    for (SprayMethod method : this.getSprayMethod())
    {
      concrete.addSprayMethod(method);
    }    
  }
  
  public void populateView(ZoneSpray concrete)
  {
    this.setConcreteId(concrete.getId());    
    this.setSprayDate(concrete.getSprayDate());
    this.setBrand(concrete.getBrand());
    this.setGeoEntity(concrete.getGeoEntity());
    this.setSurfaceType(concrete.getSurfaceType());
    this.setTarget(concrete.getTarget());
    this.setSupervisor(concrete.getSupervisor());
    this.setSprayWeek(concrete.getSprayWeek());
    
    this.clearSprayMethod();
    
    for (SprayMethod method : concrete.getSprayMethod())
    {
      this.addSprayMethod(method);
    }    
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      ZoneSpray.get(this.getConcreteId()).delete();
    }
  }
  
  protected boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
  
  public TeamSprayStatusView[] getStatus()
  {
    List<TeamSprayStatusView> list = new LinkedList<TeamSprayStatusView>();
    
    ZoneSpray spray = ZoneSpray.get(this.getConcreteId());
    
    TeamSprayStatusQuery query = new TeamSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().EQ(spray));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends TeamSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add(it.next().getView());
      }

      return list.toArray(new TeamSprayStatusView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }


  public boolean hasStatus()
  {
    return this.getStatus().length > 0;
  }

  public static ZoneSprayView searchBySprayData(String geoId, Date sprayDate, SprayMethod sprayMethod, InsecticideBrand brand)
  {
    ZoneSprayQuery query = new ZoneSprayQuery(new QueryFactory());

    query.WHERE(query.getBrand().EQ(brand));
    query.AND(query.getGeoEntity().getGeoId().EQ(geoId));
    query.AND(query.getSprayDate().EQ(sprayDate));
    query.AND(query.getSprayMethod().containsAny(sprayMethod));

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
