package mdss.entomology;

import java.text.DateFormat;

public abstract class AbstractMosquitoCollection extends AbstractMosquitoCollectionBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1234288138118L;
  
  public AbstractMosquitoCollection()
  {
    super();
  }
  
  @Override
  protected String buildKey()
  {
    //TODO The date format needs to be localizable
    DateFormat format = DateFormat.getDateInstance();
    return format.format(this.getDateCollected()) + " - " + this.getGeoEntity().getGeoId();
  }  
}
