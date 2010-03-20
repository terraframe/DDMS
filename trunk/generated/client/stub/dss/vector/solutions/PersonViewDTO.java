package dss.vector.solutions;

public class PersonViewDTO extends PersonViewDTOBase
 implements com.runwaysdk.generation.loader.Reloadable{
  private static final long serialVersionUID = 1254169588839L;
  
  public PersonViewDTO(com.runwaysdk.constants.ClientRequestIF clientRequest)
  {
    super(clientRequest);
  }
  
  @Override
  public String toString()
  {
    return this.getFirstName() + " " + this.getLastName();
  }

  public boolean getIsStockStaffReadable()
  {
    return this.isIsStockStaffReadable();
  }
  
  public boolean getIsSupervisorReadable()
  {
    return this.isIsSupervisorReadable();
  }
}
