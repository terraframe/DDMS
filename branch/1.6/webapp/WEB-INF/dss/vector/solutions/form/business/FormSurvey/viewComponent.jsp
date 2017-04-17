<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_FormSurvey" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.form.business.FormSurvey.form.id" name="dss.vector.solutions.form.business.FormSurvey.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="disease">
        ${item.disease.keyName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.form.business.FormSurvey.form.edit.button" value="Edit" action="dss.vector.solutions.form.business.FormSurveyController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.form.business.FormSurvey.viewAll.link" action="dss.vector.solutions.form.business.FormSurveyController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
