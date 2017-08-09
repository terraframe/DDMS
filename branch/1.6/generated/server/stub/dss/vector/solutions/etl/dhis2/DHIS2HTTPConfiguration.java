package dss.vector.solutions.etl.dhis2;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class DHIS2HTTPConfiguration extends DHIS2HTTPConfigurationBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1790963550;

  public DHIS2HTTPConfiguration()
  {
    super();
  }
  
  @Override
  @Transaction
  public void connect()
  {
    DHIS2GeoMapper mapper = new DHIS2GeoMapper(this.getUrl(), this.getUsername(), this.getPazzword());
    mapper.mapAll();
    
    this.apply();    
  }

  public static DHIS2HTTPConfiguration getInstance()
  {
    DHIS2HTTPConfiguration instance = DHIS2HTTPConfiguration.getByKey("DEFAULT");

    return instance;
  }

}
