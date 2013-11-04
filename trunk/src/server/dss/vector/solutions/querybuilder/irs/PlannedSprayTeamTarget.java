package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class PlannedSprayTeamTarget extends PlannedResourceTarget implements Reloadable
{
  // private String teamSprayTable;
  //
  // private String sprayTeamCol;
  //
  // private String targetCol;
  
  public PlannedSprayTeamTarget(IRSQB irsQB)
  {
    super(irsQB);

    // MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
    // this.teamSprayTable = teamSprayMd.getTableName();
    // sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
    // targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
  }
  
  @Override
  protected View getView()
  {
    return View.PLANNED_TEAM;
  }

  @Override
  public String setTeamPlannedTarget(Alias alias)
  {
    return set(IRSQB.WEEKLY_TARGET, alias);
  }
  
  @Override
  public String setSprayTeam(Alias alias)
  {
    return set(sprayTeamTable, idCol, alias);
  }

  @Override
  public String setTarget(Alias alias)
  {
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(), this.targeter, alias);
  }

  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }

  @Override
  public String setUniquePlannedId(Alias alias)
  {
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(), idCol, alias);
  }

  // @Override
  // public String setTeamActualTarget(Alias alias)
  // {
  // String sql = "(SELECT SUM(" + this.targetCol + ") FROM " +
  // this.teamSprayTable + " t INNER JOIN "
  // + this.abstractSprayTable + " a on t." + idCol + " = a." + idCol +
  // " WHERE t."
  // + this.sprayTeamCol + " = " + IRSQuery.RESOURCE_TARGET_VIEW + "." +
  // this.q.getTargeter()
  // + " AND " + IRSQuery.RESOURCE_TARGET_VIEW + "." + IRSQuery.TARGET_WEEK
  // + " = get_epiWeek_from_date(a." + this.sprayDateCol + ", " +
  // this.q.getStartDay() + "))";
  // return set(sql, alias);
  // }

  @Override
  public String FROM()
  {
    String resourceTargetView = IRSQB.View.RESOURCE_TARGET_VIEW.getView();
    String sql = "--Planned Spray Team Target\n";
    sql += resourceTargetView + " " + resourceTargetView + " INNER JOIN "
        + resourceTargetTable + " " + resourceTargetTable + " ON " + resourceTargetView + "."
        + idCol + " = " + resourceTargetTable + "." + idCol + " \n";
    sql += " INNER JOIN " + sprayTeamTable + " " + sprayTeamTable + " ON " + resourceTargetTable + "."
        + targeter + " = " + sprayTeamTable + "." + idCol + " \n";

    return sql;
  }
}
