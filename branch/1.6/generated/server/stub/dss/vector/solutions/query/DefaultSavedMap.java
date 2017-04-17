package dss.vector.solutions.query;

import com.runwaysdk.dataaccess.database.ServerIDGenerator;

public class DefaultSavedMap extends DefaultSavedMapBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 188069998;

  public DefaultSavedMap()
  {
    super();
  }

  public void apply()
  {
    // Map names MUST be unique but default maps are not specified
    // explicitely by the user, so generate a unique string.
    String mapName = ServerIDGenerator.nextID();
    this.setMapName(mapName);

    super.apply();
  }

}
