package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -664234239)
public abstract class SprayedSumProblemDTOBase extends dss.vector.solutions.NotificationProblemDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.SprayedSumProblem";
  private static final long serialVersionUID = -664234239;
  
  public SprayedSumProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  public SprayedSumProblemDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF, java.util.Locale locale)
  {
    super(clientRequestIF, locale);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OBJECTLABEL = "objectLabel";
  public static java.lang.String OBJECTS = "objects";
  public static java.lang.String SPRAYEDOBJECTLABEL = "sprayedObjectLabel";
  public static java.lang.String SPRAYEDOBJECTS = "sprayedObjects";
  public String getObjectLabel()
  {
    return getValue(OBJECTLABEL);
  }
  
  public void setObjectLabel(String value)
  {
    if(value == null)
    {
      setValue(OBJECTLABEL, "");
    }
    else
    {
      setValue(OBJECTLABEL, value);
    }
  }
  
  public boolean isObjectLabelWritable()
  {
    return isWritable(OBJECTLABEL);
  }
  
  public boolean isObjectLabelReadable()
  {
    return isReadable(OBJECTLABEL);
  }
  
  public boolean isObjectLabelModified()
  {
    return isModified(OBJECTLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getObjectLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(OBJECTLABEL).getAttributeMdDTO();
  }
  
  public Integer getObjects()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OBJECTS));
  }
  
  public void setObjects(Integer value)
  {
    if(value == null)
    {
      setValue(OBJECTS, "");
    }
    else
    {
      setValue(OBJECTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isObjectsWritable()
  {
    return isWritable(OBJECTS);
  }
  
  public boolean isObjectsReadable()
  {
    return isReadable(OBJECTS);
  }
  
  public boolean isObjectsModified()
  {
    return isModified(OBJECTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getObjectsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OBJECTS).getAttributeMdDTO();
  }
  
  public String getSprayedObjectLabel()
  {
    return getValue(SPRAYEDOBJECTLABEL);
  }
  
  public void setSprayedObjectLabel(String value)
  {
    if(value == null)
    {
      setValue(SPRAYEDOBJECTLABEL, "");
    }
    else
    {
      setValue(SPRAYEDOBJECTLABEL, value);
    }
  }
  
  public boolean isSprayedObjectLabelWritable()
  {
    return isWritable(SPRAYEDOBJECTLABEL);
  }
  
  public boolean isSprayedObjectLabelReadable()
  {
    return isReadable(SPRAYEDOBJECTLABEL);
  }
  
  public boolean isSprayedObjectLabelModified()
  {
    return isModified(SPRAYEDOBJECTLABEL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSprayedObjectLabelMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPRAYEDOBJECTLABEL).getAttributeMdDTO();
  }
  
  public Integer getSprayedObjects()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(SPRAYEDOBJECTS));
  }
  
  public void setSprayedObjects(Integer value)
  {
    if(value == null)
    {
      setValue(SPRAYEDOBJECTS, "");
    }
    else
    {
      setValue(SPRAYEDOBJECTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isSprayedObjectsWritable()
  {
    return isWritable(SPRAYEDOBJECTS);
  }
  
  public boolean isSprayedObjectsReadable()
  {
    return isReadable(SPRAYEDOBJECTS);
  }
  
  public boolean isSprayedObjectsModified()
  {
    return isModified(SPRAYEDOBJECTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getSprayedObjectsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(SPRAYEDOBJECTS).getAttributeMdDTO();
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
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.irs.SprayedSumProblem", locale);
      
      message = message.replace("{objectLabel}", this.getObjectLabel().toString());
      message = message.replace("{objects}", this.getObjects().toString());
      message = message.replace("{sprayedObjectLabel}", this.getSprayedObjectLabel().toString());
      message = message.replace("{sprayedObjects}", this.getSprayedObjects().toString());
      
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
