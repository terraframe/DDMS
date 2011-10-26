package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = 1623574998)
public class MdFormAdminControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.form.MdFormAdminController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  private static final long serialVersionUID = 1623574998;
  
  public MdFormAdminControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public MdFormAdminControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
  {
    this.req = req;
    this.resp = resp;
    this.isAsynchronous = isAsynchronous;
    this.dir = dir;
    this.layout = layout;
  }
  
  protected void render(String jsp) throws java.io.IOException, javax.servlet.ServletException
  {
    if(!resp.isCommitted())
    {
      if(this.isAsynchronous())
      {
        req.getRequestDispatcher(dir+jsp).forward(req, resp);
      }
      else
      {
        req.setAttribute(com.runwaysdk.controller.JSPFetcher.INNER_JSP, dir+jsp);
        req.getRequestDispatcher(layout).forward(req, resp);
      }
    }
  }
  
  public javax.servlet.http.HttpServletRequest getRequest()
  {
    return this.req;
  }
  
  public javax.servlet.http.HttpServletResponse getResponse()
  {
    return this.resp;
  }
  
  public java.lang.Boolean isAsynchronous()
  {
    return this.isAsynchronous;
  }
  
  public com.runwaysdk.constants.ClientRequestIF getClientRequest()
  {
    return (com.runwaysdk.constants.ClientRequestIF) req.getAttribute(com.runwaysdk.constants.ClientConstants.CLIENTREQUEST);
  }
  
  public com.runwaysdk.ClientSession getClientSession()
  {
    return (com.runwaysdk.ClientSession) req.getSession().getAttribute(com.runwaysdk.constants.ClientConstants.CLIENTSESSION);
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void addCondition(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.addCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void failAddCondition(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failAddCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void availableFields() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.availableFields");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failAvailableFields() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failAvailableFields");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void cancel(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.cancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void failCancel(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failCancel");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.FieldConditionDTO:condition", post=true)
  public void cancelCondition(com.runwaysdk.system.metadata.FieldConditionDTO condition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.cancelCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.FieldConditionDTO:condition", post=true)
  public void failCancelCondition(com.runwaysdk.system.metadata.FieldConditionDTO condition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failCancelCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdFieldDTO:mdField", post=true)
  public void cancelMdField(com.runwaysdk.system.metadata.MdFieldDTO mdField) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.cancelMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdFieldDTO:mdField", post=true)
  public void failCancelMdField(com.runwaysdk.system.metadata.MdFieldDTO mdField) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failCancelMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void create(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.create");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void failCreate(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failCreate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId, com.runwaysdk.system.metadata.FieldConditionDTO:condition", post=true)
  public void createCondition(java.lang.String mdFieldId, com.runwaysdk.system.metadata.FieldConditionDTO condition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.createCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId, com.runwaysdk.system.metadata.FieldConditionDTO:condition", post=true)
  public void failCreateCondition(java.lang.String mdFieldId, com.runwaysdk.system.metadata.FieldConditionDTO condition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failCreateCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdFieldDTO:mdField, java.lang.String:mdFormId", post=true)
  public void createMdField(com.runwaysdk.system.metadata.MdFieldDTO mdField, java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.createMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdFieldDTO:mdField, java.lang.String:mdFormId", post=true)
  public void failCreateMdField(com.runwaysdk.system.metadata.MdFieldDTO mdField, java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failCreateMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void delete(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.delete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void failDelete(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failDelete");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId, java.lang.String:conditionId", post=true)
  public void deleteCondition(java.lang.String mdFieldId, java.lang.String conditionId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.deleteCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId, java.lang.String:conditionId", post=true)
  public void failDeleteCondition(java.lang.String mdFieldId, java.lang.String conditionId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failDeleteCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId, java.lang.String:fieldId", post=true)
  public void deleteField(java.lang.String formId, java.lang.String fieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.deleteField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId, java.lang.String:fieldId", post=true)
  public void failDeleteField(java.lang.String formId, java.lang.String fieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failDeleteField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=true)
  public void deleteForm(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.deleteForm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=true)
  public void failDeleteForm(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failDeleteForm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void edit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.edit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=true)
  public void failEdit(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failEdit");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:conditionId", post=true)
  public void editCondition(java.lang.String conditionId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.editCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:conditionId", post=true)
  public void failEditCondition(java.lang.String conditionId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failEditCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId", post=true)
  public void editFormAttributes(java.lang.String formId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.editFormAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId", post=true)
  public void failEditFormAttributes(java.lang.String formId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failEditFormAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void editMdField(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.editMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void failEditMdField(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failEditMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void existingForms() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.existingForms");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=true)
  public void failExistingForms() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failExistingForms");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId", post=true)
  public void fetchFormAttributes(java.lang.String formId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.fetchFormAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId", post=true)
  public void failFetchFormAttributes(java.lang.String formId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failFetchFormAttributes");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId", post=true)
  public void fetchFormFields(java.lang.String formId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.fetchFormFields");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formId", post=true)
  public void failFetchFormFields(java.lang.String formId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failFetchFormFields");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void getConditionList(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.getConditionList");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void failGetConditionList(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failGetConditionList");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void getConditions(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.getConditions");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void failGetConditions(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failGetConditions");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void mdFormAdmin() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.mdFormAdmin");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failMdFormAdmin() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failMdFormAdmin");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void newCondition(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.newCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldId", post=true)
  public void failNewCondition(java.lang.String mdFieldId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failNewCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void newInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failNewInstance() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldType", post=false)
  public void newMdField(java.lang.String mdFieldType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.newMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFieldType", post=false)
  public void failNewMdField(java.lang.String mdFieldType) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failNewMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void update(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.update");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdWebFormDTO:form", post=true)
  public void failUpdate(com.runwaysdk.system.metadata.MdWebFormDTO form) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failUpdate");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.FieldConditionDTO:condition", post=true)
  public void updateCondition(com.runwaysdk.system.metadata.FieldConditionDTO condition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.updateCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.FieldConditionDTO:condition", post=true)
  public void failUpdateCondition(com.runwaysdk.system.metadata.FieldConditionDTO condition) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failUpdateCondition");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdFieldDTO:mdField", post=true)
  public void updateMdField(com.runwaysdk.system.metadata.MdFieldDTO mdField) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.updateMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.system.metadata.MdFieldDTO:mdField", post=true)
  public void failUpdateMdField(com.runwaysdk.system.metadata.MdFieldDTO mdField) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failUpdateMdField");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void view(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.view");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:id", post=false)
  public void failView(java.lang.String id) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failView");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void viewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.viewAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="", post=false)
  public void failViewAll() throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.MdFormAdminController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.MdFormAdminController.failViewAll");
  }
  
}
