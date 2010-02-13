package dss.vector.solutions.synchronization;

@com.terraframe.mojo.business.ClassSignature(hash = -64679264)
public abstract class ImportLogViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.synchronization.ImportLogView";
  private static final long serialVersionUID = -64679264;
  
  protected ImportLogViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String EXPORTSEQ = "exportSeq";
  public static java.lang.String ID = "id";
  public static java.lang.String SOURCESITE = "sourceSite";
  public Integer getExportSeq()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(EXPORTSEQ));
  }
  
  public void setExportSeq(Integer value)
  {
    if(value == null)
    {
      setValue(EXPORTSEQ, "");
    }
    else
    {
      setValue(EXPORTSEQ, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isExportSeqWritable()
  {
    return isWritable(EXPORTSEQ);
  }
  
  public boolean isExportSeqReadable()
  {
    return isReadable(EXPORTSEQ);
  }
  
  public boolean isExportSeqModified()
  {
    return isModified(EXPORTSEQ);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getExportSeqMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(EXPORTSEQ).getAttributeMdDTO();
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSourceSiteMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SOURCESITE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.synchronization.ImportLogViewQueryDTO getQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.synchronization.ImportLogViewDTO.CLASS, "getQuery", _declaredTypes);
    return (dss.vector.solutions.synchronization.ImportLogViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ImportLogViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
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
