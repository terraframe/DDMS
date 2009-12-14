          // set the layers
            /* FIXME MAP
          var request2 = new MDSS.Request({
            thisRef : this.thisRef,
            onSend : function(){},
            onSuccess : function(layerViews){
  
              for(var i=0; i<layerViews.length; i++)
              {
                var layerView = layerViews[i];
  
                if(layerView.getIsThematic())
                {
                  var type = layerView.getThematicType();
                  var valid = type != null && type !== '';
                  if(valid)
                  {
                    // set the selected thematic layer (the list will be populatd by now)
                    this.thisRef._queryPanel.setSelectedThematicLayer(type);
                  }
  
                  this.thisRef._queryPanel.toggleThematicSettings(valid);
                }
                else
                {
                  var layerId = layerView.getLayerId();
                  var type = layerView.getUniversalType();
  
                  this.thisRef._queryPanel.addDefinedLayer(layerId, type);
                }
              }
            }
          });
  
          Mojo.$.dss.vector.solutions.query.SavedSearch.getAllLayers(request2, this.savedSearchId);
              */

Mojo.Meta.newClass('MDSS.MapPanel', {
  
  Constants : {
    MAP_PANEL_ID : "mapPanel",
    MAP_CONTAINER : "mapContainer",
    ANNOTATIONS : "annotations",
    REFRESH_MAP_BUTTON : "refreshMap",
    THEMATIC_VARIABLES_LIST : "thematicVariablesList",
    THEMATIC_LAYERS_SELECT : "thematicLayersSelect",
    DEFINED_LAYERS_LIST : "definedLayersList",
    AVAILABLE_LAYERS_LIST : "availableLayersList",
    CATEGORY_LIST : "categoryList",
    LEFT_COLUMN : "leftColumn",
    QUERY_LIST : "queryList",
    ADD_LAYER_BUTTON : "addLayerBtn",
    AVAILABLE_LAYERS : "availableLayers",
    DELETE_SUFFIX : "_delete",
    EDIT_SUFFIX : "_edit",
    MAP_LIST : "mapList",
    LAYER_NAME_SUFFIX : "_layerName"
  },
  
  Instance : {
    
    initialize : function(mapPanelId, mapList)
    {
      this._mapList = mapList;
    
      var minWidth = 1250;
      var minHeight = 500;

      var pWidth =  (window.innerWidth - 30) > minWidth ? (window.innerWidth - 30) : minWidth;
      var pHeight = (window.innerHeight - 160) > minHeight ? (window.innerHeight -160) : minHeight;
    
      this._mapLayout = new YAHOO.widget.Layout(mapPanelId, {
        height: pHeight,
        width: pWidth,
        units: [
            { position: 'left', width: 300, resize: false, body: '', gutter: '2 5 0 2', scroll: true },
            { position: 'bottom', height: 40, body: '', gutter: '2' },
            { position: 'center', body: '<div id="'+MDSS.MapPanel.MAP_CONTAINER+'"></div>', gutter: '2 2 0 0', scroll: true }
        ]
      });
      
      // The button that adds a new layer when clicked
      this._addLayerButton = null;
    
      // a map of ThematicVariable objects
      this._thematicVariables = {};
      this._thematicLayers = [];
    
  
      // The current ThematicVarible object that is used for mapping.
      this._currentThematicVariable = null;
  
      // the current layers in the map. If this._currentSavedSearch
      // is not null, then these layers belong to that SavedSearch.
      this._layers = [];      
      
      this._annotation = new MDSS.Annotation(this);
      
      this._currentModal = null;
      this._secondaryModal = null;
      
      this._SavedMapController = Mojo.$.dss.vector.solutions.query.SavedMapController;
      this._SavedMapController.setCreateListener(Mojo.Util.bind(this, this._mapCreateListener));
      this._SavedMapController.setCancelListener(Mojo.Util.bind(this, this._mapCancelListener));

      this._LayerController = Mojo.$.dss.vector.solutions.query.LayerController;
      this._LayerController.setSaveLayerListener(Mojo.Util.bind(this, this._saveLayerListener));
      this._LayerController.setCancelListener(Mojo.Util.bind(this, this._layerCancelListener));
      
      this._AbstractCategoryController = Mojo.$.dss.vector.solutions.query.AbstractCategoryController;
      this._AbstractCategoryController.setSaveCategoryListener(Mojo.Util.bind(this, this._saveCategoryListener));
      
      this._RangeController = Mojo.$.dss.vector.solutions.query.RangeCategoryController;
      this._RangeController.setNewInstanceListener(Mojo.Util.bind(this, this._categoryNewInstanceListener));
      this._RangeController.setCancelListener(Mojo.Util.bind(this, this._categoryCancelListener));
      
      this._NonRangeController = Mojo.$.dss.vector.solutions.query.NonRangeCategoryController;
      this._NonRangeController.setNewInstanceListener(Mojo.Util.bind(this, this._categoryNewInstanceListener));
      this._NonRangeController.setCancelListener(Mojo.Util.bind(this, this._categoryCancelListener));
    },
    
    render : function()
    {
      this._mapLayout.render();
      
      this._createLeftColumn();
      this._createBottomRow();
      this._loadDefaultMap();
    },
    
    _loadDefaultMap : function()
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(defaultMap)
        {
          this.that._clearLayers();
        
          MDSS.MapPanel.setCurrentMap(defaultMap.getId());
          
          // reset the select list index of maps to the first element
          document.getElementById(MDSS.MapPanel.MAP_LIST).selectedIndex = 0;
        }
      });
      
      Mojo.$.dss.vector.solutions.query.SavedMap.loadDefaultMap(request, null);
    },
    
    _createLeftColumn : function()
    {
      var html = '';
      html += '<div id="'+MDSS.MapPanel.LEFT_COLUMN+'">';
      
      /* 
      // Query List
      html += MDSS.Localized.Available_Queries+':<br />';
      
      html += '<select id="'+MDSS.MapPanel.QUERY_LIST+'" class="queryList">'; 
      html += Mojo.Iter.map(this._queryList, function(query){
        return '<option value="'+query.id+'">'+query.name+'</option>'; 
      }).join('');
      */ 
      
      // Add query button
      html += MDSS.localize('Available_Layers')+':&nbsp;';
      html += '<input type="button" id="'+MDSS.MapPanel.ADD_LAYER_BUTTON+'" value="'+MDSS.localize('Add_Layer')+'">';
      html += '<br />';
      
      // layers
      html += '<div style="margin-top: 5px">';
      html += '<ul id="'+MDSS.MapPanel.AVAILABLE_LAYERS+'">';
      html += '</ul>';
      
      html += '</div>';
      html += '</div>';
    
      var left = this._mapLayout.getUnitByPosition('left');
      left.body.innerHTML = html;
      
      // Event to add a query to the selected queries
      YAHOO.util.Event.on(MDSS.MapPanel.ADD_LAYER_BUTTON, 'click', this._addLayer, null, this);
      
      // Make the selected queries a drag target and use it as a
      // click event delegater since the drag and drop operations
      // force HTML replacement so registered events can be lost.
      new YAHOO.util.DDTarget(MDSS.MapPanel.AVAILABLE_LAYERS);
      YAHOO.util.Event.on(MDSS.MapPanel.AVAILABLE_LAYERS, 'click', this._handleSelectedQueryClick, null, this);
    },
    
    _handleSelectedQueryClick : function(e)
    {
      var target = e.target;
      var id = target.id;
      if(!id || target.nodeName !== 'IMG')
      {
        return;
      }
      
      if(id.indexOf(MDSS.MapPanel.DELETE_SUFFIX) != -1)
      {
        var layerId = id.replace(MDSS.MapPanel.DELETE_SUFFIX, '');
        
        var request = new MDSS.Request({
          onSuccess : function()
          {
            var li = document.getElementById(layerId);
            li.parentNode.removeChild(li);
        
            MDSS.MapPanel.toggleBaseLayer();
          }
        });
        
        var mapId = this.constructor.getCurrentMap();
        Mojo.$.dss.vector.solutions.query.SavedMap.deleteLayerFromMap(request, mapId, layerId);
      }
      else if(id.indexOf(MDSS.MapPanel.EDIT_SUFFIX) != -1)
      {
        var layerId = id.replace(MDSS.MapPanel.EDIT_SUFFIX, '');
        
        var request = new MDSS.Request({
          that: this,
          onSuccess : function(html)
          {
            var that = this.that;          
            that._createModal(html, '', true);
            
            // Use the UL#categoryList as a delegate for
            // all edit/delete events on individual categories
            YAHOO.util.Event.on('categoryList', 'click', that._handleCategoryClick, null, that); 
          }
        });
        
        this._LayerController.edit(request, layerId);
      }
    },
    
    /**
     * Handles any edit/delete request on an AbstractCategory instance.
     */
    _handleCategoryClick : function(e)
    {
      var el = e.target;
      
      if(!el.id)
      {
        return;
      }
      
      var id = el.id;
      if(id.indexOf(MDSS.MapPanel.DELETE_SUFFIX) != -1)
      {
        var categoryId = id.replace(MDSS.MapPanel.DELETE_SUFFIX, '');
        
        var request = new MDSS.Request({
          onSuccess : function()
          {
            // remove the category from the list
            var li = document.getElementById(categoryId+"_div").parentNode;
            li.parentNode.removeChild(li);
          }
        });
        
        Mojo.Facade.deleteEntity(request, categoryId);
      }
      else if(id.indexOf(MDSS.MapPanel.EDIT_SUFFIX) != -1)
      {
        var categoryId = id.replace(MDSS.MapPanel.EDIT_SUFFIX, '');
        var type = document.getElementById(categoryId+"_type").value;
        
        var request = new MDSS.Request({
          that: this,
          onSuccess : function(html)
          {
            this.that._createModalSec(html, '');
          }
        });
        
        if(type === Mojo.$.dss.vector.solutions.query.RangeCategory.CLASS)
        {
          this._RangeController.edit(request, categoryId);
        }
        else
        {
          this._NonRangeController.edit(request, categoryId);
        }
      }
    },
    
    _saveCategoryListener : function(params)
    {
      // Add the layer id so the relationship between Layer and
      // Category can be made in the business layer.
      params['layerId'] = document.getElementById('layerId').value;
    
      var request = new MDSS.Request({
        that : this,
        isNew : (params['category.isNew'] === 'true'),
        categoryId : params['category.componentId'],
        onSuccess : function(html)
        {
          if(this.isNew)
          {
            var li = document.createElement('li');
            li.innerHTML = html;
          
            document.getElementById(MDSS.MapPanel.CATEGORY_LIST).appendChild(li);
          }
          else
          {
            var div = document.getElementById(this.categoryId+'_div');
            div.parentNode.innerHTML = html;
          }
          
          this.that._destroyModalSec();
        }
      });
      
      return request;
    },
    
    _categoryCancelListener : function(params)
    {
      if(params['category.isNew'] === 'true')
      {
        this._destroyModalSec();
      }
      else
      {
        var request = new MDSS.Request({
          that: this,
          onSuccess : function()
          {
            this.that._destroyModalSec();
          }
        });
        
        Mojo.$.dss.vector.solutions.query.AbstractCategory.unlock(request, params['category.componentId']);
      }
    },
    
    _categoryNewInstanceListener : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(html){
          
          var that = this.that;
          
          that._createModalSec(html, ''); 
        }
      });
      
      return request;
    },
    
    _layerCancelListener : function(params)
    {
      if(params['layer.isNew'] === 'true')
      {
        this._destroyModal();
      }
      else
      {
        var request = new MDSS.Request({
          that: this,
          onSuccess : function()
          {
            this.that._destroyModal();
          }
        });
        
        Mojo.$.dss.vector.solutions.query.Layer.unlock(request, params['layer.componentId']);
      }
    },
    
    _saveLayerListener : function(params)
    {
      // IMPORTANT: The value of Layer.savedSearch is submitted as an array
      // because it is a select list on the form. But we want to submit it as 
      // a single value because savedSearch is just a reference attribute.
      if(params['layer.savedSearch'].length > 0)
      {
        params['layer.savedSearch'] = params['layer.savedSearch'][0];
      }
      
      params['savedMapId'] = MDSS.MapPanel.getCurrentMap();
    
      var request = new MDSS.Request({
        isNew : (params['layer.isNew'] === 'true'),
        that : this,
        layerName : params['layer.layerName'],
        onSuccess : function(layerId)
        {
          var that = this.that;
          that._destroyModal();
          
          // add a draggable list item for the new layer
          if(this.isNew)
          {
            var li = that._createLayerLI(layerId, this.layerName);
            
            var ul = document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS);
            ul.appendChild(li);
            
            
            MDSS.MapPanel.toggleBaseLayer();
          }
          else
          {
            document.getElementById(layerId+MDSS.MapPanel.LAYER_NAME_SUFFIX).innerHTML = this.layerName;
          }
        }
      });
      
      return request;
    },
    
    _addLayer : function()
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(html)
        {
          this.that._createModal(html, '', true);
        }
      });
      
      this._LayerController.newInstance(request);
    },
    
    _createBottomRow : function()
    {
      // add map loading/saved/editing
      var mapList = document.createElement('select');
      mapList.id = MDSS.MapPanel.MAP_LIST;
      
      // add one option for the default (and this will reset the query)
      var options = '<option value=""></option>';
      for(var i=0; i<this._mapList.length; i++)
      {
        var map = this._mapList[i];
        options += '<option value="'+map.id+'">'+map.name+'</option>'; 
      }
      mapList.innerHTML = options;
      
      YAHOO.util.Event.on(mapList, 'change', this._loadMap, null, this);
      
      var saveButton = new YAHOO.util.Element(document.createElement('input'));
      saveButton.set('type', 'button');
      saveButton.set('value', MDSS.localize('Save_Map'));
      saveButton.set('id', "saveQueryButton");
      saveButton.addClass('queryButton');
      saveButton.on('click', this._saveMap, {}, this);

      var saveAsButton = new YAHOO.util.Element(document.createElement('input'));
      saveAsButton.set('type', 'button');
      saveAsButton.set('value', MDSS.localize('Save_Map_As'));
      saveAsButton.set('id', "saveAsQueryButton");
      saveAsButton.addClass('queryButton');
      saveAsButton.on('click', this._saveMapAs, {}, this);

      var deleteButton = new YAHOO.util.Element(document.createElement('input'));
      deleteButton.set('type', 'button');
      deleteButton.set('value', MDSS.localize("Delete_Map"));
      deleteButton.set('id', this.LOAD_QUERY_BUTTON);
      deleteButton.addClass('queryButton');
      deleteButton.on('click', this._deleteMap, {}, this);
      
      var loadingDiv = new YAHOO.util.Element(document.createElement('div'));
      loadingDiv.setStyle('float', 'left');
      loadingDiv.appendChild(mapList);
      loadingDiv.appendChild(saveButton);
      loadingDiv.appendChild(saveAsButton);
      loadingDiv.appendChild(deleteButton);
    
      var mapButtonDiv = new YAHOO.util.Element(document.createElement('div'));
      mapButtonDiv.setStyle('float', 'right');
      
      // Add the map buttons
      var annotation = document.createElement('input');
      YAHOO.util.Dom.setAttribute(annotation, 'type', 'button');
      YAHOO.util.Dom.setAttribute(annotation, 'id', MDSS.MapPanel.ANNOTATIONS);
      YAHOO.util.Dom.setAttribute(annotation, 'value', MDSS.Localized.Annotations);
      YAHOO.util.Dom.addClass(annotation, 'queryButton');
      YAHOO.util.Event.on(annotation, 'click', this._annotation.showModal, null, this._annotation);
      annotation.disabled = true;
      mapButtonDiv.appendChild(annotation);
  
      var refreshMapButton = new YAHOO.util.Element(document.createElement('input'));
      refreshMapButton.set('type', 'button');
      refreshMapButton.set('value', MDSS.Localized.Query.Refresh);
      refreshMapButton.set('id', MDSS.MapPanel.REFRESH_MAP_BUTTON);
      refreshMapButton.addClass('queryButton');
      refreshMapButton.on('click', this._mapQuery, null, this);
      mapButtonDiv.appendChild(refreshMapButton);
      
      var bottom = this._mapLayout.getUnitByPosition('bottom');
      var mBottom = new YAHOO.util.Element(bottom.body);
      mBottom.appendChild(loadingDiv);
      mBottom.appendChild(mapButtonDiv);
    },
    
    _saveMap : function()
    {
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var mapId = mapList.options[mapList.selectedIndex].value;
      
      if(mapId && mapId.length > 0)
      {
      }
      else
      {
        // Save as a new map
        this._saveMapAs();
      }
    },
    
    _saveMapAs : function()
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess: function(html)
        {
          var that = this.that;
          that._createModal(html, MDSS.localize('Save_Map_As'));
        }
      });
  
      this._SavedMapController.newInstance(request);
    },
    
    _mapCreateListener : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(query, savedMap)
        {
          var mapId = savedMap.getId();
        
          // insert the new map into the select box
          var that = this.that;
          mapId = Mojo.Util.trim(mapId);
          
          that._addSavedMap(mapId, savedMap.getMapName());
          that._setLayers(query.getResultSet());          
          
          that._destroyModal();
        }
      });

      var existingMapId = MDSS.MapPanel.getCurrentMap();
      
      var map = new Mojo.$.dss.vector.solutions.query.SavedMap();
      map.setMapName(params['dto.mapName']);
      map.createFromExisting(request, existingMapId);
    },
    
    _mapCancelListener : function(params)
    {
      this._destroyModal();
    },
    
    _deleteMap : function()
    {
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      if(mapList.selectedIndex !== 0)
      {
        var option = mapList.options[mapList.selectedIndex];
        var mapId = option.value;
        
        var request = new MDSS.Request({
          that : this,
          onSuccess : function(){

            // remove the option node for the deleted map
            mapList.removeChild(option);
            mapList.selectedIndex = 0;
            
            this.that._loadDefaultMap();
          }
        });
        
        Mojo.Facade.deleteEntity(request, mapId);
      }
    },
    
    _addSavedMap : function(mapId, mapName)
    {
      var option = document.createElement('option');
      option.value = mapId;
      option.innerHTML = mapName;
      
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      mapList.appendChild(option);
      mapList.selectedIndex = mapList.options.length-1;
      
      // update the current map to the newly created one
      MDSS.MapPanel.setCurrentMap(mapId);
    },
    
    _clearLayers : function()
    {
      // clear the old layers
      document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS).innerHTML = '';
    },
    
    _loadMap : function(e)
    {
      var mapId = document.getElementById(MDSS.MapPanel.MAP_LIST).value;
      
      if(mapId === '')
      {
        this._loadDefaultMap(); // this will reset the current default map
      }
      else
      {
        var request = new MDSS.Request({
          mapId : mapId,
          that : this,
          onSuccess : function(query)
          {
            MDSS.MapPanel.setCurrentMap(this.mapId);
            var that = this.that;
          
            // repopulate the map list with the layers
            that._setLayers(query.getResultSet());
          }
        });

        Mojo.$.dss.vector.solutions.query.SavedMap.getAllLayers(request, mapId);        
      }
    },
    
    _setLayers : function(layerViews)
    {
      this._clearLayers();
    
      var frag = document.createDocumentFragment();
      Mojo.Iter.forEach(layerViews, function(view){
        
        var li = this._createLayerLI(view.getLayerId(), view.getLayerName());
        frag.appendChild(li);
      }, this);
      
      document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS).appendChild(frag);
      MDSS.MapPanel.toggleBaseLayer();
    },
    
    /**
     * Creates the HTML for one layer
     */
    _createLayerLI : function(layerId, layerName)
    {
      var li = document.createElement('li');
      li.id = Mojo.Util.trim(layerId);
            
      var html = '';
      html += '<img class="clickable" style="margin-right: 5px" src="imgs/icons/wand.png" id="'
        +li.id+MDSS.MapPanel.EDIT_SUFFIX+'" />';
      html += '<img class="clickable" style="margin-right: 5px" src="imgs/icons/delete.png" id="'
        +li.id+MDSS.MapPanel.DELETE_SUFFIX+'" />';
      html += '<span style="font-weight: bold"></span>';
      html += '<span id="'+li.id+MDSS.MapPanel.LAYER_NAME_SUFFIX+'">'+layerName+'</span>';
      new MDSS.SelectedQueryDD(li.id);
      
      li.innerHTML = html;
      
      return li;
    },
    
    /**
     * Creates the map
     */
    _mapQuery : function()
    {
      /* FIXME remove this obsolete code
      if(Mojo.Util.isFunction(this._config.mapQuery))
      {
        this._config.mapQuery.call(this._queryClass);
  
        document.getElementById(MDSS.MapPanel.ANNOTATIONS).disabled = false;
      }
      */ 
    },
    
    _destroyModal : function()
    {
      // Cleanup event handler for the category list delegate
      var categoryList = document.getElementById('categoryList');
      if(categoryList)
      {
        YAHOO.util.Event.removeListener(categoryList, 'click');
      }
    
      if(this._currentModal)
      {
        this._currentModal.destroy();
      }
      
      this._currentModal = null;
    },
    
    _destroyModalSec : function()
    {
      if(this._secondaryModal)
      {
        this._secondaryModal.destroy();
      }
      
      this._secondaryModal = null;
    },
    
    _createModalInternal : function(html, title, useLarge)
    {
      var executable = MDSS.util.extractScripts(html);
      var html = MDSS.util.removeScripts(html);
  
      var modal = new YAHOO.widget.Panel("modal_"+MDSS.MapPanel.zIndex, {
        width: "400px",
        height: useLarge ? "530px" : "400px",
        fixedcenter:true,
        close: false,
        draggable:false,
        zindex:MDSS.MapPanel.zIndex++,
        modal:true,
        visible:true
      });
  
      // wrap content in divs
      var outer = document.createElement('div');
  
      var header = document.createElement('div');
      header.innerHTML = '<h3>'+title+'</h3><hr />';
      outer.appendChild(header);
  
      var contentDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(contentDiv, (useLarge ? 'innerContentModalLarge' : 'innerContentModal'));
      contentDiv.innerHTML = html;
      outer.appendChild(contentDiv);
  
      modal.setBody(outer);
      modal.render(document.body);
  
      eval(executable);
  
      modal.bringToTop();
      
      return modal;
    },
    
    _createModalSec : function(html, title, useLarge)
    {
      this._secondaryModal = this._createModalInternal(html, title, useLarge);
    },
    
    /**
     * Creates a modal with the given HTML as its body and the given title
     * as the modal title, wrapped in an H3.
     */
    _createModal : function(html, title, useLarge)
    {
      this._currentModal = this._createModalInternal(html, title, useLarge);
    },
  
    /**
     * Returns the curren ThematicVariable instance.
     */
    getCurrentThematicVariable : function(thematicVar)
    {
      return this._currentThematicVariable;
    },
  
    /**
     * Called when a user makes a request to edit a layer.
     */
    _editDefinedLayer : function(e, obj)
    {
      if(Mojo.Util.isFunction(this._config.editLayer))
      {
        this._config.editLayer.call(this._queryClass, obj.layerId);
      }
    },
  
    _deleteDefinedLayer : function(e, obj)
    {
      if(Mojo.Util.isFunction(this._config.deleteLayer))
      {
        this._config.deleteLayer.call(this._queryClass, obj.layerId, obj.type);
      }
    },
  
    removeDefinedLayer : function(layerId, type)
    {
      var li = document.getElementById(layerId+'_defined');
      li.parentNode.removeChild(li);
  
      // enable the option in the available list
      document.getElementById(type+"_available").disabled = false;
    },
  
    /**
     * Adds a user defined layer to the right panel
     * of the map screen. The entry for the universal
     * with the given type is removed as an "available"
     * layer to add.
     */
    addDefinedLayer : function(layerId, type)
    {
      var li = document.createElement('li');
      YAHOO.util.Dom.setAttribute(li, 'id', layerId+"_defined")
  
      var layerObj = {
        layerId: layerId,
        type: type
      }
  
      var del = document.createElement('img');
      YAHOO.util.Dom.setAttribute(del, 'src', 'imgs/icons/delete.png');
      YAHOO.util.Event.on(del, 'click', this._deleteDefinedLayer, layerObj, this);
  
      var edit = document.createElement('img');
      YAHOO.util.Dom.setAttribute(edit, 'src', 'imgs/icons/wand.png');
      YAHOO.util.Event.on(edit, 'click', this._editDefinedLayer, layerObj, this);
  
      var check = document.createElement('input');
      YAHOO.util.Dom.setAttribute(check, 'type', 'checkbox');
      YAHOO.util.Dom.setAttribute(check, 'value', layerId);
  
      var span = document.createElement('span');
      var types = MDSS.GeoTreeSelectables.types; // defined by 111
      span.innerHTML = types[type].label;
  
      li.appendChild(del);
      li.appendChild(edit);
      li.appendChild(check);
      li.appendChild(span);
  
      var ul = document.getElementById(this.DEFINED_LAYERS_LIST);
      ul.appendChild(li);
  
      // disable the option in the available list (can't have duplicate layers)
      document.getElementById(type+"_available").disabled = true;
    },
  
    /**
     * Returns all selected layers to include in the map.
     */
    getSelectedLayers : function()
    {
      var layers = [];
  
      var ul = document.getElementById(this.DEFINED_LAYERS_LIST);
      var checks = YAHOO.util.Selector.query('input', ul);
      for(var i=0; i<checks.length; i++)
      {
        var check = checks[i];
        if(check.checked)
        {
          layers.push(check.value);
        }
      }
  
      return layers;
    },
  
    /**
     * Sets the selected thematic layer. Note that this should be called
     * after this.setAvailableThematicLayers().
     */
    setSelectedThematicLayer : function(layer)
    {
      var select = document.getElementById(this.THEMATIC_LAYERS_SELECT);
      var options = select.options;
  
      for(var i=0; i<options.length; i++)
      {
  
        var option = options[i];
        if(option.value === layer)
        {
          select.selectedIndex = i;
          break;
        }
      }
    },
    
    setAvailableThematicLayers : function(layers)
    {
      this._thematicLayers = layers;
      this._resetThematicOptions();
    },
    

    /**
     * Adds to the list of possible thematic variables a user can
     * choose from.
     */
    addThematicVariable : function(entityAlias, attributeName, userAlias, displayLabel)
    {
      var thematicVar = new Mojo.$.dss.vector.solutions.query.ThematicVariable();
      thematicVar.setEntityAlias(entityAlias);
      thematicVar.setAttributeName(attributeName);
      thematicVar.setUserAlias(userAlias);
      // thematicVar.setDisplayLabel(displayLabel); obsolete because display will be cached
  
      this._thematicVariables[userAlias] = thematicVar;
    },
  
    /**
     * Removes the given thematic variable.
     */
    removeThematicVariable : function(userAlias)
    {
      delete this._thematicVariables[userAlias];
    },
  
    getThematicVariables : function()
    {
      return Mojo.Util.getValues(this._thematicVariables);
    },

    _resetThematicOptions : function()
    {
      var select = document.getElementById(this.THEMATIC_LAYERS_SELECT);
      if(select)
      {
        var oldSelected = select.selectedIndex != -1 ? select.options[select.selectedIndex].value : null;
        var startIndex = null;
        select.innerHTML = '<option value="">&nbsp;</value>';
        for(var i=0; i<this._thematicLayers.length; i++)
        {
          var layer = this._thematicLayers[i];
  
          var option = document.createElement('option');
          option.value = layer;
          option.innerHTML = MDSS.GeoTreeSelectables.types[layer].label;
  
          select.appendChild(option);
  
          if(oldSelected != null && oldSelected === layer)
          {
            startIndex = i;
          }
        }
  
        select.selectedIndex = startIndex != null ? startIndex + 1 : 0;
      }
    },
  
    _thematicLayerSelected : function(e)
    {
      if(Mojo.Util.isFunction(this._config.thematicLayerSelected))
      {
        var select = e.target;
        var option = select.options[select.selectedIndex];
  
        this._config.thematicLayerSelected.call(this._queryClass, option.value);
      }
    },
  
    toggleThematicSettings : function(enabled)
    {
      document.getElementById(this.EDIT_DEFAULT_STYLE).disabled = !enabled;
      document.getElementById(this.EDIT_VARIABLE_STYLE).disabled = !enabled;
    },
    
    /**
     * Handler for when a new thematic layer type is selected.
     */
    thematicLayerSelected : function(layerType)
    {
      // Update the thematic layer for mapping. This is the only forced
      // save due to required artifact dependencies.
      var request = new MDSS.Request({
        thisRef : this,
        layerType : layerType,
        onSuccess : function()
        {
          var enable = this.layerType != null && this.layerType !== '';
          this.thisRef._queryPanel.toggleThematicSettings(enable);
        }
      });
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var thematicLayerId = (savedSearchView != null ? savedSearchView.getThematicLayerId() : "");
  
      Mojo.$.dss.vector.solutions.query.ThematicLayer.changeLayerType(request, thematicLayerId, layerType);
    },
    
    /**
     * Adds a map with the given configuration.
     */
    createMap : function(layers)
    {
      var baseLayer = layers[0];
      var geoServerPath = baseLayer.geoserverURL;
  
      // clear any previous map
      if(this._map != null)
      {
        this._map.destroy();
        this._annotation.hideAll();
      }
  
      // pink tile avoidance
      OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
      // make OL compute scale according to WMS spec
      OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
  
      var bbox = baseLayer.bbox;
      var bounds = new OpenLayers.Bounds(bbox[0], bbox[1], bbox[2], bbox[3]);
      var options = {
          controls: [],
          projection: "EPSG:4326",
          units: 'degrees'
      };
  
  
      this._map = new OpenLayers.Map(MDSS.MapPanel.MAP_CONTAINER, options);
  
      // setup base tiled layer
      var mapLayers = [];
      var tiled = new OpenLayers.Layer.WMS(
          "", geoServerPath+"/wms",
          {
              srs: 'EPSG:4326',
              layers: baseLayer.view,
              styles: '',
              format: 'image/png',
              sld: Mojo.ClientSession.getBaseEndpoint()+baseLayer.sld,
              tiled: 'true'
          },
          {
            buffer: 0,
            opacity: 1.0,
            isBaseLayer: true
      });
      
      mapLayers.push(tiled);
  
      for(var i=1; i<layers.length; i++)
      {
        var layerName = layers[i];
          var extraLayer = new OpenLayers.Layer.WMS(
          "", geoServerPath+"/wms",
          {
              srs: 'EPSG:4326',
              layers: layerName.view,
              styles: '',
              format: 'image/png',
              sld: Mojo.ClientSession.getBaseEndpoint()+layerName.sld,
              transparent: true,
              tiled: 'true'
          },
          {
            buffer: 0,
            opacity: 0.5
        });
  
        mapLayers.push(extraLayer);
      }
  
      this._map.addLayers(mapLayers);
  
      // build up all controls
      this._map.addControl(new OpenLayers.Control.PanZoomBar({
          position: new OpenLayers.Pixel(2, 15)
      }));
      this._map.addControl(new OpenLayers.Control.Navigation());
      //this._map.addControl(new OpenLayers.Control.ScaleLine());
      this._map.zoomToExtent(bounds);
    }, 
    
    /* FIXME MAP
    addLayer : function(type)
    {
      var request = new MDSS.Request({
        type: type,
        thisRef: this,
        onSuccess: function(layerId){
  
          layerId = MDSS.util.stripWhitespace(layerId);
          this.thisRef._queryPanel.addDefinedLayer(layerId, this.type);
        }
      });
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var savedSearchId = savedSearchView.getSavedQueryId();
      Mojo.$.dss.vector.solutions.query.MappingController.createLayer(request, savedSearchId, type);
    },
  
    editLayer : function(layerId)
    {
      var controller = Mojo.$.dss.vector.solutions.query.MappingController;
  
      var request = new MDSS.Request({
        thisRef: this,
        layerId: layerId,
        controller: controller,
        onSuccess: function(html){
  
          var modal = this.thisRef._createModal(html, MDSS.Localized.Update, true);
  
          var update = Mojo.Util.bind(this.thisRef, this.thisRef._updateLayerListener, modal);
          var canceled = Mojo.Util.bind(this.thisRef, this.thisRef._cancelLayerListener, modal, layerId, true);
          this.controller.setUpdateLayerListener(update);
          this.controller.setCancelLayerListener(canceled);
        }
      });
  
      controller.editLayer(request, layerId);
    },
     */
  
    _updateLayerListener : function(modal, params, action)
    {
      var request = new MDSS.Request({
        modal: modal,
        onSuccess: function(){
  
          this.modal.destroy();
        }
      });
  
      return request;
    },
  
    _cancelLayerListener : function(modal, layerId, unlock)
    {
      var request = new MDSS.Request({
        modal: modal,
        onSuccess: function(){
          this.modal.destroy();
        }
      });
  
      Mojo.$.dss.vector.solutions.query.MappingController.cancelLayer(request, layerId, unlock);
    },
  
    /**
     *
     */
    deleteLayer : function(layerId, type)
    {
      var request = new MDSS.Request({
        thisRef : this,
        layerId : layerId,
        type : type,
        onSuccess : function()
        {
          this.thisRef._queryPanel.removeDefinedLayer(this.layerId, this.type);
        }
      });
  
      Mojo.$.dss.vector.solutions.query.MappingController.deleteLayer(request, layerId);
    },
  
    /**
     * Listener for when a use requesets a new AbstractCategory.
     * The returned HTML form is appended to the list of editable
     * categories.
     */
    _newCategoryListener : function(params, action)
    {
      var request = new MDSS.Request({
        thisRef: this,
        onSuccess: function(html)
        {
          this.thisRef._queryPanel.addCategoryHTML(html);
        }
      });
  
      return request;
    },
  
    _editThematicVariable : function(modal, layerId, params, action)
    {
      var request = new MDSS.Request({
        modal: modal,
        onSuccess: function()
        {
          this.modal.destroy();
        }
      });
  
  
      var thematicVarStr = params['variable'][0];
      var thematicVar;
      if(thematicVarStr !== '')
      {
        // The string values are set in editVariableStyles.jsp
        thematicVar = new Mojo.$.dss.vector.solutions.query.ThematicVariable();
        var pieces = thematicVarStr.split('_-_');
        thematicVar.setEntityAlias(pieces[0]);
        thematicVar.setAttributeName(pieces[1]);
        thematicVar.setUserAlias(pieces[2]);
  
        var display = this._queryPanel.getSelectedDisplayLabel(pieces[2]);
        thematicVar.setDisplayLabel(display);
      }
      else
      {
        thematicVar = null;
      }
  
      this._queryPanel.setCurrentThematicVariable(thematicVar);
  
      var categories = this._queryPanel.scrapeCategories();
      Mojo.$.dss.vector.solutions.query.ThematicLayer.updateThematicVariable(request, layerId, thematicVar, categories);
    },
  
    /**
     * Handler to edit the styles associated with a thematic variable.
     */
    editVariableStyles : function()
    {
      var controller = Mojo.$.dss.vector.solutions.query.MappingController;
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var thematicLayerId = savedSearchView.getThematicLayerId();
  
      var request = new MDSS.Request({
        thisRef: this,
        controller: controller,
        thematicLayerId: thematicLayerId,
        onSuccess: function(html){
  
          var modal = this.thisRef._createModal(html, MDSS.Localized.Update);
  
          var update = Mojo.Util.bind(this.thisRef, this.thisRef._editThematicVariable, modal, this.thematicLayerId);
          var canceled = Mojo.Util.bind(this.thisRef, this.thisRef._cancelLayerListener, modal, this.thematicLayerId,true);
          var newCategory = Mojo.Util.bind(this.thisRef, this.thisRef._newCategoryListener);
  
          this.controller.setUpdateThematicVariableListener(update);
          this.controller.setCancelLayerListener(canceled);
  
          Mojo.$.dss.vector.solutions.query.RangeCategoryController.setNewInstanceListener(newCategory);
          Mojo.$.dss.vector.solutions.query.NonRangeCategoryController.setNewInstanceListener(newCategory);
        }
      });
  
      var thematicVars = this._queryPanel.getThematicVariables();
      for(var i=0; i<thematicVars.length; i++)
      {
        // grab the most recent version of the display
        // label (taking aggregate functions into account)
        var thematic = thematicVars[i];
  
        var display = this._queryPanel.getSelectedDisplayLabel(thematic.getUserAlias());
        thematic.setDisplayLabel(display);
      }
  
      controller.editThematicLayer(request, thematicLayerId, thematicVars);
    }
  },
  
  Static : {
  
    _currentMap : null,
    
    setCurrentMap : function(mapId)
    {
      MDSS.MapPanel._currentMap = mapId;
    },
    
    getCurrentMap : function()
    {
      return MDSS.MapPanel._currentMap;
    },  
    
    /**
     * Called after any insert, delete, or move is done on the
     * list of available layers. This method resets the base layer
     * and re-numbers all other layers.
     */
    toggleBaseLayer : function()
    {
      var ul = document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS);
      var children = ul.childNodes;
      
      var dom = YAHOO.util.Dom;
      
      if(children.length > 0)
      {
        Mojo.Iter.forEach(children, function(child, index){
          
          var layerCount;
          if(index === 0)
          {
            layerCount = MDSS.localize('Base_Layer')+': ';
          }
          else
          {
            layerCount = MDSS.localize('Layer')+' '+index+': ';
          }
          
          child.childNodes[2].innerHTML = layerCount;
          
          if(dom.hasClass(child, 'baseLayer'))
          {
            dom.removeClass(child, 'baseLayer');
          }
        });
        
        dom.addClass(children[0], 'baseLayer');
      }
    },
    
    zIndex : 10 // starting zIndex of Modals
  }
  
});

