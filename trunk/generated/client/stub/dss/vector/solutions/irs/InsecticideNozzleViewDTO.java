package dss.vector.solutions.irs;

public class InsecticideNozzleViewDTO extends InsecticideNozzleViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1241039480301L;

  public InsecticideNozzleViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
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
