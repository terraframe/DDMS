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
  },
  
  /**
   * Notifies the select handler that a GeoEntity
   * has been selected. The GeoEntityView is passed
   * to the handler.
   */
  _notifySelectHandler : function(geoEntityView)
  {
  	if(Mojo.util.isFunction(this._selectHandler))
  	{
  	  this._selectHandler(geoEntityView);
  	}
  },
  
  /**
   * Invokes the appropriate controller action to
   * render the select search component.
   */
  _invokeControllerAction : function(request, rootId)
  {
    Mojo.$.dss.vector.solutions.geo.GeoEntityTree.displaySingleSelectSearch(request, rootId);
  },
});
