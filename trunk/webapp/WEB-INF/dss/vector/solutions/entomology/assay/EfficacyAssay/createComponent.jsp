<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.generated.SurfaceDTO"%>

<c:set var="window_title" value="Efficacy_bioassay_data_entry" scope="request" />
<c:set var="page_title" value="Enter_New_Data" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= SurfaceDTO.CLASS %>');
</script>


<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.create.button" />
  </dl>
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div>