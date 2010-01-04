package dss.vector.solutions.query;

public class AttributeGeoHierarchyDTO extends AttributeGeoHierarchyDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 351805106;
  
  public AttributeGeoHierarchyDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public String getConcatId()
  {
    return this.getMdAttributeId()+":"+this.getGeoHierarchyId();
  }
  
}
