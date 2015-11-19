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
      
      // map between type and select element
      // where key/value is type/select element in this._selectLists
      this._selectMap = {};      
  
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
      
      this._suffix = Mojo.Util.generateId();
      
      // TODO this is hacky ... clean it up
      var wrappedHandler = (function(searchRef)
      {
        return function(geoEntity){
        
          searchRef._notifyTreeSelectHandler(geoEntity);
          searchRef._geoTreePanel.hide();
        }
      })(this);

      this._tree = new MDSS.GeoEntityTree("treeView" + this._suffix, wrappedHandler, this._selectSearchRootId);
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
    
    setOkHandler : function(handler)
    {
      this._okHandler = handler;
    },
    
    setCancelHandler : function(handler)
    {
      this._cancelHandler = handler;
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
      if(e.getType() == MDSS.Event.BEFORE_SEARCH)
      {
        var autocomplete = e.getValue().autocomplete;
    
        var searchedId = autocomplete.getDisplayElement().id;
          
        // When a new value is inputed we want to hide all
        // the other visible search results
        for(var i=0; i < this._autocompletes.length; i++)
        {
          var element = this._autocompletes[i];
          var elementId = element.getDisplayElement().id;
        
          if(searchedId != elementId) {
            element.hide();
          }
        }
      }
      else if (e.getType() == MDSS.Event.AFTER_SELECTION && e.getValue().autoComplete != null)
      {
        // Clear all the input elements because the ids that used to be there are no longer valid
        for (var i = 0; i < this._autocompletes.length; ++i)
        {
          if (this._autocompletes[i].getHashCode() !== e.getValue().autoComplete.getHashCode())
          {
            this._autocompletes[i]._hasSelection = true;
            this._autocompletes[i].setOption({})
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
      var that = this;
      
      var request = new MDSS.Request({
        onSuccess : function(hierarchies)
        {
          that._renderHierarchies(hierarchies);
        }
      });
  
      var method = this._getControllerAction();
      
      method(request, this._selectSearchRootId, this.getFlags(), this.getExtraUniversals());
    },
    
    /**
     * Creates the OK, Cancel buttons.
     */
    _renderButtons : function(parent)
    {
      var fac = com.runwaysdk.ui.Manager.getFactory();
      var that = this;
      
      var ok = com.runwaysdk.Localize.get("OK");
      var cancel = com.runwaysdk.Localize.get("Cancel");
      
      this._bContainer = fac.newElement("div", null, {height:"50px", width:"130px", "padding-left":"120px", "padding-top":"20px"});
      this._bOK = fac.newButton(ok, function() {
        if (that._okHandler != null)
        {
          that._okHandler();
        }
        that.hide();
      });
      this._bOK.setStyle("margin-right", "5px");
      this._bOK.setEnabled(false);
      this._bContainer.appendChild(this._bOK);
      this._bCancel = fac.newButton(cancel, function() {
        if (that._cancelHandler != null)
        {
          that._cancelHandler();
        }
        that.hide();
      });
      this._bContainer.appendChild(this._bCancel);
      
      this._bContainer.render(parent);
    },
    
    _renderHierarchies : function(hierarchies)
    {
      var UI = Mojo.Meta.alias(Mojo.UI_PACKAGE+'*');
      UI.Manager.setFactory("YUI3");
        
      var factory = UI.Manager.getFactory();

      var panelId = "searchSelectModal_" + this._suffix;
        
      // use modal to contain MDSS101
      this._searchModal = new YAHOO.widget.Panel(panelId,  {
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
      this._searchModal.subscribe('beforeHide', function(){
  
        this._closeAllResultPanels();
  
        if(this._geoTreePanel != null)
        {
          this._geoTreePanel.hide();
        }
  
        this._hideHandler();
  
      }, null, this);
      
      var componentDiv = factory.newElement('div', {'id':this._SELECT_CONTAINER_ID + this._suffix, 'class':this._SELECT_CONTAINER_ID});

      var dl = factory.newElement('dl');
      componentDiv.appendChild(dl);
      
      for(var i = 0; i < hierarchies.length; i++ )
      {
        this._renderHierarchy(factory, hierarchies[i], dl, i);        
      }
      
      var selectionDiv = factory.newElement('div', {'id':'searchSelection' + this._suffix, 'class':'searchSelection'});

      var h3 = factory.newElement('h3');
      h3.setInnerHTML(MDSS.localize('Current_Selection'));      
      selectionDiv.appendChild(h3);
      selectionDiv.appendChild(factory.newElement('hr'));
      selectionDiv.appendChild(this._renderCurrentSelection(factory));
      
      this._outerDiv = factory.newElement('div');
      this._outerDiv.setStyle('width', '700px');
      this._outerDiv.setStyle('margin-top', '15px');
      
      this._outerDiv.appendChild(componentDiv);      
      this._outerDiv.appendChild(selectionDiv);
      
      this._renderButtons(this._outerDiv);
      
      var panelDiv = factory.newElement('div');
      
      var titleSpan = factory.newElement("div", null, {"font-size":"20px", width:"300px", "margin-left":"auto", "margin-right":"auto"});
      
      var geoLabel = "";
//      try
//      {
        if (!(this._filterType == null || this._filterType === '' || (Mojo.Util.isArray(this._filterType) && this._filterType.length === 0)))
        {
          if (Mojo.Util.isArray(this._filterType))
          {
            for (var i = 0; i < this._filterType.length; ++i)
            {
              geoLabel = geoLabel + eval("new " + this._filterType[i] + "().getMd().getDisplayLabel()");
              if (i < this._filterType.length-1)
              {
                geoLabel = geoLabel + ", ";
              }
            }
          }
          else
          {
            geoLabel = eval("new " + this._filterType + "().getMd().getDisplayLabel()");
          }
        }
//      }
//      catch (ex) {}
      if (geoLabel == null || geoLabel === "")
      {
        geoLabel = com.runwaysdk.Localize.get("Geo_Entity");
      }
      titleSpan.setInnerHTML(com.runwaysdk.Localize.get("SelectAValid") + " " + geoLabel);
      panelDiv.appendChild(titleSpan);
      
      panelDiv.appendChild(this._outerDiv);
      
      this._searchModal.appendToBody(panelDiv.getRawEl());
      this._searchModal.render(document.body);
  
      this._doFilter();
      
      // hook event to open tree
      new YAHOO.util.Element("treeOpener" + this._suffix).on('click', this._openTree, null, this);
  
      this._postRender();
  
      this._rendered = true;
  
      this._createRoot();
    },
    
    _renderHierarchy : function(factory, hierarchy, dl, index)
    {
      var rootId = 'h' + Mojo.Util.generateId();
      var type = 'dss.vector.solutions.geo.generated.' + hierarchy.getTypeName();
      
      this._typeAndSelectMap[type] = index; // select name is the type

      var dt = this._renderHierarchyHeader(factory, hierarchy, index, rootId);      
      dl.appendChild(dt);
        
      var dd = factory.newElement('dd', {'id':rootId + '_dd'});
      dl.appendChild(dd);
                
      var container = factory.newElement('div', {'id':rootId + '_container', 'class':'typeContainer'});
      dd.appendChild(container);
        
      if(index != 0)
      {
        var input = factory.newElement('input', {'type':'text', 'id':rootId + '_search', 'class':'ajaxSearch'});
        input.setStyle('width', '225px');
        container.appendChild(input);
          
        var results = factory.newElement('div', {'id':rootId + '_results', 'class':'ajaxResults'});
        container.appendChild(results);
        
        var search = input.getRawEl();
            
        var sFunction = Mojo.Util.bind(this, function(typeRef, request, value){
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request, typeRef, value, this.enforcesRoot());
        }, type);
            
        var sHandler = Mojo.Util.bind(this, function(typeRef, option){
          this._resetWithSelection(typeRef, option.id);
        }, type);
            
        var listener = Mojo.Util.bind(this, this.eventHandler);
            
        var autocomplete = new MDSS.GenericSearch(search, null, this._modalListFunction, this._modalDisplayFunction, this._modalIdFunction, sFunction, sHandler);                        
        autocomplete.addListener(listener);
            
        this._autocompletes.push(autocomplete);
      }
        
      var select = factory.newElement('select', {'name':rootId, 'id':rootId, 'class':'typeSelect', 'disabled':'disabled'});
      select.setStyle('width', '250px');
      container.appendChild(select);
      
      this._selectLists.push(select.getRawEl());
      this._selectMap[type] = select.getRawEl();

      if(index != 0)
      {
        var option = factory.newElement('option', {'value':'DEFAULT'});
        option.setInnerHTML(MDSS.localize("Select_One"));
        select.appendChild(option);                            
      }        
      else
      {
        var a = factory.newElement('a', {'id':'treeOpener' + this._suffix, 'href':'#'});
        a.setStyle('marginLeft', '20px');          
        container.appendChild(a);
          
        var img = factory.newElement('img', {'src':'./imgs/icons/world.png'});
        img.setStyle('marginRight', '5px');          
        a.appendChild(img);
        a.appendInnerHTML(MDSS.localize('Tree'));
                    
        container.appendChild(factory.newElement('br'));
          
        var treeContainer = factory.newElement('div', {'id':'treeViewContainer' + this._suffix, 'class':'yui-skin-sam', 'style':'background-color:white;'});
        treeContainer.appendChild(factory.newElement('div', {'id':'treeView' + this._suffix}));
        container.appendChild(treeContainer);
      }    	
    },
    
    _renderHierarchyHeader :
    {
      IsAbstract : true
    },
    
    _renderCurrentSelection :
    {
      IsAbstract : true
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
  
        var option = new YAHOO.util.Element(optionRaw);
        option.on('click', this._getChildren, null, this);
  
        this._geoEntityViewCache[geoEntityView.getGeoEntityId()] = geoEntityView;
      }
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
     * Returns the appropriate controller action to
     * render the select search component.
     */
    _getControllerAction : function()
    {
      return Mojo.$.dss.vector.solutions.geo.GeoHierarchy.collectHierarchies;
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
      var containerId = "treeViewContainer" + this._suffix;
  
      if(this._geoTreePanel == null)
      {
        this._geoTreePanel = new YAHOO.widget.Panel(containerId, {width:'400px', height:'400px', zindex:9});
  
        this._geoTreePanel.render();
        this._geoTreePanel.bringToTop();
  
        YAHOO.util.Dom.setStyle(containerId, 'overflow', 'scroll');
        this._tree.render();
        //MDSS.GeoEntityTree.initializeTree("treeView" + this._suffix, wrappedHandler, this._selectSearchRootId);
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
      var firstInd = selectIndex == 0 ? 0 : this._getStartIndex();
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
  
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, geoEntityId, true, "");
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
  
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request, parentEntityView.getGeoEntityId(), "");
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