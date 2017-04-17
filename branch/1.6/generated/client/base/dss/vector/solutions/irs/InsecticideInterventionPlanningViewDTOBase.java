package dss.vector.solutions.irs;

@com.runwaysdk.business.ClassSignature(hash = 657857043)
public abstract class InsecticideInterventionPlanningViewDTOBase extends dss.vector.solutions.irs.InterventionPlanningViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideInterventionPlanningView";
  private static final long serialVersionUID = 657857043;
  
  protected InsecticideInterventionPlanningViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
    return com.runwaysdk.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(REQUIREDINSECTICIDE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getRequiredInsecticideMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(REQUIREDINSECTICIDE).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] calculate(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views, java.lang.String brandId)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideInterventionPlanningView;", "java.lang.String"};
    Object[] _parameters = new Object[]{views, brandId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO.CLASS, "calculate", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final java.io.InputStream exportToExcel(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.irs.InsecticideInterventionPlanningView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO.CLASS, "exportToExcel", _declaredTypes);
    return (java.io.InputStream) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String geoId, dss.vector.solutions.general.MalariaSeasonDTO season)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "dss.vector.solutions.general.MalariaSeason"};
    Object[] _parameters = new Object[]{geoId, season};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.irs.InsecticideInterventionPlanningViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static InsecticideInterventionPlanningViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
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
