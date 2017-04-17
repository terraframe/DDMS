package dss.vector.solutions.surveillance;

@com.runwaysdk.business.ClassSignature(hash = 44045061)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to CaseDiagnosisTypeView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class CaseDiagnosisTypeViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.surveillance.CaseDiagnosisTypeView";
  public static java.lang.String AGGREGATEDCASE = "aggregatedCase";
  public static java.lang.String CONCRETEID = "concreteId";
  public static java.lang.String DIAGNOSISCATEGORY = "diagnosisCategory";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
  private static final long serialVersionUID = 44045061;
  
  public CaseDiagnosisTypeViewBase()
  {
    super();
  }
  
  public dss.vector.solutions.surveillance.AggregatedCase getAggregatedCase()
  {
    if (getValue(AGGREGATEDCASE).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.surveillance.AggregatedCase.get(getValue(AGGREGATEDCASE));
    }
  }
  
  public String getAggregatedCaseId()
  {
    return getValue(AGGREGATEDCASE);
  }
  
  public void validateAggregatedCase()
  {
    this.validateAttribute(AGGREGATEDCASE);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getAggregatedCaseMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(AGGREGATEDCASE);
  }
  
  public void setAggregatedCase(dss.vector.solutions.surveillance.AggregatedCase value)
  {
    if(value == null)
    {
      setValue(AGGREGATEDCASE, "");
    }
    else
    {
      setValue(AGGREGATEDCASE, value.getId());
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
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getConcreteIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(CONCRETEID);
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
  
  public dss.vector.solutions.ontology.Term getDiagnosisCategory()
  {
    if (getValue(DIAGNOSISCATEGORY).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.ontology.Term.get(getValue(DIAGNOSISCATEGORY));
    }
  }
  
  public String getDiagnosisCategoryId()
  {
    return getValue(DIAGNOSISCATEGORY);
  }
  
  public void validateDiagnosisCategory()
  {
    this.validateAttribute(DIAGNOSISCATEGORY);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getDiagnosisCategoryMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(DIAGNOSISCATEGORY);
  }
  
  public void setDiagnosisCategory(dss.vector.solutions.ontology.Term value)
  {
    if(value == null)
    {
      setValue(DIAGNOSISCATEGORY, "");
    }
    else
    {
      setValue(DIAGNOSISCATEGORY, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF getTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.surveillance.CaseDiagnosisTypeView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeVirtualDAOIF)mdClassIF.definesAttribute(TERM);
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
  
  public static CaseDiagnosisTypeView get(String id)
  {
    return (CaseDiagnosisTypeView) com.runwaysdk.business.View.get(id);
  }
  
  public dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView[] getAmounts()
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.CaseDiagnosisTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
  }
  
  public static final dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView[] getAmounts(java.lang.String id)
  {
    CaseDiagnosisTypeView _instance = CaseDiagnosisTypeView.get(id);
    return _instance.getAmounts();
  }
  
  public static dss.vector.solutions.surveillance.CaseDiagnosisTypeAmountView[][] getAmountsForViews(java.lang.String[] ids)
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.surveillance.CaseDiagnosisTypeView.java";
    throw new com.runwaysdk.dataaccess.metadata.ForbiddenMethodException(msg);
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
