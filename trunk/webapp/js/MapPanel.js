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
    ADD_AS_LAYER_BUTTON : "addAsLayerBtn",
    AVAILABLE_LAYERS : "availableLayers",
    DELETE_SUFFIX : "_delete",
    MAP_LIST : "mapList"
  },
  
  Instance : {
    
    initialize : function(mapPanelId, queryList, mapList)
    {
      this._queryList = queryList;
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
      
      this._SavedMapController = Mojo.$.dss.vector.solutions.query.SavedMapController;
      this._SavedMapController.setCreateListener(Mojo.Util.bind(this, this._createListener));
      this._SavedMapController.setCancelListener(Mojo.Util.bind(this, this._cancelListener));
//      this._SavedMapController.setDeleteListener(Mojo.Util.bind(this, this._deleteListener));
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
          MDSS.MapPanel.setCurrentMap(defaultMap.getId()); 
        }
      });
      
      Mojo.$.dss.vector.solutions.query.SavedMap.loadDefaultMap(request, null);
    },
    
    _createLeftColumn : function()
    {
      var html = '';
      html += '<div id="'+MDSS.MapPanel.LEFT_COLUMN+'">';
      
      // Query List
      html += MDSS.Localized.Available_Queries+':<br />';
      
      html += '<select id="'+MDSS.MapPanel.QUERY_LIST+'" class="queryList">'; 
      html += Mojo.Iter.map(this._queryList, function(query){
        return '<option value="'+query.id+'">'+query.name+'</option>'; 
      }).join('');
      
      // Add query button
      html += '<input type="button" id="'+MDSS.MapPanel.ADD_AS_LAYER_BUTTON+'" value="'+MDSS.localize('Add_As_Layer')+'">';
      html += '<br />';
      
      // layers
      html += '<div style="margin-top: 5px">';
      html += MDSS.localize('Available_Layers')+':<br />';
      html += '<ul id="'+MDSS.MapPanel.AVAILABLE_LAYERS+'">';
      html += '</ul>';
      
      html += '</div>';
      html += '</div>';
    
      var left = this._mapLayout.getUnitByPosition('left');
      left.body.innerHTML = html;
      
      // Event to add a query to the selected queries
      YAHOO.util.Event.on(MDSS.MapPanel.ADD_AS_LAYER_BUTTON, 'click', this._addAsLayer, null, this);
      
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
      
      if(id.indexOf(MDSS.MapPanel.DELETE_SUFFIX) != 1)
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
    },
    
    _addAsLayer : function()
    {
      var select = document.getElementById(MDSS.MapPanel.QUERY_LIST);
      var savedSearchId = select.options[select.selectedIndex].value;
      
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(layerView)
        {
          // add a draggable list item for the new layer
          var li = document.createElement('li');
          li.id = layerView.getLayerId();
          
          var html = '<span style="font-weight: bold"></span>';
          html += '<img class="clickable" style="margin-right: 5px" src="imgs/icons/delete.png" id="'
            +li.id+MDSS.MapPanel.DELETE_SUFFIX+'" />';
          html += layerView.getLayerName();
          
          li.innerHTML = html;
          
          var ul = document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS);
          ul.appendChild(li);
          
          new MDSS.SelectedQueryDD(li.id);
          
          MDSS.MapPanel.toggleBaseLayer();
        } 
      });
      
      var mapId = this.constructor.getCurrentMap();
      Mojo.$.dss.vector.solutions.query.SavedMap.addLayerToMap(request, mapId, savedSearchId);
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
    
    _createListener : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        mapName: params['savedMap.mapName'],
        onSuccess : function(mapId)
        {
          // insert the new map into the select box
          var that = this.that;
          mapId = Mojo.Util.trim(mapId);
          
          that._addSavedMap(mapId, this.mapName);
          that._destroyModal();
        }
      });
      
      return request;
    },
    
    _cancelListener : function(params)
    {
      this._destroyModal();
    },
    
    _deleteMap : function()
    {
    },
    
    _addSavedMap : function(mapId, mapName)
    {
      var option = document.createElement('option');
      option.value = mapId;
      option.innerHTML = mapName;
      
      var mapList = document.getElementById(MDSS.MapPanel);
      mapList.appendChild(option);
      mapList.selectedIndex = mapList.options.length-1;
      
      // update the current map to the newly created one
      MDSS.MapPanel.setCurrentMap(mapId);
    },
    
    _loadMap : function(e)
    {
      alert(e);
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
      if(this._currentModal)
      {
        this._currentModal.destroy();
      }
      
      this._currentModal = null;
    },
    
    /**
     * Creates a modal with the given HTML as its body and the given title
     * as the modal title, wrapped in an H3.
     */
    _createModal : function(html, title, useLarge)
    {
      var executable = MDSS.util.extractScripts(html);
      var html = MDSS.util.removeScripts(html);
  
      var modal = new YAHOO.widget.Panel("editQuery", {
        width: "400px",
        height: useLarge ? "530px" : "400px",
        fixedcenter:true,
        close: false,
        draggable:false,
        zindex:4,
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
  
      this._currentModal = modal;
    },
    
    /**
     * Scraps the category list HTML and creates new instances
     * of AbstractCategor.
     */
    scrapeCategories : function()
    {
      var categories = [];
  
      var categoryList = document.getElementById(this.CATEGORY_LIST);
      var dds = YAHOO.util.Selector.query('dd', categoryList);
      for(var i=0; i<dds.length; i++)
      {
        var dd = dds[i];
        var inputs = YAHOO.util.Selector.query('input', dd);
  
        var type = inputs[0].value;
        var thematicColor = inputs[1].value;
  
        var construct = Mojo.Meta.findClass(type);
        var category = new construct();
        category.setThematicColor(thematicColor);
  
        if(type === Mojo.$.dss.vector.solutions.query.RangeCategory.CLASS)
        {
          var lowerBound = inputs[2].value;
          var upperBound = inputs[3].value;
  
          category.setLowerBound(lowerBound);
          category.setUpperBound(upperBound);
        }
        else
        {
          var exactValue = inputs[2].value;
  
          category.setExactValue(exactValue);
        }
  
        categories.push(category);
      }
  
      return categories;
    },
  
    addCategoryHTML : function(html)
    {
      var categoryList = document.getElementById(this.CATEGORY_LIST);
      var li = document.createElement('li');
      li.innerHTML = html;
  
      categoryList.appendChild(li);
    },
      
    /**
     * Adds a thematic variable to the map.
     */
    _editVariableStyles : function(e)
    {
      if(Mojo.Util.isFunction(this._config.editVariableStyles))
      {
        this._config.editVariableStyles.call(this._queryClass);
      }
    },
    

    /**
     * Returns the currently selected thematic layer in the drop down list
     * of available thematic layers.
     */
    getCurrentThematicLayer : function()
    {
      var select = document.getElementById(this.THEMATIC_LAYERS_SELECT);
      return select.options[select.selectedIndex].value;
    },
    
    /**
     * Sets the current ThematicVariable instance.
     */
    setCurrentThematicVariable : function(thematicVar)
    {
      this._currentThematicVariable = thematicVar;
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
     * Handler for when a new layer is added.
     */
    _addLayer : function()
    {
      if(Mojo.Util.isFunction(this._config.addLayer))
      {
        var layersList = document.getElementById(this.AVAILABLE_LAYERS_LIST);
        var options = layersList.options;
        var selected = options[layersList.selectedIndex];
  
        if(selected && selected.value)
        {
          var type = selected.value;
          this._config.addLayer.call(this._queryClass, type);
        }
      }
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
     * Builds a list of possible universal layers
     * that can be selected.
     */
    _buildUniversalList : function()
    {
      // list thematic variables
      var thematicDiv = new YAHOO.util.Element(document.createElement('div'));
  
      var thematicLayerDiv = document.createElement('div');
  
      var html = MDSS.Localized.Thematic.Layer+"<br />";
      html += "<select style='margin: 3px 0px; min-width: 220px;' id='"+this.THEMATIC_LAYERS_SELECT+"'>";
      html += "<option value=''></option>";
      html += "</select>";
      thematicLayerDiv.innerHTML = html;
  
      // edit default style
      var editDefaultStyle = new YAHOO.util.Element(document.createElement('input'));
      editDefaultStyle.set('type', 'button');
      editDefaultStyle.set('id', this.EDIT_DEFAULT_STYLE);
      editDefaultStyle.set('disabled', true);
      editDefaultStyle.set('value', MDSS.Localized.Thematic.Edit_Default_Style);
      editDefaultStyle.on('click', function(e){
        var search = this._currentSavedSearch;
        var layerId = search != null ? search.getThematicLayerId() : '';
        this._editDefinedLayer(e, {layerId:layerId});
      }, null, this);
  
      var editVariableStyles = new YAHOO.util.Element(document.createElement('input'));
      editVariableStyles.set('type', 'button');
      editVariableStyles.set('id', this.EDIT_VARIABLE_STYLE);
      editVariableStyles.set('disabled', true);
      editVariableStyles.set('value', MDSS.Localized.Thematic.Edit_Variable_Styles);
      editVariableStyles.on('click', this._editVariableStyles, null, this);
  
      thematicDiv.appendChild(thematicLayerDiv);
      thematicDiv.appendChild(editDefaultStyle);
      thematicDiv.appendChild(editVariableStyles);
  
      var availableSpan = document.createElement('span');
      YAHOO.util.Dom.setStyle(availableSpan, 'display', 'block');
      availableSpan.innerHTML = MDSS.Localized.Available_Layers;
  
      // this data structure is defined by
      // the GeoEntity tree for use case 111.
      // (We're just stealing it for our own use here.)
      var types = MDSS.GeoTreeSelectables.types;
      var typeNames = Mojo.Util.getKeys(types);
      typeNames.sort();
  
      var universalListDiv = new YAHOO.util.Element(document.createElement('div'));
      YAHOO.util.Dom.addClass(universalListDiv, 'universalList');
      var layers = document.createElement('select');
      YAHOO.util.Dom.setAttribute(layers, 'id', this.AVAILABLE_LAYERS_LIST);
  
      for(var i=0; i<typeNames.length; i++)
      {
        var type = typeNames[i];
        var displayLabel = types[type].label;
  
        var option = document.createElement('option');
        YAHOO.util.Dom.setAttribute(option, 'value', type);
        YAHOO.util.Dom.setAttribute(option, 'id', type+"_available");
        option.innerHTML = displayLabel;
  
        layers.appendChild(option);
      }
  
      universalListDiv.appendChild(availableSpan);
      universalListDiv.appendChild(layers);
  
      // add container for user defined layers
      var layersListDiv = document.createElement('div');
      YAHOO.util.Dom.addClass(layersListDiv, 'definedLayers');
  
      var definedSpan = document.createElement('span');
      definedSpan.innerHTML = MDSS.Localized.Defined_Layers;
  
      var ul = document.createElement('ul');
      YAHOO.util.Dom.setAttribute(ul, 'id', this.DEFINED_LAYERS_LIST);
  
      var ulDiv = document.createElement('div');
      ulDiv.appendChild(ul);
  
      layersListDiv.appendChild(definedSpan);
      layersListDiv.appendChild(ulDiv);
  
      // add button for new layer
      this._addLayerButton = new YAHOO.util.Element(document.createElement('input'));
      this._addLayerButton.set('type', 'button');
      this._addLayerButton.set('value', MDSS.Localized.Add);
      this._addLayerButton.on('click', this._addLayer, null, this);
  
      universalListDiv.appendChild(this._addLayerButton);
  
      var wrapper = new YAHOO.util.Element(document.createElement('div'));
      YAHOO.util.Dom.addClass(wrapper, 'layersWrapper');
      wrapper.appendChild(thematicDiv);
      wrapper.appendChild(universalListDiv);
      wrapper.appendChild(layersListDiv);
  
      // clear any prior HTML
      this._mLeftUnit.body.innerHTML = '';
      var body = new YAHOO.util.Element(this._mLeftUnit.body);
      body.appendChild(wrapper);
  
      var thematicSelect = document.getElementById(this.THEMATIC_LAYERS_SELECT);
      thematicSelect.selectedIndex = 0;
  
      YAHOO.util.Event.on(thematicSelect, 'change', this._thematicLayerSelected, null, this);
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
    
    /**
     * Adds a layer of the given type to the map.
     * The map is not refreshed.
     */
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
  
    /**
     * Locks a layer and its components to put them
     * in edit mode.
     */
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
          
          child.firstChild.innerHTML = layerCount;
          
          if(dom.hasClass(child, 'baseLayer'))
          {
            dom.removeClass(child, 'baseLayer');
          }
        });
        
        dom.addClass(children[0], 'baseLayer');
      }
    }
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