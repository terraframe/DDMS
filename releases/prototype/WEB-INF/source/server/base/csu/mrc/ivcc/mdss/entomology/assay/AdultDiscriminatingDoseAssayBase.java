package csu.mrc.ivcc.mdss.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to AdultDiscriminatingDoseAssay.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class AdultDiscriminatingDoseAssayBase extends csu.mrc.ivcc.mdss.entomology.assay.InsecticideAssay implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONTROLTESTMORTALITY = "controlTestMortality";
  public static java.lang.String HOLDINGTIME = "holdingTime";
  public static java.lang.String INTERVALTIME = "intervalTime";
  public static java.lang.String MORTALITY = "mortality";
  public static java.lang.String QUANTITYDEAD = "quantityDead";
  public static java.lang.String QUANTITYLIVE = "quantityLive";
  private static final long serialVersionUID = 1236612274533L;
  
  public AdultDiscriminatingDoseAssayBase()
  {
    super();
  }
  
  public csu.mrc.ivcc.mdss.entomology.MosquitoCollection getCollection()
  {
    try
    {
      return csu.mrc.ivcc.mdss.entomology.MosquitoCollection.get(getValue(COLLECTION));
    }
    catch (com.terraframe.mojo.dataaccess.cache.DataNotFoundException e)
    {
      return null;
    }
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getCollectionMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(csu.mrc.ivcc.mdss.entomology.MosquitoCollection value)
  {
    if(value == null)
    {
      setValue(COLLECTION, "");
    }
    else
    {
      setValue(COLLECTION, value.getId());
    }
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
  
  public Integer getIntervalTime()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(INTERVALTIME));
  }
  
  public void validateIntervalTime()
  {
    this.validateAttribute(INTERVALTIME);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getIntervalTimeMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
    return mdClassIF.definesAttribute(INTERVALTIME);
  }
  
  public void setIntervalTime(Integer value)
  {
    if(value == null)
    {
      setValue(INTERVALTIME, "");
    }
    else
    {
      setValue(INTERVALTIME, java.lang.Integer.toString(value));
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(csu.mrc.ivcc.mdss.entomology.assay.AdultDiscriminatingDoseAssay.CLASS);
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
  
  public csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView[] getTestIntervals()
  {
    return null;
  }
  
  public static final csu.mrc.ivcc.mdss.entomology.assay.ADDATestIntervalView[] getTestIntervals(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
    return _instance.getTestIntervals();
  }
  
  public java.lang.Double getKD50()
  {
    return null;
  }
  
  public static final java.lang.Double getKD50(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
    return _instance.getKD50();
  }
  
  public java.lang.Double getKD95()
  {
    return null;
  }
  
  public static final java.lang.Double getKD95(java.lang.String id)
  {
    AdultDiscriminatingDoseAssay _instance = AdultDiscriminatingDoseAssay.get(id);
    return _instance.getKD95();
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
