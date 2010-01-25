package dss.vector.solutions.surveillance;

@com.terraframe.mojo.business.ClassSignature(hash = 1285260003)
public abstract class AggregatedCaseSearchViewDTOBase extends dss.vector.solutions.surveillance.AggregatedCaseViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.AggregatedCaseSearchView";
  private static final long serialVersionUID = 1285260003;
  
  protected AggregatedCaseSearchViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String SEARCHTYPE = "searchType";
  public Boolean getSearchType()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SEARCHTYPE));
  }
  
  public void setSearchType(Boolean value)
  {
    if(value == null)
    {
      setValue(SEARCHTYPE, "");
    }
    else
    {
      setValue(SEARCHTYPE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isSearchTypeWritable()
  {
    return isWritable(SEARCHTYPE);
  }
  
  public boolean isSearchTypeReadable()
  {
    return isReadable(SEARCHTYPE);
  }
  
  public boolean isSearchTypeModified()
  {
    return isModified(SEARCHTYPE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getSearchTypeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(SEARCHTYPE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.surveillance.AggregatedCaseViewDTO searchByView()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseSearchViewDTO.CLASS, "searchByView", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseViewDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.surveillance.AggregatedCaseViewDTO searchByView(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.surveillance.AggregatedCaseSearchViewDTO.CLASS, "searchByView", _declaredTypes);
    return (dss.vector.solutions.surveillance.AggregatedCaseViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AggregatedCaseSearchViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedCaseSearchViewDTO) dto;
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
