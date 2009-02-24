package mdss.ivcc.mrc.csu.entomology.assay;

import mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRangeDTOBase;

public class AdultAgeRangeDTO extends AdultAgeRangeDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  public final static String CLASS = "mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange";
  private static final long serialVersionUID = 1234731978161L;
  
  public AdultAgeRangeDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given StructDTO into a new DTO.
  * 
  * @param structDTO The StructDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected AdultAgeRangeDTO(com.terraframe.mojo.business.StructDTO structDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(structDTO, clientRequest);
  }
  
}
