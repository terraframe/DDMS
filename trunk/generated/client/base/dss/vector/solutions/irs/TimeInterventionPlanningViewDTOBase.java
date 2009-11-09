package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = 2091206862)
public abstract class TimeInterventionPlanningViewDTOBase extends dss.vector.solutions.irs.InterventionPlanningViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.TimeInterventionPlanningView";
  private static final long serialVersionUID = 2091206862;
  
  protected TimeInterventionPlanningViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String OPERATORS = "operators";
  public static java.lang.String REQUIREDDAYS = "requiredDays";
  public Integer getOperators()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(OPERATORS));
  }
  
  public void setOperators(Integer value)
  {
    if(value == null)
    {
      setValue(OPERATORS, "");
    }
    else
    {
      setValue(OPERATORS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isOperatorsWritable()
  {
    return isWritable(OPERATORS);
  }
  
  public boolean isOperatorsReadable()
  {
    return isReadable(OPERATORS);
  }
  
  public boolean isOperatorsModified()
  {
    return isModified(OPERATORS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getOperatorsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(OPERATORS).getAttributeMdDTO();
  }
  
  public Integer getRequiredDays()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REQUIREDDAYS));
  }
  
  public void setRequiredDays(Integer value)
  {
    if(value == null)
    {
      setValue(REQUIREDDAYS, "");
    }
    else
    {
      setValue(REQUIREDDAYS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRequiredDaysWritable()
  {
    return isWritable(REQUIREDDAYS);
  }
  
  public boolean isRequiredDaysReadable()
  {
    return isReadable(REQUIREDDAYS);
  }
  
  public boolean isRequiredDaysModified()
  {
    return isModified(REQUIREDDAYS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRequiredDaysMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REQUIREDDAYS).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] calculate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views, java.lang.Integer unitsPerDay)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TimeInterventionPlanningView;", "java.lang.Integer"};
    Object[] _parameters = new Object[]{views, unitsPerDay};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "calculate", _declaredTypes);
    return (dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] calculateDefault(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TimeInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "calculateDefault", _declaredTypes);
    return (dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.TimeInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[] getViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.TimeInterventionPlanningViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.irs.TimeInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static TimeInterventionPlanningViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (TimeInterventionPlanningViewDTO) dto;
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
