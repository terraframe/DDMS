<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr"%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>


<%@page import="dss.vector.solutions.query.SelectableGroup"%>

<c:set	var="page_title" value="Query_Type" scope="request" />

<jsp:include page="../templates/header.jsp" />
<jsp:include page="/WEB-INF/inlineError.jsp" />
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false" />
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<script>
<jsp:include page="/js/Query/QueryType.js.jsp" />
</script>

<%
  ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  List<String> typesToLoad = (List<String>) request.getAttribute("typesToLoad");
%>

<%=Halp.loadTypes(typesToLoad)%>

<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);


    var queryList = <%=(String) request.getAttribute("queryList")%>;

    var item = new Mojo.$.<%=(String) request.getAttribute("type")%>();
    
    var attributes = <%=request.getAttribute("attributes")%>;

    var dropDownMaps = {<%=(String) request.getAttribute("dropDownMaps")%>};
        
    var columns = attributes.map(MDSS.QueryBaseNew.mapAttribs, {obj:item, suffix:'_root', dropDownMaps:dropDownMaps});

    var selectableGroups = [{title:'<%=(String) request.getAttribute("label")%>', values:columns, group:"root", klass:'<%=(String) request.getAttribute("type")%>'}];


    <%
    List<SelectableGroup> extraGroups = (List<SelectableGroup>) request.getAttribute("extraGroups");
    for (SelectableGroup extraGroup : extraGroups)
    {
      out.write("selectableGroups.push(" + extraGroup.serialize() + ");\n");
    }
    %>

    var query = new MDSS.QueryType(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>