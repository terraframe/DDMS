
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="com.runwaysdk.web.json.JSONController"%><script type="text/javascript">
  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  
    String[] types = {BrowserRootDTO.CLASS, BrowserRootViewDTO.CLASS, BrowserRootController.CLASS, TermViewDTO.CLASS, TermDTO.CLASS};
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>    
</script>