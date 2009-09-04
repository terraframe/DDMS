package dss.vector.solutions.export;

import com.terraframe.mojo.dataaccess.cache.DataNotFoundException;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;

import dss.vector.solutions.irs.ActorSprayView;
import dss.vector.solutions.irs.SprayOperator;

public abstract class ActorSprayExcelView extends ActorSprayExcelViewBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1246598143569L;

  public ActorSprayExcelView()
  {
    super();
  }

  public void populate(ActorSprayView actorSprayView)
  {
    super.populate(actorSprayView);

    String leaderID = this.getLeaderId();
    if (leaderID != null && !leaderID.equals(""))
    {
      SprayOperator leader = SprayOperator.getByOperatorId(leaderID);

      if(leader != null)
      {
        actorSprayView.setTeamLeader(leader);
      }
      else
      {
        String msg = "Unknown spray operator [" + leaderID + "]";
        throw new DataNotFoundException(msg, MdTypeDAO.getMdTypeDAO(SprayOperator.CLASS));
      }
    }
    actorSprayView.setTeamSprayWeek(this.getTeamSprayWeek());
    actorSprayView.setTarget(this.getTarget());
    actorSprayView.setReceived(this.getReceived());
    actorSprayView.setRefills(this.getRefills());
    actorSprayView.setReturned(this.getReturned());
    actorSprayView.setUsed(this.getUsed());
  }

}
