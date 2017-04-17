package dss.vector.solutions.geo;

@com.runwaysdk.business.ClassSignature(hash = -1596308817)
public abstract class ChildEntityOverflowInformationDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.geo.ChildEntityOverflowInformation";
  private static final long serialVersionUID = -1596308817;
  
  public ChildEntityOverflowInformationDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String OVERFLOWEND = "overflowEnd";
  public static java.lang.String OVERFLOWNUMBER = "overflowNumber";
  public static java.lang.String OVERFLOWPAGE = "overflowPage";
  public static java.lang.String OVERFLOWSTART = "overflowStart";
  public static java.lang.String OVERFLOWTHRESHOLD = "overflowThreshold";
  public Integer getOverflowEnd()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OVERFLOWEND));
  }
  
  public void setOverflowEnd(Integer value)
  {
    if(value == null)
    {
      setValue(OVERFLOWEND, "");
    }
    else
    {
      setValue(OVERFLOWEND, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOverflowEndWritable()
  {
    return isWritable(OVERFLOWEND);
  }
  
  public boolean isOverflowEndReadable()
  {
    return isReadable(OVERFLOWEND);
  }
  
  public boolean isOverflowEndModified()
  {
    return isModified(OVERFLOWEND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOverflowEndMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OVERFLOWEND).getAttributeMdDTO();
  }
  
  public Integer getOverflowNumber()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OVERFLOWNUMBER));
  }
  
  public void setOverflowNumber(Integer value)
  {
    if(value == null)
    {
      setValue(OVERFLOWNUMBER, "");
    }
    else
    {
      setValue(OVERFLOWNUMBER, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOverflowNumberWritable()
  {
    return isWritable(OVERFLOWNUMBER);
  }
  
  public boolean isOverflowNumberReadable()
  {
    return isReadable(OVERFLOWNUMBER);
  }
  
  public boolean isOverflowNumberModified()
  {
    return isModified(OVERFLOWNUMBER);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOverflowNumberMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OVERFLOWNUMBER).getAttributeMdDTO();
  }
  
  public Integer getOverflowPage()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OVERFLOWPAGE));
  }
  
  public void setOverflowPage(Integer value)
  {
    if(value == null)
    {
      setValue(OVERFLOWPAGE, "");
    }
    else
    {
      setValue(OVERFLOWPAGE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOverflowPageWritable()
  {
    return isWritable(OVERFLOWPAGE);
  }
  
  public boolean isOverflowPageReadable()
  {
    return isReadable(OVERFLOWPAGE);
  }
  
  public boolean isOverflowPageModified()
  {
    return isModified(OVERFLOWPAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOverflowPageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OVERFLOWPAGE).getAttributeMdDTO();
  }
  
  public Integer getOverflowStart()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OVERFLOWSTART));
  }
  
  public void setOverflowStart(Integer value)
  {
    if(value == null)
    {
      setValue(OVERFLOWSTART, "");
    }
    else
    {
      setValue(OVERFLOWSTART, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOverflowStartWritable()
  {
    return isWritable(OVERFLOWSTART);
  }
  
  public boolean isOverflowStartReadable()
  {
    return isReadable(OVERFLOWSTART);
  }
  
  public boolean isOverflowStartModified()
  {
    return isModified(OVERFLOWSTART);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOverflowStartMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OVERFLOWSTART).getAttributeMdDTO();
  }
  
  public Integer getOverflowThreshold()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OVERFLOWTHRESHOLD));
  }
  
  public void setOverflowThreshold(Integer value)
  {
    if(value == null)
    {
      setValue(OVERFLOWTHRESHOLD, "");
    }
    else
    {
      setValue(OVERFLOWTHRESHOLD, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOverflowThresholdWritable()
  {
    return isWritable(OVERFLOWTHRESHOLD);
  }
  
  public boolean isOverflowThresholdReadable()
  {
    return isReadable(OVERFLOWTHRESHOLD);
  }
  
  public boolean isOverflowThresholdModified()
  {
    return isModified(OVERFLOWTHRESHOLD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getOverflowThresholdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OVERFLOWTHRESHOLD).getAttributeMdDTO();
  }
  
}
