package dss.vector.solutions.stock;

@com.runwaysdk.business.ClassSignature(hash = 1443142427)
/**
 * This class is generated automatically.
 * DO NOT MAKE CHANGES TO IT - THEY WILL BE OVERWRITTEN
 * Custom business logic should be added to StockEventView.java
 *
 * @author Autogenerated by RunwaySDK
 */
public class StockEventViewQueryDTO extends com.runwaysdk.business.ViewQueryDTO
 implements com.runwaysdk.generation.loader.Reloadable
{
private static final long serialVersionUID = 1443142427;

  protected StockEventViewQueryDTO(String type)
  {
    super(type);
  }

@SuppressWarnings("unchecked")
public java.util.List<? extends dss.vector.solutions.stock.StockEventViewDTO> getResultSet()
{
  return (java.util.List<? extends dss.vector.solutions.stock.StockEventViewDTO>)super.getResultSet();
}
}
