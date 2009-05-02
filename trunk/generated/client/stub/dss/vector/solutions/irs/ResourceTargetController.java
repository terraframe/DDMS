package dss.vector.solutions.irs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dss.vector.solutions.MDSSUserDTO;
import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.irs.ResourceTargetViewDTO;

;

public class ResourceTargetController extends ResourceTargetControllerBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/irs/ResourceTarget/";

  public static final String LAYOUT           = JSP_DIR + "layout.jsp";

  private static final long  serialVersionUID = 1240257007714L;

  public ResourceTargetController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    // dss.vector.solutions.irs.ResourceTargetQueryDTO query =
    // dss.vector.solutions.irs.ResourceTargetDTO.getAllInstances(clientRequest,
    // null, true, 20, 1);
    // req.setAttribute("query", query);

    // MDSSUserDTO teamMember = new MDSSUserDTO(clientRequest);
    // teamMember.setPerson(PersonDTO.lock(clientRequest,
    // "6puntn78b7wz9xa04940iokdkz46yoq68t2041h1jskpl9mq0iawu5qighg9got7"));
    // teamMember.apply();

    // SprayTeamDTO newTeam = new SprayTeamDTO(clientRequest);
    // newTeam.getAllSprayTeam

    // newTeam.addSprayTeamMembers(teamMember);

    // newTeam.lock(clientRequest, newTeam.getId()).apply();

    List<SprayTeamDTO> sprayTeams = (List<SprayTeamDTO>) SprayTeamDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet();

    // Integer i = 0;
    // for(SprayTeamDTO team: sprayTeams)
    // {
    // team.setTeamCode("TEAM"+i.toString());
    // team = SprayTeamDTO.lock(clientRequest, team.getId());
    // team.apply();
    // i++;
    // }

    req.setAttribute("sprayTeams", sprayTeams);
    req.setAttribute("page_title", "View All ResourceTargetController Objects");
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    resp.sendError(500);
  }

  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    com.terraframe.mojo.constants.ClientRequestIF clientRequest = super.getClientRequest();
    List<SprayTeamDTO> sprayTeams = (List<SprayTeamDTO>) SprayTeamDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet();

    Integer year = new Integer(req.getParameter("year"));

    List<String> targetIds = new ArrayList<String>();
    // Collections.sort(children, new GeoEntitySorter());
    for (SprayTeamDTO teamMember : sprayTeams)
    {
      targetIds.add(teamMember.getId());
    }
    ResourceTargetViewDTO[] geoTargetViews = ResourceTargetViewDTO.getResourceTargets(clientRequest, (String[]) targetIds.toArray(new String[targetIds.size()]), year);

    req.setAttribute("sprayTeams", sprayTeams);
    render("viewComponent.jsp");
  }

  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    this.viewAll();
  }

  /*
   * public void newInstance() throws java.io.IOException,
   * javax.servlet.ServletException {
   * com.terraframe.mojo.constants.ClientRequestIF clientRequest =
   * super.getClientRequest(); dss.vector.solutions.irs.ResourceTargetDTO dto =
   * new dss.vector.solutions.irs.ResourceTargetDTO(clientRequest);
   * req.setAttribute("item", dto); req.setAttribute("page_title",
   * "Create ResourceTargetController"); render("createComponent.jsp"); } public
   * void failNewInstance() throws java.io.IOException,
   * javax.servlet.ServletException { this.viewAll(); } public void
   * cancel(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException { dto.unlock();
   * this.view(dto.getId()); } public void
   * failCancel(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException { resp.sendError(500);
   * } public void create(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException { try { dto.apply();
   * this.view(dto.getId()); } catch(com.terraframe.mojo.ProblemExceptionDTO e)
   * { this.failCreate(dto); } } public void
   * failCreate(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException {
   * req.setAttribute("dss_vector_solutions_irs_ResourceTarget_targeter",
   * dss.vector
   * .solutions.irs.TargeterDTO.getAllInstances(super.getClientSession(
   * ).getRequest(), "keyName", true, 0, 0).getResultSet());
   * req.setAttribute("item", dto); req.setAttribute("page_title",
   * "Create ResourceTargetController"); render("createComponent.jsp"); } public
   * void viewPage(java.lang.String sortAttribute, java.lang.Boolean
   * isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber)
   * throws java.io.IOException, javax.servlet.ServletException {
   * com.terraframe.mojo.constants.ClientRequestIF clientRequest =
   * super.getClientRequest(); dss.vector.solutions.irs.ResourceTargetQueryDTO
   * query =
   * dss.vector.solutions.irs.ResourceTargetDTO.getAllInstances(clientRequest,
   * sortAttribute, isAscending, pageSize, pageNumber);
   * req.setAttribute("query", query); req.setAttribute("page_title",
   * "View All ResourceTargetController Objects");
   * render("viewAllComponent.jsp"); } public void failViewPage(java.lang.String
   * sortAttribute, java.lang.String isAscending, java.lang.String pageSize,
   * java.lang.String pageNumber) throws java.io.IOException,
   * javax.servlet.ServletException { resp.sendError(500); } public void
   * edit(java.lang.String id) throws java.io.IOException,
   * javax.servlet.ServletException { dss.vector.solutions.irs.ResourceTargetDTO
   * dto =
   * dss.vector.solutions.irs.ResourceTargetDTO.lock(super.getClientRequest(),
   * id); req.setAttribute("dss_vector_solutions_irs_ResourceTarget_targeter",
   * dss
   * .vector.solutions.irs.TargeterDTO.getAllInstances(super.getClientSession(
   * ).getRequest(), "keyName", true, 0, 0).getResultSet());
   * req.setAttribute("item", dto); req.setAttribute("page_title",
   * "Edit ResourceTargetController"); render("editComponent.jsp"); } public
   * void failEdit(java.lang.String id) throws java.io.IOException,
   * javax.servlet.ServletException { this.view(id); } public void
   * update(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException { try { dto.apply();
   * this.view(dto.getId()); } catch(com.terraframe.mojo.ProblemExceptionDTO e)
   * { this.failUpdate(dto); } } public void
   * failUpdate(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException {
   * req.setAttribute("dss_vector_solutions_irs_ResourceTarget_targeter",
   * dss.vector
   * .solutions.irs.TargeterDTO.getAllInstances(super.getClientSession(
   * ).getRequest(), "keyName", true, 0, 0).getResultSet());
   * req.setAttribute("item", dto); req.setAttribute("page_title",
   * "Update ResourceTargetController"); render("editComponent.jsp"); }
   *
   *
   *
   * public void delete(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException { try { dto.delete();
   * this.viewAll(); } catch(com.terraframe.mojo.ProblemExceptionDTO e) {
   * this.failDelete(dto); } } public void
   * failDelete(dss.vector.solutions.irs.ResourceTargetDTO dto) throws
   * java.io.IOException, javax.servlet.ServletException {
   * req.setAttribute("dss_vector_solutions_irs_ResourceTarget_targeter",
   * dss.vector
   * .solutions.irs.TargeterDTO.getAllInstances(super.getClientSession(
   * ).getRequest(), "keyName", true, 0, 0).getResultSet());
   * req.setAttribute("item", dto); req.setAttribute("page_title",
   * "Edit ResourceTargetController"); render("editComponent.jsp"); }
   */
}