Mojo.Meta.newClass("MDSS.Annotation", {

  Instance : {
  
    initialize : function(queryPanel)
    {
      this._queryPanel = queryPanel;
      this._modal = null;
    },
  
    showModal : function()
    {
  
      if(this._modal == null)
      {
        this._modal = new YAHOO.widget.Panel("editAnnotations", {
          width:"400px",
          height: "400px",
          fixedcenter:true,
          close: true,
          draggable:false,
          zindex:4,
          modal:true,
          visible:true
        });
  
        // wrap content in divs
        var outer = document.createElement('div');
  
        var header = document.createElement('div');
        header.innerHTML = '<h3>'+MDSS.Localized.Annotations+'</h3><hr />';
        outer.appendChild(header);
  
        var html = '';
        html += '<dl>';
        html += '  <dt>';
        html += '    '+MDSS.Localized.Title+'&nbsp;<input id="titleInput" type="text" />';
        html += '  </dt>';
        html += '  <dd>';
        html += '    <input type="checkbox" id="titleAnnotationCheck" />&nbsp;'+MDSS.Localized.Enable;
        html += '  </dd>';
        html += '  <dt>';
        html += '    '+MDSS.Localized.NorthArrow;
        html += '  </dt>';
        html += '  <dd>';
        html += '    <input type="checkbox" id="arrowAnnotationCheck" />&nbsp;'+MDSS.Localized.Enable;
        html += '  </dd>';
        html += '  <dt>';
        html += '   '+MDSS.Localized.Legend;
        html += '  </dt>';
        html += '  <dd>';
        html += '    <input type="checkbox" id="legendAnnotationCheck" />&nbsp;'+MDSS.Localized.Enable;
        html += '  </dd>';
        html += '  <dt>';
        html += '   Scale';
        html += '  </dt>';
        html += '  <dd>';
        html += '    <input type="checkbox" id="scaleAnnotationCheck" />&nbsp;'+MDSS.Localized.Enable;
        html += '  </dd>';
        html += '</dl>';
        html += '<input type="button" id="updateAnnotations" value="'+MDSS.Localized.Update+'" />';
  
        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);
  
        this._modal.setBody(outer);
        this._modal.render(document.body);
  
        header = null; // cleanup
        contentDiv = null;
  
        YAHOO.util.Event.on('updateAnnotations', 'click', this.updateAnnotations, null, this);
      }
      else
      {
        this._modal.show();
        this._modal.bringToTop();
      }
  
    },
  
    updateAnnotations : function()
    {
      var titleC = document.getElementById('titleAnnotationCheck');
      if(titleC.checked)
      {
        this.showTitle();
      }
      else
      {
        this.hideTitle();
      }
  
      var arrowC = document.getElementById('arrowAnnotationCheck');
      if(arrowC.checked)
      {
        this.showArrow();
      }
      else
      {
        this.hideArrow();
      }
  
      var legendC = document.getElementById('legendAnnotationCheck');
      if(legendC.checked)
      {
        this.showLegend();
      }
      else
      {
        this.hideLegend();
      }
  
      try
      {
      var scaleC = document.getElementById('scaleAnnotationCheck');
      if(scaleC.checked)
      {
        this.showScale();
      }
      else
      {
        this.hideScale();
      }
      }
      catch(e){alert(e);}
  
      this.hideModal();
    },
  
    hideModal : function()
    {
      this._modal.hide();
    },
  
    showScale : function()
    {
      var scaleDiv = document.getElementById('scaleDiv');
      if(scaleDiv == null)
      {
        scaleDiv = document.createElement('div');
        scaleDiv.id = 'scaleDiv';
  
        this.addDragDrop(scaleDiv);
      }
  
      // always clear the div just to be safe
      scaleDiv.innerHTML = '';
  
      if(this._queryPanel._map != null)
      {
        this._queryPanel._map.addControl(new OpenLayers.Control.ScaleLine({'div':scaleDiv}));
      }
  
      this.position(scaleDiv, -100, -100);
    },
  
    hideScale : function()
    {
      var scaleDiv = document.getElementById('scaleDiv');
      if(scaleDiv)
      {
        YAHOO.util.Dom.setStyle(scaleDiv, 'display', 'none');
      }
    },
  
    showTitle : function()
    {
      var titleDiv = document.getElementById('titleDiv');
      if(titleDiv == null)
      {
        titleDiv = document.createElement('div');
        titleDiv.id = 'titleDiv';
  
        this.addDragDrop(titleDiv);
      }
  
      var title = document.getElementById('titleInput').value;
      titleDiv.innerHTML = title;
  
  
      this.position(titleDiv, 100, -100);
    },
  
    hideAll : function()
    {
      this.hideTitle();
      this.hideScale();
      this.hideLegend();
      this.hideArrow();
    },
  
    hideTitle : function()
    {
      var titleDiv = document.getElementById('titleDiv');
      if(titleDiv)
      {
        YAHOO.util.Dom.setStyle(titleDiv, 'display', 'none');
        document.getElementById('titleInput').value = '';
      }
    },
  
    position : function(div, leftOffset, topOffset)
    {
      YAHOO.util.Dom.setStyle(div, 'display', 'block');
  
      var mapDiv = document.getElementById('mapContainer');
  
      var xy = YAHOO.util.Dom.getXY(mapDiv);
  
      var mWidth = parseInt(YAHOO.util.Dom.getStyle(mapDiv, 'width'), 10);
      var mHeight = parseInt(YAHOO.util.Dom.getStyle(mapDiv, 'height'), 10);
  
      var dWidth = parseInt(YAHOO.util.Dom.getStyle(div, 'width'), 10);
      var dHeight = parseInt(YAHOO.util.Dom.getStyle(div, 'height'), 10);
  
      var center = [xy[0] + (mWidth/2) - (dWidth/2) + leftOffset,
        xy[1] + (mHeight/2) - (dHeight/2) + topOffset];
  
      YAHOO.util.Dom.setXY(div, center);
  
      var region = YAHOO.util.Region.getRegion(mapDiv);
      xy = YAHOO.util.Dom.getXY(div);
  
      //Set left to x minus left
      var left = xy[0] - region.left;
  
      //Set right to right minus x minus width
      var right = region.right - xy[0] - dWidth;
  
      //Set top to y minus top
      var top = xy[1] - region.top;
  
      //Set bottom to bottom minus y minus height
      var bottom = region.bottom - xy[1] - dHeight;
  
      //Set the constraints based on the above calculations
      var dd = YAHOO.util.DragDropMgr.getDDById(div.id);
      dd.setXConstraint(left, right);
      dd.setYConstraint(top, bottom);
    },
  
    addDragDrop : function(div)
    {
      var mapDiv = document.getElementById('mapPanel');
      var region = YAHOO.util.Region.getRegion(mapDiv);
  
      YAHOO.util.Dom.setStyle(div, 'position', 'absolute');
      YAHOO.util.Dom.setStyle(div, 'display', 'block');
  
      mapDiv.appendChild(div);
  
      var dd = new YAHOO.util.DD(div, {
        dragOnly : true
      });
    },
  
    showArrow : function()
    {
      var arrowDiv = document.getElementById('arrowDiv');
      if(arrowDiv == null)
      {
        arrowDiv = document.createElement('div');
        arrowDiv.id = 'arrowDiv';
        arrowDiv.innerHTML = '<img src="imgs/northArrow.png" style="width: 50px; height: 50px;" />';
  
        this.addDragDrop(arrowDiv);
      }
  
      this.position(arrowDiv, -100, 100);
    },
  
    hideArrow : function()
    {
      var arrowDiv = document.getElementById('arrowDiv');
      if(arrowDiv)
      {
        YAHOO.util.Dom.setStyle(arrowDiv, 'display', 'none');
      }
    },
  
    hideLegend : function()
    {
      var legendDiv = document.getElementById('legendDiv');
      if(legendDiv)
      {
        YAHOO.util.Dom.setStyle(legendDiv, 'display', 'none');
      }
    },
  
    showLegend : function()
    {
      var request = new MDSS.Request({
        thisRef : this,
        onSuccess : function(legendJSON)
        {
          var legendDiv = document.getElementById('legendDiv');
  
          legendJSON = MDSS.util.stripWhitespace(legendJSON);
          if(legendJSON.indexOf('{') == -1)
          {
            new MDSS.ErrorModal(legendJSON);
          }
          else if(legendDiv == null)
          {
            legendDiv = document.createElement('div');
            legendDiv.id = 'legendDiv';
            this.thisRef.addDragDrop(legendDiv);
          }
  
          var table = '<table>';
  
          var legend = Mojo.Util.getObject(legendJSON);
          table += '<tr><td colspan="2">'+legend.thematicVariable+'</td></tr>';
  
          var categories = legend.categories;
          for(var i=0; i<categories.length; i++)
          {
            var category = categories[i];
            var color = category.color;
            var values = category.values;
  
            table += '<tr>';
  
            if(values.length == 1)
            {
              // exact value
                table += '<td>'+values[0]+'</td>';
            }
            else
            {
              // range
              table += '<td>'+values[0]+'  -  '+values[1]+'</td>';
            }
  
            table += '<td><div class="colorPickerValue" style="background-color: '+color+'">&nbsp;</div></td>';
  
  
            table += '</tr>';
          }
  
          table += '</table>';
  
          legendDiv.innerHTML = table;
  
          this.thisRef.position(legendDiv, 100, 100);
        }
  
      });
  
      var savedSearchView = this._queryPanel.getCurrentSavedSearch();
      var savedSearchId = savedSearchView.getSavedQueryId();
      Mojo.$.dss.vector.solutions.query.MappingController.getLegend(request, savedSearchId);
    }
  }
});

