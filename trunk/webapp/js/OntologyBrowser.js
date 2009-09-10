Mojo.Meta.newClass("MDSS.OntologyBrowser", {

  Constants : {
  
    ENTRY_SUFFIX : '_entry',
    BREADCRUMB_SUFFIX : '_breadcrumb',
    SELECT_SUFFIX : '_select',
    DELETE_SUFFIX : '_select',
    SELECTION_SUFFIX : '_selection',
    
  },

  Instance : {
    
    initialize : function(ontology, instance, relationship, multipleSelect)
    {
      this._ontology = ontology;
      this._instance = instance;
      this._relationship = relationship;
      this._multipleSelect = multipleSelect || false;
      
      this._rendered = false;
      
      this._panel = null;
      this._id = new String(Math.random()).substring(2);
      this._breadcrumbId = this._id+'_breadcrumb';
      this._contentId = this._id+'_contentId';
      this._selectionId = this._id+'_selection';
      
      // selected terms
      this._selection = {};
      
      // breadcrumb stack
      this._breadcrumbs = [];
      this._breadcrumbs.push({id: 'ROOT', display: MDSS.Localized.ROOT});
      
      this._rootNodes = []; // used to regenerate default state
    },
    
    show : function()
    {
      this.panel.show();
      this.panel.bringToTop();
    },
    
    hide : function()
    {
      this.panel.hide();
    },

    _getRootContent : function()
    {
      // will be an ajax call
      var roots = MDSS.MO.roots;
      var rootEntries = Mojo.Iter.map(roots, function(root){
        return this._createTermEntry(root);
      }, this);
      
      return rootEntries.join('');
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
      var term = MDSS.MO.terms[termId];
      
      if(!this._multipleSelect)
      {
        // reset entire selection
        this._selection = {};
        selection.innerHTML = '';
      }

      this._selection[termId] = term;
      var li = this._getSelectionLi(term);
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
    
    _doTermSelect : function(termId)
    {
      var term = MDSS.MO.terms[termId]; // span id == term id
      var nodes = this._getChildren(term);
      var content = document.getElementById(this._contentId);
      content.innerHTML = nodes;

      // push selected node onto breadcrumbs 
      this._breadcrumbs.push({id: term.getId(), display: term.getTerm()});
      this._resetBreadcrumbs();
    },
    
    /**
     * Adds an individual Term as the last child
     * of the selection list.
     */
    _getSelectionLi : function(term)
    {
      var imgId = term.getId()+this.constructor.DELETE_SUFFIX;
      var liId = term.getId()+this.constructor.SELECTION_SUFFIX;
      var img = '<img src="imgs/icons/delete.png" style="margin-right: 5px" id="'+imgId+'" />';
      
      var li = document.createElement('li');
      li.id = liId;
      li.innerHTML = img+term.getTerm();
      
      return li;
    },
    
    /**
     * Returns the HTML to represent all selections. Used
     * for prepopulating the widget.
     */
    _getSelection : function()
    {
      /*
      var terms = Mojo.Util.getValues(this._selection);
      terms.sort(function(t1, t2){
        return t1.getTerm() < t2.getTerm();
      });
      
      var selected = Mojo.Iter.map(terms, function(term){
     
        var li = this._getSelectionLi(term);
      }, this);
      
      return selected.join('');
      */
      
      return ''; 
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
      html += '  <div class="browserBreadcrumbs">';
      html += '    <ul class="breadcrumbsNav" id="'+this._breadcrumbId+'">';
      html += this._getBreadcrumbs();
      html += '    </ul>';
      html += '  </div>';
      html += '  <div class="browserContent">';
      html += '    <ul class="currentNodes" id="'+this._contentId+'">';
      html += this._getRootContent();     
      html += '    </ul>';
      html += '  </div>';
      html += '  <div class="browserSelection">';
      html += '    <ul class="selectedNodes" id="'+this._selectionId+'">';
      html += this._getSelection();
      html += '    </ul>';
      html += '  </div>';
      html += '</div>';
      
      this._panel.setBody(html);
      this._panel.render(document.body);
      this._panel.bringToTop();
     
      this._hookEvents();
      
      this._rendered = true;
    },
    
    _hookEvents : function()
    {
      var Event = YAHOO.util.Event;
      Event.on(this._breadcrumbId, 'click', this._doNavigate, null, this);
      Event.on(this._contentId, 'click', this._doTermSelectHandler, null, this);
      Event.on(this._selectionId, 'click', this._doSelectionAction, null, this);
    },
    
    _createTermEntry : function(term)
    {
      var li;
      if(term.getSelectable())
      {
        var imgId = term.getId()+this.constructor.SELECT_SUFFIX;
        li = '<li><img src="imgs/icons/accept.png" style="margin-right: 5px" id="'+imgId+'" />';
      }
      else
      {
        li = '<li style="padding-left: 21px">';
      }
      
      return li+this.newSpan(term.getTerm(), term.getId()+this.constructor.ENTRY_SUFFIX)+'</li>'
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
      var html = this._getRootContent();
      document.getElementById(this._contentId).innerHTML = html;
      this._resetBreadcrumbs();
    },
    
    _getChildren : function(term)
    {
      // FIXME fetch based on relationship    
      var children = term.getChildren();
      if(children == null)
      {
        return '<li>NO MORE CHILDREN</li>';
      }
       
      var lis = Mojo.Iter.map(children, function(child){
        return this._createTermEntry(child); 
      }, this);
      
      return lis.join('');
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

Mojo.Meta.newClass('MDSS.MO', {

  Instance : {
 
     initialize : function(term, id, selectable)
     {
       this._term = term;
       this._id = id; 
       this._selectable = selectable;
     },
     
     getTerm : function(){ return this._term; },
     getId : function(){ return this._id; },
     getSelectable : function() { return this._selectable; },
     
     getChildren : function()
     {
       return this.constructor.mapping[this.getId()];
     },
  },

  Static : {
    roots : [],
    mapping : {},
    terms : {}
  }
});

(function(){

  function genTerm(level, selectable)
  {
    var id = new String(Math.random()).substring(2);
    var mo = new MDSS.MO('T'+level.toString()+'_'+id, id, selectable);
    MDSS.MO.terms[id] = mo;
    return mo; 
  }

  function addChildren(mo, level)
  {
    level++;
  
    if(level === 5)
    {
      return;
    }
    else
    {
      var numChildren = Math.round(Math.random() * 9+1);
      var children = [];
      for(var i=0; i<numChildren; i++)
      {
        var child = genTerm(level, Boolean(Math.round(Math.random())));
        children.push(child);
        addChildren(child, level);
      }
      
      MDSS.MO.mapping[mo.getId()] = children;
      
      level--;
    }
  }

  // create roots
  var level = 0;
  var numRoots = Math.round(Math.random() * 9+1);
  for(var i=0; i<numRoots; i++)
  {
    var mo = genTerm(level, true);
    MDSS.MO.roots.push(mo);
    
    addChildren(mo, level);
  }

})();
                                                                                                                                                                                            