<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.generated.SurfaceDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl');
      var geoEntityId = document.getElementById('geoEntityId');
      var geoEntityName = document.getElementById('located_in');

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityId.value = selected.getGeoEntityId();
        geoEntityName.innerHTML = selected.getEntityName();
      }
      else
      {
        geoId.value = '';
        geoEntityId.value = '';
        geoEntityName.innerHTML = '';
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

<c:set var="window_title" value="Efficacy bioassay data entry" scope="request" />
<c:set var="page_title" value="Enter New Data" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="geoEntity">
        <mjl:input id="geoIdEl" param="none" type="text" value="${item.geoEntity.geoId}" maxlength="16"/>
        <a href="#" id="searchOpener"><img src="./imgs/icons/world.png" /></a>
        <mjl:input id="geoEntityId" param="geoEntity" type="hidden" /><br/>
        (<span id ="located_in">${item.geoEntity.entityName}</span>)
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <mjl:select var="current" valueAttribute="id" items="${testMethod}" param="testMethod">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <mjl:select var="current" valueAttribute="id" items="${specie}" param="specie">
          <mjl:option>
            ${current.termName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="colonyName">
        <mjl:input type="text" param="colonyName" />
      </mjl:dt>
      <mjl:dt attribute="ageRange">
      <dl>
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint" type="text"  />
          <mjl:dt attribute="endPoint" type="text"  />
        </mjl:struct>
      </dl>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mjl:select var="current" valueAttribute="enumName" items="${sex}" param="sex">
          <mjl:option>
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>

      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
      </mjl:dt>

      <mjl:dt attribute="timeOnSurface" type="text"/>

      <mjl:dt attribute="surfacePostion">
        <mjl:select var="current" valueAttribute="enumName" items="${surfacePostion}" param="surfacePostion">
              <mjl:option>
                ${item.surfacePostionMd.enumItems[current.enumName]}
              </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        <mjl:input type="text" param="quantityTested" />
      </mjl:dt>
      <mjl:dt attribute="quantityDead">
        <mjl:input type="text" param="quantityDead" />
      </mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.create.button" />
</mjl:form>
<div id="cal1Container" class="yui-skin-sam"></div>