package mdss.ivcc.mrc.csu.mo;

import java.util.LinkedList;
import java.util.List;

import mdss.ivcc.mrc.csu.mo.InsecticideBase;
import mdss.ivcc.mrc.csu.mo.InsecticideQuery;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Insecticide extends InsecticideBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731976982L;
  
  public Insecticide()
  {
    super();
  }
  
  public static Insecticide[] getAll()
  {
    List<Insecticide> list = new LinkedList<Insecticide>();   
    InsecticideQuery query = new InsecticideQuery(new QueryFactory());
    OIterator<? extends Insecticide> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new Insecticide[list.size()]);
  }  
}
