package dss.vector.solutions.irs;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class SprayOperatorView extends SprayOperatorViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241450710966L;
  
  public SprayOperatorView()
  {
    super();
  }
  
  private void populate(SprayOperator operator)
  {
    this.setActorId(operator.getId());
    this.setOperatorId(operator.getOperatorId());
    this.setFirstName(operator.getPerson().getFirstName());
    this.setLastName(operator.getPerson().getLastName());
  }
  
  public static SprayOperatorView[] getAll()
  {
    List<SprayOperatorView> list = new LinkedList<SprayOperatorView>();
    SprayOperatorQuery query = new SprayOperatorQuery(new QueryFactory());
    query.ORDER_BY_ASC(query.getPerson().getLastName());
    
    OIterator<? extends SprayOperator> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        SprayOperatorView view = new SprayOperatorView();
        view.populate(it.next());
        
        list.add(view);
      }
      
      return list.toArray(new SprayOperatorView[list.size()]);
    }
    finally
    {
      it.close();
    }
  }
  
}
