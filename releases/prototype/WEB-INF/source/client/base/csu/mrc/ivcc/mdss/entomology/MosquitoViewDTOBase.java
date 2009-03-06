package csu.mrc.ivcc.mdss.entomology;

public abstract class MosquitoViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.MosquitoView";
  protected MosquitoViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AESTERASE = "aEsterase";
  public static java.lang.String AESTERASEMETHOD = "aEsteraseMethod";
  public static java.lang.String ACHEBIOCHEMICAL = "acHEBiochemical";
  public static java.lang.String ACHEBIOCHEMICALMETHOD = "acHEBiochemicalMethod";
  public static java.lang.String ACHEMOLECULAR = "acHEMolecular";
  public static java.lang.String ACHEMOLECULARMETHOD = "acHEMolecularMethod";
  public static java.lang.String BESTERASE = "bEsterase";
  public static java.lang.String BESTERASEMETHOD = "bEsteraseMethod";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String EKDR = "eKDR";
  public static java.lang.String EKDRMETHOD = "eKDRMethod";
  public static java.lang.String GABA = "gABA";
  public static java.lang.String GABAMETHOD = "gABAMethod";
  public static java.lang.String GST = "gST";
  public static java.lang.String GSTMETHOD = "gSTMethod";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MONOOXYGENASE = "monooxygenase";
  public static java.lang.String MONOOXYGENASEMETHOD = "monooxygenaseMethod";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String PFALCIPARUM = "pFalciparum";
  public static java.lang.String PFALCIPARUMMETHOD = "pFalciparumMethod";
  public static java.lang.String PMALARIAE = "pMalariae";
  public static java.lang.String PMALARIAEMETHOD = "pMalariaeMethod";
  public static java.lang.String PNPA = "pNPA";
  public static java.lang.String PNPAMETHOD = "pNPAMethod";
  public static java.lang.String POVALE = "pOvale";
  public static java.lang.String POVALEMETHOD = "pOvaleMethod";
  public static java.lang.String PVIVAX = "pVivax";
  public static java.lang.String PVIVAXMETHOD = "pVivaxMethod";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIE = "specie";
  public static java.lang.String TESTDATE = "testDate";
  public static java.lang.String WKDR = "wKDR";
  public static java.lang.String WKDRMETHOD = "wKDRMethod";
  public Integer getAEsterase()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AESTERASE));
  }
  
  public void setAEsterase(Integer value)
  {
    if(value == null)
    {
      setValue(AESTERASE, "");
    }
    else
    {
      setValue(AESTERASE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isAEsteraseWritable()
  {
    return isWritable(AESTERASE);
  }
  
  public boolean isAEsteraseReadable()
  {
    return isReadable(AESTERASE);
  }
  
  public boolean isAEsteraseModified()
  {
    return isModified(AESTERASE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getAEsteraseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("aEsterase").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO getAEsteraseMethod()
  {
    return csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO.get(getRequest(), getValue(AESTERASEMETHOD));
  }
  
  public void setAEsteraseMethod(csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO value)
  {
    setValue(AESTERASEMETHOD, value.getId());
  }
  
  public boolean isAEsteraseMethodWritable()
  {
    return isWritable(AESTERASEMETHOD);
  }
  
  public boolean isAEsteraseMethodReadable()
  {
    return isReadable(AESTERASEMETHOD);
  }
  
  public boolean isAEsteraseMethodModified()
  {
    return isModified(AESTERASEMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAEsteraseMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("aEsteraseMethod").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO getAcHEBiochemical()
  {
    return csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.get(getRequest(), getValue(ACHEBIOCHEMICAL));
  }
  
  public void setAcHEBiochemical(csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO value)
  {
    setValue(ACHEBIOCHEMICAL, value.getId());
  }
  
  public boolean isAcHEBiochemicalWritable()
  {
    return isWritable(ACHEBIOCHEMICAL);
  }
  
  public boolean isAcHEBiochemicalReadable()
  {
    return isReadable(ACHEBIOCHEMICAL);
  }
  
  public boolean isAcHEBiochemicalModified()
  {
    return isModified(ACHEBIOCHEMICAL);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEBiochemicalMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEBiochemical").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO getAcHEBiochemicalMethod()
  {
    return csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO.get(getRequest(), getValue(ACHEBIOCHEMICALMETHOD));
  }
  
  public void setAcHEBiochemicalMethod(csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO value)
  {
    setValue(ACHEBIOCHEMICALMETHOD, value.getId());
  }
  
  public boolean isAcHEBiochemicalMethodWritable()
  {
    return isWritable(ACHEBIOCHEMICALMETHOD);
  }
  
  public boolean isAcHEBiochemicalMethodReadable()
  {
    return isReadable(ACHEBIOCHEMICALMETHOD);
  }
  
  public boolean isAcHEBiochemicalMethodModified()
  {
    return isModified(ACHEBIOCHEMICALMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEBiochemicalMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEBiochemicalMethod").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO getAcHEMolecular()
  {
    return csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.get(getRequest(), getValue(ACHEMOLECULAR));
  }
  
  public void setAcHEMolecular(csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO value)
  {
    setValue(ACHEMOLECULAR, value.getId());
  }
  
  public boolean isAcHEMolecularWritable()
  {
    return isWritable(ACHEMOLECULAR);
  }
  
  public boolean isAcHEMolecularReadable()
  {
    return isReadable(ACHEMOLECULAR);
  }
  
  public boolean isAcHEMolecularModified()
  {
    return isModified(ACHEMOLECULAR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEMolecularMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEMolecular").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO getAcHEMolecularMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(ACHEMOLECULARMETHOD));
  }
  
  public void setAcHEMolecularMethod(csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO value)
  {
    setValue(ACHEMOLECULARMETHOD, value.getId());
  }
  
  public boolean isAcHEMolecularMethodWritable()
  {
    return isWritable(ACHEMOLECULARMETHOD);
  }
  
  public boolean isAcHEMolecularMethodReadable()
  {
    return isReadable(ACHEMOLECULARMETHOD);
  }
  
  public boolean isAcHEMolecularMethodModified()
  {
    return isModified(ACHEMOLECULARMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEMolecularMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEMolecularMethod").getAttributeMdDTO();
  }
  
  public Integer getBEsterase()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(BESTERASE));
  }
  
  public void setBEsterase(Integer value)
  {
    if(value == null)
    {
      setValue(BESTERASE, "");
    }
    else
    {
      setValue(BESTERASE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isBEsteraseWritable()
  {
    return isWritable(BESTERASE);
  }
  
  public boolean isBEsteraseReadable()
  {
    return isReadable(BESTERASE);
  }
  
  public boolean isBEsteraseModified()
  {
    return isModified(BESTERASE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getBEsteraseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("bEsterase").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO getBEsteraseMethod()
  {
    return csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO.get(getRequest(), getValue(BESTERASEMETHOD));
  }
  
  public void setBEsteraseMethod(csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO value)
  {
    setValue(BESTERASEMETHOD, value.getId());
  }
  
  public boolean isBEsteraseMethodWritable()
  {
    return isWritable(BESTERASEMETHOD);
  }
  
  public boolean isBEsteraseMethodReadable()
  {
    return isReadable(BESTERASEMETHOD);
  }
  
  public boolean isBEsteraseMethodModified()
  {
    return isModified(BESTERASEMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getBEsteraseMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("bEsteraseMethod").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO getCollection()
  {
    return csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO.get(getRequest(), getValue(COLLECTION));
  }
  
  public void setCollection(csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionDTO value)
  {
    setValue(COLLECTION, value.getId());
  }
  
  public boolean isCollectionWritable()
  {
    return isWritable(COLLECTION);
  }
  
  public boolean isCollectionReadable()
  {
    return isReadable(COLLECTION);
  }
  
  public boolean isCollectionModified()
  {
    return isModified(COLLECTION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getCollectionMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("collection").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO getEKDR()
  {
    return csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.get(getRequest(), getValue(EKDR));
  }
  
  public void setEKDR(csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO value)
  {
    setValue(EKDR, value.getId());
  }
  
  public boolean isEKDRWritable()
  {
    return isWritable(EKDR);
  }
  
  public boolean isEKDRReadable()
  {
    return isReadable(EKDR);
  }
  
  public boolean isEKDRModified()
  {
    return isModified(EKDR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEKDRMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("eKDR").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO getEKDRMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(EKDRMETHOD));
  }
  
  public void setEKDRMethod(csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO value)
  {
    setValue(EKDRMETHOD, value.getId());
  }
  
  public boolean isEKDRMethodWritable()
  {
    return isWritable(EKDRMETHOD);
  }
  
  public boolean isEKDRMethodReadable()
  {
    return isReadable(EKDRMETHOD);
  }
  
  public boolean isEKDRMethodModified()
  {
    return isModified(EKDRMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getEKDRMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("eKDRMethod").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO getGABA()
  {
    return csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.get(getRequest(), getValue(GABA));
  }
  
  public void setGABA(csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO value)
  {
    setValue(GABA, value.getId());
  }
  
  public boolean isGABAWritable()
  {
    return isWritable(GABA);
  }
  
  public boolean isGABAReadable()
  {
    return isReadable(GABA);
  }
  
  public boolean isGABAModified()
  {
    return isModified(GABA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGABAMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gABA").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO getGABAMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(GABAMETHOD));
  }
  
  public void setGABAMethod(csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO value)
  {
    setValue(GABAMETHOD, value.getId());
  }
  
  public boolean isGABAMethodWritable()
  {
    return isWritable(GABAMETHOD);
  }
  
  public boolean isGABAMethodReadable()
  {
    return isReadable(GABAMETHOD);
  }
  
  public boolean isGABAMethodModified()
  {
    return isModified(GABAMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGABAMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gABAMethod").getAttributeMdDTO();
  }
  
  public Integer getGST()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(GST));
  }
  
  public void setGST(Integer value)
  {
    if(value == null)
    {
      setValue(GST, "");
    }
    else
    {
      setValue(GST, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isGSTWritable()
  {
    return isWritable(GST);
  }
  
  public boolean isGSTReadable()
  {
    return isReadable(GST);
  }
  
  public boolean isGSTModified()
  {
    return isModified(GST);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getGSTMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("gST").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO getGSTMethod()
  {
    return csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO.get(getRequest(), getValue(GSTMETHOD));
  }
  
  public void setGSTMethod(csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO value)
  {
    setValue(GSTMETHOD, value.getId());
  }
  
  public boolean isGSTMethodWritable()
  {
    return isWritable(GSTMETHOD);
  }
  
  public boolean isGSTMethodReadable()
  {
    return isReadable(GSTMETHOD);
  }
  
  public boolean isGSTMethodModified()
  {
    return isModified(GSTMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGSTMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gSTMethod").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.GenerationDTO getGeneration()
  {
    return csu.mrc.ivcc.mdss.mo.GenerationDTO.get(getRequest(), getValue(GENERATION));
  }
  
  public void setGeneration(csu.mrc.ivcc.mdss.mo.GenerationDTO value)
  {
    setValue(GENERATION, value.getId());
  }
  
  public boolean isGenerationWritable()
  {
    return isWritable(GENERATION);
  }
  
  public boolean isGenerationReadable()
  {
    return isReadable(GENERATION);
  }
  
  public boolean isGenerationModified()
  {
    return isModified(GENERATION);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGenerationMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("generation").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO getIdentificationMethod()
  {
    return csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO.get(getRequest(), getValue(IDENTIFICATIONMETHOD));
  }
  
  public void setIdentificationMethod(csu.mrc.ivcc.mdss.mo.IdentificationMethodDTO value)
  {
    setValue(IDENTIFICATIONMETHOD, value.getId());
  }
  
  public boolean isIdentificationMethodWritable()
  {
    return isWritable(IDENTIFICATIONMETHOD);
  }
  
  public boolean isIdentificationMethodReadable()
  {
    return isReadable(IDENTIFICATIONMETHOD);
  }
  
  public boolean isIdentificationMethodModified()
  {
    return isModified(IDENTIFICATIONMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIdentificationMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("identificationMethod").getAttributeMdDTO();
  }
  
  public Boolean getIsofemale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void setIsofemale(Boolean value)
  {
    if(value == null)
    {
      setValue(ISOFEMALE, "");
    }
    else
    {
      setValue(ISOFEMALE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isIsofemaleWritable()
  {
    return isWritable(ISOFEMALE);
  }
  
  public boolean isIsofemaleReadable()
  {
    return isReadable(ISOFEMALE);
  }
  
  public boolean isIsofemaleModified()
  {
    return isModified(ISOFEMALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getIsofemaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("isofemale").getAttributeMdDTO();
  }
  
  public Integer getMonooxygenase()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(MONOOXYGENASE));
  }
  
  public void setMonooxygenase(Integer value)
  {
    if(value == null)
    {
      setValue(MONOOXYGENASE, "");
    }
    else
    {
      setValue(MONOOXYGENASE, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isMonooxygenaseWritable()
  {
    return isWritable(MONOOXYGENASE);
  }
  
  public boolean isMonooxygenaseReadable()
  {
    return isReadable(MONOOXYGENASE);
  }
  
  public boolean isMonooxygenaseModified()
  {
    return isModified(MONOOXYGENASE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getMonooxygenaseMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("monooxygenase").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO getMonooxygenaseMethod()
  {
    return csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO.get(getRequest(), getValue(MONOOXYGENASEMETHOD));
  }
  
  public void setMonooxygenaseMethod(csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO value)
  {
    setValue(MONOOXYGENASEMETHOD, value.getId());
  }
  
  public boolean isMonooxygenaseMethodWritable()
  {
    return isWritable(MONOOXYGENASEMETHOD);
  }
  
  public boolean isMonooxygenaseMethodReadable()
  {
    return isReadable(MONOOXYGENASEMETHOD);
  }
  
  public boolean isMonooxygenaseMethodModified()
  {
    return isModified(MONOOXYGENASEMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getMonooxygenaseMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("monooxygenaseMethod").getAttributeMdDTO();
  }
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void setMosquitoId(String value)
  {
    if(value == null)
    {
      setValue(MOSQUITOID, "");
    }
    else
    {
      setValue(MOSQUITOID, value);
    }
  }
  
  public boolean isMosquitoIdWritable()
  {
    return isWritable(MOSQUITOID);
  }
  
  public boolean isMosquitoIdReadable()
  {
    return isReadable(MOSQUITOID);
  }
  
  public boolean isMosquitoIdModified()
  {
    return isModified(MOSQUITOID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getMosquitoIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("mosquitoId").getAttributeMdDTO();
  }
  
  public Boolean getPFalciparum()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PFALCIPARUM));
  }
  
  public void setPFalciparum(Boolean value)
  {
    if(value == null)
    {
      setValue(PFALCIPARUM, "");
    }
    else
    {
      setValue(PFALCIPARUM, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPFalciparumWritable()
  {
    return isWritable(PFALCIPARUM);
  }
  
  public boolean isPFalciparumReadable()
  {
    return isReadable(PFALCIPARUM);
  }
  
  public boolean isPFalciparumModified()
  {
    return isModified(PFALCIPARUM);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPFalciparumMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("pFalciparum").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO getPFalciparumMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(PFALCIPARUMMETHOD));
  }
  
  public void setPFalciparumMethod(csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO value)
  {
    setValue(PFALCIPARUMMETHOD, value.getId());
  }
  
  public boolean isPFalciparumMethodWritable()
  {
    return isWritable(PFALCIPARUMMETHOD);
  }
  
  public boolean isPFalciparumMethodReadable()
  {
    return isReadable(PFALCIPARUMMETHOD);
  }
  
  public boolean isPFalciparumMethodModified()
  {
    return isModified(PFALCIPARUMMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPFalciparumMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("pFalciparumMethod").getAttributeMdDTO();
  }
  
  public Boolean getPMalariae()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PMALARIAE));
  }
  
  public void setPMalariae(Boolean value)
  {
    if(value == null)
    {
      setValue(PMALARIAE, "");
    }
    else
    {
      setValue(PMALARIAE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPMalariaeWritable()
  {
    return isWritable(PMALARIAE);
  }
  
  public boolean isPMalariaeReadable()
  {
    return isReadable(PMALARIAE);
  }
  
  public boolean isPMalariaeModified()
  {
    return isModified(PMALARIAE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPMalariaeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("pMalariae").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO getPMalariaeMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(PMALARIAEMETHOD));
  }
  
  public void setPMalariaeMethod(csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO value)
  {
    setValue(PMALARIAEMETHOD, value.getId());
  }
  
  public boolean isPMalariaeMethodWritable()
  {
    return isWritable(PMALARIAEMETHOD);
  }
  
  public boolean isPMalariaeMethodReadable()
  {
    return isReadable(PMALARIAEMETHOD);
  }
  
  public boolean isPMalariaeMethodModified()
  {
    return isModified(PMALARIAEMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPMalariaeMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("pMalariaeMethod").getAttributeMdDTO();
  }
  
  public Integer getPNPA()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(PNPA));
  }
  
  public void setPNPA(Integer value)
  {
    if(value == null)
    {
      setValue(PNPA, "");
    }
    else
    {
      setValue(PNPA, java.lang.Integer.toString(value));
    }
  }
  
  public boolean isPNPAWritable()
  {
    return isWritable(PNPA);
  }
  
  public boolean isPNPAReadable()
  {
    return isReadable(PNPA);
  }
  
  public boolean isPNPAModified()
  {
    return isModified(PNPA);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO getPNPAMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO) getAttributeDTO("pNPA").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO getPNPAMethod()
  {
    return csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO.get(getRequest(), getValue(PNPAMETHOD));
  }
  
  public void setPNPAMethod(csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyDTO value)
  {
    setValue(PNPAMETHOD, value.getId());
  }
  
  public boolean isPNPAMethodWritable()
  {
    return isWritable(PNPAMETHOD);
  }
  
  public boolean isPNPAMethodReadable()
  {
    return isReadable(PNPAMETHOD);
  }
  
  public boolean isPNPAMethodModified()
  {
    return isModified(PNPAMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPNPAMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("pNPAMethod").getAttributeMdDTO();
  }
  
  public Boolean getPOvale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(POVALE));
  }
  
  public void setPOvale(Boolean value)
  {
    if(value == null)
    {
      setValue(POVALE, "");
    }
    else
    {
      setValue(POVALE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPOvaleWritable()
  {
    return isWritable(POVALE);
  }
  
  public boolean isPOvaleReadable()
  {
    return isReadable(POVALE);
  }
  
  public boolean isPOvaleModified()
  {
    return isModified(POVALE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPOvaleMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("pOvale").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO getPOvaleMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(POVALEMETHOD));
  }
  
  public void setPOvaleMethod(csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO value)
  {
    setValue(POVALEMETHOD, value.getId());
  }
  
  public boolean isPOvaleMethodWritable()
  {
    return isWritable(POVALEMETHOD);
  }
  
  public boolean isPOvaleMethodReadable()
  {
    return isReadable(POVALEMETHOD);
  }
  
  public boolean isPOvaleMethodModified()
  {
    return isModified(POVALEMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPOvaleMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("pOvaleMethod").getAttributeMdDTO();
  }
  
  public Boolean getPVivax()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PVIVAX));
  }
  
  public void setPVivax(Boolean value)
  {
    if(value == null)
    {
      setValue(PVIVAX, "");
    }
    else
    {
      setValue(PVIVAX, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isPVivaxWritable()
  {
    return isWritable(PVIVAX);
  }
  
  public boolean isPVivaxReadable()
  {
    return isReadable(PVIVAX);
  }
  
  public boolean isPVivaxModified()
  {
    return isModified(PVIVAX);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPVivaxMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("pVivax").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO getPVivaxMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(PVIVAXMETHOD));
  }
  
  public void setPVivaxMethod(csu.mrc.ivcc.mdss.mo.InfectivityMethodologyDTO value)
  {
    setValue(PVIVAXMETHOD, value.getId());
  }
  
  public boolean isPVivaxMethodWritable()
  {
    return isWritable(PVIVAXMETHOD);
  }
  
  public boolean isPVivaxMethodReadable()
  {
    return isReadable(PVIVAXMETHOD);
  }
  
  public boolean isPVivaxMethodModified()
  {
    return isModified(PVIVAXMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getPVivaxMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("pVivaxMethod").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<csu.mrc.ivcc.mdss.entomology.SexDTO> getSex()
  {
    return (java.util.List<csu.mrc.ivcc.mdss.entomology.SexDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "csu.mrc.ivcc.mdss.entomology.Sex", getEnumNames(SEX));
  }
  
  public java.util.List<String> getSexEnumNames()
  {
    return getEnumNames(SEX);
  }
  
  public void addSex(csu.mrc.ivcc.mdss.entomology.SexDTO enumDTO)
  {
    addEnumItem(SEX, enumDTO.toString());
  }
  
  public void removeSex(csu.mrc.ivcc.mdss.entomology.SexDTO enumDTO)
  {
    removeEnumItem(SEX, enumDTO.toString());
  }
  
  public void clearSex()
  {
    clearEnum(SEX);
  }
  
  public boolean isSexWritable()
  {
    return isWritable(SEX);
  }
  
  public boolean isSexReadable()
  {
    return isReadable(SEX);
  }
  
  public boolean isSexModified()
  {
    return isModified(SEX);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO getSexMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO) getAttributeDTO("sex").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.SpecieDTO getSpecie()
  {
    return csu.mrc.ivcc.mdss.mo.SpecieDTO.get(getRequest(), getValue(SPECIE));
  }
  
  public void setSpecie(csu.mrc.ivcc.mdss.mo.SpecieDTO value)
  {
    setValue(SPECIE, value.getId());
  }
  
  public boolean isSpecieWritable()
  {
    return isWritable(SPECIE);
  }
  
  public boolean isSpecieReadable()
  {
    return isReadable(SPECIE);
  }
  
  public boolean isSpecieModified()
  {
    return isModified(SPECIE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getSpecieMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("specie").getAttributeMdDTO();
  }
  
  public java.util.Date getTestDate()
  {
    return com.terraframe.mojo.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.terraframe.mojo.constants.Constants.DATE_FORMAT).format(value));
    }
  }
  
  public boolean isTestDateWritable()
  {
    return isWritable(TESTDATE);
  }
  
  public boolean isTestDateReadable()
  {
    return isReadable(TESTDATE);
  }
  
  public boolean isTestDateModified()
  {
    return isModified(TESTDATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeDateMdDTO getTestDateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeDateMdDTO) getAttributeDTO("testDate").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO getWKDR()
  {
    return csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO.get(getRequest(), getValue(WKDR));
  }
  
  public void setWKDR(csu.mrc.ivcc.mdss.mo.MolecularAssayResultDTO value)
  {
    setValue(WKDR, value.getId());
  }
  
  public boolean isWKDRWritable()
  {
    return isWritable(WKDR);
  }
  
  public boolean isWKDRReadable()
  {
    return isReadable(WKDR);
  }
  
  public boolean isWKDRModified()
  {
    return isModified(WKDR);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWKDRMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("wKDR").getAttributeMdDTO();
  }
  
  public csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO getWKDRMethod()
  {
    return csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(WKDRMETHOD));
  }
  
  public void setWKDRMethod(csu.mrc.ivcc.mdss.mo.InsecticideMethodologyDTO value)
  {
    setValue(WKDRMETHOD, value.getId());
  }
  
  public boolean isWKDRMethodWritable()
  {
    return isWritable(WKDRMETHOD);
  }
  
  public boolean isWKDRMethodReadable()
  {
    return isReadable(WKDRMETHOD);
  }
  
  public boolean isWKDRMethodModified()
  {
    return isModified(WKDRMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getWKDRMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("wKDRMethod").getAttributeMdDTO();
  }
  
  public static MosquitoViewDTO get(com.terraframe.mojo.constants.ClientRequestIF clientRequest, String id)
  {
    com.terraframe.mojo.business.ViewDTO dto = (com.terraframe.mojo.business.ViewDTO)clientRequest.get(id);
    
    return (MosquitoViewDTO) dto;
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
