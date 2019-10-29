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
package dss.vector.solutions.irs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.CurrentDateProblem;
import dss.vector.solutions.general.Disease;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.ontology.Term;

public class OperatorSpray extends OperatorSprayBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long          serialVersionUID = 1240853391117L;

  public OperatorSpray()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    
    return this.getClassDisplayLabel();
  }

  @Override
  protected String buildKey()
  {
    if (this.getBrand() != null && this.getGeoEntity() != null && this.getSprayDate() != null && this.getSprayMethodForIndex() != null  && this.getSprayOperator() != null)
    {
      DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
      String dateFormat = format.format(this.getSprayDate());
      String methodName = this.getSprayMethodForIndex();
      
      String key = this.getBrand().getKey() + "." + this.getGeoEntity().getGeoId() + "." + dateFormat + "." + methodName + "." + this.getSprayOperator().getKey();
      
      Term surfaceType = this.getSurfaceType();
      if (surfaceType != null)
      {
        key = key + "." + surfaceType.getKey();
      }

      return key;
    }

    return this.getId();
  }
  
  @Override
  public void apply()
  {
    List<SprayMethod> _sprayMethod = this.getSprayMethod();

    if(_sprayMethod.size() > 0)
    {
      this.setSprayMethodForIndex(_sprayMethod.get(0).getEnumName());      
    }
    
    this.setGeoEntityForIndex(this.getGeoEntity());
    this.setBrandForIndex(this.getBrand());
    this.setSprayDateForIndex(this.getSprayDate());
    
    
	if (this.isNew() && this.getDisease() == null) {
		this.setDisease(Disease.getCurrent());
	}
	
	validateSprayDate();
	
    super.apply();
  }
  
  @Override
  public void validateSprayDate()
  {
    if (this.getSprayDate() != null && this.getSprayDate().after(new Date()))
    {
      String msg = "It is impossible to have a spray date after the current date";

      CurrentDateProblem p = new CurrentDateProblem(msg);
      p.setGivenDate(this.getSprayDate());
      p.setCurrentDate(new Date());
      p.setNotification(this, TeamSpray.SPRAYDATE);
      p.apply();
      p.throwIt();
    }
  }

  public OperatorSprayView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public OperatorSprayView lockView()
  {
    this.lock();

    return this.getView();
  }

  public OperatorSprayView getView()
  {
    OperatorSprayView view = new OperatorSprayView();

    view.populateView(this);

    return view;
  }
  
  public static OperatorSpray get(Date sprayDate, GeoEntity geoEntity, InsecticideBrand brand, TeamMember operator, SprayMethod... sprayMethod)
  {
    OperatorSprayQuery query = new OperatorSprayQuery(new QueryFactory());
    query.WHERE(query.getSprayDate().EQ(sprayDate));
    query.WHERE(query.getGeoEntity().EQ(geoEntity));
    query.WHERE(query.getBrand().EQ(brand));
    query.WHERE(query.getSprayMethod().containsAll(sprayMethod));
    query.AND(query.getSprayOperator().EQ(operator));

    OIterator<? extends OperatorSpray> it = query.getIterator();

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
}
