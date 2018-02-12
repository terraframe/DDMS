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

@com.runwaysdk.business.ClassSignature(hash = 403036245)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to EmptyValueProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class EmptyValueProblemBase extends dss.vector.solutions.NotificationProblem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.EmptyValueProblem";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  private static final long serialVersionUID = 403036245;
  
  public EmptyValueProblemBase()
  {
    super();
  }
  
  public EmptyValueProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void validateEntityLabel()
  {
    this.validateAttribute(ENTITYLABEL);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getEntityLabelMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.EmptyValueProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{entityLabel}", this.getEntityLabel());
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
