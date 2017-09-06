package dss.vector.solutions.etl.dhis2;

import java.util.List;

import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

public class OrgUnitLevel extends OrgUnitLevelBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 787015857;

  public OrgUnitLevel()
  {
    super();
  }

  @Override
  public String buildKey()
  {
    return this.getDhis2Id();
  }

  public static OrgUnitLevel[] getAll()
  {
    OrgUnitLevelQuery query = new OrgUnitLevelQuery(new QueryFactory());
    query.ORDER_BY_DESC(query.getLevel());

    OIterator<? extends OrgUnitLevel> it = query.getIterator();

    try
    {
      List<? extends OrgUnitLevel> levels = it.getAll();

      return levels.toArray(new OrgUnitLevel[levels.size()]);
    }
    finally
    {
      it.close();
    }
  }
}
