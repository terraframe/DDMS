package dss.vector.solutions.util;

import java.io.IOException;

import javax.servlet.ServletException;

public class ConfigurableListController extends ConfigurableListControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1243479609000L;
  
  public ConfigurableListController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
    this.dir = "WEB-INF/dss/vector/solutions/util/ConfigurableListController/";
    this.layout = "/layout.jsp";
  }
  
  @Override
  public void viewAll() throws IOException, ServletException
  {
//    ClientRequestIF clientRequest = super.getClientRequest();
    render("viewAll.jsp");
  }
}
