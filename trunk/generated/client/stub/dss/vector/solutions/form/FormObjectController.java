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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.BusinessDTO;
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
import com.runwaysdk.form.web.JSONWebFieldConstants;
import com.runwaysdk.form.web.WebFormObject;
import com.runwaysdk.form.web.field.WebAttribute;
import com.runwaysdk.form.web.field.WebBoolean;
import com.runwaysdk.form.web.field.WebCharacter;
import com.runwaysdk.form.web.field.WebDate;
import com.runwaysdk.form.web.field.WebDecimal;
import com.runwaysdk.form.web.field.WebDouble;
import com.runwaysdk.form.web.field.WebFloat;
import com.runwaysdk.form.web.field.WebGeo;
import com.runwaysdk.form.web.field.WebInteger;
import com.runwaysdk.form.web.field.WebLong;
import com.runwaysdk.form.web.field.WebMultipleTerm;
import com.runwaysdk.form.web.field.WebReference;
import com.runwaysdk.form.web.field.WebSingleTermGrid;
import com.runwaysdk.form.web.field.WebText;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdFormDTO;
import com.runwaysdk.system.metadata.MdWebMultipleTermDTO;
import com.runwaysdk.transport.attributes.AttributeDTO;
import com.runwaysdk.transport.metadata.AttributeMdDTO;

