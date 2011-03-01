package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

public class PlannedSprayTeamTarget extends PlannedResourceTarget implements Reloadable
{
//  private String teamSprayTable;
//
//  private String sprayTeamCol;
//
//  private String targetCol;

  public PlannedSprayTeamTarget()
  {
    super();

//    MdEntityDAOIF teamSprayMd = MdEntityDAO.getMdEntityDAO(TeamSpray.CLASS);
//    this.teamSprayTable = teamSprayMd.getTableName();
//    sprayTeamCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.SPRAYTEAM);
//    targetCol = QueryUtil.getColumnName(teamSprayMd, TeamSpray.TARGET);
  }

  @Override
  public String setTeamPlannedTarget(Alias alias)
  {
    return set(IRSQB.WEEKLY_TARGET, alias);
  }

  @Override
  public String setSprayTeamDefaultLocale(Alias alias)
  {
    return set(sprayTeamTable, teamIdCol, alias);
  }

//  @Override
//  public String setTeamActualTarget(Alias alias)
//  {
//    String sql = "(SELECT SUM(" + this.targetCol + ") FROM " + this.teamSprayTable + " t INNER JOIN "
//        + this.abstractSprayTable + " a on t." + idCol + " = a." + idCol + " WHERE t."
//        + this.sprayTeamCol + " = " + IRSQuery.RESOURCE_TARGET_VIEW + "." + this.q.getTargeter()
//        + " AND " + IRSQuery.RESOURCE_TARGET_VIEW + "." + IRSQuery.TARGET_WEEK
//        + " = get_epiWeek_from_date(a." + this.sprayDateCol + ", " + this.q.getStartDay() + "))";
//    return set(sql, alias);
//  }

  @Override
  public String from()
  {
    String sql = "--Planned Spray Team Target\n";
    sql += IRSQB.RESOURCE_TARGET_VIEW + " " + IRSQB.RESOURCE_TARGET_VIEW + " INNER JOIN "
        + resourceTargetTable + " " + resourceTargetTable + " ON " + IRSQB.RESOURCE_TARGET_VIEW + "."
        + idCol + " = " + resourceTargetTable + "." + idCol + " \n";
    sql += " INNER JOIN " + sprayTeamTable + " " + sprayTeamTable + " ON " + resourceTargetTable + "."
        + targeter + " = " + sprayTeamTable + "." + idCol + " \n";

    return sql;
  }
}
