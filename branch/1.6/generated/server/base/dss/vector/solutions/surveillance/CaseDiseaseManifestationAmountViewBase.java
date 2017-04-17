package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = -1179781927)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiseaseManifestationAmountView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CaseDiseaseManifestationAmountViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String ID = "id";
  public static java.lang.String MANIFESTATION = "manifestation";
  public static java.lang.String TERM = "term";
  private static final long serialVersionUID = -1179781927;
  
  public CaseDiseaseManifestationAmountViewBase()
  {
    super();
  }
  
  public Integer getAmount()
  {
    return com.runwaysdk.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(AMOUNT));
  }
  
  public void validateAmount()
  {
    this.validateAttribute(AMOUNT);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getAmountMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(AMOUNT);
  }
  
  public void setAmount(Integer value)
  {
    if(value == null)
    {
      setValue(AMOUNT, "");
    }
    else
    {
      setValue(AMOUNT, java.lang.Integer.toString(value));
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  public dss.vector.solutions.surveillance.CaseDiseaseManifestation getManifestation()
  {
    if (getValue(MANIFESTATION).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.CaseDiseaseManifestation.get(getValue(MANIFESTATION));
    }
  }
  
  public String getManifestationId()
  {
    return getValue(MANIFESTATION);
  }
  
  public void validateManifestation()
  {
    this.validateAttribute(MANIFESTATION);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getManifestationMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(MANIFESTATION);
  }
  
  public void setManifestation(dss.vector.solutions.surveillance.CaseDiseaseManifestation value)
  {
    if(value == null)
    {
      setValue(MANIFESTATION, "");
    }
    else
    {
      setValue(MANIFESTATION, value.getId());
    }
  }
  
  public dss.vector.solutions.ontology.Term getTerm()
  {
    if (getValue(TERM).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(TERM));
    }
  }
  
  public String getTermId()
  {
    return getValue(TERM);
  }
  
  public void validateTerm()
  {
    this.validateAttribute(TERM);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiseaseManifestationAmountView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(TERM);
  }
  
  public void setTerm(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(TERM, "");
    }
    else
    {
      setValue(TERM, value.getId());
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public static CaseDiseaseManifestationAmountView get(String id)
  {
    return (CaseDiseaseManifestationAmountView) com.runwaysdk.business.View.get(id);
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
