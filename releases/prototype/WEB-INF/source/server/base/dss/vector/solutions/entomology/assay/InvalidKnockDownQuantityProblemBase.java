package dss.vector.solutions.entomology.assay;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidKnockDownQuantityProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidKnockDownQuantityProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem";
  public static java.lang.String QUANTITYKNOCKDOWN = "quantityKnockDown";
  public static java.lang.String QUANTITYTESTED = "quantityTested";
  private static final long serialVersionUID = 1239075003403L;
  
  public InvalidKnockDownQuantityProblemBase()
  {
    super();
  }
  
  public InvalidKnockDownQuantityProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public Integer getQuantityKnockDown()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYKNOCKDOWN));
  }
  
  public void validateQuantityKnockDown()
  {
    this.validateAttribute(QUANTITYKNOCKDOWN);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityKnockDownMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITYKNOCKDOWN);
  }
  
  public void setQuantityKnockDown(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYKNOCKDOWN, "");
    }
    else
    {
      setValue(QUANTITYKNOCKDOWN, java.lang.Integer.toString(value));
    }
  }
  
  public Integer getQuantityTested()
  {
    return com.terraframe.mojo.constants.MdAttributeIntegerUtil.getTypeSafeValue(getValue(QUANTITYTESTED));
  }
  
  public void validateQuantityTested()
  {
    this.validateAttribute(QUANTITYTESTED);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getQuantityTestedMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem.CLASS);
    return mdClassIF.definesAttribute(QUANTITYTESTED);
  }
  
  public void setQuantityTested(Integer value)
  {
    if(value == null)
    {
      setValue(QUANTITYTESTED, "");
    }
    else
    {
      setValue(QUANTITYTESTED, java.lang.Integer.toString(value));
    }
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.assay.InvalidKnockDownQuantityProblem", locale);
      
      message = replace(message, "{quantityKnockDown}", this.getQuantityKnockDown());
      message = replace(message, "{quantityTested}", this.getQuantityTested());
      
      return message;
    }
    catch (java.io.IOException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (org.xml.sax.SAXException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (javax.xml.parsers.ParserConfigurationException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
    catch (com.terraframe.mojo.util.LocalizeException e)
    {
      throw new com.terraframe.mojo.dataaccess.io.XMLException(e.getLocalizedMessage());
    }
  }
  
  public String toString()
  {
    if (this.isNew())
    {
      return "New: Invalid Knock Down Quantity";
    }
    else
    {
      return super.toString();
    }
  }
}
