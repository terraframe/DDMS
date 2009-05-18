<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.generated.SurfaceDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">

<c:set var="window_title" value="Efficacy_bioassay_data_entry" scope="request" />
<c:set var="page_title" value="Enter_New_Data" scope="request" />

YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl');

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
      }
      else
      {
        geoId.value = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);
    var searchFilter = '<%=SurfaceDTO.CLASS%>';
    selectSearch.setFilter('');


    var opener = new YAHOO.util.Element("searchOpener");
    opener.on("click", function(){

      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    });
  }, null, true);

</script>


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
