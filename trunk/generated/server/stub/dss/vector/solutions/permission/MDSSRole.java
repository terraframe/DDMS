package dss.vector.solutions.permission;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.Roles;

public class MDSSRole extends MDSSRoleBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1185298795;
  
  public MDSSRole()
  {
    super();
  }
  
  @Override
  @Transaction
  public void delete()
  {
    this.getRole().delete();
    
    super.delete();
  }
  
  @Override
  public MDSSRoleView lockView()
  {
    this.lock();
    
    return this.getView();
  }  
  
  public MDSSRoleView unlockView()
  {
    this.unlock();
    
    return this.getView();
  }
  
  public MDSSRoleView getView()
  {
    MDSSRoleView view = new MDSSRoleView();
    
    view.populateView(this);
    
    return view;
  }  
  
  public static Roles[] getRoles()
  {
    List<Roles> list = new ArrayList<Roles>();
    MDSSRoleQuery query = new MDSSRoleQuery(new QueryFactory());
    
    OIterator<? extends MDSSRole> it = query.getIterator();
    
    try
    {
      while(it.hasNext())
      {
        MDSSRole role = it.next();
        list.add(role.getRole());
      }
    }
    finally
    {
      it.close();
    }
    
    return list.toArray(new Roles[list.size()]);
  }  
}
