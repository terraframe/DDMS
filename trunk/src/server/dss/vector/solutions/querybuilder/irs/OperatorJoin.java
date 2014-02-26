package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;

public class OperatorJoin extends TargetJoin implements Reloadable
{

  public OperatorJoin(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  public final String FROM()
  {
    String a = IRSQB.View.ALL_ACTUALS + " " + TargetJoin.ACTUAL_ALIAS;
    String p = IRSQB.View.PLANNED_OPERATOR + " " + TargetJoin.PLANNED_ALIAS;

    if (hasActual && hasPlanned)
    {
      String sql = "";

      sql += p + new DateGroups(irsQB, this, View.PLANNED_OPERATOR, TargetJoin.PLANNED_ALIAS, Alias.PLANNED_DATE).getOverrideSQL() + " LEFT JOIN \n";

      sql += "( \n";
      sql += a+ " "+ dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias());
      sql += ") \n";
      
      // NOTE: old code for reference
//      sql += "ON extract(YEAR FROM "+TargetJoin.ACTUAL_ALIAS+"."+Alias.SPRAY_DATE.getAlias()+") " +
//      "= extract(YEAR FROM "+TargetJoin.PLANNED_ALIAS+"."+Alias.PLANNED_DATE.getAlias()+") \n";
      sql += "ON " + TargetJoin.PLANNED_ALIAS + "." + Alias.TARGET_WEEK + " = "
          + View.DATE_GROUPS.getView()+"."+Alias.DATEGROUP_EPIWEEK+"::"+Alias.TARGET_WEEK.getType() + " \n";
      
      // FIXED: joined based on spray season instead of year + week
      sql += "AND "+TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_SEASON + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_SEASON + " \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " = "
          + TargetJoin.ACTUAL_ALIAS + "." + Alias.SPRAY_OPERATOR_DEFAULT_LOCALE + " \n";
      
      sql += "AND " + TargetJoin.PLANNED_ALIAS + "." + Alias.DISEASE + " = " + TargetJoin.ACTUAL_ALIAS
          + "." + Alias.DISEASE + " \n";
      
      
      // #2323 Change: restrict the planned rows by showing only those that have activity within the time and geo criteria, but don't do a strict row-by-row
      // join on target week. Reference the ticket examples for something better
      sql += "INNER JOIN \n";
      sql += "(SELECT "+Alias.UNIQUE_PLANNED_ID+" FROM "+IRSQB.View.PLANNED_OPERATOR+" p2 INNER JOIN "+IRSQB.View.ALL_ACTUALS+" a2 ON \n";
      sql += "p2."+Alias.SPRAY_SEASON+" = a2."+Alias.SPRAY_SEASON+"  \n";
      sql += "AND p2."+Alias.SPRAY_OPERATOR_DEFAULT_LOCALE+" = a2."+Alias.SPRAY_OPERATOR_DEFAULT_LOCALE+"  \n";
      sql += "AND p2."+Alias.DISEASE+" = a2."+Alias.DISEASE+") restriction \n";
      sql += "ON p."+Alias.UNIQUE_PLANNED_ID+" = restriction."+Alias.UNIQUE_PLANNED_ID+" \n";
      
      return sql;
    }
    else if(hasPlanned)
    {
      return p + new DateGroups(irsQB, this, View.PLANNED_OPERATOR, TargetJoin.PLANNED_ALIAS, Alias.PLANNED_DATE).getOverrideSQL();
    }
    else
    {
      return a + dateGroupJoin(TargetJoin.ACTUAL_ALIAS, Alias.SPRAY_DATE.getAlias());
    }
  }
}
