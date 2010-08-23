package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;

import dss.vector.solutions.irs.IRSUnionIF.ALIAS;
import dss.vector.solutions.util.QueryUtil;

public class ActualTeamSprayTarget extends ActualTargetUnion
{
  private String teamSprayTable;
  private String teamLeaderCol;
  private String sprayTeamCol;
  private String targetCol;
  
  public ActualTeamSprayTarget()
  {
    super();
    
    MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    this.teamSprayTable = teamSprayMd.getTableName();
    teamLeaderCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TEAMLEADER);
    sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
    targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
    
  }
  
  public String setId(ALIAS alias)
  {
    return set(this.teamSprayTable, this.q.idCol, alias);
  }
  
  public String setAggregationLevel(ALIAS alias)
  {
    return set("'2'::TEXT", alias);
  }
}
