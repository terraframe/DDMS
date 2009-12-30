package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = 183572800)
public abstract class KnockDownAssayDTOBase extends dss.vector.solutions.entomology.assay.AdultAssayDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.KnockDownAssay";
  private static final long serialVersionUID = 183572800;
  
  protected KnockDownAssayDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected KnockDownAssayDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String INTERVAL10 = "interval10";
  public static java.lang.String INTERVAL20 = "interval20";
  public static java.lang.String INTERVAL30 = "interval30";
  public static java.lang.String INTERVAL40 = "interval40";
  public static java.lang.String INTERVAL50 = "interval50";
  public static java.lang.String INTERVAL60 = "interval60";
  public static java.lang.String KD50 = "kd50";
  public static java.lang.String KD95 = "kd95";
  public Integer getInterval10()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVAL10));
  }
  
  public void setInterval10(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVAL10, "");
    }
    else
    {
      setValue(INTERVAL10, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInterval10Writable()
  {
    return isWritable(INTERVAL10);
  }
  
  public boolean isInterval10Readable()
  {
    return isReadable(INTERVAL10);
  }
  
  public boolean isInterval10Modified()
  {
    return isModified(INTERVAL10);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInterval10Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVAL10).getAttributeMdDTO();
  }
  
  public Integer getInterval20()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVAL20));
  }
  
  public void setInterval20(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVAL20, "");
    }
    else
    {
      setValue(INTERVAL20, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInterval20Writable()
  {
    return isWritable(INTERVAL20);
  }
  
  public boolean isInterval20Readable()
  {
    return isReadable(INTERVAL20);
  }
  
  public boolean isInterval20Modified()
  {
    return isModified(INTERVAL20);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInterval20Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVAL20).getAttributeMdDTO();
  }
  
  public Integer getInterval30()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVAL30));
  }
  
  public void setInterval30(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVAL30, "");
    }
    else
    {
      setValue(INTERVAL30, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInterval30Writable()
  {
    return isWritable(INTERVAL30);
  }
  
  public boolean isInterval30Readable()
  {
    return isReadable(INTERVAL30);
  }
  
  public boolean isInterval30Modified()
  {
    return isModified(INTERVAL30);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInterval30Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVAL30).getAttributeMdDTO();
  }
  
  public Integer getInterval40()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVAL40));
  }
  
  public void setInterval40(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVAL40, "");
    }
    else
    {
      setValue(INTERVAL40, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInterval40Writable()
  {
    return isWritable(INTERVAL40);
  }
  
  public boolean isInterval40Readable()
  {
    return isReadable(INTERVAL40);
  }
  
  public boolean isInterval40Modified()
  {
    return isModified(INTERVAL40);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInterval40Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVAL40).getAttributeMdDTO();
  }
  
  public Integer getInterval50()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVAL50));
  }
  
  public void setInterval50(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVAL50, "");
    }
    else
    {
      setValue(INTERVAL50, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInterval50Writable()
  {
    return isWritable(INTERVAL50);
  }
  
  public boolean isInterval50Readable()
  {
    return isReadable(INTERVAL50);
  }
  
  public boolean isInterval50Modified()
  {
    return isModified(INTERVAL50);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInterval50Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVAL50).getAttributeMdDTO();
  }
  
  public Integer getInterval60()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVAL60));
  }
  
  public void setInterval60(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVAL60, "");
    }
    else
    {
      setValue(INTERVAL60, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isInterval60Writable()
  {
    return isWritable(INTERVAL60);
  }
  
  public boolean isInterval60Readable()
  {
    return isReadable(INTERVAL60);
  }
  
  public boolean isInterval60Modified()
  {
    return isModified(INTERVAL60);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getInterval60Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVAL60).getAttributeMdDTO();
  }
  
  public Double getKd50()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD50));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getKd50Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(KD50).getAttributeMdDTO();
  }
  
  public Double getKd95()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD95));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getKd95Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(KD95).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.entomology.assay.KnockDownAssayDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.entomology.assay.KnockDownAssayDTO) dto;
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
  
  public static dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.entomology.assay.KnockDownAssay", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.KnockDownAssayDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.KnockDownAssayDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.KnockDownAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.entomology.assay.KnockDownAssayDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.KnockDownAssayDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.KnockDownAssayDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
