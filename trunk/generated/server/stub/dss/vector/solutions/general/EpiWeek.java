package dss.vector.solutions.general;

import com.terraframe.mojo.query.AND;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class EpiWeek extends EpiWeekBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1256576864448L;

  public EpiWeek()
  {
    super();
  }

  public static EpiWeek getEpiWeek(Integer period, Integer year)
  {
    EpiWeekQuery query = new EpiWeekQuery(new QueryFactory());
    query.WHERE(AND.get(query.getPeriod().EQ(period), query.getYearOfWeek().EQ(year)));
    OIterator<? extends EpiWeek> it = query.getIterator();
    
    try
    {
      if(it.hasNext())
      {
        return it.next();
      }
      
      return null;
    }
    finally
    {
      it.close();
    }
  }
}
