package dss.vector.solutions;

@com.runwaysdk.business.ClassSignature(hash = 1557516647)
public abstract class CurrentDateProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.CurrentDateProblem";
  private static final long serialVersionUID = 1557516647;
  
  public CurrentDateProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public CurrentDateProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CURRENTDATE = "currentDate";
  public static java.lang.String GIVENDATE = "givenDate";
  public java.util.Date getCurrentDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(CURRENTDATE));
  }
  
  public void setCurrentDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(CURRENTDATE, "");
    }
    else
    {
      setValue(CURRENTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCurrentDateWritable()
  {
    return isWritable(CURRENTDATE);
  }
  
  public boolean isCurrentDateReadable()
  {
    return isReadable(CURRENTDATE);
  }
  
  public boolean isCurrentDateModified()
  {
    return isModified(CURRENTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCurrentDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(CURRENTDATE).getAttributeMdDTO();
  }
  
  public java.util.Date getGivenDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(GIVENDATE));
  }
  
  public void setGivenDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(GIVENDATE, "");
    }
    else
    {
      setValue(GIVENDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isGivenDateWritable()
  {
    return isWritable(GIVENDATE);
  }
  
  public boolean isGivenDateReadable()
  {
    return isReadable(GIVENDATE);
  }
  
  public boolean isGivenDateModified()
  {
    return isModified(GIVENDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getGivenDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(GIVENDATE).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{currentDate}", this.getCurrentDate().toString());
    template = template.replace("{givenDate}", this.getGivenDate().toString());
    
    return template;
  }
  
}
