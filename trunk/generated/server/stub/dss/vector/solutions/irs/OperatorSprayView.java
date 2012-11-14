package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorSprayView extends OperatorSprayViewBase implements com.runwaysdk.generation.loader.Reloadable
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
    OperatorSpray concrete;
    if (this.hasConcrete())
    {
      concrete = OperatorSpray.get(this.getConcreteId());
      concrete.appLock();
      validateSprayMethod(concrete.getSprayMethod());
    }
    else
    {
      concrete = new OperatorSpray();
    }
        
    this.populateMapping(concrete);

    this.populateConcrete(concrete);
        
    concrete.apply();

    this.populateView(concrete);
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

  protected void populateMapping(OperatorSpray spray)
  {
    new AttributeNotificationMap(spray, OperatorSpray.BRAND, this, OperatorSprayView.BRAND);
    new AttributeNotificationMap(spray, OperatorSpray.GEOENTITY, this, OperatorSprayView.GEOENTITY);
    new AttributeNotificationMap(spray, OperatorSpray.SPRAYDATE, this, OperatorSprayView.SPRAYDATE);
    new AttributeNotificationMap(spray, OperatorSpray.SPRAYMETHOD, this, OperatorSprayView.SPRAYMETHOD);
    new AttributeNotificationMap(spray, OperatorSpray.SURFACETYPE, this, OperatorSprayView.SURFACETYPE);
    new AttributeNotificationMap(spray, OperatorSpray.TEAMLEADER, this, OperatorSprayView.TEAMLEADER);
    new AttributeNotificationMap(spray, OperatorSpray.TARGET, this, OperatorSprayView.TARGET);
    new AttributeNotificationMap(spray, OperatorSpray.RECEIVED, this, OperatorSprayView.RECEIVED);
    new AttributeNotificationMap(spray, OperatorSpray.REFILLS, this, OperatorSprayView.REFILLS);
    new AttributeNotificationMap(spray, OperatorSpray.RETURNED, this, OperatorSprayView.RETURNED);        
    new AttributeNotificationMap(spray, OperatorSpray.SPRAYOPERATOR, this, OperatorSprayView.SPRAYOPERATOR);
    new AttributeNotificationMap(spray, OperatorSpray.SPRAYTEAM, this, OperatorSprayView.SPRAYTEAM);
  }

  protected void populateConcrete(OperatorSpray concrete)
  {
    concrete.setSprayDate(this.getSprayDate());
    concrete.setBrand(this.getBrand());
    concrete.setGeoEntity(this.getGeoEntity());
    concrete.setSurfaceType(this.getSurfaceType());
    concrete.setSprayOperator(this.getSprayOperator());
    concrete.setTarget(this.getTarget());
    concrete.setReceived(this.getReceived());
    concrete.setRefills(this.getRefills());
    concrete.setReturned(this.getReturned());
    concrete.setUsed(this.getUsed());
    concrete.setTeamLeader(this.getTeamLeader());
    concrete.setSprayTeam(this.getSprayTeam());
    concrete.clearSprayMethod();

    for (SprayMethod method : this.getSprayMethod())
    {
      concrete.addSprayMethod(method);
    }    
  }
  
  public void populateView(OperatorSpray concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setSprayDate(concrete.getSprayDate());
    this.setBrand(concrete.getBrand());
    this.setGeoEntity(concrete.getGeoEntity());
    this.setSurfaceType(concrete.getSurfaceType());
    this.setSprayOperator(concrete.getSprayOperator());
    this.setTarget(concrete.getTarget());
    this.setReceived(concrete.getReceived());
    this.setRefills(concrete.getRefills());
    this.setReturned(concrete.getReturned());
    this.setUsed(concrete.getUsed());
    this.setTeamLeader(concrete.getTeamLeader());
    this.setSprayTeam(concrete.getSprayTeam());
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
      OperatorSpray.get(this.getConcreteId()).delete();
    }
  }
  
  protected boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
  
  public boolean hasStatus()
  {
    return this.getStatus().length > 0;
  }

  public HouseholdSprayStatusView[] getStatus()
  {
    List<HouseholdSprayStatusView> list = new LinkedList<HouseholdSprayStatusView>();
    
    OperatorSpray spray = OperatorSpray.get(this.getConcreteId());
    
    HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
    query.WHERE(query.getSpray().EQ(spray));
    query.ORDER_BY_ASC(query.getHouseholdId());
    query.ORDER_BY_ASC(query.getStructureId());

    OIterator<? extends HouseholdSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        list.add((HouseholdSprayStatusView) it.next().getView());
      }

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
    
    Condition condition = query.getBrand().EQ(brand);
    condition = AND.get(condition, query.getGeoEntity().getGeoId().EQ(geoId));
    condition = AND.get(condition, query.getSprayDate().EQ(sprayDate));
    condition = AND.get(condition, query.getSprayMethod().containsAny(sprayMethod));
    condition = AND.get(condition, query.getSprayOperator().EQ(operatorId));

    query.WHERE(condition);
    
    OIterator<? extends OperatorSpray> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next().getView();
      }

      GeoEntity geoEntity = GeoEntity.searchByGeoId(geoId);
      OperatorSprayView view = new OperatorSprayView();
      view.setGeoEntity(geoEntity);
      view.setSprayDate(sprayDate);
      view.addSprayMethod(sprayMethod);
      view.setBrand(brand);
      view.setSprayOperator(TeamMember.getSprayOperator(operatorId));

      return view;
    }
    finally
    {
      it.close();
    }    
  }
}
