package dss.vector.solutions.surveillance;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class ReferralGrid extends ReferralGridBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1238693138964L;
  
  public ReferralGrid()
  {
    super();
  }
 
  public static ReferralGrid[] getAll()
  {
    List<ReferralGrid> list = new LinkedList<ReferralGrid>();
    ReferralGridQuery query = new ReferralGridQuery(new QueryFactory());
    query.WHERE(query.getActive().EQ(true));
    
    for(ReferralGrid d : query.getIterator())
    {
      list.add(d);
    }
    
    return list.toArray(new ReferralGrid[list.size()]);
  }

}
