package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;

public class OperatorJoin extends TargetJoin implements Reloadable
{

  public OperatorJoin(boolean hasActual, boolean hasPlanned)
  {
    super(hasActual, hasPlanned);
  }

  public final String from()
  {
    String a = IRSQB.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.PLANNED_OPERATOR + " " + TargetJoin.PLANNED_ALIAS;

    if (hasPlanned)
    {
      String sql = "";

      sql += a + " FULL OUTER JOIN " + p + " \n";
      
      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
      "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.TARGET_WEEK + " \n";
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";
      return sql;
    }
    else
    {
      return a;
    }
  }
}
