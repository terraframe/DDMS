package dss.vector.solutions.entomology.assay;

@com.runwaysdk.business.ClassSignature(hash = 101522925)
public abstract class SusceptibleCollectionDTOBase extends com.runwaysdk.business.InformationDTO implements com.runwaysdk.generation.loader.Reloadable
{
  public final static String CLASS = "dss.vector.solutions.entomology.assay.SusceptibleCollection";
  private static final long serialVersionUID = 101522925;
  
  public SusceptibleCollectionDTOBase(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
  protected java.lang.String getDeclaredType()
  {
    return CLASS;
  }
  
  public static java.lang.String ACTIVEINGREDIENT = "activeIngredient";
  public static java.lang.String COLLECTIONID = "collectionId";
  public static java.lang.String ID = "id";
  public String getActiveIngredient()
  {
    return getValue(ACTIVEINGREDIENT);
  }
  
  public void setActiveIngredient(String value)
  {
    if(value == null)
    {
      setValue(ACTIVEINGREDIENT, "");
    }
    else
    {
      setValue(ACTIVEINGREDIENT, value);
    }
  }
  
  public boolean isActiveIngredientWritable()
  {
    return isWritable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientReadable()
  {
    return isReadable(ACTIVEINGREDIENT);
  }
  
  public boolean isActiveIngredientModified()
  {
    return isModified(ACTIVEINGREDIENT);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getActiveIngredientMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(ACTIVEINGREDIENT).getAttributeMdDTO();
  }
  
  public String getCollectionId()
  {
    return getValue(COLLECTIONID);
  }
  
  public void setCollectionId(String value)
  {
    if(value == null)
    {
      setValue(COLLECTIONID, "");
    }
    else
    {
      setValue(COLLECTIONID, value);
    }
  }
  
  public boolean isCollectionIdWritable()
  {
    return isWritable(COLLECTIONID);
  }
  
  public boolean isCollectionIdReadable()
  {
    return isReadable(COLLECTIONID);
  }
  
  public boolean isCollectionIdModified()
  {
    return isModified(COLLECTIONID);
  }
  
  public final com.runwaysdk.transport.metadata.AttributeCharacterMdDTO getCollectionIdMd()
  {
    return (com.runwaysdk.transport.metadata.AttributeCharacterMdDTO) getAttributeDTO(COLLECTIONID).getAttributeMdDTO();
  }
  
}
