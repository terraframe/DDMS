/**
 * Class that implements single select searching for GeoEntities
 */
Mojo.Meta.newClass('MDSS.SingleSelectSearch', {

  Extends : MDSS.AbstractSelectSearch,
  
  Instance : {

    /**
     * Constructor.
     */
    initialize : function()
    {
      this.$initialize();
  
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
  
      if(Mojo.Util.isFunction(this._selectHandler))
      {
        this._selectHandler(geoEntityView);
      }
    },
    
    /**
     * Notifies the select handler that a GeoEntity
     * has been selected via the tree. The GeoEntityView is passed
     * to the handler.
     */
    _notifyTreeSelectHandler : function(geoEntityView)
    {
      this._updateSelection(geoEntityView);
  
      if(Mojo.Util.isFunction(this._treeSelectHandler))
      {
        this._treeSelectHandler(geoEntityView);
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
  
  }
});

YAHOO.util.Event.onDOMReady(function(){

    var currentGeoIdInput = null;

    function selectHandler(selected)
    {
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

       
        var valid;
        var currentFilter = selectSearch.getFilter();
        if(currentFilter == null || currentFilter == '')
        {
          valid = true;
        }
        else
        {
          // FIXME use Class metadata
          var expectedKlass = Mojo.Meta.findClass(currentFilter);
          var givenKlass = Mojo.Meta.findClass(selected.getEntityType());
          
          valid = givenKlass.$class.isSubClassOf(expectedKlass);
        }

        // Some pages also have a field that takes the geoentity id.
        // Those fields are namespaced as the geo id field+"_geoEntityId",
        // so a geo input with an id of "geoIdEl" may have another field
        // called "geoIdEl_geoEntityId".
        var currentgeoEntityIdInput = document.getElementById(currentGeoIdInput.id+'_geoEntityId');
        
        var geoInfo = document.getElementById(currentGeoIdInput.id+'Info');
        if (valid)
        {
            YAHOO.util.Dom.removeClass(geoInfo,'alert');
            currentGeoIdInput.value = selected.getGeoId();
            if(currentgeoEntityIdInput) {
              currentgeoEntityIdInput.value = selected.getGeoEntityId();
            }
            geoInfo.innerHTML = selected.getEntityName() + ' (' + selected.getTypeDisplayLabel()+ ')';
        }
        else
        {
            YAHOO.util.Dom.addClass(geoInfo,'alert');
            currentGeoIdInput.value = '';
            geoInfo.innerHTML = selected.getEntityName() + ' (' + selected.getTypeDisplayLabel()+ ') '+MDSS.Localized.InvalidGeoType;
            if(currentgeoEntityIdInput) {
              currentgeoEntityIdInput.value = selected.getGeoEntityId();
            }
        }
      }
      else
      {
        YAHOO.util.Dom.removeClass(geoInfo,'alert');
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(currentgeoEntityIdInput) currentgeoEntityIdInput.value ='';
      }

      if(typeof onValidGeoEntitySelected !== 'undefined' && Mojo.Util.isFunction(onValidGeoEntitySelected))
      {
        onValidGeoEntitySelected();
      }      
    }

    function checkManualEntry(e)
    {
      // reset context
      currentGeoIdInput = e.target;

      var request = new MDSS.Request({
          selectHandler: this,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
            selectHandler(result);
        }
      });

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, currentGeoIdInput.value);

    }
    
    var openPicker = function(e, geoInput)
    {
      // reset context
      currentGeoIdInput = geoInput;
      
      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    }

    /**
     * Performs an ajax search based on the entity
     * name and type.
     */
    var ajaxSearch =  function(e)
    {
      var input = e.target;
      
      // reset context
      currentGeoIdInput = input;
      
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

            var construct = Mojo.Meta.findClass(valueObj.getValue(typeAttr));
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

  var geoInputs = YAHOO.util.Dom.getElementsByClassName("geoInput");
  // use a single picker for all geo inputs
  var selectSearch = null;  
  if(geoInputs.length > 0)
  {
    selectSearch = new MDSS.SingleSelectSearch(); 
    selectSearch.setSelectHandler(selectHandler);
    selectSearch.setTreeSelectHandler(selectHandler);

    // set up the filter if it exists
    var radios = YAHOO.util.Dom.getElementsByClassName("filterType");
    
    var defaultFilter = '';
    // Allow either filtering via radio button or through
    // a predictable element whose value is a type.
    for(var i=0; i<radios.length; i++)
    {
      var radio = radios[i];
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
    
    // look for any other filter (this will override any radio input filter)
    var typeSearchFilter = document.getElementById('typeSearchFilter');
    if(typeSearchFilter)
    {
      defaultFilter = typeSearchFilter.value;
    }
    
    selectSearch.setFilter(defaultFilter);
  }

  for(var i=0; i<geoInputs.length; i++)
  {
    var geoInput = geoInputs[i];
    
    // Append the globe img to open the geo picker
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
    YAHOO.util.Dom.insertAfter(geoSearchResults,geoInfo);

    // swap out the geo input context per click
    opener.on("click", openPicker, geoInput, null);
    YAHOO.util.Event.on(geoInput, 'blur', checkManualEntry, null, null);
    YAHOO.util.Event.on(geoInput, 'keyup', ajaxSearch, null, null);
    
    var panel = new YAHOO.widget.Panel(geoSearchResults.id, {
      width:'400px',
      height:'200px',
      zindex:15,
      draggable: false,
      close: true
    });
  }
},null,null);
