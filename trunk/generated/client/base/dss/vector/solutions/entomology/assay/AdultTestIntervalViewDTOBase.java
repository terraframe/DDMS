package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -548592285)
public abstract class AdultTestIntervalViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.AdultTestIntervalView";
  private static final long serialVersionUID = -548592285;
  
  protected AdultTestIntervalViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVALID = "intervalId";
  public static java.lang.String INTERVALTIME = "intervalTime";
  public static java.lang.String KNOCKEDDOWN = "knockedDown";
  public static java.lang.String PERIOD = "period";
  public dss.vector.solutions.entomology.assay.AdultAssayDTO getAssay()
  {
    if(getValue(ASSAY) == null || getValue(ASSAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.assay.AdultAssayDTO.get(getRequest(), getValue(ASSAY));
    }
  }
  
  public void setAssay(dss.vector.solutions.entomology.assay.AdultAssayDTO value)
  {
    if(value == null)
    {
      setValue(ASSAY, "");
    }
    else
    {
      setValue(ASSAY, value.getId());
    }
  }
  
  public boolean isAssayWritable()
  {
    return isWritable(ASSAY);
  }
  
  public boolean isAssayReadable()
  {
    return isReadable(ASSAY);
  }
  
  public boolean isAssayModified()
  {
    return isModified(ASSAY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAssayMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ASSAY).getAttributeMdDTO();
  }
  
  public String getIntervalId()
  {
    return getValue(INTERVALID);
  }
  
  public void setIntervalId(String value)
  {
    if(value == null)
    {
      setValue(INTERVALID, "");
    }
    else
    {
      setValue(INTERVALID, value);
    }
  }
  
  public boolean isIntervalIdWritable()
  {
    return isWritable(INTERVALID);
  }
  
  public boolean isIntervalIdReadable()
  {
    return isReadable(INTERVALID);
  }
  
  public boolean isIntervalIdModified()
  {
    return isModified(INTERVALID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getIntervalIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(INTERVALID).getAttributeMdDTO();
  }
  
  public Integer getIntervalTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVALTIME));
  }
  
  public void setIntervalTime(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVALTIME, "");
    }
    else
    {
      setValue(INTERVALTIME, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isIntervalTimeWritable()
  {
    return isWritable(INTERVALTIME);
  }
  
  public boolean isIntervalTimeReadable()
  {
    return isReadable(INTERVALTIME);
  }
  
  public boolean isIntervalTimeModified()
  {
    return isModified(INTERVALTIME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getIntervalTimeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVALTIME).getAttributeMdDTO();
  }
  
  public Integer getKnockedDown()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(KNOCKEDDOWN));
  }
  
  public void setKnockedDown(Integer value)
  {
    if(value == null)
    {
      setValue(KNOCKEDDOWN, "");
    }
    else
    {
      setValue(KNOCKEDDOWN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isKnockedDownWritable()
  {
    return isWritable(KNOCKEDDOWN);
  }
  
  public boolean isKnockedDownReadable()
  {
    return isReadable(KNOCKEDDOWN);
  }
  
  public boolean isKnockedDownModified()
  {
    return isModified(KNOCKEDDOWN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getKnockedDownMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(KNOCKEDDOWN).getAttributeMdDTO();
  }
  
  public Integer getPeriod()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIOD));
  }
  
  public void setPeriod(Integer value)
  {
    if(value == null)
    {
      setValue(PERIOD, "");
    }
    else
    {
      setValue(PERIOD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodWritable()
  {
    return isWritable(PERIOD);
  }
  
  public boolean isPeriodReadable()
  {
    return isReadable(PERIOD);
  }
  
  public boolean isPeriodModified()
  {
    return isModified(PERIOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIOD).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO[] saveAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO[] array)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.assay.AdultTestIntervalView;"};
    Object[] _parameters = new Object[]{array};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO.CLASS, "saveAll", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultTestIntervalViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AdultTestIntervalViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (AdultTestIntervalViewDTO) dto;
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
