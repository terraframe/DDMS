package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 415692865)
public abstract class ExcelImportManagerDTOBase extends com.runwaysdk.business.UtilDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ExcelImportManager";
  private static final long serialVersionUID = 415692865;
  
  protected ExcelImportManagerDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String SERIALIZEDUNKNOWNTERM = "serializedUnknownTerm";
  public static java.lang.String UNMATCHEDGEOVIEWIDSTRING = "unmatchedGeoViewIdString";
  public String getSerializedUnknownTerm()
  {
    return getValue(SERIALIZEDUNKNOWNTERM);
  }
  
  public void setSerializedUnknownTerm(String value)
  {
    if(value == null)
    {
      setValue(SERIALIZEDUNKNOWNTERM, "");
    }
    else
    {
      setValue(SERIALIZEDUNKNOWNTERM, value);
    }
  }
  
  public boolean isSerializedUnknownTermWritable()
  {
    return isWritable(SERIALIZEDUNKNOWNTERM);
  }
  
  public boolean isSerializedUnknownTermReadable()
  {
    return isReadable(SERIALIZEDUNKNOWNTERM);
  }
  
  public boolean isSerializedUnknownTermModified()
  {
    return isModified(SERIALIZEDUNKNOWNTERM);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getSerializedUnknownTermMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SERIALIZEDUNKNOWNTERM).getAttributeMdDTO();
  }
  
  public String getUnmatchedGeoViewIdString()
  {
    return getValue(UNMATCHEDGEOVIEWIDSTRING);
  }
  
  public void setUnmatchedGeoViewIdString(String value)
  {
    if(value == null)
    {
      setValue(UNMATCHEDGEOVIEWIDSTRING, "");
    }
    else
    {
      setValue(UNMATCHEDGEOVIEWIDSTRING, value);
    }
  }
  
  public boolean isUnmatchedGeoViewIdStringWritable()
  {
    return isWritable(UNMATCHEDGEOVIEWIDSTRING);
  }
  
  public boolean isUnmatchedGeoViewIdStringReadable()
  {
    return isReadable(UNMATCHEDGEOVIEWIDSTRING);
  }
  
  public boolean isUnmatchedGeoViewIdStringModified()
  {
    return isModified(UNMATCHEDGEOVIEWIDSTRING);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getUnmatchedGeoViewIdStringMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(UNMATCHEDGEOVIEWIDSTRING).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.ExcelImportManagerDTO getNewInstance(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "getNewInstance", _declaredTypes);
    return (dss.vector.solutions.ExcelImportManagerDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.ontology.UnknownTermDTO[] getUnknownTerms()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "getUnknownTerms", _declaredTypes);
    return (dss.vector.solutions.ontology.UnknownTermDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.ontology.UnknownTermDTO[] getUnknownTerms(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "getUnknownTerms", _declaredTypes);
    return (dss.vector.solutions.ontology.UnknownTermDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.geo.UnknownGeoEntityDTO[] getUnmatchedGeoViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "getUnmatchedGeoViews", _declaredTypes);
    return (dss.vector.solutions.geo.UnknownGeoEntityDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.geo.UnknownGeoEntityDTO[] getUnmatchedGeoViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "getUnmatchedGeoViews", _declaredTypes);
    return (dss.vector.solutions.geo.UnknownGeoEntityDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final java.io.InputStream importWhatYouCan(java.io.InputStream inputStream, java.lang.String[] params, java.lang.String fileName)
  {
    String[] _declaredTypes = new String[]{"java.io.InputStream", "[Ljava.lang.String;", "java.lang.String"};
    Object[] _parameters = new Object[]{inputStream, params, fileName};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "importWhatYouCan", _declaredTypes);
    return (java.io.InputStream) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final java.io.InputStream importWhatYouCan(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.io.InputStream inputStream, java.lang.String[] params, java.lang.String fileName)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.io.InputStream", "[Ljava.lang.String;", "java.lang.String"};
    Object[] _parameters = new Object[]{id, inputStream, params, fileName};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportManagerDTO.CLASS, "importWhatYouCan", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ExcelImportManagerDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.UtilDTO dto = (com.runwaysdk.business.UtilDTO)clientRequest.get(id);
    
    return (ExcelImportManagerDTO) dto;
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
