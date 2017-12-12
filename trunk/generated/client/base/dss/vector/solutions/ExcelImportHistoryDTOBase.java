package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = -445159404)
public abstract class ExcelImportHistoryDTOBase extends com.runwaysdk.system.scheduler.JobHistoryDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.ExcelImportHistory";
  private static final long serialVersionUID = -445159404;
  
  protected ExcelImportHistoryDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ExcelImportHistoryDTOBase(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ERRORFILE = "errorFile";
  public static java.lang.String IMPORTCOUNT = "importCount";
  public static java.lang.String SERIALIZEDUNKNOWNGEOS = "serializedUnknownGeos";
  public static java.lang.String SERIALIZEDUNKNOWNTERMS = "serializedUnknownTerms";
  public static java.lang.String TOTALRECORDS = "totalRecords";
  public com.runwaysdk.system.VaultFileDTO getErrorFile()
  {
    if(getValue(ERRORFILE) == null || getValue(ERRORFILE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return com.runwaysdk.system.VaultFileDTO.get(getRequest(), getValue(ERRORFILE));
    }
  }
  
  public String getErrorFileId()
  {
    return getValue(ERRORFILE);
  }
  
  public void setErrorFile(com.runwaysdk.system.VaultFileDTO value)
  {
    if(value == null)
    {
      setValue(ERRORFILE, "");
    }
    else
    {
      setValue(ERRORFILE, value.getId());
    }
  }
  
  public boolean isErrorFileWritable()
  {
    return isWritable(ERRORFILE);
  }
  
  public boolean isErrorFileReadable()
  {
    return isReadable(ERRORFILE);
  }
  
  public boolean isErrorFileModified()
  {
    return isModified(ERRORFILE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getErrorFileMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ERRORFILE).getAttributeMdDTO();
  }
  
  public Integer getImportCount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(IMPORTCOUNT));
  }
  
  public void setImportCount(Integer value)
  {
    if(value == null)
    {
      setValue(IMPORTCOUNT, "");
    }
    else
    {
      setValue(IMPORTCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isImportCountWritable()
  {
    return isWritable(IMPORTCOUNT);
  }
  
  public boolean isImportCountReadable()
  {
    return isReadable(IMPORTCOUNT);
  }
  
  public boolean isImportCountModified()
  {
    return isModified(IMPORTCOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getImportCountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(IMPORTCOUNT).getAttributeMdDTO();
  }
  
  public String getSerializedUnknownGeos()
  {
    return getValue(SERIALIZEDUNKNOWNGEOS);
  }
  
  public void setSerializedUnknownGeos(String value)
  {
    if(value == null)
    {
      setValue(SERIALIZEDUNKNOWNGEOS, "");
    }
    else
    {
      setValue(SERIALIZEDUNKNOWNGEOS, value);
    }
  }
  
  public boolean isSerializedUnknownGeosWritable()
  {
    return isWritable(SERIALIZEDUNKNOWNGEOS);
  }
  
  public boolean isSerializedUnknownGeosReadable()
  {
    return isReadable(SERIALIZEDUNKNOWNGEOS);
  }
  
  public boolean isSerializedUnknownGeosModified()
  {
    return isModified(SERIALIZEDUNKNOWNGEOS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getSerializedUnknownGeosMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SERIALIZEDUNKNOWNGEOS).getAttributeMdDTO();
  }
  
  public String getSerializedUnknownTerms()
  {
    return getValue(SERIALIZEDUNKNOWNTERMS);
  }
  
  public void setSerializedUnknownTerms(String value)
  {
    if(value == null)
    {
      setValue(SERIALIZEDUNKNOWNTERMS, "");
    }
    else
    {
      setValue(SERIALIZEDUNKNOWNTERMS, value);
    }
  }
  
  public boolean isSerializedUnknownTermsWritable()
  {
    return isWritable(SERIALIZEDUNKNOWNTERMS);
  }
  
  public boolean isSerializedUnknownTermsReadable()
  {
    return isReadable(SERIALIZEDUNKNOWNTERMS);
  }
  
  public boolean isSerializedUnknownTermsModified()
  {
    return isModified(SERIALIZEDUNKNOWNTERMS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getSerializedUnknownTermsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(SERIALIZEDUNKNOWNTERMS).getAttributeMdDTO();
  }
  
  public Integer getTotalRecords()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALRECORDS));
  }
  
  public void setTotalRecords(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALRECORDS, "");
    }
    else
    {
      setValue(TOTALRECORDS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalRecordsWritable()
  {
    return isWritable(TOTALRECORDS);
  }
  
  public boolean isTotalRecordsReadable()
  {
    return isReadable(TOTALRECORDS);
  }
  
  public boolean isTotalRecordsModified()
  {
    return isModified(TOTALRECORDS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTotalRecordsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTALRECORDS).getAttributeMdDTO();
  }
  
  public static final void deleteAllHistory(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportHistoryDTO.CLASS, "deleteAllHistory", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.ExcelImportHistoryDTO[] getAllHistory(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportHistoryDTO.CLASS, "getAllHistory", _declaredTypes);
    return (dss.vector.solutions.ExcelImportHistoryDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static dss.vector.solutions.ExcelImportHistoryDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.EntityDTO dto = (com.runwaysdk.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.ExcelImportHistoryDTO) dto;
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
  
  public static dss.vector.solutions.ExcelImportHistoryQueryDTO getAllInstances(com.runwaysdk.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.ExcelImportHistoryQueryDTO) clientRequest.getAllInstances(dss.vector.solutions.ExcelImportHistoryDTO.CLASS, sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.ExcelImportHistoryDTO lock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportHistoryDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.ExcelImportHistoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.ExcelImportHistoryDTO unlock(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.ExcelImportHistoryDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.ExcelImportHistoryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
