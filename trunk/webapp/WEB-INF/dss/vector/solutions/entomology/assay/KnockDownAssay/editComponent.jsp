<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>

<c:set var="page_title" value="Edit_Knockdown_Assay"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.name" id="dss.vector.solutions.entomology.assay.KnockDownAssay.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.update.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.delete.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.KnockDownAssayController.cancel.mojo" name="dss.vector.solutions.entomology.assay.KnockDownAssay.form.cancel.button" />
  </dl>
</mjl:form>
