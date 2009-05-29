<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.generated.SurfaceDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= SurfaceDTO.CLASS %>');
</script>

<c:set var="window_title" value="Efficacy_bioassay_data_entry" scope="request" />
<c:set var="page_title" value="Enter_New_Data" scope="request" />



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
