package dss.vector.solutions.form;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.MdAttributeBooleanUtil;
import com.runwaysdk.constants.MdAttributeDateUtil;
import com.runwaysdk.constants.MdAttributeDecimalUtil;
import com.runwaysdk.constants.MdAttributeDoubleUtil;
import com.runwaysdk.constants.MdAttributeFloatUtil;
import com.runwaysdk.constants.MdAttributeIntegerUtil;
import com.runwaysdk.constants.MdAttributeLongUtil;
import com.runwaysdk.constants.TypeGeneratorInfo;
import com.runwaysdk.form.FormObject;
import com.runwaysdk.form.field.FieldIF;
import com.runwaysdk.form.web.JSONFormVisitor;
import com.runwaysdk.form.web.WebFormObject;
import com.runwaysdk.form.web.field.WebBoolean;
import com.runwaysdk.form.web.field.WebCharacter;
import com.runwaysdk.form.web.field.WebDate;
import com.runwaysdk.form.web.field.WebDecimal;
import com.runwaysdk.form.web.field.WebDouble;
import com.runwaysdk.form.web.field.WebFloat;
import com.runwaysdk.form.web.field.WebInteger;
import com.runwaysdk.form.web.field.WebLong;
import com.runwaysdk.form.web.field.WebText;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdFormDTO;

import dss.vector.solutions.general.DiseaseDTO;
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
      this.req.setAttribute("mdFormId", mdFormId);
      
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
  public void editInstance(String mdFormId, String dataId) throws IOException, ServletException
  {
    try
    {
      MdFormDTO mdFormDTO = MdFormDTO.get(getClientRequest(), mdFormId);
      MdClassDTO mdClass = mdFormDTO.getFormMdClass();
      String type = mdClass.getPackageName()+"."+mdClass.getTypeName()+TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);

      klass.getMethod("lock").invoke(null, dataId);

      WebFormObject formObject = WebFormObject.getInstance(mdFormDTO, dataId);
      
      this.convertToJSON(formObject);      
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);      
    }
  }
  
  @Override
  public void cancelInstance(FormObject formObject) throws IOException, ServletException
  {
    try
    {
      if(!formObject.isNewInstance())
      {
        MdFormDTO mdFormDTO = MdFormDTO.get(getClientRequest(), formObject.getMd().getId());
        MdClassDTO mdClass = mdFormDTO.getFormMdClass();
        String type = mdClass.getPackageName()+"."+mdClass.getTypeName()+TypeGeneratorInfo.DTO_SUFFIX;
        Class<?> klass = LoaderDecorator.load(type);

        klass.getMethod("unlock").invoke(null, formObject.getDataId());

        WebFormObject webFormObject = WebFormObject.getInstance(mdFormDTO, formObject.getId());
        
        this.convertToJSON(webFormObject);   
      }
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);      
    }
  }
  
  @Override
  public void viewInstance(String mdFormId, String dataId) throws IOException, ServletException
  {
    try
    {
      MdFormDTO mdFormDTO = MdFormDTO.get(this.getClientRequest(), mdFormId);
      WebFormObject formObject = WebFormObject.getInstance(mdFormDTO, dataId);
      
      this.convertToJSON(formObject);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);      
    }
  }
  
  @Override
  public void deleteInstance(String mdFormId, String dataId) throws IOException, ServletException
  {
    try
    {
      MdFormDTO mdFormDTO = MdFormDTO.get(getClientRequest(), mdFormId);
      MdClassDTO mdClass = mdFormDTO.getFormMdClass();
      String type = mdClass.getPackageName()+"."+mdClass.getTypeName()+TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);

      klass.getMethod("delete").invoke(null, dataId);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);      
    }
  }
  
  @Override
  public void updateInstance(FormObject formObject) throws IOException, ServletException
  {
    try
    {
      String mdFormId = formObject.getMd().getId();
      MdFormDTO mdFormDTO = MdFormDTO.get(this.getClientRequest(), mdFormId);
      
      MdClassDTO mdClass = mdFormDTO.getFormMdClass();
      String type = mdClass.getPackageName()+"."+mdClass.getTypeName()+TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);
      
      MutableDTO dto = (MutableDTO) klass.getMethod("get").invoke(null, formObject.getDataId());
      this.populate(formObject, dto, klass);
      klass.getMethod("apply").invoke(dto);
      
      WebFormObject applied = WebFormObject.getInstance(mdFormDTO, dto.getId());
      
      this.convertToJSON(applied);
    }
    catch(Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);      
    }
  }
  
  private void convertToJSON(WebFormObject formObject) throws IOException
  {
    JSONFormVisitor visitor = new JSONFormVisitor();
    formObject.accept(visitor);
    
    JSONObject json = visitor.getJSON();
    resp.getWriter().print(json.toString());
  }
  
  private void populate(FormObject formObject, MutableDTO dto, Class<?> klass) throws Throwable
  {
    FieldIF[] fields = formObject.getFields();
    for(FieldIF field : fields)
    {
      String setter = "set" + CommonGenerationUtil.upperFirstCharacter(field.getFieldName());
      Object o;
      Method m;
      if(field instanceof WebBoolean)
      {
        m = klass.getMethod(setter, Boolean.class);
        o = MdAttributeBooleanUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);
      }
      else if(field instanceof WebCharacter || field instanceof WebText)
      {
        m = klass.getMethod(setter, String.class);
        o = field.getValue();
        m.invoke(dto, o);
      }
      else if(field instanceof WebDate)
      {
        m = klass.getMethod(setter, Date.class);
        o = MdAttributeDateUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);
      }
      else if(field instanceof WebInteger)
      {
        m = klass.getMethod(setter, Integer.class);
        o = MdAttributeIntegerUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);          
      }
      else if(field instanceof WebLong)
      {
        m = klass.getMethod(setter, Long.class);
        o = MdAttributeLongUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);          
      }
      else if(field instanceof WebFloat)
      {
        m = klass.getMethod(setter, Float.class);
        o = MdAttributeFloatUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);          
      }
      else if(field instanceof WebDouble)
      {
        m = klass.getMethod(setter, Double.class);
        o = MdAttributeDoubleUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);          
      }
      else if(field instanceof WebDecimal)
      {
        m = klass.getMethod(setter, BigDecimal.class);
        o = MdAttributeDecimalUtil.getTypeSafeValue(field.getValue());
        m.invoke(dto, o);          
      }
      else
      {
        o = field.getValue();
        dto.setValue(field.getFieldName(), o);
      }
      
    }
    
    DiseaseDTO d = DiseaseDTO.getCurrent(this.getClientRequest());
    dto.setValue("disease", d.getId()); // FIXME create MdFormUtilInfo.DISEASE
  }
  
  @Override
  public void createInstance(FormObject formObject) throws IOException, ServletException
  {
    try
    {
      String mdFormId = formObject.getMd().getId();
      MdFormDTO mdFormDTO = MdFormDTO.get(this.getClientRequest(), mdFormId);
      
      MdClassDTO mdClass = mdFormDTO.getFormMdClass();
      String type = mdClass.getPackageName()+"."+mdClass.getTypeName()+TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);
      
      MutableDTO dto = (MutableDTO) klass.getConstructor(ClientRequestIF.class).newInstance(this.getClientRequest());
      this.populate(formObject, dto, klass);
      klass.getMethod("apply").invoke(dto);
      
      WebFormObject applied = WebFormObject.getInstance(mdFormDTO, dto.getId());
      
      this.convertToJSON(applied);
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
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
  
}
