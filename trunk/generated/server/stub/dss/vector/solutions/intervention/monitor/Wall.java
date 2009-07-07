package dss.vector.solutions.intervention.monitor;

import java.util.LinkedList;
import java.util.List;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.query.OIterator;
import com.terraframe.mojo.query.QueryFactory;

public class Wall extends WallBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1241556936582L;
  
  public Wall()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    super.apply();

    List<? extends Wall> parents = this.getAllParentWalls().getAll();
    
    if(this.getParentWall() == null)
    {
      if(parents.size() > 0)
      {
        this.deleteAllParents();        
      }
    }
    else if(!parents.contains(this.getParentWall()))
    {
      deleteAllParents();

      Wall parent = this.getParentWall();
      
      WallHeiarchy heiarchy = new WallHeiarchy(parent, this);
      heiarchy.apply();
    }
  }
  
  @Transaction
  private void deleteAllParents()
  {
    List<? extends WallHeiarchy> hierarchy = this.getAllParentWallsRel().getAll();
    
    for(WallHeiarchy h : hierarchy)
    {
      h.delete();
    }
  }

  
  public WallView getView()
  {
    WallView view = new WallView();
    view.populate(this);
    
    return view;
  }
  
  public static Wall[] getAll()
  {
    WallQuery query = new WallQuery(new QueryFactory());
    query.getEnabled().EQ(true);
    
    List<? extends Wall> walls = query.getIterator().getAll();
    
    return walls.toArray(new Wall[walls.size()]);
  }
  
  public static Wall[] getRoots()
  {
    WallQuery query = new WallQuery(new QueryFactory());
    query.WHERE(query.NOT_IN_parentWalls());
    query.ORDER_BY_ASC(query.getCreateDate());

    OIterator<? extends Wall> it = query.getIterator();

    try
    {
      List<Wall> Walls = new LinkedList<Wall>();

      while (it.hasNext())
      {
        Walls.add(it.next());
      }

      return Walls.toArray(new Wall[Walls.size()]);
    }
    finally
    {
      it.close();
    }
  }
}
