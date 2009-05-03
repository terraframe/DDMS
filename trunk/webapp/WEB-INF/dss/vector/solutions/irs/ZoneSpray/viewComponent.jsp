<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>


<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.irs.ZoneSprayViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayStatusViewDTO"%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <dl>
    <mjl:input value="${item.sprayId}" type="hidden" param="id" />      
    
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="surfaceType" >          
        <ul>
          <c:forEach var="enumName" items="${item.surfaceTypeEnumNames}">
            <li>${item.surfaceTypeMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>        
    </mjl:component>
  </dl>  
  <mjl:command value="Edit" action="dss.vector.solutions.irs.ZoneSprayController.edit.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.edit.button" />
</mjl:form>
