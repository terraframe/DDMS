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
  render: function() {
    this.drawBegin();
    YAHOO.widget.TreeViewDD.superclass.render.call(this); 
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
            destNode.refresh();  
          }

          destNode.expanded = false; // force re-expansion
          destNode.expand();
        },
        onFailure : function(e){
          alert(e.getLocalizedMessage());
        }
      });
      
      var childGeoEntity = MDSS.GeoEntityTree.getGeoEntity(this.id);
      var parentGeoEntity = MDSS.GeoEntityTree.getGeoEntity(id);
      childGeoEntity.applyWithParentGeoEntity(request, parentGeoEntity.getId(), false);
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
  
  // The menu for CRUD operations
  var _menu = null;
  
  // reference to modal for node create/edit
  var _modal = null;
  
  // callback function for selecting a node in the tree
  var _selectCallback = null;
  
  /**
   * Removes everything from the current Tree
   */
  function _destroyAll()
  {
  	_nodeToGeoEntityMap = {};
  	_geoEntityCache = {};
  	_selectedNode = null;
  	_modal = null;
  	_selectCallback = null;
  	try
  	{
  	  _menu.destroy();
  	}
  	catch(e)
  	{
  	  _menu = null;
  	}
  	_geoTree.destroy();
  	_geoTree = null;
  }
  
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
    var geId = _getGeoEntity(nodeId).getId();
    
    delete _nodeToGeoEntityMap[nodeId];
    delete _geoEntityCache[geId];
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
    var activated = params['dto.activated'];
    
    geoEntity.setEntityName(entityName);
    geoEntity.setGeoId(geoId);
    geoEntity.setActivated(activated);

    var request = new Mojo.ClientRequest({
      onSuccess : function(ids, geoEntity){
        
        // add the node directly if the children have already been dynamically loaded
        if(_selectedNode.dynamicLoadComplete)
        {
          var node = new YAHOO.widget.HTMLNode(geoEntity.getEntityName(), _selectedNode);
          
          _selectedNode.expanded = false // force a re-expansions
          _selectedNode.refresh();
          
          // must reset mapping with new id
          _setMapping(node, geoEntity);
        }

        _selectedNode.expand();
        
        _modal.destroy();
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    var parentGeoEntity = _getGeoEntity(_selectedNode);
    geoEntity.applyWithParentGeoEntity(request, parentGeoEntity.getId(), false);
  }
  
  /**
   * Updates a node.
   */
  function _updateNode(params, actions)
  {
    var entityName = params['dto.entityName'];
    var geoId = params['dto.geoId'];
    var activated = params['dto.activated'];
    
    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.setEntityName(entityName);
    geoEntity.setGeoId(geoId);
    geoEntity.setActivated(activated);
    
    var request = new Mojo.ClientRequest({
      onSuccess: function(ids, geoEntity){
        _selectedNode.setHtml(geoEntity.getEntityName());
        _setMapping(_selectedNode, geoEntity);
        
        _modal.destroy();
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    geoEntity.updateFromTree(request);
  }
  
  /**
   * Event handler for when a GeoEntity type is selected
   * to create a new instance of that type.
   */
  function _createTypeSelected(e, obj)
  {
  	var type = obj.type;
    var request = new Mojo.ClientRequest({
      onSuccess : function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);
        
        _modal.setBody(html);
        
        eval(executable);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });

    var controller = Mojo.util.getType(type+"Controller");
    controller.setCreateListener(MDSS.util.curry(_createNode, type));
    controller.newInstance(request);    	
  }
  
  /**
   * Creates a modal to contain GeoEntity create/edit operations.
   */
  function _createModal(html)
  {
    _modal = new YAHOO.widget.Panel("select",  
      { width:"400px", 
        height: "400px",
        fixedcenter:true, 
        close:true, 
        draggable:false, 
        zindex:4,
        modal:true,
        visible:true
      } 
    );
    
    _modal.setBody(html);
    _modal.render(document.body);
  }
  
  /**
   * Handler for new node request.
   */
  function _addNodeHandler()
  {
    // create ul to hold labels
    var ulRaw = document.createElement('ul');
    ul = new YAHOO.util.Element(ulRaw);
    
    // add all labels
    var geoEntity = _getGeoEntity(_selectedNode);
    var type = geoEntity.getType();
    
    var allowedMap = {};
    function collectSubtypes(map, parent)
    {
      var selectable = MDSS.GeoTreeSelectables.types[parent];
      for(var i=0; i<selectable.length; i++)
      {
      	var entry = selectable[i];
      	map[entry.type] = entry;
      	
      	collectSubtypes(map, entry.type);
      }
    }
    
    collectSubtypes(allowedMap, type);
    
    var allowedTypes = Mojo.util.getValues(allowedMap);
    for(var i=0; i<allowedTypes.length; i++)
    {
      var entry = allowedTypes[i];
    	
      var liRaw = document.createElement('li');
      var li = new YAHOO.util.Element(liRaw);
      
      li.on('click', _createTypeSelected, {
        type : entry.type
      });
      
      liRaw.innerHTML = entry.label;
      
      ul.appendChild(liRaw);
    }
    
    _createModal(ulRaw);
  }
  
  /**
   * Cancels an action to update a node.
   */
  function _cancelNode()
  {
    var request = new Mojo.ClientRequest({
      onSuccess: function(){
        _modal.destroy();
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
      }
    });

    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.unlock(request);
  }
  
  /**
   * Deletes the current node from the tree.
   */
  function _deleteNode()
  {
    var request = new Mojo.ClientRequest({
      onSuccess: function(){
        
        _modal.destroy();
        
        _removeMapping(_selectedNode);

        var parent = _selectedNode.parent;
        _geoTree.removeNode(_selectedNode);
        parent.refresh();
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
      }
    });

    var geoEntity = _getGeoEntity(_selectedNode);
    geoEntity.remove(request);
  }
  
  /**
   * Handler for edit node request.
   */
  function _editNodeHandler()
  {
    var request = new Mojo.ClientRequest({
      onSuccess: function(html){
        var executable = MDSS.util.extractScripts(html);
        var html = MDSS.util.removeScripts(html);
        
        _createModal(html);
        
        eval(executable);
      },
      onFailure: function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    var geoEntity = _getGeoEntity(_selectedNode);

    var controller = Mojo.util.getType(geoEntity.getType()+"Controller");
    controller.setDeleteListener(_deleteNode);
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
   * Invokes _selectedCallback with the id of the GeoEntity
   * represented by the currently selected node.
   */
  function _customSelectHandler()
  {
    var geoEntity = _getGeoEntity(_selectedNode);
    
    _selectCallback(geoEntity, _selectedNode);
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
      onSuccess : function(query){
        
        var childNodes = query.getResultSet();
        
        for(var i=0; i<childNodes.length; i++)
        {
          var child = childNodes[i];
          var node = new YAHOO.widget.HTMLNode(child.getEntityName());
          parentNode.appendChild(node);
          
          _setMapping(node, child);
        }
        
        fnLoadComplete();
        parentNode.refresh();
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });

    var geoEntity = _getGeoEntity(parentNode);
    geoEntity.getOrderedChildEntities(request);
  }
  
  /**
   * Renders the actual tree with the given root GeoEntity
   */
  function _renderTree(treeId, geoEntity, selectCallback)
  {
    var node = {type:"HTML", html:geoEntity.getEntityName()};

    _geoTree = new YAHOO.widget.TreeViewDD(treeId, [node]);
    _geoTree.setDynamicLoad(_dynamicLoad);
    _geoTree.render();

    var itemData = [];

    // the select callback is optional
    if(Mojo.util.isFunction(selectCallback))
    {
      _selectCallback = selectCallback;
      var selectMenuItem = new YAHOO.widget.ContextMenuItem("Select");
      selectMenuItem.subscribe("click", _customSelectHandler);
      itemData.push(selectMenuItem);
    }

    var createMenuItem = new YAHOO.widget.ContextMenuItem("Create");
    createMenuItem.subscribe("click", _addNodeHandler);
    itemData.push(createMenuItem);
    
    var editMenuItem = new YAHOO.widget.ContextMenuItem("Edit");
    editMenuItem.subscribe("click", _editNodeHandler);
    itemData.push(editMenuItem);
    
    var deleteMenuItem = new YAHOO.widget.ContextMenuItem("Delete");
    deleteMenuItem.subscribe("click", _deleteNodeHandler);
    itemData.push(deleteMenuItem);

    _menu = new YAHOO.widget.ContextMenu("treeMenu", {
      trigger:treeId,
      lazyload:true,
      itemdata: itemData
    });
    
    _menu.subscribe("triggerContextMenu", _nodeMenuSelect);
    
    // map node to GeoEntity
    _setMapping(_geoTree.getRoot().children[0], geoEntity);
  }
  
  /**
   * Initializes the tree by setting the GeoEntity with the
   * given id as first node under the root.
   */
  function _initializeTree(treeId, selectCallback) {
    var request = new Mojo.ClientRequest({
      onSuccess : function(geoEntity){
        // build tree
        _renderTree(treeId, geoEntity, selectCallback);
      },
      onFailure : function(e){
        alert(e.getLocalizedMessage());
      }
    });
    
    // Fetch the root node
    Mojo.$.csu.mrc.ivcc.mdss.geo.generated.GeoEntity.get(request, MDSS.GeoTreeRootId);
  }
  
  // return all public methods/properties
  return {
    initializeTree : _initializeTree,
    getGeoEntity : _getGeoEntity,
    destroyAll : _destroyAll
  };
})();