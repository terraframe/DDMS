package dss.vector.solutions.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.InfectivityMethodologyBase;
import dss.vector.solutions.mo.InfectivityMethodologyQuery;

public class InfectivityMethodology extends InfectivityMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235957068443L;
  
  public InfectivityMethodology()
  {
    super();
  }
  
  public static InfectivityMethodology[] getAll()
  {
    List<InfectivityMethodology> list = new LinkedList<InfectivityMethodology>();   
    InfectivityMethodologyQuery query = new InfectivityMethodologyQuery(new QueryFactory());
    OIterator<? extends InfectivityMethodology> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new InfectivityMethodology[list.size()]);
  }  
}