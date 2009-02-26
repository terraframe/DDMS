var MDSS = MDSS || {}; // TODO push namespace into common js file

/*
This code is written by Andrew Roth (andrewroth.ca) and is intended for
use with the YUI or to be taken in any part or form and put directly into
the YUI if desired by the YUI team.

It extends the treeview widget to support drag and drop.

Code licensed under the BSD License:
http://developer.yahoo.net/yui/license.txt
version: 2.5.1
*/
/**
 * The treeview widget is a generic tree building tool.  This 
 * version has drag and drop support.
 *
 * @module treeviewdd
 * @title TreeView Drag and Drop Widget
 * @requires yahoo, event, treeview, dragdrop
 * @optional animation
 * @namespace YAHOO.widget
 */

/**
 * A tree view with drag and drop capability.
 * Only works with HtmlNode, but you can easily make your
 * own dd nodes (see {@link YAHOO.widget.DDNode}).
 *
 * for example:
 * 
 * tree = new YAHOO.widget.TreeViewDD("tree");
 * node1 = new YAHOO.widget.HtmlNode({ html: 'Hi!' }, tree.getRoot(), false, true);
 * node2 = new YAHOO.widget.HtmlNode({ html: 'Yay!' }, node1, false, true);
 * 
 * and set up the dragging and drag-hint css classes:
 *
 * .dragging, .drag-hint {
 *   border: 1px solid gray;
 *   background-color: blue;
 *   color: white;
 *   opacity: 0.76;
 *   filter: "alpha(opacity=76)";
 * }
 *
 * should be all you need to get drag and drop ability.
 *
 * @class TreeView
 * @uses YAHOO.util.EventProvider
 * @constructor
 * @extends YAHOO.widget.TreeView
 * @param {string|HTMLElement} id The id of the element, or the element
 * itself that the tree will be inserted into.
 */
YAHOO.widget.TreeViewDD = function(id, data) {
  YAHOO.widget.TreeViewDD.superclass.constructor.call(this, id, data); 
};

YAHOO.lang.extend(YAHOO.widget.TreeViewDD, YAHOO.widget.TreeView, {

  /**
   * Callback for before the tree is drawn.
   * @method init
   */
  drawBegin: function() {
    // not used, just in here for symmetry
  },

  /**
   * Callback for after the tree is drawn.
   * @method init
   */
  drawEnd: function() {
    this.getRoot().initDD();
  },

  /**
   * Renders the tree boilerplate and visible nodes, same as @method 
   * YAHOO.widget.TreeView.draw but with the begin and end methods called.
   */
  draw: function() {
    this.drawBegin();
    YAHOO.widget.TreeViewDD.superclass.draw.call(this); 
    this.drawEnd();
  }
});

/**
 * The base class for all drag and drop tree nodes.
 * This class will augment the YAHOO.widget.Node class so that
 * all classes that extend YAHOO.widget.Node can be drag and
 * dropped.  Simply implement the 
 * {@link YAHOO.widget.DDNode.getDdId }
 * method in any class that extends YAHOO.widget.Node.
 *
 * Unfortunately TextNode's don't work because of the anchor, see 
 * {@link YAHOO.widget.TextNode.getDdId}
 * 
 * @namespace YAHOO.widget
 * @class DDNode
 * @constructor
 */
YAHOO.widget.DDNode = function() {}

