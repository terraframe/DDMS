package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1664865987)
public abstract class ImmatureThresholdViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ImmatureThresholdView";
  private static final long serialVersionUID = -1664865987;
  
  protected ImmatureThresholdViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DISPLAYLABEL = "displayLabel";
  public static java.lang.String ID = "id";
  public static java.lang.String THRESHOLDINDEX = "thresholdIndex";
  public static java.lang.String THRESHOLDVALUE = "thresholdValue";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelDTO getDisplayLabel()
  {
    return (dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelDTO) this.getAttributeStructDTO(DISPLAYLABEL).getStructDTO();
  }
  
  public boolean isDisplayLabelWritable()
  {
    return isWritable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelReadable()
  {
    return isReadable(DISPLAYLABEL);
  }
  
  public boolean isDisplayLabelModified()
  {
    return isModified(DISPLAYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeLocalCharacterMdDTO getDisplayLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeLocalCharacterMdDTO) getAttributeDTO(DISPLAYLABEL).getAttributeMdDTO();
  }
  
  public String getThresholdIndex()
  {
    return getValue(THRESHOLDINDEX);
  }
  
  public void setThresholdIndex(String value)
  {
    if(value == null)
    {
      setValue(THRESHOLDINDEX, "");
    }
    else
    {
      setValue(THRESHOLDINDEX, value);
    }
  }
  
  public boolean isThresholdIndexWritable()
  {
    return isWritable(THRESHOLDINDEX);
  }
  
  public boolean isThresholdIndexReadable()
  {
    return isReadable(THRESHOLDINDEX);
  }
  
  public boolean isThresholdIndexModified()
  {
    return isModified(THRESHOLDINDEX);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getThresholdIndexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(THRESHOLDINDEX).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getThresholdValue()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(THRESHOLDVALUE));
  }
  
  public void setThresholdValue(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(THRESHOLDVALUE, "");
    }
    else
    {
      setValue(THRESHOLDVALUE, value.toString());
    }
  }
  
  public boolean isThresholdValueWritable()
  {
    return isWritable(THRESHOLDVALUE);
  }
  
  public boolean isThresholdValueReadable()
  {
    return isReadable(THRESHOLDVALUE);
  }
  
  public boolean isThresholdValueModified()
  {
    return isModified(THRESHOLDVALUE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getThresholdValueMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(THRESHOLDVALUE).getAttributeMdDTO();
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureThresholdViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureThresholdViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.ImmatureThresholdViewQueryDTO getPage(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureThresholdViewDTO.CLASS, "getPage", _declaredTypes);
    return (dss.vector.solutions.entomology.ImmatureThresholdViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ImmatureThresholdViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ImmatureThresholdViewDTO) dto;
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
