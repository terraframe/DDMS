MDSS.AbstractSelectSearch = Mojo.Class.create();
MDSS.AbstractSelectSearch.SelectSearchRootId = null; // must be set before instantiating a subclass.
MDSS.AbstractSelectSearch.prototype = {
  
  /**
   * Constructor.
   */
  initialize : function()
  {
    // handler for when a new geo entity is selected
    this._selectHandler = null;

    // handler for when a geo entity is selected via the tree
    this._treeSelectHandler = null;
    
    // The id of the element that contains the select lists.
    this._SELECT_CONTAINER_ID = "selectSearchComponent";
    
    // cache of GeoEntities with key/value = geoEntity.getId()/geoEntity
    this._geoEntityViewCache = {};
    
    // reference to modal that contains the select search
    this._searchModal = null;

    // reference to the Yahoo Panel that contains the search tree.
    this._geoTreePanel = null;
  
    // the GeoEntity subtype class by which to filter results
    this._filterType = null;
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
    view.setEntityName(geoEntity.getEntityName());
    view.setEntityType(geoEntity.getType());
    
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
  	this._selectModal.hide();
  },
  
  /**
   * Sets the handler function to be called when
   * a user selects a GeoEntity.
   */
  setSelectHandler : function(handler)
  {
  	this._selectHandler = handler;
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
   * Sets the root entity
   */
  _createRoot : function()
  {
    // Populate the root
    var request = new MDSS.Request({
      onSuccess : function(geoEntity){
        var view = _copyEntityToView(geoEntity);
        _setEntityOption(view);
      }
    });
    
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, MDSS.SelectSearchRootId);
  },
  
  /**
   * Renders the select search component.
   */
  render : function()
  {
    var selects = YAHOO.util.Selector.query('select', this._SELECT_CONTAINER_ID);
    for(var i=0; i<selects.length; i++)
    {
      var select = selects[i];
      
      // add handler to clear nodes (except for the root)
      if(i != 0)
      {
        var defaultOption = new YAHOO.util.Element(select.options[0]);
        defaultOption.on('click', _clearOptions);
      }
    }
    
    // hook all search events for manual entry
    var searches = YAHOO.util.Selector.query('input.manualSearch', this._SELECT_CONTAINER_ID);
    for(var i=0; i<searches.length; i++)
    {
      var search = new YAHOO.util.Element(searches[i]);
      search.on('click', _manualSearch);
    }
  	
    var request = new MDSS.Request({
      onSuccess : function(html){
        
        // use modal to contain MDSS101
        _searchModal = new YAHOO.widget.Panel("searchSelectModal",  {
          width:"100%", 
          height: "100%",
          fixedcenter:true, 
          close:true, 
          draggable:false, 
          zindex:4,
          modal:true,
          visible:true
        });
  
        // must hide the tree if search model is hidden
        _searchModal.subscribe('beforeHide', function(){
          if(_geoTreePanel != null)
          {
            _geoTreePanel.hide();
          }
        });
  
        _searchModal.setBody(html);
        _searchModal.render(document.body);
        
        // hook event to open tree
        var treeOpener = new YAHOO.util.Element("treeOpener");
        treeOpener.on('click', _openTree);
        
        _createRoot();
      }
    });
  	
    this._invokeControllerAction(request, MDSS.SelectSearchRootId);
  },
  
  /**
   * Creates the mapping between the given GeoEntity
   * and an option for the select element that represents
   * the GeoEntity's type.
   */
  _setEntityOption : function(geoEntityView)
  {
    var select = document.getElementById(geoEntityView.getEntityType());

    if(select && !this._geoEntityViewCache[geoEntityView.getGeoEntityId()])
    {
      select.disabled = false;

      var optionRaw = document.createElement('option');
      optionRaw.value = geoEntityView.getGeoEntityId();
      optionRaw.id = geoEntityView.getGeoEntityId();
      optionRaw.innerHTML = geoEntityView.getEntityName();
        
      select.appendChild(optionRaw);
      
      var option = new YAHOO.util.Element(optionRaw);
      option.on('click', _getChildren);

      this._geoEntityViewCache[geoEntityView.getGeoEntityId()] = geoEntityView;
    }
  },
  
  /**
   * Abstract method to notify the select
   * handler that a GeoEntity has been selected.
   */
  _notifySelectHandler : function(geoEntityView)
  {
  	// abstract
  },
  
  /**
   * Invokes the appropriate controller action to
   * render the select search component.
   */
  _invokeControllerAction : function(request, rootId)
  {
    // abstract
  },
  
  /**
   * Updates the HTML area with information about the most recently selected
   * (best fit) GeoEntity.
   */
  _updateBestFit : function(geoEntity)
  {
    document.getElementById('bestFitName').innerHTML = geoEntity != null ? geoEntity.getEntityNameMd().getDisplayLabel() : '';
    document.getElementById('bestFitNameValue').innerHTML = geoEntity != null ? geoEntity.getEntityName() : '';
    document.getElementById('bestFitId').innerHTML = geoEntity != null ? geoEntity.getGeoIdMd().getDisplayLabel() : '';
    document.getElementById('bestFitIdValue').innerHTML = geoEntity != null ? geoEntity.getGeoId() : '';
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
      
      // Bug Workaround: The Yahoo ContextMenu loses its event handlers in
      // the tree, so destroy the tree every time the panel is closed, then
      // use a new tree per request.
      this._geoTreePanel.subscribe('beforeHide', function(e, obj){
      	YAHOO.util.Dom.setStyle(obj.containerId, 'overflow', 'none');
        MDSS.GeoEntityTree.destroyAll();
      }, {containerId:containerId});
      this._geoTreePanel.render();
      this._geoTreePanel.bringToTop();
    }
    else
    {
      _geoTreePanel.show();
    }
    
    // always create a new tree per request
    MDSS.GeoEntityTree.initializeTree("treeView", function(geoEntity){
      this._updateBestFit(geoEntity);
      this._treeSelectHandler(geoEntity);
    }, this._filterType);
    
    
    YAHOO.util.Dom.setStyle(containerId, 'overflow', 'scroll');
  },
  
  /**
   * Changes the GeoEntity subtype filter and clears
   * the select search and tree.
   */
  _changeFilter : function(filter)
  {
    
  }
}


