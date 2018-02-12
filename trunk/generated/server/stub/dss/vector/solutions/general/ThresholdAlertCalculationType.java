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
package dss.vector.solutions.general;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.geo.GeoHierarchy;

public class ThresholdAlertCalculationType extends ThresholdAlertCalculationTypeBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 168898279;

  public ThresholdAlertCalculationType()
  {
    super();
  }

  @Override
  public void apply()
  {
    if (this.isNew() && this.getDisease() == null)
    {
      this.setDisease(Disease.getCurrent());
    }
    super.apply();
  }

  @Override
  protected String buildKey()
  {
    return this.getDisease().getKeyName();
  }

  public static ThresholdAlertCalculationType getCurrent()
  {
    return getCurrent(Disease.getCurrent());
  }
  
  public static ThresholdAlertCalculationType getCurrent(Disease disease)
  {
    return getByKey(disease.getKeyName());
  }

  public static List<GeoHierarchy> getEpidemicUniversals()
  {
    List<GeoHierarchy> list = new LinkedList<GeoHierarchy>();
    ThresholdAlertCalculationTypeQuery query = new ThresholdAlertCalculationTypeQuery(new QueryFactory());
    OIterator<? extends ThresholdAlertCalculationType> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        ThresholdAlertCalculationType configuration = it.next();
        
        list.add(configuration.getEpidemicUniversal());
      }
      
      return list;
    }
    finally
    {
      it.close();
    }
  }
}
