Mojo.Meta.newClass('MDSS.AbstractSelectSearch', {

  IsAbstract : true,

  Instance : {

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
    
      // handler for when a new geo entity is selected
      this._selectHandler = null;
  
      // handler for when a geo entity is selected via the tree
      this._treeSelectHandler = null;
  
      // handler to be executed when the search is closed.
      this._hideHandler = null;
  
      // The id of the element that contains the select lists.
      this._SELECT_CONTAINER_ID = "selectSearchComponent";
  
      // cache of GeoEntities with key/value = geoEntity.getId()/geoEntity
      this._geoEntityViewCache = {};
  
      // reference to modal that contains the select search
      this._searchModal = null;
  
      // reference to the Yahoo Panel that contains the search tree.
      this._geoTreePanel = null;
  
      // the GeoEntity subtype class by which to filter results
      this._filterType = '';
  
      // list of select elements
      this._selectLists = [];
  
      // map between type and select list index
      // where key/value is type/select index in this._selectLists
      this._typeAndSelectMap = {};
  
      // List of all the auto completes in the system
      this._autocompletes = [];
  
      // the current type of GeoEntity for ajax searching
      this._currentSearchType = null;
  
      this._political = true;
      
      this._populated = false;
  
      this._sprayTargetAllowed = false;
      
      this._urban = false;

      // must be set before instantiating a subclass.
      this._extraUniversals = [],
      
      this._rendered = false;  
      
      this._clearAfterFilter = false;
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
  
    /**
     * Checks if the searching has been enabled (i.e., the modal has
     * been initialized).
     */
    isInitialized : function()
    {
      return this._searchModal != null;
    },
  
    /**
     * Shows the select modal if it is hidden.
     */
    show : function()
    {
      this._searchModal.show();
    },
  
    /**
     * Hides the modal if it visibile.
     */
    hide : function()
    {
      this._searchModal.hide();
    },
  
    /**
     * Sets the handler function to be called when
     * a user selects a GeoEntity.
     */
    setSelectHandler : function(handler)
    {
      this._selectHandler = handler;
    },
    
    getSelectHandler : function()
    {
      return this._selectHandler;
    },
  
    /**
     * Sets the handler function to be called when
     * a user selects a GeoEntity from the tree.
     */
    setTreeSelectHandler : function(handler)
    {
      this._treeSelectHandler = handler;
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
  
    _doCreateRoot : 
    {
      IsAbstract : true
    },
  
    /**
     * Closes all the result panels.
     */
    eventHandler : function(e) {
      if(e.getType() == MDSS.Event.BEFORE_SEARCH) {
        var autocomplete = e.getValue().autocomplete;
    
        var searchedId = autocomplete.getDisplayElement().id;
          
        // When a new value is inputed we want to hide all
        // the other visible search results
        for(var i=0; i < this._autocompletes.length; i++) {
          var element = this._autocompletes[i];
          var elementId = element.getDisplayElement().id;
        
          if(searchedId != elementId) {
            element.hide();
          }
        }      
      }
    },
    
    _closeAllResultPanels : function() {
      for(var i=0; i < this._autocompletes.length; i++) {
        var element = this._autocompletes[i];
        element.hide();
      }
    },
  
    /**
     * Renders the select search component.
     */
    render : function()
    {
      var request = new MDSS.Request({
        searchRef: this,
        onSuccess : function(html){
  
          // use modal to contain MDSS101
          this.searchRef._searchModal = new YAHOO.widget.Panel("searchSelectModal",  {
            width:"100%",
            height: "100%",
            fixedcenter:true,
            close:true,
            draggable:false,
            zindex:4,
            modal:true,
            visible:true
          });
  
          // hide all panels spawned by the search modal
          this.searchRef._searchModal.subscribe('beforeHide', function(){
  
            this._closeAllResultPanels();
  
            if(this._geoTreePanel != null)
            {
              this._geoTreePanel.hide();
            }
  
            this._notifyHideHandler();
  
          }, null, this.searchRef);
  
          this.searchRef._searchModal.setBody(html);
          this.searchRef._searchModal.render(document.body);
  
          this.searchRef._doFilter();
  
          // hook event to open tree
          var treeOpener = new YAHOO.util.Element("treeOpener");
          treeOpener.on('click', this.searchRef._openTree, null, this.searchRef);
  
          var selects = YAHOO.util.Selector.query('select.typeSelect', this.searchRef._SELECT_CONTAINER_ID);
          for(var i=0; i<selects.length; i++)
          {
            var select = selects[i];
  
            // create mapping
            this.searchRef._typeAndSelectMap[select.name] = i; // select name is the type
            this.searchRef._selectLists.push(select);
  
            YAHOO.util.Event.on(select, this._getChildren, null, this.searchRef);
          }                   
  
          // hook all entity name search events
          var ajaxSearches = YAHOO.util.Selector.query('input.ajaxSearch', this._SELECT_CONTAINER_ID);
          
          for(var i=0; i<ajaxSearches.length; i++)
          {
            var search = ajaxSearches[i];            
            var type = search.id.replace(/_search/, '');
            
            var sFunction = Mojo.Util.bind(this.searchRef, function(typeRef, request, value){
              Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request, typeRef, value, this.enforcesRoot());
            }, type);
            
            var sHandler = Mojo.Util.bind(this, function(typeRef, option){
              this.searchRef._resetWithSelection(typeRef, option.id);
            }, type);
            
            var lF = this.searchRef._modalListFunction;
            var dF = this.searchRef._modalDisplayFunction;
            var iF = this.searchRef._modalIdFunction;
            var listener = Mojo.Util.bind(this.searchRef, this.searchRef.eventHandler);
            
            var autocomplete = new MDSS.GenericSearch(search, null, lF, dF, iF, sFunction, sHandler);                        
            autocomplete.addListener(listener);
            
            this.searchRef._autocompletes.push(autocomplete);
          }
           
          this.searchRef._postRender();
  
          this.searchRef._rendered = true;
  
          this.searchRef._createRoot();
        }
      });
  
      var method = this._getControllerAction();
      
      method(request, this._selectSearchRootId, this.getFlags(), this.getExtraUniversals());
    },
    
    getFlags : function()
    {
      var flags = new Array();
        
      flags.push(this.getPolitical());
      flags.push(this.getSprayTargetAllowed());
      flags.push(this.getUrban());
      
      return flags;
    },
  
    /**
     * Calls after render() but before _createRoot(). Subclasses
     * may override this method to do any post render processing.
     */
    _postRender :
    {
      IsAbstract : true
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
        select = document.getElementById(geoEntityView.getEntityType());
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
  
        var option = new YAHOO.util.Element(optionRaw);
        option.on('click', this._getChildren, null, this);
  
        this._geoEntityViewCache[geoEntityView.getGeoEntityId()] = geoEntityView;
      }
    },
  
    /**
     * Calls the handler when the user hides the search modal.
     */
    _notifyHideHandler :
    {
      IsAbstract : true
    },
  
    /**
     * Abstract method to notify the select
     * handler that a GeoEntity has been selected.
     */
    _notifySelectHandler :
    {
      IsAbstract : true
    },
    
    _notifyTreeSelectHandler :
    {
      IsAbstract : true
    },
  
    /**
     * Invokes the appropriate controller action to
     * render the select search component.
     */
    _getControllerAction :
    {
      IsAbstract : true
    },
  
    /**
     * Updates the HTML area with information about the most recently selected GeoEntity.
     */
    _updateSelection :
    {
      IsAbstract : true
    },
  
    /**
     * Opens the tree for extra search functionality.
     */
    _openTree : function()
    {
      var containerId = "treeViewContainer";
  
      if(this._geoTreePanel == null)
      {
        this._geoTreePanel = new YAHOO.widget.Panel(containerId, {width:'400px', height:'400px', zindex:9});
  
        this._geoTreePanel.render();
        this._geoTreePanel.bringToTop();
  
        var wrappedHandler = (function(searchRef)
        {
          return function(geoEntity){
          
            searchRef._notifyTreeSelectHandler(geoEntity);
            searchRef._geoTreePanel.hide();
          }
        })(this);
  
        YAHOO.util.Dom.setStyle(containerId, 'overflow', 'scroll');
        MDSS.GeoEntityTree.initializeTree("treeView", wrappedHandler);
      }
      else
      {
        this._geoTreePanel.show();
        this._geoTreePanel.bringToTop();
      }
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
    
    /**
     * Clears all select lists and adds the given results
     * as new select list options.
     */
    _clearAndAddAll : function(selectIndex, results, entityOrGeoId)
    {
      // clear all select lists
      this._clearSelectLists(0);
  
      for(var i=0; i<results.length; i++)
      {
        var childView = results[i];
        var overrideIndex = childView.getGeoEntityId() == entityOrGeoId || childView.getGeoId() == entityOrGeoId ? selectIndex : null;
  
        this._setEntityOption(childView, overrideIndex);
      }
  
      for(var i=selectIndex; i>=0; i--)
      {
        var select = this._selectLists[i];
        if(select.options.length > this._getStartIndex())
        {
          select.selectedIndex = this._getStartIndex();
        }
      }
  
      var select = this._selectLists[selectIndex];
  
      // the root won't have a default root entry
      var firstInd = selectIndex == 0? 0 : this._getStartIndex();
      var firstEntry = select.options[firstInd];
      var geoEntityView = this._geoEntityViewCache[firstEntry.id];
      this._notifySelectHandler(geoEntityView, true);
    },
  
    /**
     * Resets all select lists with the given GeoEntity
     * and its parents (recursively) and children (immediate).
     */
    _resetWithSelection : function(type, geoEntityId)
    {
      var request = new MDSS.Request({
        searchRef: this,
        type: type,
        onSuccess: function(results)
        {
          var selectIndex = this.searchRef._typeAndSelectMap[this.type];
  
          this.searchRef._clearAndAddAll(selectIndex, results, geoEntityId);
        }
      });
  
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, geoEntityId, true, this._filterType);
    },
    
    populateSelections : function(geoId)
    {
      var request = new MDSS.Request({
        that: this,
        geoId : geoId,
        onSuccess: function(results)
        {
          this.that._clearAndAddAllFindIndex(results, geoId);
        }
      });
      
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedInByGeoId(request, geoId, true, this._filterType);    
    },
  
    /**
     * Gets the children for a given GeoEntity or
     * does nothing if the event was triggered by
     * an unselect. In either case, all unselected
     * options are cleared of children.
     */
    _getChildren : function(e)
    {
      var currentOption = e.target;
      var select = currentOption.parentNode;
  
  
      var parentEntityView = this._geoEntityViewCache[currentOption.id];
  
  
      // get the children
      var request = new MDSS.Request({
      searchRef: this,
      currentType: parentEntityView.getEntityType(),
      parentEntityView: parentEntityView,
      onSuccess : function(query){
  
        // clear nodes below this type to make way for new children
        this.searchRef._clearSelectLists.call(this.searchRef, this.currentType);
  
        // these are GeoEntityView objects
        var geoEntities = query.getResultSet();
  
        for(var i=0; i<geoEntities.length; i++)
        {
          var childView = geoEntities[i];
          this.searchRef._setEntityOption(childView, null);
        }
  
        this.searchRef._notifySelectHandler(this.parentEntityView, true);
      }
      });
  
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request, parentEntityView.getGeoEntityId(), this._filterType);
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
     * Subclasses must override this method to return the index
     * at which non-root select lists start listing GeoEntities.
     */
    _getStartIndex :
    {
      IsAbstract : true
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
    _disableAllowed :
    {
      IsAbstract : true
    },
  
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