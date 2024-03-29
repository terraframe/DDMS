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
package dss.vector.solutions.ontology;

@com.runwaysdk.business.ClassSignature(hash = -93714755)
public abstract class TermSynonymViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ontology.TermSynonymView";
  private static final long serialVersionUID = -93714755;
  
  protected TermSynonymViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SYNONYMNAME = "synonymName";
  public static java.lang.String TERMSYNONYMID = "termSynonymId";
  public String getSynonymName()
  {
    return getValue(SYNONYMNAME);
  }
  
  public void setSynonymName(String value)
  {
    if(value == null)
    {
      setValue(SYNONYMNAME, "");
    }
    else
    {
      setValue(SYNONYMNAME, value);
    }
  }
  
  public boolean isSynonymNameWritable()
  {
    return isWritable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameReadable()
  {
    return isReadable(SYNONYMNAME);
  }
  
  public boolean isSynonymNameModified()
  {
    return isModified(SYNONYMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSynonymNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SYNONYMNAME).getAttributeMdDTO();
  }
  
  public String getTermSynonymId()
  {
    return getValue(TERMSYNONYMID);
  }
  
  public void setTermSynonymId(String value)
  {
    if(value == null)
    {
      setValue(TERMSYNONYMID, "");
    }
    else
    {
      setValue(TERMSYNONYMID, value);
    }
  }
  
  public boolean isTermSynonymIdWritable()
  {
    return isWritable(TERMSYNONYMID);
  }
  
  public boolean isTermSynonymIdReadable()
  {
    return isReadable(TERMSYNONYMID);
  }
  
  public boolean isTermSynonymIdModified()
  {
    return isModified(TERMSYNONYMID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTermSynonymIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TERMSYNONYMID).getAttributeMdDTO();
  }
  
  public static TermSynonymViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (TermSynonymViewDTO) dto;
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
