package dss.vector.solutions.permissions;

import junit.extensions.TestSetup;
import junit.framework.Test;

public class PermissionTestSetup extends TestSetup
{
  private String role;
  
  public PermissionTestSetup(Test test, String role)
  {
    super(test);

    this.role = role;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }
  
  @Override
  protected void setUp() throws Exception
  {
    PermissionTest.classSetUp(this.getRole());
  }

  @Override
  protected void tearDown() throws Exception
  {
    PermissionTest.classTearDown();
  }  
}
