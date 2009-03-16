package dss.vector.solutions.mo;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.LarvaeAgeBase;
import dss.vector.solutions.mo.LarvaeAgeQuery;

public class LarvaeAge extends LarvaeAgeBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1236960166799L;
  
  public LarvaeAge()
  {
    super();
  }
  
  public static dss.vector.solutions.mo.LarvaeAge[] getAll()
  {
    List<LarvaeAge> list = new LinkedList<LarvaeAge>();   
    LarvaeAgeQuery query = new LarvaeAgeQuery(new QueryFactory());
    OIterator<? extends LarvaeAge> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new LarvaeAge[list.size()]);
  }  
}
