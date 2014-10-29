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
    LAYER_NAME_SUFFIX : "_layerName",
    TOGGLE_TIMEOUT : 500
  },
  
  Instance : {
    
    initialize : function(mapPanelId, mapList)
    {
      // Force the image path so the images can be located when JAWR is not in
      // debug mode
      OpenLayers.ImgPath = "js/OpenLayers/img/";
    
      this._mapList = mapList;
    
      var minWidth = 1250;
      var minHeight = 500;

      var pWidth =  (window.innerWidth - 30) > minWidth ? (window.innerWidth - 30) : minWidth;
      var pHeight = (window.innerHeight - 160) > minHeight ? (window.innerHeight -160) : minHeight;
      
      var that = this;
    
      this._mapLayout = new YAHOO.widget.Layout(mapPanelId, {
        height: pHeight,
        width: pWidth,
        units: [
            { position: 'left', width: 300, resize: false, body: '', gutter: '2 5 0 2', scroll: true },
            { position: 'bottom', height: 40, body: '', gutter: '2' },
            { position: 'center', body: '<div id="'+MDSS.MapPanel.MAP_CONTAINER+'"></div>', gutter: '2 2 0 0', scroll: true }
        ]
      });
      
      this._mapLayout.on('render', function() {
        var c = that._mapLayout.getUnitByPosition('left');
        
        // Apply the new layout to the body element of the first layout
        that._leftLayout = new YAHOO.widget.Layout(c.body, {
          parent: that._mapLayout,
          units: [
            { position: 'center', body: '', gutter: '2', resize: false, scroll: true },
            { position: 'bottom', height: 200, body: '', gutter: '2', resize: false}
          ]
        });
        
        that._leftLayout.render();
      });      
      
          
      // a map of ThematicVariable objects
      this._thematicVariables = {};
      this._thematicLayers = [];
    
  
      // The current ThematicVarible object that is used for mapping.
      this._currentThematicVariable = null;      
  
      // the current layers in the map. If this._currentSavedSearch
      // is not null, then these layers belong to that SavedSearch.
      this._layers = [];      
      
      this._currentModal = null;
      this._secondaryModal = null;
      
      this._SavedMapController = Mojo.$.dss.vector.solutions.query.SavedMapController;
      this._SavedMapController.setCreateListener(Mojo.Util.bind(this, this._mapCreateListener));
      this._SavedMapController.setCancelListener(Mojo.Util.bind(this, this._mapCancelListener));

      this._LayerController = Mojo.$.dss.vector.solutions.query.LayerController;
      this._LayerController.setSaveLayerListener(Mojo.Util.bind(this, this._saveLayerListener));
      this._LayerController.setCancelListener(Mojo.Util.bind(this, this._layerCancelListener));
      this._LayerController.setCalculateQueryInfoListener(Mojo.Util.bind(this, this._calculateQueryInfoListener));
      this._LayerController.setRequestGenerateListener(Mojo.Util.bind(this, this._requestGenerateListener));
      this._LayerController.setGenerateCategoriesListener(Mojo.Util.bind(this, this._generateCategoriesListener));
      
      this._AbstractCategoryController = Mojo.$.dss.vector.solutions.query.AbstractCategoryController;
      this._AbstractCategoryController.setSaveCategoryListener(Mojo.Util.bind(this, this._saveCategoryListener));
      
      this._RangeController = Mojo.$.dss.vector.solutions.query.RangeCategoryController;
      this._RangeController.setNewInstanceListener(Mojo.Util.bind(this, this._categoryNewInstanceListener));
      this._RangeController.setCancelListener(Mojo.Util.bind(this, this._categoryCancelListener));
      
      this._NonRangeController = Mojo.$.dss.vector.solutions.query.NonRangeCategoryController;
      this._NonRangeController.setNewInstanceListener(Mojo.Util.bind(this, this._categoryNewInstanceListener));
      this._NonRangeController.setCancelListener(Mojo.Util.bind(this, this._categoryCancelListener));
      
      
      this._MappingController = Mojo.$.dss.vector.solutions.query.MappingController;
      this._MappingController.setSetTextListener(Mojo.Util.bind(this, this._setTextListener));
      
      this._map = null;
      
      this._ddDivs = [];
      
      // offsets when placing floating divs
      this._offsetInd = 0;
      this._offsets = [
        [0,100],
        [45,45],
        [100,0],
        [45,-45],
        [0,-100],
        [-45,-45],
        [-100,0],
        [-45,45]
      ];
      
      this._northArrow = false;
      this._scale = false;
      
      // attach load listener to Iframe to receive message when error occurs
      // during
      // export operations
      YAHOO.util.Event.on('exportIframe', 'load', this._handleExport, null, this);
    
      YAHOO.util.Event.on('imageIframe', 'load', this._handleImageUpload, null, this);
      
      YAHOO.util.Event.on('mapExportIframe', 'load', this._handleMapExportUpload, null, this);
      
      this._drawLineControl = null;
      this._measureControle = null;      
    },
    
    /**
     * Handler for mapExportIframe event listener
     * 
     */
    _handleMapExportUpload : function(e)
    {
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var mapId = this.constructor.getCurrentMap();
      
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
       	var that = this;  
        var obj = Mojo.Util.getObject(text);
        if(obj.success)
        {
          that._renderImages(obj.message, obj.id);
          this._destroyModal();
        }
        else
        {
          new MDSS.ErrorModal(obj.message);
        }
      }
    },
    
    /**
     * Handler for imageIframe event listener
     * 
     */
    _handleImageUpload : function(e)
    {
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var mapId = this.constructor.getCurrentMap();
      
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
         var that = this;  
        var obj = Mojo.Util.getObject(text);
        if(obj.success)
        {
          that._renderImages(obj.message, obj.id);
          this._destroyModal();
        }
        else
        {
          new MDSS.ErrorModal(obj.message);
        }
      }
    },
    
    /**
     * Renders an image on the map
     * 
     * @src - path to image on the file system
     * @id - unique id of the image (MapImage instance id)
     */
    _renderImages : function(src, id)
    {
        var img = document.createElement('img');
        img.src = src;
        
        var closeDiv = document.createElement('span');
        closeDiv.setAttribute("class", "closeButton");
        YAHOO.util.Dom.setStyle(closeDiv, "display", "none");
        closeDiv.innerHTML = "X";
        
        var div = document.createElement('div');
        div.id = id;
        YAHOO.util.Dom.addClass(div, 'mapImage');
        div.appendChild(closeDiv)
        div.appendChild(img);
        
        YAHOO.util.Dom.setStyle(div, 'cursor', 'move');
        
        var mouseEnterHandler = function(e){
             var child = YAHOO.util.Dom.getFirstChild(e.target);
           if(YAHOO.util.Dom.hasClass(child, "closeButton")){
             YAHOO.util.Dom.setStyle(closeDiv, "display", "");
           }
        }
        var mouseLeaveHandler = function(e){
             var child = YAHOO.util.Dom.getFirstChild(e.target);
           if(YAHOO.util.Dom.hasClass(child, "closeButton")){
             YAHOO.util.Dom.setStyle(closeDiv, "display", "none");
           }
        }
        
        YAHOO.util.Event.on(div, 'mouseenter', mouseEnterHandler);
        YAHOO.util.Event.on(div, 'mouseleave', mouseLeaveHandler);
        
        var dd = this.addDragDrop(div);
        dd.endDrag = Mojo.Util.bind(this, this._updateDefaultMapImageStatus);
        
        this.position(div);
        
        YAHOO.util.Event.on(closeDiv, 'click', this._removeImage, null, this);
          
        this._ddDivs.push({div:div, dd:dd});
        
        return div;
    },
    
    /**
     * Renders a text element on the map
     * 
     * @textId = id of the text element
     * @fontColor = hex code (#000000)
     * @fontSize = integer size of font
     * @fontStyle = css font style text ("font-style: NORMAL; font-weight:
     *            normal;")
     * @fontFamily = string name of font family ("Times New Roman")
     * @leftPosition = Integer position for the css left param
     * @topPosition = Integer position for the css top param
     * @text = the value of the text element
     */
    _renderTextElements : function(textId, fontColor, fontSize, fontStyle, fontFamily, leftPosition, topPosition, text)
    {      
        var div = document.createElement('div');
        div.id = textId;
        YAHOO.util.Dom.addClass(div, 'ddmsTextElement');
        
        var closeDiv = document.createElement('span');
        closeDiv.setAttribute("class", "closeButton");
        YAHOO.util.Dom.setStyle(closeDiv, "display", "none");
        closeDiv.innerHTML = "X";
        
        YAHOO.util.Dom.setStyle(div, 'cursor', 'move');
        
        var style = 'color: '+fontColor+';';
        style += 'font-size: '+fontSize+'px;';
        style += fontStyle;
        style += 'font-family: '+fontFamily+';';
        style += 'padding:7px;';
        var html = '<span style="'+style+'">'+text+'</span>';
        div.innerHTML = html;
        
        // add the close button at the top of the container
        div.insertBefore(closeDiv, div.firstChild)
        
        var dd = this.addDragDrop(div);
        dd.endDrag = Mojo.Util.bind(this, this._updateDefaultTextElementState);

        this.position(div);

        var mouseEnterHandler = function(e){
          e.target.firstChild.style.display="";
        }
        
        var mouseLeaveHandler = function(e){
          e.target.firstChild.style.display="none";
        }
        
        YAHOO.util.Event.on(div, 'mouseenter', mouseEnterHandler);
        YAHOO.util.Event.on(div, 'mouseleave', mouseLeaveHandler);
        
        YAHOO.util.Event.on(closeDiv, 'click', this._removeTextElement, null, this);
        
        this._ddDivs.push({div:div, dd:dd});
   
        if(parseInt(leftPosition) > 0 && parseInt(topPosition) > 0){
          div.style.left = leftPosition + "px";
          div.style.top = topPosition + "px";
        }
    },
    
    /**
     * Remove the image from the browser and the database (instance of MapImage)
     * 
     */
    _removeImage : function(e)
    {
        var request = new MDSS.Request({
            that: this,
            onSuccess : function(deletedMapImageId)
            {
              // remove the containing div
              var imageContainerDiv = document.getElementById(deletedMapImageId);
              imageContainerDiv.remove();
              
              // remove the entry from the ddDivs cache
              var dragNDropDivs = this.that._ddDivs;
              var toDelete;
              for(var i=0; i<dragNDropDivs.length; i++){
                if(this.that._ddDivs[i].div.id === deletedMapImageId){
                  toDelete = this.that._ddDivs[i];
            }
              }
              var index = dragNDropDivs.indexOf(toDelete);
              this.that._ddDivs.splice(index, 1);
            }
          });
        
        var imageId = e.target.parentElement.id;
        var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
        var defaultMapId = mapList.options[0].value;
        Mojo.$.dss.vector.solutions.query.SavedMap.removeMapImage(request, defaultMapId, imageId);
    },
    
    /**
     * Remove the text element from the browser and the database (instance of
     * TextElement)
     * 
     */
    _removeTextElement : function(e)
    {
        var request = new MDSS.Request({
            that: this,
            onSuccess : function(deletedTextElementId)
            {
              // remove the containing div
              var textContainerDiv = document.getElementById(deletedTextElementId);
              textContainerDiv.remove();
              
              // remove the entry from the ddDivs cache
              var dragNDropDivs = this.that._ddDivs;
              var toDelete;
              for(var i=0; i<dragNDropDivs.length; i++){
                if(this.that._ddDivs[i].div.id === deletedTextElementId){
                  toDelete = this.that._ddDivs[i];
                }
              }
              var index = dragNDropDivs.indexOf(toDelete);
              this.that._ddDivs.splice(index, 1);
            }
          });
        
        var textId = e.target.parentElement.id;
        var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
        var defaultMapId = mapList.options[0].value;
        Mojo.$.dss.vector.solutions.query.SavedMap.removeTextElement(request, defaultMapId, textId);
    },
    
    _handleExport : function(e)
    {
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }
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
          var select = document.getElementById(MDSS.MapPanel.MAP_LIST);
          select.options[0].value = defaultMap.getId();
          select.selectedIndex = 0;
                    
          this.that._loadCycleJob(defaultMap.getId());
        }
      });
      
      Mojo.$.dss.vector.solutions.query.SavedMap.loadDefaultMap(request, null);
    },
    
    _createLeftColumn : function()
    {
      /*
       * CREATE THE LAYERS PANE
       */
      var html = '';
      html += '<div id="'+MDSS.MapPanel.LEFT_COLUMN+'">';
      
      // Add add layer button
      html += MDSS.localize('Available_Layers')+':&nbsp;';
      html += '<input type="button" id="'+MDSS.MapPanel.ADD_LAYER_BUTTON+'" value="'+MDSS.localize('Add_Layer')+'">';
      html += '<br />';
      
      // layers
      html += '<div style="margin-top: 5px">';
      html += '<ul id="'+MDSS.MapPanel.AVAILABLE_LAYERS+'">';
      html += '</ul>';
      
      html += '</div>';
      html += '</div>';
    
      var left = this._leftLayout.getUnitByPosition('center');
      left.body.innerHTML = html;
      
      // Event to add a layer to the map
      YAHOO.util.Event.on(MDSS.MapPanel.ADD_LAYER_BUTTON, 'click', this._addLayer, null, this);
      
      // Make the selected layer a drag target and use it as a
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
          onSuccess : function(semanticId)
          {
            /*
             * Remove the layer from the list of layers
             */
            var li = document.getElementById(layerId);
            li.parentNode.removeChild(li);
        
            MDSS.MapPanel.toggleBaseLayer();
            
            /*
             * Remove the layer from the options in the cycle job pane
             */
            var select = document.getElementById('job-layer-id');

            for (i=0;i<select.length;  i++)
            {
               if (select.options[i].value == semanticId)
               {
                 select.remove(i);
               }
            }
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
            that._createModal(html, '', false);
            
            // Use the UL#categoryList as a delegate for
            // all edit/delete events on individual categories
            YAHOO.util.Event.on('categoryList', 'click', that._handleCategoryClick, null, that);
            YAHOO.util.Event.on('attrGeoSelect', 'change', that._attachAttrGeoChangeListener, null, that);
            YAHOO.util.Event.on('savedSearchList', 'change', that._attachSearchChangeListener, null, that);
            
            that._toggleLayerStylesAfterRender();
          }
        });
        
        this._LayerController.edit(request, layerId);
      }
    },
    
    _hideSections : function(forCategory)
    {
      var sections = ['Label', 'Geo'];
      
      var styleId;
      if(forCategory)
      {
        styleId = document.getElementById('categoryStyleId').value;
      }
      else
      {
        sections.push('Legend');
        styleId = document.getElementById('hiddenStyleId').value;
      }

      // hide the sections by default
      for(var i=0; i<sections.length; i++)
      {
        var section = sections[i];
        var toggleId = styleId+'_toggle'+section;
        YAHOO.util.Event.on(toggleId, 'click', this._toggleStyles, null, this);
        document.getElementById(toggleId+'Div').style.display = 'none';
      }
      
      // Grab the value of the containing layer's RenderAs value to know
      // if we should show or hide the point/polygon styles.
      var radios = document.getElementsByName('layer.renderAs');
      var len = radios.length;
      for(var i=0; i<len; i++)
      {
        var radio = radios[i];
        if(radio.checked)
        {
           MDSS.MapPanel.toggleGeoStyles(radio.value);
           break;
        }
      }
    },
    
    /**
     * Toggles the show/hide status of either the point or polygon styles
     * depending on the selected value of Layer.renderAs.
     */
    _toggleStylesAfterRender : function(forCategory)
    {
      // Alot of html/dom is being manipulated, so let the JS engine
      // wait until there is no more work to do before hiding sections.
      setTimeout(Mojo.Util.bind(this, this._hideSections, forCategory), this.constructor.TOGGLE_TIMEOUT);
    },
    
    /**
     * Called when a user clicks the show/hide toggle for a set of styles.
     */
    _toggleStyles : function(e)
    {
      var el = e.target;
      var divId = el.id+'Div';
      var div = document.getElementById(divId);
      if(div.style.display == 'none')
      {
        el.innerHTML = MDSS.localize('Toggle_Hide');
        div.style.display = 'block';
      }
      else
      {
        el.innerHTML = MDSS.localize('Toggle_Show');
        div.style.display = 'none';
      }
    },
    
    /**
     * Toggles the show/hide status of the entire styles div (the advanced
     * options).
     */
    _toggleLayerStylesAfterRender : function()
    {
      // Attach toggle events to the point/polygon radios
      var radios = document.getElementsByName('layer.renderAs');
      var len = radios.length;
      for(var i=0; i<len; i++)
      {
        YAHOO.util.Event.on(radios[i], 'change', MDSS.MapPanel.toggleGeoStylesHandler); 
      }
      
      this._toggleStylesAfterRender(false);
    },
    
    _requestGenerateListener : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(html)
        {
          this.that._createModalSec(html, MDSS.localize('Generate'), true, true);
        }
      });
      
      return request;
    },
    
    _generateCategoriesListener : function(params)
    {
      var params2 = Mojo.Util.collectFormValues('dss.vector.solutions.query.Layer.form.id');
      
      this._convertRefSelectParams(params2);
      
      params2['savedMapId'] = MDSS.MapPanel.getCurrentMap();
      params2['layer.isNew'] = 'true';      
      
      Mojo.Util.copy(params2, params);
    
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(html)
        {
          var el = document.getElementById(MDSS.MapPanel.CATEGORY_LIST);
          el.innerHTML = html;        
        
          this.that._destroyModalSec();
        }
      });
      
      return request;
    },
    
    /**
     * Changes listener for when a new SavedSearch is selected. The
     * SavedSearch's attribute and universal options are refreshed.
     * 
     */
    _attachSearchChangeListener : function(e)
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(attrGeos)
        {
          var mdAttributeId = '';
          var geoHierarchyId = '';
          
          var options = Mojo.Iter.map(attrGeos, function(attrGeo, index){
            
            // take the values from the first option by default
            if(index === 0)
            {
              mdAttributeId = attrGeo.getMdAttributeId();
              geoHierarchyId = attrGeo.getGeoHierarchyId();
            }
          
            var value = attrGeo.getMdAttributeId()+':'+attrGeo.getGeoHierarchyId();
            var inner = attrGeo.getGeoHierarchyDisplayLabel()+' ('+attrGeo.getAttributeDisplayLabel()+')';
            return '<option value="'+value+'">'+inner+' </option>'; 
          });
          
          var select = document.getElementById('attrGeoSelect');
          select.innerHTML = options.join('');
          
          document.getElementById('mdAttributeId').value = mdAttributeId;
          document.getElementById('geoHierarchyId').value = geoHierarchyId;
          
          this.that._resetThematicVariables();
        }
      });
      
      var savedSearchId = this._getSavedSearchId();
      Mojo.$.dss.vector.solutions.query.SavedSearch.getAttributeGeoHierarchies(request, savedSearchId);
    },
    
    /**
     * Loads the thematic variables into the select list after a new saved
     * search is selected.
     */
    _resetThematicVariables : function()
    {
      var request = new MDSS.Request({
        onSuccess : function(thematicVars)
        {
          var options = ['<option value=""></option>'];
          options = options.concat(Mojo.Iter.map(thematicVars, function(thematicVar){
            
            return '<option value="'+thematicVar.getUserAlias()+'">'
              +thematicVar.getDisplayLabel()+'</option>';
          }));
          
          document.getElementById('thematicVariables').innerHTML = options.join('');
        }
      });
      
      var savedSearchId = this._getSavedSearchId();
      Mojo.$.dss.vector.solutions.query.SavedSearch.getThematicVariables(request, savedSearchId);
    },
    
    /**
     * Returns the id of the saved search currently selected.
     */
    _getSavedSearchId : function()
    {
      var select = document.getElementById('savedSearchList');
      var savedSearchId = select.options[select.selectedIndex].value;
      return savedSearchId;
    },
    
    /**
     * Change listener to set the value of the MdAttribute and GeoHierarchy on
     * the Layer inputs.
     */
    _attachAttrGeoChangeListener : function(e)
    {
      var select = e.target;
      var option = select.options[select.selectedIndex];
      var ids = option.value.split(':');
      var mdAttributeId = ids[0];
      var geoHierarchyId = ids[1];
  
      document.getElementById('mdAttributeId').value = mdAttributeId;
      document.getElementById('geoHierarchyId').value = geoHierarchyId;
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
        
        com.runwaysdk.Facade.deleteEntity(request, categoryId);
      }
      else if(id.indexOf(MDSS.MapPanel.EDIT_SUFFIX) != -1)
      {
        var categoryId = id.replace(MDSS.MapPanel.EDIT_SUFFIX, '');
        var type = document.getElementById(categoryId+"_type").value;
        
        var request = new MDSS.Request({
          that: this,
          onSuccess : function(html)
          {
            this.that._createModalSec(html, '', true);
            this.that._toggleStylesAfterRender(true);
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
      // set an explicit false for all checkboxes if they are not checked.
      // Otherwise, the value will never change because it will be submitted
      // as null and the scraper will ignore the value.
      var keys = Mojo.Util.getKeys(params, true);
      for(var i=0; i<keys.length; i++)
      {
        var key = keys[i];
        if(key.indexOf('styles.enable_') != -1  && params[key].length === 0)
        {
          params[key].push(false);
        }
      }
      
      // Add the layer id so the relationship between Layer and
      // Category can be made in the business layer.
      params['layerId'] = document.getElementById('layerId').value;
    
      var request = new MDSS.Request({
        that : this,
        isNew : (params['category.isNew'] === 'true'),
        categoryId : params['category.componentId'],
        onSuccess : function(html)
        {
          document.getElementById(MDSS.MapPanel.CATEGORY_LIST).innerHTML = html;
          
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
          
          that._createModalSec(html, '', true);
          that._toggleStylesAfterRender(true);
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
    
    /**
     * The value of savedSearch legendColor is submitted as an array because
     * from select lists on the form. But we want to submit it as a single value
     * because they are just a reference attribute.
     */
    _convertRefSelectParams : function(params)
    {
      params['layer.savedSearch'] = params['layer.savedSearch'][0];
      params['layer.legendColor'] = params['layer.legendColor'][0];
      
      var opacityNum = parseFloat(params['layer.opacity']);
      params['layer.opacity'] = MDSS.formatNumber(opacityNum);
      
      var goodness = parseFloat(params['styles.goodnessOfFit']);
      params['styles.goodnessOfFit'] = MDSS.formatNumber(goodness);      
    },
    
    /**
     * 
     */
    _calculateQueryInfoListener : function(params)
    {
      this._convertRefSelectParams(params);
    
      params['savedMapId'] = MDSS.MapPanel.getCurrentMap();
      params['layer.isNew'] = 'true';
      
      var request = new MDSS.Request({
        onSuccess : function(html)
        {
          document.getElementById('queryInfo').innerHTML = html;
        }
      });
      
      return request;   
    },
    
    _saveLayerListener : function(params)
    {
      this._convertRefSelectParams(params);
      
      params['savedMapId'] = MDSS.MapPanel.getCurrentMap();
      
      var request = new MDSS.Request({
        isNew : (params['layer.isNew'] === 'true'),
        that : this,
        layerName : params['layer.layerName'],
        onSuccess : function(response)
        {
          var that = this.that;
          
          var resObject = JSON.parse(response);
          
          var layerId = resObject.layerId;
          var semanticId = resObject.semanticId;
          
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
          
          if(this.isNew)
          {            
            /*
             * A new layer has been added, we need to add the option to the
             * cycle pane
             */
   
            var option = document.createElement("option");
            option.text = this.layerName;
            option.value = semanticId;
            
            var select = document.getElementById('job-layer-id');
            select.appendChild(option);
          }
          else
          {
            /*
             * The layer name might have been changed, we need to update the
             * existing options of the cycle pane
             */
            var select = document.getElementById('job-layer-id');
            
            for(var i=0; i < select.options.length; i++)
            {
              if(select.options[i].value == semanticId)
              {
                select.options[i].text = this.layerName;
              }
            }

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
          var that = this.that;
        
          that._createModal(html, '', false);
          YAHOO.util.Event.on('attrGeoSelect', 'change', that._attachAttrGeoChangeListener, null, that);
          YAHOO.util.Event.on('savedSearchList', 'change', that._attachSearchChangeListener, null, that);
          
          that._toggleLayerStylesAfterRender();
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

      var exportButton = new YAHOO.util.Element(document.createElement('input'));
      exportButton.set('type', 'button');
      exportButton.set('value', MDSS.localize("Export_Map"));
      exportButton.set('id', "exportMapButton");
      exportButton.addClass('queryButton');
      exportButton.on('click', this._exportMap, {}, this);

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
      loadingDiv.appendChild(exportButton);
      loadingDiv.appendChild(deleteButton);
    
      var annotationsDiv = new YAHOO.util.Element(document.createElement('div'));
      annotationsDiv.set('id', MDSS.MapPanel.ANNOTATIONS);
      annotationsDiv.setStyle('float', 'right');
      
      // scale
      var scale = document.createElement('input');
      YAHOO.util.Dom.setAttribute(scale, 'type', 'button');
      YAHOO.util.Dom.setAttribute(scale, 'value', MDSS.localize('Scale'));
      YAHOO.util.Dom.addClass(scale, 'queryButton');
      YAHOO.util.Dom.setAttribute(scale, "id", "scaleBarBtn");
      YAHOO.util.Event.on(scale, 'click', this._toggleScale, null, this);
      scale.disabled = true;
      annotationsDiv.appendChild(scale);
      
      // north arrow
      var northArrow = document.createElement('input');
      YAHOO.util.Dom.setAttribute(northArrow, 'type', 'button');
      YAHOO.util.Dom.setAttribute(northArrow, 'value', MDSS.localize('NorthArrow'));
      YAHOO.util.Dom.addClass(northArrow, 'queryButton');
      YAHOO.util.Dom.setAttribute(northArrow, "id", "northArrowBtn");
      YAHOO.util.Event.on(northArrow, 'click', this._toggleArrow, null, this);
      northArrow.disabled = true;
      annotationsDiv.appendChild(northArrow);

      // Add Text
      var addTextBtn = document.createElement('input');
      YAHOO.util.Dom.setAttribute(addTextBtn, 'type', 'button');
      YAHOO.util.Dom.setAttribute(addTextBtn, 'value', MDSS.localize('Add_Text'));
      YAHOO.util.Dom.addClass(addTextBtn, 'queryButton');
      YAHOO.util.Event.on(addTextBtn, 'click', this._addText, null, this);
      addTextBtn.disabled = true;
      annotationsDiv.appendChild(addTextBtn);
      
      // add image
      var imageBtn = document.createElement('input');
      YAHOO.util.Dom.setAttribute(imageBtn, 'type', 'button');
      YAHOO.util.Dom.setAttribute(imageBtn, 'value', MDSS.localize('Add_Image'));
      YAHOO.util.Dom.addClass(imageBtn, 'queryButton');
      YAHOO.util.Event.on(imageBtn, 'click', this._addImage, null, this);
      imageBtn.disabled = true;
      annotationsDiv.appendChild(imageBtn);

      // Add the draw line button
      var drawLineBtn = document.createElement('input');
      YAHOO.util.Dom.setAttribute(drawLineBtn, 'type', 'button');
      YAHOO.util.Dom.setAttribute(drawLineBtn, 'value', MDSS.localize('Draw_Line'));
      YAHOO.util.Dom.addClass(drawLineBtn, 'queryButton');
      YAHOO.util.Event.on(drawLineBtn, 'click', this._drawLine, null, this);
      drawLineBtn.disabled = true;
      annotationsDiv.appendChild(drawLineBtn);
      
      var measureBtn = document.createElement('input');
      YAHOO.util.Dom.setAttribute(measureBtn, 'type', 'button');
      YAHOO.util.Dom.setAttribute(measureBtn, 'value', MDSS.localize('Measure'));
      YAHOO.util.Dom.addClass(measureBtn, 'queryButton');
      YAHOO.util.Event.on(measureBtn, 'click', this._measure, null, this);
      measureBtn.disabled = true;
      annotationsDiv.appendChild(measureBtn);

      var mapButtonDiv = new YAHOO.util.Element(document.createElement('div'));
      mapButtonDiv.setStyle('float', 'right');

      var exportShape = document.createElement('input');
      YAHOO.util.Dom.setAttribute(exportShape, 'type', 'button');
      YAHOO.util.Dom.setAttribute(exportShape, 'value', MDSS.localize('Export_Shapefile'));
      YAHOO.util.Dom.addClass(exportShape, 'queryButton');
      YAHOO.util.Event.on(exportShape, 'click', this._exportShapefile, null, this);
      mapButtonDiv.appendChild(exportShape);
  
      var refreshMapButton = new YAHOO.util.Element(document.createElement('input'));
      refreshMapButton.set('type', 'button');
      refreshMapButton.set('value', MDSS.localize('Query_Refresh'));
      refreshMapButton.set('id', MDSS.MapPanel.REFRESH_MAP_BUTTON);
      refreshMapButton.addClass('queryButton');
      refreshMapButton.on('click', this._refreshMap, null, this);
      mapButtonDiv.appendChild(refreshMapButton);
      
      var bottom = this._mapLayout.getUnitByPosition('bottom');
      var mBottom = new YAHOO.util.Element(bottom.body);
      mBottom.appendChild(loadingDiv);
      mBottom.appendChild(mapButtonDiv);
      mBottom.appendChild(annotationsDiv);
    },
    
    /**
     * Opens the image upload form
     * 
     */
    _addImage : function()
    {
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      var html = '<form action="dss.vector.solutions.query.MappingController.uploadMapImage.mojo"'
        +' method="POST" enctype="multipart/form-data" target="imageIframe">';
      html += '<input type="file" name="imageFile" />';
      html += '<input type="hidden" name="mapId" id="mapId" value="'+ defaultMapId +'" />';
      html += '<input type="submit" value="'+MDSS.localize('Submit')+'" />';
      html += '</form>';
      
      this._createModal(html, MDSS.localize('Add_Image'), true, true);
    },
    
    _showScale : function()
    {
      var scaleDiv = document.getElementById('scaleDiv');
      if(scaleDiv == null)
      {
        scaleDiv = document.createElement('div');
        scaleDiv.id = 'scaleDiv';
  
        var dd = this.addDragDrop(scaleDiv);
        dd.endDrag = Mojo.Util.bind(this, this._updateDefaultScaleBarStatus);
      }
  
      // always clear the div just to be safe
      scaleDiv.innerHTML = '';
  
      if(this._map != null)
      {
        this._map.addControl(new OpenLayers.Control.ScaleLine({'div':scaleDiv}));
      }
  
      this.position(scaleDiv);
      
      this._scale = true;
    },
  
    _hideScale : function()
    {
      var scaleDiv = document.getElementById('scaleDiv');
      if(scaleDiv)
      {
        YAHOO.util.Dom.setStyle(scaleDiv, 'display', 'none');
      }
      
      this._scale = false;
    },
  
    _showArrow : function()
    {
      var arrowDiv = document.getElementById('arrowDiv');
      if(arrowDiv == null)
      {
        arrowDiv = document.createElement('div');
        arrowDiv.id = 'arrowDiv';
        arrowDiv.innerHTML = '<img src="imgs/northArrow.png" style="width: 50px; height: 50px;" />';
  
        var dd = this.addDragDrop(arrowDiv);
        dd.endDrag = Mojo.Util.bind(this, this._updateDefaultNorthArrowStatus);
      }
  
      this.position(arrowDiv);
      
      this._northArrow = true;
    },
    
    _hideArrow : function()
    {
      var arrowDiv = document.getElementById('arrowDiv');
      if(arrowDiv)
      {
        YAHOO.util.Dom.setStyle(arrowDiv, 'display', 'none');
      }
      
      this._northArrow = false;
    },
    
    _toggleScale : function(e)
    {
      var btn = e.target;

      if(this._scale)
      {
        this._hideScale();
        this._markOff(btn);
      }
      else
      {
        this._showScale();
        this._markOn(btn);
      }
      
      // Persist the change to the default map, so that it is rendered on the exported map and on map refresh
      this._updateDefaultScaleBarStatus();
    },    
    
    _toggleArrow : function(e)
    {
      var btn = e.target;

      if(this._northArrow)
      {
        this._hideArrow();
        this._markOff(btn);
      }
      else
      {
        this._showArrow();
        this._markOn(btn);
      }
      
      // Persist the change to the default map, so that it is rendered on the exported map and on map refresh
      this._updateDefaultNorthArrowStatus();
    },
        
    _exportShapefile : function()
    {
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      document.getElementById('export_namedMapId').value = mapList.value;
      
      var form = document.getElementById('exportShapefile');
      form.submit();
    },
    
    _drawLine : function(e)
    {
      var btn = e.target;
    
      if(this._drawLineControl.active)
      {
        this._drawLineControl.deactivate();
        this._drawLineControl.layer.display(false);
        this._markOff(btn);
      }
      else
      {
        this._drawLineControl.activate();
        this._drawLineControl.layer.display(true);
        this._markOn(btn);
      }
    },
    
    _measure : function(e)
    {
      var btn = e.target;
    
      if(this._measureControl.active)
      {
        this._measureControl.deactivate();
        this._markOff(btn);
      }
      else
      {
        this._measureControl.activate();
        this._markOn(btn);
      }    
    },
    
    _saveMap : function()
    {
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      
      if(mapList.selectedIndex == 0)
      {
        // Save as a new map
        this._saveMapAs();
      }
      else
      {
        var mapId = mapList.value;
        var defaultMapId = mapList.options[0].value;
        
        var request = new MDSS.Request({
          that : this,
          onSuccess : function(query)
          {
            // repopulate the map list with the layers
            this.that._setLayers(query.getResultSet());
            
            // Persist map elements status (active or inactive) and well as
            // location
//            this.that._updateNorthArrowStatus(mapId); 
//            this.that._updateScaleBarStatus(mapId);
//            this.that._updateLegendStatus(mapId);
            this.that._updateMapState(mapId);
            
            this.that._saveCycleJob();
          }
        });        
        
        Mojo.$.dss.vector.solutions.query.SavedMap.createFromExisting(request, mapId, defaultMapId);
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
          
          // Persist map elements status (active or inactive) and well as
          // location
//          that._updateNorthArrowStatus(mapId);  
//          that._updateScaleBarStatus(mapId); 
//          that._updateLegendStatus(mapId);
          that._updateMapState(mapId);
          
          that._destroyModal();
          
          /*
           * Save the cycle job
           */
          that._saveCycleJob(mapId);
        }
      });

      var existingMapId = MDSS.MapPanel.getCurrentMap();
      var select = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var activeMapId = select.value;
      
      // Creates the new instance of SavedMap when saving a new map
      var map = new Mojo.$.dss.vector.solutions.query.SavedMap();
      map.setMapName(params['dto.mapName']);
      map.createFromExisting(request, existingMapId);
      
    },
    
    _mapCancelListener : function(params)
    {
      this._destroyModal();
    },
    
    /**
     * Persist north arrow status (active or inactive) and well as location
     * 
     * @mapId = mapId of the map to persist north arrow status
     */
    _updateNorthArrowStatus : function(mapId)
    {
      if(typeof arrowDiv !== "undefined"){
          var request = new MDSS.Request({
              that : this,
              onSuccess : function(query)
              {
                // No callback necessary
              }
            });  
          
          // Set the location of the north arrow.
          // if the north arrow is not visible the left/top values will be set
          // to 0
          var northArrowIsActive = false;
          if(arrowDiv.style.display !== 'none'){
            northArrowIsActive = true;
          }
          
          var style = window.getComputedStyle(arrowDiv);
          var top = style.getPropertyValue('top');
          var left = style.getPropertyValue('left');
          
          Mojo.$.dss.vector.solutions.query.SavedMap.updateNorthArrow(request, mapId, left, top, northArrowIsActive);
      }
    },
        
    _updateDefaultNorthArrowStatus : function ()
    {
      // We need to update the north arrow status of the default map
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      this._updateNorthArrowStatus(defaultMapId);  
    },

    
    /**
     * Persist scale bar status (active or inactive) and well as location
     * 
     * @mapId = mapId of the map to persist scale bar status
     */
    _updateScaleBarStatus : function(mapId)
    {
      if(typeof scaleDiv !== "undefined"){
          var request = new MDSS.Request({
              that : this,
              onSuccess : function(query)
              {
                // No callback necessary
              }
            });  
          
          // Set the location of the north arrow.
          // if the north arrow is not visible the left/top values will be set
          // to 0
          var scaleBarIsActive = false;
          if(scaleDiv.style.display !== 'none'){
            scaleBarIsActive = true;
          }
          
          var style = window.getComputedStyle(scaleDiv);
          var top = style.getPropertyValue('top');
          var left = style.getPropertyValue('left');
          
          Mojo.$.dss.vector.solutions.query.SavedMap.updateScaleBar(request, mapId, left, top, scaleBarIsActive);
      }
    },
    
    _updateDefaultScaleBarStatus : function ()
    {
      // We need to update the scale bar status of the default map
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      this._updateScaleBarStatus(defaultMapId);  
    },    
    
    /**
     * Persist scale bar status (active or inactive) and well as location
     * 
     * @mapId = mapId of the map to persist all legends status as JSON
     */
    _updateLegendStatus : function(mapId)
    {
      
        var activeLegends = document.getElementsByClassName("ddmsLegend");
        
        var legendsJSONArr = [];
        for(var i=0; i<activeLegends.length; i++){
          var legend = activeLegends[i];
          var legendId = legend.id;
          
          var style = window.getComputedStyle(legend);
          var top = style.getPropertyValue('top');
          var left = style.getPropertyValue('left');
          
          var legendInfo = { "legendId":legendId, "top":top, "left":left };
          legendsJSONArr.push(legendInfo);
        }
        
        var legendsJSON = { "legends" : legendsJSONArr };
        
        var request = new MDSS.Request({
            that : this,
            onSuccess : function(query)
            {
              // No callback necessary
            }
          });  
        
      
        Mojo.$.dss.vector.solutions.query.SavedMap.updateLegendLocations(request, mapId, legendsJSON);
    },
        
    _updateDefaultLegendStatus : function ()
    {
      // We need to update the image locations of the default map
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      this._updateLegendStatus(defaultMapId);  
    },    

    
    /**
     * Persist map image status (active or inactive) as well as location
     * 
     * @mapId = mapId of the map to persist all legends status as JSON
     */
    _updateMapImageStatus : function(mapId)
    {
    	// COMMENTED FOR TESTING AFTER BREAKING OUT GETACTIVEMAPIMAGES(). DELETE
      // AFTER TESTS PASS
// var activeMapImages = document.getElementsByClassName("mapImage");
//        
// var imagesJSONArr = [];
// for(var i=0; i<activeMapImages.length; i++){
// var img = activeMapImages[i];
// var imgId = img.id;
//        	
// var style = window.getComputedStyle(img);
// var top = style.getPropertyValue('top');
// var left = style.getPropertyValue('left');
//        	
// var imgInfo = { "imageId":imgId, "top":top, "left":left };
// imagesJSONArr.push(imgInfo);
// }
//        
// var imagesJSON = { "images" : imagesJSONArr };
    	
    	var imagesJSON = this._getActiveMapImages();
        
        var request = new MDSS.Request({
            that : this,
            onSuccess : function(query)
            {
              // No callback necessary
            }
          });  	
        
        Mojo.$.dss.vector.solutions.query.SavedMap.updateImageLocations(request, mapId, imagesJSON);
    },
    
    _updateDefaultMapImageStatus : function ()
    {
      // We need to update the image locations of the default map
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      this._updateMapImageStatus(defaultMapId);  
    },  
    
    
    
    /**
     * Build a json object containing all visible images from the map. This is
     * for getting visible map images and has no notion of saved images
     * 
     * @return
     */
    _getActiveMapImages : function()
    {
        var activeMapImages = document.getElementsByClassName("mapImage");
        
        var imagesJSONArr = [];
        for(var i=0; i<activeMapImages.length; i++){
          var img = activeMapImages[i];
          var imgId = img.id;
          
          var style = window.getComputedStyle(img);
          var top = style.getPropertyValue('top');
          var left = style.getPropertyValue('left');
          
          var imgInfo = { "imageId":imgId, "top":top, "left":left };
          imagesJSONArr.push(imgInfo);
        }
        
        var imagesJSON = { "images" : imagesJSONArr };
        
        return imagesJSON;
    },
    
    
    /**
     * Persist map image status (active or inactive) as well as location
     * 
     * @mapId = mapId of the map to persist all legends status as JSON
     */
    _updateMapState : function(mapId)
    {
      // if trying to save an empty default map the _map object won't exist yet.
      if(this._map){
        var mapBounds ={};
        mapBounds.left = this._map.getExtent().left;
        mapBounds.bottom = this._map.getExtent().bottom;
        mapBounds.right = this._map.getExtent().right;
        mapBounds.top = this._map.getExtent().top;
        
        
	    	var zoomLevel = this._map.getZoom();
        
          var request = new MDSS.Request({
              that : this,
              onSuccess : function(query)
              {
                // No callback necessary
              }
            });  
          
          Mojo.$.dss.vector.solutions.query.SavedMap.updateMapState(request, mapId, zoomLevel, mapBounds);
      }
    },    
    
    _updateDefaultMapState : function ()
    {
      // We need to update the zoom state of the default map
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      this._updateMapState(defaultMapId);  
    },        
    
    
    /**
     * Persist user added text element positions
     * 
     * @mapId = mapId of the map to persist all
     */
    _updateTextElementState : function(mapId)
    {
      
    	// COMMENTED FOR TESTING AFTER BREAKING OUT GETACTIVETEXTELEMENTS().
      // DELETE AFTER TESTS PASS
// var activeText = document.getElementsByClassName("ddmsTextElement");
//    	
// var textJSONArr = [];
// for(var i=0; i<activeText.length; i++){
// var text = activeText[i];
// var textId = text.id;
//        	
// var style = window.getComputedStyle(text);
// var top = style.getPropertyValue('top');
// var left = style.getPropertyValue('left');
//        	
// var textInfo = { "textId":textId, "top":top, "left":left };
// textJSONArr.push(textInfo);
// }
//        
// var textJSON = { "textElements" : textJSONArr };
    	
    	var textJSON = this._getActiveTextElements();
    	
        var request = new MDSS.Request({
            that : this,
            onSuccess : function(query)
            {
              // No callback necessary
            }
          });  
        
        Mojo.$.dss.vector.solutions.query.SavedMap.updateTextElements(request, mapId, textJSON);
    },
    
    
    _updateDefaultTextElementState : function ()
    {
      // We need to update the text locations of the default map
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      var defaultMapId = mapList.options[0].value;
      
      this._updateTextElementState(defaultMapId);  
    },                
    
    /**
     * Build a json object containing all visible user defined text elements
     * from the map. This is for getting visible text elements and has no notion
     * of saved text elements
     * 
     * @return
     */
    _getActiveTextElements : function()
    {
      var activeText = document.getElementsByClassName("ddmsTextElement");
      
        var textJSONArr = [];
        for(var i=0; i<activeText.length; i++){
          var text = activeText[i];
          var textId = text.id;
          
          var style = window.getComputedStyle(text);
          var top = style.getPropertyValue('top');
          var left = style.getPropertyValue('left');
          
          var textInfo = { "textId":textId, "top":top, "left":left };
          textJSONArr.push(textInfo);
        }
        
        var textJSON = { "textElements" : textJSONArr };
      
        return textJSON;
    },
    
    
    _doDeleteMap : function(mapId)
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(){

          // remove the option node for the deleted map
          var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
          var option = mapList.options[mapList.selectedIndex];
          mapList.removeChild(option);
          mapList.selectedIndex = 0;
          
          this.that._loadDefaultMap();
        }
      });
        
      com.runwaysdk.Facade.deleteEntity(request, mapId);
    },
    
    _deleteMap : function()
    {
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      if(mapList.selectedIndex !== 0)
      {
        var option = mapList.options[mapList.selectedIndex];
        var mapId = option.value;
        
        var doDel = Mojo.Util.bind(this, this._doDeleteMap, mapId);
        MDSS.confirmModal(MDSS.localize('Confirm_Delete_Map'), doDel, function(){});
      }
    },
    
    _exportMapModal : function(mapId, mapBounds, mapSize, outFileName)
    {
    	
    	var html = '';
    	html += '<form id="exportMap" target="mapExportIframe" method="POST" action="dss.vector.solutions.query.MappingController.mapExport.mojo">';
    	html += '<input type="hidden" id="mapId" name="mapId" value=\'' + mapId + '\' />';
    	html += '<input type="hidden" id="mapBounds" name="mapBounds" value=\'' + mapBounds+ '\' />';
    	html += '<input type="hidden" id="mapSize" name="mapSize" value=\'' + mapSize + '\' />';    	
    	html += '<input type="hidden" id="outFileName" name="outFileName" value=\'' + outFileName + '\' />';    	    	
    	html += '<dl>';
    	html += '	<dt>'+ MDSS.localize('File_Format') +'	</dt>';
    	html += '	<dd>';
    	html += '		<select name="outFileFormat">';
    	html += '			<option value="png">' + MDSS.localize('PNG') + '</option>';
    	html += '			<option value="jpg">' + MDSS.localize('JPG') + '</option>';
    	html += '			<option value="bmp">' + MDSS.localize('BMP') + '</option>';
    	html += '			<option value="gif">' + MDSS.localize('GIF') + '</option>';
    	html += '		</select>';
    	html += '	</dd>';
    	html += '</dl>';

    	html += '<input type="submit" value="' + MDSS.localize("Export_Map") + '" />';
    	html += '</form>';
    	
    	this._createModal(html, MDSS.localize("Export_Map"), true, true);
    },
    
    _exportMap : function()
    {
    	var outFileName = "test_image";
    	var outFilePath = "/home/jlewis/development/scratch/";
    	var outFileFormat = "png";  // gif, png, jpg, bmp
    	
    	var mapBounds = {};
    	var mapExtent = this._map.getExtent();
    	mapBounds.left = mapExtent.left;
    	mapBounds.bottom = mapExtent.bottom;
    	mapBounds.right = mapExtent.right;
    	mapBounds.top = mapExtent.top;
    	mapBoundsStr = JSON.stringify(mapBounds);
    	
    	var mapSize = {};
    	mapSize.width = document.getElementById("mapContainer").offsetWidth;
    	mapSize.height = document.getElementById("mapContainer").offsetHeight;
    	mapSizeStr = JSON.stringify(mapSize);
    	
    	var select = document.getElementById(MDSS.MapPanel.MAP_LIST);
        var mapId = select.value;
        
        var mapId = select.value;
        var defaultMapId = select.options[0].value;
        
// var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
// document.getElementById('mapId').value = defaultMapId;
// document.getElementById('outFileName').value = outFileName;
// document.getElementById('outFileFormat').value = outFileFormat;
// document.getElementById('mapBounds').value = mapBoundsStr;
// document.getElementById('mapSize').value = mapSizeStr;
        
        this._exportMapModal(defaultMapId, mapBoundsStr, mapSizeStr, select.text);
        
// var form = document.getElementById('exportMap');
// form.submit();
    },
    
    _addSavedMap : function(mapId, mapName)
    {
      var option = document.createElement('option');
      option.value = mapId;
      option.innerHTML = mapName;
      
      var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
      mapList.appendChild(option);
      mapList.selectedIndex = mapList.options.length-1;
    },
    
    _clearLayers : function()
    {
      // clear the old layers
      document.getElementById(MDSS.MapPanel.AVAILABLE_LAYERS).innerHTML = '';
    },
    
    _loadMap : function(e)
    {
      var select = document.getElementById(MDSS.MapPanel.MAP_LIST);
      
      if(select.selectedIndex == 0)
      {
        this._loadDefaultMap(); // this will reset the current default map and
                                // set the current map to the default
      }
      else
      {
        var mapId = select.value;
        var defaultMapId = select.options[0].value;

        var request = new MDSS.Request({
          that : this,
          onSuccess : function(query)
          {
            // repopulate the map list with the layers
            this.that._setLayers(query.getResultSet());
            
            this.that._loadCycleJob(mapId);
          }
        });

        Mojo.$.dss.vector.solutions.query.SavedMap.createFromExisting(request, defaultMapId, mapId);
      }
    },
    
    _loadCycleJob : function(mapId)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess: function(html)
        {
          this.that._setJobHtml(html);
        }
      });
  
      Mojo.$.dss.vector.solutions.query.CycleJobController.getJobForMap(request, mapId);
    },
    
    _setJobHtml : function(html)
    {
      var left = this._leftLayout.getUnitByPosition('bottom');
      left.body.innerHTML = html;
    },
    
    _saveCycleJob : function(mapId)
    {
      var params = Mojo.Util.collectFormValues('job-form');
      
      if(params["view.layerId"] != null && params["view.layerId"].length > 0)
      {
        params["view.layerId"] = params["view.layerId"][0];        
      }
      else
      {
        params["view.layerId"] = '';
      }
      
      if(mapId != null)
      {
        params["view.savedMap"] = mapId;
      }
      
      var request = new MDSS.Request({
        that: this,
        onSuccess: function(html)
        {
          this.that._setJobHtml(html);
        }
      });
      
      Mojo.$.dss.vector.solutions.query.CycleJobController.saveMap(request, params);
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
     * Creates the map based on the current state of the layers.
     */
    _refreshMap : function()
    {
      var mapId = MDSS.MapPanel.getCurrentMap();

      var request = new MDSS.Request({
        that : this,
        mapId : mapId,
        onSuccess : function(retJSON)
        {
          var retObj = Mojo.Util.getObject(retJSON);
          var info = Mojo.$.com.runwaysdk.DTOUtil.convertToType(retObj.information);
          
          if(info.length > 0)
          {
            var m = '';
            for(var i=0, len=info.length; i<len; i++)
            {
              m += info[i].getMessage()+"<br />";
            }
            new MDSS.ErrorModal(m);
          }
        
          var that = this.that;
        
          var mapData = Mojo.Util.getObject(retObj.returnValue);
        
          var geoServerPath = mapData.geoserverURL;
          
          var layers = mapData.layers;
      
          // clear any previous map
          if(that._map != null)
          {
            that._map.destroy();
          }
      
          // pink tile avoidance
          OpenLayers.IMAGE_RELOAD_ATTEMPTS = 5;
          // make OL compute scale according to WMS spec
          OpenLayers.DOTS_PER_INCH = 25.4 / 0.28;
          
          // Restrict the bounding box to that of the base layer, and zoom
          // out entirely except for a couple of levels.
          var bbox = mapData.bbox;
          var bounds;
          if(bbox.length === 2)
          {
            bounds = new OpenLayers.Bounds();
            bounds.extend(new OpenLayers.Geometry.Point(bbox[0], bbox[1]));
          }
          else
          {
            bounds = new OpenLayers.Bounds(bbox[0], bbox[1], bbox[2], bbox[3]);
          }
          
          var options = {
              controls: [],
              projection: "EPSG:4326",
              units: 'degrees',
              resolutions: [
                            1.40625,0.703125,
                            0.3515625,0.17578125, 
                            0.1142578125, 
                            0.087890625, 
                            0.05712890625, 
                            // all resolutions below are 1.3 x increase/decrease
                            // from each other
                            0.04922315513,
                            0.03786396549,
                            0.0291261273,
                            0.02240471331,
                            0.01723439486,
                            0.01325722682,
                            0.01019786679,
                            0.00784451292,
                            0.00603424071,
                            0.00464172363,
                            0.00357055664,
                            0.00274658203125,
                            // additions to allow for closer zoom levels
                            0.002112755,
                            0.001625196,
                            0.001250151,
                            0.000961655,
                            0.000739734,
                            0.000569026,
                            0.000437713,
                            0.000336702,
                            0.000259002,
                            0.000199232,
                            0.000153255,
                            0.000117889,
                            0.000090684,
                            0.000069757,
                            0.000053659,
                            0.000041276
// 0.000031751
// 0.000024424
                            // end of 1/3 intervals
                            ],
              minResolution: "auto",
              maxResolution: "auto"
// numZoomLevels : 20
          };
      
      
          that._map = new OpenLayers.Map(MDSS.MapPanel.MAP_CONTAINER, options);

          var mapLayers = [];

          for(var i=0; i<layers.length; i++)
          {
              var layer = layers[i];
              var extraLayer = new OpenLayers.Layer.WMS("", geoServerPath+"/wms",
              {
                  srs: 'EPSG:4326',
                  layers: layer.view,
                  styles: '',
                  sld: mapData.sldURL+layer.sld,
                  format: OpenLayers.Format.SVG,
                  transparent: (i !== 0), // base layer is false
                  isBaseLayer : (i === 0) // base layer is true
              },
              {
                buffer: 0,
                singleTile : true,
                opacity: layer.opacity
              }
            );
      
            mapLayers.push(extraLayer);
          }
          // ... map.addLayers(mapLayers)
          
          var lineLayer = new OpenLayers.Layer.Vector("Line Layer");
          that._drawLineControl = new OpenLayers.Control.DrawFeature(lineLayer, OpenLayers.Handler.Path);
          
          mapLayers.push(lineLayer);
          
          that._map.addLayers(mapLayers);

          // build up all controls
          that._map.addControl(new OpenLayers.Control.PanZoomBar({
              position: new OpenLayers.Pixel(2, 15)
          }));
          that._map.addControl(new OpenLayers.Control.Navigation({zoomWheelEnabled:false}));
          that._map.addControl(new OpenLayers.Control.MousePosition());
          that._map.addControl(that._drawLineControl);
          
          var sketchSymbolizers = {
              "Line": {
                  strokeWidth: 3,
                  strokeOpacity: 1,
                  strokeColor: "#000000"
              }
          };
          var style = new OpenLayers.Style();
          style.addRules([
              new OpenLayers.Rule({symbolizer: sketchSymbolizers})
          ]);
          var styleMap = new OpenLayers.StyleMap({"default": style});
          
          that._measureControl = new OpenLayers.Control.Measure(
                  OpenLayers.Handler.Path, {
                      persist: true,
                      handlerOptions: {
                          layerOptions: {styleMap: styleMap}
                      }
                  }
          );
          
          var handleMeasurements = Mojo.Util.bind(that, that._handleMeasurements);
          
          that._measureControl.events.on({
                  "measure": handleMeasurements
          });
          that._map.addControl(that._measureControl);

          that._map.zoomToExtent(bounds);  
          
          // OpenLayers doesn't always fit the saved map bounds to the zoom
          // level that was saved
          // so we will ensure the correct zoom level is used
          if(mapData.zoomLevel){
            that._map.zoomTo(mapData.zoomLevel);
          }
          
          that._addLegends(layers);
          
          that._enableAnnotations();
          
           // render saved images
          var savedImages = mapData.savedImages;
          
          for(var i=0; i<savedImages.length; i++){
            var savedImage = savedImages[i];
            var leftPosition = savedImage.imageXPosition;
            var topPosition = savedImage.imageYPosition;
           
            var div = that._renderImages(savedImage.filePath, savedImage.imageId);
            
            if(parseInt(leftPosition) > 0 && parseInt(topPosition) > 0){
              div.style.left = leftPosition + "px";
              div.style.top = topPosition + "px";
            }
          }
          
          // render saved text elements
          var textElements = mapData.savedTextElements;
          for(var i=0; i<textElements.length; i++){
            var textElement = textElements[i];
            var leftPosition = textElement.textXPosition;
            var topPosition = textElement.textYPosition;
         
            var text = textElement.textValue;
            if(text.length > 0)
            {
              var fontColor = textElement.fontColor;
              var fontFamily = textElement.fontFamily;
              var fontSize = textElement.fontSize;
              var fontStyle = textElement.fontStyle;
              var textId = textElement.textId;
              
              // render the text element to the map
              that._renderTextElements(textId, fontColor, fontSize, fontStyle, fontFamily, leftPosition, topPosition, text);               
            }
          }
          
          var request = new MDSS.Request({
            that : this,
            onSuccess : function(map)
            {
              if(map.getNorthArrowActive()){
                var leftPosition = map.getNorthArrowXPosition().toString() + "px";
                var topPosition = map.getNorthArrowYPosition().toString() + "px";
                
                that._showArrow();
                arrowDiv.style.left = leftPosition;
                arrowDiv.style.top = topPosition;
                
                that._markOn(document.getElementById("northArrowBtn"));  
              }
              
              if(map.getScaleBarActive()){
                var leftPosition = map.getScaleBarXPosition().toString() + "px";
                var topPosition = map.getScaleBarYPosition().toString() + "px";
                
                that._showScale();
                scaleDiv.style.left = leftPosition;
                scaleDiv.style.top = topPosition;
                
                that._markOn(document.getElementById("scaleBarBtn"));  
              }
            }
          });  
          
          Mojo.$.dss.vector.solutions.query.SavedMap.get(request, mapId);         
        } 
      });
      
      this._removeDragNDropDivs();
      this._disableAnnotations();
      
      this._MappingController.refreshMap(request, mapId, '');
    },
    
    _handleMeasurements : function(event)
    {
      var geometry = event.geometry;
      var units = event.units;
      var order = event.order;
      var measure = event.measure;
      var out = '<div style="text-align:center; margin-top: 30%;"><h2>';
      if(order == 1) {
          out += measure.toFixed(3) + " " + units;
      } else {
          out += measure.toFixed(3) + " " + units + "<sup>2</" + "sup>";
      }
      out += '</h2></div>';
      
      this._createModal(out, MDSS.localize('Measure'), true, true);
    },
    
    _enableAnnotations : function()
    {
      var div = document.getElementById(MDSS.MapPanel.ANNOTATIONS);
      Mojo.Iter.forEach(div.childNodes, function(child){
        if(child.nodeName === 'INPUT')
        {
          child.disabled = false;
        }
      });
    },
    
    _markOn : function(input)
    {
      input.style.color = 'blue';
    },
    
    _markOff : function(input)
    {
      input.style.color = 'black';
    },
    
    _disableAnnotations : function()
    {
      var div = document.getElementById(MDSS.MapPanel.ANNOTATIONS);
      Mojo.Iter.forEach(div.childNodes, function(child){
        if(child.nodeName === 'INPUT')
        {
          this._markOff(child);
          child.disabled = true;
        }
      }, this);
      
      this._hideArrow();
      this._hideScale();
    },
    
    _removeDragNDropDivs : function()
    {
      // remove the old legend divs
      for(var i=0; i<this._ddDivs.length; i++)
      {
        var obj = this._ddDivs[i];
        var dd = obj.dd;
        var div = obj.div;
        
        dd.unsubscribeAll();
        
        div.parentNode.removeChild(div);
      }
      this._ddDivs = [];
    },
    
    _addText : function()
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(html){
          this.that._createModal(html, MDSS.localize('Add_Text'), true, true);
        }
      });
      
      this._MappingController.addText(request);
    },
    
    _setTextListener : function(params)
    {
      var text = Mojo.Util.trim(params['freeText.customText']);
      if(text.length > 0)
      {
         var fontColor = params['freeText.textFontFill'];
         var fontFamily = params['freeText.textFontFamily'][0];
         var fontSize = params['freeText.textFontSize'];
         var fontStyle = this._getFontStyle(params['freeText.textFontStyles'][0]);
         var leftPosition = 0;
         var topPosition = 0;
        
         var textId = "text_" + Mojo.Util.generateId();
        
        var mapList = document.getElementById(MDSS.MapPanel.MAP_LIST);
        var defaultMapId = mapList.options[0].value;
          
        // render the text element to the map
        this._renderTextElements(textId, fontColor, fontSize, fontStyle, fontFamily, leftPosition, topPosition, text);
         
         
        var request = new MDSS.Request({
          that : this,
          onSuccess : function(){
             // no success callback
          },
          onFailure : function (e) {
            alert(e.getLocalizedMessage());
          }
         });
         
         Mojo.$.dss.vector.solutions.query.SavedMap.addTextElement(request, defaultMapId, '', text, fontColor, fontFamily, fontSize, fontStyle, textId);
      }
      
      this._destroyModal();
    },
    
    _getFontStyle : function(fontStyle)
    {
      if(fontStyle == 'bold')
      {
        return 'font-style: normal; font-weight: '+fontStyle+';';
      }
      else
      {
        return 'font-style: '+fontStyle+'; font-weight: normal;';
      }                
    },        
    
    _addLegends : function(layers)
    {
      for(var i=0; i<layers.length; i++)
      {
        var legend = layers[i].legend;
        if(legend != null)
        {
          var div = document.createElement('div');
          div.id = "legend_" + layers[i].id;
          div.setAttribute("class", "ddmsLegend");
          var css = 'display: none; background-color: #ffffff;';
          css += 'font-size: '+legend.fontSize+'px; font-family: '+legend.fontFamily+'; ';
          css += this._getFontStyle(legend.fontStyle) + ' color: '+legend.fontFill+';';
          
          div.style.cssText = css;
          
          var bg = legend.showLegendBorder ? '' : 'border: 2px solid white !important';
          var table = '<table class="legend" style="'+bg+'">';
          
          var titleSpan = '<span style="font-size: '+legend.fontTitleSize+'px; font-family: '
            +legend.fontTitleFamily+'; ' + this._getFontStyle(legend.fontTitleStyle) + ' color: '
            +legend.fontTitleFill+';">'+legend.title+'</span>';
          
          if(legend.createRawLegend)
          {
            table += '<tr><td>'+titleSpan+'</td><td><div class="colorPickerValue legendColor" style="background-color: '+legend.color+'">&nbsp;</div></td></tr>';
          }
          else
          {
          
            table += '<tr><td colspan="2">'+titleSpan+'</td></tr>';
            var categories = legend.categories;
            for(var j=0; j<categories.length; j++)
            {
              var category = categories[j];
            
              var display;
              if(category.exact != null)
              {
                display = category.exact;
              }
              else
              {
                display = category.lower + ' < ' + category.upper;
              }
              
              table += '<tr><td>'+display+'</td>';
              table += '<td><div class="colorPickerValue legendColor" style="background-color: '+category.color+'">&nbsp;</div></td></tr>';
            }
          }
          
          table += '</table>';

          div.innerHTML = table;
          var dd = this.addDragDrop(div);
          dd.endDrag = Mojo.Util.bind(this, this._updateDefaultLegendStatus);

          this.position(div);
                    
          // Assign saved legend position after creating the legend div
          if(legend.legendXPosition > 0 && legend.legendYPosition > 0){
            div.style.left = legend.legendXPosition+"px";
            div.style.top = legend.legendYPosition+"px";
          }
          
          this._ddDivs.push({div:div, dd:dd});
        }
      }
      
      this._updateDefaultLegendStatus();
    },
    
    _destroyModal : function()
    {
      // Cleanup event handler for the category list delegate
      var categoryList = document.getElementById('categoryList');
      if(categoryList)
      {
        YAHOO.util.Event.removeListener(categoryList, 'click');
      }
      
      var attrGeoSel = document.getElementById('attrGeoSelect');
      if(attrGeoSel)
      {
        YAHOO.util.Event.removeListener(categoryList, 'change');
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
    
    _createModalInternal : function(html, title, useContentModal, closeIt)
    {
      var executable = MDSS.util.extractScripts(html);
      var html = MDSS.util.removeScripts(html);
  
      var modal = new YAHOO.widget.Panel("modal_"+MDSS.MapPanel.zIndex, {
        width: "400px",
        height: "400px",
        fixedcenter:true,
        close: closeIt || false,
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
      
      if(useContentModal){
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
      }
      
      contentDiv.innerHTML = html;
      outer.appendChild(contentDiv);
  
      modal.setBody(outer);
      modal.render(document.body);
  
      eval(executable);
  
      modal.bringToTop();
      
      return modal;
    },
    
    _createModalSec : function(html, title, useContentModal, closeIt)
    {
      this._secondaryModal = this._createModalInternal(html, title, useContentModal, closeIt);
      var that = this;
      this._secondaryModal.subscribe('hide', function(){
        setTimeout(function(){
          that._destroyModalSec();
        }, 15);
      });
    },
    
    /**
     * Creates a modal with the given HTML as its body and the given title as
     * the modal title, wrapped in an H3.
     */
    _createModal : function(html, title, useContentModal, closeIt)
    {
      this._currentModal = this._createModalInternal(html, title, useContentModal, closeIt);
      var that = this;
      this._currentModal.subscribe('hide', function(){
        setTimeout(function(){
          that._destroyModal();
        }, 15);
      });
    },
    
    position : function(div)
    {
      YAHOO.util.Dom.setStyle(div, 'display', 'block');
  
      var mapDiv = document.getElementById('mapContainer');
  
      var xy = YAHOO.util.Dom.getXY(mapDiv);
  
      var mWidth = parseInt(YAHOO.util.Dom.getStyle(mapDiv, 'width'), 10);
      var mHeight = parseInt(YAHOO.util.Dom.getStyle(mapDiv, 'height'), 10);
  
      var dWidth = parseInt(YAHOO.util.Dom.getStyle(div, 'width'), 10);
      var dHeight = parseInt(YAHOO.util.Dom.getStyle(div, 'height'), 10);

  
      var offsets = this._offsets[this._offsetInd];
      var leftO = offsets[0];
      var topO = offsets[1];
      
      if(this._offsetInd == this._offsets.length-1)
      {
        this._offsetInd = 0;
      }
      else
      {
        this._offsetInd++;
      }

      var center = [xy[0] + (mWidth/2) - (dWidth/2) + leftO,
        xy[1] + (mHeight/2) - (dHeight/2) + topO];
  
      YAHOO.util.Dom.setXY(div, center);
  
      var region = YAHOO.util.Region.getRegion(mapDiv);
      xy = YAHOO.util.Dom.getXY(div);
  
      // Set left to x minus left
      var left = xy[0] - region.left;
  
      // Set right to right minus x minus width
      var right = region.right - xy[0] - dWidth;
  
      // Set top to y minus top
      var top = xy[1] - region.top;
  
      // Set bottom to bottom minus y minus height
      var bottom = region.bottom - xy[1] - dHeight;
  
      // Set the constraints based on the above calculations
      var dd = YAHOO.util.DragDropMgr.getDDById(div.id);
      // dd.setXConstraint(left, right);
      // dd.setYConstraint(top, bottom);
    },
  
    addDragDrop : function(div, handler)
    {
      var mapDiv = document.getElementById('mapPanel');
      var region = YAHOO.util.Region.getRegion(mapDiv);
  
      YAHOO.util.Dom.setStyle(div, 'position', 'absolute');
      YAHOO.util.Dom.setStyle(div, 'display', 'block');
  
      mapDiv.appendChild(div);
  
      var dd = new YAHOO.util.DD(div, {
        dragOnly : true
      });
      
      return dd;
    }

  },
  
  Static : {

    attachRotationCanvas : function(attribute)
    {
      var canvas = document.getElementById(attribute+'Canvas');
      var context = canvas.getContext('2d');
      
      var value = parseInt(document.getElementById(attribute).value);
      
      canvas.setAttribute('width', MDSS.rotationCircle.width);
      canvas.setAttribute('height', MDSS.rotationCircle.height);
      
      context.translate(canvas.width/2, canvas.height/2);
      context.rotate(value * Math.PI / 180);
      context.translate(-1*(canvas.width/2), -1*(canvas.height/2));
      context.drawImage(MDSS.rotationCircle, 0, 0);
      
      document.getElementById(attribute+'Display').innerHTML = value;
      
      var handler = function(e, changeBy)
      {
        var el = document.getElementById(attribute);
        var value = parseInt(el.value)+changeBy;
        
        if(value < 0)
        {
          value = 355;
        }
        else
        {
          value = value % 360;
        }
        
        canvas.setAttribute('width', MDSS.rotationCircle.width);
        canvas.setAttribute('height', MDSS.rotationCircle.height);
        
        context.translate(canvas.width/2, canvas.height/2);
        context.rotate(value * Math.PI / 180);
        context.translate(-1*(canvas.width/2), -1*(canvas.height/2));
        context.drawImage(MDSS.rotationCircle, 0, 0);
        
        el.value = value;
        document.getElementById(attribute+'Display').innerHTML = value+'&nbsp;&deg;';
      };
      
      // Clockwise
      YAHOO.util.Event.on(attribute+'CW', 'click', handler, 5);
      
      // Counter-Clockwise
      YAHOO.util.Event.on(attribute+'CCW', 'click', handler, -5);
    },
    
    _attachSlider : function(attribute, config)
    {
      var Event = YAHOO.util.Event,
      Dom   = YAHOO.util.Dom,
      lang  = YAHOO.lang,
      bg=attribute+"SliderBG", thumb=attribute+"Thumb", 
      textfield=attribute+"Converted"

      var valueStr = document.getElementById(attribute).value;
      var value = config.getValue(valueStr);
    
      var slider = YAHOO.widget.Slider.getHorizSlider(bg, 
                           thumb, config.start, config.end, config.increment);
      
      slider.animate = false;
      slider.setValue(value);
                           
      slider.subscribe("change", function(offsetFromStart) {
        var obj = config.onChange(offsetFromStart);
        
        document.getElementById(attribute+'Display').innerHTML = obj.display;
        document.getElementById(attribute).value = obj.value;
      });
    },
    
    attachDisplacementSlider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:1,
        getValue : function(value)
        {
          return parseInt(value) + 50;
        },
        onChange : function(offsetFromStart)
        {
          var px = parseInt(offsetFromStart) - 50;
          
          return {
            value : px,
            display : px
          };
        }
      });
    },
  
    attachAnchorSlider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:1,
        getValue : function(value)
        {
          return parseFloat(value) * 100;
        },
        onChange : function(offsetFromStart)
        {
          var anchor = offsetFromStart/100;
          
          return {
            value : anchor,
            display : anchor
          };
        }
      });
    },
    
    attach50Slider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:2,
        getValue : function(value)
        {
          return parseInt(value) * 2;
        },
        onChange : function(offsetFromStart)
        {
          var size = offsetFromStart / 2;
          
          return {
            value : size,
            display : size
          };
        }
      });
    },
    
    attach100Slider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:1,
        getValue : function(value)
        {
          return parseInt(value);
        },
        onChange : function(offsetFromStart)
        {
          return {
            value : offsetFromStart,
            display : offsetFromStart
          };
        }
      });
    },
    
    attach10Slider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:5,
        getValue : function(value)
        {
          return  (parseInt(value) + 10) * 5;
        },
        onChange : function(offsetFromStart)
        {
          var size = (parseInt(offsetFromStart) / 5) - 10;
          
          return {
            value : size,
            display : size
          };
        }
      });
    },

    attach1Slider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:1,
        getValue : function(value)
        {
          return parseFloat(value) * 100;
        },
        onChange : function(offsetFromStart)
        {
          var size = parseInt(offsetFromStart) / 100;
          
          return {
            value : size,
            display : size
          };
        }
      });
    },    
    
    attachOpacitySlider : function(attribute)
    {
      MDSS.MapPanel._attachSlider(attribute, {
        start:0,
        end:100,
        increment:1,
        getValue : function(value)
        {
          return parseFloat(value) * 100;
        },
        onChange : function(offsetFromStart)
        {
          return {
            value : offsetFromStart / 100,
            display : offsetFromStart
          };
        }
      });      
    },
  
    _currentMap : null,
    
    setCurrentMap : function(mapId)
    {
      MDSS.MapPanel._currentMap = mapId;
      
      // update the export form
      document.getElementById('export_mapId').value = mapId;
    },
    
    getCurrentMap : function()
    {
      return MDSS.MapPanel._currentMap;
    },
    
    toggleGeoStylesHandler : function(e)
    {
      MDSS.MapPanel.toggleGeoStyles(e.target.value);
    },
    
    /**
     * Shows/hides point and polygon styles based on the current selection for
     * Layer.renderAs.
     */
    toggleGeoStyles : function(value)
    {
      if(value === 'POINT')
      {
        document.getElementById('pointStyles').style.display = 'block';
        document.getElementById('polygonStyles').style.display = 'none';
      }
      else
      {
        document.getElementById('pointStyles').style.display = 'none';
        document.getElementById('polygonStyles').style.display = 'block';
      }
    },
    
    /**
     * Called after any insert, delete, or move is done on the list of available
     * layers. This method resets the base layer and re-numbers all other
     * layers.
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

(function(){
var Dom = YAHOO.util.Dom;
var Event = YAHOO.util.Event;
var DDM = YAHOO.util.DragDropMgr;


// ////////////////////////////////////////////////////////////////////////////
// custom drag and drop implementation
// ////////////////////////////////////////////////////////////////////////////

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

        // Hide the proxy and show the source element when finished with the
        // animation
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

        // If there is one drop interaction, the li was dropped either on the
        // list,
        // or it was dropped on the current location of the source element.
        if (DDM.interactionInfo.drop.length === 1) {

            // The position of the cursor at the time of the drop
            // (YAHOO.util.Point)
            var pt = DDM.interactionInfo.point;

            // The region occupied by the source element at the time of the drop
            var region = DDM.interactionInfo.sourceRegion;

            // Check to see if we are over the source element's location. We
            // will
            // append to the bottom of the list once we are sure it was a drop
            // in
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
     * Attaches the picker to the element of the given id such that a click
     * event on the element will render the picker.
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
     * Handles the submit click after selecting a color.
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
     * Cancels selecting a color and closes the dialog.
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
          modal : true,
          draggable: false,
          close: false,
          zindex: 200,
          buttons : [ { text: MDSS.localize('Submit'), handler:sBound, isDefault:true },
              { text: MDSS.localize('Cancel'), handler:cBound } ]
        });
  
        this._dialog.setBody('<div class="yui-picker" id="singleton_picker"></div>');
        this._dialog.render(document.body);
        this._dialog.bringToTop();
  
        this._picker = new YAHOO.widget.ColorPicker("singleton_picker", {
          container: this._dialog,
          images: {
            PICKER_THUMB: "js/yui/build/colorpicker/assets/picker_thumb.png",
            HUE_THUMB: "js/yui/build/colorpicker/assets/hue_thumb.png" 
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