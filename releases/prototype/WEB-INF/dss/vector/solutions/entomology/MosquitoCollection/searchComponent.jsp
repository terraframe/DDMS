<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinalSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinalSiteDTO"%>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

MDSS.currentSearchModal = null;

  YAHOO.util.Event.onDOMReady(function(){
    
    var opener = new YAHOO.util.Element("searchOpener");
    opener.on("click", function(){
    
      var request = new Mojo.ClientRequest({
        onSuccess : function(html){
          
          // use modal to contain MDSS101
          MDSS.currentSearchModal = new YAHOO.widget.Panel("searchSelectModal",  {
            width:"100%", 
            height: "100%",
            fixedcenter:true, 
            close:true, 
            draggable:false, 
            zindex:4,
            modal:true,
            visible:true
          });
    
          MDSS.currentSearchModal.setBody(html);
          MDSS.currentSearchModal.render(document.body);
          

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
  
          // select list and tree share the same handler
          //var radios = YAHOO.util.Selector.query('input[type="radio"]', 'searchMosquitoCollections');
          var filterType = '';
          /*
          for(var i=0; i<radios.length; i++)
          {
            var radio = radios[i];
            if(radio.checked)
            {
              filterType = radio.value;
            }
          }
          */
          
          MDSS.SelectSearch.initialize(selectHandler, selectHandler, filterType); 
        },
        onFailure : function(e){
          alert(e.getLocalizedMessage());
        }
      });
    
      if(MDSS.currentSearchModal == null)
      {
        Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displaySelectSearch(request, '<%= (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID) %>');
      }
      else
      {
        MDSS.currentSearchModal.show();
      }
    });
  }, null, true);

</script>

<%
  request.setAttribute("SentinalSiteClass", SentinalSiteDTO.CLASS);
  request.setAttribute("NonSentinalSiteClass", NonSentinalSiteDTO.CLASS);
%>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollection.search" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt> Filter </dt>
    <dd>
        All <input type="radio" name="filterType" value="" checked="checked" />
        Sentinal Site <input type="radio" name="filterType" value="${SentinalSiteClass}" />
        (Non) Sentinal Site <input type="radio" name="filterType" value="${NonSentinalSiteClass}" />
    </dd>
    <dt> Geo Id </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" /><span id="searchOpener"><img src="./imgs/icons/world.png"/></span></dd>
    <dt> Date </dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick" id="collectionDate"/></dd>
  </dl>  
  <br>
  <br>
  <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionController.searchByGeoIdAndDate.mojo" name="search.button"
  value="Search"
  />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>

