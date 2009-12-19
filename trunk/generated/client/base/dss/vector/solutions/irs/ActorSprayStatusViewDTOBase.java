package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 682827559)
public abstract class ActorSprayStatusViewDTOBase extends dss.vector.solutions.irs.SprayStatusViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.ActorSprayStatusView";
  private static final long serialVersionUID = 682827559;
  
  protected ActorSprayStatusViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String RECEIVED = "received";
  public static java.lang.String REFILLS = "refills";
  public static java.lang.String RETURNED = "returned";
  public static java.lang.String SPRAYDATA = "sprayData";
  public static java.lang.String USED = "used";
  public Integer getReceived()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RECEIVED));
  }
  
  public void setReceived(Integer value)
  {
    if(value == null)
    {
      setValue(RECEIVED, "");
    }
    else
    {
      setValue(RECEIVED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReceivedWritable()
  {
    return isWritable(RECEIVED);
  }
  
  public boolean isReceivedReadable()
  {
    return isReadable(RECEIVED);
  }
  
  public boolean isReceivedModified()
  {
    return isModified(RECEIVED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReceivedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RECEIVED).getAttributeMdDTO();
  }
  
  public Integer getRefills()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REFILLS));
  }
  
  public void setRefills(Integer value)
  {
    if(value == null)
    {
      setValue(REFILLS, "");
    }
    else
    {
      setValue(REFILLS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRefillsWritable()
  {
    return isWritable(REFILLS);
  }
  
  public boolean isRefillsReadable()
  {
    return isReadable(REFILLS);
  }
  
  public boolean isRefillsModified()
  {
    return isModified(REFILLS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRefillsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REFILLS).getAttributeMdDTO();
  }
  
  public Integer getReturned()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(RETURNED));
  }
  
  public void setReturned(Integer value)
  {
    if(value == null)
    {
      setValue(RETURNED, "");
    }
    else
    {
      setValue(RETURNED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isReturnedWritable()
  {
    return isWritable(RETURNED);
  }
  
  public boolean isReturnedReadable()
  {
    return isReadable(RETURNED);
  }
  
  public boolean isReturnedModified()
  {
    return isModified(RETURNED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getReturnedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(RETURNED).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.irs.SprayDataDTO getSprayData()
  {
    if(getValue(SPRAYDATA) == null || getValue(SPRAYDATA).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.SprayDataDTO.get(getRequest(), getValue(SPRAYDATA));
    }
  }
  
  public void setSprayData(dss.vector.solutions.irs.SprayDataDTO value)
  {
    if(value == null)
    {
      setValue(SPRAYDATA, "");
    }
    else
    {
      setValue(SPRAYDATA, value.getId());
    }
  }
  
  public boolean isSprayDataWritable()
  {
    return isWritable(SPRAYDATA);
  }
  
  public boolean isSprayDataReadable()
  {
    return isReadable(SPRAYDATA);
  }
  
  public boolean isSprayDataModified()
  {
    return isModified(SPRAYDATA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSprayDataMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(SPRAYDATA).getAttributeMdDTO();
  }
  
  public Integer getUsed()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(USED));
  }
  
  public void setUsed(Integer value)
  {
    if(value == null)
    {
      setValue(USED, "");
    }
    else
    {
      setValue(USED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUsedWritable()
  {
    return isWritable(USED);
  }
  
  public boolean isUsedReadable()
  {
    return isReadable(USED);
  }
  
  public boolean isUsedModified()
  {
    return isModified(USED);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUsedMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(USED).getAttributeMdDTO();
  }
  
  public static ActorSprayStatusViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (ActorSprayStatusViewDTO) dto;
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
