package dss.vector.solutions.irs;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;
import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.BasicCondition;
import com.terraframe.mojo.query.Condition;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.OR;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.RequiredAttributeProblem;
import dss.vector.solutions.geo.generated.GeoEntity;

public class OperatorSprayStatusView extends OperatorSprayStatusViewBase implements com.terraframe.mojo.generation.loader.Reloadable
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
    this.setRooms(concrete.getRooms());
    this.setSprayedRooms(concrete.getSprayedRooms());
    this.setPeople(concrete.getPeople());
    this.setBedNets(concrete.getBedNets());
    this.setRoomsWithBedNets(concrete.getRoomsWithBedNets());
    this.setLocked(concrete.getLocked());
    this.setOther(concrete.getOther());
    this.setRefused(concrete.getRefused());
    this.setSprayOperator(concrete.getSprayOperator());
    this.setOperatorSprayWeek(concrete.getOperatorSprayWeek());
    if (concrete.getSprayOperator() != null) {
        this.setOperatorLabel(concrete.getSprayOperator().getLabel());
    } else {
        RequiredAttributeProblem p = new RequiredAttributeProblem();
        p.setNotification(this, SPRAYOPERATOR);
        p.apply();

        p.throwIt();
    }
    this.setReceived(concrete.getReceived());
    this.setRefills(concrete.getRefills());
    this.setReturned(concrete.getReturned());
    this.setUsed(concrete.getUsed());
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
    concrete.setRooms(this.getRooms());
    concrete.setSprayedRooms(this.getSprayedRooms());
    concrete.setPeople(this.getPeople());
    concrete.setBedNets(this.getBedNets());
    concrete.setRoomsWithBedNets(this.getRoomsWithBedNets());
    concrete.setLocked(this.getLocked());
    concrete.setOther(this.getOther());
    concrete.setRefused(this.getRefused());
    concrete.setSprayOperator(this.getSprayOperator());
    concrete.setOperatorSprayWeek(this.getOperatorSprayWeek());
    concrete.setReceived(this.getReceived());
    concrete.setRefills(this.getRefills());
    concrete.setReturned(this.getReturned());
    concrete.setUsed(this.getUsed());    
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
    new AttributeNotificationMap(concrete, OperatorSprayStatus.ROOMS, this, OperatorSprayStatusView.ROOMS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYEDROOMS, this, OperatorSprayStatusView.SPRAYEDROOMS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.PEOPLE, this, OperatorSprayStatusView.PEOPLE);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.BEDNETS, this, OperatorSprayStatusView.BEDNETS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.ROOMSWITHBEDNETS, this, OperatorSprayStatusView.ROOMSWITHBEDNETS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.LOCKED, this, OperatorSprayStatusView.LOCKED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.OTHER, this, OperatorSprayStatusView.OTHER);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.REFUSED, this, OperatorSprayStatusView.REFUSED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.OPERATORSPRAYWEEK, this, OperatorSprayStatusView.OPERATORSPRAYWEEK);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.SPRAYOPERATOR, this, OperatorSprayStatusView.SPRAYOPERATOR);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.RECEIVED, this, OperatorSprayStatusView.RECEIVED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.REFILLS, this, OperatorSprayStatusView.REFILLS);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.RETURNED, this, OperatorSprayStatusView.RETURNED);
    new AttributeNotificationMap(concrete, OperatorSprayStatus.USED, this, OperatorSprayStatusView.USED);
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
