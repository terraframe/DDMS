package dss.vector.solutions;

import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.UserDAO;

public class MDSSUser extends MDSSUserBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240853345874L;
  
  public MDSSUser()
  {
    super();
  }
  
  @Override
  public void apply()
  {
    super.apply();
    
    //Assign this user to the GUIVisibility role
    UserDAO userDAO = (UserDAO) BusinessFacade.getEntityDAO(this).getEntityDAO();
    
    RoleDAO.findRole("GUIVisibility").assignMember(userDAO);
  }
  
  @Override
  public void updateRoles(String[] assign, String[] revoke)
  {
    UserDAO userDAO = (UserDAO)BusinessFacade.getEntityDAO(this).getEntityDAO();
    for (String roleName : assign)
    {
      RoleDAO.findRole(roleName).assignMember(userDAO);
    }
    for (String roleName : revoke)
    {
      RoleDAO.findRole(roleName).getBusinessDAO().deassignMember(userDAO);
    }
  }
}
