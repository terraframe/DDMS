<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Form_Admin" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:forEach var="item" items="${forms}">
    <mjl:commandLink name="view.link" action="dss.vector.solutions.form.MdFormAdminController.view.mojo">
        <mjl:property name="id" value="${item.id}" />
        ${item.displayLabel}
    </mjl:commandLink>
         <br />
</c:forEach>

<mjl:commandLink name="MdFormAdminController.newInstance" action="dss.vector.solutions.form.MdFormAdminController.newInstance.mojo">
  <mdss:localize key="Create_a_new_form" />
</mjl:commandLink>
