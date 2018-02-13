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
Mojo.Meta.newClass('MDSS.GeoPickerWithUniversals', {

  IsAbstract : false,
  
  Extends : MDSS.GeoPicker,

  Instance : {

    /**
     * Constructor.
     */
    initialize : function(queryBase)
    {
      this.$initialize(true);
      
      this._queryBase = queryBase;
      this._geoMap = new com.runwaysdk.structure.HashMap();
      this._liToNodesMap = {};
    },
    
    layoutLeftPanel : function(parent)
    {
      var factory = com.runwaysdk.ui.Manager.getFactory();
      var dividerColor = "#87a4db";
      
      this._buttonHeight = 30;
      
      this._geoDaddyW = 70; // in Percentage
      this._uniDaddyW = 100 - this._geoDaddyW;
      
      this._geoPickerContainer.setStyle("height", "calc(100% - " + (this._titleHeight + this._buttonHeight + this._margin*2) + "px)");
      
      // Universal grand daddy el
      var uniDaddy = factory.newElement("div", {"id":"uniDaddy" + this._suffix});
      uniDaddy.setStyle("margin-top", "10px");
      uniDaddy.setStyle("float", "left");
      uniDaddy.setStyle("width", "calc(" + this._uniDaddyW + "% - " + (this._margin*2 + 1) + "px)");
      uniDaddy.setStyle("height", "100%");
      parent.appendChild(uniDaddy);
      
      // Section title that says "Select columns"
      var uniSectionTitle = factory.newElement("h2", {"id":"uniSectionTitle" + this._suffix});
      uniSectionTitle.setInnerHTML(com.runwaysdk.Localize.get("geopicker_select_columns"));
      uniDaddy.appendChild(uniSectionTitle);
      uniDaddy.appendChild(factory.newElement("hr", {"color": dividerColor}));
      
      // Universal list
      this.layoutUniversalList(uniDaddy);
      
      parent.appendChild(factory.newElement("hr", {"width": "1", "size": "300"}, {"float": "left", "margin": this._margin}));
    },
    
    hasLeftPanel : function()
    {
      return true;
    },
    
    layoutUniversalList : function(parent)
    {
      var factory = com.runwaysdk.ui.Manager.getFactory();
      var that = this;
      
      var request = new MDSS.Request({
        onSuccess : function(hierarchies)
        {
          var uniListDiv = factory.newElement("div", {"id": "uniList" + that._suffix});
          var uniListDivJQ = $(uniListDiv.getRawEl());
          for (var i = 0; i < hierarchies.length; ++i)
          {
            var uni = hierarchies[i];
            var type = 'dss.vector.solutions.geo.generated.' + uni.getTypeName();
            
            var uniDiv = factory.newElement("div");
            uniListDiv.appendChild(uniDiv);
            
            var uniInput = factory.newElement("input", {"type": "checkbox"});
            uniInput.addClassName("selectUniversalType");
            uniInput.setStyle("margin-right", "10px");
            uniInput.setStyle("margin-left", "10px");
            uniInput.getRawEl().value = type;
            uniDiv.appendChild(uniInput);
            
            var uniLabel = factory.newElement("span");
            uniLabel.setInnerHTML(uni.getDisplayLabel());
            uniDiv.appendChild(uniLabel);
          }
          parent.appendChild(uniListDiv);
        }
      });
      
      dss.vector.solutions.geo.GeoHierarchy.collectHierarchies(request, this._selectSearchRootId, this.getFlags(), this.getExtraUniversals());
    },
    
    layoutGeoSection : function(parent)
    {
      var factory = com.runwaysdk.ui.Manager.getFactory();
      var geoDaddy = this.$layoutGeoSection(parent);
      
      this._geoDaddy.setStyle("height", "100%");
      this._geoDaddy.setStyle("width",  "calc(" + this._geoDaddyW + "% - " + (this._margin*2 + 1) + "px)");
      
      this._treeContainer.setStyle("height","calc(70% - " + ((this._geoInputHeight + 14 + 45)/2) + "px)");
      
      this._results = factory.newElement('div', {'id':"results" + this._suffix}, {"bottom":"0px", "height":"calc(30% - " + ((this._geoInputHeight + 14 + 45)/2) + "px)", "overflow":"auto"});
      geoDaddy.appendChild(this._results);
      
      // OK / Cancel Buttons
      this._renderButtons(this._geoPickerPanelEl);
      
      this._tree.addOnDynamicLoadListener(Mojo.Util.bind(this, this._onDynamicLoadListener));
    },
    
    /**
     * Creates the OK, Cancel buttons.
     */
    _renderButtons : function(parent)
    {
      var fac = com.runwaysdk.ui.Manager.getFactory();
      
      var ok = com.runwaysdk.Localize.get("OK");
      var cancel = com.runwaysdk.Localize.get("Cancel");
      
      this._bContainer = fac.newElement("div", null, {height: this._buttonHeight + "px", width:"100px", "padding-left":"calc(50% - 100px)", "position":"absolute", "padding-top":"10px", "bottom":"0px"});
      this._bOK = fac.newButton(ok, Mojo.Util.bind(this, this._okHandler));
      this._bOK.setStyle("margin-right", "5px");
      this._bOK.setEnabled(true);
      this._bContainer.appendChild(this._bOK);
      this._bCancel = fac.newButton(cancel, Mojo.Util.bind(this, this._cancelHandler));
      this._bContainer.appendChild(this._bCancel);
      
      this._bContainer.render(parent);
    },
    
    _okHandler : function()
    {
      var entities = this._geoMap.values();
      var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
      var selected = [];
      for(var i = 0; i < checkboxes.length; i++)
      {
        var check = checkboxes[i];
        if(check.checked)
        {
          var type = check.value;
          selected.push(type);
        }
      }
      
      this._queryBase._hideHandler(entities, selected);
      
      this.hide();
    },
    
    _cancelHandler : function()
    {
//      this._bOK.setEnabled(false);
      this.hide();
    },
    
    /**
     * Copies a GeoEntity to a GeoEntityView (used for caching).
     */
    _copyEntityToView : function(geoEntity)
    {
      var view = new dss.vector.solutions.geo.GeoEntityView();
  
      view.setGeoEntityId(geoEntity.getId());
      view.setGeoId(geoEntity.getGeoId());
      view.setActivated(geoEntity.getActivated());
      view.setEntityLabel(geoEntity.getEntityLabel().getLocalizedValue());
      view.setEntityType(geoEntity.getType());
      view.setTypeDisplayLabel(geoEntity.getTypeMd().getDisplayLabel());
  
      return view;
    },
    
    _onGeoSelect : function(geoEntityView, node)
    {
      if (!this._isActiveBasedOnGeoFilterCriteria(geoEntityView))
      {
        node.unhighlight()
        node.toggle()
        return;
      }
      
      var id = geoEntityView.getGeoEntityId();
      var mapVal = this._geoMap.get(id);
      if (mapVal == null)
      {
        this._layoutNewListGeo(geoEntityView, node);
        this._geoMap.put(id, geoEntityView);
      }
      else if (Mojo.Util.isObject(mapVal))
      {
        this._geoMap.remove(id);
        this._deleteSelection(id+"_selected");
      }
      
      // the YUI tree opens the branch when you click the checkbox for some reason.
      // This toggle method will toggle whether or not the branch is open.
      // Since we toggle it here, when they toggle it later it will not open/close the branch.
      node.toggle()
      
//      if (!reopen)
//      {
        // All this code just to figure out if the ok button is enabled/disabled
//        this._bOK.setEnabled(this._geoMap.size() > 0);
//      }
    },
    
    setSelectedUniversals : function(unis)
    {
      var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
      for (var j = 0; j < checkboxes.length; ++j)
      {
        var check = checkboxes[j];
        check.checked = false;
      }
      
      for (var i = 0; i < unis.length; ++i)
      {
        var uni = unis[i];
        
        var checkboxes = YAHOO.util.Selector.query('input[type="checkbox"].selectUniversalType');
        for (var j = 0; j < checkboxes.length; ++j)
        {
          var check = checkboxes[j];
          if (check.value === uni)
          {
            check.checked = true;
          }
        }
      }
    },
    
    setCriteria : function(geos)
    {
      // uncheck everything that used to be checked
      var oldGeos = this._geoMap.keySet();
      for (var i = 0; i < oldGeos.length; ++i)
      {
        var oldGeo = oldGeos[i];
        
        var nodes = this._tree.getNodeByGeoEntityId(oldGeo);
        if (nodes != null)
        {
          for (var j = 0; j < nodes.length; ++j)
          {
            nodes[j].unhighlight();
          }
        }
      }
      
      this._geoMap.clear();
      this._results.getRawEl().innerHTML = '';
      this._liToNodesMap = [];
      
      if (geos.length > 0)
      {
        for (var i = 0; i < geos.length; ++i)
        {
          var geoView = geos[i];
          
          var nodes = this._tree.getNodeByGeoEntityId(geoView.getGeoEntityId());
          if (nodes != null)
          {
            for (var j = 0; j < nodes.length; ++j)
            {
              nodes[j].highlight();
            }
          }
          this._layoutNewListGeo(geoView, nodes);
          
          this._geoMap.put(geoView.getGeoEntityId(), geoView);
        }
      }
    },
    
    _onDynamicLoadListener : function(node, geoView)
    {
      var liId = geoView.getGeoEntityId()+"_selected";
      
      if (this._geoMap.containsKey(geoView.getGeoEntityId()))
      {
        if (this._liToNodesMap[liId] != null)
        {
          this._liToNodesMap[liId].push(node);
        }
        else
        {
          this._liToNodesMap[liId] = [node];
        }
        node.highlight();
      }
    },
    
    /**
     * Deletes the li element from the current selection list.
     * The GeoEntity associated with that selection will no longer
     * be passed to the calling process.
     */
    _deleteSelection : function(id)
    {
      var li = document.getElementById(id);
      var ul = li.parentNode;
      ul.removeChild(li);
      
      this._geoMap.remove(id.substring(0, id.length - 9));

//    node.highlight() // adds a checkbox
//      li.node.unhighlight() // removes a checkbox
      var nodes = this._liToNodesMap[id];
      
      if (Mojo.Util.isArray(nodes))
      {
        for (var i = 0; i < nodes.length; ++i)
        {
          nodes[i].unhighlight(); // unhighlight unchecks a checkbox
        }
      }
      
      delete this._liToNodesMap[id];
    },
    
    _layoutNewListGeo : function(geoEntityView, node)
    {
      var liId = geoEntityView.getGeoEntityId()+"_selected";
      
      if (!this._geoMap.containsKey(geoEntityView.getGeoEntityId()))
      {
        var li = document.createElement('li');
        li.className = "geoLi";
        li.id = liId;
    
        var del = document.createElement('img');
        YAHOO.util.Dom.setAttribute(del, 'src', 'imgs/icons/delete.png');
        YAHOO.util.Event.on(del, 'click', function(mouseEvent, id) {
          this._deleteSelection(id);
        }, liId, this);
    
        var span = document.createElement('span');
        span.innerHTML = this.constructor.formatDisplay(geoEntityView);
    
        var div = document.createElement('div');
        div.appendChild(del);
        div.appendChild(span);
    
        li.appendChild(div);
        
        if (node != null)
        {
          if (!Mojo.Util.isArray(node))
          {
            node = [node];
          }
          
          this._liToNodesMap[liId] = node;
        }
        else
        {
          this._liToNodesMap[liId] = [];
        }
        
        this._results.appendChild(li);
      }
    }
  }
});