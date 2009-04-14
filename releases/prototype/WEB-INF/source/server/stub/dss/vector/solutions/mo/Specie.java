package dss.vector.solutions.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.SpecieBase;
import dss.vector.solutions.mo.SpecieQuery;

public class Specie extends SpecieBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725411963L;
  
  public Specie()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return super.getTermName();
  }  
  
  public static Specie[] getAll()
  {
    List<Specie> list = new LinkedList<Specie>();   
    SpecieQuery query = new SpecieQuery(new QueryFactory());
    OIterator<? extends Specie> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new Specie[list.size()]);
  }

}
