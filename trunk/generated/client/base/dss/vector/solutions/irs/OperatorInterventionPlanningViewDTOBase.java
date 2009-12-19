package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -606041362)
public abstract class OperatorInterventionPlanningViewDTOBase extends dss.vector.solutions.irs.InterventionPlanningViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.OperatorInterventionPlanningView";
  private static final long serialVersionUID = -606041362;
  
  protected OperatorInterventionPlanningViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String NUMBEROFDAYS = "numberofDays";
  public static java.lang.String REQUIREDOPERATORS = "requiredOperators";
  public static java.lang.String UNITSPERDAY = "unitsPerDay";
  public Integer getNumberofDays()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFDAYS));
  }
  
  public void setNumberofDays(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFDAYS, "");
    }
    else
    {
      setValue(NUMBEROFDAYS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberofDaysWritable()
  {
    return isWritable(NUMBEROFDAYS);
  }
  
  public boolean isNumberofDaysReadable()
  {
    return isReadable(NUMBEROFDAYS);
  }
  
  public boolean isNumberofDaysModified()
  {
    return isModified(NUMBEROFDAYS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getNumberofDaysMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFDAYS).getAttributeMdDTO();
  }
  
  public Integer getRequiredOperators()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(REQUIREDOPERATORS));
  }
  
  public void setRequiredOperators(Integer value)
  {
    if(value == null)
    {
      setValue(REQUIREDOPERATORS, "");
    }
    else
    {
      setValue(REQUIREDOPERATORS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isRequiredOperatorsWritable()
  {
    return isWritable(REQUIREDOPERATORS);
  }
  
  public boolean isRequiredOperatorsReadable()
  {
    return isReadable(REQUIREDOPERATORS);
  }
  
  public boolean isRequiredOperatorsModified()
  {
    return isModified(REQUIREDOPERATORS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getRequiredOperatorsMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(REQUIREDOPERATORS).getAttributeMdDTO();
  }
  
  public Integer getUnitsPerDay()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(UNITSPERDAY));
  }
  
  public void setUnitsPerDay(Integer value)
  {
    if(value == null)
    {
      setValue(UNITSPERDAY, "");
    }
    else
    {
      setValue(UNITSPERDAY, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isUnitsPerDayWritable()
  {
    return isWritable(UNITSPERDAY);
  }
  
  public boolean isUnitsPerDayReadable()
  {
    return isReadable(UNITSPERDAY);
  }
  
  public boolean isUnitsPerDayModified()
  {
    return isModified(UNITSPERDAY);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getUnitsPerDayMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(UNITSPERDAY).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] calculate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.OperatorInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO.CLASS, "calculate", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.OperatorInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[] getViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.irs.OperatorInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static OperatorInterventionPlanningViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (OperatorInterventionPlanningViewDTO) dto;
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
