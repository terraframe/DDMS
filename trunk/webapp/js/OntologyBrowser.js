Mojo.Meta.newClass("MDSS.OntologyBrowser", {

  Constants : {
  
    ENTRY_SUFFIX : '_entry',
    BREADCRUMB_SUFFIX : '_breadcrumb',
    SELECT_SUFFIX : '_select',
    DELETE_SUFFIX : '_select',
    SELECTION_SUFFIX : '_selection'
  },

  Instance : {
    
    initialize : function(multipleSelect, className, attributeName)
    {
      // map of termId, termName
      this._cache = {};
      
      // map of termId to its children 
      this._childCache = {};
      
      // temporary cache of TermViews while a search is being performed
      this._searchCache = {};
    
//      this._ontology = ontology;
//      this._instance = instance;
//      this._relationship = relationship;
      this._multipleSelect = multipleSelect || false;
      
      // is this browser to render all terms (for field admin) or
      // is it to render specific roots for a class attribute?
      this._defaultRoot = arguments.length === 1;
      this._className = className;
      this._attributeName = attributeName;
      
      this._rendered = false;
      
      this._panel = null;
      this._id = new String(Math.random()).substring(2);
      this._breadcrumbId = this._id+'_breadcrumb';
      this._contentId = this._id+'_contentId';
      this._selectionId = this._id+'_selection';
      this._backButton = this._id+'_back';
      this._saveButton = this._id+'_save';
      this._cancelButton = this._id+'_cancel';
      this._searchInput = this._id+'_search';
      
      this._ROOT = "ROOT";
      
      // selected terms
      this._selection = {};
      
      // breadcrumb stack
      this._breadcrumbs = [];
      
      // handler invoked during a save action.
      this._customHandler = null;
      
      this._searchPanel = null;
    },
    
    show : function()
    {
      this._panel.show();
      this._panel.bringToTop();
    },
    
    hide : function()
    {
      this._panel.hide();
    },
    
    /**
     * Sets the content of the main pane with a list of items
     * that map to the given TermView objects.
     */
    _setContent : function(views)
    {
      var nodes = Mojo.Iter.map(views, function(view){
            
         return this._createTermEntry(view); 
      }, this);
          
      var content = document.getElementById(this._contentId);
      content.innerHTML = nodes.join('');
    },

    /**
     * Fetches and populates the root nodes for the ontology browser.
     * If no restricting class and attribute are specified then the
     * system-wide default roots are returned (terms that are never
     * children in a relationship).
     */
    _getRootContent : function()
    {
      var cached = this._getCachedChildren(this._ROOT);
      if(cached)
      {
        this._setContent(cached);
      }
      else
      {
        var request = new MDSS.Request({
          that : this, 
          onSuccess : function(roots)
          {
            this.that._setCachedChildren(this.that._ROOT, roots);
            this.that._setContent(roots);
          }
        });
     
        if(this._defaultRoot)
        {
          Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getDefaultRoot(request);
        }
        else
        {
          Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getAttributeRoots(request, this._className, this._attributeName);
        }
      }
    },
    
    _setCachedChildren : function(parentId, childViews)
    {
      var children = Mojo.Iter.map(childViews, function(view){
     
        var toCache = view;
        if(view instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
        {
          // convert a BrowserRootView into a TermView
          var nView = new Mojo.$.dss.vector.solutions.ontology.TermView();
          nView.setTermName(view.getTermName());
          nView.setTermId(view.getTermId());
          nView.setTermOntologyId(view.getTermOntologyId());
          
          toCache = nView;
        }
        
        // add each child to the cache
        this._cacheSet(view.getTermId(), toCache);
        return view;
        
      }, this); 
      
      
      this._childCache[parentId] = children; 
    },
    
    _getCachedChildren : function(parentId)
    {
      return this._childCache[parentId];
    },
    
    _cacheSet : function(termId, view)
    {
      this._cache[termId] = view;
    },
    
    _cacheGet : function(termId)
    {
      return this._cache[termId];
    },
    
    _doTermSelectHandler : function(e)
    {
      var el = e.target;
      if(el.nodeName === 'SPAN')
      {
        var termId = el.id.replace(this.constructor.ENTRY_SUFFIX, '');
        this._doTermSelect(termId);
      }
      else if(el.nodeName === 'IMG')
      {
        var termId = el.id.replace(this.constructor.SELECT_SUFFIX, '');
        this._addToSelection(termId);
      }
    },
    
    _addToSelection : function(termId)
    {
      if(this._selection[termId])
      {
        // term has already been added
        return;
      }
      
      var selection = document.getElementById(this._selectionId);
      
      if(!this._multipleSelect)
      {
        // reset entire selection
        this._selection = {};
        selection.innerHTML = '';
      }

      this._selection[termId] = this._cacheGet(termId);
      
      var li = this._getSelectionLi(termId);
      selection.appendChild(li);
      
      // move y scrollbar down to last selection
      var divParent = selection.parentNode; // containing div
      var sHeight = divParent.scrollHeight;
      var oHeight = divParent.offsetHeight;
      if(sHeight > oHeight)
      {
        divParent.scrollTop = sHeight - oHeight;
      }
    },
    
    _doTermSelect : function(termId, dontAdd)
    {
      var cached = this._getCachedChildren(termId);
      if(cached)
      {
        this._setContent(cached);
      }
      else
      {
        var request = new MDSS.Request({
          that: this,
          termId : termId,
          dontAdd : dontAdd,
          onSuccess : function(query)
          {
            var results = query.getResultSet();
            this.that._setCachedChildren(this.termId, results);
            this.that._setContent(results);
          }
        });
    
        Mojo.$.dss.vector.solutions.ontology.Term.getOntologyChildren(request, termId);
      }
      
      // push selected node onto breadcrumbs
      if(!dontAdd)
      { 
        var term = this._cacheGet(termId);
        this._breadcrumbs.push(term);
      }
      
      this._resetBreadcrumbs();
    },
    
    /**
     * Adds an individual Term as the last child
     * of the selection list.
     */
    _getSelectionLi : function(termId)
    {
      var term = this._cacheGet(termId);
    
      var imgId = termId+this.constructor.DELETE_SUFFIX;
      var liId = termId+this.constructor.SELECTION_SUFFIX;
      var img = '<img src="imgs/icons/delete.png" style="margin-right: 5px" id="'+imgId+'" />';
      
      var li = document.createElement('li');
      li.id = liId;
      li.innerHTML = img+term.getTermName()+ ' ('+term.getTermOntologyId()+')';
      
      return li;
    },
    
    newSpan : function(content, id)
    {
      return '<span id="'+id+'" class="linkify">'+content+'</span>';
    },
    
    _resetBreadcrumbs : function()
    {
      var el = document.getElementById(this._breadcrumbId);
      var breadcrumbs = this._getBreadcrumbs();
      el.innerHTML = breadcrumbs;
      
      // move scroll bar down as needed
      var divParent = el.parentNode;
      var sHeight = divParent.scrollHeight;
      var oHeight = divParent.offsetHeight;
      if(sHeight > oHeight)
      {
        divParent.scrollTop = sHeight - oHeight;
      }
    },
    
    _getBreadcrumbs : function()
    {
      var last = this._breadcrumbs.length-1;
      var breadcrumbs = Mojo.Iter.map(this._breadcrumbs, function(breadcrumb, ind){
        
        var span = this.newSpan(breadcrumb.getTermName(), 
          breadcrumb.getTermId()+this.constructor.BREADCRUMB_SUFFIX);
        return '<li class="breadcrumbNavItem'+(ind === last ? ' currentBreadcrumbsNav' : '')+'">'+span+'</li>'; 
      }, this); 
      
      return breadcrumbs.join('<li class="termDelimeter">/</li>');
    },
    
    render : function()
    {
      if(!this.constructor.typesImported)
      {
        var request = new MDSS.Request({
          that: this,
          onSuccess : function(){
            this.that.constructor.typesImported = true;
            this.that.render(); // now we can safely render the browser
          }
        });
  
        var pck = 'dss.vector.solutions.ontology.';
        var types = [pck+'BrowserRoot', pck+'BrowserRootView', pck+'TermView', pck+'Term'];
        Mojo.Facade.importTypes(request, types, {autoEval:true});
        return;
      }
      
      // add an artificial TermView as the root
      var rootView = new Mojo.$.dss.vector.solutions.ontology.TermView();
      rootView.setTermId(this._ROOT);
      rootView.setTermName(MDSS.Localized.ROOT);
      rootView.setTermOntologyId(this._ROOT);
      
      this._breadcrumbs.push(rootView);    
    
      this._panel = new YAHOO.widget.Panel(this._id,  {
        width: '400px',
        height: '400px',
        fixedcenter:true,
        close:false,
        draggable:false,
        zindex:4,
        modal:true,
        visible:true
      });
      
      var html = '';
      html += '<div class="browserWrapper">';
      html += '  <div class="browserBack">';
      html += '     <button id="'+this._backButton+'">&larr;</button>';
      html += '  </div>';
      html += '  <div class="browserBreadcrumbs autosize">';
      html += '    <div>'; // wrapper div for auto-sizing
      html += '    <div class="browserResizeRestrict">'; // restricts auto-sizing
      html += '    <ul class="breadcrumbsNav" id="'+this._breadcrumbId+'">';
      html += this._getBreadcrumbs();
      html += '    </ul>';
      html += '    </div>';
      html += '    </div>';
      html += '  </div>';
      html += '  <div class="browserSearch">';
      html += '    <span>'+MDSS.Localized.Search+':</span>';
      html += '    <input type="text" id="'+this._searchInput+'" class="browserSearchInput" />';
      html += '  </div>';
      html += '  <div class="browserContent">';
      html += '    <ul class="currentNodes" id="'+this._contentId+'">';
      html += '    </ul>';
      html += '  </div>';
      html += '  <div class="browserSelection">';
      html += '    <ul class="selectedNodes" id="'+this._selectionId+'">';
      html += '    </ul>';
      html += '  </div>';
      html += '  <div class="browserButtons">';
      html += '    <input type="button" id="'+this._cancelButton+'" class="browserCancelButton" value="'+MDSS.Localized.Cancel+'" />';
      html += '    <input type="button" id="'+this._saveButton+'" class="browserSaveButton" value="'+MDSS.Localized.save+'" />';
      html += '  </div>';
      html += '</div>';
      
      this._panel.setBody(html);
      this._panel.render(document.body);
      this._panel.bringToTop();
     
      this._hookEvents();
      
      this._attachSearch();      
      this._getRootContent();
      
      this._rendered = true;
    },
    
    _displayFunction : function(view)
    {
      return view.getTermName() + ' ('+view.getTermOntologyId()+')';
    },
    
    _listFunction : function(view)
    {
      this._searchCache[view.getTermId()] = view;
    
      return view.getTermName() + ' ('+view.getTermOntologyId()+')';
    },
    
    _idFunction : function(view)
    {
      return view.getTermId();
    },
    
    _selectEventHandler : function(selected)
    {
      var termId = selected.id;
      
      // move the selected TermView from the search cache into the main cache
      // and reset the search cache to free memory
      this._cache[termId] = this._searchCache[termId];
      this._searchCache = {};

      document.getElementById(this._searchInput).value = '';

      this._addToSelection(termId);
    },
    
    _searchFunction : function(request, value)
    {
      Mojo.$.dss.vector.solutions.ontology.Term.searchTerms(request, value, "");
    },
    
    _attachSearch : function()
    {
      var displayElement = document.getElementById(this._searchInput);
      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._listFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sEH = Mojo.Util.bind(this, this._selectEventHandler);
      
      this._searchPanel = new MDSS.GenericSearch(displayElement, null, lF, dF, iF, sF, sEH);
      
      YAHOO.util.Event.on(this._searchInput, 'keyup', this._searchPanel.performSearch, null, this._searchPanel); 
    },
    
    isRendered : function()
    {
      return this._rendered;
    },
    
    /**
     * Resets the browser to its default state, meaning
     * the breadcrumbs will go back to root, the default
     * roots will be displayed, and no terms will be shown
     * as selected.
     */
    reset : function()
    {
      this._breadcrumbs = this._breadcrumbs.slice(0, 1);
    
      this._resetToDefault();
      this._resetSelection();
    },
    
    /**
     * Sets the currently selected Term(s) by providing
     * an array of term ids.
     */
    setSelection : function(termIds)
    {
      var toFetch = [];
      var cached = [];
      
      Mojo.Iter.forEach(termIds, function(termId){
      
        var term = this._cacheGet(termId);
        if(term)
        {
          cached.push(term);
        }
        else
        {
          toFetch.push(termId);
        }
        
        
      }, this);
      
      // there items to fetch, so hit the server
      if(toFetch.length > 0)
      {
        var request = new MDSS.Request({
          that: this,
          cached : cached,
          onSuccess : function(query)
          {
            var views = query.getResultSet();
            
            // combine the cached and fetched views and sort
            // them by term name.
            var total = this.cached.concat(views);
            total.sort(function(term1, term2){
              var t1 = term1.getTermName();
              var t2 = term2.getTermName();
              if(t1 > t2)
              {
                return 1;
              }
              else if(t1 < t2)
              {
                return -1;
              }
              else
              {
                return 0;
              }
            });
            
            // Now reset the selection
            Mojo.Iter.forEach(total, function(view){
              
              // must add term to the cache first
              var termId = view.getTermId();
              this._cacheSet(termId, view);
              
              this._addToSelection(termId); 
            }, this.that);
          }
        });
        
        Mojo.$.dss.vector.solutions.ontology.Term.getByIds(request, toFetch);
      }
      else
      {
        // no items to fetch, so refresh the selection with the
        // cached items
        Mojo.Iter.forEach(cached, function(view){
          this._addToSelection(view.getTermId()); 
        }, this);
      }
    },
    
    _resetSelection : function()
    {
      this._selection = {};
      var selection = document.getElementById(this._selectionId);
      selection.innerHTML = '';
    },
    
    isMultiSelect : function() {
      return this._multipleSelect;
    },
    
    setHandler : function(handler, context)
    {
      this._customHandler = Mojo.Util.bind(context || this, handler);
    },
    
    _save: function()
    {
      if(Mojo.Util.isFunction(this._customHandler));
      {
        var selected = Mojo.Util.getValues(this._selection);
        this._customHandler(selected);
      }
      
      this.hide();
    },
    
    _hookEvents : function()
    {
      var Event = YAHOO.util.Event;
      Event.on(this._breadcrumbId, 'click', this._doNavigate, null, this);
      Event.on(this._contentId, 'click', this._doTermSelectHandler, null, this);
      Event.on(this._selectionId, 'click', this._doSelectionAction, null, this);
      Event.on(this._backButton, 'click', this._goBack, null, this);
      Event.on(this._saveButton, 'click', this._save, null, this);
      Event.on(this._cancelButton, 'click', this.hide, null, this);
    },
    
    _createTermEntry : function(view)
    {
      var li;
      if(view instanceof Mojo.$.dss.vector.solutions.ontology.TermView || view.getSelectable())
      {
        var imgId = view.getTermId()+this.constructor.SELECT_SUFFIX;
        li = '<li><img src="imgs/icons/add.png" style="margin-right: 5px" id="'+imgId+'" />';
      }
      else
      {
        li = '<li style="padding-left: 21px">';
      }
      
      var content = view.getTermName() + ' ('+view.getTermOntologyId()+')';
      return li+this.newSpan(content, view.getTermId()+this.constructor.ENTRY_SUFFIX)+'</li>';
    },
    
    _goBack : function(e)
    {
      var size = this._breadcrumbs.length;
      if(size === 1)
      {
        // can't go back beyond root
        return;
      }
      
      // remove last entry and use next to last as new endpoint
      this._breadcrumbs.pop();
      size = this._breadcrumbs.length;
      if(size === 1)
      {
        this._resetToDefault();
      }
      else
      {
        var termId = this._breadcrumbs[size-1].id;
        this._doTermSelect(termId, true);
      }
    },
    
    _doNavigate : function(e)
    {
      var el = e.target;
      if(el.nodeName !== 'SPAN')
      {
        return;
      }
     
      var termId = el.id.replace(this.constructor.BREADCRUMB_SUFFIX, '');
      
      var isRoot = false;
      var newEndInd;
      if(termId === this._ROOT)
      {
        newEndInd = 1;
        isRoot = true;
      }
      else
      {
        Mojo.Iter.forEach(this._breadcrumbs, function(breadcrumb, ind){
          
          if(breadcrumb.getTermId() === termId)
          {
            newEndInd = ind;
          }
        }, this);
      }
      
      // cut back the breadcrumbs to the last selection
      this._breadcrumbs = this._breadcrumbs.slice(0, newEndInd);

      if(isRoot)
      {
        this._resetToDefault();
      }
      else
      {
        this._doTermSelect(termId);
      }
    },
    
    _resetToDefault : function()
    {
      // reset with the root nodes
      this._getRootContent();
      this._resetBreadcrumbs();
    },
    
    _doSelectionAction : function(e)
    {
      var el = e.target;
      if(el.nodeName !== 'IMG')
      {
        return;
      }
      
      var termId = el.id.replace(this.constructor.DELETE_SUFFIX, '');
      delete this._selection[termId];
      
      var li = el.parentNode;
      li.parentNode.removeChild(li);
    },
    
    isRendered : function()
    {
      return this._rendered;
    }
  },
  Static : {
  
    typesImported : false, 
    formatLabel : function(term)
    {
      return term.getTermName() + ' ('+term.getTermOntologyId()+')';
    }
  }
});

Mojo.Meta.newClass("MDSS.GenericOntologyBrowser", {
  Instance : {
    initialize : function(className, configs) {
      configs = Mojo.Util.isArray(configs) ? configs : [configs];
    
      Mojo.Iter.forEach(configs, function(config){
        var attributeName = config.attributeName;
        var multipleSelect = Mojo.Util.isBoolean(config.multipleSelect) ? config.multipleSelect : false;
        var attributeClass = Mojo.Util.isString(config.className) ? config.className : className;
        var browserField = Mojo.Util.isString(config.browserField) ? config.browserField : config.attributeName;        
        
        var browser = new MDSS.OntologyBrowser(multipleSelect, attributeClass, browserField);
            
        browser.setHandler(Mojo.Util.curry(this.setField, attributeName));
     
        YAHOO.util.Event.on(attributeName + 'Btn', "click", this.openBrowser, {browser:browser, attributeName:attributeName});
      }, this);    
    },

    setField : function(attribute, selected) {
      // this: the browser instances
    	
      var attributeEl = document.getElementById(attribute);        
      var displayEl = document.getElementById(attribute + 'Display');        

      if(this.isMultiSelect()) {
          if(selected.length > 0) {
            var innerHTML = '';
            var displayHTML = '';

        	for(var i = 0; i < selected.length; i++) {
        	  var sel = selected[i];
        	  var component = attribute + '_' + i;
        		
        	  innerHTML += '<input type="hidden" class="' + attribute + '" name="' + component + '.componentId" value="' + sel.getTermId() + '" />\n';
        	  innerHTML += '<input type="hidden" name="' + component + '.isNew" value="false" />\n';
        	  
        	  displayHTML += MDSS.OntologyBrowser.formatLabel(sel) + '\n';
        	}
        	
        	attributeEl.innerHTML = innerHTML;
        	displayEl.innerHTML = displayHTML;
          }
          else
          {
            attributeEl.innerHTML = '';
            displayEl.innerHTML = '';
          }    	  
      }
      else {
    	  if(selected.length > 0)
    	  {
    		  var sel = selected[0];
    		  attributeEl.value = sel.getTermId();
    		  displayEl.innerHTML = MDSS.OntologyBrowser.formatLabel(sel);
    	  }
    	  else
    	  {
    		  attributeEl.value = '';
    		  displayEl.innerHTML = '';
    	  }
      }
    },
    
    openBrowser : function(e, config) {
      // set the default selection (if it exists)
      var browser = config.browser;
      var selected = [];
      var attributeName = config.attributeName;
      
      if(browser.isMultiSelect()) {
        var terms = YAHOO.util.Selector.query('.' + attributeName);
        
        for(var i = 0; i < terms.length; i++) {
          var termId = terms[i].value;
        	
          if(termId !== '')
          {
            selected.push(termId); 
          }
        }
      }
      else {
        var termId = document.getElementById(attributeName).value;

        if(termId !== '')
        {
          selected.push(termId); 
        }
      }
      
      if(browser.isRendered()) {
        browser.show();
        browser.reset();
      }
      else {
        browser.render();
      }
      
      browser.setSelection(selected); 
    }
  }
});

/**
 * Custom BaseCellEditor subclass to spawn an ontology browser instance
 * when a user clicks on a cell that needs to select terms.
 */
YAHOO.widget.OntologyTermEditor = function(oConfigs) {
    this._sId = Mojo.Util.generateId();
    
    this._klass = oConfigs.klass;
    this._attribute= oConfigs.attribute;
    this._browser = new MDSS.OntologyBrowser(false /*, this._klass, this._attribute */); // FIXME pass in klass + attribute
    this._browser.setHandler(this._setSelected, this);
    this._lastSelected = null;
    
    oConfigs.disableBtns = false;

    YAHOO.widget.OntologyTermEditor.superclass.constructor.call(this, "ontology", oConfigs);
};

// OntologyTermEditor extends BaseCellEditor
YAHOO.lang.extend(YAHOO.widget.OntologyTermEditor, YAHOO.widget.BaseCellEditor, {

_setSelected : function(views)
{
  this._lastSelected = views.length > 0 ? views[0] : null;
  this.save();
},

/**
 * Render a form with select element.
 *
 * @method renderForm
 */
renderForm : function() {

  this.getContainerEl().innerHTML = '';
},

/**
 * After rendering form, if disabledBtns is set to true, then sets up a mechanism
 * to save input without them. 
 *
 * @method handleDisabledBtns
 */
handleDisabledBtns : function() {
},

/**
 * Resets OntologyTermEditor UI to initial state.
 *
 * @method resetForm
 */
resetForm : function() {
  this._lastSelected = null;
  this.getContainerEl().innerHTML = '';
},

/**
 * Sets focus in OntologyTermEditor.
 *
 * @method focus
 */
focus : function() {

  if(this._browser.isRendered())
  {
    this._browser.reset();
    this._browser.show();
  }
  else
  {
    this._browser.render();
  }
  
  // TODO get existing term id from cell and put it into the selected array
  var selected = [];
  this._browser.setSelection(selected);
},

/**
 * Retrieves input value from OntologyTermEditor.
 *
 * @method getInputValue
 */
getInputValue : function() {
  return this._lastSelected != null ? this._lastSelected.getTermId() : '';
},

getInputDisplayLabel : function() {
  return this._lastSelected != null ? MDSS.OntologyBrowser.formatLabel(this._lastSelected) : '';
}


});

// Copy static members to OntologyTermEditor class
YAHOO.lang.augmentObject(YAHOO.widget.OntologyTermEditor, YAHOO.widget.BaseCellEditor);