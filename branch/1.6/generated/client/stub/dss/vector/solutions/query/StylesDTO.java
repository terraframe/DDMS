package dss.vector.solutions.query;

import com.runwaysdk.transport.metadata.AttributeEnumerationMdDTO;

public class StylesDTO extends StylesDTOBase implements com.runwaysdk.generation.loader.Reloadable, StylesIF
{
  private static final long serialVersionUID = -213714992;

  public StylesDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

  /**
   * Copy Constructor: Duplicates the values and attributes of the given BusinessDTO into a new DTO.
   * 
   * @param businessDTO
   *          The BusinessDTO to duplicate
   * @param clientRequest
   *          The clientRequest this DTO should use to communicate with the server.
   */
  protected StylesDTO(com.runwaysdk.business.BusinessDTO businessDTO, com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(businessDTO, clientRequest);
  }

  @Override
  public boolean isEnumerationAttribute(String styleName)
  {
    return ( this.getAttributeMd(styleName) instanceof AttributeEnumerationMdDTO );
  }

  @Override
  public StylesIF createMergedStyle()
  {
    return new StylesDTO(this.getRequest());
  }

  @Override
  public String getFontStylesName()
  {
    return this.getFontStyles().get(0).name().toLowerCase();
  }

  @Override
  public String getPointMarkerName()
  {
    WellKnownNamesDTO wknDTO = this.getPointMarker().get(0);
    String wkn = wknDTO.name().toLowerCase();

    return wkn;
  }
}
