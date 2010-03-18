package dss.vector.solutions.synchronization;

@com.terraframe.mojo.business.ClassSignature(hash = 394629109)
public abstract class TransactionItemViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.TransactionItemView";
  private static final long serialVersionUID = 394629109;
  
  protected TransactionItemViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIONLABEL = "actionLabel";
  public static java.lang.String COMPONENTID = "componentId";
  public static java.lang.String COMPONENTLABEL = "componentLabel";
  public static java.lang.String COMPONENTSEQ = "componentSeq";
  public static java.lang.String COMPONENTSITEMASTER = "componentSiteMaster";
  public static java.lang.String ID = "id";
  public static java.lang.String ITEMID = "itemId";
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
  
  public String getComponentLabel()
  {
    return getValue(COMPONENTLABEL);
  }
  
  public void setComponentLabel(String value)
  {
    if(value == null)
    {
      setValue(COMPONENTLABEL, "");
    }
    else
    {
      setValue(COMPONENTLABEL, value);
    }
  }
  
  public boolean isComponentLabelWritable()
  {
    return isWritable(COMPONENTLABEL);
  }
  
  public boolean isComponentLabelReadable()
  {
    return isReadable(COMPONENTLABEL);
  }
  
  public boolean isComponentLabelModified()
  {
    return isModified(COMPONENTLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getComponentLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COMPONENTLABEL).getAttributeMdDTO();
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
  
  public String getItemId()
  {
    return getValue(ITEMID);
  }
  
  public void setItemId(String value)
  {
    if(value == null)
    {
      setValue(ITEMID, "");
    }
    else
    {
      setValue(ITEMID, value);
    }
  }
  
  public boolean isItemIdWritable()
  {
    return isWritable(ITEMID);
  }
  
  public boolean isItemIdReadable()
  {
    return isReadable(ITEMID);
  }
  
  public boolean isItemIdModified()
  {
    return isModified(ITEMID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getItemIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ITEMID).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.synchronization.TransactionItemViewQueryDTO getQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String recordId, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{recordId, sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.TransactionItemViewDTO.CLASS, "getQuery", _declaredTypes);
    return (dss.vector.solutions.synchronization.TransactionItemViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.system.transaction.TransactionRecordQueryDTO getRecordQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.TransactionItemViewDTO.CLASS, "getRecordQuery", _declaredTypes);
    return (com.terraframe.mojo.system.transaction.TransactionRecordQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TransactionItemViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (TransactionItemViewDTO) dto;
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
