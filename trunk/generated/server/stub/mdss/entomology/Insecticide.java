package mdss.entomology;

import mdss.mo.AbstractTerm;

import com.terraframe.mojo.query.QueryFactory;

public class Insecticide extends InsecticideBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731976982L;
  
  public Insecticide()
  {
    super();
  }
  
  public static java.lang.String[] getAllTermNames()
  {
    return AbstractTerm.getAllTermNames(new IdentificationMethodQuery(new QueryFactory()));
  }  
}
