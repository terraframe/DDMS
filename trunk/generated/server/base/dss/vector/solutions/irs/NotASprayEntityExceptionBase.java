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

@com.runwaysdk.business.ClassSignature(hash = -1354041256)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to NotASprayEntityException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class NotASprayEntityExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.NotASprayEntityException";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1354041256;
  
  public NotASprayEntityExceptionBase()
  {
    super();
  }
  
  public NotASprayEntityExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public NotASprayEntityExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public NotASprayEntityExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NotASprayEntityException.CLASS);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.irs.NotASprayEntityException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{entityLabel}", this.getEntityLabel());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
