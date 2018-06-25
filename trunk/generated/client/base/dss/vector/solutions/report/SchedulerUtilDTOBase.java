package dss.vector.solutions.report;

@com.runwaysdk.business.ClassSignature(hash = -1387001737)
public abstract class SchedulerUtilDTOBase extends com.runwaysdk.business.UtilDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.report.SchedulerUtil";
  private static final long serialVersionUID = -1387001737;
  
  protected SchedulerUtilDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ID = "id";
  public static final com.runwaysdk.system.scheduler.ExecutableJobQueryDTO instanceQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String[] filterTypes, java.lang.String sortAttr, java.lang.Boolean isDescending, java.lang.Integer pageSize, java.lang.Integer pageNum)
  {
    String[] _declaredTypes = new String[]{"[Ljava.lang.String;", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{filterTypes, sortAttr, isDescending, pageSize, pageNum};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.report.SchedulerUtilDTO.CLASS, "instanceQuery", _declaredTypes);
    return (com.runwaysdk.system.scheduler.ExecutableJobQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO searchByValueQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{value};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.report.SchedulerUtilDTO.CLASS, "searchByValueQuery", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static SchedulerUtilDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.UtilDTO dto = (com.runwaysdk.business.UtilDTO)clientRequest.get(id);
    
    return (SchedulerUtilDTO) dto;
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
