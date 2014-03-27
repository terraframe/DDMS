package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.BasicCondition;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorSprayStatusView extends OperatorSprayStatusViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860663695L;

  public OperatorSprayStatusView()
  {
    super();
  }

  public void populateView(OperatorSprayStatus concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setSpray(concrete.getSpray());
    this.setHouseholds(concrete.getHouseholds());
    this.setStructures(concrete.getStructures());
    this.setSprayedHouseholds(concrete.getSprayedHouseholds());
    this.setSprayedStructures(concrete.getSprayedStructures());
    this.setPrevSprayedHouseholds(concrete.getPrevSprayedHouseholds());
    this.setPrevSprayedStructures(concrete.getPrevSprayedStructures());
    this.setSprayOperator(concrete.getSprayOperator());
    this.setOperatorTarget(concrete.getOperatorTarget());    
    this.setReceived(concrete.getReceived());
    this.setRefills(concrete.getRefills());
    this.setReturned(concrete.getReturned());
    this.setUsed(concrete.getUsed());
    this.setRooms(concrete.getRooms());
    this.setVerandas(concrete.getVerandas());
    this.setCattleSheds(concrete.getCattleSheds());
    this.setSprayedRooms(concrete.getSprayedRooms());
    this.setVerandasSprayed(concrete.getVerandasSprayed());
    this.setCattleShedsSprayed(concrete.getCattleShedsSprayed());
    this.setPeople(concrete.getPeople());
    this.setBedNets(concrete.getBedNets());
    this.setRoomsWithBedNets(concrete.getRoomsWithBedNets());
    this.setLocked(concrete.getLocked());
    this.setVerandasLocked(concrete.getVerandasLocked());
    this.setCattleShedsLocked(concrete.getCattleShedsLocked());
    this.setOther(concrete.getOther());
    this.setVerandasOther(concrete.getVerandasOther());
    this.setCattleShedsOther(concrete.getCattleShedsOther());    
    this.setRefused(concrete.getRefused());
    this.setVerandasRefused(concrete.getVerandasRefused());
    this.setCattleShedsRefused(concrete.getCattleShedsRefused());
    this.setWrongSurface(concrete.getWrongSurface());
    this.setNozzlesUsed(concrete.getNozzlesUsed());
    this.setPumpsUsed(concrete.getPumpsUsed());
    
    if (concrete.getSprayOperator() != null)
    {
      this.setOperatorLabel(concrete.getSprayOperator().getLabel());
    }
    else
    {
      RequiredAttributeProblem p = new RequiredAttributeProblem();
      p.setNotification(this, SPRAYOPERATOR);
      p.apply();

      p.throwIt();
    }
  }

  protected void populateConcrete(OperatorSprayStatus concrete)
  {
    concrete.setSpray(this.getSpray());
    concrete.setHouseholds(this.getHouseholds());
    concrete.setStructures(this.getStructures());
    concrete.setSprayedHouseholds(this.getSprayedHouseholds());
    concrete.setSprayedStructures(this.getSprayedStructures());
    concrete.setPrevSprayedHouseholds(this.getPrevSprayedHouseholds());
    concrete.setPrevSprayedStructures(this.getPrevSprayedStructures());
    concrete.setSprayOperator(this.getSprayOperator());
    concrete.setOperatorTarget(this.getOperatorTarget());
    concrete.setReceived(this.getReceived());
    concrete.setRefills(this.getRefills());
    concrete.setReturned(this.getReturned());
    concrete.setUsed(this.getUsed());
    concrete.setRooms(this.getRooms());
    concrete.setVerandas(this.getVerandas());
    concrete.setCattleSheds(this.getCattleSheds());
    concrete.setSprayedRooms(this.getSprayedRooms());
    concrete.setVerandasSprayed(this.getVerandasSprayed());
    concrete.setCattleShedsSprayed(this.getCattleShedsSprayed());
    concrete.setPeople(this.getPeople());
    concrete.setBedNets(this.getBedNets());
    concrete.setRoomsWithBedNets(this.getRoomsWithBedNets());
    concrete.setLocked(this.getLocked());
    concrete.setVerandasLocked(this.getVerandasLocked());
    concrete.setCattleShedsLocked(this.getCattleShedsLocked());
    concrete.setOther(this.getOther());
    concrete.setVerandasOther(this.getVerandasOther());
    concrete.setCattleShedsOther(this.getCattleShedsOther());    
    concrete.setRefused(this.getRefused());
    concrete.setVerandasRefused(this.getVerandasRefused());
    concrete.setCattleShedsRefused(this.getCattleShedsRefused());
    concrete.setWrongSurface(this.getWrongSurface());    
    concrete.setNozzlesUsed(this.getNozzlesUsed());
    concrete.setPumpsUsed(this.getPumpsUsed());
  }

  private void populateMapping(OperatorSprayStatus concrete)
  {
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAY, this, OperatorSprayStatusView.SPRAY);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.HOUSEHOLDS, this, OperatorSprayStatusView.HOUSEHOLDS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.STRUCTURES, this, OperatorSprayStatusView.STRUCTURES);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYEDHOUSEHOLDS, this, OperatorSprayStatusView.SPRAYEDHOUSEHOLDS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYEDSTRUCTURES, this, OperatorSprayStatusView.SPRAYEDSTRUCTURES);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.PREVSPRAYEDHOUSEHOLDS, this, OperatorSprayStatusView.PREVSPRAYEDHOUSEHOLDS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.PREVSPRAYEDSTRUCTURES, this, OperatorSprayStatusView.PREVSPRAYEDSTRUCTURES);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYEDROOMS, this, OperatorSprayStatusView.SPRAYEDROOMS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.BEDNETS, this, OperatorSprayStatusView.BEDNETS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.ROOMSWITHBEDNETS, this, OperatorSprayStatusView.ROOMSWITHBEDNETS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.OPERATORTARGET, this, OperatorSprayStatusView.OPERATORTARGET);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYOPERATOR, this, OperatorSprayStatusView.SPRAYOPERATOR);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.RECEIVED, this, OperatorSprayStatusView.RECEIVED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.REFILLS, this, OperatorSprayStatusView.REFILLS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.RETURNED, this, OperatorSprayStatusView.RETURNED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.USED, this, OperatorSprayStatusView.USED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.ROOMS, this, OperatorSprayStatusView.ROOMS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.VERANDAS, this, OperatorSprayStatusView.VERANDAS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.CATTLESHEDS, this, OperatorSprayStatusView.CATTLESHEDS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYEDROOMS, this, OperatorSprayStatusView.SPRAYEDROOMS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.VERANDASSPRAYED, this, OperatorSprayStatusView.VERANDASSPRAYED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.CATTLESHEDSSPRAYED, this, OperatorSprayStatusView.CATTLESHEDSSPRAYED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.PEOPLE, this, OperatorSprayStatusView.PEOPLE);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.BEDNETS, this, OperatorSprayStatusView.BEDNETS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.ROOMSWITHBEDNETS, this, OperatorSprayStatusView.ROOMSWITHBEDNETS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.LOCKED, this, OperatorSprayStatusView.LOCKED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.VERANDASLOCKED, this, OperatorSprayStatusView.VERANDASLOCKED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.CATTLESHEDSLOCKED, this, OperatorSprayStatusView.CATTLESHEDSLOCKED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.OTHER, this, OperatorSprayStatusView.OTHER);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.VERANDASOTHER, this, OperatorSprayStatusView.VERANDASOTHER);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.CATTLESHEDSOTHER, this, OperatorSprayStatusView.CATTLESHEDSOTHER);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.REFUSED, this, OperatorSprayStatusView.REFUSED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.VERANDASREFUSED, this, OperatorSprayStatusView.VERANDASREFUSED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.CATTLESHEDSREFUSED, this, OperatorSprayStatusView.CATTLESHEDSREFUSED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.WRONGSURFACE, this, OperatorSprayStatusView.WRONGSURFACE);    
    new AttributeNotificationMap(concrete, OperatorSprayStatus.NOZZLESUSED, this, OperatorSprayStatusView.NOZZLESUSED);    
    new AttributeNotificationMap(concrete, OperatorSprayStatus.PUMPSUSED, this, OperatorSprayStatusView.PUMPSUSED);    
  }

  @Override
  @Transaction
  public void apply()
  {
    OperatorSprayStatus concrete = new OperatorSprayStatus();

    if (this.hasConcrete())
    {
      concrete = OperatorSprayStatus.lock(this.getConcreteId());
    }

    this.populateMapping(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  protected boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }

  public void deleteConcrete()
  {
    if (this.hasConcrete())
    {
      OperatorSprayStatus.get(this.getConcreteId()).delete();
    }
  }

  @Transaction
  public static OperatorSprayStatusView[] applyAll(OperatorSprayStatusView[] views)
  {
    for (OperatorSprayStatusView view : views)
    {
      view.apply();
    }

    return views;
  }

  public static OperatorSprayStatusView[] search(InsecticideBrand brand, GeoEntity geoEntity, Date date, SprayMethod[] sprayMethods, TeamMember... operators)
  {
    List<OperatorSprayStatusView> list = new LinkedList<OperatorSprayStatusView>();

    QueryFactory factory = new QueryFactory();

    OperatorSprayQuery operatorQuery = new OperatorSprayQuery(factory);
    operatorQuery.WHERE(operatorQuery.getBrand().EQ(brand));
    operatorQuery.AND(operatorQuery.getGeoEntity().EQ(geoEntity));
    operatorQuery.AND(operatorQuery.getSprayDate().EQ(date));
    operatorQuery.AND(operatorQuery.getSprayMethod().containsAny(sprayMethods));
    operatorQuery.AND(OperatorSprayStatusView.buildOrCondition(operatorQuery, operators));

    OperatorSprayStatusQuery query = new OperatorSprayStatusQuery(factory);
    query.WHERE(query.getSpray().EQ(operatorQuery));
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends OperatorSprayStatus> it = query.getIterator();

    try
    {
      while (it.hasNext())
      {
        OperatorSprayStatus status = it.next();

        list.add(status.getView());
      }
    }
    finally
    {
      it.close();
    }

    return list.toArray(new OperatorSprayStatusView[list.size()]);
  }

  private static Condition buildOrCondition(OperatorSprayQuery query, TeamMember... operators)
  {
    Condition or = null;

    for (TeamMember operator : operators)
    {
      BasicCondition condition = query.getSprayOperator().EQ(operator);

      if (or == null)
      {
        or = condition;
      }
      else
      {
        or = OR.get(or, condition);
      }
    }
    return or;
  }

}
