package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = -1363428865)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to StockDepotProblem.java
 *
 * @author Autogenerated by RunwaySDK
 */
public abstract class StockDepotProblemBase extends com.runwaysdk.business.Problem implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.stock.StockDepotProblem";
  public static java.lang.String GEOID = "geoId";
  public static java.lang.String ID = "id";
  private static final long serialVersionUID = -1363428865;
  
  public StockDepotProblemBase()
  {
    super();
  }
  
  public StockDepotProblemBase(java.lang.String developerMessage)
  {
    super(developerMessage);
  }
  
  public String getGeoId()
  {
    return getValue(GEOID);
  }
  
  public void validateGeoId()
  {
    this.validateAttribute(GEOID);
  }
  
  public static com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF getGeoIdMd()
  {
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.stock.StockDepotProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(GEOID);
  }
  
  public void setGeoId(String value)
  {
    if(value == null)
    {
      setValue(GEOID, "");
    }
    else
    {
      setValue(GEOID, value);
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
    com.runwaysdk.dataaccess.MdClassDAOIF mdClassIF = com.runwaysdk.dataaccess.metadata.MdClassDAO.getMdClassDAO(dss.vector.solutions.stock.StockDepotProblem.CLASS);
    return (com.runwaysdk.dataaccess.MdAttributeCharacterDAOIF)mdClassIF.definesAttribute(ID);
  }
  
  protected String getDeclaredType()
  {
    return CLASS;
  }
  
  public java.lang.String localize(java.util.Locale locale)
  {
    java.lang.String message = super.localize(locale);
    message = replace(message, "{geoId}", this.getGeoId());
    message = replace(message, "{id}", this.getId());
    return message;
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
