/**
 * Encapsulates functionality for the GeoEntity select search.
 */
MDSS.SelectSearch = (function(){
  
  // cache of GeoEntities with key/value = geoEntity.getId()/geoEntity
  var _geoEntityCache = {};

  // container id that holds the select id
  var _containerId = "selectSearchComponent";
  
  // handler for when a new geo entity is selected
  var _selectHandler = null;
  
  // handler for when a geo entity is selected via the tree
  var _treeSelectHandler = null;
  
  // mapping between parent and children
  var _parentMap = {};
  
  // mapping between child and parents
  var _childMap = {};

  // reference to modal that contains the select search
  var _searchModal = null;

  // reference to the Yahoo Panel that contains the search tree.
  var _geoTreePanel = null;
  
  // the GeoEntity subtype class by which to filter results
  var _filterType = null;
  
  _setParentChildMapping = function(parent, child)
  {
    // check if entry already exists for child
    var cEntry = _childMap[child.getId()];
    if(cEntry == null)
    {
      cEntry = {};
      cEntry[parent.getId()] = parent.getEntityName();
      _childMap[child.getId()] = cEntry;
    }
    else
    {
      // add a second parent
      cEntry[parent.getId()] = parent.getEntityName();
    }
    
    // set the parent/child mapping
    var pEntry = _parentMap[parent.getId()];
    if(pEntry == null)
    {
      pEntry = {};
      pEntry[child.getId()] = child.getEntityName();
      _parentMap[parent.getId()] = pEntry;
    }
    else
    {
      pEntry[child.getId()] = child.getEntityName();
    }
  }
  
  /**
   * Returns all currently selected GeoEntities.
   */
  _getAllSelected = function()
  {
    var geos = [];
    var selects = YAHOO.util.Selector.query('select', _containerId);
    for(var i=0; i<selects.length; i++)
    {
      var select = selects[i];
      var options = select.options;
      for(var j=0; j<options.length; j++)
      {
        var option = options[j];
        if(option.selected)
        {
          var geo = _geoEntityCache[option.id];
        
          geos.push(geo);
        }
      }
    }
    
    return geos;
  }
  
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
      
    delete _geoEntityCache[optionEl.id];
      
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
   * Creates the mapping between the given GeoEntity
   * and an option for the give select element that represents
   * the GeoEntity's type.
   */
  _setEntityOption = function(geoEntity)
  {
    var select = document.getElementById(geoEntity.getType());

    if(select && !_geoEntityCache[geoEntity.getId()])
    {
      select.disabled = false;

      var optionRaw = document.createElement('option');
      optionRaw.value = geoEntity.getId();
      optionRaw.id = geoEntity.getId();
      optionRaw.innerHTML = geoEntity.getEntityName();
        
      select.appendChild(optionRaw);
      
      var option = new YAHOO.util.Element(optionRaw);
      option.on('click', _getChildren);

      _geoEntityCache[geoEntity.getId()] = geoEntity;
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
  
  _updateBestFit = function(geoEntity)
  {
    document.getElementById('bestFitName').innerHTML = geoEntity != null ? geoEntity.getEntityNameMd().getDisplayLabel() : '';
    document.getElementById('bestFitNameValue').innerHTML = geoEntity != null ? geoEntity.getEntityName() : '';
    document.getElementById('bestFitId').innerHTML = geoEntity != null ? geoEntity.getGeoIdMd().getDisplayLabel() : '';
    document.getElementById('bestFitIdValue').innerHTML = geoEntity != null ? geoEntity.getGeoId() : '';
  }
  
  /**
   * Notifies the select handler that a new select list
   * option has been chosen.
   */
  _notifySelectHandler = function(geoEntity)
  {
    var allSelected = _getAllSelected();

    // update the best fit field
    var lastEntity = null;
    if(allSelected != null && allSelected.length > 0)
    {
      var lastEntity = allSelected[allSelected.length-1];
    }

    _updateBestFit(lastEntity);
    
    if(Mojo.util.isFunction(_selectHandler))
    {
      _selectHandler(geoEntity, allSelected);
    }
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
      var request = new Mojo.ClientRequest({
      
      onSuccess : function(query, geoEntity){
        
        var geoEntities = query.getResultSet();
        
        for(var i=0; i<geoEntities.length; i++)
        {
          var child = geoEntities[i];
          _setEntityOption(child);
          
          _setParentChildMapping(geoEntity, child);
        }
        
        _notifySelectHandler(geoEntity);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
        }
      });
    
      var geoEntity = _geoEntityCache[currentOption.id];
    
      geoEntity.getOrderedChildren(request, _filterType);
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
    
    var request = new Mojo.ClientRequest({
      onSuccess : function(geoEntity)
      {
        var select = document.getElementById(geoEntity.getType());
        _clearOptionsOnSelect(select);
        
        _setEntityOption(geoEntity);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByGeoId(request, geoId);
  }
  
  /**
   * Opens the tree for extra search functionality.
   */
  _openTree = function()
  {
    if(_geoTreePanel == null)
    {
      _geoTreePanel = new YAHOO.widget.Panel("treeViewContainer", {width:'300px', height:'300px', zindex:9});
      
      // Bug Workaround: The Yahoo ContextMenu loses its event handlers in
      // the tree, so destroy the tree every time the panel is closed, then
      // use a new tree per request.
      _geoTreePanel.subscribe('beforeHide', function(){
        MDSS.GeoEntityTree.destroyAll();
      });
      _geoTreePanel.render();
      _geoTreePanel.bringToTop();
    }
    else
    {
      _geoTreePanel.show();
    }
    
    // always create a new tree per request
    MDSS.GeoEntityTree.initializeTree("treeView", function(geoEntity){
      _updateBestFit(geoEntity);
      _treeSelectHandler(geoEntity);
    }, _filterType);
  }
  
  /**
   * Changes the GeoEntity subtype filter and clears
   * the select search and tree.
   */
  _changeFilter = function(filter)
  {
    
  }
  
  /**
   * Creates the root entity and adds it to the select search.
   */
  _createRoot = function()
  {
    // Populate the root
    var request = new Mojo.ClientRequest({
      onSuccess : function(geoEntity){
        _setEntityOption(geoEntity);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.get(request, MDSS.SelectSearchRootId);
  }
  
  /**
   * Renders the modal with the default select search.
   */
  _renderModal = function()
  {
    var request = new Mojo.ClientRequest({
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
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
  
    Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displaySelectSearch(request, MDSS.SelectSearchRootId);
  }
  
  /**
   * Checks if the searching has been enabled (i.e., the modal has
   * been initialized).
   */
  _isInitialized = function()
  {
  	return _searchModal != null;
  }
  
  /**
   * Shows the select modal if it is hidden.
   */
  _show = function()
  {
    _searchModal.show();
  }
  
  /**
   * Hides the modal if it visibile.
   */
  _hide = function()
  {
  	_selectModal.hide();
  }
  
  /**
   * Initializes the select search.
   */
  _initialize = function(selectHandler, treeSelectHandler, filterType)
  {
    _selectHandler = selectHandler;
    _treeSelectHandler = treeSelectHandler;
    
    var selects = YAHOO.util.Selector.query('select', _containerId);
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
    var searches = YAHOO.util.Selector.query('input.manualSearch', _containerId);
    for(var i=0; i<searches.length; i++)
    {
      var search = new YAHOO.util.Element(searches[i]);
      search.on('click', _manualSearch);
    }
    
    _filterType = arguments.length === 3 ? filterType : '';

    _renderModal();
  }
  
  // public methods/properties
  return {
    initialize : _initialize,
    isInitialized : _isInitialized,
    show : _show,
    hide : _hide,
    changeFilter : _changeFilter
  };
  
})();