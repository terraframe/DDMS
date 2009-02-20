package mdss.mo;

import mdss.mo.SpecieBase;
import mdss.mo.SpecieQuery;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

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
  
  public static Specie getSpecie(String termName)
  {
    Specie specie = null;
    SpecieQuery query = new SpecieQuery(new QueryFactory());
    query.WHERE(query.getTermName().EQ(termName));
    
    OIterator<? extends Specie> iterator = query.getIterator();

    while(iterator.hasNext())
    {
      specie = iterator.next();
    }
    
    iterator.close();
    
    return specie;
  }
  
  public static java.lang.String[] getAllTermNames()
  {
    return AbstractTerm.getAllTermNames(new SpecieQuery(new QueryFactory()));
  }

}
