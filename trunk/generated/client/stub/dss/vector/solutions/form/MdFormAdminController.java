package dss.vector.solutions.form;

import java.io.IOException;

import javax.servlet.ServletException;

import com.runwaysdk.business.BusinessDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.TypeGeneratorInfo;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.system.AllOperationDTO;
import com.runwaysdk.system.CharacterOperationDTO;
import com.runwaysdk.system.metadata.CharacterConditionDTO;
import com.runwaysdk.system.metadata.DateConditionDTO;
import com.runwaysdk.system.metadata.DoubleConditionDTO;
import com.runwaysdk.system.metadata.EQFieldConditionDTO;
import com.runwaysdk.system.metadata.FieldConditionDTO;
import com.runwaysdk.system.metadata.LongConditionDTO;
import com.runwaysdk.system.metadata.MdFieldDTO;
import com.runwaysdk.system.metadata.MdWebCharacterDTO;
import com.runwaysdk.system.metadata.MdWebDateDTO;
import com.runwaysdk.system.metadata.MdWebDoubleDTO;
import com.runwaysdk.system.metadata.MdWebFieldDTO;
import com.runwaysdk.system.metadata.MdWebFormDTO;
import com.runwaysdk.system.metadata.MdWebLongDTO;
import com.runwaysdk.system.metadata.MdWebNumberDTO;
import com.runwaysdk.system.metadata.MdWebTextDTO;

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

  public static final String CONFIRM_DELETE_FORM_JSP  = JSP_DIR + "editFormAttributes.jsp";

  public static final String CONFIRM_DELETE_MDFIELD_JSP  = JSP_DIR + "confirmDeleteMdField.jsp";

  public static final String CREATE_NEW_FORM_JSP       = JSP_DIR + "createNewForm.jsp";

  public static final String NEW_CONDITION_JSP       = JSP_DIR + "newCondition.jsp";

  public static final String CONDITIONS_LIST       = JSP_DIR + "conditionsList.jsp";

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
      // grab the appropriate MdField
      Class<?> klass = LoaderDecorator.load(mdFieldType + TypeGeneratorInfo.DTO_SUFFIX);

      // populate the new MdField instance
      BusinessDTO dto = (BusinessDTO) klass.getConstructor(ClientRequestIF.class).newInstance(this.getClientRequest());
      this.req.setAttribute("item", dto);

      // forward to the namespaced jsp
      this.forwardToFieldPage(mdFieldType, "createComponent.jsp");
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
      MdFormUtilDTO.createMdField(this.getClientRequest(), mdField, mdFormId);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void editMdField(String fieldId) throws IOException, ServletException
  {
    try
    {
      MdFieldDTO fieldDTO = MdFieldDTO.lock(this.getClientRequest(), fieldId);
      
      this.req.setAttribute("item", fieldDTO);
      
      this.forwardToFieldPage(fieldDTO.getType(), "editComponent.jsp");
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void cancelMdField(MdFieldDTO mdField) throws IOException, ServletException
  {
    try
    {
      mdField.unlock();
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void updateMdField(MdFieldDTO mdField) throws IOException, ServletException
  {
    try
    {
      MdFormUtilDTO.updateMdField(this.getClientRequest(), mdField);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  /**
   * Forwards to the proper namespaced for a given field type.
   * 
   * @param type
   * @param page
   * @throws IOException 
   * @throws ServletException 
   */
  private void forwardToFieldPage(String type, String page) throws ServletException, IOException
  {
    String pck = type.replaceAll("\\.", "/");
    String createJSP = "WEB-INF" + "/" + pck + "/" + page;

    this.req.getRequestDispatcher(createJSP).forward(req, resp);
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
      MdWebFormDTO applied = MdFormUtilDTO.apply(getClientRequest(), form);
      req.setAttribute("form", applied);

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
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  public void deleteForm(String formId) throws IOException, ServletException
  {
    try
    {
      ClientRequestIF clientRequest = getClientRequest();
      MdWebFormDTO form = MdWebFormDTO.get(clientRequest, formId);
      MdFormUtilDTO.delete(clientRequest, form);
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
      MdWebFieldDTO fieldToDelete = MdWebFieldDTO.get(clientRequest, fieldId);
            
      MdFormUtilDTO.deleteField(clientRequest, form, fieldToDelete);
      
      //MdWebFieldDTO[] fields = MdFormUtilDTO.getFields(clientRequest, form);
      //req.setAttribute("fields", fields);
      
      //this.req.getRequestDispatcher(FETCH_FORM_FIELDS_JSP).forward(req, resp);
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
    try {
      form.unlock();
      this.fetchFormAttributes(form.getId());
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  public void failCancel(MdWebFormDTO form) throws IOException, ServletException
  {
    this.edit(form.getId());
  }
  
  @Override
  public void getConditionList(String mdFieldId) throws IOException, ServletException
  {
    try
    {
      MdWebFieldDTO field = MdWebFieldDTO.get(this.getClientRequest(), mdFieldId);
      FieldConditionDTO cond = field.getFieldCondition();
      if(cond == null)
      {
        
      }
      else
      {
        // traverse the conditions one dimension deep (beyond that is not supported in the UI)
      }
      
      MdFieldDTO[] fields = MdFormUtilDTO.getFieldsForConditions(this.getClientRequest(), mdFieldId);
      req.setAttribute("fields", fields);
      
      this.req.getRequestDispatcher(CONDITIONS_LIST).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void newCondition(String mdFieldId) throws IOException, ServletException
  {
    try
    {
      // FIXME clean this up and define IFs on MdWebFieldDTO to handle creating new conditions.
      MdWebFieldDTO mdField = MdWebFieldDTO.get(this.getClientRequest(), mdFieldId);
      FieldConditionDTO condition = null;
      if(mdField instanceof MdWebDateDTO)
      {
        DateConditionDTO c = new DateConditionDTO(this.getClientRequest());
        this.req.setAttribute("operations", AllOperationDTO.allItems(this.getClientRequest()));
        condition = c;
      }
      else if(mdField instanceof MdWebCharacterDTO || mdField instanceof MdWebTextDTO)
      {
        CharacterConditionDTO c = new CharacterConditionDTO(this.getClientRequest());
        this.req.setAttribute("operations", CharacterOperationDTO.allItems(this.getClientRequest()));
        condition = c;
      }
      else if(mdField instanceof MdWebDoubleDTO)
      {
        DoubleConditionDTO c = new DoubleConditionDTO(this.getClientRequest());
        this.req.setAttribute("operations", AllOperationDTO.allItems(this.getClientRequest()));
        condition = c;
      }
      else if(mdField instanceof MdWebLongDTO)
      {
        LongConditionDTO c = new LongConditionDTO(this.getClientRequest());
        this.req.setAttribute("operations", AllOperationDTO.allItems(this.getClientRequest()));
        condition = c;
      }
      else if(mdField instanceof MdWebNumberDTO)
      {
//        NumberConditionDTO c = new NumberConditionDTO(this.getClientRequest());
//        this.req.setAttribute("operations", AllOperationDTO.allItems(this.getClientRequest()));
//        condition = c;
      }
      else
      {
        EQFieldConditionDTO c = new EQFieldConditionDTO(this.getClientRequest());
//        this.req.setAttribute("operations", AllOperationDTO.EQ);
        condition = c;
      }
      
      this.req.setAttribute("definingMdField", mdFieldId);
      this.req.setAttribute("condition", condition);
      
      this.req.getRequestDispatcher(NEW_CONDITION_JSP).forward(req, resp);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void createCondition(String mdFieldId, FieldConditionDTO condition) throws IOException,
      ServletException
  {
    try
    {
      
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void cancelCondition(FieldConditionDTO condition) throws IOException, ServletException
  {
    try
    {
      
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
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
