package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.terraframe.mojo.web.json.JSONController;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.geo.GeoHierarchyViewDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ClientConstants;
import dss.vector.solutions.geo.GeoHierarchyDTO;
import dss.vector.solutions.geo.GeoEntityTreeController;
import org.json.JSONArray;
import dss.vector.solutions.geo.generated.EarthDTO;
import dss.vector.solutions.util.Halp;
import java.util.List;
import java.util.Arrays;
import dss.vector.solutions.geo.GeoEntityViewDTO;

public final class selectSearch_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "geoEntityTreeComponent.jsp", out, false);
      out.write('\n');
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
String rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);
String[] types = new String[] { GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTreeController.CLASS, GeoEntityViewDTO.CLASS };
//String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
// out.print(js);
      out.write('\n');
      out.print(Halp.loadTypes((List<String>) Arrays.asList(types)));
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("MDSS.SelectSearchRootId = '");
      out.print(rootId);
      out.write("';\n");
      out.write("</script>");
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