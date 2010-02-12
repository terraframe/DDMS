package dss.vector.solutions.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.ApplicationException;
import com.terraframe.mojo.business.BusinessDTO;
import com.terraframe.mojo.business.ClassQueryDTO;
import com.terraframe.mojo.business.ComponentDTO;
import com.terraframe.mojo.business.ViewDTO;
import com.terraframe.mojo.business.generation.GenerationUtil;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.Constants;
import com.terraframe.mojo.controller.DTOFacade;
import com.terraframe.mojo.dataaccess.attributes.ClientReadAttributePermissionException;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.system.EnumerationMasterDTO;
import com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeDateMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO;

import dss.vector.solutions.LabeledDTO;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;

public class Halp implements com.terraframe.mojo.generation.loader.Reloadable
{

  public final static String CLASS           = "dss.vector.solutions.util.Halp";

  public final static String EMAIL_ERRORS_TO = "dtaylor@terraframe.com";

  public static String getDateFormatString(HttpServletRequest request)
  {
    Locale locale = request.getLocale();
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    SimpleDateFormat formatter = (SimpleDateFormat) df;
    String pattern = formatter.toPattern();
    pattern = pattern.replace("yy", "yyyy");
    pattern = pattern.replace("yyyyyyyy", "yyyy");
    request.setAttribute("dateFormatPattern", pattern);

    return pattern;
  }

  public static String getFormatedDate(HttpServletRequest request, Date date)
  {
    SimpleDateFormat formatter = new SimpleDateFormat(getDateFormatString(request));
    return formatter.format(date);
  }

  static enum MdType implements com.terraframe.mojo.generation.loader.Reloadable {
    DATE, ENUMERATION, REFERENCE, OTHER;
    public static MdType toType(String attributeType)
    {
      try
      {
        attributeType = attributeType.substring(attributeType.lastIndexOf(".MdAttribute") + 12).toUpperCase();
        return valueOf(attributeType);
      }
      catch (Exception ex)
      {
        return OTHER;
      }
    }
  }

  public static String join(List<String> s)
  {
    return Halp.join(s, ",");
  }

  public static String join(List<String> s, String delimiter)
  {
    StringBuilder builder = new StringBuilder();
    List<String> list = new LinkedList<String>();

    for (String string : s)
    {
      if (string != null)
      {
        list.add(string);
      }
    }

    Iterator<String> iterator = list.iterator();

    while (iterator.hasNext())
    {
      builder.append(iterator.next());
      if (iterator.hasNext())
      {
        builder.append(delimiter);
      }
    }

    return builder.toString();
  }

  public static String loadTypes(List<String> types)
  {
    StringBuilder builder = new StringBuilder();

    builder.append("<script type=\"text/javascript\" src=\"js/getClass.js.jsp?");
    builder.append(Halp.join(types, "&"));
    builder.append("\"></script>");

    return builder.toString();
  }

  private static String generateDropDownMap(LabeledDTO[] terms) throws JSONException
  {
    JSONObject map = new JSONObject();

    for (LabeledDTO term : terms)
    {
      map.put(term.getOptionId(), term.getLabel().replaceAll("'", "\\\\'"));
    }

    return map.toString();
  }

  public static String getDisplayLabels(LabeledDTO[] terms, String name) throws JSONException
  {
    JSONArray options = new JSONArray();

    for (LabeledDTO term : terms)
    {
      options.put(Halp.getLabeledPair(term.getLabel(), term.getOptionId()));
    }

    return name + "Options = " + options.toString() + ";";
  }

  public static String getDataMap(ViewDTO[] rows, String[] attribs, ViewDTO view) throws JSONException
  {
    return Halp.getDataMap(rows, attribs, view, new HashMap<String, RowSetup>());
  }

