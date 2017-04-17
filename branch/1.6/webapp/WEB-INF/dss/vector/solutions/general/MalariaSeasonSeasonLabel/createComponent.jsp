<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Create_MalariaSeasonSeasonLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.MalariaSeasonSeasonLabel.form.id" name="dss.vector.solutions.general.MalariaSeasonSeasonLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize var="Create_Localize" key="Create" />
    <mjl:command name="dss.vector.solutions.general.MalariaSeasonSeasonLabel.form.create.button" value="${Create_Localize}" action="dss.vector.solutions.general.MalariaSeasonSeasonLabelController.create.mojo" />
  </mjl:form>
</dl>
