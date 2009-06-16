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
    this._criteriaMap = {};
    this._CURRENT_SELECTIONS = 'currentSelections';
    this._restrictingType = null;
  },

  /**
   * Override to handle shift+click.
   */
  _getChildren : function(e)
  {
    if(e.shiftKey)
    {
      var currentOption = e.target;
      var select = currentOption.parentNode;
      var geoEntityView = this._geoEntityViewCache[currentOption.id];

      // don't allow Earth
      if(geoEntityView.getEntityType() === "dss.vector.solutions.geo.generated.Earth")
      {
        return;
      }

      this._updateSelection(geoEntityView);
    }
    else
    {
      MDSS.AbstractSelectSearch.prototype._getChildren.call(this, e);
    }
  },

  /**
   * Adds the given GeoEntity to the list of current selections.
   */
  _updateSelection : function(geoEntityView)
  {
    var id = geoEntityView.getGeoEntityId();

    // check the geo entity hasn't already been selected
    if(Mojo.util.isObject(this._criteriaMap[id]))
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

    // add * to denote select all if geo entity type does not match query type
    if(geoEntityView.getEntityType() !== this._restrictingType)
    {
      span.innerHTML += '<span style="font-weight: bold"> *</span>';
    }

    var div = document.createElement('div');
    div.appendChild(del);
    div.appendChild(span);

    li.appendChild(div);

    var selections = document.getElementById(this._CURRENT_SELECTIONS);
    selections.appendChild(li);

    this._criteriaMap[id] = geoEntityView;
  },

  /**
   * Deletes the li element from the current selection list.
   * The GeoEntity associated with that selection will no longer
   * be passed to the calling process.
   */
  _deleteSelection : function(e, liId)
  {
  	var id = liId.replace(/_selected/, '');
    delete this._criteriaMap[id];

    var li = document.getElementById(liId);
    var ul = li.parentNode;
    ul.removeChild(li);
  },

  _notifyHideHandler : function()
  {
    if(Mojo.util.isFunction(this._hideHandler))
    {
      var entities = Mojo.util.getValues(this._criteriaMap);
      var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
      var selected = {};
      for(var i=0; i<checkboxes.length; i++)
      {
      	var check = checkboxes[i];
      	if(check.checked)
      	{
          var type = check.value;

  	      var construct = Mojo.util.getType(type);
    	  var geoEntity = new construct();
          var geoEntityView = this._copyEntityToView(geoEntity);

          selected[type] = geoEntityView;
        }
      }

      this._hideHandler(entities, selected, this._restrictingType);
    }
  },

  /**
   * Changes the type of query/map geo entity type to that of the current
   * value in the select list.
   */
  _restrictType : function(e)
  {
    var select = e.target;
    var option = select.options[select.selectedIndex];
    var type = option.value;

    if(select.selectedIndex === 0)
    {
      // don't do anything for the first "Select One" entry
      return;
    }

    // clear all prior information if the user is selecting
    // a new restricting type
    if(this._restrictingType != null)
    {
      var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
      for(var i=0; i<checkboxes.length; i++)
      {
        checkboxes[i].checked = false;
      }

      var selections = document.getElementById(this._CURRENT_SELECTIONS);
      selections.innerHTML = '';
      this._criteriaMap = {};

      this._createRoot();
    }

    document.getElementById(type+"_selectUniversalType").checked = true;

    this._restrictingType = type;

    // check the universal associated with the restricted type
  	var construct = Mojo.util.getType(type);
  	var geoEntity = new construct();
    var geoEntityView = this._copyEntityToView(geoEntity);

    this._notifySelectHandler(geoEntityView);
  },

  /**
   * Adds click handlers to the
   * Select All options.
   */
  _postRender : function()
  {
  	// attach event to the select box where the user chooses the query/map type
    YAHOO.util.Event.on('restrictingType', 'change', this._restrictType, null, this);

    // attach event handlers to all Select All options
    var selects = YAHOO.util.Selector.query('select.typeSelect', this._SELECT_CONTAINER_ID);
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

    // create toggle events to display selectable types
    var toggles = YAHOO.util.Selector.query('input.selectUniversalType', this._SELECT_CONTAINER_ID);
    for(var i=0; i<toggles.length; i++)
    {
      var toggle = toggles[i];
      YAHOO.util.Event.on(toggle, 'click', this._notifySelectUniversalTypeHandler, toggle.value, this);
    }
  },

  /**
   * Returns 2 as the start index. One option for a blank field,
   * and another for Select All.
   */
  _getStartIndex : function()
  {
    return 1;
  },

  /**
   * Gets the appropriate controller action to
   * render the select search component.
   */
  _getControllerAction : function()
  {
    return Mojo.$.dss.vector.solutions.geo.GeoEntityTreeController.displayMultipleSelectSearch;
  },

  _disableAllowed : function()
  {
  	return true;
  }
});