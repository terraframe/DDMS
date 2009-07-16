/**
 * Class that implements single select searching for GeoEntities
 */
MDSS.SingleSelectSearch = Mojo.Class.create();
MDSS.SingleSelectSearch.prototype = Mojo.Class.extend(MDSS.AbstractSelectSearch, {

  /**
   * Constructor.
   */
  initialize : function()
  {
    MDSS.AbstractSelectSearch.prototype.initialize.call(this);

    this._currentSelection = null;
    this._CURRENT_SELECTION = 'currentSelection';
  },

  /**
   * Replaces the current selection with the given GeoEntityView.
   */
  _updateSelection : function(geoEntityView)
  {
    var div = document.getElementById(this._CURRENT_SELECTION);
    div.innerHTML = geoEntityView.getEntityName() + ' ('+geoEntityView.getGeoId()+')';

    this._currentSelection = geoEntityView;
  },

  /**
   * Notifies the select handler that a GeoEntity
   * has been selected. The GeoEntityView is passed
   * to the handler.
   */
  _notifySelectHandler : function(geoEntityView, updateSelection)
  {
  	if(updateSelection)
  	{
  	  this._updateSelection(geoEntityView);
  	}

  	if(Mojo.util.isFunction(this._selectHandler))
  	{
  	  this._selectHandler(geoEntityView);
  	}
  },

  getSelectHandler : function()
  {
    return(this._selectHandler);
  },

  /**
   * Returns 1 as the start index. One option is for the
   * Select One field.
   */
  _getStartIndex : function()
  {
    return 1;
  },

  /**
   * Returns the appropriate controller action to
   * render the select search component.
   */
  _getControllerAction : function()
  {
    return Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displaySingleSelectSearch;
  },

  _disableAllowed : function()
  {
  	return true;
  }
});



YAHOO.util.Event.onDOMReady(function(){

  for each (geoInput in YAHOO.util.Dom.getElementsByClassName("geoInput")){

  	var opener = document.createElement('img');
    opener.src = "./imgs/icons/world.png";
    opener.id = geoInput.id +'Go';
    opener = new YAHOO.util.Element(opener);
    YAHOO.util.Dom.addClass(opener,'geoOpener');
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

    var radios = YAHOO.util.Dom.getElementsByClassName("filterType");
    var validTypes = radios.map(function(c){return c.value}).filter(function(c){return c != ''});

    function selectHandler(selected)
    {
      var geoId = document.getElementById('geoIdEl');
      var geoEntityId = document.getElementById('geoEntityId');

      if(selected != null)
      {

    	if(typeof selected == 'string'){
          var request = new MDSS.Request({
              selectHandler: this,
              onSend: function(){},
              onComplete: function(){},
              onFailure: function(){},
              onSuccess : function(result){
                selectHandler(result);
            }
          });
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getView(request, selected);
          return;
        }

        if (validTypes.length == 0  || validTypes.indexOf(selected.getEntityType()) > -1 )
        {
            YAHOO.util.Dom.removeClass(geoInfo,'alert');
            geoId.value = selected.getGeoId();
            if(geoEntityId) {
            	geoEntityId.value = selected.getGeoEntityId();
            }
            geoInfo.innerHTML = selected.getEntityName() + ' (' + selected.getTypeDisplayLabel()+ ')';
        }
        else
        {
            YAHOO.util.Dom.addClass(geoInfo,'alert');
            geoId.value = '';
            geoInfo.innerHTML = selected.getEntityName() + ' (' + selected.getTypeDisplayLabel()+ ') is not a valid Geo Entity Type for this Field';
            if(geoEntityId) {
            	geoEntityId.value = selected.getGeoEntityId();
            }
        }
      }
      else
      {
        YAHOO.util.Dom.removeClass(geoInfo,'alert');
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(geoEntityId) geoEntityId.value ='';
      }

      if(typeof onValidGeoEntitySelected !== 'undefined' && Mojo.util.isFunction(onValidGeoEntitySelected))
      {
    	  onValidGeoEntitySelected();
      }      
    }

    function checkManualEntry(selected)
    {

      var geoId = document.getElementById('geoIdEl');

      var request = new MDSS.Request({
          selectHandler: this,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
            selectHandler(result);
        }
      });

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, geoId.value);

    }

    YAHOO.util.Event.on(geoInput, 'blur', checkManualEntry, null, null);

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);

    var defaultFilter = '';
    for each (radio in radios)
    {
      if(radio.checked)
      {
        defaultFilter = radio.value;
      }
    
      YAHOO.util.Event.on(radio, 'click', function(e, obj){
        var radio = e.target;
        if(radio.checked)
        {
          var filter = e.target.value;
          this.setFilter(filter);
        }

      }, null, selectSearch);
    }
    
    selectSearch.setFilter(defaultFilter);   

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

    //selectSearch.initialize();

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
          var typeAttr = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.TYPE;
          for(var i=0; i<resultSet.length; i++)
          {
            var valueObj = resultSet[i];

            var li = document.createElement('li');
            li.id = valueObj.getValue(idAttr);

            var construct = Mojo.util.getType(valueObj.getValue(typeAttr));
            var type = new construct(); // use new instance as a template
            var localizedType = type.getTypeMd().getDisplayLabel();


            var displayStr = valueObj.getValue(entityNameAttr) + '('+ localizedType + ') - ' + valueObj.getValue(geoIdAttr) ;
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
  }
},null,null);
