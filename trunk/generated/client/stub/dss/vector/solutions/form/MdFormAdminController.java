package dss.vector.solutions.form;

import java.io.IOException;

import javax.servlet.ServletException;

public class MdFormAdminController extends MdFormAdminControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/form/";
  private static final long serialVersionUID = -117792511;
  
  public MdFormAdminController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, "");
  }
  
  @Override
  public void mdFormAdmin() throws IOException, ServletException
  {
    render("mdFormAdmin.jsp");
  }
}
