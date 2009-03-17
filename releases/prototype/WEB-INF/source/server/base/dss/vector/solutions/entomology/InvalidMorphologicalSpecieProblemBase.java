package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidMorphologicalSpecieProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidMorphologicalSpecieProblemBase extends com.terraframe.mojo.business.Problem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidMorphologicalSpecieProblem";
  public static java.lang.String ATTRIBUTELABEL = "attributeLabel";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = 1237311435414L;
  
  public InvalidMorphologicalSpecieProblemBase()
  {
    super();
  }
  
  public InvalidMorphologicalSpecieProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getAttributeLabel()
  {
    return getValue(ATTRIBUTELABEL);
  }
  
  public void validateAttributeLabel()
  {
    this.validateAttribute(ATTRIBUTELABEL);
  }
  
  public static com.terraframe.mojo.dataaccess.MdAttributeDAOIF getAttributeLabelMd()
  {
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidMorphologicalSpecieProblem.CLASS);
    return mdClassIF.definesAttribute(ATTRIBUTELABEL);
  }
  
  public void setAttributeLabel(String value)
  {
    if(value == null)
    {
      setValue(ATTRIBUTELABEL, "");
    }
    else
    {
      setValue(ATTRIBUTELABEL, value);
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
    com.terraframe.mojo.dataaccess.MdClassDAOIF mdClassIF = com.terraframe.mojo.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.InvalidMorphologicalSpecieProblem.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    try
    {
      java.lang.String message = com.terraframe.mojo.util.LocalizeUtil.getTemplate("dss.vector.solutions.entomology.InvalidMorphologicalSpecieProblem", locale);
      
      message = replace(message, "{attributeLabel}", this.getAttributeLabel());
      message = replace(message, "{id}", this.getId());
      
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
      return "New: Invalid Morphological Specie Problem";
    }
    else
    {
      return super.toString();
    }
  }
}
