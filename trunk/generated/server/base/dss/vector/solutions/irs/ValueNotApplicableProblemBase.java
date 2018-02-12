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

@com.runwaysdk.business.ClassSignature(hash = -1016633743)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to ValueNotApplicableProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class ValueNotApplicableProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ValueNotApplicableProblem";
  public static java.lang.String HOUSEHOLDID = "householdId";
  public static java.lang.String STRUCTUREID = "structureId";
  private static final long serialVersionUID = -1016633743;
  
  public ValueNotApplicableProblemBase()
  {
    super();
  }
  
  public ValueNotApplicableProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getHouseholdId()
  {
    return getValue(HOUSEHOLDID);
  }
  
  public void validateHouseholdId()
  {
    this.validateAttribute(HOUSEHOLDID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getHouseholdIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ValueNotApplicableProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(HOUSEHOLDID);
  }
  
  public void setHouseholdId(String value)
  {
    if(value == null)
    {
      setValue(HOUSEHOLDID, "");
    }
    else
    {
      setValue(HOUSEHOLDID, value);
    }
  }
  
  public String getStructureId()
  {
    return getValue(STRUCTUREID);
  }
  
  public void validateStructureId()
  {
    this.validateAttribute(STRUCTUREID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getStructureIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.ValueNotApplicableProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(STRUCTUREID);
  }
  
  public void setStructureId(String value)
  {
    if(value == null)
    {
      setValue(STRUCTUREID, "");
    }
    else
    {
      setValue(STRUCTUREID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{householdId}", this.getHouseholdId());
    message = replace(message, "{structureId}", this.getStructureId());
    return message;
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