YAHOO.widget.DDNode.prototype = {
  applyParentBeforeDD: YAHOO.widget.Node.prototype.applyParent,
  applyParent: function(p) {
    this.applyParentBeforeDD.call(this, p);
  },
  
 /**
  * Overrides the refresh method to setup the dd elements after.
  * 
  * @method refresh
  */
  refreshBeforeDD: YAHOO.widget.Node.prototype.refresh,
  refresh: function() {
    this.refreshBeforeDD.call(this);
    this.initDD();
  },
  
 /**
  * Returns true if the Node is a descendent of supplied Node
  *
  * @method isDescendentOf
  * @param parentNode {Node} the Node to check
  * @return {boolean} true iff this node is a descendent of parentNode
  * @private
  */
  isDescendentOf: function(parentNode) {
    if (!parentNode || this.isRoot()) return false;
    
    if (this.parent == parentNode) return true
    return this.parent.isDescendentOf(parentNode);
  },
  
  /**
   * The drag and drop (proxy) to use for this element.
   * Not set until initProxies is called
   * @property ddProxy
   * @type DDProxy
   * @default null
   */
  dd: null,
  
  /**
   * Returns the id for this node's dd container div
   * @method getDdId
   * @return {string} the element dd id
   */
  getDdId: function() {
      return "ygtvdd" + this.index;
  },
  
  /**
   * Initializes all the drag and drop objects for this node
   * and its children.
   * @method initDD
   */
   initDD: function() {
    if (!this.isRoot()
      && this instanceof YAHOO.widget.HTMLNode /* JN change: only HTMLNodes can be draggable */) {     
      if (this.dd == null || this.dd == undefined) {
        this.dd = new YAHOO.util.DDNodeProxy(this, this.getDdId());
      } else {
        this.dd.unreg();
        this.dd.init(this.getDdId());
      }
    }
    
    for (var i = 0; i < this.children.length; ++i) {
        this.children[i].initDD();
    }
  }
};

YAHOO.lang.augment(YAHOO.widget.Node, YAHOO.widget.DDNode, true);

/**
 * Gets the id the drag and drop will be set to.  Unfortunately it
 * isn't working right with TextNode's.  It's an anchor element that
 * is the text node and that isn't working to be a DDProxy.  Maybe
 * someone will have a solution, or just use the HtmlNode.
 *
 * @method getDdId
 * @namespace YAHOO.widget
 * @class TextNode
 */
YAHOO.widget.TextNode.prototype.getDdId = function() {
  return this.labelElId;
};

/**
 * Gets the id the drag and drop will be set to.
 * @method getDdId
 * @namespace YAHOO.widget
 * @class HTMLNode
 */
YAHOO.widget.HTMLNode.prototype.getDdId = function() {
  return this.contentElId;
};

/**
 * This is a DD proxy specifically for tree nodes.  It will
 * move nodes around when dropped, among a few other things
 * specifically for trees.
 *
 * @namespace YAHOO.util
 * @class DDNodeProxy
 * @extends YAHOO.util.DDNodeProxy
 * @constructor
 * @param {String} id the id of the linked html element
 * @param {String} sGroup the group of related DragDrop objects
 * @param {object} config an object containing configurable attributes
 *                Valid properties for DDProxy in addition to those in DragDrop: 
 *                   resizeFrame, centerFrame, dragElId
 */

YAHOO.util.DDNodeProxy = function(node, id, sGroup, config) {
  YAHOO.util.DDNodeProxy.superclass.constructor.call(this, 
    id, sGroup, config);
  
  // remember node for drag drop event
  this.node = node;  
};

