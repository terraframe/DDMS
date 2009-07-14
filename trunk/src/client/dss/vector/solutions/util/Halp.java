package dss.vector.solutions.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
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
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.terraframe.mojo.business.ComponentDTO;
import com.terraframe.mojo.business.ViewDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.Constants;
import com.terraframe.mojo.generation.loader.LoaderDecorator;
import com.terraframe.mojo.system.EnumerationMasterDTO;
import com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeDateMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeNumberMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO;

import dss.vector.solutions.LabeledDTO;

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
        attributeType = attributeType.substring(attributeType.lastIndexOf(".MdAttribute") + 12)
            .toUpperCase();
        return valueOf(attributeType);
      }
      catch (Exception ex)
      {
        return OTHER;
      }
    }
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

  public static String getDropDownMap(LabeledDTO[] terms) throws JSONException
  {
    JSONObject map = new JSONObject();
    for (LabeledDTO term : terms)
    {
      map.put(term.getOptionId(), term.getLabel());
    }
    return map.toString();
  }

  public static String getDisplayLabels(LabeledDTO[] terms, String name) throws JSONException
  {
    JSONArray ids = new JSONArray();
    JSONArray labels = new JSONArray();

    for (LabeledDTO term : terms)
    {
      ids.put(term.getOptionId());
      labels.put(term.getLabel().replaceAll("'", "\\'"));
    }

    return name + "Ids = " + ids.toString() + "; \n " + name + "Labels = " + labels.toString() + ";";
  }

  @SuppressWarnings("deprecation")
  public static String getDataMap(ViewDTO[] rows, String[] attribs, ViewDTO view) throws JSONException
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
    System.out.println("attribs length =  " + ordered_attribs.size());
    for (ViewDTO row : rows)
    {
      JSONObject element = new JSONObject();
      Class<?> c = row.getClass();

      for (String attrib : ordered_attribs)
      {
        try
        {
          String value = (String) c.getMethod("get" + attrib).invoke(row).toString();

          String attributeType = view.getAttributeType(attrib.substring(0, 1).toLowerCase()
              + attrib.substring(1));

          SimpleDateFormat df = new SimpleDateFormat(Constants.DATETIME_FORMAT);

          switch (Halp.MdType.toType(attributeType))
          {
            case DATE:
              value = df.format(new Date(value));
              break;
            case ENUMERATION:
              // FIXME: this is a hack for enums
              value = value.replaceAll("\\[", "").replaceAll("\\]", "");
              break;
            case REFERENCE:
              value = (String) ( (ComponentDTO) c.getMethod("get" + attrib).invoke(row) ).getId();
              break;
          }
          element.put(attrib, value);

        }
        catch (IllegalAccessException x)
        {
          System.out.println(x + " " + x.getCause());
        }
        catch (IllegalArgumentException x)
        {
          System.out.println(x + " " + x.getCause());
        }
        catch (InvocationTargetException x)
        {
          System.out.println(x + " " + x.getCause());
        }
        catch (NoSuchMethodException x)
        {
          System.out.println("No such method get" + attrib);
        }
        catch (NullPointerException x)
        {
          System.out.println("Null Pointer Exception get" + attrib);
        } // catch (ParseException x) {
        // System.out.println("Could not parse date" + attrib);
        // }

      }
      map.put(element);
    }
    return map.toString();// .replaceAll(",", ",\n");
  }

  public static String getDropdownSetup(ViewDTO view, String[] attribs, String extra_rows,
      ClientRequestIF clientRequest) throws JSONException
  {
    return Halp
        .getDropdownSetup(view, attribs, extra_rows, clientRequest, new HashMap<String, String>());
  }

  public static String getDropdownSetup(ViewDTO view, String[] attribs, String extra_rows,
      ClientRequestIF clientRequest, Map<String, String> map) throws JSONException
  {
    Class<?> v = view.getClass();
    ArrayList<String> ordered_attribs = new ArrayList<String>(Arrays.asList(attribs));

    for (String a : view.getAccessorNames())
    {
      if (!ordered_attribs.contains(a) && a.length() >= 3)
      {
        ordered_attribs.add(a.substring(0, 1).toUpperCase() + a.substring(1));
      }
    }

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
            LabeledDTO[] terms = (LabeledDTO[]) clazz.getMethod("getAllActive",
                new Class[] { ClientRequestIF.class }).invoke(null, clientRequest);
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

  @SuppressWarnings("unchecked")
  public static String getDropDownMaps(ViewDTO view, ClientRequestIF clientRequest) throws JSONException
  {
    int colnum = 0;
    Class<?> v = view.getClass();
    ArrayList<String> ordered_attribs = new ArrayList<String>();

    for (String a : view.getAccessorNames())
    {
      if (a.length() >= 3)
      {
        ordered_attribs.add(a);
      }
    }

    ArrayList<String> dropdownbuff = new ArrayList<String>();
    for (String attrib : ordered_attribs)
    {
      try
      {
        AttributeMdDTO md = (AttributeMdDTO) v.getMethod(
            "get" + attrib.substring(0, 1).toUpperCase() + attrib.substring(1) + "Md").invoke(view);

        if (md instanceof AttributeReferenceMdDTO)
        {
          Class<?> clazz = md.getJavaType();
          if (LabeledDTO.class.isAssignableFrom(clazz))
          {
            LabeledDTO[] terms = (LabeledDTO[]) clazz.getMethod("getAllActive",
                new Class[] { ClientRequestIF.class }).invoke(null, clientRequest);
            dropdownbuff.add(attrib + " : " + getDropDownMap(terms) + ",");
          }
        }
        if (md instanceof AttributeEnumerationMdDTO)
        {
          AttributeEnumerationMdDTO enumMd = (AttributeEnumerationMdDTO) md;
          String map = "{";
          /*
           * for (Map.Entry<String, String> e :
           * enumMd.getEnumItems().entrySet()) { map += "'" + e.getKey() + "':'"
           * + e.getValue() + "',"; }
           */
          for (EnumerationMasterDTO e : (List<EnumerationMasterDTO>) clientRequest
              .getAllEnumerations(enumMd.getReferencedMdEnumeration()))
          {
            map += "'" + e.getId() + "':'" + e.getDisplayLabel() + "',";
          }
          map += "}";
          dropdownbuff.add(attrib + " : " + map + ",");
        }
        if (md instanceof AttributeBooleanMdDTO)
        {
          dropdownbuff.add(attrib + ":{'true':'"
              + ( (AttributeBooleanMdDTO) md ).getPositiveDisplayLabel().replaceAll("'", "\\'")
              + "', 'false':'"
              + ( (AttributeBooleanMdDTO) md ).getNegativeDisplayLabel().replaceAll("'", "\\'") + "'},");
        }

      }
      catch (Exception x)
      {
        throw new RuntimeException(x);
      }
      colnum++;
    }

    return ( Halp.join(dropdownbuff, "\n") );
  }

  public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload)
      throws JSONException
  {
    return getColumnSetup(view, attribs, extra_rows, autoload, 1);
  }

  public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows,
      boolean autoload, int num_to_hide) throws JSONException
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

  public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows,
      boolean autoload, List<Integer> no_show, List<Integer> no_edit) throws JSONException
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

  public static String getColumnSetup(ViewDTO view, String[] attributes, String extra_rows,
      boolean autoload, Map<String, ColumnSetup> map) throws JSONException
  {
    List<String> list = Arrays.asList(attributes);

    List<String> columns = new ArrayList<String>();
    List<String> ordered = new ArrayList<String>(list);

    for (String accessorName : view.getAccessorNames())
    {
      String upcased_attrib = accessorName.substring(0, 1).toUpperCase() + accessorName.substring(1);
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

  public static String getHiddenColumns(ViewDTO view, String[] attributes, Map<String, ColumnSetup> map)
      throws JSONException
  {
    List<String> list = Arrays.asList(attributes);

    List<String> columns = new ArrayList<String>();
    List<String> ordered = new ArrayList<String>();

    for (String accessorName : view.getAccessorNames())
    {
      String attributeName = accessorName.substring(0, 1).toUpperCase() + accessorName.substring(1);

      if (!list.contains(attributeName) && accessorName.length() >= 3)
      {
        ordered.add(attributeName);
      }

      if (!view.isReadable(accessorName))
      {
        ordered.remove(attributeName);
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

    return ( "[" + Halp.join(columns, ",\n") + "]" );
  }

  private static String generateColumnMap(ViewDTO view, ColumnSetup setup, String attrib)
  {
    try
    {
      Class<?> clazz = view.getClass();

      ArrayList<String> buff = new ArrayList<String>();

      buff.add("key:'" + attrib + "'");

      AttributeMdDTO md = (AttributeMdDTO) clazz.getMethod("get" + attrib + "Md").invoke(view);
      Class<?> mdClass = md.getClass();

      String label = (String) mdClass.getMethod("getDisplayLabel").invoke(md).toString();
      buff.add("label:'" + label.replaceAll("'", "\\\\'") + "'");

      if (setup.isHidden())
      {
        buff.add("hidden:true");
      }
      else
      {
        buff.add(Halp.generateFormatter(md));
        buff.add(Halp.generateSaveFlag(md));
        buff.add(Halp.generateEditor(attrib, md));
        buff.add(Halp.generateValidator(view, md, setup));
      }

      return Halp.join(buff, ",");
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

  private static String generateEditor(String attrib, AttributeMdDTO md)
  {
    String editor = "null";

    if (md instanceof AttributeNumberMdDTO)
    {
      editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
    }
    else if (md instanceof AttributeBooleanMdDTO)
    {
      editor = "new YAHOO.widget.RadioCellEditor({radioOptions:[{label:'"
          + ( (AttributeBooleanMdDTO) md ).getPositiveDisplayLabel().replaceAll("'", "\\\\'")
          + "', value:'true'}, {label:'"
          + ( (AttributeBooleanMdDTO) md ).getNegativeDisplayLabel().replaceAll("'", "\\\\'")
          + "', value:'false'}],disableBtns:true})";
    }
    else if (md instanceof AttributeCharacterMdDTO)
    {
      editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
    }
    else if (md instanceof AttributeDateMdDTO)
    {
      editor = "new YAHOO.widget.DateCellEditor({calendar:MDSS.Calendar.init(),disableBtns:true})";
    }
    else if (md instanceof AttributeEnumerationMdDTO)
    {
      AttributeEnumerationMdDTO enumMd = (AttributeEnumerationMdDTO) md;
      editor = "new YAHOO.widget.RadioCellEditor({radioOptions:[";
      String comma = "";
      for (Map.Entry<String, String> e : enumMd.getEnumItems().entrySet())
      {
        editor += comma + "{label:'" + e.getValue() + "', value:'" + e.getKey() + "'}";
        comma = ",";
      }
      editor += "],disableBtns:true})";
    }
    else
    {
      Class<?> refrenced_class = md.getJavaType();

      if (LabeledDTO.class.isAssignableFrom(refrenced_class))
      {
        editor = "new YAHOO.widget.DropdownCellEditor({dropdownOptions:" + attrib
            + "Labels,disableBtns:true})";
      }
      else
      {
        editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
      }

    }

    return "editor:" + editor;
  }

  public static void sendErrorMail(Throwable exception, HttpServletRequest request, String text)
  {
    String from = "MDSSS-no-reply@terraframe.com";
    String to = EMAIL_ERRORS_TO;
    String subject = "MDSS has produced an uncaught exception";
    if (text == null)
    {
      text = "Requested url: ";
      text += request.getAttribute("javax.servlet.forward.request_uri") + "\n\n";
      text += "Error in class: ";
      text += exception.getClass().getName() + "\n\n";
      text += "Error in class: ";
      text += exception.getClass().getName() + "\n\n";
      text += exception.getLocalizedMessage() + "\n\n";
      text += request.getQueryString() + "\n\n";
      final Writer result = new StringWriter();
      final PrintWriter printWriter = new PrintWriter(result);
      exception.printStackTrace(printWriter);
      text += result.toString() + "\n\n";
    }
    //
    // A properties to store mail server smtp information such as the host
    // name and the port number. With this properties we create a Session
    // object from which we'll create the Message object.
    //
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "terraframe.com");
    properties.put("mail.smtp.port", "25");
    properties.put("mail.smtp.auth", true);
    Session session = Session.getDefaultInstance(properties, null);

    try
    {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(subject);
      message.setText(text);
      // Send the message to the recipient.
      Transport.send(message);
    }
    catch (MessagingException e)
    {
      e.printStackTrace();
    }
  }

  class MyAuth extends Authenticator
  {
    protected PasswordAuthentication getPasswordAuthentication()
    {
      return new PasswordAuthentication("dtaylor", "Rc9hs8Z2");
    }
  }

  public static ByteArrayOutputStream renderJspToByteArray(HttpServletRequest request,
      HttpServletResponse response, String jsp_to_render) throws ServletException, IOException
  {

    // create an output stream - to file, to memory...
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    //
    response.setCharacterEncoding("UTF-8");

    // create the "dummy" response object
    RedirectingServletResponse dummyResponse;
    dummyResponse = new RedirectingServletResponse(response, out);

    // get the path to the calling jsp's folder
    String current_path = request.getServletPath().substring(0,
        request.getServletPath().lastIndexOf("/") + 1);

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
  public static String renderJspToString(HttpServletRequest request, HttpServletResponse response,
      String jsp_to_render)
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
}