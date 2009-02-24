package mdss.mo;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;

public abstract class AbstractTerm extends AbstractTermBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234811656578L;
  
  public AbstractTerm()
  {
    super();
  }
  
  public static java.lang.String[] getAllDisplayLabels(AbstractTermQuery query)
  {
    List<String> list = new LinkedList<String>();   
    OIterator<? extends AbstractTerm> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next().getDisplayLabel());
    }
    
    return list.toArray(new String[list.size()]);
  }
}
