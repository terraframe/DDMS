package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

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
    IdentificationMethodQuery query = new IdentificationMethodQuery(new QueryFactory());
    List<IdentificationMethod> list = AbstractTerm.getAll(query, IdentificationMethod.class);

    return list.toArray(new IdentificationMethod[list.size()]);
  }
  
  public static IdentificationMethod[] getAllActive()
  {
    IdentificationMethodQuery query = new IdentificationMethodQuery(new QueryFactory());
    List<IdentificationMethod> list = AbstractTerm.getAllActive(query, IdentificationMethod.class);

    return list.toArray(new IdentificationMethod[list.size()]);
  }
}
