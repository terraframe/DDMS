package dss.vector.solutions.intervention.monitor;

@com.runwaysdk.business.ClassSignature(hash = 684176890)
public abstract class ControlInterventionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.intervention.monitor.ControlInterventionView";
  private static final long serialVersionUID = 684176890;
  
  protected ControlInterventionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AGGREGATEDPREMISEUNIVERSAL = "aggregatedPremiseUniversal";
  public static java.lang.String COMMENTS = "comments";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INDIVIDULPREMISEUNIVERSAL = "individulPremiseUniversal";
  public static java.lang.String INSECTICIDEINTERVENTION = "insecticideIntervention";
  public static java.lang.String PERSONINTERVENTION = "personIntervention";
  public static java.lang.String STARTDATE = "startDate";
  public dss.vector.solutions.geo.GeoHierarchyDTO getAggregatedPremiseUniversal()
  {
    if(getValue(AGGREGATEDPREMISEUNIVERSAL) == null || getValue(AGGREGATEDPREMISEUNIVERSAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchyDTO.get(getRequest(), getValue(AGGREGATEDPREMISEUNIVERSAL));
    }
  }
  
  public String getAggregatedPremiseUniversalId()
  {
    return getValue(AGGREGATEDPREMISEUNIVERSAL);
  }
  
  public void setAggregatedPremiseUniversal(dss.vector.solutions.geo.GeoHierarchyDTO value)
  {
    if(value == null)
    {
      setValue(AGGREGATEDPREMISEUNIVERSAL, "");
    }
    else
    {
      setValue(AGGREGATEDPREMISEUNIVERSAL, value.getId());
    }
  }
  
  public boolean isAggregatedPremiseUniversalWritable()
  {
    return isWritable(AGGREGATEDPREMISEUNIVERSAL);
  }
  
  public boolean isAggregatedPremiseUniversalReadable()
  {
    return isReadable(AGGREGATEDPREMISEUNIVERSAL);
  }
  
  public boolean isAggregatedPremiseUniversalModified()
  {
    return isModified(AGGREGATEDPREMISEUNIVERSAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getAggregatedPremiseUniversalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(AGGREGATEDPREMISEUNIVERSAL).getAttributeMdDTO();
  }
  
  public String getComments()
  {
    return getValue(COMMENTS);
  }
  
  public void setComments(String value)
  {
    if(value == null)
    {
      setValue(COMMENTS, "");
    }
    else
    {
      setValue(COMMENTS, value);
    }
  }
  
  public boolean isCommentsWritable()
  {
    return isWritable(COMMENTS);
  }
  
  public boolean isCommentsReadable()
  {
    return isReadable(COMMENTS);
  }
  
  public boolean isCommentsModified()
  {
    return isModified(COMMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getCommentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(COMMENTS).getAttributeMdDTO();
  }
  
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
  
  public java.util.Date getEndDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(ENDDATE));
  }
  
  public void setEndDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(ENDDATE, "");
    }
    else
    {
      setValue(ENDDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isEndDateWritable()
  {
    return isWritable(ENDDATE);
  }
  
  public boolean isEndDateReadable()
  {
    return isReadable(ENDDATE);
  }
  
  public boolean isEndDateModified()
  {
    return isModified(ENDDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getEndDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(ENDDATE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.geo.GeoHierarchyDTO getIndividulPremiseUniversal()
  {
    if(getValue(INDIVIDULPREMISEUNIVERSAL) == null || getValue(INDIVIDULPREMISEUNIVERSAL).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.geo.GeoHierarchyDTO.get(getRequest(), getValue(INDIVIDULPREMISEUNIVERSAL));
    }
  }
  
  public String getIndividulPremiseUniversalId()
  {
    return getValue(INDIVIDULPREMISEUNIVERSAL);
  }
  
  public void setIndividulPremiseUniversal(dss.vector.solutions.geo.GeoHierarchyDTO value)
  {
    if(value == null)
    {
      setValue(INDIVIDULPREMISEUNIVERSAL, "");
    }
    else
    {
      setValue(INDIVIDULPREMISEUNIVERSAL, value.getId());
    }
  }
  
  public boolean isIndividulPremiseUniversalWritable()
  {
    return isWritable(INDIVIDULPREMISEUNIVERSAL);
  }
  
  public boolean isIndividulPremiseUniversalReadable()
  {
    return isReadable(INDIVIDULPREMISEUNIVERSAL);
  }
  
  public boolean isIndividulPremiseUniversalModified()
  {
    return isModified(INDIVIDULPREMISEUNIVERSAL);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getIndividulPremiseUniversalMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INDIVIDULPREMISEUNIVERSAL).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getInsecticideIntervention()
  {
    if(getValue(INSECTICIDEINTERVENTION) == null || getValue(INSECTICIDEINTERVENTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(INSECTICIDEINTERVENTION));
    }
  }
  
  public String getInsecticideInterventionId()
  {
    return getValue(INSECTICIDEINTERVENTION);
  }
  
  public void setInsecticideIntervention(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEINTERVENTION, "");
    }
    else
    {
      setValue(INSECTICIDEINTERVENTION, value.getId());
    }
  }
  
  public boolean isInsecticideInterventionWritable()
  {
    return isWritable(INSECTICIDEINTERVENTION);
  }
  
  public boolean isInsecticideInterventionReadable()
  {
    return isReadable(INSECTICIDEINTERVENTION);
  }
  
  public boolean isInsecticideInterventionModified()
  {
    return isModified(INSECTICIDEINTERVENTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideInterventionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDEINTERVENTION).getAttributeMdDTO();
  }
  
  public String getPersonIntervention()
  {
    return getValue(PERSONINTERVENTION);
  }
  
  public void setPersonIntervention(String value)
  {
    if(value == null)
    {
      setValue(PERSONINTERVENTION, "");
    }
    else
    {
      setValue(PERSONINTERVENTION, value);
    }
  }
  
  public boolean isPersonInterventionWritable()
  {
    return isWritable(PERSONINTERVENTION);
  }
  
  public boolean isPersonInterventionReadable()
  {
    return isReadable(PERSONINTERVENTION);
  }
  
  public boolean isPersonInterventionModified()
  {
    return isModified(PERSONINTERVENTION);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPersonInterventionMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PERSONINTERVENTION).getAttributeMdDTO();
  }
  
  public java.util.Date getStartDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(STARTDATE));
  }
  
  public void setStartDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(STARTDATE, "");
    }
    else
    {
      setValue(STARTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isStartDateWritable()
  {
    return isWritable(STARTDATE);
  }
  
  public boolean isStartDateReadable()
  {
    return isReadable(STARTDATE);
  }
  
  public boolean isStartDateModified()
  {
    return isModified(STARTDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getStartDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(STARTDATE).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] applyWithAggregatedPremiseViews(dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] premises, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[][] reasons, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;", "[[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView;", "[[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseMethodView;"};
    Object[] _parameters = new Object[]{premises, reasons, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithAggregatedPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] applyWithAggregatedPremiseViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] premises, dss.vector.solutions.intervention.monitor.AggregatedPremiseReasonViewDTO[][] reasons, dss.vector.solutions.intervention.monitor.AggregatedPremiseMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView;", "[[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseReasonView;", "[[Ldss.vector.solutions.intervention.monitor.AggregatedPremiseMethodView;"};
    Object[] _parameters = new Object[]{id, premises, reasons, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithAggregatedPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] applyWithIndividualPremiseViews(dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] premises, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;", "[[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView;"};
    Object[] _parameters = new Object[]{premises, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithIndividualPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] applyWithIndividualPremiseViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] premises, dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitView;", "[[Ldss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethodView;"};
    Object[] _parameters = new Object[]{id, premises, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithIndividualPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[] applyWithInsecticideInterventionViews(dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.InsecticideInterventionView;"};
    Object[] _parameters = new Object[]{views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithInsecticideInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[] applyWithInsecticideInterventionViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[] views)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.InsecticideInterventionView;"};
    Object[] _parameters = new Object[]{id, views};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithInsecticideInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] applyWithPersonInterventionViews(dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] premises, dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.intervention.monitor.PersonInterventionView;", "[[Ldss.vector.solutions.intervention.monitor.PersonInterventionMethodView;"};
    Object[] _parameters = new Object[]{premises, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithPersonInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] applyWithPersonInterventionViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] premises, dss.vector.solutions.intervention.monitor.PersonInterventionMethodViewDTO[][] methods)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.intervention.monitor.PersonInterventionView;", "[[Ldss.vector.solutions.intervention.monitor.PersonInterventionMethodView;"};
    Object[] _parameters = new Object[]{id, premises, methods};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "applyWithPersonInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] getAggregatedPremiseViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getAggregatedPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[] getAggregatedPremiseViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getAggregatedPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] getIndividualPremiseViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getIndividualPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[] getIndividualPremiseViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getIndividualPremiseViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.IndividualPremiseVisitViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[] getInsecticideInterventionViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getInsecticideInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[] getInsecticideInterventionViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getInsecticideInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.InsecticideInterventionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO getIntervention(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO dto)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.intervention.monitor.ControlInterventionView"};
    Object[] _parameters = new Object[]{dto};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getIntervention", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ControlInterventionViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ControlInterventionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] getPersonInterventionViews()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getPersonInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[] getPersonInterventionViews(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "getPersonInterventionViews", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.PersonInterventionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.intervention.monitor.ControlInterventionViewQueryDTO search(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO criteria, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.intervention.monitor.ControlInterventionView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{criteria, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.intervention.monitor.ControlInterventionViewDTO.CLASS, "search", _declaredTypes);
    return (dss.vector.solutions.intervention.monitor.ControlInterventionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ControlInterventionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ControlInterventionViewDTO) dto;
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
