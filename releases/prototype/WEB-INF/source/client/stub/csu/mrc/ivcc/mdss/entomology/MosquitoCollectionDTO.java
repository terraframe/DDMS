package csu.mrc.ivcc.mdss.entomology;


import com.terraframe.mojo.util.Converter;

import csu.mrc.ivcc.mdss.entomology.MosquitoCollectionDTOBase;
import csu.mrc.ivcc.mdss.util.DateConverter;

public class MosquitoCollectionDTO extends MosquitoCollectionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1234288137540L;
  
  public MosquitoCollectionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected MosquitoCollectionDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
     
  public Converter getDateCollectedConverter()
  {
    String label = getDateCollectedMd().getDisplayLabel();
    
    return new DateConverter(label);
  }
}