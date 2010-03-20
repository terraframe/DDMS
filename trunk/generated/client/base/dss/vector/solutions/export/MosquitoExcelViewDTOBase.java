package dss.vector.solutions.export;

@com.runwaysdk.business.ClassSignature(hash = 1781978455)
public abstract class MosquitoExcelViewDTOBase extends com.runwaysdk.business.ViewDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.MosquitoExcelView";
  private static final long serialVersionUID = 1781978455;
  
  protected MosquitoExcelViewDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
  public static java.lang.String COLLECTIONID = "collectionId";
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
  public static java.lang.String MIXED = "mixed";
  public static java.lang.String MIXEDMETHOD = "mixedMethod";
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
  public String getAAcetate()
  {
    return getValue(AACETATE);
  }
  
  public void setAAcetate(String value)
  {
    if(value == null)
    {
      setValue(AACETATE, "");
    }
    else
    {
      setValue(AACETATE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAAcetateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(AACETATE).getAttributeMdDTO();
  }
  
  public String getAcHES()
  {
    return getValue(ACHES);
  }
  
  public void setAcHES(String value)
  {
    if(value == null)
    {
      setValue(ACHES, "");
    }
    else
    {
      setValue(ACHES, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAcHESMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACHES).getAttributeMdDTO();
  }
  
  public String getAcHESMethod()
  {
    return getValue(ACHESMETHOD);
  }
  
  public void setAcHESMethod(String value)
  {
    if(value == null)
    {
      setValue(ACHESMETHOD, "");
    }
    else
    {
      setValue(ACHESMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAcHESMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACHESMETHOD).getAttributeMdDTO();
  }
  
  public String getAcHEV()
  {
    return getValue(ACHEV);
  }
  
  public void setAcHEV(String value)
  {
    if(value == null)
    {
      setValue(ACHEV, "");
    }
    else
    {
      setValue(ACHEV, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAcHEVMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACHEV).getAttributeMdDTO();
  }
  
  public String getAcHEVMethod()
  {
    return getValue(ACHEVMETHOD);
  }
  
  public void setAcHEVMethod(String value)
  {
    if(value == null)
    {
      setValue(ACHEVMETHOD, "");
    }
    else
    {
      setValue(ACHEVMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAcHEVMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACHEVMETHOD).getAttributeMdDTO();
  }
  
  public String getAcHEW()
  {
    return getValue(ACHEW);
  }
  
  public void setAcHEW(String value)
  {
    if(value == null)
    {
      setValue(ACHEW, "");
    }
    else
    {
      setValue(ACHEW, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAcHEWMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACHEW).getAttributeMdDTO();
  }
  
  public String getAcHEWMethod()
  {
    return getValue(ACHEWMETHOD);
  }
  
  public void setAcHEWMethod(String value)
  {
    if(value == null)
    {
      setValue(ACHEWMETHOD, "");
    }
    else
    {
      setValue(ACHEWMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getAcHEWMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACHEWMETHOD).getAttributeMdDTO();
  }
  
  public String getBAcetate()
  {
    return getValue(BACETATE);
  }
  
  public void setBAcetate(String value)
  {
    if(value == null)
    {
      setValue(BACETATE, "");
    }
    else
    {
      setValue(BACETATE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getBAcetateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(BACETATE).getAttributeMdDTO();
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
  
  public String getEKDR()
  {
    return getValue(EKDR);
  }
  
  public void setEKDR(String value)
  {
    if(value == null)
    {
      setValue(EKDR, "");
    }
    else
    {
      setValue(EKDR, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEKDRMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EKDR).getAttributeMdDTO();
  }
  
  public String getEKDRMethod()
  {
    return getValue(EKDRMETHOD);
  }
  
  public void setEKDRMethod(String value)
  {
    if(value == null)
    {
      setValue(EKDRMETHOD, "");
    }
    else
    {
      setValue(EKDRMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getEKDRMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(EKDRMETHOD).getAttributeMdDTO();
  }
  
  public String getGABAG()
  {
    return getValue(GABAG);
  }
  
  public void setGABAG(String value)
  {
    if(value == null)
    {
      setValue(GABAG, "");
    }
    else
    {
      setValue(GABAG, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGABAGMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GABAG).getAttributeMdDTO();
  }
  
  public String getGABAGMethod()
  {
    return getValue(GABAGMETHOD);
  }
  
  public void setGABAGMethod(String value)
  {
    if(value == null)
    {
      setValue(GABAGMETHOD, "");
    }
    else
    {
      setValue(GABAGMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGABAGMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GABAGMETHOD).getAttributeMdDTO();
  }
  
  public String getGABAS()
  {
    return getValue(GABAS);
  }
  
  public void setGABAS(String value)
  {
    if(value == null)
    {
      setValue(GABAS, "");
    }
    else
    {
      setValue(GABAS, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGABASMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GABAS).getAttributeMdDTO();
  }
  
  public String getGABASMethod()
  {
    return getValue(GABASMETHOD);
  }
  
  public void setGABASMethod(String value)
  {
    if(value == null)
    {
      setValue(GABASMETHOD, "");
    }
    else
    {
      setValue(GABASMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGABASMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GABASMETHOD).getAttributeMdDTO();
  }
  
  public String getGSTCDNB()
  {
    return getValue(GSTCDNB);
  }
  
  public void setGSTCDNB(String value)
  {
    if(value == null)
    {
      setValue(GSTCDNB, "");
    }
    else
    {
      setValue(GSTCDNB, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGSTCDNBMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GSTCDNB).getAttributeMdDTO();
  }
  
  public String getGSTDCNB()
  {
    return getValue(GSTDCNB);
  }
  
  public void setGSTDCNB(String value)
  {
    if(value == null)
    {
      setValue(GSTDCNB, "");
    }
    else
    {
      setValue(GSTDCNB, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGSTDCNBMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GSTDCNB).getAttributeMdDTO();
  }
  
  public String getGeneration()
  {
    return getValue(GENERATION);
  }
  
  public void setGeneration(String value)
  {
    if(value == null)
    {
      setValue(GENERATION, "");
    }
    else
    {
      setValue(GENERATION, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getGenerationMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(GENERATION).getAttributeMdDTO();
  }
  
  public String getHeme()
  {
    return getValue(HEME);
  }
  
  public void setHeme(String value)
  {
    if(value == null)
    {
      setValue(HEME, "");
    }
    else
    {
      setValue(HEME, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getHemeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(HEME).getAttributeMdDTO();
  }
  
  public String getIAcHE()
  {
    return getValue(IACHE);
  }
  
  public void setIAcHE(String value)
  {
    if(value == null)
    {
      setValue(IACHE, "");
    }
    else
    {
      setValue(IACHE, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIAcHEMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IACHE).getAttributeMdDTO();
  }
  
  public String getIAcHEMethod()
  {
    return getValue(IACHEMETHOD);
  }
  
  public void setIAcHEMethod(String value)
  {
    if(value == null)
    {
      setValue(IACHEMETHOD, "");
    }
    else
    {
      setValue(IACHEMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIAcHEMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IACHEMETHOD).getAttributeMdDTO();
  }
  
  public String getIdentificationMethod()
  {
    return getValue(IDENTIFICATIONMETHOD);
  }
  
  public void setIdentificationMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTIFICATIONMETHOD, "");
    }
    else
    {
      setValue(IDENTIFICATIONMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getIdentificationMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(IDENTIFICATIONMETHOD).getAttributeMdDTO();
  }
  
  public Boolean getIsofemale()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
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
  
  public final com.runwaysdk.transport.metadata.AttributeBooleanMdDTO getIsofemaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeBooleanMdDTO) getAttributeDTO(ISOFEMALE).getAttributeMdDTO();
  }
  
  public String getMixed()
  {
    return getValue(MIXED);
  }
  
  public void setMixed(String value)
  {
    if(value == null)
    {
      setValue(MIXED, "");
    }
    else
    {
      setValue(MIXED, value);
    }
  }
  
  public boolean isMixedWritable()
  {
    return isWritable(MIXED);
  }
  
  public boolean isMixedReadable()
  {
    return isReadable(MIXED);
  }
  
  public boolean isMixedModified()
  {
    return isModified(MIXED);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMixedMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MIXED).getAttributeMdDTO();
  }
  
  public String getMixedMethod()
  {
    return getValue(MIXEDMETHOD);
  }
  
  public void setMixedMethod(String value)
  {
    if(value == null)
    {
      setValue(MIXEDMETHOD, "");
    }
    else
    {
      setValue(MIXEDMETHOD, value);
    }
  }
  
  public boolean isMixedMethodWritable()
  {
    return isWritable(MIXEDMETHOD);
  }
  
  public boolean isMixedMethodReadable()
  {
    return isReadable(MIXEDMETHOD);
  }
  
  public boolean isMixedMethodModified()
  {
    return isModified(MIXEDMETHOD);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getMixedMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(MIXEDMETHOD).getAttributeMdDTO();
  }
  
  public String getP450()
  {
    return getValue(P450);
  }
  
  public void setP450(String value)
  {
    if(value == null)
    {
      setValue(P450, "");
    }
    else
    {
      setValue(P450, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getP450Md()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(P450).getAttributeMdDTO();
  }
  
  public String getPFalciparum()
  {
    return getValue(PFALCIPARUM);
  }
  
  public void setPFalciparum(String value)
  {
    if(value == null)
    {
      setValue(PFALCIPARUM, "");
    }
    else
    {
      setValue(PFALCIPARUM, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPFalciparumMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PFALCIPARUM).getAttributeMdDTO();
  }
  
  public String getPFalciparumMethod()
  {
    return getValue(PFALCIPARUMMETHOD);
  }
  
  public void setPFalciparumMethod(String value)
  {
    if(value == null)
    {
      setValue(PFALCIPARUMMETHOD, "");
    }
    else
    {
      setValue(PFALCIPARUMMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPFalciparumMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PFALCIPARUMMETHOD).getAttributeMdDTO();
  }
  
  public String getPMalariae()
  {
    return getValue(PMALARIAE);
  }
  
  public void setPMalariae(String value)
  {
    if(value == null)
    {
      setValue(PMALARIAE, "");
    }
    else
    {
      setValue(PMALARIAE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPMalariaeMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PMALARIAE).getAttributeMdDTO();
  }
  
  public String getPMalariaeMethod()
  {
    return getValue(PMALARIAEMETHOD);
  }
  
  public void setPMalariaeMethod(String value)
  {
    if(value == null)
    {
      setValue(PMALARIAEMETHOD, "");
    }
    else
    {
      setValue(PMALARIAEMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPMalariaeMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PMALARIAEMETHOD).getAttributeMdDTO();
  }
  
  public String getPNPA()
  {
    return getValue(PNPA);
  }
  
  public void setPNPA(String value)
  {
    if(value == null)
    {
      setValue(PNPA, "");
    }
    else
    {
      setValue(PNPA, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPNPAMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PNPA).getAttributeMdDTO();
  }
  
  public String getPOvale()
  {
    return getValue(POVALE);
  }
  
  public void setPOvale(String value)
  {
    if(value == null)
    {
      setValue(POVALE, "");
    }
    else
    {
      setValue(POVALE, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPOvaleMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POVALE).getAttributeMdDTO();
  }
  
  public String getPOvaleMethod()
  {
    return getValue(POVALEMETHOD);
  }
  
  public void setPOvaleMethod(String value)
  {
    if(value == null)
    {
      setValue(POVALEMETHOD, "");
    }
    else
    {
      setValue(POVALEMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPOvaleMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(POVALEMETHOD).getAttributeMdDTO();
  }
  
  public String getPVivax()
  {
    return getValue(PVIVAX);
  }
  
  public void setPVivax(String value)
  {
    if(value == null)
    {
      setValue(PVIVAX, "");
    }
    else
    {
      setValue(PVIVAX, value);
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPVivaxMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PVIVAX).getAttributeMdDTO();
  }
  
  public String getPVivaxMethod()
  {
    return getValue(PVIVAXMETHOD);
  }
  
  public void setPVivaxMethod(String value)
  {
    if(value == null)
    {
      setValue(PVIVAXMETHOD, "");
    }
    else
    {
      setValue(PVIVAXMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getPVivaxMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(PVIVAXMETHOD).getAttributeMdDTO();
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSampleIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SAMPLEID).getAttributeMdDTO();
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void setSex(String value)
  {
    if(value == null)
    {
      setValue(SEX, "");
    }
    else
    {
      setValue(SEX, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSexMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SEX).getAttributeMdDTO();
  }
  
  public String getSpecie()
  {
    return getValue(SPECIE);
  }
  
  public void setSpecie(String value)
  {
    if(value == null)
    {
      setValue(SPECIE, "");
    }
    else
    {
      setValue(SPECIE, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getSpecieMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(SPECIE).getAttributeMdDTO();
  }
  
  public java.util.Date getTestDate()
  {
    return com.runwaysdk.constants.MdAttributeDateUtil.getTypeSafeValue(getValue(TESTDATE));
  }
  
  public void setTestDate(java.util.Date value)
  {
    if(value == null)
    {
      setValue(TESTDATE, "");
    }
    else
    {
      setValue(TESTDATE, new java.text.SimpleDateFormat(com.runwaysdk.constants.Constants.DATE_FORMAT).format(value));
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
  
  public final com.runwaysdk.transport.metadata.AttributeDateMdDTO getTestDateMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeDateMdDTO) getAttributeDTO(TESTDATE).getAttributeMdDTO();
  }
  
  public String getWKDR()
  {
    return getValue(WKDR);
  }
  
  public void setWKDR(String value)
  {
    if(value == null)
    {
      setValue(WKDR, "");
    }
    else
    {
      setValue(WKDR, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWKDRMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WKDR).getAttributeMdDTO();
  }
  
  public String getWKDRMethod()
  {
    return getValue(WKDRMETHOD);
  }
  
  public void setWKDRMethod(String value)
  {
    if(value == null)
    {
      setValue(WKDRMETHOD, "");
    }
    else
    {
      setValue(WKDRMETHOD, value);
    }
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
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getWKDRMethodMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(WKDRMETHOD).getAttributeMdDTO();
  }
  
  public static MosquitoExcelViewDTO get(com.runwaysdk.constants.ClientRequestIF clientRequest, String id)
  {
    com.runwaysdk.business.ViewDTO dto = (com.runwaysdk.business.ViewDTO)clientRequest.get(id);
    
    return (MosquitoExcelViewDTO) dto;
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
