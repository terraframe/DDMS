package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.terraframe.mojo.query.OIterator;

public class RoofView extends RoofViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241561582174L;
  
  public RoofView()
  {
    super();
  }
 
  public void populate(Roof roof)
  {
    OIterator<? extends Roof> childrenIt = roof.getAllChildRoofs();
    OIterator<? extends Roof> parentIt = roof.getAllParentRoofs();
    
    try
    {
      this.setDisplayLabel(roof.getDisplayLabel());
      this.setHasChildren(childrenIt.hasNext());
      this.setHasParent(parentIt.hasNext());
      this.setRoofId(roof.getId());
    }
    finally
    {
      childrenIt.close();
      parentIt.close();
    }
  }
  
  public static RoofView[] getAll()
  {
    //A list of all this household nets plus nets that it doesn't have
    List<RoofView> list = new LinkedList<RoofView>();
    Stack<Roof> stack = new Stack<Roof>();
    
    for(Roof wall : Roof.getRoots())
    {
      stack.push(wall);
    }

    while(!stack.empty())
    {
      Roof wall = stack.pop();

      for(Roof child : wall.getAllChildRoofs())
      {
        stack.push(child);
      }

      list.add(wall.getView());
    }

    return list.toArray(new RoofView[list.size()]);    
  }
}
