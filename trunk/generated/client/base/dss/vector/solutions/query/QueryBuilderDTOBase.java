package dss.vector.solutions.query;

@com.terraframe.mojo.business.ClassSignature(hash = -245383816)
public abstract class QueryBuilderDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.query.QueryBuilder";
  private static final long serialVersionUID = -245383816;
  
  protected QueryBuilderDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static final java.io.InputStream exportQueryToCSV(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryType, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryType, queryXML, config, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.QueryBuilderDTO.CLASS, "exportQueryToCSV", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportQueryToExcel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryType, java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"};
    Object[] _parameters = new Object[]{queryType, queryXML, config, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.QueryBuilderDTO.CLASS, "exportQueryToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.terraframe.mojo.business.ValueQueryDTO getQueryResults(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryType, java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{queryType, queryXML, config, sortBy, ascending, pageNumber, pageSize};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.QueryBuilderDTO.CLASS, "getQueryResults", _declaredTypes);
    return (com.terraframe.mojo.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.lang.String mapQuery(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String queryType, java.lang.String queryXML, java.lang.String thematicLayerType, java.lang.String[] universalLayers, java.lang.String savedSearchId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "[Ljava.lang.String;", "java.lang.String"};
    Object[] _parameters = new Object[]{queryType, queryXML, thematicLayerType, universalLayers, savedSearchId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.query.QueryBuilderDTO.CLASS, "mapQuery", _declaredTypes);
    return (java.lang.String) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static QueryBuilderDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (QueryBuilderDTO) dto;
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
