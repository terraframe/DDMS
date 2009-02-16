package mdss.entomology;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class IdentificationMethod extends IdentificationMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725407730L;
  
  public IdentificationMethod()
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
  
  public static IdentificationMethod getIdentificationMethod(String termName)
  {
    IdentificationMethod identificationMethod = null;
    IdentificationMethodQuery query = new IdentificationMethodQuery(new QueryFactory());
    query.WHERE(query.getTermName().EQ(termName));

    OIterator<? extends IdentificationMethod> iterator = query.getIterator();
    
    while(iterator.hasNext())
    {
      identificationMethod = iterator.next();
    }
    
    iterator.close();
    
    return identificationMethod;    
  }  
  
  public static java.lang.String[] getAllTermNames()
  {
    List<String> list = new LinkedList<String>();

    IdentificationMethodQuery query = new IdentificationMethodQuery(new QueryFactory());
    OIterator<? extends IdentificationMethod> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next().getTermName());
    }
    
    return list.toArray(new String[list.size()]);
  }

}
