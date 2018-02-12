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
package dss.vector.solutions.kaleidoscope.dashboard;

@com.runwaysdk.business.ClassSignature(hash = -1479410851)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to GeometryAggregationStrategy.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class GeometryAggregationStrategyBase extends dss.vector.solutions.kaleidoscope.dashboard.AggregationStrategy implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.kaleidoscope.dashboard.GeometryAggregationStrategy";
  private static final long serialVersionUID = -1479410851;
  
  public GeometryAggregationStrategyBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static GeometryAggregationStrategyQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    GeometryAggregationStrategyQuery query = new GeometryAggregationStrategyQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static GeometryAggregationStrategy get(String id)
  {
    return (GeometryAggregationStrategy) com.runwaysdk.business.Business.get(id);
  }
  
  public static GeometryAggregationStrategy getByKey(String key)
  {
    return (GeometryAggregationStrategy) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static GeometryAggregationStrategy lock(java.lang.String id)
  {
    GeometryAggregationStrategy _instance = GeometryAggregationStrategy.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static GeometryAggregationStrategy unlock(java.lang.String id)
  {
    GeometryAggregationStrategy _instance = GeometryAggregationStrategy.get(id);
    _instance.unlock();
    
    return _instance;
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
