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
package dss.vector.solutions.permission;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 1287075330)
public enum PermissionOptionDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  NONE(),
  
  READ(),
  
  WRITE();
  
  public final static String CLASS = "dss.vector.solutions.permission.PermissionOption";
  
  
  public dss.vector.solutions.permission.PermissionOptionMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.permission.PermissionOptionMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.permission.PermissionOptionDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, PermissionOptionDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.permission.PermissionOptionDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.permission.PermissionOptionMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.permission.PermissionOptionDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