YAHOO.extend(YAHOO.util.DDNodeProxy, YAHOO.util.DDProxy, {
  
  /**
   * Returns true if this can be dropped on the dest proxy's node.
   * Disallows nodes to be moved to one of their children.
   *
   * @method validDest
   * @param ddid string the id of the destination dragdrop to check.
   */
  validDest: function(ddid) {
    destNode = YAHOO.util.DDM.getDDById(ddid).node;
    
    return !destNode.isDescendentOf(this.node);
  },
  
  /**
    * Overriden to disable the element moving after a drag.
    *
    * @method endDrag
    */
  endDrag: function() {
  },
  
  /**
    * Called when source object first selected for dragging
    * Copies the HTML to make it more than a plain outline,
    * Adds it to the dragging class.
    * 
    * @method endDrag
    */
  startDrag: function(x, y) {
    var dragEl = this.getDragEl();
    var clickEl = this.getEl();
    
    dragEl.innerHTML = clickEl.innerHTML;
    dragEl.className = clickEl.className + " dragging";
  },
  
  /**
    * Called when source object is dragged overtop
    * another node.  Adds it to the drag-hint class
    * and keeps a record of the classes before adding
    * it to drag-hint so that it can be reversed.
    * 
    * @method onDragEnter
    * @param {Event} e the mousemove event
    * @param {String|DragDrop[]} id In POINT mode, the element
    */
  onDragEnter: function(e, id) {
    if (this.validDest(id)) {
      el = this.getElDom(id);
      el.classNameBeforeDrag = el.className;
      el.className += ' drag-hint';
    }
  },
  
  /**
    * Called when the source object is dropped on a valid target.
    * Moves node represented by this DDNodeProxy to be a child of the
    * target, if valid.
    * 
    * @method onDragDrop
    * @param {Event} e the mousemove event
    * @param {String|DragDrop[]} id element being dragged onto
    */
  onDragDrop: function(e, id) {
    if (this.validDest(id)) {

      /* JN change: create new relationship between parent and child */
      var ddThis = this;
      var request = new Mojo.ClientRequest({
      	ddThis : ddThis,
      	onSuccess : function(){
      	  
          // remove drag-hint class
          this.ddThis.getElDom(id).className = this.ddThis.getElDom(id).classNameBeforeDrag;
      
          var destNode = YAHOO.util.DDM.getDDById(id).node;
          
          // remove
          thisParent = this.ddThis.node.parent;
          this.ddThis.node.tree.popNode(this.ddThis.node);
      
          // fixes bug where a parent with 1 item is still marked
          // expanded, so when a new node is dragged to it, it doesn't
          // draw the new node (thinks it's already expanded)
          if (thisParent.children.length == 0) {
            thisParent.expanded = false;
          }
      
          // make removal changes visible
          thisParent.refresh();
          
          // Only add the node if the children have loaded via Ajax.
          // Otherwise, the node will appear twice (i.e., once from
          // the drag and drop and once from the Ajax load).
          if(destNode.dynamicLoadComplete)
          {
            this.ddThis.node.appendTo(destNode);
            destNode.expanded = false; // force re-expansion
          }

          destNode.refresh();  
          destNode.expand();
      	},
      	onFailure : function(e){
      	  alert(e.getLocalizedMessage());
      	}
      });
      
      var childGeoEntity = MDSS.GeoEntityTree.getGeoEntity(this.id);
      var parentGeoEntity = MDSS.GeoEntityTree.getGeoEntity(id);
      childGeoEntity.applyWithParentGeoEntity(request, parentGeoEntity.getId());
    }
  },
  
  /**
    * Called when source object is dragged out of a node.
    * Resets the classes to what it was in order to remove the
    * drag-hint class.
    * @param {Event} e the mousemove event
    * @param {String|DragDrop[]} id the element being dragged out of
    */
  onDragOut: function(e, id) {
    if (this.validDest(id)) {
      el = this.getElDom(id);
      el.className = el.classNameBeforeDrag;
    }
  },

  /**
    * Helper method to get the drag and drop element for the given id.
    * 
    * @method getElDom
    * @param {id} string|object The id to get the element for
    */
  getElDom: function(id) {
    return YAHOO.util.Dom.get(id);
  }

});
// END Andrew Roth's code

/**
 * Extracts all script tag contents and returns
 * a string of executable code that can be evaluated.
 */
MDSS.extractScripts = function(html)
{
  var scripts = html.match(/<script\b[^>]*>[\s\S]*?<\/script>/img);
  var executables = [];
  for(var i=0; i<scripts.length; i++)
  {
    var scriptM = scripts[i].match(/<script\b[^>]*>([\s\S]*?)<\/script>/im);
  	executables.push(scriptM[1]);
  }
  
  return executables.join('');
}

/**
 * Removes all scripts from the HTML and returns
 * a string of the cleansed HTML.
 */
MDSS.removeScripts = function(html)
{
  return html.replace(/<script\b[^>]*>[\s\S]*?<\/script>/img, '');
}

/**
 * GeoEntityTree object manages the GeoEntity tree
 * operations (e.g., drag and drop, create node, delete node).
 */
