package org.apache.jsp.js;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dss.vector.solutions.ontology.BrowserRootViewDTO;
import dss.vector.solutions.ontology.BrowserRootDTO;
import dss.vector.solutions.ontology.TermViewDTO;
import dss.vector.solutions.ontology.TermDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.geo.GeoHierarchyViewDTO;
import java.util.LinkedList;
import dss.vector.solutions.geo.GeoHierarchyViewQueryDTO;
import com.terraframe.mojo.system.metadata.MdBusinessDTO;
import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.util.Halp;
import java.util.Arrays;
import java.util.List;
import com.ibm.icu.text.SimpleDateFormat;
import java.util.Date;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.Calendar;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ClientConstants;
import com.terraframe.mojo.constants.Constants;
import com.terraframe.mojo.web.json.JSONController;

public final class getClass_js_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/tlds/mojoLib.tld");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

//tell firfox to allways check for a new version
response.setHeader("Content-Type","text/html;charset=UTF-8");
response.setHeader("Cache-Control","must-revalidate");
response.setHeader("Expires","0");
session.getId();

//parse the input
String includeUniversalTypes = request.getParameter("includeUniversalTypes");
String[] types_to_load;
if(includeUniversalTypes != null)
{
  GeoHierarchyViewQueryDTO query = GeoHierarchyDTO.getAllGeoHierarchyViews(clientRequest);
  List<? extends GeoHierarchyViewDTO> results = query.getResultSet();
  List<String> toLoad = new LinkedList<String>();

  for(GeoHierarchyViewDTO view : results)
  {
	String type = MDSSInfo.GENERATED_GEO_PACKAGE+"."+view.getTypeName();
    toLoad.add(type);
    toLoad.add(type+"Controller");
  }
  
  toLoad.add(TermViewDTO.CLASS);
  toLoad.add(TermDTO.CLASS);
  toLoad.add(BrowserRootDTO.CLASS);
  toLoad.add(BrowserRootViewDTO.CLASS);

  types_to_load = toLoad.toArray(new String[toLoad.size()]);
}
else
{
  types_to_load = request.getQueryString().split("&");
}




//set last update to far in the future incase something goes wrong
Date  lastUpdate    = new Date();

//-1 means something went wrong
Long  lastUpdatedTimestamp = new Long(-1);

//long etag = 0;

String etagStr="NEW";

try
{
  SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATETIME_FORMAT);
  String newestUpdated = JSONController.getNewestUpdateDate(clientRequest.getSessionId() , types_to_load,true);
  lastUpdate = dateFormat.parse(newestUpdated, new java.text.ParsePosition(0));
  lastUpdatedTimestamp = new Long(lastUpdate.getTime());
  response.setDateHeader("Last-Modified",lastUpdate.getTime());
  etagStr = session.getId() + lastUpdatedTimestamp.toString();

  response.setHeader("Etag", etagStr);
  //parse incoming etag
  //etag = Long.parseLong(request.getHeader("If-None-Match").replace("\"",""));
}
catch(Exception e){}


String cacheControl = request.getHeader("Cache-Control");
boolean refreshWanted = false;
if(cacheControl != null)
{
  refreshWanted = cacheControl.equals("max-age=0");
}

Date  todaysDate    = new Date();

SimpleDateFormat   showDateFormatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");

String   outString = "";
outString += "/*================================================================\n";
outString += "This file was created " + showDateFormatter.format(todaysDate)+"\n";
outString += "For Session : " + session.getId()+"\n";
outString += "Newest Type's Update date is " + showDateFormatter.format(lastUpdate)+"\n";
outString += Halp.join((List<String>) Arrays.asList(types_to_load),"\n") +"\n";
outString += "================================================================*/\n";


if ( etagStr.equals(request.getHeader("If-None-Match")))
{
  outString += "Contents are unchanged: send 304 status message \n";
  response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
}
else
{
  out.println(outString);
  out.println(JSONController.importTypes(clientRequest.getSessionId() , types_to_load,true));
}
//System.out.println(outString);

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
