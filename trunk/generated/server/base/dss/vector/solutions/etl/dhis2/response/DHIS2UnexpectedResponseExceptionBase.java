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
package dss.vector.solutions.etl.dhis2.response;

@com.runwaysdk.business.ClassSignature(hash = 1860768186)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DHIS2UnexpectedResponseException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DHIS2UnexpectedResponseExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.etl.dhis2.response.DHIS2UnexpectedResponseException";
  public static java.lang.String DHIS2RESPONSE = "dhis2Response";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1860768186;
  
  public DHIS2UnexpectedResponseExceptionBase()
  {
    super();
  }
  
  public DHIS2UnexpectedResponseExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public DHIS2UnexpectedResponseExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public DHIS2UnexpectedResponseExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
  }
  
  public String getDhis2Response()
  {
    return getValue(DHIS2RESPONSE);
  }
  
  public void validateDhis2Response()
  {
    this.validateAttribute(DHIS2RESPONSE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getDhis2ResponseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.response.DHIS2UnexpectedResponseException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(DHIS2RESPONSE);
  }
  
  public void setDhis2Response(String value)
  {
    if(value == null)
    {
      setValue(DHIS2RESPONSE, "");
    }
    else
    {
      setValue(DHIS2RESPONSE, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.etl.dhis2.response.DHIS2UnexpectedResponseException.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{dhis2Response}", this.getDhis2Response());
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
