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
package dss.vector.solutions.synchronization;

@com.runwaysdk.business.ClassSignature(hash = 1182980140)
public abstract class ImportLogViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.ImportLogView";
  private static final long serialVersionUID = 1182980140;
  
  protected ImportLogViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String LASTEXPORTSEQ = "lastExportSeq";
  public static java.lang.String SOURCESITE = "sourceSite";
  public Integer getLastExportSeq()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(LASTEXPORTSEQ));
  }
  
  public void setLastExportSeq(Integer value)
  {
    if(value == null)
    {
      setValue(LASTEXPORTSEQ, "");
    }
    else
    {
      setValue(LASTEXPORTSEQ, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isLastExportSeqWritable()
  {
    return isWritable(LASTEXPORTSEQ);
  }
  
  public boolean isLastExportSeqReadable()
  {
    return isReadable(LASTEXPORTSEQ);
  }
  
  public boolean isLastExportSeqModified()
  {
    return isModified(LASTEXPORTSEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getLastExportSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(LASTEXPORTSEQ).getAttributeMdDTO();
  }
  
  public String getSourceSite()
  {
    return getValue(SOURCESITE);
  }
  
  public void setSourceSite(String value)
  {
    if(value == null)
    {
      setValue(SOURCESITE, "");
    }
    else
    {
      setValue(SOURCESITE, value);
    }
  }
  
  public boolean isSourceSiteWritable()
  {
    return isWritable(SOURCESITE);
  }
  
  public boolean isSourceSiteReadable()
  {
    return isReadable(SOURCESITE);
  }
  
  public boolean isSourceSiteModified()
  {
    return isModified(SOURCESITE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSourceSiteMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SOURCESITE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.synchronization.ImportLogViewQueryDTO getQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.ImportLogViewDTO.CLASS, "getQuery", _declaredTypes);
    return (dss.vector.solutions.synchronization.ImportLogViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ImportLogViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ImportLogViewDTO) dto;
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
