<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="com.runwaysdk.business.ViewDTO"%>
<c:set var="page_title" value="Ontology_Fields" scope="request" />

<script type="text/javascript" src="js/OntologyFields.js"></script>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<dl>
<c:forEach items="${moFields}" var="moField">
 <dt>
   ${moField.mdClassLabel} : ${moField.mdAttributeLabel}<br />
   <button type="button" value="Add Root" id=""></button>
 </dt> 
 <dd>
   <table>
     <tr><th>Term</th><th>Selectable</th><th>Edit</th><th>Delete</th></tr>
   </table>
 </dd>
 
 
 <script type="text/javascript">
 </script>
</c:forEach>
</dl>