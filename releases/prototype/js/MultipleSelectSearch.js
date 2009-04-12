/**
 * Class that represents multiple select search functionality for GeoEntities.
 */
MDSS.MultipleSelectSearch = Mojo.Class.create();
MDSS.MultipleSelectSearch.prototype = Mojo.Class.extend(MDSS.AbstractSelectSearch, {

  /**
   * Constructor.
   */
  initialize : function()
  {
    MDSS.AbstractSelectSearch.prototype.initialize.call(this);
    
    // mapping between parents and their children.
    this._parentMap = {};
    
    // mapping between children and their parents.
    this._childMap = {};
  },
  
  /**
   * Sets the parent/child mapping so that each parent
   * knows of its children and each child knows of its
   * parent.
   */
  _setParentChildMapping : function(parent, child)
  {
    // check if entry already exists for child
    var cEntry = this._childMap[child.getGeoEntityId()];
    if(cEntry == null)
    {
      cEntry = {};
      cEntry[parent.getGeoEntityId()] = parent.getEntityName();
      this._childMap[child.getGeoEntityId()] = cEntry;
    }
    else
    {
      // add a second parent
      cEntry[parent.getGeoEntityId()] = parent.getEntityName();
    }
    
    // set the parent/child mapping
    var pEntry = this._parentMap[parent.getGeoEntityId()];
    if(pEntry == null)
    {
      pEntry = {};
      pEntry[child.getGeoEntityId()] = child.getEntityName();
      this._parentMap[parent.getGeoEntityId()] = pEntry;
    }
    else
    {
      pEntry[child.getGeoEntityId()] = child.getEntityName();
    }
  },
  
  /**
   * Returns all currently selected GeoEntities.
   */
  _getAllSelected : function()
  {
    var geos = [];
    var selects = YAHOO.util.Selector.query('select', _SELECT_CONTAINER_ID);
    for(var i=0; i<selects.length; i++)
    {
      var select = selects[i];
      var options = select.options;
      for(var j=0; j<options.length; j++)
      {
        var option = options[j];
        if(option.selected)
        {
          var geo = this._geoEntityViewCache[option.id];
        
          geos.push(geo);
        }
      }
    }
    
    return geos;
  },
  
  /**
   * Notifies the select handler that a new select list
   * option has been chosen. Because this class is for
   * multiple select, all currently selected GeoEntities
   * are returned, along with the most recently selected
   * GeoEntity.
   */
  _notifySelectHandler : function(geoEntityView)
  {
    var allSelected = this._getAllSelected();

    // update the best fit field
    var lastEntity = null;
    if(allSelected != null && allSelected.length > 0)
    {
      var lastEntity = allSelected[allSelected.length-1];
    }

    this._updateBestFit(lastEntity);
    
    if(Mojo.util.isFunction(this._selectHandler))
    {
      this._selectHandler(geoEntityView, allSelected);
    }
  }  
});