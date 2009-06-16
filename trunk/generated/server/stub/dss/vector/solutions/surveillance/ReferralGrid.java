package dss.vector.solutions.surveillance;

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
    ReferralGridQuery query = new ReferralGridQuery(new QueryFactory());
    List<ReferralGrid> list = AbstractGrid.getAll(query, ReferralGrid.class);

    return list.toArray(new ReferralGrid[list.size()]);
  }

  public static ReferralGrid[] getAllActive()
  {
    ReferralGridQuery query = new ReferralGridQuery(new QueryFactory());
    List<ReferralGrid> list = AbstractGrid.getAllActive(query, ReferralGrid.class);

    return list.toArray(new ReferralGrid[list.size()]);
  }
}