import dss.vector.solutions.general.DiseaseDTO;
import dss.vector.solutions.generator.GenericGridBuilder;
import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.geo.GeoFieldDTO;
import dss.vector.solutions.ontology.TermViewDTO;
import dss.vector.solutions.ontology.TermViewQueryDTO;
import dss.vector.solutions.util.DefaultConverter;
import dss.vector.solutions.util.ErrorUtility;
import dss.vector.solutions.util.yui.DataGrid;

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

      String formDisplayLabel = mdForm.getDisplayLabel().toString();
      this.req.setAttribute("localized_page_title", formDisplayLabel);

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

      this.convertToJSON(formObject, true);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }

  // private JSONObject getRootsForTermFields(WebFormObject formObject) throws
  // JSONException
  // {
  // // Add the browser roots manually
  // JSONObject rootsJSON = new JSONObject();
  // for(FieldIF field : formObject.getFields())
  // {
  // if(field instanceof WebSingleTerm || field instanceof WebMultipleTerm)
  // {
  // String mdAttributeId =
  // ((WebAttribute)field).getFieldMd().getDefiningMdAttribute();
  // MdAttributeReferenceDTO mdAttr =
  // MdAttributeReferenceDTO.get(this.getClientRequest(), mdAttributeId);
  //        
  // String clazz = mdAttr.getDefiningMdClassMd().getReferencedMdBusiness();
  // String name = mdAttr.getAttributeName();
  //        
  // BrowserRootViewDTO[] roots =
  // BrowserRootDTO.getAttributeRoots(this.getClientRequest(), clazz, name);
  // JSONArray rootsArr = new JSONArray();
  // for(BrowserRootViewDTO root : roots)
  // {
  // JSONObject rootJSON = new JSONObject();
  // rootJSON.put("termId", root.getTermId());
  // rootJSON.put("selectable", root.getSelectable());
  // rootJSON.put("type", clazz);
  // rootJSON.put("attribute", name);
  //          
  // rootsArr.put(rootJSON);
  // }
  // rootsJSON.put(field.getFieldName(), rootsArr);
  // }
  // }
  //    
  // return rootsJSON;
  // }

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

        this.convertToJSON(webFormObject, false);
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

      this.convertToJSON(formObject, false);
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

      BusinessDTO dto = (BusinessDTO) klass.getMethod("get", ClientRequestIF.class, String.class)
          .invoke(null, this.getClientRequest(), formObject.getDataId());

      JSONArray multipleTermJSON = new JSONArray();
      JSONArray singleTermGridJSON = new JSONArray();
      
      this.populate(formObject, dto, multipleTermJSON, singleTermGridJSON);

      dto = MdFormUtilDTO.persistObject(this.getClientRequest(), dto, multipleTermJSON.toString(), singleTermGridJSON.toString());

      WebFormObject applied = WebFormObject.getInstance(mdFormDTO, dto.getId());

      this.convertToJSON(applied, false);
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

  private void convertToJSON(WebFormObject formObject, boolean editMode) throws IOException,
      JSONException
  {
    JSONFormVisitor visitor = new JSONFormVisitor();
    formObject.accept(visitor);

    JSONObject json = visitor.getJSON();

    // HACK TIME: Because MdWebMultipleTerm creates metadata outside of Runway,
    // we have to
    // dereference those metadata instances here and create our own transient
    // data structures.
    JSONArray fieldsArr = json.getJSONArray(JSONWebFieldConstants.FIELDS);
    FieldIF[] fields = formObject.getFields();
    JSONObject terms = new JSONObject();
    for (int i = 0; i < fields.length; i++)
    {
      FieldIF field = fields[i];
      JSONObject fieldJSON = fieldsArr.getJSONObject(i);
      if (field instanceof WebMultipleTerm && !formObject.isNewInstance())
      {
        WebMultipleTerm mTerm = (WebMultipleTerm) field;
        String parentId = formObject.getDataId();

        MdWebMultipleTermDTO mdField = MdWebMultipleTermDTO.get(this.getClientRequest(), mTerm.getFieldMd().getId());
        TermViewQueryDTO query = MdFormUtilDTO.getTermsForMultiTermField(this.getClientRequest(), mdField, parentId);

        for (TermViewDTO term : query.getResultSet())
        {
          terms.put(term.getTermId(), term.getTermName());
        }

        // put the term ids and display labels manually into the outgoing JSON
        // object.
        fieldJSON.put("terms", terms);
      }
      else if (editMode && field instanceof WebGeo)
      {
        GeoFieldDTO geoField = GeoFieldDTO.getGeoFieldForMdWebGeo(this.getClientRequest(), field.getFieldMd().getId());
        fieldJSON.put("geoField", geoField.convertToJSON().toString());
      }
      else if(field instanceof WebReference && !formObject.isNewInstance())
      {
        String refId = field.getValue();
        if(refId != null && refId.trim().length() > 0)
        {
          BusinessDTO ref = (BusinessDTO) this.getClientRequest().get(refId);
          fieldJSON.put("referenceDisplay", ref.getValue(BusinessDTO.KEYNAME));
        }
      }
      else if(field instanceof WebSingleTermGrid)
      {
        WebSingleTermGrid grid = (WebSingleTermGrid) field;
        GenericGridBuilder builder = new GenericGridBuilder(formObject, grid, this.getClientRequest(), !editMode);
        
        DataGrid dataGrid = builder.build();
        
        fieldJSON.put("grid", dataGrid.getJavascript());
      }
    }

    resp.getWriter().print(json.toString());
  }

  /**
   * Populates the given MutableDTO with the values from the FormObject.
   * 
   * @param formObject
   * @param dto
   * @param singleTermGridJSON 
   * @param multipleTermJSON 
   * @param klass
   * @throws Throwable
   */
  private void populate(FormObject formObject, MutableDTO dto, JSONArray multipleTermJSON, JSONArray singleTermGridJSON) throws Throwable
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
      if (field instanceof WebMultipleTerm)
      {
        WebMultipleTerm wmt = (WebMultipleTerm) field;

        // create the relationships between the parent/child objects.
        JSONArray arr = new JSONArray(field.getValue());

        JSONObject entry = new JSONObject();
        entry.put("mdField", field.getFieldMd().getId());
        entry.put("termIds", arr);

        multipleTermJSON.put(entry);

        // clear the field to avoid errors from the reference value being
        // invalid.
        wmt.setValue(null);
      }
      if(field instanceof WebSingleTermGrid)
      {
        WebSingleTermGrid grid = (WebSingleTermGrid) field;
        
        JSONObject entry = new JSONObject();
        entry.put("mdField", field.getFieldMd().getId());
        
        JSONArray arr = field.getValue() != null ? new JSONArray(field.getValue()) : new JSONArray();
        entry.put("rows", arr);
        
        singleTermGridJSON.put(entry);
        
        grid.setValue(null);
      }
      else if (field instanceof WebAttribute)
      {
        String mdAttrId = ( (WebAttribute) field ).getFieldMd().getDefiningMdAttribute();

        attributeMdDTO = mdIdToAttrDTOs.get(mdAttrId).getAttributeMdDTO();
        setterAttr = attributeMdDTO.getName();

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
            problems.add(new BooleanParseProblemDTO(dto, attributeMdDTO, locale, value));
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
            problems.add(new CharacterParseProblemDTO(dto, attributeMdDTO, locale, value));
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
            problems.add(new DateParseProblemDTO(dto, attributeMdDTO, locale, value,
                Constants.DATE_FORMAT));
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
            problems.add(new IntegerParseExceptionDTO(dto, attributeMdDTO, locale, value));
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
            problems.add(new IntegerParseExceptionDTO(dto, attributeMdDTO, locale, value));
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
            problems.add(new DecimalParseProblemDTO(dto, attributeMdDTO, locale, value));
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
            problems.add(new DecimalParseProblemDTO(dto, attributeMdDTO, locale, value));
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
            problems.add(new DecimalParseProblemDTO(dto, attributeMdDTO, locale, value));
          }
        }
        else
        {
          o = field.getValue();
          dto.setValue(field.getFieldName(), o);
        }
      }
      else
      {
        continue;
        // String msg = "The field [" + field.getFieldName() + "] of type [" +
        // field.getClass().getName()
        // + "] is not supported at this time.";
        // throw new ConversionExceptionDTO(msg);
      }
    }

    // throw an exception if we have any problems
    if (problems.size() > 0)
    {
      String msg = "Problems have occurred while submitting the form ["
          + formObject.getMd().getDisplayLabel() + "].";
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

      BusinessDTO dto = (BusinessDTO) klass.getConstructor(ClientRequestIF.class).newInstance(
          this.getClientRequest());

      JSONArray multipleTermJSON = new JSONArray();
      JSONArray singleTermGridJSON = new JSONArray();
      
      this.populate(formObject, dto, multipleTermJSON, singleTermGridJSON);

      dto = MdFormUtilDTO.persistObject(this.getClientRequest(), dto, multipleTermJSON.toString(), singleTermGridJSON.toString());

      WebFormObject applied = WebFormObject.getInstance(mdFormDTO, dto.getId());

      this.convertToJSON(applied, false);
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

      this.convertToJSON(form, true);
    }
    catch (Throwable t)
    {
      ErrorUtility.prepareAjaxThrowable(t, resp);
    }
  }
}
