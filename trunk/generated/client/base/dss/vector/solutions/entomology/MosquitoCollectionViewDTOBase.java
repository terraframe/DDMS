package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = 508147059)
public abstract class MosquitoCollectionViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollectionView";
  private static final long serialVersionUID = 508147059;
  
  protected MosquitoCollectionViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ABUNDANCE = "abundance";
  public static java.lang.String COLLECTIONDATE = "collectionDate";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String COLLECTIONMETHOD = "collectionMethod";
  public static java.lang.String COLLECTIONROUND = "collectionRound";
  public static java.lang.String COLLECTIONTYPE = "collectionType";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DATELASTSPRAYED = "dateLastSprayed";
  public static java.lang.String GEOENTITY = "geoEntity";
  public static java.lang.String ID = "id";
  public static java.lang.String INSECTICIDEBRAND = "insecticideBrand";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String NUMBEROFANIMALOCCUPANTS = "numberOfAnimalOccupants";
  public static java.lang.String NUMBEROFHUMANOCCUPANTS = "numberOfHumanOccupants";
  public static java.lang.String NUMBEROFLLINS = "numberOfLLINs";
  public static java.lang.String RESISTANCEASSAYCOMMENTS = "resistanceAssayComments";
  public Boolean getAbundance()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ABUNDANCE));
  }
  
  public void setAbundance(Boolean value)
  {
    if(value == null)
    {
      setValue(ABUNDANCE, "");
    }
    else
    {
      setValue(ABUNDANCE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAbundanceWritable()
  {
    return isWritable(ABUNDANCE);
  }
  
  public boolean isAbundanceReadable()
  {
    return isReadable(ABUNDANCE);
  }
  
  public boolean isAbundanceModified()
  {
    return isModified(ABUNDANCE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getAbundanceMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ABUNDANCE).getAttributeMdDTO();
  }
  
  public java.util.Date getCollectionDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(COLLECTIONDATE));
  }
  
  public void setCollectionDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(COLLECTIONDATE, "");
    }
    else
    {
      setValue(COLLECTIONDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isCollectionDateWritable()
  {
    return isWritable(COLLECTIONDATE);
  }
  
  public boolean isCollectionDateReadable()
  {
    return isReadable(COLLECTIONDATE);
  }
  
  public boolean isCollectionDateModified()
  {
    return isModified(COLLECTIONDATE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getCollectionDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(COLLECTIONDATE).getAttributeMdDTO();
  }
  
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
  
  public dss.vector.solutions.ontology.TermDTO getCollectionMethod()
  {
    if(getValue(COLLECTIONMETHOD) == null || getValue(COLLECTIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONMETHOD));
    }
  }
  
  public String getCollectionMethodId()
  {
    return getValue(COLLECTIONMETHOD);
  }
  
  public void setCollectionMethod(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONMETHOD, "");
    }
    else
    {
      setValue(COLLECTIONMETHOD, value.getId());
    }
  }
  
  public boolean isCollectionMethodWritable()
  {
    return isWritable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodReadable()
  {
    return isReadable(COLLECTIONMETHOD);
  }
  
  public boolean isCollectionMethodModified()
  {
    return isModified(COLLECTIONMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONMETHOD).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCollectionRound()
  {
    if(getValue(COLLECTIONROUND) == null || getValue(COLLECTIONROUND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONROUND));
    }
  }
  
  public String getCollectionRoundId()
  {
    return getValue(COLLECTIONROUND);
  }
  
  public void setCollectionRound(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONROUND, "");
    }
    else
    {
      setValue(COLLECTIONROUND, value.getId());
    }
  }
  
  public boolean isCollectionRoundWritable()
  {
    return isWritable(COLLECTIONROUND);
  }
  
  public boolean isCollectionRoundReadable()
  {
    return isReadable(COLLECTIONROUND);
  }
  
  public boolean isCollectionRoundModified()
  {
    return isModified(COLLECTIONROUND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionRoundMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONROUND).getAttributeMdDTO();
  }
  
  public dss.vector.solutions.ontology.TermDTO getCollectionType()
  {
    if(getValue(COLLECTIONTYPE) == null || getValue(COLLECTIONTYPE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.TermDTO.get(getRequest(), getValue(COLLECTIONTYPE));
    }
  }
  
  public String getCollectionTypeId()
  {
    return getValue(COLLECTIONTYPE);
  }
  
  public void setCollectionType(dss.vector.solutions.ontology.TermDTO value)
  {
    if(value == null)
    {
      setValue(COLLECTIONTYPE, "");
    }
    else
    {
      setValue(COLLECTIONTYPE, value.getId());
    }
  }
  
  public boolean isCollectionTypeWritable()
  {
    return isWritable(COLLECTIONTYPE);
  }
  
  public boolean isCollectionTypeReadable()
  {
    return isReadable(COLLECTIONTYPE);
  }
  
  public boolean isCollectionTypeModified()
  {
    return isModified(COLLECTIONTYPE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getCollectionTypeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(COLLECTIONTYPE).getAttributeMdDTO();
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
  
  public java.util.Date getDateLastSprayed()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(DATELASTSPRAYED));
  }
  
  public void setDateLastSprayed(java.util.Date value)
  {
    if(value == null)
    {
      setValue(DATELASTSPRAYED, "");
    }
    else
    {
      setValue(DATELASTSPRAYED, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isDateLastSprayedWritable()
  {
    return isWritable(DATELASTSPRAYED);
  }
  
  public boolean isDateLastSprayedReadable()
  {
    return isReadable(DATELASTSPRAYED);
  }
  
  public boolean isDateLastSprayedModified()
  {
    return isModified(DATELASTSPRAYED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getDateLastSprayedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(DATELASTSPRAYED).getAttributeMdDTO();
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
  
  public dss.vector.solutions.irs.InsecticideBrandDTO getInsecticideBrand()
  {
    if(getValue(INSECTICIDEBRAND) == null || getValue(INSECTICIDEBRAND).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.irs.InsecticideBrandDTO.get(getRequest(), getValue(INSECTICIDEBRAND));
    }
  }
  
  public String getInsecticideBrandId()
  {
    return getValue(INSECTICIDEBRAND);
  }
  
  public void setInsecticideBrand(dss.vector.solutions.irs.InsecticideBrandDTO value)
  {
    if(value == null)
    {
      setValue(INSECTICIDEBRAND, "");
    }
    else
    {
      setValue(INSECTICIDEBRAND, value.getId());
    }
  }
  
  public boolean isInsecticideBrandWritable()
  {
    return isWritable(INSECTICIDEBRAND);
  }
  
  public boolean isInsecticideBrandReadable()
  {
    return isReadable(INSECTICIDEBRAND);
  }
  
  public boolean isInsecticideBrandModified()
  {
    return isModified(INSECTICIDEBRAND);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeReferenceMdDTO getInsecticideBrandMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO(INSECTICIDEBRAND).getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.LifeStageDTO> getLifeStage()
  {
    return (java.util.List<dss.vector.solutions.entomology.LifeStageDTO>) com.runwaysdk.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), dss.vector.solutions.entomology.LifeStageDTO.CLASS, getEnumNames(LIFESTAGE));
  }
  
  public java.util.List<String> getLifeStageEnumNames()
  {
    return getEnumNames(LIFESTAGE);
  }
  
  public void addLifeStage(dss.vector.solutions.entomology.LifeStageDTO enumDTO)
  {
    addEnumItem(LIFESTAGE, enumDTO.toString());
  }
  
  public void removeLifeStage(dss.vector.solutions.entomology.LifeStageDTO enumDTO)
  {
    removeEnumItem(LIFESTAGE, enumDTO.toString());
  }
  
  public void clearLifeStage()
  {
    clearEnum(LIFESTAGE);
  }
  
  public boolean isLifeStageWritable()
  {
    return isWritable(LIFESTAGE);
  }
  
  public boolean isLifeStageReadable()
  {
    return isReadable(LIFESTAGE);
  }
  
  public boolean isLifeStageModified()
  {
    return isModified(LIFESTAGE);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO getLifeStageMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO(LIFESTAGE).getAttributeMdDTO();
  }
  
  public Integer getNumberOfAnimalOccupants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFANIMALOCCUPANTS));
  }
  
  public void setNumberOfAnimalOccupants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFANIMALOCCUPANTS, "");
    }
    else
    {
      setValue(NUMBEROFANIMALOCCUPANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfAnimalOccupantsWritable()
  {
    return isWritable(NUMBEROFANIMALOCCUPANTS);
  }
  
  public boolean isNumberOfAnimalOccupantsReadable()
  {
    return isReadable(NUMBEROFANIMALOCCUPANTS);
  }
  
  public boolean isNumberOfAnimalOccupantsModified()
  {
    return isModified(NUMBEROFANIMALOCCUPANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfAnimalOccupantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFANIMALOCCUPANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberOfHumanOccupants()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFHUMANOCCUPANTS));
  }
  
  public void setNumberOfHumanOccupants(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFHUMANOCCUPANTS, "");
    }
    else
    {
      setValue(NUMBEROFHUMANOCCUPANTS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfHumanOccupantsWritable()
  {
    return isWritable(NUMBEROFHUMANOCCUPANTS);
  }
  
  public boolean isNumberOfHumanOccupantsReadable()
  {
    return isReadable(NUMBEROFHUMANOCCUPANTS);
  }
  
  public boolean isNumberOfHumanOccupantsModified()
  {
    return isModified(NUMBEROFHUMANOCCUPANTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfHumanOccupantsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFHUMANOCCUPANTS).getAttributeMdDTO();
  }
  
  public Integer getNumberOfLLINs()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBEROFLLINS));
  }
  
  public void setNumberOfLLINs(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBEROFLLINS, "");
    }
    else
    {
      setValue(NUMBEROFLLINS, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isNumberOfLLINsWritable()
  {
    return isWritable(NUMBEROFLLINS);
  }
  
  public boolean isNumberOfLLINsReadable()
  {
    return isReadable(NUMBEROFLLINS);
  }
  
  public boolean isNumberOfLLINsModified()
  {
    return isModified(NUMBEROFLLINS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeNumberMdDTO getNumberOfLLINsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeNumberMdDTO) getAttributeDTO(NUMBEROFLLINS).getAttributeMdDTO();
  }
  
  public String getResistanceAssayComments()
  {
    return getValue(RESISTANCEASSAYCOMMENTS);
  }
  
  public void setResistanceAssayComments(String value)
  {
    if(value == null)
    {
      setValue(RESISTANCEASSAYCOMMENTS, "");
    }
    else
    {
      setValue(RESISTANCEASSAYCOMMENTS, value);
    }
  }
  
  public boolean isResistanceAssayCommentsWritable()
  {
    return isWritable(RESISTANCEASSAYCOMMENTS);
  }
  
  public boolean isResistanceAssayCommentsReadable()
  {
    return isReadable(RESISTANCEASSAYCOMMENTS);
  }
  
  public boolean isResistanceAssayCommentsModified()
  {
    return isModified(RESISTANCEASSAYCOMMENTS);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeTextMdDTO getResistanceAssayCommentsMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeTextMdDTO) getAttributeDTO(RESISTANCEASSAYCOMMENTS).getAttributeMdDTO();
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO[] applyAll(dss.vector.solutions.entomology.SubCollectionViewDTO[] subCollections)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.SubCollectionView;"};
    Object[] _parameters = new Object[]{subCollections};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO[] applyAll(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, dss.vector.solutions.entomology.SubCollectionViewDTO[] subCollections)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "[Ldss.vector.solutions.entomology.SubCollectionView;"};
    Object[] _parameters = new Object[]{id, subCollections};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "applyAll", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final void deleteConcrete()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final void deleteConcrete(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "deleteConcrete", _declaredTypes);
    clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO getAdultDoseAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getAdultDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO getAdultDoseAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getAdultDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.BiochemicalAssayViewDTO[] getBiochemicalAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getBiochemicalAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.BiochemicalAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.BiochemicalAssayViewDTO[] getBiochemicalAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getBiochemicalAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.BiochemicalAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO getCollection(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.MosquitoCollectionViewDTO collection)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.MosquitoCollectionView"};
    Object[] _parameters = new Object[]{collection};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getCollection", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.DiagnosticAssayViewDTO[] getDiagnosticAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getDiagnosticAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.DiagnosticAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.DiagnosticAssayViewDTO[] getDiagnosticAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getDiagnosticAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.DiagnosticAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.InfectionAssayViewDTO[] getInfectionAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.InfectionAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.InfectionAssayViewDTO[] getInfectionAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.InfectionAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO getKnockDownAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getKnockDownAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO getKnockDownAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getKnockDownAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.KnockDownAssayQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO getLarvaeDoseAssays(java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getLarvaeDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO getLarvaeDoseAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"java.lang.String", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{id, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getLarvaeDoseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.MolecularAssayViewDTO[] getMolecularAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getMolecularAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.MolecularAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MolecularAssayViewDTO[] getMolecularAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getMolecularAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.MolecularAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO getMostRecent(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getMostRecent", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[] getPooledInfectionAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getPooledInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[] getPooledInfectionAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getPooledInfectionAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.PooledInfectionAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.SubCollectionViewDTO[] getSubCollections()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getSubCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.SubCollectionViewDTO[] getSubCollections(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getSubCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.SubCollectionViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public final dss.vector.solutions.entomology.TimeResponseAssayViewDTO[] getTimeResponseAssays()
  {
    String[] _declaredTypes = new String[]{};
    Object[] _parameters = new Object[]{};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getTimeResponseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.TimeResponseAssayViewDTO[]) getRequest().invokeMethod(_metadata, this, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.TimeResponseAssayViewDTO[] getTimeResponseAssays(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String id)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{id};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getTimeResponseAssays", _declaredTypes);
    return (dss.vector.solutions.entomology.TimeResponseAssayViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewDTO getViewByCollectionId(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String collectionId)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{collectionId};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "getViewByCollectionId", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final com.runwaysdk.business.ValueQueryDTO searchByValueQuery(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{value};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "searchByValueQuery", _declaredTypes);
    return (com.runwaysdk.business.ValueQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO searchCollection(com.runwaysdk.constants.ClientRequestIF clientRequest, java.lang.String value)
  {
    String[] _declaredTypes = new String[]{"java.lang.String"};
    Object[] _parameters = new Object[]{value};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "searchCollection", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static final dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO searchCollections(com.runwaysdk.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO collection, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
  {
    String[] _declaredTypes = new String[]{"dss.vector.solutions.entomology.SearchMosquitoCollectionView", "java.lang.String", "java.lang.Boolean", "java.lang.Integer", "java.lang.Integer"};
    Object[] _parameters = new Object[]{collection, sortAttribute, isAscending, pageSize, pageNumber};
    com.runwaysdk.business.MethodMetaData _metadata = new com.runwaysdk.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoCollectionViewDTO.CLASS, "searchCollections", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoCollectionViewQueryDTO) clientRequest.invokeMethod(_metadata, null, _parameters);
  }
  
  public static MosquitoCollectionViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MosquitoCollectionViewDTO) dto;
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
