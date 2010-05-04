<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="entity" scope="request" value="${item.geoEntity}" />
<c:set var="page_title" value="Control_intervention"  scope="request"/>

<mjl:form name="ControlIntervention.search" method="POST" id="ControlIntervention">
  <dl>
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="startDate">
        <mjl:input param="collectionDate" type="text" classes="DatePick NoFuture" id="collectionDate"/>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate"/>
      </mjl:dt>
      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" political="false" populated="false" spray="false" urban="true" listener="MDSS_validGeoEntityHandler" value="${entity}" />
      </mjl:dt>
      <mjl:dt attribute="aggregationUniversal">
        <mjl:select param="aggregationUniversal" items="${universals}" var="current" valueAttribute="geoHierarchyId" includeBlank="true" id="universals">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>  
    </mjl:component>
    <dt>
      <label><fmt:message key="DATA_TYPE"/></label>
    </dt>
    <dd>
      <input name="dataType" type="radio" value="INDIVIDUAL_PREMISE" /> <fmt:message key="premises_units_visit"/>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.intervention.monitor.ControlInterventionController.searchByParameters.mojo" name="search.button" value="Search"/>
  </dl>
</mjl:form>

<script>
  var MDSS_validGeoEntityHandler = function(event) {
	var loadUniversals = function(id) {
      var request = new MDSS.Request({
        onSuccess : function(views) {
          var universals = document.getElementById('universals');

          Selectbox.removeAllOptions(universals);

          for(var i in views) {
            var view = views[i];

            Selectbox.addOption(universals, view.getDisplayLabel(), view.getGeoHierarchyId(), false);
          }
        }
      });
      
      dss.vector.solutions.geo.GeoHierarchyView.getUrbanHierarchies(request, id);                    
	};
    var type = event.getType();

    if(type == MDSS.Event.AFTER_VALID_SELECTION) {
      var value = event.getValue();
      var selected = value.selected;

      if(selected != null) {
        if(selected instanceof dss.vector.solutions.geo.GeoEntityView) {
          loadUniversals(selected.getGeoEntityId());
        }
        else {
          loadUniversals(selected.id);
        }
      }
    }
  };

</script>