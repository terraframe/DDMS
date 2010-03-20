package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.ResistantCollectionDTOBase;

public class ResistantCollectionDTO extends ResistantCollectionDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1236278668339L;
  
  public ResistantCollectionDTO(com.runwaysdk.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
}