  public static String getDataMap(ViewDTO[] rows, String[] attribs, ViewDTO view, Map<String, RowSetup> setups) throws JSONException
  {
    JSONArray map = new JSONArray();
    ArrayList<String> ordered_attribs = new ArrayList<String>(Arrays.asList(attribs));
    for (String a : view.getAccessorNames())
    {
      if (!ordered_attribs.contains(a))
      {
        ordered_attribs.add(a.substring(0, 1).toUpperCase() + a.substring(1));
      }
    }

    for (ViewDTO row : rows)
    {
      JSONObject element = new JSONObject();

      for (String attrib : ordered_attribs)
      {
        try
        {
          Object object = null;

          if (setups.containsKey(attrib))
          {
            RowSetup setup = setups.get(attrib);

            Class<? extends ViewDTO> klass = row.getClass();

            object = klass.getMethod(setup.getGetter()).invoke(row);
          }
          else
          {
            object = new DTOFacade(attrib, row).getValue();
          }

          String value = object.toString();

          String attributeType = view.getAttributeType(attrib.substring(0, 1).toLowerCase() + attrib.substring(1));

          SimpleDateFormat df = new SimpleDateFormat(Constants.DATETIME_FORMAT);

          switch (Halp.MdType.toType(attributeType))
          {
            case DATE:
              value = df.format((Date) object);
              break;
            case ENUMERATION:
              // FIXME: this is a hack for enums
              value = value.replaceAll("\\[", "").replaceAll("\\]", "");
              break;
            case REFERENCE:
              ComponentDTO componentDTO = (ComponentDTO) ( object );

              if (componentDTO instanceof TermDTO)
              {
                TermDTO term = (TermDTO) componentDTO;

                value = Halp.getTermIdWithDisplayLabel(term);
              }
              else if (componentDTO instanceof LabeledDTO)
              {
                LabeledDTO labeled = (LabeledDTO) componentDTO;

                value = labeled.getOptionId();
              }
              else
              {
                value = componentDTO.getId();
              }

              break;
          }
          element.put(attrib, value);

        }
        catch (IllegalAccessException x)
        {
          //System.out.println(x + " " + x.getCause());
        }
        catch (IllegalArgumentException x)
        {
         // System.out.println(x + " " + x.getCause());
        }
        catch (InvocationTargetException x)
        {
          //System.out.println(x + " " + x.getCause());
        }
        catch (NoSuchMethodException x)
        {
          //System.out.println("No such method get" + attrib);
        }
        catch (NullPointerException x)
        {
          //System.out.println("Null Pointer Exception get" + attrib);
        } // catch (ParseException x) {
        // System.out.println("Could not parse date" + attrib);
        // }

      }
      map.put(element);
    }
    return map.toString();// .replaceAll(",", ",\n");
  }

  public static String getTermIdWithDisplayLabel(TermDTO term)
  {
    String label = term.getDisplayLabel();
    String id = term.getId();

    return label + "^^^^" + id;
  }

  private static JSONObject getLabeledPair(String label, String value)
  {
    JSONObject object = new JSONObject();
    try
    {
      object.put("label", label);
      object.put("value", value);
    }
    catch (JSONException e)
    {
      throw new RuntimeException(e);
    }

    return object;
  }

  public static String getDropdownSetup(ViewDTO view, String[] attribs, String extra_rows, ClientRequestIF clientRequest) throws JSONException
  {
    return Halp.getDropdownSetup(view, attribs, extra_rows, clientRequest, new HashMap<String, String>());
  }

