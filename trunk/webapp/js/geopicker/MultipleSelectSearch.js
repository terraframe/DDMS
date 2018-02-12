#-------------------------------------------------------------------------------
# Copyright (C) 2018 IVCC
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------
/**
 * Class that represents multiple select search functionality for GeoEntities.
 */
Mojo.Meta.newClass('MDSS.MultipleSelectSearch', {

  Extends : MDSS.AbstractSelectSearch,
  
  Instance : {

    /**
     * Constructor.
     */
    initialize : function(queryBase)
    {
      this.$initialize(true);
      
      this._queryBase = queryBase;
  
      // map of currently selected objects
      this._criteriaMap = {};
      this._CURRENT_SELECTIONS = 'currentSelections';
      
      this._initSelectedUniversals = [];
      
      // A mapping between type - checkbox element id
      this._checkboxMap = {};
      
      // Create a list of what values are on the QB so we can diff it with what the user is selecting so we know when to enable/disable the ok button.
      this._applied = new com.runwaysdk.structure.HashSet();
      this._unsaved = new com.runwaysdk.structure.HashSet();
      
      // optional function handler that will be called before adding an entity as criteria
      this._validator = null;
      
      this.setOkHandler(Mojo.Util.bind(this, this.okHandler));
      this.setCancelHandler(Mojo.Util.bind(this, this.cancelHandler));
      this.setHideHandler(Mojo.Util.bind(this, this.cancelHandler));
      
      
      
      // Hack for ticket 3458
      // There's no way with the new 'onchange' event to detect shift clicks, so we're doing this hacky crap to listen for shift key
      var that = this;
      this._isShiftDown = false;
      
      var docu = new YAHOO.util.Element(document);
      docu.on('keydown', function(down) {
        if(down.keyCode == 16 || down.keyCode == 17) {
          that._isShiftDown = true;
        }
      });
      docu.on("keyup", function(up){
        if (up.keyCode == 16 || up.keyCode == 17) {
          that._isShiftDown = false;
        }
      });
    },
    
    _renderHierarchyHeader : function(factory, hierarchy, index, rootId)
    {
      var dt = factory.newElement('dt', {'id':rootId + '_dt'});
      var type = 'dss.vector.solutions.geo.generated.' + hierarchy.getTypeName();
        
      if(index != 0)
      {
        var checkbox = factory.newElement('input', {'type':'checkbox', 'value':type, 'id':type + '_selectUniversalType', 'class':'selectUniversalType'})

        dt.appendChild(checkbox);
        dt.appendInnerHTML('&nbsp;');
        
        this._checkboxMap[type] = checkbox.getId();
      }
      
      dt.appendInnerHTML(hierarchy.getDisplayLabel());
      
      return dt;
    },

    _onClickCheckbox : function(event, checkbox)
    {
      if ( (checkbox.checked && this._applied.contains(checkbox.id)) || (!checkbox.checked && !this._applied.contains(checkbox.id)) )
      {
        this._unsaved.remove(checkbox.id);
      }
      else if ( (!checkbox.checked && this._applied.contains(checkbox.id)) || (checkbox.checked && !this._applied.contains(checkbox.id)) )
      {
        this._unsaved.add(checkbox.id);
      }
      
      this._bOK.setEnabled(this._unsaved.size() > 0);
    },
    
    _renderCurrentSelection : function(factory)
    {
      return factory.newElement('ul', {'id':this._CURRENT_SELECTIONS + this._suffix});
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
            var elementId = this._checkboxMap[selected[i]];
            var check = document.getElementById(elementId);
            
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
      if (e.target.selectedIndex == 0) { return; }
      
      if(this._isShiftDown)
      {
        var currentOption = e.target.options[e.target.selectedIndex];
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
          var selections = document.getElementById(this._CURRENT_SELECTIONS + this._suffix);
          selections.innerHTML = '';
        }
    
        for(var i=0; i<criteria.length; i++)
        {
          this._updateSelection(criteria[i], this._rendered, true);
        }
      }
    },
    
    retain : function(ids)
    {
    },
  
    /**
     * Adds the given GeoEntity to the list of current selections.
     */
    _updateSelection : function(geoEntityView, updateList, reopen)
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
        this._updateSelection2(geoEntityView, updateList, reopen);
      }
    },
    
    okHandler : function()
    {
      this._applied = new com.runwaysdk.structure.HashSet();
      var entities = Mojo.Util.getValues(this._criteriaMap);
      var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
      var selected = [];
      for(var i = 0; i < entities.length; i++)
      {
         this._applied.add(entities[i].getGeoEntityId());
      }
      for(var i = 0; i < checkboxes.length; i++)
      {
        var check = checkboxes[i];
        if(check.checked)
        {
          var type = check.value;
          selected.push(type);
          this._applied.add(check.id);
        }
      }
      
      this._queryBase._hideHandler(entities, selected);
      
      this._bOK.setEnabled(false);
      this._unsaved.removeAll();
    },
    
    cancelHandler : function()
    {
      this._bOK.setEnabled(false);
      this._unsaved.removeAll();
    },
    
    _updateSelection2 : function(geoEntityView, updateList, reopen)
    {
      var id = geoEntityView.getGeoEntityId();
      this._criteriaMap[id] = geoEntityView;
  
      if(updateList)
      {
        this._updateSelectionList(geoEntityView, reopen);
      }
    },
    
    /**
     * 
     */
    _updateSelectionList : function(geoEntityView, reopen)
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
  
      var selections = document.getElementById(this._CURRENT_SELECTIONS + this._suffix);
      selections.appendChild(li);
      
      if (!reopen)
      {
        // All this code just to figure out if the ok button is enabled/disabled
        if (this._applied.contains(geoEntityView.getGeoEntityId()))
        {
          this._unsaved.remove(geoEntityView.getGeoEntityId());
        }
        else
        {
          this._unsaved.add(geoEntityView.getGeoEntityId());
        }
        this._bOK.setEnabled(this._unsaved.size() > 0);
      }
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
      
      // All this code just to figure out if the ok button is enabled/disabled
      if (this._applied.contains(id))
      {
        this._unsaved.add(id);
      }
      else
      {
        this._unsaved.remove(id);
      }
      this._bOK.setEnabled(this._unsaved.size() > 0);
    },
    
    _notifySelectHandler : function()
    {
      // do nothing
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
  
        var selections = document.getElementById(this._CURRENT_SELECTIONS + this._suffix);
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
      this._applied = new com.runwaysdk.structure.HashSet();
      var views = Mojo.Util.getValues(this._criteriaMap);
      for(var i=0; i<views.length; i++)
      {
        this._updateSelectionList(views[i]);
        this._applied.add(views[i].getGeoEntityId());
      }
      
      for(var i=0; i<this._initSelectedUniversals.length; i++)
      {
        var check = document.getElementById(this._initSelectedUniversals[i]+"_selectUniversalType");
        if(check)
        {
          check.checked = true;
          this._applied.add(check.id);
        }
      }
      
      var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
      for(var i = 0; i < checkboxes.length; i++)
      {
        var check = checkboxes[i];
        YAHOO.util.Event.on(check, 'click', this._onClickCheckbox, check, this);
      }
    },
  
    /**
     * Returns 1 as the start index with one option for the blank field.
     */
    _getStartIndex : function()
    {
      return 1;
    },
  
    _disableAllowed : function()
    {
      return true;
    }
  
  }
});
