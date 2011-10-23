package dss.vector.solutions.form.business;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.system.metadata.MdWebFormDTO;

import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.util.ErrorUtility;

public class FormSurveyController extends FormSurveyControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR          = "WEB-INF/dss/vector/solutions/form/business/FormSurvey/";

  public static final String LAYOUT           = "/layout.jsp";

  private static final long  serialVersionUID = 1196476865;

  public FormSurveyController(HttpServletRequest req, HttpServletResponse resp, Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  public void cancel(FormSurveyDTO dto) throws IOException, ServletException
  {
    dto.unlock();
    this.view(dto.getId());
  }

  public void failCancel(FormSurveyDTO dto) throws IOException, ServletException
  {
    this.edit(dto.getId());
  }

  public void create(FormSurveyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failCreate(dto);
      }
    }
  }

  public void failCreate(FormSurveyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("createComponent.jsp");
  }

  public void delete(FormSurveyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.delete();
      this.viewAll();
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failDelete(dto);
      }
    }
  }

  public void failDelete(FormSurveyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      FormSurveyDTO dto = FormSurveyDTO.lock(super.getClientRequest(), id);
      req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failEdit(id);
      }
    }
  }

  public void failEdit(String id) throws IOException, ServletException
  {
    this.view(id);
  }

  public void newInstance() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      FormSurveyDTO dto = new FormSurveyDTO(clientRequest);
      req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("createComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failNewInstance();
      }
    }
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  public void update(FormSurveyDTO dto) throws IOException, ServletException
  {
    try
    {
      dto.apply();
      this.view(dto.getId());
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failUpdate(dto);
      }
    }
  }

  public void failUpdate(FormSurveyDTO dto) throws IOException, ServletException
  {
    req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
    req.setAttribute("item", dto);
    render("editComponent.jsp");
  }

  public void view(String id) throws IOException, ServletException
  {
    try
    {
      dss.vector.solutions.util.RedirectUtility utility = new dss.vector.solutions.util.RedirectUtility(req, resp);
      utility.put("id", id);
      utility.checkURL(this.getClass().getSimpleName(), "view");
      ClientRequestIF clientRequest = super.getClientRequest();
      FormSurveyDTO dto = FormSurveyDTO.get(clientRequest, id);
      req.setAttribute("disease", dss.vector.solutions.general.DiseaseDTO.getAllInstances(super.getClientSession().getRequest(), "keyName", true, 0, 0).getResultSet());
      req.setAttribute("item", dto);
      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirect = dss.vector.solutions.util.ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());
      if (!redirect)
      {
        this.failView(id);
      }
    }
  }

  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  public void viewAll() throws IOException, ServletException
  {
    try
    {
      String surveyFormType = "dss.vector.solutions.form.FormSurvey";

      MdWebFormDTO surveyForm = MdFormUtilDTO.getForm(this.getClientRequest(), surveyFormType);

      String householdFormType = "dss.vector.solutions.form.FormHousehold";

      MdWebFormDTO householdForm = MdFormUtilDTO.getForm(this.getClientRequest(), householdFormType);

      String bedNetFormType = "dss.vector.solutions.form.FormBedNet";

      MdWebFormDTO bedNetForm = MdFormUtilDTO.getForm(this.getClientRequest(), bedNetFormType);

      String personFormType = "dss.vector.solutions.form.FormPerson";
      
      MdWebFormDTO personForm = MdFormUtilDTO.getForm(this.getClientRequest(), personFormType);
      
      this.req.setAttribute("surveyFormId", surveyForm.getId());
      this.req.setAttribute("surveyClassType", FormSurveyDTO.CLASS);
      this.req.setAttribute("surveyFormType", surveyFormType);
      this.req.setAttribute("householdFormId", householdForm.getId());
      this.req.setAttribute("householdClassType", FormHouseholdDTO.CLASS);
      this.req.setAttribute("householdFormType", householdFormType);
      this.req.setAttribute("bedNetFormId", bedNetForm.getId());
      this.req.setAttribute("bedNetClassType", FormBedNetDTO.CLASS);
      this.req.setAttribute("bedNetFormType", bedNetFormType);
      this.req.setAttribute("personFormId", personForm.getId());
      this.req.setAttribute("personClassType", FormPersonDTO.CLASS);
      this.req.setAttribute("personFormType", personFormType);

      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failViewAll();
      }
    }
  }

  public void failViewAll() throws IOException, ServletException
  {
    this.req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  public void viewPage(String sortAttribute, Boolean isAscending, Integer pageSize, Integer pageNumber) throws IOException, ServletException
  {
    ClientRequestIF clientRequest = super.getClientRequest();
    FormSurveyQueryDTO query = FormSurveyDTO.getAllInstances(clientRequest, sortAttribute, isAscending, pageSize, pageNumber);
    req.setAttribute("query", query);
    render("viewAllComponent.jsp");
  }

  public void failViewPage(String sortAttribute, String isAscending, String pageSize, String pageNumber) throws IOException, ServletException
  {
    resp.sendError(500);
  }
}
