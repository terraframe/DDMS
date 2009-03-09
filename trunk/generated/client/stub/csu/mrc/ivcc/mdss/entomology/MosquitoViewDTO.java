package csu.mrc.ivcc.mdss.entomology;

import csu.mrc.ivcc.mdss.entomology.MosquitoViewDTOBase;

public class MosquitoViewDTO extends MosquitoViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235599942202L;
  
  public MosquitoViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
}
