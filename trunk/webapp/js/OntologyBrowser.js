
// FIXME put focus on search text when browser opens
// FIXME have enter keypress fire save event on browser

Mojo.Meta.newClass("MDSS.OntologyBrowser", {

  Constants : {
  
    ENTRY_SUFFIX : '_entry',
    BREADCRUMB_SUFFIX : '_breadcrumb',
    SELECT_SUFFIX : '_select',
    DELETE_SUFFIX : '_select',
    SELECTION_SUFFIX : '_selection'
  },

  Instance : {
    
    initialize : function(multipleSelect)
    {

      // map of termId, termName
      this._cache = {};
      
      // map of termId to its children
      this._childCache = {};
      
      // temporary cache of TermViews while a search is being performed
      this._searchCache = {};
    
      this._multipleSelect = multipleSelect || false;
      
      // is this browser to render all terms (for field admin) or
      // is it to render specific roots for a class attribute?
      if(arguments.length === 1)
      {
        this._defaultRoot = true;
      }
      else if(arguments.length == 2)
      {
        // id of MdAttribute passed in
        this._mdAttributeId = arguments[1];
      }
      else if(arguments.length === 3 && arguments[1] === true)
      {
        this._universalType = arguments[2];
      }
      else
      {
        // class name and attribute passed as params
        this._className = arguments[1];
        this._attributeName = arguments[2];
      }
      
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
      this._cancelHandler = null;
      
      this._searchPanel = null;
      
      // List of Term ids that represent the current displayed terms
      // that are in actuality possible parents of other terms.
      this._currentParents = [];
    },
    
    show : function()
    {
      this._panel.show();
      this._panel.bringToTop();
      
      this._focusSearch();
    },
    
    getDisplay : function(termId)
    {
      if(this._cache[termId])
      {
        var obj = this._cache[termId];
        if(obj instanceof Mojo.$.com.runwaysdk.business.ViewDTO)
        {
          return this.constructor.formatLabelFromView(obj);
        }
        else
        {
          return this.constructor.formatLabelFromValueObject(obj);
        }
      }
      else
      {
        return null;
      }
    },
    
    /**
   * Puts focus on the search input and clears any previous value.
   */
    _focusSearch : function()
    {      
      setTimeout(Mojo.Util.bind(this, function(){
        var search = document.getElementById(this._searchInput);        
        search.value = '';
        
        search.focus();      
      }),15);
    },
      
    hide : function()
    {
      this._panel.hide();
      
      if(this._cancelHandler != null)
      {
      this._cancelHandler();
      }
    },
    
    /**
   * Sets the content of the main pane with a list of items that map to the
   * given TermView objects.
   */
    _setContent : function(views)
    {
      // new content is being added so clear prior parent term ids.
      this._currentParents = [];
    
      var nodes = Mojo.Iter.map(views, function(view){
         
         this._currentParents.push(view.getTermId());
         return this._createTermEntry(view); 
      }, this);
          
      var content = document.getElementById(this._contentId);
      content.innerHTML = nodes.join('');
    },

    /**
   * Fetches and populates the root nodes for the ontology browser. If no
   * restricting class and attribute are specified then the system-wide
   * default roots are returned (terms that are never children in a
   * relationship).
   */
    _getRootContent : function()
    {
      var cached = this._getCachedChildren(this._ROOT);
      if(cached)
      {
        this._setContent(cached);
        this._focusSearch();
      }
      else
      {
        var request = new MDSS.Request({
          that : this, 
          onSuccess : function(roots)
          {
            this.that._setCachedChildren(this.that._ROOT, roots);
            this.that._setContent(roots);
            this.that._focusSearch();
          }
        });
     
        if(this._defaultRoot)
        {
          Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getDefaultRoot(request);
        }
        else if(this._universalType)
        {
          Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getDefaultGeoRoots(request, this._universalType);
        }
        else if(this._mdAttributeId)
        {
          Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getAttributeRoots(request, '', this._mdAttributeId);
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
        
        if(!this.isMultiSelect())
        {
          this._save();
        }
      }
    },
    
    _addToSelection : function(termId)
    {
      if(this._selection[termId])
      {
        // term has already been added
        if(!this.isMultiSelect())
        {
          this.hide();
        }
        
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
      
      
      if(!this.isMultiSelect())
      {
        // this._save();
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
    
        Mojo.$.dss.vector.solutions.ontology.Term.getOntologyChildren(request, termId, true);
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
   * Adds an individual Term as the last child of the selection list.
   */
    _getSelectionLi : function(termId)
    {
      var term = this._cacheGet(termId);
    
      var imgId = termId+this.constructor.DELETE_SUFFIX;
      var liId = termId+this.constructor.SELECTION_SUFFIX;
      var img = '<img src="imgs/icons/delete.png" style="margin-right: 5px" id="'+imgId+'" />';
      
      var li = document.createElement('li');
      li.id = liId;
      
      var label = this._displayFunction(term);
      
      li.innerHTML = img + label;
      
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
      // add an artificial TermView as the root
      var rootView = new Mojo.$.dss.vector.solutions.ontology.TermView();
      rootView.setTermId(this._ROOT);
      rootView.setTermName(MDSS.localize('ROOT'));
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
      html += '    <div class="browserResizeRestrict">'; // restricts
                              // auto-sizing
      html += '    <ul class="breadcrumbsNav" id="'+this._breadcrumbId+'">';
      html += this._getBreadcrumbs();
      html += '    </ul>';
      html += '    </div>';
      html += '    </div>';
      html += '  </div>';
      html += '  <div class="browserSearch">';
      html += '    <span>'+MDSS.localize('Search')+':</span>';
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
      html += '    <input type="button" id="'+this._cancelButton+'" class="browserCancelButton" value="'+MDSS.localize('Cancel')+'" />';
      html += '    <input type="button" id="'+this._saveButton+'" class="browserSaveButton" value="'+MDSS.localize('save')+'" />';
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
    
    _displayFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
      }

      return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
    },
    
    _listFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView)
      {
        var termId = valueObject.getTermId();
        this._searchCache[termId] = valueObject;
      
        return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
      }
      else
      {
        var termId = valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
        this._searchCache[termId] = valueObject;

        return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
      }
    },
    
    _idFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return valueObject.getTermId();
      }
    
      return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
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
      
      if(!this.isMultiSelect())
      {
        this._save();
      }
    },
    
    _searchFunction : function(request, value)
    {
      Mojo.$.dss.vector.solutions.ontology.Term.termQuery(request, value, this._currentParents);
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
      
      // hide the search panel when the main panel is hidden.
      var that = this;
      this._panel.subscribe('beforeHide', function(){
        that._searchPanel.hide();
      });
    },
    
    isRendered : function()
    {
      return this._rendered;
    },
    
    /**
   * Resets the browser to its default state, meaning the breadcrumbs will go
   * back to root, the default roots will be displayed, and no terms will be
   * shown as selected.
   */
    reset : function()
    {
      this._breadcrumbs = this._breadcrumbs.slice(0, 1);
    
      this._resetToDefault();
      this._resetSelection();
    },
    
    /**
   * Sets the currently selected Term(s) by providing an array of term ids.
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
            
            var total = this.cached.concat(views);
            
            // Now reset the selection
            Mojo.Iter.forEach(total, function(term){
              
              // must add term to the cache first
              var id = this._idFunction(term)
              this._cacheSet(id, term);
              
              this._addToSelection(id); 
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
          this._addToSelection(this._idFunction(view)); 
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
    
    setCancelHandler : function(handler, context)
    {
      this._cancelHandler = Mojo.Util.bind(context || this, handler);
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
        var imgId = this._idFunction(view) + this.constructor.SELECT_SUFFIX;
        li = '<li><img src="imgs/icons/add.png" style="margin-right: 5px;position:relative; top:3px;" id="'+imgId+'" />';
      }
      else
      {
        li = '<li style="padding-left: 21px">';
      }
      
      var content = this._displayFunction(view);
      return li + this.newSpan(content, this._idFunction(view) + this.constructor.ENTRY_SUFFIX)+'</li>';
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
  
    formatLabelFromView : function(term)
    {
      return MDSS.OntologyBrowser.formatLabel(term.getTermName(), term.getTermOntologyId());
    },
    
    formatLabelFromValueObject : function(valueObject)
    {
      var displayLabel = valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.TERMDISPLAYLABEL);
      var termId = valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.TERMID);

      return MDSS.OntologyBrowser.formatLabel(displayLabel, termId);
    },
    
    formatLabel : function(label, termId) {
      return label;
    },
    
    // Extracts the term name from the display created with
  // formatLabelFromView()
    extractName : function(html)
    {
      html = Mojo.Util.trim(html);
      html = html.replace(/ \(.*?\)$/, '');
      
      return html;
    }
  }
});

Mojo.Meta.newClass("MDSS.OntologyValidator", {
  Instance : {
    initialize : function(attributeName, search, getParameters, setField){
      this._attributeEl = document.getElementById(attributeName);
      this._displayEl = document.getElementById(attributeName + 'Display');        
      this._button = document.getElementById(attributeName + 'Btn');        
      
      this._search = search;
      this._getParameters = getParameters;
      this.setField = setField;
      
      // Setup the validator
      YAHOO.util.Event.on(this._displayEl, "blur", this._blurHandler, this, this);      
    },
    
    _blurHandler : function(e) {
      if(e) {
        var blurEl = e.explicitOriginalTarget || document.activeElement;
        
        if(blurEl.parentNode != null && blurEl.parentNode == this._button) {
          return;
        }
          
        var ul = YAHOO.util.Dom.getAncestorByClassName(blurEl, "selectableList")
          
        if(ul) {
          return; 
        }
          
        this._validateSelection();
      }       
    },

    _validateSelection : function() {
      var termId = this._displayEl.value;        
      var concreteId = this._attributeEl.value;
      
      if((termId != null && termId != '') && (concreteId == null || concreteId == '')) {
        MDSS.Calendar.removeError(this._button);          
        MDSS.Calendar.addError(this._button, MDSS.localize("UNKNOWN_TERM"));
      }      
      else {
       MDSS.Calendar.removeError(this._button);      
     }
   }
  }
});

Mojo.Meta.newClass("MDSS.GenericOntologyBrowser", {
  Instance : {
    initialize : function(className, configs) {
      var config = Mojo.Util.isArray(configs) && configs.length > 0 ? configs[0] : configs;
    
      this._attributeName = config.attributeName;
      this._attributeClass = Mojo.Util.isString(config.className) ? config.className : className;
      this._browserField = Mojo.Util.isString(config.browserField) ? config.browserField : config.attributeName;
      this._enabled = Mojo.Util.isBoolean(config.enabled) ? config.enabled : true;
        
      this._attributeEl = document.getElementById(this._attributeName);
      this._displayEl = document.getElementById(this._attributeName + 'Display');        
      this._button = document.getElementById(this._attributeName + 'Btn');
      
      this._roots = [];
       
      // Setup the ontology browser
      this._browser = new MDSS.OntologyBrowser(false, this._attributeClass, this._browserField);            
      this._browser.setHandler(Mojo.Util.bind(this, this.setField));
        
      if(this._enabled) {
        YAHOO.util.Event.on(this._button, "click", this.openBrowser, {browser:this._browser, attributeName:this._attributeName});
      }
               
      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._displayFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var selF = Mojo.Util.bind(this, this._selectFunction);
      
      this._search = new MDSS.GenericSearch(this._displayEl, this._attributeEl, lF, dF, iF, sF, selF);     
      
      // Setup validator      
      var gP = Mojo.Util.bind(this, this._getParameters);
      var sF = Mojo.Util.bind(this, this.setField);
      
      new MDSS.OntologyValidator(this._attributeName, this._search, gP, sF);
    },
    
    addRoot : function (root) {
      this._roots.push(root);
    },
    
    _getParameters : function() {
      return [this._attributeClass, this._attributeName];
    },
    
    _searchFunction : function(request, value) {
      if(this._roots.length > 0) {
        Mojo.$.dss.vector.solutions.ontology.Term.searchByRoots(request, value, this._roots);    
      }
      else {
        var parameters = this._getParameters();
      
        Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request, value, parameters);    
      }
    },
    
    _selectFunction : function () {
      MDSS.Calendar.removeError(this._button);      
    },
        
    setField : function(selected) {
      if(selected.length > 0) {
        var sel = selected[0];
        
        this._attributeEl.value = this._idFunction(sel);
        this._displayEl.value = this._displayFunction(sel);
      }
      else
      {
        this._attributeEl.value = '';
        this._displayEl.value = '';
      }

      MDSS.Calendar.removeError(this._button);
    },
    
    openBrowser : function(e, config) {
      // set the default selection (if it exists)
      var browser = config.browser;
      var selected = [];
      var attributeName = config.attributeName;
      var termId = document.getElementById(attributeName).value;

      if(termId != null && termId !== '')
      {
        selected.push(termId); 
      }
      
      if(browser.isRendered()) {
        browser.show();
        browser.reset();
      }
      else {
        browser.render();
      }
      
      browser.setSelection(selected); 
    },
    
    _displayFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
      }

      return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
    },
    
    _idFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return valueObject.getTermId();
      }
    
      return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
    },    
    
    _attachSearch : function(attributeElement, displayElement, searchFunction)
    {
    }    
  }
});

Mojo.Meta.newClass("MDSS.GenericMultiOntologyBrowser", {
  Instance : {
    initialize : function(className, config) {
      this.attributeName = config.attributeName;
      this.attributeClass = Mojo.Util.isString(config.className) ? config.className : className;
      this.browserField = Mojo.Util.isString(config.browserField) ? config.browserField : config.attributeName;       
      this.enabled = Mojo.Util.isBoolean(config.enabled) ? config.enabled : true;
      this.index = -1;
      this.map = {};
      this._roots = [];
          
      // Setup the ontology browser
      this.browser = new MDSS.OntologyBrowser(true, this.attributeClass, this.browserField);
      this.browser.setHandler(Mojo.Util.bind(this, this.setField));     
      
      if(this.enabled == true) {
        YAHOO.util.Event.on(this.attributeName + 'Btn', "click", this.openBrowser, this, this);
      }
          
      // Setup the ontology search
      this.attributeElement = document.getElementById(this.attributeName);
      var dF = Mojo.Util.bind(this, this._displayFunction);
      var iF = Mojo.Util.bind(this, this._idFunction);
      var lF = Mojo.Util.bind(this, this._displayFunction);
      var sF = Mojo.Util.bind(this, this._searchFunction);
      var sH = Mojo.Util.bind(this, this._selectionHandler);
      
      var search = new MDSS.GenericSearch(this.attributeElement, null, lF, dF, iF, sF, sH); 
    },

    setField : function(selected) {
      // this: the browser instances

      var resultEl = document.getElementById(this.attributeName + 'ResultList');        

      if(selected.length > 0) {
        this.map = {};
        var innerHTML = '';
        
        for(var i = 0; i < selected.length; i++) {
          var label = this._displayFunction(selected[i]);
          var id = this._idFunction(selected[i]);
        
          innerHTML += this._getInnerHTML(i, label, id);
          
          this.map[id] = label;
        }

        resultEl.innerHTML = innerHTML;
        this.index = selected.length;
      }
      else {
        resultEl.innerHTML = '';
        this.index = 0;
        this.map = {};        
      }      
    },        

    openBrowser : function(e) {
      // set the default selection (if it exists)
      var browser = this.browser;
      var selected = [];
      var attributeName = this.attributeName;

      var terms = YAHOO.util.Selector.query('.' + attributeName);

      for(var i = 0; i < terms.length; i++) {
        var termId = terms[i].value;

        if(termId != null && termId !== '') {
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
    },
    
    _nextTermNumber : function()
    { 
      return ++this.index;
    },
        
    addRoot : function (root) {
      this._roots.push(root);
    },
    
    addSelection : function(label, id)
    {
      // IMPORTANT: Must check that this id is not already being displayed
      if(this.map[id] == null)
      {
        var resultEl = document.getElementById(this.attributeName + 'ResultList');        

        var index = this._nextTermNumber();
      
        var innerHTML = this._getInnerHTML(index, label, id);

        resultEl.innerHTML += innerHTML; 
        
        this.map[id] = label;
      }
    },
    
    _getInnerHTML : function(index, label, id)
    {
      var component = this.attributeName + '_' + index;

      innerHTML = '<li>\n';
      innerHTML += '<input type="hidden" class="' + this.attributeName + '" name="' + component + '.componentId" value="' + id + '" />\n';
      innerHTML += '<input type="hidden" name="' + component + '.termDisplayLabel" value="' + label + '" />\n';
      innerHTML += '<input type="hidden" name="' + component + '.isNew" value="false" />\n';
      innerHTML += label + '\n';
      innerHTML += '<li>\n';
      
      return innerHTML;
    },
    
    _displayFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return MDSS.OntologyBrowser.formatLabelFromView(valueObject);
      }

      return MDSS.OntologyBrowser.formatLabelFromValueObject(valueObject);
    },
    
    _idFunction : function(valueObject)
    {
      if(valueObject instanceof Mojo.$.dss.vector.solutions.ontology.TermView || valueObject instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        return valueObject.getTermId();
      }
    
      return valueObject.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
    },    
    
    _searchFunction : function(request, value)
    {
      if(this._roots.length > 0) {
        Mojo.$.dss.vector.solutions.ontology.Term.searchByRoots(request, value, this._roots);    
      }
      else {
        var parameters = [this.attributeClass, this.browserField];
            
        Mojo.$.dss.vector.solutions.ontology.Term.termQueryWithRoots(request, value, parameters);
      }
    },
    
    _selectionHandler : function(selection)
    {
      this.addSelection(selection.label, selection.id);
      
      this.attributeElement.value = '';
    }        
  }
});

/**
 * Custom BaseCellEditor subclass to spawn an ontology browser instance when a
 * user clicks on a cell that needs to select terms.
 */
YAHOO.widget.OntologyTermEditor = function(oConfigs) {
    this._sId = Mojo.Util.generateId();
    
    this._klass = oConfigs.klass;
    this._attribute= oConfigs.attribute;
    
    var attributeName = this._attribute.substring(0,1).toLowerCase() + this._attribute.substring(1);
    
    this._browser = new MDSS.OntologyBrowser(false , this._klass, attributeName);
    
    this._browser.setHandler(this._setSelected, this);
    this._browser.setCancelHandler(this._refocus, this);
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

_refocus : function()
{
  // Get the current selected cell
  var cell = this.getDataTable().getLastSelectedCell();
 
  // Refocus on the selected cell
  this.getDataTable().focus();
  this.getDataTable().selectCell(cell);
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
 * After rendering form, if disabledBtns is set to true, then sets up a
 * mechanism to save input without them.
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
  
  this.dataGrid = this.getDataTable().dataGrid;
  
  // get existing term id from cell and put it into the selected array
  if(this.dataGrid)
  {    
    var row  = this.getDataTable().getTrIndex(this.getRecord());
    var column = this.getColumn().getField();
  
    var termId = this.dataGrid.getData(row, column);
    var selected = [];
    
    if(Mojo.Util.isString(termId) && termId !== '') {
      selected.push(termId);
    }
    
    this._browser.setSelection(selected);
  }
},

/**
 * Retrieves input value from OntologyTermEditor.
 * 
 * @method getInputValue
 */
getInputValue : function() {
  if(this._lastSelected != null)
  {
    if(this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.TermView || this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
    {
      return this._lastSelected.getTermId();
    }
  
    return this._lastSelected.getValue(Mojo.$.dss.vector.solutions.ontology.Term.ID);
  }
  
  return '';
},

getInputDisplayLabel : function() {
  if(this._lastSelected != null)
  {
    if(this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.TermView || this._lastSelected instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
    {
      return MDSS.OntologyBrowser.formatLabelFromView(this._lastSelected);
    }

    return MDSS.OntologyBrowser.formatLabelFromValueObject(this._lastSelected);
  }
  
  return '';
}


});

// Copy static members to OntologyTermEditor class
YAHOO.lang.augmentObject(YAHOO.widget.OntologyTermEditor, YAHOO.widget.BaseCellEditor);


/**
 * Custom BaseCellEditor subclass to spawn an NumberCellEditor instance when a
 * user clicks on a cell that needs to select terms.
 */
YAHOO.widget.NumberCellEditor = function(oConfigs) {
  YAHOO.widget.NumberCellEditor.superclass.constructor.call(this, oConfigs);
};

// NumberCellEditor extends TextboxCellEditor
YAHOO.lang.extend(YAHOO.widget.NumberCellEditor, YAHOO.widget.TextboxCellEditor, {});

// Copy static members to NumberCellEditor class
YAHOO.lang.augmentObject(YAHOO.widget.NumberCellEditor, YAHOO.widget.TextboxCellEditor);