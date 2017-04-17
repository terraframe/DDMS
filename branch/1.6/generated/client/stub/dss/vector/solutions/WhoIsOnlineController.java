package dss.vector.solutions;



public class WhoIsOnlineController extends WhoIsOnlineControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/WhoIsOnline/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1236023121846L;
  
  public WhoIsOnlineController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }
  
  @Override
  public void view() throws java.io.IOException, javax.servlet.ServletException
  {
    render("whoIsOnline.jsp");
  }
  
  @Override
  public void failView() throws java.io.IOException, javax.servlet.ServletException
  {
    
  }
}
