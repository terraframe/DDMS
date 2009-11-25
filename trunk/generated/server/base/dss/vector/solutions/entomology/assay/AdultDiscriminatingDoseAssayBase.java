package dss.vector.solutions.entomology.assay;

@com.terraframe.mojo.business.ClassSignature(hash = -1555697750)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AdultDiscriminatingDoseAssayBase extends dss.vector.solutions.entomology.assay.AdultAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay";
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String KD50 = "kd50";
  public static java.lang.String KD95 = "kd95";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  private static final long serialVersionUID = -1555697750;
  
  public AdultDiscriminatingDoseAssayBase()
  {
    super();
  }
  
  public Float getControlTestMortality()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(CONTROLTESTMORTALITY));
  }
  
  public void validateControlTestMortality()
  {
    this.validateAttribute(CONTROLTESTMORTALITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getControlTestMortalityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(CONTROLTESTMORTALITY);
  }
  
  public void setControlTestMortality(Float value)
  {
    if(value == null)
    {
      setValue(CONTROLTESTMORTALITY, "");
    }
    else
    {
      setValue(CONTROLTESTMORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public Integer getHoldingTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(HOLDINGTIME));
  }
  
  public void validateHoldingTime()
  {
    this.validateAttribute(HOLDINGTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getHoldingTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(HOLDINGTIME);
  }
  
  public void setHoldingTime(Integer value)
  {
    if(value == null)
    {
      setValue(HOLDINGTIME, "");
    }
    else
    {
      setValue(HOLDINGTIME, java.lang.Integer.toString(value));
    }
  }
  
  public Double getKd50()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD50));
  }
  
  public void validateKd50()
  {
    this.validateAttribute(KD50);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKd50Md()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(KD50);
  }
  
  public void setKd50(Double value)
  {
    if(value == null)
    {
      setValue(KD50, "");
    }
    else
    {
      setValue(KD50, java.lang.Double.toString(value));
    }
  }
  
  public Double getKd95()
  {
    return com.terraframe.mojo.constants.MdAttributeDoubleUtil.getTypeSafeValue(getValue(KD95));
  }
  
  public void validateKd95()
  {
    this.validateAttribute(KD95);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getKd95Md()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(KD95);
  }
  
  public void setKd95(Double value)
  {
    if(value == null)
    {
      setValue(KD95, "");
    }
    else
    {
      setValue(KD95, java.lang.Double.toString(value));
    }
  }
  
  public Float getMortality()
  {
    return com.terraframe.mojo.constants.MdAttributeFloatUtil.getTypeSafeValue(getValue(MORTALITY));
  }
  
  public void validateMortality()
  {
    this.validateAttribute(MORTALITY);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getMortalityMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(MORTALITY);
  }
  
  public void setMortality(Float value)
  {
    if(value == null)
    {
      setValue(MORTALITY, "");
    }
    else
    {
      setValue(MORTALITY, java.lang.Float.toString(value));
    }
  }
  
  public Integer getQuantityDead()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYDEAD));
  }
  
  public void validateQuantityDead()
  {
    this.validateAttribute(QUANTITYDEAD);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityDeadMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(QUANTITYDEAD);
  }
  
  public void setQuantityDead(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYDEAD, "");
    }
    else
    {
      setValue(QUANTITYDEAD, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityLive()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYLIVE));
  }
  
  public void validateQuantityLive()
  {
    this.validateAttribute(QUANTITYLIVE);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityLiveMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(QUANTITYLIVE);
  }
  
  public void setQuantityLive(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYLIVE, "");
    }
    else
    {
      setValue(QUANTITYLIVE, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static AdultDiscriminatingDoseAssayQuery getAllInstances(String sortAttribute, Boolean ascending, Integer pageSize, Integer pageNumber)
  {
    AdultDiscriminatingDoseAssayQuery query = new AdultDiscriminatingDoseAssayQuery(new com.terraframe.mojo.query.QueryFactory());
    com.terraframe.mojo.business.Entity.getAllInstances(query, sortAttribute, ascending, pageSize, pageNumber);
    return query;
  }
  
  public static AdultDiscriminatingDoseAssay get(String id)
  {
    return (AdultDiscriminatingDoseAssay) com.terraframe.mojo.business.Business.get(id);
  }
  
  public static AdultDiscriminatingDoseAssay getByKey(String key)
  {
    return (AdultDiscriminatingDoseAssay) com.terraframe.mojo.business.Business.get(CLASS, key);
  }
  
  public static java.io.InputStream exportQueryToCSV(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.io.InputStream exportQueryToExcel(java.lang.String queryXML, java.lang.String config, java.lang.String savedSearchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static java.lang.String mapQuery(java.lang.String queryXML, java.lang.String config, java.lang.String[] universalLayers, java.lang.String savedSearchId)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.terraframe.mojo.query.ValueQuery queryResistance(java.lang.String queryXML, java.lang.String config, java.lang.String sortBy, java.lang.Boolean ascending, java.lang.Integer pageNumber, java.lang.Integer pageSize)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static com.terraframe.mojo.query.ValueQuery xmlToValueQuery(java.lang.String xml, java.lang.String[] selectedUniversals, java.lang.Boolean includeGeometry)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.java";
    throw new com.terraframe.mojo.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static AdultDiscriminatingDoseAssay lock(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
    _instance.lock();
    
    return _instance;
  }
  
  public static AdultDiscriminatingDoseAssay unlock(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
    _instance.unlock();
    
    return _instance;
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
