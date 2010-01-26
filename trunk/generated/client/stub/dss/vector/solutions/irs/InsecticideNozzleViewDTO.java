package dss.vector.solutions.irs;

public class InsecticideNozzleViewDTO extends InsecticideNozzleViewDTOBase
 implements com.terraframe.mojo.generation.loader.Reloadable{
  private static final long serialVersionUID = 1241039480301L;

  public InsecticideNozzleViewDTO(com.terraframe.mojo.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  public InsecticideBrandViewDTO getBrandView()
  {
    return InsecticideBrandDTO.getView(this.getRequest(), this.getValue(BRAND));
  }
  
  public NozzleViewDTO getNozzleView()
  {
    return NozzleDTO.getView(this.getRequest(), this.getValue(NOZZLE));
  }
}
