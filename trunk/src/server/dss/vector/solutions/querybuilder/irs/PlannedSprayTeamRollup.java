package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.dataaccess.RelationshipDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.irs.InTeam;
import dss.vector.solutions.querybuilder.IRSQB;

public class PlannedSprayTeamRollup extends PlannedSprayTeamTarget implements Reloadable
{

  private String inTeamTable;

  public PlannedSprayTeamRollup()
  {
    super();

    this.inTeamTable = MdEntityDAO.getMdEntityDAO(InTeam.CLASS).getTableName();
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

  @Override
  public String setUniquePlannedId(Alias alias)
  {
    return set(IRSQB.RESOURCE_TARGET_VIEW, idCol, alias);
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
  public String from()
  {
    String sql = "--Planned Spray Team Target\n";

    sql += IRSQB.RESOURCE_TARGET_VIEW + " " + IRSQB.RESOURCE_TARGET_VIEW + " \n";
    sql += "INNER JOIN " + resourceTargetTable + " " + resourceTargetTable + " \n";
    sql += "ON " + IRSQB.RESOURCE_TARGET_VIEW + "." + idCol + " = " + resourceTargetTable + "." + idCol
        + " \n";
    sql += "INNER JOIN " + teamMemberTable + " " + teamMemberTable + " ON " + resourceTargetTable + "."
        + targeter + " = " + teamMemberTable + "." + idCol + "  \n";
    sql += "INNER JOIN " + inTeamTable + " " + inTeamTable + " ON " + inTeamTable + "."
        + RelationshipDAOIF.CHILD_ID_COLUMN + " = " + teamMemberTable + "." + idCol + " \n";
    sql += "INNER JOIN " + sprayTeamTable + " " + sprayTeamTable + " ON " + sprayTeamTable + "." + idCol
        + " = " + inTeamTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " \n";

    return sql;
  }
}
