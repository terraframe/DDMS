package dss.vector.solutions.mo;


import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

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
    SpecieQuery query = new SpecieQuery(new QueryFactory());
    List<Specie> list = AbstractTerm.getAll(query, Specie.class);

    return list.toArray(new Specie[list.size()]);
  }
  
  public static Specie[] getAllActive()
  {
    SpecieQuery query = new SpecieQuery(new QueryFactory());
    List<Specie> list = AbstractTerm.getAllActive(query, Specie.class);

    return list.toArray(new Specie[list.size()]);
  }  
}
