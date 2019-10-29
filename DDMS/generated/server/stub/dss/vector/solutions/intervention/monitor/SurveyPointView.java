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

import dss.vector.solutions.geo.generated.GeoEntity;

public class SurveyPointView extends SurveyPointViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1242407822127L;
  
  public SurveyPointView()
  {
    super();
  }
  
  public void deleteConcrete()
  {
    if(this.hasConcrete())
    {
      SurveyPoint.get(this.getConcreteId()).delete();
    }
  }
  
  public Household[] getHouseholds()
  {
    if(this.hasConcrete())
    {
      SurveyPoint point = SurveyPoint.get(this.getConcreteId());
      List<Household> list = new LinkedList<Household>();
      
      OIterator<? extends Household> it = point.getAllHouseholds();
      
      try
      {
        while(it.hasNext())
        {
          list.add(it.next());
        }
        
        return list.toArray(new Household[list.size()]);
      }
      finally
      {
        it.close();
      }      
    }
    
    return new Household[0];
  }
  
  @Override
  public HouseholdView[] getHouseholdViews()
  {
    Household[] households = this.getHouseholds();
    HouseholdView[] views = new HouseholdView[households.length];
    
    for(int i = 0; i < households.length; i++)
    {
      views[i] = households[i].getView();
    }
    
    return views;
  }
  
  public void apply()
  {
    SurveyPoint point = new SurveyPoint();
    
    if(this.hasConcrete())
    {
      point = SurveyPoint.lock(this.getConcreteId());
    }

    this.populateMapping(point);
    
    this.populateConcrete(point);

    point.apply();
    
    this.populateView(point);
  }

  private void populateMapping(SurveyPoint point)
  {
    new AttributeNotificationMap(point, SurveyPoint.GEOENTITY, this, SurveyPointView.GEOID);
    new AttributeNotificationMap(point, SurveyPoint.SURVEYDATE, this, SurveyPointView.SURVEYDATE);
  }

  public void populateView(SurveyPoint point)
  {
    this.setGeoId(point.getGeoEntity().getGeoId());
    this.setSurveyDate(point.getSurveyDate());
    this.setConcreteId(point.getId());
  }

  public void populateConcrete(SurveyPoint point)
  {
    GeoEntity geoEntity = GeoEntity.searchByGeoId(this.getGeoId());
    
    point.setGeoEntity(geoEntity);
    point.setSurveyDate(this.getSurveyDate());
  }

  private boolean hasConcrete()
  {
    return this.getConcreteId() != null && !this.getConcreteId().equals("");
  }  
}
