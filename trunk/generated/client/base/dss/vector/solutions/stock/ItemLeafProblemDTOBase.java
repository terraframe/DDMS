package dss.vector.solutions.stock;

@com.terraframe.mojo.business.ClassSignature(hash = 510897301)
public abstract class ItemLeafProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.ItemLeafProblem";
  private static final long serialVersionUID = 510897301;
  
  public ItemLeafProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public ItemLeafProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getItemNameMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ITEMNAME).getAttributeMdDTO();
  }
  
  /**
   * Overrides java.lang.Throwable#getMessage() to retrieve the localized
   * message from the exceptionDTO, instead of from a class variable.
   */
  public String getMessage()
  {
    if (this.getLocale() != null)
    {
      return this.localize(this.getLocale());
    }
    else
    {
      return localizedMessage;
    }
  }
  private java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.stock.ItemLeafProblem", locale);
      
      message = message.replace("{itemName}", this.getItemName().toString());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLExceptionDTO(e.getLocalizedMessage());
    }
  }
  
}
