Mojo.Meta.newClass('MDSS.GeoPicker', {

  IsAbstract : false,

  Instance : {
    
    // TODO 
    // 1. Add support to the geo tree for filtering geo entities
    // 2. 

    /**
     * Constructor.
     */
    initialize : function(enforceRoot, selectSearchRootId)
    {
      this._enforceRoot = enforceRoot;
      this._selectSearchRootId = MDSS.SelectSearchRootId;
      
      if(selectSearchRootId != null)
      {
        this._selectSearchRootId = selectSearchRootId;
      }
      
      this._listeners = [];
      
      this._political = true;
      
      this._populated = false;
  
      this._sprayTargetAllowed = false;
      
      this._urban = false;

      // must be set before instantiating a subclass.
      this._extraUniversals = [],
      
      this._rendered = false;
      
      this._suffix = Mojo.Util.generateId();
      
      // TODO this is hacky ... clean it up
      var wrappedHandler = (function(thisRef)
      {
        return function(geoEntity){
        
          thisRef._onGeoSelect(geoEntity);
          thisRef._geoPickerMainPanel.hide();
        }
      })(this);
      
      this._tree = new MDSS.GeoEntityTree("treeView" + this._suffix, wrappedHandler, this._selectSearchRootId);
    },
    
    show : function()
    {
      this._geoPickerMainPanel.show();
      this._geoPickerMainPanel.bringToTop();
    },
    
    hide : function()
    {
      this._geoPickerMainPanel.hide();
    },
    
    performLayout : function()
    {
      com.runwaysdk.ui.Manager.setFactory("YUI3");
      var factory = com.runwaysdk.ui.Manager.getFactory();
      var containerId = "geoPickerContainer" + this._suffix;
      var dividerColor = "#87a4db";
      var width = this.hasLeftPanel() ? "600px" : "400px";
      
      
      // Geo Picker container (grand daddy of all els)
      var geoPickerPanelEl = factory.newElement("div", {"id":"geoPickerPanelEl" + this._suffix});
      this._geoPickerContainer = factory.newElement("div", {"id":containerId});
      geoPickerPanelEl.setStyle("background-color", "white");
      this._geoPickerContainer.setStyle("overflow", "auto");
      this._geoPickerContainer.setStyle("height", (400 - 24) + "px")
      this._geoPickerContainer.setStyle("width", "100%")
      this._geoPickerContainer.setStyle("position", "absolute");
      geoPickerPanelEl.appendChild(this._geoPickerContainer);
      geoPickerPanelEl.render(document.body);
      
      
      // Turn that geoPickerContainer element into a Popup
      this._geoPickerMainPanel = new YAHOO.widget.Panel("geoPickerPanelEl" + this._suffix, {width: width, height:'400px', fixedcenter: true, zindex:9});
      this._geoPickerMainPanel.setHeader("Geography"); // TODO : localize
      this._geoPickerMainPanel.render();
      this._geoPickerMainPanel.bringToTop();
      this._geoPickerMainPanel.hide();
      
      
      // ***************** //
      // Universal section //
      // ***************** //
      
      this.layoutLeftPanel(this._geoPickerContainer);
      
      
      // ***************** //
      // Geoentity section //
      // ***************** //
      
      // Geo grand daddy el
      var geoDaddy = factory.newElement("div", {"id":"geoDaddy" + this._suffix});
      geoDaddy.setStyle("margin-top", "10px");
      geoDaddy.setStyle("float", "left");
      this._geoPickerContainer.appendChild(geoDaddy);
      
      // Section title that says "Select filters"
      if (this.hasLeftPanel())
      {
        var geoSectionTitle = factory.newElement("h2", {"id":"geoSectionTitle" + this._suffix});
        geoSectionTitle.setInnerHTML("Select filters"); // TODO : localize
        geoDaddy.appendChild(geoSectionTitle);
        geoDaddy.appendChild(factory.newElement("hr", {"color": dividerColor}));
      }
      
      // Geo search bar
      var geoInputId = 'geoPickerSearchBar_' + this._suffix;
      var geoInput = factory.newElement("input", {"type":"text", "id":geoInputId});
      var hiddenGeoInput = factory.newElement("input", {"type":"hidden", "id": geoInputId + '_geoEntityId'});
      geoDaddy.appendChild(hiddenGeoInput);
      geoDaddy.appendChild(geoInput);
      var geoSearch = new MDSS.GeoSearch(geoInput.getRawEl(), this.getGeoFilterCriteria());
      geoSearch.setSelectEventHandler(Mojo.Util.bind(this, this.searchBarSelectHandler));
      geoSearch.setIsFormInput(false);
      
      // Geo Tree
      var treeContainer = factory.newElement('div', {'id':"treeViewContainer" + this._suffix, 'class':'yui-skin-sam', 'style':'background-color:white;'});
      treeContainer.appendChild(factory.newElement('div', {'id':'treeView' + this._suffix}));
      geoDaddy.appendChild(treeContainer);
      this._tree.render();
      
      
      // OK / Cancel Buttons
//      this._renderButtons(this._geoPickerContainer);
    },
    
    getSelectHandler : function()
    {
      return Mojo.Util.bind(this, this._selectHandler == null ? this._onGeoSelect : this._selectHandler);
    },
    
    /**
     * Sets the handler function to be called when
     * a user selects a GeoEntity.
     */
    setSelectHandler : function(handler)
    {
      this._selectHandler = handler;
    },
    
    searchBarSelectHandler : function(view)
    {
      this._onGeoSelect(view);
    },
    
    layoutLeftPanel : function()
    {
    },
    
    hasLeftPanel : function()
    {
      return false;
    },
    
    render : function()
    {
//      var that = this;
//      
//      var request = new MDSS.Request({
//        onSuccess : function(hierarchies)
//        {
//          that._renderHierarchies(hierarchies);
//        }
//      });
//  
//      var method = this._getControllerAction();
//      
//      method(request, this._selectSearchRootId, this.getFlags(), this.getExtraUniversals());
      
      this.performLayout();
      
      this._rendered = true;
    },
    
    enforcesRoot : function()
    {
      return this._enforceRoot;
    },
    
    /**
     * Copies a GeoEntity to a GeoEntityView (used for caching).
     */
    _copyEntityToView : function(geoEntity)
    {
      var view = new Mojo.$.dss.vector.solutions.geo.GeoEntityView();
  
      view.setGeoEntityId(geoEntity.getId());
      view.setGeoId(geoEntity.getGeoId());
      view.setActivated(geoEntity.getActivated());
      view.setEntityLabel(geoEntity.getEntityLabel().getLocalizedValue());
      view.setEntityType(geoEntity.getType());
      view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());
  
      return view;
    },
    
    getGeoFilterCriteria : function()
    {
      return {
        filter : this.getFilter(),
        enforceRoot : this.enforcesRoot(),
        political : this.getPolitical(),
        populated : this.getPopulated(),
        sprayTargetAllowed : this.getSprayTargetAllowed(),
        urban : this.getUrban(),
        extraUniversals : this.getExtraUniversals()
      }
    },
    
    /**
     * TODO : is this function necessary?
     */
    setGeoId : function(geoId)
    {
      this._geoId = geoId;
    },
  
    /**
     * Sets the handler to be called when this modal is hidden.
     */
    setHideHandler : function(handler)
    {
      this._hideHandler = handler;
    },
    
    /**
     * Sets the root entity
     */
    _createRoot : function()
    {
      // Populate the root
      var request = new MDSS.Request({
        searchRef: this,
        onSuccess : function(results){
  
          this.searchRef._clearAndAddAll(0, results);
        }
      });
  
      this._doCreateRoot(request);
    },
    
    /**
     * Creates the OK, Cancel buttons.
     */
//    _renderButtons : function(parent)
//    {
//      var fac = com.runwaysdk.ui.Manager.getFactory();
//      var that = this;
//      
//      var ok = com.runwaysdk.Localize.get("OK");
//      var cancel = com.runwaysdk.Localize.get("Cancel");
//      
//      this._bContainer = fac.newElement("div", null, {height:"50px", width:"130px", "padding-left":"120px", "padding-top":"20px"});
//      this._bOK = fac.newButton(ok, function() {
//        if (that._okHandler != null)
//        {
//          that._okHandler();
//        }
//        that.hide();
//      });
//      this._bOK.setStyle("margin-right", "5px");
//      this._bOK.setEnabled(false);
//      this._bContainer.appendChild(this._bOK);
//      this._bCancel = fac.newButton(cancel, function() {
//        if (that._cancelHandler != null)
//        {
//          that._cancelHandler();
//        }
//        that.hide();
//      });
//      this._bContainer.appendChild(this._bCancel);
//      
//      this._bContainer.render(parent);
//    },
    
    getFlags : function()
    {
      var flags = new Array();
        
      flags.push(this.getPolitical());
      flags.push(this.getSprayTargetAllowed());
      flags.push(this.getUrban());
      
      return flags;
    },
    
    /**
     * Creates the mapping between the given GeoEntity
     * and an option for the select element that represents
     * the GeoEntity's type.
     */
    _setEntityOption : function(geoEntityView, overrideIndex)
    {
      var select;
      if(overrideIndex != null)
      {
        select = this._selectLists[overrideIndex];
      }
      else
      {
        select = this._selectMap[geoEntityView.getEntityType()];
      }
  
      if(select && !this._geoEntityViewCache[geoEntityView.getGeoEntityId()])
      {
        if(this._disableAllowed())
        {
          select.disabled = false;
        }
  
        var optionRaw = document.createElement('option');
        optionRaw.value = geoEntityView.getGeoEntityId();
        optionRaw.id = geoEntityView.getGeoEntityId();
        optionRaw.innerHTML = geoEntityView.getEntityLabel();
  
        select.appendChild(optionRaw);
  
        var selectYahoo = new YAHOO.util.Element(select);
        selectYahoo.on('change', this._getChildren, null, this);
  
        this._geoEntityViewCache[geoEntityView.getGeoEntityId()] = geoEntityView;
      }
    },
    
    _onGeoSelect : function(geoEntityView)
    {
//      if(Mojo.Util.isFunction(this._selectHandler))
//      {
//        this._selectHandler(geoEntityView);
//      }
      
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      var currentgeoEntityIdInput = document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'_geoEntityId');

      var geoInfo = document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'Info');

      if(geoEntityView === null)
      {
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(currentgeoEntityIdInput) { currentgeoEntityIdInput.value =''; }
      }
      else
      {
        MDSS.GeoSearch.currentGeoIdInput.value = geoEntityView.getGeoId();
      
        if(currentgeoEntityIdInput) {
          currentgeoEntityIdInput.value = geoEntityView.getGeoEntityId();
        }
      
        geoInfo.innerHTML = this.constructor.formatDisplay(geoEntityView);
      }
      
//    FIXME: global (and absurdly hacky) method callback
      if(typeof onValidGeoEntitySelected !== 'undefined' && Mojo.Util.isFunction(onValidGeoEntitySelected))
      {
        onValidGeoEntitySelected();
      }
      
      this._fireEvent(new MDSS.Event(MDSS.Event.AFTER_SELECTION,{selected:geoEntityView}));
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
     * Returns the appropriate controller action to
     * render the select search component.
     */
    _getControllerAction : function()
    {
      return Mojo.$.dss.vector.solutions.geo.GeoHierarchy.collectHierarchies;
    },
    
    /**
     * Changes the GeoEntity subtype filter and clears
     * the select search and tree.
     */
    setFilter : function(filter)
    {
      this._filterType = filter;
  
      if(this.isInitialized())
      {
        this._doFilter();
      }
    },
    
    getFilter : function()
    {
      return this._filterType;
    },
    
    setPopulated : function(populated)
    {
      this._populated = populated;
    },
    
    getPopulated : function()
    {
      return this._populated;
    },
    
    setPolitical : function(political)
    {
      this._political = political;
    },
    
    getPolitical : function()
    {
      return this._political;
    },
  
    setSprayTargetAllowed : function(sprayTargetAllowed)
    {
      this._sprayTargetAllowed = sprayTargetAllowed;
    },
    
    getSprayTargetAllowed : function()
    {
      return this._sprayTargetAllowed;
    },
    
    setUrban : function(urban)
    {
      this._urban = urban;
    },
    
    getUrban : function()
    {
      return this._urban;
    },
    
    addExtraUniversal : function(universal)
    {
      this._extraUniversals.push(universal);
    },

    clearExtraUniversals : function()
    {
      this._extraUniversals = [];
    },
    
    getExtraUniversals : function()
    {
      return this._extraUniversals;
    },
            
    /**
     * Performs the DOM level filtering by hiding/showing
     * the proper select lists.
     */
    _doFilter : function()
    {
      // show all if no filter exists
      if(this._filterType == null || this._filterType === '' || (Mojo.Util.isArray(this._filterType) && this._filterType.length === 0))
      {
        var selects = YAHOO.util.Selector.query('select', this._SELECT_CONTAINER_ID);
        for(var i=0; i<selects.length; i++)
        {
          var type = selects[i].id;
          var dt = new YAHOO.util.Element(type+"_dt");
          var dd = new YAHOO.util.Element(type+"_dd");
  
          dt.setStyle('display', 'block');
          dd.setStyle('display', 'block');
        }
  
        return;
      }
  
      function collectParents(tree, allowedSet, typeEntry)
      {
        var parentType = typeEntry.parent;
  
        if(Mojo.Util.isString(parentType))
        {
          var parentEntry = tree.types[parentType];
          allowedSet.set(parentType);
          collectParents(tree, allowedSet, parentEntry);
        }
      }
  
      function collectChildren(tree, allowedSet, typeEntry)
      {
        var children = typeEntry.children;
        for(var i=0; i<children.length; i++)
        {
          var childEntry = tree.types[children[i]];
          allowedSet.set(children[i]);
          collectChildren(tree, allowedSet, childEntry);
        }
      }
  
      var allowed = new MDSS.Set();
      var tree = MDSS.GeoTreeSelectables;
      
      if(Mojo.Util.isArray(this._filterType))
      {
        for(var i=0; i<this._filterType.length; i++)
        {
          var type = this._filterType[i];
          allowed.set(type);

          var typeEntry = tree.types[type];
          collectParents(tree, allowed, typeEntry);
          collectChildren(tree, allowed, typeEntry);
        }
      }
      else
      {
        allowed.set(this._filterType);

        var typeEntry = tree.types[this._filterType];
        collectParents(tree, allowed, typeEntry);
        collectChildren(tree, allowed, typeEntry);
      }
  
      // hide all sections not in the allowed array
      var selects = YAHOO.util.Selector.query('select', this._SELECT_CONTAINER_ID);
      for(var i=0; i<selects.length; i++)
      {
        var type = selects[i].id;
        var dt = new YAHOO.util.Element(type+"_dt");
        var dd = new YAHOO.util.Element(type+"_dd");
  
        if(allowed.contains(type))
        {
          dt.setStyle('display', 'block');
          dd.setStyle('display', 'block');
        }
        else
        {
          dt.setStyle('display', 'none');
          dd.setStyle('display', 'none');
        }
      }
      
      if(this._clearAfterFilter)
      {
        this._filterType = '';
      }
    },
    
    clearAfterFilter : function(clear)
    {
      this._clearAfterFilter = clear;
    },
    
    _modalListFunction : function(valueObject) {
      var GeoEntity = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity;
      var geoIdAttr = GeoEntity.GEOID;
      var entityLabelAttr = GeoEntity.ENTITYLABEL;
        
      var entityLabel = valueObject.getValue(entityLabelAttr);
      var displayLabel = valueObject.getValue('displayLabel');
      var geoId = valueObject.getValue(geoIdAttr);
      var moSubType = valueObject.getValue('moSubType');
          
      return MDSS.AbstractSelectSearch.formatDisplay2(entityLabel, displayLabel, geoId, moSubType);    
    },
        
    _modalDisplayFunction : function(valueObject) {
      var GeoEntity = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity;
      var geoIdAttr = GeoEntity.GEOID;
      var geoId = valueObject.getValue(geoIdAttr);
          
      return geoId;
    },
        
    _modalIdFunction : function(valueObject) {
      var GeoEntity = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity;
      var idAttr = GeoEntity.ID;
      var id = valueObject.getValue(idAttr);
          
      return id;
    },
    
    _clearAndAddAllFindIndex : function(results, geoId)
    {
      var selectIndex = 0;
      
      for(var i=0; i<results.length; i++)
      {
        var childView = results[i];
        if(childView.getGeoId() == geoId)
        {
          var type = childView.getEntityType();
          selectIndex = this._typeAndSelectMap[type];
        }
      }
      
      this._clearAndAddAll(selectIndex, results, geoId);
    },
    
    populateSelections : function(geoId)
    {
      var request = new MDSS.Request({
        that: this,
        geoId : geoId,
        onSuccess: function(results)
        {
          this.that._clearAndAddAllFindIndex(results, geoId);
          this.that._searchModal.render();
        }
      });
      
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedInByGeoId(request, geoId, true, "");    
    },
  
    /**
     * Clears all select options in the select lists
     * below the given type. This is called to clear the
     * way for new children.
     */
    _clearSelectLists : function(typeIndex, inclusive)
    {
      var index = Mojo.Util.isString(typeIndex) ? this._typeAndSelectMap[typeIndex] : typeIndex;
  
      // don't do anything for the last select list
      if(index == this._selectLists.length-1)
      {
        return;
      }
  
      var startIndex = inclusive ? index : index+1;
      for(var i=startIndex; i<this._selectLists.length; i++)
      {
        var select = this._selectLists[i];
  
        // remove option node and mappings
        this._clearOptionsOnSelect(i, select);
      }
    },
  
    /**
     * Clears options on the given select list.
     */
    _clearOptionsOnSelect : function(selectIndex, select)
    {
      var options = select.options;
      var oLength = options.length;
  
      var startInd = selectIndex == 0 ? 0 : this._getStartIndex()-1;
  
      for(var i=oLength-1; i>startInd; i--)
      {
        var option = options[i];
  
        this._removeOptionNode(option);
      }
    },
    
    /**
     * Removes the given option from the DOM and
     * clears the GeoEntity cache of the object
     * with the same id.
     */
    _removeOptionNode : function(option)
    {
      var optionEl = Mojo.Util.isString(option) ? document.getElementById(option) : option;
      var select = optionEl.parentNode;
      select.removeChild(optionEl);
  
      delete this._geoEntityViewCache[optionEl.id];
  
      if(select.options.length == this._getStartIndex())
      {
        select.selectedIndex = 0;
  
        if(this._disableAllowed())
        {
          select.disabled = true;
        }
      }
    },
  
    /**
     * Subclasses must override this to denote if empty select
     * lists can be disabled.
     */
//    _disableAllowed :
//    {
//      IsAbstract : true
//    },
  
  },
  
  Static : {
  
    /**
     * Formats the given GeoEntityView to a standardized string.
     */
    formatDisplay : function(geoEntityView)
    {
      // IMPORTANT: Don't include the moSubType because geoEntityView.getTypeDisplayLabel() already appends the mo sub type  
      return MDSS.AbstractSelectSearch.formatDisplay2(geoEntityView.getEntityLabel(), geoEntityView.getTypeDisplayLabel(), geoEntityView.getGeoId(), null);
    },
    
    getDisplayWithSubtype : function(geoEntityView)
    {
      return MDSS.AbstractSelectSearch.formatDisplay2(geoEntityView.getEntityLabel(), geoEntityView.getTypeDisplayLabel(), geoEntityView.getGeoId(), geoEntityView.getMoSubType());
    },
    
    formatDisplay2 : function(entityLabel, typeDisplayLabel, geoId, moSubType)
    {
      var mo = moSubType != null && moSubType.length > 0 ? " : "+moSubType : '';
      
      return entityLabel + ' ('+typeDisplayLabel+mo+') - ' + geoId;
    }
  }
});

