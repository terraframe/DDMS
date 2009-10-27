<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="str" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>


<%@page import="java.util.List"%><c:if test="${(errorMessage != null && errorMessage != '') || errorMessageArray != null}">
<div class="alert alertbox">
<p>
</c:if>

<c:if test="${errorMessage != null && errorMessage != ''}">
  <str:replace replace="\\\\n" with="<br />NL" newlineToken="NL"><str:escape>${errorMessage}</str:escape></str:replace> 
</c:if>

<c:if test="${errorMessageArray != null}">
  <str:escape><str:join separator="<br />" items="${errorMessageArray}"></str:join></str:escape>
</c:if>

<c:if test="${(errorMessage != null && errorMessage != '') || errorMessageArray != null}">
</p>
</div>
</c:if>

<%
  Object array = request.getAttribute("messageArray");
  String messages = "null";

  if(array != null && array instanceof String[])
  {
    List<String> list = Arrays.asList((String[]) array); 

    messages = "['" + Halp.join(list, "','" ) + "']";
  }
%>

<script type="text/javascript">

(function(){
	  YAHOO.util.Event.onDOMReady(function(){
      var messages = <%=messages%>;

      if(Mojo.Util.isArray(messages)) {
          for(var i = 0; i < messages.length; i++) {
              alert(messages[i]);
          }
      }		  
	  });
})();

</script>