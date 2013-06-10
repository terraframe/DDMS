package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -542424167)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to DiagnosticAssayView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class DiagnosticAssayViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.DiagnosticAssayView";
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String COLLECTION = "collection";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String ID = "id";
  public static java.lang.String LIFESTAGE = "lifeStage";
  public static java.lang.String OUTCOME = "outcome";
  public static java.lang.String SPECIES = "species";
  public static java.lang.String SYNERGIST = "synergist";
  public static java.lang.String UNIQUEASSAYID = "uniqueAssayId";
  private static final long serialVersionUID = -542424167;
  
  public DiagnosticAssayViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.ontology.Term getActiveIngredient()
  {
    if (getValue(ACTIVEINGREDIENT).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(ACTIVEINGREDIENT));
    }
  }
  
  public String getActiveIngredientId()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void validateActiveIngredient()
  {
    this.validateAttribute(ACTIVEINGREDIENT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getActiveIngredientMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value.getId());
    }
  }
  
  public dss.vector.solutions.entomology.MosquitoCollection getCollection()
  {
    if (getValue(COLLECTION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.MosquitoCollection.get(getValue(COLLECTION));
    }
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTION);
  }
  
  public void validateCollection()
  {
    this.validateAttribute(COLLECTION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getCollectionMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(COLLECTION);
  }
  
  public void setCollection(dss.vector.solutions.entomology.MosquitoCollection value)
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
  
  public String getConcreteId()
  {
    return getValue(CONCRETEID);
  }
  
  public void validateConcreteId()
  {
    this.validateAttribute(CONCRETEID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(CONCRETEID);
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
  
  public String getId()
  {
    return getValue(ID);
  }
  
  public void validateId()
  {
    this.validateAttribute(ID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.ontology.Term getLifeStage()
  {
    if (getValue(LIFESTAGE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(LIFESTAGE));
    }
  }
  
  public String getLifeStageId()
  {
    return getValue(LIFESTAGE);
  }
  
  public void validateLifeStage()
  {
    this.validateAttribute(LIFESTAGE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getLifeStageMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(LIFESTAGE);
  }
  
  public void setLifeStage(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(LIFESTAGE, "");
    }
    else
    {
      setValue(LIFESTAGE, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getOutcome()
  {
    if (getValue(OUTCOME).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(OUTCOME));
    }
  }
  
  public String getOutcomeId()
  {
    return getValue(OUTCOME);
  }
  
  public void validateOutcome()
  {
    this.validateAttribute(OUTCOME);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getOutcomeMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(OUTCOME);
  }
  
  public void setOutcome(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(OUTCOME, "");
    }
    else
    {
      setValue(OUTCOME, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getSpecies()
  {
    if (getValue(SPECIES).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(SPECIES));
    }
  }
  
  public String getSpeciesId()
  {
    return getValue(SPECIES);
  }
  
  public void validateSpecies()
  {
    this.validateAttribute(SPECIES);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSpeciesMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(SPECIES);
  }
  
  public void setSpecies(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(SPECIES, "");
    }
    else
    {
      setValue(SPECIES, value.getId());
    }
  }
  
  public Boolean getSynergist()
  {
    return com.runwaysdk.constants.MdAttributeBooleanUtil.getTypeSafeValue(getValue(SYNERGIST));
  }
  
  public void validateSynergist()
  {
    this.validateAttribute(SYNERGIST);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getSynergistMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(SYNERGIST);
  }
  
  public void setSynergist(Boolean value)
  {
    if(value == null)
    {
      setValue(SYNERGIST, "");
    }
    else
    {
      setValue(SYNERGIST, java.lang.Boolean.toString(value));
    }
  }
  
  public String getUniqueAssayId()
  {
    return getValue(UNIQUEASSAYID);
  }
  
  public void validateUniqueAssayId()
  {
    this.validateAttribute(UNIQUEASSAYID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeDAOIF getUniqueAssayIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.DiagnosticAssayView.CLASS);
    return mdClassIF.definesAttribute(UNIQUEASSAYID);
  }
  
  public void setUniqueAssayId(String value)
  {
    if(value == null)
    {
      setValue(UNIQUEASSAYID, "");
    }
    else
    {
      setValue(UNIQUEASSAYID, value);
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static DiagnosticAssayView get(String id)
  {
    return (DiagnosticAssayView) com.runwaysdk.business.View.get(id);
  }
  
  public static dss.vector.solutions.entomology.DiagnosticAssayView[] applyAll(dss.vector.solutions.entomology.DiagnosticAssayView[] views)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.DiagnosticAssayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public void deleteConcrete()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.entomology.DiagnosticAssayView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final void deleteConcrete(java.lang.String id)
  {
    DiagnosticAssayView _instance = DiagnosticAssayView.get(id);
    _instance.deleteConcrete();
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
