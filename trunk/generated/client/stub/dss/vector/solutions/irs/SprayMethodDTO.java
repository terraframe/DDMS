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

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 2126078006)
public enum SprayMethodDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  MAIN_SPRAY(),
  
  MOP_UP();
  
  public final static String CLASS = "dss.vector.solutions.irs.SprayMethod";
  
  
  public dss.vector.solutions.irs.SprayMethodMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.irs.SprayMethodMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.irs.SprayMethodDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.SprayMethodMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, SprayMethodDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.irs.SprayMethodMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.irs.SprayMethodDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.SprayMethodMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.irs.SprayMethodMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.irs.SprayMethodDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
