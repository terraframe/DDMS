package dss.vector.solutions.form;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.TypeGeneratorInfo;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.system.metadata.MdFieldDTO;
import com.runwaysdk.system.metadata.MdTypeDTO;
import com.runwaysdk.system.metadata.MdWebFieldDTO;
import com.runwaysdk.system.metadata.MdWebFormDTO;

import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.util.ErrorUtility;

public class MdFormAdminController extends MdFormAdminControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String JSP_DIR                   = "WEB-INF/dss/vector/solutions/form/";

  public static final String LAYOUT                    = "/layout.jsp";

  public static final String MDFORM_ADMIN              = JSP_DIR + "mdFormAdmin.jsp";

  public static final String AVAILABLE_MD_FIELDS_JSP   = JSP_DIR + "availableMdFields.jsp";

  public static final String EXISTING_FORMS_JSP        = JSP_DIR + "existingForms.jsp";

  public static final String FETCH_FORM_ATTRIBUTES_JSP = JSP_DIR + "fetchFormAttributes.jsp";

  public static final String FETCH_FORM_FIELDS_JSP = JSP_DIR + "fetchFormFields.jsp";

  public static final String EDIT_FORM_ATTRIBUTES_JSP  = JSP_DIR + "editFormAttributes.jsp";

  public static final String CREATE_NEW_FORM_JSP       = JSP_DIR + "createNewForm.jsp";

  private static final long  serialVersionUID          = -117792511;

  public MdFormAdminController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous, JSP_DIR, LAYOUT);
  }

  @Override
  public void viewAll() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      MdWebFormDTO[] forms = MdFormUtilDTO.getAllForms(clientRequest);

      this.req.setAttribute("forms", forms);

      render("viewAllComponent.jsp");
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void existingForms() throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = this.getClientRequest();
      MdWebFormDTO[] forms = MdFormUtilDTO.getAllForms(clientRequest);

      this.req.setAttribute("forms", forms);
      
      this.req.getRequestDispatcher(EXISTING_FORMS_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void fetchFormAttributes(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.get(clientRequest, id);
      req.setAttribute("form", form);

      this.req.getRequestDispatcher(FETCH_FORM_ATTRIBUTES_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void fetchFormFields(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.get(clientRequest, id);
      MdWebFieldDTO[] fields = MdFormUtilDTO.getFields(clientRequest, form);
      req.setAttribute("fields", fields);
      
      this.req.getRequestDispatcher(FETCH_FORM_FIELDS_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void editFormAttributes(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.lock(clientRequest, id);
      req.setAttribute("form", form);

      this.req.getRequestDispatcher(EDIT_FORM_ATTRIBUTES_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void availableFields() throws IOException, ServletException
  {
    try
    {
      MdFieldTypeQueryDTO query = MdFormUtilDTO.getAvailableFields(this.getClientRequest());
      req.setAttribute("results", query.getResultSet());

      this.req.getRequestDispatcher(AVAILABLE_MD_FIELDS_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  /**
   * Provides a new MdField definition screen.
   */
  @Override
  public void newMdField(String mdFieldType) throws IOException, ServletException
  {
    try
    {
      MdTypeDTO mdType = MdTypeDTO.get(this.getClientRequest(), mdFieldType);
      String type = mdType.getPackageName() + "." + mdType.getTypeName();

      // grab the appropriate MdField
      Class<?> klass = LoaderDecorator.load(type + TypeGeneratorInfo.DTO_SUFFIX);

      // populate the new MdField instance
      BusinessDTO dto = (BusinessDTO) klass.getConstructor(ClientRequestIF.class).newInstance(this.getClientRequest());
      this.req.setAttribute("item", dto);

      // forward to the namespaced jsp
      String pck = type.replaceAll("\\.", File.separator);
      String createJSP = "WEB-INF" + File.separator + pck + File.separator + "createComponent.jsp";

      this.req.getRequestDispatcher(createJSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void createMdField(MdFieldDTO mdField, String mdFormId) throws IOException, ServletException
  {
    try
    {
      MdFieldDTO created = MdFormUtilDTO.createMdField(this.getClientRequest(), mdField, mdFormId);

      // forward to the proper read view
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void failViewAll() throws IOException
  {
    resp.sendError(500);
  }

  @Override
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    this.newInstance(new MdWebFormDTO(this.getClientRequest()));
  }

  public void newInstance(MdWebFormDTO form) throws IOException, ServletException
  {
    try
    {
      req.setAttribute("form", form);

      this.req.getRequestDispatcher(CREATE_NEW_FORM_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  public void failNewInstance() throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void create(MdWebFormDTO form) throws IOException, ServletException
  {
    try
    {
      MdFormUtilDTO.apply(getClientRequest(), form);
      req.setAttribute("form", form);

      this.req.getRequestDispatcher(FETCH_FORM_ATTRIBUTES_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void failCreate(MdWebFormDTO form) throws IOException, ServletException
  {
    this.newInstance(form);
  }

  @Override
  public void view(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.get(clientRequest, id);
      req.setAttribute("form", form);

      render("viewComponent.jsp");
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void failView(String id) throws IOException, ServletException
  {
    this.viewAll();
  }

  @Override
  public void edit(String id) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = super.getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.lock(clientRequest, id);

      req.setAttribute("form", form);
      render("editComponent.jsp");
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void failEdit(String id) throws IOException, ServletException
  {
    this.edit(id);
  }

  @Override
  public void update(MdWebFormDTO form) throws IOException, ServletException
  {
    try
    {
      form.apply();
      this.fetchFormAttributes(form.getId());
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void failUpdate(MdWebFormDTO form) throws IOException, ServletException
  {
    req.setAttribute("item", form);
    render("editComponent.jsp");
  }

  @Override
  public void delete(MdWebFormDTO form) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdFormUtilDTO.delete(clientRequest, form);
      
      this.req.getRequestDispatcher(MDFORM_ADMIN).forward(req, resp);
      
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void deleteField(String formId, String fieldId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.get(clientRequest, formId);
      
      MdWebFieldDTO[] fields = MdFormUtilDTO.getFields(clientRequest, form);
      MdWebFieldDTO fieldToDelete = null;
      for (MdWebFieldDTO field : fields)
      {
        if (fieldId.equals(field.getId()))
          fieldToDelete = field;
      }
      
      MdFormUtilDTO.deleteField(clientRequest, form, fieldToDelete);
      
      req.setAttribute("fields", fields);
      
      this.req.getRequestDispatcher(FETCH_FORM_FIELDS_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void failDelete(MdWebFormDTO form) throws IOException, ServletException
  {
    req.setAttribute("item", form);
    render("editComponent.jsp");
  }

  @Override
  public void cancel(MdWebFormDTO form) throws IOException, ServletException
  {
    form.unlock();
    this.view(form.getId());
  }

  public void failCancel(MdWebFormDTO form) throws IOException, ServletException
  {
    this.edit(form.getId());
  }

  @Override
  public void mdFormAdmin() throws IOException, ServletException
  {
    try
    {
      /*
       * ClientRequestIF clientRequest = this.getClientRequest(); MdWebFormDTO[]
       * forms = MdFormUtilDTO.getAllForms(clientRequest);
       * 
       * this.req.setAttribute("forms", forms);
       */
      this.req.getRequestDispatcher(MDFORM_ADMIN).forward(req, resp);
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
}
