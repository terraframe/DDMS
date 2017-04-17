package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 938342702)
public abstract class ImmatureCollectionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.ImmatureCollectionView";
  private static final long serialVersionUID = 938342702;
  
  protected ImmatureCollectionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String CONTAINERGRID = "containerGrid";
  public static java.lang.String ENDDATE = "endDate";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String NOTES = "notes";
  public static java.lang.String NUMBEREXAMINED = "numberExamined";
  public static java.lang.String NUMBERINHABITANTS = "numberInhabitants";
  public static java.lang.String NUMBERWITHIMMATURES = "numberWithImmatures";
  public static java.lang.String NUMBERWITHLARVAE = "numberWithLarvae";
  public static java.lang.String NUMBERWITHPUPAE = "numberWithPupae";
  public static java.lang.String PREMISEID = "premiseId";
  public static java.lang.String PREMISESIZE = "premiseSize";
  public static java.lang.String PREMISETYPE = "premiseType";
  public static java.lang.String STARTDATE = "startDate";
  public static java.lang.String TAXON = "taxon";
  public static java.lang.String TAXONID = "taxonId";
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
    }
  }
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getContainerGrid()
  {
    if(getValue(CONTAINERGRID) == null || getValue(CONTAINERGRID).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(CONTAINERGRID));
    }
  }
  
  public String getContainerGridId()
  {
    return getValue(CONTAINERGRID);
  }
  
  public void setContainerGrid(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(CONTAINERGRID, "");
    }
    else
    {
      setValue(CONTAINERGRID, value.getId());
    }
  }
  
  public boolean isContainerGridWritable()
  {
    return isWritable(CONTAINERGRID);
  }
  
  public boolean isContainerGridReadable()
  {
    return isReadable(CONTAINERGRID);
  }
  
  public boolean isContainerGridModified()
  {
    return isModified(CONTAINERGRID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getContainerGridMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(CONTAINERGRID).getAttributeMdDTO();
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
  
  public String getNotes()
  {
    return getValue(NOTES);
  }
  
  public void setNotes(String value)
  {
    if(value == null)
    {
      setValue(NOTES, "");
    }
    else
    {
      setValue(NOTES, value);
    }
  }
  
  public boolean isNotesWritable()
  {
    return isWritable(NOTES);
  }
  
  public boolean isNotesReadable()
  {
    return isReadable(NOTES);
  }
  
  public boolean isNotesModified()
  {
    return isModified(NOTES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getNotesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(NOTES).getAttributeMdDTO();
  }
  
  public Integer getNumberExamined()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEREXAMINED));
  }
  
  public void setNumberExamined(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEREXAMINED, "");
    }
    else
    {
      setValue(NUMBEREXAMINED, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberExaminedWritable()
  {
    return isWritable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedReadable()
  {
    return isReadable(NUMBEREXAMINED);
  }
  
  public boolean isNumberExaminedModified()
  {
    return isModified(NUMBEREXAMINED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberExaminedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEREXAMINED).getAttributeMdDTO();
  }
  
  public Integer getNumberInhabitants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERINHABITANTS));
  }
  
  public void setNumberInhabitants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERINHABITANTS, "");
    }
    else
    {
      setValue(NUMBERINHABITANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberInhabitantsWritable()
  {
    return isWritable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsReadable()
  {
    return isReadable(NUMBERINHABITANTS);
  }
  
  public boolean isNumberInhabitantsModified()
  {
    return isModified(NUMBERINHABITANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberInhabitantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERINHABITANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberWithImmatures()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHIMMATURES));
  }
  
  public void setNumberWithImmatures(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHIMMATURES, "");
    }
    else
    {
      setValue(NUMBERWITHIMMATURES, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithImmaturesWritable()
  {
    return isWritable(NUMBERWITHIMMATURES);
  }
  
  public boolean isNumberWithImmaturesReadable()
  {
    return isReadable(NUMBERWITHIMMATURES);
  }
  
  public boolean isNumberWithImmaturesModified()
  {
    return isModified(NUMBERWITHIMMATURES);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithImmaturesMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHIMMATURES).getAttributeMdDTO();
  }
  
  public Integer getNumberWithLarvae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHLARVAE));
  }
  
  public void setNumberWithLarvae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHLARVAE, "");
    }
    else
    {
      setValue(NUMBERWITHLARVAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithLarvaeWritable()
  {
    return isWritable(NUMBERWITHLARVAE);
  }
  
  public boolean isNumberWithLarvaeReadable()
  {
    return isReadable(NUMBERWITHLARVAE);
  }
  
  public boolean isNumberWithLarvaeModified()
  {
    return isModified(NUMBERWITHLARVAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithLarvaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHLARVAE).getAttributeMdDTO();
  }
  
  public Integer getNumberWithPupae()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERWITHPUPAE));
  }
  
  public void setNumberWithPupae(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERWITHPUPAE, "");
    }
    else
    {
      setValue(NUMBERWITHPUPAE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberWithPupaeWritable()
  {
    return isWritable(NUMBERWITHPUPAE);
  }
  
  public boolean isNumberWithPupaeReadable()
  {
    return isReadable(NUMBERWITHPUPAE);
  }
  
  public boolean isNumberWithPupaeModified()
  {
    return isModified(NUMBERWITHPUPAE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberWithPupaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBERWITHPUPAE).getAttributeMdDTO();
  }
  
  public String getPremiseId()
  {
    return getValue(PREMISEID);
  }
  
  public void setPremiseId(String value)
  {
    if(value == null)
    {
      setValue(PREMISEID, "");
    }
    else
    {
      setValue(PREMISEID, value);
    }
  }
  
  public boolean isPremiseIdWritable()
  {
    return isWritable(PREMISEID);
  }
  
  public boolean isPremiseIdReadable()
  {
    return isReadable(PREMISEID);
  }
  
  public boolean isPremiseIdModified()
  {
    return isModified(PREMISEID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPremiseIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PREMISEID).getAttributeMdDTO();
  }
  
  public java.math.BigDecimal getPremiseSize()
  {
    return com.runwaysdk.constants.MdAttributeDecimalUtil.getTypeSafeValue(getValue(PREMISESIZE));
  }
  
  public void setPremiseSize(java.math.BigDecimal value)
  {
    if(value == null)
    {
      setValue(PREMISESIZE, "");
    }
    else
    {
      setValue(PREMISESIZE, value.toString());
    }
  }
  
  public boolean isPremiseSizeWritable()
  {
    return isWritable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeReadable()
  {
    return isReadable(PREMISESIZE);
  }
  
  public boolean isPremiseSizeModified()
  {
    return isModified(PREMISESIZE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDecMdDTO getPremiseSizeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDecMdDTO) getAttributeDTO(PREMISESIZE).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getPremiseType()
  {
    if(getValue(PREMISETYPE) == null || getValue(PREMISETYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(PREMISETYPE));
    }
  }
  
  public String getPremiseTypeId()
  {
    return getValue(PREMISETYPE);
  }
  
  public void setPremiseType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(PREMISETYPE, "");
    }
    else
    {
      setValue(PREMISETYPE, value.getId());
    }
  }
  
  public boolean isPremiseTypeWritable()
  {
    return isWritable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeReadable()
  {
    return isReadable(PREMISETYPE);
  }
  
  public boolean isPremiseTypeModified()
  {
    return isModified(PREMISETYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getPremiseTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(PREMISETYPE).getAttributeMdDTO();
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
  
  public dss.vector.solutions.ontology.TermDTO getTaxon()
  {
    if(getValue(TAXON) == null || getValue(TAXON).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(TAXON));
    }
  }
  
  public void setTaxon(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(TAXON, "");
    }
    else
    {
      setValue(TAXON, value.getId());
    }
  }
  
  public boolean isTaxonWritable()
  {
    return isWritable(TAXON);
  }
  
  public boolean isTaxonReadable()
  {
    return isReadable(TAXON);
  }
  
  public boolean isTaxonModified()
  {
    return isModified(TAXON);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getTaxonMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(TAXON).getAttributeMdDTO();
  }
  
  public String getTaxonId()
  {
    return getValue(TAXONID);
  }
  
  public void setTaxonId(String value)
  {
    if(value == null)
    {
      setValue(TAXONID, "");
    }
    else
    {
      setValue(TAXONID, value);
    }
  }
  
  public boolean isTaxonIdWritable()
  {
    return isWritable(TAXONID);
  }
  
  public boolean isTaxonIdReadable()
  {
    return isReadable(TAXONID);
  }
  
  public boolean isTaxonIdModified()
  {
    return isModified(TAXONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getTaxonIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(TAXONID).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.CollectionContainerViewDTO[] applyWithContainers(dss.vector.solutions.entomology.CollectionContainerViewDTO[] containers)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.CollectionContainerView;"};
    Object[] _parameters = new Object[]{containers};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "applyWithContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionContainerViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.CollectionContainerViewDTO[] applyWithContainers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.entomology.CollectionContainerViewDTO[] containers)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.entomology.CollectionContainerView;"};
    Object[] _parameters = new Object[]{id, containers};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "applyWithContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionContainerViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deletePremise()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "deletePremise", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deletePremise(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "deletePremise", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteTaxon()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "deleteTaxon", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteTaxon(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "deleteTaxon", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.ImmatureCollectionViewDTO getCollection(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.ImmatureCollectionViewDTO collection)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.ImmatureCollectionView"};
    Object[] _parameters = new Object[]{collection};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "getCollection", _declaredTypes);
    return (dss.vector.solutions.entomology.ImmatureCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.CollectionContainerViewDTO[] getContainers()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "getContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionContainerViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.CollectionContainerViewDTO[] getContainers(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "getContainers", _declaredTypes);
    return (dss.vector.solutions.entomology.CollectionContainerViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.ImmatureCollectionViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.entomology.ImmatureCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.ImmatureCollectionViewQueryDTO searchCollections(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.ImmatureCollectionViewDTO collection, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.ImmatureCollectionView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{collection, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.ImmatureCollectionViewDTO.CLASS, "searchCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.ImmatureCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static ImmatureCollectionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (ImmatureCollectionViewDTO) dto;
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
