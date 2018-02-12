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

@com.runwaysdk.business.ClassSignature(hash = 835884267)
public abstract class TransactionItemFullViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.TransactionItemFullView";
  private static final long serialVersionUID = 835884267;
  
  protected TransactionItemFullViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIONLABEL = "actionLabel";
  public static java.lang.String COMPONENTID = "componentId";
  public static java.lang.String COMPONENTSEQ = "componentSeq";
  public static java.lang.String COMPONENTSITEMASTER = "componentSiteMaster";
  public static java.lang.String ID = "id";
  public static java.lang.String RECORDID = "recordId";
  public static java.lang.String XMLRECORD = "xmlRecord";
  public String getActionLabel()
  {
    return getValue(ACTIONLABEL);
  }
  
  public void setActionLabel(String value)
  {
    if(value == null)
    {
      setValue(ACTIONLABEL, "");
    }
    else
    {
      setValue(ACTIONLABEL, value);
    }
  }
  
  public boolean isActionLabelWritable()
  {
    return isWritable(ACTIONLABEL);
  }
  
  public boolean isActionLabelReadable()
  {
    return isReadable(ACTIONLABEL);
  }
  
  public boolean isActionLabelModified()
  {
    return isModified(ACTIONLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getActionLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTIONLABEL).getAttributeMdDTO();
  }
  
  public String getComponentId()
  {
    return getValue(COMPONENTID);
  }
  
  public void setComponentId(String value)
  {
    if(value == null)
    {
      setValue(COMPONENTID, "");
    }
    else
    {
      setValue(COMPONENTID, value);
    }
  }
  
  public boolean isComponentIdWritable()
  {
    return isWritable(COMPONENTID);
  }
  
  public boolean isComponentIdReadable()
  {
    return isReadable(COMPONENTID);
  }
  
  public boolean isComponentIdModified()
  {
    return isModified(COMPONENTID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getComponentIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COMPONENTID).getAttributeMdDTO();
  }
  
  public Long getComponentSeq()
  {
    return com.runwaysdk.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(COMPONENTSEQ));
  }
  
  public void setComponentSeq(Long value)
  {
    if(value == null)
    {
      setValue(COMPONENTSEQ, "");
    }
    else
    {
      setValue(COMPONENTSEQ, java.lang.Long.toString(value));
    }
  }
  
  public boolean isComponentSeqWritable()
  {
    return isWritable(COMPONENTSEQ);
  }
  
  public boolean isComponentSeqReadable()
  {
    return isReadable(COMPONENTSEQ);
  }
  
  public boolean isComponentSeqModified()
  {
    return isModified(COMPONENTSEQ);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getComponentSeqMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(COMPONENTSEQ).getAttributeMdDTO();
  }
  
  public String getComponentSiteMaster()
  {
    return getValue(COMPONENTSITEMASTER);
  }
  
  public void setComponentSiteMaster(String value)
  {
    if(value == null)
    {
      setValue(COMPONENTSITEMASTER, "");
    }
    else
    {
      setValue(COMPONENTSITEMASTER, value);
    }
  }
  
  public boolean isComponentSiteMasterWritable()
  {
    return isWritable(COMPONENTSITEMASTER);
  }
  
  public boolean isComponentSiteMasterReadable()
  {
    return isReadable(COMPONENTSITEMASTER);
  }
  
  public boolean isComponentSiteMasterModified()
  {
    return isModified(COMPONENTSITEMASTER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getComponentSiteMasterMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COMPONENTSITEMASTER).getAttributeMdDTO();
  }
  
  public String getRecordId()
  {
    return getValue(RECORDID);
  }
  
  public void setRecordId(String value)
  {
    if(value == null)
    {
      setValue(RECORDID, "");
    }
    else
    {
      setValue(RECORDID, value);
    }
  }
  
  public boolean isRecordIdWritable()
  {
    return isWritable(RECORDID);
  }
  
  public boolean isRecordIdReadable()
  {
    return isReadable(RECORDID);
  }
  
  public boolean isRecordIdModified()
  {
    return isModified(RECORDID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getRecordIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RECORDID).getAttributeMdDTO();
  }
  
  public String getXmlRecord()
  {
    return getValue(XMLRECORD);
  }
  
  public void setXmlRecord(String value)
  {
    if(value == null)
    {
      setValue(XMLRECORD, "");
    }
    else
    {
      setValue(XMLRECORD, value);
    }
  }
  
  public boolean isXmlRecordWritable()
  {
    return isWritable(XMLRECORD);
  }
  
  public boolean isXmlRecordReadable()
  {
    return isReadable(XMLRECORD);
  }
  
  public boolean isXmlRecordModified()
  {
    return isModified(XMLRECORD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeClobMdDTO getXmlRecordMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeClobMdDTO) getAttributeDTO(XMLRECORD).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.synchronization.TransactionItemFullViewDTO getByItemId(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String itemID)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{itemID};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.synchronization.TransactionItemFullViewDTO.CLASS, "getByItemId", _declaredTypes);
    return (dss.vector.solutions.synchronization.TransactionItemFullViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TransactionItemFullViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (TransactionItemFullViewDTO) dto;
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
