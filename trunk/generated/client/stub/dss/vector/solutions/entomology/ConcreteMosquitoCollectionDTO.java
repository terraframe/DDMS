package dss.vector.solutions.entomology;

import java.text.DateFormat;

import com.terraframe.mojo.util.Converter;

import dss.vector.solutions.util.DateConverter;

public abstract class ConcreteMosquitoCollectionDTO extends ConcreteMosquitoCollectionDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1236104204962L;
  
  public ConcreteMosquitoCollectionDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  /**
  * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
  * 
  * @param businessDTO The BusinessDTO to duplicate
  * @param clientRequest The clientRequest this DTO should use to communicate with the server.
  */
  protected ConcreteMosquitoCollectionDTO(com.terraframe.mojo.business.BusinessDTO businessDTO, com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }
  
  public String getDisplayLabel()
  {
    // TODO The date format needs to be localizable
    if (this.getDateCollected() != null)
    {
      DateFormat format = DateFormat.getDateInstance();
      return format.format(this.getDateCollected()) + " - " + this.getGeoEntity().getGeoId();
    }
    
    return this.getId();
  }
  
  public Converter getDateCollectedConverter()
  {
    String label = getDateCollectedMd().getDisplayLabel();
    
    return new DateConverter(label);
  }

}
