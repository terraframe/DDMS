package dss.vector.solutions.irs;

import com.runwaysdk.generation.loader.Reloadable;

public class PlannedOperatorTarget extends PlannedResourceTarget implements Reloadable
{
  @Override
  public String setOperatorPlannedTarget(Alias alias)
  {
    return set(IRSQuery.WEEKLY_TARGET, alias);
  }
  
  @Override
  public String setSprayOperatorDefaultLocale(Alias alias)
  {
    return set("sprayoperator."+memberIdCol+" || ' - ' || person."+firstNameCol+
        " || ' ' || "+personTable+"."+lastNameCol, alias);
  }
  
  @Override
  public String from()
  {
    String sql = "--Planned Operator Target\n"; 
    sql +=   IRSQuery.RESOURCE_TARGET_VIEW + " " + IRSQuery.RESOURCE_TARGET_VIEW + " INNER JOIN "+resourceTargetTable + " " + resourceTargetTable+" ON "
      +IRSQuery.RESOURCE_TARGET_VIEW+"."+idCol+" = "+resourceTargetTable+"."+idCol+" \n";
    sql += " INNER JOIN "+teamMemberTable+" sprayoperator ON "+resourceTargetTable+"."+targeter+" = sprayoperator."+idCol+" \n";
    sql += " INNER JOIN "+personTable + " AS "+personTable+" ON sprayoperator."+personCol+" = "+personTable+".id\n";

    return sql;
  }
}
