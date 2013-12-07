package dss.vector.solutions.querybuilder.irs;

import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.general.EpiWeek;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class DateExtrapolationView extends AuxiliaryProvider implements Reloadable
{

  public DateExtrapolationView(IRSQB irsQB)
  {
    super(irsQB);
  }

  @Override
  public void loadDependencies()
  {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  protected View getView()
  {
    return View.DATE_EXTRAPOLATION_VIEW;
  }

  @Override
  public String getSQL()
  {
    String yearCol = QueryUtil.getColumnName(EpiWeek.getYearOfWeekMd());
    String periodCol = this.irsQB.getPeriodCol();
    int startDay = this.irsQB.getStartDay();

    String sql = "";
    sql += "SELECT \n";
    sql += " "+yearCol+" AS "+yearCol+", \n";
    sql += " " + periodCol + " AS " + periodCol + ", \n";
    sql += " (get_epistart(" + yearCol + ", " + startDay + ") + (to_char((" + periodCol
        + ")*7, '999')||' days')::interval)::date AS " + Alias.PLANNED_DATE + " \n";
    sql += "FROM epi_week ";

    return sql;
  }

}
