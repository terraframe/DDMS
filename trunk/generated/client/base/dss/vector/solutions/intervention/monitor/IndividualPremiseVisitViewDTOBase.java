package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = -2052243677)
public abstract class IndividualPremiseVisitViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView";
  private static final long serialVersionUID = -2052243677;
  
  protected IndividualPremiseVisitViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String POINT = "point";
  public static java.lang.String REASONSFORNOTTREATED = "reasonsForNotTreated";
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
  
  public String getGeoEntityId()
  {
    return getValue(GEOENTITY);
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
  
  public String getInterventionMethodId()
  {
    return getValue(INTERVENTIONMETHOD);
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
  
  public String getPointId()
  {
    return getValue(POINT);
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
  
  public dss.vector.solutions.ontology.TermDTO getReasonsForNotTreated()
  {
    if(getValue(REASONSFORNOTTREATED) == null || getValue(REASONSFORNOTTREATED).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(REASONSFORNOTTREATED));
    }
  }
  
  public String getReasonsForNotTreatedId()
  {
    return getValue(REASONSFORNOTTREATED);
  }
  
  public void setReasonsForNotTreated(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(REASONSFORNOTTREATED, "");
    }
    else
    {
      setValue(REASONSFORNOTTREATED, value.getId());
    }
  }
  
  public boolean isReasonsForNotTreatedWritable()
  {
    return isWritable(REASONSFORNOTTREATED);
  }
  
  public boolean isReasonsForNotTreatedReadable()
  {
    return isReadable(REASONSFORNOTTREATED);
  }
  
  public boolean isReasonsForNotTreatedModified()
  {
    return isModified(REASONSFORNOTTREATED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getReasonsForNotTreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(REASONSFORNOTTREATED).getAttributeMdDTO();
  }
  
  public Boolean getTreated()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(TREATED));
  }
  
  public void setTreated(Boolean value)
  {
    if(value == null)
    {
      setValue(TREATED, "");
    }
    else
    {
      setValue(TREATED, java.lang.Boolean.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getTreatedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(TREATED).getAttributeMdDTO();
  }
  
  public Boolean getVisited()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(VISITED));
  }
  
  public void setVisited(Boolean value)
  {
    if(value == null)
    {
      setValue(VISITED, "");
    }
    else
    {
      setValue(VISITED, java.lang.Boolean.toString(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getVisitedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(VISITED).getAttributeMdDTO();
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] views, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;", "[[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView;"};
    Object[] _parameters = new Object[]{views, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[] getInterventionMethods()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO.CLASS, "getInterventionMethods", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[] getInterventionMethods(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO.CLASS, "getInterventionMethods", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[][] getInterventionMethodsForViews(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO.CLASS, "getInterventionMethodsForViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[][]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] getViews(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO criteria)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.intervention.monitor.ControlInterventionView"};
    Object[] _parameters = new Object[]{criteria};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO.CLASS, "getViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static IndividualPremiseVisitViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (IndividualPremiseVisitViewDTO) dto;
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
