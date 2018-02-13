/*
 * Copyright (C) 2008 IVCC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * Class that implements single select searching for GeoEntities
 */
Mojo.Meta.newClass('MDSS.SingleSelectSearch', {

  Extends : MDSS.AbstractSelectSearch,

  Instance : {

    /**
     * Constructor.
     */
    initialize : function(enforceRoot, selectSearchRootId)
    {
      this.$initialize(enforceRoot, selectSearchRootId);

      this._currentSelection = null;
      this._CURRENT_SELECTION = 'currentSelection';
      this._listeners = [];
      this._geoId = null;
      
      // Set this.selectHandler as the default handler
      this.setSelectHandler(Mojo.Util.bind(this, this.selectHandler));
      this.setOkHandler(Mojo.Util.bind(this, this.okHandler));
      this.setTreeSelectHandler(Mojo.Util.bind(this, this.selectHandler));
    },
    
    _renderHierarchyHeader : function(factory, hierarchy, index, rootId)
    {
      var dt = factory.newElement('dt', {'id':rootId + '_dt'});
      dt.setInnerHTML(hierarchy.getDisplayLabel());
      
      return dt;
    },
        
    _renderCurrentSelection : function(factory)
    {
      return factory.newElement('span', {'id':'currentSelection' + this._suffix});
    },
    
    setGeoId : function(geoId)
    {
      this._geoId = geoId;
    },
    
    _postRender : function()
    {
      // do nothing
    },
    
    /**
     * This function is called in 2 very different contexts.
     * 1) the user selects a value from the dropdown on the CRUD form, the geopicker is never even opened or used.
     * 2) The user selects a value from one of the downdowns in the geopicker.
     * For situation #1 the user has actually selected a value, but for #2 they're just playing around in the geopicker.
     * A value is not set until they hit the okay button.
     * 
     * @param selected Either the select input element or a GeoEntityView, depending on how this method was called.
     * @param updateGoeSelect This is only set to true in context #1
     */
    selectHandler : function(selected, updateGeoSelect)
    {
      var valid = true;
      if(selected != null)
      {
        if(typeof selected == 'string'){
          var request = new MDSS.Request({
              that: this,
              onSend: function(){},
              onComplete: function(){},
              onFailure: function(){},
              onSuccess : function(result){
                this.that.selectHandler(result, updateGeoSelect); // recalls this method
            }
          });
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getView(request, selected);
          return;
        }

        var currentFilter = this.getFilter();
        if(currentFilter)
        {
          var expectedKlass = Mojo.Meta.findClass(currentFilter);
          var givenKlass = Mojo.Meta.findClass(selected.getEntityType());

          valid = givenKlass.getMetaClass().isSubClassOf(expectedKlass);
        }
      }
      
      // Notify the user that their selection is invalid.
//      if(valid)
//      {
//        YAHOO.util.Dom.removeClass(geoInfo,'alert');
//      }
//      else
//      {
//        YAHOO.util.Dom.addClass(geoInfo,'alert');
//      }
      if (this._bOK != null)
      {
        this._bOK.setEnabled(valid);
      }
      
      if (valid)
      {
        this._currentSelection = selected;
      }
      
      if (updateGeoSelect)
      {
        this.okHandler();
      }
    },
    
    okHandler : function()
    {
      var selected = this._currentSelection;
      
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      var currentgeoEntityIdInput = document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'_geoEntityId');

      var geoInfo = document.getElementById(MDSS.GeoSearch.currentGeoIdInput.id+'Info');

      if(selected === null)
      {
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(currentgeoEntityIdInput) { currentgeoEntityIdInput.value =''; }
      }
      else
      {
        MDSS.GeoSearch.currentGeoIdInput.value = selected.getGeoId();
      
        if(currentgeoEntityIdInput) {
          currentgeoEntityIdInput.value = selected.getGeoEntityId();
        }
      
        geoInfo.innerHTML = this.constructor.formatDisplay(selected);
      }
      
//    FIXME: global (and absurdly hacky) method callback
      if(typeof onValidGeoEntitySelected !== 'undefined' && Mojo.Util.isFunction(onValidGeoEntitySelected))
      {
        onValidGeoEntitySelected();
      }
      
      this._fireEvent(new MDSS.Event(MDSS.Event.AFTER_SELECTION,{selected:selected, info:geoInfo.innerHTML}));
    },
    
    validateSelection : function()
    {
      
    },
    
    addListener : function(listener)
    {
      this._listeners.push(listener);
    },
    
    _fireEvent : function(event)
    {
      for(var i = 0; i < this._listeners.length; i++)
      {
        this._listeners[i].handleEvent(event);
      }
    },

    /**
     * Replaces the current selection with the given GeoEntityView.
     */
    _updateSelection : function(geoEntityView)
    {
      var div = document.getElementById(this._CURRENT_SELECTION + this._suffix);
      
      if(div != null)
      {
        div.innerHTML = this.constructor.formatDisplay(geoEntityView);    	  
      }

//      this._currentSelection = geoEntityView;
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

      if(Mojo.Util.isFunction(this._selectHandler))
      {
        this._selectHandler(geoEntityView);
      }
    },

    /**
     * Notifies the select handler that a GeoEntity
     * has been selected via the tree. The GeoEntityView is passed
     * to the handler.
     */
    _notifyTreeSelectHandler : function(geoEntityView)
    {
      this._updateSelection(geoEntityView);

      if(Mojo.Util.isFunction(this._treeSelectHandler))
      {
        this._treeSelectHandler(geoEntityView);
      }
      
      this._resetWithSelection(geoEntityView.getEntityType(), geoEntityView.getGeoEntityId());
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
    
    _doCreateRoot : function(request)
    {
      if(this._geoId != null && this._geoId !== '')
      {
        // Use a custom geo entity
        this.populateSelections(this._geoId);
      }
      else
      {
        // Fetch the system default root.
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.collectAllLocatedIn(request, this._selectSearchRootId, false, "");
      }
    },

    _disableAllowed : function()
    {
      return true;
    }
  }
});

