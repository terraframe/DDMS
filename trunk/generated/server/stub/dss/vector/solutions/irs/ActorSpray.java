package dss.vector.solutions.irs;

public abstract class ActorSpray extends ActorSprayBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853373518L;
  
  public ActorSpray()
  {
    super();
  }
  
  public void populateView(ActorSprayView view)
  {
    super.populateView(view);
    
    view.setTarget(this.getTarget());
    view.setReceived(this.getReceived());
    view.setRefills(this.getRefills());
    view.setReturned(this.getReturned());
    view.setUsed(this.getUsed());
    view.setTarget(this.getTarget());
    view.setTeamSprayWeek(this.getTeamSprayWeek());
    view.setTeamLeader(this.getTeamLeader());
  }

  
}
