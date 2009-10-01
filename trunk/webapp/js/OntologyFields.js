Mojo.Meta.newClass("MDSS.OntologyFields", {

  Constants : {
  
    DIV_ID : "ontologyFields",
    TABLE_SUFFIX : "_table",
    ROW_SUFFIX : "_row",
    TERM_SUFFIX : "_term",
    TERMNAME_SUFFIX : "_termName"
  },
  
  Instance : {
 
    initialize : function()
    {
      // listen for all click events on main div.
      YAHOO.util.Event.on(this.constructor.DIV_ID, 'click', this._handleClick, null, this);
      
      // Create the one instance of the OntologyBrowser that will
      // be shared by all BrowserRoots. This is done because only one
      // BrowserRoot can be edited at a time.
      this._sharedBrowser = new MDSS.OntologyBrowser(false);
      this._sharedBrowser.setHandler(this._setField, this);
      
      // These fields point to the edit window/fields for the
      // BrowserRoot that is currently being edited.
      this._currentModal = null;
      this._currentBrowserInput = null;
      this._currentBrowserDisplay = null;

      // set the handlers for the BrowserRootController
      this._rootController = Mojo.$.dss.vector.solutions.ontology.BrowserRootController;
      
      // cancel/update actions use the same function
      var cancelB = Mojo.Util.bind(this, this._cancelRoot);
      this._rootController.setCancelListener(cancelB);
      
      // action to open the OntologyBrowser
      var openB = Mojo.Util.bind(this, this._openBrowser);
      this._rootController.setOpenBrowserListener(openB);
      
      var updateB = Mojo.Util.bind(this, this._updateRoot);
      this._rootController.setUpdateListener(updateB); 
    },
    
    _createModal : function(html, closeWin)
    {
      var modal = new YAHOO.widget.Panel("select", {
        width:"400px",
        height: "400px",
        fixedcenter:true,
        close: closeWin || false,
        draggable:false,
        zindex:4,
        modal:true,
        visible:true
      });

      modal.setBody(html);
      modal.render(document.body);
      modal.bringToTop();
      
      return modal;
    },
    
    _handleClick : function(e)
    {
      var el = e.target;
      var Dom = YAHOO.util.Dom;
      
      if(Dom.hasClass(el, 'addRootBtn'))
      {
        var fieldId = el.value;
        this._addRoot(fieldId);
      }
      else if(Dom.hasClass(el, 'editRootBtn'))
      {
        var rootId = el.value;
        this._editRoot(rootId);
      }
      else if(Dom.hasClass(el, 'deleteRootBtn'))
      {
        var rootId = el.value;
        this._deleteRoot(rootId);
      }
    },
    
    _openBrowser : function(params)
    {
      // set the current input fields
      this._currentBrowserInput = params['dto.componentId']+this.constructor.TERM_SUFFIX;
      this._currentBrowserDisplay = params['dto.componentId']+this.constructor.TERMNAME_SUFFIX;

      if(this._sharedBrowser.isRendered())
      {
        this._sharedBrowser.reset();
        this._sharedBrowser.show();
      }
      else
      {
        this._sharedBrowser.render();
      }
      
      // set the default selection (if it exists)
      var termId = document.getElementById(this._currentBrowserInput).value;
      var selected = [];
      if(termId !== '')
      {
        selected.push(termId); 
      }
      this._sharedBrowser.setSelection(selected); 
    },
    
    _setField : function(selected)
    {
      var el = document.getElementById(this._currentBrowserInput);
      var dEl = document.getElementById(this._currentBrowserDisplay);
      if(selected.length > 0)
      {
        var sel = selected[0];
        el.value = sel.getTermId();
        dEl.innerHTML = sel.getTermName() + '('+sel.getTermOntologyId()+')'; 
      }
      else
      {
        el.value = '';
        dEl.innerHTML = '';
      }
    },
    
    /**
     * Removes the create/edit modal and
     * the automatic call to the BrowserRootController
     * will unlock the object if it is not new.
     */
    _cancelRoot : function(params)
    {
      if(params['dto.isNew'] === 'true')
      {
        this._currentModal.destroy();
      }
      else
      {
        var request = new MDSS.Request({
          that:this,
          onSuccess: function()
          {
            this.that._currentModal.destroy();
          }
        });
        
        var id = params['dto.componentId'];
        Mojo.$.dss.vector.solutions.ontology.BrowserRoot.unlock(request, id); 
      }
    },
    
    /**
     * Presents the form to create a new BrowserRoot.
     */
    _addRoot : function(fieldId)
    {
      var request = new MDSS.Request({
        that : this,
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var html = MDSS.util.removeScripts(html);
          
          this.that._currentModal = this.that._createModal(html);
          
          eval(executable);
        } 
      });
      
      // reset the create handler to a curried function with the
      // id of the BrowserField that we're adding a new root to.
      var createB = Mojo.Util.bind(this, this._createRoot, fieldId); 
      this._rootController.setCreateListener(createB);
      this._rootController.newInstance(request);
    },
    
    _editRoot : function(rootId)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(html)
        {
          var executable = MDSS.util.extractScripts(html);
          var html = MDSS.util.removeScripts(html);
          
          this.that._currentModal = this.that._createModal(html);
          
          eval(executable);
        }
      });
      
      this._rootController.edit(request, rootId);
    },
    
    _updateRoot : function(params)
    {
      var request = new MDSS.Request({
        that: this,
        onSuccess : function(rootView)
        {
          var tr = document.getElementById(rootView.getBrowserRootId()+MDSS.OntologyFields.ROW_SUFFIX);
          this.that._populateRow(tr, rootView);
          
          this.that._currentModal.destroy();
        }
      });
      
      // We don't have the DTO object representing the BrowserRoot, so update
      // by populating a new instance and sending that into the update() method.
      var id = params['dto.componentId'];
      var browserRoot = this._populateRoot(params);
      Mojo.$.dss.vector.solutions.ontology.BrowserRoot.update(request, id, browserRoot);
    },
    
    _deleteRoot : function(rootId)
    {
      var request = new MDSS.Request({
        rootId : rootId,
        onSuccess : function()
        {
          var tr = document.getElementById(rootId+MDSS.OntologyFields.ROW_SUFFIX);
          var table = tr.offsetParent;
          table.deleteRow(tr.rowIndex); 
        }
      });

      Mojo.Facade.deleteEntity(request, rootId);      
    },
    
    _populateRoot : function(params)
    {
      var termId = params['dto.term'];
      var selectable = params['dto.selectable'];
      
      var browserRoot = new Mojo.$.dss.vector.solutions.ontology.BrowserRoot();
      browserRoot.setTerm(termId);
      browserRoot.setSelectable(selectable);
      
      return browserRoot;
    },
    
    _populateRow : function(tr, rootView)
    {
      // add a row to the table
      tr.id = rootView.getBrowserRootId()+MDSS.OntologyFields.ROW_SUFFIX;
      
      var html = '';
      html += '<td>'+rootView.getTermName()+' ('+rootView.getTermOntologyId()+')</td>';
      html += '<td>'+rootView.getSelectable()+'</td>';
      html += '<td><button class="editRootBtn" value="'+rootView.getBrowserRootId()+'">'+MDSS.Localized.Edit+'</button></td>';
      html += '<td><button class="deleteRootBtn" value="'+rootView.getBrowserRootId()+'">'+MDSS.Localized.Delete+'</button></td>';
      
      tr.innerHTML = html;
    },
    
    _createRoot : function(fieldId, params)
    {
      var browserRoot = this._populateRoot(params);
      
      var request = new MDSS.Request({
        that : this,
        fieldId : fieldId,
        onSuccess : function(rootView){
        
          var table = document.getElementById(this.fieldId+MDSS.OntologyFields.TABLE_SUFFIX);
          var size = table.getElementsByTagName('tr').length;
          var tr = table.insertRow(size); // append row to end of table        
        
          this.that._populateRow(tr, rootView);
          
          this.that._currentModal.destroy();
        }
      });
      
      Mojo.$.dss.vector.solutions.ontology.BrowserField.addBrowserRoot(request, fieldId, browserRoot);
    }
  }

});
