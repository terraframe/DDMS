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

@com.runwaysdk.business.ClassSignature(hash = 844694051)
public abstract class IgnoredColumnInformationDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.IgnoredColumnInformation";
  private static final long serialVersionUID = 844694051;
  
  public IgnoredColumnInformationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLUMNNAME = "columnName";
  public static java.lang.String ID = "id";
  public String getColumnName()
  {
    return getValue(COLUMNNAME);
  }
  
  public void setColumnName(String value)
  {
    if(value == null)
    {
      setValue(COLUMNNAME, "");
    }
    else
    {
      setValue(COLUMNNAME, value);
    }
  }
  
  public boolean isColumnNameWritable()
  {
    return isWritable(COLUMNNAME);
  }
  
  public boolean isColumnNameReadable()
  {
    return isReadable(COLUMNNAME);
  }
  
  public boolean isColumnNameModified()
  {
    return isModified(COLUMNNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getColumnNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(COLUMNNAME).getAttributeMdDTO();
  }
  
}
