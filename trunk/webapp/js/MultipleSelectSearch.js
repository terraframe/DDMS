/**
 * Class that represents multiple select search functionality for GeoEntities.
 */
Mojo.Meta.newClass('MDSS.MultipleSelectSearch', {

  Extends : MDSS.AbstractSelectSearch,
  
  Instance : {

    /**
     * Constructor.
     */
    initialize : function()
    {
      this.$initialize(true);
  
      // map of currently selected objects
      this._criteriaMap = {};
      this._CURRENT_SELECTIONS = 'currentSelections';
      
      this._initSelectedUniversals = [];
      
      // optional function handler that will be called before adding an entity as criteria
      this._validator = null;
    },
    
    setValidator : function(validator)
    {
      this._validator = validator;
    },
    
    _notifyTreeSelectHandler : function(geoEntityView)
    {
      this._updateSelection(geoEntityView, true);
    },
    
    setSelectedUniversals : function(selected)
    {
      if(selected != null)
      {
        if(this._rendered)
        {
          var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
          for(var i=0; i<checkboxes.length; i++)
          {
            var check = checkboxes[i];
            if(check.checked)
            {
              check.checked = false;
            }
          }
        }
        else
        {
          this._initSelectedUniversals = [];
        }
    
        for(var i=0; i<selected.length; i++)
        {
          if(this._rendered)
          {
            var check = document.getElementById(selected[i]+'_selectUniversalType');
            if(check)
            {
              check.checked = true;
            }
          }
          else
          {
            this._initSelectedUniversals.push(selected[i]);
          }
        }
      }
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
  
        this._updateSelection(geoEntityView, true);
      }
      else
      {
        this.$_getChildren(e);
      }
    },
    
    /**
     * Sets the query criteria on this search. The criteria
     * are the geo entities by which the user is restricting the
     * query. This will clear any prior selected options.
     */
    setCriteria : function(criteria)
    {
      if(criteria != null)
      {
        this._criteriaMap = {};
      
        if(this._rendered)
        {
          var selections = document.getElementById(this._CURRENT_SELECTIONS);
          selections.innerHTML = '';
        }
    
        for(var i=0; i<criteria.length; i++)
        {
          this._updateSelection(criteria[i], this._rendered);
        }
      }
    },
    
    retain : function(ids)
    {
    },
  
    /**
     * Adds the given GeoEntity to the list of current selections.
     */
    _updateSelection : function(geoEntityView, updateList)
    {
      var id = geoEntityView.getGeoEntityId();
  
      // check the geo entity hasn't already been selected
      if(Mojo.Util.isObject(this._criteriaMap[id]))
      {
        return;
      }
      
      if(Mojo.Util.isFunction(this._validator))
      {
        var cb = Mojo.Util.bind(this, this._updateSelection2, geoEntityView, updateList);
        this._validator(cb, geoEntityView, Mojo.Util.getKeys(this._criteriaMap, true));
      }
      else
      {
        this._updateSelection2(geoEntityView, updateList);
      }
    },
    
    _updateSelection2 : function(geoEntityView, updateList)
    {
      var id = geoEntityView.getGeoEntityId();
      this._criteriaMap[id] = geoEntityView;
  
      if(updateList)
      {
        this._updateSelectionList(geoEntityView);
      }
    },
    
    /**
     * 
     */
    _updateSelectionList : function(geoEntityView)
    {
      var liId = geoEntityView.getGeoEntityId()+"_selected";
  
      var li = document.createElement('li');
      li.id = liId;
  
      var del = document.createElement('img');
      YAHOO.util.Dom.setAttribute(del, 'src', 'imgs/icons/delete.png');
      YAHOO.util.Event.on(del, 'click', this._deleteSelection, liId, this);
  
      var span = document.createElement('span');
      span.innerHTML = this.constructor.formatDisplay(geoEntityView);
  
      var div = document.createElement('div');
      div.appendChild(del);
      div.appendChild(span);
  
      li.appendChild(div);
  
      var selections = document.getElementById(this._CURRENT_SELECTIONS);
      selections.appendChild(li);
    },
    
    _doCreateRoot : function(request)
    {
      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, this._selectSearchRootId, false, '');
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
    
    _notifySelectHandler : function()
    {
      // do nothing
    },
  
    _notifyHideHandler : function()
    {
      if(Mojo.Util.isFunction(this._hideHandler))
      {
        var entities = Mojo.Util.getValues(this._criteriaMap);
        var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
        var selected = [];
        for(var i=0; i<checkboxes.length; i++)
        {
          var check = checkboxes[i];
          if(check.checked)
          {
            var type = check.value;
            selected.push(type);
          }
        }
  
        this._hideHandler(entities, selected);
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
      var construct = Mojo.Meta.findClass(type);
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
      // create toggle events to display selectable types
      var toggles = YAHOO.util.Selector.query('input.selectUniversalType', this._SELECT_CONTAINER_ID);
      for(var i=0; i<toggles.length; i++)
      {
        var toggle = toggles[i];
        YAHOO.util.Event.on(toggle, 'click', this._notifySelectUniversalTypeHandler, toggle.value, this);
      }
      
      var views = Mojo.Util.getValues(this._criteriaMap);
      for(var i=0; i<views.length; i++)
      {
        this._updateSelectionList(views[i]);
      }
      
      for(var i=0; i<this._initSelectedUniversals.length; i++)
      {
        var check = document.getElementById(this._initSelectedUniversals[i]+"_selectUniversalType");
        if(check)
        {
          check.checked = true;
        }
      }
    },
  
    /**
     * Returns 1 as the start index with one option for the blank field.
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
  
  }
});