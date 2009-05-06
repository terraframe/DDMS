package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.terraframe.mojo.query.OIterator;

public class WallView extends WallViewBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241561582310L;
  
  public WallView()
  {
    super();
  }

  public void populate(Wall wall)
  {
    OIterator<? extends Wall> childrenIt = wall.getAllChildRoofs();
    OIterator<? extends Wall> parentIt = wall.getAllParentRoofs();
    
    try
    {
      this.setDisplayLabel(wall.getDisplayLabel());
      this.setHasChildren(childrenIt.hasNext());
      this.setHasParent(parentIt.hasNext());
      this.setWallId(wall.getId());
    }
    finally
    {
      childrenIt.close();
      parentIt.close();
    }
  }
  
  public static WallView[] getAll()
  {
    //A list of all this household nets plus nets that it doesn't have
    List<WallView> list = new LinkedList<WallView>();
    Stack<Wall> stack = new Stack<Wall>();
    
    for(Wall wall : Wall.getRoots())
    {
      stack.push(wall);
    }

    while(!stack.empty())
    {
      Wall wall = stack.pop();

      for(Wall child : wall.getAllChildRoofs())
      {
        stack.push(child);
      }

      list.add(wall.getView());
    }

    return list.toArray(new WallView[list.size()]);    
  }
  
}
