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
  },

  /**
   * Returns all currently selected GeoEntities.
   */
  _getAllSelected : function()
  {
    var geos = [];
    var selects = this._selectLists;
    for(var i=0; i<selects.length; i++)
    {
      var select = selects[i];
      var options = select.options;
      for(var j=0; j<options.length; j++)
      {
        var option = options[j];
        if(option.selected && option.value !== 'DEFAULT')
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
  },

  /**
   * Invokes the appropriate controller action to
   * render the select search component.
   */
  _invokeControllerAction : function(request, rootId)
  {
    Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displayMultipleSelectSearch(request, rootId);
  }
});