(function(){
var Dom = YAHOO.util.Dom;
var Event = YAHOO.util.Event;
var DDM = YAHOO.util.DragDropMgr;


//////////////////////////////////////////////////////////////////////////////
// custom drag and drop implementation
//////////////////////////////////////////////////////////////////////////////

MDSS.SelectedQueryDD = function(id, sGroup, config) {

    MDSS.SelectedQueryDD.superclass.constructor.call(this, id, sGroup, config);

    this.logger = this.logger || YAHOO;
    var el = this.getDragEl();
    Dom.setStyle(el, "opacity", 0.67); // The proxy is slightly transparent

    this.goingUp = false;
    this.lastY = 0;
    this.lastIndex = null;
};

YAHOO.extend(MDSS.SelectedQueryDD, YAHOO.util.DDProxy, {

    startDrag: function(x, y) {
        this.logger.log(this.id + " startDrag");

        // make the proxy look like the source element
        var dragEl = this.getDragEl();
        var clickEl = this.getEl();
        
        var oldChildren = document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS).childNodes;

        for(var i=0; i<oldChildren.length; i++)
        {
          if(oldChildren[i].id === clickEl.id)
          {
            this.oldIndex = i;
            break;
          }
        }
        
        Dom.setStyle(clickEl, "visibility", "hidden");

        dragEl.innerHTML = clickEl.innerHTML;

        Dom.setStyle(dragEl, "color", Dom.getStyle(clickEl, "color"));
        Dom.setStyle(dragEl, "backgroundColor", Dom.getStyle(clickEl, "backgroundColor"));
        Dom.setStyle(dragEl, "border", "2px solid gray");
    },

    endDrag: function(e) {

        var srcEl = this.getEl();
        var proxy = this.getDragEl();
        var proxyid = proxy.id;
        var thisid = this.id;

        // Show the proxy element and animate it to the src element's location
        Dom.setStyle(proxy, "visibility", "");
        var a = new YAHOO.util.Motion(
            proxy, {
                points: {
                    to: Dom.getXY(srcEl)
                }
            },
            0.2,
            YAHOO.util.Easing.easeOut
        )

        // Hide the proxy and show the source element when finished with the animation
        var that = this;
        a.onComplete.subscribe(function() {
        
          var complete = function() {
              Dom.setStyle(proxyid, "visibility", "hidden");
              Dom.setStyle(thisid, "visibility", "");

              MDSS.MapPanel.toggleBaseLayer();                
          }
        
          var request = new MDSS.Request({
            onSuccess : complete
          });
          
          var children = document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS).childNodes;
          var index = null;
          for(var i=0; i<children.length; i++)
          {
            if(children[i].id === thisid)
            {
              index = i;
              break;
            }
          }
          
          if(that.oldIndex !== index)
          {
            var mapId = MDSS.MapPanel.getCurrentMap();
            Mojo.$.dss.vector.solutions.query.SavedMap.moveLayerOnMap(request, mapId, thisid, index);
          }
          else
          {
            complete();
          }
        
        });
        a.animate();
    },

    onDragDrop: function(e, id) {

        // If there is one drop interaction, the li was dropped either on the list,
        // or it was dropped on the current location of the source element.
        if (DDM.interactionInfo.drop.length === 1) {

            // The position of the cursor at the time of the drop (YAHOO.util.Point)
            var pt = DDM.interactionInfo.point;

            // The region occupied by the source element at the time of the drop
            var region = DDM.interactionInfo.sourceRegion;

            // Check to see if we are over the source element's location.  We will
            // append to the bottom of the list once we are sure it was a drop in
            // the negative space (the area of the list without any list items)
            if (!region.intersect(pt)) {
                var destEl = Dom.get(id);
                var destDD = DDM.getDDById(id);
                destEl.appendChild(this.getEl());
                destDD.isEmpty = false;
                DDM.refreshCache();
            }

        }
    },

    onDrag: function(e) {

        // Keep track of the direction of the drag for use during onDragOver
        var y = Event.getPageY(e);

        if (y < this.lastY) {
            this.goingUp = true;
        } else if (y > this.lastY) {
            this.goingUp = false;
        }

        this.lastY = y;
    },

    onDragOver: function(e, id) {

        var srcEl = this.getEl();
        var destEl = Dom.get(id);

        // We are only concerned with list items, we ignore the dragover
        // notifications for the list.
        if (destEl.nodeName.toLowerCase() == "li") {
            var orig_p = srcEl.parentNode;
            var p = destEl.parentNode;

            if (this.goingUp) {
                p.insertBefore(srcEl, destEl); // insert above
            } else {
                p.insertBefore(srcEl, destEl.nextSibling); // insert below
            }

            DDM.refreshCache();
        }
    }
});
})();

