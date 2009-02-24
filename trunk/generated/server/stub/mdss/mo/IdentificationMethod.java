package mdss.mo;

import mdss.mo.IdentificationMethodBase;
import mdss.mo.IdentificationMethodQuery;

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
  
  public static java.lang.String[] getAllDisplayLabels()
  {
    return AbstractTerm.getAllDisplayLabels(new IdentificationMethodQuery(new QueryFactory()));
  }

}
