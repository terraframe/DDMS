package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1565627796)
public abstract class NetQuantityProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.NetQuantityProblem";
  private static final long serialVersionUID = -1565627796;
  
  public NetQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public NetQuantityProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String NETCOUNT = "netCount";
  public static java.lang.String QUANTITY = "quantity";
  public Integer getNetCount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NETCOUNT));
  }
  
  public void setNetCount(Integer value)
  {
    if(value == null)
    {
      setValue(NETCOUNT, "");
    }
    else
    {
      setValue(NETCOUNT, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNetCountWritable()
  {
    return isWritable(NETCOUNT);
  }
  
  public boolean isNetCountReadable()
  {
    return isReadable(NETCOUNT);
  }
  
  public boolean isNetCountModified()
  {
    return isModified(NETCOUNT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNetCountMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NETCOUNT).getAttributeMdDTO();
  }
  
  public Integer getQuantity()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITY));
  }
  
  public void setQuantity(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITY, "");
    }
    else
    {
      setValue(QUANTITY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isQuantityWritable()
  {
    return isWritable(QUANTITY);
  }
  
  public boolean isQuantityReadable()
  {
    return isReadable(QUANTITY);
  }
  
  public boolean isQuantityModified()
  {
    return isModified(QUANTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getQuantityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(QUANTITY).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{netCount}", this.getNetCount().toString());
    template = template.replace("{quantity}", this.getQuantity().toString());
    
    return template;
  }
  
}
