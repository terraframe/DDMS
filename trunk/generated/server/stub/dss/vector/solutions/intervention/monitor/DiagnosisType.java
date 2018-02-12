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

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 1827871320)
public enum DiagnosisType implements com.runwaysdk.business.BusinessEnumeration, com.runwaysdk.generation.loader.Reloadable
{
  CLINICAL_DIAGNOSIS(),
  
  NEGATIVE_DIAGNOSIS(),
  
  POSITIVE_DIAGNOSIS();
  
  public static final java.lang.String CLASS = "dss.vector.solutions.intervention.monitor.DiagnosisType";
  private dss.vector.solutions.intervention.monitor.DiagnosisTypeMaster enumeration;
  
  private synchronized void loadEnumeration()
  {
    dss.vector.solutions.intervention.monitor.DiagnosisTypeMaster enu = dss.vector.solutions.intervention.monitor.DiagnosisTypeMaster.getEnumeration(this.name());
    setEnumeration(enu);
  }
  
  private synchronized void setEnumeration(dss.vector.solutions.intervention.monitor.DiagnosisTypeMaster enumeration)
  {
    this.enumeration = enumeration;
  }
  
  public java.lang.String getId()
  {
    loadEnumeration();
    return enumeration.getId();
  }
  
  public java.lang.String getEnumName()
  {
    loadEnumeration();
    return enumeration.getEnumName();
  }
  
  public java.lang.String getDisplayLabel()
  {
    loadEnumeration();
    return enumeration.getDisplayLabel().getValue(com.runwaysdk.session.Session.getCurrentLocale());
  }
  
  public static DiagnosisType get(String id)
  {
    for (DiagnosisType e : DiagnosisType.values())
    {
      if (e.getId().equals(id))
      {
        return e;
      }
    }
    return null;
  }
  
}
