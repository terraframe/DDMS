package dss.vector.solutions.irs;

public abstract class ActorSprayStatusView extends ActorSprayStatusViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240941676542L;

  public ActorSprayStatusView()
  {
    super();
  }

  public void populate(SprayStatus status)
  {
    super.populate(status);

    this.setSprayData(status.getSpray().getSprayData());
    
    ActorSpray s = (ActorSpray) status.getSpray();

    this.setReceived(s.getReceived());
    this.setRefills(s.getRefills());
    this.setReturned(s.getReturned());
    this.setUsed(s.getUsed());
  }
  
  protected void populateSpray(ActorSpray spray)
  {
    spray.setSprayData(this.getSprayData());

    spray.setReceived(this.getReceived());
    spray.setRefills(this.getRefills());
    spray.setReturned(this.getReturned());
    spray.setUsed(this.getUsed());
  }

}
