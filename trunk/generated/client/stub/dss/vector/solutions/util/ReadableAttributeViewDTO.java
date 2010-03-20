package dss.vector.solutions.util;

public class ReadableAttributeViewDTO extends ReadableAttributeViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239221651151L;
  
  public ReadableAttributeViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String toString()
  {
    return "!Attribute=" + this.getAttributeName() + " Readable=" + this.getReadPermission();
  }
}
