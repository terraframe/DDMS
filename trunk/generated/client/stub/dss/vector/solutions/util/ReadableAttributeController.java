package dss.vector.solutions.util;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.system.metadata.MdClassQueryDTO;

public class ReadableAttributeController extends ReadableAttributeControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1239296298324L;
  
  public ReadableAttributeController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
    this.dir = "WEB-INF/dss/vector/solutions/util/ReadableAttributeController/";
    this.layout = this.dir + "layout.jsp";
  }

  @Override
  public void getUniversal(String actor) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    MdClassQueryDTO classes = FacadeDTO.getMDSSClasses(clientRequest);
    req.setAttribute("page_title", "Select_Class");
    req.setAttribute("actor", actor);
    req.setAttribute("actorOptions", FacadeDTO.getMDSSRoles(clientRequest));
    req.setAttribute("query", classes);
    req.setAttribute("universals", classes.getResultSet());
    render("selectUniversal.jsp");
  }
  
  @Override
  public void getAttributes(String universal, String actor) throws IOException, ServletException
  {
    ReadableAttributeViewDTO[] attributeViews = ReadableAttributeViewDTO.getActorAttributes(super.getClientRequest(), universal, actor);
    req.setAttribute("page_title", "Assign_Attribute_Permissions");
    req.setAttribute("views", Arrays.asList(attributeViews));
    req.setAttribute("universal", universal);
    req.setAttribute("actor", actor);
    render("view.jsp");
  }
  
  @Override
  public void setAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    ReadableAttributeViewDTO.setActorAttributes(super.getClientRequest(), universal, actor, attributeViews);
    req.setAttribute("page_title", "Permissions_Sucess");
    render("success.jsp");
  }
}
