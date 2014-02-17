<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_ReportItem" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.report.ReportItem.form.id" name="dss.vector.solutions.report.ReportItem.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="reportLabel">
        ${item.reportLabel}
      </mjl:dt>
      <mjl:dt attribute="outputFormat">
        <c:forEach items="${item.outputFormatEnumNames}" var="enumName">
          ${item.outputFormatMd.enumItems[enumName]}                  
        </c:forEach>
      </mjl:dt>      
      <mjl:dt attribute="cacheDocument">
        ${item.cacheDocument ? item.cacheDocumentMd.positiveDisplayLabel : item.cacheDocumentMd.negativeDisplayLabel}
      </mjl:dt>      
      <mjl:dt attribute="reportName">
        ${item.reportName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.report.ReportItem.form.edit.button" value="Edit" action="dss.vector.solutions.report.ReportItemController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.report.ReportItem.viewAll.link" action="dss.vector.solutions.report.ReportItemController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
