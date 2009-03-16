package dss.vector.solutions.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.GenerationBase;
import dss.vector.solutions.mo.GenerationQuery;

public class Generation extends GenerationBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725414912L;
  
  public Generation()
  {
    super();
  }
  
  
  public static Generation[] getAll()
  {
    List<Generation> list = new LinkedList<Generation>();   
    GenerationQuery query = new GenerationQuery(new QueryFactory());
    OIterator<? extends Generation> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new Generation[list.size()]);
  }  
  
}