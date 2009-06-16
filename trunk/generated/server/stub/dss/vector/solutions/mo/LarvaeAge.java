package dss.vector.solutions.mo;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class LarvaeAge extends LarvaeAgeBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236960166799L;
  
  public LarvaeAge()
  {
    super();
  }
  
  public static LarvaeAge[] getAll()
  {
    LarvaeAgeQuery query = new LarvaeAgeQuery(new QueryFactory());
    List<LarvaeAge> list = AbstractTerm.getAll(query, LarvaeAge.class);

    return list.toArray(new LarvaeAge[list.size()]);
  }
  
  public static LarvaeAge[] getAllActive()
  {
    LarvaeAgeQuery query = new LarvaeAgeQuery(new QueryFactory());
    List<LarvaeAge> list = AbstractTerm.getAllActive(query, LarvaeAge.class);

    return list.toArray(new LarvaeAge[list.size()]);
  }  
}
