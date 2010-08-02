package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -1364380623)
public abstract class AggregatedPremiseVisitViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView";
  private static final long serialVersionUID = -1364380623;
  
  protected AggregatedPremiseVisitViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ENTITYLABEL = "entityLabel";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INTERVENTIONMETHOD = "interventionMethod";
  public static java.lang.String NONTREATMENTREASON = "nonTreatmentReason";
  public static java.lang.String POINT = "point";
  public static java.lang.String PREMISES = "premises";
  public static java.lang.String PREMISESAVAILABLE = "premisesAvailable";
  public static java.lang.String PREMISESINCLUDED = "premisesIncluded";
  public static java.lang.String TREATED = "treated";
  public static java.lang.String VISITED = "visited";
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void setConcreteId(String value)
  {
    if(value == null)
    {
      setValue(CONCRETEID, "");
    }
    else
    {
      setValue(CONCRETEID, value);
    }
  }
  
  public boolean isConcreteIdWritable()
  {
    return isWritable(CONCRETEID);
  }
  
  public boolean isConcreteIdReadable()
  {
    return isReadable(CONCRETEID);
  }
  
  public boolean isConcreteIdModified()
  {
    return isModified(CONCRETEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getConcreteIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(CONCRETEID).getAttributeMdDTO();
  }
  
  public String getEntityLabel()
  {
    return getValue(ENTITYLABEL);
  }
  
  public void setEntityLabel(String value)
  {
    if(value == null)
    {
      setValue(ENTITYLABEL, "");
    }
    else
    {
      setValue(ENTITYLABEL, value);
    }
  }
  
  public boolean isEntityLabelWritable()
  {
    return isWritable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelReadable()
  {
    return isReadable(ENTITYLABEL);
  }
  
  public boolean isEntityLabelModified()
  {
    return isModified(ENTITYLABEL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEntityLabelMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ENTITYLABEL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.geo.generated.GeoEntityDTO getGeoEntity()
  {
    if(getValue(GEOENTITY) == null || getValue(GEOENTITY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.generated.GeoEntityDTO.get(getRequest(), getValue(GEOENTITY));
    }
  }
  
  public void setGeoEntity(dss.vector.solutions.geo.generated.GeoEntityDTO value)
  {
    if(value == null)
    {
      setValue(GEOENTITY, "");
    }
    else
    {
      setValue(GEOENTITY, value.getId());
    }
  }
  
  public boolean isGeoEntityWritable()
  {
    return isWritable(GEOENTITY);
  }
  
  public boolean isGeoEntityReadable()
  {
    return isReadable(GEOENTITY);
  }
  
  public boolean isGeoEntityModified()
  {
    return isModified(GEOENTITY);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getGeoEntityMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(GEOENTITY).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getInterventionMethod()
  {
    if(getValue(INTERVENTIONMETHOD) == null || getValue(INTERVENTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(INTERVENTIONMETHOD));
    }
  }
  
  public void setInterventionMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(INTERVENTIONMETHOD, "");
    }
    else
    {
      setValue(INTERVENTIONMETHOD, value.getId());
    }
  }
  
  public boolean isInterventionMethodWritable()
  {
    return isWritable(INTERVENTIONMETHOD);
  }
  
  public boolean isInterventionMethodReadable()
  {
    return isReadable(INTERVENTIONMETHOD);
  }
  
  public boolean isInterventionMethodModified()
  {
    return isModified(INTERVENTIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInterventionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INTERVENTIONMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getNonTreatmentReason()
  {
    if(getValue(NONTREATMENTREASON) == null || getValue(NONTREATMENTREASON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(NONTREATMENTREASON));
    }
  }
  
  public void setNonTreatmentReason(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(NONTREATMENTREASON, "");
    }
    else
    {
      setValue(NONTREATMENTREASON, value.getId());
    }
  }
  
  public boolean isNonTreatmentReasonWritable()
  {
    return isWritable(NONTREATMENTREASON);
  }
  
  public boolean isNonTreatmentReasonReadable()
  {
    return isReadable(NONTREATMENTREASON);
  }
  
  public boolean isNonTreatmentReasonModified()
  {
    return isModified(NONTREATMENTREASON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getNonTreatmentReasonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(NONTREATMENTREASON).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.intervention.monitor.ControlInterventionDTO getPoint()
  {
    if(getValue(POINT) == null || getValue(POINT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.intervention.monitor.ControlInterventionDTO.get(getRequest(), getValue(POINT));
    }
  }
  
  public void setPoint(dss.vector.solutions.intervention.monitor.ControlInterventionDTO value)
  {
    if(value == null)
    {
      setValue(POINT, "");
    }
    else
    {
      setValue(POINT, value.getId());
    }
  }
  
  public boolean isPointWritable()
  {
    return isWritable(POINT);
  }
  
  public boolean isPointReadable()
  {
    return isReadable(POINT);
  }
  
  public boolean isPointModified()
  {
    return isModified(POINT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPointMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(POINT).getAttributeMdDTO();
  }
  
  public Integer getPremises()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREMISES));
  }
  
  public void setPremises(Integer value)
  {
    if(value == null)
    {
      setValue(PREMISES, "");
    }
    else
    {
      setValue(PREMISES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPremisesWritable()
  {
    return isWritable(PREMISES);
  }
  
  public boolean isPremisesReadable()
  {
    return isReadable(PREMISES);
  }
  
  public boolean isPremisesModified()
  {
    return isModified(PREMISES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPremisesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREMISES).getAttributeMdDTO();
  }
  
  public Integer getPremisesAvailable()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREMISESAVAILABLE));
  }
  
  public void setPremisesAvailable(Integer value)
  {
    if(value == null)
    {
      setValue(PREMISESAVAILABLE, "");
    }
    else
    {
      setValue(PREMISESAVAILABLE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPremisesAvailableWritable()
  {
    return isWritable(PREMISESAVAILABLE);
  }
  
  public boolean isPremisesAvailableReadable()
  {
    return isReadable(PREMISESAVAILABLE);
  }
  
  public boolean isPremisesAvailableModified()
  {
    return isModified(PREMISESAVAILABLE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPremisesAvailableMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREMISESAVAILABLE).getAttributeMdDTO();
  }
  
  public Integer getPremisesIncluded()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PREMISESINCLUDED));
  }
  
  public void setPremisesIncluded(Integer value)
  {
    if(value == null)
    {
      setValue(PREMISESINCLUDED, "");
    }
    else
    {
      setValue(PREMISESINCLUDED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPremisesIncludedWritable()
  {
    return isWritable(PREMISESINCLUDED);
  }
  
  public boolean isPremisesIncludedReadable()
  {
    return isReadable(PREMISESINCLUDED);
  }
  
  public boolean isPremisesIncludedModified()
  {
    return isModified(PREMISESINCLUDED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getPremisesIncludedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(PREMISESINCLUDED).getAttributeMdDTO();
  }
  
  public Integer getTreated()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void setTreated(Integer value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isTreatedWritable()
  {
    return isWritable(TREATED);
  }
  
  public boolean isTreatedReadable()
  {
    return isReadable(TREATED);
  }
  
  public boolean isTreatedModified()
  {
    return isModified(TREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getTreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(TREATED).getAttributeMdDTO();
  }
  
  public Integer getVisited()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(VISITED));
  }
  
  public void setVisited(Integer value)
  {
    if(value == null)
    {
      setValue(VISITED, "");
    }
    else
    {
      setValue(VISITED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isVisitedWritable()
  {
    return isWritable(VISITED);
  }
  
  public boolean isVisitedReadable()
  {
    return isReadable(VISITED);
  }
  
  public boolean isVisitedModified()
  {
    return isModified(VISITED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getVisitedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(VISITED).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[] getInterventionMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO.CLASS, "getInterventionMethods", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[] getInterventionMethods(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO.CLASS, "getInterventionMethods", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[][] getInterventionMethodsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO.CLASS, "getInterventionMethodsForViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[] getNonTreatmentReasons()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO.CLASS, "getNonTreatmentReasons", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[] getNonTreatmentReasons(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO.CLASS, "getNonTreatmentReasons", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[][] getNonTreatmentReasonsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO.CLASS, "getNonTreatmentReasonsForViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static AggregatedPremiseVisitViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (AggregatedPremiseVisitViewDTO) dto;
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