Mojo.Meta.newClass("MDSS.ColorPicker", {

  IsSingleton: true,
  
  Instance : {
    
    initialize : function()
    {
      this._dialog = null;
      this._picker = null;
    },
    
    /**
     * Attaches the picker to the element of the given id such
     * that a click event on the element will render the picker.
     */
    attach : function(openerId, inputId)
    {
      YAHOO.util.Event.removeListener(openerId, 'click', this._renderDialog);
  
      var obj = {
        openerId : openerId,
        inputId : inputId
      };
  
      var openerEl = document.getElementById(openerId);
      YAHOO.util.Event.on(openerEl, 'click', this._renderDialog, obj, this);
    },
  
    /**
     * Handles the submit click after
     * selecting a color.
     */
    _handleSubmit: function()
    {
      var openerEl = document.getElementById(this._openerId);
  
      var sColor = "#" + this._picker.get("hex");
      document.getElementById(this._inputId).value = sColor;
      YAHOO.util.Dom.setStyle(openerEl, 'background-color', sColor);
  
      this._dialog.hide();
    },
  
    /**
     * Cancels selecting a color and
     * closes the dialog.
     */
    _handleCancel: function()
    {
      this._dialog.hide();
    },
  
    _renderDialog : function(e, obj)
    {
      this._openerId = obj.openerId;
      this._inputId = obj.inputId;
  
      if(this._dialog == null)
      {
        var sBound = Mojo.Util.bind(this, this._handleSubmit);
        var cBound = Mojo.Util.bind(this, this._handleCancel);
  
        this._dialog = new YAHOO.widget.Dialog("singleton_pickerPanel", {
          width : "400px",
          height: "250px",
          fixedcenter : true,
          visible : true,
          constraintoviewport : true,
          draggable: false,
          close: false,
          zindex: 200,
          buttons : [ { text: MDSS.Localized.Submit, handler:sBound, isDefault:true },
              { text: MDSS.Localized.Cancel, handler:cBound } ]
        });
  
        this._dialog.setBody('<div class="yui-picker" id="singleton_picker"></div>');
        this._dialog.render(document.body);
        this._dialog.bringToTop();
  
        this._picker = new YAHOO.widget.ColorPicker("singleton_picker", {
          container: this._dialog,
          showcontrols: false,
          images: {
            PICKER_THUMB: "js/yui/build/colorpicker/assets/picker_thumb.png"
          },
        });
      }
      else
      {
        this._dialog.show();
        this._dialog.bringToTop();
      }
      
      var val = document.getElementById(this._inputId).value.substring(1);      
      this._picker.set('hex', val);
    }    
  }

});