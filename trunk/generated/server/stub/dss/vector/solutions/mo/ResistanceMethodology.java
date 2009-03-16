package dss.vector.solutions.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.mo.ResistanceMethodologyBase;
import dss.vector.solutions.mo.ResistanceMethodologyQuery;

public class ResistanceMethodology extends ResistanceMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731975869L;
  
  public ResistanceMethodology()
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
    return this.getTermName();
  }
  
  public static ResistanceMethodology[] getAll()
  {
    List<ResistanceMethodology> list = new LinkedList<ResistanceMethodology>();   
    ResistanceMethodologyQuery query = new ResistanceMethodologyQuery(new QueryFactory());
    OIterator<? extends ResistanceMethodology> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new ResistanceMethodology[list.size()]);
  }

}
