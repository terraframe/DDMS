package csu.mrc.ivcc.mdss.mo;


import java.util.LinkedList;
import java.util.List;


import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

import csu.mrc.ivcc.mdss.mo.IdentificationMethodBase;
import csu.mrc.ivcc.mdss.mo.IdentificationMethodQuery;

public class IdentificationMethod extends IdentificationMethodBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725407730L;
  
  public IdentificationMethod()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    return this.getTermName();
  }
  
  public static IdentificationMethod[] getAll()
  {
    List<IdentificationMethod> list = new LinkedList<IdentificationMethod>();   
    IdentificationMethodQuery query = new IdentificationMethodQuery(new QueryFactory());
    OIterator<? extends IdentificationMethod> it = query.getIterator();
    
    while(it.hasNext())
    {
      list.add(it.next());
    }
    
    it.close();
    
    return list.toArray(new IdentificationMethod[list.size()]);
  }

}
