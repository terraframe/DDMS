/**
 * Class that implements single select searching for GeoEntities
 */
Mojo.Meta.newClass('MDSS.SingleSelectSearch', {

  Extends : MDSS.AbstractSelectSearch,

  Instance : {

    /**
     * Constructor.
     */
    initialize : function(enforceRoot, selectSearchRootId)
    {
      this.$initialize(enforceRoot, selectSearchRootId);

      this._currentSelection = null;
      this._CURRENT_SELECTION = 'currentSelection';
      this._listeners = [];
      this._geoId = null;
      
      // Set this.selectHandler as the default handler
      this.setSelectHandler(Mojo.Util.bind(this, this.selectHandler));
      this.setTreeSelectHandler(Mojo.Util.bind(this, this.selectHandler));
    },
    
    _renderHierarchyHeader : function(factory, hierarchy, index, rootId)
    {
      var dt = factory.newElement('dt', {'id':rootId + '_dt'});
      dt.setInnerHTML(hierarchy.getDisplayLabel());
      
      return dt;
    },
        
    _renderCurrentSelection : function(factory)
    {
      return factory.newElement('span', {'id':'currentSelection' + this._suffix});
    },
    
    setGeoId : function(geoId)
    {
      this._geoId = geoId;
    },
    
    _postRender : function()
    {
      // do nothing
    },
    
    _notifyHideHandler : function()
    {
      // do nothing
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

          valid = givenKlass.getMetaClass().isSubClassOf(expectedKlass);
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
      
      this._fireEvent(new MDSS.Event(MDSS.Event.AFTER_SELECTION,{selected:selected}));
    },
    
    addListener : function(listener)
    {
      this._listeners.push(listener);
    },
    
    _fireEvent : function(event)
    {
      for(var i = 0; i < this._listeners.length; i++)
      {
        this._listeners[i].handleEvent(event);
      }
    },

    /**
     * Replaces the current selection with the given GeoEntityView.
     */
    _updateSelection : function(geoEntityView)
    {
      var div = document.getElementById(this._CURRENT_SELECTION + this._suffix);
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
      
      this._resetWithSelection(geoEntityView.getEntityType(), geoEntityView.getGeoEntityId());
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
    
    _doCreateRoot : function(request)
    {
      if(this._geoId != null && this._geoId !== '')
      {
        // Use a custom geo entity
        this.populateSelections(this._geoId);
      }
      else
      {
        // Fetch the system default root.
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, this._selectSearchRootId, false, this._filterType);
      }
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
      this._listeners = [];
      this._selectSearch = selectSearch || null; // 101 widget reference
      
      this._geoInput = Mojo.Util.isString(geoInput) ? document.getElementById(geoInput) : geoInput;
      
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      this._geoElement = document.getElementById(this._geoInput.id+'_geoEntityId');
      
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
      
      //YAHOO.util.Event.on(geoInput, 'blur', this._checkManualEntry, null, this);
      
      // add generic ajax search
      this._genericSearch = new MDSS.GenericSearch(this._geoInput, this._geoElement, lF, dF, iF, sF, sEH);
      
      // IMPORTANT: We must reset the search cache because new geo entities made be created/deleted in 101.
      //            As such the existing search results may no longer be valid.
      YAHOO.util.Event.on(this._opener, "click", this._clickHandler, this, this); 

      YAHOO.util.Event.on(this._geoInput, 'focus', this._setCurrentInput, null, this);

      // Set up validation on the geo input
      YAHOO.util.Event.on(this._geoInput, 'blur', this._blurHandler, this, this);
      
      // 
      var GeoEntity = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity
      this._idAttr = GeoEntity.ID;
      this._entityLabelAttr = GeoEntity.ENTITYLABEL;
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
      
      if(this._selectSearch != null)
      {
        this._selectSearch.addListener(this);
      }      
    },
    
    _clickHandler : function(e)
    {
      var initialized = this._selectSearch.isInitialized();
      
      // avoid setting the geo id in 101 if there isn't a valid value
      if(this._geoInput != null && this._geoInput.value !== '')
      {
        var geoId = this._geoInput.value;
        this._selectSearch.setGeoId(geoId);
      }
      
      this._openPicker(e, this._geoInput);
      this._genericSearch.resetCache();
      this._setTreeValidator();

      if(initialized)
      {
        this._selectSearch.populateSelections(geoId);
      }
    },
    
    _setTreeValidator : function()
    {
      this._selectSearch._tree.setValidator(Mojo.Util.bind(this, this._validator));          
    },
    
    destroy : function()
    {
      YAHOO.util.Event.removeListener(this._opener, 'click', this._openPicker);
      YAHOO.util.Event.removeListener(this._geoInput, 'focus', this._setCurrentInput);
      
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
      var entityLabel = valueObj.getValue(this._entityLabelAttr);
      var typeLabel = valueObj.getValue('displayLabel');
      var geoId = valueObj.getValue(this._geoIdAttr);
      var subType = valueObj.getValue('moSubType');
      
      return MDSS.AbstractSelectSearch.formatDisplay2(entityLabel, typeLabel, geoId, subType);
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
      
      this._validateSelection(selected);      
    },
    
    _searchFunction : function(request, value)
    {
      var type = this._selectSearch.getFilter();
      var enforceRoot = this._selectSearch.enforcesRoot();
      
      if(Mojo.Util.isString(type) && type != '')
      {
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request, type, value, enforceRoot);
      }
      else 
      {
        var political = this._selectSearch.getPolitical();
        var populated = this._selectSearch.getPopulated();
        var sprayTarget = this._selectSearch.getSprayTargetAllowed();        
        var urban = this._selectSearch.getUrban();

        var parameters = [political, populated, sprayTarget, urban].concat(this._selectSearch.getExtraUniversals());
        
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByParameters(request, value, parameters, enforceRoot);
      }
    },
    
    _getValidationRequest : function(selected)
    {
      var request = new MDSS.Request({
        that : this,
        selected : selected,
        onSend : function(){}, 
        onComplete : function(){},        
        onFailure : function(e){          
          MDSS.Calendar.removeError(this.that._opener);
          
          MDSS.Calendar.addError(this.that._opener, e.getMessage());
          
          this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_INVALID_SELECTION, {selected:selected}));
        },
        onProblemExceptionDTO : function(e){
          MDSS.Calendar.removeError(this.that._opener);
          
          var problems = e.getProblems();
          for(var i = 0; i < problems.length; i++) {
            var problem = problems[i];
            MDSS.Calendar.addError(this.that._opener, problem.getMessage());
          }
          
          this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_INVALID_SELECTION, {selected:selected}));
        },
        onSuccess : function() {
          MDSS.Calendar.removeError(this.that._opener);
          
          this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_VALID_SELECTION, {selected:selected}));
        }            
      });
        
      return request;
    },
    
    _blurHandler : function (e)
    {
      if(e)
      {
        var blurEl = e.explicitOriginalTarget || document.activeElement;
        
        var ul = YAHOO.util.Dom.getAncestorByClassName(blurEl, "selectableList")
        
        if(ul)
        {
          return; 
        }
        
        this._validateSelection();
      }    
    },
    
    _validateSelection : function(selected)
    {
      var geoId = this._geoInput.value;
      
      if(Mojo.Util.isString(geoId) && geoId != '')
      {
        var request = this._getValidationRequest(selected);
        
        this._validator(request, geoId);
      }
    },
    
    _validator : function(request, geoId)
    {
      var type = this._selectSearch.getFilter();
        
      if(Mojo.Util.isString(type) && type != '')
      {
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByType(request, geoId, type);
      }
      else 
      {
        var political = this._selectSearch.getPolitical();
        var populated = this._selectSearch.getPopulated();
        var sprayTarget = this._selectSearch.getSprayTargetAllowed();        
        var urban = this._selectSearch.getUrban();
        
        var parameters = [political, populated, sprayTarget, urban].concat(this._selectSearch.getExtraUniversals());
           
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByParameters(request, geoId, parameters);
      }    
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
    },
    
    handleEvent : function(event)
    {
      if(event.getType() == MDSS.Event.AFTER_SELECTION)
      {
        this._validateSelection(event.getValue().selected);
      }
    },
    
    setFilter : function(filter)
    {
      if(this._selectSearch != null) {
        this._selectSearch.setFilter(filter);
      }
      
      this._genericSearch.resetCache();
    },
    
    addListener : function(listener) 
    {
      this._listeners.push(listener);
    },
    
    fireEvent : function(event)
    {
      for(var i = 0; i < this._listeners.length; i++)
      {
        this._listeners[i](event);
      }    
    },
    
    resetCache : function() {
      this._genericSearch.resetCache();
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