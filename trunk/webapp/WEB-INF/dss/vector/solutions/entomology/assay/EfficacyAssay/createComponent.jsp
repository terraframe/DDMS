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
    selectSearch.setFilter(searchFilter);


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
        <mjl:input id="geoIdEl" param="none" type="text" />
        <a href="#" id="searchOpener"><img src="./imgs/icons/world.png" /></a>
        <mjl:input id="geoEntityId" param="geoEntity" type="hidden" />
        <mjl:messages attribute="geoEntity">
          <mjl:message />
        </mjl:messages>
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
      <dt><label> ${item.ageRangeMd.displayLabel} </label></dt>
      <dd>
      <dl>
        <mjl:struct param="ageRange">
          <dt><label> ${item.ageRange.startPointMd.displayLabel} </label></dt>
          <dd><mjl:input type="text" param="startPoint" /> <mjl:messages attribute="startPoint">
            <mjl:message />
          </mjl:messages></dd>
          <dt><label> ${item.ageRange.endPointMd.displayLabel} </label></dt>
          <dd><mjl:input type="text" param="endPoint" /> <mjl:messages attribute="endPoint">
            <mjl:message />
          </mjl:messages></dd>
        </mjl:struct>
      </dl>
      </dd>
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

      <mjl:dt attribute="timeOnSurface">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>

      <mjl:dt attribute="surfacePostion">
        <mjl:select var="current" valueAttribute="enumName" items="${surfacePostion}" param="surfacePostion">
          <c:choose>
            <c:when test="${mjl:contains(item.surfacePostionEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.surfacePostionMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.surfacePostionMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
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