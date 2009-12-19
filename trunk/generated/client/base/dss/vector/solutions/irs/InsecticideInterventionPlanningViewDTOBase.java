package dss.vector.solutions.irs;

@com.terraframe.mojo.business.ClassSignature(hash = -1659026396)
public abstract class InsecticideInterventionPlanningViewDTOBase extends dss.vector.solutions.irs.InterventionPlanningViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideInterventionPlanningView";
  private static final long serialVersionUID = -1659026396;
  
  protected InsecticideInterventionPlanningViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String REQUIREDINSECTICIDE = "requiredInsecticide";
  public Double getRequiredInsecticide()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(REQUIREDINSECTICIDE));
  }
  
  public void setRequiredInsecticide(Double value)
  {
    if(value == null)
    {
      setValue(REQUIREDINSECTICIDE, "");
    }
    else
    {
      setValue(REQUIREDINSECTICIDE, java.lang.Double.toString(value));
    }
  }
  
  public boolean isRequiredInsecticideWritable()
  {
    return isWritable(REQUIREDINSECTICIDE);
  }
  
  public boolean isRequiredInsecticideReadable()
  {
    return isReadable(REQUIREDINSECTICIDE);
  }
  
  public boolean isRequiredInsecticideModified()
  {
    return isModified(REQUIREDINSECTICIDE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDecMdDTO getRequiredInsecticideMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDecMdDTO) getAttributeDTO(REQUIREDINSECTICIDE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] calculate(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views, java.lang.String configurationId)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideInterventionPlanningView;", "java.lang.String"};
    Object[] _parameters = new Object[]{views, configurationId};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO.CLASS, "calculate", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] getViews(com.terraframe.mojo.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideInterventionPlanningViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (InsecticideInterventionPlanningViewDTO) dto;
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
