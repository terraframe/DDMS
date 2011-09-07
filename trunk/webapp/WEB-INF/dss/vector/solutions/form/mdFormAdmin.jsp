<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<c:set var="page_title" value="MdForm_Admin"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>


<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

  //new MDSS.MdFormAdmin('adminPanel').render();
  //var mapList = <%= (String) request.getAttribute("mapList") %>
});
</script>

<div class="yui-skin-sam">
  <div id="adminPanel"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>