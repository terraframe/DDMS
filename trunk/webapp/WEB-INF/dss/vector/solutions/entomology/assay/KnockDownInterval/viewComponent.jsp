<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_KnockDownInterval" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.entomology.assay.KnockDownInterval.form.id" name="dss.vector.solutions.entomology.assay.KnockDownInterval.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="amount">
        ${item.amount}
      </mjl:dt>
      <mjl:dt attribute="assay">
        ${item.assay.keyName}
      </mjl:dt>
      <mjl:dt attribute="intervalTime">
        ${item.intervalTime}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.entomology.assay.KnockDownInterval.form.edit.button" value="Edit" action="dss.vector.solutions.entomology.assay.KnockDownIntervalController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.entomology.assay.KnockDownInterval.viewAll.link" action="dss.vector.solutions.entomology.assay.KnockDownIntervalController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
