package dss.vector.solutions.entomology;

import dss.vector.solutions.entomology.MosquitoViewDTOBase;

public class MosquitoViewDTO extends MosquitoViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1235599942202L;
  
  public MosquitoViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
}