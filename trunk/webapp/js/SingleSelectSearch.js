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
      
      // Set this.selectHandler as the default handler
      this.setSelectHandler(Mojo.Util.bind(this, this.selectHandler));
      this.setTreeSelectHandler(Mojo.Util.bind(this, this.selectHandler));
    },
    
    selectHandler : function(selected, ignoreSetting)
    {
      var valid = true;
      if(selected != null)
      {

      if(typeof selected == 'string'){
          var request = new MDSS.Request({
              that: this,
              onSend: function(){},
              onComplete: function(){},
              onFailure: function(){},
              onSuccess : function(result){
                this.that.selectHandler(result); // recalls this method
            }
          });
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getView(request, selected);
          return;
        }

        var currentFilter = this.getFilter();
        if(currentFilter)
        {
          var expectedKlass = Mojo.Meta.findClass(currentFilter);
          var givenKlass = Mojo.Meta.findClass(selected.getEntityType());

          valid = givenKlass.$class.isSubClassOf(expectedKlass);
        }

      }
      
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      var currentgeoEntityIdInput = document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'_geoEntityId');

      var geoInfo = document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'Info');

      if(selected === null)
      {
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(currentgeoEntityIdInput) currentgeoEntityIdInput.value ='';
      }
      else
      {
          if(!ignoreSetting)
          {
	        MDSS.GeoSearch.currentGeoIdInput.value = selected.getGeoId();
	      }
	      
	      if(currentgeoEntityIdInput) {
	        currentgeoEntityIdInput.value = selected.getGeoEntityId();
	      }
	      geoInfo.innerHTML = this.constructor.formatDisplay(selected);
	    }

      if(valid)
      {
      	YAHOO.util.Dom.removeClass(geoInfo,'alert');
      }
      else
      {
      	YAHOO.util.Dom.addClass(geoInfo,'alert');
      }
      
      // FIXME: global (and absurdly hacky) method callback
      if(valid && typeof onValidGeoEntitySelected !== 'undefined' && Mojo.Util.isFunction(onValidGeoEntitySelected))
      {
        onValidGeoEntitySelected();
      }
    },

    /**
     * Replaces the current selection with the given GeoEntityView.
     */
    _updateSelection : function(geoEntityView)
    {
      var div = document.getElementById(this._CURRENT_SELECTION);
      div.innerHTML = this.constructor.formatDisplay(geoEntityView);

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

/**
 * Class that that can be attached to any input field
 * to provide searching capability for geo entities.
 */
Mojo.Meta.newClass("MDSS.GeoSearch", {

  Instance : {
    
    initialize : function(geoInput, selectSearch)
    {
      this._selectSearch = selectSearch || null; // 101 widget reference
    
      this._geoInput = Mojo.Util.isString(geoInput) ? document.getElementById(geoInput) : geoInput;
    
      // Append the globe img to open the geo picker
      this._opener = document.createElement('img');
      this._opener.src = "./imgs/icons/world.png";
      this._opener.id = this._geoInput.id +'Go';
      //opener = new YAHOO.util.Element(opener);
      YAHOO.util.Dom.addClass(this._opener,'geoOpener');
      YAHOO.util.Dom.insertAfter(this._opener,this._geoInput);
  
      var geoInfo = document.createElement('div');
      geoInfo.id = this._geoInput.id +'Info';
      geoInfo.innerHTML = '()';
      YAHOO.util.Dom.insertAfter(geoInfo, this._opener);
  
      var geoSearchResults = document.createElement('div');
      geoSearchResults.id = this._geoInput.id +'_results';
      geoSearchResults.className = "yui-panel-container show-scrollbars shadow";
      YAHOO.util.Dom.insertAfter(geoSearchResults,geoInfo);
  

      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._listFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sEH = Mojo.Util.bind(this, this._selectEventHandler);
      
      YAHOO.util.Event.on(this._opener, "click", this._openPicker, this._geoInput, this);
      
      
      //YAHOO.util.Event.on(geoInput, 'blur', this._checkManualEntry, null, this);
      
      // add generic ajax search
      this._genericSearch = new MDSS.GenericSearch(this._geoInput, null, lF, dF, iF, sF, sEH);
      YAHOO.util.Event.on(this._geoInput, 'focus', this._setCurrentInput, null, this);
      YAHOO.util.Event.on(this._geoInput, 'keyup', this._genericSearch.performSearch, null, this._genericSearch);
      
      // 
      var GeoEntity = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity
      this._idAttr = GeoEntity.ID;
      this._entityNameAttr = GeoEntity.ENTITYNAME;
      this._geoIdAttr = GeoEntity.GEOID;
      this._typeAttr = GeoEntity.TYPE;
      
      // Fetch the display info if a geo entity value exists
      if(this._geoInput.value.length > 0)
      {
        var request = new MDSS.Request({
          that: this,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
         	 this.that.showGeoInfo(result,this.that._geoInput,true);
          }
        });

        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, this._geoInput.value);
      }
    },
    
    destroy : function()
    {
      YAHOO.util.Event.removeListener(this._opener, 'click', this._openPicker);
      YAHOO.util.Event.removeListener(this._geoInput, 'focus', this._setCurrentInput);
      YAHOO.util.Event.removeListener(this._geoInput, 'keyup', this._genericSearch.performSearch);
      
      this._opener = null;
      this._geoInput = null;
    },
    
    _setCurrentInput : function()
    {
      this.constructor.currentGeoIdInput = this._geoInput;
    },
    
    _openPicker : function(e, newGeoInput)
    {
      // reset context
      this.constructor.currentGeoIdInput = newGeoInput;

      if(this._selectSearch.isInitialized())
      {
        this._selectSearch.show();
      }
      else
      {
        this._selectSearch.render();
      }
    },
    
    _displayFunction : function(valueObj)
    {
      return valueObj.getValue(this._geoIdAttr);
    },
    
    _listFunction : function(valueObj)
    {
      return MDSS.AbstractSelectSearch.formatDisplay2(valueObj.getValue(this._entityNameAttr),
        valueObj.getValue('displayLabel'), valueObj.getValue(this._geoIdAttr), valueObj.getValue('moSubType'));
    },
    
    _idFunction : function(valueObj)
    {
      return valueObj.getValue(this._idAttr);
    },
    
    _selectEventHandler : function(selected)
    {
      var geoEntityId = selected.id;
      var handler = this._selectSearch.getSelectHandler();
      
      handler(geoEntityId, true);
    },
    
    _searchFunction : function(request, value)
    {
      var type = this._selectSearch.getFilter();
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request, type, value);
    },
    
    showGeoInfo : function(selected,currentGeoIdInput,valid)
    {
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      var currentgeoEntityIdInput = document.getElementById(currentGeoIdInput.id+'_geoEntityId');

      var geoInfo = document.getElementById(currentGeoIdInput.id+'Info');

      if(selected === null)
      {
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(currentgeoEntityIdInput) currentgeoEntityIdInput.value ='';
      }
      else
      {
	      currentGeoIdInput.value = selected.getGeoId();
	      if(currentgeoEntityIdInput) {
	        currentgeoEntityIdInput.value = selected.getGeoEntityId();
	      }
	      geoInfo.innerHTML = MDSS.AbstractSelectSearch.formatDisplay(selected);
	    }

      if(valid)
      {
      	YAHOO.util.Dom.removeClass(geoInfo,'alert');
      }
      else
      {
      	YAHOO.util.Dom.addClass(geoInfo,'alert');
      }
    }
    
    /*
    _checkManualEntry : function(e)
    {
      // reset context
      this.constructor.currentGeoIdInput = e.target;

      var request = new MDSS.Request({
          that: this,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
            
          // execute the select handler
          this.that._selectSearch.getSelectHandler()(result);
        }
      });

      var id = this.constructor.currentGeoIdInput.value;

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, id);
    }
    */
  },
  
  Static : {
    currentGeoIdInput : null
  }
});


YAHOO.util.Event.onDOMReady(function(){

  // Add 101 and the geo searching to any element with a classname of geoInput
  var geoInputs = YAHOO.util.Dom.getElementsByClassName("geoInput");
  
  // use a single picker for all geo inputs
  var selectSearch = null;
  if(geoInputs.length > 0)
  {
    selectSearch = new MDSS.SingleSelectSearch();
//    selectSearch.setSelectHandler(selectHandler);
//    selectSearch.setTreeSelectHandler(selectHandler);

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

    new MDSS.GeoSearch(geoInput, selectSearch);
  }
},null,null);
