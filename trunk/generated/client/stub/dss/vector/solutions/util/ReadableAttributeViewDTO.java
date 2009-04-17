package dss.vector.solutions.util;

public class ReadableAttributeViewDTO extends ReadableAttributeViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1239221651151L;
  
  public ReadableAttributeViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String toString()
  {
    return "!Attribute=" + this.getAttributeName() + " Readable=" + this.getReadPermission();
  }
}
