package dss.vector.solutions.irs;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.util.QueryUtil;

public class PlannedSprayTeamTarget extends PlannedResourceTarget implements Reloadable
{
  
  public PlannedSprayTeamTarget()
  {
    super();
    
    
    MdEntityDAOIF sprayTeamMd = MdEntityDAO.getMdEntityDAO(SprayTeam.CLASS);
    this.teamIdCol = QueryUtil.getColumnName(sprayTeamMd, SprayTeam.TEAMID);
    this.sprayTeamTable = sprayTeamMd.getTableName();
  }
  
  @Override
  public String setTeamPlannedTarget(Alias alias)
  {
    return set(IRSQuery.WEEKLY_TARGET, alias);
  }
  
  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }
  
  @Override
  public String from()
  {
    String sql = "--Planned Spray Team Target\n"; 
    sql +=   IRSQuery.RESOURCE_TARGET_VIEW + " " + IRSQuery.RESOURCE_TARGET_VIEW + " INNER JOIN "+resourceTargetTable + " " + resourceTargetTable+" ON "
      + IRSQuery.RESOURCE_TARGET_VIEW+"."+idCol+" = "+resourceTargetTable+"."+idCol+" \n";
    sql += " INNER JOIN "+sprayTeamTable+" "+sprayTeamTable+" ON " +resourceTargetTable+"."+targeter+" = "+sprayTeamTable+"."+idCol+" \n";
    
    return sql;
  }
}
