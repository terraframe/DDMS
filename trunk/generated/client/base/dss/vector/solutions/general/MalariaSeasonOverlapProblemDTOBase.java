package dss.vector.solutions.general;

@com.terraframe.mojo.business.ClassSignature(hash = -38743319)
public abstract class MalariaSeasonOverlapProblemDTOBase extends com.terraframe.mojo.business.ProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.general.MalariaSeasonOverlapProblem";
  private static final long serialVersionUID = -38743319;
  
  public MalariaSeasonOverlapProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public MalariaSeasonOverlapProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String OVERLAP = "overlap";
  public String getOverlap()
  {
    return getValue(OVERLAP);
  }
  
  public void setOverlap(String value)
  {
    if(value == null)
    {
      setValue(OVERLAP, "");
    }
    else
    {
      setValue(OVERLAP, value);
    }
  }
  
  public boolean isOverlapWritable()
  {
    return isWritable(OVERLAP);
  }
  
  public boolean isOverlapReadable()
  {
    return isReadable(OVERLAP);
  }
  
  public boolean isOverlapModified()
  {
    return isModified(OVERLAP);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getOverlapMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OVERLAP).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.general.MalariaSeasonOverlapProblem", locale);
      
      message = message.replace("{id}", this.getId().toString());
      message = message.replace("{overlap}", this.getOverlap().toString());
      
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
