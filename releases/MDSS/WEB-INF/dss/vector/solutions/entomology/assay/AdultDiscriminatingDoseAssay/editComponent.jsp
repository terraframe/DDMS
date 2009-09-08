<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<c:set var="page_title" value="Edit_ADDA" scope="request" />
<mjl:form name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.update.mojo"
      name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.update.button" classes="submitButton" />
    <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.delete.mojo"
      name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.delete.button" classes="submitButton" />
    <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayController.cancel.mojo"
      name="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.form.cancel.button" classes="submitButton" />
  </dl>
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div>