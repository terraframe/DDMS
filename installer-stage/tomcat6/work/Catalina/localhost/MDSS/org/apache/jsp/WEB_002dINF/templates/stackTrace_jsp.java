package org.apache.jsp.WEB_002dINF.templates;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.regex.Pattern;
import dss.vector.solutions.util.Halp;
import java.io.PrintWriter;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.constants.ClientConstants;
import java.util.Enumeration;

public final class stackTrace_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    Throwable exception = org.apache.jasper.runtime.JspRuntimeLibrary.getThrowable(request);
    if (exception != null) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("<br>\n");
      out.write("<br>\n");

  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  if (clientRequest != null && clientRequest.isLoggedIn())
  {
    out.println("Logged In As: " + clientRequest.getSessionUser() + "<br/>");
  }

  out.println("Client:" + request.getRemoteAddr() + " " + request.getRemoteHost()+ "<br/>");

  out.println("Server:" + request.getLocalAddr() + " " + request.getLocalName()+ "<br/>");

  out.println("Requested:" + request.getAttribute("javax.servlet.forward.request_uri") + "<br/>");

  out.println("Detected Locale is " + request.getLocale().toString());



  out.println("<h2>Request Headers:</h2>");
  Enumeration headers = request.getHeaderNames();
  while (headers.hasMoreElements())
  {
    String pName = (String) headers.nextElement();
    String pValue = null;
    if (pName != null)
    {
      pValue = request.getHeader(pName);
    }
    out.println(pName + " = " + pValue + "<br/>");
  }


  out.println("<h2>Request Params:</h2>");
  Enumeration params = request.getParameterNames();
  while (params.hasMoreElements())
  {
    String pName = (String) params.nextElement();
    String pValue = null;
    if (pName != null)
    {
      pValue = request.getParameter(pName);
    }
    out.println(pName + " = " + pValue + "<br/>");
  }


  out.print(exception.getClass().getName() + "<br/>");
try
{
  if (exception.getClass().getName().contains("RuntimeException"))
  {
    out.println("<h2>Cause:</h2>");
    out.print(exception.getCause().getMessage() + "<br/>");
    out.println("<pre>");
    PrintWriter pw = new PrintWriter(out);
    exception.getCause().printStackTrace(pw);
    out.println("</pre><br/><br/><br/>");
  }
}
catch(Exception e)
{out.println(e.getStackTrace());}


  out.println("<pre>");
  out.print(exception.getLocalizedMessage());
  out.println("</pre>");
  out.println("<pre>");
  PrintWriter pw = new PrintWriter(out);
  exception.printStackTrace(pw);
  out.println("</pre>");

      out.write('\n');
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
