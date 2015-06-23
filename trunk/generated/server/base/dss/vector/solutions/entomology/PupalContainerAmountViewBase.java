package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -649090612)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to PupalContainerAmountView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class PupalContainerAmountViewBase extends com.runwaysdk.business.View implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.PupalContainerAmountView";
  public static java.lang.String AMOUNT = "amount";
  public static java.lang.String CONTAINER = "container";
  public static java.lang.String ID = "id";
  public static java.lang.String TERM = "term";
  private static final long serialVersionUID = -649090612;
  
  public PupalContainerAmountViewBase()
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.PupalContainerAmountView.CLASS);
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
  
  public dss.vector.solutions.entomology.PupalContainer getContainer()
  {
    if (getValue(CONTAINER).trim().equals(""))
    {
      return null;
    }
    else
    {
      return dss.vector.solutions.entomology.PupalContainer.get(getValue(CONTAINER));
    }
  }
  
  public String getContainerId()
  {
    return getValue(CONTAINER);
  }
  
  public void validateContainer()
  {
    this.validateAttribute(CONTAINER);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getContainerMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.PupalContainerAmountView.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF)mdClassIF.definesAttribute(CONTAINER);
  }
  
  public void setContainer(dss.vector.solutions.entomology.PupalContainer value)
  {
    if(value == null)
    {
      setValue(CONTAINER, "");
    }
    else
    {
      setValue(CONTAINER, value.getId());
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.PupalContainerAmountView.CLASS);
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
  
  public static com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF getTermMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.PupalContainerAmountView.CLASS);
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
  
  public static PupalContainerAmountView get(String id)
  {
    return (PupalContainerAmountView) com.runwaysdk.business.View.get(id);
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
