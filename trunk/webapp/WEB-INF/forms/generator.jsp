<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>





<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.AttributeGeoHierarchyDTO"%>

<%@page import="dss.vector.solutions.form.FormObjectController"%>

<%@page import="com.runwaysdk.system.metadata.MdWebInteger"%>
<%@page import="com.runwaysdk.system.metadata.MdWebLongDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebIntegerDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDecimalDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDoubleDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebFloatDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDateDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebTextDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebBooleanDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCharacterDTO"%>



<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="com.runwaysdk.system.metadata.MdWebBreakDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCommentDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebHeaderDTO"%><c:set var="page_title" value="Form_Generator"  scope="request"/>

<jsp:include page="../templates/header.jsp"></jsp:include>

<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

//String[] types = new String[]{FormObjectController.CLASS};
String[] types = new String[]{
MdFormAdminController.CLASS,
    
// WebNumber (excluding float)
MdWebIntegerDTO.CLASS,
MdWebLongDTO.CLASS,
MdWebDecimalDTO.CLASS,
MdWebDoubleDTO.CLASS,
MdWebFloatDTO.CLASS,

// WebMoment (excluding DateTime, Time)
MdWebDateDTO.CLASS,
// Character, Text, Boolean
MdWebCharacterDTO.CLASS,
MdWebTextDTO.CLASS,
MdWebBooleanDTO.CLASS,

MdWebBreakDTO.CLASS,
MdWebHeaderDTO.CLASS,
MdWebCommentDTO.CLASS
};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>

YAHOO.util.Event.onDOMReady(function(){

/*
  var mdFormId = '<%= request.getParameter("mdFormId") %>';
  var request = new MDSS.Request({
    onSuccess : function(formObjectJSON){
      
      var webForm = new com.runwaysdk.form.web.WebFormObject.parseFromJSON(formObjectJSON);
      var formRender = new com.runwaysdk.form.FormObjectRenderer(webForm);
      formRender.render('formContainer');
    }
  });
  
  dss.vector.solutions.form.FormObjectController.newInstance(request, mdFormId);

  
      YAHOO.util.Event.on('availableMdFields', 'click', function(e){
      
        var request = new MDSS.Request({
          onSuccess : function(html){
     
            // FIXME use Async queue
            var executable = MDSS.util.extractScripts(html);
            var html = MDSS.util.removeScripts(html);     
          
            document.getElementById('formContainer').innerHTML = html;
          }
        });
      
        var mdFieldType = e.target.id;
        
        dss.vector.solutions.form.MdFormAdminController.newMdField(request, mdFieldType);
        
      }, null, this);
*/
try
{
var UI = Mojo.Meta.alias("com.runwaysdk.ui.*");
UI.Manager.setFactory("YUI3");
var factory = UI.Manager.getFactory();

var grid = factory.newDataTable('${mdClassType}');
grid.render('#formContainer');
}catch(e){console.log(e);}
});
</script>
<div id="formContainer">
</div>

<jsp:include page="../templates/footer.jsp"></jsp:include>