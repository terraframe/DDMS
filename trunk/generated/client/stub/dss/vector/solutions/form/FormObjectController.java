package dss.vector.solutions.form;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;

import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ComponentDTOFacade;
import com.runwaysdk.business.MutableDTO;
import com.runwaysdk.business.ProblemDTOIF;
import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.constants.Constants;
import com.runwaysdk.constants.MdAttributeBooleanUtil;
import com.runwaysdk.constants.TypeGeneratorInfo;
import com.runwaysdk.controller.BooleanParseProblemDTO;
import com.runwaysdk.controller.CharacterParseProblemDTO;
import com.runwaysdk.controller.DateParseProblemDTO;
import com.runwaysdk.controller.DecimalParseProblemDTO;
import com.runwaysdk.controller.IntegerParseExceptionDTO;
import com.runwaysdk.controller.StringParseException;
import com.runwaysdk.form.FormObject;
import com.runwaysdk.form.field.FieldIF;
import com.runwaysdk.form.web.JSONFormVisitor;
import com.runwaysdk.form.web.WebFormObject;
import com.runwaysdk.form.web.field.WebAttribute;
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
import com.runwaysdk.transport.attributes.AttributeDTO;
import com.runwaysdk.transport.conversion.ConversionExceptionDTO;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

import dss.vector.solutions.general.DiseaseDTO;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;

