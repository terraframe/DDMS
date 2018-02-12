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

@com.runwaysdk.business.ClassSignature(hash = 1867209852)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to SprayMethodMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class SprayMethodMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayMethodMaster";
  private static final long serialVersionUID = 1867209852;
  
  public SprayMethodMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static SprayMethodMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    SprayMethodMasterQuery query = new SprayMethodMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static SprayMethodMaster get(String id)
  {
    return (SprayMethodMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static SprayMethodMaster getByKey(String key)
  {
    return (SprayMethodMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static SprayMethodMaster getEnumeration(String enumName)
  {
    return (SprayMethodMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.irs.SprayMethodMaster.CLASS ,enumName);
  }
  
  public static SprayMethodMaster lock(java.lang.String id)
  {
    SprayMethodMaster _instance = SprayMethodMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static SprayMethodMaster unlock(java.lang.String id)
  {
    SprayMethodMaster _instance = SprayMethodMaster.get(id);
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
