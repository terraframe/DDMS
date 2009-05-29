/**
 * Class that implements single select searching for GeoEntities
 */
MDSS.SingleSelectSearch = Mojo.Class.create();
MDSS.SingleSelectSearch.prototype = Mojo.Class.extend(MDSS.AbstractSelectSearch, {

  /**
   * Constructor.
   */
  initialize : function()
  {
    MDSS.AbstractSelectSearch.prototype.initialize.call(this);

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

  	if(Mojo.util.isFunction(this._selectHandler))
  	{
  	  this._selectHandler(geoEntityView);
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
});
