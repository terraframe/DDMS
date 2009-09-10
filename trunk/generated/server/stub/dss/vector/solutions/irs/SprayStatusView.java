package dss.vector.solutions.irs;


public abstract class SprayStatusView extends SprayStatusViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860656337L;

  public SprayStatusView()
  {
    super();
  }

  public void populate(SprayStatus status)
  {
    this.setStatusId(status.getId());
    this.setSpray(status.getSpray());
    this.setHouseholds(status.getHouseholds());
    this.setStructures(status.getStructures());
    this.setSprayedHouseholds(status.getSprayedHouseholds());
    this.setSprayedStructures(status.getSprayedStructures());
    this.setPrevSprayedHouseholds(status.getPrevSprayedHouseholds());
    this.setPrevSprayedStructures(status.getPrevSprayedStructures());
    this.setRooms(status.getRooms());
    this.setSprayedRooms(status.getSprayedRooms());
    this.setPeople(status.getPeople());
    this.setBedNets(status.getBedNets());
    this.setRoomsWithBedNets(status.getRoomsWithBedNets());
    this.setLocked(status.getLocked());
    this.setOther(status.getOther());
    this.setRefused(status.getRefused());
  }

  protected void populateConcrete(SprayStatus status)
  {
    status.setSpray(this.getSpray());
    status.setHouseholds(this.getHouseholds());
    status.setStructures(this.getStructures());
    status.setSprayedHouseholds(this.getSprayedHouseholds());
    status.setSprayedStructures(this.getSprayedStructures());
    status.setPrevSprayedHouseholds(this.getPrevSprayedHouseholds());
    status.setPrevSprayedStructures(this.getPrevSprayedStructures());
    status.setRooms(this.getRooms());
    status.setSprayedRooms(this.getSprayedRooms());
    status.setPeople(this.getPeople());
    status.setBedNets(this.getBedNets());
    status.setRoomsWithBedNets(this.getRoomsWithBedNets());
    status.setLocked(this.getLocked());
    status.setOther(this.getOther());
    status.setRefused(this.getRefused());
  }

  protected boolean hasConcrete()
  {
    return this.getStatusId() != null && !this.getStatusId().equals("");
  }
}
