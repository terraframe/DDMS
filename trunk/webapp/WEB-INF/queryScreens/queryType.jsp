<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr"%>

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.query.SelectableGroup"%>


<c:if test="${page_title == null}">
  <c:set var="page_title" value="Query_Form"  scope="request"/>
</c:if>

<jsp:include page="../templates/header.jsp" />
<jsp:include page="/WEB-INF/inlineError.jsp" />
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false" />
<jwr:script src="/bundles/formBundle.js" useRandomParam="false"/>

<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
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

    var selectableGroups = [];

    <%
    List<SelectableGroup> groups = (List<SelectableGroup>) request.getAttribute("groups");
    for (SelectableGroup group : groups)
    {
      out.write("selectableGroups.push(" + group.serialize() + ");\n");
    }
    %>

    var query = new MDSS.QueryType(selectableGroups, queryList);
    query.render();
});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>
