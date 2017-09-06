<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<c:set var="page_title" scope="request" value="View_All_OfflineBasemapManagement" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<h2><mdss:localize key="offline_basemap_instructions_header" /></h2>

<p><mdss:localize key="offline_basemap_basic_instructions1" /></p>
<ol style="margin-left:25px;">
  <li style="list-style:decimal;"><mdss:localize key="offline_basemap_basic_instructions2" /></li>
  <li style="list-style:decimal;"><mdss:localize key="offline_basemap_basic_instructions3" /></li>
  <li style="list-style:decimal;"><mdss:localize key="offline_basemap_basic_instructions4" /></li>
  <li style="list-style:decimal;"><mdss:localize key="offline_basemap_basic_instructions5" /></li>
  <li style="list-style:decimal;"><mdss:localize key="offline_basemap_basic_instructions6" /></li>
</ol>

<h2><mdss:localize key="offline_basemap_basic_download_header" /></h2>
<ol style="margin-left:25px;">
  <li style="list-style:decimal;"><a href="http://download.geofabrik.de/" target="_blank">http://download.geofabrik.de/</a></li>
</ol>

<h3 style="color:red;margin:10px 0px 10px 0;"><mdss:localize key="offline_basemap_instructions_important" /></h3>

<form id="basemapForm" action="dss.vector.solutions.basemap.OfflineBasemapController.submit.mojo" method="post" 
style="width:300px;left:0;margin-left:0;border:1px solid #CCCCCC;margin-top:10px;"
onsubmit="return checkForm(this);">
  <fieldset style="background:#f6f5ed; border:none;">
	  <div>
	  
	    <c:forEach items="${availableFiles}" var="availableFile" varStatus="loop">
	      <div style="margin-bottom:10px;">
             <c:if test="${not availableFile.value}">
               <input id="file${loop.index}" name="file${loop.index}" type="checkbox" value="${availableFile.key}" >
             </c:if>
             <c:if test="${availableFile.value}">
               <input id="file${loop.index}" name="file${loop.index}" type="checkbox" value="${availableFile.key}" checked>
             </c:if>
             
             <label for="file${loop.index}">${availableFile.key}</label>
        </div>
      </c:forEach>
	  
	  </div>
	  <div>
	    <button id="basemapRebuildBtn" type="submit" form="basemapForm" value="Submit" style="padding:4px;"><mdss:localize key="offline_basemap_button" /></button>
	  </div>
  </fieldset>
</form>

<%-- <c:if test="${uploadStatus}"> --%>
<%--    <h2><mdss:localize key="offline_map_data_upload_complete" /></h2> --%>
<%-- </c:if> --%>

<script type="text/javascript">

  function checkForm(form)
  {
    $("body").append('<div style="width:300px;position:absolute;margin:0 auto;top:50%;bottom:0;left:0;right:0;height:50px;background:rgba(169, 169, 169, .80);"><img src="imgs/rel_interstitial_loading.gif" style="margin:0 auto;display:block;transform:translateY(15px);" /><div>')
    
    form.basemapRebuildBtn.disabled = true;
    return true;
  }

</script>