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
package dss.vector.solutions.generator;

@com.runwaysdk.business.ClassSignature(hash = -974430343)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DuplicateFormInstanceException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DuplicateFormInstanceExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.generator.DuplicateFormInstanceException";
  public static java.lang.String FORMID = "formId";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -974430343;
  
  public DuplicateFormInstanceExceptionBase()
  {
    super();
  }
  
  public DuplicateFormInstanceExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DuplicateFormInstanceExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DuplicateFormInstanceExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getFormId()
  {
    return getValue(FORMID);
  }
  
  public void validateFormId()
  {
    this.validateAttribute(FORMID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getFormIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.generator.DuplicateFormInstanceException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(FORMID);
  }
  
  public void setFormId(String value)
  {
    if(value == null)
    {
      setValue(FORMID, "");
    }
    else
    {
      setValue(FORMID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.generator.DuplicateFormInstanceException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{formId}", this.getFormId());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
