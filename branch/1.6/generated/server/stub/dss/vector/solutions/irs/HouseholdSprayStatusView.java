package dss.vector.solutions.irs;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.LocalProperty;

public class HouseholdSprayStatusView extends HouseholdSprayStatusViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860697955L;

  public HouseholdSprayStatusView()
  {
    super();
  }

  public void populateView(HouseholdSprayStatus concrete)
  {
    this.setConcreteId(concrete.getId());
    this.setHouseholdId(concrete.getHouseholdId());
    this.setStructureId(concrete.getStructureId());    
    this.setSpray(concrete.getSpray());
    this.setHouseholds(concrete.getHouseholds());
    this.setStructures(concrete.getStructures());
    this.setSprayedHouseholds(concrete.getSprayedHouseholds());
    this.setSprayedStructures(concrete.getSprayedStructures());
    this.setPrevSprayedHouseholds(concrete.getPrevSprayedHouseholds());
    this.setPrevSprayedStructures(concrete.getPrevSprayedStructures());
    this.setRooms(concrete.getRooms());
    this.setVerandas(concrete.getVerandas());
    this.setCattleSheds(concrete.getCattleSheds());
    this.setSprayedRooms(concrete.getSprayedRooms());
    this.setVerandasSprayed(concrete.getVerandasSprayed());
    this.setCattleShedsSprayed(concrete.getCattleShedsSprayed());
    this.setPeople(concrete.getPeople());
    this.setNumberOfPeople(concrete.getNumberOfPeople());
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
    this.setReasonNotSprayed(concrete.getReasonNotSprayed());
    this.setStructureType(concrete.getStructureType());
  }

  protected void populateConcrete(HouseholdSprayStatus concrete)
  {
    concrete.setHouseholdId(this.getHouseholdId());
    concrete.setStructureId(this.getStructureId());    
    concrete.setSpray(this.getSpray());
    concrete.setHouseholds(this.getHouseholds());
    concrete.setStructures(this.getStructures());
    concrete.setSprayedHouseholds(this.getSprayedHouseholds());
    concrete.setSprayedStructures(this.getSprayedStructures());
    concrete.setPrevSprayedHouseholds(this.getPrevSprayedHouseholds());
    concrete.setPrevSprayedStructures(this.getPrevSprayedStructures());
    concrete.setRooms(this.getRooms());
    concrete.setVerandas(this.getVerandas());
    concrete.setCattleSheds(this.getCattleSheds());
    concrete.setSprayedRooms(this.getSprayedRooms());
    concrete.setVerandasSprayed(this.getVerandasSprayed());
    concrete.setCattleShedsSprayed(this.getCattleShedsSprayed());
    concrete.setPeople(this.getPeople());
    concrete.setNumberOfPeople(this.getNumberOfPeople());
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
    concrete.setStructureType(this.getStructureType());
    concrete.setReasonNotSprayed(this.getReasonNotSprayed());
  }

  private void populateMapping(HouseholdSprayStatus concrete)
  {
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.HOUSEHOLDID, this, HouseholdSprayStatusView.HOUSEHOLDID);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.STRUCTUREID, this, HouseholdSprayStatusView.STRUCTUREID);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.SPRAY, this, HouseholdSprayStatusView.SPRAY);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.HOUSEHOLDS, this, HouseholdSprayStatusView.HOUSEHOLDS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.STRUCTURES, this, HouseholdSprayStatusView.STRUCTURES);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.SPRAYEDHOUSEHOLDS, this, HouseholdSprayStatusView.SPRAYEDHOUSEHOLDS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.SPRAYEDSTRUCTURES, this, HouseholdSprayStatusView.SPRAYEDSTRUCTURES);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.PREVSPRAYEDHOUSEHOLDS, this, HouseholdSprayStatusView.PREVSPRAYEDHOUSEHOLDS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.PREVSPRAYEDSTRUCTURES, this, HouseholdSprayStatusView.PREVSPRAYEDSTRUCTURES);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.ROOMS, this, HouseholdSprayStatusView.ROOMS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.VERANDAS, this, HouseholdSprayStatusView.VERANDAS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.CATTLESHEDS, this, HouseholdSprayStatusView.CATTLESHEDS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.SPRAYEDROOMS, this, HouseholdSprayStatusView.SPRAYEDROOMS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.VERANDASSPRAYED, this, HouseholdSprayStatusView.VERANDASSPRAYED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.CATTLESHEDSSPRAYED, this, HouseholdSprayStatusView.CATTLESHEDSSPRAYED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.PEOPLE, this, HouseholdSprayStatusView.PEOPLE);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.NUMBEROFPEOPLE, this, HouseholdSprayStatusView.NUMBEROFPEOPLE);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.BEDNETS, this, HouseholdSprayStatusView.BEDNETS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.ROOMSWITHBEDNETS, this, HouseholdSprayStatusView.ROOMSWITHBEDNETS);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.LOCKED, this, HouseholdSprayStatusView.LOCKED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.VERANDASLOCKED, this, HouseholdSprayStatusView.VERANDASLOCKED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.CATTLESHEDSLOCKED, this, HouseholdSprayStatusView.CATTLESHEDSLOCKED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.OTHER, this, HouseholdSprayStatusView.OTHER);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.VERANDASOTHER, this, HouseholdSprayStatusView.VERANDASOTHER);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.CATTLESHEDSOTHER, this, HouseholdSprayStatusView.CATTLESHEDSOTHER);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.REFUSED, this, HouseholdSprayStatusView.REFUSED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.VERANDASREFUSED, this, HouseholdSprayStatusView.VERANDASREFUSED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.CATTLESHEDSREFUSED, this, HouseholdSprayStatusView.CATTLESHEDSREFUSED);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.WRONGSURFACE, this, HouseholdSprayStatusView.WRONGSURFACE);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.STRUCTURETYPE, this, HouseholdSprayStatusView.STRUCTURETYPE);
    new AttributeNotificationMap(concrete, HouseholdSprayStatus.REASONNOTSPRAYED, this, HouseholdSprayStatusView.REASONNOTSPRAYED);
  }

  @Override
  @Transaction
  public void apply()
  {
    HouseholdSprayStatus concrete = new HouseholdSprayStatus();

    if (this.hasConcrete())
    {
      concrete = HouseholdSprayStatus.lock(this.getConcreteId());
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
      HouseholdSprayStatus.get(this.getConcreteId()).delete();
    }
  }

  public static HouseholdSprayStatusView[] applyAll(HouseholdSprayStatusView[] views)
  {
    synchronized (HouseholdSprayStatusView.class)
    {
      HouseholdSprayStatusView.applyAllSynchronized(views);
    }

    return views;
  }

  @Transaction
  private static void applyAllSynchronized(HouseholdSprayStatusView[] views)
  {
    Set<String> uncountedHouseholdIds = HouseholdSprayStatusView.getUncountedHouseholdIds(views);

    for (HouseholdSprayStatusView view : views)
    {
      if (uncountedHouseholdIds.contains(view.getHouseholdId()))
      {
        view.setHouseholds(1);
        uncountedHouseholdIds.remove(view.getHouseholdId());
      }
      else
      {
        view.setHouseholds(0);
      }

      view.apply();
    }
  }

  private static Set<String> getUncountedHouseholdIds(HouseholdSprayStatusView[] views)
  {
    Set<String> householdIds = HouseholdSprayStatusView.getHouseholdIds(views);
    Set<String> concreteIds = HouseholdSprayStatusView.getConcreteIds(views);

    Iterator<String> it = householdIds.iterator();
    while (it.hasNext())
    {
      String householdId = it.next();
      HouseholdSprayStatusQuery query = new HouseholdSprayStatusQuery(new QueryFactory());
      query.WHERE(query.getHouseholdId().EQ(householdId));
      query.AND(query.getHouseholds().EQ(1));

      for (String conreteId : concreteIds)
      {
        query.AND(query.getId().NE(conreteId));
      }

      if (query.getCount() > 0)
      {
        it.remove();
      }
    }

    return householdIds;
  }

  private static Set<String> getConcreteIds(HouseholdSprayStatusView[] views)
  {
    Set<String> set = new TreeSet<String>();

    for (HouseholdSprayStatusView view : views)
    {
      String concreteId = view.getConcreteId();

      if (concreteId != null && concreteId.length() > 0)
      {
        set.add(concreteId);
      }
    }

    return set;
  }

  private static Set<String> getHouseholdIds(HouseholdSprayStatusView[] views)
  {
    Set<String> set = new TreeSet<String>();

    for (HouseholdSprayStatusView view : views)
    {
      set.add(view.getHouseholdId());
    }

    return set;
  }

  public static String[] getGeneratedIds()
  {
    return new String[] { LocalProperty.getNextId(), LocalProperty.getNextId() };
  }
}
