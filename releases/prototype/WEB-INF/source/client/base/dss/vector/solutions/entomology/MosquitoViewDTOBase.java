package dss.vector.solutions.entomology;

public abstract class MosquitoViewDTOBase extends com.terraframe.mojo.business.ViewDTO implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoView";
  protected MosquitoViewDTOBase(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String AACETATE = "aAcetate";
  public static java.lang.String ACHES = "acHES";
  public static java.lang.String ACHESMETHOD = "acHESMethod";
  public static java.lang.String ACHEV = "acHEV";
  public static java.lang.String ACHEVMETHOD = "acHEVMethod";
  public static java.lang.String ACHEW = "acHEW";
  public static java.lang.String ACHEWMETHOD = "acHEWMethod";
  public static java.lang.String BACETATE = "bAcetate";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String EKDR = "eKDR";
  public static java.lang.String EKDRMETHOD = "eKDRMethod";
  public static java.lang.String GABAG = "gABAG";
  public static java.lang.String GABAGMETHOD = "gABAGMethod";
  public static java.lang.String GABAS = "gABAS";
  public static java.lang.String GABASMETHOD = "gABASMethod";
  public static java.lang.String GSTCDNB = "gSTCDNB";
  public static java.lang.String GSTDCNB = "gSTDCNB";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String HEME = "heme";
  public static java.lang.String IACHE = "iAcHE";
  public static java.lang.String IACHEMETHOD = "iAcHEMethod";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTIFICATIONMETHOD = "identificationMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String P450 = "p450";
  public static java.lang.String PFALCIPARUM = "pFalciparum";
  public static java.lang.String PFALCIPARUMMETHOD = "pFalciparumMethod";
  public static java.lang.String PMALARIAE = "pMalariae";
  public static java.lang.String PMALARIAEMETHOD = "pMalariaeMethod";
  public static java.lang.String PNPA = "pNPA";
  public static java.lang.String POVALE = "pOvale";
  public static java.lang.String POVALEMETHOD = "pOvaleMethod";
  public static java.lang.String PVIVAX = "pVivax";
  public static java.lang.String PVIVAXMETHOD = "pVivaxMethod";
  public static java.lang.String SAMPLEID = "sampleId";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIE = "specie";
  public static java.lang.String TESTDATE = "testDate";
  public static java.lang.String WKDR = "wKDR";
  public static java.lang.String WKDRMETHOD = "wKDRMethod";
  public Boolean getAAcetate()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(AACETATE));
  }
  
  public void setAAcetate(Boolean value)
  {
    if(value == null)
    {
      setValue(AACETATE, "");
    }
    else
    {
      setValue(AACETATE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isAAcetateWritable()
  {
    return isWritable(AACETATE);
  }
  
  public boolean isAAcetateReadable()
  {
    return isReadable(AACETATE);
  }
  
  public boolean isAAcetateModified()
  {
    return isModified(AACETATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getAAcetateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("aAcetate").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getAcHES()
  {
    if(getValue(ACHES) == null || getValue(ACHES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(ACHES));
    }
  }
  
  public void setAcHES(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(ACHES, value.getId());
  }
  
  public boolean isAcHESWritable()
  {
    return isWritable(ACHES);
  }
  
  public boolean isAcHESReadable()
  {
    return isReadable(ACHES);
  }
  
  public boolean isAcHESModified()
  {
    return isModified(ACHES);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHESMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHES").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getAcHESMethod()
  {
    if(getValue(ACHESMETHOD) == null || getValue(ACHESMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(ACHESMETHOD));
    }
  }
  
  public void setAcHESMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
  {
    setValue(ACHESMETHOD, value.getId());
  }
  
  public boolean isAcHESMethodWritable()
  {
    return isWritable(ACHESMETHOD);
  }
  
  public boolean isAcHESMethodReadable()
  {
    return isReadable(ACHESMETHOD);
  }
  
  public boolean isAcHESMethodModified()
  {
    return isModified(ACHESMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHESMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHESMethod").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getAcHEV()
  {
    if(getValue(ACHEV) == null || getValue(ACHEV).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(ACHEV));
    }
  }
  
  public void setAcHEV(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(ACHEV, value.getId());
  }
  
  public boolean isAcHEVWritable()
  {
    return isWritable(ACHEV);
  }
  
  public boolean isAcHEVReadable()
  {
    return isReadable(ACHEV);
  }
  
  public boolean isAcHEVModified()
  {
    return isModified(ACHEV);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEVMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEV").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getAcHEVMethod()
  {
    if(getValue(ACHEVMETHOD) == null || getValue(ACHEVMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(ACHEVMETHOD));
    }
  }
  
  public void setAcHEVMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
  {
    setValue(ACHEVMETHOD, value.getId());
  }
  
  public boolean isAcHEVMethodWritable()
  {
    return isWritable(ACHEVMETHOD);
  }
  
  public boolean isAcHEVMethodReadable()
  {
    return isReadable(ACHEVMETHOD);
  }
  
  public boolean isAcHEVMethodModified()
  {
    return isModified(ACHEVMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEVMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEVMethod").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getAcHEW()
  {
    if(getValue(ACHEW) == null || getValue(ACHEW).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(ACHEW));
    }
  }
  
  public void setAcHEW(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(ACHEW, value.getId());
  }
  
  public boolean isAcHEWWritable()
  {
    return isWritable(ACHEW);
  }
  
  public boolean isAcHEWReadable()
  {
    return isReadable(ACHEW);
  }
  
  public boolean isAcHEWModified()
  {
    return isModified(ACHEW);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEWMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEW").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getAcHEWMethod()
  {
    if(getValue(ACHEWMETHOD) == null || getValue(ACHEWMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(ACHEWMETHOD));
    }
  }
  
  public void setAcHEWMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
  {
    setValue(ACHEWMETHOD, value.getId());
  }
  
  public boolean isAcHEWMethodWritable()
  {
    return isWritable(ACHEWMETHOD);
  }
  
  public boolean isAcHEWMethodReadable()
  {
    return isReadable(ACHEWMETHOD);
  }
  
  public boolean isAcHEWMethodModified()
  {
    return isModified(ACHEWMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getAcHEWMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("acHEWMethod").getAttributeMdDTO();
  }
  
  public Boolean getBAcetate()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(BACETATE));
  }
  
  public void setBAcetate(Boolean value)
  {
    if(value == null)
    {
      setValue(BACETATE, "");
    }
    else
    {
      setValue(BACETATE, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isBAcetateWritable()
  {
    return isWritable(BACETATE);
  }
  
  public boolean isBAcetateReadable()
  {
    return isReadable(BACETATE);
  }
  
  public boolean isBAcetateModified()
  {
    return isModified(BACETATE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getBAcetateMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("bAcetate").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO getCollection()
  {
    if(getValue(COLLECTION) == null || getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO.get(getRequest(), getValue(COLLECTION));
    }
  }
  
  public void setCollection(dss.vector.solutions.entomology.AbstractMosquitoCollectionDTO value)
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
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getEKDR()
  {
    if(getValue(EKDR) == null || getValue(EKDR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(EKDR));
    }
  }
  
  public void setEKDR(dss.vector.solutions.mo.MolecularAssayResultDTO value)
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
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getEKDRMethod()
  {
    if(getValue(EKDRMETHOD) == null || getValue(EKDRMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(EKDRMETHOD));
    }
  }
  
  public void setEKDRMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
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
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getGABAG()
  {
    if(getValue(GABAG) == null || getValue(GABAG).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(GABAG));
    }
  }
  
  public void setGABAG(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(GABAG, value.getId());
  }
  
  public boolean isGABAGWritable()
  {
    return isWritable(GABAG);
  }
  
  public boolean isGABAGReadable()
  {
    return isReadable(GABAG);
  }
  
  public boolean isGABAGModified()
  {
    return isModified(GABAG);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGABAGMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gABAG").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getGABAGMethod()
  {
    if(getValue(GABAGMETHOD) == null || getValue(GABAGMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(GABAGMETHOD));
    }
  }
  
  public void setGABAGMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
  {
    setValue(GABAGMETHOD, value.getId());
  }
  
  public boolean isGABAGMethodWritable()
  {
    return isWritable(GABAGMETHOD);
  }
  
  public boolean isGABAGMethodReadable()
  {
    return isReadable(GABAGMETHOD);
  }
  
  public boolean isGABAGMethodModified()
  {
    return isModified(GABAGMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGABAGMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gABAGMethod").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getGABAS()
  {
    if(getValue(GABAS) == null || getValue(GABAS).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(GABAS));
    }
  }
  
  public void setGABAS(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(GABAS, value.getId());
  }
  
  public boolean isGABASWritable()
  {
    return isWritable(GABAS);
  }
  
  public boolean isGABASReadable()
  {
    return isReadable(GABAS);
  }
  
  public boolean isGABASModified()
  {
    return isModified(GABAS);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGABASMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gABAS").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getGABASMethod()
  {
    if(getValue(GABASMETHOD) == null || getValue(GABASMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(GABASMETHOD));
    }
  }
  
  public void setGABASMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
  {
    setValue(GABASMETHOD, value.getId());
  }
  
  public boolean isGABASMethodWritable()
  {
    return isWritable(GABASMETHOD);
  }
  
  public boolean isGABASMethodReadable()
  {
    return isReadable(GABASMETHOD);
  }
  
  public boolean isGABASMethodModified()
  {
    return isModified(GABASMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getGABASMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("gABASMethod").getAttributeMdDTO();
  }
  
  public Boolean getGSTCDNB()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(GSTCDNB));
  }
  
  public void setGSTCDNB(Boolean value)
  {
    if(value == null)
    {
      setValue(GSTCDNB, "");
    }
    else
    {
      setValue(GSTCDNB, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isGSTCDNBWritable()
  {
    return isWritable(GSTCDNB);
  }
  
  public boolean isGSTCDNBReadable()
  {
    return isReadable(GSTCDNB);
  }
  
  public boolean isGSTCDNBModified()
  {
    return isModified(GSTCDNB);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getGSTCDNBMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("gSTCDNB").getAttributeMdDTO();
  }
  
  public Boolean getGSTDCNB()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(GSTDCNB));
  }
  
  public void setGSTDCNB(Boolean value)
  {
    if(value == null)
    {
      setValue(GSTDCNB, "");
    }
    else
    {
      setValue(GSTDCNB, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isGSTDCNBWritable()
  {
    return isWritable(GSTDCNB);
  }
  
  public boolean isGSTDCNBReadable()
  {
    return isReadable(GSTDCNB);
  }
  
  public boolean isGSTDCNBModified()
  {
    return isModified(GSTDCNB);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getGSTDCNBMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("gSTDCNB").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.GenerationDTO getGeneration()
  {
    if(getValue(GENERATION) == null || getValue(GENERATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.GenerationDTO.get(getRequest(), getValue(GENERATION));
    }
  }
  
  public void setGeneration(dss.vector.solutions.mo.GenerationDTO value)
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
  
  public Boolean getHeme()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(HEME));
  }
  
  public void setHeme(Boolean value)
  {
    if(value == null)
    {
      setValue(HEME, "");
    }
    else
    {
      setValue(HEME, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isHemeWritable()
  {
    return isWritable(HEME);
  }
  
  public boolean isHemeReadable()
  {
    return isReadable(HEME);
  }
  
  public boolean isHemeModified()
  {
    return isModified(HEME);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getHemeMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("heme").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getIAcHE()
  {
    if(getValue(IACHE) == null || getValue(IACHE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(IACHE));
    }
  }
  
  public void setIAcHE(dss.vector.solutions.mo.MolecularAssayResultDTO value)
  {
    setValue(IACHE, value.getId());
  }
  
  public boolean isIAcHEWritable()
  {
    return isWritable(IACHE);
  }
  
  public boolean isIAcHEReadable()
  {
    return isReadable(IACHE);
  }
  
  public boolean isIAcHEModified()
  {
    return isModified(IACHE);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIAcHEMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("iAcHE").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getIAcHEMethod()
  {
    if(getValue(IACHEMETHOD) == null || getValue(IACHEMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(IACHEMETHOD));
    }
  }
  
  public void setIAcHEMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
  {
    setValue(IACHEMETHOD, value.getId());
  }
  
  public boolean isIAcHEMethodWritable()
  {
    return isWritable(IACHEMETHOD);
  }
  
  public boolean isIAcHEMethodReadable()
  {
    return isReadable(IACHEMETHOD);
  }
  
  public boolean isIAcHEMethodModified()
  {
    return isModified(IACHEMETHOD);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO getIAcHEMethodMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO) getAttributeDTO("iAcHEMethod").getAttributeMdDTO();
  }
  
  public dss.vector.solutions.mo.IdentificationMethodDTO getIdentificationMethod()
  {
    if(getValue(IDENTIFICATIONMETHOD) == null || getValue(IDENTIFICATIONMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.IdentificationMethodDTO.get(getRequest(), getValue(IDENTIFICATIONMETHOD));
    }
  }
  
  public void setIdentificationMethod(dss.vector.solutions.mo.IdentificationMethodDTO value)
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
  
  public Boolean getP450()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(P450));
  }
  
  public void setP450(Boolean value)
  {
    if(value == null)
    {
      setValue(P450, "");
    }
    else
    {
      setValue(P450, java.lang.Boolean.toString(value));
    }
  }
  
  public boolean isP450Writable()
  {
    return isWritable(P450);
  }
  
  public boolean isP450Readable()
  {
    return isReadable(P450);
  }
  
  public boolean isP450Modified()
  {
    return isModified(P450);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getP450Md()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("p450").getAttributeMdDTO();
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
  
  public dss.vector.solutions.mo.InfectivityMethodologyDTO getPFalciparumMethod()
  {
    if(getValue(PFALCIPARUMMETHOD) == null || getValue(PFALCIPARUMMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(PFALCIPARUMMETHOD));
    }
  }
  
  public void setPFalciparumMethod(dss.vector.solutions.mo.InfectivityMethodologyDTO value)
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
  
  public dss.vector.solutions.mo.InfectivityMethodologyDTO getPMalariaeMethod()
  {
    if(getValue(PMALARIAEMETHOD) == null || getValue(PMALARIAEMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(PMALARIAEMETHOD));
    }
  }
  
  public void setPMalariaeMethod(dss.vector.solutions.mo.InfectivityMethodologyDTO value)
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
  
  public Boolean getPNPA()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(PNPA));
  }
  
  public void setPNPA(Boolean value)
  {
    if(value == null)
    {
      setValue(PNPA, "");
    }
    else
    {
      setValue(PNPA, java.lang.Boolean.toString(value));
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
  
  public final com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO getPNPAMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO("pNPA").getAttributeMdDTO();
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
  
  public dss.vector.solutions.mo.InfectivityMethodologyDTO getPOvaleMethod()
  {
    if(getValue(POVALEMETHOD) == null || getValue(POVALEMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(POVALEMETHOD));
    }
  }
  
  public void setPOvaleMethod(dss.vector.solutions.mo.InfectivityMethodologyDTO value)
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
  
  public dss.vector.solutions.mo.InfectivityMethodologyDTO getPVivaxMethod()
  {
    if(getValue(PVIVAXMETHOD) == null || getValue(PVIVAXMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InfectivityMethodologyDTO.get(getRequest(), getValue(PVIVAXMETHOD));
    }
  }
  
  public void setPVivaxMethod(dss.vector.solutions.mo.InfectivityMethodologyDTO value)
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
  
  public String getSampleId()
  {
    return getValue(SAMPLEID);
  }
  
  public void setSampleId(String value)
  {
    if(value == null)
    {
      setValue(SAMPLEID, "");
    }
    else
    {
      setValue(SAMPLEID, value);
    }
  }
  
  public boolean isSampleIdWritable()
  {
    return isWritable(SAMPLEID);
  }
  
  public boolean isSampleIdReadable()
  {
    return isReadable(SAMPLEID);
  }
  
  public boolean isSampleIdModified()
  {
    return isModified(SAMPLEID);
  }
  
  public final com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO getSampleIdMd()
  {
    return (com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO("sampleId").getAttributeMdDTO();
  }
  
  @SuppressWarnings("unchecked")
  public java.util.List<dss.vector.solutions.entomology.SexDTO> getSex()
  {
    return (java.util.List<dss.vector.solutions.entomology.SexDTO>) com.terraframe.mojo.transport.conversion.ConversionFacade.convertEnumDTOsFromEnumNames(getRequest(), "dss.vector.solutions.entomology.Sex", getEnumNames(SEX));
  }
  
  public java.util.List<String> getSexEnumNames()
  {
    return getEnumNames(SEX);
  }
  
  public void addSex(dss.vector.solutions.entomology.SexDTO enumDTO)
  {
    addEnumItem(SEX, enumDTO.toString());
  }
  
  public void removeSex(dss.vector.solutions.entomology.SexDTO enumDTO)
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
  
  public dss.vector.solutions.mo.SpecieDTO getSpecie()
  {
    if(getValue(SPECIE) == null || getValue(SPECIE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.SpecieDTO.get(getRequest(), getValue(SPECIE));
    }
  }
  
  public void setSpecie(dss.vector.solutions.mo.SpecieDTO value)
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
  
  public dss.vector.solutions.mo.MolecularAssayResultDTO getWKDR()
  {
    if(getValue(WKDR) == null || getValue(WKDR).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.MolecularAssayResultDTO.get(getRequest(), getValue(WKDR));
    }
  }
  
  public void setWKDR(dss.vector.solutions.mo.MolecularAssayResultDTO value)
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
  
  public dss.vector.solutions.mo.InsecticideMethodologyDTO getWKDRMethod()
  {
    if(getValue(WKDRMETHOD) == null || getValue(WKDRMETHOD).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.mo.InsecticideMethodologyDTO.get(getRequest(), getValue(WKDRMETHOD));
    }
  }
  
  public void setWKDRMethod(dss.vector.solutions.mo.InsecticideMethodologyDTO value)
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
  
  public static final dss.vector.solutions.entomology.MosquitoViewDTO[] saveAll(com.terraframe.mojo.constants.ClientRequestIF clientRequest, dss.vector.solutions.entomology.MosquitoViewDTO[] array)
  {
    String[] _declaredTypes = new String[]{"[Ldss.vector.solutions.entomology.MosquitoView;"};
    Object[] _parameters = new Object[]{array};
    com.terraframe.mojo.business.MethodMetaData _metadata = new com.terraframe.mojo.business.MethodMetaData(dss.vector.solutions.entomology.MosquitoViewDTO.CLASS, "saveAll", _declaredTypes);
    return (dss.vector.solutions.entomology.MosquitoViewDTO[]) clientRequest.invokeMethod(_metadata, null, _parameters);
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
