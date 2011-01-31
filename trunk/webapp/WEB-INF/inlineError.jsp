<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="str" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>


<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.ErrorUtility"%><c:if test="${(errorMessage != null && errorMessage != '') || errorMessageArray != null}">
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

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var messages = <%=ErrorUtility.getMessagesForJavascript(request)%>;

    if(Mojo.Util.isArray(messages)) {
      for(var i = 0; i < messages.length; i++) {

        var message = messages[i];
        var html = '<form action="dss.vector.solutions.FileController.exportToFile.mojo" method="POST">';
            html += '<input type="hidden" name="message" value="'+message+'" />';
            html += '<input type="hidden" name="fileName" value="alert" />';
            html += '<p>' + message + '</p>';
            html += '<div style="margin-top:10px">';
            html += '<input type="submit" value="Export" />';
            html += '</form>';

        new MDSS.ErrorModal(html);
      }
    } 
  });
})();

</script>