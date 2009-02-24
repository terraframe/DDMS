package mdss.mo;

import mdss.mo.ResistanceMethodologyBase;
import mdss.mo.ResistanceMethodologyQuery;

import com.terraframe.mojo.query.QueryFactory;

public class ResistanceMethodology extends ResistanceMethodologyBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234731975869L;
  
  public ResistanceMethodology()
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
  
  public static java.lang.String[] getAllDisplayLabels()
  {
    return AbstractTerm.getAllDisplayLabels(new ResistanceMethodologyQuery(new QueryFactory()));
  }

}
