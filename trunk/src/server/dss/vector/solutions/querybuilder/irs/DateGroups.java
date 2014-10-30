package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdEntity;

import dss.vector.solutions.irs.AbstractSpray;
import dss.vector.solutions.irs.OperatorSpray;
import dss.vector.solutions.irs.TeamSpray;
import dss.vector.solutions.irs.ZoneSpray;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class DateGroups extends AbstractSprayProvider implements Reloadable
{
  private static final String dateJoined = "date_joined";
  private static final String djCol = dateJoined+".";
  
  private String overrideName;
  private String overrideDate;
  private String overrideAlias;
  private View view;
  private TargetJoin targetJoin;
  
  public DateGroups(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  public DateGroups(IRSQB irsQB, TargetJoin targetJoin, IRSQB.View overrideView, String overrideAlias, Alias overrideDate)
  {
    super(irsQB);
    
    this.view = overrideView;
    this.overrideName = overrideView.getView();
    this.overrideDate = overrideDate.getAlias();
    this.overrideAlias = overrideAlias;
    this.targetJoin = targetJoin;
  }

  @Override
  public void loadDependencies()
  {
    super.loadDependencies();
    
    this.irsQB.addRequiredAlias(this.getView(), Alias.SPRAY_DATE);
    this.irsQB.addRequiredAlias(View.SPRAY_VIEW, Alias.SPRAY_DATE);
    
    // for every date group that was defined in the query we must also define
    // in the DateGroup view and in the final SprayView.
    for(String group : this.irsQB.getDategroups())
    {
      Alias alias = AliasLookup.get(group);
      this.irsQB.addRequiredAlias(this.getView(), alias);
      this.irsQB.addRequiredAlias(View.SPRAY_VIEW, alias);
    }
    
    if(this.hasTargetWeekConversion())
    {
      this.irsQB.addRequiredAlias(View.DATE_GROUPS, Alias.DATEGROUP_EPIWEEK);      
    }
  }
  
  // Area calculations require the target week, which we compute from the epi week (they're the same thing)
  private boolean hasTargetWeekConversion()
  {
    return this.irsQB.getRequiredAlias(View.ALL_ACTUALS).contains(Alias.TARGET_WEEK) || this.irsQB.hasAreaCalcs();
  }
  
  public String setSprayDate(Alias alias)
  {
    String col = this.overrideDate != null ? this.overrideDate : Alias.SPRAY_DATE.getAlias();
    return this.set(col, alias);
  }
  
  public String setDategroupEpiWeek(Alias alias)
  {
    String col = this.overrideDate != null ? this.overrideDate : Alias.SPRAY_DATE.getAlias();
    return this.set(QueryUtil.getEpiWeekSQL(col), alias);
  }
  
  public String setDategroupSeason(Alias alias)
  {
    String season = QueryUtil.getSeasonNameSelect();
    return this.set(season, Alias.DATEGROUP_SEASON);
  }
  
  public String setDategroupMonth(Alias alias)
  {
    String col = this.overrideDate != null ? this.overrideDate : Alias.SPRAY_DATE.getAlias();
    return this.set(QueryUtil.getMonthSQL(col), alias);
  }

  public String setDategroupQuarter(Alias alias)
  {
    String col = this.overrideDate != null ? this.overrideDate : Alias.SPRAY_DATE.getAlias();
    return this.set(QueryUtil.getQuarterSQL(col), alias);
  }
  
  public String setDategroupEpiYear(Alias alias)
  {
    String col = this.overrideDate != null ? this.overrideDate : Alias.SPRAY_DATE.getAlias();
    return this.set(QueryUtil.getEpiYearSQL(col), alias);
  }
  
  public String setDategroupYear(Alias alias)
  {
    String col = this.overrideDate != null ? this.overrideDate : Alias.SPRAY_DATE.getAlias();
    return this.set(QueryUtil.getCalendarYearSQL(col), alias);
  }
  
  public String getOverrideSQL()
  {
    String sql = "";
    
    if(this.irsQB.getDategroups().size() > 0)
    {
      Set<Alias> selected = this.irsQB.getSelectAliases();
      
      sql += " INNER JOIN \n";
      sql += "( \n";
      sql += " SELECT \n";

      if(selected.contains(Alias.DATEGROUP_EPIWEEK) || hasTargetWeekConversion())
      {
        sql += this.setDategroupEpiWeek(Alias.DATEGROUP_EPIWEEK)+", \n";
      }
      
      if(selected.contains(Alias.DATEGROUP_EPIYEAR))
      {
        sql += this.setDategroupEpiYear(Alias.DATEGROUP_EPIYEAR)+", \n";
      }
      
      if(selected.contains(Alias.DATEGROUP_YEAR))
      {
        sql += this.setDategroupYear(Alias.DATEGROUP_YEAR)+", \n";
      }
      
      if(selected.contains(Alias.DATEGROUP_QUARTER))
      {
        sql += this.setDategroupQuarter(Alias.DATEGROUP_QUARTER)+", \n";
      }
      
      if(selected.contains(Alias.DATEGROUP_MONTH))
      {
        sql += this.setDategroupMonth(Alias.DATEGROUP_MONTH)+", \n";
      }
      
      if(selected.contains(Alias.DATEGROUP_SEASON))
      {
        sql += this.setDategroupSeason(Alias.DATEGROUP_SEASON)+", \n";
      }
      
      sql += this.set("date_joined", this.overrideDate, Alias.PLANNED_DATE)+" \n";
//      sql += this.set("date_joined", Alias.DISEASE.getAlias(), Alias.DISEASE)+" \n";
      sql += "FROM \n";
      sql += "( \n";
      sql += "  SELECT "+this.overrideDate+" FROM "+this.overrideName+" GROUP BY "+this.overrideDate+" \n";
      sql += ") "+dateJoined+" \n";
      
      if (selected.contains(Alias.DATEGROUP_SEASON))
      {
        sql += " INNER JOIN ";
        sql += QueryUtil.getSeasonNameFrom("'"+this.irsQB.getDisease().getId()+"'", djCol+this.overrideDate,
            djCol+this.overrideDate, false);
      }
      
      sql += ") \n";
      sql += this.targetJoin.getDateGroupAlias()+" ON "+this.targetJoin.getDateGroupAlias()+"."+this.overrideDate+" = "+this.overrideAlias+"."+this.overrideDate;
    }
    
    return sql;
  }

  @Override
  public String FROM()
  {
    String as = MdEntity.getMdEntity(AbstractSpray.CLASS).getTableName();
    String oper = MdEntity.getMdEntity(OperatorSpray.CLASS).getTableName();
    String zone = MdEntity.getMdEntity(ZoneSpray.CLASS).getTableName();
    String team = MdEntity.getMdEntity(TeamSpray.CLASS).getTableName();
    
    String od = this.set(QueryUtil.getColumnName(OperatorSpray.getDiseaseMd()), Alias.DISEASE);
    String zd = this.set(QueryUtil.getColumnName(ZoneSpray.getDiseaseMd()), Alias.DISEASE);
    String td = this.set(QueryUtil.getColumnName(TeamSpray.getDiseaseMd()), Alias.DISEASE);
    
    Set<Alias> selected = this.irsQB.getSelectAliases();

    String from = "";
    from += "( \n";
    from += "  SELECT DISTINCT "+Alias.SPRAY_DATE+", "+Alias.DISEASE+" FROM "+as+" a INNER JOIN \n";
    from += "  ( \n";
    from += "    SELECT "+this.irsQB.getIdCol()+", "+Alias.DISEASE+" FROM \n";
    from += "    ( \n";
    from += "      SELECT "+this.irsQB.getIdCol()+", "+od+" FROM "+oper+" \n";
    from += "      UNION ALL \n";
    from += "      SELECT "+this.irsQB.getIdCol()+", "+zd+" FROM "+zone+" \n";
    from += "      UNION ALL \n";
    from += "      SELECT "+this.irsQB.getIdCol()+", "+td+" FROM "+team+" \n";
    from += "    ) levels \n";
    from += "  ) diseases ON diseases."+this.irsQB.getIdCol()+" = a."+this.irsQB.getIdCol()+"  \n";
    from += ") "+dateJoined+" \n";

    
    if (selected.contains(Alias.DATEGROUP_SEASON))
    {
      from += " INNER JOIN ";
      from += QueryUtil.getSeasonNameFrom(djCol+Alias.DISEASE.getAlias(), djCol+Alias.SPRAY_DATE.getAlias(),
          djCol+Alias.SPRAY_DATE.getAlias(), false);
    }

    return from;
  }

  @Override
  protected View getView()
  {
    return View.DATE_GROUPS;
  }

}
