package dss.vector.solutions.entomology.assay;

import dss.vector.solutions.entomology.assay.PotentiallyResistantCollectionDTOBase;

public class PotentiallyResistantCollectionDTO extends PotentiallyResistantCollectionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1236278677671L;
  
  public PotentiallyResistantCollectionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequestIF)
  {
    super(clientRequestIF);
  }
  
}