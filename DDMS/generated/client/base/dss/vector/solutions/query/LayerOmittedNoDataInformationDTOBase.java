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
package dss.vector.solutions.query;

@com.runwaysdk.business.ClassSignature(hash = -1091382628)
public abstract class LayerOmittedNoDataInformationDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.LayerOmittedNoDataInformation";
  private static final long serialVersionUID = -1091382628;
  
  public LayerOmittedNoDataInformationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LAYERNAME = "layerName";
  public String getLayerName()
  {
    return getValue(LAYERNAME);
  }
  
  public void setLayerName(String value)
  {
    if(value == null)
    {
      setValue(LAYERNAME, "");
    }
    else
    {
      setValue(LAYERNAME, value);
    }
  }
  
  public boolean isLayerNameWritable()
  {
    return isWritable(LAYERNAME);
  }
  
  public boolean isLayerNameReadable()
  {
    return isReadable(LAYERNAME);
  }
  
  public boolean isLayerNameModified()
  {
    return isModified(LAYERNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getLayerNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(LAYERNAME).getAttributeMdDTO();
  }
  
}
