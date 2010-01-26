<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<c:set var="page_title" value="Edit_Efficacy_bioassay" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.update.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.delete.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.cancel.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.cancel.button" />
  </dl>
</mjl:form>
