package mdss.ivcc.mrc.csu.mo;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class InsecticideMethodology extends InsecticideMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235957080309L;
  
  public InsecticideMethodology()
  {
    super();
  }

  public static InsecticideMethodology[] getAll()
  {
    List<InsecticideMethodology> list = new LinkedList<InsecticideMethodology>();   
    InsecticideMethodologyQuery query = new InsecticideMethodologyQuery(new QueryFactory());
    OIterator<? extends InsecticideMethodology> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new InsecticideMethodology[list.size()]);
  }  

}