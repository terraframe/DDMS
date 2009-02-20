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
YAHOO.widget.DDNode = function() { }

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
    if (!this.isRoot()) {     
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
      // remove drag-hint class
      this.getElDom(id).className = this.getElDom(id).classNameBeforeDrag;
      
      destNode = YAHOO.util.DDM.getDDById(id).node;
      
      // remove
      thisParent = this.node.parent;
      this.node.tree.popNode(this.node);
      
      // fixes bug where a parent with 1 item is still marked
      // expanded, so when a new node is dragged to it, it doesn't
      // draw the new node (thinks it's already expanded)
      if (thisParent.children.length == 0) {
        thisParent.expanded = false;
      }
      
      // make removal changes visible
      thisParent.refresh();
      
      // add
      this.node.appendTo(destNode);
      
      //this.node.tree.draw();
      destNode.expand();
      destNode.refresh();
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

YAHOO.namespace("GeoEntityTree");
YAHOO.GeoEntityTree = (function(){

  // The selected node in tree
  var selectedNode = null;
  
  // The tree for GeoEntities
  var geoTree = null;
  
  /**
   * Adds a new node to the selected node (a refresh and expand is performed).
   */
  function _addNodeHandler()
  {
    new YAHOO.widget.HTMLNode('new node', selectedNode);
    selectedNode.refresh();
    selectedNode.expand();
  }
  
  /**
   * Deletes the selected node from the tree.
   */
  function _deleteNodeHandler()
  {
    var parent = selectedNode.parent;
    geoTree.removeNode(selectedNode);
    parent.refresh();
  }
  
  /**
   * Event handler for a triggered context menu. This method
   * cancels the action if the menu was not triggered on a node.
   */
  function _nodeMenuSelect()
  {
    var oTarget = this.contextEventTarget;

    var oTextNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvhtml") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvhtml");
    if (oTextNode) {
      selectedNode = geoTree.getNodeByElement(oTextNode);
    }
    else {
      this.cancel();
    }
  }
  
  /**
   * Initializes the tree with the given data.
   */
  function _initializeTree(data) {
    geoTree = new YAHOO.widget.TreeViewDD('treeView', data);
    geoTree.draw();

    var menu = new YAHOO.widget.ContextMenu("treeMenu", {
      trigger:'treeView',
      lazyload:true,
      itemdata:[{text:"Create Node", onclick: {fn:_addNodeHandler}}, {text:"Delete Node", onclick: {fn:_deleteNodeHandler}}]
    });
      
    menu.subscribe("triggerContextMenu", _nodeMenuSelect);
  }
  
  // return all public methods/properties
  return {
    initializeTree : _initializeTree
  };
})();