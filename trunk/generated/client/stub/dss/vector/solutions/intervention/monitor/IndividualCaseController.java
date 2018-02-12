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
package dss.vector.solutions.intervention.monitor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.AttributeNotificationDTO;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.format.AbstractFormatFactory;
import com.runwaysdk.format.Format;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.PersonDTO;
import dss.vector.solutions.PersonViewDTO;
import dss.vector.solutions.RelativeValueProblemDTO;
import dss.vector.solutions.geo.generated.HealthFacilityDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.surveillance.RequiredDiagnosisDateProblemDTO;
import dss.vector.solutions.util.AttributeUtil;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.FacadeDTO;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.LocalizationFacadeDTO;
import dss.vector.solutions.util.RedirectUtility;

public class IndividualCaseController extends IndividualCaseControllerBase implements Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/intervention/monitor/IndividualCase/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1254360074929L;

  public IndividualCaseController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void viewAll() throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualCaseQueryDTO query = IndividualCaseDTO.getAllInstances(clientRequest, null, true, 20, 1);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewAll() throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void search(Date diagnosisDate, Date caseReportDate, String personId) throws IOException, ServletException
  {
    try
    {
      validateSearchCriteria(diagnosisDate, caseReportDate, personId);

      ClientRequestIF clientRequest = getClientRequest();
      IndividualCaseDTO individualCase = IndividualCaseDTO.searchForExistingCase(clientRequest, diagnosisDate, personId);

      if (individualCase.isNewInstance())
      {
        // Ensure the use has permissions to create a new Instance
        new IndividualCaseDTO(clientRequest);

        individualCase.setDiagnosisDate(diagnosisDate);
        individualCase.setCaseReportDate(caseReportDate);

        renderCreate(individualCase, personId);
      }
      else
      {
        renderView(individualCase);
      }
    }

    catch (Throwable t)
    {
      ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous(), false);

      Format<Date> f = AbstractFormatFactory.getFormatFactory().getFormat(Date.class);

      String failDiagnosis = f.format(diagnosisDate, req.getLocale());
      String failCase = f.format(caseReportDate, req.getLocale());

      this.failSearch(failDiagnosis, failCase, personId);
    }
  }

  private void validateSearchCriteria(Date diagnosisDate, Date caseReportDate, String personId)
  {
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    ClientRequestIF clientRequest = super.getClientSession().getRequest();

    if (diagnosisDate == null)
    {
      problems.add(new RequiredDiagnosisDateProblemDTO(clientRequest, req.getLocale()));
    }
    else
    {
      if (caseReportDate != null && caseReportDate.before(diagnosisDate))
      {
        String caseReportLabel = FacadeDTO.getAttributeDisplayLabel(clientRequest, IndividualCaseDTO.CLASS, IndividualCaseDTO.CASEREPORTDATE);
        String diagnosisDateLabel = FacadeDTO.getAttributeDisplayLabel(clientRequest, IndividualCaseDTO.CLASS, IndividualCaseDTO.DIAGNOSISDATE);

        RelativeValueProblemDTO problem = new RelativeValueProblemDTO(clientRequest, req.getLocale());
        problem.setAttributeName(IndividualCaseDTO.CASEREPORTDATE);
        problem.setComponentId(AttributeNotificationDTO.NO_COMPONENT);
        problem.setAttributeDisplayLabel(caseReportLabel);
        problem.setRelation(LocalizationFacadeDTO.getFromBundles(clientRequest, "Compare_AE"));
        problem.setRelativeAttributeLabel(diagnosisDateLabel);
        problems.add(problem);
      }

      if (personId != null)
      {
        PersonDTO person = PersonDTO.get(clientRequest, personId);

        if (person != null && diagnosisDate.before(person.getDateOfBirth()))
        {
          String diagnosisDateLabel = FacadeDTO.getAttributeDisplayLabel(clientRequest, IndividualCaseDTO.CLASS, IndividualCaseDTO.DIAGNOSISDATE);

          RelativeValueProblemDTO problem = new RelativeValueProblemDTO(clientRequest, req.getLocale());
          problem.setAttributeName(IndividualCaseDTO.DIAGNOSISDATE);
          problem.setComponentId(AttributeNotificationDTO.NO_COMPONENT);
          problem.setAttributeDisplayLabel(diagnosisDateLabel);
          problem.setRelation(LocalizationFacadeDTO.getFromBundles(clientRequest, "Compare_AE"));
          problem.setRelativeAttributeLabel(person.getDateOfBirthMd().getDisplayLabel());
          problems.add(problem);
        }
      }
    }

    if (problems.size() > 0)
    {
      throw new ProblemExceptionDTO("", problems);
    }
  }

  private void renderCreate(IndividualCaseDTO individualCase, String personId) throws IOException, ServletException
  {
    IndividualInstanceDTO dto = new IndividualInstanceDTO(getClientRequest());
    dto.setActivelyDetected(false);
    TermDTO[] symptoms = dto.getSymptoms();

    renderCreate(individualCase, personId, dto, symptoms);
  }

  private void renderCreate(IndividualCaseDTO individualCase, String personId, IndividualInstanceDTO dto, TermDTO[] symptoms) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = this.getClientRequest();

    PersonViewDTO person = PersonDTO.getView(clientRequest, personId);
    individualCase.setResidenceText(AttributeUtil.getString(PersonViewDTO.RESIDENTIALINFORMATION, person));
    individualCase.setWorkplaceText(AttributeUtil.getString(PersonViewDTO.WORKINFORMATION, person));

    Set<String> permissions = Halp.getReadableAttributeNames(IndividualCaseDTO.CLASS, this.getClientRequest());

    // Case stuff
    req.setAttribute("hasPermission", permissions.contains(IndividualCaseDTO.PATIENT));
    req.setAttribute("person", person);
    req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    req.setAttribute("individualCase", individualCase);
    req.setAttribute("personId", personId);

    // Instance Stuff
    req.setAttribute("item", dto);
    req.setAttribute("healthFacility", AttributeUtil.getValue(IndividualInstanceDTO.HEALTHFACILITY, dto));
    req.setAttribute("diagnosisType", DiagnosisTypeDTO.allItems(clientRequest));
    req.setAttribute("symptoms", Arrays.asList(symptoms));
    req.setAttribute("caseId", individualCase.getId());
    req.setAttribute("HEALTH_FACILITY", HealthFacilityDTO.CLASS);

    render("createComponent.jsp");
  }

  @Override
  public void failSearch(String diagnosisDate, String caseReportDate, String personId) throws IOException, ServletException
  {
    ClientRequestIF request = this.getClientSession().getRequest();

    req.setAttribute("diagnosisDate", diagnosisDate);
    req.setAttribute("caseReportDate", caseReportDate);

    if (personId != null && !personId.equals(""))
    {
      req.setAttribute("person", PersonDTO.getView(request, personId));
    }

    renderSearch(new IndividualCaseViewDTO(request));
  }

  public void cancel(IndividualCaseDTO dto) throws IOException, ServletException
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

  public void failCancel(IndividualCaseDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      renderView(IndividualCaseDTO.get(super.getClientRequest(), id));
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

  private void renderView(IndividualCaseDTO individualCaseDTO) throws IOException, ServletException
  {
    RedirectUtility utility = new RedirectUtility(req, resp);
    utility.put("id", individualCaseDTO.getId());
    utility.checkURL(this.getClass().getSimpleName(), "view");

    Set<String> permissions = Halp.getReadableAttributeNames(IndividualCaseDTO.CLASS, this.getClientRequest());
    boolean hasPermissions = permissions.contains(IndividualCaseDTO.PATIENT);

    req.setAttribute("query", individualCaseDTO.getInstances());
    req.setAttribute("item", individualCaseDTO);
    req.setAttribute("hasPermission", hasPermissions);

    if (hasPermissions)
    {
      try
      {
        PersonViewDTO person = individualCaseDTO.getPatient().getPerson().getView();

        req.setAttribute("person", person);
        req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
      }
      catch (Exception e)
      {

      }
    }

    render("viewComponent.jsp");
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.newInstance();
  }

  public void delete(IndividualCaseDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();

      this.newInstance();
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

  public void failDelete(IndividualCaseDTO dto) throws IOException, ServletException
  {
    renderEdit(dto);
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      IndividualCaseDTO dto = IndividualCaseDTO.lock(super.getClientRequest(), id);
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

  private void renderEdit(IndividualCaseDTO dto) throws IOException, ServletException
  {

    Set<String> permissions = Halp.getReadableAttributeNames(IndividualCaseDTO.CLASS, this.getClientRequest());
    boolean hasPermissions = permissions.contains(IndividualCaseDTO.PATIENT);

    req.setAttribute("hasPermission", hasPermissions);
    req.setAttribute("individualCase", dto);

    if (hasPermissions)
    {
      PersonViewDTO person = dto.getPatient().getPerson().getView();
      req.setAttribute("person", person);
      req.setAttribute("residential", AttributeUtil.getGeoEntityFromGeoId(PersonViewDTO.RESIDENTIALGEOID, person));
    }

    render("editComponent.jsp");
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    IndividualCaseQueryDTO query = IndividualCaseDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }

  public void update(IndividualCaseDTO dto) throws IOException, ServletException
  {
    try
    {
      if (dto.getProbableSource() == null)
      {
        dto.setProbableSource(dto.getResidence());
      }

      dto.apply();

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(IndividualCaseDTO dto) throws IOException, ServletException
  {
    renderEdit(dto);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      IndividualCaseViewDTO dto = new IndividualCaseViewDTO(clientRequest);
      renderSearch(dto);
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

  private void renderSearch(IndividualCaseViewDTO dto) throws IOException, ServletException
  {
    Set<String> permissions = Halp.getReadableAttributeNames(IndividualCaseDTO.CLASS, this.getClientRequest());

    req.setAttribute("item", dto);
    req.setAttribute("person", new PersonViewDTO(this.getClientRequest()));
    req.setAttribute("hasPermission", permissions.contains(IndividualCaseDTO.PATIENT));

    render("searchComponent.jsp");
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void create(IndividualCaseDTO dto, String personId, IndividualInstanceDTO instance, TermDTO[] symptoms) throws IOException, ServletException
  {
    try
    {
      dto.applyWithPersonId(personId, instance, symptoms);

      ClientRequestIF request = dto.getRequest();

      ErrorUtility.prepareInformation(request.getInformation(), req);

      renderView(dto);
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failCreate(dto, personId, instance, symptoms);
      }
    }
  }

  @Override
  public void failCreate(IndividualCaseDTO dto, String personId, IndividualInstanceDTO instance, TermDTO[] symptoms) throws IOException, ServletException
  {
    renderCreate(dto, personId, instance, symptoms);
  }
}