/**
 * Encapsulates functionality for the GeoEntity select search.
 */
MDSS.SelectSearch1 = (function(){
  
  /**
   * Removes the given option from the DOM and
   * clears the GeoEntity cache of the object
   * with the same id.
   */
  _removeOptionNode = function(option)
  {
    var optionEl = Mojo.util.isString(option) ? document.getElementById(option) : option;
    var select = optionEl.parentNode;
    select.removeChild(optionEl);
      
    delete _geoEntityViewCache[optionEl.id];
      
    if(select.options.length == 1)
    {
      select.selectedIndex = -1;
      select.disabled = true;
    }       
  }
  
  /**
   * Removes all children of the given parent (recursively).
   */
  _clearOptionChildren = function(parentId)
  {
    var pEntry = _parentMap[parentId];
    if(Mojo.util.isObject(pEntry))
    {
      var childrenIds = Mojo.util.getKeys(pEntry);
      for(var i=0; i<childrenIds.length; i++)
      {
        var childId = childrenIds[i];
        
        // recurse first to delete all children of child
        _clearOptionChildren(childId);
        
        var cEntry = _childMap[childId];
        if(Mojo.util.getKeys(cEntry) > 1)
        {
          // more than one parent, so remove entry but not the option
          delete cEntry[parentId];
        }
        else
        {
          // only one parent, so delete child map entry
          delete _childMap[childId];
          
          _removeOptionNode(childId);
        }
        
        // always delete this parent/child mapping
        delete pEntry[childId];
      }
    }
  }
  
  /**
   * Event handler to clear options.
   */
  _clearOptions = function(e)
  {
    var defaultOption = e.target;
    var select = defaultOption.parentNode;
    
    _clearOptionsOnSelect(select);
  }
  
  /**
   * Clears options on the given select list.
   */
  _clearOptionsOnSelect = function(select)
  {
    var options = select.options;
    var oLength = options.length;
    for(var i=oLength-1; i>0; i--)
    {
      var option = options[i];
      _clearOptionChildren(option.id);
      
      // unhook parents explicitely, otherwise
      // they'll never know their children are gone
      var cEntry = _childMap[option.id];
      var parentIds = Mojo.util.getKeys(cEntry);
      for(var j=0; j<parentIds.length; j++)
      {
        var pEntry = _parentMap[parentIds[j]];
        delete pEntry[option.id];
      }

      _removeOptionNode(option);
    }
    
    _notifySelectHandler(null);
  }
  
  /**
   * Gets the children for a given GeoEntity or
   * does nothing if the event was triggered by
   * an unselect. In either case, all unselected
   * options are cleared of children.
   */
  _getChildren = function(e)
  {
    var currentOption = e.target;
    var select = currentOption.parentNode;
    var parentEntityView = _geoEntityViewCache[currentOption.id];
    
    // clear all unchecked options
    var options = select.options;
    for(var i=0; i<options.length; i++)
    {
      var option = options[i];
      if(option.value !== 'DEFAULT' && !option.selected)
      {
        _clearOptionChildren(option.id);
      }
    }
    
    if(!currentOption.selected)
    {
      // do nothign. Options already cleared before reaching
      // this point.
      _notifySelectHandler(null);
      return;
    }
    else
    {
      // get the children
      var request = new MDSS.Request({
      parentEntityView: parentEntityView,
      onSuccess : function(query){
        
        // these are GeoEntityView objects
        var geoEntities = query.getResultSet();
        
        for(var i=0; i<geoEntities.length; i++)
        {
          var childView = geoEntities[i];
          _setEntityOption(childView);
          
          _setParentChildMapping(this.parentEntityView, childView);
        }
        
        _notifySelectHandler(this.parentEntityView);
      }
      });
    
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getOrderedChildren(request, parentEntityView.getGeoEntityId(), _filterType);
    }
  }
  
  /**
   * Searches for a specific GeoEntity.
   */
  _manualSearch = function(e)
  {
    var select = e.target;
    var input = select.previousSibling;
    
    var geoId = input.value;
    
    var request = new MDSS.Request({
      onSuccess : function(geoEntity)
      {
        var select = document.getElementById(geoEntity.getType());
        _clearOptionsOnSelect(select);
        
        var view = _copyEntityToView(geoEntity);
        _setEntityOption(view);
      }
    });
    
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByGeoId(request, geoId);
  }
  
  
})();