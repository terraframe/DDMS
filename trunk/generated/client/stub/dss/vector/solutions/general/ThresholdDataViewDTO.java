package dss.vector.solutions.general;

public class ThresholdDataViewDTO extends ThresholdDataViewDTOBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1256068148409L;

  public static final String IDENTIFICATION   = "identification_";

  public static final String OUTBREAK         = "outbreak_";

  public ThresholdDataViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }

}
