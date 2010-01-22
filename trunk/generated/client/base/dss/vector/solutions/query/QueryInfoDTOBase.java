package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = 2089620585)
public abstract class QueryInfoDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.QueryInfo";
  private static final long serialVersionUID = 2089620585;
  
  protected QueryInfoDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static java.lang.String MAXIMUM = "maximum";
  public static java.lang.String MINIMUM = "minimum";
  public static java.lang.String TOTALRESULTS = "totalResults";
  public String getMaximum()
  {
    return getValue(MAXIMUM);
  }
  
  public void setMaximum(String value)
  {
    if(value == null)
    {
      setValue(MAXIMUM, "");
    }
    else
    {
      setValue(MAXIMUM, value);
    }
  }
  
  public boolean isMaximumWritable()
  {
    return isWritable(MAXIMUM);
  }
  
  public boolean isMaximumReadable()
  {
    return isReadable(MAXIMUM);
  }
  
  public boolean isMaximumModified()
  {
    return isModified(MAXIMUM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMaximumMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MAXIMUM).getAttributeMdDTO();
  }
  
  public String getMinimum()
  {
    return getValue(MINIMUM);
  }
  
  public void setMinimum(String value)
  {
    if(value == null)
    {
      setValue(MINIMUM, "");
    }
    else
    {
      setValue(MINIMUM, value);
    }
  }
  
  public boolean isMinimumWritable()
  {
    return isWritable(MINIMUM);
  }
  
  public boolean isMinimumReadable()
  {
    return isReadable(MINIMUM);
  }
  
  public boolean isMinimumModified()
  {
    return isModified(MINIMUM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMinimumMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MINIMUM).getAttributeMdDTO();
  }
  
  public Integer getTotalResults()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TOTALRESULTS));
  }
  
  public void setTotalResults(Integer value)
  {
    if(value == null)
    {
      setValue(TOTALRESULTS, "");
    }
    else
    {
      setValue(TOTALRESULTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTotalResultsWritable()
  {
    return isWritable(TOTALRESULTS);
  }
  
  public boolean isTotalResultsReadable()
  {
    return isReadable(TOTALRESULTS);
  }
  
  public boolean isTotalResultsModified()
  {
    return isModified(TOTALRESULTS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getTotalResultsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TOTALRESULTS).getAttributeMdDTO();
  }
  
  public static QueryInfoDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (QueryInfoDTO) dto;
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
