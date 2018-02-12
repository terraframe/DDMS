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
package dss.vector.solutions.form;

@com.runwaysdk.business.ClassSignature(hash = -655385477)
public class FormObjectControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  public static final String CLASS = "dss.vector.solutions.form.FormObjectController";
  protected javax.servlet.http.HttpServletRequest req;
  protected javax.servlet.http.HttpServletResponse resp;
  protected java.lang.Boolean isAsynchronous;
  protected java.lang.String dir;
  protected java.lang.String layout;
  
  public FormObjectControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    this(req, resp, isAsynchronous, "","");
  }
  
  public FormObjectControllerBase(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous, java.lang.String dir, java.lang.String layout)
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
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:formObject", post=true)
  public void cancelInstance(com.runwaysdk.form.FormObject formObject) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.cancelInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:formObject", post=true)
  public void failCancelInstance(com.runwaysdk.form.FormObject formObject) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failCancelInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:formObject", post=true)
  public void createInstance(com.runwaysdk.form.FormObject formObject) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.createInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:formObject", post=true)
  public void failCreateInstance(com.runwaysdk.form.FormObject formObject) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failCreateInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:criteria, java.lang.String:mdFormId", post=true)
  public void createNewInstance(com.runwaysdk.form.FormObject criteria, java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.createNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:criteria, java.lang.String:mdFormId", post=true)
  public void failCreateNewInstance(com.runwaysdk.form.FormObject criteria, java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failCreateNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:criteria, java.lang.String:type", post=true)
  public void deleteAll(com.runwaysdk.form.FormObject criteria, java.lang.String type) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.deleteAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:criteria, java.lang.String:type", post=true)
  public void failDeleteAll(com.runwaysdk.form.FormObject criteria, java.lang.String type) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failDeleteAll");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId, java.lang.String:dataId", post=true)
  public void deleteInstance(java.lang.String mdFormId, java.lang.String dataId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.deleteInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId, java.lang.String:dataId", post=true)
  public void failDeleteInstance(java.lang.String mdFormId, java.lang.String dataId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failDeleteInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId, java.lang.String:dataId", post=true)
  public void editInstance(java.lang.String mdFormId, java.lang.String dataId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.editInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId, java.lang.String:dataId", post=true)
  public void failEditInstance(java.lang.String mdFormId, java.lang.String dataId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failEditInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=false)
  public void formGenerator(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.formGenerator");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=false)
  public void failFormGenerator(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failFormGenerator");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formKey", post=false)
  public void formGeneratorKey(java.lang.String formKey) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.formGeneratorKey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:formKey", post=false)
  public void failFormGeneratorKey(java.lang.String formKey) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failFormGeneratorKey");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=true)
  public void newInstance(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.newInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=true)
  public void failNewInstance(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failNewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=true)
  public void searchForm(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.searchForm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId", post=true)
  public void failSearchForm(java.lang.String mdFormId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failSearchForm");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:criteria, java.lang.String:type, java.lang.String:sortAttribute, java.lang.Boolean:isAscending, java.lang.Integer:pageSize, java.lang.Integer:pageNumber", post=true)
  public void searchInstance(com.runwaysdk.form.FormObject criteria, java.lang.String type, java.lang.String sortAttribute, java.lang.Boolean isAscending, java.lang.Integer pageSize, java.lang.Integer pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.searchInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:criteria, java.lang.String:type, java.lang.String:sortAttribute, java.lang.String:isAscending, java.lang.String:pageSize, java.lang.String:pageNumber", post=true)
  public void failSearchInstance(com.runwaysdk.form.FormObject criteria, java.lang.String type, java.lang.String sortAttribute, java.lang.String isAscending, java.lang.String pageSize, java.lang.String pageNumber) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failSearchInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:formObject", post=true)
  public void updateInstance(com.runwaysdk.form.FormObject formObject) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.updateInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="com.runwaysdk.form.FormObject:formObject", post=true)
  public void failUpdateInstance(com.runwaysdk.form.FormObject formObject) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failUpdateInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId, java.lang.String:dataId", post=true)
  public void viewInstance(java.lang.String mdFormId, java.lang.String dataId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.viewInstance");
  }
  
  @com.runwaysdk.controller.ActionParameters(parameters="java.lang.String:mdFormId, java.lang.String:dataId", post=true)
  public void failViewInstance(java.lang.String mdFormId, java.lang.String dataId) throws java.io.IOException, javax.servlet.ServletException
  {
    String msg = "This method should never be invoked.  It should be overwritten in dss.vector.solutions.form.FormObjectController.java";
    throw new com.runwaysdk.controller.UndefinedControllerActionException(msg, req.getLocale(), "dss.vector.solutions.form.FormObjectController.failViewInstance");
  }
  
}
