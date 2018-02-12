/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.transport.attributes.AttributeDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.general.DiseaseDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.RedirectUtility;

public class PersonController extends PersonControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/Person/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1240792904565L;

  public PersonController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void search(PersonViewDTO person) throws IOException, ServletException
  {
    PersonWithDelegatesViewQueryDTO query = person.searchForDuplicates();
    String repassword = req.getParameter("person.repassword");

    req.setAttribute("repassword", repassword);
    req.setAttribute("query", query);
    req.setAttribute("newPerson", person);

    this.setupQueryLabels(query);

    // Saving the sex is a pain. This is a shortcut.
    TermDTO sex = person.getSex();
    if (sex != null)
    {
      req.setAttribute("sex", sex.getId());
    }

    render("searchResults.jsp");
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();

      // Ensure that the user has permissions to create a person
      new PersonDTO(clientRequest);

      PersonViewDTO view = new PersonViewDTO(clientRequest);
      renderCreate(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance();
      }
    }

  }

  @Override
  public void continueNewInstance(PersonViewDTO person) throws IOException, ServletException
  {
    String repassword = req.getParameter("person.repassword");

    req.setAttribute("repassword", repassword);

    renderCreate(person);
  }

  @Override
  public void failContinueNewInstance(PersonViewDTO person) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void createFromView(PersonViewDTO person) throws IOException, ServletException
  {
    try
    {
      person.apply();
      this.view(person.getPersonId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.renderCreate(person);
      }
    }
  }

  @Override
  public void failCreateFromView(PersonViewDTO person) throws IOException, ServletException
  {
    renderCreate(person);
  }

  private void renderCreate(PersonViewDTO view) throws IOException, ServletException
  {
    prepareRequest(view);
    render("createComponent.jsp");
  }

  private void prepareRequest(PersonViewDTO view)
  {
    req.setAttribute("sex", view.getSex());
    req.setAttribute("item", view);
    req.setAttribute("allDiseases", Arrays.asList(DiseaseDTO.getAllDiseases(getClientRequest())));
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      PersonViewDTO dto = PersonDTO.lockView(super.getClientRequest(), id);

      renderEdit(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failEdit(id);
      }
    }

  }

  @Override
  public void updateFromView(PersonViewDTO person) throws IOException, ServletException
  {
    try
    {
      person.apply();
      this.view(person.getPersonId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.renderEdit(person);
      }
    }
  }

  @Override
  public void failUpdateFromView(PersonViewDTO person) throws IOException, ServletException
  {
    renderEdit(person);
  }

  private void renderEdit(PersonViewDTO dto) throws IOException, ServletException
  {
    prepareRequest(dto);
    render("editComponent.jsp");
  }

  @Override
  public void deleteFromView(PersonViewDTO person) throws IOException, ServletException
  {
    try
    {
      PersonDTO.lock(super.getClientRequest(), person.getPersonId()).delete();
      viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDeleteFromView(person);
      }
    }
  }

  @Override
  public void failDeleteFromView(PersonViewDTO person) throws IOException, ServletException
  {
    this.renderEdit(person);
  }

  private void renderView(PersonViewDTO view) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", view.getPersonId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, view));
    prepareRequest(view);
    render("viewComponent.jsp");
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();

    PersonWithDelegatesViewQueryDTO query = PersonWithDelegatesViewDTO.getPage(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);

    this.setupQueryLabels(query);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void viewAll() throws IOException, ServletException
  {
    if (!this.isAsynchronous())
    {
      new RedirectUtility(req, resp).checkURL(this.getClass().getSimpleName(), "viewAll");

      ClientRequestIF clientRequest = super.getClientRequest();

      PersonWithDelegatesViewQueryDTO query = PersonWithDelegatesViewDTO.getPage(clientRequest, null, true, 20, 1);

      this.setupQueryLabels(query);

      req.setAttribute("query", query);
      render("viewAllComponent.jsp");
    }
  }

  private void setupQueryLabels(PersonWithDelegatesViewQueryDTO query)
  {
    this.setupQueryLabel(query, PersonWithDelegatesViewDTO.ISMDSSUSER, "userLabel");
    this.setupQueryLabel(query, PersonWithDelegatesViewDTO.ISSPRAYOPERATOR, "sprayOperatorLabel");
    this.setupQueryLabel(query, PersonWithDelegatesViewDTO.ISSPRAYLEADER, "sprayLeaderLabel");
    this.setupQueryLabel(query, PersonWithDelegatesViewDTO.ISSTOCKSTAFF, "stockStaffLabel");
    this.setupQueryLabel(query, PersonWithDelegatesViewDTO.ISSUPERVISOR, "supervisorLabel");
    this.setupQueryLabel(query, PersonWithDelegatesViewDTO.ISPHYSICIAN, "physicianLabel");
  }

  private void setupQueryLabel(PersonWithDelegatesViewQueryDTO query, String accessor, String label)
  {
    AttributeDTO attribute = query.getAttributeDTO(accessor);

    if (attribute != null && attribute.isReadable())
    {
      String attributeLabel = attribute.getAttributeMdDTO().getDisplayLabel();

      req.setAttribute(label, attributeLabel);
    }
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      PersonViewDTO view = PersonDTO.getView(clientRequest, id);

      renderView(view);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failView(id);
      }
    }

  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(PersonDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failCreate(dto);
    }
  }

  public void failCreate(PersonDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void update(PersonDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (com.runwaysdk.ProblemExceptionDTO e)
    {
      this.failUpdate(dto);
    }
  }

  public void failUpdate(PersonDTO dto) throws IOException, ServletException
  {
    this.setupRequest();
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void cancel(PersonDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.unlock();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCancel(dto);
      }
    }
  }

  public void failCancel(PersonDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void delete(PersonDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(PersonDTO dto) throws IOException, ServletException
  {
    this.setupRequest();

    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  private void setupRequest()
  {
  }

  public void failSearch(PersonDTO person) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  @Override
  public void editRecipient(String id) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
    PersonViewDTO dto = new PersonViewDTO(request);

    if (id != null && !id.equals(""))
    {
      dto = PersonDTO.getView(request, id);
    }

    this.editRecipient(dto);
  }

  private void editRecipient(PersonViewDTO dto) throws IOException, ServletException
  {
    try
    {

      req.setAttribute("sex", dto.getSex());
      req.setAttribute("item", dto);

      render("editRecipientComponent.jsp");
    }
    catch (Throwable t)
    {
      if (this.isAsynchronous())
      {
        JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
        resp.setStatus(500);
        resp.getWriter().print(jsonE.getJSON());
      }
      else
      {
        this.viewAll();
      }
    }
  }

  @Override
  public void failEditRecipient(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void updateRecipient(PersonViewDTO patient) throws IOException, ServletException
  {
    try
    {
      patient.applyNonDelegates();

      if (!this.isAsynchronous())
      {
        this.viewAll();
      }
      else
      {
        String id = patient.getPersonId();

        resp.getWriter().print(id);
      }
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if(!redirected)
      {
        this.failUpdateRecipient(patient);
      }
    }
  }

  @Override
  public void failUpdateRecipient(PersonViewDTO patient) throws IOException, ServletException
  {
    req.setAttribute("item", patient);

    render("editRecipientComponent.jsp");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void changeDisease(String diseaseName) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = getClientRequest();

    if (clientRequest.isLoggedIn())
    {
      BusinessDTO user = clientRequest.getSessionUser();
      MDSSUserDTO mdss = (MDSSUserDTO) user;
      mdss.changeDisease(diseaseName);

      DiseaseDTO.setCurrentDimension(clientRequest);
    }

    req.getSession().setAttribute(MDSSUserDTO.DISEASENAME, diseaseName);
    
    String diseaseDisplay = DiseaseDTO.getCurrent(clientRequest).getDimension().getDisplayLabel().getValue();
    req.getSession().setAttribute(MDSSUserDTO.DISEASELABEL, diseaseDisplay);

    Map<String, String> menus = (Map<String, String>) req.getSession().getAttribute("menus");

    if (menus == null)
    {
      menus = new HashMap<String, String>();
    }

    if (menus.get(diseaseName) == null)
    {
      menus.put(diseaseName, DiseaseDTO.getMenuJson(getClientRequest()));
    }

    req.getSession().setAttribute("menus", menus);

//    req.getRequestDispatcher("index.jsp").forward(req, resp);
    
    resp.sendRedirect("dss.vector.solutions.kaleidoscope.UserMenuController.kaleidoscopes.mojo");    
  }

  @Override
  public void editPhysician(String id) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientRequest();
    PersonViewDTO dto = new PersonViewDTO(request);

    if (id != null && !id.equals(""))
    {
      dto = PhysicianDTO.getView(request, id);
    }

    this.editRecipient(dto);
  }

}
