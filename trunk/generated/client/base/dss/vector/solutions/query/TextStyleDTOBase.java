package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -1101112422)
public abstract class TextStyleDTOBase extends dss.vector.solutions.query.StyleRuleDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.TextStyle";
  private static final long serialVersionUID = -1101112422;
  
  protected TextStyleDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected TextStyleDTOBase(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String FILL = "fill";
  public static java.lang.String FONTFAMILY = "fontFamily";
  public static java.lang.String FONTSIZE = "fontSize";
  public static java.lang.String FONTSTYLE = "fontStyle";
  public String getFill()
  {
    return getValue(FILL);
  }
  
  public void setFill(String value)
  {
    if(value == null)
    {
      setValue(FILL, "");
    }
    else
    {
      setValue(FILL, value);
    }
  }
  
  public boolean isFillWritable()
  {
    return isWritable(FILL);
  }
  
  public boolean isFillReadable()
  {
    return isReadable(FILL);
  }
  
  public boolean isFillModified()
  {
    return isModified(FILL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFillMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FILL).getAttributeMdDTO();
  }
  
  public String getFontFamily()
  {
    return getValue(FONTFAMILY);
  }
  
  public void setFontFamily(String value)
  {
    if(value == null)
    {
      setValue(FONTFAMILY, "");
    }
    else
    {
      setValue(FONTFAMILY, value);
    }
  }
  
  public boolean isFontFamilyWritable()
  {
    return isWritable(FONTFAMILY);
  }
  
  public boolean isFontFamilyReadable()
  {
    return isReadable(FONTFAMILY);
  }
  
  public boolean isFontFamilyModified()
  {
    return isModified(FONTFAMILY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFontFamilyMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTFAMILY).getAttributeMdDTO();
  }
  
  public Integer getFontSize()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(FONTSIZE));
  }
  
  public void setFontSize(Integer value)
  {
    if(value == null)
    {
      setValue(FONTSIZE, "");
    }
    else
    {
      setValue(FONTSIZE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isFontSizeWritable()
  {
    return isWritable(FONTSIZE);
  }
  
  public boolean isFontSizeReadable()
  {
    return isReadable(FONTSIZE);
  }
  
  public boolean isFontSizeModified()
  {
    return isModified(FONTSIZE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getFontSizeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(FONTSIZE).getAttributeMdDTO();
  }
  
  public String getFontStyle()
  {
    return getValue(FONTSTYLE);
  }
  
  public void setFontStyle(String value)
  {
    if(value == null)
    {
      setValue(FONTSTYLE, "");
    }
    else
    {
      setValue(FONTSTYLE, value);
    }
  }
  
  public boolean isFontStyleWritable()
  {
    return isWritable(FONTSTYLE);
  }
  
  public boolean isFontStyleReadable()
  {
    return isReadable(FONTSTYLE);
  }
  
  public boolean isFontStyleModified()
  {
    return isModified(FONTSTYLE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getFontStyleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(FONTSTYLE).getAttributeMdDTO();
  }
  
  public static dss.vector.solutions.query.TextStyleDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.EntityDTO dto = (com.terraframe.mojo.business.EntityDTO)clientRequest.get(id);
    
    return (dss.vector.solutions.query.TextStyleDTO) dto;
  }
  
  public void apply()
  {
    if(isNewInstance())
    {
      getRequest().createBusiness(this);
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
  
  public static dss.vector.solutions.query.TextStyleQueryDTO getAllInstances(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    return (dss.vector.solutions.query.TextStyleQueryDTO) clientRequest.getAllInstances("dss.vector.solutions.query.TextStyle", sortAttribute, ascending, pageSize, pageNumber);
  }
  
  public void lock()
  {
    getRequest().lock(this);
  }
  
  public static dss.vector.solutions.query.TextStyleDTO lock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.TextStyleDTO.CLASS, "lock", _declaredTypes);
    return (dss.vector.solutions.query.TextStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public void unlock()
  {
    getRequest().unlock(this);
  }
  
  public static dss.vector.solutions.query.TextStyleDTO unlock(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.TextStyleDTO.CLASS, "unlock", _declaredTypes);
    return (dss.vector.solutions.query.TextStyleDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
}
