package dss.vector.solutions.irs;

/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 *
 * @author Autogenerated by RunwaySDK
 */
@com.runwaysdk.business.ClassSignature(hash = 830996802)
public enum InsecticideBrandConcentrationQualifierDTO implements com.runwaysdk.business.EnumerationDTOIF, com.runwaysdk.generation.loader.Reloadable
{
  GRAMS_PER_KILOGRAM(),
  
  GRAMS_PER_LITER(),
  
  GRAMS_PER_SQUARE_METER(),
  
  MICROGRAMS_PER_LITER(),
  
  MICROLITERS_PER_LITER(),
  
  MILLIGRAMS_PER_KILOGRAM(),
  
  MILLIGRAMS_PER_SQUARE_METER(),
  
  MILLILITERS_PER_LITER(),
  
  PERCENT(),
  
  PICOGRAMS_PER_KILOGRAM(),
  
  PICOGRAMS_PER_LITER(),
  
  PICOLITERS_PER_LITER(),
  
  UNKNOWN();
  
  public final static String CLASS = "dss.vector.solutions.irs.InsecticideBrandConcentrationQualifier";
  
  
  public dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMasterDTO item(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMasterDTO) clientRequest.getEnumeration(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO.CLASS, this.name());
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMasterDTO> items(com.runwaysdk.constants.ClientRequestIF clientRequest, InsecticideBrandConcentrationQualifierDTO ... items)
  {
    java.lang.String[] itemNames = new java.lang.String[items.length];
    for(int i=0; i<items.length; i++)
    {
      itemNames[i] = items[i].name();
    }
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMasterDTO>) clientRequest.getEnumerations(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO.CLASS, itemNames);
  }
  
  @java.lang.SuppressWarnings("unchecked")
  public static java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMasterDTO> allItems(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    return (java.util.List<dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierMasterDTO>) clientRequest.getAllEnumerations(dss.vector.solutions.irs.InsecticideBrandConcentrationQualifierDTO.CLASS);
  }
  
  public java.lang.String getName()
  {
    return this.name();
  }
}
