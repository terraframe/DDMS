<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<jsp:include page="geoEntityTreeComponent.jsp"/>
<script type="text/javascript">
<%ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
String rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);
String[] types = new String[] { GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTreeController.CLASS };
//String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
// out.print(js);%>
MDSS.SelectSearchRootId = '<%=rootId%>';


YAHOO.util.Event.onDOMReady(function(){

for each (geoInput in YAHOO.util.Dom.getElementsByClassName("geoInput"))
{
	//var geoInput = new YAHOO.util.Element(geoIn);

    var opener = document.createElement('img');
    opener.src = "./imgs/icons/world.png";
    opener.id = geoInput.id +'Go';
    opener = new YAHOO.util.Element(opener);
    YAHOO.util.Dom.insertAfter(opener,geoInput);

    var geoInfo = document.createElement('div');
    geoInfo.id = geoInput.id +'Info';
    geoInfo.innerHTML = '()';
    YAHOO.util.Dom.insertAfter(geoInfo,opener);


    var geoSearchResults = document.createElement('div');
    geoSearchResults.id = geoInput.id +'_results';
    geoSearchResults.className = "yui-panel-container show-scrollbars shadow";
    //geoSearchResults.setStyle('visibility','hidden');
    YAHOO.util.Dom.insertAfter(geoSearchResults,geoInfo);


    //alert('test');
    var radios = YAHOO.util.Dom.getElementsByClassName("filterType");
    var validTypes = radios.map(function(c){return c.value}).filter(function(c){return c != ''});

    function selectHandler(selected)
    {

      var geoId = document.getElementById('geoIdEl');

      if(selected != null)
      {

    	if(typeof selected == 'string'){
          var request = new MDSS.Request({
              selectHandler: this,
              onSuccess : function(result){
                selectHandler(result);
            }
          });
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getView(request, selected);
          return;
        }

        if (validTypes.indexOf(selected.getEntityType()) > -1 )
        {
            YAHOO.util.Dom.removeClass(geoInfo,'alert');
            geoId.value = selected.getGeoId();
            geoInfo.innerHTML = selected.getEntityName() + ' (' + selected.getTypeDisplayLabel()+ ')';
        }
        else
        {
            YAHOO.util.Dom.addClass(geoInfo,'alert');
            geoId.value = '';
            geoInfo.innerHTML = selected.getEntityName() + ' (' + selected.getTypeDisplayLabel()+ ') is not a valid Geo Entity Type for this Field';
        }
      }
      else
      {
        YAHOO.util.Dom.removeClass(geoInfo,'alert');
        geoInput.value = '';
        geoInfo.innerHTML = '';
      }
    }

    function checkManualEntry(selected)
    {

      var geoId = document.getElementById('geoIdEl');

      var request = new MDSS.Request({
          selectHandler: this,
          onSuccess : function(result){
            selectHandler(result);
        }
      });
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, geoId.value);

    }

    YAHOO.util.Event.on(geoInput, 'change', checkManualEntry, null, null);

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);
    selectSearch.setFilter('');

    for each (radio in radios)
    {
      YAHOO.util.Event.on(radio, 'click', function(e, obj){
        var radio = e.target;
        if(radio.checked)
        {
          var filter = e.target.value;
          this.setFilter(filter);
        }

      }, null, selectSearch);
    }

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
}


selectSearch.initialize();

var div = document.getElementById('geoIdEl'+'_results');
var panel = new YAHOO.widget.Panel(div, {
  width:'400px',
  height:'200px',
  zindex:15,
  draggable: false,
  close: true
});


/**
 * Performs an ajax search based on the entity
 * name and type.
 */
ajaxSearch =  function(e)
{
  var input = document.getElementById('geoIdEl');
  var value = input.value;
  var type = selectSearch._filterType;
  var resultPanel = panel; //document.getElementById('geoIdEl'+'_results');

  // must have at least 2 characters ready
  if(value.length < 2)
  {
    return;
  }

  var request = new MDSS.Request({
    resultPanel: resultPanel,
    searchValue: value,
    selectHandler: selectHandler,
    input: input,
    searchRef: this,
    type: type,
    // don't paint a loading bar. It's too slow for this
    // type of call
    onSend: function(){},
    onComplete: function(){},
    onSuccess: function(query)
    {
      var resultSet = query.getResultSet();

      var outer = document.createElement('div');

      var header = document.createElement('div');
      header.innerHTML = '<h3>'+MDSS.Localized.Search_Results+'</h3><hr />';
      outer.appendChild(header);

      var inner = document.createElement('div');
      YAHOO.util.Dom.addClass(inner, 'entitySearchResults');
      outer.appendChild(inner);

      var ul = document.createElement('ul');
      YAHOO.util.Dom.addClass(ul, 'selectableList')
      YAHOO.util.Event.on(ul, 'mouseover', function(e, obj){

        var li = e.target;
        var ul = e.currentTarget;
        if(li.nodeName === 'SPAN')
        {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI')
        {
          return;
        }

        // clear all lis of their current class
        var lis = YAHOO.util.Selector.query('li.currentSelection', ul);
        for(var i=0; i<lis.length; i++)
        {
          YAHOO.util.Dom.removeClass(lis[i], 'currentSelection');
        }

        YAHOO.util.Dom.addClass(li, 'currentSelection');
      });

      YAHOO.util.Event.on(ul, 'click', function(e, obj){

        var li = e.target;
        var ul = e.currentTarget;
        if(li.nodeName === 'SPAN')
        {
          li = li.parentNode;
        }

        if(li.nodeName !== 'LI')
        {
          return;
        }

        var geoEntityId = li.id;
        resultPanel.hide();
        selectHandler(geoEntityId);

      }, {input: this.input, panel: this.resultPanel}, this.searchRef);

      var idAttr = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.ID;
      var entityNameAttr = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.ENTITYNAME;
      var geoIdAttr = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.GEOID;
      for(var i=0; i<resultSet.length; i++)
      {
        var valueObj = resultSet[i];

        var li = document.createElement('li');
        li.id = valueObj.getValue(idAttr);
        var displayStr = valueObj.getValue(entityNameAttr) + ' - ' + valueObj.getValue(geoIdAttr) ;
        var matched = displayStr.replace(new RegExp("(.*?)("+this.searchValue+")(.*?)", "gi"), "$1<span class='searchMatch'>$2</span>$3");
        li.innerHTML = matched;

        ul.appendChild(li);
      }

      inner.appendChild(ul);

      this.resultPanel.setBody(outer);
      this.resultPanel.render();
      this.resultPanel.show();
      this.resultPanel.bringToTop();

      // refocus the input field
      this.input.focus();
    }
  });

  Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request, type, value);
}

YAHOO.util.Event.on(geoInput, 'keyup', ajaxSearch, null, null);


},null,null);

</script>
<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>