  public static String getDropdownSetup(ViewDTO view, String[] attribs, String extra_rows, ClientRequestIF clientRequest, Map<String, String> map) throws JSONException
  {
    Class<?> v = view.getClass();
    ArrayList<String> ordered_attribs = new ArrayList<String>(Arrays.asList(attribs));

    ArrayList<String> dropdownbuff = new ArrayList<String>();
    for (String attrib : ordered_attribs)
    {
      try
      {
        AttributeMdDTO md = (AttributeMdDTO) v.getMethod("get" + attrib + "Md").invoke(view);

        if (md instanceof AttributeReferenceMdDTO)
        {
          Class<?> clazz = md.getJavaType();

          if (map.containsKey(attrib))
          {
            clazz = LoaderDecorator.load(map.get(attrib));
          }

          if (LabeledDTO.class.isAssignableFrom(clazz))
          {
            LabeledDTO[] terms = (LabeledDTO[]) clazz.getMethod("getAllActive", new Class[] { ClientRequestIF.class }).invoke(null, clientRequest);
            dropdownbuff.add(getDisplayLabels(terms, attrib));
          }
        }

      }
      catch (Exception x)
      {
        throw new RuntimeException(x);
      }
    }

    return ( Halp.join(dropdownbuff, "\n") );
  }

  public static String getDropDownMaps(ViewDTO view, ClientRequestIF clientRequest) throws JSONException
  {
    return Halp.getDropDownMaps(view, clientRequest, "\n");
  }

  public static String getDropDownMaps(ViewDTO view, ClientRequestIF clientRequest, String delimeter) throws JSONException
  {
    List<AttributeMdDTO> list = new LinkedList<AttributeMdDTO>();

    for (String attrib : view.getAccessorNames())
    {
      if (attrib.length() >= 3)
      {
        try
        {
          list.add(new DTOFacade(attrib, view).getAttributeMdDTO());
        }
        catch (Exception x)
        {
          throw new RuntimeException(x);
        }
      }
    }

    return Halp.getDropDownMaps(list, clientRequest, delimeter);
  }

  public static String getDropDownMaps(ClassQueryDTO query, ClientRequestIF clientRequest, String delimeter) throws JSONException
  {
    List<AttributeMdDTO> list = new LinkedList<AttributeMdDTO>();

    for (String attrib : query.getAttributeNames())
    {
      if (attrib.length() >= 3)
      {
        try
        {
          list.add(query.getAttributeDTO(attrib).getAttributeMdDTO());
        }
        catch (Exception x)
        {
          throw new RuntimeException(x);
        }
      }
    }

    return Halp.getDropDownMaps(list, clientRequest, delimeter);
  }

  public static String getDropDownMaps(List<AttributeMdDTO> list, ClientRequestIF request, String delimeter)
  {
    ArrayList<String> dropdownbuff = new ArrayList<String>();

    for (AttributeMdDTO md : list)
    {
      try
      {
        String map = Halp.generateDropDownMap(md, request);

        if (map != null)
        {
          String accessor = md.getAccessorName();
          String key = accessor.substring(0, 1).toUpperCase() + accessor.substring(1);

          dropdownbuff.add(key + " : " + map);
        }
      }
      catch (Exception x)
      {
        throw new RuntimeException(x);
      }
    }

    return ( Halp.join(dropdownbuff, delimeter) );
  }

  private static String generateDropDownMap(AttributeMdDTO md, ClientRequestIF request)
  {
    try
    {
      if (md instanceof AttributeReferenceMdDTO)
      {
        Class<?> clazz = md.getJavaType();

        if (LabeledDTO.class.isAssignableFrom(clazz))
        {
          Class<?>[] params = new Class[] { ClientRequestIF.class };
          Method method = clazz.getMethod("getAllActive", params);

          return generateDropDownMap((LabeledDTO[]) method.invoke(null, request));
        }
      }
      else if (md instanceof AttributeEnumerationMdDTO)
      {
        List<String> list = new LinkedList<String>();

        AttributeEnumerationMdDTO enumMd = (AttributeEnumerationMdDTO) md;
        String enumeration = enumMd.getReferencedMdEnumeration();

        for (BusinessDTO dto : request.getAllEnumerations(enumeration))
        {
          EnumerationMasterDTO e = (EnumerationMasterDTO) dto;

          list.add("'" + e.getId() + "':'" + e.getDisplayLabel().toString().replaceAll("'", "\\\\'") + "'");
        }

        return "{" + Halp.join(list) + "}";
      }
      else if (md instanceof AttributeBooleanMdDTO)
      {
        String positiveLabel = ( (AttributeBooleanMdDTO) md ).getPositiveDisplayLabel().replaceAll("'", "\\\\'");
        String negativeLabel = ( (AttributeBooleanMdDTO) md ).getNegativeDisplayLabel().replaceAll("'", "\\\\'");

        return "{'true':'" + positiveLabel + "', 'false':'" + negativeLabel + "'}";
      }

      return null;
    }
    catch (Exception x)
    {
      throw new RuntimeException(x);
    }
  }

