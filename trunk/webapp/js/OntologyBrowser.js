Mojo.Meta.newClass("MDSS.OntologyBrowser", {

  Constants : {
  
    ENTRY_SUFFIX : '_entry',
    BREADCRUMB_SUFFIX : '_breadcrumb',
    SELECT_SUFFIX : '_select',
    DELETE_SUFFIX : '_select',
    SELECTION_SUFFIX : '_selection',
    
  },

  Instance : {
    
    initialize : function(multipleSelect, className, attributeName)
    {
      // map of termId, termName
      this._cache = {};
    
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
      
      // selected terms
      this._selection = {};
      
      // breadcrumb stack
      this._breadcrumbs = [];
      this._breadcrumbs.push({id: 'ROOT', display: MDSS.Localized.ROOT});
      
      this._customHandler = null;
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

    _getRootContent : function()
    {
    
      if(this._defaultRoot)
      {
        var request = new MDSS.Request({
          that : this, 
          onSuccess : function(roots)
          {
          
            var nodes = Mojo.Iter.map(roots, function(browserRootView){
              return this.that._createTermEntry(browserRootView); 
            }, this);
          
            var content = document.getElementById(this.that._contentId);
            content.innerHTML = nodes.join('');
          }
        });
      
        Mojo.$.dss.vector.solutions.ontology.BrowserRoot.getDefaultRoot(request);
      }
      else
      {
        // FIXME fetch roots based on class/attribute
      }
      
    },
    
    _cacheSet: function(termId, view)
    {
      if(view instanceof Mojo.$.dss.vector.solutions.ontology.BrowserRootView)
      {
        // convert a BrowserRootView into a TermView
        var nView = new Mojo.$.dss.vector.solutions.ontology.TermView();
        nView.setTermName(view.getTermName());
        nView.setTermId(view.getTermId());
        
        this._cache[termId] = nView;
      }
      else
      {
        this._cache[termId] = view;
      }
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
      var request = new MDSS.Request({
        that: this,
        termId : termId,
        dontAdd : dontAdd,
        onSuccess : function(query)
        {
          var results = query.getResultSet();
          
          var nodes = Mojo.Iter.map(results, function(termView){
            return this.that._createTermEntry(termView); 
          }, this);
          
          var content = document.getElementById(this.that._contentId);
          content.innerHTML = nodes.join('');
      
          // push selected node onto breadcrumbs
          if(!this.dontAdd)
          { 
            var termName = this.that._cacheGet(this.termId).getTermName();
            this.that._breadcrumbs.push({id: this.termId, display: termName});
          }
          this.that._resetBreadcrumbs();
        }
      });
    
      Mojo.$.dss.vector.solutions.ontology.Term.getOntologyChildren(request, termId);
    },
    
    /**
     * Adds an individual Term as the last child
     * of the selection list.
     */
    _getSelectionLi : function(termId)
    {
      var termName = this._cacheGet(termId).getTermName();
    
      var imgId = termId+this.constructor.DELETE_SUFFIX;
      var liId = termId+this.constructor.SELECTION_SUFFIX;
      var img = '<img src="imgs/icons/delete.png" style="margin-right: 5px" id="'+imgId+'" />';
      
      var li = document.createElement('li');
      li.id = liId;
      li.innerHTML = img+termName;
      
      return li;
    },
    
    newSpan : function(content, id)
    {
      return '<span id="'+id+'" onmouseover="this.style.color=\'#0000ff\'"'+
       ' onmouseout="this.style.color=\'#000000\'">'+content+'</span>';
    },
    
    _resetBreadcrumbs : function()
    {
      var el = document.getElementById(this._breadcrumbId);
      var breadcrumbs = this._getBreadcrumbs();
      el.innerHTML = breadcrumbs;
      
      // move scroll bar as needed to show right-most breadcrumb
      var divParent = el.parentNode;
      var sWidth = divParent.scrollWidth;
      var oWidth = divParent.offsetWidth;
      if(sWidth > oWidth)
      {
        divParent.scrollLeft = sWidth - oWidth;
      }
    },
    
    _getBreadcrumbs : function()
    {
      var last = this._breadcrumbs.length-1;
      var breadcrumbs = Mojo.Iter.map(this._breadcrumbs, function(breadcrumb, ind){
        
        var span = this.newSpan(breadcrumb.display, breadcrumb.id+this.constructor.BREADCRUMB_SUFFIX);
        return '<li class="breadcrumbNavItem'+(ind === last ? ' currentBreadcrumbsNav' : '')+'">'+span+'</li>'; 
      }, this); 
      
      return breadcrumbs.join('<li>/</li>');
    },
    
    render : function()
    {
      this._panel = new YAHOO.widget.Panel(this._id,  {
        width: '400px',
        height: '400px',
        fixedcenter:true,
        close:true,
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
      html += '  <div class="browserBreadcrumbs">';
      html += '    <ul class="breadcrumbsNav" id="'+this._breadcrumbId+'">';
      html += this._getBreadcrumbs();
      html += '    </ul>';
      html += '  </div>';
      html += '  <div class="browserContent">';
      html += '    <ul class="currentNodes" id="'+this._contentId+'">';
      html += '    </ul>';
      html += '  </div>';
      html += '  <div class="browserSelection">';
      html += '    <ul class="selectedNodes" id="'+this._selectionId+'">';
      html += '    </ul>';
      html += '  </div>';
      html += '</div>';
      
      var hideHandlerB = Mojo.Util.bind(this, this._hideHandler);
      this._panel.subscribe('beforeHide', hideHandlerB);
      
      this._panel.setBody(html);
      this._panel.render(document.body);
      this._panel.bringToTop();
     
      this._hookEvents();
      
      this._rendered = true;
      
      this._getRootContent();
    },
    
    setHandler : function(handler, context)
    {
      this._customHandler = Mojo.Util.bind(context || this, handler);
    },
    
    _hideHandler : function()
    {
      if(Mojo.Util.isFunction(this._customHandler));
      {
        var selected = Mojo.Util.getValues(this._selection);
        this._customHandler(selected);
      }
    },
    
    _hookEvents : function()
    {
      var Event = YAHOO.util.Event;
      Event.on(this._breadcrumbId, 'click', this._doNavigate, null, this);
      Event.on(this._contentId, 'click', this._doTermSelectHandler, null, this);
      Event.on(this._selectionId, 'click', this._doSelectionAction, null, this);
      Event.on(this._backButton, 'click', this._goBack, null, this);
    },
    
    _createTermEntry : function(view)
    {
      this._cacheSet(view.getTermId(), view);
    
      var li;
      if(view instanceof Mojo.$.dss.vector.solutions.ontology.TermView || view.getSelectable())
      {
        var imgId = view.getTermId()+this.constructor.SELECT_SUFFIX;
        li = '<li><img src="imgs/icons/accept.png" style="margin-right: 5px" id="'+imgId+'" />';
      }
      else
      {
        li = '<li style="padding-left: 21px">';
      }
      
      return li+this.newSpan(view.getTermName(), view.getTermId()+this.constructor.ENTRY_SUFFIX)+'</li>'
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
      if(termId === 'ROOT')
      {
        newEndInd = 1;
        isRoot = true;
      }
      else
      {
        Mojo.Iter.forEach(this._breadcrumbs, function(breadcrumb, ind){
          
          if(breadcrumb.id === termId)
          {
            newEndInd = ind;
          }
        }, this);
      }
      
      // cut back the breadcrumbs to the last selection
      this._breadcrumbs = this._breadcrumbs.splice(0, newEndInd);

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
  }
});                                                                                                                                                                                        