package csu.mrc.ivcc.mdss.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyBase;
import csu.mrc.ivcc.mdss.mo.BiochemicalMethodologyQuery;

public class BiochemicalMethodology extends BiochemicalMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235957067464L;
  
  public BiochemicalMethodology()
  {
    super();
  }
  
  public static BiochemicalMethodology[] getAll()
  {
    List<BiochemicalMethodology> list = new LinkedList<BiochemicalMethodology>();   
    BiochemicalMethodologyQuery query = new BiochemicalMethodologyQuery(new QueryFactory());
    OIterator<? extends BiochemicalMethodology> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new BiochemicalMethodology[list.size()]);
  }  
}