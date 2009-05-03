package dss.vector.solutions.irs;

import com.terraframe.mojo.util.Converter;

import dss.vector.solutions.util.DateConverter;

public abstract class AbstractSprayViewDTO extends AbstractSprayViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1240860693804L;
  
  public AbstractSprayViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public Converter getSprayDateConverter()
  {
    String label = getSprayDateMd().getDisplayLabel();
    
    return new DateConverter(label);
  }  
}
