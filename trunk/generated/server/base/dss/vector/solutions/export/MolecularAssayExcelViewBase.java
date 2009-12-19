package dss.vector.solutions.export;

@com.terraframe.mojo.business.ClassSignature(hash = -1934263271)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MolecularAssayExcelView.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class MolecularAssayExcelViewBase extends com.terraframe.mojo.business.View implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.export.MolecularAssayExcelView";
  public static java.lang.String ASSAYMETHOD = "assayMethod";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String GENERATION = "generation";
  public static java.lang.String ID = "id";
  public static java.lang.String IDENTMETHOD = "identMethod";
  public static java.lang.String ISOFEMALE = "isofemale";
  public static java.lang.String MOSQUITOID = "mosquitoId";
  public static java.lang.String NUMBERRR = "numberRR";
  public static java.lang.String NUMBERRS = "numberRS";
  public static java.lang.String NUMBERSS = "numberSS";
  public static java.lang.String SEX = "sex";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String TARGET = "target";
  private static final long serialVersionUID = -1934263271;
  
  public MolecularAssayExcelViewBase()
  {
    super();
  }
  
  public String getAssayMethod()
  {
    return getValue(ASSAYMETHOD);
  }
  
  public void validateAssayMethod()
  {
    this.validateAttribute(ASSAYMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAssayMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ASSAYMETHOD);
  }
  
  public void setAssayMethod(String value)
  {
    if(value == null)
    {
      setValue(ASSAYMETHOD, "");
    }
    else
    {
      setValue(ASSAYMETHOD, value);
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void validateCollectionId()
  {
    this.validateAttribute(COLLECTIONID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(COLLECTIONID);
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
  
  public String getGeneration()
  {
    return getValue(GENERATION);
  }
  
  public void validateGeneration()
  {
    this.validateAttribute(GENERATION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getGenerationMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(GENERATION);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public String getIdentMethod()
  {
    return getValue(IDENTMETHOD);
  }
  
  public void validateIdentMethod()
  {
    this.validateAttribute(IDENTMETHOD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIdentMethodMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(IDENTMETHOD);
  }
  
  public void setIdentMethod(String value)
  {
    if(value == null)
    {
      setValue(IDENTMETHOD, "");
    }
    else
    {
      setValue(IDENTMETHOD, value);
    }
  }
  
  public Boolean getIsofemale()
  {
    return com.terraframe.mojo.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(ISOFEMALE));
  }
  
  public void validateIsofemale()
  {
    this.validateAttribute(ISOFEMALE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIsofemaleMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(ISOFEMALE);
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
  
  public String getMosquitoId()
  {
    return getValue(MOSQUITOID);
  }
  
  public void validateMosquitoId()
  {
    this.validateAttribute(MOSQUITOID);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMosquitoIdMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(MOSQUITOID);
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
  
  public Integer getNumberRR()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRR));
  }
  
  public void validateNumberRR()
  {
    this.validateAttribute(NUMBERRR);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberRRMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(NUMBERRR);
  }
  
  public void setNumberRR(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRR, "");
    }
    else
    {
      setValue(NUMBERRR, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberRS()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERRS));
  }
  
  public void validateNumberRS()
  {
    this.validateAttribute(NUMBERRS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberRSMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(NUMBERRS);
  }
  
  public void setNumberRS(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERRS, "");
    }
    else
    {
      setValue(NUMBERRS, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getNumberSS()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(NUMBERSS));
  }
  
  public void validateNumberSS()
  {
    this.validateAttribute(NUMBERSS);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getNumberSSMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(NUMBERSS);
  }
  
  public void setNumberSS(Integer value)
  {
    if(value == null)
    {
      setValue(NUMBERSS, "");
    }
    else
    {
      setValue(NUMBERSS, java.lang.Integer.toString(value));
    }
  }
  
  public String getSex()
  {
    return getValue(SEX);
  }
  
  public void validateSex()
  {
    this.validateAttribute(SEX);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSexMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(SEX);
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
  
  public String getSpecies()
  {
    return getValue(SPECIES);
  }
  
  public void validateSpecies()
  {
    this.validateAttribute(SPECIES);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getSpeciesMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(SPECIES);
  }
  
  public void setSpecies(String value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value);
    }
  }
  
  public String getTarget()
  {
    return getValue(TARGET);
  }
  
  public void validateTarget()
  {
    this.validateAttribute(TARGET);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getTargetMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.export.MolecularAssayExcelView.CLASS);
    return mdClassIF.definesAttribute(TARGET);
  }
  
  public void setTarget(String value)
  {
    if(value == null)
    {
      setValue(TARGET, "");
    }
    else
    {
      setValue(TARGET, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static MolecularAssayExcelView get(String id)
  {
    return (MolecularAssayExcelView) com.terraframe.mojo.business.View.get(id);
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: "+ this.getClassDisplayLabel();
    }
    else
    {
      return super.toString();
    }
  }
}
