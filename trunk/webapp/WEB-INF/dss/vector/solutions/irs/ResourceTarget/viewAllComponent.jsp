<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.PropertyDTO"%>

<c:set var="page_title" value="Search_Spray_Team_Targets"  scope="request"/>




<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>


<mjl:form name="dss.vector.solutions.irs.GeoTargetController.view.mojo" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt><fmt:message key="Team "/></dt>
    <dd>
        <mjl:select var="sprayTeam" valueAttribute="id" items="${sprayTeams}" param="id" >
       <mjl:option>
          ${team.teamCode}
       </mjl:option>
    </mjl:select>
   </dd>

    <dt> <label> <fmt:message key="Season"/></label></dt>
    <dd>
    <input id="year" type="text" name="targetYear" value="<fmt:formatDate pattern="yyyy" value="${now}"/>" maxlength="4" />
    </dd>
  </dl>
  <br>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.irs.ResourceTargetController.view.mojo" name="search.button"value="Search"/>
</mjl:form>