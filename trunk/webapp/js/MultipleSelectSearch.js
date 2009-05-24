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

    // map of currently selected objects
    this._selectedMap = {};
    this._CURRENT_SELECTIONS = 'currentSelections';
  },

  /**
   * Adds the given GeoEntity to the list of current selections.
   */
  _updateSelection : function(geoEntityView)
  {
    var id = geoEntityView.getGeoEntityId();

    // check the geo entity hasn't already been selected
    if(Mojo.util.isObject(this._selectedMap[id]))
    {
      return;
    }

    var liId = geoEntityView.getGeoEntityId()+"_selected";

    var li = document.createElement('li');
    li.id = liId;

    var del = document.createElement('img');
    YAHOO.util.Dom.setAttribute(del, 'src', 'imgs/icons/delete.png');
    YAHOO.util.Event.on(del, 'click', this._deleteSelection, liId, this);

    var span = document.createElement('span');
    span.innerHTML = geoEntityView.getEntityName() + ' ('+geoEntityView.getGeoId()+')';

    var div = document.createElement('div');
    div.appendChild(del);
    div.appendChild(span);

    li.appendChild(div);

    var selections = document.getElementById(this._CURRENT_SELECTIONS);
    selections.appendChild(li);

    this._selectedMap[id] = geoEntityView;
  },

  /**
   * Deletes the li element from the current selection list.
   * The GeoEntity associated with that selection will no longer
   * be passed to the calling process.
   */
  _deleteSelection : function(e, liId)
  {
  	var id = liId.replace(/_selected/, '');
    delete this._selectedMap[id];

    var li = document.getElementById(liId);
    var ul = li.parentNode;
    ul.removeChild(li);
  },

  _notifyHideHandler : function()
  {
    if(Mojo.util.isFunction(this._hideHandler))
    {
      var entities = Mojo.util.getValues(this._selectedMap);
      this._hideHandler(entities);
    }
  },

  /**
   * Adds click handlers to the
   * Select All options.
   */
  _postRender : function()
  {
    // attach event handlers to all Select All options that will clear
    var selects = YAHOO.util.Selector.query('select', this._SELECT_CONTAINER_ID);
    for(var i=0; i<selects.length; i++)
    {
      var select = selects[i];

      // root select list will not have a default option, so filter that out.
      if(select.options.length > 1)
      {
      	var selectAll = select.options[1];

        YAHOO.util.Event.on(selectAll, 'click', this._selectAll, null, this);
      }
    }
  },

  /**
   * Returns 2 as the start index. One option for a blank field,
   * and another for Select All.
   */
  _getStartIndex : function()
  {
    return 2;
  },

  /**
   * Handler when a use chooses to select all of a given type.
   * The list of current selections will be cleared.
   */
  _selectAll : function(e, obj)
  {
    this._selectedMap = {};

    var ul = document.getElementById(this._CURRENT_SELECTIONS);
    ul.innerHTML = '';


  	// add the GeoEntity type. The target is the option,
  	// and the parent is the select list whose id matches that
  	// of a GeoEntity type.
    var type = e.target.parentNode.id;

  	// Send in a template instance that can be used to add
  	// the given type as selection criteria.
  	var construct = Mojo.util.getType(type);
  	var geoEntity = new construct();
    var geoEntityView = this._copyEntityToView(geoEntity);

    this._notifySelectHandler(geoEntityView, false);
  },

  /**
   Notifies the select handler that a new select list
   * option has been chosen.
   */
  _notifySelectHandler : function(geoEntityView, updateSelection)
  {
    // add to list of currently selected

    // don't allow Earth
    if(geoEntityView.getEntityType() === "dss.vector.solutions.geo.generated.Earth")
    {
      return;
    }

    if(updateSelection)
    {
      this._updateSelection(geoEntityView);
    }

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
    Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displayMultipleSelectSearch(request, rootId);
  },

  _disableAllowed : function()
  {
  	return false;
  }
});