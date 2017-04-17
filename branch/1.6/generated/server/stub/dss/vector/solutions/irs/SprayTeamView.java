package dss.vector.solutions.irs;

import com.runwaysdk.query.AttributeReference;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectablePrimitive;

public class SprayTeamView extends SprayTeamViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2088238935;
  
  public SprayTeamView()
  {
    super();
  }
 
  public static SprayTeamViewQuery getPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber)
  {
    SprayTeamViewQuery query = new SprayTeamViewQuery(new QueryFactory());
    
    if(sortAttribute == null)
    {
      sortAttribute = SprayTeamView.TEAMID;
    }

    Selectable attribute = query.getComponentQuery().getSelectableRef(sortAttribute);

    if (sortAttribute.equalsIgnoreCase(SprayTeamView.SPRAYZONE))
    {
      attribute = ( (AttributeReference) attribute ).aCharacter("entityName");
    }

    if (isAscending)
    {
      query.ORDER_BY_ASC((SelectablePrimitive) attribute, sortAttribute);
    }
    else
    {
      query.ORDER_BY_DESC((SelectablePrimitive) attribute, sortAttribute);
    }

    if (pageSize != 0 && pageNumber != 0)
    {
      query.restrictRows(pageSize, pageNumber);
    }

    return query;
  }
}
