package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

public class Generation extends GenerationBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234725414912L;
  
  public Generation()
  {
    super();
  }
    
  public static Generation[] getAll()
  {
    GenerationQuery query = new GenerationQuery(new QueryFactory());
    List<Generation> list = AbstractTerm.getAll(query, Generation.class);

    return list.toArray(new Generation[list.size()]);
  }
  
  public static Generation[] getAllActive()
  {
    GenerationQuery query = new GenerationQuery(new QueryFactory());
    List<Generation> list = AbstractTerm.getAllActive(query, Generation.class);

    return list.toArray(new Generation[list.size()]);
  }  
}
