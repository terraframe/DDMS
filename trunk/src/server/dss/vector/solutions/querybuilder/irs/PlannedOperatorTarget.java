package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class PlannedOperatorTarget extends PlannedResourceTarget implements Reloadable
{
  // private String operSprayTable;
  // private String targetCol;
  // private String sprayOperatorCol;

  public PlannedOperatorTarget(IRSQB irsQB)
  {
    super(irsQB);

    // MdEntityDAOIF operSprayMd =
    // MdEntityDAO.getMdEntityDAO(OperatorSpray.CLASS);
    // this.operSprayTable = operSprayMd.getTableName();
    // this.targetCol = QueryUtil.getColumnName(operSprayMd,
    // OperatorSpray.TARGET);
    // this.sprayOperatorCol = QueryUtil.getColumnName(operSprayMd,
    // OperatorSpray.SPRAYOPERATOR);
  }
  
  @Override
  protected View getView()
  {
    return View.PLANNED_OPERATOR;
  }

  @Override
  public String setOperatorPlannedTarget(Alias alias)
  {
    return set(IRSQB.WEEKLY_TARGET, alias);
  }

  @Override
  public String setTarget(Alias alias)
  {
    return setNULL(alias);
  }

  // @Override
  // public String setOperatorActualTarget(Alias alias)
  // {
  // String sql =
  // "(SELECT SUM("+this.targetCol+") FROM "+this.operSprayTable+" o INNER JOIN "+this.abstractSprayTable+" a on o."+idCol+" = a."+idCol+" WHERE o."+this.sprayOperatorCol+" = "+IRSQuery.RESOURCE_TARGET_VIEW+"."+this.q.getTargeter()+" AND "+IRSQuery.RESOURCE_TARGET_VIEW+"."+IRSQuery.TARGET_WEEK+" = get_epiWeek_from_date(a."+this.sprayDateCol+", "+this.q.getStartDay()+"))";
  // return set(sql, alias);
  // }

  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set("sprayoperator." + memberIdCol + " || ' - ' || person." + firstNameCol + " || ' ' || "
        + personTable + "." + lastNameCol, alias);
  }

  @Override
  public String setUniquePlannedId(Alias alias)
  {
    return set(IRSQB.View.RESOURCE_TARGET_VIEW.getView(), keyName, alias);
  }

  @Override
  public String FROM()
  {
    String resourceTargetView = IRSQB.View.RESOURCE_TARGET_VIEW.getView();
    String sql = "--Planned Operator Target\n";
    sql += resourceTargetView + " " + resourceTargetView + " INNER JOIN "
        + resourceTargetTable + " " + resourceTargetTable + " ON " + resourceTargetView + "."
        + idCol + " = " + resourceTargetTable + "." + idCol + " \n";
    sql += " INNER JOIN " + teamMemberTable + " sprayoperator ON " + resourceTargetTable + "."
        + targeter + " = sprayoperator." + idCol + " \n";
    sql += " INNER JOIN " + personTable + " AS " + personTable + " ON sprayoperator." + personCol
        + " = " + personTable + ".id\n";

    return sql;
  }
}
