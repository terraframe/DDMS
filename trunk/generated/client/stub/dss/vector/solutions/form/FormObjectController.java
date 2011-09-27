package dss.vector.solutions.form;

import java.io.IOException;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.form.FormObject;
import com.runwaysdk.form.web.JSONFormVisitor;
import com.runwaysdk.form.web.WebFormObject;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdFormDTO;
import com.runwaysdk.web.json.JSONProblemExceptionDTO;
import com.runwaysdk.web.json.JSONRunwayExceptionDTO;

import dss.vector.solutions.util.ErrorUtility;

public class FormObjectController extends FormObjectControllerBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = 2036299192;
  
  private static final String JSP_DIR              = "/WEB-INF/forms/";
  
  private static final String FORM_GENERATOR = JSP_DIR+"generator.jsp";
  
  public FormObjectController(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }
  
  @Override
  public void formGenerator(String mdFormId) throws IOException, ServletException
  {
    try
    {
      MdFormDTO mdForm = MdFormDTO.get(this.getClientRequest(), mdFormId);
      MdClassDTO mdClass = mdForm.getFormMdClass();
      
      String type = mdClass.getPackageName()+"."+mdClass.getTypeName();
      this.req.setAttribute("mdClassType", type);
      
      this.req.getRequestDispatcher(FORM_GENERATOR).forward(req, resp);
    }
    catch(Throwable t)
    {
      boolean redirected = ErrorUtility.prepareThrowable(t, req, resp, this.isAsynchronous());

      if (!redirected)
      {
        this.failNewInstance(mdFormId);
      }
    }
  }
  
  @Override
  public void createInstance(FormObject formObject) throws IOException, ServletException
  {
    try
    {
      String mdFormId = formObject.getMd().getId();
      MdFormDTO mdFormDTO = MdFormDTO.get(this.getClientRequest(), mdFormId);
      WebFormObject form = (WebFormObject) WebFormObject.newInstance(mdFormDTO);
      
      // TODO apply the underlying DTO with the new values
      
      JSONFormVisitor visitor = new JSONFormVisitor();
      form.accept(visitor);
      
      JSONObject json = visitor.getJSON();
      resp.getWriter().print(json.toString());
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
  @Override
  public void newInstance(String mdFormId) throws IOException, ServletException
  {
    try
    {
      MdFormDTO mdFormDTO = MdFormDTO.get(this.getClientRequest(), mdFormId);
      WebFormObject form = (WebFormObject) WebFormObject.newInstance(mdFormDTO);
      
      JSONFormVisitor visitor = new JSONFormVisitor();
      form.accept(visitor);
      
      JSONObject json = visitor.getJSON();
      resp.getWriter().print(json.toString());
    }
    catch(ProblemExceptionDTO e)
    {
      JSONProblemExceptionDTO jsonE = new JSONProblemExceptionDTO(e);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
    catch (Throwable t)
    {
      JSONRunwayExceptionDTO jsonE = new JSONRunwayExceptionDTO(t);
      resp.setStatus(500);
      resp.getWriter().print(jsonE.getJSON());
    }
  }
  
}
