package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -130577691)
public abstract class AdultDiscriminatingDoseAssayDTOBase extends dss.vector.solutions.entomology.assay.AdultAssayDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay";
  private static final long serialVersionUID = -130577691;
  
  protected AdultDiscriminatingDoseAssayDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AdultDiscriminatingDoseAssayDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String KD50 = "kd50";
  public static java.lang.String KD95 = "kd95";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  public Float getControlTestMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(CONTROLTESTMORTALITY));
  }
  
  public void setControlTestMortality(Float value)
  {
    if(value == null)
    {
      setValue(CONTROLTESTMORTALITY, "");
    }
    else
    {
      setValue(CONTROLTESTMORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public boolean isControlTestMortalityWritable()
  {
    return isWritable(CONTROLTESTMORTALITY);
  }
  
  public boolean isControlTestMortalityReadable()
  {
    return isReadable(CONTROLTESTMORTALITY);
  }
  
  public boolean isControlTestMortalityModified()
  {
    return isModified(CONTROLTESTMORTALITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getControlTestMortalityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(CONTROLTESTMORTALITY).getAttributeMdDTO();
  }
  
  public Integer getHoldingTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
  }
  
  public void setHoldingTime(Integer value)
  {
    if(value == null)
    {
      setValue(HOLDINGTIME, "");
    }
    else
    {
      setValue(HOLDINGTIME, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isHoldingTimeWritable()
  {
    return isWritable(HOLDINGTIME);
  }
  
  public boolean isHoldingTimeReadable()
  {
    return isReadable(HOLDINGTIME);
  }
  
  public boolean isHoldingTimeModified()
  {
    return isModified(HOLDINGTIME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getHoldingTimeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(HOLDINGTIME).getAttributeMdDTO();
  }
  
  public Double getKd50()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD50));
  }
  
  public void setKd50(Double value)
  {
    if(value == null)
    {
      setValue(KD50, "");
    }
    else
    {
      setValue(KD50, java.lang.Double.toString(value));
    }
  }
  
  public boolean isKd50Writable()
  {
    return isWritable(KD50);
  }
  
  public boolean isKd50Readable()
  {
    return isReadable(KD50);
  }
  
  public boolean isKd50Modified()
  {
    return isModified(KD50);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getKd50Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(KD50).getAttributeMdDTO();
  }
  
  public Double getKd95()
  {
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD95));
  }
  
  public void setKd95(Double value)
  {
    if(value == null)
    {
      setValue(KD95, "");
    }
    else
    {
      setValue(KD95, java.lang.Double.toString(value));
    }
  }
  
  public boolean isKd95Writable()
  {
    return isWritable(KD95);
  }
  
  public boolean isKd95Readable()
  {
    return isReadable(KD95);
  }
  
  public boolean isKd95Modified()
  {
    return isModified(KD95);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getKd95Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(KD95).getAttributeMdDTO();
  }
  
  public Float getMortality()
  {
    return com.runwaysdk.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
  }
  
  public void setMortality(Float value)
  {
    if(value == null)
    {
      setValue(MORTALITY, "");
    }
    else
    {
      setValue(MORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public boolean isMortalityWritable()
  {
    return isWritable(MORTALITY);
  }
  
  public boolean isMortalityReadable()
  {
    return isReadable(MORTALITY);
  }
  
  public boolean isMortalityModified()
  {
    return isModified(MORTALITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getMortalityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(MORTALITY).getAttributeMdDTO();
  }
  
  public Integer getQuantityDead()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
  }
  
  public void setQuantityDead(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYDEAD, "");
    }
    else
    {
      setValue(QUANTITYDEAD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityDeadWritable()
  {
    return isWritable(QUANTITYDEAD);
  }
  
  public boolean isQuantityDeadReadable()
  {
    return isReadable(QUANTITYDEAD);
  }
  
  public boolean isQuantityDeadModified()
  {
    return isModified(QUANTITYDEAD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityDeadMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYDEAD).getAttributeMdDTO();
  }
  
  public Integer getQuantityLive()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
  }
  
  public void setQuantityLive(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYLIVE, "");
    }
    else
    {
      setValue(QUANTITYLIVE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityLiveWritable()
  {
    return isWritable(QUANTITYLIVE);
  }
  
  public boolean isQuantityLiveReadable()
  {
    return isReadable(QUANTITYLIVE);
  }
  
  public boolean isQuantityLiveModified()
  {
    return isModified(QUANTITYLIVE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityLiveMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITYLIVE).getAttributeMdDTO();
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void setUniqueAssayId(String value)
  {
    if(value == null)
    {
      setValue(UNIQUEASSAYID, "");
    }
    else
    {
      setValue(UNIQUEASSAYID, value);
    }
  }
  
  public boolean isUniqueAssayIdWritable()
  {
    return isWritable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdReadable()
  {
    return isReadable(UNIQUEASSAYID);
  }
  
  public boolean isUniqueAssayIdModified()
  {
    return isModified(UNIQUEASSAYID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUniqueAssayIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNIQUEASSAYID).getAttributeMdDTO();
  }
  
  public final void applyAll(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO[] intervals)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalView;"};
    Object[] _parameters = new Object[]{intervals};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "applyAll", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO[] intervals)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalView;"};
    Object[] _parameters = new Object[]{id, intervals};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "applyAll", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportQueryToCSV(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "exportQueryToCSV", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportQueryToExcel(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, savedSearchId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "exportQueryToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO[] getIntervals()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "getIntervals", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO[] getIntervals(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "getIntervals", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String mapQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "[Ljava.lang.String;", "java.lang.String"};
    Object[] _parameters = new Object[]{queryXML, config, universalLayers, savedSearchId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "mapQuery", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO queryResistance(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{queryXML, config, sortBy, ascending, pageNumber, pageSize};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "queryResistance", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO xmlToValueQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String xml, java.lang.String[] selectedUniversals, java.lang.Boolean includeGeometry)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ljava.lang.String;", "java.lang.Boolean"};
    Object[] _parameters = new Object[]{xml, selectedUniversals, includeGeometry};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "xmlToValueQuery", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
