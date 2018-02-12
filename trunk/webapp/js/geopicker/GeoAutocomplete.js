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
 * Class that that can be attached to any input field
 * to provide searching capability for geo entities.
 */
Mojo.Meta.newClass("MDSS.GeoSearch", {

  Instance : {
    
    initialize : function(geoInput, geoFilterCriteria, geoPicker)
    {
      this._geoFilterCriteria = geoFilterCriteria;
      this._listeners = [];
      this._geoPicker = geoPicker || null;
      this._isFormInput = true;
      
      this._geoInput = Mojo.Util.isString(geoInput) ? document.getElementById(geoInput) : geoInput;
      
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      this._geoElement = document.getElementById(this._geoInput.id+'_geoEntityId');
      
      if (this._geoPicker != null)
      {
        // Append the globe img to open the geo picker
        this._opener = document.createElement('img');
        this._opener.src = "./imgs/icons/world.png";
        this._opener.id = this._geoInput.id +'Go';
        //opener = new YAHOO.util.Element(opener);
        YAHOO.util.Dom.addClass(this._opener,'geoOpener');
        YAHOO.util.Dom.insertAfter(this._opener,this._geoInput);
      }
  
      var geoInfo = document.createElement('div');
      geoInfo.id = this._geoInput.id +'Info';
      geoInfo.innerHTML = '()';
      YAHOO.util.Dom.insertAfter(geoInfo, this._opener);
  
      var geoSearchResults = document.createElement('div');
      geoSearchResults.id = this._geoInput.id +'_results';
      geoSearchResults.className = "yui-panel-container show-scrollbars shadow";
      YAHOO.util.Dom.insertAfter(geoSearchResults,geoInfo);

      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._listFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sEH = Mojo.Util.bind(this, this._selectEventHandler);
      
      //YAHOO.util.Event.on(geoInput, 'blur', this._checkManualEntry, null, this);
      
      // add generic ajax search
      this._genericSearch = new MDSS.GenericSearch(this._geoInput, this._geoElement, lF, dF, iF, sF, sEH);
      
      // IMPORTANT: We must reset the search cache because new geo entities made be created/deleted in 101.
      //            As such the existing search results may no longer be valid.
      YAHOO.util.Event.on(this._opener, "click", this._clickHandler, this, this); 

      YAHOO.util.Event.on(this._geoInput, 'focus', this._setCurrentInput, null, this);

      // Set up validation on the geo input
      YAHOO.util.Event.on(this._geoInput, 'blur', this._blurHandler, this, this);
      
      // 
      var GeoEntity = Mojo.$.dss.vector.solutions.geo.generated.GeoEntity
      this._idAttr = GeoEntity.ID;
      this._entityLabelAttr = GeoEntity.ENTITYLABEL;
      this._geoIdAttr = GeoEntity.GEOID;
      this._typeAttr = GeoEntity.TYPE;
      
      // Fetch the display info if a geo entity value exists
      if(this._geoInput.value.length > 0)
      {
        var request = new MDSS.Request({
          that: this,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
            this.that.showGeoInfo(result,this.that._geoInput,true);
          }
        });       

        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, this._geoInput.value);
      }
      
      if(this._geoPicker != null)
      {
        this._geoPicker.addListener(this);
      }
    },
    
    hide : function()
    {
      this._genericSearch.hide();
    },
    
    _clickHandler : function(e)
    {
      if(this._geoInput != null && this._geoInput.value !== '' && this._geoPicker != null)
      {
        var geoId = this._geoInput.value;
        this._geoPicker.setGeoId(geoId);
      }
      
      this._openPicker(e, this._geoInput);
      this._genericSearch.resetCache();
      this._setTreeValidator();
    },
    
    _setTreeValidator : function()
    {
      if (this._geoPicker != null)
      {
        this._geoPicker._tree.setValidator(Mojo.Util.bind(this, this._validator));
      }
    },
    
    destroy : function()
    {
      if (this._opener != null)
      {
        YAHOO.util.Event.removeListener(this._opener, 'click', this._openPicker);
      }
      YAHOO.util.Event.removeListener(this._geoInput, 'focus', this._setCurrentInput);
      
      this._opener = null;
      this._geoInput = null;
    },
    
    setIsFormInput : function(bool)
    {
      this._isFormInput = bool;
    },
    
    _setCurrentInput : function()
    {
      if (this._isFormInput)
      {
        this.constructor.currentGeoIdInput = this._geoInput;
      }
    },
    
    _openPicker : function(e, newGeoInput)
    {
      // reset context
      this.constructor.currentGeoIdInput = newGeoInput;

      this._geoPicker.show();
    },
    
    _displayFunction : function(valueObj)
    {
      return valueObj.getValue(this._geoIdAttr);
    },
    
    _listFunction : function(valueObj)
    {
      var entityLabel = valueObj.getValue(this._entityLabelAttr);
      var typeLabel = valueObj.getValue('displayLabel');
      var geoId = valueObj.getValue(this._geoIdAttr);
      var subType = valueObj.getValue('moSubType');
      
      return MDSS.AbstractSelectSearch.formatDisplay2(entityLabel, typeLabel, geoId, subType);
    },
    
    _idFunction : function(valueObj)
    {
      return valueObj.getValue(this._idAttr);
    },
    
    setSelectEventHandler : function(handler)
    {
      this._selectHandler = handler;
    },
    
    /**
     * Select handler for when they select a value in the autocomplete dropdown in the input field on the CRUD form.
     */
    _selectEventHandler : function(selected)
    {
      var view = new dss.vector.solutions.geo.GeoEntityView();
      view.setGeoId(selected.label);
      view.setGeoEntityId(selected.id);
      
      var regex = /(.*) \((.*)\) - (.*)/g;
      var matches = regex.exec(selected.innerText);
      
      view.setTypeDisplayLabel(matches[2]);
      view.setEntityLabel(matches[1]);
      
      if (this._geoPicker != null && this._geoPicker.getSelectHandler() != null)
      {
        view.setPolitical(this._geoPicker.getPolitical());
        view.setSprayTargetAllowed(this._geoPicker.getSprayTargetAllowed());
        view.setPopulationAllowed(this._geoPicker.getPopulated());
        view.setUrban(this._geoPicker.getUrban());
        
        var handler = this._geoPicker.getSelectHandler();
        handler(view, true);
      }
      if (this._selectHandler != null)
      {
        this._selectHandler(view, true);
      }
      
      this._validateSelection(selected);
    },
    
    _searchFunction : function(request, value)
    {
      var type = this._geoFilterCriteria.filter;
      var enforceRoot = this._geoFilterCriteria.enforceRoot;
      
      if(type != null && type.length > 0)
      {
      	if(this._geoFilterCriteria.root != null) {
      	  var parameters = [type[0], this._geoFilterCriteria.root];
              
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByParameters(request, value, parameters, enforceRoot);
      	}
        else {          
          Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByEntityNameOrGeoId(request, type[0], value, enforceRoot);        
        }
      }
      else 
      {
        var political = this._geoFilterCriteria.political;
        var populated = this._geoFilterCriteria.populated;
        var sprayTarget = this._geoFilterCriteria.sprayTargetAllowed;        
        var urban = this._geoFilterCriteria.urban;

        var parameters = [political, populated, sprayTarget, urban].concat(this._geoFilterCriteria.extraUniversals);
        
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.searchByParameters(request, value, parameters, enforceRoot);
      }
    },
    
    _getValidationRequest : function(selected)
    {
      var request = new MDSS.Request({
        that : this,
        selected : selected,
        onSend : function(){}, 
        onComplete : function(){},
        onFailure : function(e){
          if (this.that._opener != null)
          {
            MDSS.Calendar.removeError(this.that._opener);
          
            MDSS.Calendar.addError(this.that._opener, e.getMessage());
          }
          
          this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_INVALID_SELECTION, {selected:selected}));
        },
        onProblemExceptionDTO : function(e){
          if (this.that._opener != null)
          {
            MDSS.Calendar.removeError(this.that._opener);
            
            var problems = e.getProblems();
            for(var i = 0; i < problems.length; i++) {
              var problem = problems[i];
              MDSS.Calendar.addError(this.that._opener, problem.getMessage());
            }
          }
          
          this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_INVALID_SELECTION, {selected:selected}));
        },
        onSuccess : function() {
          if (this.that._opener != null)
          {
            MDSS.Calendar.removeError(this.that._opener);
          }
          
          this.that.fireEvent(new MDSS.Event(MDSS.Event.AFTER_VALID_SELECTION, {selected:selected}));
        }            
      });
        
      return request;
    },
    
    _blurHandler : function (e)
    {
      if(e)
      {
        var blurEl = e.explicitOriginalTarget || document.activeElement;
        
        var ul = YAHOO.util.Dom.getAncestorByClassName(blurEl, "selectableList")
        
        if(ul)
        {
          return; 
        }
        
        this._validateSelection();
      }    
    },
    
    _validateSelection : function(selected)
    {
      var geoId = this._geoInput.value;
      
      if(Mojo.Util.isString(geoId) && geoId != '')
      {
        var request = this._getValidationRequest(selected);
        
        this._validator(request, geoId);
      }
    },
    
    _validator : function(request, geoId)
    {
      var type = this._geoFilterCriteria.filter;
        
      if(Mojo.Util.isString(type) && type != '')
      {
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByType(request, geoId, type);
      }
      else
      {
        var political = this._geoFilterCriteria.political;
        var populated = this._geoFilterCriteria.populated;
        var sprayTarget = this._geoFilterCriteria.sprayTargetAllowed;        
        var urban = this._geoFilterCriteria.urban;
        
        var parameters = [political, populated, sprayTarget, urban].concat(this._geoFilterCriteria.extraUniversals);
        
        Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.validateByParameters(request, geoId, parameters);
      }    
    },
    
    showGeoInfo : function(selected,currentGeoIdInput,valid)
    {
      // Some pages also have a field that takes the geoentity id.
      // Those fields are namespaced as the geo id field+"_geoEntityId",
      // so a geo input with an id of "geoIdEl" may have another field
      // called "geoIdEl_geoEntityId".
      var currentgeoEntityIdInput = document.getElementById(currentGeoIdInput.id+'_geoEntityId');

      var geoInfo = document.getElementById(currentGeoIdInput.id+'Info');

      if(selected === null)
      {
        geoInput.value = '';
        geoInfo.innerHTML = '';
        if(currentgeoEntityIdInput) currentgeoEntityIdInput.value ='';
      }
      else
      {
        currentGeoIdInput.value = selected.getGeoId();
        if(currentgeoEntityIdInput) {
          currentgeoEntityIdInput.value = selected.getGeoEntityId();
        }
        geoInfo.innerHTML = MDSS.AbstractSelectSearch.formatDisplay(selected);
      }

      if(valid)
      {
        YAHOO.util.Dom.removeClass(geoInfo,'alert');
      }
      else
      {
        YAHOO.util.Dom.addClass(geoInfo,'alert');
      }
    },
    
    handleEvent : function(event)
    {
      if(event.getType() == MDSS.Event.AFTER_SELECTION)
      {
        this._validateSelection(event.getValue().selected);
      }
    },
    
    setFilter : function(filter)
    {
      if(this._geoPicker != null) {
        this._geoPicker.setFilter(filter);
      }
      
      this._genericSearch.resetCache();
    },
    
    addListener : function(listener) 
    {
      this._listeners.push(listener);
    },
    
    fireEvent : function(event)
    {
      for(var i = 0; i < this._listeners.length; i++)
      {
        this._listeners[i](event);
      }    
    },
    
    resetCache : function() {
      this._genericSearch.resetCache();
    }

    
    /*
    _checkManualEntry : function(e)
    {
      // reset context
      this.constructor.currentGeoIdInput = e.target;

      var request = new MDSS.Request({
          that: this,
          onSend: function(){},
          onComplete: function(){},
          onFailure: function(){},
          onSuccess : function(result){
            
          // execute the select handler
          this.that._selectSearch.getSelectHandler()(result);
        }
      });

      var id = this.constructor.currentGeoIdInput.value;

      Mojo.$.dss.vector.solutions.geo.generated.GeoEntity.getViewByGeoId(request, id);
    }
    */
  },
  
  Static : {
    currentGeoIdInput : null
  }
});
