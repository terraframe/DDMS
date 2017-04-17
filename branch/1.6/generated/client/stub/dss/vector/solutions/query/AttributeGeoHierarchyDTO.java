package dss.vector.solutions.query;

public class AttributeGeoHierarchyDTO extends AttributeGeoHierarchyDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 351805106;
  
  public AttributeGeoHierarchyDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String getConcatId()
  {
    return this.getMdAttributeId()+":"+this.getGeoHierarchyId();
  }
  
}
