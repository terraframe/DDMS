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
package dss.vector.solutions.general;

@com.runwaysdk.business.ClassSignature(hash = -240273202)
public abstract class EpiConfigurationDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.EpiConfiguration";
  private static final long serialVersionUID = -240273202;
  
  protected EpiConfigurationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FIRSTDAY = "firstDay";
  public static java.lang.String ID = "id";
  public String getFirstDay()
  {
    return getValue(FIRSTDAY);
  }
  
  public void setFirstDay(String value)
  {
    if(value == null)
    {
      setValue(FIRSTDAY, "");
    }
    else
    {
      setValue(FIRSTDAY, value);
    }
  }
  
  public boolean isFirstDayWritable()
  {
    return isWritable(FIRSTDAY);
  }
  
  public boolean isFirstDayReadable()
  {
    return isReadable(FIRSTDAY);
  }
  
  public boolean isFirstDayModified()
  {
    return isModified(FIRSTDAY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getFirstDayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FIRSTDAY).getAttributeMdDTO();
  }
  
  public static EpiConfigurationDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (EpiConfigurationDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createSessionComponent(this);
    }
    else
    {
      getRequest().update(this);
    }
  }
  public void delete()
  {
    getRequest().delete(this.getId());
  }
  
}
