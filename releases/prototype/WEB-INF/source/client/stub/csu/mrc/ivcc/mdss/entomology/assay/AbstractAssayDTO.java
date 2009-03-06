package csu.mrc.ivcc.mdss.entomology.assay;


import com.terraframe.mojo.util.Converter;

import csu.mrc.ivcc.mdss.entomology.assay.AbstractAssayDTOBase;
import csu.mrc.ivcc.mdss.util.DateConverter;

public abstract class AbstractAssayDTO extends AbstractAssayDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234731978813L;
  
  public AbstractAssayDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AbstractAssayDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public Converter getTestDateConverter()
  {
    String label = getTestDateMd().getDisplayLabel();
    
    return new DateConverter(label);
  }  
}