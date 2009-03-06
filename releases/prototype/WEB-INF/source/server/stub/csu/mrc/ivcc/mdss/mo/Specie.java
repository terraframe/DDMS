package csu.mrc.ivcc.mdss.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.mo.SpecieBase;
import csu.mrc.ivcc.mdss.mo.SpecieQuery;

public class Specie extends SpecieBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725411963L;
  
  public Specie()
  {
    super();
  }
  
  @Override
  public String toString()
  {
    return this.getTermName();
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