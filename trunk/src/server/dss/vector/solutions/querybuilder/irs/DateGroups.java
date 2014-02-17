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
  private static String dateJoined = "date_joined";
  
  public DateGroups(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
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
    
  }
  
  public String setSprayDate(Alias alias)
  {
    return this.set(dateJoined, Alias.SPRAY_DATE.getAlias(), alias);
  }
  
  public String setDategroupEpiWeek(Alias alias)
  {
    return this.set(QueryUtil.getEpiWeekSQL(Alias.SPRAY_DATE.getAlias()), alias);
  }
  
  public String setDategroupSeason(Alias alias)
  {
    String season = QueryUtil.getSeasonNameSelect();
    return this.set(season, Alias.DATEGROUP_SEASON);
  }
  
  public String setDategroupMonth(Alias alias)
  {
    return this.set(QueryUtil.getMonthSQL(Alias.SPRAY_DATE.getAlias()), alias);
  }

  public String setDategroupQuarter(Alias alias)
  {
    return this.set(QueryUtil.getQuarterSQL(Alias.SPRAY_DATE.getAlias()), alias);
  }
  
  public String setDategroupEpiYear(Alias alias)
  {
    return this.set(QueryUtil.getEpiYearSQL(Alias.SPRAY_DATE.getAlias()), alias);
  }
  
  public String setDategroupYear(Alias alias)
  {
    return this.set(QueryUtil.getCalendarYearSQL(Alias.SPRAY_DATE.getAlias()), alias);
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
      dateJoined += ".";
      from += " INNER JOIN ";
      from += QueryUtil.getSeasonNameFrom(dateJoined+Alias.DISEASE.getAlias(), dateJoined+Alias.SPRAY_DATE.getAlias(),
          dateJoined+Alias.SPRAY_DATE.getAlias(), false);
    }

    return from;
  }

  @Override
  protected View getView()
  {
    return View.DATE_GROUPS;
  }

}
