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
package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1081649016)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ContainerShapeMaster.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ContainerShapeMasterBase extends com.runwaysdk.system.EnumerationMaster implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ContainerShapeMaster";
  private static final long serialVersionUID = -1081649016;
  
  public ContainerShapeMasterBase()
  {
    super();
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static ContainerShapeMasterQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    ContainerShapeMasterQuery query = new ContainerShapeMasterQuery(new com.runwaysdk.query.QueryFactory());
    com.runwaysdk.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static ContainerShapeMaster get(String id)
  {
    return (ContainerShapeMaster) com.runwaysdk.business.Business.get(id);
  }
  
  public static ContainerShapeMaster getByKey(String key)
  {
    return (ContainerShapeMaster) com.runwaysdk.business.Business.get(CLASS, key);
  }
  
  public static ContainerShapeMaster getEnumeration(String enumName)
  {
    return (ContainerShapeMaster) com.runwaysdk.business.Business.getEnumeration(dss.vector.solutions.entomology.ContainerShapeMaster.CLASS ,enumName);
  }
  
  public static ContainerShapeMaster lock(java.lang.String id)
  {
    ContainerShapeMaster _instance = ContainerShapeMaster.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static ContainerShapeMaster unlock(java.lang.String id)
  {
    ContainerShapeMaster _instance = ContainerShapeMaster.get(id);
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
