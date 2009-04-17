package dss.vector.solutions.util;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.terraframe.mojo.ClientSession;
import com.terraframe.mojo.business.BusinessFacade;
import com.terraframe.mojo.business.rbac.Operation;
import com.terraframe.mojo.business.rbac.RoleDAO;
import com.terraframe.mojo.business.rbac.UserDAO;
import com.terraframe.mojo.dataaccess.metadata.MdAttributeDAO;
import com.terraframe.mojo.dataaccess.metadata.MdTypeDAO;
import com.terraframe.mojo.session.SessionFacade;
import com.terraframe.mojo.system.AllOperations;
import com.terraframe.mojo.system.Roles;
import com.terraframe.mojo.system.TypePermission;
import com.terraframe.mojo.system.Users;
import com.terraframe.mojo.system.metadata.MdAttribute;
import com.terraframe.mojo.system.metadata.MdAttributeBoolean;
import com.terraframe.mojo.system.metadata.MdAttributeCharacter;
import com.terraframe.mojo.system.metadata.MdAttributeInteger;
import com.terraframe.mojo.system.metadata.MdBusiness;

public class PermissionsTest extends TestCase
{
  private static MdBusiness mdssType;
  private static Roles mdssAdmin;
  private static Roles mdssUser;
  private static Users adminUser;
  private static MdAttributeCharacter visibleChar;
  private static Users publicUser;
  private static MdAttributeInteger hiddenInt;
  private static MdAttributeBoolean userBoolean;

  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(PermissionsTest.class);

    TestSetup wrapper = new TestSetup(suite)
    {
      protected void setUp()
      {
        classSetUp();
      }


      protected void tearDown()
      {
        classTearDown();
      }

    };

    return wrapper;
  }

  protected static void classSetUp()
  {
    mdssAdmin = new Roles();
    mdssAdmin.setRoleName("MdssAdmin");
    mdssAdmin.apply();
    
    mdssUser = new Roles();
    mdssUser.setRoleName("MdssUser");
    mdssUser.apply();
    
    mdssType = new MdBusiness();
    mdssType.setTypeName("MdssType");
    mdssType.setPackageName("dss.test");
    mdssType.getDisplayLabel().setEn("MDSS Test Type");
    mdssType.apply();
    
    visibleChar = new MdAttributeCharacter();
    visibleChar.setAttributeName("visibleChar");
    visibleChar.setDatabaseSize(32);
    visibleChar.setDisplayLabel("Visible Character");
    visibleChar.setDefiningMdClass(mdssType);
    visibleChar.apply();
    
    hiddenInt = new MdAttributeInteger();
    hiddenInt.setAttributeName("hiddenInt");
    hiddenInt.setDisplayLabel("Hidden Integer");
    hiddenInt.setDefiningMdClass(mdssType);
    hiddenInt.apply();
    
    userBoolean = new MdAttributeBoolean();
    userBoolean.setAttributeName("userBoolean");
    userBoolean.setDisplayLabel("User Boolean");
    userBoolean.setDefiningMdClass(mdssType);
    userBoolean.apply();
    
    TypePermission readVisible = mdssAdmin.addMetadata(visibleChar);
    readVisible.addOperations(AllOperations.READ);
    readVisible.apply();
    
    adminUser = new Users();
    adminUser.setUsername("admin@mt2009.com");
    adminUser.setPassword("admin@mt2009.com");
    adminUser.apply();
    adminUser.addAssignedRole(mdssAdmin).apply();
    
    publicUser = new Users();
    publicUser.setUsername("public@mt2009.com");
    publicUser.setPassword("public@mt2009.com");
    publicUser.addAssignedRole(mdssUser);
    publicUser.apply();
  }
  
  protected static void classTearDown()
  {
    UserDAO.findUser(adminUser.getUsername()).getBusinessDAO().delete();
    UserDAO.findUser(publicUser.getUsername()).getBusinessDAO().delete();
    RoleDAO.findRole(mdssAdmin.getRoleName()).getBusinessDAO().delete();
    RoleDAO.findRole(mdssUser.getRoleName()).getBusinessDAO().delete();
    MdTypeDAO.getMdTypeDAO(mdssType.definesType()).getBusinessDAO().delete();
  }
  
  public void testReadVisible() throws Exception
  {
    if (!checkAttributeAccess(adminUser, visibleChar))
      fail("Admin user cannot read visibleChar");
  }
  
  public void testGetRoleAttributes() throws Exception
  {
    ReadableAttributeView[] views = ReadableAttributeView.getActorAttributes(mdssType.definesType(), mdssAdmin.getRoleName());
    assertEquals(hiddenInt.getAttributeName(), views[0].getAttributeName());
    assertEquals(Boolean.FALSE, views[0].getReadPermission());
    assertEquals(userBoolean.getAttributeName(), views[1].getAttributeName());
    assertEquals(Boolean.FALSE, views[1].getReadPermission());
    assertEquals(visibleChar.getAttributeName(), views[2].getAttributeName());
    assertEquals(Boolean.TRUE, views[2].getReadPermission());
  }
  
  public void testSetRoleAttributes() throws Exception
  {
    assertEquals(false, checkAttributeAccess(adminUser, hiddenInt));
    assertEquals(false, checkAttributeAccess(adminUser, userBoolean));
    assertEquals(true, checkAttributeAccess(adminUser, visibleChar));
    
    ReadableAttributeView[] views = new ReadableAttributeView[3];
    views[0] = new ReadableAttributeView();
    views[0].setAttributeName(hiddenInt.getAttributeName());
    views[0].setDisplayLabel(hiddenInt.getDisplayLabel());
    views[0].setReadPermission(true);
    views[0].apply();
    
    views[1] = new ReadableAttributeView();
    views[1].setAttributeName(userBoolean.getAttributeName());
    views[1].setDisplayLabel(userBoolean.getDisplayLabel());
    views[1].setReadPermission(false);
    views[1].apply();
    
    views[2] = new ReadableAttributeView();
    views[2].setAttributeName(visibleChar.getAttributeName());
    views[2].setDisplayLabel(visibleChar.getDisplayLabel());
    views[2].setReadPermission(false);
    views[2].apply();
    
    ReadableAttributeView.setActorAttributes(mdssType.definesType(), mdssAdmin.getRoleName(), views);
    
    assertEquals(true, checkAttributeAccess(adminUser, hiddenInt));
    assertEquals(false, checkAttributeAccess(adminUser, userBoolean));
    assertEquals(false, checkAttributeAccess(adminUser, visibleChar));
  }
  
  private boolean checkAttributeAccess(Users user, MdAttribute attribute)
  {
    ClientSession adminSession = ClientSession.createUserSession(user.getUsername(), user.getUsername());
    MdAttributeDAO mdAttributeDAO = (MdAttributeDAO) BusinessFacade.getEntityDAO(attribute);
    boolean checkAttributeAccess = SessionFacade.checkAttributeAccess(adminSession.getSessionId(), Operation.READ, mdAttributeDAO);
    adminSession.logout();
    return checkAttributeAccess;
    
  }
}
