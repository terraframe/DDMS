package org.apache.jsp.WEB_002dINF.dss.vector.solutions.stock.StockEvent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dss.vector.solutions.stock.StockEventDTO;
import dss.vector.solutions.stock.StockItemViewDTO;
import dss.vector.solutions.geo.generated.StockDepotDTO;

public final class searchComponent_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/tlds/mojoLib.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fscope;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fotherwise;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fscope = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fscope.release();
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fchoose.release();
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fotherwise.release();
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
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/selectSearch.jsp", out, false);
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_mjl_005fmessages_005f0(_jspx_page_context))
        return;
      out.write('\n');
      out.write('\n');
      //  c:set
      org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fscope.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
      _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fset_005f1.setParent(null);
      // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(16,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f1.setVar("StockDepot");
      // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(16,0) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f1.setScope("request");
      int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
      if (_jspx_eval_c_005fset_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        if (_jspx_eval_c_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.pushBody();
          _jspx_th_c_005fset_005f1.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
          _jspx_th_c_005fset_005f1.doInitBody();
        }
        do {
          out.print(StockDepotDTO.CLASS);
          int evalDoAfterBody = _jspx_th_c_005fset_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
        if (_jspx_eval_c_005fset_005f1 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
          out = _jspx_page_context.popBody();
        }
      }
      if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fscope.reuse(_jspx_th_c_005fset_005f1);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fscope.reuse(_jspx_th_c_005fset_005f1);
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_mjl_005fform_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("<script type=\"text/javascript\">  \n");
      out.write("(function(){\n");
      out.write("  YAHOO.util.Event.onDOMReady(function(){   \n");
      out.write("    var attributes = [\n");
      out.write("         {attributeName:'itemName'}\n");
      out.write("    ];\n");
      out.write("    \n");
      out.write("    new MDSS.GenericOntologyBrowser(\"");
      out.print(StockItemViewDTO.CLASS);
      out.write("\", attributes);\n");
      out.write("  })\n");
      out.write("})();\n");
      out.write("</script>\n");
      out.write("\n");
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

  private boolean _jspx_meth_c_005fset_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(8,67) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("page_title");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(8,67) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new String("Stock_Detail"));
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(8,67) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setScope("request");
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_mjl_005fmessages_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:messages
    com.terraframe.mojo.controller.tag.MessagesTagSupport _jspx_th_mjl_005fmessages_005f0 = new com.terraframe.mojo.controller.tag.MessagesTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005fmessages_005f0);
    _jspx_th_mjl_005fmessages_005f0.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005fmessages_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_mjl_005fmessages_005f0, null));
    _jspx_th_mjl_005fmessages_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005fmessages_005f0);
    return false;
  }

  private boolean _jspx_meth_mjl_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:message
    com.terraframe.mojo.controller.tag.MessageTagSupport _jspx_th_mjl_005fmessage_005f0 = new com.terraframe.mojo.controller.tag.MessageTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005fmessage_005f0);
    _jspx_th_mjl_005fmessage_005f0.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005fmessage_005f0.setParent(_jspx_parent);
    _jspx_th_mjl_005fmessage_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_mjl_005fform_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:form
    com.terraframe.mojo.controller.tag.FormTagSupport _jspx_th_mjl_005fform_005f0 = new com.terraframe.mojo.controller.tag.FormTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005fform_005f0);
    _jspx_th_mjl_005fform_005f0.setJspContext(_jspx_page_context);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(18,0) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fform_005f0.setName("StockDetail.search.mojo");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(18,0) name = method type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fform_005f0.setMethod("POST");
    _jspx_th_mjl_005fform_005f0.setJspBody(new Helper( 1, _jspx_page_context, _jspx_th_mjl_005fform_005f0, null));
    _jspx_th_mjl_005fform_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005fform_005f0);
    return false;
  }

  private boolean _jspx_meth_mjl_005finput_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:input
    com.terraframe.mojo.controller.tag.InputTagSupport _jspx_th_mjl_005finput_005f0 = new com.terraframe.mojo.controller.tag.InputTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f0);
    _jspx_th_mjl_005finput_005f0.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005finput_005f0.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(25,6) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f0.setId("geoIdEl");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(25,6) name = param type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f0.setParam("geoId");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(25,6) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f0.setType("text");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(25,6) name = maxlength type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f0.setMaxlength("16");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(25,6) name = classes type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f0.setClasses("geoInput");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(25,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f0.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${geoId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    _jspx_th_mjl_005finput_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(31,64) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f0.setKey("Browser");
    int _jspx_eval_fmt_005fmessage_005f0 = _jspx_th_fmt_005fmessage_005f0.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fchoose_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fc_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fchoose_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    int _jspx_eval_c_005fchoose_005f0 = _jspx_th_c_005fchoose_005f0.doStartTag();
    if (_jspx_eval_c_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("            ");
        if (_jspx_meth_c_005fwhen_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("            ");
        if (_jspx_meth_c_005fotherwise_005f0(_jspx_th_c_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("          ");
        int evalDoAfterBody = _jspx_th_c_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fchoose.reuse(_jspx_th_c_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(34,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item != null}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fwhen_005f0 = _jspx_th_c_005fwhen_005f0.doStartTag();
    if (_jspx_eval_c_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("              ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.displayLabel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_c_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fwhen_0026_005ftest.reuse(_jspx_th_c_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fchoose_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fc_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fchoose_005f0);
    int _jspx_eval_c_005fotherwise_005f0 = _jspx_th_c_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_c_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("              ");
        if (_jspx_meth_fmt_005fmessage_005f1(_jspx_th_c_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("            ");
        int evalDoAfterBody = _jspx_th_c_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fotherwise.reuse(_jspx_th_c_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fotherwise_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f1 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fotherwise_005f0);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(38,14) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f1.setKey("no_value");
    int _jspx_eval_fmt_005fmessage_005f1 = _jspx_th_fmt_005fmessage_005f1.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_mjl_005finput_005f1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:input
    com.terraframe.mojo.controller.tag.InputTagSupport _jspx_th_mjl_005finput_005f1 = new com.terraframe.mojo.controller.tag.InputTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f1);
    _jspx_th_mjl_005finput_005f1.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005finput_005f1.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(42,6) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f1.setType("hidden");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(42,6) name = param type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f1.setParam("item.componentId");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(42,6) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f1.setId("itemName");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(42,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f1.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item != null ? item.id : ''}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    _jspx_th_mjl_005finput_005f1.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f1);
    return false;
  }

  private boolean _jspx_meth_mjl_005finput_005f2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:input
    com.terraframe.mojo.controller.tag.InputTagSupport _jspx_th_mjl_005finput_005f2 = new com.terraframe.mojo.controller.tag.InputTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f2);
    _jspx_th_mjl_005finput_005f2.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005finput_005f2.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(48,6) name = param type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f2.setParam("date");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(48,6) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f2.setType("text");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(48,6) name = classes type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f2.setClasses("DatePick NoFuture");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(48,6) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f2.setId("date");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(48,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f2.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${date}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    _jspx_th_mjl_005finput_005f2.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f2);
    return false;
  }

  private boolean _jspx_meth_mjl_005fcommand_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:command
    com.terraframe.mojo.controller.tag.CommandTagSupport _jspx_th_mjl_005fcommand_005f0 = new com.terraframe.mojo.controller.tag.CommandTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005fcommand_005f0);
    _jspx_th_mjl_005fcommand_005f0.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005fcommand_005f0.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(50,4) name = classes type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f0.setClasses("submitButton");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(50,4) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f0.setAction("dss.vector.solutions.stock.StockEventController.searchInStock.mojo");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(50,4) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f0.setName("inStock");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(50,4) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f0.setValue("In_Stock");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(50,4) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f0.setId("inStock");
    _jspx_th_mjl_005fcommand_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005fcommand_005f0);
    return false;
  }

  private boolean _jspx_meth_mjl_005fcommand_005f1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:command
    com.terraframe.mojo.controller.tag.CommandTagSupport _jspx_th_mjl_005fcommand_005f1 = new com.terraframe.mojo.controller.tag.CommandTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005fcommand_005f1);
    _jspx_th_mjl_005fcommand_005f1.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005fcommand_005f1.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(51,4) name = classes type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f1.setClasses("submitButton");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(51,4) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f1.setAction("dss.vector.solutions.stock.StockEventController.searchOutStock.mojo");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(51,4) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f1.setName("outStock");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(51,4) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f1.setValue("Out_Stock");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(51,4) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f1.setId("outStock");
    _jspx_th_mjl_005fcommand_005f1.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005fcommand_005f1);
    return false;
  }

  private boolean _jspx_meth_fmt_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_005fmessage_005f2 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fmessage_005f2.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(53,14) name = key type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fmessage_005f2.setKey("endDate");
    int _jspx_eval_fmt_005fmessage_005f2 = _jspx_th_fmt_005fmessage_005f2.doStartTag();
    if (_jspx_th_fmt_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005ffmt_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_fmt_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_mjl_005finput_005f3(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:input
    com.terraframe.mojo.controller.tag.InputTagSupport _jspx_th_mjl_005finput_005f3 = new com.terraframe.mojo.controller.tag.InputTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f3);
    _jspx_th_mjl_005finput_005f3.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005finput_005f3.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(56,6) name = param type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f3.setParam("endDate");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(56,6) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f3.setType("text");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(56,6) name = classes type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f3.setClasses("DatePick NoFuture");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(56,6) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f3.setId("endDate");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(56,6) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005finput_005f3.setValue((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${endDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    _jspx_th_mjl_005finput_005f3.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005finput_005f3);
    return false;
  }

  private boolean _jspx_meth_mjl_005fcommand_005f2(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  mjl:command
    com.terraframe.mojo.controller.tag.CommandTagSupport _jspx_th_mjl_005fcommand_005f2 = new com.terraframe.mojo.controller.tag.CommandTagSupport();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_mjl_005fcommand_005f2);
    _jspx_th_mjl_005fcommand_005f2.setJspContext(_jspx_page_context);
    _jspx_th_mjl_005fcommand_005f2.setParent(_jspx_parent);
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(58,4) name = classes type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f2.setClasses("submitButton");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(58,4) name = action type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f2.setAction("dss.vector.solutions.stock.StockEventController.searchPage.mojo");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(58,4) name = name type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f2.setName("searchPage");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(58,4) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f2.setValue("Search");
    // /WEB-INF/dss/vector/solutions/stock/StockEvent/searchComponent.jsp(58,4) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mjl_005fcommand_005f2.setId("searchPage");
    _jspx_th_mjl_005fcommand_005f2.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_mjl_005fcommand_005f2);
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( JspWriter out ) 
      throws Throwable
    {
      out.write('\n');
      out.write(' ');
      out.write(' ');
      if (_jspx_meth_mjl_005fmessage_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write('\n');
      return false;
    }
    public boolean invoke1( JspWriter out ) 
      throws Throwable
    {
      out.write("\n");
      out.write("  <dl>\n");
      out.write("    <dt>\n");
      out.write("      <label> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${view.stockDepotMd.displayLabel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(" </label>\n");
      out.write("    </dt>\n");
      out.write("    <dd>\n");
      out.write("      <input type=\"hidden\" name=\"typeSearchFilter\" id=\"typeSearchFilter\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${StockDepot}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\n");
      out.write("      ");
      if (_jspx_meth_mjl_005finput_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </dd>\n");
      out.write("    <dt>\n");
      out.write("      <label> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${view.itemMd.displayLabel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(" </label>\n");
      out.write("    </dt>\n");
      out.write("    <dd>\n");
      out.write("      <span class=\"clickable browserLauncher\" id=\"itemNameBtn\"> ");
      if (_jspx_meth_fmt_005fmessage_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("</span>\n");
      out.write("      <div id=\"itemNameDisplay\" class=\"ontologyDisplay\">\n");
      out.write("          ");
      if (_jspx_meth_c_005fchoose_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("      </div>\n");
      out.write("      ");
      if (_jspx_meth_mjl_005finput_005f1(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </dd>\n");
      out.write("    <dt>\n");
      out.write("      <label> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${view.eventDateMd.displayLabel}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(" </label>\n");
      out.write("    </dt>\n");
      out.write("    <dd>\n");
      out.write("      ");
      if (_jspx_meth_mjl_005finput_005f2(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </dd>    \n");
      out.write("    ");
      if (_jspx_meth_mjl_005fcommand_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    ");
      if (_jspx_meth_mjl_005fcommand_005f1(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    <dt>\n");
      out.write("      <label> ");
      if (_jspx_meth_fmt_005fmessage_005f2(_jspx_parent, _jspx_page_context))
        return true;
      out.write(" </label>\n");
      out.write("    </dt>\n");
      out.write("    <dd>\n");
      out.write("      ");
      if (_jspx_meth_mjl_005finput_005f3(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("    </dd>    \n");
      out.write("    ");
      if (_jspx_meth_mjl_005fcommand_005f2(_jspx_parent, _jspx_page_context))
        return true;
      out.write("    \n");
      out.write("  </dl>\n");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws JspException
    {
      JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        this.jspContext.getELContext().putContext(JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
          case 1:
            invoke1( out );
            break;
        }
      }
      catch( Throwable e ) {
        if (e instanceof SkipPageException)
            throw (SkipPageException) e;
        throw new JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
