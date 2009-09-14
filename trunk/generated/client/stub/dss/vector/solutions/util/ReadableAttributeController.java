package dss.vector.solutions.util;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;

import com.terraframe.mojo.constants.ClientRequestIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.surveillance.AggregatedAgeGroupDTO;
import dss.vector.solutions.surveillance.AggregatedAgeGroupQueryDTO;

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
    if (actor == null)
    {
      actor = MDSSRoleInfo.GUI_VISIBILITY;
    }

    ClientRequestIF clientRequest = super.getClientRequest();
    req.setAttribute("actor", actor);
    req.setAttribute("actorOptions", FacadeDTO.getMDSSRoles(clientRequest));

    // I commented the follow lines out because they are not used in
    // selectUniversal.jsp and they were causing exceptions to be thrown
    // -Justin Smethie
    
    // MdClassQueryDTO classes = FacadeDTO.getMDSSClasses(clientRequest);
    // req.setAttribute("query", classes);
    // req.setAttribute("universals", classes.getResultSet());

    AggregatedAgeGroupQueryDTO query = AggregatedAgeGroupDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("ageGroups", query.getResultSet());

    render("selectUniversal.jsp");
  }

  @Override
  public void getAttributes(String universal, String actor) throws IOException, ServletException
  {
    ReadableAttributeViewDTO[] attributeViews = ReadableAttributeViewDTO.getActorAttributes(super.getClientRequest(), universal, actor);
    req.setAttribute("views", Arrays.asList(attributeViews));
    req.setAttribute("universal", universal);
    req.setAttribute("actor", actor);
    render("view.jsp");
  }

  @Override
  public void setAttributes(String universal, String actor, ReadableAttributeViewDTO[] attributeViews) throws IOException, ServletException
  {
    ReadableAttributeViewDTO.setActorAttributes(super.getClientRequest(), universal, actor, attributeViews);
    render("success.jsp");
  }
}
