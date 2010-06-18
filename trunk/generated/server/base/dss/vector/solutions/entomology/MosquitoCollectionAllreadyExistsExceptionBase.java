package dss.vector.solutions.entomology;

@com.runwaysdk.business.ClassSignature(hash = -1522477795)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to MosquitoCollectionAllreadyExistsException.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class MosquitoCollectionAllreadyExistsExceptionBase extends com.runwaysdk.business.SmartException implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.MosquitoCollectionAllreadyExistsException";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1522477795;
  
  public MosquitoCollectionAllreadyExistsExceptionBase()
  {
    super();
  }
  
  public MosquitoCollectionAllreadyExistsExceptionBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public MosquitoCollectionAllreadyExistsExceptionBase(java.lang.String developerMessage, java.lang.Throwable cause)
  {
    super(developerMessage, cause);
  }
  
  public MosquitoCollectionAllreadyExistsExceptionBase(java.lang.Throwable cause)
  {
    super(cause);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.entomology.MosquitoCollectionAllreadyExistsException.CLASS);
    return mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{id}", this.getId());
    return message;
  }
  
}
