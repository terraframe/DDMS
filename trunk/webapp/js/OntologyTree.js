Mojo.Meta.newClass("MDSS.OntologyTree", {

  Instance : {
  
    initialize : function(treeViewId)
    {
      // id of the div that will contain the TreeView
      this._treeViewId = treeViewId;
      
      // reference to the TreeView object and ContextMenu
      this._tree = null;
      this._menu = null;
      
      // alias the Term/TermController class for convenience
      this._Term = Mojo.$.dss.vector.solutions.ontology.Term;
      this._controller = Mojo.$.dss.vector.solutions.ontology.TermController;
      
      this._controller.setCreateListener(Mojo.Util.bind(this, this._createListener));
      this._controller.setUpdateListener(Mojo.Util.bind(this, this._updateListener));
      this._controller.setCancelListener(Mojo.Util.bind(this, this._cancelListener));
      
      // The current node of operation (for drag/drop and CRUD operations)
      this._currentNode = null;
      
      // the current node of operation 
      this._selectedNode = null;
    },
    
    /**
     * Handler called when either Yes or No is clicked to
     * confirm that a Term will change its parent.
     */
    _changeParentListener : function(cloneOperation, childNode, parentNode, params)
    {
      var request = new MDSS.Request({
        that: this,
        cloneOperation : cloneOperation,
        childNode : childNode,
        parentNode : parentNode,
        onSuccess : function(view)
        {
          // add the child to the parent
          if(this.parentNode.dynamicLoadComplete)
          {
            // append the child (non-Ajax)
            var node = this.that._createNode(view);
            this.parentNode.appendChild(node);
            
            // BUG FIX: The element that contains the children is hidden
            // if there is only one child and it is already expanded. So force
            // the children element to show.
            if(this.parentNode.children.length === 1)
            {
              this.parentNode.getChildrenEl().style.display = 'block';
            }
            
            // Calling refresh on an unexpanded yet loaded node
            // will cause the children to be displayed but the 
            // parent will not be marked as expanded.
            if(this.parentNode.expanded)
            {
              this.parentNode.refresh();
            }
            else
            {
              this.parentNode.expand();
            }
          }
          else
          {
            this.parentNode.expand();
          }
          
        
          if(!this.cloneOperation)
          {
            // remove the node from the old parent
            this.that._tree.removeNode(this.childNode, true);     
          }
        
          this.that._destroyModal();
        }
      });
    
      var childId = params['childId'];
//      var parentId = params['parentId'];
      var parentId = parentNode.data.termId;
      var oldParentId = childNode.parent.data.termId;
      this._Term.applyWithParent(request, childId, parentId, cloneOperation, oldParentId, null);
    },
    
    /**
     * Listener for a cancel button click. The request
     * is forwarded to the the controller.
     */
    _cancelListener : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function()
        {
          this.that._destroyModal();
        }
      });
      
      return request; 
    },
    
    _createListener : function(params)
    {
      var parentId = this._selectedNode.data.termId;
      params['parentId'] = parentId;
      
      var that = this;
      
      var request = new MDSS.Request({
        onSuccess : function(termId)
        {
          var request2 = new MDSS.Request({
            onSuccess : function(query){
            
              var view = query.getResultSet()[0];
            
              // append the child to all nodes that map to the Term's parent
              var nodes = that._tree.getNodesByProperty('termId', parentId);
              Mojo.Iter.forEach(nodes, function(parent){
              
                if(parent.dynamicLoadComplete)
                {
                  // append the child (non-Ajax)
                  var node = that._createNode(view);
                  parent.appendChild(node);
                  
                  // BUG FIX: The element that contains the children is hidden
                  // if there is only one child and it is already expanded. So force
                  // the children element to show.
                  if(parent.children.length === 1)
                  {
                    parent.getChildrenEl().style.display = 'block';
                  }
                  
                  // Calling refresh on an unexpanded yet loaded node
                  // will cause the children to be displayed but the 
                  // parent will not be marked as expanded.
                  if(parent.expanded)
                  {
                    parent.refresh();
                  }
                  else
                  {
                    parent.expand();
                  }
                }
                else if(parent === that._selectedNode)
                {
                  // force re-expansion (Ajax)
                  parent.expand();
                }
                
              }, that);

              that._destroyModal();            
            }
          });
        
          that._Term.getByIds(request2, [termId]);
        }
      });
      
      return request;
    },
    
    _updateListener : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function()
        {
          this.that._destroyModal();
          this.that._updateTermHTML();
        }
      });
      
      return request; 
    },
    
    /**
     * Updates the view for the currently selected term node by
     * fetching a fresh view, updating the html.
     */
    _updateTermHTML : function()
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(query)
        {
          var view = query.getResultSet()[0];
          
          // update all nodes referencing this term
          var html = this.that._createNodeHTML(view);
          
          var nodes = this.that._tree.getNodesByProperty('termId', view.getTermId());
          Mojo.Iter.forEach(nodes, function(node){
            
            node.setHtml(html); 
          }, this.that);
        }
      });
    
      var id = this._selectedNode.data.termId;
      this._Term.getByIds(request, [id]);
    },
    
    _createNodeHTML : function(view)
    {
      var className = view.getInactive() ? 'inactiveEntity' : 'activeEntity';
      
      return "<div class='"+className+"'>"+MDSS.OntologyBrowser.formatLabelFromView(view)+"</div>";
    },
    
    /**
     * Creates an HTML node from the the given TermView.
     * If pureObject is set to true, then the returned node
     * will be an object literal as opposed to a HTMLNode.
     */
    _createNode : function(view, pureObject)
    {
      var html = this._createNodeHTML(view);
      
      var data = {termId: view.getTermId()};
      
      if(pureObject)
      {
        return {type: 'HTML', html: html, data: data};
      }
      else
      {
        return new YAHOO.widget.HTMLNode({html: html, data: data});
      }
    },
    
    /**
     * Event handler for a triggered context menu. This method
     * cancels the action if the menu was not triggered on a node.
     */
    _nodeMenuSelect : function(a, b, c, d)
    {
      var oTarget = this._menu.contextEventTarget;
      var htmlNode = YAHOO.util.Dom.hasClass(oTarget, "ygtvhtml") ? oTarget : YAHOO.util.Dom.getAncestorByClassName(oTarget, "ygtvhtml");
      if (htmlNode) {
        this._selectedNode = this._tree.getNodeByElement(htmlNode);
        
        // disable the delete menu item for the root
        if(this._selectedNode.parent.isRoot())
        {
          this._menu.itemData[2].cfg.setProperty('disabled', true);
        }
        else
        {
          this._menu.itemData[2].cfg.setProperty('disabled', false);
        }
        
        this._menu.bringToTop();
      }
      else {
        this._menu.cancel();
      }
    },
    
    /**
     * Loads the children of the given parentNode into the tree.
     */
    _dynamicLoad : function(parentNode, fnLoadComplete)
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(query)
        {
          var views = query.getResultSet();
          Mojo.Iter.forEach(views, function(view){
         
            var node = this._createNode(view); 
            parentNode.appendChild(node);
          }, this.that);
          
          fnLoadComplete(); // inform the TreeView the loading is complete
          parentNode.refresh();
        }
      });
      
      var termId = parentNode.data.termId;
      this._Term.getOntologyChildren(request, termId, false);
    },
    
    /**
     * Handler for the drop operation. The *this* reference is that of the
     * DDNodeProxy object that wraps the draggable node. The id parameter
     * is the id of the parent node on which the draggable node is being dropped.
     * 
     * The first parameter is the *this* reference of the OntologyTree instance.
     */
    _dragDropHandler : function(source, id)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess: function(html)
        {
           this.that._createModal(html, true, true);
        }
      });

      var childNode = source.node;
      var childId = childNode.data.termId;
      
      var newParentEl = document.getElementById(id);
      var newParentNode = source.node.tree.getNodeByElement(newParentEl);

      var oldParentNode = childNode.parent;
      var oldParentId = oldParentNode.data.termId;
      
      // Change the listeners to contain the relevant nodes and info
      var termController = Mojo.$.dss.vector.solutions.ontology.TermController;
      termController.setDoCloneListener(Mojo.Util.bind(this, 
        this._changeParentListener, true, childNode, newParentNode));
      termController.setDoNotCloneListener(Mojo.Util.bind(this,
        this._changeParentListener, false, childNode, newParentNode));      
      
      Mojo.$.dss.vector.solutions.ontology.TermController.confirmChangeParent(request, childId, oldParentId);
    },
    
    _addNodeHandler : function()
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess: function(html)
        {
           this.that._createModal(html, false, true);
        }     
      });
      
      this._controller.newInstance(request);
    },
    
    /**
     * Gives the edit screen for the current node of operation.
     */
    _editNodeHandler : function()
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess: function(html)
        {
           this.that._createModal(html, false);
        }
      });
      
      var termId = this._selectedNode.data.termId;
      this._controller.edit(request, termId);
    },
    
    /**
     * Deletes a node from both the database and DOM.
     */
    _deleteNodeHandler : function()
    {
      var termId = this._selectedNode.data.termId;
      var parentId = this._selectedNode.parent.data.termId;    
    
      var request = new MDSS.Request({
        that: this,
        onSuccess: function()
        {
          var that = this.that;
          that._tree.removeNode(that._selectedNode, true);
        },
        onConfirmDeleteTermException : function(e)
        {
          var modal = new YAHOO.widget.Panel("confirmDelete", {
            fixedcenter: true,
            width: '300px',
            visible: true,
            draggable: false,
            zindex: 8000,
            modal:true
          });
  
          var upperDiv = document.createElement('div');
          YAHOO.util.Dom.addClass(upperDiv, 'modalAlertBox');

          var message = document.createElement('span');
          message.innerHTML = e.getLocalizedMessage();
          upperDiv.appendChild(message);
  
          // yes/no buttons
          var lowerDiv = document.createElement('div');
          YAHOO.util.Dom.addClass(lowerDiv, 'modalAlertBox');
  
          var that = this.that;
  
          var delObj = {
            deleteTerm:true,
            childId:termId,
            parentId:parentId,
            modal:modal
          }
          var delTerm = document.createElement('input');
          YAHOO.util.Dom.setAttribute(delTerm, 'type', 'button');
          YAHOO.util.Dom.setAttribute(delTerm, 'value', MDSS.localize('Delete_Term'));
          YAHOO.util.Event.on(delTerm, 'click', that._deleteAfterConfirmation, delObj, that);
          lowerDiv.appendChild(delTerm);
  
          delObj = {
            deleteTerm:false,
            childId:termId,
            parentId:parentId,
            modal:modal
          };
          
          var delRel = document.createElement('input');
          YAHOO.util.Dom.setAttribute(delRel, 'type', 'button');
          YAHOO.util.Dom.setAttribute(delRel, 'value', MDSS.localize('Delete_Relationship'));
          YAHOO.util.Event.on(delRel, 'click', that._deleteAfterConfirmation, delObj, that);
          lowerDiv.appendChild(delRel);
  
          var wrapperDiv = document.createElement('div');
          wrapperDiv.appendChild(upperDiv);
          wrapperDiv.appendChild(lowerDiv);
  
          modal.bringToTop();
          modal.setBody(wrapperDiv);
          modal.render(document.body);
        },
      });
    
      this._Term.confirmDeleteTerm(request, termId, parentId);
    },
    
    _deleteAfterConfirmation : function(e, obj)
    {
      var childId = obj.childId;
      var parentId = obj.parentId;
      var deleteTerm = obj.deleteTerm;
      var modal = obj.modal;
      
      var request = new MDSS.Request({
        that : this,
        onSuccess : function()
        {
          var that = this.that;
          
          // Remove the node(s) associated with the Term
          if(deleteTerm)
          {
            var nodes = this.that._tree.getNodesByProperty('termId', childId);
              Mojo.Iter.forEach(nodes, function(node){
            
                that._tree.removeNode(node, true);
            }, that);
          }
          else
          {
            // remove the node from the old parent
            that._tree.removeNode(that._selectedNode, true);   
          }

          modal.destroy();
        }
      });
      
      if(deleteTerm)
      {
        Mojo.$.dss.vector.solutions.ontology.Term.deleteTerm(request, childId);
      }
      else
      {
        Mojo.$.dss.vector.solutions.ontology.Term.deleteRelationship(request, childId, parentId);
      }
    },
    
    /**
     * Creates a modal with the given html. Any scripts in the
     * html will be extracted and executed after the modal is rendered. 
     */
    _createModal : function(html, useSmall, closeIt)
    {
      var executable = MDSS.util.extractScripts(html);
      html = MDSS.util.removeScripts(html);
      
      var id = new String(Math.random()).substring(2);
      this._panel = new YAHOO.widget.Panel(id,  {
          width: (useSmall ? '300px' : '400px'),
          height: (useSmall ? '200px' : '410px'),
          fixedcenter:true,
          close: closeIt || false,
          draggable:false,
          zindex:4,
          modal:true,
          visible:true
        });

      if(!useSmall)
      {
        html = '<h3>&nbsp;</h3><div class="innerContentModal">' + html +"</div>";
      }
      
      this._panel.subscribe('hide', function(){
        var that = this;
        setTimeout(function(){
          that.destroy();
        }, 15);
      });

      this._panel.setBody(html);
      this._panel.render(document.body);
      this._panel.bringToTop();
      
      eval(executable);
    },
    
    /**
     * Destroys the modal and nullifies the reference.
     */
    _destroyModal : function()
    {
      this._panel.destroy();
      this._panel = null;
    },
    
    /**
     * Instantiates the tree and sets its roots and dynamic load callback.
     */
    _setupTree : function(roots)
    {
      this._tree = new YAHOO.widget.TreeViewDD(this._treeViewId, roots, Mojo.Util.bind(this, this._dragDropHandler));
          
      var loadB = Mojo.Util.bind(this, this._dynamicLoad);
      this._tree.setDynamicLoad(loadB);
      this._tree.render();
      
      this._setupMenu();
    },

    _setupMenu : function()
    {
      var itemData = [];
      
      var importMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Import_Button'));
      importMenuItem.subscribe("click", Mojo.Util.bind(this, this._uploadImport));
      itemData.push(importMenuItem);
      
      var exportMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Export_Button'));
      exportMenuItem.subscribe("click", Mojo.Util.bind(this, this._doExport));
      itemData.push(exportMenuItem);
    
      var createMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Create'));
      createMenuItem.subscribe("click", Mojo.Util.bind(this, this._addNodeHandler));
      itemData.push(createMenuItem);
  
      var editMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Edit'));
      editMenuItem.subscribe("click", Mojo.Util.bind(this, this._editNodeHandler));
      itemData.push(editMenuItem);
  
      var deleteMenuItem = new YAHOO.widget.ContextMenuItem(MDSS.localize('Tree_Delete'));
      deleteMenuItem.subscribe("click", Mojo.Util.bind(this, this._deleteNodeHandler));
      itemData.push(deleteMenuItem);
  
      this._menu = new YAHOO.widget.ContextMenu("ontologyTreeMenu", {
        trigger:this._treeViewId,
        lazyload:true,
        itemdata: itemData,
        zindex:500
      });
  
      this._menu.subscribe("triggerContextMenu", Mojo.Util.bind(this, this._nodeMenuSelect));
    },
    
    /**
     * Renders the tree by fetching all root Terms and appending
     * them as the first level nodes on the tree.
     */
    render : function()
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(query)
        {
          var views = query.getResultSet();
          var roots = Mojo.Iter.map(views, function(view){
          
            // add roots to the virtual root (YUI requires object literals
            // for nodes in the TreeView constructor).
            return this._createNode(view, true);
          }, this.that);
          
          this.that._setupTree(roots);
        } 
      });
      this._Term.getDefaultRoots(request);
    },
    
    /**
     * Invoked when the user clicks "export" on the context menu.
     */
    _doExport : function()
    {
//      var request = new MDSS.Request({
//        that: this,
//        onSuccess: function(html)
//        {
//           this.that._createModal(html, false, true);
//        }     
//      });
//      
//      this._controller.exportTerms(request);
    },
    
    /**
     * Action to upload a template file.
     */
    _uploadImport : function()
    {
      if (this._uploadModal == null)
      {
        var formId = 'importUploadForm';
        var action = 'dss.vector.solutions.ontology.TermController.importTerms.mojo';

        var html = MDSS.localize('File_Upload_Status') + ":<br />";
        html += "<iframe name='importIframe' id='importIframe' style='height:200px; width:350px; margin-bottom: 15px'></iframe>";
        html += "<form action='" + action + "' enctype='multipart/form-data' target='importIframe' id='" + formId + "' method='post'>";
        html += "<input type='file' name='importFile' id='importFile' /><br />";
        html += "<input type='submit' name='import' value='" + MDSS.localize('Submit') + "' />"
        html += "</form>";

        this._uploadModal = new YAHOO.widget.Panel("uploadTemplateModal", {
          width : "400px",
          height : "400px",
          fixedcenter : true,
          close : true,
          draggable : false,
          zindex : 8,
          modal : true,
          visible : true
        });

        // wrap content in divs
        var outer = document.createElement('div');

        var header = document.createElement('div');
        header.innerHTML = '<h3>' + MDSS.localize('Upload_Template') + '</h3><hr />';
        outer.appendChild(header);

        var contentDiv = document.createElement('div');
        YAHOO.util.Dom.addClass(contentDiv, 'innerContentModal');
        contentDiv.innerHTML = html;
        outer.appendChild(contentDiv);

        var that = this;
        this._uploadModal.subscribe('hide', function()
        {
          that._uploadClearStatus();

          that._selectedNode.collapse();
          that._selectedNode.dynamicLoadComplete = false;
          that._selectedNode.expanded = false;
          that._selectedNode.expand();
        });
        this._uploadModal.setBody(outer);
        this._uploadModal.render(document.body);
        this._uploadModal.bringToTop();

        YAHOO.util.Event.on('importFile', 'change', this._uploadClearStatus, null, this);
        YAHOO.util.Event.on(formId, 'submit', this._uploadImportOnSubmit, null, this);
      } 
      else
      {
        this._uploadModal.show();
      }
    },
        
    _uploadClearStatus : function(e)
    {
      // Clear out the status list of any existing imports
      var myIFrame = document.getElementById('importIframe');

      if (myIFrame != null)
      {
        myIFrame.contentWindow.document.body.innerHTML = "";
      }    	
    },

    _uploadImportOnSubmit : function(e)
    { 
      this._uploadClearStatus();
      
      return true;
    }
  }
});