  public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload) throws JSONException
  {
    return getColumnSetup(view, attribs, extra_rows, autoload, 1);
  }

  public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload, int num_to_hide) throws JSONException
  {
    ArrayList<Integer> no_edit_cols = new ArrayList<Integer>();
    ArrayList<Integer> no_show_cols = new ArrayList<Integer>();

    for (Integer i = 0; i < num_to_hide; i++)
    {
      no_edit_cols.add(i);
      no_show_cols.add(i);
    }

    return getColumnSetup(view, attribs, extra_rows, autoload, no_edit_cols, no_show_cols);
  }

  public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload, List<Integer> no_show, List<Integer> no_edit) throws JSONException
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    for (Integer i : no_show)
    {
      String key = attribs[i];

      ColumnSetup setup = new ColumnSetup();
      setup.setHidden(true);

      map.put(key, setup);
    }

    for (Integer i : no_edit)
    {
      String key = attribs[i];

      ColumnSetup setup = new ColumnSetup();

      if (map.containsKey(key))
      {
        setup = map.get(key);
      }

      setup.setEditable(false);

      map.put(attribs[i], setup);
    }

    return getColumnSetup(view, attribs, extra_rows, autoload, map);
  }

  public static String getColumnSetup(ViewDTO view, String[] attributes, String extra_rows, boolean autoload, Map<String, ColumnSetup> map) throws JSONException
  {
    List<String> list = Arrays.asList(attributes);

    List<String> columns = new ArrayList<String>();
    List<String> ordered = new ArrayList<String>(list);

    for (String accessorName : view.getAccessorNames())
    {
      String upcased_attrib = GenerationUtil.upperFirstCharacter(accessorName);

      if (!ordered.contains(upcased_attrib) && accessorName.length() >= 3 && autoload)
      {
        ordered.add(upcased_attrib);
      }

      if (!view.isReadable(accessorName))
      {
        ordered.remove(upcased_attrib);
      }
    }

    for (String attribute : ordered)
    {
      ColumnSetup setup = new ColumnSetup();

      if (map.containsKey(attribute))
      {
        setup = map.get(attribute);
      }

      String buffer = Halp.generateColumnMap(view, setup, attribute);

      columns.add("{" + buffer + "}");
    }

    if (extra_rows.length() > 0)
    {
      columns.add(extra_rows);
    }
    return ( "[" + Halp.join(columns, ",\n") + "]" );
  }

  public static String getEditorDefinitions(ViewDTO view, Map<String, ColumnSetup> map) throws JSONException
  {
    JSONObject json = new JSONObject();

    try
    {
      for (String attribute : view.getAccessorNames())
      {
        ColumnSetup setup = new ColumnSetup();

        if (map.containsKey(attribute))
        {
          setup = map.get(attribute);
        }
        String key = attribute.substring(0, 1).toUpperCase() + attribute.substring(1);

        AttributeMdDTO md = new DTOFacade(attribute, view).getAttributeMdDTO();

        String buffer = Halp.generateEditor(view, attribute, md, setup);
        buffer = buffer.replaceFirst("editor:", "");

        json.put(key, buffer);
      }
    }
    catch (Exception e)
    {

    }

    return json.toString();
  }

  private static String generateColumnMap(ViewDTO view, ColumnSetup setup, String attrib)
  {
    try
    {
      ArrayList<String> buff = new ArrayList<String>();
      AttributeMdDTO md = new DTOFacade(attrib, view).getAttributeMdDTO();

      String label = md.getDisplayLabel();

      if (setup.getLabel() != null)
      {
        label = setup.getLabel();
      }

      buff.add("key:'" + attrib + "'");
      buff.add("label:'" + label.replaceAll("'", "\\\\'") + "'");

      if (setup.isSum())
      {
        buff.add("sum:true");
      }

      if (setup.getTitle() != null)
      {
        buff.add("title:'" + setup.getTitle() + "'");
      }

      if (setup.isHidden())
      {
        buff.add("hidden:true");
      }
      else
      {
        buff.add(Halp.generateFormatter(md));
        buff.add(Halp.generateSaveFlag(md));

        if (setup.isEditable())
        {
          buff.add(Halp.generateEditor(view, attrib, md, setup));
        }
      }

      return Halp.join(buff);
    }
    catch (Exception e)
    {
      // TODO fix exception handling
      throw new RuntimeException(e);
    }
  }

  private static String generateValidator(ViewDTO view, AttributeMdDTO md, ColumnSetup setup)
  {
    if (view.isWritable(md.getAccessorName()) && setup.isEditable())
    {
      if (setup.getValidator() != null)
      {
        return "validator:" + setup.getValidator();
      }
    }

    return null;
  }

  private static String generateSaveFlag(AttributeMdDTO md)
  {
    if (md instanceof AttributeReferenceMdDTO)
    {
      Class<?> refrenced_class = md.getJavaType();

      if (LabeledDTO.class.isAssignableFrom(refrenced_class))
      {
        return "save_as_id:true";
      }
    }

    return null;
  }

  private static String generateFormatter(AttributeMdDTO md)
  {
    if (md instanceof AttributeDateMdDTO)
    {
      return "formatter:YAHOO.widget.DataTable.formatDate";
    }

    return null;
  }

  private static String generateEditor(ViewDTO view, String attrib, AttributeMdDTO md, ColumnSetup setup)
  {
    String DROPDOWN_EDITOR = "new YAHOO.widget.DropdownCellEditor";
    String TEXTBOX_EDITOR = "new YAHOO.widget.TextboxCellEditor";
    String DATE_EDITOR = "new YAHOO.widget.DateCellEditor";
    String TERM_EDITOR = "new YAHOO.widget.OntologyTermEditor";

    // Default to a text box editor
    String editor = TEXTBOX_EDITOR;
    List<String> options = new LinkedList<String>();

    if (md instanceof AttributeBooleanMdDTO)
    {
      String positiveLabel = ( (AttributeBooleanMdDTO) md ).getPositiveDisplayLabel().replaceAll("'", "\\\\'");
      String negativeLabel = ( (AttributeBooleanMdDTO) md ).getNegativeDisplayLabel().replaceAll("'", "\\\\'");

      List<String> radioOptions = new LinkedList<String>();
      radioOptions.add("{label:'" + positiveLabel + "', value:'true'}");
      radioOptions.add("{label:'" + negativeLabel + "', value:'false'}");

      options.add("dropdownOptions:[" + Halp.join(radioOptions) + "]");

      editor = DROPDOWN_EDITOR;
    }
    else if (md instanceof AttributeDateMdDTO)
    {
      options.add("calendar:MDSS.Calendar.init()");

      editor = DATE_EDITOR;
    }
    else
    {
      if (md instanceof AttributeEnumerationMdDTO)
      {
        List<String> dropdownOptions = new LinkedList<String>();
        AttributeEnumerationMdDTO enumMd = (AttributeEnumerationMdDTO) md;

        for (Map.Entry<String, String> e : enumMd.getEnumItems().entrySet())
        {
          dropdownOptions.add("{label:'" + e.getValue() + "', value:'" + e.getKey() + "'}");
        }

        options.add("dropdownOptions:[" + Halp.join(dropdownOptions) + "]");

        editor = DROPDOWN_EDITOR;
      }
      else
      {
        Class<?> refrenced_class = md.getJavaType();

        if (setup.getType() != null)
        {
          refrenced_class = LoaderDecorator.load(setup.getType());
        }

        if (LabeledDTO.class.isAssignableFrom(refrenced_class))
        {
          options.add("dropdownOptions:" + attrib + "Options");

          editor = DROPDOWN_EDITOR;
        }
        else if (TermDTO.class.isAssignableFrom(refrenced_class))
        {
          options.add("klass:'" + view.getType() + "'");
          options.add("attribute:'" + attrib + "'");
          editor = TERM_EDITOR;
        }
      }
    }

    options.add(generateValidator(view, md, setup));
    options.add("disableBtns:true");

    return "editor:" + editor + "({" + Halp.join(options) + "})";
  }

  public static void sendErrorMail(Throwable exception, HttpServletRequest request, String text)
  {
    /*
     * String from = "MDSSS-no-reply@terraframe.com"; String to =
     * EMAIL_ERRORS_TO; String subject =
     * "MDSS has produced an uncaught exception"; if (text == null) { text =
     * "Requested url: "; text +=
     * request.getAttribute("javax.servlet.forward.request_uri") + "\n\n"; text
     * += "Error in class: "; text += exception.getClass().getName() + "\n\n";
     * text += "Error in class: "; text += exception.getClass().getName() +
     * "\n\n"; text += exception.getLocalizedMessage() + "\n\n"; text +=
     * request.getQueryString() + "\n\n"; final Writer result = new
     * StringWriter(); final PrintWriter printWriter = new PrintWriter(result);
     * exception.printStackTrace(printWriter); text += result.toString() +
     * "\n\n"; } // // A properties to store mail server smtp information such
     * as the host // name and the port number. With this properties we create a
     * Session // object from which we'll create the Message object. //
     * Properties properties = new Properties();
     * properties.put("mail.smtp.host", "terraframe.com");
     * properties.put("mail.smtp.port", "25"); properties.put("mail.smtp.auth",
     * true); Session session = Session.getDefaultInstance(properties, null);
     * 
     * try {
     * 
     * Message message = new MimeMessage(session); message.setFrom(new
     * InternetAddress(from)); message.setRecipient(Message.RecipientType.TO,
     * new InternetAddress(to)); message.setSubject(subject);
     * message.setText(text); // Send the message to the recipient.
     * Transport.send(message); } catch (MessagingException e) {
     * e.printStackTrace(); }
     */
  }

  class MyAuth extends Authenticator
  {
    protected PasswordAuthentication getPasswordAuthentication()
    {
      return new PasswordAuthentication("", "");
    }
  }

  public static ByteArrayOutputStream renderJspToByteArray(HttpServletRequest request, HttpServletResponse response, String jsp_to_render) throws ServletException, IOException
  {

    // create an output stream - to file, to memory...
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    //
    response.setCharacterEncoding("UTF-8");

    // create the "dummy" response object
    RedirectingServletResponse dummyResponse;
    dummyResponse = new RedirectingServletResponse(response, out);

    // get the path to the calling jsp's folder
    String current_path = request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/") + 1);

    // in same folder jsp_to_render.contains(current_path)
    if (jsp_to_render.contains("/") && !jsp_to_render.startsWith("/"))
    {
      jsp_to_render = "/" + jsp_to_render;
    }

    if (jsp_to_render.contains(current_path) && current_path.length() > 1)
    {
      jsp_to_render = jsp_to_render.substring(jsp_to_render.lastIndexOf(current_path));
      request.setAttribute("jsp_to_render", jsp_to_render);
    }
    else
    {
      // set the full path of the jsp to render
      request.setAttribute("jsp_to_render", current_path + jsp_to_render);
    }
    // get a request dispatcher for the jsp template
    // RequestDispatcher rd =
    // request.getRequestDispatcher("/WEB-INF/templates/force_flush.jsp");
    RequestDispatcher rd = request.getRequestDispatcher(jsp_to_render);

    // execute the jsp and return the output stream
    rd.include(request, dummyResponse);
    dummyResponse.flushBuffer();
    return out;

  }

  // This renders a jsp to a string, usefull for emails and inside out
  // rendering
  public static String renderJspToString(HttpServletRequest request, HttpServletResponse response, String jsp_to_render)
  {
    // we are rendering inside out, so we have to set the date format here,
    // because the header has not been rendered yet.
    Halp.getDateFormatString(request);

    try
    {
      return renderJspToByteArray(request, response, jsp_to_render).toString("UTF-8");
    }
    catch (Exception exception)
    {
      exception.printStackTrace(System.out);
      String text = "<pre> ";
      text += request.getAttribute("javax.servlet.forward.request_uri") + "\n\n";
      text += "Error in class: ";
      text += exception.getClass().getName() + "\n\n";
      text += exception.getLocalizedMessage() + "\n\n";
      text += request.getQueryString() + "\n\n";
      final Writer result = new StringWriter();
      final PrintWriter printWriter = new PrintWriter(result);
      exception.printStackTrace(printWriter);
      text += result.toString() + "\n\n</pre>";

      return text;
    }
  }

  public static String getDefaultValues(ViewDTO view, String[] keys)
  {
    List<String> defaults = new LinkedList<String>();

    for (String key : keys)
    {
      try
      {
        DTOFacade facade = new DTOFacade(key, view);
        Object value = facade.getValue();
        if (value != null)
        {
          if (value instanceof LabeledDTO)
          {
            LabeledDTO dto = (LabeledDTO) value;
            defaults.add(Halp.getDefaultValue(key, dto.getOptionId(), dto.getLabel()));
          }
          else if (value instanceof List<?>)
          {
            // This is an enumeration attribute: Do nothing
          }
          else if (value instanceof GeoEntityDTO)
          {
            GeoEntityDTO dto = (GeoEntityDTO) value;
            defaults.add(Halp.getDefaultValue(key, dto.getId(), dto.getDisplayString()));
          }
          else if (value instanceof TermDTO)
          {
            TermDTO dto = (TermDTO) value;
            defaults.add(Halp.getDefaultValue(key, dto.getId(), dto.getDisplayLabel()));
          }
          else if (value instanceof ComponentDTO)
          {
            ComponentDTO dto = (ComponentDTO) value;
            defaults.add(Halp.getDefaultValue(key, dto.getId()));
          }
          else if (value instanceof Boolean)
          {
            Boolean booleanValue = (Boolean) value;
            AttributeBooleanMdDTO attributeBooleanMd = (AttributeBooleanMdDTO) facade.getAttributeMdDTO();

            String objectValue = booleanValue.toString();
            String objectLabel = attributeBooleanMd.getNegativeDisplayLabel();

            if (booleanValue)
            {
              objectLabel = attributeBooleanMd.getPositiveDisplayLabel();
            }

            defaults.add(Halp.getDefaultValue(key, objectValue, objectLabel));
          }
          else if (!value.toString().equals(""))
          {
            defaults.add(Halp.getDefaultValue(key, value.toString()));
          }

        }
      }
      catch (InvocationTargetException e)
      {
        Throwable t = e.getTargetException();

        if (! ( t instanceof ClientReadAttributePermissionException ))
        {
          if (t instanceof RuntimeException)
          {
            throw (RuntimeException) t;
          }
          else
          {
            throw new ApplicationException(t);
          }
        }
      }
      catch (ClientReadAttributePermissionException e)
      {
        // Do nothing
      }
      catch (Exception e)
      {
        throw new ApplicationException(e);
      }
    }

    return "{" + Halp.join(defaults, ",") + "}";
  }

  private static String getDefaultValue(String key, String value)
  {
    return "'" + key + "':'" + value + "'";
  }

  private static String getDefaultValue(String key, String objectValue, String objectLabel)
  {
    return "'" + key + "':{'value':'" + objectValue + "','label':'" + objectLabel + "'}";
  }
}