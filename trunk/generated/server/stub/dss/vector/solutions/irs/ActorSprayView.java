package dss.vector.solutions.irs;

import com.terraframe.mojo.dataaccess.transaction.AttributeNotificationMap;

public abstract class ActorSprayView extends ActorSprayViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240860676330L;

  public ActorSprayView()
  {
    super();
  }

  protected void populateConcrete(ActorSpray spray)
  {
    super.populateConcrete(spray);
    
    spray.setTarget(this.getTarget());
    spray.setTeamSprayWeek(this.getTeamSprayWeek());    
    spray.setReceived(this.getReceived());
    spray.setRefills(this.getRefills());
    spray.setReturned(this.getReturned());
    spray.setUsed(this.getUsed());
    spray.setTeamLeader(this.getTeamLeader());
  }

  protected void populateMapping(ActorSpray spray)
  {
    super.populateMapping(spray);
    
    new AttributeNotificationMap(spray, ActorSpray.TEAMLEADER, this, ActorSprayView.TEAMLEADER);
    new AttributeNotificationMap(spray, ActorSpray.TARGET, this, ActorSprayView.TARGET);
    new AttributeNotificationMap(spray, ActorSpray.TEAMSPRAYWEEK, this, ActorSprayView.TEAMSPRAYWEEK);
    new AttributeNotificationMap(spray, ActorSpray.RECEIVED, this, ActorSprayView.RECEIVED);
    new AttributeNotificationMap(spray, ActorSpray.REFILLS, this, ActorSprayView.REFILLS);
    new AttributeNotificationMap(spray, ActorSpray.RETURNED, this, ActorSprayView.RETURNED);    
  }
}
