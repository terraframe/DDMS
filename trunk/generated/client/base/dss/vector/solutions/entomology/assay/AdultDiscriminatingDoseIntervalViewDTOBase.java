package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = -1821977636)
public abstract class AdultDiscriminatingDoseIntervalViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalView";
  private static final long serialVersionUID = -1821977636;
  
  protected AdultDiscriminatingDoseIntervalViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String ASSAY = "assay";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVALTIME = "intervalTime";
  public Integer getAmount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void setAmount(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAmountWritable()
  {
    return isWritable(AMOUNT);
  }
  
  public boolean isAmountReadable()
  {
    return isReadable(AMOUNT);
  }
  
  public boolean isAmountModified()
  {
    return isModified(AMOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getAmountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(AMOUNT).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO getAssay()
  {
    if(getValue(ASSAY) == null || getValue(ASSAY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO.get(getRequest(), getValue(ASSAY));
    }
  }
  
  public String getAssayId()
  {
    return getValue(ASSAY);
  }
  
  public void setAssay(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO value)
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
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAssayMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(ASSAY).getAttributeMdDTO();
  }
  
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
  
  public Integer getIntervalTime()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVALTIME));
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
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getIntervalTimeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(INTERVALTIME).getAttributeMdDTO();
  }
  
  public static AdultDiscriminatingDoseIntervalViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AdultDiscriminatingDoseIntervalViewDTO) dto;
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
