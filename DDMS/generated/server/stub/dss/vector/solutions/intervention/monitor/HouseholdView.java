/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.AttributeNotificationMap;
import com.runwaysdk.query.OIterator;

import dss.vector.solutions.Response;

public class HouseholdView extends HouseholdViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1255641605608L;
  
  public HouseholdView()
  {
    super();
  }
  
  public void populateView(Household concrete)
  {    
    this.setConcreteId(concrete.getId());

    this.setHasWindows(concrete.getHasWindows());
    this.setHouseholdName(concrete.getHouseholdName());
    
    this.clearHasBeenSprayed();
    for(Response response : concrete.getHasBeenSprayed())
    {
      this.addHasBeenSprayed(response);
    }    
    
    this.setLastSprayed(concrete.getLastSprayed());
    this.setNets(concrete.getNets());
    this.setPeople(concrete.getPeople());
    this.setRoof(concrete.getRoof());
    this.setRoofInfo(concrete.getRoofInfo());
    this.setRooms(concrete.getRooms());
    this.setSurveyPoint(concrete.getSurveyPoint());
    this.setUrban(concrete.getUrban());
    this.setWall(concrete.getWall());
    this.setWallInfo(concrete.getWallInfo());
    this.setWindowType(concrete.getWindowType());

    long count = ITNInstance.getCount(concrete);
    this.setHasHouseholdNets(count > 0);
    
  }

  private void populateConcrete(Household concrete)
  {
    concrete.setHasWindows(this.getHasWindows());
    concrete.setHouseholdName(this.getHouseholdName());
        
    concrete.clearHasBeenSprayed();
    for(Response response : this.getHasBeenSprayed())
    {
      concrete.addHasBeenSprayed(response);
    }    
    
    concrete.setLastSprayed(this.getLastSprayed());
    concrete.setNets(this.getNets());
    concrete.setPeople(this.getPeople());
    concrete.setRoof(this.getRoof());
    concrete.setRoofInfo(this.getRoofInfo());
    concrete.setRooms(this.getRooms());
    concrete.setSurveyPoint(this.getSurveyPoint());
    concrete.setUrban(this.getUrban());
    concrete.setWall(this.getWall());
    concrete.setWallInfo(this.getWallInfo());
    concrete.setWindowType(this.getWindowType());
  }

  private void buildAttributeMap(Household concrete)
  {    
    new AttributeNotificationMap(concrete, Household.HASWINDOWS, this, HouseholdView.HASWINDOWS);
    new AttributeNotificationMap(concrete, Household.HOUSEHOLDNAME, this, HouseholdView.HOUSEHOLDNAME);
    new AttributeNotificationMap(concrete, Household.HASBEENSPRAYED, this, HouseholdView.HASBEENSPRAYED);
    new AttributeNotificationMap(concrete, Household.LASTSPRAYED, this, HouseholdView.LASTSPRAYED);
    new AttributeNotificationMap(concrete, Household.NETS, this, HouseholdView.NETS);
    new AttributeNotificationMap(concrete, Household.PEOPLE, this, HouseholdView.PEOPLE);
    new AttributeNotificationMap(concrete, Household.ROOF, this, HouseholdView.ROOF);
    new AttributeNotificationMap(concrete, Household.ROOFINFO, this, HouseholdView.ROOFINFO);
    new AttributeNotificationMap(concrete, Household.ROOMS, this, HouseholdView.ROOMS);
    new AttributeNotificationMap(concrete, Household.SURVEYPOINT, this, HouseholdView.SURVEYPOINT);
    new AttributeNotificationMap(concrete, Household.URBAN, this, HouseholdView.URBAN);
    new AttributeNotificationMap(concrete, Household.WALL, this, HouseholdView.WALL);
    new AttributeNotificationMap(concrete, Household.WALLINFO, this, HouseholdView.WALLINFO);
    new AttributeNotificationMap(concrete, Household.WINDOWTYPE, this, HouseholdView.WINDOWTYPE);
  }

  @Override
  public void apply()
  {
    Household concrete = new Household();

    if (this.hasConcrete())
    {
      concrete = Household.get(this.getConcreteId());
    }

    // Build the attribute map between Household and
    // HouseholdView for error handling
    this.buildAttributeMap(concrete);

    this.populateConcrete(concrete);

    concrete.apply();

    this.populateView(concrete);
  }

  @Override
  public void deleteConcrete()
  {
    String householdId = this.getConcreteId();

    if (this.hasConcrete())
    {
      
      Household.get(householdId).delete();
    }
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }
  
  @Override
  public SurveyedPersonView[] getSurveyedPeople()
  {
    if(this.hasConcrete())
    {
      Household household = Household.get(this.getConcreteId());
      
      OIterator<? extends SurveyedPerson> it = household.getAllSurveyedPeople();
      
      try
      {
        List<SurveyedPersonView> list = new LinkedList<SurveyedPersonView>();

        while(it.hasNext())
        {
          list.add(it.next().getView());
        }
        
        return list.toArray(new SurveyedPersonView[list.size()]);
      }
      finally
      {
        it.close();
      }
      
    }
    
    return new SurveyedPersonView[0];    
  }
  
  @Override
  public ITNInstanceView[] getItns()
  {
    if(this.hasConcrete())
    {
      Household household = Household.get(this.getConcreteId());
      
      OIterator<? extends ITNInstance> it = household.getAllITNs();
      
      try
      {
        List<ITNInstanceView> list = new LinkedList<ITNInstanceView>();

        while(it.hasNext())
        {
          list.add(it.next().getView());
        }
        
        return list.toArray(new ITNInstanceView[list.size()]);
      }
      finally
      {
        it.close();
      }
      
    }
    
    return new ITNInstanceView[0];        
  }
}
