package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = 471514099)
public abstract class ItemLeafProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.ItemLeafProblem";
  private static final long serialVersionUID = 471514099;
  
  public ItemLeafProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public ItemLeafProblemDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ITEMNAME = "itemName";
  public String getItemName()
  {
    return getValue(ITEMNAME);
  }
  
  public void setItemName(String value)
  {
    if(value == null)
    {
      setValue(ITEMNAME, "");
    }
    else
    {
      setValue(ITEMNAME, value);
    }
  }
  
  public boolean isItemNameWritable()
  {
    return isWritable(ITEMNAME);
  }
  
  public boolean isItemNameReadable()
  {
    return isReadable(ITEMNAME);
  }
  
  public boolean isItemNameModified()
  {
    return isModified(ITEMNAME);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getItemNameMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ITEMNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    java.lang.String template = super.getMessage();
    
    template = template.replace("{itemName}", this.getItemName().toString());
    
    return template;
  }
  
}