public class FormObjectController extends FormObjectControllerBase implements
    com.runwaysdk.generation.loader.Reloadable
{
  private static final long   serialVersionUID = 2036299192;

  private static final String JSP_DIR          = "/WEB-INF/forms/";

  private static final String FORM_GENERATOR   = JSP_DIR + "generator.jsp";

  public FormObjectController(javax.servlet.http.HttpServletRequest req,
      javax.servlet.http.HttpServletResponse resp, java.lang.Boolean isAsynchronous)
  {
    super(req, resp, isAsynchronous);
  }

  @Override
  public void formGenerator(String mdFormId) throws IOException, ServletException
  {
    try
    {
      this.req.setAttribute("mdFormId", mdFormId);

      MdFormDTO mdForm = MdFormDTO.get(this.getClientRequest(), mdFormId);
      MdClassDTO mdClass = mdForm.getFormMdClass();

      String type = mdClass.getPackageName() + "." + mdClass.getTypeName();
      this.req.setAttribute("mdClassType", type);

      String mdFormType = mdForm.getPackageName() + "." + mdForm.getTypeName();
      this.req.setAttribute("mdFormType", mdFormType);

      this.req.getRequestDispatcher(FORM_GENERATOR).forward(req, resp);
    }
    catch (Throwable t)
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
      String type = mdClass.getPackageName() + "." + mdClass.getTypeName()
          + TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);

      klass.getMethod("lock", ClientRequestIF.class, String.class).invoke(null, this.getClientRequest(),
          dataId);

      WebFormObject formObject = WebFormObject.getInstance(mdFormDTO, dataId);

      this.convertToJSON(formObject);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  @Override
  public void cancelInstance(FormObject formObject) throws IOException, ServletException
  {
    try
    {
      if (!formObject.isNewInstance())
      {
        MdFormDTO mdFormDTO = MdFormDTO.get(getClientRequest(), formObject.getMd().getId());
        MdClassDTO mdClass = mdFormDTO.getFormMdClass();
        String type = mdClass.getPackageName() + "." + mdClass.getTypeName()
            + TypeGeneratorInfo.DTO_SUFFIX;
        Class<?> klass = LoaderDecorator.load(type);

        klass.getMethod("unlock", ClientRequestIF.class, String.class).invoke(null,
            this.getClientRequest(), formObject.getDataId());

        WebFormObject webFormObject = WebFormObject.getInstance(mdFormDTO, formObject.getDataId());

        this.convertToJSON(webFormObject);
      }
    }
    catch (Throwable t)
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
    catch (Throwable t)
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
      String type = mdClass.getPackageName() + "." + mdClass.getTypeName()
          + TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);

      MutableDTO dto = (MutableDTO) klass.getMethod("get", ClientRequestIF.class, String.class).invoke(
          null, this.getClientRequest(), dataId);
      klass.getMethod("delete").invoke(dto);
    }
    catch (Throwable t)
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
      String type = mdClass.getPackageName() + "." + mdClass.getTypeName()
          + TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);

      MutableDTO dto = (MutableDTO) klass.getMethod("get", ClientRequestIF.class, String.class).invoke(
          null, this.getClientRequest(), formObject.getDataId());
      this.populate(formObject, dto);
      klass.getMethod("apply").invoke(dto);

      WebFormObject applied = WebFormObject.getInstance(mdFormDTO, dto.getId());

      this.convertToJSON(applied);
    }
    catch (Throwable t)
    {
      if (t instanceof InvocationTargetException)
      {
        t = t.getCause();
      }
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

  /**
   * Populates the given MutableDTO with the values from the FormObject.
   * @param formObject
   * @param dto
   * @param klass
   * @throws Throwable
   */
  private void populate(FormObject formObject, MutableDTO dto) throws Throwable
  {
    Map<String, AttributeDTO> mdIdToAttrDTOs = ComponentDTOFacade.mapMdAttributeIdToAttributeDTOs(dto);

    FieldIF[] fields = formObject.getFields();
    List<ProblemDTOIF> problems = new LinkedList<ProblemDTOIF>();

    Class<?> klass = dto.getClass();
    Locale locale = req.getLocale();

    for (FieldIF field : fields)
    {
      String setterAttr;
      AttributeMdDTO attributeMdDTO;
      if (field instanceof WebAttribute)
      {
        String mdAttrId = ( (WebAttribute) field ).getFieldMd().getDefiningMdAttribute();

        attributeMdDTO = mdIdToAttrDTOs.get(mdAttrId).getAttributeMdDTO();
        setterAttr = attributeMdDTO.getName();
      }
      else
      {
        continue;
        //String msg = "The field [" + field.getFieldName() + "] of type [" + field.getClass().getName()
            //+ "] is not supported at this time.";
        //throw new ConversionExceptionDTO(msg);
      }

      String setter = "set" + CommonGenerationUtil.upperFirstCharacter(setterAttr);
      Object o;
      Method m;
      if (field instanceof WebBoolean)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, Boolean.class);
          o = MdAttributeBooleanUtil.getTypeSafeValue(value);
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new BooleanParseProblemDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else if (field instanceof WebCharacter || field instanceof WebText)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, String.class);
          m.invoke(dto, value);
        }
        catch (StringParseException e)
        {
          problems
              .add(new CharacterParseProblemDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else if (field instanceof WebDate)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, Date.class);
          o = new DefaultConverter(Date.class).parse(value, req.getLocale());
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new DateParseProblemDTO(dto, attributeMdDTO, locale, value, Constants.DATE_FORMAT));
        }
      }
      else if (field instanceof WebInteger)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, Integer.class);
          o = new DefaultConverter(Integer.class).parse(value, req.getLocale());
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new IntegerParseExceptionDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else if (field instanceof WebLong)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, Long.class);
          o = new DefaultConverter(Long.class).parse(value, req.getLocale());
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new IntegerParseExceptionDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else if (field instanceof WebFloat)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, Float.class);
          o = new DefaultConverter(Float.class).parse(value, req.getLocale());
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new DecimalParseProblemDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else if (field instanceof WebDouble)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, Double.class);
          o = new DefaultConverter(Double.class).parse(value, req.getLocale());
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new DecimalParseProblemDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else if (field instanceof WebDecimal)
      {
        String value = field.getValue();
        try
        {
          m = klass.getMethod(setter, BigDecimal.class);
          o = new DefaultConverter(BigDecimal.class).parse(value, req.getLocale());
          m.invoke(dto, o);
        }
        catch (StringParseException e)
        {
          problems
              .add(new DecimalParseProblemDTO(dto, attributeMdDTO, locale, value));
        }
      }
      else
      {
        o = field.getValue();
        dto.setValue(field.getFieldName(), o);
      }
    }
    
    // throw an exception if we have any problems
    if(problems.size() > 0)
    {
      String msg = "Problems have occurred while submitting the form ["+formObject.getMd().getDisplayLabel()+"].";
      throw new ProblemExceptionDTO(msg, problems);
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
      String type = mdClass.getPackageName() + "." + mdClass.getTypeName()
          + TypeGeneratorInfo.DTO_SUFFIX;
      Class<?> klass = LoaderDecorator.load(type);

      MutableDTO dto = (MutableDTO) klass.getConstructor(ClientRequestIF.class).newInstance(
          this.getClientRequest());
      this.populate(formObject, dto);
      klass.getMethod("apply").invoke(dto);

      WebFormObject applied = WebFormObject.getInstance(mdFormDTO, dto.getId());

      this.convertToJSON(applied);
    }
    catch (Throwable t)
    {
      if (t instanceof InvocationTargetException)
      {
        t = t.getCause();
      }
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