MDSS.GeoEntityTree = (function(){

  // key/value is node id/GeoEntity id
  var _nodeToGeoEntityMap = {};

  // key/value is GeoEntity id/GeoEntity
  var _geoEntityCache = {};

  // The selected node in tree
  var _selectedNode = null;
  
  // The tree for GeoEntities
  var _geoTree = null;
  
  /**
   * Sets the mapping between a node and GeoEntity.
   * More than one node may point to a GeoEntity.
   */
  function _setMapping(node, geoEntity)
  {
  	var nodeId = node.contentElId;
  	var geId = geoEntity.getId();
  	
  	// overwrite any existing entries
  	_nodeToGeoEntityMap[nodeId] = geId;
  	_geoEntityCache[geId] = geoEntity;
  }
  
  /**
   * Gets the GeoEntity that maps to the given
   * node.
   */
  function _getGeoEntity(node)
  {
  	var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
  	var geId = _nodeToGeoEntityMap[nodeId];
  	return _geoEntityCache[geId];
  }
  
  /**
   * Removes the node to GeoEntity mapping.
   */
  function _removeMapping(node)
  {
    var nodeId = node instanceof YAHOO.widget.HTMLNode ? node.contentElId : node;
    delete _nodeToGeoEntityMap[nodeId];
  }
  
  /**
   * Creates a new node.
   */
  function _createNode(type, params, action)
  {
    var geConstructor = Mojo.util.getType(type);
    var geoEntity = new geConstructor();

  	var entityName = params['dto.entityName'];
  	var geoId = params['dto.geoId'];
  	var terrain = Mojo.$.mdss.ivcc.mrc.csu.geo.Terrain[params['dto.terrain']];
    
    geoEntity.setEntityName(entityName);
    geoEntity.setGeoId(geoId);
    geoEntity.addTerrain(terrain);

  	var request = new Mojo.ClientRequest({
  	  onSuccess : function(retVal, geoEntity){
  	  	
  	  	// add the node directly if the children have already been dynamically loaded
  	    if(_selectedNode.dynamicLoadComplete)
  	    {
          var node = new YAHOO.widget.HTMLNode(geoEntity.getEntityName(), _selectedNode);
          
          _selectedNode.expanded=false // force a re-expansions
          _selectedNode.refresh();
          _setMapping(node, geoEntity);
  	    }

        _selectedNode.expand();
        
        document.getElementById('nodeEdit').innerHTML = '';
  	  },
  	  onFailure : function(e){
  	    alert(e.getLocalizedMessage());
  	  }
  	});
    
    var parentGeoEntity = _getGeoEntity(_selectedNode);
    geoEntity.applyWithParentGeoEntity(request, parentGeoEntity.getId());
  }
  
  /**
   * Updates a node.
   */
  function _updateNode(params, actions)
  {
    var entityName = params['dto.entityName'];
  	var geoId = params['dto.geoId'];
  	var terrain = Mojo.$.mdss.ivcc.mrc.csu.geoTerrain[params['dto.terrain']];
    
    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.setEntityName(entityName);
    geoEntity.setGeoId(geoId);
    geoEntity.addTerrain(terrain);
    
    var request = new Mojo.ClientRequest({
      onSuccess: function(geoEntity){
      	_selectedNode.setHtml(geoEntity.getEntityName());
        _setMapping(_selectedNode, geoEntity);
        document.getElementById('nodeEdit').innerHTML = '';
      },
      onFailure: function(e){
  	    alert(e.getLocalizedMessage());
      }
    });
    
    geoEntity.apply(request);
  }
  
  /**
   * Handler for new node request.
   */
  function _addNodeHandler()
  {
  	var request = new Mojo.ClientRequest({
  	  onSuccess : function(html){
  	  	var executable = MDSS.extractScripts(html);
  	  	var html = MDSS.removeScripts(html);
  	  	document.getElementById('nodeEdit').innerHTML = html;
  	  	eval(executable);
  	  },
  	  onFailure : function(e){
  	    alert(e.getLocalizedMessage());
  	  }
  	});

    var type = this.id;
  	var controller = Mojo.util.getType(type+"Controller");
  	controller.setCreateListener((function(type){
  	  return function(params, action){
  	    _createNode(type, params, action);
  	  };
  	})(type));
  	controller.newInstance(request);
  }
  
  /**
   * Cancels an action to update a node.
   */
  function _cancelNode()
  {
    var request = new Mojo.ClientRequest({
      onSuccess: function(){
        document.getElementById('nodeEdit').innerHTML = '';
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
      }
    });

    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.unlock(request);
  }
  
  /**
   * Handler for edit node request.
   */
  function _editNodeHandler()
  {
    var request = new Mojo.ClientRequest({
      onSuccess: function(html){
   	  	var executable = MDSS.extractScripts(html);
  	  	var html = MDSS.removeScripts(html);
  	  	document.getElementById('nodeEdit').innerHTML = html;
  	  	eval(executable);
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    var geoEntity = _getGeoEntity(_selectedNode);

    var controller = Mojo.util.getType(geoEntity.getType()+"Controller");
    controller.setUpdateListener(_updateNode);
    controller.setCancelListener(_cancelNode);
    controller.edit(request, geoEntity.getId());
  }
  
  /**
   * Deletes the selected node from the tree.
   */
  function _deleteNodeHandler()
  {
  	var request = new Mojo.ClientRequest({
  	  onSuccess : function(){
  	  	
        _removeMapping(_selectedNode);

  	    var parent = _selectedNode.parent;
        _geoTree.removeNode(_selectedNode);
        parent.refresh();
  	  },
  	  onFailure : function(e){
  	    alert(e.getLocalizedMessage());
  	  }
  	});
  	
    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.remove(request);
  }
  
  /**
   * Event handler for a triggered context menu. This method
   * cancels the action if the menu was not triggered on a node.
   */
  function _nodeMenuSelect()
  {
    var oTarget = this.contextEventTarget;

    var htmlNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvhtml") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvhtml");
    if (htmlNode) {
      _selectedNode = _geoTree.getNodeByElement(htmlNode);
    }
    else {
      this.cancel();
    }
  }
  
  /**
   * Loads child nodes dynamically
   */
  function _dynamicLoad(parentNode, fnLoadComplete)
  {
  	// request to fetch children
    var request = new Mojo.ClientRequest({
      onSuccess : function(childNodes){
        
        for(var i=0; i<childNodes.length; i++)
        {
          var child = childNodes[i];
          var node = new YAHOO.widget.HTMLNode(child.getEntityName());
          parentNode.appendChild(node);
          
          _setMapping(node, child);
        }
        
        parentNode.refresh();
        fnLoadComplete();
      },
      onFailure : function(e){
      	alert(e.getLocalizedMessage());
      }
    });

    var geoEntity = _getGeoEntity(parentNode);
    geoEntity.getAllContainsGeoEntity(request);
  }
  
  /**
   * Initializes the tree by setting the GeoEntity with the
   * given id as first node under the root.
   */
  function _initializeTree(treeId, geoEntity) {

    var node = {type:"HTML", html:geoEntity.getEntityName()};

    _geoTree = new YAHOO.widget.TreeViewDD(treeId, [node]);
    _geoTree.setDynamicLoad(_dynamicLoad);
    _geoTree.draw();

    var menu = new YAHOO.widget.ContextMenu("treeMenu", {
      trigger:treeId,
      lazyload:true,
      itemdata:[
        {text:"Create", submenu: {
          id:"createMenu",
          itemdata : [
            {text:"Country", id:"mdss.test.Country", onclick: {fn:_addNodeHandler}},
            {text:"Province", id:"mdss.test.Province", onclick: {fn:_addNodeHandler}},
            {text:"District", id:"mdss.test.District", onclick: {fn:_addNodeHandler}},
            {text:"Sub District", id:"mdss.test.SubDistrict", onclick: {fn:_addNodeHandler}}
          ]
        }},
        {text:"Edit", onclick: {fn:_editNodeHandler}},
        {text:"Delete", onclick: {fn:_deleteNodeHandler}}
      ]
    });
      
    menu.subscribe("triggerContextMenu", _nodeMenuSelect);
    
    // map node to GeoEntity
    _setMapping(_geoTree.getRoot().children[0], geoEntity);
  }
  
  // return all public methods/properties
  return {
    initializeTree : _initializeTree,
    getGeoEntity : _getGeoEntity
  };
})();