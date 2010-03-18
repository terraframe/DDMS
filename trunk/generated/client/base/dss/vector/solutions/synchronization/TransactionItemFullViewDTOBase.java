package dss.vector.solutions.synchronization;

@com.terraframe.mojo.business.ClassSignature(hash = -463210950)
public abstract class TransactionItemFullViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.TransactionItemFullView";
  private static final long serialVersionUID = -463210950;
  
  protected TransactionItemFullViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getActionLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTIONLABEL).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getComponentIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COMPONENTID).getAttributeMdDTO();
  }
  
  public Long getComponentSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeLongUtil.getTypeSafeValue(getValue(COMPONENTSEQ));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getComponentSeqMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(COMPONENTSEQ).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getComponentSiteMasterMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COMPONENTSITEMASTER).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getRecordIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(RECORDID).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeTextMdDTO getXmlRecordMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeTextMdDTO) getAttributeDTO(XMLRECORD).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.synchronization.TransactionItemFullViewDTO getByItemId(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String itemID)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{itemID};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.TransactionItemFullViewDTO.CLASS, "getByItemId", _declaredTypes);
    return (dss.vector.solutions.synchronization.TransactionItemFullViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TransactionItemFullViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
