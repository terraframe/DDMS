package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -48053031)
public abstract class AggregatedIPTExcelViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.AggregatedIPTExcelView";
  private static final long serialVersionUID = -48053031;
  
  protected AggregatedIPTExcelViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NUMBERNATALCARE = "numberNatalCare";
  public static java.lang.String NUMBERPREGNANT = "numberPregnant";
  public static java.lang.String NUMBERPREGNANTITN = "numberPregnantITN";
  public static java.lang.String NUMBERPREGNANTIRON = "numberPregnantIron";
  public static java.lang.String PERIOD = "period";
  public static java.lang.String PERIODTYPE = "periodType";
  public static java.lang.String PERIODYEAR = "periodYear";
  public static java.lang.String TOTALITN = "totalITN";
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public Integer getNumberNatalCare()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERNATALCARE));
  }
  
  public void setNumberNatalCare(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERNATALCARE, "");
    }
    else
    {
      setValue(NUMBERNATALCARE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberNatalCareWritable()
  {
    return isWritable(NUMBERNATALCARE);
  }
  
  public boolean isNumberNatalCareReadable()
  {
    return isReadable(NUMBERNATALCARE);
  }
  
  public boolean isNumberNatalCareModified()
  {
    return isModified(NUMBERNATALCARE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberNatalCareMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERNATALCARE).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnant()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANT));
  }
  
  public void setNumberPregnant(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANT, "");
    }
    else
    {
      setValue(NUMBERPREGNANT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantWritable()
  {
    return isWritable(NUMBERPREGNANT);
  }
  
  public boolean isNumberPregnantReadable()
  {
    return isReadable(NUMBERPREGNANT);
  }
  
  public boolean isNumberPregnantModified()
  {
    return isModified(NUMBERPREGNANT);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPregnantMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANT).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantITN()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTITN));
  }
  
  public void setNumberPregnantITN(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTITN, "");
    }
    else
    {
      setValue(NUMBERPREGNANTITN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantITNWritable()
  {
    return isWritable(NUMBERPREGNANTITN);
  }
  
  public boolean isNumberPregnantITNReadable()
  {
    return isReadable(NUMBERPREGNANTITN);
  }
  
  public boolean isNumberPregnantITNModified()
  {
    return isModified(NUMBERPREGNANTITN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPregnantITNMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTITN).getAttributeMdDTO();
  }
  
  public Integer getNumberPregnantIron()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERPREGNANTIRON));
  }
  
  public void setNumberPregnantIron(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERPREGNANTIRON, "");
    }
    else
    {
      setValue(NUMBERPREGNANTIRON, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberPregnantIronWritable()
  {
    return isWritable(NUMBERPREGNANTIRON);
  }
  
  public boolean isNumberPregnantIronReadable()
  {
    return isReadable(NUMBERPREGNANTIRON);
  }
  
  public boolean isNumberPregnantIronModified()
  {
    return isModified(NUMBERPREGNANTIRON);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberPregnantIronMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERPREGNANTIRON).getAttributeMdDTO();
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
  
  public String getPeriodType()
  {
    return getValue(PERIODTYPE);
  }
  
  public void setPeriodType(String value)
  {
    if(value == null)
    {
      setValue(PERIODTYPE, "");
    }
    else
    {
      setValue(PERIODTYPE, value);
    }
  }
  
  public boolean isPeriodTypeWritable()
  {
    return isWritable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeReadable()
  {
    return isReadable(PERIODTYPE);
  }
  
  public boolean isPeriodTypeModified()
  {
    return isModified(PERIODTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getPeriodTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERIODTYPE).getAttributeMdDTO();
  }
  
  public Integer getPeriodYear()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PERIODYEAR));
  }
  
  public void setPeriodYear(Integer value)
  {
    if(value == null)
    {
      setValue(PERIODYEAR, "");
    }
    else
    {
      setValue(PERIODYEAR, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPeriodYearWritable()
  {
    return isWritable(PERIODYEAR);
  }
  
  public boolean isPeriodYearReadable()
  {
    return isReadable(PERIODYEAR);
  }
  
  public boolean isPeriodYearModified()
  {
    return isModified(PERIODYEAR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPeriodYearMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PERIODYEAR).getAttributeMdDTO();
  }
  
  public Integer getTotalITN()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALITN));
  }
  
  public void setTotalITN(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALITN, "");
    }
    else
    {
      setValue(TOTALITN, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalITNWritable()
  {
    return isWritable(TOTALITN);
  }
  
  public boolean isTotalITNReadable()
  {
    return isReadable(TOTALITN);
  }
  
  public boolean isTotalITNModified()
  {
    return isModified(TOTALITN);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTotalITNMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTALITN).getAttributeMdDTO();
  }
  
  public static AggregatedIPTExcelViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedIPTExcelViewDTO) dto;
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
