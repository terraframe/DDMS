package csu.mrc.ivcc.mdss.entomology.assay;

public abstract class ADDATestIntervalViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView";
  protected ADDATestIntervalViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
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
  public csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayDTO getAssay()
  {
    if(getValue(ASSAY) == null || getValue(ASSAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayDTO.get(getRequest(), getValue(ASSAY));
    }
  }
  
  public void setAssay(csu.mrc.ivcc.mdss.entomology.assay.DiscriminatingDoseAssayDTO value)
  {
    setValue(ASSAY, value.getId());
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
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("assay").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("intervalId").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("intervalTime").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("knockedDown").getAttributeMdDTO();
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
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("period").getAttributeMdDTO();
  }
  
  public static final csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalViewDTO[] saveAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalViewDTO[] array)
  {
    String[] _declaredTypes = new String[]{"[Lcsu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView;"};
    Object[] _parameters = new Object[]{array};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalViewDTO.CLASS, "saveAll", _declaredTypes);
    return (csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ADDATestIntervalViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ADDATestIntervalViewDTO) dto;
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
