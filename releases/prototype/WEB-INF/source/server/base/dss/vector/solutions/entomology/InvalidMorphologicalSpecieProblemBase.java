package dss.vector.solutions.entomology;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to InvalidMorphologicalSpecieProblem.java
 *
 * @author Autogenerated by TerraFrame
 */
public abstract class InvalidMorphologicalSpecieProblemBase extends dss.vector.solutions.NotificationProblem implements com.terraframe.mojo.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.InvalidMorphologicalSpecieProblem";
  private static final long serialVersionUID = 1238826360883L;
  
  public InvalidMorphologicalSpecieProblemBase()
  {
    super();
  }
  
  public InvalidMorphologicalSpecieProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
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
