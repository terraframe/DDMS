Mojo.Meta.newClass("MDSS.OntologyFields", {

  Constants : {
  
    DIV_ID : "ontologyFields",
    TABLE_SUFFIX : "_table",
    ROW_SUFFIX : "_row",
    TERM_SUFFIX : "_term",
    TERMNAME_SUFFIX : "_termName",
    DEFAULT_DISPLAY_SUFFIX : "_defaultDisplay",
    DEFAULT_TERM_SUFFIX : "_defaultTerm"
  },
  
  Instance : {
 
    initialize : function()
    {
      // listen for all click events on main div.
      YAHOO.util.Event.on(this.constructor.DIV_ID, 'click', this._handleClick, null, this);
      
      // Create the one instance of the OntologyBrowser that will
      // be shared by all BrowserRoots. This is done because only one
      // BrowserRoot can be edited at a time.
      this._rootBrowser = new MDSS.OntologyBrowser(false);
      this._rootBrowser.setHandler(this._setField, this);
      
      // Browser to be shared by all fields for setting a default Term
      this._defaultBrowser = new MDSS.OntologyBrowser(false);
      this._defaultBrowser.setHandler(this._setDefault, this);
      
      // These fields point to the edit window/fields for the
      // BrowserRoot that is currently being edited.
      this._currentModal = null;
      this._currentRootInput = 'term';
      this._currentRootDisplay = 'termDisplay';

      // The id of the currently selected MdAttribute (field) in which a default term is being set.
      this._currentDefaultInput = null;
      this._currentDefaultDisplay = null;

      // set the handlers for the BrowserRootController
      this._rootController = Mojo.$.dss.vector.solutions.ontology.BrowserRootController;
      
      // cancel/update actions use the same function
      var cancelB = Mojo.Util.bind(this, this._cancelRoot);
      this._rootController.setCancelListener(cancelB);
      
      var updateB = Mojo.Util.bind(this, this._updateRoot);
      this._rootController.setUpdateListener(updateB);
      
      // add events to open the browser to set the default field terms
      var defaultTerms = YAHOO.util.Selector.query('.defaultTermBrowser');
      Mojo.Iter.forEach(defaultTerms, function(defaultTerm){
      
        var mdAttributeId = defaultTerm.id.replace(this.constructor.DEFAULT_DISPLAY_SUFFIX, '')
        var browser = new MDSS.OntologyBrowser(false, mdAttributeId);
        
        browser.setHandler(this._setDefault, this);
      
        YAHOO.util.Event.on(defaultTerm, 'click', this._openDefaultBrowser, browser, this);
      }, this);
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
      
      // add event to open the browser for roots
      YAHOO.util.Event.on('termBtn', 'click', this._openBrowser, null, this);
      YAHOO.util.Event.on('termDisplay', 'click', this._openBrowser, null, this);
      
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
    
    _openDefaultBrowser : function(e, browser)
    {
      this._currentDefaultDisplay = e.target.id;
      this._currentDefaultInput = 
        this._currentDefaultDisplay.replace(this.constructor.DEFAULT_DISPLAY_SUFFIX, this.constructor.DEFAULT_TERM_SUFFIX);
      
      this._launchBrowser(browser, this._currentDefaultInput);
    },
    
    _openBrowser : function()
    {
      this._launchBrowser(this._rootBrowser, this._currentRootInput);
    },
    
    _launchBrowser : function(browser, termInputId)
    {
      var termId = document.getElementById(termInputId).value;
      var selected = [];
      if(termId !== '')
      {
        selected.push(termId); 
      }

      if(browser.isRendered())
      {
        browser.reset();
        browser.show();
      }
      else
      {
        browser.render();
      }

      browser.setSelection(selected); 
    },
    
    _setDefault : function(selected)
    {
      var mdAttributeId = this._currentDefaultInput.replace(this.constructor.DEFAULT_TERM_SUFFIX, '');
    
      var view = new Mojo.$.dss.vector.solutions.ontology.FieldDefaultView();
      view.setMdAttribute(mdAttributeId);

      var request = new MDSS.Request({
        onSuccess : Mojo.Util.bind(this, this._setInput, selected, this._currentDefaultInput, this._currentDefaultDisplay)
      });

      if(selected.length > 0)
      {
        // set the default term
        var sel = selected[0];
        view.setDefaultValue(sel.getTermId());
        view.applyDefaultValue(request);
      }
      else
      {
        // clear the default term
        view.deleteConcrete(request);
      }
    },
    
    _setField : function(selected)
    {
      this._setInput(selected, this._currentRootInput, this._currentRootDisplay);
    },
    
    _setInput : function(selected, inputId, displayId)
    {
      var el = document.getElementById(inputId);
      var dEl = document.getElementById(displayId);
      if(selected.length > 0)
      {
        var sel = selected[0];
        el.value = sel.getTermId();
        dEl.innerHTML = MDSS.OntologyBrowser.formatLabel(sel);
      }
      else
      {
        el.value = '';
        dEl.innerHTML = MDSS.Localized.no_value;
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
