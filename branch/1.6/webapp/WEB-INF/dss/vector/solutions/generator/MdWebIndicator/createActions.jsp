<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

  <mdss:localize var="Create_Localize" key="Create" />
  <mdss:localize var="Cancel_Localize" key="Cancel" />
  <c:choose>
    <c:when test="${isComposite}">
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.createMdField.button" value="${Create_Localize}" action="dss.vector.solutions.form.MdFormAdminController.createCompositeField.mojo" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelMdField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelCompositeField.mojo" />
    </c:when>
    <c:otherwise>
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.createMdField.button" value="${Create_Localize}" action="dss.vector.solutions.form.MdFormAdminController.createMdField.mojo" />
      <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelMdField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelMdField.mojo" />
    </c:otherwise>
  </c:choose>
