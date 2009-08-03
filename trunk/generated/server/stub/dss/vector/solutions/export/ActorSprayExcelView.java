package dss.vector.solutions.export;

import dss.vector.solutions.irs.ActorSprayView;
import dss.vector.solutions.irs.SprayOperator;

public abstract class ActorSprayExcelView extends ActorSprayExcelViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246598143569L;
  
  public ActorSprayExcelView()
  {
    super();
  }

  public void populate(ActorSprayView actorSprayView)
  {
    super.populate(actorSprayView);

    SprayOperator leader = SprayOperator.getByOperatorId(this.getLeaderId());
    
    actorSprayView.setTeamLeader(leader);
    actorSprayView.setTeamSprayWeek(this.getTeamSprayWeek());
    actorSprayView.setTarget(this.getTarget());
    actorSprayView.setReceived(this.getReceived());
    actorSprayView.setRefills(this.getRefills());
    actorSprayView.setReturned(this.getReturned());
    actorSprayView.setUsed(this.getUsed());
  }
  
}
